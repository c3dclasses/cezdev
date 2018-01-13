//------------------------------------------------------------------------------------------------
// name: CControlsDriver
// desc: defines the driver interface and implementor to do crud operation on control objects
//------------------------------------------------------------------------------------------------
package c3dclasses;
import java.io.*;
import java.util.*;

//-----------------------------------------------------------------------------
// name: CControlsDriver
// desc: defines the interface object to do crud operation on control objects
//-----------------------------------------------------------------------------
public class CControlsDriver extends CProcessor {	
	public CControlsDriver() {
		// setup ccontrols processor instruction set for each type of control
		super();
		
		// add some instruction sets to the processor
		this.addCInstructions("CControlInstructions", new CControlInstructions(this));	
		this.addCInstructions("CFormInstructions", new CFormInstructions(this));
	} // end CControlsDriver()
	
	
	public String toInstruction(Object operand) {
		CControl _this = (CControl) operand;
		if(_this == null)
			return "";
		String strtype = (String) _this._("m_strtype");
		String strdefaulttype = (String) _this._("m_strdefaulttype");
		String straction = (String) _this._("m_straction");
		String strpropname = (String) _this._("m_strpropname");
		String strinstruction = "";
		if(strdefaulttype != null)	// control
			strinstruction += strdefaulttype;	
		else if(strtype != null)
			strinstruction += strtype;
		if(straction != null)
			strinstruction += "->" + straction;
		if(strpropname != null)
			strinstruction += "->" + strpropname;	
		return strinstruction.toLowerCase();
	} // end toInstruction()
	
	// setup processor specific to this object (CControls)  
	static public CProcessor m_processor = new CControlsDriver();
	public static CControl call(CControl ccontrol) { return (CControl) CControlsDriver.m_processor.process(ccontrol); }
} // end CControlsDriver
