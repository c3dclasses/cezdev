//--------------------------------------------------
// file: CAlgorithmsUnitTest
// desc:
//--------------------------------------------------
package c3dclasses;
import org.junit.Test;

//--------------------------------------------------------
// name: CAlgorithmsUnitTest
// desc:
//--------------------------------------------------------
public class CAlgorithmsUnitTest extends CUnitTest {
	@Test 
	public void test() {
		CFunction compare = new CFunction() {
			public CReturn call(CArray args) {
				return CReturn._done(args._int(0) == args._int(1));
			} // end call()
		}; // end CFunction()
		//_.alert(calgorithms.add_nbits(_.carray(1,1,1,1),_.carray(1,1,1,1)));
		//_.alert(calgorithms.has_repeated_elements_bf(_.carray(1,2,4,4), compare));
	} // end test()
} // end _UnitTest