package com.xerox.ghs.mmis.enterprise.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.xerox.ghs.mmis.member.support.bean.MemberClaim;
import com.xerox.ghs.mmis.member.support.service.MockMemberService;
import com.xerox.ghs.mmis.member.support.util.MemberConstants;

/**
 * Handles requests for the application home page.
 */

@Controller
public class MemberLandingController {
	
	/**
	 * This method is used to get all the claim details of the member who has logged in
	 * @return  Map<String, List<MemberClaim>>
	 */
	@RequestMapping(MemberConstants.MEM_LDNG_PG_CTLR_PATH)
	@ResponseBody
	public Map<String, List<MemberClaim>> searchClaimsAll() {
		Map<String, List<MemberClaim>> myMap = new LinkedHashMap<String, List<MemberClaim>>();
		List<MemberClaim> myList = MockMemberService.getInstance().getMemberSearchResult();
		myMap.put(MemberConstants.RECENT_CLAIMS, myList);		
		return myMap;
	}

}
