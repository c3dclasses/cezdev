//---------------------------------------------------------------------------------
// file: CLog
// desc: defines a object to log information, warnings and errors
//---------------------------------------------------------------------------------
package cglobal;

//---------------------------------------------------------------------------------
// name: CLog
// desc: defines a object to log information, warnings and errors
//---------------------------------------------------------------------------------
public class CLog {	
	public static void error(Object strerror) { CLog.msg(2, "ERROR: " + strerror.toString()); }
	public static void warn(Object strwarn) { CLog.msg(1, "WARN: " + strwarn.toString()); }
	public static void info(Object strinfo) { CLog.msg(0, "INFO: " + strinfo.toString()); }
	public static String error() { return CLog.msg(2); }
	public static String warn() { return CLog.msg(1); }
	public static String info() { return CLog.msg(0); }
	public static void print() { for(int i=0; i<CLog.m_strmsg.length; i++) _.print(CLog.msg(i)); }
	// helper methods
	protected static String [] m_strmsg = {"","",""};
	protected static String msg(int i) {
		String strmsg = CLog.m_strmsg[i]; 
		if(strmsg == null || strmsg.equals(""))
			return "";
		CLog.m_strmsg[i] = "";
		return strmsg;		 
	} // end msg()
	protected static void msg(int i, String strmsg) { CLog.m_strmsg[i] += strmsg + "\n"; }
} // end CFunction