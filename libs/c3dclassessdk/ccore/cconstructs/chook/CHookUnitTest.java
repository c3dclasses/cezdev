//-------------------------------------------------------------------------------------------------------
// file: CHookUnitTest.java
// desc: provides a way to store php handler function that will be executed later by the ckernal object
//-------------------------------------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;

//-----------------------------------------------------------
// name: CHook
// desc: singleton object used to store handler functions
//-----------------------------------------------------------
public class CHookUnitTest extends CUnitTest {
	public static int m_count = 0;
	@Test
	public void test() {	
		CHook.add("onload", new CFunction() {
			public CReturn call(CArray args) {
				CHookUnitTest.m_count++;
				return null;
			} // end _()
		}); // end CHook.add()
		
		CHook.add("onload", new CFunction() {
			public CReturn call(CArray args) {
				CHookUnitTest.m_count++;
				return null;
			} // end _()
		}); // end CHook.add()
		
		CHook.add("onload", new CFunction() {
			public CReturn call(CArray args) {
				CHookUnitTest.m_count++;
				return null;
			} // end _()
		}); // end CHook.add()
		
		CHook.add("oninit", new CFunction() {
			public CReturn call(CArray args) {
				CHookUnitTest.m_count++;
				return null;
			} // end _()
		}); // end CHook.add()
		
		CHook.add("oninit", new CFunction() {
			public CReturn call(CArray args) {
				CHookUnitTest.m_count++;
				return null;
			} // end _()
		}); // end CHook.add()
		
		CFunction cfunction = new CFunction() {
			public CReturn call(CArray args) {
				CHookUnitTest.m_count++;
				return null;
			} // end _()
		}; // end cfunction 
		CHook.add("oninit", cfunction);
		
		CHookUnitTest.m_count = 0;
		CHook.fire("onload");
		this.assertTrue(CHookUnitTest.m_count == 3);	
		
		CHookUnitTest.m_count = 0;
		CHook.fire("oninit");
		this.assertTrue(CHookUnitTest.m_count == 3);	
		
		CHookUnitTest.m_count = 0;
		CHook.remove("onload", null);
		CHook.fire("onload");
		this.assertTrue(CHookUnitTest.m_count == 0);	
		
		CHookUnitTest.m_count = 0;
		CHook.remove("oninit", cfunction);
		CHook.fire("oninit");
		this.assertTrue(CHookUnitTest.m_count == 2);	
	} // end test()
} // end CHookUnitTest