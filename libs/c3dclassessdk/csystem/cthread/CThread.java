//-----------------------------------------------------------
// name: CThread
// desc: 
//-----------------------------------------------------------
package c3dclasses;

//--------------------------------------------------------
// name: CThreadd
// desc: 
//--------------------------------------------------------
public class CThread extends CObject {
	public CThread() { this("c3dclasses.CThreadDriver"); }
	public CThread(String strcthreaddivertype) { 
		CDriver.include(strcthreaddivertype);
		this._("m_fncallback", null); 
		this._("m_iintervalms", -1);
		this._("m_strdrivertype", strcthreaddivertype);
	} // end CThread()
	
	// create
	public boolean create(CFunction fncallback) { 
		if(fncallback == null)
			return false;
		this._("m_fncallback", fncallback); 
		this.driver("create", NOOP);
		return this._boolean("m_outparam");
	} // end create()
	
	public boolean create(int iintervalms, CFunction fncallback) { 
		boolean ret = (iintervalms < 1 || !this.create(fncallback));
		if(ret == true) 
			return false;
		this._("m_iintervalms", iintervalms);
		return true;
	} // end create()
	
	// retrieve
	public String name() { this.driver("retrieve", NAME); return this._string("m_outparam"); }
	public int priority() {this.driver("retrieve", PRIORITY); return this._int("m_outparam"); }
	public boolean daemon() { this.driver("retrieve", DAEMON); return this._boolean("m_outparam"); }
	public long id() { this.driver("retrieve", ID); return this._long("m_outparam"); }
	public boolean alive() { this.driver("retrieve", ALIVE); return this._boolean("m_outparam"); }
	public boolean interrupted() { this.driver("retrieve", INTERRUPT); return this._boolean("m_outparam"); }
	
	// update
	public boolean start() { this.driver("update", START); return this._boolean("m_outparam"); }
	public boolean stop() { this.driver("update", STOP); return this._boolean("m_outparam"); }
	
	public void name(String name) { this._("m_inparam", name); this.driver("update", NAME); }
	public void priority(int ipriority) { this._("m_inparam", ipriority); this.driver("update", PRIORITY); }
	public void daemon(boolean bon) { this._("m_inparam", bon); this.driver("update", DAEMON); }
	public void sleep(int iintervalms) { this._("m_inparam", iintervalms); this.driver("update", SLEEP); }
	public void join(int iintervalms) { this._("m_inparam", iintervalms); this.driver("update", JOIN); }
	public void interrupt() { this.driver("update", INTERRUPT); }
	
	public void jump(CFunction fncallback) { if(fncallback == null){ this.interrupt(); } this._("m_fncallback", fncallback); }
	public void jump(String strcallback) { this.jump(_.cfunction(strcallback)); }
	
	public boolean lock(CFunction fnlock) {
		if(fnlock == null)
			return true;
		if(fnlock._("cthread") == null) {
			fnlock._("cthread", this);
			this._("m_fnlock", fnlock);
		} // end if
		if(fnlock._("cthread") != null)
			return true;
		return false;
	} // end lock()
	
	public boolean unlock() {
		CFunction fnunlock = this._cfunction("m_fnlock");
		if(fnunlock == null || fnunlock._("cthread") == null)
			return true;
		if(fnunlock._("cthread") != this)
			return false;
		fnunlock._("cthread", null);
		return true;
	} // end unlock()
	
	//public static CThread currentThread() { this.driver("update", START); }
	
	// delete
	public void destroy() { 
		this.driver("delete", NOOP);
		this._("m_fncallback", null); 
		this._("m_iintervalms", -1);
	} // end destroy()
	
	public void exit(int iexitcode) {
		this.destroy();
		this._("m_iexitcode", iexitcode);
		this.unlock();
	}
	
	// helper
	public void driver(String strop, int ioptype) { 
		String strtype = this._string("m_strdrivertype");
		this._("m_ioptype", ioptype); 
		CFunction.get(strtype + "." + strop).call((Object)this); 
	} // end driver
	// constants
	
	// operation types
	static public final int NOOP=-1;
	static public final int START=0;
	static public final int STOP=1;
	static public final int NAME=2;
	static public final int PRIORITY=3;
	static public final int DAEMON=4;
	static public final int ID=5;
	static public final int ALIVE=6;
	static public final int INTERRUPT=7;
	static public final int JOIN=8;
	static public final int SLEEP=9;
	static public final int STATE=10;
} // end CThread
