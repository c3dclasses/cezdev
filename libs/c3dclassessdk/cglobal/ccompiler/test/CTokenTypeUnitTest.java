//---------------------------------------------------------------------------
// file: CTokenTypeUnitTest
// desc: defines the type of tokens to find 
//---------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;

//--------------------------------------------------------
// name: CTokenType
// desc: defines the type of tokens to find
//--------------------------------------------------------
public class CTokenTypeUnitTest extends CUnitTest {
	@Test
	public void testCTokenTypeUnitTest() {
		CTokenType ctokentype = new CTokenType("comment", "/*", "*/");
		this.assertTrue(ctokentype.m_strbegin == "/*");
		this.assertTrue(ctokentype.m_strend == "*/");
		this.assertTrue(ctokentype.m_strtype == "comment");
	} // end CTokenTypeUnitTest()	
} // end CTokenType