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
	public static String processParams(CHash params, CForm cform) {
		if(params == null)
			return "";

		String strtype = (String) params._("ccontrol-type");
		String strname = (String) params._("ccontrol-name");
		String value = (String) params._("ccontrol-value");
		CHash attributes = (CHash) params._("ccontrol-attributes");
		String strmemid = (String) params._("cmemory-id");
		String strformid = (String) params._("cform-id");
		//CHash _params = (CHash) params._("ccontrol-params");
		String strcontrol = "";
	
		if(strmemid != null) {
			if(strformid != "" || strformid != null)
				strname = strformid + "_" + strname;	
		} // end if
		
		if(strtype.equals("section")) {
			strcontrol = "";
		} // end if
		else if(strtype.equals("form")) {
			//CHash hash = new CHash();
			//attributes._("id", strname);
			//attributes._("name", strname);
			//attributes._("value", value);
			CFormDriver.buildCFormFromJFrame(cform);
			//strcontrol = buildHTMLOpenTag("form", attributes);
				
			
		} // end elseif
		else if(strtype == "endform") { 
			strcontrol = "</form>";//buildHTMLTag("/form");
		}  // end elseif
	
		return " ";
	} // end processParams
	
	
	public static boolean buildCFormFromJFrame(CForm cform) {
		String strlookfeel = "";//(String)cform.getParams("ezdev.lookandfeel");
		int width = 400; //Integer.parseInt((String)cform.getParams("ezdev.width"));
		int height = 500; //Integer.parseInt((String)cform.getParams("ezdev.height");
		String pid = ""; //(String)cform.getParams("ezdev.pid");
		String title = "Form Title"; //(String)cform.getParams("ezdev.pid");
		
		JFrame jframe = new JFrame();
		try{ UIManager.setLookAndFeel(strlookfeel); }
		catch(Exception ex){}
		Container cp = jframe.getContentPane();
		cp.setLayout(new GridLayout(0, 1));	// try to manipulate this via flex like properties
		jframe.setTitle(title);  	
		jframe.getContentPane().setPreferredSize(new Dimension(width,height));
	  	jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		jframe.pack();
		jframe.setVisible(true);
		
		// set this as a property 
		cform.setParam("jframe",jframe); 
		return true;
	} // end buildCFormFromJFrame()
} // end class CFormDriver