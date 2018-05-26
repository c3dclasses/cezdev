//-----------------------------------------------------------------------------------------------
// file: CVar
// desc: 
//-----------------------------------------------------------------------------------------------
package c3dclasses;

//--------------------------------------------------------------------------------
// name: CVar
// desc: 
//--------------------------------------------------------------------------------
public class CVar {
	String m_strname;
	CMemory m_cmemory;
	
	public CVar(String strname, String strpath){ 
		this.create(strname, strpath); 
	} 
	
	public boolean create(String strname, String strpath) { 
		if(CMemory.include("cmemory-test", strpath, "c3dclasses.CJSONMemoryDriver", null) == null)
			return false;
		this.m_cmemory = CMemory.use("cmemory-test");
		CReturn creturn = m_cmemory.create(strname, "default-memory-value", "string", null);
		m_strname = strname; 
		return true; 
	} // end create()
	
	public String retrieve() { 
		CHash cvar = null;
		CReturn creturn = this.m_cmemory.retrieve(this.m_strname);
		if(creturn != null) 
			cvar = (CHash) creturn.data();
		if(cvar != null)
			return cvar._string("m_value");
		return "";
	} // end retrieve()
	
	public void update(String strvalue) {
		this.m_cmemory.update(m_strname, strvalue, "string", null);
	}
	
	public void delete() {
		this.m_cmemory.delete(m_strname);
	}
} // end CVar
