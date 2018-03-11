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
	public Object m_object = null;
	public CFunction() {}
	public CFunction(String strname) { CFunction.set(strname, this); }
	public CFunction(String strname, Object object) { CFunction.set(strname, this); this.m_object = object; }
	public CReturn call(CArray args) { return null; }
	public CReturn call(CHash params) { return null; }
	public CReturn call(Object obj) { return null; }
	public CReturn call(Object... obj) { return null; }
	public void setObject(Object object) { this.m_object = object; }
	public CFunction bind(Object object) { 
		CFunction cfunction = (CFunction) _._new(this.getClass().getName()); 
		cfunction.setObject(object);
		return cfunction; 
	} // end bind()
	static protected CHash m_cfunctions = _.chash();
	static public CFunction get(String strname) { return CFunction.m_cfunctions._cfunction(strname); }
	static public void set(String strname, CFunction cfunction) { CFunction.m_cfunctions._(strname, cfunction); }
	static public void map(String strname, String strfuncname) { CFunction.set(strname,CFunction.get(strfuncname)); }	
} // end CFunction