//--------------------------------------------------
// file: CISOMapUnitTest
// desc:
//--------------------------------------------------
package c3dclasses;
import org.junit.Test;
import org.junit.Ignore;

//--------------------------------------------------------
// name: CISOMapUnitTest
// desc:
//--------------------------------------------------------
public class CISOMapUnitTest extends CUnitTest {
	
	@Ignore
	@Test 
	public void test() throws Exception {
		CMatrix X = _.cmatrix(_.load_csv_file(_.dir_path(this) + "/mnist-digit-9-5000-obs.csv"));
		CMatrix Xisomap = _.cmatrix(_.load_csv_file(_.dir_path(this) + "/mnist-digit-9-5000-isomap-k5.csv"));
		int N = 4;
		CMatrix M_NxN = Xisomap.subMatrixGrid(N,N);
		CMatrix M_NxN_closest = Xisomap.closestRowsToMatrix(M_NxN);	
		CArray indexes = Xisomap.closestRowsIndexesToMatrix(M_NxN);
		CMatrix Imgs = new CMatrix(N*28,N*28);
		for(int i=0,k=0; i<N; i++) {
			for(int j=0; j<N; j++,k++) {
				CVector x = X.i(indexes._int(k));
				Imgs.subMatrixBlock(i,j,x.toMatrix(28,28));			
			} // end for
		} // end for
		Imgs.toImageFile(_.dir_path(this) + "/digit-grid.jpg", "jpg");
		Xisomap.appendColumn(0);
		M_NxN_closest.appendColumn(1);
		Xisomap.appendRows(M_NxN_closest);
		Xisomap.toFile(_.dir_path(this) + "/plot-grid-isomap-k5.csv");
	} // end test()
} // end CISOMapUnitTest