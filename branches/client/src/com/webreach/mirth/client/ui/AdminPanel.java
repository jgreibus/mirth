package com.webreach.mirth.client.ui;

import com.webreach.mirth.client.core.ClientException;
import java.util.Properties;
import java.util.prefs.Preferences;
import javax.swing.JFrame;

public class AdminPanel extends javax.swing.JPanel
{
    public Users u;

    private static Preferences userPreferences;
    private Frame parent;

    /** Creates new form AdminPanel */
    public AdminPanel()
    {
        this.parent = PlatformUI.MIRTH_FRAME;
        initComponents();
        u = new Users();
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(users);
        users.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(u, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(u, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        
        loadSettings();
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
        adminPanel = new javax.swing.JTabbedPane();
        users = new javax.swing.JPanel();
        settings = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        smtpPassword = new com.webreach.mirth.client.ui.MirthPasswordField();
        smtpUsername = new com.webreach.mirth.client.ui.MirthTextField();
        smtpPort = new com.webreach.mirth.client.ui.MirthTextField();
        smtpHost = new com.webreach.mirth.client.ui.MirthTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        intervalTime = new com.webreach.mirth.client.ui.MirthTextField();
        rowHighlightYes = new com.webreach.mirth.client.ui.MirthRadioButton();
        rowHighlightNo = new com.webreach.mirth.client.ui.MirthRadioButton();

        users.addComponentListener(new java.awt.event.ComponentAdapter()
        {
            public void componentShown(java.awt.event.ComponentEvent evt)
            {
                usersComponentShown(evt);
            }
        });

        org.jdesktop.layout.GroupLayout usersLayout = new org.jdesktop.layout.GroupLayout(users);
        users.setLayout(usersLayout);
        usersLayout.setHorizontalGroup(
            usersLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 631, Short.MAX_VALUE)
        );
        usersLayout.setVerticalGroup(
            usersLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 575, Short.MAX_VALUE)
        );
        adminPanel.addTab("Users", users);

        settings.setBackground(new java.awt.Color(255, 255, 255));
        settings.addComponentListener(new java.awt.event.ComponentAdapter()
        {
            public void componentShown(java.awt.event.ComponentEvent evt)
            {
                settingsComponentShown(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Server", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0)));
        jLabel3.setText("Alerts:");

        jLabel4.setText("SMTP Host:");

        jLabel5.setText("SMTP Port:");

        jLabel6.setText("Username:");

        jLabel7.setText("Password:");

        smtpPassword.setFont(new java.awt.Font("Tahoma", 0, 11));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel3))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(72, 72, 72)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel6)
                            .add(jLabel5)
                            .add(jLabel4)
                            .add(jLabel7))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(smtpUsername, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(smtpPassword, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(smtpPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(smtpHost, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 117, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(347, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(smtpHost, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(smtpPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(smtpUsername, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(smtpPassword, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(135, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Client", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0)));
        jLabel1.setText("Refresh Interval Time (seconds):");

        jLabel2.setText("Alternate Row Highlighting in Tables:");

        rowHighlightYes.setBackground(new java.awt.Color(255, 255, 255));
        rowHighlightYes.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buttonGroup1.add(rowHighlightYes);
        rowHighlightYes.setSelected(true);
        rowHighlightYes.setText("Yes");
        rowHighlightYes.setMargin(new java.awt.Insets(0, 0, 0, 0));

        rowHighlightNo.setBackground(new java.awt.Color(255, 255, 255));
        rowHighlightNo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buttonGroup1.add(rowHighlightNo);
        rowHighlightNo.setText("No");
        rowHighlightNo.setMargin(new java.awt.Insets(0, 0, 0, 0));

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(28, 28, 28)
                        .add(jLabel1))
                    .add(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel2)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(rowHighlightYes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(rowHighlightNo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(intervalTime, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 78, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(327, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(intervalTime, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(rowHighlightYes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(rowHighlightNo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addContainerGap(172, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout settingsLayout = new org.jdesktop.layout.GroupLayout(settings);
        settings.setLayout(settingsLayout);
        settingsLayout.setHorizontalGroup(
            settingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, settingsLayout.createSequentialGroup()
                .addContainerGap()
                .add(settingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        settingsLayout.setVerticalGroup(
            settingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, settingsLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(21, 21, 21)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        adminPanel.addTab("Settings", settings);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, adminPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(adminPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void settingsComponentShown(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_settingsComponentShown
    {//GEN-HEADEREND:event_settingsComponentShown
        parent.setFocus(parent.settingsTasks);
        loadSettings();
    }//GEN-LAST:event_settingsComponentShown

    private void usersComponentShown(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_usersComponentShown
    {//GEN-HEADEREND:event_usersComponentShown
        parent.setFocus(parent.userTasks);
    }//GEN-LAST:event_usersComponentShown
    
    public void loadSettings()
    {
        intervalTime.setDocument(new MirthTextFieldLimit(3, false, true));
        userPreferences = Preferences.systemNodeForPackage(Mirth.class);
        int interval = userPreferences.getInt("intervalTime", 10);
        intervalTime.setText(interval + "");
        
        if(userPreferences.getBoolean("highlightRows", true))
            rowHighlightYes.setSelected(true);
        else
            rowHighlightNo.setSelected(true);
        try
        {
            Properties serverProperties = parent.mirthClient.getServerProperties();
            if(serverProperties.getProperty("smtp.host") != null)
                smtpHost.setText((String)serverProperties.getProperty("smtp.host"));
            else
                smtpHost.setText("");
            
            if(serverProperties.getProperty("smtp.port") != null)
                smtpPort.setText((String)serverProperties.getProperty("smtp.port"));
            else
                smtpPort.setText("");
            
            if(serverProperties.getProperty("smtp.username") != null)
                smtpUsername.setText((String)serverProperties.getProperty("smtp.username"));
            else
                smtpUsername.setText("");
            
            if(serverProperties.getProperty("smtp.password") != null)
                smtpPassword.setText((String)serverProperties.getProperty("smtp.password"));
            else
                smtpPassword.setText("");
        } 
        catch (ClientException ex)
        {
            ex.printStackTrace();
        }
        
    }
    
    public void showTasks()
    {
        if(settings.isVisible())
        {
            parent.setFocus(parent.settingsTasks);
        }
        else
        {
            parent.setFocus(parent.userTasks);
        }
    }

    public void saveSettings()
    {
        int interval = Integer.parseInt(intervalTime.getText());
        
        if(intervalTime.getText().length() == 0)
            parent.alertWarning("Please enter a valid interval time.");
        else if(interval <= 0)
            parent.alertWarning("Please enter an interval time that is larger than 0.");
        else
        {
            userPreferences.putInt("intervalTime",Integer.parseInt(intervalTime.getText()));
            parent.settingsTasks.getContentPane().getComponent(1).setVisible(false);
            userPreferences.putBoolean("highlightRows", rowHighlightYes.isSelected());
            
            Properties serverProperties = new Properties();
            serverProperties.put("smtp.host", smtpHost.getText());
            serverProperties.put("smtp.port", smtpPort.getText());
            serverProperties.put("smtp.username", smtpUsername.getText());
            serverProperties.put("smtp.password", new String(smtpPassword.getPassword()));
            
            try
            {
                parent.mirthClient.updateServerProperties(serverProperties);
            } 
            catch (ClientException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane adminPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.webreach.mirth.client.ui.MirthTextField intervalTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.webreach.mirth.client.ui.MirthRadioButton rowHighlightNo;
    private com.webreach.mirth.client.ui.MirthRadioButton rowHighlightYes;
    private javax.swing.JPanel settings;
    private com.webreach.mirth.client.ui.MirthTextField smtpHost;
    private com.webreach.mirth.client.ui.MirthPasswordField smtpPassword;
    private com.webreach.mirth.client.ui.MirthTextField smtpPort;
    private com.webreach.mirth.client.ui.MirthTextField smtpUsername;
    private javax.swing.JPanel users;
    // End of variables declaration//GEN-END:variables

}
