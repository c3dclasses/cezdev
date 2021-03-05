//-----------------------------------------------------------------------------------------
// file: CGraphProgram
// desc: 
//-----------------------------------------------------------------------------------------
package c3dclasses;
import java.util.*;

//-------------------------------------------
// class CGraphProgram
// desc: 
//-------------------------------------------
public class CGraphProgram {
	
	public static void main(String [] args) {
		
		__.alert("running the graph program");
		CGraph cgraph = new CGraph();
		
		cgraph.addVertex("0");
		cgraph.addVertex("1");
		cgraph.addVertex("2");
		cgraph.addVertex("3");
		cgraph.addVertex("4");
		cgraph.addVertex("5");
		cgraph.addVertex("6");
		cgraph.addVertex("7");
		
		cgraph.addUnDirectedEdge(0,1,"a");
		cgraph.addUnDirectedEdge(0,2,"b");
		cgraph.addUnDirectedEdge(1,2,"c");
		cgraph.addUnDirectedEdge(1,3,"d");
		cgraph.addUnDirectedEdge(1,4,"e");
		cgraph.addUnDirectedEdge(2,6,"f");
		cgraph.addUnDirectedEdge(2,7,"g");
		cgraph.addUnDirectedEdge(3,4,"h");
		cgraph.addUnDirectedEdge(4,5,"i");
		cgraph.addUnDirectedEdge(6,7,"j");
		
			
		CArray out = cgraph.BFS(0);
		saveArborInputFile(cgraph, "input.json");
		saveArborInputFile((CGraph)out._(1), "input1.json");
		
		out = cgraph.DFS(0);
		saveArborInputFile((CGraph)out._(1), "input5.json");
		
		
		
		cgraph.getCGraphVertices().foreach(new CFunction() {
			public CReturn call(CArray args) {
				int index = args._int(0);
				CGraphVertex cgraphvertex = (CGraphVertex) args._(1);
				Object input = args._(2);
				__.println(index + " : " + cgraphvertex);
				return CReturn._done(0);
			}; // end call()			
		}); // end new CFunction()
		

		CGraph cgraph2 = new CGraph();
		cgraph2.addVertex("0");
		cgraph2.addVertex("1");
		cgraph2.addVertex("2");
		cgraph2.addVertex("3");
		cgraph2.addVertex("4");
		cgraph2.addVertex("5");
		cgraph2.addVertex("6");
		cgraph2.addVertex("7");
		cgraph2.addVertex("8");
		
		cgraph2.addEdge(0,1,4);
		cgraph2.addEdge(0,7,8);
		cgraph2.addEdge(1,2,8);
		cgraph2.addEdge(1,7,11);
		cgraph2.addEdge(2,3,7);
		cgraph2.addEdge(2,8,2);
		cgraph2.addEdge(2,5,4);
		cgraph2.addEdge(3,4,9);
		cgraph2.addEdge(3,5,14);
		cgraph2.addEdge(4,5,10);
		cgraph2.addEdge(5,6,2);
		cgraph2.addEdge(6,7,1);
		cgraph2.addEdge(6,8,6);
		cgraph2.addEdge(7,8,7);
		
		saveArborInputFile(cgraph2, "input3.json");
		
		
		//__.exec_command("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
	} // end main()
	
	//--------------------------------------------------------------
	// name: saveArborInputFile()
	// desc:
	//--------------------------------------------------------------
	public static void saveArborInputFile(CGraph G, String outfilename) {
		String strpath = "C:/Users/gpad/Desktop/cezdev/libs/c3dclassessdk/ccore/cdatastructures/cgraph/cgraph/";
		
		// get the nodes
		CArray V = G.getCGraphVertices();
		CHash nodes = __.chash();
		for(int i=0; i<V.length(); i++) {
			CGraphVertex v = (CGraphVertex) V._(i);
			nodes._(v.getData().toString(), __.chash("color","red","shape","dot","alpha",1));
		} // end for
		
		// get the edges
		CArray E = G.getCGraphEdges();
		CHash edges = __.chash();
		for(int i=0; i<E.length(); i++) {
			CGraphEdge e = (CGraphEdge) E._(i);
			String v1label = e.getCGraphVertex1().getData().toString();
			CHash Vs = edges._chash(v1label);
			if(Vs == null) {
				edges._(v1label, __.chash());
				Vs = edges._chash(v1label);
			} // end if
			String v2label = e.getCGraphVertex2().getData().toString();
			Vs._(v2label, __.chash());	
		} // end for
		
		CHash arbor = __.chash("nodes",nodes,"edges",edges); 
		arbor.toJSONFile(strpath + outfilename);
		
		__.alert("saved the file");
	} // end saveArborInputFile()
} // end CGraphProgram 

 