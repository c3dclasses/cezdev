//----------------------------------------------------------------
// file: cremotememory.drv.js
// desc: defines the remote memory driver object
//----------------------------------------------------------------

//----------------------------------------------------------------
// class: CRemoteMemoryDriver
// desc: defines the remote memory driver object
//----------------------------------------------------------------
var CRemoteMemoryDriver = new Class({
	Extends: CMemoryDriver,
	
	create: function(cvar) {
		return this.triggerRemoteOperation({
			"memtype":this.type(),
			"mempath":this.path(),
			"memid":this.id(), 
			"memcommand":"create", 
			"memvar":cvar
		}); // end triggerRemoteOperation() 
	}, // end create() 
	
	retrieve: function(strname) { 
		return this.triggerRemoteOperation({
			"memtype":this.type(),
			"mempath":this.path(),
			"memid":this.id(), 
			"memcommand":"retrieve", 
			"memvarname":strname 
		}); // end triggerRemoteOperation() 
	}, // end retrieve()
	
	update: function(cvar) { 
		return this.triggerRemoteOperation( {
			"memtype":this.type(),
			"mempath":this.path(),
			"memid":this.id(), 
			"memcommand":"update", 
			"memvar":cvar
		}); // end triggerRemoteOperation()
	}, // end update() 
	
	delete: function(strname) { 
		return this.triggerRemoteOperation( {
			"memtype":this.type(),
			"mempath":this.path(),
			"memid":this.id(), 
			"memcommand":"delete", 
			"memvarname":strname 
		}); // end triggerRemoteOperation() 
	}, // end delete()
	
	sync: function(cache) {	
		return this.triggerRemoteOperation( {
			"memtype":this.type(),
			"mempath":this.path(),
			"memid":this.id(), 
			"memcommand":"sync", 
			"memcache":(cache)?JSON.stringify(cache):"null"
		}); // end triggerRemoteOperation()
	},	// end sync()
	
	type : function() {
		return this.param("cremotememorydriver_type");
	}, // end type()
	
	uri : function() {
		return this.param("cremotememorydriver_uri");
	}, // end uri()
	
	id : function() {
		return this.param("cremotememorydriver_id");		
	}, // end id()
	
	triggerRemoteOperation : function(inparams) {
		var struri = this.uri();
		
		console.log(this);
		
		alert(struri);
		var cds = new CDataStream();	// create
		if(!struri || !cds || cds.open(struri, "post", "cremotememorydriver") == false) // open
        		return _return_done(null);	
    	cds.setDataParam("cremotememorydriver",true);
    	cds.setDataParam("cremotememorydriver_uri",struri);	// server of the function
		cds.setDataParam("cremotememorydriver_type",this.type());	// file of the function 
		cds.setDataParam("cremotememorydriver_id",this.id()); 	// name of the function
		cds.setDataParam("cremotememorydriver_params", this.m_hashparams.m_hash);
		
		if(inparams && typeof(inparams) == "object")
        	for(var name in inparams)
           		cds.setDataParam(name, inparams[name]);
		else cds.setDataParam("cremotememorydriver_inparam", inparams);
    	
		console.log(cds);
		
		alert("done");
		return cds.send();
	} // end triggerRemoteOperation()
}); // end CRemoteMemoryDriver