//------------------------------------------------------------------------------
// name: CJavaD.java
// desc: responsible for deploying java programs, commands to a directory
//------------------------------------------------------------------------------
package c3dclasses;

//--------------------------------------------------------
// name: CJavaD
// desc:
//--------------------------------------------------------
public class CJavaD {
	//-------------------------------------------------------
	// name: main()
	// desc: 
	//-------------------------------------------------------
	public static void main(String[] args) {
		CArray cargs = __.carray(args);
		if(cargs == null || cargs.length() < 3) {
			__.println("Please supply 2 or more arguments. Example > cmd <classname-to-run> <path-to-ccommandname>");
			return;
		} // end if
		String strclassname = cargs._string(1);
		String strjarfilepath =  cargs._string(2);
		String strpath = __.prompt_path("Please choose a directory to store the command");
		
		__.alert(strclassname);
		__.alert(strjarfilepath);
		__.alert(strpath);
		
		if(!__.file_copy(strjarfilepath, strpath + "/c3dclasses.jar")) {
			__.println("Please supply the correct path of the jar file to copy and try again.");
			return;
		} // end if
		String strcontents = "call java -cp c3dclasses.jar;. "+ strclassname +" %*";
		__.file_set_contents(strpath + "/" + strclassname + ".bat", strcontents);
	} // end main()
} // end CJavaD