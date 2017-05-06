//---------------------------------------------------------
// file: ccontrols.js
// desc: defines controls used inside a form
//---------------------------------------------------------

//-----------------------------------------------------------------
// name: CControls
// desc: defines controls used inside a form
//-----------------------------------------------------------------
var CControls = new Class({		
	Extends : CHash,	
	initialize : function(){ this.m_cform=null; this.m_cmemoryid=""; this.parent();},	
	create : function(cform){ this.m_cform = cform; this.parent(); return true; },
	form : function(strname, value, params) { ob_start(); echo(this.control("form", strname, value, params)); },
	endform : function(){ echo(this.control("endform",null,null,null)); return ob_end(); },
	section : function(strname, strlabel, params){ return this.control("section", strname, strlabel, params);},
	label : function(strname, value, params){ return this.control("label", strname, value, params);},
	hidden : function(strname, value, params){ return this.control("hidden", strname, value, params);},
	text : function(strname, value, params){ return this.control("text", strname, value, params);},
	textarea : function(strname, value, params){ return this.control("textarea", strname, value, params);},
	select : function(strname, value, opts, params){return this.control_choices("select", strname, value, opts, params);},
	checkbox : function(strname, value, params){ return this.control("checkbox", strname, value, params);},
	radio : function(strname, value, params){return this.control("radio", strname, value, params);},
	button : function(strname, value, params){ return this.control("button", strname, value, params);}, 
	submit : function(strname, value, params){ return this.control("submit", strname, value, params);}, 
	dropDownPages : function(strname, value, params){ return this.control("dropdown-pages", strname, value, params); },
	colorpicker : function(strname, value, params){ return this.control("color", strname, value, params); },
	image : function(strname, value, params){ return this.control("image", strname, value, params); },
	fileupload : function(strname, value, params){ return this.control("fileupload", strname, value,params); },
	crud : function(strname, strtype, params){ 
		strtype = strtype | "string";
		this.clear();
		this.set("data-name", strname); 
		this.set("data-type", strtype); 
		this.set("data-action", "create");
		this.set("class", "ccontrol-crud");
		str = this.button( "btn-" + strname + "-create", "create");
		this.set("data-action", "retrieve"); 
		str += this.button( "btn-" + strname + "-retrieve", "retrieve");
		this.set("data-action", "update"); 
		str += this.button( "btn-" + strname + "-update", "update"); 
		this.set("data-action", "delete"); 
		str += this.button( "btn-" + strname + "-delete", "delete"); 	 
		this.clear();
		return str;
	}, // end crud()
	control_choices : function(strtype, strname, value, options, params){
		params = params || {};
		params['choices']=options; 
		return this.control(strtype, strname, value, params);
	}, // end control_choices()
	control : function(strtype, strname, value, params){		
		if(this.m_cform && (coptions = this.m_cform.getCOptions()) && strtype != "label") {
			ovalue = (coptions.optionExists(strname)) ? coptions.option(strname) : "";	
			if(strtype == "radio" || strtype == "checkbox") {
				if(ovalue != "") {
					if(ovalue == value) // optionvalue == attribute-value
						this.set("checked","");
					else this.remove("checked");
				} // end if
				else {
				} // end else
				value = value;
			} // end if
			else value = (ovalue) ? ovalue : value;
		} // end if
		var _params={};
		_params["ccontrol-type"]=strtype;
		_params["ccontrol-name"]=strname; 
		_params["ccontrol-value"]=value;
		_params["ccontrol-params"]=params;
		_params["ccontrol-attributes"]=this._();	
		_params["cmemory-id"]=this.m_cform.getCMemoryID();
		_params["cform-id"]=this.m_cform.getID();
		var ret = this.processParams(_params);
		this.clear();
		return ret;
	}, // end control()
	processParams : function(params){ return CControls_processParams(params); } 
}); // end class CControls