//--------------------------------------------------------------------------
// file: CIntervalConcurrentEvent
// desc: 
//--------------------------------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------
// name: CIntervalConcurrentEvent
// desc: 
//---------------------------------------------------------------------
class CIntervalConcurrentEvent extends CConcurrentEvent {
	public CIntervalConcurrentEvent() { super(); }
	public boolean create(int imilliseconds, CFunction fncallback) {
		if(fncallback == null || imilliseconds < 1)
			return false;
		if(super.create() == false)
			return false;
		this._("m_imilliseconds", imilliseconds);
		this._("m_cfncallback", fncallback);
		this._("m_starttimeinterval", __.time());
		this._("m_starttime", __.time());
		return true;
	} // end create()
	public boolean consumeEvent() { 
		CFunction cfnhandler = this._cfunction("m_cfncallback");
		if(cfnhandler == null)
			return false;
		cfnhandler.call((CArray)null);
		return true;
	} // end consumeEvent()
	public boolean produceEvent() { 
		long curtime = __.time();
		long starttime = this._long("m_starttime");
		long starttimeinterval = this._long("m_starttimeinterval");
		long ims = this._long("m_imilliseconds");
		//__.println(curtime + " " + starttime + " " + ims + " " + starttimeinterval);
		if((curtime - starttimeinterval) < ims)
			return false;
		//__.println("produced the event");
		this._("m_starttimeinterval", __.time());
		return true;
	} // end productEvent()
	
	public static int setInterval(CFunction fncallback, int itimeinms) {
		CIntervalConcurrentEvent cevent = new CIntervalConcurrentEvent();
		return (!cevent.create(itimeinms, fncallback)) ? -1 : cevent.getID();
	} // end setInterval()
	
	public static void clearInterval(int id) {
		CConcurrentEvent cevent = (CConcurrentEvent) CConcurrentEvent.getCConcurrentEvent(id);
		if(cevent != null)
			cevent.destroy();
	} // end clearInterval()
} // end CIntervalConcurrentEvent