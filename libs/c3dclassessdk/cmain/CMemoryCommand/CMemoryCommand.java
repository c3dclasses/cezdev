//-------------------------------------------------------
// name: CMemoryCommand.java
// desc: defines the a command that uses CMemory object
//-------------------------------------------------------
import javax.swing.JOptionPane;
import cglobal.*;
import c3dclasses.ccore.*;
import c3dclasses.csystem.cdevice.*;

public class CMemoryCommand {				
	public static void main(String[] args) {		
		if(args.length < 2) {
			_.println("ERROR: No Configuration file was setup.");
			_.println("Please create a config.json file.");
			return;
		} // end if	
		CHash config = (CHash) _.json_decode_from_file(args[1], true);	
		String strid = (String)config._("cmemory-id");
		String strpath = (String)config._("cmemory-path");
		String strdriver = (String)config._("cmemory-driver");
		String strcommand = (args.length < 3) ? "" : args[2];
		String strname = (args.length < 4) ? "" : args[3].trim();
		String strvalue = (args.length < 5) ? "" : args[4].trim();	
		String strtype = (args.length < 6) ? "string" : args[5];	
		if(CMemory.include(strid, strpath, strdriver, null) == null) {
			_.println("ERROR: Couldn't create memory from configuration: ["+strid+","+strpath+","+strdriver+"].");
			_.println("Please try a different configuration.");
			return;
		} // end if
		CMemory cmemory = (CMemory) CMemory.use(strid);
		CReturn creturn = null;		
		if(strcommand.equals("fcreate") == true || strcommand.equals("fupdate") == true) {
			strvalue = _.getFileContents(strvalue);
			_.alert(strvalue);
		}
		
		if(strcommand.equals("create") == true || strcommand.equals("fcreate") == true) {
			creturn = cmemory.create(strname, strvalue, strtype, null);
			if(creturn == null || creturn._boolean() == false)
				creturn = cmemory.update(strname, strvalue, strtype, null);	
		} // end if
		else if(strcommand.equals("update") == true || strcommand.equals("fupdate") == true)
			creturn = cmemory.update(strname, strvalue, strtype, null);	
		else if(strcommand.equals("retrieve") == true) { 
			creturn = cmemory.retrieve(strname);	
			if(creturn._boolean())
				_.print(cmemory.get(strname)._("m_value"));
			return;
		} // end if
		else if(strcommand.equals("delete") == true)
			creturn = cmemory.delete(strname);	
		else if(strcommand.equals("sync") == true)
			creturn = cmemory.sync();	
		else if(strcommand.equals("cache") == true)
			_.println(cmemory.toString());
		else if(strcommand.equals("config") == true)
			_.println(cmemory.toString());
		if(creturn != null)
			_.println(creturn);
	} // end main()
} // end CMemoryProgram