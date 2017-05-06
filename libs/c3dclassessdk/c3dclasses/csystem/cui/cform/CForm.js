//------------------------------------------------------------------
// file: cform.js
// desc: defines a form object for creating controls and options
//------------------------------------------------------------------

//---------------------------------------------------------
// class: CForm
// desc: defines the form used in the application
//---------------------------------------------------------
var CForm = new Class({ 
	// constructing
	initialize : function(COptionsType, CControlsType){
		COptionsType = COptionsType || "COptions";
		CControlsType = CControlsType || "CControls";
		// members
		this.m_coptions = new window[COptionsType]();
		this.m_ccontrols = new window[CControlsType]();	
		this.m_params = null;
		this.m_strid = "";
		this.m_strcmemoryid = "";
	}, // end initialize()
	
	create : function(strname, params) {
		this.m_ccontrols.create(this);
		this.m_coptions.create(this);
		this.m_params = params;
		this.m_strid = strname;
		return true;
	}, // end create()
		
	setID : function(strid) {
		this.m_strid = strid;
	}, // end setID()
		
	getID : function() { 
		return this.m_strid;
	}, // end getID()
	
	setCMemoryID : function(strid) {
		this.m_strcmemoryid = strid;
		return;
	}, // end setCMemoryID()
	
	getCMemoryID : function() {
		return this.m_strcmemoryid;
	}, // end getCMemoryID()
	
	getParams : function() { 
		return this.m_params; 
	}, // end getParams()
	
	setParam : function(strname, value) { 
		this.m_params = this.m_params || {}
		this.m_params[strname] = value;		
	}, // end setParam()
	
	getParam : function(strname) { 
		var params = this.getParams();
		return (params && params[strname]) ? params[strname] : null; 
	}, // end getParam()

	getCOptions : function() { 
		return this.m_coptions; 
	}, // end getCOtions()
	
	getCControls : function() { 
		return this.m_ccontrols; 
	} // end getCControls()
}); // end CForm