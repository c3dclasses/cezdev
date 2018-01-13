//---------------------------------------------------------------------------
// file: cparsertreenode.php
// desc: parses the tokens of the input
//---------------------------------------------------------------------------
package c3dclasses;

//-----------------------------------------------------
// name: CParseTreeNode
// desc: defines a parse tree node
//-----------------------------------------------------
class CParseTreeNode {
	public CToken m_ctoken;	// ctoken object - leaf node
	public CArray m_cparsetreenodes; // children nodes
	public String m_strname;	// name of the node
	
	public CParseTreeNode(String strname) {
		this.m_cparsetreenodes = null;
		this.m_strname = strname;
		this.m_ctoken = null;
	} // end CParseTreeNode()
	
	public void add(CToken ctoken) {
		this.m_ctoken = ctoken;
	} // end add()
	
	public void add(CParseTreeNode cparsetreenode) {
		this.m_cparsetreenodes.push(cparsetreenode);
	} // end add()

	public CArray getParseTreeNodes() {
		return this.m_cparsetreenodes;
	} // end getParseTreeNode()

	public CToken getToken() {
		return this.m_ctoken;
	} // end getToken()
	
	public String getName() {
		return this.m_strname;
	}  // end getName()
} // end CParseTreeNode