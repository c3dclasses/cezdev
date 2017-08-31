//---------------------------------------------------------------------------------------------
// name: CEZDEVButton
// desc: defines a button control object used by EZDEV
//---------------------------------------------------------------------------------------------
package cezdev;
import java.awt.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.*;
import javax.swing.*;
import cglobal.*;

//----------------------------------------------------------------------------------
// name: CEZDEVButton
// desc: defines a button control object used by EZDEV
//----------------------------------------------------------------------------------
public class CEZDEVButton extends CEZDEVControl {
	protected JButton m_jbutton = null;
	
	public CEZDEVButton(String strcmdfile) {
		super(strcmdfile);
	} // end CEZDEVControl()
	
	public boolean createControl() {
		this.m_jbutton = new JButton();
		return (this.m_jbutton != null);
	} // end createControl()
		
	public boolean setProperties(CJSONObject cjsonobject) {
		if(this.m_jbutton == null)
			return false;
		
		String str = (String) cjsonobject.get("name");
		if(str == null || str == "") {
			str = "NOLABEL";
		} // end if 
		this.m_jbutton.setLabel(str);	
		
		str = (String) cjsonobject.get("value");
		if( str == null || str == "" ) {
			str = "NOVALUE";
		} // end if
		
		str = (String) cjsonobject.get("icon");
		if(str != null || str != "" || _.isFile(str) == false) {
			str = _.getPath(this.getFilename()) + "\\" + str;
			this.m_jbutton.setIcon(new ImageIcon(str));	
		} // end if	
		
		// add event properties
		final CEZDEVButton _this = this;
		this.m_jbutton.addActionListener((new ActionListener() { 
			public void actionPerformed(ActionEvent e) {	
				//CConfigurationVariables.update();
				//_this.doCommand();
			} // end actionPerformed()
		})); // end addActionListener()

		return true;
	} // end setProperties()	
} // end CEZDEVButtonControl