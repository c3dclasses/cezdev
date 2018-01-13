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
	protected CGraphAdjacency m_cadjacency;
	public CGraph() { this.m_cadjacency = new CGraphAdjacencyList(); }
	public CGraph(CGraphAdjacency cadjacency) { this.m_cadjacency = cadjacency; }
	public CGraphAdjacency _adj() { return m_cadjacency; } 
	public String toString() { return ""; }
} // end CGraph 

