//---------------------------------------------------------------------------------
// file: CHash
// desc: defines a hash object
//---------------------------------------------------------------------------------
package c3dclasses.ccore;
import java.util.*;
import cglobal.*;

//-------------------------------------------
// class CHash
// desc: defines a hash object
//-------------------------------------------
public class CHash{
	public CHash() { this.m_hash=new HashMap();} 
	public void create(HashMap hash) { this.m_hash = hash; }
	public void clear() { this.m_hash.clear();  }
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
	public void _(Object key, Object value) { this.set(key, value); }
	
	
	/*
	visit : function(fnvisit) { if(typeof(fnvisit) != "function") return; for(key in this.m_hash) fnvisit(key, this.m_hash[key]); },
	toStringVisit : function(fnvisit, cdata) {if(typeof(fnvisit) != "function") return; str=""; for(key in this.m_hash) str += fnvisit(key, this.m_hash[key]); return str; },
	hash : function(key) { if(arguments.length == 1) return this.get(key); else if(arguments.length == 2) this.set(key, arguments[1]); return; },
	urlencode : function() { if(this.m_hash) for(key in this.m_hash) this.m_hash[key] = urlencode($value); },
	toJSON : function() { return CParse.toJSONString(this.m_hash); },
	*/
	protected HashMap m_hash;
} // end CHash

/*
// functions 
function chash($inhash=NULL) {
	$hash = new CHash();
	$hash->create($inhash); 
	return $hash;
} // end carray()
*/