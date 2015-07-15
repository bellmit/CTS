package com.xerox.ghs.mmis.provider.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.acs.enterprise.mmis.provider.common.domain.Provider;
/*import com.acs.enterprise.mmis.provider.common.vo.ProviderApplicationRequestVO;
import com.acs.enterprise.mmis.provider.common.vo.ProviderApplicationResponseVO;
import com.acs.enterprise.mmis.provider.enrollment.application.exception.ProviderInsertException;*/
import com.acs.enterprise.mmis.provider.enrollment.common.delegate.ProviderEnrollmentDelegate;
import com.ibm.websphere.plugincfg.initializers.ProviderApplicationChangePluginTask;
import com.xerox.ghs.mmis.enterprise.common.security.common.util.CommonConstants;
import com.xerox.ghs.mmis.enterprise.common.security.common.util.NavigationManager;
import com.xerox.ghs.mmis.enterprise.common.security.common.util.NewsAndAlertManager;
import com.xerox.ghs.mmis.member.support.bean.Member;
import com.xerox.ghs.mmis.member.support.util.MemberConstants;
import com.xerox.ghs.mmis.provider.model.MemberSearch;
import com.xerox.ghs.mmis.provider.service.MockProviderDataService;
import com.xerox.ghs.mmis.provider.util.ProviderConstants;

/**
 * Provider Informations and Controls
 * 
 */
@Controller
public class ProviderController {
	
	/**
	 * This method is used to get Provider Landing Page ModelAndView
	 * @return ModelAndView 
	 */
	@RequestMapping(ProviderConstants.PDR_LDNG_PG_PATH)
	public ModelAndView providerLandingPage() {
    	
		ModelAndView providerLandingView = new ModelAndView(ProviderConstants.PDR_LDNG_PG_VIEW);
		
		providerLandingView.addObject(CommonConstants.MENU_LIST,NavigationManager.getProviderPageMenuList());
		providerLandingView.addObject(CommonConstants.QUICK_LINK_LIST,NavigationManager.getQuickLinkList());
		providerLandingView.addObject(CommonConstants.NEWS_LIST, NewsAndAlertManager.getLatestNewsAndAlert());
		
		return providerLandingView;
    }
	
	/**
	 * This method is used to get check MemberEligibilty for Provider 
	 * @return ModelAndView 
	 */
	@RequestMapping(ProviderConstants.PDR_MEM_ELG_PATH)
    public ModelAndView memberEligibility() {
    	
        ModelAndView providerLandingView = new ModelAndView(ProviderConstants.PDR_MEM_ELG_VIEW);
		
		providerLandingView.addObject(CommonConstants.MENU_LIST,NavigationManager.getProviderPageMenuList());
		providerLandingView.addObject(CommonConstants.QUICK_LINK_LIST,NavigationManager.getQuickLinkList());
		providerLandingView.addObject(CommonConstants.NEWS_LIST, NewsAndAlertManager.getLatestNewsAndAlert());
		
		return providerLandingView;
    }
	
	/**
	 * This method is used to retrive the list of Eligible member
	 * @param MemberSearch application/json
	 * @return List<Member> application/json 
	 */
	@RequestMapping(value=ProviderConstants.PDR_MEM_ELG_SRH_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Member> searchMemberForEligibility(@RequestBody MemberSearch membersearch) throws Exception {
		MockProviderDataService dataService = MockProviderDataService.getInstance();
		return dataService.getMemberEligibilityList();
	}
	
	/**
	 * To display the Create dental claim
	 */
	@RequestMapping(ProviderConstants.CREATE_DENTAL_CLAIM_PATH)
	public ModelAndView createDentalClaim() {
		return new ModelAndView(ProviderConstants.CREATE_DENTAL_CLAIM_VIEW);
	}
	
	/**
	 * This method is used to enroll a provider
	 * @throws ProviderInsertException 
	 */
	/*public ProviderApplicationResponseVO enrollProvider(Provider provider) throws ProviderInsertException
	{
		ProviderEnrollmentDelegate providerEnrollmentDelegate = new ProviderEnrollmentDelegate();
		ProviderApplicationRequestVO providerAppRequestVO = new ProviderApplicationRequestVO();
		providerAppRequestVO.setProvider(provider);
		ProviderApplicationResponseVO providerResponseVO = providerEnrollmentDelegate.enrollProviderApplication(providerAppRequestVO);
		System.out.println("Status"+providerResponseVO.getApplicationStatusVO().getApplicationStatus());
		return providerResponseVO;
	}*/
}
