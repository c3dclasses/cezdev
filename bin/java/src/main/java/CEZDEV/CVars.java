//---------------------------------------------------------------------------------------------
// name: CVars.java
// desc: defines variable environment used inside and outside EZDEV
//---------------------------------------------------------------------------------------------

// imports
import java.io.*;
import java.util.*;
import java.net.*;
import java.text.*;
import java.awt.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.*;
import javax.swing.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//------------------------------------------------------------------------
// name: CVars
// desc: changes the ip where a given host or host points
//------------------------------------------------------------------------
public class CVars {
	static protected HashMap m_vars = null; 
	
	public CVars() {
	} // end CVars()
		
		/*
	public static void load() {
		CVars.reload(null);
	} // end load()
	
	public static void reload(String strvarsfile) {
		this.open(strvarsfile);
		try { 
			File file = new File(strvarsfile); 
			file.delete(); 
		} // end try
		catch(Exception ex) {
		} // end catch
		return;
	} // end reload()
	
	public static void open(String strvarsfile) {
		strvarsfile = (strvarsfile==null || strvarsfile=="") ? (String)CVars.get("ezdev.vars") : strvarsfile;
		ArrayList<String> strlines = _.getLinesFromFile(strvarsfile);
		if(strlines == null)
			return;
		for(int i=0; i<strlines.size(); i++) {
			String [] nv = strlines.get(i).split("=");
			nv[1] = nv[1].replace("\\","\\\\").trim(); 
			CVars.set(nv[0],nv[1]);
			nv=null;
		} // end for
		return;
	} // end open()

	public static void set(String strname, String strvalue) {
		if(m_vars == null)
			m_vars = new HashMap();
		//if(strvalue != "" && strvalue != null)
		System.out.println("SUCCESS: CVars.set(): " + strname + "," + strvalue);
		m_vars.put(strname, strvalue);
		return;
	} // end set()

	public static String get(String strname) { 
		try {
			Map<String, String> env = System.getenv();	// read from the env variables too first
			String strvalue = env.get(strname);
			if((strvalue == null || strvalue == ""))
				strvalue = (String)m_vars.get(strname);
			return strvalue;
		} // end try
		catch(Exception ex) {
			System.out.println("ERROR: CVars.get(): Couldn't get value for: " + strname);
			return null;
		} // end catch()
	} // end get()
	
	public static void remove(String strname) {
		if(strname == "" || strname == null || m_vars == null)
			return;
		Object [] strvars = m_vars.keySet().toArray();  
		for(int i=0; i<strvars.length; i++) {
			String strvar = (String) strvars[i];
			if(strvar.equals(strname))
				m_vars.remove(strvar);
		} // end for
		return;
	} // end remove()

	public static void removeLike(String strname) {
		if(strname == "" || strname == null || m_vars == null)
			return;
		Object [] strvars = m_vars.keySet().toArray();  
		for(int i=0; i<strvars.length; i++) {
			String strvar = (String) strvars[i];
			if(strvar.indexOf(strname) > -1)
				m_vars.remove(strvar);
		} // end for
		return;
	} // end removeLike()		
	
	// updates the configuration variables to be used by templates and commands
	public static boolean update() {
		if(m_vars == null)
			return false;
		String strmetapath = (String)CVars.get("ezdev.meta.path");
		String strfilenamebat = strmetapath + "/updateVARS.bat";	
		String strfilenametxt = strmetapath + "/updateVARS.txt";	
		Object [] strvars = m_vars.keySet().toArray();  
		if(strvars == null)
			return false;
		String eol = System.getProperty("line.separator");  
		String strcontents = "";
		String strvarname = "";
		String strvarvalue = "";
		String strconffilename = "";
		String strcontents2 = "";
		System.out.println("SUCCESS: CVars.update() - Updated Configuration Variables: ");
		for(int i=0; i<strvars.length; i++) {
			strvarname = (String)strvars[i];
			strvarvalue = (String)m_vars.get(strvarname);  
			if(strvarvalue == null || strvarvalue == "")
				continue;
			System.out.println("\t" + strvarname + ": " + strvarvalue);
			strvarvalue = strvarvalue.replace("\\\\","\\").trim(); 
			strcontents += "SET " + strvarname + '=' + strvarvalue + eol;
			strcontents2 += "" + strvarname + '=' + strvarvalue + eol;			 
		} // end for
		System.out.println("\n");
		_.setFileContents(strfilenamebat, strcontents);	
		_.setFileContents(strfilenametxt, strcontents2);
		return true;		
	} // end updateVariables()
	
	public static boolean updateTemplate(String strsrcfilename, String strdstfilename) {	
		if(strsrcfilename == "" || strdstfilename == "" || strsrcfilename == null || strdstfilename == null || m_vars == null) {
			System.out.println("ERROR: CVars.updateTemplate(): no src, dst filename or configuration variables");
			return false;
		} // end if
		
		Object [] strvars = m_vars.keySet().toArray();  
		String strcontents = _.getFileContents(strsrcfilename);
		if(strcontents == null || strcontents == "") {
			System.out.println("ERROR: CVars.updateTemplate(): " + strsrcfilename + " contains no contents.");
			return false;
		} // end if
		
		String strvarname = "";
		String strvarvalue = "";
		for(int i=0; i<strvars.length; i++) {
			strvarname = (String)strvars[i];
			strvarvalue = (String)m_vars.get(strvarname);  
			strcontents = strcontents.replace("%"+strvarname+"%", strvarvalue);
			strcontents = strcontents.replace("[["+strvarname+"]]", strvarvalue);
		} // end for
		
		System.out.println("SUCCESS: CVars.updateTemplate():\n\t" + strsrcfilename + " -> " + strdstfilename);
		_.setFileContents(strdstfilename, strcontents);			
		
		return true;
	} // end updateTemplate()
	*/
} // end CVars