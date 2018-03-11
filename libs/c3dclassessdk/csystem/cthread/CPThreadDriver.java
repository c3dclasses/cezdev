//-----------------------------------------------------------
// name: CPThreadDriver
// desc: 
//-----------------------------------------------------------
package c3dclasses;

//--------------------------------------------------------
// name: CPThreadDriver
// desc: 
//--------------------------------------------------------
public class CPThreadDriver extends CDriver {
	static public int m_icounter = 0;
	public CPThreadDriver() {
		super();
		new CFunction("c3dclasses.CPThreadDriver.create") {
			public CReturn call(Object object) { 
				CThread cthread = (CThread) object;
				cthread._("m_outparam", CPThreadDriver.initCThread(cthread));
				return null;
			} // end call()		
		}; // end create()
		
		new CFunction("c3dclasses.CPThreadDriver.retrieve") {
			public CReturn call(Object object) { 
				CThread cthread = (CThread) object;
				int ioptype = cthread._int("m_ioptype");
				switch(ioptype) {
					case CThread.NAME: cthread._("m_outparam", cthread._("m_strname")); break;
					case CThread.PRIORITY: cthread._("m_outparam", cthread._int("m_ipriority")); break;
					case CThread.DAEMON: cthread._("m_outparam", cthread._boolean("m_bdaemon")); break;
					case CThread.ID: cthread._("m_outparam", cthread._int("m_iid")); break;
					case CThread.ALIVE: cthread._("m_outparam", cthread._int("m_balive"));  break;
					case CThread.INTERRUPT: cthread._("m_outparam", cthread._int("m_binterrupt")); break;
					case CThread.STATE: cthread._("m_outparam", cthread._int("m_istate")); break;
					case CThread.JOIN: break;	
				} // end switch()
				return null;
			} // end call()
		}; // end retrieve()
		
		new CFunction("c3dclasses.CPThreadDriver.update") {
			public CReturn call(Object object) { 			
				try {
					CThread cthread = (CThread) object;
					int ioptype = cthread._int("m_ioptype");
					switch(ioptype) {
						case CThread.START: 
							_.alert("starting");
							_.alert(cthread._int("m_iintervalms"));
							if(cthread._cfunction("m_fnrun") != null)
								cthread._("m_iintervalid", _.setInterval(cthread._cfunction("m_fnrun"), cthread._int("m_iintervalms")));		
						break;
						case CThread.ID: cthread._("m_iid", cthread._string("m_inparam")); break;
						case CThread.NAME: cthread._("m_strname", cthread._string("m_inparam")); break;
						case CThread.PRIORITY:  cthread._("m_ipriority", cthread._int("m_inparam")); break;
						case CThread.DAEMON:  cthread._("m_bdaemon", cthread._boolean("m_inparam")); break;
						case CThread.SLEEP: cthread._("m_iintervalms", cthread._long("m_inparam")); cthread.stop(); cthread.start(); break;
						case CThread.INTERRUPT:
						case CThread.STOP: 
							_.clearInterval(cthread._int("m_iintervalid")); 
							cthread._("m_iintervalid", -1); 
						break;
						case CThread.JOIN: break;
						case CThread.ALIVE: break;
					} // end switch()
					return null;
				} // end try
				catch(Exception ex) {
				} // end catch()
				return null;
			} // end call()
		}; // end update()
		
		new CFunction("c3dclasses.CPThreadDriver.delete") {
			public CReturn call(Object object) { 
				CThread cthread = (CThread) object;
				_.clearInterval(cthread._int("m_iintervalid"));
				cthread._("m_iintervalid", -1);
				return null;
			} // end call()
		}; // end CThreadDriver.delete()			
	} // end CPThreadDriver()
	
	public static boolean initCThread(CThread cthread) {
		if(cthread == null)
			return false;
		cthread._("m_iid", CPThreadDriver.m_icounter);
		cthread._("m_strname", "");
		cthread._("m_ipriority", -1);
		cthread._("m_bdaemon", false);
		cthread._("m_balive", true);
		cthread._("m_binterrupt", false);
		cthread._("m_istate", -1);
		cthread._("m_iiteration", 0);
		cthread._("m_fnrun", new CPThreadFunction(cthread));
		cthread._("m_iintervalms", 0);
		cthread._("m_inparam", null);
		cthread._("m_outparam", null);
		cthread._("m_iintervalid", -1); 
		CPThreadDriver.m_icounter++;
		return true;
	} // end init()
} // end CPThreadDriver

//--------------------------------------------------
// name: CPThreadFunction
// desc:
//--------------------------------------------------
class CPThreadFunction extends CFunction {
	protected CThread m_cthread = null;
	public CPThreadFunction(CThread cthread) {
		super();
		this.m_cthread = cthread;
	} // end CPThreadFunction()
	public CReturn call(CArray args) {
		if(this.m_cthread != null && this.m_cthread._("m_fncallback") != null) { 
			//_.alert("running the cfunction");
			this.m_cthread._cfunction("m_fncallback").call((Object)this.m_cthread); 
			this.m_cthread._("m_iiteration", this.m_cthread._int("m_iiteration") + 1); 
		} // end if
		return null;	
	} // end call()
	public CReturn call(Object object) {
		if(this.m_cthread != null && this.m_cthread._("m_fncallback") != null) { 
			//_.alert("running the cfunction");
			this.m_cthread._cfunction("m_fncallback").call((Object)this.m_cthread); 
			this.m_cthread._("m_iiteration", this.m_cthread._int("m_iiteration") + 1); 
		} // end if
		return null;	
	} // end call()
} // end CPThreadFunction