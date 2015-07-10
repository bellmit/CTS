package com.cognizant.rif.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	
	public static Properties loadProperties(String path) throws IOException {
		
		Properties properties = new Properties();
		InputStream input = new FileInputStream(new File(path));
		properties.load(input);
		
		return properties;
	}

}
