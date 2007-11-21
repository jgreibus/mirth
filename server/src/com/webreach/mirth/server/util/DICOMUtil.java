package com.webreach.mirth.server.util;

import ij.plugin.DICOM;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.webreach.mirth.model.Attachment;
import com.webreach.mirth.model.MessageObject;
import com.webreach.mirth.model.converters.DICOMSerializer;
import com.webreach.mirth.server.controllers.MessageObjectController;

/**
 * Created by IntelliJ IDEA.
 * User: 
 * Date: Oct 4, 2007
 * Time: 10:42:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class DICOMUtil {
    public static String getDICOMRawData(MessageObject message) {
        if(message.isAttachment()){
            MessageObjectController mos = MessageObjectController.getInstance();
            ArrayList images = new ArrayList();
            try {
                List<Attachment> attachments = null;
                if(message.getCorrelationId() != null)
                    attachments = mos.getAttachmentsByMessageId(message.getCorrelationId());
                else
                    attachments = mos.getAttachmentsByMessageId(message.getId());
                return mergeHeaderAttachments(message, attachments);
            }
            catch (Exception e){
                e.printStackTrace();
                return message.getRawData();
            }
        }
        else {
            return message.getRawData();
        }
    }
    public static String mergeHeaderAttachments(MessageObject message, List attachments) throws IOException{
        ArrayList images = new ArrayList();
        BASE64Decoder decoder = new BASE64Decoder();
        BASE64Encoder encoder = new BASE64Encoder();
        Iterator i = attachments.iterator();
        while(i.hasNext()){
            Attachment attach = (Attachment) i.next();
            byte[] image = decoder.decodeBuffer(new String(attach.getData()));
            images.add(image);
        }
        byte[] headerData = decoder.decodeBuffer(message.getRawData());
        if(images.size() <= 1){
            byte[] pixelData = (byte[]) images.get(0);
            String dicomString = DICOMSerializer.mergeHeaderPixelData(headerData,pixelData);
            return dicomString;
        }
        else {
            String dicomString = DICOMSerializer.mergeHeaderPixelData(headerData,images);
            return dicomString;
        }
    }
    public static List<Attachment> getMessageAttachments(MessageObject message){
        if(message.isAttachment()){
            MessageObjectController mos = MessageObjectController.getInstance();
            try {
                List<Attachment> attachments = null;
                if(message.getCorrelationId() != null)
                    attachments = mos.getAttachmentsByMessageId(message.getCorrelationId());
                else
                    attachments = mos.getAttachmentsByMessageId(message.getId());
                return attachments;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static String convertDICOM(String imageType,MessageObject message){
            return returnOther(message, imageType);
    }

    public static String returnOther(MessageObject message, String format)
    {
        String encodedData = getDICOMRawData(message);
        BASE64Decoder decoder = new BASE64Decoder();
        BASE64Encoder base64Encoder = new BASE64Encoder();           
        try {
            byte[] rawImage = decoder.decodeBuffer(encodedData);
            ByteArrayInputStream bis = new ByteArrayInputStream(rawImage);
            DICOM dcm = new DICOM(bis);
            dcm.run(message.getType());
            int width = dcm.getWidth();
            int height = dcm.getHeight();
            BufferedImage   bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            ByteArrayOutputStream  f  = new ByteArrayOutputStream();
            Graphics g = bi.createGraphics();
            g.drawImage(dcm.getImage(), 0, 0, null);
            g.dispose();            
            ImageIO.write(bi,format,f);
            return base64Encoder.encode(f.toByteArray());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }    
}
