/**
 *  © Cognizant Technology Solutions 2012. 
 *  All rights reserved.
 *  
 *  Except for  open  source or  proprietary  third party software components embedded in  this Cognizant
 *  proprietary software program (“Program”), this Program is protected by copyright laws,  international
 *  treaties and other pending or existing intellectual property rights and statutes in India, the United 
 *  States and other countries. Except as expressly permitted by Cognizant and its third party licensors,  
 *  the program may  neither be used,  reproduced, transmitted, distributed or modified, either  in whole  
 *  nor in part, in any manner or form   whatsoever (including without limitation electronic, mechanical, 
 *  printing,  photocopying,  recording or otherwise), without  the prior,  express,  written consent and  
 *  acknowledgment of  Cognizant Technology Solutions.  Any violation  may result  in  severe  civil  and  
 *  criminal penalties, and will be prosecuted to the maximum extent possible under the law. 
 */
package com.cognizant.rif.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.cognizant.rif.common.logging.LogUtil;



/**
 * 
 * @author 117847
 * 
 */
public class CmdThread extends Thread {

	InputStream is;
	String type;
	private static final String CLASS_NAME = "CmdThread";
	
    /**
     * Class level variable that contains the logger name
     */
    private static final String LOG_NAME = "EXTRACT_LOG";

	/**
	 * 
	 * @param is
	 * @param type
	 */
	public CmdThread(InputStream is, String type) {
		this.is = is;
		this.type = type;
	}

	public void run() {
		try {
			if(LogUtil.isDebugEnabled(LOG_NAME)){
				LogUtil.debug(CLASS_NAME, "run", "Entering the run.", LOG_NAME);
			}
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
			  LogUtil.error(CLASS_NAME, "run", line,  LOG_NAME);
			}
			if(LogUtil.isDebugEnabled(LOG_NAME)){
				LogUtil.debug(CLASS_NAME, "run", "Exiting the run.", LOG_NAME);
			}
		} catch (IOException ioe) {
			LogUtil.error(CLASS_NAME, "run", ioe.getMessage(),  LOG_NAME);
		}
	}
}
