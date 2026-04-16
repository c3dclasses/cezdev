//-----------------------------------------------------------------------------------------
// file: CGraphEdge
// desc: defines an edge object of a graph
//-----------------------------------------------------------------------------------------
package c3dclasses;
import java.util.*;

//-------------------------------------------
// name: CGraphEdge
// desc: defines an edge object of a graph
//-------------------------------------------
class CGraphEdge extends CObject {
	
	//////////////////////
	// members
	protected CGraph m_cgraph;
	protected int m_vindex1;
	protected int m_vindex2;
	
	////////////////////
	// constructor
	public CGraphEdge(CGraph cgraph, int vindex1, int vindex2) {
		this.m_cgraph = cgraph;
		this.m_vindex1 = vindex1;
		this.m_vindex2 = vindex2;
	} // end CGraphEdge()

	//////////////////////
	// indices
	public int getVertexIndex1() { return this.m_vindex1; } 
	public int getVertexIndex2() { return this.m_vindex2; } 
	
	//////////////////////
	// vertex
	public CGraphVertex getCGraphVertex1() { return this.m_cgraph.getCGraphVertex(this.m_vindex1); } 
	public CGraphVertex getCGraphVertex2() { return this.m_cgraph.getCGraphVertex(this.m_vindex2); } 
	
	//////////////////////
	// data
	public Object getData() { return this.m_cgraph.getCGraphData().getEdgeData(this.m_vindex1, this.m_vindex2); }
	public Object setData(Object data) { return this.m_cgraph.getCGraphData().setEdgeData(this.m_vindex1, this.m_vindex2, data); }
	
	public CGraph getCGraph() { return this.m_cgraph; }
	public String toString() { return "(" + this.m_vindex1 + "," + this.m_vindex2 + ")=>" + this.getData().toString(); } 
} // end CGraphEdge