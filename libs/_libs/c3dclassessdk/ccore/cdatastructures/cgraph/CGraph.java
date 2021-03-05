//-----------------------------------------------------------------------------------------
// file: CGraph
// desc: defines an graph object
//-----------------------------------------------------------------------------------------
package c3dclasses;
import java.util.*;

//-------------------------------------------
// class CGraph
// desc: creates a graph object
//-------------------------------------------
public class CGraph {
	protected CGraphData m_cgraphdata;
	
	//////////////////////
	// constructors
	public CGraph() { this.m_cgraphdata = new CGraphData(); }
	public CGraph(CArray vertices) { this.m_cgraphdata = new CGraphData(vertices); }
	public CGraph(CGraphData cgraphdata) { this.m_cgraphdata = cgraphdata; }
	
	////////////////////
	// vertices
	public boolean addVertex(Object data) { return this.m_cgraphdata.addVertex(data); }
	public boolean removeVertex(int vindex) { return this.m_cgraphdata.removeVertex(vindex); }
	
	////////////////////
	// CGraphVertex
	public CGraphVertex getCGraphVertex(int vindex) { return (this.m_cgraphdata.getVertexData(vindex)!=null) ? new CGraphVertex(this,vindex) : null; }
	public CArray getCGraphVertices() { 
		CArray vertices = this.m_cgraphdata.getVertices(); 
		if(vertices != null && vertices.length() <= 0)
			return null;
		CArray cvertices = __.carray();
		int n = vertices.length();
		for(int i=0; i<n; i++) {
			CGraphVertex cgraphvertex = this.getCGraphVertex(i);
			if(cgraphvertex != null)
				cvertices.push(cgraphvertex);
		} // end for
		return cvertices;
	} // end getCGraphVertices()
	public CArray getCGraphVertices(int vindex) { 
		CArray vindices = this.m_cgraphdata.getVertexIndices(vindex);
		int l = vindices.length();
		CArray vertices = __.carray();
		for(int i=0; i<l; i++) {
			CGraphVertex v = this.getCGraphVertex(vindices._int(i));
			if(v!=null)
				vertices.push(v);
		} // end for
		return vertices;
	} // end getCGraphVertices()
	
	///////////////////
	// edges
	public boolean addEdge(int vindex1, int vindex2, Object edgedata) { return this.m_cgraphdata.addEdge(vindex1, vindex2, edgedata); } 
	public boolean addUnDirectedEdge(int vindex1, int vindex2, Object edgedata) { 
		return this.addEdge(vindex1, vindex2, edgedata) &&
			this.addEdge(vindex2, vindex1, edgedata);
	} // end addUndirectedEdge()
	public boolean removeEdge(int vindex1, int vindex2) { return this.m_cgraphdata.removeEdge(vindex1, vindex2); }
	public boolean removeUnDirectedEdge(int vindex1, int vindex2) { 
		return this.removeEdge(vindex1, vindex2) && 
			this.removeEdge(vindex2, vindex1); 
	} // end removeUndirectedEdge()
	
	/////////////////////
	// CGraphEdges
	public CGraphEdge getCGraphEdge(int vindex1, int vindex2) { 
		Object data = this.m_cgraphdata.getEdgeData(vindex1,vindex2); 
		return (data != null) ? new CGraphEdge(this,vindex1,vindex2) : null;
	} // end getCGraphEdge()
	public CArray getCGraphEdges(int vindex) { 
		CArray edges =  this.m_cgraphdata.getEdges(vindex);		
		CArray cedges = __.carray();
		int n = edges.length();
		for(int i=0; i<n; i++) {
			CArray edge = edges._carray(i);
			//__.println(edge);
			cedges.push(this.getCGraphEdge(vindex, edge._int(0)));
		} // end for
		return cedges;	
	} // end getCGraphEdges()
	public CArray getCGraphEdges() { 
		CArray edges =  this.m_cgraphdata.getEdges();
		CArray cedges = __.carray();
		int l = edges.length();
		for(int i=0; i<l; i++) {
			CArray vedges = edges._carray(i);
			//__.println(vedges);
			int n = vedges.length();
			for(int j=0; j<n; j++) {
				CArray e = vedges._carray(j);
				cedges.push(this.getCGraphEdge(i, e._int(0)));
			} // end for
		} // end for 
		//__.println();
		return cedges;
	} // end getCGraphEdges()
	
	///////////////
	// data
	public CGraphData getCGraphData() { return this.m_cgraphdata; } 
	
	/////////////////////
	// toString()
	public String toString() { return this.m_cgraphdata.toString(); }
		
		
	///////////////////////
	// traversal
	public CArray BFS(int vindex) {
		CArray Q = __.carray();				 // Create(Q)
		CArray L = __.carray();				 // Create(Levels)
		CGraph T = new CGraph(this.m_cgraphdata.getVertices());
		CArray V = this.getCGraphVertices(); // make all variable undiscovered
		for(int i=0; i<V.length(); i++)		
			((CGraphVertex) V._(i))._("discovered", false);
		Q.push(V._(vindex));			// Last(Q) = s
		L.push(__.carray(V._(vindex)));	// Level(s) = 0
		((CGraphVertex) V._(vindex))._("discovered", true);
		while(!Q.empty()) {
			CGraphVertex v = (CGraphVertex) Q.shift();
			CArray indices = v.getVertexIndices();
			L.push(__.carray());
			CArray L_last = (CArray) L.last();
			int vindex1 = v.getIndex();	
			for(int i=0; i<indices.length(); i++) {
				int vindex2 = indices._int(i);
				CGraphVertex u = (CGraphVertex) V._(vindex2);
				if(!u._boolean("discovered")) {
					u._("discovered", true);	
					Q.push(V._(vindex2));
					L_last.push(V._(vindex2));
					T.addEdge(vindex1, vindex2, "100");
				} // end if
			} // end for
		} // end while
		return __.carray(L,T);
	} // end BFS()
	
	public CArray DFS(int vindex) {
		CArray V = this.getCGraphVertices(); // make all variable unexplored
		for(int i=0; i<V.length(); i++) {
			CGraphVertex v = (CGraphVertex) V._(i);
			v._("explored", false);
		} // end for
		CGraph T = new CGraph(this.m_cgraphdata.getVertices());
		CArray o = __.carray(V,T);
		this.DFS_rec(vindex, o);
		return o;
	} // end DFS()
	
	public void DFS_rec(int vindex, CArray o) {
		CArray V = (CArray) o._(0);
		CGraph T = (CGraph) o._(1);
		CGraphVertex v = (CGraphVertex) V._(vindex);
		v._("explored", true);
		CArray indices = v.getVertexIndices();
		for(int i=0; i<indices.length(); i++) {
			int vindex2 = indices._int(i);
			CGraphVertex u = (CGraphVertex) V._(vindex2);
			if(!u._boolean("explored")) {
				T.addEdge(vindex, vindex2, "100");
				this.DFS_rec(vindex2, o);
			}
		} // end for()
	} // end DFS()
	
	/*
	///////////////////////////
	// shortest path
	public CArray shortestPath(int sindex) {
		CPQueue Q = new CPQueue();
		CArray S = __.carray();	// the set of explored nodes
		CArray V = this.getCGraphVertices(); // make all variable undiscovered
		CGraphVertex s = (CGraphVertex) V._(sindex);
		S.push(V._(s));	//	S={s} 
		s._("d",0);		// 	d(s) = 0
		while(S.length() != V.length()) {
			
		} // end while
	} // end 
	*/
} // end CGraph 

