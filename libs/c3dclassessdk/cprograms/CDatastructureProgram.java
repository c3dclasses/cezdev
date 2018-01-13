//-------------------------------------------------------
// name: CDatastructureProgram.java
// desc: 
//-------------------------------------------------------
import javax.swing.JOptionPane;
import c3dclasses.*;

public class CDatastructureProgram {				
	public static void main(String[] args) {
		
		/*
		CLinkedList list = new CLinkedList();
		
		// adding tail
		list.addTail("Kevin");
		list.addTail("Joseph");
		list.addTail("Lewis");
		list.addTail("Adashima");
		list.addTail("Oyo");
		_.println(list);
		
		// removing tail
		list.removeTail();
		_.println(list);
		list.removeTail();
		_.println(list);
		list.removeTail();
		_.println(list);
		list.removeTail();
		_.println(list);
		list.removeTail();
		_.println(list);
		list.removeTail();
		_.println(list);
		list.removeTail();
		_.println(list);
		list.removeTail();
		_.println(list);
		
		// adding head
		list.addHead("Kevin");
		list.addHead("Joseph");
		list.addHead("Lewis");
		list.addHead("Adashima");
		list.addHead("Oyo");
		_.println(list);
		
		// removing tail
		list.removeHead();
		_.println(list);
		list.removeHead();
		_.println(list);
		list.removeHead();
		_.println(list);
		list.removeHead();
		_.println(list);
		list.removeHead();
		_.println(list);
		list.removeHead();
		_.println(list);
		list.removeHead();
		_.println(list);
		list.removeHead();
		_.println(list);
				
		// adding at
		list.addHead("Kevin");
		list.addHead("Joseph");
		list.addHead("Lewis");
		list.addHead("Adashima");
		list.addHead("Oyo");
		list.addAt(0, "New1");
		list.addAt(2, "New2");
		list.addAt(4, "New3");
		list.addAt(6, "New4");
		list.addAt(8, "New5");
		_.println(list);
		
		// remove at
		list.removeAt(0);
		_.println(list);
		list.removeAt(list.size()-1);
		_.println(list);
		list.removeAt(4);
		_.println(list);
		list.removeAt(6);
		_.println(list);
		list.removeAt(2);
		_.println(list);
		list.removeAt(2);
		_.println(list);
		list.removeAt(1);
		_.println(list);
		*/
		
		
		CGraph cgraph = new CGraph();
		cgraph._adj().addVertex(new Integer(0));
		cgraph._adj().addVertex(new Integer(1));
		cgraph._adj().addVertex(new Integer(2));
		cgraph._adj().addVertex(new Integer(3));
		cgraph._adj().addVertex(new Integer(4));
		cgraph._adj().addVertex(new Integer(5));
		
		cgraph._adj().addEdge(0, 1, new Integer(5));
		cgraph._adj().addEdge(0, 2, new Integer(5));
		cgraph._adj().addEdge(0, 3, new Integer(5));
		cgraph._adj().addEdge(0, 4, new Integer(5));
		cgraph._adj().addEdge(0, 5, new Integer(5));
		
		_.println(cgraph._adj().toString());
		
		 /* Let us create following weighted graph
             10
        0--------1
        |  \     |
       6|   5\   |15
        |      \ |
        2--------3
            4       */
			
		CGraph cgraph2 = new CGraph();	
		
		// vertices
		cgraph2._adj().addVertex(new Integer(0));
		cgraph2._adj().addVertex(new Integer(1));
		cgraph2._adj().addVertex(new Integer(2));
		cgraph2._adj().addVertex(new Integer(3));
		
		// edges
		cgraph2._adj().addEdge(0, 1, new Integer(10));
		cgraph2._adj().addEdge(0, 2, new Integer(6));
		cgraph2._adj().addEdge(0, 3, new Integer(5));
		cgraph2._adj().addEdge(2, 3, new Integer(4));
		cgraph2._adj().addEdge(1, 3, new Integer(15));
		cgraph2._adj().addEdge(2, 3, new Integer(4));
		
		
		_.println();
		_.println(cgraph2._adj().toString());
		_.println();
		
		//CTree ctree = cgraph2.mst_prim();
		//CTree ctree = cgrahp2.mst_kruskal();
		
		CObject cobject = new CObject();
		
		cobject._("m_foo", "this is a string object");
		_.println(cobject._("m_foo"));
		
		cobject._("m_func", new CFunction() { public CArray _(CArray args) { 
			_.alert("in the function"); 
			return null; 
		}}); // end m_func()
		
		cobject._func("m_func")._((CArray) null);
		
		
	} // end main()
} // end CDatastructureProgram
