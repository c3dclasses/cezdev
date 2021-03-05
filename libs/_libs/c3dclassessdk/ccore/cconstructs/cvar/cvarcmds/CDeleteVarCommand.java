//---------------------------------------------------------------------------------------------------------
// name: CDeleteVarCommand.java
// desc: deletes a cmemory variable and environment variable
//---------------------------------------------------------------------------------------------------------
package c3dclasses;

//-----------------------------------------------------------------------------------------------------------
// name: CDeleteVarCommand
// desc: deletes a cmemory variable and environment variable
//-----------------------------------------------------------------------------------------------------------
public class CDeleteVarCommand {
	//-------------------------------------------------------------------------------------
	// name: main()
	// desc:
	//-------------------------------------------------------------------------------------
	public static void main(String[] args) {
		// get command line arguments
		CArray cargs = __.carray(args);
		if(cargs == null || cargs.length() < 2) {
			__.println("Please supply 1 arguments.");
			__.println("delvar <ENVVARNAME>");
			__.println("delvar JAVA_HOME");
			__.println();
			return;
		} // end if
		__.print("params: ");
		__.println(cargs);
		
		String strmetapath =  __.dirname(cargs._string(1));
		String strvarname = cargs._string(2);
		String strvarspath = cargs._string(1);
		
		// include / open memory
		if(CMemory.include("cvars.js", strvarspath, "c3dclasses.CJSONMemoryDriver", null) == null)
			return;
		CMemory cmemory = CMemory.use("cvars.js");
		
		// delete the memory contents
		CReturn creturn = cmemory.delete(strvarname);	
		if(creturn != null && creturn._boolean())
			__.println("cmemory.delete(" + strvarname + ") - Deleted the memory location.");
		else __.println("cmemory.delete(" + strvarname + ") - Could not deleted the memory location - it may not exist.");
		cmemory.close();
		__.println("cmemory.close() - closing memory location.");
				
		// create a tmp script to delete the environment variable
		String strscriptfile = strmetapath + "/" + strvarname + "_tmp.bat";
		String strcontents = "echo 'deleting the environment variable:'\n";
		strcontents += "set " + strvarname + "=\n";
		__.println("Created the script for deleting environment variable in: " + strscriptfile);
		__.file_set_contents(strscriptfile, strcontents);
		return;
	} // end main()
} // end CDeleteVarCommand