//--------------------------------------------------
// file: CSortUnitTest
// desc:
//--------------------------------------------------
package c3dclasses;
import org.junit.Test;

//--------------------------------------------------------
// name: CSortUnitTest
// desc:
//--------------------------------------------------------
public class CSortUnitTest extends CUnitTest {
	@Test 
	public void test() {
		// compare functions
		CFunction increasing = new CFunction("inc") { 
			public CReturn call(CArray args) { 
				return CReturn._done(args._int(0) >= args._int(1));
			} // end call()
		}; // end increasing()
		CFunction decreasing = new CFunction("dec") { 
			public CReturn call(CArray args) { 
				return CReturn._done(args._int(0) <= args._int(1));
			} // end call()
		}; // end increasing()
		
		// insertion sort
		CArray carray = _.carray(9,8,7,6,5,4,3,2,10,6,8,9,0);
		CArray sorted_carray = _.carray(0,2,3,4,5,6,6,7,8,8,9,9,10);
		this.assertTrue(csort.insertion_sort(carray, _.f("inc")).toString().equals(sorted_carray.toString()));
		carray = _.carray(5,2,4,1,6,1,3);
		sorted_carray = _.carray(1,1,2,3,4,5,6);
		this.assertTrue(csort.insertion_sort(carray, _.f("inc")).toString().equals(sorted_carray.toString()));
		carray = _.carray(31,41,59,26,41,58);
		sorted_carray = _.carray(26,31,41,41,58,59);
		this.assertTrue(csort.insertion_sort(carray, _.f("inc")).toString().equals(sorted_carray.toString()));
		carray = _.carray(31,41,59,26,41,58);
		sorted_carray = _.carray(59,58,41,41,31,26);
		this.assertTrue(csort.insertion_sort(carray, decreasing).toString().equals(sorted_carray.toString()));
		
		// selection sort
		carray = _.carray(9,8,7,6,5,4,3,2,10,6,8,9,0);
		sorted_carray = _.carray(0,2,3,4,5,6,6,7,8,8,9,9,10);
		this.assertTrue(csort.selection_sort(carray, _.f("inc")).toString().equals(sorted_carray.toString()));
		carray = _.carray(5,2,4,1,6,1,3);
		sorted_carray = _.carray(1,1,2,3,4,5,6);
		this.assertTrue(csort.selection_sort(carray, _.f("inc")).toString().equals(sorted_carray.toString()));
		carray = _.carray(31,41,59,26,41,58);
		sorted_carray = _.carray(26,31,41,41,58,59);
		this.assertTrue(csort.selection_sort(carray, _.f("inc")).toString().equals(sorted_carray.toString()));
		carray = _.carray(31,41,59,26,41,58);
		sorted_carray = _.carray(59,58,41,41,31,26);
		this.assertTrue(csort.selection_sort(carray, decreasing).toString().equals(sorted_carray.toString()));
		
		// bubble_sort
		carray = _.carray(9,8,7,6,5,4,3,2,10,6,8,9,0);
		sorted_carray = _.carray(0,2,3,4,5,6,6,7,8,8,9,9,10);
		this.assertTrue(csort.bubble_sort(carray, _.f("inc")).toString().equals(sorted_carray.toString()));
		carray = _.carray(5,2,4,1,6,1,3);
		sorted_carray = _.carray(1,1,2,3,4,5,6);
		this.assertTrue(csort.bubble_sort(carray, _.f("inc")).toString().equals(sorted_carray.toString()));
		carray = _.carray(31,41,59,26,41,58);
		sorted_carray = _.carray(26,31,41,41,58,59);
		this.assertTrue(csort.bubble_sort(carray, _.f("inc")).toString().equals(sorted_carray.toString()));
		carray = _.carray(31,41,59,26,41,58);
		sorted_carray = _.carray(59,58,41,41,31,26);
		this.assertTrue(csort.bubble_sort(carray, decreasing).toString().equals(sorted_carray.toString()));
		
		// quick_sort
		carray = _.carray(9,8,7,6,5,4,3,2,10,6,8,9,0);
		sorted_carray = _.carray(0,2,3,4,5,6,6,7,8,8,9,9,10);
		this.assertTrue(csort.quick_sort(carray, _.f("inc")).toString().equals(sorted_carray.toString()));
		carray = _.carray(5,2,4,1,6,1,3);
		sorted_carray = _.carray(1,1,2,3,4,5,6);
		this.assertTrue(csort.quick_sort(carray, _.f("inc")).toString().equals(sorted_carray.toString()));
		carray = _.carray(31,41,59,26,41,58);
		sorted_carray = _.carray(26,31,41,41,58,59);
		this.assertTrue(csort.quick_sort(carray, _.f("inc")).toString().equals(sorted_carray.toString()));
		carray = _.carray(31,41,59,26,41,58);
		sorted_carray = _.carray(59,58,41,41,31,26);
		this.assertTrue(csort.quick_sort(carray, decreasing).toString().equals(sorted_carray.toString()));
	} // end test()
} // end CSortUnitTest