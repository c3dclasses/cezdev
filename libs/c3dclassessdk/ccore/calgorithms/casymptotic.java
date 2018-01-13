//---------------------------------------------------------
// file: casymptotic
// desc: defines the shortest path tree algorithms 
//---------------------------------------------------------
package c3dclasses;
import java.util.*;

//---------------------------------------------------------
// name: casymptotic
// desc: defines the shortest path tree algorithms 
//---------------------------------------------------------
public class casymptotic extends CFunction {
	protected String m_strfunc;
	
	public casymptotic(String strfunc) {
		this.m_strfunc = strfunc;
	} // end casymptotic()
	
	public CArray big_oh(casymptotic cfunction, int Ns, int Ne){
		// determine n0
		boolean n0=false;		
		for(int n=Ns; n<Ne; n++) {
			int f = this._(_.args(n))._int(0);
			int g = cfunction._(_.args(n))._int(0);	
			if(f <= g) {
				if(n0 == false) {
					n0 = true;
					_.println("n0 - f(" + this.m_strfunc + ") = O(g(" + cfunction.m_strfunc + ")) : " + "f(" + n + ") <= g(" + n + ") : " + f + " <= " + g);
				}
				else _.println("f(" + this.m_strfunc + ") = O(g(" + cfunction.m_strfunc + ")) : " + "f(" + n + ") <= g(" + n + ") : " + f + " <= " + g);
			} // end if
			else {
				_.println("f(" + this.m_strfunc + ") = O(g(" + cfunction.m_strfunc + ")) : " + "f(" + n + ") > g(" + n + ") : " + f + " > " + g);
			} // end else
		} // end for		
		return null;
	} // end big_oh()
	
	// Driver program to test above functions
    public static void main(String[] args) {
		// n^2
		final casymptotic n2 = new casymptotic("n^2") { 
			public CArray _(CArray args) { 
				int n = args._int(0);
				return _.args(n * n); 
			} // end _()		
		}; // n_squared()
		
		//20*n^2
		final casymptotic _20_n2 = new casymptotic("20*n^2") { 
			public CArray _(CArray args) { 
				int n = args._int(0);
				return _.args(20 * n * n); 
			} // end _()		
		}; // n_squared()

		// logn
		final casymptotic logn = new casymptotic("logn") { 
			public CArray _(CArray args) { 
				int n = args._int(0);
				return _.args((n <= 0) ? -1 : (int)(Math.log(n)/Math.log(2))); 
			} // end _()		
		}; // nlogn()
		

		//20*nlogn
		casymptotic nlogn = new casymptotic("20*nlogn") { 
			public CArray _(CArray args) { 
				int n = args._int(0);
				int n2 = logn._(_.args(n))._int(0);	
				return (n2 < 0) ? _.args(-1)  : _.args(20 * n * n2); 
			} // end _()		
		}; // nlogn()
		
		
		
		nlogn.big_oh(n2, 10, 200);
		
		_.print(Math.log(0));

  		//casymptotic a = new casymptotic(n2); // end new CFunction()
		//a.big_oh(_20_n2, 2000);
		//a.big_oh(nlogn, 2000);		
		
    } // end main()
} // end casymptotic
