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
		CMatrix Z = __.cmatrix(
			 __.v(2104, 5, 1, 45),
			 __.v(1416, 3, 2, 40),
			 __.v(1534, 3, 2, 30),
			 __.v(852, 2, 1, 36)
		);
		
		//__.alert(Z);
		
		
		this.assertTrue(Z.columnLength()==4);
		this.assertTrue(Z.rowLength()==4);
		this.assertTrue(Z.ij(0,0) == 2104);
		this.assertTrue(Z.ij(3,3) == 36);
		
		CMatrix A = __.cmatrix(
			 __.v(1, 0),
			 __.v(2, 5),
			 __.v(3, 1)
		);
		
		CMatrix B = __.cmatrix(
			 __.v(4, 0.5),
			 __.v(2, 5),
			 __.v(0, 1)
		);
		
		CMatrix C = __.cmatrix(
			 __.v(1, 0),
			 __.v(2, 5),
			 __.v(3, 1)
		);
		
		CMatrix D = __.cmatrix(
			 __.v(4, 0),
			 __.v(6, 3)
		);
		
		CMatrix E = __.cmatrix(
			 __.v(1, 3),
			 __.v(4, 0),
			 __.v(2, 1)
		);
		
		CMatrix R1 = __.cmatrix(
			 __.v(5, 0.5),
			 __.v(4, 10),
			 __.v(3, 2)
		);
		
		CMatrix R2 = __.cmatrix(
			 __.v(3, 0),
			 __.v(6, 15),
			 __.v(9, 3)
		);
		
		CMatrix R3 = __.cmatrix(
			 __.v(1, 0),
			 __.v(3.0/2.0, 3.0/4.0)
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
		
		R = E.multiply( __.v(1, 5));
		this.assertTrue(R.equals(__.cmatrix( __.v(16), __.v(4), __.v(7))));
		this.assertTrue(!R.equals(D));
		
		CVector col = R2.column(1);
		this.assertTrue(col.equals( __.v(0,15,3)));
		
		CVector hypothesis =  __.v(-40, 0.25); // parameters - x * 0.25 - 40
		CMatrix house_sizes = __.cmatrix( 	 // datamatrix  
			 __.v(1,2104),
			 __.v(1,1416),
			 __.v(1,1534),
			 __.v(1,1852)
		);
		CMatrix predictions = house_sizes.multiply(hypothesis);
		this.assertTrue(predictions.equals(__.cmatrix( __.v(486), __.v(314), __.v(343.5), __.v(423.0))));
		
		CMatrix hypotheses = __.cmatrix(
							 __.v(-40, 200, -150),// h(x) = x * 0.25 - 40
							 __.v(0.25, 0.1, 0.4) // h(x) = x * 0.1 + 200 // h(x) = x * 0.4 - 150
							);
		
		predictions = house_sizes.multiply(hypotheses);
		//__.alert(predictions);
		//this.assertTrue(predictions.toString().equals("[[486.0, 410.40002, 691.60004], [314.0, 341.6, 416.40002], [343.5, 353.40002, 463.60004], [423.0, 385.2, 590.8]]"));
		
		this.assertTrue(hypotheses.multiply(CMatrix.identity(3)).equals(hypotheses)); // H * I = H
		this.assertTrue(!CMatrix.identity(3).multiply(hypotheses).equals(hypotheses)); // I * H != H
		this.assertTrue(CMatrix.identity(3).squared());
		this.assertTrue(!hypotheses.squared());
	
		CMatrix M4 = __.cmatrix(
						 __.v(1, 0, 2, -1),
                         __.v(3, 0, 0, 5),
                         __.v(2, 1, 4, -3),
                         __.v(1, 0, 5, 0)
					); // end __.cmatrix()
		
		this.assertTrue(CMath.equal(M4.determinant(), 30.0f));
		
		CMatrix M5 = __.cmatrix( 
			 __.v(5, -2, 2, 7),
			 __.v(1, 0, 0, 3),
			 __.v(-3, 1, 5, 0),
			 __.v(3, -1, -9, 4)
		); // end __.cmatrix()
			
		CMatrix R10 = __.cmatrix(
			 __.v(-12, 76, -60, -36), 
			 __.v(-56, 208, -82, -58),
			 __.v(4, 4, -2, -10), 
			 __.v(4, 4, 20, 12)
		); // end __.cmatrix()
		
		this.assertTrue(M5.adjugate().equals(R10));
		
		CMatrix M6 = M5.multiply(M5.inverse());
		CMatrix M7 = M5.inverse().multiply(M5);
		this.assertTrue(M6.equals(M7));
		this.assertTrue(M7.equals(CMatrix.identity(4)));
	
		this.assertTrue(R10.diagonal().equals(__.v(-12,208,-2,12)));
		
		CMatrix M = __.m(__.v(10, 20, 10),
						__.v(-20, -30, 10),
						__.v(30, 50, 0));
		this.assertTrue(M.rank() == 2);
		
		this.assertTrue(M.toVector().equals(__.v(10, 20, 10, -20, -30, 10, 30, 50, 0)));
		
		M.appendRows(__.m(__.v(-30, -40, 20),__.v(40, 50, 60)));
		this.assertTrue(M.equals(__.m(
			__.v(10, 20, 10),
			__.v(-20, -30, 10),
			__.v(30, 50, 0),
			__.v(-30, -40, 20),
			__.v(40, 50, 60)
		)));
		
		M.appendColumns(__.m(__.v(-30, -40, 20, 10, 0),__.v(40, 50, 60, 70, 80)));
		this.assertTrue(M.equals(__.m(
			__.v(10, 20, 10,-30,40),
			__.v(-20, -30, 10, -40, 50),
			__.v(30, 50, 0, 20, 60),
			__.v(-30, -40, 20, 10, 70),
			__.v(40, 50, 60, 0, 80)
		)));
		
		//__.println(M);
		
		CMatrix Mrand = CMatrix.rand(768,5000);
		//__.println(Mrand);
		
		/*
		CMatrix Mpoints = CMatrix.rand(20,3);
		Mpoints.j(2,0);
		CMatrix Mgrid = Mpoints.subMatrixGrid(3,3);
		Mgrid.j(2,0);
		
		Mgrid = Mpoints.closestRowsToMatrix(Mgrid);
		Mgrid.j(2,1);
		
		CVector min = Mpoints.minRowComponents();
		CVector max = Mpoints.maxRowComponents();
		min.i(2,2);
		max.i(2,2);
		
		Mpoints.appendRows(Mgrid);
		Mpoints.push(min);
		Mpoints.push(max);
		
		//__.println(Mgrid);
		Mpoints.toFile(__.dir_path(this) + "/Mpoints.txt");
		Mgrid.toFile(__.dir_path(this) + "/Mgrid.txt");
		*/
	} // end test()
} // end CMatrixUnitTest