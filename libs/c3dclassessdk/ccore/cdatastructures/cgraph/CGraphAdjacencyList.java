//-----------------------------------------------------------------------------------------
// file: CGraphAdjacency
// desc: defines the abstract adjacency object of the Graph
//-----------------------------------------------------------------------------------------
package c3dclasses;
import java.util.*;

//-------------------------------------------
// class CGraphAdjacencyList
// desc: creates an adjacency list object
//-------------------------------------------
class CGraphAdjacencyList extends CGraphAdjacency {	
	protected CArray m_adjlist; 	// array of linked list
	protected CArray m_vertices; 	// array of vertex data
	protected CArray m_edges; 		// array of edge data
	
	public CGraphAdjacencyList() { 
		this.m_adjlist = _.carray(); 
		this.m_vertices = _.carray(); 
		this.m_edges = _.carray(); 
	} // end CGraphAdjacencyList()
	
	public int addVertex(Object vertexdata) { 
		if(this.m_vertices == null || this.m_adjlist == null)
			return -1; 
		this.m_vertices.push(new CGraphVertex(vertexdata));  
		this.m_adjlist.push(new CLinkedList());
		return this.m_vertices.length() - 1;		
	} // end addVertex()
	
	public int addEdge(int isrc, int idst, Object edgedata) { 
		if(this.m_vertices == null || this.m_adjlist == null)
			return -1;
		CGraphVertex vsrcdata = (CGraphVertex) this.m_vertices._(isrc);
		CGraphVertex vdstdata = (CGraphVertex) this.m_vertices._(idst);
		CLinkedList vsrclist = (CLinkedList) this.m_adjlist._(isrc);	
		if(vsrcdata == null || vdstdata == null || vsrclist == null)
			return -1;
		CGraphEdge cedge = new CGraphEdge(vsrcdata, vdstdata, edgedata);
		if(cedge == null)
			return -1;
		vsrclist.enqueue(cedge);	
		this.m_edges.push(cedge);
		return this.m_edges.length() - 1;
	} // end addEdge()
	
	public CGraphVertex vertex(int i) { return (CGraphVertex) this.m_vertices._(i); }
	
	public CGraphEdge edge(int i) { return (CGraphEdge) this.m_edges._(i); }
	public CGraphEdge edge(int isrc, int idst) { 
		CLinkedList vsrclist = (CLinkedList) this.m_adjlist._(isrc);		
		CGraphVertex vdstdata = (CGraphVertex) this.m_vertices._(idst);	
		if(vsrclist == null)
			return null;
		for(CLinkedListNode node = vsrclist.begin(); node != null; node = node.next()) {
			CGraphEdge cedge = (CGraphEdge) node.data();
			if(cedge.m_cvertex2 == vdstdata)
				return cedge;
		} // end cedge
		return null;
	} // end edge()
	
	public String toString() {
		return "m_vertices: " + this.m_vertices.toString() + "," + 
		"m_edges: " + this.m_edges.toString() + "," + "m_adjlist: " + this.m_adjlist.toString() + ",";
	} // end toString()
} // end CGraphAdjacencyList
