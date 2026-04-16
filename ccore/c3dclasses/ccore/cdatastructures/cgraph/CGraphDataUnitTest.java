//-------------------------------------------------------------------
// file: CGraphDataUnitTest
// desc:
//-------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;
import org.junit.Ignore;

//--------------------------------------------------------
// name: CGraphDataUnitTest 
// desc:
//--------------------------------------------------------
public class CGraphDataUnitTest extends CUnitTest {
	@Test	
	public void test() throws Exception {
		CGraphData cgraphdata = new CGraphData();
		this.assertTrue(cgraphdata != null);
		this.assertTrue(cgraphdata.addVertex("0") == true);
		this.assertTrue(cgraphdata.addVertex("1") == true);
		this.assertTrue(cgraphdata.addVertex("2") == true);
		this.assertTrue(cgraphdata.addVertex("3") == true);
		this.assertTrue(cgraphdata.addVertex("4") == true);
		this.assertTrue(cgraphdata.addVertex("5") == true);
		
		this.assertTrue(cgraphdata.addEdge(0,1,"a") == true);
		this.assertTrue(cgraphdata.addEdge(0,2,"b") == true);
		this.assertTrue(cgraphdata.addEdge(1,2,"c") == true);
		this.assertTrue(cgraphdata.addEdge(1,3,"d") == true);
		this.assertTrue(cgraphdata.addEdge(1,4,"e") == true);
		this.assertTrue(cgraphdata.addEdge(2,6,"f") == false);
		this.assertTrue(cgraphdata.addEdge(2,7,"g") == false);
		this.assertTrue(cgraphdata.addEdge(3,4,"h") == true);
		this.assertTrue(cgraphdata.addEdge(4,5,"i") == true);
		this.assertTrue(cgraphdata.addEdge(6,7,"j") == false);
		__.println(cgraphdata.toString());
		
		this.assertTrue(cgraphdata.removeEdge(0,1) == true);
		this.assertTrue(cgraphdata.removeEdge(1,4) == true);
		this.assertTrue(cgraphdata.removeEdge(1,1) == false);
		__.println(cgraphdata.toString());
		
		this.assertTrue(cgraphdata.removeVertex(0) == true);
		
		this.assertTrue(cgraphdata.getVertexData(0) == null);
		this.assertTrue(cgraphdata.getVertexData(1) != null);
		this.assertTrue(((String)cgraphdata.getVertexData(2)).equals("2"));
		this.assertTrue(((String)cgraphdata.getVertexData(3)).equals("3"));
		this.assertTrue(((String)cgraphdata.getVertexData(4)).equals("4"));
		this.assertTrue(((String)cgraphdata.getVertexData(5)).equals("5"));
		cgraphdata.setVertexData(1, "New Vertex Data");
		__.println(cgraphdata.toString());
		
		this.assertTrue(((String)cgraphdata.getVertexData(1)).equals("New Vertex Data"));
		String data = (String) cgraphdata.getEdgeData(1,2);
		this.assertTrue(data != null);
		this.assertTrue(data.equals("c"));
		cgraphdata.setEdgeData(1,2,"New Edge Data");
		data = (String) cgraphdata.getEdgeData(1,2);
		this.assertTrue(data != null);
		this.assertTrue(data.equals("New Edge Data"));
	} // end test()
} // end CGraphDataUnitTest 