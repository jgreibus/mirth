/** MapperPanel.java
 *
 * 	@author  franciscos
 * 	Created on June 21, 2006, 4:38 PM
 */


package com.webreach.mirth.client.ui.editors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.Ostermiller.Syntax.HighlightedDocument;
import com.webreach.mirth.client.ui.MirthTextPane;
import com.webreach.mirth.client.ui.PlatformUI;
import com.webreach.mirth.model.Channel;


public class MapperPanel extends CardPanel {
	
	/** Creates new form MapperPanel */
	public MapperPanel() { initComponents(); }
	public MapperPanel(MirthEditorPane p) {
		super();
		parent = p;		
		initComponents();
	}
	
	/** initialize components and set layout;
	 *  originally created with NetBeans, modified by franciscos
	 */
	private void initComponents() {
		mappingPanel = new JPanel();
		labelPanel = new JPanel();
		mappingLabel = new JLabel( "   " + label );
		mappingTextField = new JTextField();
		mappingScrollPane = new JScrollPane();
		HighlightedDocument mappingDoc = new HighlightedDocument();
		mappingDoc.setHighlightStyle( HighlightedDocument.JAVASCRIPT_STYLE );
		mappingTextPane = new MirthTextPane( mappingDoc );
		
		mappingPanel.setBorder( BorderFactory.createEmptyBorder() );
		mappingTextField.setBorder( BorderFactory.createEtchedBorder() );
		mappingTextPane.setBorder( BorderFactory.createEmptyBorder() );
		mappingScrollPane.setBorder( BorderFactory.createTitledBorder( 
				BorderFactory.createLoweredBevelBorder(), "Mapping: ", TitledBorder.LEFT,
				TitledBorder.ABOVE_TOP, new Font( null, Font.PLAIN, 11 ), 
				Color.black ));
		
		mappingTextPane.setFont( EditorConstants.DEFAULT_FONT );
		
		mappingTextPanel = new JPanel();
		mappingTextPanel.setLayout( new BorderLayout() );
		mappingTextPanel.add( mappingTextPane, BorderLayout.CENTER );
		
		labelPanel.setLayout( new BorderLayout() );
		labelPanel.add( mappingLabel, BorderLayout.NORTH );
		labelPanel.add( new JLabel( " " ), BorderLayout.WEST );
		labelPanel.add( mappingTextField, BorderLayout.CENTER );
		labelPanel.setBorder( BorderFactory.createEmptyBorder( 0, 0, 0, 150) );
		
		mappingScrollPane.setViewportView( mappingTextPanel );
		
		mappingPanel.setLayout( new BorderLayout() );
		mappingPanel.add( labelPanel, BorderLayout.NORTH );
		mappingPanel.add( mappingScrollPane, BorderLayout.CENTER );
		
		// BGN listeners
		mappingTextField.getDocument().addDocumentListener(
				new DocumentListener() {
					public void changedUpdate(DocumentEvent arg0) {
						parent.modified = true;
					}
					
					public void insertUpdate(DocumentEvent arg0) {
						parent.modified = true;						
					}
					
					public void removeUpdate(DocumentEvent arg0) {
						parent.modified = true;						
					}
					
				});
		
		mappingTextPane.getDocument().addDocumentListener(
				new DocumentListener() {
					public void changedUpdate(DocumentEvent arg0) {
						parent.modified = true;
					}
					
					public void insertUpdate(DocumentEvent arg0) {
						parent.modified = true;						
					}
					
					public void removeUpdate(DocumentEvent arg0) {
						parent.modified = true;						
					}
					
				});
		// END listeners
		
		this.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10) );
		this.setLayout( new BorderLayout() );
		this.add( mappingPanel, BorderLayout.CENTER );
	} 
	
	public void update(){
		parent.update();
		
		Channel channel = PlatformUI.MIRTH_FRAME.channelEditPage.currentChannel;
		if ( channel.getDirection().equals( Channel.Direction.INBOUND ) ){
			mappingLabel.setText( "   Variable: " );
			parent.setDroppedTextPrefix("msg");
		} else if ( channel.getDirection().equals( Channel.Direction.OUTBOUND ) ){
			mappingLabel.setText( "   HL7 Message Segment: " );
			parent.setDroppedTextPrefix("hl7");
		}
	}
	
	public Map<Object, Object> getData() {
		Map<Object, Object> m = new HashMap<Object, Object>();
		m.put( "Variable", mappingTextField.getText().trim() );
		m.put( "Mapping", mappingTextPane.getText().trim() );
		
		return m;
	}
	
	
	public void setData( Map<Object, Object> data ) {
		if ( data != null ) {
			mappingTextField.setText( (String)data.get( "Variable" ) );
			mappingTextPane.setText( (String)data.get( "Mapping" ) );
		} else {
			mappingTextField.setText( "" );
			mappingTextPane.setText( "" );
		}
	}
	
	
	private String label;
	private JPanel mappingTextPanel;		// for no linewrap in textpane
	private MirthTextPane mappingTextPane;
	private JLabel mappingLabel;
	private JPanel labelPanel;
	private JPanel mappingPanel;
	private JTextField mappingTextField;
	private JScrollPane mappingScrollPane;
	private MirthEditorPane parent;
}
