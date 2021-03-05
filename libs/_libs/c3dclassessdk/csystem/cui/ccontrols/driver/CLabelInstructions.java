//--------------------------------------------------------------
// name: CLabelInstructions
// desc: implements label instruction set
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
// name: CLabelInstructions
// desc: implements label instruction set
//--------------------------------------------------------
class CLabelInstructions {	
	public CLabelInstructions(CProcessor cprocessor) {
		CFunction fnCreateJLabel = new CFunction() { public CReturn call(CObject obj) { 
			CControl control = (CControl) obj; 
			CControlInstructions.createJControl(control, new JLabel((String)control._("m_value")));
			return null;
		}}; // end fnCreateJLabel
		cprocessor._("label->create", fnCreateJLabel);
	} // end CLabelInstructions()
} // end CLabelInstructions