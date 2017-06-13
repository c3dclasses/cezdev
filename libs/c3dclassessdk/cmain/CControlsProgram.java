//-------------------------------------------------------
// name: CControlsProgram.java
// desc: 
//-------------------------------------------------------
import javax.swing.JOptionPane;
import cglobal.*;
import c3dclasses.csystem.cui.*;
import c3dclasses.ccore.*;

public class CControlsProgram {				
	public static void main(String[] args) {
		CControls ccontrols = new CControls();
		//if(!ccontrols.create("ccontrolsprogram", null))
		//return;
		//CControls ccontrols = ccontrols.getCControls();	
		ccontrols.form("myform", "This is the form title", null);	
			ccontrols.menubar("menubar", "This is my menubar", null);
				ccontrols.menu("menu1", "Menu1", null);
					ccontrols.menuitem("item1", "item1", null);
					ccontrols.menuitem("item2", "item2", null);
					ccontrols.menu("menu2", "Menu1", null);
						ccontrols.menuitem("item1", "item1", null);
						ccontrols.menuitem("item2", "item2", null);
						ccontrols.menuitem("item3", "item3", null);
					ccontrols.endmenu();
					ccontrols.menuitem("item3", "item3", null);
				ccontrols.endmenu();
				ccontrols.menu("menu3", "Menu1", null);
					ccontrols.menuitem("item1", "item1", null);
					ccontrols.menuitem("item2", "item2", null);
					ccontrols.menuitem("item3", "item3", null);
				ccontrols.endmenu();
			ccontrols.endmenubar();
			ccontrols.button("control5", "Control5", null);
			ccontrols.select("control4", "HELLO3", 
				_.chash(
					_.nv("HELLO5","WORLD5"), 
					_.nv("HELLO1","WORLD1"),
					_.nv("HELLO2","WORLD2"),
					_.nv("HELLO3","WORLD3")
				), 
			null);
			ccontrols.submit("submit", "SUBMIT", null);			
			//ccontrols.label("control1", "Control1: ", null);
			ccontrols.text("text-control", "This is my Text Control", null);			
			ccontrols.label("label-control", "This is my Label", null);			
		ccontrols.endform();
		
		ccontrols.sysmenubar("menubar", "C://Users//developer//Desktop//icon.png", null);
			ccontrols.sysmenu("menu1", "Menu1", null);
				ccontrols.sysmenuitem("item1", "item1", null);
				ccontrols.syscheckboxmenuitem("checkboxitem", "checkboxitem", null);
				//ccontrols.sysmenuseperator();
				ccontrols.sysmenuitem("item2", "item2", null);
			ccontrols.endmenu();
		ccontrols.endsysmenubar();

		ccontrols.retrieve("myform").setProp("grid","true");
		ccontrols.retrieve("myform").setProp("visible","true");
		ccontrols.retrieve("myform").setProp("pack","true");
		ccontrols.retrieve("myform").setProp("close","true");
		ccontrols.retrieve("myform control5").setProp("visible","true");
		ccontrols.retrieve("myform control5").setProp("action", new CFunction() { public Object _(Object obj) {
			return null;
		}}); // end action
		
		ccontrols.retrieve("myform control5").setProp("action", new CFunction() { public Object _(Object obj) {
			_.execCommand("C:/Users/developer/Desktop/test.bat");
			return null;
		}}); // end action
		
		ccontrols.retrieve("myform menubar menu1 menu2 item1").setProp("action", new CFunction() { public Object _(Object obj) {
			_.execCommand("bash alert.sh mannnnnnnnnnnnnnnnnnn");
			return null;
		}}); // end action
		
		ccontrols.retrieve("myform").setProp("title","FRAME");
		ccontrols.retrieve("myform control5").setProp("title","BUTTON");
		
		_.alert(ccontrols.retrieve("myform control5").getProp("visible"));
		_.println(ccontrols.toStringContents());
		
		//CHash chash = ccontrols.retrieve("ccontrolsprogram myform");
		//_.println(ccontrols.toStringContents(chash));
		/*
		ccontrols.hidden("cprogramtype","CControlsProgram", null);
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
		CControls ccontrols2 = new CControls();
		ccontrols2.create("ccontrolsprogram-js", null);	
		CControls ccontrols2 = ccontrols.getCControls();	
		ccontrols2.form("form", "myform", null);
		ccontrols2.endform();
		*/
	} // end main()
} // end CMessageBox