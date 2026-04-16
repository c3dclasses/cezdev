//---------------------------------------------------------------------------------
// file: CCommand
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------------------
// name: CCommand
// desc: 
//---------------------------------------------------------------------------------
public class CCommand extends CFunction {
	public String m_strname = "";
	public String m_strscriptname = "";
	public CArray m_tmpfilename = __.carray();
	public static int m_invocationid = -1;
	public CCommand(String strname) { this(strname, ""); }
	public CCommand(String strname, String strscriptname) { 
		this.m_strname = __.check_file_path(strname);
		this.m_strscriptname = __.check_file_path(strscriptname);
		CFunction.set((this.m_strname + " " + this.m_strscriptname).trim(), this);	
	} // end CCommand()
	public CReturn call(CArray args) { 
		this.m_invocationid++;
		String strargs = this.argsToString(args);
		CReturn creturn = CReturn._done(__.exec_command(strargs));
		if(creturn.isdone()) {
			this.removeTmpFiles();
		} // end if
		return creturn;
	} // end call()
	//////////////////
	// helper
	public String argsToString(CArray args) {
		int ivid = this.m_invocationid;
		int itime = (int)__.time();
		String strargs = this.m_strname + " " + this.m_strscriptname;
		for(int i=0; i<args.length(); i++) {
			if(args._is_cmatrix(i)) {
				String strfilename = __.get_home_path() + "/argfilename_" + ivid + "_" + i + "_" + itime;  
				args._cmatrix(i).toFile(strfilename);
				args._(i, strfilename); // change arg to filename
				m_tmpfilename.push(strfilename);
			} // end if
			else if(args._is_cvector(i)) {
				String strfilename = __.get_home_path() + "/argfilename_" + ivid + "_" + i + itime;  
				args._cmatrix(i).toFile(strfilename);
				args._(i, strfilename); // change arg to filename
				m_tmpfilename.push(strfilename);
			} // end if
			if(args._is_string(i) && (args._string(i).indexOf(" ") != -1 || args._string(i).indexOf("\t") != -1))  
				args._(i, __.s(args._string(i))); // change arg to filename		
		} // end for
		return strargs + " " + args.join(" ");
	} // end argsToString()
	public void removeTmpFiles() {
		while(m_tmpfilename.length() > 0) {
			String strtmpfilename = (String) m_tmpfilename.pop();
			__.file_delete(strtmpfilename);
		} // end while()
		return;
	} // end removeTmpFiles()
} // end CCommand