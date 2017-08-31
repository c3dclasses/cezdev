//-------------------------------------------------------
// name: CMessageBox.java
// desc: defines an CMessageBox prompt for the user
//-------------------------------------------------------

// includes
import javax.swing.JOptionPane;
import cglobal.*;

//------------------------------------------------------------------------
// name: CMessageBox
// desc: the main application of CEnvironmentVariables
//------------------------------------------------------------------------
public class CTest {				
	// main entry point of the application
	public static void main(String[] args) {
String strinput = "_if(A=10){} _if(function(){return A;},function(){ }); for(var i=0; i<10; i++){ _if(a=10){ this._return(); } } _for( a=0 ){ } _if(){ this._return(); } _elseif(A=10){ } _else{ this._return(); }";
		CTokenizer ctokenizer = new CTokenizer();
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
	
		System.out.println(""+ctokenizer.getNumOfTokenTypes());
		ctokenizer.create(strinput);
		
		System.out.println(ctokenizer.toStringTokens());
		System.out.println(""+ctokenizer.getNumOfTokens());
			
		//this.assertTrue(ctoken1.toString(strinput) == "_if");
	} // end main()
} // end CMessageBox
