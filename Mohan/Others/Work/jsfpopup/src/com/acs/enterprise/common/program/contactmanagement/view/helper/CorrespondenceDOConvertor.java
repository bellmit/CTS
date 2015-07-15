/*
 * Created on Oct 22, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acs.enterprise.common.base.application.dataaccess.exception.EnterpriseBaseDataException;
import com.acs.enterprise.common.base.application.exception.EnterpriseBaseBusinessException;
import com.acs.enterprise.common.base.common.domain.Attachment;
import com.acs.enterprise.common.base.common.domain.EnterpriseBaseDomain;
import com.acs.enterprise.common.base.common.domain.Name;
import com.acs.enterprise.common.base.view.helper.DataTransferObjectConverter;
import com.acs.enterprise.common.cots.lettergeneration.common.domain.LetterGenerationRequest;
import com.acs.enterprise.common.internalmessage.common.domain.WorkUnit;
import com.acs.enterprise.common.lineitemaudit.application.exception.LineItemAuditsBusinessException;
import com.acs.enterprise.common.lineitemaudit.common.delegate.LineItemAuditsDelegate;
import com.acs.enterprise.common.lineitemaudit.common.vo.AuditaleEnterpriseBaseVO;
import com.acs.enterprise.common.program.administration.application.exception.ReferenceServiceRequestException;
import com.acs.enterprise.common.program.administration.application.exception.SystemListNotFoundException;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.vo.InputCriteria;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataListVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataSearchVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.benefitadministration.common.domain.LineOfBusiness;
import com.acs.enterprise.common.program.commonentities.application.exception.CommonEntityNotFoundException;
import com.acs.enterprise.common.program.commonentities.common.delegate.CommonEntityDelegate;
import com.acs.enterprise.common.program.commonentities.common.domain.Address;
import com.acs.enterprise.common.program.commonentities.common.domain.AddressUsage;
import com.acs.enterprise.common.program.commonentities.common.domain.CommonEntity;
import com.acs.enterprise.common.program.commonentities.common.domain.EAddressUsage;
import com.acs.enterprise.common.program.commonentities.common.domain.FinancialInformation;
import com.acs.enterprise.common.program.commonentities.common.domain.Representative;
import com.acs.enterprise.common.program.commonentities.common.domain.SpecificEntity;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonEntityDataBean;
import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonContactVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonEntityVO;
import com.acs.enterprise.common.program.commonentities.view.vo.NameVO;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CMEntityFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.CorrespondenceRecordFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CMEntityDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.CaseDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Alert;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CRCommonEntityCrossRef;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Correspondence;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceAttachmentXref;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceCSReferred;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceCategory;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceClientServices;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceLetterRequest;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceLink;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceRouting;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CorrespondenceWatchList;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomField;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldScope;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldTable;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldValue;
import com.acs.enterprise.common.program.contactmanagement.common.domain.Department;
import com.acs.enterprise.common.program.contactmanagement.common.domain.DepartmentUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.common.domain.LineOfBusinessHierarchy;
import com.acs.enterprise.common.program.contactmanagement.common.vo.AlertVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.CMRoutingVO;
import com.acs.enterprise.common.program.contactmanagement.common.vo.PriorCorrespondence;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.AlertDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.AttachmentDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CorrespondenceControllerBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CorrespondenceDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.RoutingDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.bean.SearchCorrespondenceDataBean;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AlternateIdentifiersVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AssociatedCorrespondenceVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.AttachmentVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceAttachmentXrefVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceDetailsVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceForMemberVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceForProviderVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceForTradingPartnerVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceForVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceLetterResponsesXrefVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CorrespondenceRecordVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldValueVO;
import com.acs.enterprise.common.security.services.userprofile.EnterpriseUserProfile;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.mmis.member.common.application.exception.MemberBusinessException;
import com.acs.enterprise.mmis.member.common.domain.AlternateMemberID;
import com.acs.enterprise.mmis.member.common.domain.CaseOwner;
import com.acs.enterprise.mmis.member.common.domain.DemographicInformation;
import com.acs.enterprise.mmis.member.common.domain.EligibilitySpan;
import com.acs.enterprise.mmis.member.common.domain.Member;
import com.acs.enterprise.mmis.member.common.domain.PreviousName;
import com.acs.enterprise.mmis.member.common.vo.MemberBasicInfo;
import com.acs.enterprise.mmis.member.common.vo.MemberInformationRequestVO;
import com.acs.enterprise.mmis.member.information.common.delegate.MemberInformationDelegate;
import com.acs.enterprise.mmis.operations.tpladministration.application.exception.TPLCarrierBusinessException;
import com.acs.enterprise.mmis.operations.tpladministration.common.delegate.TPLCarrierDelegate;
import com.acs.enterprise.mmis.operations.tpladministration.common.delegate.TPLPolicyDelegate;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLCarrier;
import com.acs.enterprise.mmis.operations.tpladministration.common.domain.TPLPolicyHolder;
import com.acs.enterprise.mmis.operations.tpladministration.common.vo.TPLCarrierNoteVO;
import com.acs.enterprise.mmis.operations.tpladministration.common.vo.TPLPolicyHolderDetailsVO;
import com.acs.enterprise.mmis.provider.common.delegate.ProviderInformationDelegate;
import com.acs.enterprise.mmis.provider.common.domain.AlternateIdInfo;
import com.acs.enterprise.mmis.provider.common.domain.BoardCertifiedSpeciality;
import com.acs.enterprise.mmis.provider.common.domain.EnrollmentStatus;
import com.acs.enterprise.mmis.provider.common.domain.Provider;
import com.acs.enterprise.mmis.provider.common.domain.ProviderType;
import com.acs.enterprise.mmis.provider.common.domain.TradingPartner;
import com.acs.enterprise.mmis.provider.common.vo.ProviderBasicInfo;
import com.acs.enterprise.mmis.provider.common.vo.ProviderInformationRequestVO;
import com.acs.enterprise.mmis.provider.common.vo.RepresentativeVO;
import com.acs.enterprise.mmis.provider.enrollment.common.delegate.ProviderEnrollmentDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.vo.DeptUserBasicInfo;

/**
 * @author Wipro TODO To change the template for this generated type comment go
 *         to Window - Preferences - Java - Code Style - Code Templates
 */
public class CorrespondenceDOConvertor extends DataTransferObjectConverter {

	/**
	 * Constructor for CorrespondenceDOConvertor
	 */
	public CorrespondenceDOConvertor() {
		super();
		
	}

	/** Enterprise Logger for Logging. */
	private transient EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(CorrespondenceDOConvertor.class);

	/**
	 * @param correspondenceRecordVO
	 *            The correspondenceRecordVO to set.
	 * @return Correspondence
	 */
	public Correspondence convertCorrespondenceVOToDO(
			CorrespondenceRecordVO correspondenceRecordVO) {
		

		Correspondence correspondence = new Correspondence();
		DateFormat dateFormat = new SimpleDateFormat(
				ContactManagementConstants.DATE_FORMAT);

		if (correspondenceRecordVO.getAuditUserID() != null) {
			correspondence.setAuditUserID(correspondenceRecordVO
					.getAuditUserID());
		} else {
			correspondence.setAuditUserID(getLoggedInUserID());
		}
		correspondence.setAddedAuditUserID(getLoggedInUserID());
		correspondence.setVersionNo(correspondenceRecordVO.getVersionNo());

		if (!isNullOrEmptyField(correspondenceRecordVO
				.getCorrespondenceRecordNumber())) {
			correspondence.setCorrespondenceSK(Long
					.valueOf(correspondenceRecordVO
							.getCorrespondenceRecordNumber()));
		}
    
		
		if (correspondenceRecordVO.getDaysToClose()!=null
				&& !correspondenceRecordVO.getDaysToClose().equalsIgnoreCase("null"))  {
			correspondence.setDaysToCloseNumber(Integer
					.valueOf(correspondenceRecordVO.getDaysToClose()));
		}

		correspondence.setEdmsDocTypeCode(correspondenceRecordVO
				.getEdmsDocTypeCode());

		if (!isNullOrEmptyField(correspondenceRecordVO
				.getSprvsrReviewedWorkUnit())) {
			WorkUnit supervisorWorkUnit = new WorkUnit();
			supervisorWorkUnit
					.setWorkUnitSK(Long.valueOf(correspondenceRecordVO
							.getSprvsrReviewedWorkUnit()));
			correspondence.setSupervisorReviewedWorkUnit(supervisorWorkUnit);
		}

		if (correspondenceRecordVO.getCorrespondenceDetailsVO() != null
				&& correspondenceRecordVO.getCorrespondenceDetailsVO()
						.getReceivedDate() != null
				&& !correspondenceRecordVO.getCorrespondenceDetailsVO()
						.getReceivedDate().trim().equals("")) {
			try {
				correspondence
						.setReceivedDate(dateFormat
								.parse(correspondenceRecordVO
										.getCorrespondenceDetailsVO()
										.getReceivedDate()));
			} catch (ParseException e) {
				if(logger.isErrorEnabled())
				{
					logger.error(e.getMessage(), e);
				}
			}
			if(logger.isDebugEnabled())
			{
				logger.debug("ReceivedDate in VO to DO converter--->" + correspondence.getReceivedDate());
			}
		}

		if (correspondenceRecordVO.getCorrespondenceForVO() != null) {
			if(logger.isDebugEnabled())
			{
				logger.debug("getCorrespondenceForVO in VO to DO converter--->"
						+ correspondenceRecordVO.getCorrespondenceForVO());
			}
			if (!isNullOrEmptyField(correspondenceRecordVO
					.getCorrespondenceForVO().getContact())) {
				if(logger.isDebugEnabled())
				{
					logger.debug("getContact in VO to DO converter--->"
							+ correspondenceRecordVO.getCorrespondenceForVO()
									.getContact());
				}
				Representative representative = new Representative();
				representative.setRepresentativeSK(Long
						.valueOf(correspondenceRecordVO
								.getCorrespondenceForVO().getContact()));
				correspondence.setRepresentative(representative);
				if(logger.isDebugEnabled())
				{
					logger.debug("Contact set in VO to DO converter--->"
							+ correspondence.getRepresentative()
									.getRepresentativeSK());
				}
			}
		}

		if(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getAuthNumber() != null && !correspondenceRecordVO
				.getCorrespondenceDetailsVO().getAuthNumber().isEmpty())
		{
		correspondence.setAuthorizationNumber(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getAuthNumber());
		}else
		{
			correspondence.setAuthorizationNumber(null);
			
		}
		if(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getTcn() != null && !correspondenceRecordVO
				.getCorrespondenceDetailsVO().getTcn().isEmpty())
		{
		correspondence.setTransactionNumber(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getTcn());
		}
		else
		{
			correspondence.setTransactionNumber(null);
			
		}
		
		if(logger.isDebugEnabled())
		{
			logger.debug("AuthorizationNumber in VO to DO converter--->"
					+ correspondence.getAuthorizationNumber());
			logger.debug("TransactionNumber in VO to DO converter--->"
					+ correspondence.getTransactionNumber());
		}
		correspondence
				.setResponseAllClosedIndicator(Boolean
						.valueOf(correspondenceRecordVO
								.getResponseAllClosedIndicator()));
		correspondence.setResponseExistsIndicator(Boolean
				.valueOf(correspondenceRecordVO.getResponseExistIndicator()));
		correspondence.setResponseHasFileIndicator(Boolean
				.valueOf(correspondenceRecordVO.getResponseHasFileIndicator()));
		correspondence.setSensitiveDataIndicator(Boolean
				.valueOf(correspondenceRecordVO.getSensitiveDataIndicator()));

		correspondence.setSourceCode(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getSourceCode());
		if(logger.isDebugEnabled())
		{
			logger.debug("status code "
					+ correspondenceRecordVO.getCorrespondenceDetailsVO()
							.getStatusCode());
		}

		correspondence.setStatusCode(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getStatusCode());
		if(logger.isDebugEnabled())
		{
			logger.debug("subject code "
					+ correspondenceRecordVO.getCorrespondenceDetailsVO()
							.getSubjectCode());
		}

		correspondence.setSubjectCode(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getSubjectCode());
		correspondence.setResolutionCode(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getResolutionCode());
		correspondence.setLobCode(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getAnLobCode());
		correspondence.setPriorityCode(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getPriorityCode());
		correspondence.setSupervisorReviewReqIndicator(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getSprvsrRevwReqdIndicator());

		LineOfBusinessHierarchy lobHierarchy = new LineOfBusinessHierarchy();

		Map mapOfDeptAndLobHier = getCorrespondenceDataBean()
				.getMapOfDeptAndLobHier();

		

		String lobHierarchySK = (String) mapOfDeptAndLobHier
				.get(correspondenceRecordVO.getCorrespondenceDetailsVO()
						.getDepartment());
		if(logger.isDebugEnabled())
		{
			logger.debug("lobHierarchySK " + lobHierarchySK);
		}

		lobHierarchy.setLineOfBusinessHierarchySK(Long.valueOf(lobHierarchySK));
		//lobHierarchy.setLineOfBusinessHierarchySK(Long.valueOf("10004"));
		correspondence.setLineOfBusinessHierarchy(lobHierarchy);

		WorkUnit crtByWorkUnit = new WorkUnit();
		crtByWorkUnit.setWorkUnitSK(Long.valueOf(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getCreatedBySK()));
		if(logger.isDebugEnabled())
		{
			logger.debug("CreatedBySK---> " + crtByWorkUnit.getWorkUnitSK());
		}
		/*EnterpriseUser createdByUser = new EnterpriseUser();
		setUserName(correspondenceRecordVO.getCorrespondenceDetailsVO()
				.getCreatedByName(), createdByUser);
		//createdByUser.setWorkUnit(crtByWorkUnit);

		crtByWorkUnit.setEnterpriseUser(createdByUser);
		crtByWorkUnit.setWorkUnitTypeCode("U");*/

		correspondence.setCreatedByWorkUnit(crtByWorkUnit);

		WorkUnit assgnToWorkUnit = new WorkUnit();
		if (correspondenceRecordVO.getCorrespondenceDetailsVO()
				.getAssignedToWorkUnitSK() != null) {
			assgnToWorkUnit.setWorkUnitSK(Long.valueOf(correspondenceRecordVO
					.getCorrespondenceDetailsVO().getAssignedToWorkUnitSK()));
			if(logger.isDebugEnabled())
			{
				logger.debug("AssignedToWorkUnitSK---> " + assgnToWorkUnit.getWorkUnitSK());
			}
		}
		String workUnitType = getCRAssignedGroupType();
		assgnToWorkUnit.setWorkUnitTypeCode(workUnitType);

		if ("U".equalsIgnoreCase(workUnitType)
				&& correspondenceRecordVO.getCorrespondenceDetailsVO()
						.getAssignedToWorkUnitSK() != null) {
			EnterpriseUser assignedToUser = new EnterpriseUser();

			assignedToUser.setUserWorkUnitSK(new Long(Long
					.parseLong(correspondenceRecordVO
							.getCorrespondenceDetailsVO()
							.getAssignedToWorkUnitSK())));
			String userID = correspondenceRecordVO.getCorrespondenceDetailsVO().getAssignedToWorkUnitName();
			if(userID != null)
			{
				int index = userID.indexOf(ContactManagementConstants.HYPHEN_SEPARATOR);
				userID = userID.substring(index+1,userID.length());
				
			}
			assignedToUser.setUserID(userID);
			if(logger.isDebugEnabled())
			{
				logger.debug("AssignedToWorkUnitSK---> " + assignedToUser.getUserWorkUnitSK());
			}
			/*
			 * setUserName(correspondenceRecordVO.getCorrespondenceDetailsVO()
			 * .getAssignedToWorkUnitName(), assignedToUser);
			 */
			//assignedToUser.setWorkUnit(assgnToWorkUnit);
			assgnToWorkUnit.setEnterpriseUser(assignedToUser);
			assgnToWorkUnit.setWorkUnitTypeCode("U");
		} else if ("W".equalsIgnoreCase(workUnitType)
				&& correspondenceRecordVO.getCorrespondenceDetailsVO()
						.getAssignedToWorkUnitSK() != null) {
			Department assignedToDept = new Department();
			assignedToDept.setWorkUnitSK(new Long(Long
					.parseLong(correspondenceRecordVO
							.getCorrespondenceDetailsVO()
							.getAssignedToWorkUnitSK())));
			/*
			 * assignedToDept.setName(correspondenceRecordVO
			 * .getCorrespondenceDetailsVO().getAssignedToWorkUnitName());
			 */
			assgnToWorkUnit.setDepartment(assignedToDept);
			assgnToWorkUnit.setWorkUnitTypeCode("W");
		} else if ("B".equalsIgnoreCase(workUnitType)) {
			Department assignedToDept = new Department();
			assignedToDept.setName(correspondenceRecordVO
					.getCorrespondenceDetailsVO().getAssignedToWorkUnitName());
			assgnToWorkUnit.setDepartment(assignedToDept);
			assgnToWorkUnit.setWorkUnitTypeCode("B");
		}

		correspondence.setCorrespondenceAssignToWorkUnit(assgnToWorkUnit);

		if (!isNullOrEmptyField(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getCrspdOpenWorkUnit())) {
			WorkUnit crspdOpenWorkUnit = new WorkUnit();
			crspdOpenWorkUnit.setWorkUnitSK(Long.valueOf(correspondenceRecordVO
					.getCorrespondenceDetailsVO().getCrspdOpenWorkUnit()));
			correspondence.setCorrespondenceOpenWorkUnit(crspdOpenWorkUnit);
		}

		if (!isNullOrEmptyField(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getCrspdLastUpdWorkUnit())) {
			if(logger.isDebugEnabled())
			{
				logger.debug("workunit--->"
						+ correspondenceRecordVO.getCorrespondenceDetailsVO()
								.getCrspdLastUpdWorkUnit());
			}
			WorkUnit lastUpdtWorkUnit = new WorkUnit();
			lastUpdtWorkUnit.setWorkUnitSK(Long.valueOf(correspondenceRecordVO
					.getCorrespondenceDetailsVO().getCrspdLastUpdWorkUnit()));
			correspondence
					.setCorrespondenceLastUpdatedWorkUnit(lastUpdtWorkUnit);
		}

		CorrespondenceCategory crspdCategory = new CorrespondenceCategory();
		crspdCategory.setCategorySK(Long.valueOf(correspondenceRecordVO
				.getCorrespondenceDetailsVO().getCategorySK()));
		correspondence.setCorrespondenceCategory(crspdCategory);

		Set clientServices = convertClientServicesVoToDo(correspondenceRecordVO
				.getCorrespondenceDetailsVO());
		if(logger.isDebugEnabled())
		{
			logger.debug("convertClientServicesVoToDo" + clientServices.size());
		}
		correspondence.setCorrespondenceClientServices(clientServices);

		/*
		 * New Added for lob code
		 */
		/*LineOfBusiness crspdLob = new LineOfBusiness();
		crspdLob.setLobCode(correspondenceRecordVO.getCorrespondenceDetailsVO()
				.getAnLobCode().toString());
		correspondence.setLineOfBusiness(crspdLob);*/
		if(!isNullOrEmptyField(correspondenceRecordVO.getCorrespondenceDetailsVO()
				.getAnLobCode()))
		{
			LineOfBusiness crspdLob = new LineOfBusiness();
			crspdLob.setLobCode(correspondenceRecordVO.getCorrespondenceDetailsVO()
				.getAnLobCode().toString());
			correspondence.setLineOfBusiness(crspdLob);
		}


		try {
			if (!isNullOrEmptyField(correspondenceRecordVO
					.getCorrespondenceDetailsVO().getStatusDate())) {
				correspondence.setStatusDate(dateFormat
						.parse(correspondenceRecordVO
								.getCorrespondenceDetailsVO().getStatusDate()));
			}

			if (!isNullOrEmptyField(correspondenceRecordVO
					.getCorrespondenceDetailsVO().getOpenDate())) {
				correspondence.setOpenDate(dateFormat
						.parse(correspondenceRecordVO
								.getCorrespondenceDetailsVO().getOpenDate()));
			}
		} catch (ParseException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}

		Set setOfCrCommonEntityXRefs = setPrimaryAndInquiryAbtEntities(
				correspondenceRecordVO, correspondence);
		correspondence.setCrCmnEntyXRefs(setOfCrCommonEntityXRefs);

		Set setOfCorrespondenceRouting = convertListOfRoutingVOs(correspondence);
		correspondence.setCorrespondenceRoutings(setOfCorrespondenceRouting);
		if(logger.isDebugEnabled())
		{
			logger.debug("size of routing " + setOfCorrespondenceRouting.size());
		}

		Set setOfAlert = convertListOfAlertVOs(correspondence);
		correspondence.setAlerts(setOfAlert);

		Set setOfPriorAsscCRs = convertListOfAsscCRVOs(correspondenceRecordVO);
		correspondence.setPriorAssociatedCRs(setOfPriorAsscCRs);
		if(logger.isDebugEnabled())
		{
			logger.debug("size of prior cr " + setOfPriorAsscCRs.size());
		}
		/** Added for attachment */

		AttachmentDOConvertor convertor = new AttachmentDOConvertor();
		AttachmentDataBean dataBean = getAttachmentDataBean();
		
		List attachmentList = dataBean.getAttachmentList();
		if (attachmentList != null && !attachmentList.isEmpty()) {
			if(logger.isDebugEnabled())
			{
				logger.debug("In Vo to Do converter attachmentList size :"
						+ attachmentList.size());
			}
			Set setOfAttachments = convertor.getAttachments(attachmentList,
					correspondence, dataBean.getAttachmentXrefVO());
			correspondence.setCrAttachmentXrefs(setOfAttachments);
		}
		

		/** Added for letter Generation */

		Set setOfCrspdLetterXrefs = convertListOfLettersVOs(correspondenceRecordVO);
		correspondence.setLetterRequests(setOfCrspdLetterXrefs);
		if(logger.isDebugEnabled())
		{
			logger.debug("After Conversion size " + setOfCrspdLetterXrefs.size());
		}

		if (!isNullOrEmptyField(correspondenceRecordVO
				.getResponseExistIndicator())
				&& correspondenceRecordVO.getResponseExistIndicator()
						.equalsIgnoreCase(ContactManagementConstants.Y)) {
			correspondence.setResponseExistsIndicator(Boolean.TRUE);
		}

		//correspondence.setCrAttachmentXrefs()
		//correspondence.setCurrentAssociatedCRs()
		//correspondence.setLetterRequests()
		//correspondence.setNoteSet()
		//correspondence.setPriorAssociatedCRs()

		return correspondence;
	}

	/**
	 * @return String
	 */
	protected String getCRAssignedGroupType() {
		

		String workUnitType = "U";

		List listOfCMRoutingVOs = getRoutingDataBean().getListOfCMRoutingVOs();

		if (listOfCMRoutingVOs != null && !listOfCMRoutingVOs.isEmpty()) {
			Comparator comparator = new Comparator() {
				/*
				 * (non-Javadoc)
				 * 
				 * @see java.util.Comparator#compare(java.lang.Object,
				 *      java.lang.Object)
				 */
				public int compare(Object obj1, Object obj2) {
					

					CMRoutingVO cmRoutingVO1 = (CMRoutingVO) obj1;
					CMRoutingVO cmRoutingVO2 = (CMRoutingVO) obj2;

					int returnValue = 0;

					/*DateFormat dateFormat = new SimpleDateFormat(
							ContactManagementConstants.DATE_FORMAT_TIME);*/ // Find bug issue

					
					Date dateRouted1 = cmRoutingVO1.getDateRouted();

					Date dateRouted2 = cmRoutingVO2.getDateRouted();

					returnValue = dateRouted2.compareTo(dateRouted1);
					

					return returnValue;
				}
			};

			Collections.sort(listOfCMRoutingVOs, comparator);

			CMRoutingVO cMRoutingVO = ((CMRoutingVO) listOfCMRoutingVOs.get(0));
			if (cMRoutingVO != null
					&& cMRoutingVO.getAssignThisRecordToSK() != null) {
				workUnitType = cMRoutingVO.getGroupType();
			}
			
		}

		return workUnitType;
	}

	/**
	 * @param correspondence
	 *            The correspondence to set.
	 * @return Set
	 */
	protected Set convertListOfRoutingVOs(Correspondence correspondence) {
		

		Set setOfCorrespondenceRouting = new HashSet();

		List listOfCMRoutingVOs = getRoutingDataBean().getListOfCMRoutingVOs();

		if (listOfCMRoutingVOs != null) {
			if(logger.isDebugEnabled())
			{
				logger
						.debug("listOfCMRoutingVOs size "
								+ listOfCMRoutingVOs.size());
			}

			CorrespondenceRoutingDOConvertor crRoutingConvertor = new CorrespondenceRoutingDOConvertor();
			for (Iterator iter = listOfCMRoutingVOs.iterator(); iter.hasNext();) {
				CMRoutingVO cmRoutingVO = (CMRoutingVO) iter.next();

				CorrespondenceRouting crRouting = crRoutingConvertor
						.convertRoutingVOToDO(cmRoutingVO);
				//crRouting.setCorrespondence(correspondence);
				//crRouting.setCorrespondenceSK(correspondence.getCorrespondenceSK());

				if (!cmRoutingVO.isDbRecord()) {
					correspondence.setCorrespondenceAssignToWorkUnit(crRouting
							.getRoutedToWorkUnit());
					if (crRouting.getWatchlistIndicator().booleanValue()) {
						addWatchList(correspondence, crRouting);
					}
				}

				setOfCorrespondenceRouting.add(crRouting);
			}
		}
		return setOfCorrespondenceRouting;
	}

	/**
	 * @param correspondence
	 *            The correspondence to set.
	 * @param crRouting
	 *            The crRouting to set.
	 */
	private void addWatchList(Correspondence correspondence,
			CorrespondenceRouting crRouting) {
		CorrespondenceWatchList watchList = new CorrespondenceWatchList();
		//watchList.setCorrespondence(correspondence);
		//watchList.setCorrespondenceSK(correspondence.getCorrespondenceSK());

		watchList.setWorkUnit(crRouting.getRoutedByWorkUnit());
		watchList
				.setWorkUnitSK(crRouting.getRoutedByWorkUnit().getWorkUnitSK());

		watchList.setAuditUserID(getLoggedInUserID());
		watchList.setAddedAuditUserID(getLoggedInUserID());
		watchList.setVersionNo(crRouting.getVersionNo());
		Set setOfCRWatchList = new HashSet();
		setOfCRWatchList.add(watchList);
		correspondence.setCorrespondenceWatchList(setOfCRWatchList);

		Set watchListSet = getCorrespondenceDataBean().getCrWatchList();

		if (watchListSet != null && watchListSet.size() > 0) {
			Iterator itr = watchListSet.iterator();
			while (itr.hasNext()) {
				CorrespondenceWatchList crWatchList = (CorrespondenceWatchList) itr
						.next();
				if (crWatchList.getCorrespondenceSK().equals(
						correspondence.getCorrespondenceSK())
						&& crWatchList.getWorkUnitSK()
								.equals(
										crRouting.getRoutedByWorkUnit()
												.getWorkUnitSK())) {
					watchList.setVersionNo(crWatchList.getVersionNo());
					break;
				}
			}
		}
		if(logger.isDebugEnabled())
		{
			logger.debug("size of CR Watchlist " + setOfCRWatchList.size());
		}
	}

	/**
	 * @param correspondence
	 *            The correspondence to set.
	 * @return Set
	 */
	protected Set convertListOfAlertVOs(Correspondence correspondence) {
		

		Set setOfAlert = new HashSet();

		List listOfAlertVOs = getAlertDataBean().getListOfAlertVOs();

		if (listOfAlertVOs != null) {
			if(logger.isDebugEnabled())
			{
				logger.debug("listOfAlertVOs in convertor size"
						+ listOfAlertVOs.size());
			}

			AlertDOConvertor alertDOConvertor = new AlertDOConvertor();
			for (Iterator iter = listOfAlertVOs.iterator(); iter.hasNext();) {
				AlertVO alertVO = (AlertVO) iter.next();

				Alert alert = alertDOConvertor.convertAlertVOToDO(alertVO);
				alert.setAlertOriginCode(ContactManagementConstants.ALERT_ORIGIN_CODE_CR);
				if (correspondence.getStatusCode().equalsIgnoreCase(
						ContactManagementConstants.STATUS_CLOSED)) {
					alert
							.setAlertStatusCode(ContactManagementConstants.STATUS_CLOSED);
				}
				setOfAlert.add(alert);
			}
		}
		if(logger.isDebugEnabled())
		{
			logger.debug("setOfAlert in convertor size" + setOfAlert.size());
		}
		return setOfAlert;
	}

	/**
	 * @param correspondenceRecordVO
	 *            The correspondenceRecordVO to set.
	 * @return Set
	 */
	protected Set convertListOfAsscCRVOs(
			CorrespondenceRecordVO correspondenceRecordVO) {
		

		Set setOfPriorAsscCRs = new HashSet();

		List listOfPriorAsscCRVOs = correspondenceRecordVO
				.getListOfAssociatedCRs();
		if (listOfPriorAsscCRVOs != null) {
			for (Iterator iter = listOfPriorAsscCRVOs.iterator(); iter
					.hasNext();) {
				AssociatedCorrespondenceVO asscCorrespondenceVO = (AssociatedCorrespondenceVO) iter
						.next();
				CorrespondenceLink priorCRLink = new CorrespondenceLink();
				/*
				 * Correspondence priorCR = new Correspondence();
				 * priorCR.setCorrespondenceSK(Long.valueOf(asscCorrespondenceVO
				 * .getCorrespondenceRecordNumber()));
				 * priorCR.setSubjectCode(asscCorrespondenceVO.getSubject());
				 * priorCR.setStatusCode(asscCorrespondenceVO.getStatus());
				 * 
				 * CorrespondenceCategory category = new
				 * CorrespondenceCategory();
				 * category.setDescription(asscCorrespondenceVO.getCategory());
				 * priorCR.setCorrespondenceCategory(category);
				 */

				priorCRLink.setPriorCorrespondenceSK(Long
						.valueOf(asscCorrespondenceVO
								.getCorrespondenceRecordNumber()));
				if (correspondenceRecordVO.getCorrespondenceRecordNumber() != null) {
					priorCRLink.setCurrentCorrespondenceSK(Long
							.valueOf(correspondenceRecordVO
									.getCorrespondenceRecordNumber()));
				}
				priorCRLink.setAuditUserID(getLoggedInUserID());
				priorCRLink.setAddedAuditUserID(getLoggedInUserID());
				//priorCRLink.setAddedAuditTimeStamp(new Timestamp(1000));
				//priorCRLink.setAuditTimeStamp(new Timestamp(1000));
				priorCRLink.setVersionNo(asscCorrespondenceVO.getVersionNo());

				setOfPriorAsscCRs.add(priorCRLink);
			}
		}

		List listOfExistingCRVOs = correspondenceRecordVO.getExistingCRLists();
		if (listOfExistingCRVOs != null) {
			for (Iterator iter = listOfExistingCRVOs.iterator(); iter.hasNext();) {
				AssociatedCorrespondenceVO asscCorrespondenceVO = (AssociatedCorrespondenceVO) iter
						.next();

				if (asscCorrespondenceVO.getLinkToCR().booleanValue()) {
					CorrespondenceLink priorCRLink = new CorrespondenceLink();
					Correspondence priorCR = new Correspondence();

					

					priorCR.setCorrespondenceSK(Long
							.valueOf(asscCorrespondenceVO
									.getCorrespondenceRecordNumber()));
					//priorCRLink.setPriorCorrespondence(priorCR);
					priorCRLink.setPriorCorrespondenceSK(priorCR
							.getCorrespondenceSK());

					priorCRLink.setAuditUserID(getLoggedInUserID());
					priorCRLink.setAddedAuditUserID(getLoggedInUserID());
					priorCRLink.setVersionNo(asscCorrespondenceVO
							.getVersionNo());

					

					setOfPriorAsscCRs.add(priorCRLink);
				}
			}
		}

		return setOfPriorAsscCRs;
	}

	/**
	 * @param correspondenceRecordVO
	 *            The correspondenceRecordVO to set.
	 * @return Set
	 */
	protected Set convertListOfLettersVOs(
			CorrespondenceRecordVO correspondenceRecordVO) {
		

		Set setOfCrspdLettersDOs = new HashSet();

		List listOfLettersVOs = correspondenceRecordVO
				.getListOfLettersAndResponses();

		if (listOfLettersVOs != null) {
			for (Iterator iter = listOfLettersVOs.iterator(); iter.hasNext();) {
				CorrespondenceLetterResponsesXrefVO crLetterXrefVO = (CorrespondenceLetterResponsesXrefVO) iter
						.next();
				CorrespondenceLetterRequest crLetterDO = new CorrespondenceLetterRequest();
				crLetterDO.setAddedAuditUserID(correspondenceRecordVO
						.getAddedAuditUserID());
				crLetterDO.setAuditUserID(correspondenceRecordVO
						.getAuditUserID());
				//crLetterDO
				LetterGenerationRequest letterGenerationRequestDO = new LetterGenerationRequest();
				crLetterDO.setLetterRequestSK(Long.valueOf(crLetterXrefVO
						.getLetterGeneratonRequestSK()));
				letterGenerationRequestDO.setLetterRequestSK(Long
						.valueOf(crLetterXrefVO.getLetterGeneratonRequestSK()));
				crLetterDO.setLtrGenReqObj(letterGenerationRequestDO);
				crLetterDO.setVersionNo(crLetterXrefVO.getVersionNo());
				setOfCrspdLettersDOs.add(crLetterDO);
			}
		}

		return setOfCrspdLettersDOs;
	}

	/**
	 * @param correspondence
	 *            The correspondence to set.
	 * @return Set
	 */
	private Set setCRWatchList(Correspondence correspondence) {
		Set setOfCRWatchList = new HashSet();

		Set setOfCorrespondenceRouting = correspondence
				.getCorrespondenceRoutings();

		if (setOfCorrespondenceRouting == null) {
			return setOfCRWatchList;
		}

		for (Iterator iter = setOfCorrespondenceRouting.iterator(); iter
				.hasNext();) {
			CorrespondenceRouting crRouting = (CorrespondenceRouting) iter
					.next();
			if (crRouting.getWatchlistIndicator().booleanValue()) {
				CorrespondenceWatchList watchList = new CorrespondenceWatchList();
				//watchList.setCorrespondence(correspondence);
				//watchList.setCorrespondenceSK(correspondence.getCorrespondenceSK());
				watchList.setWorkUnit(crRouting.getRoutedByWorkUnit());
				watchList.setWorkUnitSK(crRouting.getRoutedByWorkUnit()
						.getWorkUnitSK());

				watchList.setAuditUserID(getLoggedInUserID());
				watchList.setAddedAuditUserID(getLoggedInUserID());
				watchList.setVersionNo(crRouting.getVersionNo());
			}
		}

		return setOfCRWatchList;
	}

	/**
	 * @param correspondenceRecordVO
	 *            The correspondenceRecordVO to set.
	 * @param correspondence
	 *            The correspondence to set.
	 * @return Set
	 */
	protected Set setPrimaryAndInquiryAbtEntities(
			CorrespondenceRecordVO correspondenceRecordVO,
			Correspondence correspondence) {
		CRCommonEntityCrossRef primaryCrCeXref = null;

		if (getCorrespondenceDataBean().isRenderCrspdForVO()) {
			primaryCrCeXref = getCRCommonEntityXRef(correspondenceRecordVO
					.getCorrespondenceForVO(), "P", correspondence);
		} else if (getCorrespondenceDataBean()
				.isRenderCrspdUnEnrolledProviderForVO()) {
			
			primaryCrCeXref = getCRCommonEntityXRef(correspondenceRecordVO
					.getCorrespondenceForVO(), "P", correspondence);
		} else if (getCorrespondenceDataBean()
				.isRenderCrspdUnEnrolledMemberForVO()) {
			
			primaryCrCeXref = getCRCommonEntityXRef(correspondenceRecordVO
					.getCorrespondenceForVO(), "P", correspondence);
		} else if (getCorrespondenceDataBean().isRenderCrspdTPLForVO()) {
			
			primaryCrCeXref = getCRCommonEntityXRef(correspondenceRecordVO
					.getCorrespondenceForVO(), "P", correspondence);
		} else if (getCorrespondenceDataBean().isRenderCrspdMemberForVO()) {
			primaryCrCeXref = getCRCommonEntityXRef(correspondenceRecordVO
					.getCorrespondenceForMemberVO(), "P", correspondence);

			/*
			 * if (!isNullOrEmptyField(correspondenceRecordVO
			 * .getCorrespondenceForMemberVO().getContact())) { Representative
			 * representative = new Representative();
			 * representative.setRepresentativeSK(Long
			 * .valueOf(correspondenceRecordVO
			 * .getCorrespondenceForMemberVO().getContact()));
			 * correspondence.setRepresentative(representative); }
			 */
		} else if (getCorrespondenceDataBean().isRenderCrspdProviderForVO()) {
			primaryCrCeXref = getCRCommonEntityXRef(correspondenceRecordVO
					.getCorrespondenceForProviderVO(), "P", correspondence);

			if (!isNullOrEmptyField(correspondenceRecordVO
					.getCorrespondenceForProviderVO().getContact())) {
				Representative representative = new Representative();
				representative
						.setRepresentativeSK(Long
								.valueOf(correspondenceRecordVO
										.getCorrespondenceForProviderVO()
										.getContact()));
				correspondence.setRepresentative(representative);
			}
		}
		//ADDED FOR THE Correspondence ESPRD00436016
		else if (getCorrespondenceDataBean().isRenderCrspdTrdPartForVO()) {
			
			primaryCrCeXref = getCRCommonEntityXRef(correspondenceRecordVO
					.getCorrespondenceForTradingPartnerVO(), "P", correspondence);}

		Set setOfCrCommonEntityXRefs = new HashSet();
		setOfCrCommonEntityXRefs.add(primaryCrCeXref);

		List listOfInquiryAbtEntities = correspondenceRecordVO
				.getCorrespondenceDetailsVO().getListOfEnquiryAboutEntities();

		if (listOfInquiryAbtEntities != null) {
			for (Iterator iter = listOfInquiryAbtEntities.iterator(); iter
					.hasNext();) {
				CorrespondenceForVO crspdForVO = (CorrespondenceForVO) iter
						.next();
				CRCommonEntityCrossRef inquiryAbtCommonEntityXref = getCRCommonEntityXRef(
						crspdForVO, "I", correspondence);
				setOfCrCommonEntityXRefs.add(inquiryAbtCommonEntityXref);
			}
		}

		return setOfCrCommonEntityXRefs;
	}

	/**
	 * @param correspondenceForVO
	 *            The correspondenceForVO to set.
	 * @param type
	 *            The type to set.
	 * @param correspondence
	 *            The correspondence to set.
	 * @return CRCommonEntityCrossRef
	 */
	private CRCommonEntityCrossRef getCRCommonEntityXRef(
			CorrespondenceForVO correspondenceForVO, String type,
			Correspondence correspondence) {
		

		CRCommonEntityCrossRef crCommonEntityXref = new CRCommonEntityCrossRef();

		crCommonEntityXref.setCaseCRContactReasonCode(type);
		crCommonEntityXref.setCommonEntityTypeCode(correspondenceForVO
				.getEntityTypeCode());

		CommonEntity commonEntity = new CommonEntity();
		commonEntity.setCommonEntitySK(Long.valueOf(correspondenceForVO
				.getCmEntityID()));

		crCommonEntityXref.setCommonEntity(commonEntity);
		// crCommonEntityXref.setCorrespondence(correspondence);

		/*
		 * if (correspondence.getCorrespondenceSK() != null) {
		 * crCommonEntityXref.setCorrespondenceSK(correspondence.getCorrespondenceSK()); }
		 */

		correspondence.setAuditUserID(getLoggedInUserID());

		crCommonEntityXref.setAuditUserID(correspondence.getAuditUserID());
		crCommonEntityXref.setAddedAuditUserID(correspondence
				.getAddedAuditUserID());
		crCommonEntityXref.setVersionNo(correspondenceForVO.getVersionNo());

		return crCommonEntityXref;
	}

	/**
	 * @param correspondence
	 *            The correspondence to set.
	 * @return CorrespondenceRecordVO
	 */
	public CorrespondenceRecordVO convertCorrespondenceDOToVO(
			Correspondence correspondence) {
		

		CorrespondenceRecordVO correspondenceRecordVO = new CorrespondenceRecordVO();

		if (correspondence == null) {
			

			return correspondenceRecordVO;
		}

		/*DateFormat dateFormat = new SimpleDateFormat(
				ContactManagementConstants.DATE_FORMAT);*/ // FInd bug issue

		correspondenceRecordVO.setCorrespondenceRecordNumber(correspondence
				.getCorrespondenceSK().toString());

		correspondenceRecordVO.setEdmsDocTypeCode(correspondence
				.getEdmsDocTypeCode());
		// correspondenceRecordVO.setCallScriptDesc()
		// correspondenceRecordVO.setCallScriptSK()

		if (ContactManagementConstants.STATUS_CLOSED.equalsIgnoreCase(correspondence.getStatusCode()) 
				&& correspondence.getDaysToCloseNumber() != null) 
		{
			correspondenceRecordVO.setDaysToClose(correspondence
					.getDaysToCloseNumber().toString());
		}
		if(logger.isInfoEnabled())
		{
			logger.info("++correspondence.getVersionNo()--"+correspondence.getVersionNo());
		}
		correspondenceRecordVO.setResponseAllClosedIndicator(correspondence
				.getResponseAllClosedIndicator().toString());
		correspondenceRecordVO.setResponseExistIndicator(correspondence
				.getResponseExistsIndicator().toString());
		correspondenceRecordVO.setResponseHasFileIndicator(correspondence
				.getResponseHasFileIndicator().toString());
		correspondenceRecordVO.setSensitiveDataIndicator(correspondence
				.getSensitiveDataIndicator().toString());

		if (correspondence.getSupervisorReviewedWorkUnit() != null
				&& correspondence.getSupervisorReviewedWorkUnit()
						.getWorkUnitSK() != null) {
			correspondenceRecordVO
					.setSprvsrReviewedWorkUnit(correspondence
							.getSupervisorReviewedWorkUnit().getWorkUnitSK()
							.toString());
		}

		if (correspondence.getReceivedDate() != null) {
			correspondenceRecordVO.setReceivedDate(correspondence
					.getReceivedDate());
			if(logger.isInfoEnabled())
			{
				logger.info("ReceivedDate in DO TO VO converter---->"
						+ correspondenceRecordVO.getReceivedDate());
			}
		}

		CorrespondenceDetailsVO correspondenceDetailsVO = new CorrespondenceDetailsVO();
		
		populateCorrespondenceDetailsVO(correspondence, correspondenceDetailsVO);
		correspondenceRecordVO
				.setCorrespondenceDetailsVO(correspondenceDetailsVO);

		Set setOfCmEntyXRefs = correspondence.getCrCmnEntyXRefs();

		if (setOfCmEntyXRefs != null) {
			for (Iterator iter = setOfCmEntyXRefs.iterator(); iter.hasNext();) 
			{
				CRCommonEntityCrossRef crCmXref = (CRCommonEntityCrossRef) iter.next();
				
				setCrspdForOrInquiryAbtEntity(correspondenceRecordVO, crCmXref);
			}
		}
		List listOfRoutingVOs = convertListOfRoutingDOs(correspondence);
		getRoutingDataBean().setListOfCMRoutingVOs(listOfRoutingVOs);
		sortRoutingInfoOnDate();

		if (!listOfRoutingVOs.isEmpty()) {
			getRoutingDataBean().setRenderNoData(false);
		}
		//correspondenceRecordVO.setListOfCmRoutingVOs(listOfRoutingVOs);

		List listOfAlertVOs = convertListOfAlertDOs(correspondence);
		getAlertDataBean().setListOfAlertVOs(listOfAlertVOs);
		sortAlertInfoOnDueDate();
		if (!listOfAlertVOs.isEmpty()) {
			getAlertDataBean().setRenderNoData(false);
		}
		//correspondenceRecordVO.setListOfCrAlerts(listOfAlertVOs);

		List listOfAssociatedCRVOs = getListOfAssociatedCRVOs(correspondence);
		correspondenceRecordVO.setListOfAssociatedCRs(listOfAssociatedCRVOs);

		removeCRAlreadyAsscFromExitingCRLists(correspondenceRecordVO);

		/*
		 * if (getCorrespondenceDataBean().isRenderCrspdMemberForVO()) {
		 * correspondenceRecordVO.getCorrespondenceForMemberVO().setContact(
		 * correspondence.getRepresentative().getRepresentativeSK()
		 * .toString()); } else
		 */

		if (getCorrespondenceDataBean().isRenderCrspdProviderForVO()
				&& correspondence.getRepresentative() != null) {
			correspondenceRecordVO.getCorrespondenceForProviderVO().setContact(
					correspondence.getRepresentative().getRepresentativeSK()
							.toString());
		} else if (correspondence.getRepresentative() != null) {
			correspondenceRecordVO.getCorrespondenceForVO().setContact(
					correspondence.getRepresentative().getRepresentativeSK()
							.toString());
		}

		/*
		 * //correspondenceRecordVO.setListOfAssociatedCRs()
		 * //correspondenceRecordVO.setListOfAttachments()
		 */
		

		List listOfAttachmentVOs = convertListOfAttachmentDOs(correspondence);
		if (listOfAttachmentVOs == null || listOfAttachmentVOs.isEmpty()) {
			
			getAttachmentDataBean().setNoData(true);
		} else {
			getAttachmentDataBean().setNoData(false);
			if(logger.isDebugEnabled())
			{
				logger.debug("listOfAttachmentVOs size :"
						+ listOfAttachmentVOs.size());
			}
			
			// Added for the Defect ESPRD00748270 - start //
			ContactManagementHelper contactManagementHelper = new ContactManagementHelper();
			contactManagementHelper.sortCorrespondenceAttachmentsComparator("Attach_DateAdded", "desc",
					listOfAttachmentVOs);
			// Added for the Defect ESPRD00748270 - END //
			getAttachmentDataBean().setAttachmentList(listOfAttachmentVOs);
			if(logger.isDebugEnabled())
			{
				logger.debug("AttachmentList size in DataBean frm converter:"
						+ getAttachmentDataBean().getAttachmentList().size());
			}
			
			//Code added for defect - ESPRD00825861 to set the attachment size
			 getAttachmentDataBean().setAttachmentListSizeforCr(listOfAttachmentVOs.size());
			//Code Ended for defect - ESPRD00825861
		}

		/*
		 * //correspondenceRecordVO.setListOfLettersAndResponses() // Not needed
		 * here correspondence.getCorrespondenceWatchList()
		 * correspondence.getCrAttachmentXrefs()
		 * correspondence.getCurrentAssociatedCRs()
		 * correspondence.getPriorAssociatedCRs()
		 * correspondence.getLetterRequests() correspondence.getNoteSet()
		 */

		/** Added for Letter n Responses */
		List listofLetterVos = convertListOfLettersDOs(correspondence);
		correspondenceRecordVO.setListOfLettersAndResponses(listofLetterVos);

		if (correspondence.getNoteSet() != null) {
			
			getCorrespondenceDataBean().getCrNotesSetTempList().add(
					correspondence.getNoteSet());
			
		}

		correspondenceRecordVO.setAuditUserID(correspondence.getAuditUserID());
		correspondenceRecordVO.setAddedAuditUserID(correspondence
				.getAddedAuditUserID());
		correspondenceRecordVO.setAuditTimeStamp(correspondence
				.getAuditTimeStamp());
		correspondenceRecordVO.setAddedAuditTimeStamp(correspondence
				.getAddedAuditTimeStamp());
		correspondenceRecordVO.setVersionNo(correspondence.getVersionNo());
		correspondenceRecordVO.setDbRecord(true);
		Set wacthListSet = new HashSet();
		wacthListSet.addAll(correspondence.getCorrespondenceWatchList());
		getCorrespondenceDataBean().setCrWatchList(wacthListSet);

		
		
		createVOAuditKeysList(correspondence,correspondenceRecordVO);
		return correspondenceRecordVO;
	}

	/**
	 * @param correspondence
	 *            The correspondence to set.
	 * @return List
	 */
	protected List convertListOfRoutingDOs(Correspondence correspondence) {
		

		List listOfRoutingVOs = new ArrayList();
		Set setOfCrspdRouting = correspondence.getCorrespondenceRoutings();

		if (setOfCrspdRouting != null) {
			CorrespondenceRoutingDOConvertor routingConvertor = new CorrespondenceRoutingDOConvertor();

			for (Iterator iter = setOfCrspdRouting.iterator(); iter.hasNext();) {
				CorrespondenceRouting routing = (CorrespondenceRouting) iter
						.next();
				CMRoutingVO cmRoutingVO = routingConvertor
						.convertRoutingDOToVO(routing);

				listOfRoutingVOs.add(cmRoutingVO);
			}
		}

		return listOfRoutingVOs;
	}

	/**
	 * @param correspondence
	 *            The correspondence to set.
	 * @return List
	 */
	protected List convertListOfAlertDOs(Correspondence correspondence) {
		

		List listOfAlertVOs = new ArrayList();
		Set setOfAlert = correspondence.getAlerts();

		if (setOfAlert != null) {
			AlertDOConvertor alertConvertor = new AlertDOConvertor();

			for (Iterator iter = setOfAlert.iterator(); iter.hasNext();) {
				Alert alert = (Alert) iter.next();
				AlertVO alertVO = alertConvertor.convertAlertDOToVO(alert);

				listOfAlertVOs.add(alertVO);
			}
		}

		return listOfAlertVOs;
	}

	/**
	 * @param correspondence
	 *            The correspondence to set.
	 * @return List
	 */
	protected List convertListOfAttachmentDOs(Correspondence correspondence) {
		

		List listOfAttachmentVOs = new ArrayList();
		Set setOfAttachments = correspondence.getCrAttachmentXrefs();
		CMDelegate cMDelegate = new CMDelegate();
		if (setOfAttachments != null) {
			Set attachmentXrefVO_set = new HashSet();
			AttachmentDOConvertor attachmentDOConvertor = new AttachmentDOConvertor();

			for (Iterator iter = setOfAttachments.iterator(); iter.hasNext();) {
				try {
					CorrespondenceAttachmentXref attachmentXref = (CorrespondenceAttachmentXref) iter
							.next();
					CorrespondenceAttachmentXrefVO attachmentXrefVO = convertAttachmentXrefsDOs(attachmentXref);
					Attachment attachment = cMDelegate
							.getAttachmentDetails(attachmentXref
									.getAttachmentRef().getAttachmentSK());
					
					AttachmentVO attachmentVO = attachmentDOConvertor
							.convertAttachmentDO(attachment);
					attachmentXrefVO_set.add(attachmentXrefVO);
					if (attachmentXrefVO.getAttachIndicator() != null
							&& (!attachmentXrefVO.getAttachIndicator().equals(
									ContactManagementConstants.EMPTY_STRING))) {
						
						if (attachmentXrefVO.getAttachIndicator().toString().equals("false")) {
							attachmentVO
									.setAttachmentIndicator(ContactManagementConstants.NO);
						} else {
							
							attachmentVO
									.setAttachmentIndicator(ContactManagementConstants.YES);
						}
					}
					
					//for fixing Defect ESPRD00611913
					attachmentVO.setNewAttachment(false);
					listOfAttachmentVOs.add(attachmentVO);
				} catch (CorrespondenceRecordFetchBusinessException e) {
					e.printStackTrace();
				}
			}
			getAttachmentDataBean().setAttachmentXrefVO(attachmentXrefVO_set);
		}

		return listOfAttachmentVOs;
	}

	/**
	 * @param attachmentXref
	 *            The attachmentXref to set.
	 * @return CorrespondenceAttachmentXrefVO
	 */
	private CorrespondenceAttachmentXrefVO convertAttachmentXrefsDOs(
			CorrespondenceAttachmentXref attachmentXref) {
		
		CorrespondenceAttachmentXrefVO attachmentXrefVO = new CorrespondenceAttachmentXrefVO();
		if (attachmentXref != null) {
			if (attachmentXref.getDetachedAttchmntIndicator() != null) {
				attachmentXrefVO.setAttachIndicator(attachmentXref
						.getDetachedAttchmntIndicator());
			}

			if (attachmentXref.getAttachmentSK() != null) {
				attachmentXrefVO.setAttachmentSk(attachmentXref
						.getAttachmentSK());
			}

			if (attachmentXref.getAddedAuditTimeStamp() != null) {
				attachmentXrefVO.setAddedAuditTimeStamp(attachmentXref
						.getAddedAuditTimeStamp());
			}

			if (attachmentXref.getAddedAuditUserID() != null) {
				attachmentXrefVO.setAddedAuditUserID(attachmentXref
						.getAddedAuditUserID());
			}

			if (attachmentXref.getAuditTimeStamp() != null) {
				attachmentXrefVO.setAuditTimeStamp(attachmentXref
						.getAuditTimeStamp());
			}

			if (attachmentXref.getAuditUserID() != null) {
				attachmentXrefVO
						.setAuditUserID(attachmentXref.getAuditUserID());
			}

			attachmentXrefVO.setVersionNo(attachmentXref.getVersionNo());
			if(logger.isDebugEnabled())
			{
				logger.debug("iattachmentXref.getVersionNo  "
						+ attachmentXref.getVersionNo());
			}

		}

		return attachmentXrefVO;
	}

	/**
	 * @param correspondence
	 *            The correspondence to set.
	 * @return List
	 */
	protected List getListOfAssociatedCRVOs(Correspondence correspondence) 
	{
		
		List listOfPriorAsscCRs = new ArrayList();
		CMDelegate cMDelegate = new CMDelegate();
		try{
			List priorCrList  = cMDelegate.getPriorCorrespondence(correspondence.getCorrespondenceSK());
			
			if(priorCrList != null && priorCrList.size() > 0)
			{
				if(logger.isInfoEnabled())
				{
					logger.info("priorCrList.size()======="+priorCrList.size());
				}
				for (Iterator iter = priorCrList.iterator(); iter.hasNext();) {
					PriorCorrespondence priorCRLink = (PriorCorrespondence) iter.next();
					DateFormat dateFormat = new SimpleDateFormat(ContactManagementConstants.DATE_FORMAT);

					AssociatedCorrespondenceVO associatedCorrespondenceVO = new AssociatedCorrespondenceVO();
					associatedCorrespondenceVO.setCorrespondenceRecordNumber(priorCRLink.getCorrespondenceSK().toString());
					if (priorCRLink.getOpenDate() != null) 
					{
						associatedCorrespondenceVO.setOpenDate(dateFormat.format(priorCRLink.getOpenDate()));
					}
					associatedCorrespondenceVO.setSubject(priorCRLink.getSubjectCode());
					Map subjectMap = getSearchCorrespondenceDataBean().getSubjectMap();
					if (subjectMap != null && subjectMap.size() > 0
							&& subjectMap.containsKey(priorCRLink.getSubjectCode())) 
					{
						associatedCorrespondenceVO.setSubject((String) subjectMap.get(priorCRLink.getSubjectCode()));
					}
					associatedCorrespondenceVO.setCategory(priorCRLink.getCategoryDescription());
					associatedCorrespondenceVO.setStatus(priorCRLink.getStatusCode());
					Map statusMap = getSearchCorrespondenceDataBean().getStatusMap();
					if (statusMap != null && statusMap.size() > 0
							&& statusMap.containsKey(priorCRLink.getStatusCode())) {
						associatedCorrespondenceVO.setStatus((String) statusMap
								.get(priorCRLink.getStatusCode()));
					}
					associatedCorrespondenceVO.setContact(priorCRLink.getRepFirstName()
								+ ContactManagementConstants.SPACE_STRING + priorCRLink.getRepLastName());

					associatedCorrespondenceVO.setLinkToCR(Boolean.TRUE);
					associatedCorrespondenceVO.setExisting(true);
					associatedCorrespondenceVO.setVersionNo(priorCRLink.getVersionNo());
					associatedCorrespondenceVO.setAddedAuditTimeStamp(priorCRLink.getAddedAuditTimeStamp());
					associatedCorrespondenceVO.setAddedAuditUserID(priorCRLink.getAddedAuditUserID());
					associatedCorrespondenceVO.setAuditTimeStamp(priorCRLink.getAuditTimeStamp());
					associatedCorrespondenceVO.setAuditUserID(priorCRLink.getAuditUserID());
					associatedCorrespondenceVO.setDbAssocRecord(true);
					listOfPriorAsscCRs.add(associatedCorrespondenceVO);
				}
			}
		}catch(CorrespondenceRecordFetchBusinessException e)
		{
			
			e.printStackTrace();
		}
		sortlistOfPriorAsscCRs(listOfPriorAsscCRs) ;
		return listOfPriorAsscCRs;
	}

	/**
	 * @param correspondenceRecordVO
	 *            The correspondenceRecordVO to set.
	 */
	protected void removeCRAlreadyAsscFromExitingCRLists(
			CorrespondenceRecordVO correspondenceRecordVO) {
		
		CorrespondenceForVO correspondenceForVO = null;

		if (getCorrespondenceDataBean().isRenderCrspdForVO()
				|| getCorrespondenceDataBean()
						.isRenderCrspdUnEnrolledMemberForVO()
				|| getCorrespondenceDataBean()
						.isRenderCrspdUnEnrolledProviderForVO()
				|| getCorrespondenceDataBean().isRenderCrspdTPLForVO()) {
			
			correspondenceForVO = correspondenceRecordVO
					.getCorrespondenceForVO();
		} else if (getCorrespondenceDataBean().isRenderCrspdMemberForVO()) {
			
			correspondenceForVO = correspondenceRecordVO
					.getCorrespondenceForMemberVO();
		} else if (getCorrespondenceDataBean().isRenderCrspdProviderForVO()) {
			
			correspondenceForVO = correspondenceRecordVO
					.getCorrespondenceForProviderVO();
		}//ADDED FOR THE Correspondence ESPRD00436016
		else if (getCorrespondenceDataBean().isRenderCrspdTrdPartForVO()) {
			
			correspondenceForVO = correspondenceRecordVO
					.getCorrespondenceForTradingPartnerVO();
		}
		if(logger.isInfoEnabled())
		{
		logger.info("CmEntityID in converter---> "
				+ correspondenceForVO.getCmEntityID());
		logger.info("EntityTypeCode in converter---> "
				+ correspondenceForVO.getEntityTypeCode());
		}

		if (correspondenceForVO.getCmEntityID() != null) {
			getAssociatedCorrespondence(Long.valueOf(correspondenceForVO
					.getCmEntityID()), correspondenceForVO.getEntityTypeCode(),
					Integer.valueOf("90"));
		}

		List listOfAsscCRs = correspondenceRecordVO.getListOfAssociatedCRs();
		List listOfExistingCRs = getCorrespondenceDataBean()
				.getCorrespondenceRecordVO().getExistingCRLists();

		if (listOfAsscCRs != null && listOfExistingCRs != null) {
			if(logger.isDebugEnabled())
			{
			logger.debug("list size of exisisting list  *******  "
					+ listOfExistingCRs.size());
			logger.debug("list size of ass list  *******  "
					+ listOfAsscCRs.size());
			}

			for (Iterator iter = listOfAsscCRs.iterator(); iter.hasNext();) {
				AssociatedCorrespondenceVO asscCRVO = (AssociatedCorrespondenceVO) iter
						.next();
				for (Iterator iterator = listOfExistingCRs.iterator(); iterator
						.hasNext();) {
					AssociatedCorrespondenceVO existingCR = (AssociatedCorrespondenceVO) iterator
							.next();
					if (existingCR.getCorrespondenceRecordNumber()
							.equalsIgnoreCase(
									asscCRVO.getCorrespondenceRecordNumber())
							|| existingCR
									.getCorrespondenceRecordNumber()
									.equalsIgnoreCase(
											correspondenceRecordVO
													.getCorrespondenceRecordNumber())) {
						iterator.remove();
						
						break;
					}
				}

				correspondenceRecordVO.setExistingCRLists(listOfExistingCRs);
				
			}
		}
	}

	/**
	 * @param correspondence
	 *            The correspondence to set.
	 * @param correspondenceDetailsVO
	 *            The correspondenceDetailsVO to set.
	 */
	protected void populateCorrespondenceDetailsVO(
			Correspondence correspondence,
			CorrespondenceDetailsVO correspondenceDetailsVO) {
		

		DateFormat dateFormat = new SimpleDateFormat(
				ContactManagementConstants.DATE_FORMAT);

		if (correspondence.getLineOfBusiness() != null) {
			correspondenceDetailsVO.setAnLobCode(correspondence
					.getLineOfBusiness().getLobCode());
			createVOAuditKeysList(correspondence.getLineOfBusiness(),correspondenceDetailsVO);
		}else{
			correspondenceDetailsVO.setAnLobCode(" ");
		}			

		
		WorkUnit assgnToUserWorkUnit = correspondence
				.getCorrespondenceAssignToWorkUnit();
		if("U".equalsIgnoreCase(assgnToUserWorkUnit.getWorkUnitTypeCode()))
		{
			String nameDesc = getName(assgnToUserWorkUnit,
					assgnToUserWorkUnit.getWorkUnitTypeCode());
			
			StringBuffer userLabel = new StringBuffer().append(
					nameDesc).append(ContactManagementConstants.HYPHEN_SEPARATOR).append(
	                		assgnToUserWorkUnit.getEnterpriseUser().getUserID());
			if(logger.isDebugEnabled())
			{
			logger.debug("AssignedToWorkUnitName in converter-->"+userLabel);
			}
			correspondenceDetailsVO
					.setAssignedToWorkUnitName(userLabel.toString());
		}else{
			correspondenceDetailsVO
				.setAssignedToWorkUnitName(getName(assgnToUserWorkUnit,
						assgnToUserWorkUnit.getWorkUnitTypeCode()));
		}		
		
		correspondenceDetailsVO.setAssignedToWorkUnitSK(assgnToUserWorkUnit
				.getWorkUnitSK().toString());
		correspondenceDetailsVO.setWorkUnitTypeCode(assgnToUserWorkUnit
				.getWorkUnitTypeCode());
		
		WorkUnit crtByUserWorkUnit = correspondence.getCreatedByWorkUnit();
		correspondenceDetailsVO
				.setCreatedByName(getName(crtByUserWorkUnit, "U"));
		correspondenceDetailsVO.setCreatedBySK(crtByUserWorkUnit
				.getWorkUnitSK().toString());
		if (crtByUserWorkUnit.getEnterpriseUser() != null) {
			correspondenceDetailsVO.setCreatedByUserID(crtByUserWorkUnit
					.getEnterpriseUser().getUserID());
		} else if (crtByUserWorkUnit.getDepartment() != null) {
			correspondenceDetailsVO.setCreatedByUserID(crtByUserWorkUnit
					.getDepartment().getName());
		}
		if (correspondence.getCorrespondenceOpenWorkUnit() != null
				&& correspondence.getCorrespondenceOpenWorkUnit()
						.getWorkUnitSK() != null) {
			
			correspondenceDetailsVO
					.setCrspdOpenWorkUnit(correspondence
							.getCorrespondenceOpenWorkUnit().getWorkUnitSK()
							.toString());
		}

		if (correspondence.getCorrespondenceLastUpdatedWorkUnit() != null
				&& correspondence.getCorrespondenceLastUpdatedWorkUnit()
						.getWorkUnitSK() != null) {
			
			correspondenceDetailsVO.setCrspdLastUpdWorkUnit(correspondence
					.getCorrespondenceLastUpdatedWorkUnit().toString());
		}

		
		correspondenceDetailsVO.setCategorySK(correspondence
				.getCorrespondenceCategory().getCategorySK().toString());
		
		correspondenceDetailsVO.setSubjectCode(correspondence.getSubjectCode());
		

		String departmentSK = ContactManagementConstants.EMPTY_STRING;
		if(correspondence.getLineOfBusinessHierarchy() != null)
		{
			Set deptSet = correspondence.getLineOfBusinessHierarchy().getDepartments();
			if(deptSet != null && deptSet.size() > 0)
			{
				if(logger.isInfoEnabled())
				{
					logger.info("deptSet.size()-----"+deptSet.size());
				}
				Department dept = (Department) deptSet.iterator().next();
				if(logger.isInfoEnabled())
				{
				logger.info("dept name-----"+dept.getName());
				}
				List listOfDepartments = getCorrespondenceDataBean().getDepartmentsList();
				departmentSK = dept.getWorkUnitSK().toString();
				listOfDepartments.add(new SelectItem(dept.getWorkUnitSK().toString(), dept.getName()));
				Map mapOfDeptAndLobHier = getCorrespondenceDataBean().getMapOfDeptAndLobHier();
				if(dept.getWorkUnitSK() != null && correspondence.getLineOfBusinessHierarchy() != null)
				{
					mapOfDeptAndLobHier.put(dept.getWorkUnitSK().toString(),
							correspondence.getLineOfBusinessHierarchy().getLineOfBusinessHierarchySK().toString());
				}
			}
		}
		correspondenceDetailsVO.setDepartment(departmentSK);
		
		if (correspondence.getOpenDate() != null) {
			correspondenceDetailsVO.setOpenDate(dateFormat
					.format(correspondence.getOpenDate()));
		}
		
		correspondenceDetailsVO.setPriorityCode(correspondence
				.getPriorityCode());
		if(logger.isDebugEnabled())
		{
			logger.debug("PriorityCode set in convrtr---->"
					+ correspondenceDetailsVO.getPriorityCode());
		}
		getCorrespondenceDataBean().setPrevPriorityCode(
				correspondence.getPriorityCode());
		if(logger.isDebugEnabled())
		{
			logger.debug("Prev PriorityCode set in convrtr---->"
					+ getCorrespondenceDataBean().getPrevPriorityCode());
		}
		
		correspondenceDetailsVO.setResolutionCode(correspondence
				.getResolutionCode());
		
		correspondenceDetailsVO.setSourceCode(correspondence.getSourceCode());
		
		correspondenceDetailsVO.setStatusCode(correspondence.getStatusCode());
		
		correspondenceDetailsVO.setStatusDate(dateFormat.format(correspondence
				.getStatusDate()));
		
		correspondenceDetailsVO.setSprvsrRevwReqdIndicator(correspondence
				.getSupervisorReviewReqIndicator());

		//code fix for ESPRD00683016 starts
		List categoryDosList=getCorrespondenceDataBean().getListOfCategoryDOs();
		if(categoryDosList!=null){
			CorrespondenceCategory correspondenceCategory= getSelectedCategory(correspondence
				.getCorrespondenceCategory().getCategorySK().toString(),categoryDosList);
			
			if(correspondenceCategory!=null){
				if(correspondenceCategory
						.getSupervisorReviewReqIndicator().booleanValue())
				correspondenceDetailsVO
				.setSprvsrRevwReqdIndicator(correspondenceCategory
						.getSupervisorReviewReqIndicator());
			}
		}
		//code fix for ESPRD00683016 ends
		
		if (correspondence.getLineOfBusinessHierarchy() != null) {
			Long lobHierarchySK = correspondence.getLineOfBusinessHierarchy()
					.getLineOfBusinessHierarchySK();
			setReportingAndBusinessUnit(correspondenceDetailsVO, lobHierarchySK);
		}
		// correspondenceDetailsVO.setCustomField()

		if (correspondenceDetailsVO.getStatusCode().equals("C")) 
		{
			
			correspondenceDetailsVO.setDaysToClose(String
					.valueOf(correspondence.getDaysToCloseNumber()));
		}else{
			
			correspondenceDetailsVO.setDaysOpen(String
					.valueOf(correspondence.getDaysOpened()));
		}
		if (correspondence.getReceivedDate() != null) {
			correspondenceDetailsVO.setReceivedDate(ContactManagementHelper
					.dateConverter(correspondence.getReceivedDate()));
		}
		correspondenceDetailsVO.setTcn(correspondence.getTransactionNumber());
		correspondenceDetailsVO.setAuthNumber(correspondence
				.getAuthorizationNumber());
		if(logger.isDebugEnabled())
		{
		logger.debug("TCN in Cor Details---------->"
				+ correspondenceDetailsVO.getTcn());
		logger.debug("AuthNumber in Cor Details---------->"
				+ correspondenceDetailsVO.getAuthNumber());
		logger.debug("setDaysToClose in Cor Details---------->"
				+ correspondenceDetailsVO.getDaysToClose());
		logger.debug("ReceivedDate in Cor Details---------->"
				+ correspondenceDetailsVO.getReceivedDate());
		

		correspondenceDetailsVO.setDbRecord(true);
		//Client services do to vo
		
		logger.debug("correspondence.getCorrespondenceClientServices().size() "
				+ correspondence.getCorrespondenceClientServices().size());
		}
		if (correspondence.getCorrespondenceClientServices() != null
				&& correspondence.getCorrespondenceClientServices().size() > 0) {
			CorrespondenceClientServices cRClientServices = null;
			if (correspondence.getCorrespondenceClientServices().iterator()
					.hasNext()) {
				cRClientServices = (CorrespondenceClientServices) correspondence
						.getCorrespondenceClientServices().iterator().next();
			}
			if (cRClientServices != null) {
				
				if (cRClientServices.getClientServiceVIPIndicator() != null
						&& cRClientServices.getClientServiceVIPIndicator().equalsIgnoreCase(ContactManagementConstants.Y)) 
				{
					correspondenceDetailsVO.setVipPresent("Yes");
				} else if (cRClientServices.getClientServiceVIPIndicator() != null
						&& cRClientServices.getClientServiceVIPIndicator().equalsIgnoreCase(ContactManagementConstants.N))
				{
					correspondenceDetailsVO.setVipPresent("No");
				} else {
					//added if Yes/No is selected
					correspondenceDetailsVO.setVipPresent(null);
				}
				correspondenceDetailsVO.setDentalApptMadeBy(cRClientServices
						.getClientServiceDentalApptmtCode());
				if (cRClientServices.getClientServiceGlobalIndicator() != null
						&& cRClientServices.getClientServiceGlobalIndicator().equalsIgnoreCase(ContactManagementConstants.Y))
				{
					correspondenceDetailsVO.setLtdEngProficiency("Yes");
				} else if (cRClientServices.getClientServiceGlobalIndicator() != null
						&& cRClientServices.getClientServiceGlobalIndicator().equalsIgnoreCase(ContactManagementConstants.N))
				{
					correspondenceDetailsVO.setLtdEngProficiency("No");
				} else {
					//added if Yes/No is selected
					correspondenceDetailsVO.setLtdEngProficiency(null);
				}
				correspondenceDetailsVO.setVersionNo(cRClientServices
						.getVersionNo());

				if (cRClientServices.getCorrespondenceCSReferred() != null) {
					Iterator itr = cRClientServices
							.getCorrespondenceCSReferred().iterator();
					if (getCorrespondenceDataBean().getReferredToList() != null) {

						/*List referenceList = new ArrayList();*/
						List selectedList = new ArrayList();
						Map versionMap = new HashMap();
						SelectItem tempSelectItem = null;
						if(logger.isDebugEnabled())
						{
							logger.debug("Vo List Size: "
									+ getCorrespondenceDataBean()
											.getReferredToVOList().size());
						}
						while (itr.hasNext()) {
							Iterator referenceIterator = getCorrespondenceDataBean()
									.getReferredToVOList().iterator();
							CorrespondenceCSReferred cRCSReferred = (CorrespondenceCSReferred) itr
									.next();
							
							while (referenceIterator.hasNext()) {
								SelectItem reference = (SelectItem) referenceIterator
										.next();
								
								if (reference
										.getValue()
										.equals(
												cRCSReferred
														.getClientServiceRfrdToTypeCode())) {
									
									selectedList.add(reference);
									tempSelectItem = reference;
								}

								versionMap
										.put(
												cRCSReferred
														.getClientServiceRfrdToTypeCode(),
												new Integer(cRCSReferred
														.getVersionNo()));
							}
							if (tempSelectItem != null) {
								getCorrespondenceDataBean().getReferredToList()
										.remove(tempSelectItem);
							}
						}
						getCorrespondenceDataBean().setVersionMap(versionMap);
						correspondenceDetailsVO
								.setSelectedReferredToList(selectedList);
					}
				}
			}
		}
		createVOAuditKeysList(correspondence,correspondenceDetailsVO);
		createVOAuditKeysList(correspondence.getCorrespondenceCategory(),correspondenceDetailsVO);
	}

	/**
	 * @param correspondenceDetailsVO
	 *            The correspondenceDetailsVO to set.
	 * @param lobHierarchySK
	 *            The lobHierarchySK to set.
	 */
	protected void setReportingAndBusinessUnit(
			CorrespondenceDetailsVO correspondenceDetailsVO, Long lobHierarchySK) {
		
		if(logger.isDebugEnabled())
		{
		logger.debug("lobHierarchySK " + lobHierarchySK);
		}

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		try {
			Map rUnitAndBUnitMap = contactMaintenanceDelegate.getRUnitAndBUnit(lobHierarchySK);
			LineOfBusinessHierarchy reportingUnit = (LineOfBusinessHierarchy) rUnitAndBUnitMap.get("RUnit");
			LineOfBusinessHierarchy businessUnit = (LineOfBusinessHierarchy) rUnitAndBUnitMap.get("BUnit");
			if(logger.isDebugEnabled())
			{
				logger.debug("reportingUnit" + reportingUnit);
				logger.debug("businessUnit" + businessUnit);
			}

			if (reportingUnit != null) {
				if(logger.isDebugEnabled())
				{
				logger.debug("reportingUnit.getLobHierarchyDescription()"
						+ reportingUnit.getLobHierarchyDescription());
				}
				correspondenceDetailsVO.setReportingUnitName(reportingUnit
						.getLobHierarchyDescription());
				correspondenceDetailsVO.setReportingUnitSK(reportingUnit
						.getLineOfBusinessHierarchySK().toString());
			} else {
				correspondenceDetailsVO
						.setReportingUnitName(ContactManagementConstants.EMPTY_STRING);
				correspondenceDetailsVO
						.setReportingUnitSK(ContactManagementConstants.EMPTY_STRING);
			}
			if (businessUnit != null) {
				if(logger.isDebugEnabled())
				{
				logger.debug("businessUnit.getLobHierarchyDescription()"
						+ businessUnit.getLobHierarchyDescription());
				}
				correspondenceDetailsVO.setBusinessUnitName(businessUnit
						.getLobHierarchyDescription());
				correspondenceDetailsVO.setBusinessUnitSK(businessUnit
						.getLineOfBusinessHierarchySK().toString());
			} else {
				correspondenceDetailsVO
						.setBusinessUnitName(ContactManagementConstants.EMPTY_STRING);
				correspondenceDetailsVO
						.setBusinessUnitSK(ContactManagementConstants.EMPTY_STRING);
			}

		} catch (LOBHierarchyFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * @param correspondenceRecordVO
	 *            The correspondenceRecordVO to set.
	 * @param crCmXref
	 *            The crCmXref to set.
	 */
	protected void setCrspdForOrInquiryAbtEntity(
			CorrespondenceRecordVO correspondenceRecordVO,
			CRCommonEntityCrossRef crCmXref) {
		

		if ("P".equals(crCmXref.getCaseCRContactReasonCode())) {
			if(logger.isDebugEnabled())
			{
			logger
					.debug("CommonEntityTypeCode in setCrspdForOrInquiryAbtEntity--->"
							+ crCmXref.getCommonEntityTypeCode());
			}
			setCorrespondenceForEntity(correspondenceRecordVO, crCmXref);
		} else if ("I".equals(crCmXref.getCaseCRContactReasonCode())) {
			setInquiryAboutEntity(correspondenceRecordVO, crCmXref);
		}
	}

	/**
	 * @param correspondenceRecordVO
	 *            The correspondenceRecordVO to set.
	 * @param crCmXref
	 *            The crCmXref to set.
	 */
	private void setCorrespondenceForEntity(
			CorrespondenceRecordVO correspondenceRecordVO,
			CRCommonEntityCrossRef crCmXref) {
		
		//To Check ---Comented in ES2 as CmnEntityTypeCode returns null
		//String entityType = crCmXref.getCmnEntityTypeCode();

		String entityType = crCmXref.getCommonEntityTypeCode();
		Long cmEntitySK = crCmXref.getCommonEntity().getCommonEntitySK();
		if(logger.isInfoEnabled())
		{
		logger.info("entityType-------->" + entityType);
		logger.info("cmEntitySK-------->" + cmEntitySK);
		}
		if (ContactManagementConstants.ENTITY_TYPE_PROV.equals(entityType)) {
			ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();

			Long entityId = null;
			try {
				entityId = providerInformationDelegate
						.getProviderSysID(cmEntitySK);
			} catch (EnterpriseBaseBusinessException e) {
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(), e);
				}
			}

			if (entityId == null) {
				
			} else {
				CorrespondenceForProviderVO crspdProviderVO = getCorrespondenceForProviderVO(
						entityId.toString(), true);
				crspdProviderVO.setVersionNo(crCmXref.getVersionNo());

				correspondenceRecordVO
						.setCorrespondenceForProviderVO(crspdProviderVO);
				correspondenceRecordVO.getCorrespondenceForVO().setEntityTypeCodeForNote(crspdProviderVO.getCurrAltID());
				correspondenceRecordVO.getCorrespondenceForVO().setNameForNote(crspdProviderVO.getName());
				// No need of getting existing correspondence associated with
				// the entity in update mode.
				// getAssociatedCorrespondence(entityId, entityType,
				// Integer.valueOf("90"));
			}
			getCorrespondenceDataBean().setRenderCrspdTrdPartForVO(false);  
			getCorrespondenceDataBean().setRenderCrspdForVO(false);
			getCorrespondenceDataBean().setRenderCrspdProviderForVO(true);
			getCorrespondenceDataBean().setRenderCrspdMemberForVO(false);
			getCorrespondenceDataBean().setRenderCrspdUnEnrolledProviderForVO(
					false);
			getCorrespondenceDataBean().setRenderCrspdUnEnrolledMemberForVO(
					false);
			getCorrespondenceDataBean().setRenderCrspdTPLForVO(false);
		} else if (ContactManagementConstants.ENTITY_TYPE_MEM
				.equals(entityType)) {
			MemberInformationDelegate memberInformationDelegate = new MemberInformationDelegate();

			Long entityId = null;

			try {
				entityId = memberInformationDelegate.getMemberID(cmEntitySK);
			} catch (MemberBusinessException e) {
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(), e);
				}
			}

			if (entityId == null) {
				
			} else {
				CorrespondenceForMemberVO crspdForMemberVO = getCorrespondenceForMemberVO(
						entityId.toString(), true);
				crspdForMemberVO.setVersionNo(crCmXref.getVersionNo());

				correspondenceRecordVO
						.setCorrespondenceForMemberVO(crspdForMemberVO);
				correspondenceRecordVO.getCorrespondenceForVO().setEntityTypeCodeForNote(crspdForMemberVO.getCurrAltID());
				correspondenceRecordVO.getCorrespondenceForVO().setNameForNote(crspdForMemberVO.getPreviousName());
				// No need of getting existing correspondence associated with
				// the entity in update mode.
				// getAssociatedCorrespondence(entityId, entityType,
				// Integer.valueOf("90"));
			}

			getCorrespondenceDataBean().setRenderCrspdForVO(false);
			getCorrespondenceDataBean().setRenderCrspdProviderForVO(false);
			getCorrespondenceDataBean().setRenderCrspdMemberForVO(true);
			getCorrespondenceDataBean().setRenderCrspdUnEnrolledProviderForVO(
					false);
			getCorrespondenceDataBean().setRenderCrspdUnEnrolledMemberForVO(
					false);
			getCorrespondenceDataBean().setRenderCrspdTPLForVO(false);
			getCorrespondenceDataBean().setRenderCrspdTrdPartForVO(false);
		} else if (ContactManagementConstants.ENTITY_TYPE_TPL
				.equals(entityType)) {
			if(logger.isDebugEnabled())
			{
			logger.debug("TPL page----->" + entityType);
			}
			String entityId = cmEntitySK.toString();
			CorrespondenceForVO correspondenceForVO = getCorrespondenceForTPLVO(
					entityId, true);
			correspondenceForVO.setVersionNo(crCmXref.getVersionNo());
			getCorrespondenceDataBean().getListOfAlternateIds().clear();
			correspondenceRecordVO.setCorrespondenceForVO(correspondenceForVO);
			getCorrespondenceDataBean().setRenderCrspdForVO(false);
			getCorrespondenceDataBean().setRenderCrspdProviderForVO(false);
			getCorrespondenceDataBean().setRenderCrspdMemberForVO(false);
			getCorrespondenceDataBean().setRenderCrspdUnEnrolledProviderForVO(
					false);
			getCorrespondenceDataBean().setRenderCrspdUnEnrolledMemberForVO(
					false);
			getCorrespondenceDataBean().setRenderCrspdTPLForVO(true);
			getCorrespondenceDataBean().setRenderCrspdTrdPartForVO(false);
			getCorrespondenceDataBean().setTplPolicyHolder(false);

		}
		//      added entity type Unenrolled Provider for ES2
		else if (ContactManagementConstants.ENTITY_TYPE_UNPROV
				.equals(entityType)) {
			if(logger.isInfoEnabled())
			{
			logger.info("in unenrolled provider page------>" + entityType);
			}
			String entityId = cmEntitySK.toString();
			ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();
			Long pSysID = null;
			try {
				pSysID = providerInformationDelegate.getProviderSysID(cmEntitySK);
			} catch (EnterpriseBaseBusinessException e) 
			{
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(), e);
				}
			}

			if (pSysID != null) 
			{
				if(logger.isInfoEnabled())
				{
				logger.info("Correspondence For Provider Entity Found"+pSysID);
				}
				CorrespondenceForVO correspondenceForVO = getCorrespondenceForUnApprovedProvider(
															pSysID.toString(), true);
				correspondenceForVO.setVersionNo(crCmXref.getVersionNo());
				correspondenceRecordVO.setCorrespondenceForVO(correspondenceForVO);
			}else
			{
				CorrespondenceForVO correspondenceForVO = getCorrespondenceForUnEnrolledProviderVO(
															entityId, true);
				correspondenceForVO.setVersionNo(crCmXref.getVersionNo());
				correspondenceRecordVO.setCorrespondenceForVO(correspondenceForVO);
			}
			getCorrespondenceDataBean().setRenderCrspdForVO(false);
			getCorrespondenceDataBean().setRenderCrspdProviderForVO(false);
			getCorrespondenceDataBean().setRenderCrspdMemberForVO(false);
			getCorrespondenceDataBean().setRenderCrspdUnEnrolledProviderForVO(
					true);
			getCorrespondenceDataBean().setRenderCrspdUnEnrolledMemberForVO(
					false);
			getCorrespondenceDataBean().setRenderCrspdTPLForVO(false);
			getCorrespondenceDataBean().setRenderCrspdTrdPartForVO(false);
			

		} else if (ContactManagementConstants.ENTITY_TYPE_UNMEM
				.equals(entityType)) {
			if(logger.isInfoEnabled())
			{
				logger.info("in unenrolled member page------>" + entityType);
			}
			String entityId = cmEntitySK.toString();

			CorrespondenceForVO correspondenceForVO = getCorrespondenceForVO(
					entityId, true);
			correspondenceForVO.setVersionNo(crCmXref.getVersionNo());

			correspondenceRecordVO.setCorrespondenceForVO(correspondenceForVO);
			getCorrespondenceDataBean().setRenderCrspdForVO(false);
			getCorrespondenceDataBean().setRenderCrspdProviderForVO(false);
			getCorrespondenceDataBean().setRenderCrspdMemberForVO(false);
			getCorrespondenceDataBean().setRenderCrspdUnEnrolledProviderForVO(
					false);
			getCorrespondenceDataBean().setRenderCrspdTPLForVO(false);
			getCorrespondenceDataBean().setRenderCrspdUnEnrolledMemberForVO(
					true);
			getCorrespondenceDataBean().setRenderCrspdTrdPartForVO(false);
		}else if (ContactManagementConstants.ENTITY_TYPE_TP
                .equals(entityType))
        {
			String entityId = cmEntitySK.toString();
			if(logger.isInfoEnabled())
			{
				logger.info("in unenrolled member page------>" + entityType+entityId);
			}
        	CorrespondenceForVO correspondenceForVO = getCorrespondenceForTPLPolicyHolder(entityId, true);
            
            if (correspondenceForVO != null)
            {
            	correspondenceForVO.setEntityTypeCode(entityType);
               // setEntityCallScriptDetails(correspondenceForVO);
            	if(logger.isInfoEnabled())
            	{
            		logger.info("correspondenceForVO.getTplPolicyHolderId()-----"+correspondenceForVO.getTplPolicyHolderId());
            	}

            	correspondenceRecordVO.setCorrespondenceForVO(correspondenceForVO);
                //setDefaultCRDetValues();
                getCorrespondenceDataBean().setRenderCrspdForVO(false);
                getCorrespondenceDataBean().setRenderCrspdProviderForVO(false);
                getCorrespondenceDataBean().setRenderCrspdMemberForVO(false);
                getCorrespondenceDataBean().setRenderCrspdUnEnrolledProviderForVO(false);
                getCorrespondenceDataBean().setRenderCrspdUnEnrolledMemberForVO(false);
                getCorrespondenceDataBean().setRenderCrspdTPLForVO(true);
                getCorrespondenceDataBean().setTplPolicyHolder(true);
                getCorrespondenceDataBean().setRenderCrspdTrdPartForVO(false);
             }
        } //ADDED FOR THE Correspondence ESPRD00436016
		else if(ContactManagementConstants.ENTITY_TYPE_TD
                .equals(entityType)){
        	String entityId = cmEntitySK.toString();
        	if(logger.isDebugEnabled())
        	{
        	logger.debug(" ###### ContactManagementConstants.ENTITY_TYPE_TD ###### entityId : "+entityId);
        	}
        	CorrespondenceForTradingPartnerVO correspondenceTradingPartnerVO = getCorrespondenceForTradingPartner(entityId, true);
        	if (correspondenceTradingPartnerVO != null)
            {
        		if(logger.isDebugEnabled())
        		{
        		logger.debug(" ###### ContactManagementConstants.ENTITY_TYPE_TD ###### crCmXref.getVersionNo : "+crCmXref.getVersionNo());
        		}
        		correspondenceTradingPartnerVO.setEntityTypeCode(entityType);
        		correspondenceTradingPartnerVO.setVersionNo(crCmXref.getVersionNo());
               // setEntityCallScriptDetails(correspondenceForVO);          	

            	correspondenceRecordVO.setCorrespondenceForTradingPartnerVO(correspondenceTradingPartnerVO);
            	//setDefaultCRDetValues();
                getCorrespondenceDataBean().setRenderCrspdForVO(false);
                getCorrespondenceDataBean().setRenderCrspdProviderForVO(false);
                getCorrespondenceDataBean().setRenderCrspdMemberForVO(false);
                getCorrespondenceDataBean().setRenderCrspdUnEnrolledProviderForVO(false);
                getCorrespondenceDataBean().setRenderCrspdUnEnrolledMemberForVO(false);
                getCorrespondenceDataBean().setRenderCrspdTPLForVO(false);
                getCorrespondenceDataBean().setTplPolicyHolder(false);
                getCorrespondenceDataBean().setRenderCrspdTrdPartForVO(true);
             }
        	
        }
		else {
			String entityId = cmEntitySK.toString();

			CorrespondenceForVO correspondenceForVO = getCorrespondenceForVO(
					entityId, true);
			correspondenceForVO.setVersionNo(crCmXref.getVersionNo());

			// No need of getting existing correspondence associated with the
			// entity in update mode.
			// getAssociatedCorrespondence(Long.valueOf(entityId), entityType,
			// Integer.valueOf("90"));

			correspondenceRecordVO.setCorrespondenceForVO(correspondenceForVO);
			getCorrespondenceDataBean().setRenderCrspdForVO(true);
			getCorrespondenceDataBean().setRenderCrspdProviderForVO(false);
			getCorrespondenceDataBean().setRenderCrspdMemberForVO(false);
			getCorrespondenceDataBean().setRenderCrspdUnEnrolledProviderForVO(
					false);
			getCorrespondenceDataBean().setRenderCrspdTPLForVO(false);
			getCorrespondenceDataBean().setRenderCrspdUnEnrolledMemberForVO(
					false);
		}
	}

	/**
	 * @param correspondenceRecordVO
	 *            The correspondenceRecordVO to set.
	 * @param crCmXref
	 *            The crCmXref to set.
	 */
	private void setInquiryAboutEntity(
			CorrespondenceRecordVO correspondenceRecordVO,
			CRCommonEntityCrossRef crCmXref) {
		

		String entityType = crCmXref.getCommonEntityTypeCode();
		Long cmEntitySK = crCmXref.getCommonEntity().getCommonEntitySK();

		if (ContactManagementConstants.ENTITY_TYPE_PROV.equals(entityType)) {
			ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();

			Long entityId = null;
			try {
				entityId = providerInformationDelegate
						.getProviderSysID(cmEntitySK);
			} catch (EnterpriseBaseBusinessException e) {
				if(logger.isErrorEnabled())
				{
					logger.error(e.getMessage(), e);
				}
			}

			if (entityId == null) {
				
			} else {
				CorrespondenceForProviderVO crspdProviderVO = getCorrespondenceForProviderVO(
						entityId.toString(), false);
				crspdProviderVO.setVersionNo(crCmXref.getVersionNo());

				correspondenceRecordVO.getCorrespondenceDetailsVO()
						.getListOfEnquiryAboutEntities().add(crspdProviderVO);
			}
		} else if (ContactManagementConstants.ENTITY_TYPE_MEM
				.equals(entityType)) {
			MemberInformationDelegate memberInformationDelegate = new MemberInformationDelegate();

			Long entityId = null;

			try {
				entityId = memberInformationDelegate.getMemberID(cmEntitySK);
			} catch (MemberBusinessException e) {
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(), e);
				}
			}

			if (entityId == null) {
				
			} else {
				CorrespondenceForMemberVO crspdForMemberVO = getCorrespondenceForMemberVO(
						entityId.toString(), false);
				crspdForMemberVO.setVersionNo(crCmXref.getVersionNo());

				correspondenceRecordVO.getCorrespondenceDetailsVO()
						.getListOfEnquiryAboutEntities().add(crspdForMemberVO);
			}
		} else if (ContactManagementConstants.ENTITY_TYPE_TPL.equals(entityType)) {
			
			/*CMDelegate cmDelegate = new CMDelegate();

			Long entityId = null;

			try {
				TPLCarrier tplCarrier = cmDelegate.getTPLCarrier(cmEntitySK);
				entityId = tplCarrier.getCommonEntity().getCommonEntitySK();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}*/

			if (cmEntitySK == null) {
				
			} else {
				CorrespondenceForVO correspondenceForVO = getCorrespondenceForTPLVO(
						cmEntitySK.toString(), false);
				correspondenceForVO.setVersionNo(crCmXref.getVersionNo());
				correspondenceForVO.setEntityId(correspondenceForVO.getCmEntityID());
				correspondenceForVO.setName(correspondenceForVO.getCarrierName());
				correspondenceRecordVO.getCorrespondenceDetailsVO()
						.getListOfEnquiryAboutEntities().add(correspondenceForVO);
			}
		}else if (ContactManagementConstants.ENTITY_TYPE_TP.equals(entityType)) {
			

			if (cmEntitySK == null) {
				
			} else {
				CorrespondenceForVO correspondenceForVO = getCorrespondenceForTPLPolicyHolder(
						cmEntitySK.toString(), false);
				correspondenceForVO.setVersionNo(crCmXref.getVersionNo());
				correspondenceForVO.setEntityId(correspondenceForVO.getCmEntityID());
				correspondenceRecordVO.getCorrespondenceDetailsVO()
						.getListOfEnquiryAboutEntities().add(correspondenceForVO);
			}
		}//ADDED FOR THE Correspondence ESPRD00436016
		else if (ContactManagementConstants.ENTITY_TYPE_TD
				.equals(entityType)) {
			if(logger.isDebugEnabled())
			{
			logger.debug(" ContactManagementConstants.ENTITY_TYPE_TD cmEntitySK "+cmEntitySK);
			}
			CMDelegate cmDelegate=new CMDelegate();
			ProviderBasicInfo providerBasicInfo=null;
			ProviderEnrollmentDelegate providerEnrollmentDelegate= new ProviderEnrollmentDelegate();
			Long entityId = null;
			

			try {
				//TradingPartner tp=cmDelegate.getTradingPartner(null, cmEntitySK);
				providerBasicInfo=providerEnrollmentDelegate.getTradingPartnerDetailsForCommonEntitySk(null, cmEntitySK);
				if(logger.isDebugEnabled())
				{
				logger.debug(" ContactManagementConstants.ENTITY_TYPE_TD TradingPartner "+providerBasicInfo);
				}
				if(providerBasicInfo != null)
				{
				entityId = providerBasicInfo.getCommonEntitySK();
				}
				if(logger.isDebugEnabled())
				{
				logger.debug(" ContactManagementConstants.ENTITY_TYPE_TD entityId "+entityId);
				}
			} catch (Exception e) {
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(), e);
				}
			}

			if (entityId == null) {
				
			} else {
				if(logger.isDebugEnabled())
				{
				logger.debug(" crCmXref.getVersionNo() : "+crCmXref.getVersionNo());
				}
				CorrespondenceForTradingPartnerVO tpVO = getCorrespondenceForTradingPartner(entityId.toString(), false);
				tpVO.setVersionNo(crCmXref.getVersionNo());

				correspondenceRecordVO.getCorrespondenceDetailsVO()
						.getListOfEnquiryAboutEntities().add(tpVO);
			}
		} else if (ContactManagementConstants.ENTITY_TYPE_UNPROV
				.equals(entityType)) {
			if(logger.isInfoEnabled())
			{
			logger.info("in unenrolled provider page------>" + entityType);
			}
			String entityId = cmEntitySK.toString();
			ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();
			Long pSysID = null;
			CorrespondenceForVO correspondenceForVO=null;
			try {
				pSysID = providerInformationDelegate.getProviderSysID(cmEntitySK);
			} catch (EnterpriseBaseBusinessException e) 
			{
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(), e);
				}
			}

			if (pSysID != null) 
			{
				if(logger.isInfoEnabled())
				{
				logger.info("Correspondence For Provider Entity Found"+pSysID);
				}
				 correspondenceForVO = getCorrespondenceForUnApprovedProvider(pSysID.toString(), true);
				correspondenceForVO.setVersionNo(crCmXref.getVersionNo());
			
			}else
			{
				 correspondenceForVO = getCorrespondenceForUnEnrolledProviderVO(entityId, true);
				correspondenceForVO.setVersionNo(crCmXref.getVersionNo());
			}
			
			 correspondenceRecordVO.getCorrespondenceDetailsVO()
			.getListOfEnquiryAboutEntities().add(correspondenceForVO);
			
		} 
		else {
			String entityId = cmEntitySK.toString();

			CorrespondenceForVO correspondenceForVO = getCorrespondenceForVO(
					entityId, false);
			correspondenceForVO.setVersionNo(crCmXref.getVersionNo());

			correspondenceRecordVO.getCorrespondenceDetailsVO()
					.getListOfEnquiryAboutEntities().add(correspondenceForVO);
		}
	}

	/**
	 * @param entityId
	 *            The entityId to set.
	 * @param callingEntity
	 *            The callingEntity to set.
	 * @return CorrespondenceForProviderVO
	 */
	private CorrespondenceForProviderVO getCorrespondenceForProviderVO(
			String entityId, boolean callingEntity) {
		

		ProviderInformationRequestVO providerInfRequestVO = new ProviderInformationRequestVO();

		providerInfRequestVO.setProviderSysID(Long.valueOf(entityId));
		providerInfRequestVO.setAlternateIdInfoRequired(true);
		providerInfRequestVO.setEnrollmentStatusRequired(true);
		providerInfRequestVO.setRepresentativeContactRequired(true);
		providerInfRequestVO.setBoardCertifiedSpecialityRequired(true);
		providerInfRequestVO.setProviderTypesRequired(true);
		//Changes added due to change in CommonEntity.hbm.xml
		//providerInfRequestVO.setProviderBankruptcy(true);

		ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();

		Provider provider = providerInformationDelegate
				.getProviderDetails(providerInfRequestVO);

		CorrespondenceForProviderVO crspdProviderVO = CorrespondenceDOConvertor
				.getCrspdProviderVO(provider);

		getContactsForEntities(Long.valueOf(entityId));

		if (callingEntity && provider != null) {
			setAlternateIds(provider);

			CommonEntity commonEntity = provider.getCommonEntity();
			setCommonContactList(commonEntity);
		}

		return crspdProviderVO;
	}

	/**
	 * @param entityId
	 *            The entityId to set.
	 * @param callingEntity
	 *            The callingEntity to set.
	 * @return CorrespondenceForMemberVO
	 */
	private CorrespondenceForMemberVO getCorrespondenceForMemberVO(
			String entityId, boolean callingEntity) 
	{
		

        Long memberSysId = Long.valueOf(entityId);

        MemberInformationDelegate memberInformationDelegate = new MemberInformationDelegate();
        CMDelegate cMDelegate = new CMDelegate();
        //Member member = null;
        CommonEntity cmResidentAddress = null;
        CommonEntity cmMailingAddress = null;
        MemberBasicInfo memberBasicInfo= null;

        try
        {
           /* member = cMDelegate
                    .getMemberDetailForCR(memberSysId);*/
        	memberBasicInfo= memberInformationDelegate.getMemberDetailsForCR(memberSysId);
            if (memberBasicInfo == null)
            {
                
            }
            cmResidentAddress = memberInformationDelegate.getRecentAddress(
                    memberSysId, "R");
            cmMailingAddress = memberInformationDelegate.getRecentAddress(
                    memberSysId, "M");
        }
        catch (MemberBusinessException e)
        {
        	if(logger.isErrorEnabled())
        	{
            logger.error(e.getMessage(), e);
        	}
        }

        CorrespondenceForMemberVO crspdForMemberVO = CorrespondenceDOConvertor
                .getCrspdMemberVO(memberBasicInfo);
        
        /*String langDesc = getLangDesc(
                member.getDemographicInformation().getPrimaryLanguage());*/ // Find bug issue
        
        String langDesc = null;
        /*if(null != member && null != member.getDemographicInformation()){
        	langDesc = getLangDesc(
                    member.getDemographicInformation().getPrimaryLanguage());	
        }*/
        if(null != memberBasicInfo.getPrimaryLang()){
        	langDesc = getLangDesc(memberBasicInfo.getPrimaryLang());	
        }
        
        crspdForMemberVO.setPrimaryLanguage(langDesc);

        crspdForMemberVO.setResAddress(getAddress(cmResidentAddress));
        crspdForMemberVO.setMailAddress(getAddress(cmMailingAddress));

        getContactsForEntities(Long.valueOf(entityId));

        if (callingEntity &&  memberBasicInfo != null)
        {
            setAlternateIds(memberBasicInfo);

           // CommonEntity commonEntity = member.getCommonEntity();
           // setCommonContactList(commonEntity);
            if (memberBasicInfo.getCmnEntySK() != null) {
    			
    			try {
    				CommonEntityDelegate commonEntityDelegate = new CommonEntityDelegate();
    				CommonEntity commonEntity = commonEntityDelegate.getEntity(memberBasicInfo.getCmnEntySK());
    				setCommonContactList(commonEntity);
    				
    			} catch (CommonEntityNotFoundException e) {
    				e.printStackTrace();
    				
    				
         	} catch (EnterpriseBaseBusinessException e) {
    				// TODO Auto-generated catch block
         		if(logger.isErrorEnabled())
         		{
         		logger.error(e.getMessage(),e);
         		}
    			}
    		}
            
        }

        return crspdForMemberVO;
	}

	/**
	 * @param cmAddress
	 *            The cmAddress to set.
	 * @return String
	 */
	protected String getAddress(CommonEntity cmAddress) {
		

		if (cmAddress == null) {
			
			return ContactManagementConstants.EMPTY_STRING;
		}

		StringBuffer addr = new StringBuffer(
				ContactManagementConstants.DEFAULT_SIZE);
		Set setOfAddressUsage = cmAddress.getAddressUsage();

		if (setOfAddressUsage != null) {
			for (Iterator iter = setOfAddressUsage.iterator(); iter.hasNext();) {
				AddressUsage addrUsage = (AddressUsage) iter.next();
				Address address = addrUsage.getAddress();

				addr.append(address.getAddressLine1());

				if (address.getAddressLine2() != null && !address.getAddressLine2().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) 
				{
					//addr.append("\n");
					addr.append(MaintainContactManagementUIConstants.BR_TAG);
					addr.append(address.getAddressLine2());
				}
				if (address.getAddressLine3() != null && !address.getAddressLine3().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) 
				{
					//addr.append("\n");
					addr.append(MaintainContactManagementUIConstants.BR_TAG);
					addr.append(address.getAddressLine3());
				}
				if (address.getAddressLine4() != null && !address.getAddressLine4().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) 
				{
					//addr.append("\n");
					addr.append(MaintainContactManagementUIConstants.BR_TAG);
					addr.append(address.getAddressLine4());
				}
			
				// Below code modified for defect ESPRD00892307 to display the address as per UIS
				/*addr.append("\n");
				if (address.getTownCode() != null) {
					addr.append(address.getTownCode());
				}

				if (address.getCountyCode() != null) {
					addr.append(address.getCountyCode());
				}*/
				
				addr.append(MaintainContactManagementUIConstants.BR_TAG);
				if (address.getCityName() != null && !address.getCityName().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) 
				{
					addr.append(address.getCityName());
					addr.append(ContactManagementConstants.COMMA_SEPARATOR);
                    addr.append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
				}
				if (address.getStateCode() != null && !address.getStateCode().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) 
				{
					addr.append(address.getStateCode());
					addr.append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
				}
				if (address.getCountryCode() != null && !address.getCountryCode().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) 
				{
					addr.append(address.getCountryCode());
					addr.append(MaintainContactManagementUIConstants.EMPTY_STRING_SPACE);
				}
				if (address.getZipCode5() != null && !address.getZipCode5().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) 
				{
					addr.append(address.getZipCode5());
				}
				if (address.getZipCode4() != null && !address.getZipCode4().trim().equals(MaintainContactManagementUIConstants.EMPTY_STRING)) 
				{
					addr.append(MaintainContactManagementUIConstants.HYPHEN);
					addr.append(address.getZipCode4());
				}
			}
		}

		return addr.toString();
	}

	/**
	 * @param entityId
	 *            The entityId to set.
	 * @param callingEntity
	 *            The callingEntity to set.
	 * @return CorrespondenceForVO
	 */
	private CorrespondenceForVO getCorrespondenceForVO(String entityId,
			boolean callingEntity) {
		

		CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();
		SpecificEntity specificEntity = null;

		try {
			specificEntity = cmEntityDelegate.getSpecificEntityDetails(Long
					.valueOf(entityId));
		} catch (CMEntityFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		} catch (NumberFormatException e) {
			if(logger.isErrorEnabled())
			{				
			logger.error(e.getMessage(), e);
			}
		}

		CorrespondenceForVO correspondenceForVO = CorrespondenceDOConvertor
				.getCrspdForVO(specificEntity);

		if (callingEntity && specificEntity != null) {
			CommonEntity commonEntity = specificEntity.getCommonEntity();
			CorrespondenceDOConvertor.setCommonContactList(commonEntity);
		}

		return correspondenceForVO;
	}

	/**
	 * @param entityId
	 *            The entityId to set.
	 * @param callingEntity
	 *            The callingEntity to set.
	 * @return CorrespondenceForVO
	 * Code commented/Added for TPL-API
	 */
	private CorrespondenceForVO getCorrespondenceForTPLVO(String entityId,
			boolean callingEntity) {
		

		CMDelegate cmDelegate = new CMDelegate();
		TPLCarrier tplCarrier = null;
		TPLCarrierDelegate tplCarrierDelegate= new TPLCarrierDelegate();
		TPLCarrierNoteVO tplCarrierNoteVO = null;
		CorrespondenceForVO correspondenceForVO=null;

		try {
			
			// cmDelegate replaced by tplCarrierDelegate for  implement TPL-API
			//tplCarrier = cmDelegate.getTPLCarrier(Long.valueOf(entityId));
			tplCarrierNoteVO= tplCarrierDelegate.getCarrierbyCommonSK(Long.valueOf(entityId));
			
		} catch (TPLCarrierBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		} catch (NumberFormatException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}

	/*	correspondenceForVO = CorrespondenceDOConvertor
		.getCrspdForTPLVO(tplCarrier);*/
		
		if(tplCarrierNoteVO != null)
		{
		correspondenceForVO = CorrespondenceDOConvertor
				.getCrspdForTPLVO(tplCarrierNoteVO);
		}

		getContactsForEntities(Long.valueOf(entityId));
		/*
		 * if (callingEntity && specificEntity != null) { CommonEntity
		 * commonEntity = specificEntity.getCommonEntity();
		 * CorrespondenceDOConvertor.setCommonContactList(commonEntity); }
		 */

		return correspondenceForVO;
	}

	/**
	 * @param entityId
	 *            The entityId to set.
	 * @param callingEntity
	 *            The callingEntity to set.
	 * @return CorrespondenceForVO
	 */
	private CorrespondenceForVO getCorrespondenceForUnEnrolledProviderVO(
			String entityId, boolean callingEntity) {
		

		CMEntityDelegate cmEntityDelegate = new CMEntityDelegate();
		SpecificEntity specificEntity = null;
		CMDelegate cmDelegate = new CMDelegate();
		String specialityCode = null;
		Boolean specificEntityExists = Boolean.FALSE;

		try {
			specificEntity = cmEntityDelegate.getSpecificEntityDetails(Long
					.valueOf(entityId));
		} catch (CMEntityFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
				logger.error(e.getMessage(), e);
			}
		} catch (NumberFormatException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}

		CorrespondenceForVO correspondenceForVO = CorrespondenceDOConvertor
				.getCrspdForVO(specificEntity);
		try {
			specialityCode = cmDelegate.getSpecialityCode(Long
					.valueOf(entityId));
			specificEntityExists = cmDelegate.checkSpecificEntityExists(Long
					.valueOf(entityId));
		} catch (CorrespondenceRecordFetchBusinessException crf) {
			if(logger.isErrorEnabled())
			{
			logger.error(crf.getMessage(), crf);
			}
		}

		if (specialityCode != null) {
			correspondenceForVO.setSpecialityCode(specialityCode);
		}
		if (specificEntityExists.equals(Boolean.TRUE)) {
			correspondenceForVO.setStatus("Application submitted");
		}
		//calling contact list
		getContactsForEntities(Long.valueOf(entityId));
		if (callingEntity && specificEntity != null) {
			CommonEntity commonEntity = specificEntity.getCommonEntity();
			CorrespondenceDOConvertor.setCommonContactList(commonEntity);
		}

		return correspondenceForVO;
	}

	/**
	 * @param provider
	 *            The provider to set.
	 * @return CorrespondenceForProviderVO
	 */
	public static CorrespondenceForProviderVO getCrspdProviderVO(
			Provider provider) {
		/** Enterprise Logger for Logging. */
		EnterpriseLogger logger = EnterpriseLogFactory
				.getLogger(CorrespondenceDOConvertor.class);

		
		CorrespondenceForProviderVO crspdProviderVO = new CorrespondenceForProviderVO();

		if (provider == null) {
			return crspdProviderVO;
		}

		crspdProviderVO
				.setEntityTypeCode(ContactManagementConstants.ENTITY_TYPE_PROV);
		String shortDesc = ReferenceServiceDelegate
				.getReferenceSearchShortDescription(
						FunctionalAreaConstants.GENERAL,
						ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
						new Long(80),
						ContactManagementConstants.ENTITY_TYPE_PROV);
		
		crspdProviderVO.setEntityTypeDesc(shortDesc);

		crspdProviderVO.setEntitySysId(provider.getSysID().toString());
		crspdProviderVO.setContact(ContactManagementConstants.EMPTY_STRING);

		if ("I".equals(provider.getIndividualGroupCode())) {
			if(provider.getName().getMiddleName()!= null)
			{
				crspdProviderVO.setName(provider.getName().getFirstName()
						+ ContactManagementConstants.SPACE_STRING
						+ provider.getName().getMiddleName()
						+ ContactManagementConstants.SPACE_STRING
						+ provider.getName().getLastName());
			}else
			{
				crspdProviderVO.setName(provider.getName().getFirstName()
					+ ContactManagementConstants.SPACE_STRING
					+ provider.getName().getLastName());
			}
		} else {
			crspdProviderVO.setName(provider.getOrganizationName());
		}
		crspdProviderVO.setCurrAltID(provider.getReportingAlternateID());
		if(logger.isInfoEnabled())
		{
		logger.info("CurrAltID in Provider is-------->"
				+ crspdProviderVO.getCurrAltID());
		}
		//CR_ESPRD00439068_UC-PGM-CRM-033_13APR2010
		//crspdProviderVO.setEntityId(provider.getReportingAlternateID().toString());
		if(provider!=null && provider.getCommonEntity()!=null
				&& provider.getCommonEntity().getCommonEntitySK()!=null){
			crspdProviderVO.setEntityId(provider.getCommonEntity().getCommonEntitySK().toString());
		}else{
			crspdProviderVO.setEntityId(ContactManagementConstants.EMPTY_STRING);
		}
		//Eof CR_ESPRD00439068_UC-PGM-CRM-033_13APR2010
		if(logger.isInfoEnabled())
		{
		logger.info("Setting CurrAltId as EntityId of crspdProviderVO :"+crspdProviderVO.getEntityId());
		}
		
		//Defect ID:ESPRD00625281
		if(getCorrespondenceDataBean().isInqAbtForProvider() && provider.getReportingAlternateID() != null)
		{
			crspdProviderVO.setEntityId(provider.getReportingAlternateID().toString());
		}
		
		/*
		 * Change done for ES2 DO changes
		 */
		//crspdProviderVO.setPayerID(provider.getCommonEntity().getPayerID());
		//condition added for payer
		if ((provider.getCommonEntity().getFinancialInformation() != null)
				&& (provider.getCommonEntity().getFinancialInformation().size() != 0)) {
			if(logger.isInfoEnabled())
			{
			logger.info("Provider System Id" + provider.getSysID());
			}
			Set payerSet = provider.getCommonEntity().getFinancialInformation();
			Iterator payerItr = payerSet.iterator();
			FinancialInformation payer = (FinancialInformation) payerItr.next();
			if(logger.isInfoEnabled())
			{
			logger.info("Payer Id" + payer.getCommonEntitySK());
			}
			if (payer.getCommonEntitySK() != null)
				crspdProviderVO
						.setPayerID(payer.getCommonEntitySK().toString());
		}

		crspdProviderVO.setIdType(provider.getCurrentAltIDTypeCode());
		
		String idTypeDescription = ReferenceServiceDelegate
		.getReferenceSearchShortDescription(
				FunctionalAreaConstants.PROVIDER,
				ReferenceServiceDataConstants.P_ALT_ID,
				new Long(1017), //for CR ESPRD00703521
				provider.getCurrentAltIDTypeCode());
		
		crspdProviderVO.setIdTypeStr(idTypeDescription);
		if(logger.isInfoEnabled())
			
		{
		logger.info("ID Type in Provider is-------->"
				+ crspdProviderVO.getIdType());
		logger.info("ID Type Description  in Provider is-------->"
				+ crspdProviderVO.getIdType());
		}

		Set setOfProviderTypes = provider.getProviderTypes();
		if (setOfProviderTypes != null) {
			for (Iterator iter = setOfProviderTypes.iterator(); iter.hasNext();) {
				ProviderType provType = (ProviderType) iter.next();
				crspdProviderVO.setProviderType(provType.getProviderTypeCode());
							
				
				
				
			}
		}
		
		
		String providerTypeDesc = ReferenceServiceDelegate
		.getReferenceSearchShortDescription(
				FunctionalAreaConstants.PROVIDER,
				ReferenceServiceDataConstants.P_TY_CD,
				new Long(1062),
				crspdProviderVO.getProviderType());
		crspdProviderVO.setProviderTypeStr(providerTypeDesc);
		

		Set setOfProviderSpecialties = provider.getBoardCertifiedSpeciality();
		if (setOfProviderSpecialties != null) {
			for (Iterator iter = setOfProviderSpecialties.iterator(); iter
					.hasNext();) {
				BoardCertifiedSpeciality provSpcl = (BoardCertifiedSpeciality) iter
						.next();
				
				crspdProviderVO.setSpecialty(provSpcl.getSpecialityCode());
				
				break; // Get the first speciality
			}
		}
		
		String specialityDesc = ReferenceServiceDelegate
		.getReferenceSearchShortDescription(
				FunctionalAreaConstants.PROVIDER,
				ReferenceServiceDataConstants.P_SPECL_CD,
				new Long(1054),
				crspdProviderVO.getSpecialty());
		crspdProviderVO.setSpecialtyStr(specialityDesc);

		Set setOfEnrollmentStatus = provider.getEnrollmentStatus();
		if (setOfEnrollmentStatus != null) {
			for (Iterator iter = setOfEnrollmentStatus.iterator(); iter
					.hasNext();) {
				EnrollmentStatus enrollStatus = (EnrollmentStatus) iter.next();
				if(enrollStatus.getEnrollmentBeginDate().before(new Date()) && 
						enrollStatus.getEnrollmentEndDate().after(new Date()))
				{
					
					crspdProviderVO.setStatus(enrollStatus.getEnrollmentStatusCode());
					break;
				}

			}
		}

		String statusDesc = ReferenceServiceDelegate
		.getReferenceSearchShortDescription(
				FunctionalAreaConstants.PROVIDER,
				ReferenceServiceDataConstants.PROVIDER_STATUS,
				new Long(1018),
				crspdProviderVO.getStatus());
		
		
		crspdProviderVO.setStatsuSstr(statusDesc);
		
		CommonEntity commonEntity = provider.getCommonEntity();
		crspdProviderVO.setCmEntityID(commonEntity.getCommonEntitySK()
				.toString());

		return crspdProviderVO;
	}

	/**
	 * @param provider
	 *            The provider to set.
	 */
	public static void setAlternateIds(Provider provider) {
		getCorrespondenceDataBean().getListOfAlternateIds().clear();
		Set setProviderAltIdsList = provider.getAlternateIdInfo();
		
		if (setProviderAltIdsList != null) {

			for (Iterator iter = setProviderAltIdsList.iterator(); iter
					.hasNext();) {
				AlternateIdInfo altId = (AlternateIdInfo) iter.next();
				AlternateIdentifiersVO altIdVO = new AlternateIdentifiersVO();

				altIdVO.setAlternateID(altId.getAlternateID());
				altIdVO.setAlternateIDTypeCode(altId.getTypeCode());
				
				String idTypeDescription = ReferenceServiceDelegate
				.getReferenceSearchShortDescription(
						FunctionalAreaConstants.PROVIDER,
						ReferenceServiceDataConstants.P_ALT_ID,
						new Long(1002),
						altIdVO.getAlternateIDTypeCode());
				altIdVO.setAlternateIDTypeCodestr(idTypeDescription);
				
				getCorrespondenceDataBean().getListOfAlternateIds()
						.add(altIdVO);
				//altIdVO.setLineOfBusiness() - is not applicable for Provider.
			}
		}
	}

	/**
	 * @param member
	 *            The member to set.
	 * @return CorrespondenceForMemberVO
	 */
	public static CorrespondenceForMemberVO getCrspdMemberVO(MemberBasicInfo memberBasicInfo) {
		/** Enterprise Logger for Logging. */
		EnterpriseLogger logger = EnterpriseLogFactory
				.getLogger(CorrespondenceDOConvertor.class);

		
		CorrespondenceForMemberVO correspondenceForMemberVO = new CorrespondenceForMemberVO();

		if (memberBasicInfo == null) {
			return correspondenceForMemberVO;
		}

		DateFormat dateFormat = new SimpleDateFormat(
				ContactManagementConstants.DATE_FORMAT);

		// TODO populate contact list drop down.
		// correspondenceForMemberVO.setAnb() - not required in VO

		/*
		 * correspondenceForMemberVO.setMailAddress() - will be set by the
		 * calling method. correspondenceForMemberVO.setResAddress() - will be
		 * set by the calling method.
		 */

		correspondenceForMemberVO
				.setEntityTypeCode(ContactManagementConstants.ENTITY_TYPE_MEM);
		String shortDesc = ReferenceServiceDelegate
				.getReferenceSearchShortDescription(
						FunctionalAreaConstants.GENERAL,
						ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
						new Long(80),
						ContactManagementConstants.ENTITY_TYPE_MEM);
		
		correspondenceForMemberVO.setEntityTypeDesc(shortDesc);

		correspondenceForMemberVO.setEntitySysId(memberBasicInfo.getMemSysID()
				.toString());
		correspondenceForMemberVO.setCurrAltID(memberBasicInfo.getCurrAltID());
		if(logger.isInfoEnabled())
		{
		logger.info("CurrAltID in Member is-------->"
				+ correspondenceForMemberVO.getCurrAltID());
		}
		//CR_ESPRD00439068_UC-PGM-CRM-033_13APR2010
		//correspondenceForMemberVO.setEntityId(member.getCurrAltID());
		if(memberBasicInfo!=null && memberBasicInfo.getCmnEntySK()!=null){
			correspondenceForMemberVO.setEntityId(memberBasicInfo.getCmnEntySK().toString());
		}else{
			correspondenceForMemberVO.setEntityId(ContactManagementConstants.EMPTY_STRING);
		}
		//EOF CR_ESPRD00439068_UC-PGM-CRM-033_13APR2010
		if(logger.isInfoEnabled())
		{
		logger.info("Setting CurrAltID as EntityID of correspondenceForMemberVO is-------->"
				+ correspondenceForMemberVO.getEntityId());
		}
		correspondenceForMemberVO
				.setContact(ContactManagementConstants.EMPTY_STRING);

		correspondenceForMemberVO.setIdType(memberBasicInfo.getCurrAltIDTypeCD());
		if(logger.isInfoEnabled())
		{
		logger.info("ID Type in Member is-------->"
				+ correspondenceForMemberVO.getIdType());
		}
		String idTypeDesc = ReferenceServiceDelegate
		.getReferenceSearchShortDescription(
				FunctionalAreaConstants.GENERAL,
				ReferenceServiceDataConstants.B_ALT_ID_TY_CD,
				new Long(83),
				memberBasicInfo.getCurrAltIDTypeCD());
		correspondenceForMemberVO.setIdTypeDesc(idTypeDesc);

		/*Set setOfPreviousNames = member.getPreviousName();
		if (setOfPreviousNames != null) {
			for (Iterator iter = setOfPreviousNames.iterator(); iter.hasNext();) {
				PreviousName prevName = (PreviousName) iter.next();
				Name name = prevName.getName();*/
		    Name name =memberBasicInfo.getPreviousName();
			if(name!=null)
			{
		    if(name.getMiddleName()!=null)
		    	{
				correspondenceForMemberVO.setPreviousName(name.getFirstName()
						+ ContactManagementConstants.SPACE_STRING
						+ name.getMiddleName()
						+ ContactManagementConstants.SPACE_STRING
						+ name.getLastName());
		    	}else{
				
				correspondenceForMemberVO.setPreviousName(name.getFirstName()
						+ ContactManagementConstants.SPACE_STRING
						+ name.getLastName());
		    	}	
			}
		//		break; // Get the first Previous Name
		//	}
		//}
		/*Set setOfEligibilitySpans = member.getEligibilitySpans();
		if (setOfEligibilitySpans != null) {
			for (Iterator iter = setOfEligibilitySpans.iterator(); iter
					.hasNext();) {
				EligibilitySpan eligibitySpan = (EligibilitySpan) iter.next();
				correspondenceForMemberVO
						.setCategoryOfEligibility(eligibitySpan.getCoeCode());
				if (eligibitySpan.getVoidIndicator().booleanValue()) {
					correspondenceForMemberVO.setStatus("Inactive");
					correspondenceForMemberVO.setStatusStr("I-Inactive");
					
				} else {
					correspondenceForMemberVO.setStatus("Active");
					correspondenceForMemberVO.setStatusStr("A-Active");
				}

				if(eligibitySpan.getCaseInfo() != null)
				{
					Set setOfCaseOwners = eligibitySpan.getCaseInfo()
							.getCaseOwner();
	
					if (setOfCaseOwners != null) {
						for (Iterator iterator = setOfCaseOwners.iterator(); iterator
								.hasNext();) {
							CaseOwner caseOwner = (CaseOwner) iterator.next();*/
		               	correspondenceForMemberVO.setCategoryOfEligibility(memberBasicInfo.getCoeCD());				
		             	correspondenceForMemberVO.setDistrictOffice(memberBasicInfo.getDistrictOfficeCD());
		             	if(logger.isDebugEnabled())
		             	{
							logger.debug("DistrictOffice:::::::: "+ correspondenceForMemberVO.getDistrictOffice());
		             	}
							String DistrictOfficeDesc = ReferenceServiceDelegate
							.getReferenceSearchShortDescription(
									FunctionalAreaConstants.MEMBER,
									ReferenceServiceDataConstants.B_CASE_DSTCT_OFC_CD,
									new Long(1015),
									correspondenceForMemberVO.getDistrictOffice());
							correspondenceForMemberVO.setDistrictOfficedesc(DistrictOfficeDesc);
							
						/*	logger.debug(" DistrictOfficeDesc:::::::::::::;;"+DistrictOfficeDesc);
							break; // Get the first CaseOwner District Office.
						}
					}
				}

				break; // Get the first Category of Eligibility
			}
		}*/

		/*DemographicInformation demographicInformation = member
				.getDemographicInformation();
*/
		correspondenceForMemberVO.setDob(dateFormat
				.format(memberBasicInfo.getDob()));
		correspondenceForMemberVO.setName(memberBasicInfo.getFirstName()
				+ ContactManagementConstants.SPACE_STRING
				+ memberBasicInfo.getLastName());

		/*Set setOfAlternateIds = demographicInformation.getAlternateMemberID();
		if (setOfAlternateIds != null) {
			for (Iterator iter = setOfAlternateIds.iterator(); iter.hasNext();) {
				AlternateMemberID altId = (AlternateMemberID) iter.next();
				AlternateIdentifiersVO altIdVO = new AlternateIdentifiersVO();

				if ("SSN".equalsIgnoreCase(altId.getAltIDTypeCode())) {
					correspondenceForMemberVO.setSocialSecurityNumber(altId
							.getAltID());
				}
			}
		}*/
		
		List  memberBasicInfolist = memberBasicInfo.getAlternativeIDs();
		for(Iterator iter = memberBasicInfolist.iterator(); iter.hasNext();)
			
		{
			AlternateMemberID altdetails = (AlternateMemberID) iter.next();
	
			if (altdetails.getAltIDTypeCode()!=null && "SSN".equalsIgnoreCase(altdetails.getAltIDTypeCode())) 
			{
				correspondenceForMemberVO.setSocialSecurityNumber(altdetails.getAltID());
				break;
			}
	   }

		/*CommonEntity commonEntity = member.getCommonEntity();

		Set setOfeAddressUsage = commonEntity.geteAddressUsage();
		if (setOfeAddressUsage != null) {
			for (Iterator iter = setOfeAddressUsage.iterator(); iter.hasNext();) {
				EAddressUsage eAddrUsage = (EAddressUsage) iter.next();
				correspondenceForMemberVO.setEmail(eAddrUsage.geteAddress()
						.geteAddressText());

				break; // get the first email.
			}
		}

		correspondenceForMemberVO.setCmEntityID(commonEntity
				.getCommonEntitySK().toString());*/
		
		correspondenceForMemberVO.setEmail(memberBasicInfo.getEmail());
		correspondenceForMemberVO.setCmEntityID(memberBasicInfo.getCmnEntySK().toString());

		CorrespondenceDOConvertor correspondenceDOConvertor = new CorrespondenceDOConvertor();
		String langDesc = correspondenceDOConvertor.getLangDesc(memberBasicInfo.getPrimaryLang());
		correspondenceForMemberVO.setPrimaryLanguage(langDesc);

		return correspondenceForMemberVO;
	}

	//ADDED FOR THE Correspondence ESPRD00436016
	/**
     * @param entityId
     *            The entityId to set.
     * @param callingEntity
     *            The callingEntity to set.
     * @return CorrespondenceForTradingPartnerVO
     */
    protected CorrespondenceForTradingPartnerVO getCorrespondenceForTradingPartner(
            String entityId, boolean callingEntity)
    {
    	CorrespondenceForTradingPartnerVO tradPartVO = null;
    	CMDelegate cmDelegate = new CMDelegate();
        TradingPartner tradePartner = null;   
        ProviderBasicInfo providerBasicInfo=null;
		ProviderEnrollmentDelegate providerEnrollmentDelegate= new ProviderEnrollmentDelegate();
		if(logger.isDebugEnabled())
		{
        logger.debug("============entityID=========="+entityId); 
		}
    	try {
    		if(entityId!=null){
    			Long eID=new Long(entityId);
    			//tradePartner = cmDelegate.getTradingPartner(null,eID);
    			providerBasicInfo=providerEnrollmentDelegate.getTradingPartnerDetailsForCommonEntitySk(null, eID);
    			if(logger.isDebugEnabled())
    			{
    			logger.debug("============tradePartner=========="+providerBasicInfo);
    			}
    			if(providerBasicInfo!=null){
    				tradPartVO = CorrespondenceDOConvertor.getCrspdTradingPartnerVO(providerBasicInfo,entityId);    				
    			}
    		}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (EnterpriseBaseDataException e) {
			e.printStackTrace();
		}     	
    	return tradPartVO;
    }
    
	public static CorrespondenceForTradingPartnerVO getCrspdTradingPartnerVO(ProviderBasicInfo providerBasicInfo, String cmentitysk){
		
		/** Enterprise Logger for Logging. */
		EnterpriseLogger logger = EnterpriseLogFactory
				.getLogger(CorrespondenceDOConvertor.class);
		CorrespondenceForTradingPartnerVO tradePartneVO= new CorrespondenceForTradingPartnerVO();
		
		if(providerBasicInfo!=null
        		&& providerBasicInfo.getCommonEntitySK()!=null){
			if(logger.isDebugEnabled())
			{
			logger.debug(" tradingPartner.getCommonEntity().getCommonEntitySK() : "+providerBasicInfo.getCommonEntitySK());
			}
			tradePartneVO.setEntityId(String.valueOf(providerBasicInfo.getCommonEntitySK()));
			tradePartneVO.setCommonEntitySK(String.valueOf(providerBasicInfo.getCommonEntitySK()));
			tradePartneVO.setCmEntityID(String.valueOf(providerBasicInfo.getCommonEntitySK()));
			/* Below line of code added for defect ESPRD00840374  to populate data in correspondence 
			for section of Additional correspondence for entity type TD */
			tradePartneVO.setEntitySysId(providerBasicInfo.getCommonEntitySK().toString());
        }else{
        	tradePartneVO.setEntityId(ContactManagementConstants.EMPTYSTRING);
        }
        tradePartneVO.setEntityTypeCode(ContactManagementConstants.ENTITY_TYPE_TD);
        tradePartneVO.setEntityTypeDesc(ContactManagementConstants.ENTITY_TYPE_TD+ContactManagementConstants.HYPEN+"TrdngPartn");
        if(providerBasicInfo.getSortName()!=null){
        	tradePartneVO.setName(providerBasicInfo.getSortName());	        	
        }
        if(providerBasicInfo.getClassification()!=null){
        	tradePartneVO.setClassification(providerBasicInfo.getClassification());
        	
        	String classificationstr = ReferenceServiceDelegate
			.getReferenceSearchShortDescription(
					 FunctionalAreaConstants.EDI_TRADING,
					"1003",
					new Long(1003),
					tradePartneVO.getClassification());
        	tradePartneVO.setClassificationstr(classificationstr);
        	
        }
        if(providerBasicInfo.getTradingPartnerId()!=null){
        	tradePartneVO.setTradingPartnerId(providerBasicInfo.getTradingPartnerId());
        }
        if(providerBasicInfo.getApprovalProcessStatusCode()!=null){
        	tradePartneVO.setStatus(providerBasicInfo.getApprovalProcessStatusCode());
        	String statusstr = ReferenceServiceDelegate
			.getReferenceSearchShortDescription(
					 FunctionalAreaConstants.EDI_TRADING,
					"003",
					new Long(003),
					tradePartneVO.getStatus());
        	//find bug issue
        	tradePartneVO.setStatusstr(statusstr);
        	
        	
        }
        
        return tradePartneVO;
	}
	//END FOR THE Correspondence ESPRD00436016
   

	/**
	 * @param member
	 *            The member to set.
	 */
	public static void setAlternateIds(MemberBasicInfo memberBasicInfo) {
		/** Enterprise Logger for Logging. */
		EnterpriseLogger logger = EnterpriseLogFactory
				.getLogger(CorrespondenceDOConvertor.class);

		

		getCorrespondenceDataBean().getListOfAlternateIds().clear();
		/*DemographicInformation demographicInformation = member
				.getDemographicInformation();
		Set setOfAlternateIds = demographicInformation.getAlternateMemberID();*/
		Set setOfAlternateIds = new HashSet(memberBasicInfo.getAlternativeIDs());
		if (setOfAlternateIds != null) {
			for (Iterator iter = setOfAlternateIds.iterator(); iter.hasNext();) {
				AlternateMemberID altId = (AlternateMemberID) iter.next();
				AlternateIdentifiersVO altIdVO = new AlternateIdentifiersVO();

				if (!"SSN".equalsIgnoreCase(altId.getAltIDTypeCode())) {
					altIdVO.setAlternateID(altId.getAltID());
					altIdVO.setAlternateIDTypeCode(altId.getAltIDTypeCode());
					altIdVO.setAlternateIDTypeCodestr(ReferenceServiceDelegate
							.getReferenceSearchShortDescription(
									FunctionalAreaConstants.GENERAL,
									ReferenceServiceDataConstants.B_ALT_ID_TY_CD,
									new Long(83),
									altId.getAltIDTypeCode()));
					
					//  getCorrespondenceDataBean().getListOfAlternateIds().add(
					//        altIdVO);
					altIdVO.setLineOfBusiness(altId.getLobCode());
					//commented for chnage in Alternate ID domain object
					/*
					 * + ContactManagementConstants.SPACE_STRING +
					 * ContactManagementConstants.HYPHEN_SEPARATOR +
					 * ContactManagementConstants.SPACE_STRING + altId.);
					 */

					getCorrespondenceDataBean().getListOfAlternateIds().add(
							altIdVO);
				}
			}
		}
	}

	/**
	 * @param specificEntity
	 *            The specificEntity to set.
	 * @return CorrespondenceForVO
	 */
	public static CorrespondenceForVO getCrspdForVO(
			SpecificEntity specificEntity) {
		/** Enterprise Logger for Logging. */
		EnterpriseLogger logger = EnterpriseLogFactory
				.getLogger(CorrespondenceDOConvertor.class);

		

		CorrespondenceForVO correspondenceForVO = new CorrespondenceForVO();

		if (specificEntity == null) {
			return correspondenceForVO;
		}
		correspondenceForVO.setEntityTypeCode(specificEntity
				.getSpecificEntityTypeCode());
		
		String shortDesc = ReferenceServiceDelegate
				.getReferenceSearchShortDescription(
						FunctionalAreaConstants.GENERAL,
						ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
						new Long(80), specificEntity
								.getSpecificEntityTypeCode());
		
		correspondenceForVO.setEntityTypeDesc(shortDesc);
		correspondenceForVO.setEntitySysId(specificEntity.getSpecificEntitySK()
				.toString());
		correspondenceForVO.setEntityTypeCodeForNote(correspondenceForVO.getEntitySysId());

		correspondenceForVO.setEntityId(specificEntity.getSpecificEntitySK()
						.toString());
		if(logger.isInfoEnabled())
		{
		logger.info("Setting as EntitySysId to EntityId correspondenceForVO :"+correspondenceForVO.getEntityId());
		}

		if(specificEntity.getOrganizationName() != null)
		{
			correspondenceForVO.setName(specificEntity.getOrganizationName());
		}else if (specificEntity.getName() != null) {
			if(specificEntity.getName().getMiddleName()!= null)
			{
			correspondenceForVO.setName(specificEntity.getName().getFirstName()
					+ ContactManagementConstants.SPACE_STRING 
					+ specificEntity.getName().getMiddleName()
					+ ContactManagementConstants.SPACE_STRING 
					+ specificEntity.getName().getLastName());
			}else
			{
				correspondenceForVO.setName(specificEntity.getName().getFirstName()
						+ ContactManagementConstants.SPACE_STRING
						+ specificEntity.getName().getLastName());	
			
			}
		}
		correspondenceForVO.setNameForNote(correspondenceForVO.getName());
		CommonEntity commonEntity = specificEntity.getCommonEntity();

		correspondenceForVO.setCmEntityID(commonEntity.getCommonEntitySK()
				.toString());
		correspondenceForVO.setProviderType(specificEntity
				.getProviderTypeCode());
		if(logger.isDebugEnabled())
		{
		logger.debug("correspondenceForVO.getProviderType():::::::::: "+correspondenceForVO.getProviderType());
		}
		
		
		String providerTypeDesc = ReferenceServiceDelegate
		.getReferenceSearchShortDescription(
				FunctionalAreaConstants.PROVIDER,
				ReferenceServiceDataConstants.PROV_TY_CD,
				new Long(1062),
				correspondenceForVO.getProviderType());
		correspondenceForVO.setProviderTypeStr(providerTypeDesc);
		if(logger.isDebugEnabled())
		{
		logger.debug("providerTypeDesc::::::::::::::"+providerTypeDesc);
		}
		//For fixing defect ESPRD00576206
		//String givenSSN="123456789";
		String givenSSN=specificEntity.getSsnNumber();
		if(givenSSN !=null)
		{
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<givenSSN.length();i++){
				if(i==2 || i==4){
					sb.append(givenSSN.charAt(i)+"-");
				}else{
					sb.append(givenSSN.charAt(i));
				}
			}
			correspondenceForVO.setSsn(sb.toString());
		}
		else
		{
			correspondenceForVO.setSsn(givenSSN);
		}
		//correspondenceForVO.setSsn(specificEntity.getSsnNumber());

		Set setOfeAddressUsage = commonEntity.geteAddressUsage();
		if (setOfeAddressUsage != null) {
			for (Iterator iter = setOfeAddressUsage.iterator(); iter.hasNext();) {
				EAddressUsage eAddrUsage = (EAddressUsage) iter.next();
				correspondenceForVO.setEmail(eAddrUsage.geteAddress()
						.geteAddressText());

				break; // get the first email.
			}
		}
		if(logger.isDebugEnabled())
		{
		logger.debug("SSN in Cor Converter--->" + correspondenceForVO.getSsn());
		logger.debug("Email in Cor Converter--->"
				+ correspondenceForVO.getEmail());
		}

		return correspondenceForVO;
	}

	/**
	 * @param tplCarrier
	 *            The tplCarrier to set.
	 * @return CorrespondenceForVO
	 * Code modifidied for TPL-API
	 */
	public static CorrespondenceForVO getCrspdForTPLVO(TPLCarrierNoteVO tplCarrierNoteVO) {
		/** Enterprise Logger for Logging. */
		EnterpriseLogger logger = EnterpriseLogFactory
				.getLogger(CorrespondenceDOConvertor.class);

		

		CorrespondenceForVO correspondenceForTPLVO = new CorrespondenceForVO();

		if (tplCarrierNoteVO == null) {
			return correspondenceForTPLVO;
		}

		correspondenceForTPLVO.setCarrierID(tplCarrierNoteVO.getCarrierID());
		correspondenceForTPLVO.setCarrierName(tplCarrierNoteVO.getCarrierName());
		correspondenceForTPLVO.setName(tplCarrierNoteVO.getCarrierName());
		correspondenceForTPLVO
				.setEntityTypeCode(ContactManagementConstants.ENTITY_TYPE_TPL);
		correspondenceForTPLVO.setEntityTypeCodeForNote(correspondenceForTPLVO.getCarrierID());
		correspondenceForTPLVO.setNameForNote(correspondenceForTPLVO.getName());
		correspondenceForTPLVO.setEntitySysId(tplCarrierNoteVO.getCarrierID());
		String shortDesc = ReferenceServiceDelegate
				.getReferenceSearchShortDescription(
						FunctionalAreaConstants.GENERAL,
						ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
						new Long(80),
						ContactManagementConstants.ENTITY_TYPE_TPL);
		
		correspondenceForTPLVO.setEntityTypeDesc(shortDesc);
		if (tplCarrierNoteVO.getCommonSK() != null) 
		{
			correspondenceForTPLVO.setCmEntityID(tplCarrierNoteVO.getCommonSK().toString());
			correspondenceForTPLVO.setEntityId(tplCarrierNoteVO.getCommonSK().toString());
		}
		if(logger.isInfoEnabled())
		{
		logger.info("CarrierID in DO converter-------->"
				+ correspondenceForTPLVO.getCarrierID());
		logger.info("CarrierName in DO converter-------->"
				+ correspondenceForTPLVO.getCarrierName());
		}

		return correspondenceForTPLVO;
	}

	/**
	 * @param specificEntitySK
	 *            specificEntitySK to set
	 */
	public static void getContactsForEntities(Long specificEntitySK) {

		/** Enterprise Logger for Logging. */
		EnterpriseLogger logger = EnterpriseLogFactory
				.getLogger(CorrespondenceDOConvertor.class);
		if(logger.isInfoEnabled())
		{
		logger.info("getContactsForEntities------>" + specificEntitySK);
		}

		CMDelegate cmDelegate = new CMDelegate();
		List contactList = null;
		List tempContactList = new ArrayList();
		try {
			contactList = cmDelegate.getContacts(specificEntitySK);
			if(logger.isInfoEnabled())
			{
			logger.info("contactList in converter" + contactList);
			}
			if (contactList != null) {
				if(logger.isInfoEnabled())
				{
				logger.info("contactList size in converter---->"
						+ contactList.size());
				}
			}
		} catch (CorrespondenceRecordFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		} catch (NumberFormatException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}
		if (contactList != null && contactList.size() > 0) {
			for (Iterator iter = contactList.iterator(); iter.hasNext();) {
				Representative representativeDO = (Representative) iter.next();
				RepresentativeVO representativeVO = new RepresentativeVO();
				Name nameDO = representativeDO.getName();
				StringBuffer name = new StringBuffer(
						ContactManagementConstants.EMPTY_STRING);

				if (nameDO != null) {
					
					if (nameDO.getFirstName() != null) {
						
						name.append(nameDO.getFirstName());
						name.append(ContactManagementConstants.SPACE_STRING);
					}
					
					if (nameDO.getMiddleName() != null) {
						
						name.append(nameDO.getMiddleName());
						name.append(ContactManagementConstants.SPACE_STRING);
					}
										
					if (nameDO.getLastName() != null) {
						logger.info("LastName--->" + nameDO.getLastName());
						name.append(nameDO.getLastName());
					}
					
					
					representativeVO.setRepresentativeSK(representativeDO
							.getRepresentativeSK());

					tempContactList
							.add(new SelectItem(representativeVO
									.getRepresentativeSK().toString(), name
									.toString()));
				}
			}
			if(logger.isInfoEnabled())
			{
				logger.info("tempContactList size--->" + tempContactList.size());
			}
			sortContact(tempContactList);
			getCorrespondenceDataBean().setContactList(tempContactList);

			if (contactList != null && contactList.size() == 1) {
				Representative representativeDO = (Representative) contactList
						.get(0);
				if (representativeDO != null) {
					getCorrespondenceDataBean().getCorrespondenceRecordVO()
							.getCorrespondenceForVO().setContact(
									representativeDO.getRepresentativeSK()
											.toString());
				}
			}
		}
	}

	/**
	 * @param commonEntity
	 *            The commonEntity to set.
	 */
	public static void setCommonContactList(CommonEntity commonEntity) {
		ContactHelper contactHelper = new ContactHelper();

		/** Enterprise Logger for Logging. */
		EnterpriseLogger logger = EnterpriseLogFactory
				.getLogger(CorrespondenceDOConvertor.class);

		

		CommonEntityVO commonEntityVO = contactHelper
				.convertCERepDomainToCommonContactsVO(commonEntity);

		CommonEntityDataBean commonEntityDataBean = getCommonEntityDataBean();
		commonEntityDataBean.setCommonEntityVO(commonEntityVO);

		List listOfContactVOs = commonEntityVO.getContactVOList();
		List listOfContacts = getCorrespondenceDataBean().getListOfContacts();
		listOfContacts.clear();

		if (listOfContactVOs != null) {
			for (Iterator iter = listOfContactVOs.iterator(); iter.hasNext();) {
				CommonContactVO commonContactVO = (CommonContactVO) iter.next();

				StringBuffer name = new StringBuffer(
						ContactManagementConstants.EMPTY_STRING);
				NameVO nameVO = commonContactVO.getNameVO();
				
				if(nameVO != null)
				{
					if (nameVO.getFirstName() != null) {
						name.append(nameVO.getFirstName());
					}
					if (nameVO.getMiddleName() != null) {
						name.append(ContactManagementConstants.SPACE_STRING);
						name.append(nameVO.getMiddleName());
					}
					if (nameVO.getLastName() != null) {
						name.append(ContactManagementConstants.SPACE_STRING);
						name.append(nameVO.getLastName());
					}
					if(!ContactManagementConstants.EMPTY_STRING.equalsIgnoreCase(name.toString()))
					{
						listOfContacts.add(new SelectItem(commonContactVO
								.getContactSK().toString(), name.toString()));
						if(listOfContacts!=null)
						{
						 sortContact(listOfContacts);	
						}
					}
				}
			}
		}

		// getCorrespondenceDataBean().getListOfEntityCommonContacts().clear();
		// getCorrespondenceDataBean().setListOfEntityCommonContacts(listOfContactVOs);
	}

	/**
	 * This method is used to create the depts lists based on UserID.
	 * 
	 * @param userSK :
	 *            The userSK to set.
	 */
	public void setDepartmentsList(String userSK) {
		

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		DeptUserBasicInfo deptUserBasicInfo = new DeptUserBasicInfo();
		List deptsList = new ArrayList();
		try {

			// Code Commented for CM-API
			/*
			 * List deptsList =
			 * contactMaintenanceDelegate.getDepartmentUsers(Long
			 * .valueOf(userSK));
			 */
			if (userSK != null && !("".equals(userSK))) {
			
				// code added for CM-API
				deptUserBasicInfo.setUserEnterpriseSK(Long.valueOf(userSK));
				deptUserBasicInfo.setDataReqested(ProgramConstants.DUBI_Dept_Name_SK);
				
				deptUserBasicInfo= contactMaintenanceDelegate.getDeptUserBasicInfo(deptUserBasicInfo);
	               if(deptUserBasicInfo != null)
	                {
	                	deptsList= deptUserBasicInfo.getDeptNameSKList();
	                }
	               if(logger.isDebugEnabled())
	               {
	            	   	logger.debug("Dept list size" + deptsList.size());
	               }

				if (deptsList != null) {
					List listOfDepartments = getCorrespondenceDataBean()
							.getDepartmentsList();

					listOfDepartments.clear();

					for (Iterator iter = deptsList.iterator(); iter.hasNext();) {
						//DepartmentUser deptUser = (DepartmentUser) iter.next();
						
						// code added for CM-API
						Object[] deptData = (Object[]) iter.next();
						
						String name= deptData[0].toString();
						Long deptsk= (Long) deptData[1];
						Long lobSk= (Long) deptData[2];
						
						Map mapOfDeptAndLobHier = getCorrespondenceDataBean()
								.getMapOfDeptAndLobHier();
						
						mapOfDeptAndLobHier.put(deptsk.toString(), lobSk.toString());

						listOfDepartments.add(new SelectItem(deptsk.toString(), name));
					}
					
					// code Commented for CM-API
					/*for (Iterator iter = deptsList.iterator(); iter.hasNext();) {
						DepartmentUser deptUser = (DepartmentUser) iter.next();

						Map mapOfDeptAndLobHier = getCorrespondenceDataBean()
								.getMapOfDeptAndLobHier();

						mapOfDeptAndLobHier.put(deptUser.getDepartmentSK()
								.toString(), deptUser.getDepartment()
								.getLineOfBusinessHierarchy()
								.getLineOfBusinessHierarchySK().toString());

						listOfDepartments.add(new SelectItem(deptUser
								.getDepartmentSK().toString(), deptUser
								.getDepartment().getName()));
					}*/
				}
			}
		} catch (LOBHierarchyFetchBusinessException lobExp) {
			if(logger.isErrorEnabled())
			{
			logger.error(lobExp.getMessage(), lobExp);
			}
		}
	}

	/**
	 * This method is to create an instance of Data Bean.
	 * 
	 * @return Returns CorrespondenceDataBean
	 */
	protected static CorrespondenceDataBean getCorrespondenceDataBean() {
		FacesContext fc = FacesContext.getCurrentInstance();
		CorrespondenceDataBean correspondenceDataBean = (CorrespondenceDataBean) fc
				.getApplication().createValueBinding(
						"#{" + ContactManagementConstants.CORRESPONDENCE_BEAN_NAME + "}")
				.getValue(fc);
		return correspondenceDataBean;
	}

	/**
	 * This method is to create an instance of Data Bean.
	 * 
	 * @return Returns SearchCorrespondenceDataBean
	 */
	protected static SearchCorrespondenceDataBean getSearchCorrespondenceDataBean() {
		FacesContext fc = FacesContext.getCurrentInstance();
		SearchCorrespondenceDataBean searchCorrespondenceDataBean = (SearchCorrespondenceDataBean) fc
				.getApplication().createValueBinding(
						"#{" + ContactManagementConstants.SEARCHCORRESPONDENCE_BEAN_NAME + "}")
				.getValue(fc);
		return searchCorrespondenceDataBean;
	}

	/**
	 * To get the commonentityDatatBean.
	 * 
	 * @return CommonEntityDataBean.
	 */

	private static CommonEntityDataBean getCommonEntityDataBean() {
		FacesContext fc = FacesContext.getCurrentInstance();
		CommonEntityDataBean commonEntityDataBean = (CommonEntityDataBean) fc
				.getApplication().createValueBinding(
						"#{" + ProgramConstants.COMMONENTITYDATABEAN + "}")
				.getValue(fc);
		return commonEntityDataBean;
	}

	/**
	 * This method is used to get the Routing Data Bean.
	 * 
	 * @return RoutingDataBean : RoutingDataBean object.
	 */
	protected RoutingDataBean getRoutingDataBean() {
		

		FacesContext fc = FacesContext.getCurrentInstance();
		return ((RoutingDataBean) fc.getApplication().createValueBinding(
				ContactManagementConstants.BINDING_BEGIN_SEPARATOR
						+ ContactManagementConstants.ROUTING_DATA_BEAN_NAME
						+ ContactManagementConstants.BINDING_END_SEPARATOR)
				.getValue(fc));
	}

	/**
	 * This method is used to get the Alert Data Bean.
	 * 
	 * @return AlertDataBean : AlertDataBean object.
	 */
	protected AlertDataBean getAlertDataBean() {
		

		FacesContext fc = FacesContext.getCurrentInstance();
		return ((AlertDataBean) fc.getApplication().createValueBinding(
				ContactManagementConstants.BINDING_BEGIN_SEPARATOR
						+ ContactManagementConstants.ALERT_DATA_BEAN_NAME
						+ ContactManagementConstants.BINDING_END_SEPARATOR)
				.getValue(fc));
	}

	/**
	 * This method is used to get the Alert Data Bean.
	 * 
	 * @return AlertDataBean : AlertDataBean object.
	 */
	protected AttachmentDataBean getAttachmentDataBean() {
		

		FacesContext fc = FacesContext.getCurrentInstance();
		return ((AttachmentDataBean) fc.getApplication().createValueBinding(
				ContactManagementConstants.BINDING_BEGIN_SEPARATOR
						+ ContactManagementConstants.ATTACHMENT_BEAN_NAME
						+ ContactManagementConstants.BINDING_END_SEPARATOR)
				.getValue(fc));
	}

	/**
	 * This method is used to get the User ID.
	 * 
	 * @return String : User ID
	 */
	/*public String getUserID() {
		logger.info("getUserID");

		String userId = ContactManagementConstants.TEST_USERID;
		FacesContext fc = FacesContext.getCurrentInstance();
		CorrespondenceControllerBean correspondenceControllerBean = new CorrespondenceControllerBean();

		HttpServletRequest renderRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse renderResponse = null;

		EnterpriseUserProfile eup = correspondenceControllerBean.getUserData(
				renderRequest, renderResponse);

		if (eup != null && !isNullOrEmptyField(eup.getUserId())) {
			userId = eup.getUserId();
		}

		return userId;
	}*/
	
	public String getLoggedInUserID() {
		String userId = null;
		Principal principal = null ;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest)facesContext.getExternalContext().getRequest();
		if(portletRequest != null){
			
		 principal= portletRequest.getUserPrincipal();
		}
		if (principal == null) {
			userId = "guestUser";
		}
		else{
			userId = principal.getName();
		}
		
		return userId;
	}
	/**
	 * @param workUnit
	 *            The workUnit to set.
	 * @param workUnitTypeCode
	 *            The workUnitTypeCode to set.
	 * @return String
	 */
	protected String getName(WorkUnit workUnit, String workUnitTypeCode) {
		

		String name = ContactManagementConstants.EMPTY_STRING;

		if (workUnit != null
				&& "U".equalsIgnoreCase(workUnit.getWorkUnitTypeCode())) {
			EnterpriseUser user = workUnit.getEnterpriseUser();
			if (!isNullOrEmptyField(user.getLastName()))
            {
            	name = user.getLastName();
            }
            if(!isNullOrEmptyField(user.getLastName()) &&
            		!isNullOrEmptyField(user.getFirstName()))
            {
            	name = name 
                + ContactManagementConstants.COMMA_SEPARATOR
                + ContactManagementConstants.SPACE_STRING;
            }
            if (!isNullOrEmptyField(user.getFirstName()))
            {
            	name = name + user.getFirstName();
            }
		}
		if (workUnit != null
				&& "W".equalsIgnoreCase(workUnit.getWorkUnitTypeCode())) {
			Department department = workUnit.getDepartment();
			name = department.getName();
		}
		if (workUnit != null
				&& "B".equalsIgnoreCase(workUnit.getWorkUnitTypeCode())) {
			Department department = workUnit.getDepartment();
			name = department.getName();
		}

		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 * @param user
	 *            The user to set.
	 */
	protected void setUserName(String name, EnterpriseUser user) {
		

		String nameSeparator = ContactManagementConstants.COMMA_SEPARATOR;
		int indexOfSeparator = name.indexOf(nameSeparator);

		String lastName = name.substring(0, indexOfSeparator);
		String firstName = name.substring(indexOfSeparator + 1, name.length());
		
		user.setFirstName(firstName);
		user.setLastName(lastName);
	}

	/**
	 * This method is used to check if the input field is null or is equal to an
	 * empty string.
	 * 
	 * @param inputField :
	 *            String inputField
	 * @return boolean : true if input field is null or equal to an empty string
	 *         else false
	 */
	protected boolean isNullOrEmptyField(String inputField) {
		

		return (inputField == null || ContactManagementConstants.EMPTY_STRING
				.equalsIgnoreCase(inputField.trim()));
	}

	/**
	 * @param associatedDoList
	 *            The associatedDoList to set.
	 * @return List
	 */
	public List associatedDOToVOConvertor(List associatedDoList) {
		

		List associatedVoList = new ArrayList();

		if (associatedDoList != null) {
			for (Iterator iter = associatedDoList.iterator(); iter.hasNext();) {
				Correspondence correspondence = (Correspondence) iter.next();
				AssociatedCorrespondenceVO associatedCorrespondenceVO = getAssociatedCRVO(correspondence);

				associatedCorrespondenceVO.setLinkToCR(Boolean.FALSE);
				associatedVoList.add(associatedCorrespondenceVO);
				associatedCorrespondenceVO.setVersionNo(correspondence
						.getVersionNo());
			}
		}
		return associatedVoList;
	}

	/**
	 * @param correspondence
	 *            The correspondence to set.
	 * @return AssociatedCorrespondenceVO
	 */
	private AssociatedCorrespondenceVO getAssociatedCRVO(
			Correspondence correspondence) {
		

		DateFormat dateFormat = new SimpleDateFormat(
				ContactManagementConstants.DATE_FORMAT);

		AssociatedCorrespondenceVO associatedCorrespondenceVO = new AssociatedCorrespondenceVO();
		associatedCorrespondenceVO.setCorrespondenceRecordNumber(correspondence
				.getCorrespondenceSK().toString());

		if (correspondence.getOpenDate() != null) {
			associatedCorrespondenceVO.setOpenDate(dateFormat
					.format(correspondence.getOpenDate()));
		}
		associatedCorrespondenceVO.setSubject(correspondence.getSubjectCode());
		Map subjectMap = getSearchCorrespondenceDataBean().getSubjectMap();
		if (subjectMap != null && subjectMap.size() > 0
				&& subjectMap.containsKey(correspondence.getSubjectCode())) {
			associatedCorrespondenceVO.setSubject((String) subjectMap
					.get(correspondence.getSubjectCode()));
		}
		//  TODO
		associatedCorrespondenceVO.setCategory(correspondence
				.getCorrespondenceCategory().getDescription());
		associatedCorrespondenceVO.setStatus(correspondence.getStatusCode());
		Map statusMap = getSearchCorrespondenceDataBean().getStatusMap();
		if (statusMap != null && statusMap.size() > 0
				&& statusMap.containsKey(correspondence.getStatusCode())) {
			associatedCorrespondenceVO.setStatus((String) statusMap
					.get(correspondence.getStatusCode()));
		}
		if (correspondence.getRepresentative() != null) {

			associatedCorrespondenceVO.setContact(correspondence
					.getRepresentative().getName().getFirstName()
					+ ContactManagementConstants.SPACE_STRING
					+ correspondence.getRepresentative().getName()
							.getLastName());

		}
		associatedCorrespondenceVO.setAuditUserID(correspondence
				.getAuditUserID());
		associatedCorrespondenceVO.setAddedAuditUserID(correspondence
				.getAddedAuditUserID());
		//associatedCorrespondenceVO.setVersionNo(correspondence.getVersionNo());

		return associatedCorrespondenceVO;
	}

	/**
	 * Method to get the associatedCorrespondence
	 * 
	 * @param entityID
	 *            The entityID to set.
	 * @param entityType
	 *            The entityType to set.
	 * @param noOfDays
	 *            The noOfDays to set.
	 */
	public void getAssociatedCorrespondence(Long entityID, String entityType,
			Integer noOfDays) {
		

		List correspondencelist = null;
		List associatedVoList = null;

		//long entryTime = System.currentTimeMillis();

		CMDelegate cmdelegate = new CMDelegate();
		try {
			correspondencelist = cmdelegate.getAssociatedCorrespondence(
					entityID, entityType, noOfDays);
		} catch (CorrespondenceRecordFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}

		associatedVoList = associatedDOToVOConvertor(correspondencelist);

		//default sort existing CR list based on date
		//sortExistingCROnDate(associatedVoList);
		getCorrespondenceDataBean().getCorrespondenceRecordVO()
				.setExistingCRLists(associatedVoList);
	}

	/**
	 * @param correspondence
	 *            The correspondence to set.
	 * @return Set
	 */
	protected List convertListOfLettersDOs(Correspondence correspondence) {
		

		List listOfLetterVOs = new ArrayList();

		Set setOfLettersDOs = correspondence.getLetterRequests();

		if (setOfLettersDOs != null) {
			for (Iterator iter = setOfLettersDOs.iterator(); iter.hasNext();) {
				CorrespondenceLetterRequest crLetterDO = (CorrespondenceLetterRequest) iter
						.next();
				CorrespondenceLetterResponsesXrefVO crLetterXrefVO = new CorrespondenceLetterResponsesXrefVO();

				crLetterXrefVO.setLetterGeneratonRequestSK(crLetterDO
						.getLetterRequestSK().toString());
				crLetterXrefVO.setAddedAuditTimeStamp(crLetterDO
						.getAddedAuditTimeStamp());
				crLetterXrefVO.setAddedAuditUserID(crLetterDO
						.getAddedAuditUserID());
				crLetterXrefVO
						.setAuditTimeStamp(crLetterDO.getAuditTimeStamp());
				crLetterXrefVO.setAuditUserID(crLetterDO.getAuditUserID());
				crLetterXrefVO.setVersionNo(crLetterDO.getVersionNo());
				listOfLetterVOs.add(crLetterXrefVO);

			}
		}

		return listOfLetterVOs;
	}

	/**
	 * @param correspondenceRecordVO
	 *            correspondenceRecordVO to set
	 * @param fieldValueVO
	 *            fieldValueVO to set
	 * @param cfList
	 *            cfList to set
	 * @return List
	 */
	public List convertCustomFieldVO(
			CorrespondenceRecordVO correspondenceRecordVO,
			CustomFieldValueVO fieldValueVO, List cfList) {
		
		//   List cfList = getCMLogCaseDataBean().getCustomFieldDOList();
		
		String value = null;
		int size = 0;
		//int valueSKSize = 0;
		//Long cfSK = null;
		int versionNo = 0;
		String verNo = null;
		String customSK = null;
		List finalCFValueList = new ArrayList();
		if (cfList != null && !cfList.isEmpty()) {
			
			CustomField field = null;
			size = cfList.size();
			//valueSKSize =
			// getCorrespondenceDataBean().getCfValueSKMap().size();
			for (int i = 0; i < size; i++) {
				field = (CustomField) cfList.get(i);
				
				if("CB".equalsIgnoreCase(field.getDataTypeCode()))
				{
					/** panel grid issue fix*/
					//value = getCustomFieldValueForChkBox(i, fieldValueVO);
					value = String.valueOf(((CustomFieldVO) getCorrespondenceDataBean().getCustomFieldVOList().get(i)).getCheckBoxValue());
				}else{
					/** panel grid issue fix*/
					//value = getCustomFieldValue(i, fieldValueVO);
					value = ((CustomFieldVO) getCorrespondenceDataBean().getCustomFieldVOList().get(i)).getComponentInputData();

				}
				
				if (value != null && !value.equals("")) {
					CustomFieldValue fieldValue = null;
					fieldValue = convertCustomFieldValuesVOToDO(value,
							fieldValueVO, field);

					/** Insert Correspondecne RecordVo here */

					//   fieldValue.setCaseRecord(caseRecord);
					//hash map performance issue code change
					if (getCorrespondenceDataBean().getCfValueSKMap()!=null &&
							!getCorrespondenceDataBean().getCfValueSKMap()
							.isEmpty()) {
						customSK = field.getCustomFieldSK().toString();
						verNo = (String) getCorrespondenceDataBean()
								.getCfValueSKMap().get(customSK);
						if (verNo != null) {
							versionNo = Integer.valueOf(verNo).intValue();
							
							fieldValue.setVersionNo(versionNo);
						} else {
							fieldValue.setVersionNo(0);
						}
					} else {
						fieldValue.setVersionNo(0);
					}
					finalCFValueList.add(fieldValue);
				}
			}
		}
		//hash map performance issue code change
		getCorrespondenceDataBean().setCfValueSKMap(null);
		return finalCFValueList;
	}

	/**
	 * @param cfValue
	 *            cfValue ot set
	 * @param customFieldValueVO
	 *            customFieldValueVO to set
	 * @param field
	 *            field to set
	 * @return CustomFieldValue
	 */
	private CustomFieldValue convertCustomFieldValuesVOToDO(String cfValue,
			CustomFieldValueVO customFieldValueVO, CustomField field) {
		
		CustomFieldValue fieldValue = new CustomFieldValue();
		CustomFieldScope fieldScope = new CustomFieldScope();
		fieldValue.setCustomFieldValue(cfValue);
		fieldValue.setCustomField(field);
		fieldScope.setCustomField(field);
		CustomFieldTable customFieldTable = new CustomFieldTable();
		customFieldTable.setTableName("G_CR_TB");
		if (customFieldValueVO.getAddedAuditUserID() == null) {
			customFieldTable.setAddedAuditUserID(getLoggedInUserID());
		} else {
			customFieldTable
					.setAddedAuditUserID(getLoggedInUserID());
		}
		if (customFieldValueVO.getAuditUserID() == null) {
			customFieldTable
					.setAuditUserID(getLoggedInUserID());
		} else {
			customFieldTable
					.setAuditUserID(getLoggedInUserID());
		}
		fieldScope.setCustomFieldTable(customFieldTable);
		if (customFieldValueVO.getAddedAuditUserID() == null) {
			fieldScope.setAddedAuditUserID(getLoggedInUserID());
		} else {
			fieldScope
					.setAddedAuditUserID(getLoggedInUserID());
		}
		if (customFieldValueVO.getAuditUserID() == null) {
			fieldScope.setAuditUserID(getLoggedInUserID());
		} else {
			fieldScope
					.setAuditUserID(getLoggedInUserID());
		}
		fieldValue.setCustomFieldScope(fieldScope);
		//fieldValue.setVersionNo(customFieldValueVO.getVersionNo());
		
		if (customFieldValueVO.getAuditUserID() == null) {
			fieldValue
					.setAuditUserID(getLoggedInUserID());
		} else {
			fieldValue.setAuditUserID(getLoggedInUserID());
		}
		if (customFieldValueVO.getAuditTimeStamp() == null) {
			fieldValue.setAuditTimeStamp(new Date());
		} else {
			fieldValue
					.setAuditTimeStamp(customFieldValueVO.getAuditTimeStamp());
		}
		if (customFieldValueVO.getAddedAuditUserID() == null) {
			fieldValue
					.setAddedAuditUserID(getLoggedInUserID());
		} else {
			fieldValue.setAddedAuditUserID(getLoggedInUserID());
		}
		if (customFieldValueVO.getAuditTimeStamp() == null) {
			fieldValue.setAuditTimeStamp(new Date());
		} else {
			fieldValue
					.setAuditTimeStamp(customFieldValueVO.getAuditTimeStamp());
		}
		return fieldValue;
	}

	/**
	 * @param index
	 *            index to set
	 * @param customFieldValueVO
	 *            customFieldValueVO to set
	 * @return String
	 */
	public String getCustomFieldValue(int index,
			CustomFieldValueVO customFieldValueVO) {
		

		String value = null;
		if (index == 0) {
			if (customFieldValueVO.getElementZero() != null) {
				value = customFieldValueVO.getElementZero().toString();
			}
		} else if (index == 1) {
			if (customFieldValueVO.getElementOne() != null) {
				value = customFieldValueVO.getElementOne().toString();
			}
		} else if (index == 2) {
			if (customFieldValueVO.getElementTwo() != null) {
				value = customFieldValueVO.getElementTwo().toString();
			}
		} else if (index == ContactManagementConstants.INT_3) {
			if (customFieldValueVO.getElementThree() != null) {
				value = customFieldValueVO.getElementThree().toString();
			}
		} else if (index == ContactManagementConstants.INT_4) {
			if (customFieldValueVO.getElementFour() != null) {
				value = customFieldValueVO.getElementFour().toString();
			}
		} else if (index == ContactManagementConstants.INT_5) {
			if (customFieldValueVO.getElementFive() != null) {
				value = customFieldValueVO.getElementFive().toString();
			}
		} else if (index == ContactManagementConstants.INT_6) {
			if (customFieldValueVO.getElementSix() != null) {
				value = customFieldValueVO.getElementSix().toString();
			}
		} else if (index == ContactManagementConstants.INT_7) {
			if (customFieldValueVO.getElementSeven() != null) {
				value = customFieldValueVO.getElementSeven().toString();
			}
		} else if (index == ContactManagementConstants.INT_8) {
			if (customFieldValueVO.getElementEight() != null) {
				value = customFieldValueVO.getElementEight().toString();
			}
		} else if (index == ContactManagementConstants.INT_9) {
			if (customFieldValueVO.getElementNine() != null) {
				value = customFieldValueVO.getElementNine().toString();
			}
		} else if (index == ContactManagementConstants.INT_10) {
			if (customFieldValueVO.getElementTen() != null) {
				value = customFieldValueVO.getElementTen().toString();
			}
		} else if (index == ContactManagementConstants.INT_11) {
			if (customFieldValueVO.getElementEleven() != null) {
				value = customFieldValueVO.getElementEleven().toString();
			}
		} else if (index == ContactManagementConstants.INT_12) {
			if (customFieldValueVO.getElementTwelve() != null) {
				value = customFieldValueVO.getElementTwelve().toString();
			}
		} else if (index == ContactManagementConstants.INT_13) {
			if (customFieldValueVO.getElementThirteen() != null) {
				value = customFieldValueVO.getElementThirteen().toString();
			}
		} else if (index == ContactManagementConstants.INT_14) {
			if (customFieldValueVO.getElementFourteen() != null) {
				value = customFieldValueVO.getElementFourteen().toString();
			}
		} else if (index == ContactManagementConstants.INT_15) {
			if (customFieldValueVO.getElementFifteen() != null) {
				value = customFieldValueVO.getElementFifteen().toString();
			}
		} else if (index == ContactManagementConstants.INT_16) {
			if (customFieldValueVO.getElementSixteen() != null) {
				value = customFieldValueVO.getElementSixteen().toString();
			}
		} else if (index == ContactManagementConstants.INT_17) {
			if (customFieldValueVO.getElementSeventeen() != null) {
				value = customFieldValueVO.getElementSeventeen().toString();
			}
		} else if (index == ContactManagementConstants.INT_18) {
			if (customFieldValueVO.getElementEighteen() != null) {
				value = customFieldValueVO.getElementEighteen().toString();
			}
		} else if (index == ContactManagementConstants.INT_19) {
			if (customFieldValueVO.getElementNinteen() != null) {
				value = customFieldValueVO.getElementNinteen().toString();
			}
		} else if (index == ContactManagementConstants.INT_20) {
			if (customFieldValueVO.getElementTwenty() != null) {
				value = customFieldValueVO.getElementTwenty().toString();
			}
		}
		
		return value;
	}
	
	public String getCustomFieldValueForChkBox(int index,
			CustomFieldValueVO customFieldValueVO) {
		

		String value = null;
		boolean chkBoxVal = false;
		if (index == 0) {
			chkBoxVal = customFieldValueVO.isElementZeroForChkbox();
		} else if (index == 1) {
			chkBoxVal = customFieldValueVO.isElementOneForChkbox();
		} else if (index == 2) {
			chkBoxVal = customFieldValueVO.isElementTwoForChkbox();
		} else if (index == ContactManagementConstants.INT_3) {
			chkBoxVal = customFieldValueVO.isElementThreeForChkbox();
		} else if (index == ContactManagementConstants.INT_4) {
			chkBoxVal = customFieldValueVO.isElementFourteenForChkbox();
		} else if (index == ContactManagementConstants.INT_5) {
			chkBoxVal = customFieldValueVO.isElementFiveForChkbox();
		} else if (index == ContactManagementConstants.INT_6) {
			chkBoxVal = customFieldValueVO.isElementSixForChkbox();
		} else if (index == ContactManagementConstants.INT_7) {
			chkBoxVal = customFieldValueVO.isElementSevenForChkbox();
		} else if (index == ContactManagementConstants.INT_8) {
			chkBoxVal = customFieldValueVO.isElementEighteenForChkbox();
		} else if (index == ContactManagementConstants.INT_9) {
			chkBoxVal = customFieldValueVO.isElementNineForChkbox();
		} else if (index == ContactManagementConstants.INT_10) {
			chkBoxVal = customFieldValueVO.isElementTenForChkbox();
		} else if (index == ContactManagementConstants.INT_11) {
			chkBoxVal = customFieldValueVO.isElementElevenForChkbox();
		} else if (index == ContactManagementConstants.INT_12) {
			chkBoxVal = customFieldValueVO.isElementTwelveForChkbox();
		} else if (index == ContactManagementConstants.INT_13) {
			chkBoxVal = customFieldValueVO.isElementThirteenForChkbox();
		} else if (index == ContactManagementConstants.INT_14) {
			chkBoxVal = customFieldValueVO.isElementFourteenForChkbox();
		} else if (index == ContactManagementConstants.INT_15) {
			chkBoxVal = customFieldValueVO.isElementFifteenForChkbox();
		} else if (index == ContactManagementConstants.INT_16) {
			chkBoxVal = customFieldValueVO.isElementSixteenForChkbox();
		} else if (index == ContactManagementConstants.INT_17) {
			chkBoxVal = customFieldValueVO.isElementSeventeenForChkbox();
		} else if (index == ContactManagementConstants.INT_18) {
			chkBoxVal = customFieldValueVO.isElementEighteenForChkbox();
		} else if (index == ContactManagementConstants.INT_19) {
			chkBoxVal = customFieldValueVO.isElementNinteenForChkbox();
		} else if (index == ContactManagementConstants.INT_20) {
			chkBoxVal = customFieldValueVO.isElementTwentyForChkbox();
		}
		
		if(chkBoxVal)
		{
			value = "Y";
		}
		return value;
	}

	/**
	 * @param cfValueList
	 *            cfValueList to set
	 * @param cfList
	 *            cfList to set
	 */
	public void setCFValueToCustomField(List cfValueList, List cfList) {
		CustomField field = null;
		CustomFieldValue fieldValue = null;
		CustomFieldValueVO valueVO = new CustomFieldValueVO();
		//hash map performance issue code change
		Map cfValueMap=new HashMap();
		Long valueCFSK = null;
		if (cfList != null && !cfList.isEmpty()) {
			

			int cfListSize = cfList.size();
			for (int i = 0; i < cfListSize; i++) {
				field = (CustomField) cfList.get(i);
				if (cfValueList != null && !cfValueList.isEmpty()) {
					for (int j = 0; j < cfListSize; j++) {
						if (j < cfValueList.size()) {
							fieldValue = (CustomFieldValue) cfValueList.get(j);
							if (fieldValue != null) {
								valueCFSK = fieldValue.getCustomField()
										.getCustomFieldSK();
								if (field.getCustomFieldSK().longValue() == valueCFSK
										.longValue()) {
									/*if("CB".equalsIgnoreCase(field.getDataTypeCode()))
									{
									valueVO = setCustomFieldValueForChkBox(i, valueVO,
											fieldValue.getCustomFieldValue());
									}else{
										valueVO = setCustomFieldValue(i, valueVO,
												fieldValue.getCustomFieldValue());
									}*/
									valueVO = convertCustomFieldValueDOToVO(
											valueVO, fieldValue);
									
									cfValueMap.put(
													field.getCustomFieldSK()
															.toString(),
													String.valueOf(valueVO
															.getVersionNo()));
								}
							}
						}
					}
				}
			}
			getCorrespondenceDataBean().setCustomFieldValueVO(valueVO);
			//hash map performance issue code change
			getCorrespondenceDataBean().setCfValueSKMap(cfValueMap);
		}
	}

	/**
	 * @param customFieldValueVO
	 *            customFieldValueVO to set
	 * @param valueDO
	 *            valueDO to set
	 * @return CustomFieldValueVO
	 */
	private CustomFieldValueVO convertCustomFieldValueDOToVO(
			CustomFieldValueVO customFieldValueVO, CustomFieldValue valueDO) {
		customFieldValueVO.setAddedAuditTimeStamp(valueDO
				.getAddedAuditTimeStamp());
		customFieldValueVO.setAddedAuditUserID(valueDO.getAddedAuditUserID());
		customFieldValueVO.setAuditTimeStamp(valueDO.getAuditTimeStamp());
		customFieldValueVO.setAuditUserID(valueDO.getAuditUserID());
		customFieldValueVO.setVersionNo(valueDO.getVersionNo());
		
		return customFieldValueVO;
	}

	/**
	 * @param index
	 *            index to set
	 * @param customFieldValueVO
	 *            customFieldValueVO to set
	 * @param value
	 *            value to set
	 * @return CustomFieldValueVO
	 */
	private CustomFieldValueVO setCustomFieldValue(int index,
			CustomFieldValueVO customFieldValueVO, String value) {
		
		if (index == 0) {
			customFieldValueVO.setElementZero(value);
		} else if (index == 1) {
			customFieldValueVO.setElementOne(value);
		} else if (index == 2) {
			customFieldValueVO.setElementTwo(value);
		} else if (index == ContactManagementConstants.INT_3) {
			customFieldValueVO.setElementThree(value);
		} else if (index == ContactManagementConstants.INT_4) {
			customFieldValueVO.setElementFour(value);
		} else if (index == ContactManagementConstants.INT_5) {
			customFieldValueVO.setElementFive(value);
		} else if (index == ContactManagementConstants.INT_6) {
			customFieldValueVO.setElementSix(value);
		} else if (index == ContactManagementConstants.INT_7) {
			customFieldValueVO.setElementSeven(value);
		} else if (index == ContactManagementConstants.INT_8) {
			customFieldValueVO.setElementEight(value);
		} else if (index == ContactManagementConstants.INT_9) {
			customFieldValueVO.setElementNine(value);
		} else if (index == ContactManagementConstants.INT_10) {
			customFieldValueVO.setElementTen(value);
		} else if (index == ContactManagementConstants.INT_11) {
			customFieldValueVO.setElementEleven(value);
		} else if (index == ContactManagementConstants.INT_12) {
			customFieldValueVO.setElementTwelve(value);
		} else if (index == ContactManagementConstants.INT_13) {
			customFieldValueVO.setElementThirteen(value);
		} else if (index == ContactManagementConstants.INT_14) {
			customFieldValueVO.setElementFourteen(value);
		} else if (index == ContactManagementConstants.INT_15) {
			customFieldValueVO.setElementFifteen(value);
		} else if (index == ContactManagementConstants.INT_16) {
			customFieldValueVO.setElementSixteen(value);
		} else if (index == ContactManagementConstants.INT_17) {
			customFieldValueVO.setElementSeventeen(value);
		} else if (index == ContactManagementConstants.INT_18) {
			customFieldValueVO.setElementEighteen(value);
		} else if (index == ContactManagementConstants.INT_19) {
			customFieldValueVO.setElementNinteen(value);
		} else if (index == ContactManagementConstants.INT_20) {
			customFieldValueVO.setElementTwenty(value);
		}
		
		return customFieldValueVO;
	}
	
	private CustomFieldValueVO setCustomFieldValueForChkBox(int index,
			CustomFieldValueVO customFieldValueVO, String value) {
		
		boolean chkBoxVal = false;
		if("Y".equalsIgnoreCase(value))
		{
			chkBoxVal = true;
		}
		if (index == 0) {
			customFieldValueVO.setElementZeroForChkbox(chkBoxVal);
		} else if (index == 1) {
			customFieldValueVO.setElementOneForChkbox(chkBoxVal);
		} else if (index == 2) {
			customFieldValueVO.setElementTwoForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_3) {
			customFieldValueVO.setElementThreeForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_4) {
			customFieldValueVO.setElementFourForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_5) {
			customFieldValueVO.setElementFiveForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_6) {
			customFieldValueVO.setElementSixForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_7) {
			customFieldValueVO.setElementSevenForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_8) {
			customFieldValueVO.setElementEightForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_9) {
			customFieldValueVO.setElementNineForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_10) {
			customFieldValueVO.setElementTenForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_11) {
			customFieldValueVO.setElementElevenForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_12) {
			customFieldValueVO.setElementTwelveForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_13) {
			customFieldValueVO.setElementThirteenForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_14) {
			customFieldValueVO.setElementFourteenForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_15) {
			customFieldValueVO.setElementFifteenForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_16) {
			customFieldValueVO.setElementSixteenForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_17) {
			customFieldValueVO.setElementSeventeenForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_18) {
			customFieldValueVO.setElementEighteenForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_19) {
			customFieldValueVO.setElementNinteenForChkbox(chkBoxVal);
		} else if (index == ContactManagementConstants.INT_20) {
			customFieldValueVO.setElementTwentyForChkbox(chkBoxVal);
		}
		
		return customFieldValueVO;
	}

	/**
	 * This method is used to get the UserSK given the userId.
	 * 
	 * @param userId :
	 *            String User Id.
	 * @return Long : UserSK.
	 */
	public Long getUserSK(String userId) {
		

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

		Long userSK = null;

		try {
			userSK = contactMaintenanceDelegate.getWorkUnitID(userId);
		} catch (LOBHierarchyFetchBusinessException e) {
			if(logger.isErrorEnabled())
			{
			logger.error(e.getMessage(), e);
			}
		}

		return userSK;
	}

	public String getBusinessUnitforUser(Long userSK) {

		ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();
		String businessUnitDesc = null;
		try {
			List deptsList = contactMaintenanceDelegate
					.getDepartmentUsers(userSK);

			

			if (deptsList != null) {
				String businessCode = null;

				for (Iterator iter = deptsList.iterator(); iter.hasNext();) {
					DepartmentUser deptUser = (DepartmentUser) iter.next();
					String lobHierarchySK = deptUser.getDepartment()
							.getLineOfBusinessHierarchy()
							.getLineOfBusinessHierarchySK().toString();
					LineOfBusinessHierarchy businessUnit = contactMaintenanceDelegate
							.getDeptBusinessUnit(new Long(lobHierarchySK));
					if (businessUnit != null) {
						businessCode = businessUnit
								.getLineOfBusinessHierarchySK().toString();

						if (businessCode != null) {
							businessUnitDesc = businessUnit
									.getLobHierarchyDescription();
						} else if (businessCode != null) {
							businessUnitDesc = ContactManagementConstants.AllOthers;
							break;
						}
					}
				}

			}
		} catch (LOBHierarchyFetchBusinessException lobExp) {
			if(logger.isErrorEnabled())
			{
			logger.error(lobExp.getMessage(), lobExp);
			}
		}

		return businessUnitDesc;
	}

	/**
	 * 
	 * @param correspondenceDetailsVO
	 */
	protected Set convertClientServicesVoToDo(
			CorrespondenceDetailsVO cRDetailsVO) {
		Set cRClientServicesSet = new HashSet();
		
		CorrespondenceClientServices cRClientServices = null;
		if (cRDetailsVO != null) {
			cRClientServices = new CorrespondenceClientServices();

			
			if (cRDetailsVO.getVipPresent() != null) {
				if ("Yes".equals(cRDetailsVO.getVipPresent())) {
					cRClientServices.setClientServiceVIPIndicator(ContactManagementConstants.Y);
				} else if("No".equals(cRDetailsVO.getVipPresent())){
					cRClientServices.setClientServiceVIPIndicator(ContactManagementConstants.N);
				}
			}

			
			cRClientServices.setClientServiceDentalApptmtCode(cRDetailsVO
					.getDentalApptMadeBy());
			cRClientServices.setVersionNo(cRDetailsVO.getVersionNo());
			
			if (cRDetailsVO.getLtdEngProficiency() != null) {
				if ("Yes".equals(cRDetailsVO.getLtdEngProficiency())) {
					cRClientServices
							.setClientServiceGlobalIndicator(ContactManagementConstants.Y);
				} else if ("No".equals(cRDetailsVO.getLtdEngProficiency())){
					cRClientServices
							.setClientServiceGlobalIndicator(ContactManagementConstants.N);
				}
			}

			
			if (cRDetailsVO.getSelectedReferredToList() != null) {
				Set crCSReferredSet = new HashSet();
				Iterator itr = cRDetailsVO.getSelectedReferredToList()
						.iterator();
				while (itr.hasNext()) {
					SelectItem reference = (SelectItem) itr.next();
					
					CorrespondenceCSReferred crCSReferred = new CorrespondenceCSReferred();
					crCSReferred
							.setClientServiceRfrdToTypeCode((String) reference
									.getValue());
					crCSReferred.setAddedAuditTimeStamp(new Date());
					crCSReferred.setAddedAuditUserID(getLoggedInUserID());
					crCSReferred.setAuditTimeStamp(new Date());
					crCSReferred.setAuditUserID(getLoggedInUserID());
					crCSReferred
							.setCorrespondenceClientServices(cRClientServices);
					if (getCorrespondenceDataBean().getVersionMap() != null) {
						Integer versionNo = (Integer) getCorrespondenceDataBean()
								.getVersionMap()
								.get(
										crCSReferred
												.getClientServiceRfrdToTypeCode());
						if (versionNo != null) {
							crCSReferred.setVersionNo(versionNo.intValue());
						}
					}
					crCSReferredSet.add(crCSReferred);
				}
				cRClientServices.setCorrespondenceCSReferred(crCSReferredSet);
			}
			cRClientServices.setAddedAuditTimeStamp(new Date());
			cRClientServices.setAddedAuditUserID(getLoggedInUserID());
			cRClientServices.setAuditTimeStamp(new Date());
			cRClientServices.setAuditUserID(getLoggedInUserID());
			cRClientServicesSet.add(cRClientServices);
		}
		return cRClientServicesSet;
	}

	public void sortRoutingInfoOnDate() {
		

		Comparator comparator = new Comparator() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Comparator#compare(java.lang.Object,
			 *      java.lang.Object)
			 */
			public int compare(Object obj1, Object obj2) {
				

				int returnValue = 0;

				CMRoutingVO cmRoutingVO1 = (CMRoutingVO) obj1;
				CMRoutingVO cmRoutingVO2 = (CMRoutingVO) obj2;

				/*DateFormat dateFormat = new SimpleDateFormat(
						ContactManagementConstants.DATE_FORMAT_TIME, Locale
								.getDefault());*/ // Find bug issue

				
				Date date1 = cmRoutingVO1.getDateRouted();
				Date date2 = cmRoutingVO2.getDateRouted();

				returnValue = date2.compareTo(date1);
				

				return returnValue;
			}
		};

		if (getRoutingDataBean().getListOfCMRoutingVOs() != null) {
			Collections.sort(getRoutingDataBean().getListOfCMRoutingVOs(),
					comparator);
		}

	}

	public void sortExistingCROnDate(List listOfCrVO) {
		

		Comparator comparator = new Comparator() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Comparator#compare(java.lang.Object,
			 *      java.lang.Object)
			 */
			public int compare(Object obj1, Object obj2) {
				
				int returnValue = 0;

				AssociatedCorrespondenceVO associatedCrVo1 = (AssociatedCorrespondenceVO) obj1;
				AssociatedCorrespondenceVO associatedCrVo2 = (AssociatedCorrespondenceVO) obj2;

				DateFormat dateFormat = new SimpleDateFormat(
						ContactManagementConstants.DATE_FORMAT, Locale
								.getDefault());

				try {
					Date date1 = dateFormat
							.parse(associatedCrVo1.getOpenDate());
					Date date2 = dateFormat
							.parse(associatedCrVo2.getOpenDate());

					returnValue = date2.compareTo(date1);
				} catch (ParseException e) {
					if(logger.isErrorEnabled())
					{
					logger.error(e.getMessage(), e);
					}
				}

				return returnValue;
			}
		};

		if (listOfCrVO != null) {
			
			Collections.sort(listOfCrVO, comparator);
		}

	}
	
	public void sortlistOfPriorAsscCRs(List listOfCrVO) {
		
		
		Comparator comparator1 = new Comparator() {
			public int compare(Object temp, Object tempOne) {
				
				 boolean ascending = true;

				AssociatedCorrespondenceVO associatedCrVo1 = (AssociatedCorrespondenceVO) temp;
				AssociatedCorrespondenceVO associatedCrVo2 = (AssociatedCorrespondenceVO) tempOne;

				
					if (null == associatedCrVo1.getCorrespondenceRecordNumber())
                    {
						associatedCrVo1
                                .setCorrespondenceRecordNumber(MaintainContactManagementUIConstants.NULL);
                    }
                    if (null == associatedCrVo2.getCorrespondenceRecordNumber())
                    {
                    	associatedCrVo2
                                .setCorrespondenceRecordNumber(MaintainContactManagementUIConstants.NULL);
                    }
                    return ascending ? Integer.valueOf(
                    		associatedCrVo1.getCorrespondenceRecordNumber()).compareTo(
                            Integer.valueOf(associatedCrVo2.getCorrespondenceRecordNumber()))
                            : Integer.valueOf(associatedCrVo2.getCorrespondenceRecordNumber())
                                    .compareTo(
                                            Integer.valueOf(associatedCrVo1.getCorrespondenceRecordNumber()));
			}
		};
        
		if(listOfCrVO != null)
		{
		Collections.sort(listOfCrVO, comparator1);
		}

	}
	
	
	

	public void sortAlertInfoOnDueDate() {
		

		Comparator comparator = new Comparator() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Comparator#compare(java.lang.Object,
			 *      java.lang.Object)
			 */
			public int compare(Object obj1, Object obj2) {
				

				int returnValue = 0;

				AlertVO alertVO1 = (AlertVO) obj1;
				AlertVO alertVO2 = (AlertVO) obj2;
				/*
				DateFormat dateFormat = new SimpleDateFormat(
						ContactManagementConstants.DATE_FORMAT, Locale
								.getDefault());*/ // Find bug issue
				
				Date date1 = alertVO1.getDtDueDate();
				Date date2 = alertVO2.getDtDueDate();
				if(null == date1)
				{
					date1 = new Date();
				}
				if(null == date2)
				{
					date2 = new Date();
				}
				returnValue = date2.compareTo(date1);
				return returnValue;
			}
		};

		if (getAlertDataBean().getListOfAlertVOs() != null) {
			Collections.sort(getAlertDataBean().getListOfAlertVOs(),
					comparator);
		}

	}
	
	
	
	/**
	 * Method to get Language Description
	 * 
	 * @param langCode
	 * @return
	 */
	public String getLangDesc(String langCode) {
		List referenceList = new ArrayList();
		String langDesc = null;
		

		if (langCode != null) {
			Map referenceMap = null;
			InputCriteria ipCrtMemberLang = new InputCriteria();
			ipCrtMemberLang.setFunctionalArea(FunctionalAreaConstants.MEMBER);
			ipCrtMemberLang
					.setElementName(ReferenceServiceDataConstants.B_LANG_CD);

			referenceList.add(ipCrtMemberLang);

			ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
			ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
			ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();

			referenceDataSearch.setInputList(referenceList);

			try {
				referenceDataListVO = referenceServiceDelegate
						.getReferenceData(referenceDataSearch);
				referenceMap = referenceDataListVO.getResponseMap();
			} catch (ReferenceServiceRequestException e) {
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(), e);
				}
			} catch (SystemListNotFoundException e) {
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(), e);
				}
			} catch (Exception e) {
				if(logger.isErrorEnabled())
				{
				logger.error(e.getMessage(), e);
				}
			}

			if (referenceMap != null) {
				List referenceResultList = null;
				referenceList = (List) referenceMap
						.get(FunctionalAreaConstants.MEMBER
								+ ProgramConstants.HASH_SEPARATOR
								+ ReferenceServiceDataConstants.B_LANG_CD);

				if (referenceList != null) {
					int referenceListSize = referenceList.size();

					for (int i = 0; i < referenceListSize; i++) {
						ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
								.get(i);
						if (refVo.getValidValueCode()
								.equalsIgnoreCase(langCode)) {
							langDesc = refVo.getShortDescription();
							break;
						}

					}
				}
			}
		} else {
			if(logger.isErrorEnabled())
			{
			logger.error("langCode is null");
			}
		}
		
		return langDesc;
	}
	
	/**
	 * 
	 * @param provider
	 * @return
	 */
	public static CorrespondenceForVO getCrspdForUnApprovedProvider(Provider provider) 
	{
		/** Enterprise Logger for Logging. */
		EnterpriseLogger logger = EnterpriseLogFactory.getLogger(CorrespondenceDOConvertor.class);
		
		CorrespondenceForVO correspondenceForVO = new CorrespondenceForVO();
		if (provider == null) 
		{
			
			return correspondenceForVO;
		}
		correspondenceForVO.setEntityTypeCode(ContactManagementConstants.ENTITY_TYPE_UNPROV);
		String shortDesc = ReferenceServiceDelegate
				.getReferenceSearchShortDescription(
						FunctionalAreaConstants.GENERAL,
						ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
						new Long(80), ContactManagementConstants.ENTITY_TYPE_UNPROV);
		
		correspondenceForVO.setEntityTypeDesc(shortDesc);
		
		correspondenceForVO.setEntitySysId(provider.getCommonEntity().getCommonEntitySK().toString());

		
		correspondenceForVO.setEntityTypeCodeForNote(correspondenceForVO.getEntitySysId());

		correspondenceForVO.setName(provider.getName().getSortName());
		correspondenceForVO.setNameForNote(correspondenceForVO.getName());
		
		correspondenceForVO.setCmEntityID(provider.getCommonEntity().getCommonEntitySK().toString());
		correspondenceForVO.setEntityId(provider.getCommonEntity().getCommonEntitySK().toString());
		
		Set setOfProviderTypes = provider.getProviderTypes();
		if (setOfProviderTypes != null) {
			for (Iterator iter = setOfProviderTypes.iterator(); iter.hasNext();) {
				ProviderType provType = (ProviderType) iter.next();
				correspondenceForVO.setProviderType(provType.getProviderTypeCode());
			}
		}
		String providerTypeDesc = ReferenceServiceDelegate.getReferenceSearchShortDescription(
										FunctionalAreaConstants.PROVIDER,ReferenceServiceDataConstants.P_TY_CD,
										new Long(1062),correspondenceForVO.getProviderType());
		correspondenceForVO.setProviderTypeStr(providerTypeDesc);
		
		Set setOfProviderSpecialties = provider.getBoardCertifiedSpeciality();
		if (setOfProviderSpecialties != null) {
			for (Iterator iter = setOfProviderSpecialties.iterator(); iter.hasNext();) 
			{
				BoardCertifiedSpeciality provSpcl = (BoardCertifiedSpeciality) iter.next();
				correspondenceForVO.setSpecialityCode(provSpcl.getSpecialityCode());
				break; // Get the first speciality
			}
		}
		return correspondenceForVO;
	}
	
	/**
     * @param entityId
     *            The entityId to set.
     * @param callingEntity
     *            The callingEntity to set.
     * @return CorrespondenceForProviderVO
     */
    protected CorrespondenceForVO getCorrespondenceForUnApprovedProvider(
            String entityId, boolean callingEntity)
    {
        
        
        ProviderInformationRequestVO providerInfRequestVO = new ProviderInformationRequestVO();

        providerInfRequestVO.setProviderSysID(Long.valueOf(entityId));
        providerInfRequestVO.setAlternateIdInfoRequired(true);
        providerInfRequestVO.setEnrollmentStatusRequired(true);
        providerInfRequestVO.setRepresentativeContactRequired(true);
        providerInfRequestVO.setBoardCertifiedSpecialityRequired(true);
        providerInfRequestVO.setProviderTypesRequired(true);

        ProviderInformationDelegate providerInformationDelegate = new ProviderInformationDelegate();

        Provider provider = providerInformationDelegate
                .getProviderDetails(providerInfRequestVO);

        CorrespondenceForVO correspondenceForVO = CorrespondenceDOConvertor
                .getCrspdForUnApprovedProvider(provider);

        if (callingEntity && provider != null)
        {
            CorrespondenceDOConvertor.setAlternateIds(provider);
            CommonEntity commonEntity = provider.getCommonEntity();
            CorrespondenceDOConvertor.setCommonContactList(commonEntity);
        }
        return correspondenceForVO;
    }
    
    private static void sortContact(List contactList) 
        {
    		Comparator selectItemComparator = new Comparator() 
    		{
    			public int compare(Object obj1, Object obj2) 
    			{
    				String first = null;
    				String second = null;

    				SelectItem s1 = (SelectItem) obj1;
    				SelectItem s2 = (SelectItem) obj2;

    				boolean ascending = true;

    				if (s1.getLabel() != null && s2.getLabel() != null) 
    				{
    					first = s1.getLabel().toLowerCase();
    					second = s2.getLabel().toLowerCase();
    				
    					
    					return first.compareTo(second);
    				}
    				return 0;
    			}

    		};
    		Collections.sort(contactList, selectItemComparator);
    	}
//  CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010
	/**
	 * @param TPLPolicyHolder
	 *            The TPLPolicyHolder to set.
	 * @return CorrespondenceForVO
	 */
	public static CorrespondenceForVO getCrspdForTplPolicyHolderVO(TPLPolicyHolder tplPolicyHolder) {
		/** Enterprise Logger for Logging. */
		EnterpriseLogger logger = EnterpriseLogFactory
				.getLogger(CorrespondenceDOConvertor.class);

		
		CorrespondenceForVO correspondenceForTPLVO = new CorrespondenceForVO();

		if (tplPolicyHolder == null) {
			return correspondenceForTPLVO;
		}
		correspondenceForTPLVO.setCarrierID(tplPolicyHolder.getCarrierID());
		correspondenceForTPLVO
				.setEntityTypeCode(ContactManagementConstants.ENTITY_TYPE_TP);
		String shortDesc = ReferenceServiceDelegate
				.getReferenceSearchShortDescription(
						FunctionalAreaConstants.GENERAL,
						ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
						new Long(80),
						ContactManagementConstants.ENTITY_TYPE_TP);
		
		correspondenceForTPLVO.setEntityTypeDesc(shortDesc);
		
		if (tplPolicyHolder.getCommonEntity() != null) {
			correspondenceForTPLVO.setCmEntityID(tplPolicyHolder.getCommonEntity()
					.getCommonEntitySK().toString());
			correspondenceForTPLVO.setEntityId(tplPolicyHolder.getCommonEntity()
					.getCommonEntitySK().toString());
		}

        if(tplPolicyHolder!=null){
        	if(tplPolicyHolder.getName()!=null){
        		correspondenceForTPLVO.setPolicyHolderName(tplPolicyHolder.getName().getFirstName()+" "+ tplPolicyHolder.getName().getLastName());
        		correspondenceForTPLVO.setName(correspondenceForTPLVO.getPolicyHolderName());
        	}
         /*   if (tplPolicyHolder.getCommonEntity() != null)
            {
            	
            	correspondenceForTPLVO.setTplPolicyHolderId(tplPolicyHolder.getCommonEntity()
                        .getCommonEntitySK().toString());
            }*/

        	if(tplPolicyHolder.getPolicyHolderID() != null)
        	{
        	correspondenceForTPLVO.setTplPolicyHolderId(tplPolicyHolder.getPolicyHolderID());
        	}   
            if(tplPolicyHolder.getTplGroupPolicy()!=null){
            	correspondenceForTPLVO.setTplPolicyGroupId(String.valueOf(tplPolicyHolder.getTplGroupPolicy().getPolicyReferenceSK()));	
            	
            }
            
        }
        correspondenceForTPLVO.setEntityTypeCodeForNote(correspondenceForTPLVO.getTplPolicyHolderId());
        correspondenceForTPLVO.setNameForNote(correspondenceForTPLVO.getName());

		return correspondenceForTPLVO;
	}
	//EOf CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010
	
	public CorrespondenceForVO getCorrespondenceForTPLPolicyHolder(String entityId, boolean callingEntity){
     	CorrespondenceForVO correspondenceForVO=null;
     	 //CaseDelegate caseDelegate = new CaseDelegate();
          //TPLPolicyHolder tplPolicyHolder;
		TPLPolicyDelegate tplPolicyDelegate = new TPLPolicyDelegate();
		TPLPolicyHolderDetailsVO tplPolicyHolderDetailsVO;
		try {

			// tplPolicyHolder = caseDelegate.getTPLPloicyHolder(new
			// Long(entityId));
			tplPolicyHolderDetailsVO = tplPolicyDelegate
					.getTPLPloicyHolderForCase(new Long(entityId));
			/*
			 * if (tplPolicyHolder != null) { correspondenceForVO =
			 * CorrespondenceDOConvertor
			 * .getCrspdForTplPolicyHolderVO(tplPolicyHolder); }
			 */
			if (tplPolicyHolderDetailsVO != null) {
				correspondenceForVO = CorrespondenceDOConvertor
						.getCrspdForTplPolicyHolderDetailsVO(tplPolicyHolderDetailsVO);
			}
		} catch (EnterpriseBaseBusinessException e) {
			
			e.printStackTrace();
		}
     	return  correspondenceForVO;
     }
	
	
	
	
	 private void createVOAuditKeysList(EnterpriseBaseDomain enterpriseBaseDomain,AuditaleEnterpriseBaseVO  auditaleEnterpriseBaseVO){
    	
    	  List fullAuditList = new ArrayList();
    	  
    	  if(auditaleEnterpriseBaseVO.getAuditKeyList()!= null){
    	  	fullAuditList= auditaleEnterpriseBaseVO.getAuditKeyList();
    	  }
          
    	  
    	  
           LineItemAuditsDelegate auditDelegate;
    	try {
    		auditDelegate = new LineItemAuditsDelegate();
    		List resultList = auditDelegate.getAuditKeyList(enterpriseBaseDomain);
           
    	       if(resultList!=null){
    	       	fullAuditList.addAll(resultList);
    	       	
    	       	
    	       	auditaleEnterpriseBaseVO.setAuditKeyList(fullAuditList);
    	       }
    	} catch (LineItemAuditsBusinessException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
          
     
     }
	 
	 
	 /**	code fix for ESPRD00683016
		 * @param categorySK
		 *            The categorySK to set.
		 * @param listOfCategoryDOs
		 *            The listOfCategoryDOs to set.
		 * @return CorrespondenceCategory
		 */
		private CorrespondenceCategory getSelectedCategory(String categorySK,
				List listOfCategoryDOs) {

			if (listOfCategoryDOs == null) {
				return null;
			}

			for (Iterator iter = listOfCategoryDOs.iterator(); iter.hasNext();) {
				CorrespondenceCategory correspondenceCategory = (CorrespondenceCategory) iter
						.next();

				if (correspondenceCategory.getCategorySK().toString()
						.equalsIgnoreCase(categorySK)) {
					return correspondenceCategory;
				}
			}

			return null;
		}

	public static CorrespondenceForVO getCrspdForTplPolicyHolderDetailsVO(
			TPLPolicyHolderDetailsVO tplPolicyHolderDetailsVO) {
		EnterpriseLogger logger = EnterpriseLogFactory
				.getLogger(CorrespondenceDOConvertor.class);

		
		CorrespondenceForVO correspondenceForTPLVO = new CorrespondenceForVO();

		if (tplPolicyHolderDetailsVO == null) {
			return correspondenceForTPLVO;
		}
		correspondenceForTPLVO.setCarrierID(tplPolicyHolderDetailsVO
				.getCarrierID());
		correspondenceForTPLVO
				.setEntityTypeCode(ContactManagementConstants.ENTITY_TYPE_TP);
		String shortDesc = ReferenceServiceDelegate
				.getReferenceSearchShortDescription(
						FunctionalAreaConstants.GENERAL,
						ReferenceServiceDataConstants.G_CE_OR_SE_TY_CD,
						new Long(80), ContactManagementConstants.ENTITY_TYPE_TP);
		
		correspondenceForTPLVO.setEntityTypeDesc(shortDesc);

		if (tplPolicyHolderDetailsVO.getCommonEntitySK() != null) {
			correspondenceForTPLVO.setCmEntityID(tplPolicyHolderDetailsVO
					.getCommonEntitySK().toString());
			correspondenceForTPLVO.setEntityId(tplPolicyHolderDetailsVO
					.getCommonEntitySK().toString());
			correspondenceForTPLVO.setEntitySysId(tplPolicyHolderDetailsVO
					.getCommonEntitySK().toString());
		}

		String policyHolderName = ContactManagementConstants.EMPTY_STRING;
		if (tplPolicyHolderDetailsVO.getFirstName() != null) {
			policyHolderName = tplPolicyHolderDetailsVO.getFirstName();
		}
		if (tplPolicyHolderDetailsVO.getLastName() != null) {
			policyHolderName = policyHolderName + " "
					+ tplPolicyHolderDetailsVO.getLastName();
		}
		correspondenceForTPLVO.setName(policyHolderName);
		correspondenceForTPLVO.setPolicyHolderName(policyHolderName);

		if (tplPolicyHolderDetailsVO.getPolicyHolderID() != null) {
			correspondenceForTPLVO
					.setTplPolicyHolderId(tplPolicyHolderDetailsVO
							.getPolicyHolderID());
		}
		if (tplPolicyHolderDetailsVO.getPolicyReferenceSK() != null) {
			correspondenceForTPLVO.setTplPolicyGroupId(String
					.valueOf(tplPolicyHolderDetailsVO.getPolicyReferenceSK()));

		}

		correspondenceForTPLVO.setEntityTypeCodeForNote(correspondenceForTPLVO
				.getTplPolicyHolderId());
		correspondenceForTPLVO.setNameForNote(correspondenceForTPLVO.getName());

		return correspondenceForTPLVO;
	}
}