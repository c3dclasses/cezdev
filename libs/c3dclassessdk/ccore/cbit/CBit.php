<?php
//----------------------------------------------------------------
// file: CBit
// desc: defines bit constants 
//----------------------------------------------------------------
include_js(relname(__FILE__) . '/cbit.js'); 

//----------------------------------------------------------------
// file: CBit
// desc: defines bit constants 
//----------------------------------------------------------------
class CBit {
// ClassMethods 	
	static public $BIT = NULL;
	static public $BITNONE 	= 0x00000000;
	static public $BITALL 	= 0xFFFFFFFF; 
	static public $BITSIZE 	= 32; 
	static public function init() {
		CBit::$BIT = array();
		CBit::$BIT[0] = CBit::$BITNONE;
		for ($i=0; $i<CBit::$BITSIZE; $i++)
			CBit::$BIT[$i+1] = (1<<$i);
		return true;
	} // end init()
// end ClassMethods
} // end CBit

// initialize the bits
CBit::init();
?>