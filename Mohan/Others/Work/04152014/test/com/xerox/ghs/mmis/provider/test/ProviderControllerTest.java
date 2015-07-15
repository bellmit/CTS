package com.xerox.ghs.mmis.provider.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.acs.enterprise.mmis.provider.common.domain.Provider;
/*import com.acs.enterprise.mmis.provider.common.vo.ProviderApplicationResponseVO;*/
import com.xerox.ghs.mmis.provider.controller.ClaimsController;
import com.xerox.ghs.mmis.provider.controller.ProviderController;
import com.xerox.ghs.mmis.provider.model.DentalClaimVO;
import com.xerox.ghs.mmis.provider.model.MemberSearch;

/**
 * 
 * Test Class for ProviderController
 *
 */
public class ProviderControllerTest {
	
	ProviderController prvCntlr = new ProviderController();
	MemberSearch memSearch = new MemberSearch();
	ClaimsController clmsCntrl = new ClaimsController();

	
	/**
	 * Tests the provider landing view
	 */
	@Test
	public void testProviderLandingPage() {
		ModelAndView mv = prvCntlr.providerLandingPage();
		assertEquals("providerLandingPage", mv.getViewName());
	}

	/**
	 * Tests the memberEligibility view
	 */
	@Test
	public void testMemberEligibility() {
		ModelAndView mv = prvCntlr.memberEligibility();
		assertEquals("memberEligibility", mv.getViewName());
	}

	/**
	 * Tests the search results for member eligibility
	 * @throws Exception
	 */
	@Test
	public void testSearchMemberForEligibility() throws Exception {
		List responseList = prvCntlr.searchMemberForEligibility(memSearch);
		assertNotNull(responseList);
	}

	@Test
	public void testCreateDentalClaim() throws Exception{
		DentalClaimVO dentalClaimVO = new DentalClaimVO();
		dentalClaimVO.setLastName("test123");
		dentalClaimVO.setMedicalProviderID("100059");
		dentalClaimVO.setStrServiceDate("04/01/2014");
		ModelAndView mv = clmsCntrl.createDentalClaim(dentalClaimVO);
		assertNotNull("createDentalClaim",mv.getViewName());
	}
	
	/*@Test
	public void testEnrollProvider() throws Exception
	{
		ProviderController providerController = new ProviderController();
		Provider provider = new Provider();
		provider.setPhoneticLastName("testLast");
		provider.setPhoneticFirstName("testFirst");
		provider.setDateOfBirth(new Date("05/06/1988"));
		provider.setIndividualGroupCode("I");
		provider.setBirthCountryCode("USA");
		
		
		ProviderApplicationResponseVO providerAppResVO = providerController.enrollProvider(provider);
		assertNotNull(providerAppResVO);
	}*/
}
