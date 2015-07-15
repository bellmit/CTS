package com.xerox.ghs.mmis.provider.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.acs.enterprise.mmis.operations.providerclaims.c837.vo.ProviderIdVO;
import com.acs.enterprise.mmis.operations.providerclaims.c837.vo.ProviderInfoVO;
import com.acs.enterprise.mmis.operations.providerclaims.c837.vo.ProviderWebClaimVO;
import com.acs.enterprise.mmis.operations.providerclaims.c837.vo.SubscriberVO;
import com.acs.enterprise.mmis.operations.providerclaims.common.delegate.ProviderClaimsDelegate;
import com.acs.enterprise.mmis.operations.providerclaims.process.exception.ProviderClaimProcessException;

import com.xerox.ghs.mmis.provider.model.DentalClaimVO;
import com.xerox.ghs.mmis.provider.util.ProviderConstants;

/**
 * 
 * Handles request regarding to claims
 *
 */
@Controller
public class ClaimsController {
	
	/**
	 * This method is used to create a dental claim
	 * @param DentalClaimVO dentalClaimVO
	 * @return ModelAndView
	 * @throws ProviderClaimProcessException
	 */
	@RequestMapping(ProviderConstants.SAVE_CLAIM_PATH)
	public ModelAndView createDentalClaim(DentalClaimVO dentalClaimVO) throws ProviderClaimProcessException{
		String transactionID = null;
		String successMsg = null;
		
		ProviderWebClaimVO provWebClaimVO = new ProviderWebClaimVO();
		ProviderClaimsDelegate processDelegate = new ProviderClaimsDelegate();
		ProviderIdVO provIdVO = new ProviderIdVO();
		ProviderInfoVO provInfo = new ProviderInfoVO();
		SubscriberVO memInfo = new SubscriberVO();
		
		provIdVO.setMedicaidProvID(dentalClaimVO.getMedicalProviderID());
		provInfo.setProviderIdInfo(provIdVO);
		memInfo.setSubscriberId("38903150702");
		memInfo.setLastName(dentalClaimVO.getLastName());
		provWebClaimVO.setSubmitterID("PROVUSER01");
		provWebClaimVO.setBillingProviderInfo(provInfo);
		provWebClaimVO.setMemberInfo(memInfo);
		provWebClaimVO.getClaimData().setSeviceFromDate(dentalClaimVO.getServiceDate());
		
		
		transactionID = processDelegate.saveDentalClaim(provWebClaimVO);
		System.out.println("transactionID:"+transactionID);
		if(transactionID != null){
			successMsg = ProviderConstants.CLAIM_SUCCESS_MSG+transactionID;
		}
		ModelAndView dentalClaimView = new ModelAndView(ProviderConstants.CREATE_DENTAL_CLAIM_VIEW);
		dentalClaimView.addObject("message",successMsg);
		
		return  dentalClaimView ;
		
	}
	

}
