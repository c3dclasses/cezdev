//---------------------------------------------------------------------------
// file: CNode
// desc:
//---------------------------------------------------------------------------
package c3dclasses;

//--------------------------------------------------------
// name: CNode
// desc:
//--------------------------------------------------------
public class CNode extends CHash {
	public CNode(int spos, int epos, int index, String strname, int itype) {
		if(spos<0 || epos<0)
			return;
		this._("m_spos", spos);
		this._("m_epos", epos);
		this._("m_index", index);
		this._("m_strtype", strname);
		this._("m_strname", strname);
		this._("m_itype", itype);
		this._("m_cnodes", null);
		this._("m_strtranslation", strname); // translate node
		this._("m_bskip", false); 			 // skip node
		this._("m_write", "");				 // write node to file
	} // end CToken()
	
	public CNode(String strname) {
		this._("m_spos", -1);
		this._("m_epos", -1);
		this._("m_index", -1);		
		this._("m_strtype", strname);
		this._("m_strname", strname);
		this._("m_itype", -1);	
		this._("m_cnodes", _.carray());
		this._("m_strtranslation", strname); // translate node
		this._("m_bskip", false); 		// skip node
		this._("m_write", "");			// write node to file
	} // end CNode()
		
 	public String toString(String strinput) { 
		return (strinput == "" || (this._int("m_epos") - this._int("m_spos")) < 0) ? 
		"" : strinput.substring(this._int("m_spos"), this._int("m_epos"));
	} // end toString()
	
	public String toStringBeforeToken(String strinput, int ioffsetbefore) {
		return (strinput == "" || (this._int("m_spos") - ioffsetbefore) <= 0) ? 
		"" : strinput.substring(ioffsetbefore, this._int("m_spos"));
	} // toStringBeforeToken();
	
	public String toStringAfterToken(String strinput, int ioffsetafter) {
		return (strinput == "" || (this._int("m_epos") + ioffsetafter) >= strinput.length()) ? 
		"" : strinput.substring(this._int("m_epos"), this._int("m_epos") + ioffsetafter);
	} // toStringAfterToken();

	public String toStringTranslation() {
		//if(this._string("m_strname").equals("UNNAMED-TOKEN"))
			
		return this._string("m_strtranslation");
	} // toStringTranslation();

	public String getName(){
		return this._string("m_strname");
	} // end getIndex()
	
	public int getIndex(){
		return this._int("m_index");
	} // end getIndex()
	
	public int getStartPos(){
		return this._int("m_spos");
	} // end getStartPos()

	public int getEndPos(){
		return this._int("m_epos");
	} // end getEndPos()
	
	public CNode setTranslation(String strtranslation) {
		this._("m_strtranslation", strtranslation);
		return this;
	} // end setTranslation()
	
	public String getTranslation(String strtranslation) {
		return this._string("m_strtranslation");
	} // end getTranslation()
	
	public CNode appendTranslation(String strappendtranslation) {
		this._("m_strtranslation", this._string("m_strtranslation") + strappendtranslation);
		return this;
	} // end appendTranslation()
	
	public void add(CNode cnode) {
		this._carray("m_cnodes").push(cnode);
	} // end add()
	
	public boolean skip() {
		 return this._boolean("m_bskip");
	} // skip()
	
	public CArray getCNodes() {
		return this._carray("m_cnodes");
	} // end getCNodes()

	public CArray getCNodes(String strname) {
		CArray retnodes = _.carray();
		if(this._string("m_strname").equals(strname))
			retnodes.push(this);
		CArray cnodes = this.getCNodes();
		if(cnodes == null)
			return retnodes;
		for(int i=0; i<cnodes.length(); i++) {
			CNode cnode = (CNode) cnodes._(i);
			retnodes.append(cnode.getCNodes(strname));
		} // end for
		return retnodes;	
	} // end getCNode()
} // end CNode