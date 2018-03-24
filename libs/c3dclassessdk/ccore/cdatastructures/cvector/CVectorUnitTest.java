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
		CVector v = _.cvector(10);
		this.assertTrue(v.length()==10);
		
		CVector v1 = _.cvector(3.0,4.0,5.0);
		CVector v2 = _.cvector(4.0,5.0,6.0);	
		this.assertTrue(cmath.equal(v1.euclideanDistance(v2),1.7320508f));
		
		CMatrix cvectors = _.cmatrix(v1,v2);
		CVector vmin = CVector.getMinComponentVector(cvectors);
		this.assertTrue(vmin.equals(_.cvector(3.0,4.0,5.0)));
		
		cvectors = _.cmatrix(v1,v2);
		CVector vmax = CVector.getMaxComponentVector(cvectors);
		this.assertTrue(vmax.equals(_.cvector(4.0,5.0,6.0)));
		
		v = CVector.getRandomVectorFromRange(vmin, vmax);
		this.assertTrue(v.isWithinRange(vmin, vmax));
		
		cvectors = CVector.getRandomVectorsFromRange(10, vmin, vmax);
		this.assertTrue(CVector.areVectorsWithinRange(cvectors, vmin, vmax));
	} // end main()
} // end CVectorUnitTest