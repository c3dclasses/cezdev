//---------------------------------------------------------------------------------
// file: CPQueue
// desc: defines a priority queue object
//---------------------------------------------------------------------------------
package c3dclasses;
import java.util.*;
import org.junit.Test;

//-------------------------------------------
// class CPQueue
// desc: defines a priority queue object
//-------------------------------------------
public class CPQueueUnitTest extends CUnitTest {
	@Test
	public void test() {
		CPQueue cpqueue = new CPQueue();
		this.assertTrue(cpqueue != null);
		cpqueue.add(1);
		cpqueue.add(10);
		cpqueue.add(-2);
		cpqueue.add(-1);
		this.assertTrue(cpqueue.size() == 4);
		this.assertTrue(cpqueue.remove().toString().equals("-2"));
		this.assertTrue(cpqueue.remove().toString().equals("-1"));
		this.assertTrue(cpqueue.remove().toString().equals("1"));
		this.assertTrue(cpqueue.remove().toString().equals("10"));		
	} // end test()
} // end CPQueue
