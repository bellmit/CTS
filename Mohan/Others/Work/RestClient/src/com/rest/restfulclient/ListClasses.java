package com.rest.restfulclient;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

public class ListClasses {

	
	public static void main(String[] s) throws ClassNotFoundException, IOException {
		Set<Class<?>> cl = getClasses("com.rest");
		Iterator<Class<?>> i = cl.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}	
				
	}

	private static Set<Class<?>> getClasses(String string) {
		// TODO Auto-generated method stub
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());

		Reflections reflections = new Reflections(new ConfigurationBuilder()
		    .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
		    .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
		    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(string))));
		Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);	
		
		return classes;
	} 


//public static Class[] getClasses(String pckgname) throws ClassNotFoundException { 
//    ArrayList<Class<?>> classes=new ArrayList<Class<?>>(); 
//    // Get a File object for the package 
//    File directory=null; 
//    try { 
//      directory=new File(Thread.currentThread().getContextClassLoader().getResource('/'+pckgname.replace('.', '/')).getFile()); 
//    } catch(NullPointerException x) { 
//      throw new ClassNotFoundException(pckgname+" does not appear to be a valid package"); 
//    } 
//    if(directory.exists()) { 
//      // Get the list of the files contained in the package 
//      String[] files=directory.list(); 
//      for(int i=0; i<files.length; i++) { 
//        // we are only interested in .class files 
//        if(files[i].endsWith(".class")) { 
//          // removes the .class extension 
//          classes.add(Class.forName(pckgname+'.'+files[i].substring(0, files[i].length()-6))); 
//        } 
//      } 
//    } else { 
//      throw new ClassNotFoundException(pckgname+" does not appear to be a valid package"); 
//    } 
//    Class[] classesA=new Class[classes.size()]; 
//    classes.toArray(classesA); 
//    return classesA; 
//  }


}