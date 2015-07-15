package com.spring.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MySpringInitializer {

	
	
	public Log getLogger(Class classname)
	{
		Log log=LogFactory.getLog(classname);
		return log;
	}
	
	public void loadProperties()
	{
		
	}
}
