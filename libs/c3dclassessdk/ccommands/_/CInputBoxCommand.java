//---------------------------------------------------------------
// name: CInputBox.java
// desc: defines a dialog box to get input from the user
// usage: CInputBox <filename.json> 
//        json file has all of the properties for the input box
// example json: 
// {
//	"title":"value",
//	"message":"message",
//	"choices":["ham","cheese","bread"]
// }
//----------------------------------------------------------------

// includes
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import c3dclasses.*;

//------------------------------------------------------------------------
// name: CPromptBox
// desc: the main application of CEnvironmentVariables
//------------------------------------------------------------------------
public class CInputBoxCommand {				
	public static void main(String[] args) {
		if(args.length <= 1) 
			return;
		
		String strfilename = args[0];
		String strmessage = (args.length > 1) ? args[1] : "";
		String strtitle = (args.length > 2) ? args[2] : "";
		String strtype = (args.length > 3) ? args[3].toLowerCase() : "info";
		String strchoicesfilename = (args.length > 4) ? args[4] : null;
				
		// create the json object from a file		
		Object [] choices = null;
		if(strchoicesfilename != null && strchoicesfilename != "") {
			_.alert(strchoicesfilename);
			CJSONObject cjsonobject = _.to_json_object_from_file(strchoicesfilename, false);
			choices = (Object []) cjsonobject._arr("choices");
		} // end if
		
		
		//if(cjsonobject == null)
		//	return;
		
		// get the data from the file
		//String strtitle = (String) cjsonobject._str("title");
		//String strmessage = (String) cjsonobject._str("message");
		//String stricon = (String) cjsonobject._str("icon");
		//String strtype = (String) cjsonobject._str("type");
		//Object [] choices = (Object []) cjsonobject._arr("choices");
		//String strdefault = (String) cjsonobject._str("default");
		
		int itype = JOptionPane.INFORMATION_MESSAGE;
		if(strtype.equals("error")) itype = JOptionPane.ERROR_MESSAGE;
		else if(strtype.equals("warn")) itype = JOptionPane.WARNING_MESSAGE;
		else if(strtype.equals("plain")) itype = JOptionPane.PLAIN_MESSAGE;
				
		// prompt the user to enter their name
    	String stroutput = "";
		
		if(choices != null)
			stroutput = (String) JOptionPane.showInputDialog(null, strmessage, strtitle, itype, null, choices, choices[0]);
		else 
			stroutput = (String) JOptionPane.showInputDialog(null, strmessage, strtitle, itype);
		
		if(stroutput != null && stroutput.length() > 0)
			System.out.println(stroutput);
		return;
	} // end main()
} // end CInputBox