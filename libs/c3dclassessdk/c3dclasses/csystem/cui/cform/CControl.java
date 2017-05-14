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
	public CControl setProp(String strprop, String value) {	
		this._("m_straction", "set");
		this._("m_strpropname", strprop);
		this._("m_strpropvalue", value);
		CFormDriver.call(this);
		return this;
	} // end setProp()
	
	public String getProp(String strprop) {	
		this._("m_straction", "get");
		this._("m_strpropname", strprop);
		CFormDriver.call(this);
		return (String)this._("m_strpropvalue");
	} // end getProp()
} // end class CControl