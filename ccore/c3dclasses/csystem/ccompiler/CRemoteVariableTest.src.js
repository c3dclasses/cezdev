import c3dclasses;
public class CRemoteVariableTest {
	
	public _remote <"a", "C:jsonfilename"> @a;
	
	public static void main() {
		
		_remote <"b", "C:jsonfilename"> @b;
		
		@b = 10;
		
		System.out.println("b = " + @b);
		
		@a = "" + Math.random();
		
		System.out.println("a = " + @a);
		
		delete @b;
	
	} // end main()	
	
} // end CRemoteVariableTest