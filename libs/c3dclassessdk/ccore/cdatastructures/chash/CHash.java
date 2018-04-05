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
public class CHash extends CCast {
	/////////////////
	// constructor
	public CHash() {} 
	public CHash(Object [] objects) { for(int i=0; i<objects.length; i+=2) this.set(objects[i], objects[i+1]); }
	public void create(HashMap hash) { this.m_hash = hash; }
	
	///////////////////////////////////
	// removal, clearing, operations
	public void clear() { this.m_hash.clear(); }
	public Object remove(Object key) { return this.m_hash.remove(key); }
	public void append(CHash chash) { if(chash==null) return; for(Object key : chash.keys().valueOf()) { this.set(key, chash.get(key)); } }
	
	//////////////////////
	// info
	public int size() { return this.m_hash.size(); }
	public boolean isEmpty() { return this.size() == 0; }
	public boolean containsValue(Object value) { return this.m_hash.containsValue(value); }
	public boolean containsKey(Object key) { return this.m_hash.containsKey(key); }
	
	///////////////////////
	// objects
	public CArray keys() { return new CArray(this.m_hash.keySet().toArray()); }
	public CArray values() { return new CArray(this.m_hash.values().toArray()); }
	public HashMap valueOf() { return this.m_hash; }
	public HashMap _() { return this.m_hash; }
	
	///////////////////////
	// indexing
	public CCast set(Object key, Object value) { this.m_hash.put(key,value); return this; }
	public Object get(Object key) { return this.m_hash.get(key); }
	public Object _(Object key) { return this.get(key); }
	public CHash _(Object key, Object value) { this.set(key, value); return this; }	
	
	/////////////////////////
	// toString(), toJSON()
	public String toJSON(boolean bpack) { return CJSON.encode(this, bpack); }
	public String toString() { return this.toJSON(true); }
	
	// members
	protected HashMap m_hash = new HashMap();
} // end CHash	