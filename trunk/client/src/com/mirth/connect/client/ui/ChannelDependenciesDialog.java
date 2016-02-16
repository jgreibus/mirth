/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.client.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;

import com.mirth.connect.client.ui.codetemplate.CodeTemplateLibrariesPanel;
import com.mirth.connect.client.ui.codetemplate.CodeTemplatePanel.UpdateSwingWorker;
import com.mirth.connect.model.Channel;
import com.mirth.connect.model.CodeTemplate;
import com.mirth.connect.model.CodeTemplateLibrary;

public class ChannelDependenciesDialog extends MirthDialog {

    private boolean saved;
    private boolean resourcesReady;
    private boolean codeTemplateLibrariesReady;

    public ChannelDependenciesDialog(Channel channel) {
        super(PlatformUI.MIRTH_FRAME, true);

        initComponents(channel);
        initLayout();
        setPreferredSize(new Dimension(450, 434));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Channel Dependencies");
        pack();
        setLocationRelativeTo(PlatformUI.MIRTH_FRAME);

        okButton.setEnabled(false);
        setVisible(true);
    }

    public boolean wasSaved() {
        return saved;
    }

    public Map<Integer, Map<String, String>> getSelectedResourceIds() {
        return resourcesPanel.getSelectedResourceIds();
    }

    public void resourcesReady() {
        resourcesReady = true;
        checkReady();
    }

    public void codeTemplateLibrariesReady() {
        codeTemplateLibrariesReady = true;
        checkReady();
    }

    private void checkReady() {
        if (resourcesReady && codeTemplateLibrariesReady) {
            okButton.setEnabled(true);
        }
    }

    private void initComponents(Channel channel) {
        setBackground(UIConstants.BACKGROUND_COLOR);
        getContentPane().setBackground(getBackground());

        containerPanel = new JPanel();
        tabPane = new JTabbedPane();

        resourcesContainerPanel = new JPanel();
        resourcesContainerPanel.setBackground(getBackground());
        resourcesPanel = new LibraryResourcesPanel(this, channel);

        codeTemplateLibrariesContainerPanel = new JPanel();
        codeTemplateLibrariesContainerPanel.setBackground(getBackground());
        codeTemplateLibrariesPanel = new CodeTemplateLibrariesPanel(this, channel);

        bottomPanel = new JPanel();
        bottomPanel.setBackground(getBackground());
        separator = new JSeparator();

        buttonPanel = new JPanel();
        buttonPanel.setBackground(getBackground());

        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                okButtonActionPerformed();
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cancelButtonActionPerformed();
            }
        });
    }

    private void initLayout() {
        setLayout(new MigLayout("insets 0 0 12 0, novisualpadding, hidemode 3, fill"));

        containerPanel.setLayout(new MigLayout("insets 0, novisualpadding, hidemode 3, fill"));

        codeTemplateLibrariesContainerPanel.setLayout(new MigLayout("insets 12 12 0 12, novisualpadding, hidemode 3, fill"));
        codeTemplateLibrariesContainerPanel.add(codeTemplateLibrariesPanel, "grow");
        tabPane.add("Code Template Libraries", codeTemplateLibrariesContainerPanel);

        resourcesContainerPanel.setLayout(new MigLayout("insets 12 12 0 12, novisualpadding, hidemode 3, fill"));
        resourcesContainerPanel.add(resourcesPanel, "grow");
        tabPane.add("Library Resources", resourcesContainerPanel);

        containerPanel.add(tabPane, "grow, push");
        add(containerPanel, "grow, push");

        bottomPanel.setLayout(new MigLayout("insets 0 12 0 12, novisualpadding, hidemode 3, fill"));
        bottomPanel.add(separator, "grow");
        buttonPanel.setLayout(new MigLayout("insets 0, novisualpadding, hidemode 3"));
        buttonPanel.add(okButton, "w 48!");
        buttonPanel.add(cancelButton, "w 48!");
        bottomPanel.add(buttonPanel, "newline, right");
        add(bottomPanel, "newline, grow");
    }

    private void okButtonActionPerformed() {
        Map<String, CodeTemplateLibrary> libraryMap = codeTemplateLibrariesPanel.getLibraryMap();
        if (!PlatformUI.MIRTH_FRAME.codeTemplatePanel.getCachedCodeTemplateLibraries().equals(libraryMap)) {
            if (!PlatformUI.MIRTH_FRAME.alertOption(this, "You've made changes to code template libraries, which will be saved now. Are you sure you wish to continue?")) {
                return;
            }

            UpdateSwingWorker worker = PlatformUI.MIRTH_FRAME.codeTemplatePanel.getSwingWorker(libraryMap, new HashMap<String, CodeTemplateLibrary>(), new HashMap<String, CodeTemplate>(), new HashMap<String, CodeTemplate>(), false);
            worker.setActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    saved = true;
                    dispose();
                }
            });
            worker.execute();
        } else {
            saved = true;
            dispose();
        }
    }

    private void cancelButtonActionPerformed() {
        dispose();
    }

    private JPanel containerPanel;
    private JTabbedPane tabPane;
    private JPanel resourcesContainerPanel;
    private LibraryResourcesPanel resourcesPanel;
    private JPanel codeTemplateLibrariesContainerPanel;
    private CodeTemplateLibrariesPanel codeTemplateLibrariesPanel;

    private JPanel bottomPanel;
    private JSeparator separator;
    private JPanel buttonPanel;
    private JButton okButton;
    private JButton cancelButton;
}