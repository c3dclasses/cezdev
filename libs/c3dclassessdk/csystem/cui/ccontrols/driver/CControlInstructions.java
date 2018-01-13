//--------------------------------------------------------------
// name: CControlInstructions
// desc: implements control instruction set
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
// name: CControlInstructions
// desc: implements control instruction set
//--------------------------------------------------------
public class CControlInstructions extends CInstructions {	
	public CControlInstructions(CProcessor cprocessor) {	
		super(cprocessor);
		// instruction function
		CFunction fnSetVisible = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			String value = (String) ccontrol._("m_propvalue");
			Component jcontrol = (Component) ccontrol._("m_jcontrol");
			jcontrol.setVisible(Boolean.parseBoolean(value));
			return ccontrol;	
		}}; // end fnSetVisible()
			
		CFunction fnGetVisible = new CFunction() { public Object _(Object obj) { 
			CControl ccontrol = (CControl) obj;
			Component jcontrol = (Component) ccontrol._("m_jcontrol");
			String value = Boolean.toString(jcontrol.isVisible());
			ccontrol._("m_propvalue", value);
			return ccontrol;	
		}}; // end fnGetVisible()
		// add instruction id to instrunction function mapping to the processor
		cprocessor._("ccontrol->set->visible", fnSetVisible);
		cprocessor._("ccontrol->get->visible", fnGetVisible);		
	} // end CControlDriverImplementor()
	
	// instruction set helper functions
	public static Object createJControl(CControl ccontrol, Component jcontrol) {
		if(jcontrol == null)
			return null;
		ccontrol._("m_jcontrol", jcontrol);	
		Container parent = (Container) CControlInstructions.getParentContainer(ccontrol);
		if(parent != null)
			parent.add(jcontrol);
		return ccontrol;
	} // end createJControl()
	
	public static Object getParentContainer(CControl ccontrol) {
		if(ccontrol == null)
			return null;
		CControl container = (CControl) ccontrol._("m_container");
		if(container == null)
			return null;
		return (Object) container._("m_jcontrol");	
	} // end getParentContainer()
} // end CControlInstructions