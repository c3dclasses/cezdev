//---------------------------------------------------------------------------------
// file: CCastUnitTest
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;

//---------------------------------------------------------------------------------
// name: CCastUnitTest
// desc: 
//---------------------------------------------------------------------------------
public class CCastUnitTest extends CUnitTest{
	@Test
	public void test() {
		CArray arr = __.carray(10.0,"mannnn", 100, "this is a string", .783789379, .000007979);
		this.assertTrue(arr != null);
		this.assertTrue(arr._nan(0) == false);
		this.assertTrue(arr._nan(1) == true);
		this.assertTrue(arr._nan(2) == false);
		this.assertTrue(arr._nan(3) == true);
		this.assertTrue(arr._nan(4) == false);
		this.assertTrue(arr._nan(5) == false);
		//__.alert(arr);
	} // end main()
} // end CCastUnitTest