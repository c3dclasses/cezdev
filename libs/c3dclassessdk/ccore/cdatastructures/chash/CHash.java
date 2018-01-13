
//---------------------------------------------------------------------------------
// file: CHash
// desc: defines a hash object
//---------------------------------------------------------------------------------
package c3dclasses;
import java.util.*;

//-------------------------------------------
// class CHash
// desc: defines a hash object
//-------------------------------------------
public class CHash{
	public CHash() {} 
	public CHash(CPair [] cpairs) { for(CPair cpair: cpairs) this.set(cpair.m_first, cpair.m_second); }
	public void create(HashMap hash) { this.m_hash = hash; }
	public void clear() { this.m_hash.clear(); }
	public Object get(Object key) { return this.m_hash.get(key); }
	public void set(Object key, Object value) { this.m_hash.put(key,value); }
	public Object remove(Object key) { return this.m_hash.remove(key); }
	public int size() { return this.m_hash.size(); }
	public boolean isEmpty() { return this.size() == 0; }
	public boolean containsValue(Object value) { return this.m_hash.containsValue(value); }
	public boolean containsKey(Object key) { return this.m_hash.containsKey(key); }
	public CArray keys() { return new CArray(this.m_hash.keySet().toArray()); }
	public CArray values() { return new CArray(this.m_hash.values().toArray()); }
	public HashMap valueOf() { return this.m_hash; }
	public HashMap _() { return this.m_hash; }
	public Object _(Object key) { return this.get(key); }
	public int _int(Object key) { return Integer.valueOf(this.get(key).toString()); }
	public float _float(Object key) { return Float.valueOf(this.get(key).toString()); }
	public String _string(Object key) { return (String) this.get(key); }
	public boolean _boolean(Object key) { return Boolean.valueOf(this.get(key).toString()); }
	public CFunction _func(Object key) {return (CFunction) this._(key); }
	public CObject _cobject(Object key) {return (CObject) this._(key); }
	
	public void _(Object key, Object value) { this.set(key, value); }
	public void append(CHash chash) { if(chash==null) return; for(Object key : chash.keys().valueOf()) { this.set(key, chash.get(key)); } }
	public String toJSON(boolean bpack) { return _.json_encode(this, bpack); }
	public String toString() { return this.toJSON(true); }
	protected HashMap m_hash = new HashMap();
} // end CHash

//public String toJSON() { return this.toString(""); } 
	/*
	visit : function(fnvisit) { if(typeof(fnvisit) != "function") return; for(key in this.m_hash) fnvisit(key, this.m_hash[key]); },
	toStringVisit : function(fnvisit, cdata) {if(typeof(fnvisit) != "function") return; str=""; for(key in this.m_hash) str += fnvisit(key, this.m_hash[key]); return str; },
	hash : function(key) { if(arguments.length == 1) return this.get(key); else if(arguments.length == 2) this.set(key, arguments[1]); return; },
	urlencode : function() { if(this.m_hash) for(key in this.m_hash) this.m_hash[key] = urlencode($value); },
	toJSON : function() { return CParse.toJSONString(this.m_hash); },
	*/