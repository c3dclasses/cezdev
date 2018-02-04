//----------------------------------------------------------------
// file: CJSONMemoryDriver
// desc: 
//----------------------------------------------------------------
package c3dclasses;

//----------------------------------------------------------------
// class: CJSONMemoryDriver
// desc: 
//----------------------------------------------------------------
public class CJSONMemoryDriver extends CDriver {
	public CJSONMemoryDriver() {
		
		new CFunction("c3dclasses.CJSONMemoryDriver.open") {
			public CReturn call(Object object) { 
				CMemory cmemory = (CMemory) object;
				CHash json = null;
				String strcontents = "";
				String strpath = cmemory.path();
				if(_.file_exists(strpath) == false ||
					(strcontents = _.get_file_contents(strpath)) == "" ||
					(json = (CHash) CJSON.decode(strcontents)) == null)
					json = new CHash();
				if(json == null)
					return CReturn._done(false);
				cmemory._("m_json", json);
				return CReturn._done(true); 
			} // end call()		
		}; // end open()
		
		new CFunction("c3dclasses.CJSONMemoryDriver.close") {
			public CReturn call(Object object) { 
				return CFunction.get("c3dclasses.CJSONMemoryDriver.save").call(object);		
			} // end call()		
		}; // end close()

		new CFunction("c3dclasses.CJSONMemoryDriver.save") {
			public CReturn call(Object object) { 
				CMemory cmemory = (CMemory) object;
				CHash m_json = cmemory._chash("m_json");
				String strpath = cmemory.path();
				return _.set_file_contents(strpath, (m_json != null) ? m_json.toJSON(true) : "") ? CReturn._done(true) : CReturn._done(false);
			} // end call()		
		}; // end save()
		
		new CFunction("c3dclasses.CJSONMemoryDriver.create") {
			public CReturn call(CArray args) { 
				CMemory cmemory = (CMemory) args._(0);
				String strname = args._string(1);
				Object value = args._(2);
				Object strtype = args._(3);
				CHash params = args._chash(4);		
				CHash m_json = cmemory._chash("m_json");
				if(strname == null || strname == "")
					return CReturn._done(null);
				CHash cvar = _.chash();
				cvar._("m_strname", strname);
				cvar._("m_value", value);
				cvar._("m_strtype", strtype);
				cvar._("m_icreated", _.time()); // set the timestamp
				cvar._("m_iupdated", -1);
				cvar._("m_iretrieved", -1);
				m_json._(strname, cvar); 
				return (CFunction.get("c3dclasses.CJSONMemoryDriver.save").call((Object) cmemory)._boolean()) ?
					CReturn._done(m_json._(strname)) : CReturn._done(null);
			} // end call()		
		}; // end create()
		
		new CFunction("c3dclasses.CJSONMemoryDriver.retrieve") {
			public CReturn call(CArray args) { 
				CMemory cmemory = (CMemory) args._(0);
				String strname = args._string(1);
				boolean brestored = CFunction.get("c3dclasses.CJSONMemoryDriver.restore").call((Object) cmemory)._boolean();
				CHash m_json = cmemory._chash("m_json");
				CHash cvar = null;
				if(brestored == false || m_json == null || (cvar = (CHash) m_json._chash(strname)) == null)
					return CReturn._done(null);
				cvar._("m_iretrieved", _.time());
				return CReturn._done(m_json._(strname));
			} // end call()		
		}; // end retrieve()

		new CFunction("c3dclasses.CJSONMemoryDriver.update") {
			public CReturn call(CArray args) { 
				CMemory cmemory = (CMemory) args._(0);
				String strname = args._string(1);
				Object value = args._(2);
				Object strtype = args._(3);
				CHash params = args._chash(4);		
				CHash m_json = cmemory._chash("m_json");
				if(strname == null || m_json._(strname) == null)
					return CReturn._done(null);
				CHash cvar = _.chash();
				cvar._("m_strname", strname);
				cvar._("m_value", value);
				cvar._("m_strtype", strtype);
				cvar._("m_icreated", _.time()); // set the timestamp
				cvar._("m_iupdated", -1);
				cvar._("m_iretrieved", -1);
				m_json._(strname, cvar); 
				CReturn ret = CFunction.get("c3dclasses.CJSONMemoryDriver.save").call((Object) cmemory);
				return (ret._boolean()) ? CReturn._done(m_json._(strname)) : CReturn._done(null);
			} // end call()		
		}; // end create()
		
		new CFunction("c3dclasses.CJSONMemoryDriver.delete") {
			public CReturn call(CArray args) { 
				CMemory cmemory = (CMemory) args._(0);
				String strname = args._string(1);
				CHash m_json = cmemory._chash("m_json");
				if(m_json == null || m_json._(strname) == null)
					return CReturn._done(false);
				CHash cvar = m_json._chash(strname);
				m_json.remove(strname);
				return CFunction.get("c3dclasses.CJSONMemoryDriver.save").call((Object) cmemory);
			} // end call()
		}; // end delete()
		
		new CFunction("c3dclasses.CJSONMemoryDriver.sync") {
			public CReturn call(Object object) { 
				CMemory cmemory = (CMemory) object;
				CHash m_cache = cmemory.cache();
				CHash m_json = cmemory._chash("m_json");
				if(m_cache != null) {		
					CArray keys = m_cache.keys();
					for(int i=0; i<keys.length(); i++) {
						String strname = (String) keys._(i);
						if(m_json._(strname) != null)
							m_json._(strname, m_cache._(strname));
					} // end for
					CFunction.get("c3dclasses.CJSONMemoryDriver.save").call((Object) cmemory);
				} // end if
				return CReturn._done(m_json);
			} // end call()		
		}; // end sync()
		
		CFunction cfunction = new CFunction("c3dclasses.CJSONMemoryDriver.restore") {
			public CReturn call(Object object) { 
				CMemory cmemory = (CMemory) object;
				String strcontents = _.get_file_contents(cmemory.path());
				if(strcontents == null)
					return CReturn._done(false);
				CHash json = (CHash) CJSON.decode(strcontents);
				if(json == null)
					return CReturn._done(false);
				cmemory._("m_json", json);
				return CReturn._done(true);
			} // end call()		
		}; // end restore()
	} // end CJSONMemoryDriver()
} // end CJSONMemoryDriver