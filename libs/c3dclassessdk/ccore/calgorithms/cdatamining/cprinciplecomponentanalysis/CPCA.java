//--------------------------------------------------
// file: CPCA
// desc:
//--------------------------------------------------
package c3dclasses;
import org.junit.Test;

//--------------------------------------------------------
// name: CPCA
// desc:
//--------------------------------------------------------
public class CPCA {
	static private CMatrix S;
	
	public static CMatrix _(String strfilename, int k) {	
		// load the dataset
		CArray dataset = _.load_csv_file(strfilename);
		
		// convert the dataset to an matrix and remove last column of the matrix
		CMatrix X = _.cmatrix(dataset, _.args(4)); //_.cmatrix(dataset).subColumnMatrix(0,dataset.length()-2);
		
		// perform mean normalization on the dataset
		CMatrix Xn = cmath.mean_normalization(X);
		
		// build the covariance matrix
		CMatrix Sigma = Xn.covariance();
		
		// perform SVD on the matrix
		CArray USV = Sigma.svd();
		
		// get the plane vectors
		CMatrix U = (CMatrix) USV._(0);
		
		// s contains the variance matrix
		S = (CMatrix) USV._(1);
		
		// get the first k rows
		CMatrix U_r_t = U.subColumnMatrix(0,k-1).transpose();
		
		return U_r_t.multiply(Xn.transpose()).transpose();
	} // end _()
	
	public static double variance(int k) {
		CVector s = S.diagonal();
		CVector s_k = s.subVertex(k-1);
		return s_k.sum() / s.sum();
	} // end variance()
	
} // end CPCA