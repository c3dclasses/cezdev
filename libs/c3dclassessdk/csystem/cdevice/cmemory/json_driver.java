//----------------------------------------------------------------
// file: json_driver
// desc: 
//----------------------------------------------------------------
import c3dclasses.*;

//----------------------------------------------------------------
// class: json_driver
// desc: 
//----------------------------------------------------------------
public class json_driver extends CJSONMemoryDriver {
	public json_driver() {		
		super();
		CFunction.map("json_driver.open", "c3dclasses.CJSONMemoryDriver.open");
		CFunction.map("json_driver.close","c3dclasses.CJSONMemoryDriver.close");
		CFunction.map("json_driver.save","c3dclasses.CJSONMemoryDriver.save");
		CFunction.map("json_driver.create","c3dclasses.CJSONMemoryDriver.create");
		CFunction.map("json_driver.retrieve","c3dclasses.CJSONMemoryDriver.retrieve");
		CFunction.map("json_driver.update","c3dclasses.CJSONMemoryDriver.update");
		CFunction.map("json_driver.delete","c3dclasses.CJSONMemoryDriver.delete");
		CFunction.map("json_driver.sync","c3dclasses.CJSONMemoryDriver.sync");		
		CFunction.map("json_driver.restore","c3dclasses.CJSONMemoryDriver.restore");
	} // end json_driver()
} // end json_driver