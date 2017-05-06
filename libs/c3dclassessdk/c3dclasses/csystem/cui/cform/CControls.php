<?php
//---------------------------------------------------------
// file: ccontrols.php
// desc: defines controls used inside a form
//---------------------------------------------------------

// includes
include_js(relname(__FILE__) . "/ccontrols.js");

//-----------------------------------------------------------------
// name: CControls
// desc: defines controls used inside a form
//-----------------------------------------------------------------
class CControls extends CHash {		
	protected $m_cform;
	protected $m_cmemoryid = "";
	public function CControls(){ $this->m_cform=NULL; }	
	public function create($cform){ $this->m_cform = $cform; return true; }
	public function form($strname, $value=NULL, $params=NULL){ return $this->control("form", $strname, $value, $params); }
	public function endform(){ return $this->control("endform",NULL,NULL,NULL); }
	public function section($strname, $strlabel, $params=NULL){ return $this->control("section", $strname, $strlabel, $params);}
	public function label($strname, $value, $params=NULL){ return $this->control("label", $strname, $value, $params);}
	public function hidden($strname, $value, $params=NULL){ return $this->control("hidden", $strname, $value, $params);}
	public function text($strname, $value, $params=NULL){ return $this->control("text", $strname, $value, $params);}
	public function	textarea($strname, $value, $params=NULL){ return $this->control("textarea", $strname, $value, $params);}
	public function select($strname, $value, $options=NULL, $params=NULL){return $this->control_choices("select", $strname, $value, $options, $params);}
	public function checkbox($strname, $value, $params=NULL){ return $this->control("checkbox", $strname, $value, $params);}
	public function radio($strname, $value, $params=NULL){return $this->control("radio", $strname, $value, $params);}
	public function button($strname, $value, $params=NULL){ return $this->control("button", $strname, $value, $params);} 
	public function submit($strname, $value, $params=NULL){ return $this->control("submit", $strname, $value, $params);} 
	public function dropDownPages($strname, $value, $params=NULL){ return $this->control("dropdown-pages", $strname, $value,$params); }
	public function colorpicker($strname, $value, $params=NULL){ return $this->control("color", $strname, $value, $params); }
	public function image($strname, $value, $params=NULL){ return $this->control("image", $strname, $value, $params); }
	public function fileupload($strname, $value, $params=NULL){ return $this->control("fileupload", $strname, $value,$params); }
	public function crud($strname, $strtype="string", $params=NULL){ 
		$this->clear();
		$this->set("data-name", $strname); 
		$this->set("data-type", $strtype); 
		$this->set("data-action", "create");
		$this->set("class", "ccontrol-crud");
		$str = $this->button( "btn-" . $strname . "-create", "create");
		$this->set("data-action", "retrieve"); 
		$str .= $this->button( "btn-" . $strname . "-retrieve", "retrieve");
		$this->set("data-action", "update"); 
		$str .= $this->button( "btn-" . $strname . "-update", "update"); 
		$this->set("data-action", "delete"); 
		$str .= $this->button( "btn-" . $strname . "-delete", "delete"); 	 
		$this->clear();
		return $str;
	} // end crud()
	public function control_choices($strtype, $strname, $value, $options, $params){
		$params['choices']=$options; 
		return $this->control($strtype, $strname, $value, $params);
	} // end control_choices()
	public function control($strtype, $strname, $value, $params){
		if($this->m_cform && ($coptions = $this->m_cform->getCOptions()) && $strtype != "label") {
			$ovalue = ($coptions->optionExists($strname)) ? $coptions->option($strname) : "";
			 if(($strtype == "radio" || $strtype == "checkbox")) {
				if($ovalue != "") {
					if($ovalue == $value) // optionvalue == attribute-value
						$this->set("checked","");
					else $this->remove("checked");
				} // end if
				else {
				} // end else
				$value = $value;
			} // end if
			else $value = ($ovalue) ? $ovalue : $value;
		} // end if
		$_params["ccontrol-type"]=$strtype;
		$_params["ccontrol-name"]=$strname; 
		$_params["ccontrol-value"]=$value;
		$_params["ccontrol-params"]=$params;
		$_params["ccontrol-attributes"]=$this->valueOf();
		$_params["cmemory-id"]=$this->m_cform->getCMemoryID();
		$_params["cform-id"]=$this->m_cform->getID();
		$ret = $this->processParams($_params);
		$this->clear();
		return $ret;
	} // end control()
	public function processParams($params){ return CControls_processParams($params); }
} // end class CControls
?>