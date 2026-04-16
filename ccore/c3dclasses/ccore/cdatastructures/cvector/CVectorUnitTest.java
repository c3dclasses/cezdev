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
public class CVectorUnitTest extends CUnitTest {
	@Test
	public void test() {
		CVector v = new CVector(10);
		this.assertTrue(v.length()==10);
		
		CVector v1 = __.v(3.0,4.0,5.0);
		CVector v2 = __.v(4.0,5.0,6.0);	
		
		this.assertTrue(v1.length() == 3);
		this.assertTrue(v1.i(0) == 3.0);
		this.assertTrue(v1.i(1) == 4.0);
		this.assertTrue(v1.i(2) == 5.0);
		
		this.assertTrue(CMath.equal(v1.euclideanDistance(v2),1.7320508f));
		
		CMatrix cvectors = __.cmatrix(v1,v2);
		CVector vmin = cvectors.minRowComponents();
		this.assertTrue(vmin.equals(__.v(3.0,4.0,5.0)));
		
		cvectors = __.cmatrix(v1,v2);
		CVector vmax = cvectors.maxRowComponents();
		this.assertTrue(vmax.equals(__.v(4.0,5.0,6.0)));
		
		v = CVector.rand(vmin, vmax);
		this.assertTrue(v.between(vmin, vmax));
		
		v = cvectors.maxColumnComponents();
		this.assertTrue(v.equals(__.v(5.0,6.0)));
		
		cvectors = CMatrix.rand(10, vmin, vmax);
		this.assertTrue(cvectors.between(vmin, vmax));
		
		CVector v3 = v1.merge(v2);
		this.assertTrue(v3.equals(__.v(3.0,4.0,4.0,5.0,5.0,6.0)));
		
		this.assertTrue(this.isSumOf2ElementsInTheSequence(v3, 8.0));
		this.assertTrue(this.isSumOf2ElementsInTheSequence(v3, 9.0));
		
		//v1.shuffle();
		//this.assertTrue(!v1.equals(__.v(3.0,4.0,5.0)));
		
		
	} // end main()
	
	public boolean isSumOf2ElementsInTheSequence(CVector v, double sum) {
		v = v.copy();
		int l = 0;
		int r = v.length() - 1;
		while(l<r) {
			double s = v.i(r) + v.i(l);
			if(s == sum)
				return true;
			else if(s < sum) l++;
			else r--;
		} // end while
		return false;
	} // isSumOf2ElementsInTheSequence()
} // end CVectorUnitTest