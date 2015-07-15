package com.cognizant.tool.main;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TestFileNameReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter 1.FileCount 2.Constants count reader");
		Scanner sc=new Scanner(System.in);
		int exec=sc.nextInt();
		switch(exec)
		{
		case 1:
			File fname=new File("D:\\RIF\\src\\main\\java\\com\\xerox\\enterprise\\ghs\\mmis\\rif\\rules\\automation");
			File[] fileArray=fname.listFiles();
			Set<String> ruleFname=new HashSet<>();
			getRuleFileList(fileArray,ruleFname);
			System.out.println("Rule identified==>"+ruleFname.size());
			System.out.println("Rule list==>"+ruleFname);
			break;
		case 2:
			fname=new File("D:\\ClearCase_Source\\396662_CTS_MNT_FIX_Dev_2");
			Set<String> messageFname=new HashSet<>();
			getMessageFileNames(fname,messageFname);
			System.out.println("Message constant identified==>"+messageFname.size());
			System.out.println("Message list==>"+messageFname);
			break;
		}
		
		
	}
	
	public static void getMessageFileNames(File rootDir, Set<String> messageFiles) {
		File[] files = rootDir.listFiles();
		for (File file:files) {
			if (file.isDirectory())
				getMessageFileNames(file, messageFiles);
			else {
				//Removed helper files.
				//(files[i].getName().toLowerCase().indexOf("helper")!=-1)
				String filename=file.getAbsolutePath().toLowerCase();
				if (filename.endsWith("messages.java")||filename.endsWith("message.java"))
				{				
					messageFiles.add(file.getAbsolutePath());	
				}
			}
		}
	}
	
	private static void getRuleFileList(File[] fileArray,Set<String> ruleFname)
	{
		for(File file:fileArray)
		{
			if(file.isDirectory())
			{
				System.out.println("directory=>"+file.getName()+"rule count=>"+ruleFname.size());
				
				getRuleFileList(file.listFiles(), ruleFname);
			}else
			{
				String fname=file.getName();
				if(fname.split("_").length>3)
				{
					fname=fname.substring(0, fname.lastIndexOf("_"));
				}
				else
				{
					fname=fname.substring(0,fname.lastIndexOf("."));
				}
				if(!ruleFname.contains(fname))
				{
					ruleFname.add(fname);
				}
				else
				{
					//System.out.println("file exist==>"+fname);
				}
			}
			
		}
	}
}
