//-------------------------------------------------------
// name: CMemoryCommand.java
// desc: defines the a command that uses CMemory object
//-------------------------------------------------------
import javax.swing.JOptionPane;
import c3dclasses.*;

//-------------------------------------------------
// name: CMemoryCommand
// desc: 
//-------------------------------------------------
public class CMemoryCommand {	
	public static CMemory m_cdefaultmemory = null;
	public static void main(String[] args) {		
		if(args.length < 3) {
			CLog.error("CMemoryCommand.main() - Incorrect default cmemory driver path and/or type.");
			CLog.error("CMemoryCommand.main() - Please check if the cmemory driver path / type is set and configured properly.");
			CLog.print();
			return;
		} // end if	

		CMemoryCommand.m_cdefaultmemory = (CMemory) CMemory.include("cmemorycommand", args[1], args[2], null);
		if(CMemoryCommand.m_cdefaultmemory == null) {
			CLog.print();
			return;
		} // end if

		String strcommand = (args.length < 4) ? "" : args[3];
		String strname = (args.length < 5) ? "" : args[4].trim();
		String strvalue = (args.length < 6) ? "" : args[5].trim();	
		String strtype = (args.length < 7) ? "" : args[6].trim();	
		String strsecondaryvalue = CMemoryCommand.setValueFromSecondarySource(strcommand, strvalue, strtype);
		CMemory cmemory = CMemoryCommand.setCMemoryDriver();
		CReturn creturn = null;		
		
		if(strsecondaryvalue != null && strsecondaryvalue.equals("") != true) {
			strvalue = strsecondaryvalue; 	
			strcommand = "-create";
		} // end if
		
		// CRUDS	
		if(strcommand.equals("-create") == true || strcommand.equals("-update") == true || strcommand.equals("-set") == true) {
			creturn = cmemory.upsert(strname, strvalue, strtype, null);
			if(creturn._boolean())
				CLog.info(cmemory.get(strname));
		} // end if
		else if(strcommand.equals("-retrieve") == true || strcommand.equals("-get") == true) { 
			creturn = cmemory.retrieve(strname);	
			if(creturn._boolean() && strcommand.equals("-get") == true) {
				String strmember = strvalue;
				if(strmember != null && strmember.equals("") == false)
					_.println(cmemory.get(strname)._(strmember));
				else _.println(cmemory.get(strname)._("m_value"));
			} // end if
			else CLog.info(cmemory.get(strname));
			CLog.print();
			return;
		} // end if
		else if(strcommand.equals("-delete") == true)
			creturn = cmemory.delete(strname);	
		else if(strcommand.equals("-sync") == true)
			creturn = cmemory.sync();		
		
		// other	
		else if(strcommand.equals("-cache") == true)
			_.println(cmemory.cache().toJSON(false));
		else if(strcommand.equals("-config") == true)
			_.println(cmemory.toJSON(false));
		else if(strcommand.equals("-include") == true)
			CLog.info(CMemoryCommand.prompt_includeCMemoryDriver());
		else if(strcommand.equals("-use") == true)
			CLog.info(CMemoryCommand.prompt_useCMemoryDriver());
		
		if(creturn != null)
			CLog.info(creturn.toString());
		CLog.print();		
	} // end main()
	
	///////////////////////////////
	// Helper methods
	
	// prompting user
	
	//---------------------------------------------------------------------
	// name: prompt_includeCMemoryDriver()
	// desc: includes a cmemory driver from a prompt 
	//---------------------------------------------------------------------
	public static boolean prompt_includeCMemoryDriver() {
		String strid = (String)_.prompt("CMemoryDriverID", "Please Enter CMemoryDriverID:", null);
		if(_.empty(strid))
			return false;
		String strpath = _.prompt_file("file","Please Enter CMemoryDriverPath:");
		if(_.empty(strpath))
			return false;
		String strtype = (String) _.prompt("CMemoryDriverType", "Please Enter CMemoryDriverType:", null);
		if(_.empty(strtype))
			return false;
		return CMemoryCommand.includeCMemoryDriver(strid, strpath, strtype);
	} // end prompt_includeCMemoryDriver()

	//-------------------------------------------------------------------
	// name: includeCMemoryDriver()
	// desc: includes the cmemorydriver to be used later
	//-------------------------------------------------------------------
	public static boolean includeCMemoryDriver(String strid, String strpath, String strtype) {
		CMemory cmemory = CMemoryCommand.m_cdefaultmemory;
		CReturn creturn = cmemory.retrieve("cmemory.length");	
		int ilength = (creturn._boolean() == true) ? cmemory.get("cmemory.length")._int("m_value") : 0;
		cmemory.upsert("cmemory["+ilength+"].id", strid, "string", null);
		cmemory.upsert("cmemory["+ilength+"].index", ilength, "string", null);
		cmemory.upsert("cmemory["+ilength+"].path", strpath, "string", null);
		cmemory.upsert("cmemory["+ilength+"].type", strtype, "string", null);
		ilength++;	
		cmemory.upsert("cmemory.length", ilength+"", "string", null);
		return true;		
	} // end includeCMemoryDriver()
	
	//-----------------------------------------------------------------------
	// name: prompt_useCMemoryDriver()
	// desc: use a given memory driver to manipulate memory
	//-----------------------------------------------------------------------
	public static boolean prompt_useCMemoryDriver() {
		CMemory cmemory = CMemoryCommand.m_cdefaultmemory;
		CHash cdrivers =CMemoryCommand.getCMemoryDrivers();
		CReturn creturn = null;
		if(cdrivers == null || cdrivers.size() <= 0) {
			CLog.error("No CMemoryDrivers were included. Please include one using the -include command.");
			_.alert("No CMemoryDrivers were included. Please include one using the -include command.");
			return false;
		} // end if
		CArray carray_drivers = cdrivers.values();
		if(carray_drivers == null || carray_drivers.length() <= 0) {
			CLog.error("No CMemoryDrivers were included. Please include one using the -include command.");
			_.alert("No CMemoryDrivers were included. Please include one using the -include command.");
			return false;
		} // end if
		String str="";
		Object [] choices = carray_drivers.toArray();
		CHash cdriver = (CHash) _.prompt("Please select a CMemoryDriver", str, choices);
		if(cdriver != null) {
			creturn = cmemory.upsert("cmemory.selectedid", cdriver._("id"), "string", null);
			_.alert(cdriver._("id"));
		} // end if
		return true;
	} // end prompt_useCMemoryDriver()
	
	//----------------------------------------------------------------------------------------------------
	// name: setValueFromSecondarySource()
	// desc: sets the value from a secondary source from a command, file, path, or contents from a file 
	//----------------------------------------------------------------------------------------------------
	public static String setValueFromSecondarySource(String strcommand, String strtitle, String strmessage) {  
		boolean bSetValue = false; 
		String strvalue="";
		// set the value from a file
		if(strcommand.equals("-fcreate") == true || strcommand.equals("-fupdate") == true) {
			strvalue = _.get_file_contents(strtitle);
			bSetValue = true;
		} // end if
		// set the value from a prompt dialog
		else if(strcommand.equals("-prompt") == true) {
			strvalue = (String) _.prompt("Please Enter a value", strmessage, null);
			bSetValue = true;
		} // end else if
		// set the value from a file dialog
		else if(strcommand.equals("-promptfile") == true) {
			strvalue = _.prompt_file("file", strtitle);
			bSetValue = true;
		} // end else if
		// set the value from a path dialog
		else if(strcommand.equals("-promptpath") == true) {
			strvalue = _.prompt_path(strtitle);
			bSetValue = true;
		} // end else if
		return (bSetValue && strvalue != null) ? strvalue : null; 
	} // end setValueFromSecondarySource()
	
	//-------------------------------------------------------------------
	// name: getCMemoryDriver()
	// desc: returns a cdriver object at an index
	//-------------------------------------------------------------------
	public static CHash getCMemoryDriver(int index) {
		CMemory cmemory = CMemoryCommand.m_cdefaultmemory;
		CReturn creturn = cmemory.retrieve("cmemory.length");	
		int ilength = 0;
		if(!creturn._boolean())
			return null;
		ilength = cmemory.get("cmemory.length")._int("m_value");
		if(ilength < 1 || index > ilength - 1 || index < 0) 
			return null;
		CHash cdriver = _.chash();
		// id
		creturn = cmemory.retrieve("cmemory["+index+"].id");	
		if(creturn._boolean() == false)
			return null;
		String str = (String)cmemory.get("cmemory["+index+"].id")._("m_value");
		cdriver._("id", str);
		// path
		creturn = cmemory.retrieve("cmemory["+index+"].path");	
		if(creturn._boolean() == false)
			return null;
		str = (String)cmemory.get("cmemory["+index+"].path")._("m_value");
		cdriver._("path", str);
		// type
		creturn = cmemory.retrieve("cmemory["+index+"].type");	
		if(creturn._boolean() == false)
			return null;
		str = (String)cmemory.get("cmemory["+index+"].type")._("m_value");		
		cdriver._("type", str);
		return cdriver;
	} // end getCMemoryDriver()
	
	//-------------------------------------------------------------------
	// name: getCMemoryDrivers()
	// desc: returns an array of cmemorydrivers that was set
	//-------------------------------------------------------------------
	public static CHash getCMemoryDrivers() {
		CHash cdrivers = _.chash();
		CMemory cmemory = CMemoryCommand.m_cdefaultmemory;
		// add default memory
		CHash cdriver = _.chash();	
		cdriver._("id", "cmemorycommand");
		cdriver._("path", cmemory.driver().path());
		cdriver._("type", cmemory.driver().type());
		cdrivers._("cmemorycommand", cdriver);
		int index = 0;
		for(cdriver=CMemoryCommand.getCMemoryDriver(index); 
			cdriver!=null; 
			cdriver=CMemoryCommand.getCMemoryDriver(++index)) {
			cdrivers._(cdriver._("id"), cdriver);
		} // end for
		return cdrivers; 
	} // end getCMemoryDrivers()
	
	//-------------------------------------------------------------------
	// name: setCMemoryDrivers()
	// desc: sets the cmemory driver object
	//-------------------------------------------------------------------
	public static CMemory setCMemoryDriver() {
		CMemory cmemory = CMemoryCommand.m_cdefaultmemory;		
		CReturn creturn = cmemory.retrieve("cmemory.selectedid");	
		if(creturn._boolean() == false)
			return cmemory;
		String strid = (String) cmemory.get("cmemory.selectedid")._("m_value");
		CHash chash = getCMemoryDrivers();
		if(chash == null || chash.size() <= 0)
			return cmemory;
		CHash cdriver = (CHash) chash._(strid);	
		return (CMemory) CMemory.include((String) cdriver._("id"), (String) cdriver._("path"), (String) cdriver._("type"), null);
	} // end setCMemoryDriver()
} // end CMemoryProgram

