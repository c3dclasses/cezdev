//-----------------------------------------------------------------------------------------------
// file: CReturn.java
// desc: defines constructs to use to simulate feature found in multithread programming lang 
//-----------------------------------------------------------------------------------------------
package c3dclasses;

//--------------------------------------------------------------------------------
// name: CReturn
// desc: defines a return contruct or statement for asynchonous methods
// 		 its used in cdatastream objects that use ajax operations
//--------------------------------------------------------------------------------
public class CReturn extends CObject {
	public CReturn() { 
		this._("m_data", null);
		this._("m_fnformat", null);
		this._("m_strerror", "");
		this._("m_icode", CReturn.NULL);
		this._("m_strstatus", "");
	} // end CReturn()
	
	public void error(String strerror) { this._("m_strerror", strerror); } 
	public String error() { return (String) this._("m_strerror"); }
	
	public void code(int code) { this._("m_icode", code); }
	public int code() { return this._int("m_icode"); }
	
	public void status(String strstatus) { this._("m_strstatus", strstatus); } 
	public String status() { return (String) this._("m_strstatus"); }
	
	public void data(Object data) { this._("m_data", data); } 
	public Object data() { return this._("m_data"); }
	
	public Object results() { return this.data();}
	public boolean _boolean() { return Boolean.valueOf(this.data().toString()); }
	public int _int() { return Integer.valueOf(this.data().toString()); }
	public float _float() { return Float.valueOf(this.data().toString()); }
	public String _string() { return (String)this.data().toString(); }
	
	public boolean isdone() { return this._int("m_icode")  ==  CReturn.DONE; }
	public boolean isbusy() { return this._int("m_icode") == CReturn.BUSY; }
	public boolean isnull() { return this._int("m_icode") == CReturn.NULL; }
	public boolean iserror() { return this._int("m_icode") == CReturn.ERROR; } 
	
	public CReturn _(int code, Object data) { this.code(code); this.data(data); return this; }
	public CReturn done(Object data) { return this._(CReturn.DONE, data); } 
	public CReturn busy() { return this._(CReturn.BUSY, null); }
	
	// ClassMethods
	public static int NULL = 0;		// the function/method is not called yet
	public static int BUSY = 1;		// the function/method has been called and it's busy
	public static int DONE = 2;		// the function/method has been called and it's done	
	public static int ERROR = 3;	// the function/method has been called and it has an error
	public static CReturn _return(int code, Object data) {
		CReturn _return = new CReturn();
		if(_return == null)
			return null;
		_return.code(code);
		_return.data(data);
		return _return;
	} // end _return()

	public static CReturn _done(Object data) {
		return CReturn._return(CReturn.DONE, data);
	} // end _done()

	public static CReturn _busy() {
		return CReturn._return(CReturn.BUSY, null);
	} // end _busy()
	
	public static CReturn _error() {
		return CReturn._return(CReturn.ERROR, null);
	} // end _busy()
 	// end ClassMethods
} // end CReturn()