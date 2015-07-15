/**
 * Copyright (c) 2005 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.acs.enterprise.common.base.common.vo.EnterpriseSearchResultsVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CaseRecordSearchResultsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.GlobalCaseSearchResultVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.mmis.operations.tpladministration.common.vo.TPLGlobalSearchResultsVO;

/**
 * This class will be the DO to VO Converter for Maintain Case Type
 * 
 * @author Wipro
 */
public class GlobalCaseSearchConvertor

{
	/** Enterprise Logger for Logging. */
	static final EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(GlobalCaseSearchConvertor.class.getName());

	/**
	 * Constructor fro CasetyoedoConvertor
	 */
	public GlobalCaseSearchConvertor() {
		super();
		logger.debug("Inside CasetypeDo Convertor Class");
	}

	/**
	 * This method converts CaseRecordSearchResultsVO to GlobalCaseSearchResultVO
	 * @param caseRecordSearchResultsVO
	 *    Takes caseRecordSearchResultsVO as param
	 * @return GlobalCaseSearchResultVO
	 */
	public GlobalCaseSearchResultVO convertCaseVOtoCommonVO(
			CaseRecordSearchResultsVO caseRecordSearchResultsVO, Map userSkNameMap, List deptList) {
		GlobalCaseSearchResultVO globalCaseVO = new GlobalCaseSearchResultVO();
		String entityName = null;
		if (caseRecordSearchResultsVO.getEntityName() != null
				&& !caseRecordSearchResultsVO.getEntityName().equals("")) {
			entityName = caseRecordSearchResultsVO.getEntityName();
			globalCaseVO.setEntityName(entityName);
		} else {
			if (caseRecordSearchResultsVO.getEntityFirstName() != null
					&& !caseRecordSearchResultsVO.getEntityFirstName().equals(
							"")) {
				entityName = caseRecordSearchResultsVO.getEntityFirstName();
			}
			if (caseRecordSearchResultsVO.getEntityMidName() != null
					&& !caseRecordSearchResultsVO.getEntityMidName().equals("")) {
				entityName = entityName + " "
						+ caseRecordSearchResultsVO.getEntityMidName();
			}
			if (caseRecordSearchResultsVO.getEntityLastName() != null
					&& !caseRecordSearchResultsVO.getEntityLastName()
							.equals("")) {
				entityName = entityName + " "
						+ caseRecordSearchResultsVO.getEntityLastName();
			}

			globalCaseVO.setEntityName(entityName);

		}

		globalCaseVO.setType("Contact Management");

		if (caseRecordSearchResultsVO.getAssignedTo() != null) {
			globalCaseVO.setAssignedTo(caseRecordSearchResultsVO
					.getAssignedTo());
		}
		if (caseRecordSearchResultsVO.getCaseSK() != null) {
			globalCaseVO.setCaseSK(caseRecordSearchResultsVO.getCaseSK()
					.toString());
		}
		if (caseRecordSearchResultsVO.getCreatedDate() != null) {
			String crDate = ContactManagementHelper
					.dateConverter(caseRecordSearchResultsVO.getCreatedDate());
			globalCaseVO.setCreatedDate(crDate);
		}
		/* FIND BUGS-FIX */
		if (caseRecordSearchResultsVO.getCommonEntitySK() != null
				&& !caseRecordSearchResultsVO.getCommonEntitySK().equals(Long.valueOf(0))) {
			globalCaseVO.setEntityID(caseRecordSearchResultsVO
					.getCommonEntitySK().toString());
		}
		if (caseRecordSearchResultsVO.getCaseType() != null
				&& !caseRecordSearchResultsVO.getCaseType().equals("")) {
			globalCaseVO.setCaseType(caseRecordSearchResultsVO.getCaseType());
		}
		
		if(caseRecordSearchResultsVO.getAssignedTo()!=null && !caseRecordSearchResultsVO.getAssignedTo().equals("")){
			if(deptList.contains(caseRecordSearchResultsVO.getAssignedTo())){
				globalCaseVO.setAssignedTo(caseRecordSearchResultsVO.getAssignedTo());
			}else{
				globalCaseVO.setAssignedTo((String) userSkNameMap
						.get(caseRecordSearchResultsVO.getAssignedToUser()));
			}
		}
		globalCaseVO.setParamNameForIPC("send.Global.Case.Search.Results");
		globalCaseVO.setParamValueForIPC("sendGlobalCaseSearchResults");
		return globalCaseVO;
	}

	/**
	 * This method converts TPlRecoverySearchResultVo to GlobalCaseSearchResultVo
	 * @param tplSearchResultsVO
	 *   Takes tplSearchResultsVO as param
	 * @return GlobalCaseSearchResultVO
	 */
	public GlobalCaseSearchResultVO convertTplRecVOtoCommonVO(
			TPLGlobalSearchResultsVO tplSearchResultsVO, Map userIDNameMap) {
		GlobalCaseSearchResultVO globalTplRecVO = new GlobalCaseSearchResultVO();
		String entityName = null;
		if (tplSearchResultsVO.getEntityName() != null
				&& !tplSearchResultsVO.getEntityName().equals("")) {
			entityName = tplSearchResultsVO.getEntityName();
			globalTplRecVO.setEntityName(entityName);
		} else {
			if (tplSearchResultsVO.getEntityFirstName() != null
					&& !tplSearchResultsVO.getEntityFirstName().equals("")) {
				entityName = tplSearchResultsVO.getEntityFirstName();

			}
			if (tplSearchResultsVO.getEntityMidName() != null
					&& !tplSearchResultsVO.getEntityMidName().equals("")) {
				entityName = entityName + " "
						+ tplSearchResultsVO.getEntityMidName();
			}
			if (tplSearchResultsVO.getEntityLastName() != null
					&& !tplSearchResultsVO.getEntityLastName().equals("")) {
				entityName = entityName + " "
						+ tplSearchResultsVO.getEntityLastName();
			}
			globalTplRecVO.setEntityName(entityName);
		}
		globalTplRecVO.setType("TPL Recovery");
		globalTplRecVO.setEntityTypeDesc("TPL");
		globalTplRecVO.setEntityType("TPL");
		logger.debug("++tplSearchResultsVO.getCaseSK()::"+tplSearchResultsVO.getCaseSK());
		globalTplRecVO.setCaseSK(tplSearchResultsVO.getCaseSK());
		logger.debug("++userIDNameMap::"+userIDNameMap);
		logger.debug("++tplSearchResultsVO.getAssignedTo():::::::::::" + tplSearchResultsVO.getAssignedTo());
		if (tplSearchResultsVO.getAssignedTo() != null) {
			if (userIDNameMap != null) {
				globalTplRecVO.setAssignedTo((String) userIDNameMap
						.get(tplSearchResultsVO.getAssignedTo()));
				logger.debug("------------"+(String) userIDNameMap
						.get(tplSearchResultsVO.getAssignedTo()));
			}
		}
		
		logger.debug("===========tplSearchResultsVO.getCreatedDate()::"+tplSearchResultsVO.getCreatedDate());
		if (tplSearchResultsVO.getCreatedDate() != null) {
			String crDate = ContactManagementHelper
					.dateConverter(tplSearchResultsVO.getCreatedDate());
			globalTplRecVO.setCreatedDate(crDate);
		}
		globalTplRecVO.setParamNameForIPC("ACTION_NAME");
		globalTplRecVO.setParamValueForIPC("sourceAction1");
	//	System.out.println("tplSearchResultsVO.getCaseType()::::::::"+ tplSearchResultsVO.getCaseType());
		/*if (tplSearchResultsVO.getCaseType() != null
				&& !tplSearchResultsVO.getCaseType().equals("")) {
			globalTplRecVO.setCaseType(tplSearchResultsVO.getCaseType());
		}*/
		return globalTplRecVO;

	}

	/**
	 * @param tplSearchResultsVO
	 *   Takes tplSearchResultsVO as param
	 * @return GlobalCaseSearchResultVO
	 */
	public GlobalCaseSearchResultVO convertTplHippVOtoCommonVO(
			TPLGlobalSearchResultsVO tplSearchResultsVO, Map userIDNameMap) {
		GlobalCaseSearchResultVO globalTplHippVO = new GlobalCaseSearchResultVO();
		
		String entityName = null;
		if (tplSearchResultsVO.getEntityName() != null
				&& !tplSearchResultsVO.getEntityName().equals("")) {
			entityName = tplSearchResultsVO.getEntityName();
			globalTplHippVO.setEntityName(entityName);
		} else {
			if (tplSearchResultsVO.getEntityFirstName() != null
					&& !tplSearchResultsVO.getEntityFirstName().equals("")) {
				entityName = tplSearchResultsVO.getEntityFirstName();
			}
			if (tplSearchResultsVO.getEntityMidName() != null
					&& !tplSearchResultsVO.getEntityMidName().equals("")) {
				entityName = entityName + " "
						+ tplSearchResultsVO.getEntityMidName();
			}
			if (tplSearchResultsVO.getEntityLastName() != null
					&& !tplSearchResultsVO.getEntityLastName().equals("")) {
				entityName = entityName + " "
						+ tplSearchResultsVO.getEntityLastName().trim();
			}
			globalTplHippVO.setEntityName(entityName);
		}
		globalTplHippVO.setType("TPL HIPP");
		globalTplHippVO.setEntityType("TPL HIPP");
		globalTplHippVO.setEntityTypeDesc("TPL HIPP");
		logger.debug("-----Hipp Case sk--"+tplSearchResultsVO.getCaseSK());
		globalTplHippVO.setCaseSK(tplSearchResultsVO.getCaseSK());
		logger.debug("++ globalTplHippVO.getAssignedTo() ::::::::::::::" + tplSearchResultsVO.getAssignedTo());
		logger.debug("++userIDNameMap::"+userIDNameMap);
		if (tplSearchResultsVO.getAssignedTo() != null) {
			if (userIDNameMap != null) {
				globalTplHippVO.setAssignedTo((String) userIDNameMap
						.get(tplSearchResultsVO.getAssignedTo()));
			}
		}
		logger.debug(" tplSearchResultsVO.getCreatedDate(::::::::) " + tplSearchResultsVO.getCreatedDate());
		
		if (tplSearchResultsVO.getCreatedDate() != null) {
			String crDate = ContactManagementHelper
					.dateConverter(tplSearchResultsVO.getCreatedDate());
			
			logger.debug("tplSearchResultsVO.getCreatedDate():::::::::;" + crDate );
			globalTplHippVO.setCreatedDate(crDate);
		}
		
		logger.debug("tplSearchResultsVO.getCaseType():::::::"+tplSearchResultsVO.getCaseType());
		if (tplSearchResultsVO.getCaseType() != null
				&& !tplSearchResultsVO.getCaseType().equals("")) {
			
			globalTplHippVO.setCaseType(tplSearchResultsVO.getCaseType());
		}
		else{
				
			globalTplHippVO.setCaseType("HIPP");
		}
		globalTplHippVO.setParamNameForIPC("ACTION_NAME");
		globalTplHippVO.setParamValueForIPC("SourceAction");
		return globalTplHippVO;

	}
}