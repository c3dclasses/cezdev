//---------------------------------------------------------------------------------
// file: CCalculusUnitTest
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;

//---------------------------------------------------------------------------------
// name: CCalculusUnitTest
// desc: 
//---------------------------------------------------------------------------------
public class CCalculusUnitTest extends CUnitTest{
	@Test
	public void test() {
		new f("x^2") { public double _(double x) { return (x*x); }};
		new f("x^3") { public double _(double x) { return (x*x*x); }};
		new f("x^1/2") { public double _(double x) { return Math.pow(x, 1/(double)2); }};
		new f("sqrt(x)") { public double _(double x) { return Math.sqrt(x); }};
		
		//_.alert(f._("x^2")._(2) * f._("x^2")._(2));
		//_.alert(f._("x^1/2")._(64));
		//_.alert(f._("sqrt(x)")._(64));
		
		//f._("x^2").graph(2000);
		
		//f._("x^2").direvative();
		
		
	} // end main()
} // end CCalculusUnitTest