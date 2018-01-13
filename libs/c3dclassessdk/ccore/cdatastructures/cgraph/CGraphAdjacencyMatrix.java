//-----------------------------------------------------------------------------------------
// file: CGraphAdjacency
// desc: defines the abstract adjacency object of the Graph
//-----------------------------------------------------------------------------------------
package c3dclasses;
import java.util.*;

//-------------------------------------------
// class CGraphAdjacencyMatrix
// desc: creates an adjacency matrix object
//-------------------------------------------
class CGraphAdjacencyMatrix extends CGraphAdjacency {	
	protected CArray m_edges;
	protected CArray m_vertices; 	// array of vertex data
	
	public CGraphAdjacencyMatrix() { 
		this.m_edges = new CArray();
		this.m_vertices = new CArray(); 
	} // end CAdjacencyMatrix()
	
	public int addVertex(Object data) {
		this.m_vertices.push(data);		
		this.m_edges.push(new CArray()); // add a new row
		int nrows = this.m_edges.length();
		for(int i=0; i<nrows; i++)	// for each row add a new column
			this.m_edges.push(new CArray());
		return this.m_vertices.length()-1;
	} // end addVertex()
	
	public int addEdge(int isrc, int idst, Object data) {
		this.edge(isrc, idst, data);
		
		return 0;	
	} // addEdge()
	
	public void vertex(int ivertex, Object data) { this.m_vertices._(ivertex, data); }                                                          	
	public Object vertex(int ivertex) { return this.m_vertices._(ivertex); }                                                          	
	public Object edge(int isrc, int idst) { CArray row = this.m_edges._carray(isrc); return (row != null) ? row._(idst) : null; }
	public void edge(int isrc, int idst, Object data) { CArray row = this.m_edges._carray(isrc); if(row == null) return; row._(idst, data); return; }
} // end CGraphAdjacencyMatrix