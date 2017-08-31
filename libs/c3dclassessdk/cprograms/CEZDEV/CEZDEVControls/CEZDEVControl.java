//---------------------------------------------------------------------------------------------
// name: CEZDEVControl
// desc: defines a generic control object used by EZDEV
//---------------------------------------------------------------------------------------------
package cezdev;
import cglobal.*;

//----------------------------------------------------------------------------------
// name: CEZDEVControlProperties
// desc: defines a generic control object used by EZDEV
//----------------------------------------------------------------------------------
public class CEZDEVControl {
	protected String m_strcmdfile = ""; 
	
	// only override this method
	public boolean createControl() {
		return true;
	} // end createControl()
	
	public boolean setProperties(CJSONObject cjsonobject) {
		return true;
	} // end setProperties()	
	
	public CEZDEVControl(String strcmdfile) {
		this.create(strcmdfile);
	} // end CEZDEVControl()
	
	public boolean create(String strcmdfile) {
		this.m_strcmdfile = strcmdfile;
		if(strcmdfile == null || strcmdfile == "" || !this.createControl() || !this.setProperties(strcmdfile))
			return false;	
		return true;		
	} // end create()
	
	public String getFilename() {
		return this.m_strcmdfile;
	}

	public boolean setProperties(String strfilename) {
		CJSONObject cjsonobject = new CJSONObject();
		if(cjsonobject.create(strfilename) == false)
			return false;				
		return this.setProperties(cjsonobject);
	} // end setProperties()
} // end CEZDEVControl