package com.xerox.ghs.mmis.member.support.controller;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.acs.enterprise.mmis.member.common.application.exception.MemberSupportBusinessException;
import com.acs.enterprise.mmis.member.common.vo.MemberCommonDetailVO;
import com.acs.enterprise.mmis.member.support.common.delegate.MemberSupportDelegate;
import com.acs.enterprise.mmis.member.support.selfservices.view.vo.MemberClaimsSearchCriteriaVO;
import com.xerox.ghs.mmis.enterprise.common.security.common.util.CommonConstants;
import com.xerox.ghs.mmis.enterprise.common.security.common.util.NavigationManager;
import com.xerox.ghs.mmis.enterprise.common.security.common.util.NewsAndAlertManager;
import com.xerox.ghs.mmis.member.support.bean.MemberClaim;
import com.xerox.ghs.mmis.member.support.bean.SearchClaimVO;
import com.xerox.ghs.mmis.member.support.service.MemberServices;
import com.xerox.ghs.mmis.member.support.service.MockMemberService;
import com.xerox.ghs.mmis.member.support.util.MemberConstants;

@Controller
public class DisplayClaimsController {
	
	/**
	 * This method is used to get claim details for the given search criteria
	 * @param SearchClaimVO searchClaimVO
	 * @return ModelAndView
	 * @throws MemberSupportBusinessException 
	 * @throws NamingException 
	 * @throws CreateException 
	 * @throws RemoteException 
	 */
	@RequestMapping(value=MemberConstants.GET_CLAIMS_PATH, method=RequestMethod.GET)
	@ResponseBody
	public Map<String, List> getClaims(SearchClaimVO searchClaimVO) throws MemberSupportBusinessException, RemoteException, CreateException, NamingException{
		
		MemberCommonDetailVO commonDetailVO = null;
		commonDetailVO = MemberServices.getInstance().getClaims(searchClaimVO);
		Map<String, List> claimsMap = new LinkedHashMap<String, List>();
		claimsMap.put(MemberConstants.CLAIM_DETAILS_VIEW, commonDetailVO.getSpanList());
		return claimsMap;
	}
	
	@RequestMapping(value=MemberConstants.CLAIMTYPE_PATH,method=RequestMethod.GET)
	@ResponseBody
	public Map getReferenceData(){
		
		
		List referenceData = MockMemberService.getInstance().getReferenceData(MemberConstants.CLAIM_TYPE);
		Map modelData = new HashMap();
		modelData.put(MemberConstants.CLAIM_TYPE_LIST, referenceData);
		return modelData;
		
	}
	
	  /**
	  * This method is used to navigate to display claims page 
	  * @return ModelAndView 
	  */
	 @RequestMapping(MemberConstants.DISPLAY_CLAIMS_PATH)
	 public ModelAndView displayClaims() {
	 	
		 ModelAndView claimsView = new ModelAndView(MemberConstants.DISPLAY_CLAIMS_VIEW);
			
		claimsView.addObject(CommonConstants.MENU_LIST,NavigationManager.getMemberPageMenuList());
		claimsView.addObject(CommonConstants.QUICK_LINK_LIST,NavigationManager.getQuickLinkList());
		claimsView.addObject(CommonConstants.NEWS_LIST, NewsAndAlertManager.getLatestNewsAndAlert());
	
		return claimsView;
	 }
	 
	 /**
	  * This method is used to navigate to Member Landing page 
	  * @return ModelAndView 
	  */
	 @RequestMapping(MemberConstants.MEM_LDNG_PG_PATH)
	 public ModelAndView memberLanding(){
	 	
		ModelAndView memberLandingView = new ModelAndView(MemberConstants.MEM_LDNG_PG_VIEW);
			
		memberLandingView.addObject(CommonConstants.MENU_LIST,NavigationManager.getMemberPageMenuList());
		memberLandingView.addObject(CommonConstants.QUICK_LINK_LIST,NavigationManager.getQuickLinkList());
		memberLandingView.addObject(CommonConstants.NEWS_LIST, NewsAndAlertManager.getLatestNewsAndAlert());
	
		return memberLandingView;
	 }
	

}

