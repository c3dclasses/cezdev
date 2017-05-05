//----------------------------------------------------------------
// file: CBit
// desc: defines bit constants 
//----------------------------------------------------------------

//----------------------------------------------------------------
// file: CBit
// desc: defines bit constants 
//----------------------------------------------------------------
var CBit = new Class ({	
ClassMethods: {
	BIT 	: null,
	BITNONE : 0x00000000, 
	BITALL 	: 0xFFFFFFFF, 
	BITSIZE : 32,
	init : function() {
		CBit.BIT = new Array(CBit.BITSIZE);
		CBit.BIT[0] = CBit.BITNONE;
		for(var i=0; i<CBit.BITSIZE; i++)
			CBit.BIT[i+1] = (1<<i);
		return true;
	} // end init()
} // end ClassMethods
}); // end CBit

// initialize the bits
CBit.init();