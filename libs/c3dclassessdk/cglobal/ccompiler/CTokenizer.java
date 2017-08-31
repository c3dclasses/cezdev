///---------------------------------------------------------------------------
// file: CTokenizer
// desc: responsible converting the file into tokens 
//---------------------------------------------------------------------------
package cglobal;
import c3dclasses.ccore.*;

//--------------------------------------------------------
// name: CTokenizer
// desc: tokenizer object
//--------------------------------------------------------
public class CTokenizer {
	public String m_strinput;
	public CArray m_ctokens;
	public CArray m_ctokentypes;
	
	public CTokenizer() {
		this.m_strinput = "";
		this.m_ctokens = new CArray();
		this.m_ctokentypes = new CArray(); 
	} // end CTokenizer()
	
	public CTokenizer addTokenType(String strtype, String strbegin, String strend) {
		this.m_ctokentypes.push(new CTokenType(strtype, strbegin, strend));
		return this;
	} // end addTokenType()

	public boolean create(String strinput) {
		if(strinput == null || strinput == "")
			return false;  	
		int length = strinput.length();
		this.m_strinput = strinput;
		int s = 0; 
		int i = 0;
		int index = 0;
		CToken ctoken = null;
		CHash data = null;
		while(i<length) {
			if((data=(CHash)this.buildToken(i)) != null) {
				System.out.println("type: " + data.get("type"));
				System.out.println("epos: " + data.get("epos"));
				if(s < i) {	
					System.out.println("s: " + s);
					System.out.println("i: " + i);
					ctoken = new CToken(s, i, this.m_ctokens.length(), (String) data.get("type"));
					if(!ctoken.toString(strinput).trim().equals("")) // skip space tokens
						this.m_ctokens.push(ctoken);
				} // end if	
				ctoken = new CToken(i, Integer.valueOf((Integer)data.get("epos")), this.m_ctokens.length(), (String) data.get("type"));
				if(!ctoken.toString(strinput).trim().equals(""))	// skip space tokens
					this.m_ctokens.push(ctoken);
				s = i = Integer.valueOf((Integer)data.get("epos"));	
			} // end if
			else i++;
			System.out.println("");	
		} // end while
		if(s < length) {
			ctoken = new CToken(s, length, this.m_ctokens.length(),(String) data.get("type"));
			if(!ctoken.toString(strinput).trim().equals("")) // skip space tokens
				this.m_ctokens.push(ctoken);
		} // end if
		return true;
	} // end create()
	
	public CHash buildToken(int spos) {
		if(this.m_ctokentypes == null || spos < 0)
			return null;
		int epos = -1;
		int spos2 = -1;
		String type = "";
		for(int i=0; i<this.m_ctokentypes.length(); i++) {
			CTokenType ctokentype = (CTokenType)this.m_ctokentypes.get(i);
			int length = ctokentype.m_strbegin.length();
			try {		
					if(length > 0 && this.m_strinput.substring(spos, spos + length).equals(ctokentype.m_strbegin)) {
						type = ctokentype.m_strtype;
						if(ctokentype.m_strend.equals("\n")) {	
							epos = i + length;
							break;			
						} // end if
						else if(ctokentype.m_strend.equals("\0")) { //PHP_EOF) {
							epos = this.m_strinput.length();
							break;	
						} // end if
						else if((spos2 = this.m_strinput.indexOf(ctokentype.m_strend, spos + length)) > -1) {
							epos = spos2 + ctokentype.m_strend.length();
							break;
						} // end if
					} // end if	
			} // end try
			catch(Exception ex) {
				CLog.error(ex.toString());
				continue;
			}
		} // end foreach()		
		if(epos == -1 || type == null)
			return null;
		CHash chash = new CHash();
		chash.set("epos", epos);
		chash.set("type", type);	
		return chash;	
	} // end buildToken()
	
	public int getNumOfTokens() {
		return (this.m_ctokens != null) ? this.m_ctokens.length() : 0;
	} // end getNumOfTokens()
	
	public int getNumOfTokenTypes() {
		return (this.m_ctokentypes != null) ? this.m_ctokentypes.length() : 0;
	} // end getNumOfTokenTypes()
	
	public CArray getTokens() { 
		return this.m_ctokens;
	} // end getTokens()
	
	public CArray getTokenTypes() { 
		return this.m_ctokentypes;
	} // end getTokenTypes()
	
	public String getInput() {
		return this.m_strinput;
	} // end getInput()
	
	public String toStringTokens() {
		String str = "";
		CToken ctoken = null;
		for(int i=0; i<this.m_ctokens.length(); i++) {
			ctoken = (CToken)this.m_ctokens.get(i);
			str += ctoken.m_index + ". (" + ctoken.m_strtype  + ") . " + ctoken.toString(this.m_strinput) + "\n";
		} // end for
		return str;
	} // end toStringTokens()
	
	public String toStringTokenTypes() {
		String str = "";
		CTokenType ctokentype = null;
		for(int i=0; i<this.m_ctokentypes.length(); i++) {
			ctokentype = (CTokenType)this.m_ctokentypes.get(i);
			str += i +". " + ctokentype.toString() + "\n";
		} // end for
		return str;
	} // end toStringTokenTypes()
} // end CTokenizer