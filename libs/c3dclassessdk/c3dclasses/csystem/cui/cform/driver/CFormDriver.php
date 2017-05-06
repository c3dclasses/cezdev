<?php
//----------------------------------------------------------------------------
// file: cform.drv.php
// desc: defines a driver
//----------------------------------------------------------------------------

////////////////
// includes
////////////////
include_js(relname(__FILE__) . "/cform.drv.js");
include_array_memory("crequestmemory", "crequestmemory", $_REQUEST, array("client"=>true));

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
function COptions_processParams($params){
	$strop = $params["coption-operator"];
	$strname = $params["coption-name"];
	$value = $params["coption-value"];
	$strmemid = $params["cmemory-id"];
	$strformid = $params["cform-id"];
	
	if(!$strmemid) {
		if($strformid)
			$strname = $strformid . "_" . $strname;
		$strmemid = "crequestmemory";
	} // end if
	
	$cmemory = use_memory($strmemid);
	if(!$cmemory) {
		return;
	} // end if
	else if($strop == "get") {
		$cvar = $cmemory->retrieve($strname);
		return isset($cvar["m_value"]) ? $cvar["m_value"] : "";
	} // end if
	else if($strop == "set") {
		if(!$cmemory->update($strname, $value, gettype($value)))
			$cmemory->create($strname, $value, gettype($value));
	} // end if
	else if($strop == "remove") {
		$cmemory->delete($strname);
	} // end else if
	return;
} // end COptions_processParams()


//////////////////
// CControls
//////////////////

$cmemory_md_hash = NULL;
//--------------------------------------------------------
// name: CControls_processParams()
// desc: method used to process params
//--------------------------------------------------------
function CControls_processParams($params){
	if(!$params)
		return "";
	$strtype = $params["ccontrol-type"];
	$strname = $params["ccontrol-name"];
	$value = $params["ccontrol-value"];
	$attributes = $params["ccontrol-attributes"];	
	$strmemid = $params["cmemory-id"];
	$strformid = $params["cform-id"];
	$params = $params["ccontrol-params"];
	$strcontrol="";

	if(!$strmemid) {
		if($strformid)
        	$strname = $strformid . "_" . $strname;	
	} // end if
	
	if($strtype == "section") {
		$strcontrol = "";
	} // end if
	else if($strtype == "form") {
		$attributes["id"] = $strname;
		$attributes["name"] = $strname;
		$attributes["value"] = $value;
		$strcontrol = buildHTMLOpenTag("form", $attributes);
	} // end elseif
	else if($strtype == "endform") { 
		$strcontrol = "</form>";//buildHTMLTag("/form");
	}  // end elseif
	else if($strtype == "label") {
		$attributes["for"] = $strname;
		$strcontrol = buildHTMLTag("label", $attributes, true, $value);
	} // end elseif
	else if($strtype == "text") {
		$attributes["type"] = "text";
		$attributes["class"] = "widefat";
		$attributes["id"] = $strname;
		$attributes["name"] = $strname;
		$attributes["value"] = $value;
		$strcontrol = buildHTMLTag("input", $attributes);
	} // end elseif
	else if($strtype == "hidden"){	
		$attributes["type"] = "hidden";
		$attributes["class"] = "widefat";
		$attributes["id"] = $strname;
		$attributes["name"] = $strname;
		$attributes["value"] = $value;
		$strcontrol = buildHTMLTag("input", $attributes);
	} // end elseif
	else if($strtype == "textarea") {
		$attributes["type"] = "text";
		$attributes["class"] = "widefat";
		$attributes["id"] = $strname;
		$attributes["name"] = $strname;
		$strcontrol = buildHTMLTag("textarea", $attributes, true, $value);
	} // end elseif
	else if($strtype == "checkbox") {
		$attributes["type"] = "checkbox";
		$attributes["id"] = $strname;
		$attributes["name"] = $strname;
		$attributes["value"] = $value;	
		$strcontrol = buildHTMLTag("input", $attributes);
	} // end elseif
	else if($strtype == "radio") {
		$attributes["type"] = "radio";
		$attributes["id"] = $strname;
		$attributes["name"] = $strname;
		$attributes["value"] = $value;	
		$strcontrol = buildHTMLTag("input", $attributes);
	} // end elseif
	else if($strtype == "button") {
		$attributes["type"] = "button";
		$attributes["id"] = $strname;
		$attributes["name"] = $strname;
		$attributes["value"] = $value;
		$strcontrol = buildHTMLTag("input", $attributes);
	} // end elseif
	else if($strtype == "submit") {
		$attributes["type"] = "submit";
		$attributes["id"] = $strname;
		$attributes["name"] = $strname;
		$attributes["value"] = $value;
		$strcontrol = buildHTMLTag("input", $attributes);
	} // end elseif
	else if($strtype == "select"){		
		$selectedvalue = $value;
		$stroptions = "";
		if($options=$params["choices"]) {
			foreach($options as $name=>$ovalue){ 
				$selected = ($selectedvalue == $name) ? "selected=''" : ""; 
				$attr = NULL;
				if($selected) $attr["selected"] = '';
				$attr["value"] = $name;
				$stroptions .= buildHTMLTag("option", $attr, true, $ovalue);
			} // end foreach 
		} // end if
		$attributes["id"] = $strname;
		$attributes["name"] = $strname;
		$attributes["value"] = $value;
		$attributes["class"] = "widefat";
		$strcontrol = buildHTMLTag("select", $attributes, true, $stroptions);	
	} // end else if
	
	// build the hidden control to hold the cmemory meta data
	global $cmemory_md_hash;
	if($strmemid && !isset($cmemory_md_hash[$strname])) { 
		$i = count($cmemory_md_hash);	
		$cmemory_md_hash[$strname]=true;
		
		// build the cmemory meta name / id
		$attributes["type"] = "hidden";
		$attributes["id"] = "";
		$attributes["name"] = "cmemory_md-name-$i";
		$attributes["value"] = $strname;
		$strmetamemname = buildHTMLTag("input", $attributes);
		
		$attributes["type"] = "hidden";
		$attributes["id"] = "";
		$attributes["name"] = "cmemory_md-id-$i";
		$attributes["value"] = $strmemid;
		$strmetamemid = buildHTMLTag("input", $attributes);
		$strcontrol .= ($strmetamemname . $strmetamemid);				
	} // end if
	
	return $strcontrol;
} // end CControls_processParams()

///////////
// hooks
///////////

//---------------------------------------------------------------------
// name: CForm_REQUEST_toJSON()
// desc: takes the request and make it available as a json object
//---------------------------------------------------------------------
function CForm_REQUEST_toJSON(){
	return "CForm._REQUEST = " . json_encode($_REQUEST) .";";
} // end CForm_REQUEST_toJSON()
CHook :: add("script", "CForm_REQUEST_toJSON"); 

//-------------------------------------------------------
// name: CMemory_updateFromREQUEST()
// desc: updates the memory from the request object
//------------------------------------------------------- 
function CMemory_updateFromREQUEST() {
	if(empty($_REQUEST))
		return;
	for($i=0; isset($_REQUEST["cmemory_md-id-$i"]); $i++){
		$cmemoryid = $_REQUEST["cmemory_md-id-$i"];
		$varname = $_REQUEST["cmemory_md-name-$i"];
		$varvalue = $_REQUEST[$varname];
		$cmemory = use_memory($cmemoryid);
		if($cmemory && !$cmemory->update($varname, $varvalue, "string"))
			$cmemory->create($varname, $varvalue, "string");
	} // end for
} // end CMemory_updateFromREQUEST()
CHook :: add( "init", "CMemory_updateFromREQUEST" );
?>