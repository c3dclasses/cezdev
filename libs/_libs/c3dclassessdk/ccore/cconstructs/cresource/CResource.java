//---------------------------------------------------------------------------------------
// file: CResource
// desc: an object treated as a resource or (singleton) to be used throughout the SDK  
//---------------------------------------------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------------------------
// name: CResource
// desc: an object treated as a resource or (singleton) to be used throughout the SDK  
//---------------------------------------------------------------------------------------
public class CResource extends CObject {
	public CResource() {} 
	
	// methods 
	public boolean open(String strpath, String strtype, CHash params) {
		this.append(params);
		this.set("cresource_path", strpath);
		this.set("cresource_type", strtype);
		return true;
	} // end open()
	public boolean close() { this.clear(); return true; } 
	public boolean restore() { return true; }
	public String path() { return (String) this.get("cresource_path"); }
	public String type(){ return (String) this.get("cresource_type"); }
	public String toString(){ return super.toString(); }
	
	// ClassMethods
	// include / use
	public static CResource include(String strid, String strpath, String strtype, CHash params) { 
		return CResource._register(strid, strpath, strtype, params); 
	} // end include()
	public static CResource use(String strid) { return CResource._getByID(strid); }
	// no need to call these methods below
	protected static CHash m_pathtoresource = new CHash();	// stores the "filepath" -> resource
	protected static CHash m_idtoresource = new CHash();	// stroes the "identifier" -> resource
	protected static CResource _getByPath (String strpath) { 
		return (CResource) CResource.m_pathtoresource._(strpath); 
	} // _getByPath()
	protected static boolean _addByPath (String strpath, CResource cresource) {
		if(cresource == null || CResource.m_pathtoresource.containsKey(strpath) != false)
			return false;
		CResource.m_pathtoresource._(strpath, cresource);
		return true;			
	} // end _addByPath()		
	protected static CResource _getByID (String strid) { return (CResource) CResource.m_idtoresource.get(strid); }
	protected static boolean _addByID (String strid, CResource cresource) {
		if(cresource == null && CResource.m_idtoresource.containsKey(strid) != false)
			return false;
		CResource.m_idtoresource.set(strid, cresource);
		return true;
	} // end _addByID()
	protected static CResource _register (String strid, String strpath, String strtype, CHash params) {
		CResource cresource = null;
		if(strid != null && (cresource = CResource._getByID(strid)) != null) {
			cresource.append(params);
			return cresource;
		} // end if
		if(strpath != null && (cresource = CResource._getByPath(strpath)) != null) {
			CResource._addByID(strid, cresource);
			cresource.append(params);
			return cresource;
		} // end if
		if(strtype == "" || (cresource = (CResource) __._new(strtype)) == null || 
			cresource.open(strpath, strtype, params) == false) {	
			return null; 	
		}
		CResource._addByID(strid, cresource);
		CResource._addByPath(strpath, cresource);		
		return cresource;
	} // end _register()
} // end CResource