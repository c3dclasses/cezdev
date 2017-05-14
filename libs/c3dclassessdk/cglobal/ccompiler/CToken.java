//---------------------------------------------------------------------------
// file: CToken
// desc: defines the token object found in the file 
//---------------------------------------------------------------------------
package cglobal;

//--------------------------------------------------------
// name: CToken
// desc: token object
//--------------------------------------------------------
public class CToken {
	public int m_spos = -1;	// start position of token in the string input
	public int m_epos = -1;	// end position of token in the string input
	public String m_strtype = ""; // type of token (i.e. comment, _if, etc ..);
	public int m_index = -1;	// the index of this token within the array of tokens
	public String m_strtranslation = "";	// the translation value of this token
	
	public CToken(int spos, int epos, int index, String strtype) {
		if(spos<0 || epos<0)
			return;
		this.m_spos = spos;
		this.m_epos = epos;
		this.m_index = index;
		this.m_strtype = strtype;
	} // end CToken()
		
 	public String toString(String strinput) { 
		return (strinput == "" || (this.m_epos - this.m_spos) <= 0) ? 
		"" : strinput.substring(this.m_spos, this.m_epos);
	} // end toString()
	
	public String toStringBeforeToken(String strinput, int ioffsetbefore) {
		return (strinput == "" || (this.m_spos - ioffsetbefore) <= 0) ? 
		"" : strinput.substring(this.m_spos - ioffsetbefore, this.m_spos);
	} // toStringBeforeToken();
	
	public String toStringAfterToken(String strinput, int ioffsetafter) {
		return (strinput == "" || (this.m_epos + ioffsetafter) >= strinput.length()) ? 
		"" : strinput.substring(this.m_epos, this.m_epos + ioffsetafter);
	} // toStringAfterToken();

	public String toStringTranslation() {
		 return this.m_strtranslation;
	} // toStringTranslation();
	
	public int getIndex(){
		return this.m_index;
	} // end getIndex()
	
	public int getStartPos(){
		return this.m_spos;
	} // end getStartPos()

	public int getEndPos(){
		return this.m_epos;
	} // end getEndPos()

	public CToken setTranslation(String strtranslation) {
		this.m_strtranslation = strtranslation;
		return this;
	} // end setTranslation()
	
	public String getTranslation(String strtranslation) {
		return this.m_strtranslation;
	} // end getTranslation()
	
	public CToken appendTranslation(String strappendtranslation) {
		this.m_strtranslation += strappendtranslation;
		return this;
	} // end appendTranslation
} // end CToken