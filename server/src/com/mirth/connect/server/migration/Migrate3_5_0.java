/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.server.migration;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

import com.mirth.connect.donkey.util.DonkeyElement;
import com.mirth.connect.donkey.util.DonkeyElement.DonkeyElementException;
import com.mirth.connect.model.converters.ObjectXMLSerializer;
import com.mirth.connect.model.util.MigrationException;
import com.mirth.connect.util.ColorUtil;

public class Migrate3_5_0 extends Migrator {
    private Logger logger = Logger.getLogger(getClass());

    @Override
    public void migrate() throws MigrationException {
        migrateChannelMetadata();
    }

    private void migrateChannelMetadata() {
        Map<String, Set<String>> tags = new HashMap<String, Set<String>>();
        PreparedStatement preparedStatement = null;
        ResultSet results = null;

        try {
            Connection connection = getConnection();

            /*
             * MIRTH-1667: Derby fails if autoCommit is set to true and there are a large number of
             * results. The following error occurs: "ERROR 40XD0: Container has been closed"
             */
            connection.setAutoCommit(false);

            DonkeyElement metadataMapElement = new DonkeyElement("<map/>");

            preparedStatement = connection.prepareStatement("SELECT ID, CHANNEL FROM CHANNEL");
            results = preparedStatement.executeQuery();

            while (results.next()) {
                String channelId = "";

                try {
                    channelId = results.getString(1);
                    String channelXml = results.getString(2);

                    // Add metadata entry for this channel
                    DonkeyElement metadataEntry = metadataMapElement.addChildElement("entry");
                    metadataEntry.addChildElement("string", channelId);
                    DonkeyElement metadataElement = metadataEntry.addChildElement("com.mirth.connect.model.ChannelMetadata");

                    DonkeyElement channelElement = new DonkeyElement(channelXml);

                    // Enabled
                    metadataElement.addChildElement("enabled", channelElement.getChildElement("enabled").getTextContent());

                    // Last modified
                    DonkeyElement lastModifiedElement = channelElement.getChildElement("lastModified");
                    if (lastModifiedElement != null) {
                        try {
                            metadataElement.addChildElementFromXml(lastModifiedElement.toXml());
                        } catch (DonkeyElementException e) {
                        }
                    }

                    DonkeyElement propertiesElement = channelElement.getChildElement("properties");

                    // Pruning settings
                    DonkeyElement pruningSettingsElement = metadataElement.addChildElement("pruningSettings");
                    DonkeyElement pruneMetaDataDaysElement = propertiesElement.getChildElement("pruneMetaDataDays");
                    DonkeyElement pruneContentDaysElement = propertiesElement.getChildElement("pruneContentDays");
                    DonkeyElement archiveEnabledElement = propertiesElement.getChildElement("archiveEnabled");
                    if (pruneMetaDataDaysElement != null) {
                        pruningSettingsElement.addChildElement("pruneMetaDataDays", pruneMetaDataDaysElement.getTextContent());
                    }
                    if (pruneContentDaysElement != null) {
                        pruningSettingsElement.addChildElement("pruneContentDays", pruneContentDaysElement.getTextContent());
                    }
                    if (archiveEnabledElement != null) {
                        pruningSettingsElement.addChildElement("archiveEnabled", archiveEnabledElement.getTextContent());
                    }

                    // Tags
                    DonkeyElement tagsElement = propertiesElement.getChildElement("tags");
                    if (tagsElement != null) {
                        try {
                            Set<String> channelTags = ObjectXMLSerializer.getInstance().deserialize(tagsElement.toString(), Set.class);
                            for (String tagName : channelTags) {
                                Set<String> channelList = tags.get(tagName);

                                if (channelList == null) {
                                    channelList = new HashSet<String>();
                                    tags.put(tagName, channelList);
                                }

                                channelList.add(channelId);
                            }
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e) {
                    logger.error("Error migrating metadata for channel " + channelId + ".", e);
                }
            }

            PreparedStatement updateStatement = null;
            try {
                updateStatement = connection.prepareStatement("INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'channelMetadata', ?)");
                updateStatement.setString(1, metadataMapElement.toXml());
                updateStatement.executeUpdate();
            } finally {
                DbUtils.closeQuietly(updateStatement);
            }

            DonkeyElement tagsElement = new DonkeyElement("<set/>");
            if (MapUtils.isNotEmpty(tags)) {
                for (Entry<String, Set<String>> tag : tags.entrySet()) {
                    DonkeyElement tagElement = tagsElement.addChildElement("channelTag");
                    tagElement.addChildElement("id", UUID.randomUUID().toString());
                    tagElement.addChildElement("name", tag.getKey());

                    DonkeyElement channelIds = tagElement.addChildElement("channelIds");
                    for (String channelId : tag.getValue()) {
                        channelIds.addChildElement("string", channelId);
                    }

                    Color newColor = ColorUtil.getNewColor();
                    DonkeyElement bgColor = tagElement.addChildElement("backgroundColor");
                    bgColor.addChildElement("red", String.valueOf(newColor.getRed()));
                    bgColor.addChildElement("blue", String.valueOf(newColor.getBlue()));
                    bgColor.addChildElement("green", String.valueOf(newColor.getGreen()));
                    bgColor.addChildElement("alpha", String.valueOf(newColor.getAlpha()));
                }
            }

            updateStatement = null;
            try {
                updateStatement = connection.prepareStatement("INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'channelTags', ?)");
                updateStatement.setString(1, tagsElement.toXml());
                updateStatement.executeUpdate();
            } finally {
                DbUtils.closeQuietly(updateStatement);
            }

            connection.commit();
        } catch (Exception e) {
            logger.error("Error migrating channel metadata.", e);
        } finally {
            DbUtils.closeQuietly(results);
            DbUtils.closeQuietly(preparedStatement);
        }
    }

    @Override
    public void migrateSerializedData() throws MigrationException {}
}