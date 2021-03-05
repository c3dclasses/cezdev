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
		String strmetapath =  __.dirname(cargs._string(1));
		String strvarspath = cargs._string(1);
		
		// include / open memory
		if(CMemory.include("cvars", strvarspath, "c3dclasses.CJSONMemoryDriver", null) == null)
			return;
		CMemory cmemory = CMemory.use("cvars");
		
		// show the memory contents
		__.println(cmemory.cache().toJSON(false));
	} // end main()
} // end CIncludeBinPathCommand