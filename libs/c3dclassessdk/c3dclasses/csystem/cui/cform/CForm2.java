//----------------------------------------------------------------------------
// file: CForm
// desc: defines a form object for creating controls
//----------------------------------------------------------------------------
package c3dclasses.csystem.cui;
import cglobal.*; 
import c3dclasses.ccore.*;

//-----------------------------------------------------------------
// name: CForm
// desc: defines the form used in the application
//-----------------------------------------------------------------
public class CForm2 extends CHash {	
	protected CHash m_params = new CHash();			// stores the parameters of the form
	protected CHash m_ccontrols = new CHash(); 		// stores the ccontrols of the form
	protected CArray m_containers = new CArray();	// stores the current container ccontrol using push/pop
	
	////////////////
	// form 
	////////////////
	public CForm2() {}	
	public boolean create(String strid, CHash params) { this.m_params.append(params); this.setID(strid); return true; }
	public void setID(String strid) { this.setParam("m_strid", strid); }
	public String getID() { return (String) this.getParam("m_strid"); } 
	public void setCMemoryID(String strid) { this.setParam("m_strcmemoryid", strid); }
	public String getCMemoryID() { return (String) this.getParam("m_strcmemoryid"); }
	public void setParam(String strid, Object value) { this.m_params.set(strid, value); }
	public Object getParam(String strid) { CHash params = this.getParams(); return (params != null) ? (Object) params.get(strid) : null; }
	public CHash getParams() { return this.m_params; }
	public CHash getCControls(){ return this.m_ccontrols; }
	public CArray getContainers(){ return this.m_containers; }
	
	////////////////
	// controls
	////////////////
	
	// creating
	public boolean form(String strid, String value, CHash params) { return this.beginContainer("form", strid, value, params); }
	public boolean endform() { return this.endContainer("endform", null, null, null); }
	public boolean section(String strid, String strlabel, CHash params) { return this.control("section", strid, strlabel, params);}
	public boolean label(String strid, String value, CHash params) { return this.control("label", strid, value, params);}
	public boolean hidden(String strid, String value, CHash params) { return this.control("hidden", strid, value, params);}
	public boolean text(String strid, String value, CHash params) { return this.control("text", strid, value, params);}
	public boolean textarea(String strid, String value, CHash params) { return this.control("textarea", strid, value, params);}
	public boolean select(String strid, String value, CHash options, CHash params) { return this.choices("select", strid, value, options, params);}
	public boolean checkbox(String strid, String value, CHash params) { return this.control("checkbox", strid, value, params);}
	public boolean radio(String strid, String value, CHash params) {return this.control("radio", strid, value, params);}
	public boolean button(String strid, String value, CHash params) { return this.control("button", strid, value, params);} 
	public boolean submit(String strid, String value, CHash params) { return this.control("submit", strid, value, params);} 
	public boolean dropDownPages(String strid, String value, CHash params) { return this.control("dropdown-pages", strid, value, params); }
	public boolean colorpicker(String strid, String value, CHash params) { return this.control("color", strid, value, params); }
	public boolean image(String strid, String value, CHash params) { return this.control("image", strid, value, params); }
	public boolean fileupload(String strid, String value, CHash params) { return this.control("fileupload", strid, value, params); }
	public boolean crud(String strid, String strtype, CHash params) { 
		this.clear();
		this.set("data-name", strid); 
		this.set("data-type", strtype); 
		this.set("data-action", "create");
		this.set("class", "ccontrol-crud");
		this.button("btn-" + strid + "-create", "create", null);
		this.set("data-action", "retrieve"); 
		this.button("btn-" + strid + "-retrieve", "retrieve", null);
		this.set("data-action", "update"); 
		this.button("btn-" + strid + "-update", "update", null); 
		this.set("data-action", "delete"); 
		this.button("btn-" + strid + "-delete", "delete", null); 	 
		this.clear();
		return true;
	} // end crud()	
	public boolean choices(String strtype, String strid, String value, CHash options, CHash params) {
		if(params == null)
			params = new CHash();
		params.append(options); 
		return this.control(strtype, strid, value, params);
	} // end control_choices()
	public boolean beginContainer(String strtype, String strid, String value, CHash params) {
		this.setParam("m_strtype", strtype);
		CHash ccontrol = this.create(strid, value, params);
		if(ccontrol != null)
			m_containers.push(ccontrol);
		return ccontrol != null;
	} // end beginContainer()
	public boolean endContainer(String strtype, String strid, String value, CHash params) {
		//this.setParam("ccontrol-type", strtype);
		//CControl ccontrol = this.create(strtype, strid, value, params);
		//if(bret)
		m_containers.pop();
		return true;		
	} // end endContainer()
	public boolean control(String strtype, String strid, String value, CHash params) {
		this.setParam("m_strtype", strtype);
		return this.create(strid, value, params) != null;
	} // end control()

	////////////////
	// toString()
	////////////////
	public String toStringContents() {
		CArray keys = this.m_ccontrols.keys();
		if(keys == null)
			return "";
		int len = keys.length();
		String str = "";
		for(int i=0; i<len; i++) {
			str += this.toStringContents((String)keys._(i));
			if(i != len-1)
				str += "\n";
		} // end for
		return str;
	} // end toString()
	public String toStringContents(String strpathid) {
		return this.toStringContents((CHash) this.m_ccontrols._(strpathid));
	} // end toStringContents()
	public String toStringContents(CHash ccontrol) {
		if(ccontrol == null)
			return "";
		CArray keys = ccontrol.keys();
		if(keys == null)
			return "";
		int len = keys.length();
		String str = "";
		for(int i=0; i<len; i++) {
			String strpathid = (String)keys._(i);
			str += strpathid + ": " + ccontrol._(strpathid) + "\n";
		} // end for
		return str;
	} // end toStringContents()
		
	//////////////////
	// CRUD
	//////////////////	
	public CHash create(String strid, String value, CHash params) {
		CHash container = (CHash) this.getContainers().top();
		String strpathid = (container != null) ? (((String)container._("m_strpathid")) + " " + strid) : strid;	
		CControl ccontrol = this.retrieve(strpathid);
		if(ccontrol != null)
			return ccontrol;
		ccontrol = new CControl();
		if(ccontrol == null)
			return null;
		ccontrol._("m_strid", strid);
		ccontrol._("m_strpathid", strpathid);
		ccontrol._("m_value", value);
		ccontrol._("m_params", params);
		ccontrol._("m_straction", "create");
		ccontrol._("m_attributes", this);
		ccontrol._("m_strtype", this.getParam("m_strtype"));
		ccontrol._("m_strmemid", this.getCMemoryID());
		ccontrol._("m_container", container);
		ccontrol._("m_address", ccontrol);
		if(CFormDriver.call(ccontrol) == null) // create any driver related stuff
			return null;
		this.m_ccontrols._(strpathid, ccontrol);			
		this.clear();
		return ccontrol;
	} // end create()
	
	public CControl retrieve(String strpathid) {
		return (CControl) this.m_ccontrols._(strpathid);
	} // end retrieve()
	
	public boolean delete(String strpathid) {
		CControl ccontrol = this.retrieve(strpathid);
		ccontrol._("m_straction", "delete");
		if(CFormDriver.call(ccontrol) == null)
			return false;
		this.m_ccontrols.remove(strpathid);	
		return true;		
	} // end delete()
} // end class CForm