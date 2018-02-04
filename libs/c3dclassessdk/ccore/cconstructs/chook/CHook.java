//-------------------------------------------------------------------------------------------------------
// file: CHook.java
// desc: provides a way to store php handler function that will be executed later by the ckernal object
//-------------------------------------------------------------------------------------------------------
package c3dclasses;

//-----------------------------------------------------------
// name: CHook
// desc: singleton object used to store handler functions
//-----------------------------------------------------------
public class CHook {
	public static CHash m_chashhook = null;
	public static boolean add(String strhookname, CFunction fnhandler) {	
		if(strhookname == "" || strhookname == null || fnhandler == null)
			return false;
		if(CHook.m_chashhook == null)
			CHook.m_chashhook = _.chash();		
		if(CHook.m_chashhook.containsKey(strhookname) == false)
			CHook.m_chashhook.set(strhookname, _.carray());
		CArray chook = (CArray) CHook.m_chashhook.get(strhookname);
		if(chook != null) 
			chook.push(fnhandler);
		return true;
	} // end add()
	
	public static boolean remove(String strhookname, CFunction fnhandler) {
		if(strhookname == "" || strhookname == null || CHook.m_chashhook == null)
			return false;
		if(fnhandler == null)
			CHook.m_chashhook.remove(strhookname);
		else if(CHook.m_chashhook.containsKey(strhookname) == true) {
			CArray carray = (CArray) CHook.m_chashhook.get(strhookname);
			if(carray != null) 
				carray.remove(fnhandler);
		} // end else if
		return true;
	} // end remove()
	
	public static boolean fire(String strhookname) {	 
		if(CHook.m_chashhook == null)
			return false;
		CArray chook = (CArray) CHook.m_chashhook.get(strhookname);
		if(chook == null)
			return false;
		for(int i=0; i<chook.length(); i++)
			chook._cfunction(i).call((CArray)null);
		return true;	
	} // end fire()
} // end CHook