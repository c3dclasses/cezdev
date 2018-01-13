//-----------------------------------------------------------------------------------------
// file: CGraphAdjacency
// desc: defines the abstract adjacency object of the Graph
//-----------------------------------------------------------------------------------------
package c3dclasses;
import java.util.*;

//-------------------------------------------------------------
// file: CGraphAdjacency
// desc: defines the abstract adjacency object of the Graph
//-------------------------------------------------------------
public class CGraphAdjacency {	
	public CGraphAdjacency() {} 
	public int addVertex(Object vertexdata) { return -1; } 
	public int addEdge(int isrc, int idst, Object edgedata){ return -1; }
} // end CGraphAdjacency