//--------------------------------------------------------------
// name: CFormInstructions
// desc: implements form instruction set
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
// name: CFormInstructions
// desc: implements form instruction set
//--------------------------------------------------------
class CFormInstructions extends CInstructions {
	public CFormInstructions(CProcessor cprocessor) {
		super(cprocessor);	
		// instruction function
		final CControlInstructions ccontrolinstrunctions = (CControlInstructions) cprocessor.getCInstructions("CControlInstrunctions");
		CFunction fnCreateJFrame = new CFunction() { public Object _(Object obj) {
			CControl control = (CControl) obj; 
			return ccontrolinstrunctions.createJControl(control, new JFrame((String)control._("m_value")));
		}}; // end fnCreateJFrame
		
		CFunction fnSetFramePacking = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			String value = (String) ccontrol._("m_propvalue");
			JFrame jcontrol = (JFrame) ccontrol._("m_jcontrol");
			jcontrol.pack();
			return ccontrol;	
		}}; // end fnSetFramePacking()

		CFunction fnSetLookAndFeel = new CFunction() { public Object _(Object obj) { 
			//CControl ccontrol = (CControl) obj;
			//String value = (String) ccontrol._("m_propvalue");
			try{ UIManager.setLookAndFeel(""); }
			catch( Exception ex ){ CLog.error(ex.toString()); }
			return null;	
		}}; // end fnSetLookAndFeel()
		
		CFunction fnSetLayout = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			String value = (String) ccontrol._("m_propvalue");
			JFrame jcontrol = (JFrame) ccontrol._("m_jcontrol");
			Container cp = jcontrol.getContentPane();
			cp.setLayout(new GridLayout(0, 1));
			return ccontrol;
		}}; // end fnSetLayout()

		CFunction fnSetFrameClosing = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			String value = (String) ccontrol._("m_propvalue");
			JFrame jcontrol = (JFrame) ccontrol._("m_jcontrol");
			jcontrol.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			return ccontrol;	
		}}; // end fnSetFrameClosing()

		CFunction fnSetTitle = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			String value = (String) ccontrol._("m_propvalue");
			JFrame jcontrol = (JFrame) ccontrol._("m_jcontrol");
			jcontrol.setTitle(value);
			return ccontrol;	
		}}; // end fnSetTitle()

		CFunction fnGetTitle = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			JFrame jcontrol = (JFrame) ccontrol._("m_jcontrol");
			String value = jcontrol.getTitle();
			ccontrol._("m_propvalue", value);
			return ccontrol;	
		}}; // end fnGetTitle()

		// add instruction id to instrunction function mapping to the processor
		cprocessor._("form->create", fnCreateJFrame);
		cprocessor._("form->set->grid", fnSetLayout);
		cprocessor._("form->set->pack", fnSetFramePacking);
		cprocessor._("form->set->close", fnSetFrameClosing);
		cprocessor._("form->set->visible", cprocessor._("ccontrol->set->visible"));
		cprocessor._("form->get->visible", cprocessor._("ccontrol->get->visible"));		
		cprocessor._("form->set->title", fnSetTitle);		
		cprocessor._("form->get->title", fnGetTitle);	
	} // end CFormInstructions()
} // end CFormInstructions