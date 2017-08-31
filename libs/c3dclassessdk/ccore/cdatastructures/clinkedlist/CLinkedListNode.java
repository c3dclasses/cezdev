//-----------------------------------------------------------------------------------------
// file: CLinkedListNode
// desc: defines a linked list node object 
//-----------------------------------------------------------------------------------------
package c3dclasses.ccore;
import java.util.*;
import cglobal.*;

//----------------------------------------------
// name: CLinkedListNode
// desc: defines a linked list node object 
//----------------------------------------------
public class CLinkedListNode {
	protected CLinkedListNode m_prev;
	protected CLinkedListNode m_next;
	protected Object m_data;
	public CLinkedListNode(Object data) {
		this.m_prev = null;
		this.m_next = null;
		this.m_data = data;
	} // end CLinkedListNode()
	public CLinkedListNode next() { return this.m_next; }
	public void next(CLinkedListNode next) { this.m_next = next; }
	public CLinkedListNode prev() { return this.m_prev; }
	public void prev(CLinkedListNode prev) { this.m_prev = prev; }
	public Object data() { return this.m_data; }
	public void data(Object data) { this.m_data = data; }
	public String toString(){ return this.m_data.toString(); }
	
	public void attach(CLinkedListNode prev, CLinkedListNode next) {
		if(prev != null) {
			prev.next(this);
			this.prev(prev);
		} // end if
		if(next != null) {
			this.next(next);
			next.prev(this);
		} // end if
	} // end attach()
	
	public void detach() {
		if(this.m_prev != null)
			this.m_prev.next(this.m_next);
		if(this.m_next != null)
			this.m_next.prev(this.m_prev);
		this.m_prev = null;
		this.m_next = null;
		return;
	} // end detach()
	
	/*
	public void putNext(CLinkedListNode node) {
		if(node == null)
			return;
		this.next(node);
		node.prev(this);
		return;
	} // end putNext()
	public void putPrev(CLinkedListNode node) {
		if(node == null)
			return;
		this.prev(node);
		node.next(this);
		return;
	} // end putPrev()
	*/
} // end CLinkedListNode