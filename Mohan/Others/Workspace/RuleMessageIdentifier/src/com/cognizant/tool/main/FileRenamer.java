package com.cognizant.tool.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileRenamer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("D:\\Logs\\testProperty\\ErrorToRule.properties");
		Properties properties = new Properties();
		InputStream input;
		try {
			input = new FileInputStream(file);
			properties.load(input);
			System.out.println("Size==>"+properties.size());
			
			File rootDir=new File("C:\\Users\\396662\\Desktop\\PojoExtraction\\tpl");
			File[] files = rootDir.listFiles();
			for (int i = 0; i < files.length; i++) 
			{
				if (files[i].isFile())
				{
					System.out.println("File name==>"+files[i].getName());	
					String oldFName=files[i].getName();
					oldFName=oldFName.substring(0, oldFName.indexOf('.'));
					if(oldFName.contains("_"))
					{
						System.out.println("_==> present");
						oldFName=oldFName.substring(0, oldFName.indexOf('_'));
						System.out.println("oldFName==>"+oldFName);
					}
					String newFName=(String)properties.get(oldFName);
					System.out.println("newFname==>"+newFName);
					if(newFName!=null)
					{
						String fname=files[i].getAbsolutePath();
						fname=fname.replace(oldFName, newFName);
						File newFile=new File(fname);
						System.out.println("newFpath==>"+fname);
						files[i].renameTo(newFile);
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		

	}

}
