//--------------------------------------------------------------------------
// file: CTimeoutConcurrentEvent
// desc: 
//--------------------------------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------
// name: CTimeoutConcurrentEvent
// desc: 
//---------------------------------------------------------------------
class CTimeoutConcurrentEvent extends CIntervalConcurrentEvent {
	public CTimeoutConcurrentEvent() { super(); }
	public boolean consumeEvent() { 
		boolean bconsumed = super.consumeEvent();
		if(bconsumed)
			this.destroy();
		return bconsumed;
	} // end consumeEvent()
	public static int setTimeout(CFunction fncallback, int itimeinms) {
		CTimeoutConcurrentEvent cevent = new CTimeoutConcurrentEvent();
		return (!cevent.create(itimeinms, fncallback)) ? -1 : cevent.getID();
	} // end setTimeout()
	public static void clearTimeout(int id) { CIntervalConcurrentEvent.clearInterval(id); }
} // end CTimeoutConcurrentEvent