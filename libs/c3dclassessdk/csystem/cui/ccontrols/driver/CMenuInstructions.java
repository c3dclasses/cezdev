//--------------------------------------------------------------
// name: CMenuInstructions
// desc: implements the form control
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
// name: CMenuInstructions
// desc: implements the form control
//--------------------------------------------------------
class CMenuInstructions extends CInstructions {	
	public CMenuInstructions(CProcessor cprocessor) {
		super(cprocessor);
		CFunction fnCreateJMenuBar = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return CMenuInstructions.createJMenuBar(control, new JMenuBar());
		}}; // end fnCreateJMenuBar
	
	 	CFunction fnCreateJMenu = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return CMenuInstructions.createJMenu(control, new JMenu((String)control._("m_value")));
		}}; // end fnCreateJMenu
	 
	 	CFunction fnCreateJMenuItem = new CFunction() { public Object _(Object obj) { 
			CControl control = (CControl) obj; 
			return CMenuInstructions.createJMenuItem(control, new JMenuItem((String)control._("m_value")));
		}}; // end fnCreateJMenu
		
		CFunction fnAddJMenuSeperator = new CFunction() { public Object _(Object obj) { 
			return (Object) CMenuInstructions.addJMenuSeperator((CControl)obj);
		}}; // end fnAddMenuSeperator
		
		cprocessor._("menubar->create", fnCreateJMenuBar);	
		cprocessor._("menu->create", fnCreateJMenu);
		cprocessor._("menuitem->create", fnCreateJMenuItem);
		cprocessor._("menuitem-seperator->create", fnAddJMenuSeperator);
		//cprocessor._("menuitem->set->onclick", fnSetActionListener);				
	} // end CMenuInstructions()
	
	public static Object createJMenuBar(CControl ccontrol, JMenuBar jcontrol) {
		_.alert("create menu bar");
		if(jcontrol == null)
			return null;
		ccontrol._("m_jcontrol", jcontrol);	
		JFrame parent = (JFrame) CControlInstructions.getParentContainer(ccontrol);
		if(parent != null) {
			parent.setJMenuBar(jcontrol);
			_.alert("add menu bar");
		}
		return ccontrol;
	} // end createJMenuBar()

	public static Object createJMenu(CControl ccontrol, Component jcontrol) {
		if(jcontrol == null)
			return null;
		ccontrol._("m_jcontrol", jcontrol);	
		Container parent = (Container) CControlInstructions.getParentContainer(ccontrol);
		if(parent != null)
			parent.add(jcontrol);
		return ccontrol;
	} // end createJMenu()
	
	public static Object createJMenuItem(CControl ccontrol, JMenuItem jcontrol) {
		if(jcontrol == null)
			return null;
		ccontrol._("m_jcontrol", jcontrol);	
		JMenu parent = (JMenu) CControlInstructions.getParentContainer(ccontrol);
		if(parent != null)
			parent.add(jcontrol);
		return ccontrol;
	} // end createJMenu()
	
	public static CControl addJMenuSeperator(CControl ccontrol){
		if(ccontrol == null)
			return null;
		ccontrol._("m_jcontrol",null);
		JMenu parent = (JMenu) CControlInstructions.getParentContainer(ccontrol);
		if(parent != null)
			parent.addSeparator();
		return ccontrol;
	} // end addJMenuItemSeperator()
} // end CMenuInstructions