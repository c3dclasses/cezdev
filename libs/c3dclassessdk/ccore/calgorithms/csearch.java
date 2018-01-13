//-------------------------------------------------------
// file: csearch
// desc: defines a class containing search algorithms 
//-------------------------------------------------------
package c3dclasses;
import java.util.*;

//--------------------------------------------------------
// name: csearch
// desc: defines a class containing search algorithms 
//--------------------------------------------------------
public class csearch {	
	// linear search
	public static int linear_search(Object [] a, Object objtofind) {
		return csearch.linear_search(a, objtofind, ccompare._int());
	} // end linear_search()
	public static int linear_search(Object [] a, Object objtofind, CFunction fncompare) {
		for(int i = 0; i < a.length; i++)
			if((fncompare._(_.args(a[i], objtofind))._int(0)) == 0)	// if the same return the index
				return i;
		return -1;
	} // end linear_search()
	
	// binary search
	public static int binary_search(Object [] a, Object objtofind) {
		return csearch.binary_search(a, objtofind, ccompare._int());
	} // end binary_search()
	public static int binary_search(Object [] a, Object objtofind, CFunction fncompare) {
		return csearch.binary_search_rec(a, 0, a.length-1, objtofind, fncompare);
	} // end binary_search()
	public static int binary_search_rec(Object [] a, int l, int r, Object objtofind, CFunction fncompare) {
		if(r < l)
			return -1;
		int mid = l + (r - l)/2;	
		int cmp = fncompare._(_.args(a[mid], objtofind))._int(0);
		if(cmp == 0) // equal
			return mid;
		else if(cmp == -1) // greater
			return csearch.binary_search_rec(a, mid+1, r, objtofind, fncompare);
		return csearch.binary_search_rec(a, l, mid-1,  objtofind, fncompare);
	} // end binary_search_rec()
	
	/*
	public static int kth_smallest(Object [] a) {
		return 0;
	} // end kth_smallest()
	
	// finds th median of 2 arrays
	public static int find_median(Object [] a, Object [] a2) {
		return 0;
	} // find_median()
	*/
	
} // end csort

//class compare_int extends CFunction { public CArray _(CArray args) { return _.args(new Boolean(args._int(0) > args._int(1))); }} // end compare_int