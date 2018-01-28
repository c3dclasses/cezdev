//---------------------------------------------------------------------------------------------
// name: CJSON
// desc: converts a json object to chash / carray and vice versa
//---------------------------------------------------------------------------------------------
package c3dclasses;
import java.io.*;
import java.util.*;
import java.net.*;
import java.text.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//-----------------------------------------------------------------
// name: CJSON
// desc: converts a json object to chash / carray and vice versa
//-----------------------------------------------------------------
public class CJSON {	
	public static String encode(CHash chash, boolean bpack) { 
		return CJSON.toString(chash, bpack); 
	} // end encode()
	
	public static String encode(CArray carray, boolean bpack) { 
		return CJSON.toString(carray, bpack); 
	} // end encode()
		
	public static CHash decode(String strjson) { 
		return CJSON.toCHash(strjson); 
	} // end decode()
	
	public static CHash toCHash(String strjson) {
		try {
			JSONParser jsonparser = new JSONParser();	
			JSONObject jsonobject = (JSONObject)jsonparser.parse(strjson);
			return CJSON.toCHash(jsonobject);
		} // end try
		catch(Exception ex) { return null; }
	} // end toCHash()
	
	public static CHash toCHash(JSONObject jsonobject) {
		if(jsonobject == null)
			return null;		
		CHash chash = _.chash();
		Object [] keys = jsonobject.keySet().toArray();
		for(Object key : keys) {
			Object value = CJSON.get(jsonobject, (String)key);		
			if(value instanceof JSONObject)
				chash._(key, CJSON.toCHash((JSONObject)value));
			else if(value instanceof JSONArray)
				chash._(key, CJSON.toCArray((JSONArray)value));
			else chash._(key, value);		
		} // end for
		return chash;
	} // end toCHash()
	
	public static CArray toCArray(JSONArray jsonarray) {
		if(jsonarray == null)
			return null;
		CArray carray = _.carray();
		for (Object value : jsonarray) {
    		if(value instanceof JSONObject)
				carray.push(CJSON.toCHash((JSONObject)value));
			else if(value instanceof JSONArray)
				carray.push(CJSON.toCArray((JSONArray)value));
			else carray.push(value);	
		} // end for		
		return carray;		
	} // end toCArray()	
	
	public static Object get(JSONObject jsonobject, String strname) { 
		try { 
			return jsonobject.get(strname); 
		} // end try 
		catch(Exception ex) { 
			return null;
		} // end catch
	} // end get()
	
	public static String toString(CHash chash, boolean bpack) {
		return CJSON.toString(chash, bpack, "");
	} // end toString()
	
	public static String toString(CArray carray, boolean bpack) {
		return CJSON.toString(carray, bpack, "");
	} // end toString()
	
	public static String toString(CHash chash, boolean bpack, String ptab) {
		CArray keys = chash.keys();
		int len = keys.length();
		String comma = ",";
		String ctab = (bpack == false) ? ptab + "\t" : ""; 
		String nl = (bpack == false) ? "\n" : ""; 
		String str = "{" + nl;
		for(int i=0; i<len; i++) {
			String key = (String) keys._(i);
			Object value = chash._(key);
			str += ctab;
			if(chash == value) 
				str += CJSON.keyvalue(key, _.hash_code(value));	// make sure object don't point to itself - output address
			else if(value instanceof CArray) 
				str += CJSON.keyvalue(key, CJSON.toString((CArray)value, bpack, ctab));		
			else if(value instanceof CHash)
				str += CJSON.keyvalue(key, CJSON.toString((CHash)value, bpack, ctab));		
			else if(value instanceof String)
			 	str += CJSON.keyvalue(key, "\"" + CJSON.escape((String)value) + "\"");
			else str += CJSON.keyvalue(key, value);
			if(i+1 != len) {
				str += comma;
				str += nl;
			} // end if
			else str += nl;
		} // end for
		str += ptab + "}";
		return str; 
	} // end toString()
		
	public static String toString(CArray carray, boolean bpack, String ptab) {
		if(carray == null)
			return "";	
		String comma = ",";
		String ctab = (bpack == false) ? ptab + "\t" : ""; 
		String nl = (bpack == false) ? "\n" : ""; 
		String str = "[" + nl;
		for(int i=0; i<carray.length(); i++) {
			Object value = carray._(i);
			str += ctab;
			if(value instanceof CHash)
				str += CJSON.toString((CHash) value, bpack, ctab);
			else if(value instanceof CArray)
				str += CJSON.toString((CArray) value, bpack, ctab);
			else if(value instanceof String) 
				str += "\"" + (String) CJSON.escape((String)value) + "\"";		
			else str += value;		 
			if(i+1 != carray.length()) {
				str += comma;
				str += nl;
			} // end if
			else str += nl;
		} // end for
		str += ptab + "]";
		return str; 
	} // end toString()
	
	public static String keyvalue(String key, Object value) { 
		return "\"" + key + "\"" + ":" + value;
	} // end keyvalue()
	
	public static String escape(String str) { 
		return str.replace("\n", "\\n").replace("\0", "\\0").replace("\t", "\\t"); 
	} // end escape()

} // end CJSON