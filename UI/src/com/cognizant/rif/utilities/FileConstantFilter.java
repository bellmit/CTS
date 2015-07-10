package com.cognizant.rif.utilities;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class FileConstantFilter implements FileFilter {
	

	@Override
	public boolean accept(File pathname) {
		// TODO Auto-generated method stub
		if(pathname.isDirectory())
		{
			return true;
		}
		
		if (pathname.getAbsolutePath().toLowerCase().endsWith(".java")&&
				(pathname.getAbsolutePath().toLowerCase().indexOf("constant")!=-1))
		{
			return true;
		}
		return false;
	}
}