//-------------------------------------------------------
// file: csearch
// desc: 
//-------------------------------------------------------
package c3dclasses;

//--------------------------------------------------------
// name: csearch
// desc: 
//--------------------------------------------------------
public class csearch {	
	static public int linear_search(CArray A, Object key, CFunction compare) {
		for(int i=0; i<A.length(); i++)
			if(compare.call(_.args(A._(i),key))._boolean())
				return i;
		return -1;
	} // end linear_seach()
} // end csearch
