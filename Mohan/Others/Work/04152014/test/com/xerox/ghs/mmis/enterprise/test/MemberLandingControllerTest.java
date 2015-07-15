package com.xerox.ghs.mmis.enterprise.test;

import java.util.Map;

import org.junit.Test;

import com.xerox.ghs.mmis.enterprise.controller.MemberLandingController;

/**
 * 
 * Test class for MemberLandingController
 *
 */

public class MemberLandingControllerTest {
	
	MemberLandingController memLndngCntrl = new MemberLandingController();

	/**
	 * Tests the recent claims
	 */
	@Test
	public void testSearchClaimsAll() {
		
		Map response  = memLndngCntrl.searchClaimsAll();
		assert(response.containsKey("RecentClaims"));
		
	}

}
