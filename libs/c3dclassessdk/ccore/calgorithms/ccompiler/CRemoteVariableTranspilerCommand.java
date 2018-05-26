//-------------------------------------------------------
// name: CRemoteVariableTranspilerCommand
// desc: 
//-------------------------------------------------------

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.*;
import java.util.*;
import java.net.*;
import java.text.*;
import java.awt.*;
import java.io.File;
import c3dclasses.*;

//------------------------------------------------------------------------
// name: CRemoteVariableTranspilerCommand
// desc: 
//------------------------------------------------------------------------
public class CRemoteVariableTranspilerCommand {				
	
	//-------------------------------------------------------
	// name: main()
	// desc: 
	//-------------------------------------------------------
	public static void main(String[] args) {
		CArray cargs = _.carray(args);
		if(cargs.length() < 2) {
			_.alert("usage: command srcfile.java");
			return;
		} // end if
		
		// get the filename to transpiler
		String strcommand = cargs._string(0);
		String strcurdir = cargs._string(0);
		String strinfile = cargs._string(1);
		String stroutfile = cargs._string(2);
		
		if(stroutfile == null || stroutfile.equals("")) {
			stroutfile = strinfile.replace(".src", ".java");
		}
		
		if(CRemoteVariableTranspiler.transpile(strcurdir + "/" + strinfile, strcurdir + "/" + stroutfile) == false)
			_.alert("ERROR in translating the remote constructs in: " + strinfile);
	} // end main()
} // end CFilesToPathsCommand