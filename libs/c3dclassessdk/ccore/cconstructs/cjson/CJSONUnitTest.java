//-------------------------------------------------------------------------------------------------------
// file: CJSONUnitTest.java
// desc: 
//-------------------------------------------------------------------------------------------------------
package c3dclasses;
import org.junit.*;

//-----------------------------------------------------------
// name: CJSONUnitTest
// desc:
//-----------------------------------------------------------
public class CJSONUnitTest extends CUnitTest {
	@Test
	public void test() {
		String strjson = "{\"my_prop4\":[10,10,10],\"my_prop5\":{\"prop2\":10,\"prop1\":10},\"my_prop2\":10,\"my_prop3\":10.8,\"my_prop1\":\"this is my property\",\"my_prop6\":{\"prop2\":[10,20,30],\"prop1\":{\"inner-name\":10}}}";
		CHash chash = CJSON.toCHash(strjson);
		this.assertTrue(chash.toString().equals(strjson) == true);
	} // end test()
} // end CJSONUnitTest