//---------------------------------------------------------------------------------
// file: CCommandUnitTest
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;
import org.junit.Ignore;

//---------------------------------------------------------------------------------
// name: CCommandUnitTest
// desc: 
//---------------------------------------------------------------------------------
public class CCommandUnitTest extends CUnitTest{
	@Ignore
	@Test
	public void test() {
		//CCommand alert = new CCommand("alert.bat");
		CCommand dos = new CCommand("dos-test.bat");
		CCommand octave = new CCommand("octave-cli.exe", "octave-test.oc");
		CCommand r = new CCommand("Rscript.exe", "r-test.r");
		
		this.assertTrue(dos.call(__.args(CMatrix.rand(3,3), 1, "this is the string", "-l"))._string().contains("ran dos-test.bat from dos-test.bat"));
		this.assertTrue(octave.call(__.args(""))._string() != null);
		__.alert(r.call(__.args("hi"))._string());
		
		
		
		//alert.call(__.args("Testing alert.bat","CCommandUnitTest - alert.bat"));		
	} // end main()
} // end CFunctionMain