//---------------------------------------------------------------------------------
// file: CLogUnitTest
// desc: defines a object to log information, warnings and errors
//---------------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;

//---------------------------------------------------------------------------------
// name: CLogUnitTest
// desc: defines a object to log information, warnings and errors
//---------------------------------------------------------------------------------
public class CLogUnitTest extends CUnitTest {	
	@Test
	public void test() {
		CLog.error("my error message1");
		CLog.warn("my warn message1");
		CLog.info("my info message1");
		this.assertTrue(CLog.error().equals("ERROR: my error message1\n") == true);
		this.assertTrue(CLog.warn().equals("WARN: my warn message1\n") == true);
		this.assertTrue(CLog.info().equals("INFO: my info message1\n") == true);
		
		CLog.error("my error message1");
		CLog.warn("my warn message1");
		CLog.info("my info message1");
		CLog.error("my error message2");
		CLog.warn("my warn message2");
		CLog.info("my info message2");
		this.assertTrue(CLog.error().equals("ERROR: my error message1\nERROR: my error message2\n") == true);
		this.assertTrue(CLog.warn().equals("WARN: my warn message1\nWARN: my warn message2\n") == true);
		this.assertTrue(CLog.info().equals("INFO: my info message1\nINFO: my info message2\n") == true);	
	} // end test()
} // end CLogUnitTest