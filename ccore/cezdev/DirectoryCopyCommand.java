//-------------------------------------------------------
// name: DirectoryCopyCommand
// desc: Copies files from source to destination directory based on format filters
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
// name: DirectoryCopyCommand
// desc: Copies files from source to destination directory based on format filters
//------------------------------------------------------------------------
public class DirectoryCopyCommand {				

	//-------------------------------------------------------
	// name: main()
	// desc: Entry point - parses arguments and initiates directory copy
	//-------------------------------------------------------
	public static void main(String[] args) {
		if(args.length < 3) {
			System.out.println("[ERROR] Usage: DirectoryCopyCommand <required:srcdir> <required:dstdir> <optional:formats-to-copy> <optional:formats-to-skip> <optional:files-to-copy>");
			System.out.println("[INFO] Example 1: DirectoryCopyCommand C:\\project-path C:\\target\\project-path *.java,*.json");
			System.out.println("[INFO] Example 2: DirectoryCopyCommand C:\\project-path C:\\target\\project-path *.java,*.json  *UnitTest.java");
			return;
		} // end if
		
		System.out.println("[CALLING] DirectoryCopyCommand");
		
		File srcfolder = new File(args[0]);
        File dstfolder = new File(buildDestinationDirectory(args[1]));
		ArrayList formattocopy = DirectoryCopyCommand.split(",", args[2]);
		ArrayList formattoskip = (args.length > 3) ? DirectoryCopyCommand.split(",", args[3]) : null;
		ArrayList filestocopy = (args.length > 4) ? DirectoryCopyCommand.split(",", args[4]) : null;
		
		System.out.println("[INFO] Source directory: " + srcfolder.getAbsolutePath());
		System.out.println("[INFO] Destination directory: " + dstfolder.getAbsolutePath());
		System.out.println("[INFO] Formats to copy: " + args[2]);
		if(args.length > 3) System.out.println("[INFO] Formats to skip: " + args[3]);
		if(args.length > 4) System.out.println("[INFO] Files to copy: " + args[4]);
		System.out.println("[STEP] Copying files...");
		
		DirectoryCopyCommand.copyFolder(srcfolder, dstfolder, formattocopy, formattoskip, filestocopy);
		
		System.out.println("[ENDING] DirectoryCopyCommand");
		return;
	} // end main()
	
	//-------------------------------------------------------
	// name: copyFolder()
	// desc: Recursively copies files from source to destination based on format filters
	//-------------------------------------------------------
	public static void copyFolder(File srcfolder, File dstfolder, 
									ArrayList formattocopy, 
									ArrayList formattoskip, 
									ArrayList filestocopy) {
		try {
			if (srcfolder.isDirectory()) {
				if (!dstfolder.exists()) {
					dstfolder.mkdir();
					System.out.println("[MKDIR] " + dstfolder);
				} // end if
				String files[] = srcfolder.list(); 
				for (String file : files) {
					File srcfile = new File(srcfolder, file);
					File dstfile = new File(dstfolder, file);
					DirectoryCopyCommand.copyFolder(srcfile, dstfile, formattocopy, formattoskip, filestocopy);
				} // end for
			} // end if
			else {
				// Copy the file content from one place to another
				if(DirectoryCopyCommand.checkFormat(srcfolder.getName(), filestocopy) ||
					(DirectoryCopyCommand.checkFormat(srcfolder.getName(), formattocopy) && 
					!DirectoryCopyCommand.checkFormat(srcfolder.getName(), formattoskip))) {
					Files.copy(srcfolder.toPath(), dstfolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
					System.out.println("[COPYING]: " + srcfolder.getName());
				} // end if
			} // end else
		} // end try
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		} // end catch
		return;
	} // end copyFiles()
	
	//-------------------------------------------------------
	// name: split()
	// desc: Splits a string by a separator and returns an ArrayList of tokens
	//-------------------------------------------------------
	public static ArrayList split(String strseperator, String strtoseperate) {
		if(strseperator == "" ||  strtoseperate == "")
			return null;
		ArrayList strings = new ArrayList();
		StringTokenizer st = new StringTokenizer(strtoseperate, strseperator);
		while(st.hasMoreTokens()) 
			strings.add(st.nextToken());
		return strings;	
	} // end split()
	
	
	//-------------------------------------------------------
	// name: buildDestinationDirectory()
	// desc: Creates all directories in the path if they don't exist
	//-------------------------------------------------------
	public static String buildDestinationDirectory(String strpath) {
		if(strpath == null || strpath == "")
			return "";
		ArrayList dirpath = new ArrayList();
		ArrayList alfs = split("/", strpath);
		for(int i=0; i<alfs.size(); i++) {
			ArrayList albs = split("\\", (String)alfs.get(i));
			for(int j=0; j<albs.size(); j++) {
				dirpath.add((String)albs.get(j));
			} // end for
		} // end for
		
		String strdirpath = "";
		for(int i=0; i<dirpath.size(); i++) {
			strdirpath += dirpath.get(i);
			File dstfolder = new File(strdirpath);
			if (!dstfolder.exists()) {
				dstfolder.mkdir();
				System.out.println("[MKDIR] " + dstfolder);
			} // end if
			strdirpath += "/";
		} // end for
		return strpath;
	} // end buildDestinationDirectory()
	
	//-------------------------------------------------------
	// name: checkFormat()
	// desc: Checks if the filename matches any pattern in the format list
	//-------------------------------------------------------
	public static boolean checkFormat(String strfilename, ArrayList format) {
		if(format == null)
			return false;
		for(int i=0; i<format.size(); i++) {
			if(strfilename.indexOf((String)format.get(i)) != -1)
				return true;
		} // end for
		return false;
	} // end checkFormat()
} // end DirectoryCopyCommand