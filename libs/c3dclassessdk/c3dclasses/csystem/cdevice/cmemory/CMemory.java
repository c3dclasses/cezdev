//----------------------------------------------------------------
// file: CMemory
// desc: defines the memory object
//----------------------------------------------------------------
package c3dclasses.csystem.cdevice;
import cglobal.*; 
import c3dclasses.ccore.*;

//----------------------------------------------------------------
// class: CMemory
// desc: defines the memory object
//----------------------------------------------------------------
public class CMemory extends CResource { 
	protected CHash m_cache = null;
	public CMemory() { super(); this.close(); }	
	// open / close
	public boolean open(String strpath, String strtype, CHash params) {
		if(!super.open(strpath, strtype, params) || CMemoryDriver._open(this) == null)
			return false;
		if(params != null && params.containsKey("cmemory_cache"))
			this.m_cache.append((CHash)params._("cmemory_cache"));		
		else this.sync();
		return true;	
	} // end open()
	public boolean close() {
		this.m_cache = new CHash();
		return CMemoryDriver._close(this);
	} // end close()	
	// remote CRUD
	public CReturn create(String strname, Object value, String strtype, CHash params) {
		return CMemoryDriver._create(this, strname, value, strtype, params);
	} // end create()
    public CReturn retrieve(String strname) {
		return CMemoryDriver._retrieve(this, strname);
	} // end retrieve()
	public CReturn update(String strname, Object value, String strtype, CHash params) {
		return CMemoryDriver._update(this, strname, value, strtype, params);
	} // end update()
	public CReturn delete(String strname) {
		return CMemoryDriver._delete(this, strname);
	} // end delete()
	public CReturn sync() { return CMemoryDriver._sync(this); }
	// set / get
	public void set(String strname, Object value, String strtype) {
		if(this.m_cache._(strname) == null)
			return;
		CHash cvar = (CHash) this.m_cache._(strname);
		cvar._("m_value", value);
		this.update(strname, value, strtype, null);
		return;
	} // end set()
	public CHash get(String strname) { return (CHash) this.m_cache._(strname); }
	// other
	public CHash cache() { return this.m_cache; }
	public CHash cache(CHash cache) { return this.m_cache = cache; }
	public String toString() { return super.toString() + "\n" + this.m_cache.toString(); }
	
	// include / use 
	static public CResource include(String strid, String strdriverpath, String strdrivertype, CHash params) {
		params = _.chash(params);
		params._("cmemorydriver_type", strdrivertype);
		params._("cmemorydriver_path", strdriverpath);
		strdriverpath = "CMemory_" + strdrivertype + "_" + strdriverpath;
		return CResource.include(strid, strdriverpath, "c3dclasses.csystem.cdevice.CMemory", params);
	} // end include()
	static public CResource include_remote(String strid, String strremotedriverpath, String strremotedrivertype, String struri, CHash params) {
		params = _.chash(params);
		params._("cremotememorydriver_path", strremotedriverpath); // unique path on the machine 
		params._("cremotememorydriver_type", strremotedrivertype); // the type of memory
		params._("cremotememorydriver_uri", struri);	// the location on the machine
		params._("cremotememorydriver_id", struri + "||" + strremotedrivertype + "||" + strremotedriverpath); // the identifier
		return CMemory.include(strid, strremotedriverpath, "CRemoteMemoryDriver", params);
	} // end include_remote()
	static public CMemory use(String strid) { return (CMemory) CResource.use(strid); }
} // end CMemory()