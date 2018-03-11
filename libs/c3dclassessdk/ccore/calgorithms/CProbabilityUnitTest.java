//--------------------------------------------------
// file: CProbabilityUnitTest
// desc: 
//--------------------------------------------------
package c3dclasses;

import org.junit.Test;

//--------------------------------------------------------
// name: CProbabilityUnitTest
// desc: 
//--------------------------------------------------------
public class CProbabilityUnitTest {
	@Test 
	public void test() {
		CHash cond_dist = _.chash(_.h(
								"not-hit", _.h("red", 0.99, "yellow", 0.9, "green", 0.2),
								"hit",_.h("red", 0.99, "yellow", 0.9,"green", 0.2)
								) // end _.h()
							); // end _.chash()
		
		
		CHash joint_dist = _.chash(_.h(
								"not-hit", _.h("red", 0.198, "yellow", 0.09, "green", 0.14),
								"hit",_.h("red", 0.002, "yellow", 0.01, "green", 0.56)
								) // end _.h()
							); // end _.chash()
		
		// compute marginal distribution 
		//_.chash._("total", "red", value);
		//_.chash._("total", "red") value);
		
		
		
		_.alert(cond_dist);
		
		
	} // end test
} // end CProbabilityUnitTest