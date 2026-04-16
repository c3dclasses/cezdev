//-------------------------------------------------------
// name: CCopyFoldersCommand
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
// name: CCopyFoldersCommand
// desc: 
//------------------------------------------------------------------------
public class CCopyFoldersCommand {				

	//-------------------------------------------------------
	// name: main()
	// desc:
	//-------------------------------------------------------
	public static void main(String[] args) {
		if(args.length < 3) {
			System.out.println("[ERROR] Usage: CCopyFoldersCommand <required:srcdir> <required:dstdir> <optional:formats-to-copy> <optional:formats-to-skip> <optional:files-to-copy>");
			System.out.println("[INFO] Example 1: CCopyFoldersCommand C:\\project-path C:\\target\\project-path *.java,*.json");
			System.out.println("[INFO] Example 2: CCopyFoldersCommand C:\\project-path C:\\target\\project-path *.java,*.json  *UnitTest.java");
			return;
		} // end if
		
		System.out.println();
		System.out.println("::---------------------------------------------------------------------------------------");
		System.out.println("::  Starting CCopyFoldersCommand");
		System.out.println("::---------------------------------------------------------------------------------------");
		
		File srcfolder = new File(args[0]);
        File dstfolder = new File(buildDestinationDirectory(args[1]));
		ArrayList formattocopy = CCopyFoldersCommand.split(",", args[2]);
		ArrayList formattoskip = (args.length > 3) ? CCopyFoldersCommand.split(",", args[3]) : null;
		ArrayList filestocopy = (args.length > 4) ? CCopyFoldersCommand.split(",", args[4]) : null;
		
		System.out.println("[INFO] Source directory: " + srcfolder.getAbsolutePath());
		System.out.println("[INFO] Destination directory: " + dstfolder.getAbsolutePath());
		System.out.println("[INFO] Formats to copy: " + args[2]);
		if(args.length > 3) System.out.println("[INFO] Formats to skip: " + args[3]);
		if(args.length > 4) System.out.println("[INFO] Files to copy: " + args[4]);
		System.out.println("[STEP] Copying files...");
		
		CCopyFoldersCommand.copyFolder(srcfolder, dstfolder, formattocopy, formattoskip, filestocopy);
		
		System.out.println("::---------------------------------------------------------------------------------------");
		System.out.println("::  Ending CCopyFoldersCommand");
		System.out.println("::---------------------------------------------------------------------------------------");
		System.out.println();
		return;
	} // end main()
	
	//-------------------------------------------------------
	// name: copyFolder()
	// desc:
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
					CCopyFoldersCommand.copyFolder(srcfile, dstfile, formattocopy, formattoskip, filestocopy);
				} // end for
			} // end if
			else {
				// Copy the file content from one place to another
				if(CCopyFoldersCommand.checkFormat(srcfolder.getName(), filestocopy) ||
					(CCopyFoldersCommand.checkFormat(srcfolder.getName(), formattocopy) && 
					!CCopyFoldersCommand.checkFormat(srcfolder.getName(), formattoskip))) {
					Files.copy(srcfolder.toPath(), dstfolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
					System.out.println("  Copying: " + srcfolder.getName());
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
	// desc:
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
	// desc: 
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
	// desc:
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
} // end CCopyFilesFromSrcDirToDstDirCommand