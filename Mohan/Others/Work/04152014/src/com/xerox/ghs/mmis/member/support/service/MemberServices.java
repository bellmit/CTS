package com.xerox.ghs.mmis.member.support.service;

import java.util.logging.Logger;

import com.acs.enterprise.mmis.member.common.application.exception.MemberSupportBusinessException;
import com.acs.enterprise.mmis.member.common.vo.MemberCommonDetailVO;
import com.acs.enterprise.mmis.member.support.common.delegate.MemberSupportDelegate;
import com.acs.enterprise.mmis.member.support.selfservices.view.vo.MemberClaimsSearchCriteriaVO;
import com.xerox.ghs.mmis.member.support.bean.SearchClaimVO;

/**
 * This class contains the member services
 * 
 *
 */

public class MemberServices {
	
	private final static Logger LOGGER = Logger
			.getLogger(MemberServices.class.getName());

	private MemberServices() {

	}

	public static MemberServices getInstance() {
		MemberServices memberService = null;
		if (memberService == null) {
			memberService = new MemberServices();
		}
		return memberService;

	}
	
	public MemberCommonDetailVO getClaims(SearchClaimVO searchClaimVO) throws MemberSupportBusinessException{
		LOGGER.info("MemberServices:getClaims():start");
		MemberSupportDelegate memSupportDelegate = new MemberSupportDelegate();
		MemberClaimsSearchCriteriaVO memberSearch = new MemberClaimsSearchCriteriaVO();
		MemberCommonDetailVO commonDetailVO = null;
		memberSearch.setSysID((long)0);
		memberSearch.setClaimType(searchClaimVO.getClaimType());
		memberSearch.setProviderLastName(searchClaimVO.getLastName());
		memberSearch.setProviderFirstName(searchClaimVO.getFirstName());
		memberSearch.setBeginDate(searchClaimVO.getBeginDate());
		memberSearch.setEndDate(searchClaimVO.getEndDate());
		memberSearch.setRowsPerPage(10);
    	memberSearch.setStartIndex(0);
    	memberSearch.setSortColumn("beginDate");
    	commonDetailVO = memSupportDelegate.getClaimsForSearchCriteria(memberSearch);
    	LOGGER.info("MemberServices:getClaims():END");
		return commonDetailVO;
		
	}

}
