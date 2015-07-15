package com.xerox.ghs.mmis.provider.service;

import java.util.ArrayList;
import java.util.List;

import com.xerox.ghs.mmis.member.support.bean.Member;

/**
 * 
 * This class contains the mock services for Provider
 * 
 */

public class MockProviderDataService {
	
	private MockProviderDataService() {

	}

	public static MockProviderDataService getInstance() {
		MockProviderDataService mockService = null;
		if (mockService == null) {
			mockService = new MockProviderDataService();
		}
		return mockService;

	}
	
	public List<Member> getMemberEligibilityList(){
		
		Member m1 = new Member();
		m1.setFirstName("Williams");
		m1.setLastName("Dave");
		m1.setGender("Male");
		m1.setMemberId("546868646");
		m1.setServiceFrom("11/01/2010");
		m1.setServiceTo("11/01/2010");
		m1.setDob("04/11/1970");
		
		Member m2 = new Member();
		m2.setFirstName("Williams 2");
		m2.setLastName("Dave 2");
		m2.setGender("Male");
		m2.setMemberId("3423423423");
		m2.setServiceFrom("11/01/2010");
		m2.setServiceTo("11/01/2010");
		m2.setDob("04/11/1970");
		
		Member m3 = new Member();
		m3.setFirstName("Williams3");
		m3.setLastName("Dave 3");
		m3.setGender("Male");
		m3.setMemberId("23423423");
		m3.setServiceFrom("11/01/2010");
		m3.setServiceTo("11/01/2010");
		m3.setDob("04/11/1970");
		
		Member m4 = new Member();
		m4.setFirstName("Williams4");
		m4.setLastName("Dave 4");
		m4.setGender("Male");
		m4.setMemberId("434534534");
		m4.setServiceFrom("11/01/2010");
		m4.setServiceTo("11/01/2010");
		m4.setDob("04/11/1970");
		
		Member m5 = new Member();
		m5.setFirstName("Williams 5");
		m5.setLastName("Dave 5");
		m5.setGender("Male");
		m5.setMemberId("2342342323411");
		m5.setServiceFrom("11/01/2010");
		m5.setServiceTo("11/01/2010");
		m5.setDob("04/11/1970");
			
		Member m6 = new Member();
		m6.setFirstName("Williams 6");
		m6.setLastName("Dave 6");
		m6.setGender("Male");
		m6.setMemberId("1223112");
		m6.setServiceFrom("11/01/2010");
		m6.setServiceTo("11/01/2010");
		m6.setDob("04/11/1970");
		
		List<Member> membersList = new ArrayList<Member>();	
		
		membersList.add(m1);
		membersList.add(m2);
		membersList.add(m3);
		membersList.add(m4);
		membersList.add(m5);
		membersList.add(m6);
		
		return membersList;
	}

}
