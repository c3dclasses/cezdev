//----------------------------------------------------------------
// file: CMemory
// desc: 
//----------------------------------------------------------------
package c3dclasses;

//----------------------------------------------------------------
// class: CMemory
// desc: 
//----------------------------------------------------------------
public class CMemory extends CResource { 
	public CMemory() { 
		super(); 
		this.m_cache = null; 
	} // end CMemory()
	
	// open / close
	public boolean open(String strpath, String strtype, CHash params) {
		if(!super.open(strpath, strtype, params))
			return false;	
		if(this.driver("open", _.args(this)) == null)
			return false;	
		this.m_cache = _.chash();
		if(params != null && params.containsKey("cmemory_cache")) {
			this.m_cache.append((CHash)params._("cmemory_cache"));	
			return true;
		} // end if
		CReturn creturn = this.sync();
		if(creturn.isdone() && creturn.data() != null) {
			this.m_cache = creturn.chash();
		} // end if
		return true;	
	} // end open()
	
	public boolean close() {
		CReturn creturn = this.driver("close", _.args(this));		
		this._("cmemory_driver_type", "");
		this.m_cache = null; 
		return true;
	} // end close()	
	
	// remote CRUD
	public CReturn create(String strname, Object value, String strtype, CHash params) {
		CReturn creturn = this.driver("create", _.args(this, strname, value, strtype, params));
		if(creturn.isdone() && creturn.data() != null)
			this.m_cache._(strname,creturn.data());
		return creturn; 
	} // end create()
    
	public CReturn retrieve(String strname) {
		CReturn creturn = this.driver("retrieve", _.args(this, strname));
		if(creturn.isdone() && creturn.data() != null)
			this.m_cache._(strname,creturn.data());
		return creturn;
	} // end retrieve()
	
	public CReturn update(String strname, Object value, String strtype, CHash params) {
		CReturn creturn = this.driver("update",_.args(this, strname, value, strtype, params));
		if(creturn.isdone() && creturn.data() != null)
			this.m_cache._(strname,creturn.data());
		return creturn; 
	} // end update()
	
	public CReturn delete(String strname) {
		CReturn creturn = this.driver("delete", _.args(this, strname));
		if(creturn.isdone() && creturn.data() != null)
			this.m_cache.remove(strname);
		return creturn;
	} // end delete()
	
	public CReturn sync() { 
		CReturn creturn = this.driver("sync", _.args(this));
		if(creturn.isdone() && creturn.data() != null)
			this.cache(creturn.chash());
		return creturn;
	} // end sync()
	
	public CReturn upsert(String strname, Object value, String strtype, CHash params) {
		CReturn creturn = this.create(strname, value, strtype, params);	
		return (creturn == null || creturn.data() == null) ? 
			this.update(strname, value, strtype, params) : creturn;
	} // end create_update()
	
	// set / get
	public void set(String strname, Object value, String strtype) {
		if(this.m_cache._(strname) == null)
			return;
		CHash cvar = this.m_cache._chash(strname);
		cvar._("m_value", value);
		this.update(strname, value, strtype, null);
		return;
	} // end set()
	
	public CHash get(String strname) { 
		return (CHash) this.m_cache._chash(strname); 
	} // end get()
	
	// other
	public CHash cache() { 
		return this.m_cache; 
	} // cache()
	
	public CHash cache(CHash cache) { 
		return this.m_cache = cache; 
	} // end cache()
	
	public String toString() { 
		return super.toJSON(false) + "\n" + this.m_cache.toJSON(false); 
	} // end toString()
	
	public CReturn driver(String func, CArray args) { 
		CFunction cfunction = CFunction.get(this._string("cmemory_driver_type") + "." + func);
		return (cfunction != null) ? cfunction.call(args) : CReturn._error(null);		
	} // end driver()
	
	// include / use 
	static public CResource include(String strid, String strdriverpath, String strdrivertype, CHash params) {
		return (CDriver.include(strdrivertype)) ? CResource.include(strid, strdriverpath, 				
				"c3dclasses.CMemory", _.chash(params)._("cmemory_driver_type", strdrivertype)) : null;
	} // end include()
	
	static public CMemory use(String strid) { 
		return (CMemory) CResource.use(strid); 
	} // end use()
	
	protected CHash m_cache = null;
} // end CMemory()