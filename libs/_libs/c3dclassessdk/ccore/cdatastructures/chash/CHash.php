<?php
//---------------------------------------------------------------------------------
// file: chash.php
// desc: defines a hash object     
//---------------------------------------------------------------------------------

// includes
include_js(relname(__FILE__) . "/chash.js");

//-------------------------------------------
// class CHash
// desc: defines a hash object
//-------------------------------------------
class CHash {
	public function CHash() { $this->clear(); } 
	public function create($hash) { $this->m_hash = $hash; }
	public function merge($hash) { $this->m_hash = array_merge($this->m_hash,$hash); } 
	public function clear() { unset($this->m_hash); $this->m_hash=NULL; }
	public function get($key) { return isset($this->m_hash[$key]) ? $this->m_hash[$key] : NULL; }
	public function set($key, $value) { $this->m_hash[$key] = $value; }
	public function setRef($key, &$value) { $this->m_hash[$key] = &$value; }
	public function remove($key) { unset($this->m_hash[$key]); }
	public function size() { return count($this->m_hash); }
	public function isEmpty() { return $this->size() == 0; }
	public function containsValue($value) { return $this->values()->indexOf($value) > -1; }
	public function containsKey($key) { return isset($this->m_hash[$key]); }
	public function keys() { return new CArray(array_keys(($this->m_hash)?$this->m_hash:array())); }
	public function values() { return new CArray(array_values(($this->m_hash)?$this->m_hash:array())); }
	public function valueOf() { return $this->m_hash; }
	public function & _() { return $this->m_hash; }
	public function visit($fnvisit) { if(is_callable($fnvisit) && $this->m_hash) foreach($this->m_hash as $key => $value) call_user_func($fnvisit, $key, $value); } 
	public function toStringVisit($fnvisit, $cdata=NULL) { $str = "";if(is_callable($fnvisit) && $this->m_hash) foreach($this->m_hash as $key => $value) $str .= call_user_func($fnvisit, $key, $value, $cdata);  return $str; }
	public function hash($key) { $nargs = func_num_args();	if($nargs == 1) return $this->get($key); else if($nargs >= 2) $this->set($key, func_get_arg(1)); return; }
	public function urlencode() { if($this->m_hash) foreach($this->m_hash as $key => $value) if(gettype($value)=="string") $this->m_hash[$key] = urlencode($value); }
	public function toJSON() { return CParse::toJSONString($this->m_hash); }
	protected $m_hash = NULL;
} // end CHash

// functions 
function chash($inhash=NULL) {
	$hash = new CHash();
	$hash->create($inhash); 
	return $hash;
} // end carray()
?>