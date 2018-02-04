//------------------------------------------------------------------------------------------------
// name: CDriver
// desc: 
//------------------------------------------------------------------------------------------------
package c3dclasses;

//-----------------------------------------------------------------------------
// name: CDriver
// desc: 
//-----------------------------------------------------------------------------
public class CDriver {
	protected static CHash m_cdrivers = null; // stores all of the drivers
	public static boolean include(String strtype) {
		if(CDriver.m_cdrivers == null)
			CDriver.m_cdrivers = _.chash();
		if(CDriver.m_cdrivers._(strtype) != null) // check if it exist
			return true;
		Object cdriver = _._new(strtype);
		if(cdriver == null)		// check if new object is a driver object			
			return false;
		CDriver.m_cdrivers._(strtype, cdriver);
		return true;
	} // end include()
} // end CDriver