package com.xerox.ghs.mmis.enterprise.common.security.common.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xerox.ghs.mmis.enterprise.common.security.common.model.User;
import com.xerox.ghs.mmis.enterprise.common.security.common.util.CommonConstants;
import com.xerox.ghs.mmis.enterprise.common.security.common.util.NavigationManager;
import com.xerox.ghs.mmis.enterprise.common.security.common.util.NewsAndAlertManager;
import com.xerox.ghs.mmis.member.support.service.MockMemberService;
import com.xerox.ghs.mmis.member.support.util.MemberConstants;
import com.xerox.ghs.mmis.provider.util.ProviderConstants;

/**
 * 
 * Controller for Login , Logout operations
 *
 */
@Controller
public class LoginController {
	
	
	private final static Logger LOGGER = Logger.getLogger( LoginController.class .getName());
	
	
	/**
	 * This method is used to populate the login page
	 * @return ModelAndView 
	 */
	@RequestMapping(value={CommonConstants.LOGIN_PATH,CommonConstants.ROOT_PATH})
    public ModelAndView loginView() {
        
		ModelAndView loginView = new ModelAndView(CommonConstants.LOGIN_VIEW);
		
		loginView.addObject(CommonConstants.MENU_LIST,NavigationManager.getLoginPageMenuList());
		loginView.addObject(CommonConstants.QUICK_LINK_LIST,null);
		loginView.addObject(CommonConstants.NEWS_LIST, NewsAndAlertManager.getLatestNewsAndAlert());
		
		return loginView;
        
    }
	
	
	/**
     * This method is used to retrieve the information of a user logging in. 
	 * @param MemberClaim memberClaim
	 * @param Model model
	 * @param HttpServletRequest httpServletRequest
	 * @return ModelAndView memberLandingPage
     */
   
    @RequestMapping(value=CommonConstants.LOGIN_RESP_PATH, method = RequestMethod.POST)
    public ModelAndView getUserInfo(User user,HttpServletRequest httpServletRequest) {
    	
		HttpSession userLoginsession = httpServletRequest.getSession();		
		User usr = MockMemberService.getInstance().getUser(user);		
		String userId = user.getUserId();		
		userLoginsession.setAttribute(CommonConstants.USER_SESSION,userId);	
		
		ModelAndView landingView = new ModelAndView(CommonConstants.LOGIN_VIEW);
		
			
		if(usr.getHierarchyLOB().equalsIgnoreCase(ProviderConstants.PROVIDER_USER_GROUP)){
			
			landingView = new ModelAndView(ProviderConstants.PDR_MEM_ELG_VIEW);
			
			landingView.addObject(CommonConstants.MENU_LIST,NavigationManager.getProviderPageMenuList());
			landingView.addObject(CommonConstants.QUICK_LINK_LIST,NavigationManager.getQuickLinkList());
			landingView.addObject(CommonConstants.NEWS_LIST, NewsAndAlertManager.getLatestNewsAndAlert());
			
			
		}else{
			
			landingView = new ModelAndView(MemberConstants.MEM_LDNG_PG_VIEW);		
			
			landingView.addObject(CommonConstants.MENU_LIST,NavigationManager.getMemberPageMenuList());
			landingView.addObject(CommonConstants.QUICK_LINK_LIST,NavigationManager.getQuickLinkList());
			landingView.addObject(CommonConstants.NEWS_LIST, NewsAndAlertManager.getLatestNewsAndAlert());
			
		}
		
		return landingView;
	}
    
	/**
     * This method is used to user logging out. This method will reset the User Session Object 
	 * @param HttpServletRequest httpServletRequest
	 * @return ModelAndView LoginView
     */
    
    @RequestMapping(value=MemberConstants.LOGOUT_RESP_PATH)
    public ModelAndView logoutContoller(HttpServletRequest httpServletRequest) {
    	
		HttpSession userLoginsession = httpServletRequest.getSession();		
		userLoginsession.setAttribute(CommonConstants.USER_SESSION,null);
		
		ModelAndView loginView = new ModelAndView(CommonConstants.LOGIN_VIEW);
		
		loginView.addObject(CommonConstants.MENU_LIST,NavigationManager.getLoginPageMenuList());
		loginView.addObject(CommonConstants.QUICK_LINK_LIST,null);
		loginView.addObject(CommonConstants.NEWS_LIST, NewsAndAlertManager.getLatestNewsAndAlert());
		
		return loginView;
	}
    
    
    
}

