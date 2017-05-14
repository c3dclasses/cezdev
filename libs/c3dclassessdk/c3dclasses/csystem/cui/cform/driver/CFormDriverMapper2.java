//----------------------------------------------------------------------------
// file: CFormDriverMapper
// desc: 
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
// name: CFormDriverMapper2
// desc: 
//-----------------------------------------------------------------
public class CFormDriverMapper2 extends CHash {	
	public CFormDriverMapper2() { 
		////////////////////
		// form -> JFrame
		////////////////////
		this._("form->create", new CFunction() { public Object _(Object obj) { 
			CHash ccontrol = (CHash) obj;
			_.alert("in the jframe control");
			JFrame jframe = new JFrame(); 
			ccontrol._("m_jframe", jframe);
			_.alert("in the jframe control");
			return ccontrol;	
		}}); // end form->create()
		
		
		
		
	} // end CFormDriverMapper()
} // end CFormDriverMapper