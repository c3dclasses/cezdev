//---------------------------------------------------------------------------
// name: ccompiler.php
// desc: defines a compiler object
//---------------------------------------------------------------------------
package cglobal;
import c3dclasses.ccore.*;
// includes
//include_once("ctokenizer.php");
//include_once("cparser.php");

//--------------------------------------------------------
// name: CComplier
// desc: the compiler object
//--------------------------------------------------------
public class CCompiler {
	public CTokenizer m_ctokenizer = null;
	public CParser m_cparser = null;
	
	public CCompiler() {
		this.m_cparser = null;
		this.m_ctokenizer = new CTokenizer();
	} // end CCompiler
	
	public boolean create(String strinput) {
		// create the tokenizer
		if(this.m_ctokenizer == null || !this.m_ctokenizer.create(strinput))
			return false;
		// create the parser
		this.m_cparser = new CParser();
		if(this.m_cparser == null || !this.m_cparser.create(this.m_ctokenizer))
			return false;
		return true;
	} // end create()
	
	public CParser getParser() {
		return this.m_cparser;
	} // end getParser()

	public CTokenizer getTokenizer() {
		return this.m_ctokenizer;
	} // end getTokenizer()

	public String getInput() {
		return (this.m_ctokenizer != null) ? this.m_ctokenizer.getInput() : "";
	} // end getInput()
} // end CCompiler