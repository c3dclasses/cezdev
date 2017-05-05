//---------------------------------------------------------------------------------
// file: cunittest.js
// desc: defines an object to perform unit testing    
//---------------------------------------------------------------------------------

//----------------------------------------------------------------
// class CUnitTest
// desc: defines an object to perform unit testing
//----------------------------------------------------------------
var CUnitTest = new Class ({
	m_assert : null,	
	ClassMethods : {	
		m_cunittesttypes:null,
		
		register: function(cunittesttype, params) {
			if(!cunittesttype)
				return false;
			if( CUnitTest.m_cunittesttypes == null )
				CUnitTest.m_cunittesttypes = {}; 	
			CUnitTest.m_cunittesttypes[cunittesttype] = params;
			return true;
		}, // end register() 
		
		doTest: function(element) {
			jQuery(element).append('<div id="qunit"></div><div id="qunit-fixture"></div>');
			if(!CUnitTest.m_cunittesttypes)
				return;
			var types =  CUnitTest.m_cunittesttypes;
			for (var type in types) {
				var typename = type;
				var typeparams = types[typename];
				if(test && test.create(typeparams)) {
					QUnit.test(typename, function(type) {
						return function(assert) {
							var test = new window[type]();
							test.setAssert(assert);
							test.run();
						} // end function()
					}(typename)); // end QUnit.test()
				} // end if
			} // end foreach()
			return;
		} // end doTest()
	}, // end ClassMethods
	
	initialize : function() { 
	}, // end initialize()
	
	create : function() {
		return true;	
	}, // end create()
	
	setAssert : function(assert){
		this.m_assert = assert;
	}, // end setAssert()
	
	assertTrue : function(condition){
		this.m_assert.ok(condition, "Passed!");
	}, // end assertTrue()
	
	assertFalse : function(condition){
		this.m_assert.ok(!condition, "Passed!");
	}, // end assertTrue()
		
	run: function() {
		var obj=this;
		for(var m in obj) {
        	var patt = new RegExp("^test");
			if(typeof obj[m] == "function" && patt.test(m)) {	
				obj[m]();		
			} // end if()
    	} // end for()
	} // end run()
}); // end CUnitTest

//----------------------------------------------------------------------------------------
// name: use_test(), include_test(), include_tests()
// desc: include a bunch of test files with a given extension and from a given path 
//----------------------------------------------------------------------------------------
function include_unittest(strunittesttype,params){ CUnitTest.register( strunittesttype, params ); }