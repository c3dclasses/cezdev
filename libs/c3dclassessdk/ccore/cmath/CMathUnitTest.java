//---------------------------------------------------------------------------------
// file: CMathUnitTest
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;

//---------------------------------------------------------------------------------
// name: CMathUnitTest
// desc: 
//---------------------------------------------------------------------------------
public class CMathUnitTest extends CUnitTest{
	@Test
	public void test() {
		// scalars
		float value = cmath.get_random_value_from_range(3.0f, 10.0f);
		this.assertTrue(cmath.is_value_within_range(value, 3.0f, 10.0f));
		value = cmath.get_random_value_from_range(3.0f, 10.0f);
		this.assertTrue(cmath.is_value_within_range(value, 3.0f, 10.0f));
	
		// vectors
		CArray v1 = _.carray(3.0,4.0,5.0);
		CArray v2 = _.carray(4.0,5.0,6.0);	
		float f1 = cvector.get_euclidean_distance(v1,v2);
		CArray v3 = _.carray("nan", 3.0,4.0,5.0, "mannnnnn");
		CArray v4 = _.carray("nan", 4.0,5.0,6.0, "nan");	
		float f2 = cvector.get_euclidean_distance(v1,v2);	
		this.assertTrue(cmath.equal(f1,f2));
	
		CArray vectors = _.carray(v1,v2);
		CArray v5 = cvector.get_max_components(vectors);
		this.assertTrue(cvector.equals(v5, _.carray(4.0,5.0,6.0)));
		vectors = _.carray(v3,v4);
		CArray v6 = cvector.get_max_components(vectors);	
		this.assertTrue(cvector.equals(v6, _.carray("nan",4.0,5.0,6.0,"mannnnnn")));
		
		vectors = _.carray(v1,v2);
		v5 = cvector.get_min_components(vectors);
		this.assertTrue(cvector.equals(v5,_.carray(3.0,4.0,5.0)));
		vectors = _.carray(v3,v4);
		v6 = cvector.get_min_components(vectors);	
		this.assertTrue(cvector.equals(v6, _.carray("nan",3.0,4.0,5.0,"mannnnnn")));
		
		vectors = _.carray(v1,v2);
		CArray vmin = cvector.get_min_components(vectors);
		CArray vmax = cvector.get_max_components(vectors);
		CArray vrand = cvector.get_random_vector_from_range(vmin,vmax);
		CArray vrands = cvector.get_random_vectors_from_range(10,vmin,vmax);
		this.assertTrue(cvector.is_vector_within_vector_range(vrand, vmin, vmax));
		this.assertTrue(cvector.is_vectors_within_vector_range(vrands, vmin, vmax));	
	} // end main()
} // end CMathUnitTest