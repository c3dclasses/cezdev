<?php
//------------------------------------------------------------------------------------
// file: cresource.php
// desc: opens input and/or output files that can be used globally throughout the SDK
//------------------------------------------------------------------------------------

include_js(relname(__FILE__) . "/cresource.js");

//-------------------------------------------------------
// name: CResource
// desc: input and output file resource
//-------------------------------------------------------
class CResource {
	// members
	protected 		 $m_hashparams;					// stores the params of the resource			
	static protected $m_hashpathtoresource = NULL;	// stores the "filepath" -> resource
	static protected $m_hashidtoresource = NULL;	// stroes the "identifier" -> resource
	
	// methods
	public function CResource() {
		$this->m_hashparams = NULL;
	} // end CResource()
	
	public function open($strpath, $params) {
		$this->m_hashparams = new CHash();
		if(!$this->m_hashparams)
			return false;
		$this->m_hashparams->create($params);
		$this->m_hashparams->set("cresource_path", $strpath);
		return true;
	} // end create()
	
	public function restore() { 
		return $this->open($this->m_hashparams->get("cresource_path"), $this->m_hashparams->valueOf()); 
	} // end restore()
	
	public function toString() { 
		return ""; 
	} // end toString() 
	
	public function getParams() {
		return $this->m_hashparams;
	} // end getParams()
	
	public function param($strname) {
		return ($this->m_hashparams) ? $this->m_hashparams->get($strname) : ""; 
	} // end params	
	
	public function path() {
		return $this->param("cresource_path"); 
	} // end path()
	
	public function type() {
		return $this->param("cresource_type"); 
	} // end path(
	
	public function id() {
		return $this->param("cresource_id"); 
	} // end id()
	
	public function updateParams($params) {
		if($params && $this->m_hashparams)
			foreach($params as $key => $value)
				$this->m_hashparams->set($key, $value);
	} // end updateParams()
	
	/////////////////////////
	// class methods
	public static function _getByPath($strpath) {
		return (CResource :: $m_hashpathtoresource) ? CResource :: $m_hashpathtoresource->get($strpath) : NULL;
	} // end _getByPath()
	
	protected static function _addByPath($strpath, $cresource) {
		if(CResource :: $m_hashpathtoresource == NULL)
			CResource :: $m_hashpathtoresource = new CHash();
		if($cresource && CResource :: $m_hashpathtoresource->containsKey($strpath) == false) {
			CResource :: $m_hashpathtoresource->set($strpath, $cresource);
			return true;
		} // end if
		return false;			
	} // end _setByID
	
	public static function _getByID($strid) {
		return (CResource :: $m_hashidtoresource) ? CResource :: $m_hashidtoresource->get($strid) : NULL;
	} // end _getByID()
	
	protected static function _addByID($strid, $cresource) {
		if(CResource :: $m_hashidtoresource == NULL)
			CResource :: $m_hashidtoresource = new CHash();
		if($cresource && CResource :: $m_hashidtoresource->containsKey($strid) == false) {
			CResource :: $m_hashidtoresource->set($strid, $cresource);
			//$_SESSION[$strid]=$cresource;	// this is the resource the client is accessing via FE
			return true;
		} // end if
		return false;
	} // end _setByID
	
	public static function _register($strid, $strpath="", $params=NULL) {
		if($strid && ($cresource = CResource :: _getByID($strid))) {
			$cresource->updateParams($params);
			return $cresource;
		}
		if($strpath && ($cresource = CResource :: _getByPath($strpath))) {
			CResource :: _addByID($strid, $cresource);
			$cresource->updateParams($params);
			return $cresource;
		} // end if
		$strtype = $params["cresource_type"];
		if($strtype == "" || ($cresource = new $strtype()) == NULL || $cresource->open($strpath, $params) == false) {
			return NULL; 	
		} // end if
		CResource :: _addByID($strid, $cresource);
		CResource :: _addByPath($strpath, $cresource);		
		return $cresource;
	} // end _register()
	
	public static function toStringVisit($strcallback) {
		$rs = CResource :: $m_hashidtoresource;
		return ($rs) ? $rs->toStringVisit($strcallback) : NULL;
	} // end toStringVisit()
} // end CResource

function include_resource($strid, $strpath, $params) {
	return CResource :: _register($strid, $strpath, $params);
} // end include_resource()

function use_resource($strid) {
	$cresource = CResource :: _getByID($strid);
	if(!$cresource)
		$cresource = CResource :: _getByPath($strid);
	return $cresource;
} // end use_resource()
?>