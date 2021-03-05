//---------------------------------------------------------------------------
// name: CRemoteVariableTranspiler
// desc:
//---------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;

//--------------------------------------------------------
// name: CRemoteVariableTranspiler
// desc:
//--------------------------------------------------------
public class CRemoteVariableTranspiler {
	static String input;
	public static boolean transpile(String strinput, String stroutput) {
		CCompiler ccompiler = new CCompiler();
		if(ccompiler == null)
			return false;
		
		// get the tokenizer object
		CTokenizer ctokenizer = ccompiler.getTokenizer();
		if(ctokenizer == null)
			return false;
		
		// add non terminals (non tokens)
		if(!CRemoteVariableTranspiler.addNonTokensFunctions())
			return false;
		
		// add tokens
		ctokenizer.addTokenType("comment1-begin", "/*");
		ctokenizer.addTokenType("comment1-end", "*/");
		ctokenizer.addTokenType("comment2-begin", "//");
		ctokenizer.addTokenType("_remote", "_remote");
		ctokenizer.addTokenType("delete", "delete");
		ctokenizer.addTokenType("newline", "\n");
		ctokenizer.addTokenType("eof", "\0");
		ctokenizer.addTokenType("at", "@");
		ctokenizer.addTokenType("less-than", "<");
		ctokenizer.addTokenType("greater-than", ">");
		ctokenizer.addTokenType("left-parens", "(");
		ctokenizer.addTokenType("right-parens", ")");
		ctokenizer.addTokenType("comma", ",");
		ctokenizer.addTokenType("semicolon", ";");
		ctokenizer.addTokenType("equals", "=");
		
		// save the tokentypes to a csv file
		CArray tt = ctokenizer.getTokenTypes(); 
		CArray csv = __.carray();
		csv.push(__.a("Type", "Character"));
		for(int i=0; i<tt.length(); i++)
			csv.push(__.a(tt._chash(i)._string("m_strtype"), tt._chash(i)._string("m_strtoken")));
		__.save_csv_file("C:/Users/developer/Desktop/st-final-project/tokentypes.csv", csv);
		
		// create the compiler object
		if(ccompiler.create(__.file_get_contents(strinput)) == false)
			return false;
		
		CRemoteVariableTranspiler.input = ctokenizer.getInput();
		
		csv.clear();
		CArray t = ctokenizer.getTokens();
		csv.push(__.a("Index", "Type", "Character"));
		for(int i=0; i<t.length(); i++) {
			CNode token = (CNode) t._(i);
			String str = token.toString(ctokenizer.getInput()).trim();
			str = str.replace(",", "comma");
			if(str.equals(""))
				continue;
			csv.push(__.a(i, str, token._string("m_strname").replace(",","comma".trim())));
		} // end for
		
		// get the parser object
		CParser cparser = ccompiler.getParser();
		if(cparser == null)
			return false;
		
		// parse the source file
		if(cparser.parse(__.cfunction("BODY")) == null)
			return false;
		
		CNode root = cparser.getParseTree();
		String ast = CRemoteVariableTranspiler.buildAST(root);
		__.file_set_contents("C:/Users/developer/Desktop/st-final-project/ast.txt",ast);
		
		root = cparser.getParseTree();
		ast = CRemoteVariableTranspiler.buildTAST(root);
		__.file_set_contents("C:/Users/developer/Desktop/st-final-project/translated-ast.txt",ast);
		
		// translate the file and write output to current directory
		return __.file_set_contents(stroutput, cparser.translate());
	} // end transpile()
	
	public static String buildAST(CNode cnode) {
		if(cnode == null)
			return "";		
		String str = "[";
		str += cnode._string("m_strname");
		CArray cnodes = cnode.getCNodes();
		if(cnodes != null) {
			for(int i=0; i<cnodes.length(); i++) {
				str += CRemoteVariableTranspiler.buildAST((CNode)cnodes._(i));
			} // end for
		} // end if		
		return str + "]";
	} // end buildAST()
	
	public static String buildTAST(CNode cnode) {
		if(cnode == null)
			return "";		
		String str = "[";
		String t = "";
		if(!cnode._string("m_strtranslation").trim().equals(""))
			t = " " + cnode._string("m_strtranslation");
		str += cnode._string("m_strname") + t;
		CArray cnodes = cnode.getCNodes();
		if(cnodes != null) {
			for(int i=0; i<cnodes.length(); i++) {
				str += CRemoteVariableTranspiler.buildTAST((CNode)cnodes._(i));
			} // end for
		} // end if		
		else {
			str += cnode.toString(CRemoteVariableTranspiler.input); 
		}
		return str + "]";
	} // end buildTAST()
	
	public static boolean addNonTokensFunctions() {		
		//----------------------------------------------------------------------------------
		// name: BODY()
		// desc: BODY := ( [STOP|DONE] | REMOTE_CREATE | REMOTE_READ_UPDATE | REMOTE_DELETE | . ) BODY
		//----------------------------------------------------------------------------------
		CFunction BODY = new CFunction("BODY") {
			public CReturn call(CArray args) {
				CParser cparser = (CParser) args._(0);
				String stopToken = args._string(1);
				CNode pnode = new CNode("BODY");
				while(true) {	
					
					if((stopToken != "" && cparser.check(stopToken)) || cparser.done())	// [STOP|DONE] 
						break;
					
					CNode cnode = cparser.acceptNonToken(__.cfunction("REMOTE_CREATE")); // REMOTE_CREATE
					if(cnode != null) { 
						pnode.add(cnode);
						continue;
					} // end if
					
					cnode = cparser.acceptNonToken(__.cfunction("REMOTE_DELETE")); // REMOTE_DELETE
					if(cnode != null) { 
						pnode.add(cnode);
						continue;
					} // end if
					
					cnode = cparser.acceptNonToken(__.cfunction("REMOTE_READ_UPDATE")); // REMOTE_READ_UPDATE
					if(cnode != null) { 
						pnode.add(cnode);
						continue;
					} // end if
					
					cparser.acceptToken(); // everything else .
				} // end 
				return CReturn._done(pnode);
			} // end _()
		}; // end BODY

		//----------------------------------------------------------------------------
		// name: REMOTE_CREATE()
		// desc: REMOTE_CREATE := _remote( PARAMS ) @ IDENTIFIER 
		//----------------------------------------------------------------------------
		CFunction REMOTE_CREATE = new CFunction("REMOTE_CREATE") { 
			public CReturn call(CArray args) {
				CParser cparser = (CParser) args._(0);
				String terminateToken = args._string(1);
				CNode pnode = new CNode("REMOTE_CREATE");
				String str = cparser.getInputString();
		
				if(!cparser.accept("_remote"))	// _remote
					return CReturn._done(null);	
					
				CNode nodeRemote = cparser.token(-1);
				pnode.add(nodeRemote);
				if(!cparser.accept("<"))	// (
					return CReturn._done(null);
				pnode.add(cparser.token(-1).setTranslation("("));
					
				CNode cnode = cparser.acceptNonToken(__.cfunction("PARAMS"), ">", false); // BODY - terminate at last ) token			
				if(cnode != null)
					pnode.add(cnode);
				
				if(!cparser.accept(">"))	
					return CReturn._done(null);
				pnode.add(cparser.token(-1).setTranslation(")"));
				
				if(!cparser.accept("@"))	
					return CReturn._done(null);	
				pnode.add(cparser.token(-1).setTranslation(""));
				
				cnode = cparser.acceptNonToken(__.cfunction("IDENTIFIER"), __.args(")", "\n", "\t", ";"), false); // BODY - terminate at last } token			
				if(cnode == null)
					return CReturn._done(null);
				String id = cnode.toString(str);
				nodeRemote.setTranslation("CVar " + id.trim() + " = new CVar");
				pnode.add(cnode.setTranslation(""));
				
				return CReturn._done(pnode);
			} // end _()
		}; // end REMOTE_CREATE()
		
		//----------------------------------------------------------------------------
		// name: REMOTE_READ_UPDATE()
		// desc: REMOTE_READ_UPDATE := @ IDENTIFIER [; | = LSIDE] 
		//----------------------------------------------------------------------------
		CFunction REMOTE_READ_UPDATE = new CFunction("REMOTE_READ_UPDATE") { 
			public CReturn call(CArray args) {
				CParser cparser = (CParser) args._(0);
				String terminateToken = args._string(1);
				CNode pnode = new CNode("REMOTE_READ");
				String str = cparser.getInputString();
		
				if(!cparser.accept("@"))
					return CReturn._done(null);	
				pnode.add(cparser.token(-1).setTranslation(""));
			
				if(cparser.acceptNonToken(__.cfunction("IDENTIFIER"), __.args(")", ",", "\n", "\t", ";", "="), false) == null) // BODY - terminate at last } token			
					return CReturn._done(null);
				CNode cnode = cparser.token(-1);
				String id = cnode.toString(str);
				cnode.setTranslation(id.trim());
				
				if(!cparser.accept("=")) {
					pnode.add(cnode.setTranslation(id.trim() + ".retrieve()"));			
					return CReturn._done(pnode);
				} // end if		
				pnode._("m_strname", "REMOTE_UPDATE");
				pnode.add(cnode);			
				cnode = cparser.token(-1);
				pnode.add(cnode.setTranslation(".update("));			
				
				// LSIDE
				if(cparser.acceptNonToken(__.cfunction("IDENTIFIER"), __.args(")", ",", "\n", "\t", ";"), false) == null) // BODY - terminate at last } token			
					return CReturn._done(null);
				cnode = cparser.token(-1);
				String id2 = cnode.toString(str);
				pnode.add(cnode.setTranslation(id2.trim() + ")"));			
				return CReturn._done(pnode);
			} // end _()
		}; // end REMOTE_READ_UPDATE()
		
		//----------------------------------------------------------------------------
		// name: REMOTE_DELETE()
		// desc: REMOTE_DELETE := delete @ IDENTIFIER 
		//----------------------------------------------------------------------------
		CFunction REMOTE_DELETE = new CFunction("REMOTE_DELETE") { 
			public CReturn call(CArray args) {
				CParser cparser = (CParser) args._(0);
				String terminateToken = args._string(1);
				CNode pnode = new CNode("REMOTE_DELETE");
				String str = cparser.getInputString();
			
				if(!cparser.accept("delete"))
					return CReturn._done(null);	
				CNode node = cparser.token(-1);
				pnode.add(node);
	
				if(!cparser.accept("@"))
					return CReturn._done(null);	
				pnode.add(cparser.token(-1).setTranslation(""));
			
				CNode cnode = cparser.acceptNonToken(__.cfunction("IDENTIFIER"), __.args(")", ",", "\n", "\t", ";"), false); // BODY - terminate at last } token			
				if(cnode == null)
					return CReturn._done(null);
				
				String id = cnode.toString(str);
				node.setTranslation(id.trim() + ".delete()");
				pnode.add(cnode.setTranslation(""));
				
				return CReturn._done(pnode);
			} // end _()
		}; // end REMOTE_DELETE()
		
		//----------------------------------------------------------------------------
		// name: IDENTIFIER()
		// desc: IDENTIFIER := ( [STOP|DONE] | . ) IDENTIFIER
		//----------------------------------------------------------------------------	
		CFunction IDENTIFIER = new CFunction("IDENTIFIER") { 
			public CReturn call(CArray args) {
				CParser cparser = (CParser) args._(0);
				CArray stopTokens = args._carray(1);
				CNode pnode = null;
				String str = cparser.getInputString();
				while(true) {	
					if((stopTokens != null && cparser.check(stopTokens)) || cparser.done())	// [STOP|DONE] 
						break;
					cparser.acceptToken(); // everything else .
					pnode = cparser.token(-1);		
				} // end while
				return CReturn._done(pnode);
			} // end call()
		}; // end IDENTIFIER
		
		//----------------------------------------------------------------------------
		// name: PARAMS()
		// desc: PARAMS := ( [STOP|DONE] | BODY | . ) PARAMS
		//----------------------------------------------------------------------------
		CFunction PARAMS = new CFunction("PARAMS") { 
			public CReturn call(CArray args) {
				CParser cparser = (CParser) args._(0);
				String stopToken = args._string(1);
				CNode pnode = new CNode("PARAMS");
				while(true) {	
					if((stopToken != "" && cparser.check(stopToken)) || cparser.done())	// [STOP|DONE] 
						break;
					cparser.acceptToken(); // everything else .
					//pnode.add(cparser.token(-1));
				} // end 
				return CReturn._done(pnode);
			} // end call()
		}; // end PARAMS
		return true;
	} // end addNonTokensFunctions()
} // end CRemoteVariableTranspiler