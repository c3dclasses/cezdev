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
import java.awt.image.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.*;
import javax.swing.*;
import javax.imageio.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import org.apache.commons.lang.ArrayUtils;

//-----------------------------------------------
// class: _
// desc: global methods used throughout the SDK
//-----------------------------------------------
public class _ {	
	// print, alert, console, confirm
	public static void echo(Object object) { _.print(object); }
	public static void printbr(String str) { _._print(str + "<br />"); }
	public static void printbr() { _.printbr(""); }
	public static void println(Object object) { if(object != null) _._print(object.toString() + "\n"); }
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
	} // end print()
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
	
	// read input, prompting, prompt for a file
	public static Scanner m_scanner = new Scanner(System.in);
	public static String readln() { return (String) m_scanner.nextLine(); }
	public static Object prompt(String strtitle, String strmessage, Object [] choices) {
		int itype = JOptionPane.INFORMATION_MESSAGE;
		Object output = "";
		if(choices == null)
			output = JOptionPane.showInputDialog(null, strmessage, strtitle, itype);
		else output = JOptionPane.showInputDialog(null, strmessage, strtitle, itype, null, choices, choices[0]);
		return (output != null) ? output : null;
	} // end prompt()
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
	public static String prompt_path(String strtitle) {
    	return _.prompt_file("dir", strtitle);
	} // end promptForFile()		

	// time
	public static long time() { return _.get_time_ms(); }
	public static long get_time() { return _.get_time_ms(); }
	public static float get_time_mms() { return System.nanoTime() / 1000; }
	public static long get_time_ms() { return System.currentTimeMillis(); }

	// sleep
	public static void sleep(int milliseconds) { try { Thread.sleep(milliseconds); } catch(InterruptedException ex) {} }
	
	// hash_code
	public static int hash_code(Object object) { return System.identityHashCode(object); }

	// directory, file, path
	public static String dirname(String strfilename) { return _.get_path(strfilename); }
	public static String get_home_path() { 
		try{
			String str = new File(".").getCanonicalPath(); 
			return str; 
		} // end try
		catch(IOException ex){ 
			//CLog.error(ex.toString());
			return ""; 
		} // end catch()
	} // end getHomePath()
	public static String get_path(String strfilename) {
		try {
		 	File f = new File(strfilename);
         	return f.getParentFile().getCanonicalPath();
        } // end try
		catch(Exception ex) {
			//CLog.error(ex.toString());
			return "";
		} // end catch()
	} // end getPath()	
	public static boolean is_directory(String strpathname) {
		try{ File f = new File(strpathname); return (f.exists() && f.isDirectory());}
		catch(Exception ex) { /*CLog.error(ex.toString());*/ return false; }
	} // end isDirectory()
	public static boolean is_file(String strpathname) {
		try{ File f = new File(strpathname); return (f.exists());}
		catch(Exception ex) { /*CLog.error(ex.toString());*/ return false; }
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
	
	
	public static void traverse_path(String strstartpath, CFunction fncallback) {
		traverse_path(new File(strstartpath), fncallback);
	} // end traverse_path()
	
	public static void traverse_path(File dir, CFunction fncallback) {
		try {
			CReturn creturn = fncallback.call((Object)dir);
			//if(creturn._boolean(false))
			//	return;
			if(dir.isDirectory()) {
				String[] children = dir.list();
				for (int i=0; children != null && i<children.length; i++)
					traverse_path(new File(dir,children[i]), fncallback);	
			} // end if
		} // end try
		catch(Exception ex) {
			return;
		} // end catch
	} // end traverse_files()
	 
	// allocation
	public static Object _new(String strclassname) { 
		try { 
			return Class.forName(strclassname).newInstance(); 
		} // end try
		catch(Exception ex) { 
			//CLog.error("Could not construct a new instance of: " + strclassname);		
			//CLog.error(ex.toString());
		} // end catch()
		return null;
	} // end _new() 
	
	public static boolean empty(String str) { return str == null || str.equals(""); }
	
	// parsing	
	public static int parse_int(String str) { return Integer.parseInt(str); }
	public static float parse_float(String str) { return Float.parseFloat(str); }
	
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
	
	public static CArray explode(String strseperator, String strtoseperate) {
		return _.split(strseperator, strtoseperate);
	} // end explode()
	
	// setting / getting file contents	
	public static String file_get_contents(String strfilename) { return _.get_file_contents(strfilename); }
	public static boolean file_set_contents(String strfilename, String strcontents) { return _.set_file_contents(strfilename, strcontents); }
	public static boolean file_exists(String strfilename) { File f = new File(strfilename); return f.exists(); }
	public static CArray get_lines_from_file(String strfilename) { return _.get_lines_from_file(strfilename, false, null); }
	public static CArray load_csv_file(String strfilepathname) { return _.get_lines_from_file(strfilepathname, true, ","); }
	public static boolean save_csv_file(String strfilepathname, CArray carray) { 
		int l1 = carray.length();
		//int j2 = (l > 0 && carray._carray(0)) ? carray._carray(0).length();
		String str = "";
		for(int i=0; i<l1; i++) {
			if(carray._carray(i) != null) {
				str += carray._carray(i).join(",");
				if(i < l1-1)
					str += "\n";
			} // end if
			/*
			for(int j=0; j<l2; j++) {
				str += carray._carray(i)._(j);
				if(j<l2-1)
					str += ",";
			} // end for
			*/
		} // end for
		return _.set_file_contents(strfilepathname, str);
	} // end save_csv_file()
	
	public static CArray get_lines_from_file(String strfilename, boolean bsplitlines, String strdelimiter) {
		if(strfilename == null || strfilename == "")
			return null;
		try{
			CArray lines = _.carray();
			String strline = "";
			LineNumberReader in = new LineNumberReader(new FileReader(strfilename));
			while((strline = in.readLine()) != null) { 
				lines.push((!bsplitlines) ? strline : _.split(strdelimiter, strline)); 
			} // end while()	
			in.close();
			return lines;		
		} // end try
		catch(Exception ex) {
			//CLog.error(ex.toString());
			_.alert(ex.getMessage());
			return null; 
		} // end catch()
	} // end get_lines_from_file()

	/*
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
			//CLog.error(ex.toString());
			return null; 
		} // end catch() 
	} // end getLinesFromFile()	
	*/
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
			_.print(strcontents);	
			in.close();
		} // end try
		catch(Exception ex) {
			//CLog.error(ex.toString());
			_.alert(ex.getMessage());
			return "";
		} // end catch()
		return strcontents;
	} // end getFileContents()
	public static boolean set_file_contents(String strfilename, String strcontents) {
		if(strcontents == null)
			return false;
		try{
			//_.alert("set file contents here");
			FileWriter out = new FileWriter(strfilename);
			out.write(strcontents);
			out.close();
		} // end try
		catch(Exception ex) {
			//CLog.error(ex.toString());
			return false;
		} // end catch() 	
		return true;
	} // end setFileContents()
	
	public static boolean append_file_contents(String strfilename, String strcontents) {
		if(strcontents == null || strcontents == "")
			return false;
		try{
			//_.alert("set file contents here");
			FileWriter out = new FileWriter(strfilename, true);
			out.write(strcontents);
			out.close();
		} // end try
		catch(Exception ex) {
			//CLog.error(ex.toString());
			return false;
		} // end catch() 	
		return true;
	} // end append_file_contents()
	
	public static BufferedImage _load_img_file(String strfilename) {
		try {
			return ImageIO.read(new File(strfilename));
		} // end try 
		catch (IOException e) {
			return null;
		} // end catch()
	} // end load_img_file()
		
	public static CArray load_img_file(String strfilename) {
		BufferedImage img = _._load_img_file(strfilename);
		if(img == null)
			return null;
		int w = img.getWidth();
		int h = img.getHeight();
		CArray cimg = _.carray_c(w, h);
		for(int x=0; x<w; x++) {
			for(int y=0; y<h; y++) {	
				cimg._carray(x)._(y, (Object)(img.getRGB(x,y) & 0xFF));
			} // end for
		} // end for
		return cimg;
	} // end load_img_file()
	
	public static boolean save_img_file(String stroutfilename, String strformat, CArray img) {
		int w = img.length();
		int h = img._carray(0).length();
		BufferedImage bimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		if(bimg == null)
			return false;
		for(int x=0; x<w; x++) {
			for(int y=0; y<h; y++) {
				int graylevel = img._carray(x)._int(y);
				int gray = (graylevel << 16) + (graylevel << 8) + graylevel; 
				bimg.setRGB(x,y,gray);
			} // end for
		} // end for
		return _.save_img_file(stroutfilename, strformat, bimg);
	} // end 
		
	public static boolean save_img_file(String stroutfilename, String strformat, BufferedImage img) {
		try {
			ImageIO.write(img, strformat, new File(stroutfilename));
			return true;
		} // end try 
		catch (IOException e) {
			return false;
		} // end catch()
	} // end load_img_file()

		

	// runs a command asynchoronously
	public static boolean exec_command(String strcommand) {
		try { 
			Process p = Runtime.getRuntime().exec(strcommand); 
			int returnCode = p.waitFor(); 
			//CJSONUnitTest.info("_.exec_command(): executed command: " + strcommand);	
			return true; 
		} // end try
		catch(Exception ex) { 
			//CLog.info("_.exec_command(): couldn't execute command: " + strcommand);
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
	
	// chash, carray, cobject, args, params, obj
	public static CArray carray_c(int... capacity) { return new CArray(capacity); }
	public static CArray carray() { return new CArray(); }
	public static CArray carray(Object... objects) { return new CArray(objects); }
	public static CHash chash() { return new CHash(); }
	public static CHash chash(CHash chash) { return (chash == null) ? new CHash() : chash; }
	public static CHash chash(Object... objects) { return new CHash(objects); }	
	public static CObject cobject() { return new CObject(); }
	public static CObject cobject(Object... objects) { return new CObject(objects); }
	public static CFunction cfunction(String strname) { return CFunction.get(strname); }
	public static CArray args(Object... args) { return _.carray(args); }
	public static CArray args() { return _.carray(); }
	public static CArray a() { return new CArray(); }
	public static CArray a(Object... objects) { return new CArray(objects); }
	public static CHash params(Object... params) { return _.chash(params); }
	public static CHash params() { return _.chash(); }	
	public static CHash h(Object... params) { return _.chash(params); }
	public static CHash h() { return _.chash(); }	
	public static CObject obj(Object... namevalues){ return _.cobject(namevalues); }
	public static CObject obj(){ return _.cobject(); }
	public static CObject o(Object... namevalues){ return _.cobject(namevalues); }
	public static CObject o(){ return _.cobject(); }
	public static CFunction func(String strname) { return _.cfunction(strname); }
	
	// vectors and matrices
	public static CVector cvector() { return new CVector(); }
	public static CVector cvector(CVector cvector) { return new CVector(cvector); }
	public static CVector cvector(Object... objects) { return new CVector(objects); }
	public static CVector cvector(int size) { return new CVector(size); }
	public static CMatrix cmatrix() { return new CMatrix(); }
	public static CMatrix cmatrix(CMatrix cmatrix) { return new CMatrix(cmatrix); }
	public static CMatrix cmatrix(CVector... cvector) { return new CMatrix(cvector); }
	public static CMatrix cmatrix(int nrows, int ncols) { return new CMatrix(nrows, ncols); }
	
	
	// interval / timeout
	public static int setInterval(CFunction cfunction, int imilliseconds) { return CIntervalConcurrentEvent.setInterval(cfunction, imilliseconds); }
	public static void clearInterval(int id) { CIntervalConcurrentEvent.clearInterval(id); }
	public static int setTimeout(CFunction cfunction, int imilliseconds) { return CTimeoutConcurrentEvent.setTimeout(cfunction, imilliseconds); }
	public static void clearTimeout(int id) { CTimeoutConcurrentEvent.clearInterval(id); }	
	
	// driver code
	public static void include_driver(String strdriverclassname) { CDriver.include(strdriverclassname); }	
	
	// file / dir
	public static CHash m_filetodir = null; 
	public static String file_path(String strfilename) {		
		if(_.m_filetodir == null)
			_.m_filetodir = CJSON.toCHash(_.file_get_contents(_.get_home_path() + "/c3dclasses-src.json"));
		return (_.m_filetodir != null) ? _.m_filetodir._string(strfilename) : "";	
	} // end file()
	public static String file_path(Object object) { return _.file_path(object.getClass().getSimpleName() + ".java"); }
	public static String dir_path(String strfilename) { return _.get_path(_.file_path(strfilename)); }
	public static String dir_path(Object object) { return _.get_path(_.file_path(object)); }
	
	// out
	public static void out(String strfilename, String strcontents) { _.file_set_contents(strfilename, strcontents);}
	public static void out_append(String strfilename, String strcontents) { _.append_file_contents(strfilename, strcontents);}
	public static void outln(String strfilename, String strcontents) { _.out_append(strfilename, strcontents + "\n");}
	public static void out(Object object, Object contents) { 
		String strpath = _.dir_path(object.getClass().getSimpleName() + ".java");
		String strfilename = object.getClass().getSimpleName() + ".out";
		_.out(strpath + "/" + strfilename, contents.toString());
	} // end out()
	public static void out_append(Object object, Object contents) { 
		String strpath = _.dir_path(object.getClass().getSimpleName() + ".java");
		String strfilename = object.getClass().getSimpleName() + ".out";
		_.out_append(strpath + "/" + strfilename, contents.toString());
	} // end out_append()
	public static void outln(Object object, Object contents) { _.out_append(object, contents + "\n"); }
} // end 