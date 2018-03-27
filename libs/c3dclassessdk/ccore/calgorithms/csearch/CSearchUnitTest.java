//--------------------------------------------------
// file: CSearchUnitTest
// desc:
//--------------------------------------------------
package c3dclasses;
import org.junit.Test;
import org.junit.Ignore;

//--------------------------------------------------------
// name: CSearchUnitTest
// desc:
//--------------------------------------------------------
public class CSearchUnitTest extends CUnitTest {
	@Ignore
	@Test 
	public void test() {
		CFunction compare = new CFunction() {
			public CReturn call(CArray args) {
				return CReturn._done(args._(0) == args._(1));
			} // end call()
		}; // end CFunction()
		this.assertTrue(csearch.linear_search(_.carray(1,2,3,5,6,7,9,100,-1,80), 5, compare) == 3);
	} // end test()
} // end CSortUnitTest