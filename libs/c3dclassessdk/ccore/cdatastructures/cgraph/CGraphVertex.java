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
class CGraphVertex {
	Object m_data;
	public CGraphVertex(Object data) { this.m_data = data; }
	public Object _() { return m_data; }		
	public String toString() { return this.m_data.toString(); }
} // end CGraphVertex