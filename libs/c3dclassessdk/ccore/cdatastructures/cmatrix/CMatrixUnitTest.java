//---------------------------------------------------------------------------------
// file: CMatrixUnitTest
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;
import Jama.*; 

//---------------------------------------------------------------------------------
// name: CMatrixUnitTest
// desc: 
//---------------------------------------------------------------------------------
public class CMatrixUnitTest extends CUnitTest{
	@Test
	public void test() {
		CMatrix Z = _.cmatrix(
			 _.v(2104, 5, 1, 45),
			 _.v(1416, 3, 2, 40),
			 _.v(1534, 3, 2, 30),
			 _.v(852, 2, 1, 36)
		);
		
		_.alert(Z);
		
		
		this.assertTrue(Z.columnLength()==4);
		this.assertTrue(Z.rowLength()==4);
		this.assertTrue(Z.ij(0,0) == 2104);
		this.assertTrue(Z.ij(3,3) == 36);
		
		CMatrix A = _.cmatrix(
			 _.v(1, 0),
			 _.v(2, 5),
			 _.v(3, 1)
		);
		
		CMatrix B = _.cmatrix(
			 _.v(4, 0.5),
			 _.v(2, 5),
			 _.v(0, 1)
		);
		
		CMatrix C = _.cmatrix(
			 _.v(1, 0),
			 _.v(2, 5),
			 _.v(3, 1)
		);
		
		CMatrix D = _.cmatrix(
			 _.v(4, 0),
			 _.v(6, 3)
		);
		
		CMatrix E = _.cmatrix(
			 _.v(1, 3),
			 _.v(4, 0),
			 _.v(2, 1)
		);
		
		CMatrix R1 = _.cmatrix(
			 _.v(5, 0.5),
			 _.v(4, 10),
			 _.v(3, 2)
		);
		
		CMatrix R2 = _.cmatrix(
			 _.v(3, 0),
			 _.v(6, 15),
			 _.v(9, 3)
		);
		
		CMatrix R3 = _.cmatrix(
			 _.v(1, 0),
			 _.v(3.0/2.0, 3.0/4.0)
		);
				
		CMatrix R = A.add(B);
		this.assertTrue(R.equals(R1));
		this.assertTrue(!R.equals(A));
		
		R = A.multiply(3);
		this.assertTrue(R.equals(R2));
		this.assertTrue(!R.equals(A));
		
		R = D.multiply(1.0f/4.0f);
		this.assertTrue(R.equals(R3));
		this.assertTrue(!R.equals(D));
		
		R = E.multiply( _.v(1, 5));
		this.assertTrue(R.equals(_.cmatrix( _.v(16), _.v(4), _.v(7))));
		this.assertTrue(!R.equals(D));
		
		CVector col = R2.column(1);
		this.assertTrue(col.equals( _.v(0,15,3)));
		
		CVector hypothesis =  _.v(-40, 0.25); // parameters - x * 0.25 - 40
		CMatrix house_sizes = _.cmatrix( 	 // datamatrix  
			 _.v(1,2104),
			 _.v(1,1416),
			 _.v(1,1534),
			 _.v(1,1852)
		);
		CMatrix predictions = house_sizes.multiply(hypothesis);
		this.assertTrue(predictions.equals(_.cmatrix( _.v(486), _.v(314), _.v(343.5), _.v(423.0))));
		
		CMatrix hypotheses = _.cmatrix(
							 _.v(-40, 200, -150),// h(x) = x * 0.25 - 40
							 _.v(0.25, 0.1, 0.4) // h(x) = x * 0.1 + 200 // h(x) = x * 0.4 - 150
							);
		
		predictions = house_sizes.multiply(hypotheses);
		_.alert(predictions);
		//this.assertTrue(predictions.toString().equals("[[486.0, 410.40002, 691.60004], [314.0, 341.6, 416.40002], [343.5, 353.40002, 463.60004], [423.0, 385.2, 590.8]]"));
		
		this.assertTrue(hypotheses.multiply(CMatrix.I(3)).equals(hypotheses)); // H * I = H
		this.assertTrue(!CMatrix.I(3).multiply(hypotheses).equals(hypotheses)); // I * H != H
		this.assertTrue(CMatrix.I(3).issquared());
		this.assertTrue(!hypotheses.issquared());
	
		CMatrix M4 = _.cmatrix(
						 _.v(1, 0, 2, -1),
                         _.v(3, 0, 0, 5),
                         _.v(2, 1, 4, -3),
                         _.v(1, 0, 5, 0)
					); // end _.cmatrix()
		
		this.assertTrue(cmath.equal(M4.determinant(), 30.0f));
		
		CMatrix M5 = _.cmatrix( 
			 _.v(5, -2, 2, 7),
			 _.v(1, 0, 0, 3),
			 _.v(-3, 1, 5, 0),
			 _.v(3, -1, -9, 4)
		); // end _.cmatrix()
			
		CMatrix R10 = _.cmatrix(
			 _.v(-12, 76, -60, -36), 
			 _.v(-56, 208, -82, -58),
			 _.v(4, 4, -2, -10), 
			 _.v(4, 4, 20, 12)
		); // end _.cmatrix()
		
		this.assertTrue(M5.adjugate().equals(R10));
		
		CMatrix M6 = M5.multiply(M5.inverse());
		CMatrix M7 = M5.inverse().multiply(M5);
		this.assertTrue(M6.equals(M7));
		this.assertTrue(M7.equals(CMatrix.I(4)));
		
	
		//_.alert(M5.inverse());
		//import java.math.BigDecimal;
		//_.alert(M5.adjugate() + "\n\n" + M5.inverse().multiply(M5));
		
		/*
      // create M-by-N matrix that doesn't have full rank
      int M = 8, N = 5;
      Matrix BB = Matrix.random(5, 3);
      Matrix AA = Matrix.random(M, N).times(BB).times(BB.transpose());
      System.out.print("AA = ");
      AA.print(9, 6);

      // compute the singular vallue decomposition
      System.out.println("AA = U S V^T");
      System.out.println();
      SingularValueDecomposition s = AA.svd();
      System.out.print("U = ");
      Matrix U = s.getU();
      U.print(9, 6);
      System.out.print("Sigma = ");
      Matrix S = s.getS();
      S.print(9, 6);
      System.out.print("V = ");
      Matrix V = s.getV();
      V.print(9, 6);
      System.out.println("rank = " + s.rank());
      System.out.println("condition number = " + s.cond());
      System.out.println("2-norm = " + s.norm2());
	
      // print out singular values
      System.out.print("singular values = ");
      Matrix svalues = new Matrix(s.getSingularValues(), 1);
      svalues.print(9, 6);
	*/
	} // end test()
} // end CMatrixUnitTest