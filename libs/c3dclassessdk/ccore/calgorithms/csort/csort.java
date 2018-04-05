//--------------------------------------------------
// file: csort
// desc: 
//--------------------------------------------------
package c3dclasses;

//--------------------------------------------------------
// name: csort
// desc: 
//--------------------------------------------------------
public class csort {	
	static public CArray insertion_sort(CArray A, CFunction fncompare) {
		for(int i = 1; i < A.length(); i++) {
			Object key = A._(i);
			int j = i - 1; 
			while(j > -1 && fncompare.call(_.args(A._(j), key))._boolean()) {
				A.swap(j+1, j);
				j--;
			} // end while
			A._(j+1, key);
		} // end for()
		return A;
	} // end insertion_sort()
	
	static public CArray selection_sort(CArray A, CFunction fncompare) {
		for(int i=0; i < A.length(); i++) {
			int smallest_i = i; 
			for(int j=i+1; j < A.length(); j++) {
				if(fncompare.call(_.args(A._(smallest_i), A._(j)))._boolean())
					smallest_i = j; 
			} // end for
			A.swap(i, smallest_i);
		} // end for
		return A;
	} // end selection_sort()

	static public CArray bubble_sort(CArray A, CFunction fncompare) {
		boolean bswap = true;
		for(int i=0; i < A.length() && bswap; i++) {
			bswap = false;
			for(int j=0; j < A.length()-1; j++)
				if(fncompare.call(_.args(A._(j), A._(j+1)))._boolean()) {
					A.swap(j,j+1);
					bswap = true;
				} // end if
		} // end for
		return A;
	} // end bubble_sort()
		
	static public CArray quick_sort(CArray A, CFunction fncompare) {
		return csort.quick_sort(A, 0, A.length()-1, fncompare);
	} // end quick_sort()
	
	static public CArray quick_sort(CArray A, int low, int high, CFunction fncompare) {
		if (low > high)
			return A;
		int pi = A.partition(low, high, fncompare);
        quick_sort(A, low, pi - 1, fncompare); 
        quick_sort(A, pi + 1, high, fncompare);
		return A;
    } // end quick_sort()
	
/*	
	static final int RANGE = 255;
	static public void count_sort(CArray A, CFunction fncompare) {
		CArray indexes = _.carray_c(RANGE);
		for(int i=0; i<A.length(); i++) {
			if(indexes._(i) == null)
				indexes._(i, 1)
			else indexes._(i, indexes._int()+1);
		} // end for
			
		
	} // end count_sort()
*/	
	
	/*
	static public void heap_sort(Object a[], CFunction fncompare) {}
	static public void radix_sort(Object a[], CFunction fncompare) {}
	static public void count_sort(Object a[], CFunction fncompare) {}
	static public void bucket_sort(Object a[], CFunction fncompare) {}
	*/
} // end csort