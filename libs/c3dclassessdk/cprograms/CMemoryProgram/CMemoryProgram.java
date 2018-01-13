//-------------------------------------------------------
// name: CMemoryProgram.java
// desc: defines the a program that uses CMemory object
//-------------------------------------------------------
import javax.swing.JOptionPane;
import c3dclasses.*;

public class CMemoryProgram {				
	public static void main(String[] args) {
		CMemory.include(
			"cjsonmemory", 		// id
			"C:/Users/developer/Desktop/cezdev2/libs/c3dclassessdk/cmain/CMemoryProgram/cjsonmemory.json", // path
			"c3dclasses.csystem.cdevice.CJSONMemoryDriver", // type
		null); // end CMemory.include()
		CMemory cmemory = (CMemory) CMemory.use("cjsonmemory");
		CReturn creturn = null;
		
		String strcommand = "";
		String strname = "";
		String strvalue = "";
		
		_.println();
		_.println("cmemory.toString(): ");			
		_.println(cmemory);
		_.println();
		
		while(true) {
			_.print("Enter Command to proceed [create/retrieve/update/delete/quit]: ");
			strcommand = _.readln();	
			_.println();
			creturn = null;
			if(strcommand.equals("quit") == true)
				break;		
			if(strcommand.equals("create") == false && 
				strcommand.equals("retrieve") == false &&
				strcommand.equals("update") == false &&
				strcommand.equals("delete") == false)
				continue;		
			_.print("Enter memory location name to " + strcommand + ": ");
			strname = _.readln();	
			_.println();
			if(strcommand.equals("create") == true || strcommand.equals("update") == true) {
				_.print("Enter memory location value to " + strcommand + ": ");
				strvalue = _.readln();
				_.println();
				if(strcommand.equals("create") == true) 
					creturn = cmemory.create(strname, strvalue, "string", null);	
				if(strcommand.equals("update") == true)
					creturn = cmemory.update(strname, strvalue, "string", null);	
			} // end if
			else if(strcommand.equals("retrieve") == true) 
				creturn = cmemory.retrieve(strname);	
			else if(strcommand.equals("delete") == true)
				creturn = cmemory.delete(strname);	
				
			if(creturn != null) {
				_.println("CReturn: ");
				_.println(creturn);
			} // end if
			
			_.println();
			_.println("CMemory: ");			
			_.println(cmemory);
			_.println();
		} // end while()
		//_.println(cmemory);
	} // end main()
} // end CMemoryProgram