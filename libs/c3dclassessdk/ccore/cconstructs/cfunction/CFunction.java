//---------------------------------------------------------------------------------
// file: CFunction
// desc: defines a generic class to simulate functions ptr or anonymous functions
//---------------------------------------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------------------
// name: CFunction
// desc: defines a generic class to simulate functions ptr or anonymous functions
//---------------------------------------------------------------------------------
public class CFunction extends CHash {
	public CArray _(CArray args) { return null; }
	public CArray _(CHash params) { return null; }
	//public CArray _() { return null; }
	//protected Object m_params;
	//public CFunction() { this.m_params = null; }
	//public Object _(Object params) { return null; }
	//public Object __(Object params) { this.m_params = params; return this._(params);} 
	//public CFunction ___(Object params) { this.m_params = this.__(params); return this; } 
	//public int _int() { return Integer.valueOf(this.m_params.toString()); }
	//public float _float() { return Float.valueOf(this.m_params.toString()); }
	//public String _string() { return (String) this.m_params; }
	//public boolean _boolean() { return Boolean.valueOf(this.m_params.toString()); }
	//public CArray _carray() { return (CArray) this.m_params; }
	//public CHash _chash() { return (CHash) this.m_params; }
} // end CFunction