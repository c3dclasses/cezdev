//----------------------------------------------------------------------------
// file: CForm
// desc: defines a form object for creating controls and options
//----------------------------------------------------------------------------
package c3dclasses.csystem.cui;
import cglobal.*; 
import c3dclasses.ccore.*;

//-----------------------------------------------------------------
// name: CForm
// desc: defines the form used in the application
//-----------------------------------------------------------------
public class CForm {	
	protected String m_strid;			// stores the name/id of the form
	protected String m_strcmemoryid;	// stores the cmemory id of the form
	protected CHash m_params;			// stores the parameters of the form
	protected COptions m_coptions;		// stores the options of the form 
	protected CControls m_ccontrols;	// stores the controls of the form 
	
	public CForm() {	
		this.m_ccontrols = new CControls();
		this.m_coptions = new COptions();
		this.m_params = new CHash();
		this.m_strid = "";
		this.m_strcmemoryid = "";
	} // end CForm
	
	public CForm(CControls ccontrols, COptions coptions) {	
		this.m_ccontrols = ccontrols;
		this.m_coptions = coptions;
		this.m_params = new CHash();
		this.m_strid = "";
		this.m_strcmemoryid = "";
	} // end CForm()
	
	public boolean create(String strname, CHash params) {
		this.m_ccontrols.create(this);
		this.m_coptions.create(this);
		this.m_params = params;
		this.m_strid = strname;
		return true;
	} // end create()

	public void setID(String strid) {
		this.m_strid = strid;
	} // end setID()
	
	public String getID() { 
		return this.m_strid;
	} // end getID()
	
	public void setCMemoryID(String strid) {
		this.m_strcmemoryid = strid;
		return;
	} // end setCMemoryID()
	
	public String getCMemoryID() {
		return this.m_strcmemoryid;
	} // end getCMemoryID()
	
	public CHash getParams() { 
		return this.m_params; 
	} // end getParams()
	
	public void setParam(String strname, Object value) { 
		//this.m_params.set(strname, value);		
	} // end setParam()
	
	public Object getParam(String strname) { 
		CHash params = this.getParams();
		return (params != null) ? (Object) params.get(strname) : null; 
	} // end getParam()
	
	public COptions getCOptions() { 
		return this.m_coptions; 
	} // end getCOptions()
	
	public CControls getCControls() { 
		return this.m_ccontrols; 
	} // end getCControls()
} // end class CForm