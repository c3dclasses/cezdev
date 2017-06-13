//------------------------------------------------------------------------------------------------
// name: CControlsDriver
// desc: defines the driver interface and implementor to do crud operation on control objects
//------------------------------------------------------------------------------------------------
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

//-----------------------------------------------------------------------------
// name: CControlsDriver
// desc: defines the interface object to do crud operation on control objects
//-----------------------------------------------------------------------------
public class CControlsDriver {	
	static CControlsDriverImplementor m_implementor = new CControlsDriverImplementor();
	public static CControl call(CControl ccontrol) {
		if(ccontrol == null)
			return null;
		String strtype = (String) ccontrol._("m_strtype");
		String strdefaulttype = (String) ccontrol._("m_strdefaulttype");
		String straction = (String) ccontrol._("m_straction");
		String strpropname = (String) ccontrol._("m_strpropname");
		String strfuncid = "";
		if(strdefaulttype != null)	// control
			strfuncid += strdefaulttype;	
		else if(strtype != null)
			strfuncid += strtype;
		if(straction != null)
			strfuncid += "->" + straction;
		if(strpropname != null)
			strfuncid += "->" + strpropname;	
		CFunction cfunction = (CFunction) CControlsDriver.m_implementor._(strfuncid.toLowerCase());
		return (cfunction == null) ? null : (CControl) cfunction._((CHash)ccontrol);		
	} // call()
} // end CControlsDriver

//--------------------------------------------------------------------------------
// name: CControlsDriverImplementor
// desc: defines the implementor object to do crud operation on control objects
//--------------------------------------------------------------------------------
class CControlsDriverImplementor extends CHash {	
	public CControlsDriverImplementor() {
		final CControlsDriverImplementor _this = this; 
		
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
	
		CFunction fnCreateJTextField = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return _this.createJControl(control, new JTextField((String)control._("m_value")));
		}}; // end fnCreateJTextField
		
		CFunction fnCreateJLabel = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return _this.createJControl(control, new JLabel((String)control._("m_value")));
		}}; // end fnCreateJLabel
		
	 	CFunction fnCreateJMenuBar = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return _this.createJMenuBar(control, new JMenuBar());
		}}; // end fnCreateJMenuBar
	
	 	CFunction fnCreateJMenu = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return _this.createJMenu(control, new JMenu((String)control._("m_value")));
		}}; // end fnCreateJMenu
	 
	 	CFunction fnCreateJMenuItem = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return _this.createJMenuItem(control, new JMenuItem((String)control._("m_value")));
		}}; // end fnCreateJMenu
	
		CFunction fnCreateSystemTrayIconPopupMenu = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return _this.createSystemTrayIconPopupMenu(control, new_SystemTrayIconPopupMenu(control));
		}}; // end fnCreateSystemTrayIcon
	
	 	CFunction fnCreateMenu = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return _this.createMenu(control, new Menu((String)control._("m_value")));
		}}; // end fnCreateJMenu
	 
	 	CFunction fnCreateMenuItem = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return _this.createMenuItem(control, new MenuItem((String)control._("m_value")));
		}}; // end fnCreateJMenu
	
	 	CFunction fnCreateCheckboxMenuItem = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return _this.createCheckboxMenuItem(control, new CheckboxMenuItem((String)control._("m_value")));
		}}; // end fnCreateJMenu
		
		CFunction fnAddSeperator = new CFunction() { public Object _(Object obj) { 
			return (Object) _this.addSeperator((CControl)obj);
		}}; // end fnAddSeperator
	
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
			if(jcontrol == null)
				return null;
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
		
		this._("text->create", fnCreateJTextField);
		this._("label->create", fnCreateJLabel);
				
		
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
		this._("menubar->create", fnCreateJMenuBar);	
		this._("menu->create", fnCreateJMenu);
		this._("menuitem->create", fnCreateJMenuItem);
		this._("menuitem->set->action", fnSetButtonAction);				
		
		this._("systray-menubar->create", fnCreateSystemTrayIconPopupMenu);	
		this._("systray-menu->create", fnCreateMenu);
		this._("systray-menuitem->create", fnCreateMenuItem);
		this._("systray-checkboxmenuitem->create", fnCreateCheckboxMenuItem);
		this._("systray-menuitem->set->action", fnSetButtonAction);						
		this._("systray-menuseperator->create", fnAddSeperator);
		
	} // end CControlsDriverImplementor()

	///////////////////////
	// helper methods
	///////////////////////
	Object createJControl(CControl ccontrol, Component jcontrol) {
		if(jcontrol == null)
			return null;
		ccontrol._("m_jcontrol", jcontrol);	
		Container parent = (Container) this.getParentContainer(ccontrol);
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
		Container parent = (Container) this.getParentContainer(ccontrol);
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

	///////////////////////////
	// SystemTray menu
	///////////////////////////
	CControl createSystemTrayIconPopupMenu(CControl ccontrol, PopupMenu jcontrol){
		if(jcontrol == null)
			return null;
		ccontrol._("m_jcontrol", jcontrol);	
		return ccontrol;
	} // end createSystemTrayIcon()
	
	CControl addSeperator(CControl ccontrol){
		if(ccontrol == null)
			return null;
		Menu menu = (Menu) ccontrol._("m_jcontrol");	
		if(menu != null)
			menu.addSeparator();
		return ccontrol;
	} // end addMenuItemSeperator()
		
	CControl createPopupMenu(CControl ccontrol, PopupMenu jcontrol){
		if(jcontrol == null)
			return null;
		ccontrol._("m_jcontrol", jcontrol);	
		TrayIcon parent = (TrayIcon) this.getParentContainer(ccontrol);
		if(parent != null)
			parent.setPopupMenu(jcontrol);
		return ccontrol;
	} // end createPopupMenu()

	Object createMenu(CControl ccontrol, Menu jcontrol) {
		if(jcontrol == null)
			return null;
		ccontrol._("m_jcontrol", jcontrol);	
		Menu parent = (Menu) this.getParentContainer(ccontrol);
		if(parent != null)
			parent.add(jcontrol);
		return ccontrol;
	} // end createMenu()
	
	Object createMenuItem(CControl ccontrol, MenuItem jcontrol) {
		if(jcontrol == null)
			return null;
		ccontrol._("m_jcontrol", jcontrol);	
		Menu parent = (Menu) this.getParentContainer(ccontrol);
		if(parent != null)
			parent.add(jcontrol);
		return ccontrol;
	} // end createMenuItem()

	Object createCheckboxMenuItem(CControl ccontrol, CheckboxMenuItem jcontrol) {
		if(jcontrol == null)
			return null;
		ccontrol._("m_jcontrol", jcontrol);	
		Menu parent = (Menu) this.getParentContainer(ccontrol);
		if(parent != null)
			parent.add(jcontrol);
		return ccontrol;
	} // end createMenuItem()	
	
	Object getParentContainer(CControl ccontrol) {
		if(ccontrol == null)
			return null;
		CControl container = (CControl) ccontrol._("m_container");
		if(container == null)
			return null;
		return (Object) container._("m_jcontrol");	
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
	
	PopupMenu new_SystemTrayIconPopupMenu(CControl ccontrol) {
		if(!SystemTray.isSupported())
			return null;
		String striconpath = (String)ccontrol._("m_value");
		try {
		SystemTray tray = SystemTray.getSystemTray();
		ImageIcon imageicon = new ImageIcon(striconpath);
		TrayIcon trayIcon = new TrayIcon(imageicon.getImage());
		if(trayIcon == null)
			return null;
		tray.add(trayIcon);
		PopupMenu popupmenu = new PopupMenu();
		if(popupmenu == null)
			return null;
		trayIcon.setPopupMenu(popupmenu);
		return popupmenu;
		//return trayIcon; // this will contain the menu
		}
		catch(Exception e) {	
		}
		return null;
	} // end new_SystemTray()
} // end CControlsDriverImplementor

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
} // end JComboBoxOption