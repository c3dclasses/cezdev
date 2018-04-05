//-----------------------------------------------------------------------------------------
// file: CLinkedList
// desc: defines a linked list object
//-----------------------------------------------------------------------------------------
package c3dclasses;
import java.util.*;

//-------------------------------------------
// name: CLinkedList
// desc: defines a linked list object 
//-------------------------------------------
public class CLinkedList {
	protected CLinkedListNode m_head;
	protected CLinkedListNode m_tail;
	protected int m_isize;
	
	public CLinkedList() { 
		this.m_head = null; 
		this.m_tail = null; 
		this.m_isize = 0; 
	} // end CLinkedList()
	public int size() { return this.m_isize; }
	public boolean empty() { return this.m_head == null; }
	public String toString() {
		String str = "CList [";
		CLinkedListNode curnode = this.m_head;
		int isize = this.m_isize;
		for(int i=0; curnode != null; i++) {
			str += curnode.toString();
			if(i+1 < isize)
				str += ",";
			curnode = curnode.next();
		} // end while()
		str += "]";
		return str;
	} // end toString()
	
	// getting / setting
	public CLinkedListNode head(CLinkedListNode head) { return this.m_head = head; }
	public CLinkedListNode head() { return this.m_head; }
	public CLinkedListNode begin() { return this.m_head; }	
	public CLinkedListNode tail(CLinkedListNode tail) { return this.m_tail = tail; }
	public CLinkedListNode tail() { return this.m_tail; }
	public CLinkedListNode end() { return this.m_tail; }
	public CLinkedListNode get(int index) {
		if(index < 0 || index > this.m_isize-1)
			return null;
		CLinkedListNode curnode = this.m_head;
		for(int i=0; curnode != null; curnode=curnode.next()) {
			if(i==index) 
				return curnode;
			i++;
		} // end for
		return null;	
	} // end get()
	
	// stack, queue methods
	public void push(Object element) { this.addHead(element); }
	public boolean pop() { return this.removeHead(); }
	public Object top() { return (!this.empty()) ? this.head().data() : null; }
	public void enqueue(Object element){ this.addTail(element); }
	public boolean dequeue(){ return this.removeHead(); }
	public Object peek(){ return this.top(); }
	
	/////////////////////
	// core methods
	/////////////////////
	
	// adding 
	public boolean addHead(Object data) {
		if(data == null)
			return false;
		CLinkedListNode node = new CLinkedListNode(data);
		if(node == null)
			return false;
		if(!this.empty()) {
			node.attach(null,this.m_head);
			this.m_head = node;
		} // end if
		else {
			this.m_head = node;
			this.m_tail = node;
		} // end else
		this.m_isize++;
		return true;
	} // end addHead()
	
	public boolean addTail(Object data) {
		if(data == null)
			return false;
		CLinkedListNode node = new CLinkedListNode(data);
		if(node == null)
			return false;
		if(!this.empty()) {
			node.attach(this.m_tail, null);
			this.m_tail = node;
		} // end if
		else {
		 	this.m_head = node;
			this.m_tail = node;
		} // end else
		this.m_isize++;
		return true;
	} // end addTail()
	
	public boolean addAt(int index, Object data) {
		if(data == null || index < 0 || index > this.size()-1)
			return false;
		if(index == 0)
			return this.addHead(data);
		if(index == this.size()-1)
			return this.addTail(data);
		CLinkedListNode nodeAtIndex = this.get(index);
		CLinkedListNode node = new CLinkedListNode(data);
		if(nodeAtIndex == null || node == null)
			return false;	
		node.attach(nodeAtIndex.prev(), nodeAtIndex);	// attach the nodes
		this.m_isize++;
		return true;
	} // end addAt()
	
	////////////////
	// removing
	public boolean removeHead() {
		CLinkedListNode curhead = this.m_head;
		if(curhead == null) {
			//_.alert("no curhead");
			return false;
		} // end if
		CLinkedListNode node = curhead.next();	
		if(node == null) {
			this.m_head = null;
			this.m_tail = null;
		} // end if
		else this.m_head = node;
		curhead.detach();
		//_.alert("removed curhead");
		this.m_isize--;
		return true;
	} // end removeHead()
	public boolean removeTail() {
		CLinkedListNode curtail = this.m_tail;
		if(curtail == null) {
			//_.alert("no curtail");
			return false;
		} // end if
		CLinkedListNode node = curtail.prev();
		if(node == null) {
			this.m_head = null;
			this.m_tail = null;
		} // end if
		else this.m_tail = node;
		curtail.detach();
		//_.alert("removed curtail");
		this.m_isize--;
		return true;
	} // end removeHead()
	public boolean removeAt(int index) {
		if(index < 0 || index > this.size()-1) // out of bounds
			return false;
		if(index == 0) { // head
			return this.removeHead();
		}
		if(index == this.size()-1) { // tail
			return this.removeTail();
		}
		CLinkedListNode nodeAtIndex = this.get(index); // inner node
		if(nodeAtIndex == null)
			return false;
		nodeAtIndex.detach();
	//	_.alert("remove at index");
		this.m_isize--;
		return true;
	} // end removeAt()	
} // end CGraphVertex
