public class CRemoteVarTest {
	public static void main() {
		_remote("C:jsonfilename") @a;
		_remote("C:jsonfilename", @a) @c;
		
		
		__.print(@a);
		@a = 10;
		__.print(a);

		@a = "lomenchinko";@b = 11111111111111111111111111111111111111111111111111111111111111111111111111111111111;
		delete @a;
	} // end main()
	
	void print(a) {
		_remote("C:jsonfilename") @b;		
		__.print(@a);
		__.print(@b);
	}
	
} // end CRemoteVarTest
