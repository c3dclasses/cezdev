<?php
//----------------------------------------------------------------
// file: cmemory.drv.php
// desc: defines the memory driver object
//----------------------------------------------------------------

session_start();
// includes
include_js(relname(__FILE__) . "/cmemory.drv.js");

//----------------------------------------------------------------
// class: CMemoryDriver
// desc: defines the memory driver object
//----------------------------------------------------------------
class CMemoryDriver extends CResource {
	
	////////////////////////////
	// driver instance methods
	public function CMemoryDriver() { parent :: CResource(); } 
	public function open($strpath, $params) { return parent :: open($strpath, $params); }
	public function close() { return parent :: close(); } 
	public function create($cvar) { return NULL; }
	public function retrieve($strname) { return NULL; } 
	public function update($cvar) { return NULL; } 
	public function delete($strname) { return NULL; }
	public function sync($cache) { return NULL; } 
	
	/////////////////////////
	// class methods
	
	/////////////////////////
	// opening and closing
	public static function _open($cmemory) {
		if(!$cmemory)
			return NULL;
		$cmemorydriver = $cmemory->getCMemoryDriver();
		if($cmemorydriver)
			return $cmemorydriver;
		// create the cmemorydriver for this memory object
		$params = $cmemory->getParams(); 
		$strtype = $params->get("cmemorydriver_type"); 
		$strpath = $params->get("cmemorydriver_path"); 
		if($strtype == "" || ($cmemorydriver = new $strtype()) == NULL || 
			$cmemorydriver->open($strpath, $params->_()) == false) 
			return NULL;
		$cmemory->setCMemoryDriver($cmemorydriver);
		return $cmemorydriver;
	} // end _open()

	public static function _close($cmemory) {
		$cmemorydriver = CMemoryDriver :: _open($cmemory);
		return $cmemorydriver->close();
	} // end _close()

	/////////
	// CRUD	
	public static function _create($cmemory, $cvar) {
		if(!$cmemory || !$cvar)
			return _return_done(false);
		$cmemorydriver = CMemoryDriver :: _open($cmemory);
		if(!$cmemorydriver)
			return _return_done(false);
		$_driver_return = $cmemorydriver->create($cvar);
		if(!$_driver_return)			
			return _return_done(false);				
		$strname = $cvar["m_strname"]; 			
		$_return = _return_busy();
		if(!$_return)
			return _return_done(false);
		$cmemory->m_cache[$strname] = $cvar;
		if($_driver_return->isdone()) {
			$params = $_driver_return->results();
			if(!$params)
				$_return->done(false);
			else {
				$cmemory->m_cache[$strname] = $params;	
				$_return->done(true);
			} // end else
		} else if($_driver_return->iserror()) {
			$_return->done(false);
		} // end else if
		return $_return;	
	} // end _create()

	public static function _retrieve($cmemory, $strname) {
		if(!$cmemory || !$strname)
			return _return_done(false);
		$cmemorydriver = CMemoryDriver :: _open($cmemory);
		if(!$cmemorydriver)
			return _return_done(false);
		$_return = _return_busy();
		if(!$_return)
			return _return_done(false);
		$_driver_return = $cmemorydriver->retrieve($strname);
		if(!$_driver_return)
			return _return_done(false);
		if($_driver_return->isdone()) { 
			$params = $_driver_return->results();
			if(!$params)
				$_return->done(false);
			else {
				$cmemory->m_cache[$strname] = $params;
				$_return->done(true);
			} // end else
		} else if($_driver_return->iserror()) {
			$_return->done(false);
		} // end else if
		return $_return;
	} // end _retrieve()

	public static function _update($cmemory, $cvar) {
		if(!$cmemory || !$cvar)
			return _return_done(false) ;
		$cmemorydriver = CMemoryDriver :: _open($cmemory);
		if(!$cmemorydriver)
			return _return_done(false);	
		$_driver_return = $cmemorydriver->update($cvar);
		if(!$_driver_return) 
			return _return_done(false);
		$strname = $cvar["m_strname"]; 
		$cmemory->m_cache[$strname] = $cvar;
		$_return = _return_busy();
		if(!$_return)
			return _return_done(false);
		if($_driver_return->isdone()) {
			$params = $_driver_return->results();
			if(!$params)
				$_return->done(false);
			else {
				$cmemory->m_cache[$strname] = $params;
				$_return->done(true);
			} // end else
		} else if($_driver_return->iserror()) {
			$_return->done(false);
		} // end if
		return $_return;
	} // end _update()

	public static function _delete($cmemory, $strname) {
		if(!$cmemory || !$strname)
			return _return_done(false);
		$cmemorydriver = CMemoryDriver :: _open($cmemory);
		if(!$cmemorydriver)
			return _return_done(false);
		$_driver_return = $cmemorydriver->delete($strname);
		if(!$_driver_return)
			return _return_done(false);
		if($cmemory->m_cache && isset($cmemory->m_cache[$strname]))
			unset($cmemory->m_cache[$strname]);
		$_return = _return_busy();
		if($_driver_return->isdone()) {
			$_return->done(true);
		} else if($_driver_return->iserror()) {
			$_return->done(false);
		} // end if
		return $_return;
	} // end _delete()

	/////////////////////////
	// syncing
	public static function _sync($cmemory) {
		if(!$cmemory)
			return _return_done(false);
		$cmemorydriver = CMemoryDriver :: _open($cmemory);
		if(!$cmemorydriver)
			return _return_done(false);
		$_driver_return = $cmemorydriver->sync($cmemory->m_cache);
		if(!$_driver_return)
			return _return_done(false);
		$_return = _return_busy();
		if(!$_return)
			return _return_done(false);
		if($_driver_return->isdone()) {
			$params = $_driver_return->results();
			if(!$params)
				$_return->done(false);
			else {
				$cmemory->m_cache = $params;	
				$_return->done(true);
			} // end else
		} else if($_driver_return->iserror()) {	
			$_return->done(false);
			return $_return;
		} // endif()
		return $_return;
	} // end _sync()
	// end ClassMethods
} // end CMemoryDriver()

/////////////////////////
// includes and using
function include_memory_driver($strid, $strpath, $strtype, $params=NULL){
	$params["cresource_type"] = $strtype;
	return include_resource($strid, $strpath, $params);
} // end include_memory_driver()

function use_memory_driver($strid){
	return use_resource($strid);
} // end use_memory_driver()
?>