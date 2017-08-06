//----------------------------------------------------------------
// file: CJSONMemoryDriver
// desc: defines a json memory object 
//----------------------------------------------------------------
package c3dclasses.csystem.cdevice;
import cglobal.*; 
import c3dclasses.ccore.*;

//----------------------------------------------------------------
// file: CJSONMemoryDriver
// desc: defines a json memory object 
//----------------------------------------------------------------
public class CJSONMemoryDriver extends CMemoryDriver {
	protected CHash m_json;
	public CJSONMemoryDriver() { super(); this.m_json = null; }
	// open / close / save
	public boolean open(String strpath, String strtype, CHash params) {
		CHash json = null;
		String strcontents = "";
		if(_.file_exists(strpath) == false ||
			(strcontents = _.file_get_contents(strpath)) == "" ||
			(json = (CHash)_.json_decode(strcontents, true)) == null)
			json = new CHash();
		if(super.open(strpath, strtype, params) == false)
			return false;
		this.m_json = json;
		return true;	
	} // end open()
	public boolean close() { return this.save(); }
	public boolean save() { return _.file_set_contents(this.path(), _.json_encode(this.m_json)); }
	// CRUD - create / retrieve / update / delete	
	public CReturn create(CHash cvar) { 
		if(cvar == null) { // no var
			return CReturn._done(null);
		}
		String strname = (String) cvar._("m_strname");
		if(this.m_json._(strname) != null) { // already created no need to create
		 	return CReturn._done(null);	
		}
		Object value = cvar._("m_value");		
		CHash chash = _.chash();
		chash._("m_strname", strname);
		chash._("m_value", value);
		chash._("m_icreated", _.time()); // set the timestamp
		chash._("m_iupdated", -1);
		chash._("m_iretrieved", -1);
		this.m_json._(strname, chash); 
		return this.save() ? CReturn._done(this.m_json._(strname)) : null;
	} // end create()
	public CReturn retrieve(String strname) { 
		CHash cvar = null;
		if(this.restore() == false || this.m_json == null || this.m_json._(strname) == null || 
				(cvar = (CHash)this.m_json._(strname)) == null) 
				return CReturn._done(null);
		cvar._("m_iretrieved", _.time());
		return CReturn._done(cvar); 
	} // end retrieve()
	public CReturn update(CHash cvar) { 
		if(cvar == null)
			return CReturn._done(null);
		String strname = (String) cvar._("m_strname");
		if(this.m_json == null || this.m_json._(strname) == null) // was not created
			return CReturn._done(null);
		Object value = (Object) cvar._("m_value");	
		CHash chash = _.chash();
		chash._("m_strname", strname);
		chash._("m_value", value);
		chash._("m_icreated", this.m_json._("m_icreated")); // set the timestamp
		chash._("m_iupdated", _.time());
		chash._("m_iretrieved", -1);
		this.m_json._(strname, chash);
		return this.save() ? CReturn._done(this.m_json._(strname)) : null;
	} // end update()
	
	public CReturn delete(String strname) { 
		if(this.m_json == null || this.m_json._(strname) == null)
			return CReturn._done(null);
		this.m_json.remove(strname);
		this.save(); // update the file
		return CReturn._done(null);
	} // end delete()
	
	public CReturn sync(CHash cache) {
		// update the main cache	
		if(cache != null) {
			CArray keys = cache.keys();
			int len = keys.length();
			for(int i=0; i<len; i++) {
				String strname = (String) keys._(i);
				if(this.m_json._(strname) != null)
					this.m_json._(strname, cache._(strname));
			} // end foreach
			this.save();
		} // end if				
		return CReturn._done(this.m_json);
	} // end sync()
} // end CJSONMemoryDriver