package com.sample.rest;

import java.io.File;
import java.util.ArrayList;

public class ListClasses {

	public static void main(String s[]) throws ClassNotFoundException {
		Class<?> cl[] = getClasses("java.lang");
		for (int i =0; i<=cl.length; i++) {
			System.out.println("classes::"+cl[i].getName());
		}
	} 


public static Class[] getClasses(String pckgname) throws ClassNotFoundException { 
    ArrayList<Class<?>> classes=new ArrayList<Class<?>>(); 
    // Get a File object for the package 
    File directory=null; 
    try { 
      directory=new File(Thread.currentThread().getContextClassLoader().getResource('/'+pckgname.replace('.', '/')).getFile()); 
    } catch(NullPointerException x) { 
      throw new ClassNotFoundException(pckgname+" does not appear to be a valid package"); 
    } 
    if(directory.exists()) { 
      // Get the list of the files contained in the package 
      String[] files=directory.list(); 
      for(int i=0; i<files.length; i++) { 
        // we are only interested in .class files 
        if(files[i].endsWith(".class")) { 
          // removes the .class extension 
          classes.add(Class.forName(pckgname+'.'+files[i].substring(0, files[i].length()-6))); 
        } 
      } 
    } else { 
      throw new ClassNotFoundException(pckgname+" does not appear to be a valid package"); 
    } 
    Class[] classesA=new Class[classes.size()]; 
    classes.toArray(classesA); 
    return classesA; 
  }
}