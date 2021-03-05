//------------------------------------------------------------------------------------------------------------
// name: CProjectsCommand.java
// desc: creates a project to be included into the c3dclassessdk framework to be build during maven process
//------------------------------------------------------------------------------------------------------------
package c3dclasses;

//-----------------------------------------------------------------------------------------------------------
// name: CProjectsCommand
// desc: creates a project to be included into the c3dclassessdk framework to be build during maven process
//-----------------------------------------------------------------------------------------------------------
public class CProjectsCommand {
	//-------------------------------------------------------------------------------------
	// name: main()
	// desc:
	//-------------------------------------------------------------------------------------
	public static void main(String[] args) {
		// get command line arguments
		CArray cargs = __.carray(args);
		if(cargs == null || cargs.length() < 2) {
			__.println("Please supply 1 arguments.");
			__.println("c3dprj <PROJECTNAME>");
			__.println("c3dprj MYFOOPRJ");
			__.println();
			return;
		} // end if
		__.print("params: ");
		__.println(cargs);
		String strmetapath = cargs._string(1);
		String strprjname = cargs._string(2);
		
		if(strprjname.equals("")) {
			__.println("ERROR: You must enter a valid project name");
			__.alert("ERROR: You must enter a valid project name");
			return;
		} // end if
			
		__.println("Opening the file dialog to get a path to set....");	
		String strprjpath = "";
		strprjpath = __.prompt_path("Please browse for the project path");
		if(!__.is_directory(strprjpath)) {
			__.println("The following project path is not a directory in the system: " + strprjpath);
			__.alert("The following project path is not a directory in the system: " + strprjpath);			
			return;
		} // end if
				
		__.println("The following path is a project path in the system: " + strprjpath);
	
		// create the script to set the environment variable for EZDEV
		String strscriptfile = strmetapath + "/" + strprjname + ".prj.bat";
		String strcontents = "call cp-files \"" + strprjpath + "\" \"%C3DCLASSESSDK_CPROJECTS%/" + strprjname + "\" \".\"";	
		__.println("Creating the script for moving cprojects to c3dclassessdk: " + strscriptfile);
		__.file_set_contents(strscriptfile, strcontents);
		return;
	} // end main()
} // end CProjectsCommand