<?php 
//------------------------------------------------------------------------------
// file: carraymemory.drv.php
// desc: defines a array memory driver object 
// usage: include_memory("strmemid", "$GLOBALVARNAME", "CArrayMemoryDriver");
//	      cmemory = use_memory("strmemid"); 
//------------------------------------------------------------------------------

// includes
include_once("cmemory.drv.php");

//----------------------------------------------------------------
// file: CArrayMemoryDriver
// desc: defines a array memory object 
//----------------------------------------------------------------
class CArrayMemoryDriver extends CMemoryDriver {	
	protected $m_array;
	
	public function CArrayMemoryDriver(){ 
		parent :: CMemoryDriver(); 
		$this->m_array = NULL; 
	} // end CArrayMemoryDriver()
	
	public function open($strpath, $params=NULL){
		if(parent :: open($strpath, $params) == false)
			return false;
		try {
			eval('$array = &' . $strpath . ';');
			$this->m_array = &$array;
		} // end try
		catch(Exception $ex) {
			CLog :: error(ex.toString());
			return false;
		} // end catch()
		return true;	
	} // end open()
	
	public function close(){
		//$this->m_array = NULL; 
	} // end close()
	
	public function create($cvar) {
		if(!$cvar || !$this->exist($cvar['m_strname'])) // no var
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
		if($this->exist($strname))
			return _return_done(NULL);
		$this->m_array[$strname] = NULL;
		unset($this->m_array[$strname]);
		return _return_done(NULL);
	} // end delete()
	
	public function sync($cache) {
		// update the main cache		
		if($cache) {
			foreach($cache as $strname => $cvar) {
				if($this->m_array[$strname]) {
					$this->encode($cvar);
				} // end if
			} // end foreach
		} // end if
		$outcache = NULL;
		foreach($this->m_array as $strname => $cvar) {
			$cvar = $this->decode($strname, array("m_isynced"=>time()));
			$outcache[$strname] = $cvar;
		} // end for
		return _return_done($outcache);
	} // end sync()
	
	//////////////////////
	// helper methods
	
	public function exist($strname) {
		return ($this->m_array == NULL ||isset($this->m_array[$strname]) == FALSE);
	} // end exist()
	
	public function encode($cvar, $arrmetadata=NULL) {
		if(!$cvar && !isset($cvar['m_strname']))
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
		$this->m_array[$strname] = json_encode($cvar);
		// reset the value
		$cvar['m_value'] = $value; 
		return $cvar;
	} // end encode()
	
	public function decode($strname, $arrmetadata=NULL) {
		if(!$this->m_array)
			return NULL;	
		$cvar = isset($this->m_array[$strname]) ? json_decode($this->m_array[$strname], true) : NULL;
		if(!$cvar)
			return NULL;
		// set metadata and encode it again 
		if($arrmetadata) {
			foreach($arrmetadata as $strmetaname => $metadata) {
				$cvar[$strmetaname]=$metadata;
			} // end foreach()
		} // end if
		// json encode the memory location	
		$this->m_array[$strname] = json_encode($cvar);
		// unserialize the value if it's an object
		if(gettype($cvar['m_value']) == "object")
			$cvar['m_value'] = unserialize($cvar['m_value']);		
		return $cvar;
	} // end decode()
} // end CArrayMemoryDriver
?>