//---------------------------------------------------------------------------------------------
// name: CJSONObject.java
// desc: defines a combobox object thats created from a var file
//---------------------------------------------------------------------------------------------
package c3dclassessdk.clib;
import java.io.*;
import java.util.*;
import java.net.*;
import java.text.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import c3dclassessdk.clib.*;
import c3dclassessdk.ccore.*;


//------------------------------------------------------
// name: CJSONObject
// desc: needed to create a combobox with a name/value
//------------------------------------------------------
public class CJSONObject {
	// members
	JSONObject m_jsonobject;
	
	// constructor
	public CJSONObject() {
		m_jsonobject = null;
	} // end CJSONObject()
	
	public CJSONObject(JSONObject jsonobject) {
		m_jsonobject = jsonobject;
	} // end CJSONObject()
	
	public boolean create(String strjsoncontents){
		JSONObject jsonobject = null;
		try {
			JSONParser parser = new JSONParser();	
			jsonobject = (JSONObject)parser.parse(strjsoncontents);
		} // end try
		catch(Exception ex) {
			System.out.println("Reason: " + ex.getMessage());
			return false;
		} // end catch()
		m_jsonobject = jsonobject;
		return true;	
	} // end create()
	 
	// creates the json object from a file
	public boolean createFromFile(String strfilename) {
		String strcontents = _.getFileContents(strfilename);
		return this.create(strcontents);
	} // end createFromFile()
	
	// returns the object for a given name
	public Object get(String strname) {
		try {
			Object object = m_jsonobject.get(strname);
			if(object instanceof JSONObject) {
				object = new CJSONObject((JSONObject)object);
			} // end if
			else if(object instanceof JSONArray) {
				object = (Object []) this.toArray((JSONArray)object);
			} // end else if
			return object;
		} // end try
		catch(Exception ex)  {
			//System.out.println("ERROR: CJSONObject.get(): Couldn't get value for: " + strname);
			return null;
		} // end catch()
	} // end get()
	
	public int _i(String strpathname) { return Integer.parseInt((String)this._str(strpathname)); } 
	public String _str(String strpathname) { Object obj = this.getFromPath(strpathname);  return (obj == null) ? "" : (String)obj; } 
	//public Object _(String strpathname) { this.getFromPath(strpathname); } 
	public Object [] _arr(String strpathname) { return (Object []) this.getFromPath(strpathname); } 
		
	// returns the object for a given name in a path
	public Object getFromPath(String strpathname) { 
		if(strpathname == null || strpathname == "")
			return null;
		int index=-1;
		if((index = strpathname.indexOf(".")) == -1)
			return this.get(strpathname);
		String strstartpathname = strpathname.substring(0, index).trim();
		String strrestpathname = strpathname.substring(index+1).trim();
		CJSONObject cjsonobject = (CJSONObject)this.get(strstartpathname);
		if(cjsonobject == null)
			return null;
		if(strrestpathname == null || strrestpathname == "")
			return cjsonobject;	
		return cjsonobject.getFromPath(strrestpathname);
	} // end getFromPath()
	
	// returns the property names
	public Object [] getPropertyNames() {
		if(m_jsonobject == null)
			return null;
		Set<Object> keys = m_jsonobject.keySet();
		return keys.toArray();
	} // end getPropertyNames()
	
	// returns an arraylist
	public static Object [] toArray(JSONArray jsonarray) {
		if(jsonarray == null)
			return null;
		Object[] objects = new Object[jsonarray.size()];
  		int i = 0;
  		for (Object element : jsonarray) {
    		objects[i++]=(Object)element;
		} // end for		
		return objects;		
	} // end toArrayList()
	
	/*
	// return an array of objects 
	public static Object [] toObjectArray(JSONArray jsonarray) {
		
	} // end toObjectArray()
	*/
} // end CJSONObject