//---------------------------------------------------------------------------------
// file: CHash.java
// desc: creates a hash object
//---------------------------------------------------------------------------------
package c3dclassessdk.ccore;
import java.util.*;
import c3dclassessdk.clib.*;
import c3dclassessdk.ccore.*;

//-------------------------------------------
// class CHash
// desc: creates a hash object
//-------------------------------------------
public class CHash{
	public CHash() { this.m_hash=new HashMap();} 
	public void create( HashMap hash ) { this.m_hash = hash; }
	public void clear() { this.m_hash.clear();  }
	public Object get( Object key ) { return this.m_hash.get(key); }
	public void set( Object key, Object value ) { this.m_hash.put(key,value); }
	public Object remove( Object key ) { return this.m_hash.remove(key); }
	public int size() { return this.m_hash.size(); }
	public boolean isEmpty() { return this.size() == 0; }
	public boolean containsValue( Object value ) { return this.m_hash.containsValue( value ); }
	public boolean containsKey( Object key ) { return this.m_hash.containsKey( key ); }
	public CArray keys() { return new CArray(this.m_hash.keySet().toArray()); }
	public CArray values() { return new CArray(this.m_hash.values().toArray()); }
	public HashMap 	valueOf() { return this.m_hash; }
	public HashMap 	_() { return this.m_hash; }
	protected HashMap m_hash;
} // end CHash
