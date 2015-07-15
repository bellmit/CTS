package com.cognizant.tool.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ErrorMessageIdentification {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> errMsg=new ArrayList<String>();
		try {
			Scanner sc=new Scanner(new File("D:\\396662\\MT\\ErrorMessageToVerify.txt"));
			
			while(sc.hasNext())
			{
				errMsg.add(sc.next());
				//System.out.println(sc.next());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File fname=new File("D:\\Rule Extract\\ErrorPattern");
		File[] fileArray=fname.listFiles();
		Set<String> ruleFname=new HashSet<>();
		getRuleFileList(fileArray,ruleFname,errMsg);
		System.out.println("Rule identified==>"+ruleFname.size());
		System.out.println("Rule list==>"+ruleFname);
	}

	private static void getRuleFileList(File[] fileArray,Set<String> ruleFname,List<String> errMsg)
	{
		for(File file:fileArray)
		{
			if(file.isDirectory())
			{
				System.out.println("directory=>"+file.getName()+"rule count=>"+ruleFname.size());
				
				getRuleFileList(file.listFiles(), ruleFname,errMsg);
			}else
			{
				String fname=file.getName();
				if(fname.indexOf("_")!=-1)
				{
					//System.out.println(fname+"=>"+file.length());
					fname=fname.substring(0, fname.lastIndexOf("_"));
				}
				if(file.length() > 0 && errMsg.contains(fname) && !ruleFname.contains(fname))
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
