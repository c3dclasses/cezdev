//-------------------------------------------------------------------------------------------------------
// file: CConcurrentEventUnitTest.java
// desc: 
//-------------------------------------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;

//-----------------------------------------------------------
// name: CConcurrentEventUnitTest
// desc:
//-----------------------------------------------------------
public class CConcurrentEventUnitTest extends CUnitTest {
	static public int i = 0;
	static public int id = -1;
	static public int id2 = -1;
	
	@Test
	public void test() {
		final CConcurrentEventUnitTest _this = (CConcurrentEventUnitTest) this;
		
		// interval
		CConcurrentEventUnitTest.id = CIntervalConcurrentEvent.setInterval(new CFunction() {
			public CReturn call(CArray args) {
				int i = CConcurrentEventUnitTest.i;
				int id = CConcurrentEventUnitTest.id;
				if(i == 4) {
					_this.assertTrue(i==4);
					CIntervalConcurrentEvent.clearInterval(id);
				} // end if
				i++;
				CConcurrentEventUnitTest.i=i;
				return null;
			} // end call()
		}, 30); // end setInterval()
		this.assertTrue(CConcurrentEventUnitTest.id > -1);
		
		// timeout
		CConcurrentEventUnitTest.id2 = CTimeoutConcurrentEvent.setTimeout(new CFunction() {
			public CReturn call(CArray args) {
				int id = CConcurrentEventUnitTest.id2;
				CIntervalConcurrentEvent.clearInterval(id);
				return null;
			} // end call()
		}, 30); // end setInterval()
		this.assertTrue(CConcurrentEventUnitTest.id > -1);
		this.assertTrue(CConcurrentEventUnitTest.id2 > -1);
		this.assertTrue(CIntervalConcurrentEvent.doEventLoop(10)); // consume 10 events per iteration		
	} // end test()
} // end CConcurrentEventUnitTest