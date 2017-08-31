//----------------------------------------------------------------
// file: CBit
// desc: defines bit constants 
//----------------------------------------------------------------
package c3dclasses.ccore;

//----------------------------------------------------------------
// file: CBit
// desc: defines bit constants 
//----------------------------------------------------------------
public class CBit {	
// ClassMethods
	static public int [] BIT = null;
	static public final int BITNONE = 0x00000000;
	static public final int BITALL = 0xFFFFFFFF; 
	static public final int BITSIZE = 32;
	static public boolean init() {
		CBit.BIT = new int[CBit.BITSIZE];
		CBit.BIT[0] = CBit.BITNONE;
		for(int i=0; i<CBit.BITSIZE; i++)
			CBit.BIT[i+1] = (1<<i);
		return true;
	} // end init()
// end ClassMethods
} // end CBit

//_runtime ({
//	CBit.init();
//});