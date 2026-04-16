//---------------------------------------------------------------------------
// file: CParser
// desc: 
//---------------------------------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------
// name: CParser 
// desc: 
//---------------------------------------------------------------------
public class CParser {
	public CTokenizer m_ctokenizer;
	public CArray m_ctokens;
	public int m_ictokenindex;
	public CNode m_parsetree;
	public String m_strinput;
	
	public CParser() {
		this.m_ctokenizer = null;
		this.m_ctokens = null;
		this.m_parsetree = null;
		this.m_ictokenindex = -1;
		this.m_strinput = "";
	} // end CParser()
	
	public String getInputString() { return this.m_strinput; }
	
	public boolean create(CTokenizer ctokenizer) {
		if(ctokenizer != null && ctokenizer.getTokens().length() < 1)
			return false;
		this.m_ctokenizer = ctokenizer;
		this.m_ctokens = ctokenizer.getTokens();
        this.m_ictokenindex = 0;
		this.m_strinput = this.m_ctokenizer.getInput();
		return true;
	} // end create()
	
	public boolean done() {
		return (this.m_ctokenizer == null || this.m_ictokenindex >= this.m_ctokenizer.getTokens().length());
	} // end done()

	public boolean accept(String strtokentype) {
		return this.acceptToken(strtokentype);
	} // end accept()
	
	public boolean acceptToken() {
		return this.acceptToken(null);
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
		
	public CNode acceptTokenBlock(String strtokentypebegin, CFunction callbacknontoken, String strtokentypeend) {
		if(!this.accept(strtokentypebegin))
			return null;
		CNode nontoken = this.acceptNonToken(callbacknontoken, strtokentypeend, false);
		if(nontoken == null || !this.accept(strtokentypeend))
			return null;
		return nontoken;
	} // end acceptTokenBlock()
	
	public CNode acceptNonToken(CFunction callbackNonToken) {
		return this.acceptNonToken(callbackNonToken, "", false);
	} // end acceptNonToken()
	
	public CNode acceptNonToken(CFunction callbackNonToken, String strterminationtokentype, boolean brollbackonfailure) {
		if(callbackNonToken == null) 
			return null;
		int itokenindex = this.m_ictokenindex;
		CNode ret = (CNode) callbackNonToken.call(__.args(this, strterminationtokentype)).data();
		if(ret == null) {
			if(brollbackonfailure == true)
				this.m_ictokenindex = itokenindex;
			return ret;
		} // end if
		CNode ctokenbefore = (CNode) this.m_ctokens.get(itokenindex - 1);
		if(ctokenbefore != null)
		 	ret._("m_spos", ctokenbefore._int("m_epos"));	
		CNode ctokenafter = (CNode) this.m_ctokens.get(this.m_ictokenindex);
		if(ctokenafter != null) 
			ret._("m_epos", ctokenafter._int("m_spos"));
		return ret;
	} // end acceptNonToken()
	
	public CNode acceptNonToken(CFunction callbackNonToken, CArray strterminationtokentypes, boolean brollbackonfailure) {
		if(callbackNonToken == null) 
			return null;
		int itokenindex = this.m_ictokenindex;
		CNode ret = (CNode) callbackNonToken.call(__.args(this, strterminationtokentypes)).data();
		if(ret == null) {
			if(brollbackonfailure == true)
				this.m_ictokenindex = itokenindex;
			return ret;
		} // end if
		CNode ctokenbefore = (CNode) this.m_ctokens.get(itokenindex - 1);
		if(ctokenbefore != null)
		 	ret._("m_spos", ctokenbefore._int("m_epos"));	
		CNode ctokenafter = (CNode) this.m_ctokens.get(this.m_ictokenindex);	
		if(ctokenafter != null) 
			ret._("m_epos", ctokenafter._int("m_spos"));
		return ret;
	} // end acceptNonToken()
	
	public int getTokenIndex() {
		return this.m_ictokenindex;
	} // end getTokenIndex()
	
	public CNode getCurToken() {
		return (CNode) this.m_ctokens.get(this.m_ictokenindex);
	} // end getCurToken()
	
	public void setTokenIndex(int ictokenindex) {
		this.m_ictokenindex = ictokenindex;
	} // end getTokenIndex()
	
	public CNode getParseTree() {
		return this.m_parsetree;
	} // end getParseTree()	
	
	public CNode token(int prevnextindex) {
		CNode ctoken = null;
		return (this.m_ctokens != null && ((ctoken = (CNode)this.m_ctokens.get(this.m_ictokenindex + prevnextindex)) != null)) ? ctoken : null; 
	} // end token()
	
	public String tokenToString(int prevnextindex) {
		CNode ctoken = null;
		return ((ctoken = this.token(prevnextindex)) == null) ? "" : ctoken.toString(this.m_ctokenizer.getInput());
	} // end token()
	
	public boolean check(String strtokentype) {
		CNode ctoken = (CNode)this.m_ctokens.get(this.m_ictokenindex);
		return (!this.done() && strtokentype != null && ctoken != null && (ctoken._string("m_strtype").equals(strtokentype)));
	} // end check()	

	public boolean check(CArray strtokentypes) {
		if(strtokentypes != null)
			for(int i=0; i<strtokentypes.length(); i++)
				if(this.check(strtokentypes._string(i)))
					return true;
		return false;
	} // end check()	
	
	public CNode parse(CFunction callback) {		
		return this.parse(callback, "");
	} // end parse()
	
	public CNode parse(CFunction callback, String strstoptoken) {		
		this.m_parsetree = this.acceptNonToken(callback, strstoptoken, false);
		if(this.m_parsetree != null) {
			this.m_parsetree._("m_spos", 0);
			this.m_parsetree._("m_epos", this.m_strinput.length());
		} // end if
		return this.m_parsetree;
	} // end parse()

	protected int m_index;
	public String translate() {	
		this.m_index = 0;
		String str = this.translate_rec(this.m_parsetree);
		int length = this.m_strinput.length() - this.m_index;
		str += (length <= 0 || this.m_strinput == "" || this.m_strinput == null) ? "" : this.m_strinput.substring(this.m_index, this.m_index+length);
		return str;
	} // translate()
	
	public String translate_rec(CNode cnode) {		
		String str="";
		if(cnode == null)
			return str;		
		if(cnode.getCNodes() == null) {
			str += cnode.toStringBeforeToken(this.m_strinput,this.m_index);
			str += cnode.toStringTranslation();
			this.m_index = cnode.getEndPos();
			return str;
		} // end if
		CArray cnodes = null;
		if((cnodes=cnode.getCNodes()) != null) {
			for(int i=0; i<cnodes.length(); i++) {
				str += this.translate_rec((CNode)cnodes._(i));
			} // end for
		} // end if
		return str;
	} // end translate_rec()
	
	public CArray getCNodes(String strname) {
		return this.m_parsetree.getCNodes(strname);
	} // end getCNodes()
} // end CParser