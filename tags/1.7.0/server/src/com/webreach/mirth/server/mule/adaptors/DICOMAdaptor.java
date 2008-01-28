package com.webreach.mirth.server.mule.adaptors;

import java.util.Iterator;
import java.util.Map;

import com.webreach.mirth.model.Attachment;
import com.webreach.mirth.model.MessageObject;
import com.webreach.mirth.model.converters.DICOMSerializer;
import com.webreach.mirth.model.converters.IXMLSerializer;
import com.webreach.mirth.model.converters.SerializerFactory;
import com.webreach.mirth.server.controllers.MessageObjectController;
import com.webreach.mirth.server.util.UUIDGenerator;

/**
 * Created by IntelliJ IDEA.
 * User: dans
 * Date: Aug 7, 2007
 * Time: 1:28:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class DICOMAdaptor extends Adaptor {
    protected void populateMessage(boolean emptyFilterAndTransformer) throws AdaptorException {

        messageObject.setRawDataProtocol(MessageObject.Protocol.DICOM);
        messageObject.setTransformedDataProtocol(MessageObject.Protocol.XML);
        messageObject.setEncodedDataProtocol(MessageObject.Protocol.DICOM);

        try {
            // Set transformed data
            DICOMSerializer dSerializer = (DICOMSerializer) serializer;
            String message = dSerializer.toXML(source);
            messageObject.setTransformedData(message);

            // Set rawdata on messageobject without attachment data
            if(dSerializer.rawData != null)
                messageObject.setRawData(dSerializer.rawData);
            else
                messageObject.setRawData("");
            // Create attachment
            MessageObjectController moc = MessageObjectController.getInstance();
            if(dSerializer.getPixelData() != null && !dSerializer.getPixelData().isEmpty()) {
                Iterator<String> i = dSerializer.getPixelData().iterator();
                Attachment attachment = new Attachment();
                attachment.setType("DICOM");
                attachment.setMessageId(messageObject.getId());
                while(i.hasNext()){
                    String image = i.next();
                    attachment.setAttachmentId(UUIDGenerator.getUUID());
                    attachment.setData(image.getBytes());
                    attachment.setSize(image.length());
                    moc.insertAttachment(attachment);
                }
                messageObject.setAttachment(true);
            }
            populateMetadataFromXML(message);
        } catch (Exception e) {
            handleException(e);
        }
    }

    @Override
    public IXMLSerializer<String> getSerializer(Map properties) {
        return SerializerFactory.getSerializer(MessageObject.Protocol.DICOM, properties);
    }
}
