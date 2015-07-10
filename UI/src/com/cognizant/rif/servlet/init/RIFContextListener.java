package com.cognizant.rif.servlet.init;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cognizant.rif.constants.RIFInputConstants;
import com.cognizant.rif.rules.RuleExternalizer;
import com.cognizant.rif.utilities.FileHandlerUtil;
import com.cognizant.rif.utilities.PropertyUtil;


public class RIFContextListener implements ServletContextListener {

	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("contextDestroyed");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		ServletContext sc=sce.getServletContext();
		
		try {
			/*System.out.println("contextPath:"+sc.getContextPath());
			System.out.println("Real Path:"+sc.getRealPath(RIFInputContants.ERROR_TO_RULE_PATH));
			*/
			List<String> constantFiles;
			//setting context properties
			Properties errToRuleProp=PropertyUtil.loadProperties(sc.getRealPath(RIFInputConstants.ERROR_TO_RULE_PATH));
			Properties errorMessageProp=PropertyUtil.loadProperties(sc.getRealPath(RIFInputConstants.ERROR_PROPERTY));
			File sourceDir=new File(RIFInputConstants.SOURCE_CODE_ROOT_DIR);
			List<String> propertyFiles=FileHandlerUtil.retrievePropertyFiles(sourceDir,null);
			constantFiles=FileHandlerUtil.retrieveConstantFiles(sourceDir, null);
			
			sc.setAttribute("errtoRule", errToRuleProp);
			sc.setAttribute("errorMessage", errorMessageProp);
			sc.setAttribute("propertyFiles", propertyFiles);
			sc.setAttribute("constantFiles", constantFiles);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Context Intialization Failed:"+ e.getMessage());
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		System.out.println("Context Initialized");
	}

}
