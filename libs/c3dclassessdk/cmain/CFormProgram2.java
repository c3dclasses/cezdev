//-------------------------------------------------------
// name: CFormProgram2.java
// desc: 
//-------------------------------------------------------
import javax.swing.JOptionPane;
import cglobal.*;
import c3dclasses.csystem.cui.*;
import c3dclasses.ccore.*;

public class CFormProgram2 {				
	public static void main(String[] args) {
		CForm2 cform = new CForm2();
		if(!cform.create("cformprogram", null))
			return;
		//CControls ccontrols = cform.getCControls();	
		cform.form("myform", "This is the form title", null);	
			cform.button("control5", "Control5", null);
			cform.select("control4", "HELLO3", 
				_.chash(
					_.cpair("HELLO5","WORLD5"), 
					_.cpair("HELLO1","WORLD1"),
					_.cpair("HELLO2","WORLD2"),
					_.cpair("HELLO3","WORLD3")
				), 
			null);
			//{"HELLO5":"WORLD5", "HELLO1":"WORLD1","HELLO2":"WORLD2","HELLO3":"WORLD3"}, null);
			//ccontrols.label("control1", "Control1: ", null);
			//ccontrols.text("control1", "This is my Text Control", null);			
		cform.endform();
		
		cform.retrieve("myform").setProp("grid","true");
		cform.retrieve("myform").setProp("visible","true");
		cform.retrieve("myform").setProp("pack","true");
		cform.retrieve("myform").setProp("close","true");
		cform.retrieve("myform control5").setProp("visible","true");
		
		cform.retrieve("myform").setProp("title","FRAME");
		cform.retrieve("myform control5").setProp("title","BUTTON");
		
		
		_.alert(cform.retrieve("myform control5").getProp("visible"));
		_.println(cform.toStringContents());
		
		//CHash chash = cform.retrieve("cformprogram myform");
		//_.println(cform.toStringContents(chash));
		
		
		
		/*
		ccontrols.hidden("cprogramtype","CForm2Program", null);
		ccontrols.label("control2", "Control2 with attributes: ", null);
		ccontrols.set("data-attr1", "value1");
		ccontrols.set("data-attr2", "value2");
		ccontrols.set("data-attr3", "value3");	
		ccontrols.text("control2", "This is my Text Control With Html Attributes", null);
		ccontrols.clear(); // clear the attributes
		ccontrols.label("control3", "Control3 radio buttons: ", null);
		ccontrols.radio("control3","red", null);
		ccontrols.radio("control3","green", null);
		ccontrols.set("checked", "checked");
		ccontrols.radio("control3","blue", null);
		ccontrols.clear(); // clear the attributes
		ccontrols.label("control4", "Control7 select control: ", null);
		ccontrols.select( "control4", "HELLO3", {"HELLO5":"WORLD5", "HELLO1":"WORLD1","HELLO2":"WORLD2","HELLO3":"WORLD3"}, null);
		ccontrols.button("control5", "Control5", null);
		ccontrols.submit("control6", "Control6", null);
		*/

	
		/*
		CForm2 cform2 = new CForm2();
		cform2.create("cformprogram-js", null);	
		CControls ccontrols2 = cform.getCControls();	
		ccontrols2.form("form", "myform", null);
		ccontrols2.endform();
		*/
		
		
	} // end main()
} // end CMessageBox
