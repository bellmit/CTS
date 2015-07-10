/**
 *  � Cognizant Technology Solutions 2012. 
 *  All rights reserved.
 *  
 *  Except for  open  source or  proprietary  third party software components embedded in  this Cognizant
 *  proprietary software program (�Program�), this Program is protected by copyright laws,  international
 *  treaties and other pending or existing intellectual property rights and statutes in India, the United 
 *  States and other countries. Except as expressly permitted by Cognizant and its third party licensors,  
 *  the program may  neither be used,  reproduced, transmitted, distributed or modified, either  in whole  
 *  nor in part, in any manner or form   whatsoever (including without limitation electronic, mechanical, 
 *  printing,  photocopying,  recording or otherwise), without  the prior,  express,  written consent and  
 *  acknowledgment of  Cognizant Technology Solutions.  Any violation  may result  in  severe  civil  and  
 *  criminal penalties, and will be prosecuted to the maximum extent possible under the law. 
 */
package com.cognizant.rif.formatter;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.net.URL;

import com.cognizant.rif.common.logging.LogUtil;
import com.cognizant.rif.exceptions.FormatterException;
import com.cognizant.rif.utilities.CmdThread;


/**
 * @author 117847
 *
 */
public class JacobeFormatter {

	private static JacobeFormatter jacobeFormatter = null;
	private static final String CLASS_NAME = "JacobeFormatter";
	
	/**
     * Class level variable that contains the logger name
     */
    private static final String LOG_NAME = "EXTRACT_LOG";

	/**
	 * Method to get instance.
	 *
	 * @return helper
	 */
	public static JacobeFormatter getInstance() {
		if (null == jacobeFormatter) {
			jacobeFormatter = new JacobeFormatter();
		}
		return jacobeFormatter;
	}

	public void formatGeneratedCode( String sourceCodePath) {
		
		if(LogUtil.isDebugEnabled(LOG_NAME)){
			LogUtil.debug(CLASS_NAME, "formatGeneratedCode", "sourceCodepath="+sourceCodePath, LOG_NAME);
		}

		try{
            URL jacobeFileUrl = JacobeFormatter.class.getResource("/misc/jacobe/jacobe.exe");
            
            System.out.println("In formatGeneratedCode==>"+sourceCodePath);
            if(jacobeFileUrl==null)
			{
            	System.out.println("JacobeUrl is Null");
            	throw new FormatterException("Jacobe exe path (/misc/jacobe/jacobe.exe) is not found");
			}
			//System.out.println("The value of the sourceCodePath >>"+sourceCodePath);
			String[] cmd = new String[3];
			cmd[0] = jacobeFileUrl.toURI().getPath();
			cmd[1] = "-overwrite";
			cmd[2] = sourceCodePath;

			Runtime rt = Runtime.getRuntime();

		//	System.out.println("The value of the cmd  >>" + cmd);
			Process proc = rt.exec(cmd);
			// any error message?

			CmdThread errorGobbler = new CmdThread(proc.getErrorStream(), "SMARTE");

			// any output?
			CmdThread outputGobbler = new CmdThread(proc.getInputStream(), "OUTPUT");

			// kick them off
			errorGobbler.start();
			outputGobbler.start();

			// any error???
			int exitVal = proc.waitFor();
			if (exitVal != 0) {
				new FormatterException("Error executing the formatter. ExitValue="+exitVal);
			}
		}catch(Exception e){
			new FormatterException("Exception occured with message="+e.getMessage());
		}	
		
		if(LogUtil.isDebugEnabled(LOG_NAME)){
			LogUtil.debug(CLASS_NAME, "formatGeneratedCode", "Exit from formatGeneratedCode", LOG_NAME);
		}

	}
	
	/**
	 *
	 * @param aFile
	 */
	public void deleteJacobeFiles(File aFile) {
		LogUtil.info(CLASS_NAME, "deleteJacobeFiles", "Inside the deleteJacobeFiles Folder="+aFile.getName(), LOG_NAME);
		System.out.println("Deleting temp jacobe files");
		FilterExtension filter = new FilterExtension(".jacobe");
		if(aFile.isFile())
		{
			aFile=aFile.getParentFile();
		}
		if (aFile.isDirectory()) {

			File[] jacobeFiles = aFile.listFiles(filter);

			if (jacobeFiles != null) {
				for (int itr = 0; itr < jacobeFiles.length; itr++) {
					jacobeFiles[itr].delete();

				}
			}

			FileFilter fileFilter = new FileFilter() {
				public boolean accept(File file) {
					return file.isDirectory();
				}
			};

			File[] files = aFile.listFiles(fileFilter);

			if (files != null) {
				for (int itr = 0; itr < files.length; itr++) {
					deleteJacobeFiles(files[itr]);

				}
			}
		}
		
	//	LogUtil.info(CLASS_NAME, "deleteJacobeFiles", "Outside the deleteJacobeFiles", LOG_NAME);
	}
	
	/**
	 *
	 * @author 117847
	 *
	 */
	class FilterExtension implements FilenameFilter {
		private String ext;

		public FilterExtension(String ext) {
			this.ext = ext;
		}

		public boolean accept(File dir, String name) {
			return (name.endsWith(ext));
		}
	}
	

}
