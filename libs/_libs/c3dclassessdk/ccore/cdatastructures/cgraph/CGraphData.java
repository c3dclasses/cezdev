//-----------------------------------------------------------------------------------------
// file: CGraphData
// desc: defines the abstract adjacency object that store the data of the Graph
//-----------------------------------------------------------------------------------------
package c3dclasses;
import java.util.*;

//-----------------------------------------------------------------------------------------------
// file: CGraphData
// desc: defines the adjacency object of the Graph that abstractly stores the vertex and edges
//		 and defines the basic crud operations on this data of these objects
//-----------------------------------------------------------------------------------------------
public class CGraphData {	
	/////////////////
	// members
	protected CArray m_vertices; 	// stores the vertex data
	protected CArray m_edges; 		// stores the edge data
	
	////////////////////
	// constructor
	public CGraphData() { this.init(__.carray(), __.carray()); } 
	public CGraphData(CArray vertices) { this.init(vertices, __.carray()); } 
	public CGraphData(CArray vertices, CArray edges) { this.init(vertices, edges); } 

	///////////////////////////////////////
	// crud - vertices
	public boolean addVertex(Object vertexdata) { 
		if(this.m_vertices == null || this.m_edges == null)
			return false; 
		this.m_vertices.push(vertexdata);  
		this.m_edges.push(__.carray());
		return true;		
	} // end addVertex()
	public boolean removeVertex(int iindex) { 
		if(this.m_vertices == null || this.m_edges == null || this.getVertexData(iindex) == null)
			return false; 
		this.setVertexData(iindex, null);	// remove this vertex neighbors
		CArray edges = this.getEdges(iindex); // disconnect its neighbors
		edges.clear();
		// remove any connections this vertex has with other vertices
		int n = this.getEdges().length();
		for(int i=0; i<n; i++) {
			this.removeEdge(i, iindex);
		} // end for
		return true; 
	} // end removeVertex()	
	public Object getVertexData(int iindex) { return this.m_vertices._(iindex); }
	public void setVertexData(int iindex, Object vertexdata) { this.m_vertices._(iindex, vertexdata); }
	public CArray getVertices() { return this.m_vertices; }
	public CArray getVertexIndices(int vindex) {
		CArray edges = this.getEdges(vindex);
		int n = edges.length();
		CArray vindices = __.carray();
		for(int i=0; i<n; i++) {
			CArray edge = edges._carray(i);
			vindices.push(edge._(0));
		} // end for()
		return vindices;
	} // end getVertexIndices()
	
	//////////////////////////////////
	// crud - edges
	public boolean addEdge(int vindex1, int vindex2, Object edgedata) { 
		if(this.getVertexData(vindex2) == null)
			return false;
		if(this.getEdge(vindex1, vindex2) != null)
			return true;	
		CArray edges = this.getEdges(vindex1);
		if(edges == null)
			return false;
		edges.push(__.carray(vindex2, edgedata));
		return true; 
	} // end addEdge()
	public boolean removeEdge(int vindex1, int vindex2) {
		CArray edges = this.getEdges(vindex1);
		if(edges == null)
			return false;
		int n = edges.length();
		for(int i=0; i<n; i++) {
			CArray edge = edges._carray(i);
			if(edge != null && edge._int(0) == vindex2) {
				edges.removeAt(i);
				return true;
			} // end if
		} // end for 
		return false;
	} // end removeEdge()
	public Object getEdgeData(int vindex1, int vindex2) { CArray edge = this.getEdge(vindex1, vindex2); return (edge != null) ? edge._(1) : null; }
	public boolean setEdgeData(int vindex1, int vindex2, Object edgedata) { 
		CArray data = (CArray) this.getEdge(vindex1, vindex2);
		if(data == null)
			return false;
		data._(1, edgedata);
		return true; 
	} // end setEdgeData()
	public CArray getEdges() { return this.m_edges; }
	public CArray getEdges(int vindex) { return this.m_edges._carray(vindex); }
	public CArray getEdge(int vindex1, int vindex2) { 
		CArray edges = this.getEdges(vindex1);
		if(edges == null)
			return null;
		int n = edges.length();
		for(int i=0; i<n; i++) {
			CArray edge = edges._carray(i);
			if(edge._int(0) == vindex2)
				return edge;
		} // end for
		return null; 
	} // end getEdge()
	
	/////////////////////
	// toString
	public String toString() { return "V:" + this.m_vertices.toString() + "\n" +  "E:" + this.m_edges.toString() + "\n"; }

	//////////////////////
	// helper function
	protected void init(CArray vertices, CArray edges) { 
		this.m_vertices = vertices; 
		this.m_edges = edges; 
		while(this.m_edges.length() < this.m_vertices.length())
			this.m_edges.push(__.carray());
	} // end init()
} // end CGraphData