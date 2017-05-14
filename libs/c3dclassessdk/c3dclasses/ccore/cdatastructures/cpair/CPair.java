//---------------------------------------------------------------------------------
// file: CPair
// desc: defines an object that stores a pair of objects
//---------------------------------------------------------------------------------
package c3dclasses.ccore;
import java.util.*;
import cglobal.*;

//-------------------------------------------------------------
// class CPair
// desc: defines an object that stores a pair of objects
//-------------------------------------------------------------
public class CPair {
	public CPair(Object first, Object second) {
		this.m_first = first;
		this.m_second = second;
	} // CPair
	public Object m_first=null;
	public Object m_second=null;	
} // end CPair
