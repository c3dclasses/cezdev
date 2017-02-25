//-----------------------------------------------------------------------------------------------------------------
// file: include.java
// desc: provides a class object to include and manipulate javascript files 
//----------------------------------------------------------------------------------------------------------------
package c3dclassessdk.clib;
import java.lang.annotation.*;
import java.util.*;
import c3dclassessdk.clib.*;
import c3dclassessdk.ccore.*;


//--------------------------------------------
// name: include
// desc: includes files
//--------------------------------------------
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // on class level
public @interface include{
	
	public enum Priority {
	   LOW, MEDIUM, HIGH
	}
 
	Priority priority() default Priority.MEDIUM;
 
	String[] tags() default "";
 
	String createdBy() default "Mkyong";
 
	String lastModified() default "03/01/2014";
	
} // end class include
