//-------------------------------------------------------
// name: CControlsCommand.java
// desc: 
//-------------------------------------------------------
import javax.swing.JOptionPane;
import cglobal.*;
import c3dclasses.csystem.cui.*;
import c3dclasses.ccore.*;
import c3dclasses.csystem.cdevice.*;

public class CControlsCommand {	
	static public CControls m_ccontrols = null;			
	static public CMemory m_cmemory = null;
	static public String m_strcommandname = "ccontrol-command";
				
	public static void main(String[] args) {
		if(args.length < 2) {
			CLog.error("CControlsCommand.main(): No Configuration file was setup.");
			CLog.error("CControlsCommand.main(): Please create a config.json file.");
			CLog.print();
			return;
		} // end if
		if(CControlsCommand.doInit(args[1]) == false) {
			CLog.print();
			return;
		} // end if
		CMemory cmemory = CControlsCommand.m_cmemory;
		CControls ccontrols = CControlsCommand.m_ccontrols;
		String strcommandname = CControlsCommand.m_strcommandname;
		CReturn creturn = null;		
		boolean bDoCommand = true;
		CLog.print();
		while(bDoCommand) {
			creturn = cmemory.retrieve(strcommandname); 	
			String strcommand = (creturn != null && creturn._boolean() == true) ? (String) cmemory.get(strcommandname)._("m_value") : null;
			if(strcommand != null && strcommand != "") {
				bDoCommand = CControlsCommand.doCommand(strcommand);
				cmemory.delete(strcommandname);
				CLog.print();
			} // end if
		} // end while
	} // end main()
	
	public static boolean doInit(String strconfigfile) {
		CHash config = (CHash) _.json_decode_from_file(strconfigfile, true);	
		String strid = (String)config._("cmemory-id");
		String strpath = (String)config._("cmemory-path");
		String strdriver = (String)config._("cmemory-driver");
		String strcommandname = CControlsCommand.m_strcommandname;
		if(CMemory.include(strid, strpath, strdriver, null) == null) {
			CLog.error("CControlsCommand.doInit(): Couldn't create memory from configuration: ["+strid+","+strpath+","+strdriver+"].");
			CLog.error("CControlsCommand.doInit(): Please try a different configuration.");
			return false;
		} // end if
		CMemory cmemory = (CMemory) CMemory.use(strid);
		if(cmemory == null) {
			CLog.error("CControlsCommand.doInit(): Please try a different configuration.");
			return false;
		} // end if
		CControls ccontrols = new CControls();
		if(ccontrols == null) {
			CLog.error("CControlsCommand.doInit(): Please try a different configuration.");
			return false;		
		} // end if
		cmemory.delete(strcommandname); // clear the command queue
		CControlsCommand.m_ccontrols = ccontrols;
		CControlsCommand.m_cmemory = cmemory;
		CLog.info("CControlsCommand.doInit(): Successfully initialized.");
		return true;	
	} // end createCMemory() 
	
	public static boolean doCommand(String strcommand) {
		CArray carray = _.split(" ", strcommand);
		strcommand = (String) carray._(0);
		String strparam1 = (String) carray._(1);
		String strparam2 = (String) carray._(2);
		String strparam3 = (String) carray._(3);
		String strparam4 = (String) carray._(4);		
		CControls ccontrols = CControlsCommand.m_ccontrols;
		CMemory cmemory = CControlsCommand.m_cmemory;
		if(strcommand.equals("print") == true) {
			String strcontents = ccontrols.toStringContents();
			if(strparam1 != null && strparam1.equals("mb") == true)
				_.alert(strcontents);	
			else _.println(ccontrols.toStringContents());
		} // end if
		else if(strcommand.equals("create") == true)
			ccontrols.createEx(strparam1, strparam2, strparam3, null);
		else if(strcommand.equals("update") == true) {
			CControl ccontrol = ccontrols.retrieve(strparam1);
			if(ccontrol != null)	
				ccontrol.setProp(strparam2, strparam3);
		} // end else if
		CLog.info("CControlsCommand.doCommand(): " + carray.toString());
		return (strcommand.equals("deinit") == true) ? false : true;
	} // end doCommand()
} // end CMessageBox
