//------------------------------------------------------------------------------------------------------------
// name: CProcessTemplateFileCommand.java
// desc: runs a command to replace templated content in a file
//------------------------------------------------------------------------------------------------------------
package c3dclasses;

//-----------------------------------------------------------------------------------------------------------
// name: CProcessTemplateFileCommand
// desc: 
//-----------------------------------------------------------------------------------------------------------
public class CProcessTemplateFileCommand {
	//-------------------------------------------------------------------------------------
	// name: main()
	// desc:
	//-------------------------------------------------------------------------------------
	public static void main(String[] args) {
		// get command line arguments
		CArray cargs = __.carray(args);
		if(cargs == null || cargs.length() < 3) {
			__.println("Error: Incorrect number of arguments");
			return;
		} // end if
		String strkeyvaluefile = cargs._string(1);
		String strinfile = cargs._string(2);
		String stroutfile = cargs._string(3);
		CHash keysvalues = __.chash();
		for(int i=4; i<cargs.length(); i++) {
			CArray kv = __.split("=", cargs._string(i));
			keysvalues._(kv._(0), kv._(1));
		} // end for
		if(!__.file_replace_key_with_contents(strkeyvaluefile, strinfile, stroutfile, keysvalues))
			__.alert("error replacing content in the file");
		return;
	} // end main()
} // end CProjectsCommand
