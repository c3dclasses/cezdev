//---------------------------------------------------------------------------
// file: CTokenType
// desc: defines the type of tokens to find 
//---------------------------------------------------------------------------
package cglobal;

//--------------------------------------------------------
// name: CTokenType
// desc: defines the type of tokens to find
//--------------------------------------------------------
public class CTokenType {
	public String m_strbegin;	// beginining of the token 
	public String m_strend;		// end of the token
	public String m_strtype; 	// the type of token (i.e. comment, _if, brace, etc.) 
	
	public CTokenType(String strtype, String strbegin, String strend) {
		this.m_strtype = strtype;
		this.m_strbegin = strbegin;
		this.m_strend = strend;
	} // end CTokenType()
	
	public String toString() {
		String str = "(" + this.m_strtype;
		if(this.m_strbegin != null) 
			str +=  "," + this.m_strbegin;
		if(this.m_strend != null)
			str +=  "," + this.m_strend;
		str += ")";
		return str;		
	} // end toString()
} // end CTokenType