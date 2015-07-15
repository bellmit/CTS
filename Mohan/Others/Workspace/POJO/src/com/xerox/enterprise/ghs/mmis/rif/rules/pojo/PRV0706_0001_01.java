package com.xerox.enterprise.ghs.mmis.rif.rules.pojo;


import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.xerox.enterprise.ghs.mmis.rif.common.exception.RIFPOJORulesException;
import com.xerox.enterprise.ghs.mmis.rif.core.Rule;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesContext;
import com.xerox.enterprise.ghs.mmis.rif.core.RulesResult;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

import com.acs.enterprise.mmis.provider.common.domain.ApplicationStatus;

import org.apache.commons.beanutils.BeanUtils;

import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.mmis.provider.common.domain.Provider;
import com.acs.enterprise.mmis.provider.common.helper.ProviderUtil;

/**
 * Ruleclass of the rule "PRV0706.0001.01"
 * @author
 * POJO extracted from Class:ProviderAppMntHelper
 */
public class PRV0706_0001_01 extends Rule{
	
	/*Static logger to log a message */
	private static final Logger log = Logger.getLogger(PRV0706_0001_01.class);
	
	
	
	/**
	 * Execute method of the rule. This holds the rule conditions and actions.
	 * The required objects are initialized through init() method.
	 * return - execution result of rule either SUCCESS or FAILURE
	 */
	public static RulesResult execute(RulesContext ctx, String ruleId) throws RIFPOJORulesException {
	
			
		//Construct rules result object
		RulesResult rulesResult = new RulesResult(ruleId);
		String userID = null;
			List appStatusList = null;
			Provider oldProvider = null;
			EnterpriseLogger log = null;

		if (ctx != null) {
				
			List<Object> inputObjs = ctx.getContextObject();

			userID = (String) inputObjs.get(0);
if(userID==null){
log.error("Input Object userID is  null during intialization for the rule "+ruleId);
throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule "+ruleId);
}

				appStatusList = (List) inputObjs.get(1);
if(appStatusList==null){
log.error("Input Object appStatusList is  null during intialization for the rule "+ruleId);
throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule "+ruleId);
}

				oldProvider = (Provider) inputObjs.get(5);
if(oldProvider==null){
log.error("Input Object oldProvider is  null during intialization for the rule "+ruleId);
throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule "+ruleId);
}

				log = (EnterpriseLogger) inputObjs.get(6);
if(log==null){
log.error("Input Object log is  null during intialization for the rule "+ruleId);
throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule "+ruleId);
}
						
		} else {
			log.error("Input Context Object is null during intialization for the rule "+ruleId);
			throw new RIFPOJORulesException("Unable to initialize the required rules objects for rule "+ruleId);
		}
		log.info("Executing the actions for the rule "+ruleId);
		//EXTRACT_START PRV0706.0001.01
    	// Overwriting Old sysID data with New sysID information.
    	Set appStatusSet = null;
        if (!ProviderUtil
                .checkForNullObject(appStatusList))
        {
            Iterator appStatusItr = appStatusList.iterator();
            appStatusSet = new HashSet();
            while (appStatusItr.hasNext())
            {
                ApplicationStatus currentApplicationStatus = (ApplicationStatus) appStatusItr.next();
                ApplicationStatus oldApplicationStatus = new ApplicationStatus();    
                if(!currentApplicationStatus.getApplicationStatusCode().equalsIgnoreCase("AA"))
                {
	                try
	                {
	                    BeanUtils.copyProperties(oldApplicationStatus,currentApplicationStatus);
	                }
	                catch (IllegalAccessException ex)
	                {
	                	 log
	                            .debug("Exception occured in copyWebDisplay of ProviderAppMntHelper");
	                }
	                catch (InvocationTargetException ex)
	                {
	                	  log
	                            .debug("Exception occured in copyWebDisplay of ProviderAppMntHelper");
	                }
	                oldApplicationStatus.setProvider(oldProvider);
	                oldApplicationStatus.setVersionNo(0);
	                oldApplicationStatus.setApplicationStatusSK(null);
	                setAuditUserDetails(oldApplicationStatus,userID);
	                appStatusSet.add(oldApplicationStatus);
                }
            }
        }
        oldProvider.setApplicationStatus(appStatusSet);
		//EXTRACT_END

		
				rulesResult.setRuleStatus(true);
		return rulesResult;
		
	}
	
	
	public static void initialize() throws RIFPOJORulesException {
		// TODO Auto-generated method stub
		
	}


	
	public static void shutdown() throws RIFPOJORulesException {

		System.out.println("shutdown called");
		
	}
	
	 protected static EnterpriseBaseDomain setAuditUserDetails(EnterpriseBaseDomain baseObject, String loginUserId)
	    {	 	
	    	baseObject.setAddedAuditUserID(loginUserId);
	        baseObject.setAuditUserID(loginUserId);
	        return baseObject;
	    }
	
	}
