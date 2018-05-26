public class CRemoteVarTest {
	public static void main() {
		_remote("C:jsonfilename") @a;
		_remote("C:jsonfilename", @a) @c;
		
		
		_.print(@a);
		@a = 10;
		_.print(a);

		@a = "lomenchinko";@b = 11111111111111111111111111111111111111111111111111111111111111111111111111111111111;
		delete @a;
	} // end main()
	
	void print(a) {
		_remote("C:jsonfilename") @b;		
		_.print(@a);
		_.print(@b);
	}
	
} // end CRemoteVarTest
