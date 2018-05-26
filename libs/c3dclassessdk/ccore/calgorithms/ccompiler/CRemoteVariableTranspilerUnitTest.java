//---------------------------------------------------------------------------
// name: CRemoteVariableTranspilerUnitTest
// desc: 
//---------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;
import org.junit.Ignore;

//--------------------------------------------------------
// name: CRemoteVariableTranspilerUnitTest
// desc:
//--------------------------------------------------------
public class CRemoteVariableTranspilerUnitTest extends CUnitTest {
	@Test
	public void test() {	
		String strinfile = _.dir_path(this) + "/CRemoteVariableTest.src.js";
		String stroutfile = _.dir_path(this) + "/CRemoteVariableTest.out.js";
		if(CRemoteVariableTranspiler.transpile(strinfile, stroutfile) == false)
			_.alert("ERROR: in trans-piling.");
	} // end test()
} // end CCompiler