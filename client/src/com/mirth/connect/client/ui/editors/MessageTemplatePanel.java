/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.client.ui.editors;

import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

import javax.swing.border.TitledBorder;

import com.mirth.connect.client.ui.TemplatePanel;
import com.mirth.connect.client.ui.TransformerType;
import com.mirth.connect.client.ui.TreePanel;
import com.mirth.connect.model.datatype.DataTypeProperties;

public class MessageTemplatePanel extends javax.swing.JPanel {

    private ActionListener templateTitleListener;

    /** Creates new form MessageTreeTemplate */
    public MessageTemplatePanel() {
        initComponents();
    }

    public MessageTemplatePanel(ActionListener templateTitleListener) {
        this.templateTitleListener = templateTitleListener;
        initComponents();

        templatePanelInbound.setInbound(true);
        templatePanelOutbound.setInbound(false);

        try {
            split.addHierarchyListener(new HierarchyListener() {

                public void hierarchyChanged(HierarchyEvent e) {
                    if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
                        split.setDividerLocation(.5); //There we set the initial divider location
                        //split.removeHierarchyListener(this);
                    }
                }
            });
        } catch (Exception e) {
        }
        split.setOneTouchExpandable(true);

        ((TitledBorder) templatePanelInbound.getBorder()).setTitle("Inbound Message Template");
        ((TitledBorder) templatePanelOutbound.getBorder()).setTitle("Outbound Message Template");
    }

    public void setDataTypeEnabled(boolean inboundDataType, boolean inboundProperties, boolean outboundDataType, boolean outboundProperties, TransformerType transformerType) {
        templatePanelInbound.setDataTypeEnabled(inboundDataType, inboundProperties, transformerType);
        templatePanelOutbound.setDataTypeEnabled(outboundDataType, outboundProperties, transformerType);
    }

    public void hideOutbound() {
        split.setBottomComponent(null);
        split.setDividerSize(0);
        split.setOneTouchExpandable(false);
    }

    public void showOutbound() {
        split.setBottomComponent(templatePanelOutbound);
        split.setDividerSize(6);
        split.setDividerLocation(.5);
        split.setResizeWeight(.5);
        split.setOneTouchExpandable(true);
    }

    public void setInboundTreePanel(TreePanel tree) {
        templatePanelInbound.setTreePanel(tree);
    }

    public void setOutboundTreePanel(TreePanel tree) {
        templatePanelOutbound.setTreePanel(tree);
    }

    public TemplatePanel getInboundTemplatePanel() {
        return templatePanelInbound;
    }

    public TemplatePanel getOutboundTemplatePanel() {
        return templatePanelOutbound;
    }

    public String getInboundMessage() {
        return templatePanelInbound.getMessage();
    }

    public String getOutboundMessage() {
        return templatePanelOutbound.getMessage();
    }

    public void setInboundMessage(String msg) {
        templatePanelInbound.setMessage(msg);
    }

    public void setOutboundMessage(String msg) {
        templatePanelOutbound.setMessage(msg);
    }

    public void clearInboundMessage() {
        templatePanelInbound.clearMessage();
    }

    public void clearOutboundMessage() {
        templatePanelOutbound.clearMessage();
    }

    public void setInboundDataType(String dataType) {
        templatePanelInbound.setDataType(dataType);
    }

    public void setOutboundDataType(String dataType) {
        templatePanelOutbound.setDataType(dataType);
    }

    public String getInboundDataType() {
        return templatePanelInbound.getDataType();
    }

    public String getOutboundDataType() {
        return templatePanelOutbound.getDataType();
    }

    public DataTypeProperties getInboundDataProperties() {
        return templatePanelInbound.getDataProperties();
    }

    public DataTypeProperties getOutboundDataProperties() {
        return templatePanelOutbound.getDataProperties();
    }

    public void setInboundDataProperties(DataTypeProperties p) {
        templatePanelInbound.setDataProperties(p);
    }

    public void setOutboundDataProperties(DataTypeProperties p) {
        templatePanelOutbound.setDataProperties(p);
    }

    public void resizePanes() {
        split.setDividerLocation(.5);
        split.setResizeWeight(.5);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        split = new javax.swing.JSplitPane();
        templatePanelInbound = new TemplatePanel(templateTitleListener);
        templatePanelOutbound = new TemplatePanel(templateTitleListener);

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        split.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        split.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        split.setLeftComponent(templatePanelInbound);
        split.setRightComponent(templatePanelOutbound);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(split, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(split, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane split;
    private com.mirth.connect.client.ui.TemplatePanel templatePanelInbound;
    private com.mirth.connect.client.ui.TemplatePanel templatePanelOutbound;
    // End of variables declaration//GEN-END:variables
}
