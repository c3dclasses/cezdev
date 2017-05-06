<?php
//----------------------------------------------------------------------------
// file: cform.php
// desc: defines a form object for creating controls and options
//----------------------------------------------------------------------------

// includes
include_once("cform.drv.php");
include_once("coptions.php");
include_once("ccontrols.php");
include_js(relname(__FILE__) . "/cform.js");

//-----------------------------------------------------------------
// name: CForm
// desc: defines the form used in the application
//-----------------------------------------------------------------
class CForm {	
	// members
	protected $m_strid;			// stores the name/id of the form
	protected $m_strcmemoryid;	// stores the cmemory id of the form
	protected $m_params;		// stores the parameters of the form
	protected $m_coptions;		// stores the options of the form 
	protected $m_ccontrols;		// stores the controls of the form 
	
	public function CForm($COptionsType="COptions", $CControlsType="CControls") {	
		$this->m_ccontrols = new $CControlsType();
		$this->m_coptions = new $COptionsType();
		$this->m_params = NULL;
		$this->m_strid = "";
		$this->m_strcmemoryid = "";
	} // end CForm()
	
	public function create($strname="", $params=NULL) {
		$this->m_ccontrols->create($this);
		$this->m_coptions->create($this);
		$this->m_params = $params;
		$this->m_strid = $strname;
		return true;
	} // end create()

	public function setID($strid) {
		$this->m_strid = $strid;
	} // end setID()
	
	public function getID() { 
		return $this->m_strid;
	} // end getID()
	
	public function setCMemoryID($strid) {
		$this->m_strcmemoryid = $strid;
		return;
	} // end setCMemoryID()
	
	public function getCMemoryID() {
		return $this->m_strcmemoryid;
	} // end getCMemoryID()
	
	public function getParams() { 
		return $this->m_params; 
	} // end getParams()
	
	public function setParam($strname, $value) { 
		$this->m_params[$strname] = $value;		
	} // end setParam()
	
	public function getParam($strname) { 
		$params = $this->getParams();
		return ($params && isset($params[$strname])) ? $params[$strname] : NULL ; 
	} // end getParam()
	
	public function getCOptions() { 
		return $this->m_coptions; 
	} // end getCOptions()
	
	public function getCControls() { 
		return $this->m_ccontrols; 
	} // end getCControls()
} // end class CForm
?>