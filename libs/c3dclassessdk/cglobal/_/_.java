//-------------------------------------------------------------------------
// file: _.java
// desc: contains global functions exdev and c3dclasses namespace/package
//-------------------------------------------------------------------------
package c3dclasses;
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
import org.apache.commons.lang.ArrayUtils;

//-----------------------------------------------
// class: _
// desc: global methods used throughout the SDK
//-----------------------------------------------
public class _ {	
	// printing
	public static void printbr(String str) { _._print(str + "<br />"); }
	public static void printbr() { _.printbr(""); }
	public static void println(Object object) { if(object != null) _._print(object.toString() + "\n"); }
	public static void echo(Object object) { _.print(object); }
	public static void println(){ _.println(""); }
	public static void printjs(Object strjstoprint) { _._print("<script>" + strjstoprint.toString() + "</script>"); }
	public static void print(Object str) { _._print(str); }
	public static void print(Object[] str) { _.print(str, ","); }
	public static void print(Object[] str, String delimiter) { 
		for(int i=0; i<str.length; i++) {		
			_.print(str[i]);
			if(i < str.length-1)
				_.print(delimiter);
		} // end for
	} // end printArray()
	
	public static void _print(Object str) { if(str != null) System.out.print(str); }
	public static void alert(Object strmessage) { _.alert(strmessage, true); }
	public static void alert(Object strmessage, String strtitle) { alert(strmessage.toString(), strtitle); }
	public static void alert(String strmessage, String strtitle) {
		strmessage = (strmessage == "" || strmessage == null) ? "  " : strmessage;
		strtitle = (strtitle == "" || strtitle == null) ? "  " : strtitle;
		JOptionPane.showMessageDialog(null, strmessage, strtitle, JOptionPane.INFORMATION_MESSAGE);
	} // end alert
	public static void alert(Object strmessage, boolean bscript) { 
		JOptionPane.showMessageDialog ( null, 
			(strmessage != null) ? strmessage.toString() : "", 
			"Alert", JOptionPane.INFORMATION_MESSAGE
		); // end JOptionPane.showMessageDialog
	} // end alert()
	public static void console(Object strmessage, boolean bscript) { 
		if(bscript) 
			_.print("<script parse=\"false\">"); 
		_.print("console.log('" + strmessage + "');"); 
		if(bscript) 
			_.print("</script>");
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
	public static String print_r(Object object) { return _.print_r(object,false); } 
	
	// input	
	public static Scanner m_scanner = new Scanner (System.in);
	public static String readln() { return (String) m_scanner.nextLine(); }
	// prompt for a string from a user
	public static Object prompt(String strtitle, String strmessage, Object [] choices) {
		int itype = JOptionPane.INFORMATION_MESSAGE;
		Object output = "";
		if(choices == null)
			output = JOptionPane.showInputDialog(null, strmessage, strtitle, itype);
		else output = JOptionPane.showInputDialog(null, strmessage, strtitle, itype, null, choices, choices[0]);
		return (output != null) ? output : null;
	} // end prompt()
	// prompts for a file from user
	public static String prompt_file(String strtype, String strtitle) {
		strtitle = (strtitle != null) ? strtitle : "Open";		
		strtype = (strtype != null) ? strtype : "file";
		JFileChooser fileChooser = new JFileChooser();
		if(strtype.equals("dir")) // select a directory instead of file
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle(strtitle);
        int result = fileChooser.showOpenDialog(null);
        return (result == JFileChooser.APPROVE_OPTION) ? fileChooser.getSelectedFile().getPath() : null;
	} // end filechooser()
	// prompts for a path from the user
	public static String prompt_path(String strtitle) {
    	return _.prompt_file("dir", strtitle);
	} // end promptForFile()
	
	// time
	public static long time() { return _.get_time_ms(); }
	public static long get_time() { return _.get_time_ms(); }
	public static float get_time_mms() { return System.nanoTime() / 1000; }
	public static long get_time_ms() { return System.currentTimeMillis() % 1000; }

	// file and path
	public static String dirname(String strfilename) { return _.get_path(strfilename); }
	public static String get_home_path() { 
		try{
			String str = new File(".").getCanonicalPath(); 
			return str; 
		} // end try
		catch(IOException ex){ 
			CLog.error(ex.toString());
			return ""; 
		} // end catch()
	} // end getHomePath()
	public static String get_path(String strfilename) {
		try {
		 	File f = new File(strfilename);
         	return f.getParentFile().getCanonicalPath();
        } // end try
		catch(Exception ex) {
			CLog.error(ex.toString());
			return "";
		} // end catch()
	} // end getPath()	
	public static boolean is_directory(String strpathname) {
		try{ File f = new File(strpathname); return (f.exists() && f.isDirectory());}
		catch(Exception ex) { CLog.error(ex.toString()); return false; }
	} // end isDirectory()
	
	public static boolean is_file(String strpathname) {
		try{ File f = new File(strpathname); return (f.exists());}
		catch(Exception ex) { CLog.error(ex.toString()); return false; }
	} // end isFile()	
	 
	// file meta methods _.METHOD__(), _.CLASS__(), _.LINE__() similar to __line__ in C++
    public static int LINE__() { return Thread.currentThread().getStackTrace()[0].getLineNumber(); }
    public static String METHOD__() { return Thread.currentThread().getStackTrace()[0].getMethodName(); }
    public static String CLASS__() { return Thread.currentThread().getStackTrace()[0].getClassName(); }
	public static String PATH__(String strclassname) { return _.CLASS__(); }
	public static String FILENAME__() { return Thread.currentThread().getStackTrace()[2].getFileName(); }
	public static String FILE__() {
		String _class = Thread.currentThread().getStackTrace()[2].getClassName();
		String _filename = Thread.currentThread().getStackTrace()[2].getFileName();
		return _.get_home_path() + "\\src\\main\\java\\" + _class.replace(".", "\\");
	} // end FILE__()
    public static String testOne() {
        return "testOne:: " + " File:" + _.FILE__() + " Class:" + _.CLASS__()
                + " Method:" + _.METHOD__() + " Line:" + _.LINE__();
    } // end testOne()
	
	// allocation
	public static Object _new(String strclassname) { 
		try { 
			return Class.forName(strclassname).newInstance(); 
		} // end try
		catch(Exception ex) { 
			CLog.error("Could not construct a new instance of: " + strclassname);		
			CLog.error(ex.toString());
		} // end catch()
		return null;
	} // end _new() 
	public static boolean empty(String str) { return str == null || str.equals(""); }
	
	// parsing	
	public static int parse_int(String str) { return Integer.parseInt(str); }
	public static float parse_float(String str) { return Float.parseFloat(str); }
	public static CJSONObject to_json_object(String value) {
		CJSONObject cjsonobject = new CJSONObject();
		return (cjsonobject == null || cjsonobject.create(value) == false) ? null : cjsonobject;
	} // end toJSONObject()
	public static CJSONObject to_json_object_from_file(String strfilename, boolean bassoc) {
		CJSONObject cjsonobject = new CJSONObject();
		return (cjsonobject == null || cjsonobject.createFromFile(strfilename) == false) ? null : cjsonobject;
	} // end toJSONObject()
	public static CJSONArray to_json_array(String value, boolean bassoc) {
		CJSONArray cjsonarray = new CJSONArray();
		return (cjsonarray == null || cjsonarray.create(value) == false) ? null : cjsonarray;
	} // end toJSONArray()
	public static CJSONArray to_json_array_from_file(String strfilename, boolean bassoc) {
		CJSONArray cjsonarray = new CJSONArray();
		return (cjsonarray == null || cjsonarray.createFromFile(strfilename) == false) ? null : cjsonarray;
	} // end toJSONArray()
	public static Object json_decode_from_file(String strfilename, boolean bchash) {
		CJSONObject cjsonobject = _.to_json_object_from_file(strfilename, false);
		if(cjsonobject == null)
			return null;
		return (bchash == false) ? cjsonobject : cjsonobject.toCHash();
	} // end json_decode()
	public static Object json_decode(String strcontents, boolean bchash) {
		CJSONObject cjsonobject = _.to_json_object(strcontents);	
		if(cjsonobject == null)
			return null;
		return (bchash == false) ? cjsonobject : cjsonobject.toCHash();
	} // end json_decode()
	public static String json_encode(CHash inchash, boolean bpack) { 
		return (bpack) ? _.json_encode_pack(inchash) : _.json_encode(inchash, ""); 
	} // end json_encode()
	public static String json_encode(CHash inchash, String tabs) {
		CArray keys = inchash.keys();
		int len = keys.length();
		String str = "{\n";
		for(int i=0; i<len; i++) {
			Object key = keys._(i);
			Object value = inchash._(key);
			String comma_nl = ",\n";
			if(inchash == value) 
				str += tabs + "\t\"" + key + "\":" + System.identityHashCode(inchash);
			else if(value instanceof CArray) {
				CArray carray = (CArray) value;
				str += tabs + "\t\"" + key + "\":[\n";
				for(int j=0; j<carray.length(); j++) {
					if(carray._(j) instanceof CHash) {
						CHash chash = (CHash) carray._(j);
						str += tabs + "\t\t" + _.json_encode(chash, tabs + "\t\t");
					} // end if
					else str += tabs + "\t\t" + carray._(j);		
					if(j+1 != carray.length())
						str += comma_nl;
					else str += "\n";
				} // end for
				str += tabs + "\t]\n";
			} // end else if
			else if(value instanceof CHash) {
				CHash chash = (CHash) value;
				str += tabs + "\t\"" + key + "\":" + _.json_encode(chash, tabs + "\t");		
			} // end else if
			else if(value instanceof String) {
			 	str += tabs + "\t\"" + key + "\":\"" + inchash._(key) + "\"";
			} // end else if
			else {
			 	str += tabs + "\t\"" + key + "\":" + inchash._(key);
			} // end else if
			if(i+1 != len)
				str += comma_nl;
			else str += "\n";		
		} // end for
		str += tabs + "}";
		return str; 
	} // end json_encode()	
	
	public static String json_encode_pack(CHash inchash) {
		CArray keys = inchash.keys();
		int len = keys.length();
		String str = "{";
		for(int i=0; i<len; i++) {
			Object key = keys._(i);
			Object value = inchash._(key);
			String comma_nl = ",";
			if(inchash == value) 
				str += "\"" + key + "\":" + System.identityHashCode(inchash);
			else if(value instanceof CArray) {
				CArray carray = (CArray) value;
				str += "\"" + key + "\":[";
				for(int j=0; j<carray.length(); j++) {
					if(carray._(j) instanceof CHash) {
						CHash chash = (CHash) carray._(j);
						str += _.json_encode_pack(chash);
					} // end if
					else str += (String) carray._(j);		
					if(j+1 != carray.length())
						str += comma_nl;
					else str += "";
				} // end for
				str += "]";
			} // end else if
			else if(value instanceof CHash) {
				CHash chash = (CHash) value;
				str += "\"" + key + "\":" + _.json_encode_pack(chash);		
			} // end else if
			else if(value instanceof String) {
			 	str += "\"" + key + "\":\"" + inchash._(key) + "\"";
			} // end else if
			else {
			 	str += "\"" + key + "\":" + inchash._(key);
			} // end else if
			if(i+1 != len)
				str += comma_nl;
			else str += "";		
		} // end for
		str += "}";
		return str; 
	} // end json_encode()	

	// string imploding / exploding / splitting / combining	
	public static CArray split(String strseperator, String strtoseperate) {
		if(strseperator == "" ||  strtoseperate == "")
			return null;
		CArray strings = new CArray();
		StringTokenizer st = new StringTokenizer(strtoseperate, strseperator);
		while(st.hasMoreTokens()) 
			strings.push(st.nextToken());
		return strings;	
	} // end split()
	
	// setting / getting file contents	
	//public static String file_get_contents(String strfilename) { return _.get_file_contents(strfilename); }
	//public static boolean file_set_contents(String strfilename, String strcontents) { return _.setFileContents(strfilename, strcontents); }
	public static boolean file_exists(String strfilename) { File f = new File(strfilename); return f.exists(); }
	public static ArrayList<String> get_lines_from_file(String strfilename) {
		if(strfilename == null || strfilename == "")
			return null;
		try{
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
			CLog.error(ex.toString());
			return null; 
		} // end catch() 
	} // end getLinesFromFile()	
	public static String get_file_contents(String strfilename) {
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
			CLog.error(ex.toString());
			return "";
		} // end catch()
		return strcontents;
	} // end getFileContents()
	public static boolean set_file_contents(String strfilename, String strcontents) {
		if(strcontents == null || strcontents == "")
			return false;
		try{
			FileWriter out = new FileWriter(strfilename);
			out.write(strcontents);
			out.close();
		} // end try
		catch(Exception ex) {
			CLog.error(ex.toString());
			return false;
		} // end catch() 	
		return true;
	} // end setFileContents()
		
	// runs a command asynchoronously
	public static boolean exec_command(String strcommand) {
		try { 
			Process p = Runtime.getRuntime().exec(strcommand); 
			int returnCode = p.waitFor(); 
			CLog.info("_.exec_command(): executed command: " + strcommand);	
			return true; 
		} // end try
		catch(Exception ex) { 
			CLog.info("_.exec_command(): couldn't execute command: " + strcommand);
			return false; 
		} // end catch()
	} // end exec_command()
	public static boolean exec_async_command(String strcommand) {
		try { 
			Process p = Runtime.getRuntime().exec(strcommand); 
			System.out.println("SUCCESS: _.execCommand(): executed command: " + strcommand); 
			return true; 
		} // end try
		catch(Exception ex) { 
			System.out.println("ERROR: _.execCommand(): couldn't execute command: " + strcommand + " reason: " + ex.getMessage());
			return false; 
		} // end catch()
	} // end exec_async_command()
	
	// returns true if pid is running
	public static boolean is_pid_running(long pid) {
        try {
            Runtime runtime = Runtime.getRuntime();
            String cmds[] = {"cmd", "/c", "tasklist /FI \"PID eq " + pid + "\""};
            Process proc = runtime.exec(cmds);
            InputStream inputstream = proc.getInputStream();
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
            String line;
            while ((line = bufferedreader.readLine()) != null) {
                if (line.contains(" " + pid + " ")) {
                    return true;
                } // end if
            } // end while
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Cannot query the tasklist for some reason.");
            System.exit(0);
        } // end catch
        return false;
    } // end is_pid_running()
	
	// prints all the running pids
    public static String to_str_pids() {
        try {
            Runtime runtime = Runtime.getRuntime();
            String cmds[] = {"cmd", "/c", "tasklist"};
            Process proc = runtime.exec(cmds);
            InputStream inputstream = proc.getInputStream();
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
            String line;
			String str = "";
            while ((line = bufferedreader.readLine()) != null) {
                str += line + "\n";
            } // end while
			return str;
        } // end try 
		catch (Exception ex) {
            ex.printStackTrace();
       		return "";
	    } // end catch()
    } // end to_str_pids()
	
	// returns pid of process
	public static long get_pid() { 
		String jvmName = ManagementFactory.getRuntimeMXBean().getName(); 
		return Long.valueOf(jvmName.split("@")[0]); 
	} // end get_pid()
	
	// packs the arguments and returns an array
	public static CArray args(Object... args) { return new CArray(args); }
	public static CArray args() { return new CArray(); }
	
	public static CArray params(Object... params) { return new CArray(params); }
	public static Object [] obj(int [] a) { 
		Object [] obj = new Object[a.length];
		for(int i=0; i<a.length; i++)
			obj[i] = new Integer(a[i]);
		return obj;
	} // end _obj()
		
	
	// chash, cpair, carray
	public static CArray carray() { return new CArray(); }
	public static CArray carray(Object... objects) { return new CArray(objects); }
	public static CHash chash() { return new CHash(); }
	public static CHash chash2(CHash chash) { return (chash == null) ? new CHash() : chash; }
	public static CHash chash(CHash chash) { return (chash == null) ? new CHash() : chash; }
	public static CHash chash(CPair... cpairs) { return new CHash(cpairs); }
	public static CPair cpair(Object first, Object second) { return new CPair(first, second); }
	public static CPair nv(Object first, Object second) { return new CPair(first, second); }	
	public static CObject cobject() { return new CObject(); }
	public static CObject cobject(Object... namevalues) {
		if(namevalues == null || namevalues.length == 0)
			return null;
		CObject cobject = new CObject();
		if(cobject == null)
			return null;
		for(int i=0; i<namevalues.length; i+=2) {
			String name = (String)namevalues[i];
			Object value = namevalues[i+1];
			cobject._(name, value);		
		} // end for()
		return cobject;
	} // end cobject()
} // end _