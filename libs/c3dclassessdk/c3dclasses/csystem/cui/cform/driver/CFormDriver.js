//----------------------------------------------------------------------------
// file: cform.drv.js
// desc: defines a driver
//----------------------------------------------------------------------------

////////////////
// includes
////////////////

////////////////
// functions
////////////////

////////////////
// COptions
////////////////

//--------------------------------------------------------------------
// name: COptions_processParams()
// desc: method used to do default crud on the incoming params
//--------------------------------------------------------------------
function COptions_processParams(params){
	var strop = params["coption-operator"];
	var strname = params["coption-name"];	
	var value = params["coption-value"];
	var strmemid = params["cmemory-id"];
	var strformid = params["cform-id"];
	
	if(!strmemid) {
		if(strformid)
        		strname = strformid + "_" + strname;	
		strmemid = "crequestmemory";
	} // end if
	
	var cmemory = use_memory(strmemid);	
	if(!cmemory) {
		return;
	} // end if
	else if(strop=="get") {
		var cvar = cmemory.retrieve(strname);
		return (cvar && cvar["m_value"]) ? cvar["m_value"] : "";
	} // end if
	else if(strop == "set") {
		if(!cmemory.update(strname, value, typeof(value)))
			cmemory.create(strname, value, typeof(value));
	} // end if
	else if(strop == "remove") {
		cmemory.delete(strname);
	} // end else if
	return;
} // end COptions_processParams()


//////////////////
// CControls
//////////////////
var cmemory_md_hash = {};
//--------------------------------------------------------
// name: CControls_processParams()
// desc: method used to process params
//--------------------------------------------------------
function CControls_processParams(params){
	if(!params)
		return "";

	var strtype = params["ccontrol-type"];
	var strname = params["ccontrol-name"];
	var value = params["ccontrol-value"];
	var attributes = params["ccontrol-attributes"] || {};
	var strmemid = params["cmemory-id"];
	var strformid = params["cform-id"];
	var params = params["ccontrol-params"];
	var strcontrol="";
	
	if(!strmemid) {
		if(strformid)
        	strname = strformid + "_" + strname;	
	} // end if
	
	
	if(strtype == "section") {
		strcontrol = "";
	} // end if
	else if(strtype == "form") {
		attributes["id"] = strname;
		attributes["name"] = strname;
		attributes["value"] = value;
		strcontrol = buildHTMLOpenTag("form", attributes);
	} // end elseif
	else if(strtype == "endform") { 
		strcontrol = "</form>";//buildHTMLTag("/form");
	}  // end elseif
	else if(strtype == "label") {
		attributes["for"] = strname;
		strcontrol = buildHTMLTag("label", attributes, true, value);
	} // end elseif
	else if(strtype == "text") {
		attributes["type"] = "text";
		attributes["class"] = "widefat";
		attributes["id"] = strname;
		attributes["name"] = strname;
		attributes["value"] = value;
		strcontrol = buildHTMLTag("input", attributes);
	} // end elseif
	else if(strtype == "hidden"){	
		attributes["type"] = "hidden";
		attributes["class"] = "widefat";
		attributes["id"] = strname;
		attributes["name"] = strname;
		attributes["value"] = value;
		strcontrol = buildHTMLTag("input", attributes);
	} // end elseif
	else if(strtype == "textarea") {
		attributes["type"] = "text";
		attributes["class"] = "widefat";
		attributes["id"] = strname;
		attributes["name"] = strname;
		strcontrol = buildHTMLTag("textarea", attributes, true, value);
	} // end elseif
	else if(strtype == "checkbox") {
		attributes["type"] = "checkbox";
		attributes["id"] = strname;
		attributes["name"] = strname;
		attributes["value"] = value;	
		strcontrol = buildHTMLTag("input", attributes);
	} // end elseif
	else if(strtype == "radio") {
		attributes["type"] = "radio";
		attributes["id"] = strname;
		attributes["name"] = strname;
		attributes["value"] = value;	
		strcontrol = buildHTMLTag("input", attributes);
	} // end elseif
	else if(strtype == "button") {
		attributes["type"] = "button";
		attributes["id"] = strname;
		attributes["name"] = strname;
		attributes["value"] = value;
		strcontrol = buildHTMLTag("input", attributes);
	} // end elseif
	else if(strtype == "submit") {
		attributes["type"] = "submit";
		attributes["id"] = strname;
		attributes["name"] = strname;
		attributes["value"] = value;
		strcontrol = buildHTMLTag("input", attributes);
	} // end elseif
	else if(strtype == "select"){
		var selectedvalue = value;
		var stroptions = "";
		if(options=params["choices"]) {
			for(name in options){
				var ovalue = options[name]; 
				var selected = (selectedvalue == name) ? "selected=''" : ""; 
				var attr = {};
				if(selected) attr["selected"] = '';
				attr["value"] = name;
				stroptions += buildHTMLTag("option", attr, true, ovalue);
			} // end for
		} // end if
		attributes["id"] = strname;
		attributes["name"] = strname;
		attributes["value"] = value;
		attributes["class"] = "widefat";
		strcontrol = buildHTMLTag("select", attributes, true, stroptions);	
	} // end else if
	
	// build the hidden control to hold the cmemory meta data
	if(strmemid && !cmemory_md_hash[strname]) { 
		var i = Object.keys(cmemory_md_hash).length; 
		cmemory_md_hash[strname]=true;
		// build the cmemory meta name / id
		attributes["type"] = "hidden";
		attributes["id"] = "";
		attributes["name"] = "cmemory_md-name-" + i;
		attributes["value"] = strname;
		strmetamemname = buildHTMLTag("input", attributes);
		attributes["type"] = "hidden";
		attributes["id"] = "";
		attributes["name"] = "cmemory_md-id-" + i;
		attributes["value"] = strmemid;
		strmetamemid = buildHTMLTag("input", attributes);
		strcontrol += (strmetamemname + strmetamemid);		
	} // end if
	
	return strcontrol;
} // end CControls_processParams()

///////////
// hooks
///////////