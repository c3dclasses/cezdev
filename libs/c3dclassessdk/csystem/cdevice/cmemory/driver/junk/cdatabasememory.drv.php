<?php
//----------------------------------------------------------------
// file: cdatabasememory.drv.php
// desc: defines a database memory driver object 
//----------------------------------------------------------------

// includes
include_once("cmemory.drv.php");

//----------------------------------------------------------------
// class: CDatabaseMemoryDriver
// desc: defines a database memory driver object 
//----------------------------------------------------------------
class CDatabaseMemoryDriver extends CMemoryDriver {
	protected $m_ctable;
	
	public function CDatabaseMemoryDriver() { 
		parent::CMemoryDriver();
		$this->m_ctable = NULL;
	} // end CDatabaseMemory()
	
	public function open($strpath="", $params=NULL) {
		$params["pk_field"] = "m_strname";
		$params["pk_type"] = "CHAR(200)";
		if(!include_table($strpath, $strpath, $params) || 
			!($ctable = use_table($strpath)) ||
			!parent :: open($strpath, $params)) { 
				$this->close();
				return false;
			} // end if
		$this->m_ctable = $ctable;
		return true;
	} // end open()
	
	public function close(){ 
		if($this->m_ctable) 
			$this->m_ctable->destroy();
	} // end close()
	
	public function create($cvar) {
		if(!$cvar || !$this->m_ctable->create($cvar['m_strname']))
			return _return_done(NULL);
		return _return_done($this->encode($cvar, array("m_icreated"=>time())));
	} // end create()
	
	public function retrieve($strname) { 	
		return _return_done($this->decode($strname, array("m_iretrieved"=>time())));	
	} // end retrieve()
		
	public function update($cvar){ 
		return _return_done($this->encode($cvar, array("m_iupdated"=>time())));
	} // end update()
	
	public function delete($strname){ 
		if($this->m_ctable)
			$this->m_ctable->delete($strname);
		return _return_done(NULL);
	} // end delete()
	
	public function sync($local) {
		// update the main cache		
		if(!$this->m_ctable) 
			return NULL;
		$remote = $this->getRemoteCache();
		if($local) {
			foreach($local as $strname => $cvar) {
				if(isset($remote[$strname])) { // if its in the remote
					$rcvar = $remote[$strname];
					if(isset($rcvar["m_iupdated"]) && 
						isset($cvar["m_iupdated"]) &&
						$rcvar["m_iupdated"] > $cvar["m_iupdated"]) // check if remote var is more recent
						continue;
					$this->encode($cvar);
				} // end if
			} // end foreach
		} // end if
		
		$newlocal = NULL;
		foreach($remote as $strname => $cvar) {
			$cvar = $this->decode($strname, array("m_isynced"=>time()));
			$newlocal[$strname] = $cvar;
		} // end for
		return _return_done($newlocal);
	} // end sync()

	//////////////////////
	// helper methods
	
	public function getRemoteCache() {
		if(!$this->m_ctable)
			return NULL;
		$cache = $this->m_ctable->retrieveAll();
		if(!$cache)
			return NULL;
		foreach($cache as $strname => $cvar) {
			$cache[$strname] = $this->decode($strname);
		} // end foreach()
		return $cache;
	} // end getRemoteCache()
	
	public function exist($strname) {
		if(!$this->m_ctable || !($row = $this->m_ctable->retrieve($strname)))
			return NULL;
		return $row;
	} // end exist()
	
	public function encode($cvar, $arrmetadata=NULL) {
		$ctable = $this->m_ctable;
		if(!$ctable || !$cvar || !isset($cvar['m_strname']))
			return NULL;
		$strname = $cvar['m_strname'];	
		// serialize the value if it is an object
		$value = $cvar['m_value'];
		if(gettype($cvar['m_value']) == "object") {
			$cvar['m_value'] = serialize($cvar['m_value']);
			$cvar['m_strtype'] = "object";
		} // end if
		// set metadata
		if($arrmetadata) {
			foreach($arrmetadata as $strmetaname => $metadata) {
				$cvar[$strmetaname] = $metadata;
			} // end foreach()
		} // end if
		// json encode the memory location	
		if(!$ctable->update($strname, "m_cvar", json_encode($cvar), "TEXT")) {
			return NULL;
		} // end if
		// reset the value
		$cvar['m_value'] = $value; 
		return $cvar;
	} // end encode()
	
	public function decode($strname, $arrmetadata=NULL) {
		$ctable = $this->m_ctable;
		if(!$ctable)
			return NULL;	
		$row = $ctable->retrieve($strname);	
		$cvar = json_decode($row["m_cvar"], true);
		if(!$cvar)
			return NULL;
		// set metadata and encode it again 
		if($arrmetadata) {
			foreach($arrmetadata as $strmetaname => $metadata) {
				$cvar[$strmetaname]=$metadata;
			} // end foreach()
		} // end if
		// json encode the memory location	
		if(!$ctable->update($strname, "m_cvar", json_encode($cvar), "TEXT")) {
			return NULL;
		} // end if
		// unserialize the value if it's an object
		if(gettype($cvar['m_value']) == "object")
			$cvar['m_value'] = unserialize($cvar['m_value']);		
		return $cvar;
	} // end decode()
} // end CDatabaseMemoryDriver
?>