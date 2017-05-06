//---------------------------------------------------------
// file: coptions.js
// desc: defines the options object of the form
//---------------------------------------------------------

//-----------------------------------------------------------------
// name: COptions
// desc: defines the options object of the form
//-----------------------------------------------------------------
var COptions = new Class({
 	initialize : function(){ this.m_cform = null; },
	create : function(cform){ this.m_cform = cform; return true; },
	optionExists : function(strname) { return (this.processOption("get",strname) != null); }, 
	removeOption : function(strname){ this.processOption("remove", strname); },
	option : function() { 
		if(arguments.length > 1){ 
			this.processOption("set", arguments[0], arguments[1]);
			return this;
		} // end if
		else return this.processOption("get", arguments[0]);
	}, // end option()
	processOption : function(stroperator, strname, strvalue, params){ 
		if(!this.m_cform || !strname || strname == "")
			return;
		_params={};
		_params["coption-operator"]=stroperator;
		_params["coption-name"]=strname;
		_params["coption-value"]=strvalue;
		_params["coption-params"]=params;
		_params["cform-id"]=this.m_cform.getID();
		_params["cmemory-id"]=this.m_cform.getCMemoryID();
		return this.processParams(_params);
	}, // end processOption()
	// override this behavior to process the option
	processParams : function(params){ return COptions_processParams(params); } 
}); // end COptions