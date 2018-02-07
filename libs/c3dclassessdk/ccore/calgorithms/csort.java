//--------------------------------------------------
// file: csort
// desc: defines a class containing algorthms 
//--------------------------------------------------
package c3dclasses;

//--------------------------------------------------------
// name: csort
// desc: defines sorting algorithms 
//--------------------------------------------------------
public class csort {
	
	static public CArray insertion_sort(CArray A, CFunction compare) {
		for(int i = 1; i < A.length(); i++) {
			Object key = A._(i);
			// insert A[j] into the sorted sequence A[0...i-1].
			int j = i - 1; 
			while(j > -1 && compare.call(_.args(A._(j), key))._boolean()) {
				A.swap(j + 1, j);
				j--;
			} // end while
			A._(j + 1, key);
		} // end for()
		return A;
	} // end insertion_sort()
	
	
	/*
	////////////////////
	// bubble_sort
	
 	static public void bubble_sort(Object a[]) {
		 csort.bubble_sort(a, ccompare._int());
	} // end bubble_sort()
	
	static public void bubble_sort(Object a[], CFunction fncompare) {
        int n = a.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++) {
				int cmp = fncompare._(_.args(a[j], a[j+1]))._int(0);
                if (cmp == 1) {
                    // swap temp and a[i]
                    Object temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                } // end if
			} // end for
    } // end bubble_sort()
	
	static public CFunction bubble_sort_func() { 
		return new CFunction() { public CArray _(CArray args) { 
			csort.bubble_sort((Object [])args._(0), (CFunction)args._(1)); 
			return null; 
		}};
	} // end bubble_sort_func()
	static public void heap_sort(Object a[], CFunction fncompare) {}
	static public void quick_sort(Object a[], CFunction fncompare) {}
	static public void radix_sort(Object a[], CFunction fncompare) {}
	static public void count_sort(Object a[], CFunction fncompare) {}
	static public void bucket_sort(Object a[], CFunction fncompare) {}
	*/
} // end csort