<?php
//----------------------------------------------------------------
// file: cremotememory.drv.php
// desc: defines the remote memory driver object
//----------------------------------------------------------------

// includes

/*if($_REQUEST["cremotememorydriver"]) {
	include_once("../../../../ccore/ccore.php");
	include_once("../../../csystem.php");
} // end if
*/
include_once(dirname(dirname(dirname(dirname(dirname(__FILE__)))))."/ccore/cincludefiles/cincludeif.php");
include_if(isset($_REQUEST["cremotememorydriver"])?$_REQUEST["cremotememorydriver"]:"", array("../../../../ccore/ccore.php", "../../../csystem.php") );
include_once("cmemory.drv.php");
include_js(relname(__FILE__) . "/cremotememory.drv.js");
include_path("CRemoteMemoryDriver_URI", uri_name(__FILE__));

//----------------------------------------------------------------
// class: CRemoteMemoryDriver
// desc: defines the remote memory driver object
//----------------------------------------------------------------
class CRemoteMemoryDriver extends CMemoryDriver{
	public function create($cvar) {
		return $this->triggerRemoteOperation(array(
			"memtype"=>$this->type(),
			"mempath"=>$this->path(),
			"memid"=>$this->id(), 
			"memcommand"=>"create", 
			"memvar"=>$cvar
		)); // end triggerRemoteOperation()
	} // end create() 
	
	public function retrieve($strname) { 
		return $this->triggerRemoteOperation(array(
			"memtype"=>$this->type(),
			"mempath"=>$this->path(),
			"memid"=>$this->id(), 
			"memcommand"=>"retrieve", 
			"memvarname"=>$strname
		)); // end triggerRemoteOperation()
	} // end retrieve()
	
	public function update($cvar) { 
		return $this->triggerRemoteOperation(array(
			"memtype"=>$this->type(),
			"mempath"=>$this->path(),
			"memid"=>$this->id(), 
			"memcommand"=>"update", 
			"memvar"=>$cvar
		)); // end triggerRemoteOperation()
	} // end update() 
	
	public function delete($strname) { 
		return $this->triggerRemoteOperation(array(
			"memtype"=>$this->type(),
			"mempath"=>$this->path(),
			"memid"=>$this->id(), 
			"memcommand"=>"delete", 
			"memvarname"=>$strname 
		)); // end triggerRemoteOperation()
	} // end delete()
	
	public function sync($cache) { 
		return $this->triggerRemoteOperation(array(
			"memtype"=>$this->type(),
			"mempath"=>$this->path(),
			"memid"=>$this->id(), 
			"memcommand"=>"sync", 
			"memcache"=>($cache)?json_encode($cache):NULL
		)); // end triggerRemoteOperation()
	} // end sync()
	
	public function type() {
		return $this->param("cremotememorydriver_type");
	} // end type()
	
	public function uri() {
		return $this->param("cremotememorydriver_uri");
	} // end uri()
	
	public function id() {
		return $this->param("cremotememorydriver_id");		
	} // end id()
	
	protected function triggerRemoteOperation($inparams) {
		$struri = $this->uri();
		$cds = new CDataStream();	// create
		if(!$struri || !$cds || $cds->open($struri, "post", "cremotememorydriver") == false) // open
        		return _return_done(NULL);	
    	$cds->setDataParam("cremotememorydriver",true);
    	$cds->setDataParam("cremotememorydriver_uri",$struri);	// server of the function
		$cds->setDataParam("cremotememorydriver_type",$this->type());	// file of the function 
		$cds->setDataParam("cremotememorydriver_id",$this->id()); 	// name of the function
		if($inparams && gettype($inparams) == "array")
        		foreach($inparams as $name=>$value)
            			$cds->setDataParam($name,$value);
		else $cds->setDataParam("cremotememorydriver_inparam",$inparams);
    		$cds->send();
		return _return_done($cds->getData());
	} // end triggerRemoteOperation()
	
	static public function getLocalURI() {
		return CPath :: _("CRemoteMemoryDriver_URI");
	} // end getLocalURI()

	static public function handleRemoteOperation($params) {
		// check the parameters
		if(!$params ||
			!isset($params["cremotememorydriver"]) ||
	   		!isset($params["memtype"]) ||
	   		!isset($params["mempath"]) ||
	   		!isset($params["memcommand"])) {
			return;
		}
		// get the parameters
		$strid = isset($params["memid"]) ? $params["memid"] : "tmpid";
		$strtype = urldecode($params["memtype"]);
		$strpath = urldecode($params["mempath"]);
		$strcommand = urldecode($params["memcommand"]);
		$driver_params = $params["cremotememorydriver_params"];
		// include driver files	
		includephpfilesfrompath(dirname(__FILE__), ".drv.php");
		// get the memory driver
		if(!include_memory_driver($strid, $strpath, $strtype, $driver_params) || !$cmemorydriver = use_memory_driver($strid)) {
			echo json_encode(NULL);
			return;
		} // end if
		if($strcommand == "sync") {
			$cache = isset($params["memcache"]) ? $params["memcache"] : NULL;	
			$_return = $cmemorydriver->sync(json_decode($cache, true));
		} // end if
		else if($strcommand == "create") {
			$cvar = isset($params["memvar"]) ? $params["memvar"] : NULL;
			//printbr("hello"); print_r($params); printbr("hello");
			$_return = $cmemorydriver->create($cvar);
		} // end if
		else if($strcommand == "retrieve") {
			$strname = $params["memvarname"];
			$_return = $cmemorydriver->retrieve($strname);
		} // end if
		else if($strcommand == "update") {
			$cvar = isset($params["memvar"]) ? $params["memvar"] : NULL;
			$_return = $cmemorydriver->update($cvar);
		} // end if
		else if($strcommand == "delete") {
			$strname = $params["memvarname"];
			$_return = $cmemorydriver->delete($strname);
		} // end if
		
		echo json_encode($_return->data());
	} // end handleRemoteOperation()
} // end CRemoteMemoryDriver

// handle the request
CRemoteMemoryDriver :: handleRemoteOperation($_REQUEST);
?>