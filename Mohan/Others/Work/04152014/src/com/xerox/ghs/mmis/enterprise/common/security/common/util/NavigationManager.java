package com.xerox.ghs.mmis.enterprise.common.security.common.util;

import java.util.ArrayList;
import java.util.List;

import com.xerox.ghs.mmis.enterprise.common.security.common.model.MenuItem;

/**
 * Manage the Operations on Navigation Items
 * To retrive the Dynamic Naviagtion based on Logged in User
 *
 */

public class NavigationManager {

	/**
	 * This Method will retrive the Menu list for Login Page
	 * @return Listof MenuItems 
	 */
	public static List<MenuItem> getLoginPageMenuList(){
		
		List<MenuItem> listOfMenu = new ArrayList<MenuItem>();
		
		//Menu Item for Home
		MenuItem homeMenu = new MenuItem();		
		homeMenu.setTitle("Home");
		homeMenu.setDomClass("glyphicon glyph-home");
		homeMenu.setLink("#");
		
		//Menu Item for State Programs
		MenuItem stPrograms = new MenuItem();		
		stPrograms.setTitle("State Programs");
		stPrograms.setDomClass("glyphicon glyph-state-program");
		stPrograms.setLink("#");
		
		//Menu Item for Member Info
		MenuItem memberInfo = new MenuItem();		
		memberInfo.setTitle("Members Info");
		memberInfo.setDomClass("glyphicon glyph-member-info");
		memberInfo.setLink("#");		
		
		//Menu Item for Provider Info
		MenuItem providerInfo = new MenuItem();		
		providerInfo.setTitle("Provider Info");
		providerInfo.setDomClass("glyphicon glyph-provi-info");
		providerInfo.setLink("#");
		
		//Menu Item for Documents
		MenuItem docs = new MenuItem();		
		docs.setTitle("Documents");
		docs.setDomClass("glyphicon glyph-documents");
		docs.setLink("#");
		
		//Menu Item for Directories
		MenuItem dirs = new MenuItem();		
		dirs.setTitle("Directories");
		dirs.setDomClass("glyphicon glyph-directories");
		dirs.setLink("#");
		
		listOfMenu.add(homeMenu);
		listOfMenu.add(stPrograms);
		listOfMenu.add(memberInfo);
		listOfMenu.add(providerInfo);
		listOfMenu.add(docs);
		listOfMenu.add(dirs);
		
		
		return  listOfMenu;
		
	}
	
	/**
	 * This Method will retrive the Menu list for Members Page
	 * @return Listof MenuItems 
	 */
	public static List<MenuItem> getMemberPageMenuList(){
		
		List<MenuItem> listOfMenu = new ArrayList<MenuItem>();
		
		//Menu Item for Home
		MenuItem homeMenu = new MenuItem();		
		homeMenu.setTitle("Home");
		homeMenu.setDomClass("glyphicon glyph-home");
		homeMenu.setLink("#");
		
		//Menu Item for State Programs
		MenuItem sumOfCare = new MenuItem();		
		sumOfCare.setTitle("Summary of Care");
		sumOfCare.setDomClass("glyphicon glyph-documents");
		sumOfCare.setLink("#");
		
		//Menu Item for Requests
		MenuItem reqs = new MenuItem();		
		reqs.setTitle("Requests");
		reqs.setDomClass("glyphicon glyph-documents");
		reqs.setLink("#");
		
			List<MenuItem> reqsMenuList = new ArrayList<MenuItem>();
			
			MenuItem liveChat = new MenuItem();
			reqs.setTitle("Live chat");
			reqs.setLink("#");
		
			MenuItem msq = new MenuItem();
			msq.setTitle("Medical Services Questionnaire");
			msq.setLink("#");	
			
			MenuItem pst = new MenuItem();
			pst.setTitle("Personal Transportation");
			pst.setLink("#");

			MenuItem gps = new MenuItem();
			gps.setTitle("General Provider Search");
			gps.setLink("#");
			
			MenuItem otherIns = new MenuItem();
			otherIns.setTitle("Update Other Insurance");
			otherIns.setLink("#");
			
			MenuItem usrPre = new MenuItem();
			usrPre.setTitle("Update User Preference");
			usrPre.setLink("#");			
			
			reqsMenuList.add(liveChat);
			reqsMenuList.add(msq);
			reqsMenuList.add(pst);
			reqsMenuList.add(gps);
			reqsMenuList.add(otherIns);
			reqsMenuList.add(usrPre);		
		
		reqs.setSubMenus(reqsMenuList);
		
		
		
		listOfMenu.add(homeMenu);
		listOfMenu.add(sumOfCare);
		listOfMenu.add(reqs);
		

		
		
		return  listOfMenu;
		
	}
	
	/**
	 * This Method will retrive the Menu list for Providers Page
	 * @return Listof MenuItems 
	 */
	public static List<MenuItem> getProviderPageMenuList(){
		
		List<MenuItem> listOfMenu = new ArrayList<MenuItem>();
		
		//Menu Item for Home
		MenuItem homeMenu = new MenuItem();		
		homeMenu.setTitle("Home");
		homeMenu.setDomClass("glyphicon glyph-home");
		homeMenu.setLink("memberEligibility");
		
		//Menu Item for Member
		MenuItem memberMenu = new MenuItem();		
		memberMenu.setTitle("Member");
		memberMenu.setDomClass("glyphicon glyph-documents");
		memberMenu.setLink("#");
		
			List<MenuItem> memberMenuList = new ArrayList<MenuItem>();
			
			MenuItem checkElg = new MenuItem();
			checkElg.setTitle("Check Eligibility");
			checkElg.setLink("memberEligibility");	
			
			memberMenuList.add(checkElg);
			
		memberMenu.setSubMenus(memberMenuList);
		
		
		
		//Menu Item for Authorizations
		MenuItem authMenu = new MenuItem();		
		authMenu.setTitle("Authorizations");
		authMenu.setDomClass("glyphicon glyph-documents");
		authMenu.setLink("#");
		
			List<MenuItem> authMenuList = new ArrayList<MenuItem>();
			
			MenuItem editAuth = new MenuItem();
			editAuth.setTitle("View/Edit Authorization Request");
			editAuth.setLink("#");	
			
			MenuItem authorize = new MenuItem();
			authorize.setTitle("Authorization");
			authorize.setLink("#");	
			
			MenuItem reff = new MenuItem();
			reff.setTitle("Referral");
			reff.setLink("#");	
			
			authMenuList.add(editAuth);
			authMenuList.add(authorize);
			authMenuList.add(reff);
			
		authMenu.setSubMenus(authMenuList);
		
		
		//Menu Item for Claims
		MenuItem claimsMenu = new MenuItem();		
		claimsMenu.setTitle("Claims");
		claimsMenu.setDomClass("glyphicon glyph-directories");
		claimsMenu.setLink("#");
		
			List<MenuItem> claimsMenuList = new ArrayList<MenuItem>();
			
			MenuItem crClaim = new MenuItem();
			crClaim.setTitle("Create Claims");
			crClaim.setLink("createDentalClaim");	
			
			MenuItem mngClaim = new MenuItem();
			mngClaim.setTitle("Manage Claims");
			mngClaim.setLink("#");	
			
			MenuItem crTemp = new MenuItem();
			crTemp.setTitle("Create Templates");
			crTemp.setLink("#");	
			
			MenuItem mngTemp = new MenuItem();
			mngTemp.setTitle("Manage Templates");
			mngTemp.setLink("#");	
			
			MenuItem clmStatus = new MenuItem();
			clmStatus.setTitle("Claim Status Inquiry");
			clmStatus.setLink("#");	
			
			MenuItem paymentInq = new MenuItem();
			paymentInq.setTitle("Payment Inquiry");
			paymentInq.setLink("#");				
			
			claimsMenuList.add(crClaim);
			claimsMenuList.add(mngClaim);
			claimsMenuList.add(crTemp);
			claimsMenuList.add(mngTemp);
			claimsMenuList.add(clmStatus);
			claimsMenuList.add(paymentInq);
			
		claimsMenu.setSubMenus(claimsMenuList);	
		
		
		
		listOfMenu.add(homeMenu);
		listOfMenu.add(memberMenu);
		listOfMenu.add(authMenu);
		listOfMenu.add(claimsMenu);

		return listOfMenu;
	}
	
	/**
	 * This Method will retrive the Menu list for QuickLinks
	 * @return Listof MenuItems 
	 */
	public static List<MenuItem> getQuickLinkList(){
	
		List<MenuItem> listOfMenu = new ArrayList<MenuItem>();
		
		//Menu Item for Information topics A-Z
		MenuItem infoAZ = new MenuItem();		
		infoAZ.setTitle("Information topics A-Z");
		infoAZ.setLink("#");
		
		//Menu Item for Find a Health Care Provider
		MenuItem fndPrvdr = new MenuItem();		
		fndPrvdr.setTitle("Find a Health Care Provider");
		fndPrvdr.setLink("#");
		
		
		//Menu Item for Benefits Overview
		MenuItem bnfOverview = new MenuItem();		
		bnfOverview.setTitle("Benefits Overview");
		bnfOverview.setLink("#");
		
		
		//Menu Item for Provider Enrollment
		MenuItem prvdrEnroll = new MenuItem();		
		prvdrEnroll.setTitle("Provider Enrollment");
		prvdrEnroll.setLink("#");
		
		//Menu Item for Documents and Forms
		MenuItem docsandforms = new MenuItem();		
		docsandforms.setTitle("Documents and Forms");
		docsandforms.setLink("#");
        
		//Menu Item for Report Fraud and Abuse
		MenuItem rptfraud = new MenuItem();		
		rptfraud.setTitle("Report Fraud and Abuse");
		rptfraud.setLink("#");
		
		//Menu Item for Department of Health and Human Services
		MenuItem healthAndHumanService = new MenuItem();		
		healthAndHumanService.setTitle("Department of Health and Human Services");
		healthAndHumanService.setLink("#");		
                
	
        
		listOfMenu.add(infoAZ);
		listOfMenu.add(fndPrvdr);		
		listOfMenu.add(bnfOverview);
		listOfMenu.add(prvdrEnroll);
		listOfMenu.add(docsandforms);
		listOfMenu.add(rptfraud);
		listOfMenu.add(healthAndHumanService);
		
		return listOfMenu;		
		
	}
	
	
}
