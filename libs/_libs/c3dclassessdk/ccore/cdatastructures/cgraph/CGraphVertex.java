//-----------------------------------------------------------------------------------------
// file: CGraphVertex
// desc: defines an vertex object of a graph
//-----------------------------------------------------------------------------------------
package c3dclasses;
import java.util.*;

//-------------------------------------------
// name: CGraphVertex
// desc: defines an vertex object of a graph
//-------------------------------------------
class CGraphVertex extends CObject {
	// members
	protected CGraph m_cgraph;
	protected int m_index;
	
	///////////////////////
	// constructor
	public CGraphVertex(CGraph cgraph, int vindex) { this.setCGraph(cgraph); this.setIndex(vindex); }
	
	///////////////////
	// vertex data
	public void setData(Object data) { this.m_cgraph.getCGraphData().setVertexData(this.m_index, data); }
	public Object getData() { return this.m_cgraph.getCGraphData().getVertexData(this.m_index); }
	
	///////////////////
	// vertex index
	public void setIndex(int index) { this.m_index = index; }
	public int getIndex() { return this.m_index; }
	
	////////////////////
	// vertex edges
	public CArray getCGraphEdges() { return this.m_cgraph.getCGraphEdges(this.m_index); }
	
	////////////////////
	// vertex vertices
	public CArray getCGraphVertices() { return this.m_cgraph.getCGraphVertices(this.m_index); }
	public CArray getVertexIndices() { return this.m_cgraph.getCGraphData().getVertexIndices(this.m_index); }

	////////////////
	// graph
	public void setCGraph(CGraph cgraph) { this.m_cgraph = cgraph; }
	public CGraph getCGraph() { return this.m_cgraph; }
	
	////////////////
	// toString
	public String toString() { return "(" + this.m_index + ")=>[" + this.getData() + ":" + super.toString() + "]"; }
} // end CGraphVertex