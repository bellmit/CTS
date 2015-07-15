package com.xerox.ghs.mmis.member.support.test;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.acs.enterprise.mmis.member.common.application.exception.MemberSupportBusinessException;
import com.xerox.ghs.mmis.member.support.bean.SearchClaimVO;
import com.xerox.ghs.mmis.member.support.controller.DisplayClaimsController;

/**
 * 
 * Test Class for DisplayClaimsController
 *
 */

public class DisplayClaimsControllerTest {
	
	DisplayClaimsController displayClaims = new DisplayClaimsController();
	SearchClaimVO searchClaimVO = new SearchClaimVO();
	
	/**
	 * Set up method
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		
		searchClaimVO.setBeginDate(new Date("04/07/2014"));
		searchClaimVO.setEndDate(new Date("04/14/2014"));
		searchClaimVO.setClaimStatus("Paid");
		
	}

	/**
	 * Tests the claim details
	 * @throws NamingException 
	 * @throws CreateException 
	 * @throws RemoteException 
	 * @throws MemberSupportBusinessException 
	 */
	/*@Test
	public void testGetClaims() throws MemberSupportBusinessException, RemoteException, CreateException, NamingException {
		
		ModelAndView mv = displayClaims.getClaims(searchClaimVO);
		assertEquals("claimDetails", mv.getViewName());
		
	}*/

	/**
	 * Tests the reference data for claim type
	 */
	@Test
	public void testGetReferenceData() {
		Map responseMap = displayClaims.getReferenceData();
		assert(responseMap.containsKey("claimTypeList"));
	}

	/** 
	 * Test the display claims view
	 */
	@Test
	public void testDisplayClaims() {
		ModelAndView mv = displayClaims.displayClaims();
		assertEquals("displayClaims", mv.getViewName());
	}

	/**
	 * Tests the member landing view
	 */
	@Test
	public void testMemberLanding() {
		ModelAndView mv = displayClaims.memberLanding();
		assertEquals("memberLandingPage", mv.getViewName());
	}

}
