package com.cognizant.rif.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.rif.constants.RIFCommonConstants;
import com.cognizant.rif.utilities.FileExtensionFilter;

public class FileHandlerUtil {
	
	public static List<String> retrievePropertyFiles(File rootDir,List<String> propertyFiles)
	{
		if(propertyFiles==null)
		{
			propertyFiles=new ArrayList<>();
		}
		FileExtensionFilter filefilter=new FileExtensionFilter(RIFCommonConstants.PROPERTY_EXTENSION);
		File[] files = rootDir.listFiles(filefilter);
		//System.out.println(files);
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory())
				retrievePropertyFiles(files[i],propertyFiles);
			else 
			{
				propertyFiles.add(files[i].getAbsolutePath());	
					//System.out.println(files[i].getAbsolutePath());
				
			}
		}
		return propertyFiles;
		
	}
	
	public static List<String> retrieveConstantFiles(File rootDir,List<String> constantFiles)
	{
		if(constantFiles==null)
		{
			constantFiles=new ArrayList<>();
		}
		File[] files = rootDir.listFiles(new FileConstantFilter());
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory())
			{
				retrieveConstantFiles(files[i], constantFiles);
			}
			else 
			{
				constantFiles.add(files[i].getAbsolutePath());	
			
			}
		}
		return constantFiles;
	}

}
