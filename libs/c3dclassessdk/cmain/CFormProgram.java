//-------------------------------------------------------
// name: CFormProgram.java
// desc: 
//-------------------------------------------------------
import javax.swing.JOptionPane;

import cglobal.*;
import c3dclasses.csystem.cui.*;


public class CFormProgram {				
	// main entry point of the application
	public static void main(String[] args) {
		// creating the cform
		_.println("CForm");
		CForm cform = new CForm();
		cform.create("cformprogram-js", null);	
		_.println(cform.getID());
		_.println();
		
		// using coptions
		_.println("COptions");
		COptions coptions = cform.getCOptions();
		_.println("control1:" + coptions.getOption("control1"));
		_.println("control2:" + coptions.getOption("control2"));
		_.println("control3:" + coptions.getOption("control3"));
		_.println("control4:" + coptions.getOption("control4"));
		_.println();
		
		// using ccontrols
		_.println("CControls");
		CControls ccontrols = cform.getCControls();	
		_.echo(ccontrols.form("form", "myform", null));	
		_.echo(ccontrols.label("control1", "Control1: ", null));
		_.echo(ccontrols.text("control1", "This is my Text Control", null));
		_.println();
		
		String id = cform.getID();
		cform.setID("");
		_.echo(ccontrols.hidden("cprogramtype","CFormProgram", null));
		cform.setID(id);
		_.echo(ccontrols.label("control2", "Control2 with attributes: ", null));
		ccontrols.set("data-attr1", "value1");
		ccontrols.set("data-attr2", "value2");
		ccontrols.set("data-attr3", "value3");	
		_.echo(ccontrols.text("control2", "This is my Text Control With Html Attributes", null));
		ccontrols.clear(); // clear the attributes
		_.println();
		_.echo(ccontrols.label("control3", "Control3 radio buttons: ", null));
		_.echo(ccontrols.radio("control3","red", null));
		_.echo(ccontrols.radio("control3","green", null));
		ccontrols.set("checked", "checked");
		_.echo(ccontrols.radio("control3","blue", null));
		ccontrols.clear(); // clear the attributes
		_.println();
		_.echo(ccontrols.label("control4", "Control7 select control: ", null));
		//_.echo(ccontrols.select( "control4", "HELLO3", {"HELLO5":"WORLD5", "HELLO1":"WORLD1","HELLO2":"WORLD2","HELLO3":"WORLD3"}, null));
		//_.println();
		_.echo(ccontrols.button("control5", "Control5", null));		
		_.println();
		_.echo(ccontrols.submit("control6", "Control6", null));
		_.echo(ccontrols.endform());
		_.println();
	} // end main()
} // end CMessageBox
