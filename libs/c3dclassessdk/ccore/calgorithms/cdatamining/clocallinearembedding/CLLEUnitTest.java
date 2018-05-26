//--------------------------------------------------
// file: CLLEUnitTest
// desc:
//--------------------------------------------------
package c3dclasses;
import org.junit.Test;
import org.junit.Ignore;

//--------------------------------------------------------
// name: CLLEUnitTest
// desc:
//--------------------------------------------------------
public class CLLEUnitTest extends CUnitTest {
	public void lle_output(String obsfilename, String llefilename, String outfilename, String outfilenamegrid) {
		CMatrix X = _.cmatrix(_.load_csv_file(_.dir_path(this) + obsfilename));
		CMatrix Xlle = _.cmatrix(_.load_csv_file(_.dir_path(this) + llefilename));
		int N = 4;
		CMatrix M_NxN = Xlle.subMatrixGrid(N,N);		
		CMatrix M_NxN_closest = Xlle.closestRowsToMatrix(M_NxN);	
		CArray indexes = Xlle.closestRowsIndexesToMatrix(M_NxN);
		CMatrix Imgs = new CMatrix(N*28,N*28);
		for(int i=0,k=0; i<N; i++) {
			for(int j=0; j<N; j++,k++) {
				CVector x = X.i(indexes._int(k));
				Imgs.subMatrixBlock(i,j,x.toMatrix(28,28));			
			} // end for
		} // end for
		Imgs.toImageFile(_.dir_path(this) + outfilename, "jpg");
		Xlle.appendColumn(0);
		M_NxN_closest.appendColumn(1);
		Xlle.appendRows(M_NxN_closest);
		Xlle.toFile(_.dir_path(this) + outfilenamegrid);
	} // end lle_output()
	@Ignore
	@Test 
	public void test() throws Exception {
		lle_output("/mnist-digit-9-5000-obs.csv","/mnist-digit-9-5000-lle-k5.csv", "/digit-grid-lle-k5.jpg", "/plot-grid-lle-k5.csv");
		lle_output("/mnist-digit-9-5000-obs.csv","/mnist-digit-9-5000-lle-k10.csv", "/digit-grid-lle-k10.jpg", "/plot-grid-lle-k10.csv");
		lle_output("/mnist-digit-9-5000-obs.csv","/mnist-digit-9-5000-lle-k20.csv", "/digit-grid-lle-k20.jpg", "/plot-grid-lle-k20.csv");
		lle_output("/mnist-digit-9-5000-obs.csv","/mnist-digit-9-5000-lle-k50.csv", "/digit-grid-lle-k50.jpg", "/plot-grid-lle-k50.csv");
	} // end test()
} // end CLLEUnitTest