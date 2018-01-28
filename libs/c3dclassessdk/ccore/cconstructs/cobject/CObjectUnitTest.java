//---------------------------------------------------------------------------------
// file: CObjectUnitTest
// desc: defines a object primitive which mimics Javascript Object
//---------------------------------------------------------------------------------
package c3dclasses;
import org.junit.*;

//---------------------------------------------------------------------------------
// name: CObjectMain
// desc: defines a object primitive which mimics Javascript Object
//---------------------------------------------------------------------------------
public class CObjectUnitTest extends CUnitTest {
	@Test
	public void test() {
		CObject cobject = new CObject(); // _.cobject()
		cobject._("my_prop1", "this is my property");
		cobject._("my_prop2", 10);
		cobject._("my_prop3", 10.8);
		cobject._("my_prop4", _.carray(10,10,10));
		cobject._("my_prop5", _.chash("prop1",10,"prop2",10));
		cobject._("my_prop6", _.chash(
					"prop1", _.chash(
						"inner-name", 10
						), // end chash
					"prop2", _.carray(10,20,30)
				) // end chash
		); // end cobject
		
		this.assertTrue(cobject.toString().equals(  "{\"my_prop4\":[10,10,10],\"my_prop5\":{\"prop2\":10,\"prop1\":10},\"my_prop2\":10,\"my_prop3\":10.8,\"my_prop1\":\"this is my property\",\"my_prop6\":{\"prop2\":[10,20,30],\"prop1\":{\"inner-name\":10}}}") == true);
		this.assertTrue(cobject._chash("my_prop6").toString().equals("{\"prop2\":[10,20,30],\"prop1\":{\"inner-name\":10}}") == true);
		this.assertTrue(cobject._chash("my_prop6")._chash("prop1").toString().equals("{\"inner-name\":10}") == true);
		this.assertTrue(cobject._chash("my_prop6")._carray("prop2").toString().equals("[10, 20, 30]") == true);
		this.assertTrue(cobject._chash("my_prop6")._carray("prop2")._int(2) == 30);
	} // end main()
} // end CObjectUnitTest