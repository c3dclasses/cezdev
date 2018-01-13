//--------------------------------------------------------------
// name: CButtonInstructions
// desc: implements button instruction set
//--------------------------------------------------------------
package c3dclasses;
import java.io.*;
import java.util.*;
import java.net.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener; 
import java.applet.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;
import javax.swing.border.*;


//--------------------------------------------------------
// name: CButtonInstructions
// desc: implements button instruction set
//--------------------------------------------------------
class CButtonInstructions extends CInstructions {	
	CControlInstructions m_ccontrolinstructions;
	public CButtonInstructions(CProcessor cprocessor) {
		super(cprocessor);
		// instruction function
		final CControlInstructions ccontrolinstructions = this.m_ccontrolinstructions = (CControlInstructions) cprocessor.getCInstructions("CControlInstrunctions");
		final CButtonInstructions _this = this;
		CFunction fnCreateJButton = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return ccontrolinstructions.createJControl(control, new JButton((String)control._("m_value")));
		}}; // end fnCreateJButton()
		
		CFunction fnCreateJCheckbox = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return ccontrolinstructions.createJControl(control, new JCheckBox((String)control._("m_value")));
		}}; // end fnCreateJCheckbox()
		
		CFunction fnCreateJRadioButton = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return _this.createJRadioButton(control, new JRadioButton((String)control._("m_value")));
		}}; // end fnCreateJRadioButton()
	
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
			
		cprocessor._("radio->create", fnCreateJRadioButton);
		cprocessor._("checkbox->create", fnCreateJCheckbox);
		cprocessor._("button->create", fnCreateJButton);
		cprocessor._("button->set->visible", cprocessor._("ccontrol->set->visible"));
		cprocessor._("button->get->visible", cprocessor._("ccontrol->get->visible"));		
		cprocessor._("button->set->title", fnSetText);		
		cprocessor._("button->get->title", fnGetText);		
		cprocessor._("button->set->text", fnSetText);		
		cprocessor._("button->get->text", fnGetText);	
	} // end CButtonInstructions()
	
	Object createJRadioButton(CControl ccontrol, Component jcontrol) {
		ccontrol = (CControl) this.m_ccontrolinstructions.createJControl(ccontrol, jcontrol); 
		if(ccontrol == null)
			return null;
		CHash params = (CHash) ccontrol._("m_params");
		if(params == null)
			return null;	
		// add the radio button to the button group
		String strgroupid = (String) params._("m_strgroupid");
		if(strgroupid == null)
			return ccontrol;
		CControl container = (CControl) ccontrol._("m_container");
		if(container == null)
			return null;
		ButtonGroup buttongroup = (ButtonGroup) container._("m_btngroup-" + strgroupid);
		if(buttongroup == null) {
			buttongroup = new ButtonGroup();
			_.alert("creating ButtonGroup: " + strgroupid);
			container._("m_btngroup-" + strgroupid, buttongroup);
		} // end if
		_.alert("added the button group");
		buttongroup.add((JRadioButton)jcontrol);
		return ccontrol;
	} // end createJControl()
	
	Object createCheckboxMenuItem(CControl ccontrol, CheckboxMenuItem jcontrol) {
		if(jcontrol == null)
			return null;
		ccontrol._("m_jcontrol", jcontrol);	
		Menu parent = (Menu) this.m_ccontrolinstructions.getParentContainer(ccontrol);
		if(parent != null)
			parent.add(jcontrol);
		return ccontrol;
	} // end createMenuItem()	
} // end CButtonInstructions