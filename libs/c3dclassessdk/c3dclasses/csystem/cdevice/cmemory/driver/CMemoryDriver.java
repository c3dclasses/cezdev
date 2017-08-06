//---------------------------------------------------------------
// file: CMemoryDriver
// desc: defines the memory driver object
//----------------------------------------------------------------
package c3dclasses.csystem.cdevice;
import cglobal.*; 
import c3dclasses.ccore.*;

//----------------------------------------------------------------
// class: CMemoryDriver
// desc: defines the memory driver object
//----------------------------------------------------------------
public class CMemoryDriver extends CResource {
	public CMemoryDriver() { super(); } 
	// open / close
	public boolean open(String strpath, String strtype, CHash params) { return super.open(strpath, strtype, params); }
	public boolean close() { return super.close(); } 
	// CRUD / sync
	public CReturn create(CHash cvar) { return null; }
	public CReturn retrieve(String strname) { return null; } 
	public CReturn update(CHash cvar) { return null; } 
	public CReturn delete(String strname) { return null; }
	public CReturn sync(CHash cache) { return null; } 	
	// class methods	
	public static CMemoryDriver _open(CMemory cmemory) {
		if(cmemory == null)
			return null;		
		CMemoryDriver cmemorydriver = (CMemoryDriver) cmemory._("cmemorydriver_object"); 
		String strtype = (String) cmemory._("cmemorydriver_type"); 
		String strpath = (String) cmemory._("cmemorydriver_path"); 
		if(cmemorydriver != null)
			return cmemorydriver;	
		if(strtype == null || (cmemorydriver = (CMemoryDriver)_._new(strtype)) == null || 
			cmemorydriver.open(strpath, strtype, cmemory) == false) 
			return null;
		cmemory._("cmemorydriver_object", cmemorydriver);
		return cmemorydriver;
	} // end _open()
	public static boolean _close(CMemory cmemory) {
		CMemoryDriver cmemorydriver = CMemoryDriver._open(cmemory);
		return (cmemorydriver != null) ? cmemorydriver.close() : true;
	} // end _close()
	public static CReturn _create(CMemory cmemory, String strname, Object value, String strtype, CHash params) {
		if(cmemory == null || strname == null)
			return CReturn._done(false);
		CHash cvar = new CHash();
		cvar._("m_strname", strname);	
		cvar._("m_value", value);	
		cvar._("m_strtype", strtype);	
		cvar._("m_params", params);	
		CMemoryDriver cmemorydriver = CMemoryDriver._open(cmemory);
		if(cmemorydriver == null) {
			return CReturn._done(false);
		}
		CReturn _driver_return = cmemorydriver.create(cvar);
		if(_driver_return == null) {			
			return CReturn._done(false);
		}
		CReturn _return = CReturn._busy();
		if(_return == null) {
			return CReturn._done(false);
		}
		cmemory.cache()._(strname, cvar);
		if(_driver_return.isdone()) {
			Object results = (Object)_driver_return.results();
			if(results == null) {
				_return.done(false);
			}
			else {
				cmemory.cache()._(strname, results);	
				_return.done(true);
			} // end else
		} else if(_driver_return.iserror()) {
			_return.done(false);
		} // end else if
		return _return;	
	} // end _create()
	public static CReturn _retrieve(CMemory cmemory, String strname) {
		if(cmemory == null || strname == null)
			return CReturn._done(false);
		CMemoryDriver cmemorydriver = CMemoryDriver._open(cmemory);
		if(cmemorydriver == null)
			return CReturn._done(false);
		CReturn _return = CReturn._busy();
		if(_return == null)
			return CReturn._done(false);
		CReturn _driver_return = cmemorydriver.retrieve(strname);
		if(_driver_return == null)
			return CReturn._done(false);
		if(_driver_return.isdone()) { 
			Object results = _driver_return.results();
			if(results == null)
				_return.done(false);
			else {
				cmemory.cache()._(strname, results);
				_return.done(true);
			} // end else
		} else if(_driver_return.iserror()) {
			_return.done(false);
		} // end else if
		return _return;
	} // end _retrieve()
	public static CReturn _update(CMemory cmemory, String strname, Object value, String strtype, CHash params) {
		if(cmemory == null || strname == null)
			return CReturn._done(false) ;
		CMemoryDriver cmemorydriver = CMemoryDriver._open(cmemory);
		if(cmemorydriver == null)
			return CReturn._done(false);	
		CHash cvar = new CHash();
		cvar._("m_strname", strname);	
		cvar._("m_value", value);	
		cvar._("m_strtype", strtype);	
		cvar._("m_params", params);
		CReturn _driver_return = cmemorydriver.update(cvar);
		if(_driver_return == null) 
			return CReturn._done(false);
		cmemory.cache()._(strname, cvar);
		CReturn _return = CReturn._busy();
		if(_return == null)
			return CReturn._done(false);
		if(_driver_return.isdone()) {
			Object results = _driver_return.results();
			if(results == null)
				_return.done(false);
			else {
				cmemory.cache()._(strname, results);
				_return.done(true);
			} // end else
		} else if(_driver_return.iserror()) {
			_return.done(false);
		} // end if
		return _return;
	} // end _update()
	public static CReturn _delete(CMemory cmemory, String strname) {
		if(cmemory == null || strname == null)
			return CReturn._done(false);
		CMemoryDriver cmemorydriver = CMemoryDriver._open(cmemory);
		if(cmemorydriver == null)	
			return CReturn._done(false);
		CReturn _driver_return = cmemorydriver.delete(strname);
		if(_driver_return == null)
			return CReturn._done(false);
		cmemory.cache().remove(strname);
		CReturn _return = CReturn._busy();
		if(_driver_return.isdone()) {
			_return.done(true);
		} else if(_driver_return.iserror()) {
			_return.done(false);
		} // end if
		return _return;
	} // end _delete()
	public static CReturn _sync(CMemory cmemory) {
		if(cmemory == null)
			return CReturn._done(false);
		CMemoryDriver cmemorydriver = CMemoryDriver._open(cmemory);
		if(cmemorydriver == null)
			return CReturn._done(false);
		CReturn _driver_return = cmemorydriver.sync(cmemory.m_cache);
		if(_driver_return == null)
			return CReturn._done(false);
		CReturn _return = CReturn._busy();
		if(_return == null)
			return CReturn._done(false);
		if(_driver_return.isdone()) {
			Object results = _driver_return.results();
			if(results == null)
				_return.done(false);
			else {
				cmemory.cache((CHash)results);	
				_return.done(true);
			} // end else
		} else if(_driver_return.iserror()) {	
			_return.done(false);
			return _return;
		} // endif()
		return _return;
	} // end _sync()
	// include / use
	public static CResource include(String strid, String strpath, String strtype, CHash params){ return CResource.include(strid, strpath, strtype, params); }
	public static CResource use(String strid){ return CResource.use(strid); } 
	// end ClassMethods
} // end CMemoryDriver()