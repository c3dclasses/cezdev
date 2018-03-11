//--------------------------------------------------------------------------
// file: CConcurrentEvent
// desc: implement a concurrent event model for synchronous langauges
//--------------------------------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------
// name: CConcurrentEvent
// desc: defines a synchronus event model much like javascripts
//---------------------------------------------------------------------
class CConcurrentEvent extends CObject {
	public CConcurrentEvent() { this._("m_id",-1); }
	public boolean create() { return CConcurrentEvent.addCConcurentEvent(this); }
	public boolean destroy() { return CConcurrentEvent.removeCConcurrentEvent(this); }
	public int getID() { return this._int("m_id"); }
	// produces and event and returns true if so otherwise false
	// consumes an event and returns true otherwise false
	public boolean produceEvent() { return false; }
	public boolean consumeEvent() {	return true; }
		
	// static members and methods
	protected static CArray m_cconcurrenteventsqueue = new CArray();
	protected static CHash m_cconcurrentevents = new CHash();
	protected static int m_icounter = 0;
	static protected boolean addCConcurentEvent(CConcurrentEvent cconcurrentevent){
		if(cconcurrentevent == null)
			return false;
		int id = CConcurrentEvent.m_icounter;
		cconcurrentevent._("m_id", id);
		CConcurrentEvent.m_cconcurrentevents._(""+id, cconcurrentevent);
		CConcurrentEvent.m_icounter++;		
		return true;
	} // end addCConcurrentEvent()
	
	static protected CConcurrentEvent getCConcurrentEvent(int id) {
		return (CConcurrentEvent) CConcurrentEvent.m_cconcurrentevents._("" + id);
	} // end getCConcurrentEvent()
	
	static protected boolean removeCConcurrentEvent(CConcurrentEvent cconcurrentevent) {
		if(cconcurrentevent == null)
			return false;
		int id = cconcurrentevent._int("m_id");
		CConcurrentEvent.m_cconcurrentevents.remove("" + id);		
		CConcurrentEvent.m_cconcurrenteventsqueue.remove(id);
		
		
		cconcurrentevent = null;
		//cconcurrentevent._("m_id", -1);
		
		
		return true;
	} // removeCConcurrentEvent()
	
	// produces an event an stores it on the Queue
	static protected boolean produceEventToQueue() {
		CHash cevents = CConcurrentEvent.m_cconcurrentevents;
		if(cevents.size() <= 0)
			return false;
		CArray ids = cevents.keys();
		for(int i=0; i<ids.length(); i++) {
			CConcurrentEvent cconcurrentevent = (CConcurrentEvent) cevents._(ids._string(i));
			if(cconcurrentevent.produceEvent() == true){
				if(CConcurrentEvent.m_cconcurrenteventsqueue == null)
					CConcurrentEvent.m_cconcurrenteventsqueue =_.carray();
				CConcurrentEvent.m_cconcurrenteventsqueue.push(cconcurrentevent);
			} // end if()		
		} // end for()
		return true;
	} // end produceEventToQueue()
	
	// consumes the event from the Queue and handles it
	static protected boolean consumeEventFromQueue(int inumtoconsume) {
		CArray ceventqueue = CConcurrentEvent.m_cconcurrenteventsqueue;
		if(ceventqueue == null || ceventqueue.length() < 1)
			return false;
		int iconsumedcount = 0;
		while(ceventqueue.length() > 0) {
			CConcurrentEvent cconcurrentevent = (CConcurrentEvent) ceventqueue.shift();
			if(cconcurrentevent != null) {
				cconcurrentevent.consumeEvent();
				iconsumedcount++;
			} // end if
			if(inumtoconsume>1 && iconsumedcount==inumtoconsume)
				break;
		} // end while
		return true;
	} // end consumeEventFromQueue()
	
	static public boolean doEventLoop(int inumtoconsume) {
		//while(CConcurrentEvent.m_cconcurrenteventsqueue.length() > 0) {
		while(CConcurrentEvent.m_cconcurrentevents.size() > 0) {
			//_.println("size h:" + CConcurrentEvent.m_cconcurrentevents.size());
			//_.println("size a:" + CConcurrentEvent.m_cconcurrenteventsqueue.length());
			CConcurrentEvent.produceEventToQueue();
			CConcurrentEvent.consumeEventFromQueue(inumtoconsume);
		} // end while()
		return true;
	} // end doEventLoop()
} // end CConcurrentEvent