//---------------------------------------------------------------------------------------------
// name: _.java
// desc: defines commonly used methods used throughout c3dclassessdk
//---------------------------------------------------------------------------------------------

// imports
import java.io.*;
import java.util.*;
import java.net.*;
import java.text.*;
import java.awt.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.*;
import javax.swing.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

//------------------------------------------------------------------------
// name: _
// desc: defines commonly used methods used throughout c3dclassessdk
//------------------------------------------------------------------------
public class _ {
	// constructory
	public _() {} 
	
	// helper methods
	public static ArrayList<String> getLinesFromFile(String strfilename) {
		if(strfilename == null || strfilename == "")
			return null;
		try{
			//Vector v = new Vector();
			ArrayList<String> lines = new ArrayList<String>();
			String strline = "";
			LineNumberReader in = new LineNumberReader(new FileReader(strfilename));
			while((strline = in.readLine()) != null) { 
				lines.add(strline); 
			} // end while()	
			in.close();
			return lines;
		} // end try
		catch(Exception ex) {
			//System.out.println("ERROR: _.getLinesFromFile(): Couldn't get lines from: " + strfilename);
			return null; 
		} // end catch() 
	} // end getLinesFromFile()	
	
	// returns the contents of a file
	public static String getFileContents(String strfilename) {
		String strcontents = "";
		try {
			Reader in = new BufferedReader(new FileReader(strfilename));
			StringBuilder sb = new StringBuilder();
			char[] chars = new char[1 << 16];
			int length;
			while ((length = in.read(chars)) > 0) {
				sb.append(chars, 0, length);
			} // end while()
			strcontents = sb.toString();
			in.close();
		} // end try
		catch(Exception ex) {
			//System.out.println("ERROR: _.getFileContents(): Couldn't read from: " + strfilename);
			return "";
		} // end catch()
		return strcontents;
	} // end getFileContents()
	
	// sets the contents of a file
	public static boolean setFileContents(String strfilename, String strcontents) {
		if(strcontents == null || strcontents == "")
			return false;
		try{
			FileWriter out = new FileWriter(strfilename);
			out.write(strcontents);
			out.close();
		} // end try
		catch(Exception ex) {
			//System.out.println("ERROR: _.setFileContents(): Couldn't write to: " + strfilename);
			return false;
		} // end catch() 	
		return true;
	} // end setFileContents()
	
	// returns the pid of this application
	public static long getPID() {
		String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        return Long.valueOf(jvmName.split("@")[0]);
	} // end getPID()
	
	// runs a command asynchoronously
	public static boolean execCommand(String strcommand) {
		try{ 
			Process p = Runtime.getRuntime().exec(strcommand); 
			//System.out.println("SUCCESS: _.execCommand(): executed command: " + strcommand); 
			return true; 
		} // end try
		catch(Exception ex) { 
			//System.out.println("ERROR: _.execCommand(): couldn't execute command: " + strcommand);
			return false; 
		} // end catch()
	} // end runCommand()
	
	// runs a command synchronously
	public static boolean _execCommand(String strcommand) {
		try{ 
			Process p = Runtime.getRuntime().exec(strcommand); 
			int returnCode = p.waitFor(); 
			//System.out.println("SUCCESS: _._execCommand(): executed command: " + strcommand); 
			return true; 
		} // end try
		catch(Exception ex) { 
			//System.out.println("ERROR: _._execCommand(): couldn't execute command: " + strcommand);
			return false; 
		} // end catch()
	} // end runCommand()
	
	// returns the home path of EZDEV
	public static String getHomePath() { 
		try{
			String str=new java.io.File("..").getCanonicalPath(); 
			return str; 
		} // end try
		catch(IOException ex) { 
			//System.out.println("ERROR: _.getHomePath(): couldn't get the home path");
			return ""; 
		} // end catch()
	} // end getHomePath()
	
	// alert command
	public static void alert(String strmessage, String strtitle) {
		JOptionPane.showMessageDialog(null, strmessage, strtitle, JOptionPane.INFORMATION_MESSAGE);
	} // end alert
	
	// checks if a directory exist
	public static String getPath(String strfilename) {
		try {
		 	File f = new File(strfilename);
         	return f.getParentFile().getCanonicalPath();
        } // end try
		catch(Exception ex) {
			return "";
		} // end catch()
	} // end getPath()
	
	public static boolean isDirectory(String strpathname) {
		try{ File f = new File(strpathname); return (f.exists() && f.isDirectory());}
		catch(Exception ex) { return false; }
	} // end isDirectory()
	
	public static boolean isFile(String strpathname) {
		try{ File f = new File(strpathname); return (f.exists());}
		catch(Exception ex) { return false; }
	} // end isDirectory()
	
	// prompts for a file
	public static String promptForFile(Component parent, String strtitle) {
    	JFileChooser fc = new JFileChooser();
		fc.setDialogTitle(strtitle);
    	if(fc.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
        	return fc.getSelectedFile().getAbsolutePath();
    	} // end if
    	return null;
	} // end promptForFile()
} // end _