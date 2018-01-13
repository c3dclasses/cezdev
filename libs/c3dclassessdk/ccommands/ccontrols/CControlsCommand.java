//-------------------------------------------------------
// name: CControlsCommand.java
// desc: controls command object
//-------------------------------------------------------
import javax.swing.JOptionPane;
import c3dclasses.*;

//-------------------------------------------------------
// name: CControlsCommand
// desc: controls command object
//-------------------------------------------------------	
public class CControlsCommand {	
	protected CHash m_ccontrols;			
	protected CMemory m_cmemory;
	protected String m_strcommandname;	
	
	//-------------------------------------------------------
	// name: CControlsCommand()
	// desc: constructor
	//-------------------------------------------------------	
	public CControlsCommand() {
		this.m_cmemory = null;
		this.m_ccontrols = null;//_.chash();
		this.m_strcommandname = "ccontrol-command";	// stores the command name
	} // end CControlsCommand()
	
	//-------------------------------------------------------
	// name: create()
	// desc: creates and initializes the ccontrols object
	//-------------------------------------------------------	
	public boolean create(String [] args) {
		if(args.length < 3) {
			CLog.error("CControlsCommand.create(): No Configuration file was setup.");
			CLog.error("CControlsCommand.create(): Please create a config.json file.");
			return false;
		} // end if		
		String strid = args[0];
		String strpath = args[1];
		String strtype = args[2];
		String strcontrolid = (args.length >= 4) ? args[3] : "default";
		if(CMemory.include(strid, strpath, strtype, null) == null) {
			CLog.error("CControlsCommand.create(): Couldn't create memory from configuration: ["+strid+","+strpath+","+strtype+"].");
			CLog.error("CControlsCommand.create(): Please try a different configuration.");
			return false;
		} // end if
		CMemory cmemory = (CMemory) CMemory.use(strid);
		if(cmemory == null) {
			CLog.error("CControlsCommand.create(): Please try a different configuration.");
			return false;
		} // end if
		CControls ccontrols = new CControls();
		if(ccontrols == null) {
			CLog.error("CControlsCommand.create(): Please try a different configuration.");
			return false;		
		} // end if
		cmemory.delete(this.m_strcommandname); // clear the command queue
		this.m_ccontrols = ccontrols;//_(strcontrolid, ccontrols);
		this.m_cmemory = cmemory;
		CLog.info("CControlsCommand.create(): Successfully initialized.");
		return this.verifyOneInstance();	
	} // end create() 

	//----------------------------------------------------------------
	// name: verifyOneInstance()
	// desc: returns true if one process is running otherwise false
	//----------------------------------------------------------------
	protected boolean verifyOneInstance() {
		CReturn creturn = this.m_cmemory.retrieve("ccontrols-command-pid"); 	
		long pid = (creturn != null && creturn._boolean() == true) ? 
				(long) this.m_cmemory.get("ccontrols-command-pid")._int("m_value") : -1;
		boolean bpid = _.is_pid_running(pid); 
		long this_pid = _.get_pid();
		if(!bpid) {
			_.alert(pid + ": process is running");
			this.m_cmemory.upsert("ccontrols-command-pid", this_pid + "", "string", null);
			pid = this_pid;
		} // end if
		return pid == this_pid;
	} // end verifyOneInstance()
	
	//-----------------------------------------------------------
	// name: doCommand()
	// desc: gets the command from memory and processes it 
	//-----------------------------------------------------------
	public boolean doCommand() {
		boolean bDoCommand = true;
		CReturn creturn = this.m_cmemory.retrieve(this.m_strcommandname); 	
		String strcommand = (creturn != null && creturn._boolean() == true) ? 
			(String) this.m_cmemory.get(this.m_strcommandname)._("m_value") : null;
		if(strcommand != null && strcommand != "") {
			bDoCommand = this.processCommand(strcommand); 	// process the command
			this.m_cmemory.delete(this.m_strcommandname);	// clear the command
		} // end if
		return bDoCommand;
	} // end doCommand()
	
	//----------------------------------------------------
	// name: processCommand()
	// desc: processes the command
	//----------------------------------------------------	
	protected boolean processCommand(String strcommand) {	
		CArray carray = _.split(" ", strcommand);
		strcommand = (String) carray._(0);
		String strparam1 = (String) carray._(1);
		String strparam2 = (String) carray._(2);
		String strparam3 = (String) carray._(3);
		String strparam4 = (String) carray._(4);		
		CControls ccontrols = (CControls) this.m_ccontrols;
		if(ccontrols == null)
			return true;
		// crud
		if(strcommand.equals("-create") == true) {
			ccontrols.createEx(strparam1, strparam2, strparam3, null);
		}
		else if(strcommand.equals("-update") == true) {
			CControl ccontrol = ccontrols.retrieve(strparam1);
			if(ccontrol != null)	
				ccontrol.setProp(strparam2, strparam3);
		} // end else if
		
		// other
		else if(strcommand.equals("-print") == true) {
			String strcontents = ccontrols.toStringContents();
			if(strparam1 != null && strparam1.equals("mb") == true)
				_.alert(strcontents);	
			else _.println(strcontents);
		} // end if
		_.alert("process commands 4");
		CLog.info("CControlsCommand.processCommand(): " + carray.toString());
		return (strcommand.equals("-deinit") == true) ? false : true;
	} // end doCommand()
	
	//--------------------------------------------------
	// name: main()
	// desc: runs the command
	//--------------------------------------------------
	public static void main(String[] args) {
		CControlsCommand ccontrolscommand = new CControlsCommand();
		if(!ccontrolscommand.create(args)) {
			CLog.print();
			return;
		} // end if
		while(ccontrolscommand.doCommand())
			CLog.print();
		return;
	} // end main()
} // end CControlsCommand