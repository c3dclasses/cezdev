//-------------------------------------------------------------------------------
// file: CControls
// desc: defines an object responsible for creating controls in the application 
//-------------------------------------------------------------------------------
package c3dclasses.csystem.cui;
import cglobal.*; 
import c3dclasses.ccore.*;

//-----------------------------------------------------------------
// name: CControls
// desc: defines the form used in the application
//-----------------------------------------------------------------
public class CControls extends CHash {	
	protected CHash m_ccontrols = new CHash(); 		// stores all of the CControl objects
	protected CArray m_containers = new CArray();	// stores the current container ccontrol using push/pop
	
	public CControls() {}	
	public CHash getCControls(){ return this.m_ccontrols; }
	public CArray getContainers(){ return this.m_containers; }
	
	public boolean form(String strid, String value, CHash params) { return this.beginContainer("form", strid, value, params); }
	public boolean endform() { return this.endContainer("endform", null, null, null); }
	public boolean panel(String strid, String strlabel, CHash params) { return this.beginContainer("panel", strid, strlabel, params);}
	public boolean endpanel() { return this.endContainer("endpanel", null, null, null);}
	public boolean section(String strid, String strlabel, CHash params) { return this.panel(strid, strlabel, params);}
	public boolean endsection() { return this.endpanel(); }
	
	public boolean menubar(String strid, String value, CHash params) { return this.beginContainer("menubar", strid, value, params); }
	public boolean endmenubar() { return this.endContainer("endmenubar", null, null, null); }
	public boolean menu(String strid, String value, CHash params) { return this.beginContainer("menu", strid, value, params); }
	public boolean endmenu() { return this.endContainer("endmenu", null, null, null); }
	public boolean menuitem(String strid, String value, CHash params) { return this.control("menuitem", strid, value, params); }
	public boolean menuitem_seperator() { return this.control("menuitem-seperator", null, null, null); }
	
	public boolean sysmenubar(String strid, String stricon, CHash params) { return this.beginContainer("systray-menubar", strid, stricon, params); }
	public boolean endsysmenubar() { return this.endContainer("systray-endmenubar", null, null, null); }
	public boolean sysmenu(String strid, String value, CHash params) { return this.beginContainer("systray-menu", strid, value, params); }
	public boolean endsysmenu() { return this.endContainer("systray-endmenu", null, null, null); }
	public boolean sysmenuitem(String strid, String value, CHash params) { return this.control("systray-menuitem", strid, value, params); }
	public boolean sysmenuitem_checkbox(String strid, String value, CHash params) { return this.control("systray-menuitem-checkbox", strid, value, params); }
	public boolean sysmenuitem_seperator() { return this.control("systray-menuitem-seperator", null, null, null); }
	
	public boolean label(String strid, String value, CHash params) { return this.control("label", strid, value, params);}
	public boolean hidden(String strid, String value, CHash params) { return this.control("hidden", strid, value, params);}
	public boolean text(String strid, String value, CHash params) { return this.control("text", strid, value, params);}
	public boolean textarea(String strid, String value, CHash params) { return this.control("textarea", strid, value, params);}
	public boolean checkbox(String strid, String value, CHash params) { return this.control("checkbox", strid, value, params);}
	public boolean select(String strid, String value, CHash options, CHash params) { return this.choices("select", strid, value, options, params); }
	public boolean radio(String strgroupid, String strid, String value, CHash params) { 
		if(params == null)
			params = new CHash();
		params.set("m_strgroupid", strgroupid); 	
		return this.control("radio", strid, value, params);
	} // end radio()
	
	public boolean button(String strid, String value, CHash params) { return this.control("button", strid, value, params);} 
	public boolean submit(String strid, String value, CHash params) { return this.button(strid, value, params);} 
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
		params.set("m_options", options); 
		return this.control(strtype, strid, value, params);
	} // end control_choices()
	public boolean beginContainer(String strtype, String strid, String value, CHash params) {
		CControl ccontrol = this.create(strtype, strid, value, params);
		if(ccontrol != null)
			m_containers.push(ccontrol);
		return ccontrol != null;
	} // end beginContainer()
	public boolean endContainer(String strtype, String strid, String value, CHash params) {
		m_containers.pop();
		return true;		
	} // end endContainer()
	public boolean control(String strtype, String strid, String value, CHash params) {
		return this.create(strtype, strid, value, params) != null;
	} // end control()
		
	public boolean controlsFromJSONFile(String strjsonfile) {
		CJSONArray cjsonarray = _.toJSONArrayFromFile(strjsonfile, false);
		if(cjsonarray == null)
			return false;	
		for(int i=0; i<cjsonarray.length(); i++) {
			this.controlFromJSONObject((CJSONObject)cjsonarray.get(i));
		} // end for	
		return true;
	} // end controlsFromJSONFile()
	
	public boolean controlFromJSONFile(String strjsonfile) {
		return this.controlFromJSONObject(_.toJSONObjectFromFile(strjsonfile, false));
	} // end controlFromJSONFile()
	
	public boolean controlFromJSONObject(CJSONObject cjsonobject) {
		if(cjsonobject == null)
			return false;	
		String strtype = cjsonobject._str("type");
		String strid = cjsonobject._str("id");
		String value = cjsonobject._str("value");
		Object [] choices = (Object []) cjsonobject._arr("choices");
		CHash options = null;
		if(choices != null) {
			options = new CHash();
			for(Object choice : choices) { 
				CJSONObject jsonobject = (CJSONObject) choice;
				options.set(jsonobject.get("name"), jsonobject.get("value"));
			} // end for
		} // end if
		return this.choices(strtype, strid, value, options, null);		
	} // end controlsFromJSONObject()
	
	////////////////////////
	// helper functions
	////////////////////////
	public CControl create(String strtype, String strid, String value, CHash params) {
		CHash container = (CHash) this.getContainers().top();
		String strpathid = (container != null) ? (((String)container._("m_strpathid")) + " " + strid) : strid;	
		CControl ccontrol = this.retrieve(strpathid);
		if(ccontrol != null)
			return ccontrol;
		ccontrol = new CControl();
		if(ccontrol == null || !ccontrol.create(this, strtype, strid, strpathid, value, params))
			return null;
		this.m_ccontrols._(strpathid, ccontrol);			
		this.clear();
		return ccontrol;
	} // end create()
	
	// strid = cform.cpanel.cbutton
	public CControl createEx(String strtype, String strid, String value, CHash params) {
		CArray namespace = _.split(".", strid);	
		if(namespace.length() < 2)
			return this.create(strtype, strid, value, params);
		String strparentid = namespace._(0, namespace.length()-2).join(".");	
		String strchildid = (String) namespace.last();
		CControl ccontrol = this.retrieve(strparentid);
		if(ccontrol == null)
			return null;
		this.m_containers.push(ccontrol);	
		return this.create(strtype, strid, value, params);
	} // end createEx()
	
	public CControl retrieve(String strpathid) {
		return (CControl) this.m_ccontrols._(strpathid);
	} // end retrieve()
	
	public String toStringContents() {
		CArray keys = this.m_ccontrols.keys();
		if(keys == null)
			return "";
		int len = keys.length();
		String str = "";
		for(int i=0; i<len; i++) {
			CControl ccontrol = (CControl) this.m_ccontrols._((String)keys._(i));
			str += ccontrol.toStringContents();
			if(i != len-1)
				str += "\n";
		} // end for
		return str;
	} // end toString()
} // end class CControls