//---------------------------------------------------------------------------------
// file: CVectorUnitTest
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;

//---------------------------------------------------------------------------------
// name: CVectorUnitTest
// desc: 
//---------------------------------------------------------------------------------
public class CVectorUnitTest extends CUnitTest{
	@Test
	public void test() {
		CVector v = new CVector(10);
		this.assertTrue(v.length()==10);
		
		CVector v1 = _.v(3.0,4.0,5.0);
		CVector v2 = _.v(4.0,5.0,6.0);	
		
		this.assertTrue(v1.length() == 3);
		this.assertTrue(v1.i(0) == 3.0);
		this.assertTrue(v1.i(1) == 4.0);
		this.assertTrue(v1.i(2) == 5.0);
		
		this.assertTrue(cmath.equal(v1.euclideanDistance(v2),1.7320508f));
		
		CMatrix cvectors = _.cmatrix(v1,v2);
		CVector vmin = cvectors.minRowComponents();
		this.assertTrue(vmin.equals(_.v(3.0,4.0,5.0)));
		
		cvectors = _.cmatrix(v1,v2);
		CVector vmax = cvectors.maxRowComponents();
		this.assertTrue(vmax.equals(_.v(4.0,5.0,6.0)));
		
		v = CVector.rand(vmin, vmax);
		this.assertTrue(v.between(vmin, vmax));
		
		v = cvectors.maxColumnComponents();
		this.assertTrue(v.equals(_.v(5.0,6.0)));
		
		cvectors = CMatrix.rand(10, vmin, vmax);
		this.assertTrue(cvectors.between(vmin, vmax));
	} // end main()
} // end CVectorUnitTest