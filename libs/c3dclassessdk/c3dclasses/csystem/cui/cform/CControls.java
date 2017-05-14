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
/*	
	protected CForm m_cform = null;
	protected CHash m_selected = null;
	protected String m_cmemoryid = "";
	public CControls() { this.m_cform=null; this.m_selected=null; }	
	public boolean create(CForm cform) { this.m_cform = cform; return true; }
	public CForm getCForm() { return this.m_cform; }
	
	// create controls
	public boolean form(String strname, String value, CHash params) { return this.create("form", strname, value, params); }
	public boolean endform() { return this.create("endform",null,null,null); }
	public boolean section(String strname, String strlabel, CHash params) { return this.create("section", strname, strlabel, params);}
	public boolean label(String strname, String value, CHash params) { return this.create("label", strname, value, params);}
	public boolean hidden(String strname, String value, CHash params) { return this.create("hidden", strname, value, params);}
	public boolean text(String strname, String value, CHash params) { return this.create("text", strname, value, params);}
	public boolean textarea(String strname, String value, CHash params) { return this.create("textarea", strname, value, params);}
	public boolean select(String strname, String value, CHash options, CHash params) { return this.choices("select", strname, value, options, params);}
	public boolean checkbox(String strname, String value, CHash params) { return this.create("checkbox", strname, value, params);}
	public boolean radio(String strname, String value, CHash params) {return this.create("radio", strname, value, params);}
	public boolean button(String strname, String value, CHash params) { return this.create("button", strname, value, params);} 
	public boolean submit(String strname, String value, CHash params) { return this.create("submit", strname, value, params);} 
	public boolean dropDownPages(String strname, String value, CHash params) { return this.create("dropdown-pages", strname, value, params); }
	public boolean colorpicker(String strname, String value, CHash params) { return this.create("color", strname, value, params); }
	public boolean image(String strname, String value, CHash params) { return this.create("image", strname, value, params); }
	public boolean fileupload(String strname, String value, CHash params) { return this.create("fileupload", strname, value, params); }
	public boolean crud(String strname, String strtype, CHash params) { 
		this.clear();
		this.set("data-name", strname); 
		this.set("data-type", strtype); 
		this.set("data-action", "create");
		this.set("class", "ccontrol-crud");
		this.button( "btn-" + strname + "-create", "create", null);
		this.set("data-action", "retrieve"); 
		this.button( "btn-" + strname + "-retrieve", "retrieve", null);
		this.set("data-action", "update"); 
		this.button( "btn-" + strname + "-update", "update", null); 
		this.set("data-action", "delete"); 
		this.button( "btn-" + strname + "-delete", "delete", null); 	 
		this.clear();
		return true;
	} // end crud()	
	public boolean choices(String strtype, String strname, String value, CHash options, CHash params) {
		params.set("choices",options); 
		return this.create(strtype, strname, value, params);
	} // end control_choices()
	
	// update controls
	public CControls css(CHash params) {
		this.update(params);
		return this;
	} // end css()
	
	/////////////////////
	// CRUD operations 
	public boolean create(String strtype, String strname, String value, CHash params) {
		CHash _params = new CHash();
		_params.set("ccontrol-type", strtype);
		_params.set("ccontrol-name", strname); 
		_params.set("ccontrol-value", value);
		_params.set("ccontrol-params", params);
		_params.set("ccontrol-attributes", this);
		_params.set("cmemory-id", this.m_cform.getCMemoryID());
		_params.set("cform-id", this.m_cform.getID());
		boolean ret = CFormDriver.create(_params);
		this.clear();
		return ret;
	} // end create()
	
	public CControls retrieve(String strname) {
		CHash _params = new CHash();
		_params.set("cform-id", this.m_cform.getID());
		//this.m_selected = CFormDriver.retrieve(_params);
		return this;
	} // end retrieve()
	
	public CControls update(CHash params) {
		//CFormDriver.update(this.m_selected, params);
		return this;
	} // end retrieve()
	
	public CControls delete(CHash params) {
		//CFormDriver.update(this.m_selected, params);
		return this;
	} // end retrieve()
	*/
} // end class CControls