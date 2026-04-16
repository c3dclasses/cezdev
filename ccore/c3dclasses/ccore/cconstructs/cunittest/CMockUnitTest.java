//---------------------------------------------------------------------------------
// file: CMockUnitTest
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;
import org.junit.*;
//import static org.mockito.Mockito.*;

//---------------------------------------------------------------------------------
// name: CMockUnitTest
// desc: 
//---------------------------------------------------------------------------------
public class CMockUnitTest extends CUnitTest {
	@Test
	public void test() {
		//Create the mock object of stock service
		//CMockClass cmockclass = mock(CMockClass.class);
		//when(cmockclass.foo(9)).thenReturn(9);
		//this.assertTrue(cmockclass.foo(9) == 9);
	} // end main()
} // end CMockUnitTest

//--------------------------------------------------
// name: CMockClass
// desc: 
//--------------------------------------------------
class CMockClass {
	public int foo(int i) { return i; }
} // end CMockClass