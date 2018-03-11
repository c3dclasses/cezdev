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
	protected boolean m_bparsed = true;
	public boolean _isparsed() { return this.m_bparsed; }
	
	// creturn
	public CCast set(Object data) { return this; } 
	public Object get() { return null; }
	public boolean _boolean() { return Boolean.valueOf(this.get().toString()); }
	public int _int() { return Integer.valueOf(this.get().toString()); }
	public float _float() { return Float.valueOf(this.get().toString()); }
	public String _string() { return (String)this.get().toString(); }
	public CArray carray() { return (CArray)this.get(); }
	public CHash chash() { return (CHash)this.get(); }
	public CFunction cfunction() { return (CFunction)this.get(); }
	
	// carray
	public CCast set(int iindex, Object object) { return this; }
	public Object get(int iindex) { return null; }
	public boolean _boolean(int index) { 
		try { 
			this.m_bparsed = true; 
			return Boolean.valueOf(this.get(index).toString()); 
		} // end try 
		catch(Exception ex){ 
			this.m_bparsed = false; 
			return false; 
		} // end catch
	} // end _boolean()
	public int _int(int index) { 
		try { 
			this.m_bparsed = true; 
			return Integer.valueOf(this.get(index).toString()); 
		} // end try 
		catch(Exception ex) { 
			this.m_bparsed = false; 
			return 0; 
		} // end catch() 
	} // end _int()
	public long _long(int index) { 
		try { 
			this.m_bparsed = true;
			return Long.parseLong(this.get(index).toString()); 
		} // end try
		catch(Exception ex) {
			this.m_bparsed = false; 
			return 0;
		} // end catch
	} // end _long()
	public double _double(int index) { 
		try { 
			this.m_bparsed = true;
			return Double.valueOf(this.get(index).toString()); 
		} // end try
		catch(Exception ex) {
			this.m_bparsed = false; 
			return 0;
		} // end catch
	} // end _double()
	public float _float(int index) { 
		try { 
			this.m_bparsed = true;
			return Float.valueOf(this.get(index).toString());
		} // end try
		catch(Exception ex) {
			this.m_bparsed = false; 
			return 0;
		} // end catch		
	} // end _float
	public String _string(int index) { return (String)this.get(index); }
	public Object [] _array(int index){ return (Object [])this.get(index); }	
	public CArray _carray(int index) { return (CArray) this.get(index); }
	public CHash _chash(int index) { return (CHash)this.get(index); }
	public CObject _cobject(int index) {return (CObject) this.get(index); }
	public CFunction _cfunction(int index) {return (CFunction) this.get(index); }
	public CReturn _creturn(int index) {return (CReturn) this.get(index); }
	public CArray _explode(int index, String delimiter) { return _.explode(delimiter,this._string(index)); } 
	public boolean isnumeric(int index ){ return true; }
	
	// chash
	public CCast  set(Object key, Object value) { return this; }
	public Object get(Object key) { return null; }
	public Object _(Object key) { return this.get(key); }
	public Object _object(Object key) { return this.get(key); }
	public int _int(Object key) { return Integer.valueOf(this.get(key).toString()); }
	public long _long(Object key) { return Long.parseLong(this.get(key).toString()); }
	public float _float(Object key) { return Float.valueOf(this.get(key).toString()); }
	public double _double(Object key) { return Double.valueOf(this.get(key).toString()); }
	public String _string(Object key) { return (String) this.get(key); }
	public boolean _boolean(Object key) { return Boolean.valueOf(this.get(key).toString()); }
	public Object [] _array(Object key) { return (Object [])this.get(key); }
	public CHash _chash(Object key) {return (CHash) this._(key); }
	public CObject _cobject(Object key) {return (CObject) this._(key); }
	public CArray _carray(Object key) {return (CArray) this._(key); }
	public CFunction _cfunction(Object key) {return (CFunction) this._(key); }
	public CReturn _creturn(Object key) {return (CReturn) this._(key); }
	public CArray _explode(Object key, String delimiter) { return _.explode(delimiter, this._string(key)); }
	
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
	
} // end CCast