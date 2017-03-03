//-------------------------------------------------------
// name: CEZDEV.java
// desc: defines the ezdev application 
//-------------------------------------------------------
import javax.swing.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import c3dclassessdk.clib.*;

//------------------------------------------------------------------------
// name: CEZDEV
// desc: the main application of CEZDEV
//------------------------------------------------------------------------
public class CEZDEV extends JFrame {				
	// main entry point of the application
	public static void main(String[] args) {
		//this.setProperties();
		//_.alert(_.getHomePath());
	} // end main()
	
	// sets the look and feel of the frame
	private void setProperties() {
		// get the json properties
		//CJSONObject props = _.toJSONObjectFromFile(, false);
		//if(!props)
		//	return;
		/*
		// set the look and feel	
		try { UIManager.setLookAndFeel(props._str("lookandfeel")); } 
		catch(Exception ex) {} 
		
		// set the content pane
		Container cp = this.getContentPane();
		cp.setLayout(new GridLayout(0, 1));
		cp.setPreferredSize(new Dimension(props._int("width"), props._int("height")));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack()
		this.setVisible(true);
		*/
		
		_.alert(_.getHomePath());
		
	} // end setProperties ()
} // end CEZDEV
