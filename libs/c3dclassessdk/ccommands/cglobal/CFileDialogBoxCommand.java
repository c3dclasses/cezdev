//------------------------------------------------------------------
// name: CFileDialogBox.java
// desc: outputs the director name of the file the user selects
// usage: CFileDialogBox <title> <file/dir>
//------------------------------------------------------------------

// includes
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import c3dclasses.*;

//---------------------------------------------------------
// name: CFileDialogBox
// desc: CFileDialogBox application
//---------------------------------------------------------
public class CFileDialogBoxCommand {				
	public static void main(String[] args) {
		String strtitle = (args.length > 1) ? args[1] : "Open";		
		String strtype = (args.length > 2) ? args[2] : "file";
		JFileChooser fileChooser = new JFileChooser();
		// select a directory instead of file
		if(strtype.equals("dir"))
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle(strtitle);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
			// print the out to stdout for the script to see
          	System.out.println(fileChooser.getSelectedFile().getPath());       
	    } // end if
		return;
	} // end main()
} // end CFileDialogBox