//---------------------------------------------------------------------------
// name: CUnitTest
// desc: the unit test object
//---------------------------------------------------------------------------
package cglobal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import c3dclasses.ccore.*;

//--------------------------------------------------------
// name: CUnitTest
// desc: the unit test object
//--------------------------------------------------------
public class CUnitTest {
	public void assertTrue(boolean condition) {
		assertEquals(condition, true); 
	} // end assertTrue()
} // end CUnitTest