//---------------------------------------------------------------------------------
// file: CFunction
// desc: defines a generic class to simulate functions ptr or anonymous functions
//---------------------------------------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------------------
// name: CFunction
// desc: defines a generic class to simulate functions ptr or anonymous functions
//---------------------------------------------------------------------------------
public class CFunction extends CObject {
	protected static CHash m_cfunctions = _.chash();
	public CFunction() {}
	public CFunction(String strname) { CFunction.m_cfunctions._(strname, this); }
	public CArray _(CArray args) { return null; }
	public CArray _(CHash params) { return null; }
	public Object _(Object obj) { return null; }
	public CObject _(CObject obj) { return null; }
	static public CFunction get(String strname) { return CFunction.m_cfunctions._cfunction(strname); }
} // end CFunction