//---------------------------------------------------------------------------------
// file: CHeapProgram
// desc: defines a heap object
//---------------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;

//-------------------------------------------
// class CHeap
// desc: defines a heap object
//-------------------------------------------
public class CHeapUnitTest extends CUnitTest {
	@Test
	public void test() {
		CHeap cheap1 = new CHeap();
		this.assertTrue(cheap1 != null);	
		CHeap cheap2 = new CHeap(8);
		this.assertTrue(cheap2 != null);	
		CHeap cheap3 = new CHeap(__.carray(2,3,0,5,4,6));
		this.assertTrue(cheap3 != null);	
		CHeap cheap4 = new CHeap(__.carray(2,3,0,5,4,6), CHeap.compareMaxIntegers());
		this.assertTrue(cheap4 != null);	
		this.assertTrue(cheap1.toString().equals("[]"));
		this.assertTrue(cheap2.toString().equals("[]"));
		this.assertTrue(cheap3.copy().sort().toString().equals("[0, 2, 3, 4, 5, 6]"));	
		this.assertTrue(cheap4.copy().sort().toString().equals("[6, 5, 4, 3, 2, 0]"));
		cheap4.push(10);
		cheap4.push(-1);
		this.assertTrue(cheap4.copy().sort().toString().equals("[10, 6, 5, 4, 3, 2, 0, -1]"));	
	} // end test()
} // end CHeapUnitTest
