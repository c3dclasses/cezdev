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
	
	//////////////////////
	// compare function
	CFunction m_cmpfunc = null;
	public void _set_cmp(CFunction cmpfunc) { this.setcompare(cmpfunc); }
	public CFunction _get_cmp() { return this.getcompare(); }
	public void setcompare(CFunction cmpfunc) { this.m_cmpfunc = cmpfunc; }
	public CFunction getcompare() { return this.m_cmpfunc; }
	
	///////////////
	// no param
	public CCast set(Object data) { return this; } 
	public Object get() { return null; }
	public boolean _boolean() { return Boolean.valueOf(this.get().toString()); }
	public int _int() { return Integer.valueOf(this.get().toString()); }
	public long _long() { return Long.parseLong(this.get().toString()); }
	public double _double() { return Double.valueOf((new BigDecimal(this.get().toString())).toPlainString()); } 
	public float _float() { return Float.valueOf(this.get().toString()); }
	public String _string() { return (String)this.get().toString(); }
	public Object _object() { return this.get(); }
	public Object [] _array(){ return (Object [])this.get(); }	
	public CArray _carray() { return (CArray)this.get(); }
	public CHash _chash() { return (CHash)this.get(); }
	public CObject _cobject() {return (CObject) this.get(); }
	public CFunction _cfunction() { return (CFunction)this.get(); }
	public CReturn _creturn() {return (CReturn) this.get(); }
	public CMatrix _cmatrix() { return (CMatrix)this.get(); }
	public CVector _cvector() { return (CVector)this.get(); }
	public CArray _explode(String delimiter) { return __.explode(delimiter,this._string()); } 
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
	public Object _object(int index) { return this.get(index); }
	public Object [] _array(int index){ return (Object [])this.get(index); }	
	public CArray _carray(int index) { return (CArray) this.get(index); }
	public CHash _chash(int index) { return (CHash)this.get(index); }
	public CObject _cobject(int index) {return (CObject) this.get(index); }
	public CFunction _cfunction(int index) {return (CFunction) this.get(index); }
	public CReturn _creturn(int index) {return (CReturn) this.get(index); }
	public CMatrix _cmatrix(int index) {return (CMatrix) this.get(index); }
	public CVector _cvector(int index) {return (CVector) this.get(index); }
	public CArray _explode(int index, String delimiter) { return __.explode(delimiter,this._string(index)); } 
	public boolean _nan(int index) { return this._is_nan(this.get(index)); }
	//public boolean _is_int(int index) { return this.
	public boolean _is_string(int index) { return this.get(index) instanceof String; }
	public boolean _is_carray(int index) { return this.get(index) instanceof CArray; }
	public boolean _is_chash(int index) { return this.get(index) instanceof CHash; }
	public boolean _is_cfunction(int index) { return this.get(index) instanceof CFunction; }
	public boolean _is_cvector(int index) { return this.get(index) instanceof CVector; }
	public boolean _is_cmatrix(int index) { return this.get(index) instanceof CMatrix; }
	public int compare(int index1, int index2) { return (this.m_cmpfunc != null) ? this.m_cmpfunc.call(__.args(this.get(index1), this.get(index2)))._int() : -2; }
	public int _cmp(int index1, int index2) { return this.compare(index1, index2); } 
	public boolean _gt(int index1, int index2) { return this._cmp(index1, index2) == 1; }
	public boolean _lt(int index1, int index2) { return this._cmp(index1, index2) == -1; }
	public boolean _e(int index1, int index2) { return this._cmp(index1, index2) == 0; }
	public boolean _gte(int index1, int index2) { return this._gt(index1, index2) || this._e(index1, index2); }
	public boolean _lte(int index1, int index2) { return this._lt(index1, index2) || this._e(index1, index2); }	
	public boolean _swp(int index1, int index2) { return this.swap(index1, index2); }
	public boolean swap(int index1, int index2) {
		Object tmp = this.get(index1); 
		if(tmp != null && this.get(index2) != null) { 
			this.set(index1, this.get(index2)); 
			this.set(index2, tmp); 
			return true;
		} // end if
		return false;
	} // end swap()
	
	///////////////////
	// hashed params
	public CCast  set(Object key, Object value) { return this; }
	public void _set(Object...keys_value) {
		int l = keys_value.length;
		if(l < 2)
			return;
		CHash chash = (CHash) this;
		int i = 0;
		for(i=0; i<l-2; i++) {	
			CHash chash_inner = chash._chash(keys_value[i]);			
			if(chash_inner == null) {
				chash_inner = __.chash();
				chash.set(keys_value[i], chash_inner);
			} // end if
			chash = chash_inner;
		} // end for
		chash.set(keys_value[i], keys_value[i+1]);
		return;
	} // end _set()
	public Object get(Object key) { return null; }
	public CCast _get(Object...keys) {
		int l = keys.length;
		if(l < 1)
			return null;
		CHash chash = (CHash)this;
		for(int i=0; i<l; i++) {	
			chash = chash._chash(keys[i]);			
			if(chash == null) 
				return null;	
		} // end for
		return chash;
	} // end _get()
	
	public boolean _boolean(Object key) { Object obj = this.get(key); return Boolean.valueOf((obj!=null)?obj.toString():null); }
	public int _int(Object key) { return Integer.valueOf(this.get(key).toString()); }
	public long _long(Object key) { return Long.parseLong(this.get(key).toString()); }
	public double _double(Object key) { return Double.valueOf((new BigDecimal(this.get(key).toString())).toPlainString()); }
	public float _float(Object key) { return Float.valueOf((new BigDecimal(this.get(key).toString())).toPlainString()); }
	public String _string(Object key) { return (String) this.get(key); }
	public Object _object(Object key) { return this.get(key); }
	public Object [] _array(Object key) { return (Object [])this.get(key); }
	public CArray _carray(Object key) {return (CArray) this.get(key); }
	public CHash _chash(Object key) {return (CHash) this.get(key); }
	public CObject _cobject(Object key) {return (CObject) this.get(key); }
	public CFunction _cfunction(Object key) {return (CFunction) this.get(key); }
	public CReturn _creturn(Object key) {return (CReturn) this.get(key); }
	public CVector _cvector(Object key) {return (CVector) this.get(key); }
	public CMatrix _cmatrix(Object key) {return (CMatrix) this.get(key); }
	public CArray _explode(Object key, String delimiter) { return __.explode(delimiter, this._string(key)); }
	public boolean _nan(Object key) { return this._is_nan(this.get(key)); }
	public boolean _is_string(Object key) { return this.get(key) instanceof String; }
	public boolean _is_carray(Object key) { return this.get(key) instanceof CArray; }
	public boolean _is_chash(Object key) { return this.get(key) instanceof CHash; }
	public boolean _is_cfunction(Object key) { return this.get(key) instanceof CFunction; }
	public boolean _is_cvector(Object key) { return this.get(key) instanceof CVector; }
	public boolean _is_cmatrix(Object key) { return this.get(key) instanceof CMatrix; }
	public int _cmp(Object key1, Object key2) { return this.compare(key1, key2); } 
	public boolean _gt(Object key1, Object key2) { return this._cmp(key1, key2) == 1; }
	public boolean _lt(Object key1, Object key2) { return this._cmp(key1, key2) == -1; }
	public boolean _e(Object key1, Object key2) { return this._cmp(key1, key2) == 0; }
	public boolean _gte(Object key1, Object key2) { return this._gt(key1, key2) || this._e(key1, key2); }
	public boolean _lte(Object key1, Object key2) { return this._lt(key1, key2) || this._e(key1, key2); }	
	public boolean _swp(Object key1, Object key2) { return this.swap(key1, key2); }
	public boolean swap(Object key1, Object key2) {
		Object tmp = this.get(key1); 
		if(tmp != null && this.get(key2) != null) { 
			this.set(key1, this.get(key2)); 
			this.set(key2, tmp); 
			return true;
		} // end if
		return false;
	} // end swap()
	public int compare(Object key1, Object key2) { return (this.m_cmpfunc != null) ? this.m_cmpfunc.call(__.args(this.get(key1), this.get(key2)))._int() : -2; }
	
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
	
	
	
	
	public boolean _is_int(Object object) { try { Integer.parseInt(object.toString()); return true;} catch(Exception ex) { return false; }}
	public boolean _is_long(Object object) { try { Long.parseLong(object.toString()); return true;} catch(Exception ex) { return false; }}
	public boolean _is_double(Object object) { try { Double.parseDouble(object.toString()); return true;} catch(Exception ex) { return false; }}
	public boolean _is_float(Object object) { try { Float.parseFloat(object.toString()); return true;} catch(Exception ex) { return false; }}
	public boolean _is_nan(Object o) { return (!this._is_int(o) && !this._is_double(o) && !this._is_float(o) && !this._is_long(o)); }
} // end CCast