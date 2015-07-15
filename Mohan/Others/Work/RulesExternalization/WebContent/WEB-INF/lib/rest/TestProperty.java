package com.sample.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;
 
public class TestProperty { 
  public final static int LOAD_COUNT = 5000; 
  public TestProperty(){
	  
  }
  public static void main(String[] args) throws ClassNotFoundException {
	// TODO Auto-generated method stub
//		Properties prop = new Properties();
//		OutputStream output = null;
	 
//		try {
//	 
//			output = new FileOutputStream("config.properties");
//	 
//			// set the properties value
//			prop.setProperty("database", "localhost");
//			prop.setProperty("dbuser", "mkyong");
//			prop.setProperty("dbpassword", "password");
//	 
//			// save properties to project root folder
//			prop.store(output, null);
//	 
//		} catch (IOException io) {
//			io.printStackTrace();
//		} finally {
//			if (output != null) {
//				try {
//					output.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}}
	 
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
//private void writeFile() {
//	// TODO Auto-generated method stub
//	Properties prop = new Properties();
//	OutputStream output = null;
// 
//	try {
// 
//		output = new FileOutputStream("config.properties");
// 
//		// set the properties value
//		prop.setProperty("database", "localhost");
//		prop.setProperty("dbuser", "mkyong");
//		prop.setProperty("dbpassword", "password");
// 
//		// save properties to project root folder
//		prop.store(output, null);
// 
//	} catch (IOException io) {
//		io.printStackTrace();
//	} finally {
//		if (output != null) {
//			try {
//				output.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
// 
//	}
//}
//
//private void readFile() {
//	// TODO Auto-generated method stub
//	Properties prop = new Properties();
//	InputStream input = null;
// 
//	try {
// 
//		input = new FileInputStream("config.properties");
// 
//		// load a properties file
//		prop.load(input);
//		
//		String rule = prop.getProperty("rule_condition"); 
//		System.out.println("rule condtions"+rule);
//	
//		// get the property value and print it out
//		System.out.println(prop.getProperty("database"));
//		System.out.println(prop.getProperty("dbuser"));
//		System.out.println(prop.getProperty("dbpassword"));
// 
//	} catch (IOException ex) {
//		ex.printStackTrace();
//	} finally {
//		if (input != null) {
//			try {
//				input.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}
  
  
}
