//------------------------------------------------------------------------------------------------
// name: CCast
// desc: 
//------------------------------------------------------------------------------------------------
package c3dclasses;

//-----------------------------------------------------------------------------
// name: CCast
// desc: 
//-----------------------------------------------------------------------------
public class CCast {	
	// simple
	public CCast set(Object data) { return this; } 
	public Object get() { return null; }
	public boolean _boolean() { return Boolean.valueOf(this.get().toString()); }
	public int _int() { return Integer.valueOf(this.get().toString()); }
	public float _float() { return Float.valueOf(this.get().toString()); }
	public String _string() { return (String)this.get().toString(); }
	public CArray _carray() { return (CArray)this.get(); }
	public CHash _chash() { return (CHash)this.get(); }
	public CFunction _cfunction() { return (CFunction)this.get(); }
	public boolean _nan() { return this._is_nan(this.get()); }
	
	// indexed
	public CCast set(int iindex, Object object) { return this; }
	public Object get(int iindex) { return null; }
	public boolean _boolean(int index) { return Boolean.valueOf(this.get(index).toString()); }
	public int _int(int index) { return Integer.valueOf(this.get(index).toString()); } 
	public long _long(int index) { return Long.parseLong(this.get(index).toString()); }
	public double _double(int index) { return Double.valueOf(this.get(index).toString()); } 
	public float _float(int index) { return Float.valueOf(this.get(index).toString());} 
	public String _string(int index) { return (String)this.get(index); }
	public Object [] _array(int index){ return (Object [])this.get(index); }	
	public CArray _carray(int index) { return (CArray) this.get(index); }
	public CHash _chash(int index) { return (CHash)this.get(index); }
	public CObject _cobject(int index) {return (CObject) this.get(index); }
	public CFunction _cfunction(int index) {return (CFunction) this.get(index); }
	public CReturn _creturn(int index) {return (CReturn) this.get(index); }
	public CArray _explode(int index, String delimiter) { return _.explode(delimiter,this._string(index)); } 
	public boolean _nan(int index) { return this._is_nan(this.get(index)); }
	public CVector _cvector(int index) {return (CVector) this.get(index); }
	public CMatrix _cmatrix(int index) {return (CMatrix) this.get(index); }
	
	// hashed
	public CCast  set(Object key, Object value) { return this; }
	public Object get(Object key) { return null; }
	public Object _object(Object key) { return this.get(key); }
	public int _int(Object key) { return Integer.valueOf(this.get(key).toString()); }
	public long _long(Object key) { return Long.parseLong(this.get(key).toString()); }
	public float _float(Object key) { return Float.valueOf(this.get(key).toString()); }
	public double _double(Object key) { return Double.valueOf(this.get(key).toString()); }
	public String _string(Object key) { return (String) this.get(key); }
	public boolean _boolean(Object key) { return Boolean.valueOf(this.get(key).toString()); }
	public Object [] _array(Object key) { return (Object [])this.get(key); }
	public CHash _chash(Object key) {return (CHash) this.get(key); }
	public CObject _cobject(Object key) {return (CObject) this.get(key); }
	public CArray _carray(Object key) {return (CArray) this.get(key); }
	public CFunction _cfunction(Object key) {return (CFunction) this.get(key); }
	public CReturn _creturn(Object key) {return (CReturn) this.get(key); }
	public CArray _explode(Object key, String delimiter) { return _.explode(delimiter, this._string(key)); }
	public boolean _nan(Object key) { return this._is_nan(this.get(key)); }
	public CVector _cvector(Object key) {return (CVector) this.get(key); }
	public CMatrix _cmatrix(Object key) {return (CMatrix) this.get(key); }	
	
	// helper
	public CCast get(Object...params) {
		CCast ccast = null;
		Object object = null;
		int l = params.length;
		for(int i=0; i<l; i++) {
			if(params[i] instanceof Integer)
				object = ccast.get(params[i]);
			else if(params[i] instanceof String)
				object = ccast.get((String)params[i]);
			else return null;
			if(object == null || object instanceof CCast == false)
				return null;
			ccast = (CCast) object;
		} // end for()
		return ccast;
	} // end get()
	public boolean _is_int(Object object) { try { Integer.parseInt(object.toString()); return true;} catch(Exception ex) { return false; }}
	public boolean _is_long(Object object) { try { Long.parseLong(object.toString()); return true;} catch(Exception ex) { return false; }}
	public boolean _is_double(Object object) { try { Double.parseDouble(object.toString()); return true;} catch(Exception ex) { return false; }}
	public boolean _is_float(Object object) { try { Float.parseFloat(object.toString()); return true;} catch(Exception ex) { return false; }}
	public boolean _is_nan(Object o) { return (!this._is_int(o) && !this._is_double(o) && !this._is_float(o) && !this._is_long(o)); }
} // end CCast