//-------------------------------------------------------------------------
// file: _.java
// desc: contains global functions exdev and c3dclasses namespace/package
//-------------------------------------------------------------------------
package cglobal;
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

//-----------------------------------------------
// class: _
// desc: global methods used throughout the SDK
//-----------------------------------------------
public class _ {
	
	//------------------------------------------------------------------------------
	// name: print*() / alert() / confirm() / console()
	// desc: printing methods
	//------------------------------------------------------------------------------
	public static void printbr(String str) { 
		_._print(str + "<br />"); 
	} // end printbr()
	public static void printbr(){
		_.printbr("");
	} // end printbr()
	
	public static void println(Object object) {
		_._print(object.toString() + "\n"); 
	} // end println()

	public static void echo(Object object) {
		_.print(object);
	} // end echo()
	
	public static void println(){
		_.println(""); 
	} // end println()
	
	public static void printjs(Object strjstoprint) { 
		_._print("<script>" + strjstoprint.toString() + "</script>"); 
	} // end printjs();
	
	public static void print(Object str) {
		_._print(str);
	} // end print()

	public static void _print(Object str) {
		System.out.print(str);
	} // end _print()

	public static void console(Object strmessage, boolean bscript) { 
		if(bscript) 
			_.print("<script parse=\"false\">"); 
		_.print("console.log('" + strmessage + "');"); 
		if(bscript) 
			_.print("</script>");
	} // end console()
	
	public static void alert(Object strmessage, boolean bscript) { 
		JOptionPane.showMessageDialog(null, strmessage.toString(), "Alert", JOptionPane.INFORMATION_MESSAGE);
	} // end alert()
	
	public static void alert(Object strmessage) {
		_.alert(strmessage, true);
	} // end console()

	public static void confirm(String strmessage, String strjsyesbody, String strjsnobody, boolean bscript) { 
		if(bscript) 
			_.print("<script parse=\"false\">"); 
		_.print("if(confirm('"+strmessage+"') == true) {"+strjsyesbody+"} else {"+strjsnobody+"}");
		if(bscript) 
			_.print("</script>"); 
	} // end confirm()
	
	public static void confirm(String strmessage, String strjsyesbody, String strjsnobody) {
		_.confirm(strmessage, strjsyesbody, strjsnobody);
	} // end confirm()
		
	public static String print_r(Object object, boolean bstr) {
		String str="";
   		if(object!=null && object.getClass().isArray()) {
			Object [] objs = (Object[])object;
			for(Object obj : objs) {
				str += print_r(obj, bstr); 
			} // end for
		} // end if
		else str += object.toString(); 
		if(bstr)
			return str;
		_.print(str);
		return ""; 
	} // end print_r()
	
	//-------------------------------------------------------------------
	// name: getTimeInMilliseconds() / getTimeInMicroseconds()
	// desc: time - milliseconds / microseconds
	//-------------------------------------------------------------------
	public static float getTimeInMicroseconds(){
		return System.nanoTime() / 1000;
	} // end getCurrentTimeInMicroseconds()

	public static long getTimeInMilliseconds(){ 
		return System.currentTimeMillis() % 1000;
	} // end getTimeInMilliseconds()
	
	////////////////////////////////
	// path
	
	// returns the home path of EZDEV
	public static String getHomePath() { 
		try{
			String str=new File(".").getCanonicalPath(); 
			return str; 
		} // end try
		catch(IOException ex){ 
			System.out.println("ERROR: CUtility.getHomePath(): couldn't get the home path");
			return ""; 
		} // end catch()
	} // end getHomePath()
	
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
	
	public static String dirname(String strfilename) {
		return _.getPath(strfilename);
	} // 
	
	// __.LINE__() similar to __line__ in C++
    public static int LINE__() {
        return Thread.currentThread().getStackTrace()[0].getLineNumber();
    }
 
    // __.METHOD__()
    public static String METHOD__() {
        return Thread.currentThread().getStackTrace()[0].getMethodName();
    }
 
    // __.CLASS__()
    public static String CLASS__() {
        return Thread.currentThread().getStackTrace()[0].getClassName();
    }

	public static String PATH__(String strclassname) {
		return _.CLASS__();
	}

	public static String FILE__(){
		String _class = Thread.currentThread().getStackTrace()[2].getClassName();
		String _filename = Thread.currentThread().getStackTrace()[2].getFileName();
		return getHomePath() + "\\src\\main\\java\\" + _class.replace(".", "\\");
	} 

    // __.FILE__() similar to __file__ in C++
    public static String FILENAME__() {
		//String src = (bsrc) ? "\\src\\main\\java\\" : "\\target\\classes";
        return Thread.currentThread().getStackTrace()[2].getFileName();
    }

    public static String testOne() {
        return "testOne:: " + " File:" + _.FILE__() + " Class:" + _.CLASS__()
                + " Method:" + _.METHOD__() + " Line:" + _.LINE__();
    }
	
	//---------------------------------------------------------------
	// name: parseInt(), parseFloat(), toString()
	// desc: parsing methods
	//---------------------------------------------------------------
	public static int parseInt(String str) { 
		return Integer.parseInt(str); 
	} // end parseInt()

	public static float parseFloat(String str) { 
		return Float.parseFloat(str);
	} // end parseFloat()
/*
	//----------------------------------------------------------------
	// name: split()
	// desc: splits a string into an array of String objects
	//----------------------------------------------------------------
	public static CArray split(String strseperator, String strtoseperate) {
		if(strseperator == "" ||  strtoseperate == "")
			return null;
		CArray strings = new CArray();
		StringTokenizer st = new StringTokenizer(strtoseperate, strseperator);
		while(st.hasMoreTokens()) 
			strings.push(st.nextToken());
		return strings;	
	} // end split()
*/	
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
			System.out.println("ERROR: _.getLinesFromFile(): Couldn't get lines from: " + strfilename);
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
			System.out.println("SUCCESS: _.execCommand(): executed command: " + strcommand); 
			return true; 
		} // end try
		catch(Exception ex) { 
			System.out.println("ERROR: _.execCommand(): couldn't execute command: " + strcommand);
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
	
	public static void alert(String strmessage, String strtitle) {
		strmessage = (strmessage == "" || strmessage == null) ? "  " : strmessage;
		strtitle = (strtitle == "" || strtitle == null) ? "  " : strtitle;
		JOptionPane.showMessageDialog(null, strmessage, strtitle, JOptionPane.INFORMATION_MESSAGE);
	} // end alert
	
	public static boolean isDirectory(String strpathname) {
		try{ File f = new File(strpathname); return (f.exists() && f.isDirectory());}
		catch(Exception ex) { return false; }
	} // end isDirectory()

	
	public static boolean isFile(String strpathname) {
		try{ File f = new File(strpathname); return (f.exists());}
		catch(Exception ex) { return false; }
	} // end isFile()
	
	// prompts for a file
	public static String promptForFile(Component parent, String strtitle) {
    	JFileChooser fc = new JFileChooser();
		fc.setDialogTitle(strtitle);
    	if(fc.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
        	return fc.getSelectedFile().getAbsolutePath();
    	} // end if
    	return null;
	} // end promptForFile()

	public static CJSONObject toJSONObject(String value, boolean bassoc) {
		CJSONObject cjsonobject = new CJSONObject();
		return (cjsonobject == null || cjsonobject.create(value) == false) ? null : cjsonobject;
	} // end toJSONObject()
	
	public static CJSONObject toJSONObjectFromFile(String strfilename, boolean bassoc) {
		CJSONObject cjsonobject = new CJSONObject();
		return (cjsonobject == null || cjsonobject.createFromFile(strfilename) == false) ? null : cjsonobject;
	} // end toJSONObject()
} // end _