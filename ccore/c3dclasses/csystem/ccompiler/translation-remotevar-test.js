public class CRemoteVarTest {
	public static void main() {
		CVar a = new CVar("C:jsonfilename") ;
		CVar c = new CVar("C:jsonfilename", @a) ;
		
		
		_.print(a.retrieve());
		a.update(10);
		_.print(a);

		a.update("lomenchinko");b.update(11111111111111111111111111111111111111111111111111111111111111111111111111111111111);
		a.delete() ;
	} // end main()
	
	void print(a) {
		CVar b = new CVar("C:jsonfilename") ;		
		_.print(a.retrieve());
		_.print(b.retrieve());
	}
	
} // end CRemoteVarTest
