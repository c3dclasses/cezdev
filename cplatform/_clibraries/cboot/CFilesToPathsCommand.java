//-------------------------------------------------------
// name: CFilesToPathsCommand
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

//------------------------------------------------------------------------
// name: CFilesToPathsCommand
// desc: 
//------------------------------------------------------------------------
public class CFilesToPathsCommand {				
	//-------------------------------------------------------
	// name: main()
	// desc:
	//-------------------------------------------------------
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("usage: CFilesToPathsCommand <required:srcdir> <required:filetowriteto>");
			System.out.println("example 1: CFilesToPathsCommand C:\\c3dclassessdk\\src C:\\cezdev\\meta\\c3dclassessdk-src.json");
			return;
		} // end if
		File strfiletowriteto = new File(args[0]);
		boolean bappend = false; //(args.length > 2 && args[2] != null) ? args[2].toLowerCase().equals("append") : false;
		String jsonfiletofolder = "{";
		for(int i=1; i<args.length; i++) {
			File srcfolder = new File(args[i]);
			jsonfiletofolder += CFilesToPathsCommand.toStringFiletoFolder(srcfolder);
		} // end for
		jsonfiletofolder += "}";
		CFilesToPathsCommand.setFileContents(args[0], jsonfiletofolder, bappend);
	} // end main()
	
	//-------------------------------------------------------
	// name: toStringFiletoFolder()
	// desc:
	//-------------------------------------------------------
	public static String toStringFiletoFolder(File srcfolder) {
		String str = "";
		try {
			if (srcfolder.isDirectory()) {
				String files[] = srcfolder.list(); 
				for (String file : files)
					str += CFilesToPathsCommand.toStringFiletoFolder(new File(srcfolder, file));
			} // end if
			else {				
				str += "\"" + srcfolder.getName() + "\"";
				str += ":";
				str += "\"" + srcfolder.toString().replace("\\","/") + "\"";
				str += ",";
			} // end else
		} // end try
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		} // end catch
		return str;
	} // end toStringFiletoFolder()
	
	//-------------------------------------------------------
	// name: setFileContents()
	// desc:
	//-------------------------------------------------------
	public static boolean setFileContents(String strfilename, String strcontents, boolean bappend) {
		if(strcontents == null || strcontents == "")
			return false;
		try{
			//_.alert("set file contents here");
			FileWriter out = new FileWriter(strfilename, bappend);
			out.write(strcontents);
			out.close();
		} // end try
		catch(Exception ex) {
			return false;
		} // end catch() 	
		return true;
	} // end setFileContents()
} // end CFilesToPathsCommand