//---------------------------------------------------------------------------------------------
// name: CEZDEVDropDown.java
// desc: defines a combobox object thats created from a json file containing values
//---------------------------------------------------------------------------------------------
import java.awt.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.*;
import javax.swing.*;
import c3dclasses.*;

//-------------------------------------------------------------------------------------
// name: CEZDEVDropdown
// desc: defines a combobox object thats created from a json file containing values
//-------------------------------------------------------------------------------------
public class CEZDEVDropdown extends CEZDEVControl {
	protected JComboBox m_jcombobox = null;
	
	public CEZDEVDropdown(String strcmdfile) {
		super(strcmdfile);
	} // end CEZDEVDropdown()
	
	public boolean createControl() {
		this.m_jcombobox = new JComboBox();
		return (this.m_jcombobox != null);
	} // end createControl()
	
	public boolean setProperties(CJSONObject cjsonobject) {
		if(this.m_jcombobox == null)
			return false;
			
		String str = (String) cjsonobject.get("name");
		if(str == null || str == "") {
			str = "NOLABEL";
		} // end if 
		//this.m_jbutton.setLabel(str);	
		
		str = (String) cjsonobject.get("value");
		if( str == null || str == "" ) {
			str = "NOVALUE";
		} // end if
		
		str = (String) cjsonobject.get("icon");
		if(str != null && str != "") {
			if(_.is_file(str) == false) {
				str = _.get_path(this.getFilename()) + "\\" + str;
		//		this.m_jbutton.setIcon(new ImageIcon(str));	
			} // end if
		} // end if	
		
		str = (String) cjsonobject.get("value");
		if( str != null && str == "" ) {
			str = "NOVALUE";
		} // end if
		/*	
		// add event properties
		final CEZDEVDropdown _this = this;
		this.m_jbutton.addActionListener((new ActionListener() { 
			public void actionPerformed(ActionEvent e) {	
				//CConfigurationVariables.update();
				//_this.doCommand();
			} // end actionPerformed()
		})); // end addActionListener()
		*/
		return true;
	} // end setProperties()	
} // end CEZDEVDropdown