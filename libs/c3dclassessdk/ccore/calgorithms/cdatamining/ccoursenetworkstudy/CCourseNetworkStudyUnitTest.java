//--------------------------------------------------
// file: CCourseNetworkStudy
// desc: defines the datamining unit test
//--------------------------------------------------
package c3dclasses;
import org.junit.Test;
import org.junit.Ignore;

//--------------------------------------------------------
// name: CCourseNetworkStudy
// desc:
//--------------------------------------------------------
public class CCourseNetworkStudyUnitTest extends CUnitTest {
	static CHash group_by_student_id(CArray csv) {
		CHash Ts = _.chash();
		for(int i=1; i<csv.length(); i++) {
			String id = csv._carray(i)._string(0);
			if(Ts._(id) == null)
				Ts._(id, _.carray());
			CArray T = Ts._carray(id);
			T.push(csv._(i));
		} // end for
		return Ts;
	} // end group_by_student_id()
	
	static String build_graph(CHash Transcipts, CHash Graph) {		
		CArray Transcript_ids = Transcipts.keys();			
		String str=""; 
		for(int t=0; t<Transcript_ids.length(); t++) {
			String id = Transcript_ids._string(t);
			CArray Transcript = Transcipts._carray(Transcript_ids._(t));
			for(int c1=0; c1<Transcript.length(); c1++) {
				for(int c2=0; c2<Transcript.length(); c2++) {
					String C1 = Transcript._carray(c1)._string(25);
					String C2 = Transcript._carray(c2)._string(25);
					int semester1 = Transcript._carray(c1)._int(1);
					int semester2 = Transcript._carray(c2)._int(1);
					if(semester1 < semester2) {
						str += "graph["+C1+", "+C2+", "+"("+ semester1 + "<" + semester2 + ")] += 1\n";
						if(Graph._(C1) == null) {
							Graph._(C1, _.chash());
						} // end if
						CHash Course1 = Graph._chash(C1);
						if(Course1._(C2) == null)
							Course1._(C2, 0);
						Course1._(C2, Course1._int(C2)+1);
					} // end if 
				} // end for
			} // end for
			_.println("Transcript " + id + " has been processed.");
		} // end for
		return str;
	} // end build_graph()
	
	static String build_graph(CArray Transcript, CHash Graph) {		
		String str = "";
		for(int c1=0; c1<Transcript.length(); c1++) {
			for(int c2=0; c2<Transcript.length(); c2++) {
				String C1 = Transcript._carray(c1)._string(26);
				String C2 = Transcript._carray(c2)._string(26);
				int semester1 = Transcript._carray(c1)._int(1);
				int semester2 = Transcript._carray(c2)._int(1);
				if(semester1 < semester2) {
					str += "graph["+C1+", "+C2+", "+"("+ semester1 + "<" + semester2 + ")] += 1\n";
					if(Graph._(C1) == null) {
						Graph._(C1, _.chash());
					} // end if
					CHash Course1 = Graph._chash(C1);
					if(Course1._(C2) == null)
						Course1._(C2, 0);
					Course1._(C2, Course1._int(C2)+1);
				} // end if 
			} // end for
		} // end for
		return str;
	} // end build_graph()

	public void save_transcripts(CHash Transcripts) {
		CArray Transcript_ids = Transcripts.keys();			
		for(int t=0; t<Transcript_ids.length(); t++) {
			CArray Transcript = Transcripts._carray(Transcript_ids._(t));
			_.save_csv_file(_.dir_path(this) + "/t/t_" + t + ".txt", Transcript);
		} // end for
	} // end save_transcipts()
	
	//public void 
	
	@Ignore
	@Test	
	public void test() throws Exception {
		//String strfilename = _.dir_path(this) + "/TUGC1617.csv";
		//CArray csv = _.load_csv_file(strfilename);
		//CHash Ts = CCourseNetworkStudyUnitTest.group_by_student_id(csv);
		//CHash graph = _.chash();
		//build_graph(Ts, graph);
		//_.file_set_contents(_.dir_path(this) + "/graph3.txt", graph.toJSON(true));
		
		//_.alert(_.dir_path(this) + "/graph3.txt");
		CHash graph = _.json_file_2_chash(_.dir_path(this) + "/graph3.json");
		//_.alert(graph._chash("IMLAMP")._int("100"));
		CArray csv = _.carray();
		CArray cids = graph.keys();
		for(int i=0; i<cids.length(); i++) {
			String c1 = cids._string(i);
			CArray cids2 = graph._chash(c1).keys();
			for(int j=0; j<cids2.length(); j++) {
				String c2 = cids2._string(j);
				int w = graph._chash(c1)._int(c2);
				csv.push(_.a(c1,c2,w));
			} // end for
		} // end for
		_.save_csv_file(_.dir_path(this) + "/graph.csv", csv);
		
		
		//String str = _.file_get_contents(_.dir_path(this) + "/graph3.json");
		//_.file_set_contents(_.dir_path(this) + "/graph-check.json", str);
		
		/*
		
		if(graph == null) {
			_.alert("parse issue");
		}
		
		_.println(graph);
		
	
		CArray cids = graph.keys();
	
		_.println(cids);
	*/
	
		/*
		CArray csv = _.carray();
		for(int i=0; i<cids.length(); i++) {
			CArray cids2 = cids._chash(i).keys();
			for(int j=0; j<cids2.length(); j++) {
				String c1 = cids._string(i);
				String c2 = cids2._string(j);
				int w = graph._chash(c1)._int(c2);
				csv.push(_.a(c1,c2,w));
			} // end for
		} // end for
		
		_.save_csv_file(_.dir_path(this) + "/graph.csv", csv);
		*/
		
		
		//CHash graph = _.chash();
		//build_graph(Ts._carray("7147770759"), graph);
		//graph.toJSONFile(_.dir_path(this) + "/graph2.txt");
		//CHash graph = _.json_2_chash(_.dir_path(this) + "/graph2.txt")
		//_.file_set_contents(_.dir_path(this) + "/graph.txt", Graph.toJSON(false));
		//graph.toJSONFile("json-file.json");
		//CHash graph = _.chash("json-file");
		//CHash 
		//this.save_transcripts(Ts);
		//_.alert(Ts._carray("7147770759")._carray(0)._string(3));
		//_.file_set_contents(_.dir_path(this) + "/graph.txt", build_graph(Ts));
	} // end test()
} // end CCourseNetworkStudy