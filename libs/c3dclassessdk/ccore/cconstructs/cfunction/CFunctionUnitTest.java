//---------------------------------------------------------------------------------
// file: CFunctionUnitTest
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;

//---------------------------------------------------------------------------------
// name: CFunctionUnitTest
// desc: 
//---------------------------------------------------------------------------------
public class CFunctionUnitTest extends CUnitTest{
	@Test
	public void test() {
		//////////////////////////////////////////////
		// Passing arguments to the function
		//////////////////////////////////////////////
		
		// define the function		
		final CFunction cfunction1 = new CFunction() { 
			public CReturn call(CArray args) { 
				// parsing the arguments
				int n = args._int(0);
				return CReturn._done(n * n); 
			} // end _()		
		}; // end CFunction()
	
		// call and print the value of the function
		this.assertTrue(cfunction1.call(_.args(4, 4))._int() == 16);	
		
		//////////////////////////////////////////////////
		// Passing params to the function
		//////////////////////////////////////////////////

		// define the function
		final CFunction cfunction2 = new CFunction() { 
			public CReturn call(CHash params) { 
				int value1 = params._int("prop1");
				int value2 = params._int("prop2");
				return CReturn._done(_.args(value1, value2)); 
			} // end _()		
		}; // end CFunction()

		// call and print the values of the function
		CReturn ret = cfunction2.call(_.params("prop1", 8, "prop2", 20));
		this.assertTrue(ret._carray()._int(0) == 8);	
		this.assertTrue(ret._carray()._int(1) == 20);	
		
		////////////////////////////////////////
		// use CFunction as a CObject
		////////////////////////////////////////
		
		// define the function
		final CFunction cfunction3 = new CFunction() { 
			public CReturn call(CArray args) {
				// get the property 
				String metaprop1 = this._string("meta-prop1");
				return CReturn._done(metaprop1);
			} // end _()		
		}; // end CFunction()
		
		// set the property of the function
		cfunction3._("meta-prop1","meta-value1");
		
		// call and print the value of the function
		this.assertTrue(cfunction3.call((CArray)null)._string().equals("meta-value1"));	
	} // end main()
} // end CFunctionMain