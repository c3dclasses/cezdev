///---------------------------------------------------------------------------
// file: CTokenizer
// desc: 
//---------------------------------------------------------------------------
package c3dclasses;

//--------------------------------------------------------
// name: CTokenizer
// desc: 
//--------------------------------------------------------
public class CTokenizer {
	public String m_strinput;
	public CArray m_cnodes;
	public CArray m_cnodetypes;
	
	public CTokenizer() {
		this.m_strinput = "";
		this.m_cnodes = new CArray();
		this.m_cnodetypes = new CArray(); 
	} // end CTokenizer()

	public boolean tokenize(String strinput) {
		if(strinput == null || strinput == "")
			return false;  	
		int length = strinput.length();
		this.m_strinput = strinput;
		CNode cnode = null;
		int i = 0;
		while(i<length) {
			if((cnode = this.getToken(i)) != null) {
				this.m_cnodes.push(cnode);			
				i = cnode._int("m_epos");
			} // end if
			else i++;
		} // end for
		return true;		
	} // end create()
	
	public CTokenizer addTokenType(String strtype, String strtoken) {
		this.m_cnodetypes.push(_.chash("m_strtype", strtype, "m_strtoken", strtoken, "m_index", this.m_cnodetypes.length()));
		return this;
	} // end addTokenType()
	
	public CArray getTokenTypes() { 
		return this.m_cnodetypes;
	} // end getTokenTypes()
	
	public CArray getTokens() { 
		return this.m_cnodes;
	} // end getTokens()
	
	public String getInput() {
		return this.m_strinput;
	} // end getInput()

	public String toString() {
		String str = m_cnodetypes.toJSON(true);
		str += m_cnodes.toJSON(true);
		return str;
	} // end toString()
	
	// helper	
	public CNode getToken(int index) {
		for(int tokentype=0; tokentype<this.m_cnodetypes.length(); tokentype++) {
			CHash cnodetype = this.m_cnodetypes._chash(tokentype);
			String strtoken = cnodetype._string("m_strtoken");
			int length = strtoken.length();
			if(length > 0 && this.m_strinput.length() > index + length && this.m_strinput.substring(index, index + length).equals(strtoken))
				return new CNode(index, index + length, this.m_cnodes.length(), strtoken, cnodetype._int("m_index"));
		} // end for
		return null;
	} // end getToken()
} // end CTokenizer