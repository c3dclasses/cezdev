//-------------------------------------------------------------------
// file: CGraphUnitTest
// desc:
//-------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;
import org.junit.Ignore;

//--------------------------------------------------------
// name: CGraphUnitTest 
// desc:
//--------------------------------------------------------
public class CGraphUnitTest extends CUnitTest {
	@Test	
	public void test() throws Exception {
		CGraph cgraph = new CGraph();
		this.assertTrue(cgraph != null);
		this.assertTrue(cgraph.addVertex("0") == true);
		this.assertTrue(cgraph.addVertex("1") == true);
		this.assertTrue(cgraph.addVertex("2") == true);
		this.assertTrue(cgraph.addVertex("3") == true);
		this.assertTrue(cgraph.addVertex("4") == true);
		this.assertTrue(cgraph.addVertex("5") == true);
		
		this.assertTrue(cgraph.addEdge(0,1,"a") == true);
		this.assertTrue(cgraph.addEdge(0,2,"b") == true);
		this.assertTrue(cgraph.addEdge(1,2,"c") == true);
		this.assertTrue(cgraph.addEdge(1,3,"d") == true);
		this.assertTrue(cgraph.addEdge(1,4,"e") == true);
		this.assertTrue(cgraph.addEdge(2,6,"f") == false);
		this.assertTrue(cgraph.addEdge(2,7,"g") == false);
		this.assertTrue(cgraph.addEdge(3,4,"h") == true);
		this.assertTrue(cgraph.addEdge(4,5,"i") == true);
		this.assertTrue(cgraph.addEdge(6,7,"j") == false);
		__.println(cgraph.toString());
		
		this.assertTrue(cgraph.removeEdge(0,1) == true);
		this.assertTrue(cgraph.removeEdge(1,4) == true);
		this.assertTrue(cgraph.removeEdge(1,1) == false);
		this.assertTrue(cgraph.getCGraphEdge(0,1) == null);
		this.assertTrue(cgraph.getCGraphEdge(1,4) == null);
		this.assertTrue(cgraph.getCGraphEdge(1,1) == null);
		__.println(cgraph.toString());
		
		this.assertTrue(cgraph.removeVertex(0) == true);
		this.assertTrue(cgraph.getCGraphVertex(0) == null);
		this.assertTrue(cgraph.getCGraphVertex(1) != null);
		this.assertTrue(((String)cgraph.getCGraphVertex(2).getData()).equals("2"));
		this.assertTrue(((String)cgraph.getCGraphVertex(3).getData()).equals("3"));
		this.assertTrue(((String)cgraph.getCGraphVertex(4).getData()).equals("4"));
		this.assertTrue(((String)cgraph.getCGraphVertex(5).getData()).equals("5"));
		cgraph.getCGraphVertex(1).setData("New Vertex Data");
		this.assertTrue(((String)cgraph.getCGraphVertex(1).getData()).equals("New Vertex Data"));
		__.println(cgraph.toString());
		
		String data = (String) cgraph.getCGraphEdge(1,2).getData();
		this.assertTrue(data != null);
		this.assertTrue(data.equals("c"));
		cgraph.getCGraphEdge(1,2).setData("New Edge Data");
		data = (String) cgraph.getCGraphEdge(1,2).getData();
		this.assertTrue(data != null);
		this.assertTrue(data.equals("New Edge Data"));
	} // end test()
} // end CGraphUnitTest 