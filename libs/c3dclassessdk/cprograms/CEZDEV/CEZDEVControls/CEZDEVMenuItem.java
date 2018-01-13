//---------------------------------------------------------------------------------------------
// name: CEZDEVMenuItem
// desc: defines a button control object used by EZDEV
//---------------------------------------------------------------------------------------------
import java.awt.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.*;
import javax.swing.*;
import c3dclasses.*;


//----------------------------------------------------------------------------------
// name: CEZDEVMenuItem
// desc: defines a button control object used by EZDEV
//----------------------------------------------------------------------------------
public class CEZDEVMenuItem extends CEZDEVControl {
	protected JMenuItem m_jmenuitem = null;
	
	public CEZDEVMenuItem(String strcmdfile) {
		super(strcmdfile);
	} // end CEZDEVControl()
	
	public boolean createControl() {
		this.m_jmenuitem = new JMenuItem();
		return (this.m_jmenuitem != null);
	} // end createControl()
		
	public boolean setProperties(CJSONObject cjsonobject) {
		if(this.m_jmenuitem == null)
			return false;
		
		String str = (String) cjsonobject.get("name");
		if(str == null || str == "") {
			str = "NOLABEL";
		} // end if 
		this.m_jmenuitem.setLabel(str);	
		
		str = (String) cjsonobject.get("value");
		if( str == null || str == "" ) {
			str = "NOVALUE";
		} // end if
		
		str = (String) cjsonobject.get("icon");
		if(str != null || str != "" || _.is_file(str) == false) {
			str = _.get_path(this.getFilename()) + "\\" + str;
			this.m_jmenuitem.setIcon(new ImageIcon(str));	
		} // end if	
		
		// add event properties
		final CEZDEVMenuItem _this = this;
		this.m_jmenuitem.addActionListener((new ActionListener() { 
			public void actionPerformed(ActionEvent e) {	
				//CConfigurationVariables.update();
				//_this.doCommand();
			} // end actionPerformed()
		})); // end addActionListener()

		return true;
	} // end setProperties()	
} // end CEZDEVMenuItem