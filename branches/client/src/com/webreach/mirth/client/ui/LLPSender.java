package com.webreach.mirth.client.ui;

import java.util.Properties;
import java.util.StringTokenizer;

public class LLPSender extends ConnectorClass
{
	Frame parent;
    /** Creates new form LLPSender */
    public LLPSender()
    {
        this.parent = PlatformUI.MIRTH_FRAME;
        name = "LLP Sender";
        initComponents();
    }

    public Properties getProperties()
    {        
        Properties properties = new Properties();
        properties.put("DataType", name);
        String hostIPAddress = hostIPAddressField.getText() + "." + hostIPAddressField1.getText() + "." + hostIPAddressField2.getText() + "." + hostIPAddressField3.getText();
        properties.put("address", hostIPAddress);
        properties.put("port", hostPortField.getText());
        properties.put("ServerTimeout", serverTimeoutField.getText());
        properties.put("BufferSize", bufferSizeField.getText());

        if (keepConnectionOpenYesRadio.isSelected())
            properties.put("KeepConnectionOpen", "YES");
        else
            properties.put("KeepConnectionOpen", "NO");

        properties.put("MaximumRetryCount", maximumRetryCountField.getText());
        properties.put("StartOfMessageCharacter", startOfMessageCharacterField.getText());
        properties.put("EndOfMessageCharacter", endOfMessageCharacterField.getText());
        properties.put("RecordSeparator", recordSeparatorField.getText());
        properties.put("FieldSeparator", fieldSeparatorField.getText());
        return properties;
    }

    public void setProperties(Properties props)
    {
        String hostIPAddress = (String)props.get("address");
        StringTokenizer IP = new StringTokenizer(hostIPAddress, ".");
        if (IP.hasMoreTokens())
            hostIPAddressField.setText(IP.nextToken());
        else
            hostIPAddressField.setText("");
        if (IP.hasMoreTokens())
            hostIPAddressField1.setText(IP.nextToken());
        else
            hostIPAddressField1.setText("");
        if (IP.hasMoreTokens())
            hostIPAddressField2.setText(IP.nextToken());
        else
            hostIPAddressField2.setText("");
        if (IP.hasMoreTokens())
            hostIPAddressField3.setText(IP.nextToken());
        else
            hostIPAddressField3.setText("");
        
        hostPortField.setText((String)props.get("port"));
        serverTimeoutField.setText((String)props.get("ServerTimeout"));
        bufferSizeField.setText((String)props.get("BufferSize"));

        if(((String)props.get("KeepConnectionOpen")).equals("YES"))
            keepConnectionOpenYesRadio.setSelected(true);
        else
            keepConnectionOpenNoRadio.setSelected(true);

        maximumRetryCountField.setText((String)props.get("MaximumRetryCount"));
        startOfMessageCharacterField.setText((String)props.get("StartOfMessageCharacter"));
        endOfMessageCharacterField.setText((String)props.get("EndOfMessageCharacter"));
        recordSeparatorField.setText((String)props.get("RecordSeparator"));
        fieldSeparatorField.setText((String)props.get("FieldSeparator"));
    }

    public void setDefaults()
    {
        hostIPAddressField.setText("127");
        hostIPAddressField1.setText("0");
        hostIPAddressField2.setText("0");
        hostIPAddressField3.setText("1");
        hostPortField.setText("3601");
        serverTimeoutField.setText("");
        bufferSizeField.setText("");
        keepConnectionOpenYesRadio.setSelected(true);
        maximumRetryCountField.setText("");
        startOfMessageCharacterField.setText("");
        endOfMessageCharacterField.setText("");
        recordSeparatorField.setText("");
        fieldSeparatorField.setText("");
    }
    
    public Properties getDefaults()
    {        
        Properties properties = new Properties();
        properties.put("DataType", name);
        properties.put("address", "127.0.0.1");
        properties.put("port", "3601");
        properties.put("ServerTimeout", "");
        properties.put("BufferSize", "");
        properties.put("KeepConnectionOpen", "YES");
        properties.put("MaximumRetryCount", "");
        properties.put("StartOfMessageCharacter", "");
        properties.put("EndOfMessageCharacter", "");
        properties.put("RecordSeparator", "");
        properties.put("FieldSeparator", "");
        return properties;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        keepConnectionOpenGroup = new javax.swing.ButtonGroup();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        hostPortField = new javax.swing.JTextField();
        serverTimeoutField = new javax.swing.JTextField();
        bufferSizeField = new javax.swing.JTextField();
        maximumRetryCountField = new javax.swing.JTextField();
        startOfMessageCharacterField = new javax.swing.JTextField();
        endOfMessageCharacterField = new javax.swing.JTextField();
        recordSeparatorField = new javax.swing.JTextField();
        fieldSeparatorField = new javax.swing.JTextField();
        keepConnectionOpenYesRadio = new javax.swing.JRadioButton();
        keepConnectionOpenNoRadio = new javax.swing.JRadioButton();
        hostIPAddressField3 = new javax.swing.JTextField();
        hostIPAddressField2 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        hostIPAddressField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        hostIPAddressField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LLP Sender", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0)));
        jLabel13.setText("Keep Connection Open:");

        jLabel15.setText("Buffer Size:");

        jLabel16.setText("Send Timeout (ms):");

        jLabel17.setText("Host Port:");

        jLabel18.setText("Host IP Address:");

        jLabel8.setText("Maximum Retry Count:");

        jLabel10.setText("Start of Message Character:");

        jLabel11.setText("End of Message Character:");

        jLabel12.setText("Record Sparator:");

        jLabel21.setText("Field Separator:");

        keepConnectionOpenYesRadio.setBackground(new java.awt.Color(255, 255, 255));
        keepConnectionOpenGroup.add(keepConnectionOpenYesRadio);
        keepConnectionOpenYesRadio.setText("Yes");
        keepConnectionOpenYesRadio.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        keepConnectionOpenYesRadio.setMargin(new java.awt.Insets(0, 0, 0, 0));

        keepConnectionOpenNoRadio.setBackground(new java.awt.Color(255, 255, 255));
        keepConnectionOpenGroup.add(keepConnectionOpenNoRadio);
        keepConnectionOpenNoRadio.setText("No");
        keepConnectionOpenNoRadio.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        keepConnectionOpenNoRadio.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel25.setText(".");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel26.setText(".");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText(".");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(17, 17, 17)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel21)
                    .add(jLabel12)
                    .add(jLabel11)
                    .add(jLabel10)
                    .add(jLabel8)
                    .add(jLabel13)
                    .add(jLabel15)
                    .add(jLabel16)
                    .add(jLabel17)
                    .add(jLabel18))
                .add(17, 17, 17)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(hostPortField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(serverTimeoutField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(bufferSizeField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(keepConnectionOpenYesRadio)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(keepConnectionOpenNoRadio))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, maximumRetryCountField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, startOfMessageCharacterField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, endOfMessageCharacterField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, recordSeparatorField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, fieldSeparatorField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(hostIPAddressField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel9)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(hostIPAddressField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel26)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(hostIPAddressField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel25)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(hostIPAddressField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel18)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(hostIPAddressField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(hostIPAddressField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(hostIPAddressField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(hostIPAddressField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jLabel9)
                    .add(jLabel26)
                    .add(jLabel25))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel17)
                    .add(hostPortField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel16)
                    .add(serverTimeoutField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel15)
                    .add(bufferSizeField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel13)
                    .add(keepConnectionOpenYesRadio)
                    .add(keepConnectionOpenNoRadio))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel8)
                    .add(maximumRetryCountField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel10)
                    .add(startOfMessageCharacterField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel11)
                    .add(endOfMessageCharacterField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel12)
                    .add(recordSeparatorField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel21)
                    .add(fieldSeparatorField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bufferSizeField;
    private javax.swing.JTextField endOfMessageCharacterField;
    private javax.swing.JTextField fieldSeparatorField;
    private javax.swing.JTextField hostIPAddressField;
    private javax.swing.JTextField hostIPAddressField1;
    private javax.swing.JTextField hostIPAddressField2;
    private javax.swing.JTextField hostIPAddressField3;
    private javax.swing.JTextField hostPortField;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.ButtonGroup keepConnectionOpenGroup;
    private javax.swing.JRadioButton keepConnectionOpenNoRadio;
    private javax.swing.JRadioButton keepConnectionOpenYesRadio;
    private javax.swing.JTextField maximumRetryCountField;
    private javax.swing.JTextField recordSeparatorField;
    private javax.swing.JTextField serverTimeoutField;
    private javax.swing.JTextField startOfMessageCharacterField;
    // End of variables declaration//GEN-END:variables

}
