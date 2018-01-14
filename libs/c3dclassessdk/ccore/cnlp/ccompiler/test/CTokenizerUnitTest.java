//---------------------------------------------------------------------------
// file: CTokenizerUnitTest
// desc: test the ctokenizer object 
//---------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;

//--------------------------------------------------------
// name: CTokenizerUnitTest
// desc: test the ctokenizer object
//--------------------------------------------------------
public class CTokenizerUnitTest extends CUnitTest {
	@Test
	public void testCTokenizerUnitTest() {
		String strinput = "_if(A=10){} _if(function(){return A;},function(){ }); for(var i=0; i<10; i++){ _if(a=10){ this._return(); } } _for( a=0 ){ } _if(){ this._return(); } _elseif(A=10){ } _else{ this._return(); }";
		CTokenizer ctokenizer = new CTokenizer();
		this.assertTrue(ctokenizer != null);
		ctokenizer.addTokenType("comment1", "/*", "*/");
		ctokenizer.addTokenType("comment2", "//",  "\n"); 
		ctokenizer.addTokenType("comment3", "//",  "\0");
		ctokenizer.addTokenType("_if", "_if", "");
		ctokenizer.addTokenType("_elseif", "_elseif", "");
		ctokenizer.addTokenType("_else", "_else", "");
		ctokenizer.addTokenType("_while", "_while", "");
		ctokenizer.addTokenType("(", "(", "");
		ctokenizer.addTokenType(",", ",", "");
		ctokenizer.addTokenType(";", ";", "");
		ctokenizer.addTokenType(")", ")", "");
		ctokenizer.addTokenType("{", "{", "");
		ctokenizer.addTokenType("}", "}", "");
		ctokenizer.addTokenType("[", "[", "");
		ctokenizer.addTokenType("]", "]", "");
	
		this.assertTrue(ctokenizer.getNumOfTokenTypes() == 15);
		this.assertTrue(ctokenizer.getNumOfTokens() != 70);
		this.assertTrue(ctokenizer.create(strinput) == true);
		this.assertTrue(ctokenizer.getNumOfTokens() == 71);
		this.assertTrue(ctokenizer.getInput().equals(strinput));
	} // end testCTokenizerUnitTest()	
} // end CTokenizerUnitTest