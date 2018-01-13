//------------------------------------------------------
// name: CJSONArray.java
// desc: defines a json array object
//------------------------------------------------------
package c3dclasses;
import java.io.*;
import java.util.*;
import java.net.*;
import java.text.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//------------------------------------------------------
// name: CJSONArray
// desc: defines a json array object
//------------------------------------------------------
public class CJSONArray {
	protected JSONArray m_jsonarray;

	public CJSONArray() {
		m_jsonarray = null;
	} // end CJSONArray()

	public CJSONArray(JSONArray jsonarray) {
		m_jsonarray = jsonarray;
	} // end CJSONArray()
	
	public boolean create(String strjsoncontents){
		JSONArray jsonarray = null;
		try {
			JSONParser parser = new JSONParser();	
			jsonarray = (JSONArray)parser.parse(strjsoncontents);
		} // end try
		catch(Exception ex) {
			CLog.error(ex.toString());
			return false;
		} // end catch()
		m_jsonarray = jsonarray;
		return true;	
	} // end create()
	
	// creates the json object from a file
	public boolean createFromFile(String strfilename) {
		String strcontents = _.get_file_contents(strfilename);
		return this.create(strcontents);
	} // end createFromFile()
	
	public int length() { return this.m_jsonarray.size(); }
    
	public Object get(int i) {
		try {
			Object object = m_jsonarray.get(i);
			if(object instanceof JSONArray) {
				object = new CJSONArray((JSONArray)object);
			} // end if
			return object;
		} // end try
		catch(Exception ex)  {
			CLog.error(ex.toString());	
			return null;
		} // end catch()
	} // end get()
	
	public int _i(int i) { return Integer.parseInt((String)this._str(i)); } 
	public String _str(int i) { Object obj = this.get(i);  return (obj == null) ? "" : (String)obj; } 
	public Object [] _arr(int i) { 
		Object [] objects = (Object []) this.get(i);
		if(objects == null)
			return null;
		for(int j=0; j<objects.length; j++) {
			if(objects[j] instanceof JSONArray) {
				objects[j] = new CJSONArray((JSONArray)objects[j]);
			} // end if 
		} // end for
		return objects;
	} // end _arr()
} // end CJSONArray