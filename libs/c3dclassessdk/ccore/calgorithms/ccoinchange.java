//--------------------------------------------------
// file: ccoinchange
// desc:  
//--------------------------------------------------
package c3dclasses;
import java.util.*;

//--------------------------------------------------------
// file: ccoinchange
// desc:  
//--------------------------------------------------------
public class ccoinchange {
	static public void greedy(CArray D, int n) {
		for(int i=0; i<D.length(); i++) {
			int d = D._int(i);
			int count = n / d;
			n = n % d;
			_.println(d+":"+count);
		} // end for()
	} // end greedy()
	
	// test
	public static void main(String[] args) {
		CArray D = _.carray(25,10,5,1);
		ccoinchange.greedy(D,67);	
	} // end main()
} // end ccoinchange

