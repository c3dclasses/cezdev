//---------------------------------------------------------
// file: CControl
// desc: defines a control object
//---------------------------------------------------------
package c3dclasses.csystem.cui;
import cglobal.*; 
import c3dclasses.ccore.*;
import c3dclasses.csystem.cui.*;

//-----------------------------------------------------------------
// name: CControl
// desc: defines a control object
//-----------------------------------------------------------------
public class CControl extends CHash {		
	///////////////////
	// get/set prop
	///////////////////
	public CControl setProp(String strprop, Object value) {	
		this._("m_straction", "set");
		this._("m_strpropname", strprop);
		this._("m_propvalue", value);
		CControlsDriver.call(this);
		return this;
	} // end setProp()
	
	public Object getProp(String strprop) {	
		this._("m_straction", "get");
		this._("m_strpropname", strprop);
		CControlsDriver.call(this);
		return this._("m_propvalue");
	} // end getProp()
	
	///////////////////
	// CRUD
	///////////////////
	public boolean create(CControls ccontrols, String strtype, String strid, String strpathid, String value, CHash params) {
		this._("m_straction", "create");
		this._("m_ccontrols", ccontrols);
		this._("m_strtype", strtype);
		this._("m_strid", strid);
		this._("m_strpathid", strpathid);
		this._("m_value", value);
		this._("m_params", params);
		this._("m_attributes", ccontrols);
		this._("m_container", ccontrols.getContainers().top());
		this._("m_address", this);
		return (CControlsDriver.call(this) != null);
	} // end create()
	
	public boolean delete() {
		this._("m_straction", "delete");
		CControls ccontrols = (CControls) this._("m_ccontrols");
		String strpathid = (String) this._("m_strpathid");
		if(CControlsDriver.call(this) == null)
			return false;
		ccontrols.getCControls().remove(strpathid);	
		return true;		
	} // end delete()
	
	//////////////////////
	// toString
	//////////////////////
	public String toStringContents() {		
		CArray keys = this.keys();
		if(keys == null)
			return "";
		int len = keys.length();
		String str = "";
		for(int i=0; i<len; i++) {
			String strpathid = (String)keys._(i);
			str += strpathid + ": " + this._(strpathid) + "\n";
		} // end for
		return str;
	} // end toStringContents()
} // end class CControl