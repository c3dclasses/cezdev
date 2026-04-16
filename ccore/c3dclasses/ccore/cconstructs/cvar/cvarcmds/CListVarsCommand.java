//---------------------------------------------------------------------------------------------------------
// name: CListVarsCommand.java
// desc: sets a var path from an environment variable and cmemory to be shared by EZDEV and C3DClassesSDK
//---------------------------------------------------------------------------------------------------------
package c3dclasses;

//-----------------------------------------------------------------------------------------------------------
// name: CListVarsCommand
// desc: sets a var path from an environment variable and cmemory to be shared by EZDEV and C3DClassesSDK
//-----------------------------------------------------------------------------------------------------------
public class CListVarsCommand {
	//-------------------------------------------------------------------------------------
	// name: main()
	// desc:
	//-------------------------------------------------------------------------------------
	public static void main(String[] args) {
		// get command line arguments
		CArray cargs = __.carray(args);
		if(cargs == null || cargs.length() < 1) {
			__.println("Please supply 1 argument.");
			__.println("lsvars <memory-file>");
			__.println("lsvars C:/path/to/cvars.json");
			__.println();
			return;
		} // end if
		String strmetapath =  __.dirname(cargs._string(0));
		String strvarspath = cargs._string(0);
		
		// include / open memory
		if(CMemory.include("cvars", strvarspath, "c3dclasses.CJSONMemoryDriver", null) == null)
			return;
		CMemory cmemory = CMemory.use("cvars");
		
		// show the memory contents
		__.println(cmemory.cache().toJSON(false));
	} // end main()
} // end CIncludeBinPathCommand