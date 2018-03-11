//--------------------------------------------------
// file: CAprioriUnitTest
// desc:
//--------------------------------------------------
package c3dclasses;
import org.junit.Test;

//--------------------------------------------------------
// name: CAprioriUnitTest
// desc:
//--------------------------------------------------------
public class CAprioriUnitTest extends CUnitTest {
	@Test 
	public void test() {
		/* load the mushroom items from the file
		CArray D = _.get_lines_from_file("C:/Users/developer/Desktop/cezdev2/libs/c3dclassessdk/cnotes/cmodels/cdatamining/hw1/agaricus-lepiota.data.txt");
			
		// make sure the items in each row are indexed
		for(int i=0; i<D.length(); i++) {
			CArray items = D._explode(i,",");
			for(int j=0; j<items.length(); j++)
				items._(j,j+"."+items._string(j));		
			D._(i, items.join(","));
		} // end for
		
		// run the apriori algorithm
		CArray L = capriori._(D,10000);
		*/
	} // end test()
} // end CAprioriUnitTest