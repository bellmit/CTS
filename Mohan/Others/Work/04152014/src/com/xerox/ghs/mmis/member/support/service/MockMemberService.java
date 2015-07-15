package com.xerox.ghs.mmis.member.support.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.xerox.ghs.mmis.enterprise.common.security.common.model.User;
import com.xerox.ghs.mmis.member.support.bean.ClaimDetailsVO;
import com.xerox.ghs.mmis.member.support.bean.MemberClaim;
import com.xerox.ghs.mmis.member.support.bean.SearchClaimVO;
import com.xerox.ghs.mmis.reference.model.ReferenceServiceVO;

/**
 * 
 * This class contains the mock services
 * 
 */
public class MockMemberService {

	private final static Logger LOGGER = Logger
			.getLogger(MockMemberService.class.getName());

	private MockMemberService() {

	}

	public static MockMemberService getInstance() {
		MockMemberService mockService = null;
		if (mockService == null) {
			mockService = new MockMemberService();
		}
		return mockService;

	}

	public List<MemberClaim> getMemberSearchResult() {

		List<MemberClaim> listOfClaims = new ArrayList<MemberClaim>();

		MemberClaim memberInfoServices = new MemberClaim();
		memberInfoServices.setFromDate("03/03/2014");
		memberInfoServices.setToDate("04/04/2014");
		memberInfoServices.setProviderName("Mr.Wealth");
		memberInfoServices.setClaimType("Member");

		MemberClaim mc1 = new MemberClaim();
		mc1.setFromDate("09/02/2013");
		mc1.setToDate("11/05/2013");
		mc1.setProviderName("Xerox 1");
		mc1.setClaimType("Member");

		MemberClaim mc2 = new MemberClaim();
		mc2.setFromDate("09/02/2013");
		mc2.setToDate("11/05/2013");
		mc2.setProviderName("Xerox 1");
		mc2.setClaimType("Professional");

		listOfClaims.add(memberInfoServices);
		listOfClaims.add(mc1);
		listOfClaims.add(mc2);

		return listOfClaims;
	}

	public ClaimDetailsVO getClaims(SearchClaimVO searchClaimVO) {
		ClaimDetailsVO claimDetailsVO = new ClaimDetailsVO();
		claimDetailsVO.setBeginDate(searchClaimVO.getBeginDate());
		claimDetailsVO.setEndDate(searchClaimVO.getEndDate());
		claimDetailsVO.setClaimNumber("9876543421");
		claimDetailsVO.setClaimPaidOn("07/12/2012");
		claimDetailsVO.setMemberName("Rich Kowalske");
		claimDetailsVO.setProvider("Publix #4237");
		if (searchClaimVO.getClaimStatus() != null) {
			claimDetailsVO.setStatus(searchClaimVO.getClaimStatus());
		} else {
			claimDetailsVO.setStatus("Completed");
		}
		claimDetailsVO.setSubmittedCharges("$150.00");
		return claimDetailsVO;
	}

	public User getUser(User user) {

		LOGGER.info("Data coming in :" + user.getUserId());

		// User user = new User();

		String userId = user.getUserId();

		if (userId.toLowerCase().trim().startsWith("pr")) {
			user.setHierarchyLOB("provider");
		} else {
			user.setHierarchyLOB("member");
		}

		user.setFirstName("Test First name");
		user.setLastName("Test Last name");

		return user;
	}

	public List getReferenceData(String key) {
		List referenceData = null;

		try {

			if (referenceData == null) {
				referenceData = getClaimTypeData();
			}
		} catch (Exception e) {
			LOGGER.info("Exception" + e.getMessage());
			referenceData = getClaimTypeData();

		}

		return referenceData;
	}

	public List getClaimTypeData() {

		List referenceData = new ArrayList();
		ReferenceServiceVO referenceServiceVO1 = new ReferenceServiceVO();
		referenceServiceVO1.setValidValueCode("D");
		referenceServiceVO1.setShortDescription("Dental");

		ReferenceServiceVO referenceServiceVO2 = new ReferenceServiceVO();
		referenceServiceVO2.setValidValueCode("P");
		referenceServiceVO2.setShortDescription("Pharmacy");

		ReferenceServiceVO referenceServiceVO3 = new ReferenceServiceVO();
		referenceServiceVO3.setValidValueCode("M");
		referenceServiceVO3.setShortDescription("Medical");

		referenceData.add(referenceServiceVO1);
		referenceData.add(referenceServiceVO2);
		referenceData.add(referenceServiceVO3);

		return referenceData;

	}

}
