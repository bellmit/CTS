package com.cognizant.tool.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;
import java.util.Set;

public class PropertyFileReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream is=null;
		try {
			
			//System.setOut(new PrintStream("test.txt"));
			System.out.println("PropertyFileReader...");
			is=new FileInputStream(new File("C:\\Users\\396662\\Desktop\\ErrorMessages.properties"));
			Properties prop =new Properties();
			prop.load(is);
			System.out.println(prop.stringPropertyNames());
			Set<String> propKey=prop.stringPropertyNames();
			
			for(String key:propKey)
			{
				System.out.println(key+" = "+prop.get(key));
			}
			System.out.println("Size of Property fize==>"+propKey.size());
			
			File f=new File("C:\\Users\\396662\\Desktop\\postexception.properties");
			postExceptionReader(f);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		
		
	}

	public static void postExceptionReader(File file) throws IOException
	{
		System.out.println("<===== postExceptionReader =====>");
		InputStream is=null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties prop =new Properties();
		prop.load(is);
		System.out.println(prop.stringPropertyNames());
		Set<String> propKey=prop.stringPropertyNames();
		
		for(String key:propKey)
		{
			System.out.println(key+" = "+prop.get(key));
		}
	}
}
