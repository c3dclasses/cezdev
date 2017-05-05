//---------------------------------------------------------------------------
// file: CTokenUnitTest
// desc: test the ctoken object 
//---------------------------------------------------------------------------
package cglobal;
import org.junit.Test;

//--------------------------------------------------------
// name: CToken
// desc: test the ctoken object
//--------------------------------------------------------
public class CTokenUnitTest extends CUnitTest {
	@Test
	public void testCTokenUnitTest() {
		String strinput = "int main(){_if(a==10){print('hello, world');_while(b==10){_print(\"foo\");}}}}";
		
		String strtoken = "_if";
		int itokenlength = strtoken.length();
		int istokenindex = strinput.indexOf(strtoken);
		int ietokenindex = istokenindex + itokenlength;
		String strtokentype = strtoken.toUpperCase();
		int iindex = 0;
		CToken ctoken1 = new CToken(istokenindex, ietokenindex, iindex, strtokentype);
		this.assertTrue(ctoken1.m_spos == istokenindex);
		this.assertTrue(ctoken1.m_epos == ietokenindex);
		this.assertTrue(ctoken1.m_index == iindex);
		this.assertTrue(ctoken1.m_strtype == strtokentype);
		this.assertTrue(ctoken1.getStartPos() == istokenindex);
		this.assertTrue(ctoken1.getEndPos() == ietokenindex);
		this.assertTrue(ctoken1.getIndex() == iindex);
		this.assertTrue(ctoken1.toString(strinput).equals("_if"));
		this.assertTrue(ctoken1.toStringBeforeToken(strinput, 8).equals(" main(){"));
		this.assertTrue(ctoken1.toStringAfterToken(strinput, 8).equals("(a==10){"));
		
		strtoken = "_while";
		itokenlength = strtoken.length();
		istokenindex = strinput.indexOf(strtoken);
		ietokenindex = istokenindex + itokenlength;
		strtokentype = strtoken.toUpperCase();
		iindex = 1;
		CToken ctoken2 = new CToken(istokenindex, ietokenindex, iindex, strtokentype);
		this.assertTrue(ctoken2.m_spos == istokenindex);
		this.assertTrue(ctoken2.m_epos == ietokenindex);
		this.assertTrue(ctoken2.m_index == iindex);
		this.assertTrue(ctoken2.getStartPos() == istokenindex);
		this.assertTrue(ctoken2.getEndPos() == ietokenindex);
		this.assertTrue(ctoken2.getIndex() == iindex);
		this.assertTrue(ctoken2.m_strtype.equals(strtokentype));
		this.assertTrue(ctoken2.toString(strinput).equals("_while"));
		this.assertTrue(ctoken2.toStringBeforeToken(strinput, 8).equals("world');"));
		this.assertTrue(ctoken2.toStringAfterToken(strinput, 8).equals("(b==10){"));
	} // end CTokenUnitTest()	
} // end CToken