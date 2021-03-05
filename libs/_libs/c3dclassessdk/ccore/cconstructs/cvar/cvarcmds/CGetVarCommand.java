//---------------------------------------------------------------------------------------------------------
// name: CGetVarCommand.java
// desc: gets a var path from an environment variable and cmemory to be shared by EZDEV and C3DClassesSDK
//---------------------------------------------------------------------------------------------------------
package c3dclasses;

//-----------------------------------------------------------------------------------------------------------
// name: CGetVarCommand
// desc: gets a var path from an environment variable and cmemory to be shared by EZDEV and C3DClassesSDK
//-----------------------------------------------------------------------------------------------------------
public class CGetVarCommand {
	//-------------------------------------------------------------------------------------
	// name: main()
	// desc:
	//-------------------------------------------------------------------------------------
	public static void main(String[] args) {
		// get command line arguments
		CArray cargs = __.carray(args);
		if(cargs == null || cargs.length() < 2) {
			__.println("Please supply 1 arguments.");
			__.println("getvar <ENVVARNAME>");
			__.println("getvar JAVA_HOME");
			__.println();
			return;
		} // end if
		__.print("params: ");
		__.println(cargs);
		String strmetapath =  __.dirname(cargs._string(1));
		String strvarname = cargs._string(2);
		String strvarspath = cargs._string(1);
		
		// include / open memory
		if(CMemory.include("cvars", strvarspath, "c3dclasses.CJSONMemoryDriver", null) == null)
			return;
		CMemory cmemory = CMemory.use("cvars");
		
		// get the memory contents
		CReturn creturn = cmemory.retrieve(strvarname);	
		if(creturn == null || (CHash) creturn.data() == null) {
			__.println("cmemory.retrieve(" + strvarname + ") - Could not retrieve memory location.");
			__.println("Try using setvar command to create a variable before using getvar command.");
			return;
		} // end if		
		cmemory.close();
		__.println("cmemory.close() - closing memory location.");
				
		CHash cvar = (CHash) creturn.data();
		String strvarvalue = (cvar != null) ? cvar._string("m_value") : "";
			
		// create the script to set the environment variable for EZDEV
		String strscriptfile = strmetapath + "/" + strvarname + "_tmp.bat";
		String strcontents = "echo setting the environment variable:\n";
		strcontents += "set " + strvarname + "=" + strvarvalue + "\n";
		__.println("Creating the script for setting the environment variable in: " + strscriptfile);
		__.file_set_contents(strscriptfile, strcontents);
		return;
	} // end main()
} // end CGetVarCommand