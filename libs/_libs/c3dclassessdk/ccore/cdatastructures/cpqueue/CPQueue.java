//---------------------------------------------------------------------------------
// file: CPQueue
// desc: defines a priority queue object
//---------------------------------------------------------------------------------
package c3dclasses;
import java.util.*;

//-------------------------------------------
// class CPQueue
// desc: defines a priority queue object
//-------------------------------------------
public class CPQueue {
	protected CHeap m_cheap;
	public CPQueue() { m_cheap = new CHeap();}
	public int size(){ return m_cheap.size(); }
	public void add(Object o){ m_cheap.push(o); }
	public Object remove(){ return m_cheap.pop(); }
	public Object peek(){ return m_cheap.top(); }
	//public void contains(Object o){ m_cheap.contains(o);}
	public CHeap getCHeap() { return m_cheap; }
	public void clear(){ this.m_cheap.clear(); }
	public String toString() { return m_cheap.getCArray().toString(); }
} // end CPQueue
