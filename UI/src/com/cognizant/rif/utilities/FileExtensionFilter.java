package com.cognizant.rif.utilities;

import java.io.File;
import java.io.FileFilter;

public class FileExtensionFilter implements FileFilter {
	private String ext;

	public FileExtensionFilter(String ext) {
		this.ext = ext;
	}

	@Override
	public boolean accept(File pathname) {
		// TODO Auto-generated method stub
		if(pathname.isDirectory())
			return true;
		return (pathname.getName().endsWith(ext));
	}
}