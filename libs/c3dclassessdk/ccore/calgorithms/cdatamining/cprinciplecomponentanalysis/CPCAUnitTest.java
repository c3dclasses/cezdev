//--------------------------------------------------
// file: CPCAUnitTest
// desc:
//--------------------------------------------------
package c3dclasses;
import org.junit.Test;
import org.junit.Ignore;

//--------------------------------------------------------
// name: CPCAUnitTest
// desc:
//--------------------------------------------------------
public class CPCAUnitTest extends CUnitTest {
	@Ignore
	@Test 
	public void test() throws Exception {
		// set k dimension to reduce to 
		int k = 2;
		
		// reduce the dataset using the PCA algorithmn
		CMatrix Z = CPCA._(_.dir_path(this) + "/iris.dat", k);
		
		// save the z parameters to a file
		_.save_csv_file(_.dir_path(this) + "/iris-2d.dat", _.carray(Z));
		
		// determine if k is an optimal dimensionality for a PCS projection.
		_.println("retains variance %:" + CPCA.variance(k) + " at k="+k);
	} // end test()
} // end CPCAUnitTest