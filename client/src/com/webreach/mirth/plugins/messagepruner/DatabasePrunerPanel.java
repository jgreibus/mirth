/*
 * DatabasePrunerPanel.java
 *
 * Created on June 22, 2007, 5:19 PM
 */

package com.webreach.mirth.plugins.messagepruner;

import com.webreach.mirth.client.ui.CenterCellRenderer;
import com.webreach.mirth.client.ui.Mirth;
import com.webreach.mirth.client.ui.PlatformUI;
import com.webreach.mirth.client.ui.RefreshTableModel;
import com.webreach.mirth.client.ui.UIConstants;
import com.webreach.mirth.client.ui.components.MirthTable;
import com.webreach.mirth.client.ui.components.MirthTimePicker;
import com.webreach.mirth.model.converters.ObjectXMLSerializer;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Properties;
import java.util.prefs.Preferences;
import org.jdesktop.swingx.decorator.AlternateRowHighlighter;
import org.jdesktop.swingx.decorator.HighlighterPipeline;

/**
 *
 * @author  brendanh
 */
public class DatabasePrunerPanel extends javax.swing.JPanel
{
    private final String NAME_COLUMN_NAME = "Channel";
    private final String TIME_COLUMN_NAME = "Time Pruned";
    private final String NUMBER_COLUMN_NAME = "Messages Pruned";
        
    private ObjectXMLSerializer serializer = new ObjectXMLSerializer();
    
    /** Creates new form DatabasePrunerPanel */
    public DatabasePrunerPanel()
    {
        initComponents();
        makeLogTable();
    }
    
    public void setProperties(Properties properties, LinkedList<String[]> log)
    {
        if(properties.getProperty("interval").equals("hourly"))
        {
            hourButton.setSelected(true);
            hourButtonActionPerformed(null);
        }
        else if(properties.getProperty("interval").equals("daily"))
        {
            dayButton.setSelected(true);
            dayButtonActionPerformed(null);
            timeOfDay.setDate(properties.getProperty("time"));
        }
        else if(properties.getProperty("interval").equals("weekly"))
        {
            weekButton.setSelected(true);
            weekButtonActionPerformed(null);
            dayOfWeek.setDate(properties.getProperty("dayOfWeek"));
            timeOfDayWeekly.setDate(properties.getProperty("time"));
        }
        else if(properties.getProperty("interval").equals("monthly"))
        {
            monthButton.setSelected(true);
            monthButtonActionPerformed(null);
            dayOfMonth.setDate(properties.getProperty("dayOfMonth"));
            timeOfDayMonthly.setDate(properties.getProperty("time"));
        }
        
        updateTable(log);
    }
    
    public Properties getProperties()
    {
        Properties properties = new Properties();
        
        if(hourButton.isSelected())
        {
            properties.put("interval", "hourly");
        }
        else if (dayButton.isSelected())
        {
            properties.put("interval", "daily");
            properties.put("time", timeOfDay.getDate());
        }
        else if(weekButton.isSelected())
        {
            properties.put("interval", "weekly");
            properties.put("time", timeOfDayWeekly.getDate());
            properties.put("dayOfWeek", dayOfWeek.getDate());
        }
        else if(monthButton.isSelected())
        {
            properties.put("interval", "monthly");
            properties.put("time", timeOfDayMonthly.getDate());
            properties.put("dayOfMonth", dayOfMonth.getDate());
        }
            
        return properties;
    }
    
    /**
     * Makes the status table with all current server information.
     */
    public void makeLogTable()
    {
        updateTable(null);

        logTable.setDoubleBuffered(true);

        logTable.setSelectionMode(0);

        logTable.getColumnExt(NUMBER_COLUMN_NAME).setCellRenderer(new CenterCellRenderer());
        logTable.getColumnExt(NUMBER_COLUMN_NAME).setHeaderRenderer(PlatformUI.CENTER_COLUMN_HEADER_RENDERER);

        logTable.packTable(UIConstants.COL_MARGIN);

        logTable.setRowHeight(UIConstants.ROW_HEIGHT);
        logTable.setOpaque(true);
        logTable.setRowSelectionAllowed(false);

        logTable.setSortable(true);

        logPane.setViewportView(logTable);
    }
    
    public void updateTable(LinkedList<String[]> logs)
    {
        Object[][] tableData = null;

        if (logs != null)
        {
            tableData = new Object[logs.size()][3];
            for (int i = 0; i < logs.size(); i++)
            {
                tableData[i][0] = logs.get(i)[0];
                tableData[i][1] = logs.get(i)[1];
                tableData[i][2] = logs.get(i)[2];
            }
        }

        if (logTable != null)
        {
            RefreshTableModel model = (RefreshTableModel) logTable.getModel();
            model.refreshDataVector(tableData);
        }
        else
        {
            logTable = new MirthTable();
            logTable.setModel(new RefreshTableModel(tableData, new String[] { NAME_COLUMN_NAME, TIME_COLUMN_NAME, NUMBER_COLUMN_NAME })
            {
                boolean[] canEdit = new boolean[] { false, false, false };

                public boolean isCellEditable(int rowIndex, int columnIndex)
                {
                    return canEdit[columnIndex];
                }
            });
        }
        
        // Add the highlighters.  Always add the error highlighter.
        HighlighterPipeline highlighter = new HighlighterPipeline();
        
        if (Preferences.systemNodeForPackage(Mirth.class).getBoolean("highlightRows", true))
        {
            highlighter.addHighlighter(new AlternateRowHighlighter(UIConstants.HIGHLIGHTER_COLOR, UIConstants.BACKGROUND_COLOR, UIConstants.TITLE_TEXT_COLOR));
        }

        logTable.setHighlighters(highlighter);
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
        jPanel1 = new javax.swing.JPanel();
        dayButton = new javax.swing.JRadioButton();
        weekButton = new javax.swing.JRadioButton();
        monthButton = new javax.swing.JRadioButton();
        hourButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        dayOfWeekLabel = new javax.swing.JLabel();
        dayOfMonthLabel = new javax.swing.JLabel();
        timeOfDay = new MirthTimePicker("hh:mm aa", Calendar.MINUTE);
        timeOfDayLabel = new javax.swing.JLabel();
        dayOfMonth = new MirthTimePicker("dd", Calendar.MONTH);
        timeOfDayWeeklyLabel = new javax.swing.JLabel();
        timeOfDayWeekly = new MirthTimePicker("hh:mm aa", Calendar.MINUTE);
        timeOfDayMonthlyLabel = new javax.swing.JLabel();
        timeOfDayMonthly = new MirthTimePicker("hh:mm aa", Calendar.MINUTE);
        weeklyAtLabel = new javax.swing.JLabel();
        monthlyAtLabel = new javax.swing.JLabel();
        dayOfWeek = new MirthTimePicker("EEEEEEEE", Calendar.DAY_OF_WEEK);
        jPanel2 = new javax.swing.JPanel();
        logPane = new javax.swing.JScrollPane();
        logTable = null;

        setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Settings", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 0)));
        dayButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(dayButton);
        dayButton.setText("Daily");
        dayButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dayButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dayButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                dayButtonActionPerformed(evt);
            }
        });

        weekButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(weekButton);
        weekButton.setText("Weekly");
        weekButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        weekButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        weekButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                weekButtonActionPerformed(evt);
            }
        });

        monthButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(monthButton);
        monthButton.setText("Monthly");
        monthButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        monthButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        monthButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                monthButtonActionPerformed(evt);
            }
        });

        hourButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(hourButton);
        hourButton.setText("Hourly");
        hourButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        hourButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        hourButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                hourButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("When to prune:");

        dayOfWeekLabel.setText("Day of Week:");

        dayOfMonthLabel.setText("Day of Month:");

        timeOfDayLabel.setText("Time of Day:");

        timeOfDayWeeklyLabel.setText("Time of Day:");

        timeOfDayMonthlyLabel.setText("Time of Day:");

        weeklyAtLabel.setText("at");

        monthlyAtLabel.setText("at");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(hourButton)
                    .add(dayButton)
                    .add(weekButton)
                    .add(monthButton)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(17, 17, 17)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, dayOfMonthLabel)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, timeOfDay, 0, 75, Short.MAX_VALUE)
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, dayOfMonth, 0, 75, Short.MAX_VALUE)
                                            .add(dayOfWeek, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(monthlyAtLabel)
                                            .add(weeklyAtLabel)))
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, dayOfWeekLabel))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(timeOfDayWeeklyLabel)
                                    .add(timeOfDayWeekly, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(timeOfDayMonthlyLabel)
                                    .add(timeOfDayMonthly, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(timeOfDayLabel))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(hourButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dayButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(timeOfDayLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(timeOfDay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(4, 4, 4)
                .add(weekButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(timeOfDayWeeklyLabel)
                    .add(dayOfWeekLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(weeklyAtLabel)
                    .add(timeOfDayWeekly, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dayOfWeek, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(monthButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dayOfMonthLabel)
                    .add(timeOfDayMonthlyLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(monthlyAtLabel)
                    .add(timeOfDayMonthly, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dayOfMonth, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Activity Log", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 0)));
        logPane.setViewportView(logTable);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(logPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(logPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 223, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
        
    private void hourButtonActionPerformed(java.awt.event.ActionEvent evt)                                           
    {                                                       
        dayOfMonthLabel.setEnabled(false);
        dayOfWeekLabel.setEnabled(false);
        timeOfDayLabel.setEnabled(false);
        timeOfDayWeeklyLabel.setEnabled(false);
        timeOfDayMonthlyLabel.setEnabled(false);    
        
        monthlyAtLabel.setEnabled(false);  
        weeklyAtLabel.setEnabled(false);  
                
        dayOfMonth.setEnabled(false);
        dayOfWeek.setEnabled(false);
        timeOfDay.setEnabled(false);
        timeOfDayWeekly.setEnabled(false);
        timeOfDayMonthly.setEnabled(false);
    }                                          

    private void dayButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_dayButtonActionPerformed
    {//GEN-HEADEREND:event_dayButtonActionPerformed
        dayOfMonthLabel.setEnabled(false);
        dayOfWeekLabel.setEnabled(false);
        timeOfDayLabel.setEnabled(true);
        timeOfDayWeeklyLabel.setEnabled(false);
        timeOfDayMonthlyLabel.setEnabled(false);  
        
        monthlyAtLabel.setEnabled(false);  
        weeklyAtLabel.setEnabled(false);  
        
        dayOfMonth.setEnabled(false);
        dayOfWeek.setEnabled(false);
        timeOfDay.setEnabled(true);
        timeOfDayWeekly.setEnabled(false);
        timeOfDayMonthly.setEnabled(false);
    }//GEN-LAST:event_dayButtonActionPerformed

    private void weekButtonActionPerformed(java.awt.event.ActionEvent evt)                                           
    {                                                    
        dayOfMonthLabel.setEnabled(false);
        dayOfWeekLabel.setEnabled(true);
        timeOfDayLabel.setEnabled(false);
        timeOfDayWeeklyLabel.setEnabled(true);
        timeOfDayMonthlyLabel.setEnabled(false);  
        
        monthlyAtLabel.setEnabled(false);  
        weeklyAtLabel.setEnabled(true);  
        
        dayOfMonth.setEnabled(false);
        dayOfWeek.setEnabled(true);
        timeOfDay.setEnabled(false);
        timeOfDayWeekly.setEnabled(true);
        timeOfDayMonthly.setEnabled(false);
    }                                          

    private void monthButtonActionPerformed(java.awt.event.ActionEvent evt)                                            
    {                                                       
        dayOfMonthLabel.setEnabled(true);
        dayOfWeekLabel.setEnabled(false);
        timeOfDayLabel.setEnabled(false);
        timeOfDayWeeklyLabel.setEnabled(false);
        timeOfDayMonthlyLabel.setEnabled(true);  
        
        monthlyAtLabel.setEnabled(true);  
        weeklyAtLabel.setEnabled(false);  
        
        dayOfMonth.setEnabled(true);
        dayOfWeek.setEnabled(false);
        timeOfDay.setEnabled(false);
        timeOfDayWeekly.setEnabled(false);
        timeOfDayMonthly.setEnabled(true);
    }                                           
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton dayButton;
    private com.webreach.mirth.client.ui.components.MirthTimePicker dayOfMonth;
    private javax.swing.JLabel dayOfMonthLabel;
    private com.webreach.mirth.client.ui.components.MirthTimePicker dayOfWeek;
    private javax.swing.JLabel dayOfWeekLabel;
    private javax.swing.JRadioButton hourButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane logPane;
    private com.webreach.mirth.client.ui.components.MirthTable logTable;
    private javax.swing.JRadioButton monthButton;
    private javax.swing.JLabel monthlyAtLabel;
    private com.webreach.mirth.client.ui.components.MirthTimePicker timeOfDay;
    private javax.swing.JLabel timeOfDayLabel;
    private com.webreach.mirth.client.ui.components.MirthTimePicker timeOfDayMonthly;
    private javax.swing.JLabel timeOfDayMonthlyLabel;
    private com.webreach.mirth.client.ui.components.MirthTimePicker timeOfDayWeekly;
    private javax.swing.JLabel timeOfDayWeeklyLabel;
    private javax.swing.JRadioButton weekButton;
    private javax.swing.JLabel weeklyAtLabel;
    // End of variables declaration//GEN-END:variables
    
}
