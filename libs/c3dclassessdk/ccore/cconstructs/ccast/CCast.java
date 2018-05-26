//------------------------------------------------------------------------------------------------
// name: CCast
// desc: 
//------------------------------------------------------------------------------------------------
package c3dclasses;
import java.math.BigDecimal;

//-----------------------------------------------------------------------------
// name: CCast
// desc: 
//-----------------------------------------------------------------------------
public class CCast {	

	///////////////
	// no param
	public CCast set(Object data) { return this; } 
	public Object get() { return null; }
	public boolean _boolean() { return Boolean.valueOf(this.get().toString()); }
	public int _int() { return Integer.valueOf(this.get().toString()); }
	public float _float() { return Float.valueOf(this.get().toString()); }
	public String _string() { return (String)this.get().toString(); }
	public CArray _carray() { return (CArray)this.get(); }
	public CHash _chash() { return (CHash)this.get(); }
	public CFunction _cfunction() { return (CFunction)this.get(); }
	public CVector _cvector() { return (CVector)this.get(); }
	public CMatrix _cmatrix() { return (CMatrix)this.get(); }
	public boolean _nan() { return this._is_nan(this.get()); }
	public boolean _is_string() { return this.get() instanceof String; }
	public boolean _is_carray() { return this.get() instanceof CArray; }
	public boolean _is_chash() { return this.get() instanceof CHash; }
	public boolean _is_cfunction() { return this.get() instanceof CFunction; }
	public boolean _is_cvector() { return this.get() instanceof CVector; }
	public boolean _is_cmatrix() { return this.get() instanceof CMatrix; }
	
	//////////////////
	// indexed param
	public CCast set(int iindex, Object object) { return this; }
	public Object get(int iindex) { return null; }
	public boolean _boolean(int index) { return Boolean.valueOf(this.get(index).toString()); }
	public int _int(int index) { return Integer.valueOf(this.get(index).toString()); } 
	public long _long(int index) { return Long.parseLong(this.get(index).toString()); }
	public double _double(int index) { return Double.valueOf((new BigDecimal(this.get(index).toString())).toPlainString()); } 
	public float _float(int index) { return Float.valueOf((new BigDecimal(this.get(index).toString())).toPlainString());} 
	public String _string(int index) { return (String)this.get(index); }
	public Object [] _array(int index){ return (Object [])this.get(index); }	
	public CArray _carray(int index) { return (CArray) this.get(index); }
	public CHash _chash(int index) { return (CHash)this.get(index); }
	public CObject _cobject(int index) {return (CObject) this.get(index); }
	public CFunction _cfunction(int index) {return (CFunction) this.get(index); }
	public CReturn _creturn(int index) {return (CReturn) this.get(index); }
	public CMatrix _cmatrix(int index) {return (CMatrix) this.get(index); }
	public CVector _cvector(int index) {return (CVector) this.get(index); }
	public CArray _explode(int index, String delimiter) { return _.explode(delimiter,this._string(index)); } 
	public boolean _nan(int index) { return this._is_nan(this.get(index)); }
	public boolean _is_string(int index) { return this.get(index) instanceof String; }
	public boolean _is_carray(int index) { return this.get(index) instanceof CArray; }
	public boolean _is_chash(int index) { return this.get(index) instanceof CHash; }
	public boolean _is_cfunction(int index) { return this.get(index) instanceof CFunction; }
	public boolean _is_cvector(int index) { return this.get(index) instanceof CVector; }
	public boolean _is_cmatrix(int index) { return this.get(index) instanceof CMatrix; }
	
	///////////////////
	// hashed params
	public CCast  set(Object key, Object value) { return this; }
	public Object get(Object key) { return null; }
	public Object _object(Object key) { return this.get(key); }
	public int _int(Object key) { return Integer.valueOf(this.get(key).toString()); }
	public long _long(Object key) { return Long.parseLong(this.get(key).toString()); }
	public float _float(Object key) { return Float.valueOf((new BigDecimal(this.get(key).toString())).toPlainString()); }
	public double _double(Object key) { return Double.valueOf((new BigDecimal(this.get(key).toString())).toPlainString()); }
	public String _string(Object key) { return (String) this.get(key); }
	public boolean _boolean(Object key) { return Boolean.valueOf(this.get(key).toString()); }
	public Object [] _array(Object key) { return (Object [])this.get(key); }
	public CHash _chash(Object key) {return (CHash) this.get(key); }
	public CObject _cobject(Object key) {return (CObject) this.get(key); }
	public CArray _carray(Object key) {return (CArray) this.get(key); }
	public CFunction _cfunction(Object key) {return (CFunction) this.get(key); }
	public CReturn _creturn(Object key) {return (CReturn) this.get(key); }
	public CVector _cvector(Object key) {return (CVector) this.get(key); }
	public CMatrix _cmatrix(Object key) {return (CMatrix) this.get(key); }
	public CArray _explode(Object key, String delimiter) { return _.explode(delimiter, this._string(key)); }
	public boolean _nan(Object key) { return this._is_nan(this.get(key)); }
	public boolean _is_string(Object key) { return this.get(key) instanceof String; }
	public boolean _is_carray(Object key) { return this.get(key) instanceof CArray; }
	public boolean _is_chash(Object key) { return this.get(key) instanceof CHash; }
	public boolean _is_cfunction(Object key) { return this.get(key) instanceof CFunction; }
	public boolean _is_cvector(Object key) { return this.get(key) instanceof CVector; }
	public boolean _is_cmatrix(Object key) { return this.get(key) instanceof CMatrix; }
	
	/////////////////
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
	/*
	public void set(Object...params) {
		int l = params.length;
		for(int i=0; i<l-1; i++) {	
		} // end for
		return 
	} // end set()
	*/
	
	public boolean _is_int(Object object) { try { Integer.parseInt(object.toString()); return true;} catch(Exception ex) { return false; }}
	public boolean _is_long(Object object) { try { Long.parseLong(object.toString()); return true;} catch(Exception ex) { return false; }}
	public boolean _is_double(Object object) { try { Double.parseDouble(object.toString()); return true;} catch(Exception ex) { return false; }}
	public boolean _is_float(Object object) { try { Float.parseFloat(object.toString()); return true;} catch(Exception ex) { return false; }}
	public boolean _is_nan(Object o) { return (!this._is_int(o) && !this._is_double(o) && !this._is_float(o) && !this._is_long(o)); }
} // end CCast