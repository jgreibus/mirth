/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL
 * license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */

package com.mirth.connect.donkey.server.channel;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.mirth.connect.donkey.model.channel.ChannelState;
import com.mirth.connect.donkey.model.message.ConnectorMessage;
import com.mirth.connect.donkey.model.message.ContentType;
import com.mirth.connect.donkey.model.message.Message;
import com.mirth.connect.donkey.model.message.MessageContent;
import com.mirth.connect.donkey.model.message.RawMessage;
import com.mirth.connect.donkey.model.message.Response;
import com.mirth.connect.donkey.server.StartException;
import com.mirth.connect.donkey.server.StopException;
import com.mirth.connect.donkey.server.data.DonkeyDao;
import com.mirth.connect.donkey.server.data.DonkeyDaoFactory;

/**
 * The base class for all source connectors.
 */
public abstract class SourceConnector extends Connector {
    private Channel channel;
    private boolean respondAfterProcessing = true;
    private MetaDataReplacer metaDataReplacer;
    private String sourceName = "Source";

    private Logger logger = Logger.getLogger(getClass());

    public void setChannel(Channel channel) {
        this.channel = channel;
        setChannelId(channel.getChannelId());
    }

    public boolean isRespondAfterProcessing() {
        return respondAfterProcessing;
    }

    public void setRespondAfterProcessing(boolean respondAfterProcessing) {
        this.respondAfterProcessing = respondAfterProcessing;
    }

    public MetaDataReplacer getMetaDataReplacer() {
        return metaDataReplacer;
    }

    public void setMetaDataReplacer(MetaDataReplacer metaDataReplacer) {
        this.metaDataReplacer = metaDataReplacer;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    @Deprecated
    public boolean isRunning() {
        return getCurrentState() != ChannelState.STOPPED;
    }

    /**
     * Start the connector
     */
    @Override
    public void start() throws StartException {
        setCurrentState(ChannelState.STARTING);

        onStart();

        setCurrentState(ChannelState.STARTED);
    }

    /**
     * Stop the connector
     */
    @Override
    public void stop() throws StopException {
        //TODO make this happen before the poll connector's stop method
        setCurrentState(ChannelState.STOPPING);

        onStop();

        setCurrentState(ChannelState.STOPPED);
    }

    /**
     * Stop the connector
     */
    @Override
    public void halt() throws StopException {
        //TODO make this happen before the poll connector's stop method
        setCurrentState(ChannelState.STOPPING);

        onStop();

        setCurrentState(ChannelState.STOPPED);
    }

    /**
     * Takes a raw message and processes it if the connector is set to wait for
     * the destination
     * connectors to complete, otherwise it queues the message for processing
     * 
     * @param rawMessage
     *            A raw message
     * @return The MessageResponse, containing the message id and a response if
     *         one was received
     * @throws StoppedException
     * @throws ChannelErrorException
     * @throws StoppingException
     */
    public DispatchResult dispatchRawMessage(RawMessage rawMessage) throws ChannelException {
        if (getCurrentState() == ChannelState.STOPPED) {
            ChannelException e = new ChannelException(true);
            logger.error("Source connector is currently stopped. Channel Id: " + channel.getChannelId(), e);
            throw e;
        }

        return channel.dispatchRawMessage(rawMessage);
    }

    /**
     * Handles a response generated for a message that was recovered by the
     * channel
     * 
     * @throws ChannelException
     */
    public abstract void handleRecoveredResponse(DispatchResult dispatchResult);

    /**
     * Finish a message dispatch
     * 
     * @param dispatchResult
     *            The DispatchResult returned by dispatchRawMessage()
     */
    public void finishDispatch(DispatchResult dispatchResult) {
        finishDispatch(dispatchResult, false, null);
    }

    /**
     * Finish a message dispatch
     * 
     * @param dispatchResult
     *            The DispatchResult returned by dispatchRawMessage()
     * @param attemptedResponse
     *            True if an attempt to send a response was made, false if not
     * @param responseError
     *            An error message if an error occurred when attempting to send
     *            a response
     */
    public void finishDispatch(DispatchResult dispatchResult, boolean attemptedResponse, String responseError) {
        String response = null;

        if (dispatchResult != null) {
            Response selectedResponse = dispatchResult.getSelectedResponse();

            if (selectedResponse != null) {
                response = selectedResponse.getMessage();
            }
        }

        finishDispatch(dispatchResult, attemptedResponse, responseError, response);
    }

    /**
     * Finish a message dispatch
     * 
     * @param dispatchResult
     *            The DispatchResult returned by dispatchRawMessage()
     * @param attemptedResponse
     *            True if an attempt to send a response was made, false if not
     * @param responseError
     *            An error message if an error occurred when attempting to send
     *            a response
     * @param response
     *            The response string (if a response attempt was made)
     */
    public void finishDispatch(DispatchResult dispatchResult, boolean attemptedResponse, String responseError, String response) {
        try {
            if (dispatchResult == null) {
                return;
            }

            DonkeyDaoFactory daoFactory = channel.getDaoFactory();
            StorageSettings storageSettings = channel.getStorageSettings();
            DonkeyDao dao = null;
            long messageId = dispatchResult.getMessageId();

            try {
                if (response != null && storageSettings.isStoreSentResponse()) {
                    dao = daoFactory.getDao();
                    //TODO does this have a data type?
                    dao.insertMessageContent(new MessageContent(getChannelId(), messageId, 0, ContentType.RESPONSE, response, null, false));
                }

                if (attemptedResponse || responseError != null) {
                    if (dao == null) {
                        dao = daoFactory.getDao();
                    }

                    Message processedMessage = dispatchResult.getProcessedMessage();
                    ConnectorMessage connectorMessage = null;
                    if (processedMessage != null) {
                        connectorMessage = processedMessage.getConnectorMessages().get(0);
                    } else {
                        Set<Integer> metaDataIds = new HashSet<Integer>();
                        metaDataIds.add(0);

                        /*
                         * If there is no existing connector message, then the channel was either
                         * halted or a runtime exception occurred. In either case, we may still want
                         * to update the response date and error. Therefore, we must retrieve the
                         * existing connector message from the database.
                         */
                        connectorMessage = dao.getConnectorMessages(getChannelId(), messageId, metaDataIds, true).get(0);
                    }

                    // There are no retry attempts for sending the response back, so send attempts is either 0 or 1.
                    if (attemptedResponse) {
                        connectorMessage.setSendAttempts(1);
                        connectorMessage.setResponseDate(dispatchResult.getResponseDate());
                        dao.updateSourceResponse(connectorMessage);
                    }

                    if (responseError != null) {
                        connectorMessage.setResponseError(responseError);
                        dao.updateErrors(connectorMessage);
                    }
                }

                if (dispatchResult.isMarkAsProcessed()) {
                    if (dao == null) {
                        dao = daoFactory.getDao();
                    }

                    dao.markAsProcessed(getChannelId(), messageId);

                    if (dispatchResult.isRemoveContent()) {
                        dao.deleteMessageContent(getChannelId(), messageId);
                    }

                    if (dispatchResult.isRemoveAttachments()) {
                        dao.deleteMessageAttachments(getChannelId(), messageId);
                    }
                }

                if (dao != null) {
                    dao.commit(storageSettings.isDurable());
                }
            } finally {
                if (dao != null) {
                    dao.close();
                }
            }
        } finally {
            if (dispatchResult != null && dispatchResult.isLockAcquired()) {
                channel.releaseProcessLock();
            }
        }
    }
}
