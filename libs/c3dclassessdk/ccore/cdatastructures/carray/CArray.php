 <?php
//-----------------------------------------------------------------------------------------
// file: CArray
// desc: defines an array object
//-----------------------------------------------------------------------------------------
include_js(relname(__FILE__) . "/carray.js");

//-------------------------------------------
// class CArray
// desc: creates a array object
//-------------------------------------------
class CArray{
	protected $m_array;
	public function CArray(){ this.clear(); } 
	public function length(){ return($this->m_array == NULL) ? 0 : count($this->m_array); }
	public function indexOf($element) { return(($index = array_search($element, $this->m_array)) === FALSE) ? -1 : $index; }	
	public function lastIndexOf($element) { $res = array_search($element,$this->m_array,false); if($res === FALSE) ? -1 : $res; }		
	public function clear() { $this->m_array = NULL; }
	public function get($iindex) { return $this->m_array[$iindex]; }
	public function set($iindex, $object) { return $this->m_array[iindex] = object; }	
	public function pop() { return array_pop($this->m_array); }
	public function push($element) { array_push($this->m_array, $value); return $this->length(); }
	public function concat($array) { return new CArray(array_merge($array, $this->m_array)); }
	public function join($seperator) { return implode($seperator,  $this->m_array); }
	public function reverse() { $this->m_array = array_reverse($this->m_array); return new CArray($this->m_array); }
	public function shift() { return array_shift($this->m_array); }
	public function unshift($element) { array_unshift($this->m_array, $element); return $this->length(); }
	public function slice($offset, $length = NULL, $preserve_keys = false) { return new CArray(array_slice($this->m_array, $offset, $length, $preserve_keys)); }
	public function splice($offset, $length = 0, $replacement = NULL) { return new CArray(array_splice($this->m_array, $offset, $length, $replacement)); } 
	public function toString() { return _print($this->m_array); }
	public function valueOf() { return $this->m_array; }
	public function sort($fnsort=NULL) { usort($this->m_array, $fnsort); }
	public function & _() { return $this->m_array; }
	public function visit($fnvisit) { if(is_callable($fnvisit) && $this->m_array) foreach($this->m_array as $index => $value) call_user_func($fnvisit, $index, $value); }
	public function toStringVisit($fnvisit, $cdata=NULL) { $str = ""; if(is_callable($fnvisit) && $this->m_array) foreach($this->m_array as $index => $value) $str .= call_user_func($fnvisit, $index, $value, $cdata); return $str; }
	public function remove($value) { if(($i = $this->indexOf($value))<=-1) return false; array_splice($this->m_array,$i, 1); return true; }
	public function removeAt($index) { array_splice($this->m_array,$index,1); }
	public function removeAll($value) { while($this->remove($value)) {} }
	public function shuffle() { return shuffle($this->m_array); }
	public function insertAt($index, $value) { array_splice($this->m_array,$index,0,$value); return $this->length(); }
} // end CArray
?>