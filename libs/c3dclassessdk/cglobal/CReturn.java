//-----------------------------------------------------------------------------------------------
// file: CReturn.java
// desc: defines constructs to use to simulate feature found in multithread programming lang 
//-----------------------------------------------------------------------------------------------
package cglobal;

//--------------------------------------------------------------------------------
// name: CReturn
// desc: defines a return contruct or statement for asynchonous methods
// 		 its used in cdatastream objects that use ajax operations
//--------------------------------------------------------------------------------
public class CReturn {
	protected Object m_data;
	protected CFunction m_fnformat;
	protected String m_strerror;
	protected int m_icode;
	protected String m_strstatus;
	
	public CReturn() { 
		this.m_data = null;
		this.m_fnformat = null;
		this.m_strerror = "";
		this.m_icode = CReturn.NULL;
		this.m_strstatus = "";
	} // end CReturn()
	
	public String error(String strerror) {
		if(strerror != null || strerror != "")
			this.m_strerror = strerror;
 		return this.m_strerror; 
	} // end error()
	
	public int code(int code) { 
		this.m_icode = code;
 		return this.m_icode; 
	} // end status();
	
	public String status(String strstatus) { 
		if(strstatus != null || strstatus != "")
			this.m_strstatus = strstatus;
 		return this.m_strstatus; 
	} // end status();
	
	public Object data(Object data) {
		if(data != null)
			this.m_data = data;	
		return (this.m_fnformat != null) ? this.m_fnformat._(this.m_data) : this.m_data; 
	} // end data()

	public Object results() {
		return this.data(null);
	} // end results
	
	public CFunction formatefn(CFunction fn) {
		if(fn != null)
			this.m_fnformat = fn;
		return this.m_fnformat;
	} // end format() 
	
	public boolean isdone() { 
		return this.m_icode  ==  CReturn.DONE;
	} // end isdone()
	
	public boolean isbusy() {
		return this.m_icode == CReturn.BUSY;
	} // end isbusy()
	
	public boolean isnull() {
		return this.m_icode == CReturn.NULL;
	} // end isnull()
	
	public boolean iserror() {
		return this.m_icode == CReturn.ERROR;
	} // end isnull()

	public CReturn _(int code, Object data) {
		this.code(code);
		this.data(data);
		return this;
	} // end _return()

	public CReturn done(Object data) {
		return this._(CReturn.DONE, data);
	} // end _done()

	public CReturn busy() {
		return this._(CReturn.BUSY, null);
	} // end _busy()
	
	// ClassMethods
	public static int NULL = 0;		// the function/method is not called yet
	public static int BUSY = 1;		// the function/method has been called and it's busy
	public static int DONE = 2;		// the function/method has been called and it's done	
	public static int ERROR = 3;	// the function/method has been called and it has an error

	static public CReturn _return(int code, Object data) {
		CReturn _return = new CReturn();
		if(_return == null)
			return null;
		_return.code(code);
		_return.data(data);
		return _return;
	} // end _return()

	static public CReturn _done(Object data) {
		return CReturn._return(CReturn.DONE, data);
	} // end _done()

	static public CReturn _busy() {
		return CReturn._return(CReturn.BUSY, null);
	} // end _busy()
 	// end ClassMethods
} // end CReturn()