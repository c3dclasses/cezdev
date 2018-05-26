import c3dclasses;
public class CRemoteVariableTest {
	
	public CVar a = new CVar ("a", "C:jsonfilename") ;
	
	public static void main() {
		
		CVar b = new CVar ("b", "C:jsonfilename") ;
		
		b.update(10);
		
		System.out.println("b = " + b.retrieve());
		
		a.update("" + Math.random());
		
		System.out.println("a = " + a.retrieve());
		
		b.delete() ;
	
	} // end main()	
	
} // end CRemoteVariableTest