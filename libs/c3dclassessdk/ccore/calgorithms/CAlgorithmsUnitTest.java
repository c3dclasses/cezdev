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
		_.alert(calgorithms.add_nbits(_.carray(1,1,1,1),_.carray(1,1,1,1)));
	} // end test()
} // end CAlgorithmsUnitTest