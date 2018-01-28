//---------------------------------------------------------------------------
// name: ccompiler.php
// desc: defines a compiler object
//---------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;

//--------------------------------------------------------
// name: CComplier
// desc: the compiler object
//--------------------------------------------------------
public class CCompilerUnitTest extends CUnitTest {
	@Test
	public void test() {
		String strinput = _.file_get_contents("C:/Users/developer/Desktop/cezdev2/libs/c3dclassessdk/ccore/cnlp/ccompiler/source-test.js");
		String stroutput = _.file_get_contents("C:/Users/developer/Desktop/cezdev2/libs/c3dclassessdk/ccore/cnlp/ccompiler/translation-test.js");
		
		CCompiler ccompiler = new CCompiler();
		this.assertTrue(ccompiler != null);
		
		// add tokens
		CTokenizer ctokenizer = ccompiler.getTokenizer();
		this.assertTrue(ctokenizer != null);
		
		// add non tokens
		this.assertTrue(this.addNonTokensFunctions() == true);		
		
		ctokenizer.addTokenType("comment1-begin", "/*");
		ctokenizer.addTokenType("comment1-end", "*/");
		ctokenizer.addTokenType("comment2-begin", "//");
		ctokenizer.addTokenType("_runtime", "_runtime");
		ctokenizer.addTokenType("newline", "\n");
		ctokenizer.addTokenType("eof", "\0");
		ctokenizer.addTokenType("{", "{");
		ctokenizer.addTokenType("}", "}");	
		
		this.assertTrue(ccompiler.create(strinput) == true);
		
		CParser cparser = ccompiler.getParser();
		this.assertTrue(cparser != null);
		
		this.assertTrue(ctokenizer.getTokenTypes().length() == 8);
		this.assertTrue(ctokenizer.getTokens().length() == 45);
		this.assertTrue(cparser.parse(_.cfunction("BODY")) != null);		
		this.assertTrue(cparser.translate().equals(stroutput) == true);	
		
		CArray cnodes = cparser.getCNodes("BODY");
		this.assertTrue(cnodes.length() == 6);				
	} // end test
	
	//////////////////////////////////////////////
	
	//---------------------------------------------------------------------
	// name: addNonTokensFunctions() 
	// desc: 
	//---------------------------------------------------------------------
	public boolean addNonTokensFunctions() {
		//----------------------------------------------------------------------------------
		// name: BODY()
		// desc: BODY := ( [STOP|DONE] | _RUNTIME | . ) BODY
		// _runtime { /* code */ }
		//----------------------------------------------------------------------------------
		CFunction BODY = new CFunction("BODY") {
			public CArray _(CArray args) {
				CParser cparser = (CParser) args._(0);
				String stopToken = args._string(1);
				CNode pnode = new CNode("BODY");
				while(true) {	
					if((stopToken != "" && cparser.check(stopToken)) || cparser.done())	// [STOP|DONE] 
						break;
					CNode cnode = cparser.acceptNonToken(_.cfunction("_RUNTIME")); // _RUNTIME
					if(cnode != null) { 
						pnode.add(cnode);
						continue;
					} // end if
					cparser.acceptToken(); // .
				} // end 
				return _.args(pnode);
			} // end _()
		}; // end BODY
		
		//----------------------------------------------------------------------------
		// name: _RUNTIME()
		// desc: _RUNTIME := _runtime { BODY } 
		//----------------------------------------------------------------------------
		CFunction _RUNTIME = new CFunction("_RUNTIME") { 
			public CArray _(CArray args) {
				CParser cparser = (CParser) args._(0);
				String terminateToken = args._string(1);
				CNode pnode = new CNode("_RUNTIME");
				if(!cparser.accept("_runtime"))	// _runtime
					return _.args();
				pnode.add(cparser.token(-1).setTranslation("_runtime()"));
				if(!cparser.accept("{"))	// { 
					return _.args();
				pnode.add(cparser.token(-1).setTranslation("{"));
				CNode cnode = cparser.acceptNonToken(_.cfunction("BODY"), "}", false); // BODY - terminate at last } token			
				if(cnode != null)
					pnode.add(cnode);
				if(!cparser.accept("}"))	// }
					return _.args();
				pnode.add(cparser.token(-1).setTranslation("}"));
				return _.args(pnode);
			} // end _()
		}; // end _RUNTIME()
		return true;
	} // end addGrammer()
} // end CCompiler