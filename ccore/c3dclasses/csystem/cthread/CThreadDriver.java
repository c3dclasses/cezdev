//-----------------------------------------------------------
// name: CThreadDriver
// desc: 
//-----------------------------------------------------------
package c3dclasses;

//--------------------------------------------------------
// name: CThreadDriver
// desc: 
//--------------------------------------------------------
public class CThreadDriver extends CDriver {
	public CThreadDriver() {
		super();
		new CFunction("c3dclasses.CThreadDriver.create") {
			public CReturn call(Object object) { 
				CThread cthread = (CThread) object;
				cthread._("m_outparam", (new CJavaThread(cthread) != null));		
				return null;
			} // end call()		
		}; // end cthreaddiver.create()
		
		new CFunction("c3dclasses.CThreadDriver.retrieve") {
			public CReturn call(Object object) { 
				CThread cthread = (CThread) object;
				CJavaThread cjavathread = (CJavaThread) cthread._("m_cjavathread");
				int ioptype = cthread._int("m_ioptype");
				switch(ioptype) {
					case CThread.NAME: cthread._("m_outparam", cjavathread.getName()); break;
					case CThread.PRIORITY: cthread._("m_outparam", cjavathread.getPriority()); break;
					case CThread.DAEMON: cthread._("m_outparam", cjavathread.isDaemon()); break;
					case CThread.ID: cthread._("m_outparam", cjavathread.getId()); break;
					case CThread.ALIVE: cthread._("m_outparam", cjavathread.isAlive()); break;
					case CThread.INTERRUPT: cthread._("m_outparam", cjavathread.isInterrupted()); break;
					case CThread.STATE: cthread._("m_outparam", cjavathread.getState()); break;
					case CThread.JOIN: break;	
				} // end switch()
				return null;
			} // end call()
		}; // end CThreadDriver.retrieve()
		
		new CFunction("c3dclasses.CThreadDriver.update") {
			public CReturn call(Object object) { 
				try {
					CThread cthread = (CThread) object;
					CJavaThread cjavathread = (CJavaThread) cthread._("m_cjavathread");
					int ioptype = cthread._int("m_ioptype");
					switch(ioptype) {
						case CThread.START: cjavathread.start(); break;
						case CThread.NAME: cjavathread.setName(cthread._string("m_inparam")); break;
						case CThread.PRIORITY: cjavathread.setPriority(cthread._int("m_inparam")); break;
						case CThread.DAEMON: cjavathread.setDaemon(cthread._boolean("m_inparam")); break;
						//case CThread.ALIVE: cjavathread.setName(cthread._string("m_inparam")); break;
						case CThread.JOIN: cjavathread.join(); break;
						case CThread.SLEEP: cjavathread.sleep(cthread._long("m_inparam")); break;
						case CThread.INTERRUPT: cjavathread.interrupt(); break;
					} // end switch()
					return null;
				} // end try
				catch(Exception ex) {
				} // end catch()
				return null;
			} // end call()
		}; // end CThreadDriver.update()
		
		new CFunction("c3dclasses.CThreadDriver.delete") {
			public CReturn call(Object object) { 
				CThread cthread = (CThread) object;
				return null;
			} // end call()
		}; // end CThreadDriver.delete()
	} // end CThreadDriver()
} // end CThreadDriver

//--------------------------------------------------------
// name: CJavaThread
// desc: defines a java thread object
//--------------------------------------------------------
class CJavaThread extends Thread {	
	protected CThread m_cthread = null;
	public CJavaThread(CThread cthread) { this.m_cthread = cthread; cthread._("m_cjavathread", this); }
	public void run() { __.println("running CJavaThread"); this.m_cthread._cfunction("m_fncallback").call((Object)this.m_cthread); }
} // end CJavaThread