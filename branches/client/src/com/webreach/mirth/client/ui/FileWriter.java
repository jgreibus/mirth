package com.webreach.mirth.client.ui;

import java.util.Properties;

/** 
 * A form that extends from ConnectorClass.  All methods implemented
 * are described in ConnectorClass.
 */
public class FileWriter extends ConnectorClass
{
    Frame parent;
    
    /** Creates new form FileWriter */
    public final String DATATYPE = "DataType";
    public final String FILE_DIRECTORY = "host";
    public final String FILE_NAME = "outputPattern";
    public final String FILE_APPEND = "outputAppend";
    public final String FILE_CONTENTS = "template";

    public FileWriter()
    {
        this.parent = PlatformUI.MIRTH_FRAME;
        name = "File Writer";
        initComponents();
    }

    public Properties getProperties()
    {
        Properties properties = new Properties();
        properties.put(DATATYPE, name);
        properties.put(FILE_DIRECTORY, directoryField.getText());
        properties.put(FILE_NAME, fileNameField.getText());
        
        if (appendToFileYes.isSelected())
            properties.put(FILE_APPEND, "YES");
        else
            properties.put(FILE_APPEND, "NO");
        
        properties.put(FILE_CONTENTS, fileContentsTextArea.getText());
        return properties;
    }

    public void setProperties(Properties props)
    {
        directoryField.setText((String)props.get(FILE_DIRECTORY));
        fileNameField.setText((String)props.get(FILE_NAME));
        
        if(((String)props.get(FILE_APPEND)).equalsIgnoreCase("YES"))
            appendToFileYes.setSelected(true);
        else
            appendToFileNo.setSelected(true);
        
        fileContentsTextArea.setText((String)props.get(FILE_CONTENTS));
    }

    public void setDefaults()
    {
        directoryField.setText("");
        fileNameField.setText("");
        appendToFileNo.setSelected(true);        
        fileContentsTextArea.setText("");
    }
    
    public Properties getDefaults()
    {
        Properties properties = new Properties();
        properties.put(DATATYPE, name);
        properties.put(FILE_DIRECTORY, "inbox/");
        properties.put(FILE_NAME, "");
        properties.put(FILE_APPEND, "YES");
        properties.put(FILE_CONTENTS, "");
        return properties;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        directoryField = new com.webreach.mirth.client.ui.MirthTextField();
        fileNameField = new com.webreach.mirth.client.ui.MirthTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        fileContentsTextArea = new com.webreach.mirth.client.ui.MirthTextArea();
        jLabel4 = new javax.swing.JLabel();
        appendToFileYes = new com.webreach.mirth.client.ui.MirthRadioButton();
        appendToFileNo = new com.webreach.mirth.client.ui.MirthRadioButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "File Writer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0)));
        jLabel1.setText("Directory:");

        jLabel2.setText("File Name:");

        jLabel3.setText("File Template:");

        fileContentsTextArea.setColumns(20);
        fileContentsTextArea.setRows(5);
        jScrollPane1.setViewportView(fileContentsTextArea);

        jLabel4.setText("Append to file:");

        appendToFileYes.setBackground(new java.awt.Color(255, 255, 255));
        appendToFileYes.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buttonGroup1.add(appendToFileYes);
        appendToFileYes.setText("Yes");
        appendToFileYes.setMargin(new java.awt.Insets(0, 0, 0, 0));

        appendToFileNo.setBackground(new java.awt.Color(255, 255, 255));
        appendToFileNo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buttonGroup1.add(appendToFileNo);
        appendToFileNo.setSelected(true);
        appendToFileNo.setText("No");
        appendToFileNo.setMargin(new java.awt.Insets(0, 0, 0, 0));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel1)
                    .add(jLabel2)
                    .add(jLabel4)
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(appendToFileYes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(appendToFileNo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(fileNameField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(directoryField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 288, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(directoryField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(fileNameField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(appendToFileYes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(appendToFileNo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel3)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 235, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.webreach.mirth.client.ui.MirthRadioButton appendToFileNo;
    private com.webreach.mirth.client.ui.MirthRadioButton appendToFileYes;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.webreach.mirth.client.ui.MirthTextField directoryField;
    private com.webreach.mirth.client.ui.MirthTextArea fileContentsTextArea;
    private com.webreach.mirth.client.ui.MirthTextField fileNameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
