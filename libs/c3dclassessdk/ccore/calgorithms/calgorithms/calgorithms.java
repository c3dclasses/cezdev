//-------------------------------------------------------
// file: calgorithms
// desc: 
//-------------------------------------------------------
package c3dclasses;

//--------------------------------------------------------
// name: calgorithms
// desc: 
//--------------------------------------------------------
public class calgorithms {	
	public static CArray add_nbits(CArray A, CArray B) {
		int carry = 0;
		CArray C = _.carray();
		int n = A.length();
		int sum = 0;
		for(int i=n-1; i>-1; i--) {
			sum = (A._int(i) + B._int(i) + carry) % 2;
			carry = (A._int(i) + B._int(i) + carry) / 2;
			C.push(sum);
		} // end for
		C.push(sum);
		C.reverse();
		return C;
	} // end add_nbit_numbers()
	
	public static boolean has_repeated_elements_bf(CArray A, CFunction compare) {
		for(int i=0; i<A.length(); i++) {
			for(int j=0; j<A.length(); j++) {
				if(i != j && compare.call(_.args(A._(i), A._(j)))._boolean())
					return true;
			} // end for
		} // end for		
		return false;
	} // end has_repeated_elements()
} // end calgorithms
