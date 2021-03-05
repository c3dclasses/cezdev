//---------------------------------------------------------------------------------------
// file: CResourceUnitTest
// desc: 
//---------------------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;

//---------------------------------------------------------------------------------------
// name: CResourceUnitTest
// desc: 
//---------------------------------------------------------------------------------------
public class CResourceUnitTest extends CUnitTest {
	@Test
	public void test() {
		CResource.include("cfooresource1", "this/is/my/path1", "c3dclasses.CFooResource", null);
		CFooResource cfooresource = (CFooResource) CResource.use("cfooresource1");
		this.assertTrue(cfooresource != null);
		this.assertTrue(cfooresource.getData().equals("open [this/is/my/path1]") == true);
		CFooResource cfooresource2 = (CFooResource) CResource.use("cfooresource1");
		this.assertTrue(cfooresource2 != null);
		this.assertTrue(cfooresource == cfooresource2);
		this.assertTrue(cfooresource2.getData().equals("open [this/is/my/path1]") == true);
		this.assertTrue(cfooresource2.restore() == true);
		this.assertTrue(cfooresource2.getData().equals("restore [this/is/my/path1]") == true);
		this.assertTrue(cfooresource2.close() == true);
		this.assertTrue(cfooresource2.getData().equals("close [this/is/my/path1]") == true);
		CResource.include("cfooresource2", "this/is/my/path2", "c3dclasses.CFooResource", null);
		CFooResource cfooresource3 = (CFooResource) CResource.use("cfooresource2");
		this.assertTrue(cfooresource3 != null);
		this.assertTrue(cfooresource3.getData().equals("open [this/is/my/path2]") == true);
		this.assertTrue(cfooresource3 != cfooresource2);
	} // end test()
	@Test (timeout = 1000)
	public void test2() {
		//__.sleep(2000);
	}
} // end CResourceUnitTest

//-----------------------------------------------------------------------
// name: CFooResource
// desc:
//-----------------------------------------------------------------------
class CFooResource extends CResource { 
	protected String m_strdata;
	
	public CFooResource() { 
		super(); 
		this.close(); 
	} // end CFooResource()	
	
	// override the methods
	public boolean open(String strpath, String strtype, CHash params) {
		if(!super.open(strpath, strtype, params))
			return false;
		this.m_strdata = "open [" + strpath + "]"; 
		return true;	
	} // end open()
	
	public boolean restore() {
		this.m_strdata = "restore [" + this.path() + "]";
		return super.restore();
	} // end restore()
	
	public boolean close() {
		this.m_strdata = "close [" + this.path() + "]";
		return super.close();
	} // end close()	
	
	public String getData() { 
		return this.m_strdata; 
	} // end getData()
} // end CFooResource