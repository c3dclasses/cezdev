//---------------------------------------------------------------------------
// file: cparser.php
// desc: parses the tokens of the input
//---------------------------------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------
// name: CParser 
// desc: parses the tokens of the input
//---------------------------------------------------------------------
public class CParser {
	public CTokenizer m_ctokenizer;
	public CArray m_ctokens;
	public int m_ictokenindex;
	public CParseTreeNode m_parsetree;
	public String m_strinput;
	
	public CParser() {
		this.m_ctokenizer = null;
		this.m_ctokens = null;
		this.m_parsetree = null;
		this.m_ictokenindex = -1;
	} // end CParser()
	
	public boolean create(CTokenizer ctokenizer) {
		if(ctokenizer != null && ctokenizer.getNumOfTokens() < 1)
			return false;
		this.m_ctokenizer = ctokenizer;
		this.m_ctokens = ctokenizer.getTokens();
        this.m_ictokenindex = 0;
		this.m_strinput = this.m_ctokenizer.getInput();
		return true;
	} // end create()
	
	public boolean done() {
		return (this.m_ctokenizer == null || this.m_ictokenindex >= this.m_ctokenizer.getNumOfTokens());
	} // end done()

	public boolean accept(String strtokentype) {
		return this.acceptToken(strtokentype);
	} // end accept()
	
	public boolean acceptToken(String strtokentype) {
		if(strtokentype == null) {	
			this.m_ictokenindex++;
			return true;
		} // end if
		if(!this.check(strtokentype))
			return false;		
		this.m_ictokenindex++;	// consume
		return true;
	} // end acceptToken()
	
	public CParseTreeNode acceptTokenBlock(String strtokentypebegin, CFunction callbacknontoken, String strtokentypeend) {
		if(!this.accept(strtokentypebegin))
			return null;
		CParseTreeNode nontoken = this.acceptNonToken(callbacknontoken, strtokentypeend, false);
		if(nontoken == null || !this.accept(strtokentypeend))
			return null;
		return nontoken;
	} // end acceptTokenBlock()
	
	public CParseTreeNode acceptNonToken(CFunction callbackNonToken, String strterminationtokentype, boolean brollbackonfailure) {
		if(callbackNonToken == null) 
			return null;
		int itokenindex = this.m_ictokenindex;
		CHash params = new CHash();
		params.set("cparser", this);
		params.set("strterminationtoken", strterminationtokentype);
		CParseTreeNode ret = (CParseTreeNode) callbackNonToken._(params)._(0);
		if(ret == null && brollbackonfailure == true)
			this.m_ictokenindex = itokenindex;
		return ret;
	} // end acceptNonToken()
	
	public int getTokenIndex() {
		return this.m_ictokenindex;
	} // end getTokenIndex()
	
	public void setTokenIndex(int ictokenindex) {
		this.m_ictokenindex = ictokenindex;
	} // end getTokenIndex()
	
	public CParseTreeNode getParseTree() {
		return this.m_parsetree;
	} // end getParseTree()	
	
	public CToken token(int prevnextindex) {
		CToken ctoken = null;
		return (this.m_ctokens != null && ((ctoken = (CToken)this.m_ctokens.get(this.m_ictokenindex + prevnextindex)) != null)) ? ctoken : null; 
	} // end token()
	
	public String tokenToString(int prevnextindex) {
		CToken ctoken = null;
		return ((ctoken = this.token(prevnextindex)) == null) ? "" : ctoken.toString(this.m_ctokenizer.getInput());
	} // end token()
	
	public boolean check(String strtokentype) {
		CToken ctoken = (CToken)this.m_ctokens.get(this.m_ictokenindex);
		return (!this.done() && strtokentype != null && ctoken != null && (ctoken.m_strtype == strtokentype));
	} // end check()	
		
	public CParseTreeNode parse(CFunction callback, String strterminatetoken) {		
		return (this.m_parsetree = this.acceptNonToken(callback, strterminatetoken, false));
	} // end parse()

	public String translate() {	
		int index = 0;
		String str = this.translate_rec(this.m_parsetree, index);
		int length = this.m_strinput.length() - index;
		str += (length <= 0 || this.m_strinput == "" || this.m_strinput == null) ? "" : this.m_strinput.substring(index, index+length);
		return str;
	} // translate()
	
	public String translate_rec(CParseTreeNode cparsetreenode, int index) {		
		String str="";
		if(cparsetreenode != null)
			return str;
		if(cparsetreenode.m_ctoken != null) {
			CToken ctoken = cparsetreenode.m_ctoken;
			str += ctoken.toStringBeforeToken(this.m_strinput, index);
			str += ctoken.toStringTranslation();
			index = ctoken.getEndPos();
			return str; // return the translation
		} // end if
		CArray nodes = null;
		if((nodes=cparsetreenode.getParseTreeNodes()) !=null) {
			for(int i=0; i<nodes.length(); i++) {
				str += this.translate_rec((CParseTreeNode)nodes.get(i), index);
			} // end for
		} // end if
		return str;
	} // end translate_rec()
} // end CParser