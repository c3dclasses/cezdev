//---------------------------------------------------------
// file: CControls
// desc: defines controls used inside a form
//---------------------------------------------------------
package c3dclasses.csystem.cui;
import cglobal.*; 
import c3dclasses.ccore.*;
import c3dclasses.csystem.cui.*;

//-----------------------------------------------------------------
// name: CControls
// desc: defines controls used inside a form
//-----------------------------------------------------------------
public class CControls extends CHash {		
	protected CForm m_cform = null;
	protected String m_cmemoryid = "";
	public CControls() { this.m_cform=null; }	
	public boolean create(CForm cform) { this.m_cform = cform; return true; }
	public String form(String strname, String value, CHash params) { return this.control("form", strname, value, params); }
	public String endform() { return this.control("endform",null,null,null); }
	public String section(String strname, String strlabel, CHash params) { return this.control("section", strname, strlabel, params);}
	public String label(String strname, String value, CHash params) { return this.control("label", strname, value, params);}
	public String hidden(String strname, String value, CHash params) { return this.control("hidden", strname, value, params);}
	public String text(String strname, String value, CHash params) { return this.control("text", strname, value, params);}
	public String textarea(String strname, String value, CHash params) { return this.control("textarea", strname, value, params);}
	public String select(String strname, String value, CHash options, CHash params) { return this.control_choices("select", strname, value, options, params);}
	public String checkbox(String strname, String value, CHash params) { return this.control("checkbox", strname, value, params);}
	public String radio(String strname, String value, CHash params) {return this.control("radio", strname, value, params);}
	public String button(String strname, String value, CHash params) { return this.control("button", strname, value, params);} 
	public String submit(String strname, String value, CHash params) { return this.control("submit", strname, value, params);} 
	public String dropDownPages(String strname, String value, CHash params) { return this.control("dropdown-pages", strname, value, params); }
	public String colorpicker(String strname, String value, CHash params) { return this.control("color", strname, value, params); }
	public String image(String strname, String value, CHash params) { return this.control("image", strname, value, params); }
	public String fileupload(String strname, String value, CHash params) { return this.control("fileupload", strname, value, params); }
	public String crud(String strname, String strtype, CHash params) { 
		this.clear();
		this.set("data-name", strname); 
		this.set("data-type", strtype); 
		this.set("data-action", "create");
		this.set("class", "ccontrol-crud");
		String str = this.button( "btn-" + strname + "-create", "create", null);
		this.set("data-action", "retrieve"); 
		str += this.button( "btn-" + strname + "-retrieve", "retrieve", null);
		this.set("data-action", "update"); 
		str += this.button( "btn-" + strname + "-update", "update", null); 
		this.set("data-action", "delete"); 
		str += this.button( "btn-" + strname + "-delete", "delete", null); 	 
		this.clear();
		return str;
	} // end crud()
	public String control_choices(String strtype, String strname, String value, CHash options, CHash params) {
		params.set("choices",options); 
		return this.control(strtype, strname, value, params);
	} // end control_choices()
	public String control(String strtype, String strname, String value, CHash params) {
		
		/*
		COptions coptions = null;
		if(this.m_cform != null && (coptions = this.m_cform.getCOptions()) != null && strtype.equals("label")) {
			String ovalue = (coptions.optionExists(strname)) ? coptions.option(strname) : "";
			 if((strtype == "radio" || strtype == "checkbox")) {
				if(!ovalue.equals("")) {
					if(ovalue.equals(value)) // optionvalue == attribute-value
						this.set("checked","");
					else this.remove("checked");
				} // end if
				else {
				} // end else
				value = value;
			} // end if
			else value = (ovalue != null) ? ovalue : value;
		} // end if
		*/
		CHash _params = new CHash();
		_params.set("ccontrol-type", strtype);
		_params.set("ccontrol-name", strname); 
		_params.set("ccontrol-value", value);
		_params.set("ccontrol-params", params);
		_params.set("ccontrol-attributes", this);
		_params.set("cmemory-id", this.m_cform.getCMemoryID());
		_params.set("cform-id", this.m_cform.getID());
		String ret = this.processParams(_params);
		this.clear();
		return ret;
	} // end control()
	public String processParams(CHash params) { return CFormDriver.processParams(params, this.m_cform); }
} // end class CControls