//-------------------------------------------------------------------------------------------------------
// file: CThreadUnitTest.java
// desc: 
//-------------------------------------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;

//-----------------------------------------------------------
// name: CPThreadUnitTest
// desc:
//-----------------------------------------------------------
public class CThreadUnitTest extends CUnitTest {
	@Test
	public void test() {
		final CThreadUnitTest this_test = this;
		// thread functions
		CFunction foo1 = new CFunction("foo1") {
			public CReturn call(Object object) {
				CThread cthread = (CThread) object;
				this_test.assertTrue(cthread != null);
				this_test.assertTrue(cthread.name().equals("test-thread"));	
				cthread.jump("foo2");
				return null;
			} // end call()
		}; // end foo1()
		CFunction foo2 = new CFunction("foo2") {
			public CReturn call(Object object) {
				CThread cthread = (CThread) object;
				this_test.assertTrue(cthread != null);
				this_test.assertTrue(cthread.name().equals("test-thread"));	
				return null;
			} // end call()
		}; // end foo2()		
		
		// psuedo-thread functions
		CFunction foo3 = new CFunction("foo3") {
			public CReturn call(Object object) {		
				CThread cthread = (CThread) object;
				this_test.assertTrue(cthread != null);
				this_test.assertTrue(cthread.name().equals("test-pthread"));	
				cthread.jump("foo4");
				return null;
			} // end call()
		}; // end foo3()
		CFunction foo4 = new CFunction("foo4") {
			public CReturn call(Object object) {
				CThread cthread = (CThread) object;
				this_test.assertTrue(cthread != null);
				this_test.assertTrue(cthread.name().equals("test-pthread"));				
				cthread.destroy();
				return null;
			} // end call()
		}; // end foo4()
	
		// create a thread object using a thread driver
		CThread cthread = new CThread("c3dclasses.CThreadDriver");
		this.assertTrue(cthread != null);
		this.assertTrue(cthread.create(foo1));
		cthread.start();
		cthread.name("test-thread");
		
		// create a pthread object using a pthread driver
		CThread cpthread = new CThread("c3dclasses.CPThreadDriver");
		this.assertTrue(cpthread != null);
		this.assertTrue(cpthread.create(500, foo3));
		cpthread.name("test-pthread");
		cpthread.start();
		this.assertTrue(CIntervalConcurrentEvent.doEventLoop(10)); // consume at most 10 cycles per iteration
	} // end test()
} // end CPThreadUnitTest