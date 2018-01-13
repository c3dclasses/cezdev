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
class CGraphEdge {
	public Object m_data;
	public CGraphVertex m_cvertex1;
	public CGraphVertex m_cvertex2;
	public CGraphEdge(CGraphVertex v1, CGraphVertex v2, Object data) {
		this.m_data = data;
		this.m_cvertex1 = v1;
		this.m_cvertex2 = v2;
	} // end CGraphEdge()	
	public String toString() { 
		return "m_data=" + this.m_data.toString() + "; " +
			   "m_cvertex1=" + this.m_cvertex1.toString() + "; " +
			   "m_cvertex2=" + this.m_cvertex2.toString();
	} // end toString()
} // end CGraphEdge