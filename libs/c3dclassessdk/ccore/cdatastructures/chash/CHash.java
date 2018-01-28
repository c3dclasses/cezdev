//---------------------------------------------------------------------------------
// file: CHash
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;
import java.util.*;

//-------------------------------------------
// class CHash
// desc: 
//-------------------------------------------
public class CHash {
	public CHash() {} 
	public CHash(Object [] objects) { for(int i=0; i<objects.length; i+=2) this.set(objects[i], objects[i+1]); }
	public CHash(CPair [] cpairs) { for(CPair cpair: cpairs) this.set(cpair.m_first, cpair.m_second); }
	public void create(HashMap hash) { this.m_hash = hash; }
	public void clear() { this.m_hash.clear(); }
	public Object remove(Object key) { return this.m_hash.remove(key); }
	public int size() { return this.m_hash.size(); }
	public boolean isEmpty() { return this.size() == 0; }
	public boolean containsValue(Object value) { return this.m_hash.containsValue(value); }
	public boolean containsKey(Object key) { return this.m_hash.containsKey(key); }
	public CArray keys() { return new CArray(this.m_hash.keySet().toArray()); }
	public CArray values() { return new CArray(this.m_hash.values().toArray()); }
	public HashMap valueOf() { return this.m_hash; }
	public HashMap _() { return this.m_hash; }
	public void set(Object key, Object value) { this.m_hash.put(key,value); }
	public void _(Object key, Object value) { this.set(key, value); }	
	public Object get(Object key) { return this.m_hash.get(key); }
	public Object _(Object key) { return this.get(key); }
	public Object _object(Object key) { return this.get(key); }
	public int _int(Object key) { return Integer.valueOf(this.get(key).toString()); }
	public float _float(Object key) { return Float.valueOf(this.get(key).toString()); }
	public String _string(Object key) { return (String) this.get(key); }
	public boolean _boolean(Object key) { return Boolean.valueOf(this.get(key).toString()); }
	public Object [] _array(Object key) { return (Object [])this.get(key); }
	public CHash _chash(Object key) {return (CHash) this._(key); }
	public CObject _cobject(Object key) {return (CObject) this._(key); }
	public CArray _carray(Object key) {return (CArray) this._(key); }
	public CFunction _cfunction(Object key) {return (CFunction) this._(key); }
	public void append(CHash chash) { if(chash==null) return; for(Object key : chash.keys().valueOf()) { this.set(key, chash.get(key)); } }
	public String toJSON(boolean bpack) { return CJSON.encode(this, bpack); }
	public String toString() { return this.toJSON(true); }
	protected HashMap m_hash = new HashMap();
} // end CHash	