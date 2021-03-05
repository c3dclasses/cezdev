//---------------------------------------------------------------------------
// name: CCompiler
// desc: 
//---------------------------------------------------------------------------
package c3dclasses;

//--------------------------------------------------------
// name: CComplier
// desc:
//--------------------------------------------------------
public class CCompiler {
	public CTokenizer m_ctokenizer = null;
	public CParser m_cparser = null;
	
	public CCompiler() {
		this.m_cparser = new CParser();
		this.m_ctokenizer = new CTokenizer();
	} // end CCompiler()
	
	public boolean create(String strinput) {
		return (this.m_ctokenizer != null && this.m_ctokenizer.tokenize(strinput) &&
			this.m_cparser != null && this.m_cparser.create(this.m_ctokenizer));
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