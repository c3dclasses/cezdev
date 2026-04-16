<?php
//--------------------------------------------------
// file: cpoint.js
// desc: defines a point object 
//--------------------------------------------------

// includes
include_js(relname(__FILE__) . '/cpoint.js'); 

//--------------------------------------------------------
// name: CPoint
// desc: defines and object that stores position info
//--------------------------------------------------------
class CPoint{
	public function CPoint($x=0, $y=0, $z=0) { 
		$this->set($x, $y, $z); 
	} // end CPoint()
	public function set($x, $y, $z) { 
		$this->x = $x; 
		$this->y = $y; 
		$this->z = $z; 
	} // end set()
	public function translate($dx, $dy, $dz) { 
		$this->x += $dx; 
		$this->y += $dy; 
		$this->z += $dz; 
	} // end translate()
	public function rotate() {
	} // end rotate()
	public 	$x;
	public 	$y;
	public  $z;	
} // end CPoint
?>