//--------------------------------------------------
// file: __UnitTest
// desc:
//--------------------------------------------------
package c3dclasses;
import org.junit.Test;

//--------------------------------------------------------
// name: __UnitTest
// desc:
//--------------------------------------------------------
public class __UnitTest extends CUnitTest {
	@Test 
	public void test() {
		String strpath = __.dir_path(this);
		String inputfile1 = strpath + "/input.txt";
		this.assertTrue(__.time() <= __.time());
		this.assertTrue(__.get_time_mms() <= __.get_time_mms());
		this.assertTrue(__.hash_code(__.carray()) != __.hash_code(__.chash()));
		this.assertTrue(__.parse_double("10.00") == 10.00);
		//this.assertTrue(__.parse_double("kevin") != 10.00);
		this.assertTrue(__.split(" ", "hi my name is kevin").toString().equals("[hi, my, name, is, kevin]"));
		this.assertTrue(__.explode(" ", "hi my name is kevin").toString().equals("[hi, my, name, is, kevin]"));
		//this.assertTrue(__.exec_command("dir C:") != null);
		this.assertTrue(__.dirname(strpath + "/__UnitTest.java").equals(strpath));
		this.assertTrue(__.is_directory(strpath));
		this.assertTrue(__.is_file(strpath + "/__UnitTest.java"));
		this.assertTrue(__.set_file_contents(inputfile1, "Testing\ntesting\n1\n2\n3..."));
		this.assertTrue(__.get_file_contents(inputfile1).equals("Testing\ntesting\n1\n2\n3..."));
		this.assertTrue(__.append_file_contents(inputfile1, "\n4"));
		this.assertTrue(__.get_file_contents(inputfile1).equals("Testing\ntesting\n1\n2\n3...\n4"));
		this.assertTrue(__.file_exists(inputfile1));
		this.assertTrue(__.get_lines_from_file(inputfile1).length() == 6);
		this.assertTrue(__.file_copy(inputfile1, strpath + "/copied-file.txt"));
		this.assertTrue(__.file_delete(inputfile1));
		this.assertTrue(__.file_exists(inputfile1) == false);
		this.assertTrue(__.file_delete(strpath + "/copied-file.txt"));
		this.assertTrue(__.eval("5+Math.sqrt(4)").equals("7.0"));
		this.assertTrue(__.eval("5+Mh.sqrt(4)").indexOf("Exception") != -1);
		
	} // end test()
} // end __UnitTest