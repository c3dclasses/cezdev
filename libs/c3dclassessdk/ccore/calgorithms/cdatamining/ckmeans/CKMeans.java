//--------------------------------------------------
// file: CKMeans
// desc: 
//--------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------
// name: CKMeans
// desc: 
//---------------------------------------------------------------------
public class CKMeans {
	protected int m_ik;	
	protected int m_iiterations;	
	protected CMatrix m_matdataset;	
	
	public void k(int k) { this.m_ik=k; }
	public int k() { return this.m_ik; }
	public void iterations(int i) { this.m_iiterations=i; }
	public int iterations() { return this.m_iiterations; }
	public CMatrix dataset(CMatrix matdataset) { return this.m_matdataset = m_matdataset; }
	public CMatrix dataset() { return this.m_matdataset; }
	
	
} // end CKMeans
