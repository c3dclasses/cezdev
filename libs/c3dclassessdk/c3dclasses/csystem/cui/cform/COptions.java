//---------------------------------------------------------
// file: COptions
// desc: defines the options object of the form
//---------------------------------------------------------
package c3dclasses.csystem.cui;
import cglobal.*; 
import c3dclasses.ccore.*;

//-----------------------------------------------------------------
// name: COptions
// desc: defines the options object of the form
//-----------------------------------------------------------------
public class COptions {	
	public CForm m_cform;
	public COptions(){ this.m_cform = null; }	
	public boolean create(CForm cform){ this.m_cform=cform; return true;}
	public boolean optionExists(String strname){ return (this.processOption("get",strname, null, null) != null); } 
	public void removeOption(String strname){ this.processOption("remove", strname, null, null); }
	public String getOption(String strname){ return this.processOption("get", strname, null, null); }
	public void setOption(String strname, String strvalue){ this.processOption("set", strname, strvalue, null); }
	public String processOption(String stroperator, String strname, String strvalue, CHash params){ 
		if(this.m_cform == null || strname == null || strname == "")
			return "";
		CHash _params = new CHash();
		_params.set("coption-operator",stroperator);
		_params.set("coption-name",strname); 
		_params.set("coption-value",strvalue);
		_params.set("coption-params",params);
		_params.set("cform-id",this.m_cform.getID());
		_params.set("cmemory-id",this.m_cform.getCMemoryID());
		return this.processParams(_params, null);
	} // end processOption()
	// override this behavior to process the option
	public String processParams(CHash params, String strname){ return " "; } //return COptions_processParams(params); } 
} // end class COptions