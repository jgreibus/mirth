package com.webreach.mirth.client.ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

/**
 * Creates the status bar for the Mirth client application.
 */
public class StatusBar extends javax.swing.JPanel
{
    
    /** Creates new form StatusBar */
    public StatusBar()
    {
        initComponents();
        left.setText("Connected to: " + PlatformUI.SERVER_NAME);
        left.setIcon(new ImageIcon(com.webreach.mirth.client.ui.Frame.class.getResource("images/server.png")));
        right.setText("Logged in as: " + PlatformUI.USER_NAME);
        right.setIcon(new ImageIcon(com.webreach.mirth.client.ui.Frame.class.getResource("images/user.png")));
        right.setHorizontalTextPosition(JLabel.LEADING);
        this.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        left = new javax.swing.JLabel();
        right = new javax.swing.JLabel();

        left.setText("jLabel1");

        right.setText("jLabel2");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(left)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 637, Short.MAX_VALUE)
                .add(right))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(left)
                .add(right))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel left;
    private javax.swing.JLabel right;
    // End of variables declaration//GEN-END:variables
    
}
