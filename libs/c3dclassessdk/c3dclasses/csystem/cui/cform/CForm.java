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
	/*protected CHash m_params;			// stores the parameters of the form
	protected COptions m_coptions;		// stores the options of the form 
	protected CControls m_ccontrols;	// stores the controls of the form 
	public CForm() { this.init(null, null); }
	public CForm(CControls ccontrols, COptions coptions) { this.init(ccontrols,coptions); } 
	public boolean create(String strname, CHash params) {
		this.m_ccontrols.create(this);
		this.m_coptions.create(this);
		this.m_params.append(params);
		this.setID(strname);
		return CFormDriver.createCForm(this);		
	} // end create()
	public void setID(String strid) { this.setParam("m_strid", strid); }
	public String getID() { return (String) this.getParam("m_strid"); } 
	public void setCMemoryID(String strid) { this.setParam("m_strcmemoryid", strid); }
	public String getCMemoryID() { return (String) this.getParam("m_strcmemoryid");}
	public void setParam(String strname, Object value) { this.m_params.set(strname, value); }
	public Object getParam(String strname) { CHash params = this.getParams(); return (params != null) ? (Object) params.get(strname) : null; }
	public CHash getParams() { return this.m_params; }
	public COptions getCOptions() { return this.m_coptions; } 
	public CControls getCControls() { return this.m_ccontrols; }
	// helper methods
	public void init(CControls ccontrols, COptions coptions) { 
		this.m_ccontrols = (ccontrols == null) ? new CControls() : ccontrols; 
		this.m_coptions = (coptions == null) ? new COptions() : coptions; 
		this.m_params = new CHash();
	} // end init()
	public void deInit() { this.m_ccontrols = null; this.m_coptions = null; this.m_params = null; }
	public String toStringContents() { return CFormDriver.toStringContents(); } 
	*/
} // end class CForm