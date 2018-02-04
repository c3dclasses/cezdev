//---------------------------------------------------------------------------------
// file: CFunction
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------------------
// name: CFunction
// desc: 
//---------------------------------------------------------------------------------
public class CFunction extends CObject {
	public CFunction() {}
	public CFunction(String strname) { CFunction.set(strname, this); }
	public CReturn call(CArray args) { return null; }
	public CReturn call(CHash params) { return null; }
	public CReturn call(Object obj) { return null; }
	protected static CHash m_cfunctions = _.chash();
	protected static CFunction m_error = new CFunction() { 
		public CReturn call(Object obj){ return CReturn._error(null); }
		public CReturn call(CArray args){ return CReturn._error(null); }
		public CReturn call(CHash params){ return CReturn._error(null); }
	}; // end m_null;
	static public CFunction get(String strname) { return CFunction.m_cfunctions._cfunction(strname); }
	static public void set(String strname, CFunction cfunction) { CFunction.m_cfunctions._(strname, cfunction); }
	static public CFunction _error() { return CFunction.m_error; }
} // end CFunction