//-------------------------------------------------------------------------------------------------------
// file: CHook.java
// desc: provides a way to store php handler function that will be executed later by the ckernal object
//-------------------------------------------------------------------------------------------------------
package c3dclasses;

//-----------------------------------------------------------
// name: CHook
// desc: singleton object used to store handler functions
//-----------------------------------------------------------
public class CHook extends CFunction {
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
			CArray chash = (CArray) CHook.m_chashhook.get(strhookname);
			if(chash != null) 
				chash.remove(fnhandler);
		} // end else if
		return true;
	} // end remove()
	public static boolean fire(String strhookname) {	 
		CArray chook = null;
		if(CHook.m_chashhook == null || (chook = (CArray) CHook.m_chashhook.get(strhookname)) == null)
			return false;		
		chook.visit(new CHook()); 
		return true;	
	} // end fire()
	/*
	public static void _(Object obj) {
		CFunction.params(obj);
		CArray inparams = (CArray) obj; 
		String key = inpara._(0);
		String key = feilds._(0);
		String key = feilds._(0);
		if(isFunction(fnhandler)) fnhandler(key, params); 
	} // end doCallback()
	*/
 	// end ClassMethods
} // end CHook