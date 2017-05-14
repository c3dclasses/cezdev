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
		//String strpropvalue = (String) ccontrol._("m_strpropvalue");
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
			JComboBoxOption [] jcomboboxoption = _this.getJComboBoxOptions(control);
			return _this.createJControl(control, new JComboBox());
		}}; // end fnCreateJComboBox
	 
		/////////////////////////
		// set/get functions
		/////////////////////////
		CFunction fnSetVisible = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			String value = (String) ccontrol._("m_strpropvalue");
			Component jcontrol = (Component) ccontrol._("m_jcontrol");
			jcontrol.setVisible(Boolean.parseBoolean(value));
			return ccontrol;	
		}}; // end fnSetVisible
	
		CFunction fnGetVisible = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			Component jcontrol = (Component) ccontrol._("m_jcontrol");
			String value = Boolean.toString(jcontrol.isVisible());
			ccontrol._("m_strpropvalue", value);
			return ccontrol;	
		}}; // end fnGetVisible
		
		CFunction fnSetFramePacking = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			String value = (String) ccontrol._("m_strpropvalue");
			JFrame jcontrol = (JFrame) ccontrol._("m_jcontrol");
			jcontrol.pack();
			return ccontrol;	
		}}; // end 
		
		CFunction fnSetLookAndFeel = new CFunction() { public Object _(Object obj) { 
			//CControl ccontrol = (CControl) obj;
			//String value = (String) ccontrol._("m_strpropvalue");
			try{ UIManager.setLookAndFeel(""); }
			catch( Exception ex ){}
			return null;	
		}}; // end fnSetLayout
		
		CFunction fnSetLayout = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			String value = (String) ccontrol._("m_strpropvalue");
			JFrame jcontrol = (JFrame) ccontrol._("m_jcontrol");
			Container cp = jcontrol.getContentPane();
			cp.setLayout(new GridLayout(0, 1));
			return ccontrol;
		}}; // end fnSetGridLyout

		CFunction fnSetFrameClosing = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			String value = (String) ccontrol._("m_strpropvalue");
			JFrame jcontrol = (JFrame) ccontrol._("m_jcontrol");
			jcontrol.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			return ccontrol;	
		}}; // end fnSetFrameClosing

		CFunction fnSetTitle = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			String value = (String) ccontrol._("m_strpropvalue");
			JFrame jcontrol = (JFrame) ccontrol._("m_jcontrol");
			jcontrol.setTitle(value);
			return ccontrol;	
		}}; // end fnSetTitle

		CFunction fnGetTitle = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			JFrame jcontrol = (JFrame) ccontrol._("m_jcontrol");
			String value = jcontrol.getTitle();
			ccontrol._("m_strpropvalue", value);
			return ccontrol;	
		}}; // end fnGetTitle
		
		CFunction fnSetText = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			String value = (String) ccontrol._("m_strpropvalue");
			JButton jcontrol = (JButton) ccontrol._("m_jcontrol");
			jcontrol.setText(value);
			return ccontrol;	
		}}; // end fnSetText

		CFunction fnGetText = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			JButton jcontrol = (JButton) ccontrol._("m_jcontrol");
			String value = jcontrol.getText();
			ccontrol._("m_strpropvalue", value);
			return ccontrol;	
		}}; // end fnGetText
		
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

		this._("select->create", fnCreateJComboBox);
		this._("select->set->visible", fnSetVisible);
		this._("select->get->visible", fnGetVisible);	
	} // end CFormDriverMapper()

	///////////////////////
	// helper methods
	///////////////////////
	Object createJControl(CControl ccontrol, Component jcontrol) {
		if(jcontrol == null)
			return false;
		ccontrol._("m_jcontrol", jcontrol);	
		Container parent = this.getParentContainer(ccontrol);
		if(parent != null)
			parent.add(jcontrol);
		return ccontrol;
	} // end createJControl()
	
	Container getParentContainer(CControl ccontrol) {
		if(ccontrol == null)
			return null;
		CControl container = (CControl) ccontrol._("m_container");
		if(container == null)
			return null;
		return (Container) container._("m_jcontrol");	
	} // end addControlToContainer()
	
	JComboBoxOption [] getJComboBoxOptions(CControl ccontrol) {
		return null;
	} // end getJComboBoxOptions()
} // end CFormDriverMapper

//---------------------------------------------------------
// name: JComboBoxOption
// desc: defines the combobox option
//---------------------------------------------------------
class JComboBoxOption {
	public String m_strname = "";
	public Object m_value = null;
	public String getName() { return this.m_strname; }
	public Object getValue() { return this.m_value; }
	public String toString() { return m_strname; }
} // end CComboBoxOption