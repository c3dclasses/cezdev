//----------------------------------------------------------------------------
// file: CFormDriver
// desc: implements functions used by CForm / CControls / COptions
//----------------------------------------------------------------------------
package c3dclasses.csystem.cui;
import java.io.*;
import java.util.*;
import java.net.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;
import cglobal.*; 
import c3dclasses.ccore.*;

//-----------------------------------------------------------------
// name: CFormDriver
// desc: implements functions used by CForm / CControls / COptions
//-----------------------------------------------------------------
public class CFormDriver {	
	static CFormDriverMapper m_mapper = new CFormDriverMapper();	// maps interface code to java swing code
	public static CHash call(CHash ccontrol) {
		if(ccontrol == null)
			return null;
		String strtype = (String) ccontrol._("m_strtype");
		String strdefaulttype = (String) ccontrol._("m_strdefaulttype");
		String straction = (String) ccontrol._("m_straction");
		String strpropname = (String) ccontrol._("m_strpropname");
		//String strpropvalue = (String) ccontrol._("m_propvalue");
		String strfuncid = "";
		if(strdefaulttype != null)	// control
			strfuncid += strdefaulttype;	
		else if(strtype != null)
			strfuncid += strtype;
		if(straction != null)
			strfuncid += "->" + straction;
		if(strpropname != null)
			strfuncid += "->" + strpropname;	
		//_.alert(strfuncid);	
		CFunction cfunction = (CFunction) CFormDriver.m_mapper._(strfuncid.toLowerCase());
		return (cfunction == null) ? null : (CHash) cfunction._(ccontrol);		
	} // call()
} // end CFormDriver

//-----------------------------------------------------------------
// name: CFormDriverMapper
// desc:
//-----------------------------------------------------------------
class CFormDriverMapper extends CHash {	
	public CFormDriverMapper() {
		final CFormDriverMapper _this = this; 
		
		/////////////////////////
		// create functions
		/////////////////////////
		CFunction fnCreateJFrame = new CFunction() { public Object _(Object obj) {
			CControl control = (CControl) obj; 
			return _this.createJControl(control, new JFrame((String)control._("m_value")));
		}}; // end fnCreateJFrame
		
		CFunction fnCreateJButton = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return _this.createJControl(control, new JButton((String)control._("m_value")));
		}}; // end fnCreateJButton

		CFunction fnCreateJComboBox = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return _this.createJControl(control, new_JComboBox(control));
		}}; // end fnCreateJComboBox
	
	 	CFunction fnCreateMenuBar = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return _this.createJMenuBar(control, new JMenuBar());
		}}; // end fnCreateMenuBar
	
	 	CFunction fnCreateMenu = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return _this.createJMenu(control, new JMenu((String)control._("m_value")));
		}}; // end fnCreateMenu
	 
	 	CFunction fnCreateMenuItem = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return _this.createJMenuItem(control, new JMenuItem((String)control._("m_value")));
		}}; // end fnCreateMenu
	 
		/////////////////////////
		// set/get functions
		/////////////////////////
		CFunction fnSetVisible = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			String value = (String) ccontrol._("m_propvalue");
			Component jcontrol = (Component) ccontrol._("m_jcontrol");
			jcontrol.setVisible(Boolean.parseBoolean(value));
			return ccontrol;	
		}}; // end fnSetVisible
	
		CFunction fnGetVisible = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			Component jcontrol = (Component) ccontrol._("m_jcontrol");
			String value = Boolean.toString(jcontrol.isVisible());
			ccontrol._("m_propvalue", value);
			return ccontrol;	
		}}; // end fnGetVisible
		
		CFunction fnSetFramePacking = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			String value = (String) ccontrol._("m_propvalue");
			JFrame jcontrol = (JFrame) ccontrol._("m_jcontrol");
			jcontrol.pack();
			return ccontrol;	
		}}; // end 
		
		CFunction fnSetLookAndFeel = new CFunction() { public Object _(Object obj) { 
			//CControl ccontrol = (CControl) obj;
			//String value = (String) ccontrol._("m_propvalue");
			try{ UIManager.setLookAndFeel(""); }
			catch( Exception ex ){}
			return null;	
		}}; // end fnSetLayout
		
		CFunction fnSetLayout = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			String value = (String) ccontrol._("m_propvalue");
			JFrame jcontrol = (JFrame) ccontrol._("m_jcontrol");
			Container cp = jcontrol.getContentPane();
			cp.setLayout(new GridLayout(0, 1));
			return ccontrol;
		}}; // end fnSetGridLyout

		CFunction fnSetFrameClosing = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			String value = (String) ccontrol._("m_propvalue");
			JFrame jcontrol = (JFrame) ccontrol._("m_jcontrol");
			jcontrol.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			return ccontrol;	
		}}; // end fnSetFrameClosing

		CFunction fnSetTitle = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			String value = (String) ccontrol._("m_propvalue");
			JFrame jcontrol = (JFrame) ccontrol._("m_jcontrol");
			jcontrol.setTitle(value);
			return ccontrol;	
		}}; // end fnSetTitle

		CFunction fnGetTitle = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			JFrame jcontrol = (JFrame) ccontrol._("m_jcontrol");
			String value = jcontrol.getTitle();
			ccontrol._("m_propvalue", value);
			return ccontrol;	
		}}; // end fnGetTitle
		
		CFunction fnSetText = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			String value = (String) ccontrol._("m_propvalue");
			JButton jcontrol = (JButton) ccontrol._("m_jcontrol");
			jcontrol.setText(value);
			return ccontrol;	
		}}; // end fnSetText

		CFunction fnGetText = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			JButton jcontrol = (JButton) ccontrol._("m_jcontrol");
			String value = jcontrol.getText();
			ccontrol._("m_propvalue", value);
			return ccontrol;	
		}}; // end fnGetText
		
		CFunction fnSetButtonAction = new CFunction() { public Object _(Object obj) {
			CControl ccontrol = (CControl) obj;
			final CFunction cfunction = (CFunction) ccontrol._("m_propvalue");
			//if(cfunction == null)
			//	return null;
			AbstractButton jcontrol = (AbstractButton) ccontrol._("m_jcontrol");
			jcontrol.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cfunction._(e);
				} // end actionPerformed()
			}); // end addActionListener
			return ccontrol;
		}}; // end fnSetButtonAction
	
		CFunction fnGetSelectedItem = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			JComboBox jcontrol = (JComboBox) ccontrol._("m_jcontrol");
			JComboBoxOption item = (JComboBoxOption) jcontrol.getSelectedItem();
			ccontrol._("m_propvalue", new CPair(item.m_strname, item.m_value));
			return ccontrol;	
		}}; // end fnGetSelectedItem
	
		CFunction fnRemoveAllItems = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			JComboBox jcontrol = (JComboBox) ccontrol._("m_jcontrol");
			jcontrol.removeAllItems();
			return ccontrol;	
		}}; // end fnGetSelectedItem
		
		CFunction fnAddItem = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			JComboBox jcontrol = (JComboBox) ccontrol._("m_jcontrol");
			CPair cpair = (CPair) ccontrol._("m_propvalue");
			jcontrol.addItem(new JComboBoxOption((String)cpair.m_first, cpair.m_second));
			return ccontrol;	
		}}; // end fnGetSelectedItem
		
		
		///////////////////////////////
		// id <==> function mapping
		///////////////////////////////
		this._("form->create", fnCreateJFrame);
		this._("form->set->grid", fnSetLayout);
		this._("form->set->pack", fnSetFramePacking);
		this._("form->set->close", fnSetFrameClosing);
		this._("form->set->visible", fnSetVisible);
		this._("form->get->visible", fnSetVisible);		
		this._("form->set->title", fnSetTitle);		
		this._("form->get->title", fnGetTitle);	

		this._("button->create", fnCreateJButton);
		this._("button->set->visible", fnSetVisible);
		this._("button->get->visible", fnGetVisible);	
		this._("button->set->title", fnSetText);		
		this._("button->get->title", fnGetText);		
		this._("button->set->text", fnSetText);		
		this._("button->get->text", fnGetText);		
		this._("button->set->action", fnSetButtonAction);				

		this._("select->create", fnCreateJComboBox);
		this._("select->set->visible", fnSetVisible);
		this._("select->get->visible", fnGetVisible);	
		
		
		
		// items
		//this._("select->set->item-add", fnGetVisible);	
		//this._("select->set->item-remove-all", fnGetVisible);	
		//this._("select->set->item-remove", fnGetVisible);	
		//this._("select->set->item-remove-selected", fnGetVisible);	
		//this._("select->get->item-selected", fnGetVisible);	
		//this._("select->set->item-selected", fnGetVisible);	
		
		
		// delete
		//this._("form->set->remove", fnRemoveAll);	
		//this._("form->set->remove-all", fnRemoveAll);	
		//this._("form->set->remove-at", fnRemoveAll);	
		//this._("form->set->remove", fnRemoveAll);	
		//this._("button->set->remove", fnRemoveAll);	
		//this._("select->set->remove", fnRemoveAll);	
		
		// menubar
		this._("menubar->create", fnCreateMenuBar);	
		this._("menu->create", fnCreateMenu);
		this._("menuitem->create", fnCreateMenuItem);
			
		
		
	} // end CFormDriverMapper()

	///////////////////////
	// helper methods
	///////////////////////
	Object createJControl(CControl ccontrol, Component jcontrol) {
		if(jcontrol == null)
			return null;
		ccontrol._("m_jcontrol", jcontrol);	
		Container parent = this.getParentContainer(ccontrol);
		if(parent != null)
			parent.add(jcontrol);
		return ccontrol;
	} // end createJControl()
	
	Object createJMenuBar(CControl ccontrol, JMenuBar jcontrol) {
		_.alert("create menu bar");
		if(jcontrol == null)
			return null;
		ccontrol._("m_jcontrol", jcontrol);	
		JFrame parent = (JFrame) this.getParentContainer(ccontrol);
		if(parent != null) {
			parent.setJMenuBar(jcontrol);
			_.alert("add menu bar");
		}
		return ccontrol;
	} // end createJMenuBar()

	Object createJMenu(CControl ccontrol, Component jcontrol) {
		if(jcontrol == null)
			return null;
		ccontrol._("m_jcontrol", jcontrol);	
		Container parent = this.getParentContainer(ccontrol);
		if(parent != null)
			parent.add(jcontrol);
		return ccontrol;
	} // end createJMenu()
	
	Object createJMenuItem(CControl ccontrol, JMenuItem jcontrol) {
		if(jcontrol == null)
			return null;
		ccontrol._("m_jcontrol", jcontrol);	
		JMenu parent = (JMenu) this.getParentContainer(ccontrol);
		if(parent != null)
			parent.add(jcontrol);
		return ccontrol;
	} // end createJMenu()
	
	Container getParentContainer(CControl ccontrol) {
		if(ccontrol == null)
			return null;
		CControl container = (CControl) ccontrol._("m_container");
		if(container == null)
			return null;
		return (Container) container._("m_jcontrol");	
	} // end addControlToContainer()
	
	JComboBox new_JComboBox(CControl ccontrol) {
		CHash params = (CHash) ccontrol._("m_params");
		CHash options = (CHash) params._("m_options");
		JComboBox jcontrol = new JComboBox();
		if(options == null)
			return jcontrol;
		CArray keys = options.keys();
		if(keys == null)
			return jcontrol;
		int len = keys.length();
		String strname = "";
		for(int i=0; i<len; i++) {
			strname = (String) keys._(i);
			jcontrol.addItem(new JComboBoxOption(strname, options._(strname)));
		} // end for
		return jcontrol;
	} // end new_JComboBox()
} // end CFormDriverMapper

//---------------------------------------------------------
// name: JComboBoxOption
// desc: defines the combobox option
//---------------------------------------------------------
class JComboBoxOption {
	public JComboBoxOption(String strname, Object value) { this.m_strname = strname; this.m_value = value; }
	public String getName() { return this.m_strname; }
	public Object getValue() { return this.m_value; }
	public String toString() { return m_strname; }
	public String m_strname = "";
	public Object m_value = null;
} // end CComboBoxOption