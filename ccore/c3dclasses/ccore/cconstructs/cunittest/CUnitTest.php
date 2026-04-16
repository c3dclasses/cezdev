<?php
//---------------------------------------------------------------------------------
// file: cunittest.js
// desc: defines a unit test object
//---------------------------------------------------------------------------------

// includes
include_once("simpletest/unit_tester.php");
include_once("simpletest/reporter.php");
include_once("simpletest/mock_objects.php");
include_js( "http://code.jquery.com/qunit/qunit-1.17.1.js" );
include_css( "http://code.jquery.com/qunit/qunit-1.17.1.css" );
include_js( relname(__FILE__) . "/cunittest.js" );

//------------------------------------------
// name: CUnitTest
// desc: defines a unit test object
//------------------------------------------
class CUnitTest extends UnitTestCase {
	// members
	static public $m_cunittesttypes = NULL;	// stores the type of programs that a user can instantiate
	protected $m_params;

	//-----------------------------------------------------------
	// name: CUnitTest() / create()
	// desc: constructs and creates the CUnitTest
	//-----------------------------------------------------------
	public function  	CUnitTest(){ 
	} // end CUnitTest()
	
	public function  	create($params){ 
		$this->m_params = $params;
		return true;
	} // end create()
	
	//public function testCUnitTestClass() {
        //$this->assertTrue(1==1);	
    //} // end testCUnitTestClass()
	
	// register the test
	public static function 	register( $cunittesttype, $params=NULL ){
		if( class_exists( $cunittesttype ) == false )
			return false;
		if( CUnitTest :: $m_cunittesttypes == NULL )
			CUnitTest :: $m_cunittesttypes = array(); 	
		CUnitTest :: $m_cunittesttypes[$cunittesttype] = $params;
		return true; 
	} // end register()
	
	public static function doTest() {
		if( !CUnitTest :: $m_cunittesttypes )
			return;
		$types =  CUnitTest :: $m_cunittesttypes;
		foreach($types as $typename => $typeparams) {
			$test = new $typename();
			if($test && $test->create($typeparams)) {
				$test->run(new HtmlReporter());
			} // end if()
		} // end foreach()
	} // end doTest()
} // end CUnitTest

//----------------------------------------------------------------------------------------
// name: use_test(), include_test(), include_tests()
// desc: include a bunch of test files with a given extension and from a given path 
//----------------------------------------------------------------------------------------
function include_unittest( $strunittesttype, $params=NULL ){ CUnitTest :: register( $strunittesttype, $params ); }
function use_unittest( $cunittest, $params=NULL ){ return ( $cunittest != NULL && $cunittest->create( $params ) ); }
function include_unittests( $strpath ){ if( function_exists( "includephpfilesfrompath" ) == true ) includephpfilesfrompath( $strpath, ".tst.php" ); }
?>