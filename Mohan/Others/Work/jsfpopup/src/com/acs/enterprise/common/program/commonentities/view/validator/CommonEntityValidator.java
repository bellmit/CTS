/*
 * Created on Mar 15, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.commonentities.view.validator;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramNumberConstants;
//import com.acs.enterprise.common.program.benefitadministration.util.helper.BenefitPlanConstants;
import com.acs.enterprise.common.program.commonentities.common.domain.CommonEntity;
import com.acs.enterprise.common.program.commonentities.common.domain.NoteSet;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonContactControllerBean;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonEntityDataBean;
import com.acs.enterprise.common.program.commonentities.view.bean.CommonNotesControllerBean;
import com.acs.enterprise.common.program.commonentities.view.exception.CommonEntityUIException;
import com.acs.enterprise.common.program.commonentities.view.helper.ContactHelper;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonContactVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonEntityVO;
import com.acs.enterprise.common.program.commonentities.view.vo.CommonNotesVO;
import com.acs.enterprise.common.program.commonentities.view.vo.EAddressVO;
import com.acs.enterprise.common.program.commonentities.view.vo.NoteSetVO;
import com.acs.enterprise.common.program.commonentities.view.vo.PhoneVO;
import com.acs.enterprise.common.util.helper.MessageUtil;
import com.acs.enterprise.common.util.helper.ValidatorConstants;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
import com.acs.enterprise.common.util.validator.EnterpriseCommonValidator;

import com.acs.enterprise.common.util.helper.EnterpriseMessageConstants;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.bean.CorrespondenceDataBean;

/**
 * This is an util class for the Validating common entities.
 */
public class CommonEntityValidator {

	/**
	 * Holds commonEntityDataBean.
	 */

	private CommonEntityDataBean commonEntityDataBean = null;

	/** Enterprise Logger for Logging. */
	private transient EnterpriseLogger logger = EnterpriseLogFactory
			.getLogger(CommonEntityValidator.class);

	/**
	 * This method will validate (Phone/Address/EAddress/Contact/Note) VO's if
	 * they are not Validated. And update them to the CommonEntityVO.
	 * 
	 * @return CommonEntity
	 * @throws CommonEntityUIException
	 *             This method throws CommonEntityUIException.
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public CommonEntity getValidCommonEntityDO() throws CommonEntityUIException {
		logger.debug("CommonEntityValidator....getvalidcommonentitydo");
		ContactHelper contactHelper = null;
		CommonEntity commonEntity = null;
		CommonEntityVO commonEnitityVO = null;
		boolean isNotesValid = true;
		boolean isContactValid = true;
		/**
		 * commented as part of ES2 change
		 */
		// boolean isConatctTypeValid = true;
		commonEntityDataBean = ContactHelper.getCommonEntityDataBean();
		commonEnitityVO = commonEntityDataBean.getCommonEntityVO();
		if (commonEntityDataBean.isSaveNotesChk()) {
			isNotesValid = validateNotes(commonEnitityVO.getCommonNotesVO());
			if (isNotesValid) {
				CommonNotesControllerBean notesController = new CommonNotesControllerBean();
				notesController.saveNotes();
			}
		}

		if (commonEntityDataBean.isSaveContactChk()) {
			logger.debug("CommonEntityValidator....inside issavecontactchk");
			isContactValid = validateContact(commonEnitityVO
					.getCommonContactVO());
			if (isContactValid) {
				logger.debug("CommonEntityValidator....inside if");
				CommonContactControllerBean contactControllerBean = new CommonContactControllerBean();
				contactControllerBean.saveContact();
			}
		}

		if (!isNotesValid || !isContactValid) {
			throw new CommonEntityUIException("Error",
					"Requried values for CommonEntites Missing");
		}
		logger.debug("CommonEntityValidator....before contact helper");
		contactHelper = new ContactHelper();
		commonEntity = contactHelper
				.convertCommonEntityVOToDomain(commonEnitityVO);

		return commonEntity;
	}

	/**
	 * This method will make a call to Validate EmailAddress method in the
	 * common class and if it is valid it will update in the CommonEntityVO.
	 * 
	 * @param eAddressVO
	 *            holds eAddressVO info.
	 * @return boolean
	 */
	public boolean validateEAddress(EAddressVO eAddressVO) {
		
		CommonEntityDataBean commonEntityDataBean = getCEntityDataBean();
		String eAddress = null;
		Date beginDate = null;
		Date endDate = null;
		String usageTypeCode = null;
		String significanceTypeCode = null;
		String statusCode = null;
		boolean errFlg = true;
		boolean validDates = true;
		Date sysDate = new Date();
		eAddress = eAddressVO.getEaddress();
		significanceTypeCode = eAddressVO.getSignificance();
		beginDate = ContactHelper.dateConverter(eAddressVO.getBeginDate());
		endDate = ContactHelper.dateConverter(eAddressVO.getEndDate());
		usageTypeCode = eAddressVO.getUsageTypeCode();
		statusCode = eAddressVO.getStatus();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				ProgramConstants.DATE_FORMAT, Locale.getDefault());
		int x = 0;

		try {
			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
			sysDate = df.parse(df.format(new Date()));
		} catch (ParseException pe) {
			logger.error("Error During Parsing date in ContactHelper" + pe);
		}

		if (eAddress == null || eAddress.trim().length() == x) {
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { "E-Address" },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"inputEadd");
			errFlg = false;

		}
		//FindBug Issue Resolved
		//else if (eAddress != null || eAddress.trim().length() > 0) {
		else if (eAddress != null && eAddress.trim().length() > 0) {
			if (!EnterpriseCommonValidator.validateEmail(eAddress)) {
				setErrorMessage(ProgramConstants.EADDRESS_INVALID,
						new Object[] {},
						ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
						"inputEadd");
				errFlg = false;

			}
		}
		//to check begin date equal to null
		/*
		 * if (eAddressVO.getBeginDate() == null &&
		 * eAddressVO.getBeginDate().trim().length() == 0) {
		 * setErrorMessage(ProgramConstants.EADDRESS_BEGIN_DATE_REQUIRED, new
		 * Object[] {}, ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, null);
		 * errFlg = false; validDates = false; }
		 */

		/** To check and formta Begin date */
		if (eAddressVO.getBeginDate() != null
				&& eAddressVO.getBeginDate().trim().length() != 0) {
			if (EnterpriseCommonValidator.validateDate(eAddressVO
					.getBeginDate())) {
				try {
					eAddressVO.setBeginDate(getValidateDate(eAddressVO
							.getBeginDate()));
					dateFormat.setLenient(false);
					beginDate = ContactHelper.dateConverter(eAddressVO
							.getBeginDate());
					dateFormat.parse(eAddressVO.getBeginDate());
				} catch (ParseException e1) {
					logger.debug("in validating dates, parsing error ");

				}
			} else {
				setErrorMessage(ProgramConstants.WRONG_DATE_FORMAT,
						new Object[] {ContactManagementConstants.BEGIN_DATE},
						ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "bdate");
				errFlg = false;
				validDates = false;
			}
		}
		/** To check and formta End date */
		if (eAddressVO.getEndDate() != null
				&& eAddressVO.getEndDate().trim().length() != 0) {

			if (EnterpriseCommonValidator.validateDate(eAddressVO.getEndDate())) {
				try {
					eAddressVO.setEndDate(getValidateDate(eAddressVO
							.getEndDate()));
					dateFormat.setLenient(false);
					endDate = ContactHelper.dateConverter(eAddressVO
							.getEndDate());
					dateFormat.parse(eAddressVO.getEndDate());
				} catch (ParseException e1) {
					logger.error("Error in validating dates, parsing error "
							+ e1);

				}
			} else {

				setErrorMessage(ProgramConstants.WRONG_DATE_FORMAT,
						new Object[] {ContactManagementConstants.END_DATE},
						ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "edate");
				errFlg = false;
				validDates = false;

			}
		}
		/* If begin date is not entered */
		if (eAddressVO.getBeginDate() == null
				|| eAddressVO.getBeginDate().trim().length() == 0) {
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.BEGIN_DATE },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"bdate");
			errFlg = false;
		}
		logger.debug("Sysdate " + sysDate);
		logger.debug("beginDate " + beginDate);
		/* If end date is not entered */
		if (eAddressVO.getEndDate() == null
				|| eAddressVO.getEndDate().trim().length() == 0) {
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { ContactManagementConstants.END_DATE },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"edate");
			errFlg = false;
		}
		/* To check other date rules, when dates are entered in valid format */
		
		if (validDates
				&& (eAddressVO.getBeginDate() != null && eAddressVO
						.getBeginDate().trim().length() > 0))
		{  
			if (commonEntityDataBean.isEAddressFlag()) 
			 {			
				if (beginDate.compareTo(sysDate) < 0)
				{
					setErrorMessage(ProgramConstants.BEGIN_DATE_LESSTHAN_SYSDATE,
							new Object[] {},
							ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "bdate");
					errFlg = false;
				}
			 }
		}
		
		if (eAddressVO.getEndDate() != null && eAddressVO.getEndDate()
				.trim().length() > 0)
		{  
			/*	if ( beginDate.compareTo(endDate) > 0) */
			if (endDate.compareTo(beginDate) < 0) 
			
					
				{
				logger.debug(" inside if condition date check error**********");
					setErrorMessage(ProgramConstants.END_DATE_LESSTHAN_BEGINDATE,
							new Object[] {},
							ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "edate");
					errFlg = false;
				}
			 
		 }
		
		/* end of overlap dates check */
		if (usageTypeCode == null || usageTypeCode.trim().length() == x) {

			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { "E-Address Type Code" },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"eaddrtycode");

			errFlg = false;
		}

		//check significancetype code equal to null
		if (significanceTypeCode == null
				|| significanceTypeCode.trim().length() == 0) {
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_REQUIRED,
					new Object[] { "Significance Type Code" },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"sgnfcnce");
			errFlg = false;
		}
		//check status code equal to null
		if (statusCode == null || statusCode.trim().length() == 0) {
			setErrorMessage(ProgramConstants.EADDRESS_STATUS_CODE_NOT_NULL,
					new Object[] {},
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "stattype");
			errFlg = false;
		}
		return errFlg;
	}

	/**
	 * This method will make a call to Validate phone method in the common class
	 * and if it is valid it will update in the CommonEntityVO.
	 * 
	 * @param phoneVO
	 *            holds phone info.
	 * @return boolean
	 */
	public boolean validatePhone(PhoneVO phoneVO) {
		logger.debug("commonentityvalidator...phone validation...");
		String phoneNo = null;
		Date beginDate = null;
		String phoneType = null;
		Date endDate = null;
		Date sysDate = new Date();
		String usageTypeCode = null;
		//Added as part of ES2
		boolean validDates = true;
		boolean errFlg = true;
		logger.debug("1..phone validation...");
		commonEntityDataBean = ContactHelper.getCommonEntityDataBean();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				ProgramConstants.DATE_FORMAT, Locale.getDefault());
		phoneNo = phoneVO.getPhoneNumber();
		String phoneExt = phoneVO.getExtension();
		
		beginDate = ContactHelper.dateConverter(phoneVO.getBeginDate());
		endDate = ContactHelper.dateConverter(phoneVO.getEndDate());
		logger.debug("2..phone validation...");
		usageTypeCode = phoneVO.getUsageTypeCode();
		logger.debug("3..phone validation...");
		String intnPhoneNumber = phoneVO.getInternationalPhoneNo();
		logger.debug("4..phone validation...");
		int x = 0;
		try {
			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
			sysDate = df.parse(df.format(new Date()));
		} catch (ParseException pe) {
			logger.error("Error During Parsing date in ContactHelper" + pe);

		}

		/*
		 * if (phoneVO.getPhoneType() == null ||
		 * phoneVO.getPhoneType().trim().length() == x) {
		 * setErrorMessage(ProgramConstants.ERR_SW_REQUIRED, new Object[]
		 * {"Phone Type"}, ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
		 * "usgTyp2"); errFlg = false; }
		 */

		if (phoneNo == null || phoneNo.trim().length() == x) {
			setErrorMessage(ProgramConstants.ERR_SW_REQUIRED,
					new Object[] { "Phone Number" },
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "phno");
			errFlg = false;
		}
		//FindBug Issue Resolved
		// Added for CR - ESPRD00546167
		//else if (phoneNo != null || phoneNo.trim().length() > 0) {
		else if (phoneNo != null && phoneNo.trim().length() > 0) {
			if (!EnterpriseCommonValidator.validatePhoneNumber(phoneNo)) {
				setErrorMessage(ProgramConstants.INVALID_PHONE_DETAILS_NO_FORMAT_NEW,
						new Object[] {},
						ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "phno");
				errFlg = false;
			} else {

				phoneNo = phoneNo.replaceAll("-", "");
				char phoneDBArr[] = phoneNo.toCharArray();
				char phoneUIArr[] = new char[(phoneDBArr.length + 2)];

				for (int i = 0, j = 0; i < phoneDBArr.length; i++, j++) {
					if (i == 3 || i == 6) {
						phoneUIArr[j] = '-';
						j++;
						phoneUIArr[j] = phoneDBArr[i];
					} else
						phoneUIArr[j] = phoneDBArr[i];
				}
				phoneVO.setPhoneNumber(new String(phoneUIArr));

			}
		}
		logger.debug("5..phone validation...");
		if (phoneExt != null && phoneExt.trim().length() > x) {
			if (!EnterpriseCommonValidator.validatePhoneExtn(phoneExt)) {
				setErrorMessage(ProgramConstants.INVALID_PHONE_DETAILS_FORMAT,
						new Object[] {},
						ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "extnsn");
				errFlg = false;
			}
		}
		if (intnPhoneNumber != null && intnPhoneNumber.trim().length() > x) {
			if (!validateIntnPhoneNumber(intnPhoneNumber)) {
				setErrorMessage(ProgramConstants.INVALID_PHONE_DETAILS_FORMAT,
						new Object[] {},
						ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
						"intrnlphone");
				errFlg = false;
			}
		}
		logger.debug("6..phone validation...");
		/* to check and set the valid date format for begin date */
		if (phoneVO.getBeginDate() != null
				&& phoneVO.getBeginDate().trim().length() != x) {
			if (EnterpriseCommonValidator.validateDate(phoneVO.getBeginDate())) {

				try {
					phoneVO
							.setBeginDate(getValidateDate(phoneVO
									.getBeginDate()));
					dateFormat.setLenient(false);
					beginDate = ContactHelper.dateConverter(phoneVO
							.getBeginDate());
					dateFormat.parse(phoneVO.getBeginDate());
				} catch (ParseException e1) {
					logger.debug("validating phone error " + e1);
				}
			} else {
				setErrorMessage(ProgramConstants.WRONG_DATE_FORMAT,
						new Object[] {ContactManagementConstants.BEGIN_DATE},
						ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
						"PhBgDT1");
				errFlg = false;
				validDates = false;
			}

		}
		logger.debug("7..phone validation...");
		/* to check and set the valid date format for end date */
		if (phoneVO.getEndDate() != null
				&& phoneVO.getEndDate().trim().length() != x) {
			if (EnterpriseCommonValidator.validateDate(phoneVO.getEndDate())) {
				try {
					phoneVO.setEndDate(getValidateDate(phoneVO.getEndDate()));
					dateFormat.setLenient(false);
					endDate = ContactHelper.dateConverter(phoneVO.getEndDate());
					dateFormat.parse(phoneVO.getEndDate());
				} catch (ParseException e1) {
					logger.error("in validating dates, parsing error " + e1);
				}
			} else {

				setErrorMessage(ProgramConstants.WRONG_DATE_FORMAT,
						new Object[] {ContactManagementConstants.END_DATE},
						 ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
						"PhEndDt1");
				errFlg = false;
				validDates = false;

			}

		}
		logger.debug("8..phone validation...");
		/* if begin date is not entered */
		if (phoneVO.getBeginDate() == null
				|| phoneVO.getBeginDate().trim().length() == x) {
			setErrorMessage(ProgramConstants.ERR_SW_REQUIRED,
					new Object[] { "Begin Date " },
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "PhBgDT1");
			errFlg = false;
		}
		/* if end date is not entered */
		if (phoneVO.getEndDate() == null
				|| phoneVO.getEndDate().trim().length() == x) {
			setErrorMessage(ProgramConstants.ERR_SW_REQUIRED,
					new Object[] { "End Date" },
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "PhEndDt1");
			errFlg = false;
		}

		logger.debug("9..phone validation...");
		if (validDates
				&& (phoneVO.getBeginDate() != null && phoneVO.getBeginDate()
						.trim().length() > 0))
		{
		    if (commonEntityDataBean.isPhoneFlag()) {
				if (sysDate.compareTo(beginDate) > 0) {
					setErrorMessage(
							ProgramConstants.BEGIN_DATE_LESSTHAN_SYSDATE,
							new Object[] {},
							ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
							"PhBgDT1");
					errFlg = false;
					logger.debug("checking the Begin Date compare to SysDate");
				}
			}
		
		
		}
		
		
		
		/* when both dates are valid and to check other date rules */
		if (validDates
				&& (phoneVO.getEndDate() != null && phoneVO.getEndDate().trim()
						.length() > 0)
				&& (phoneVO.getBeginDate() != null && phoneVO.getBeginDate()
						.trim().length() > 0)) 
		{
			if (endDate.compareTo(beginDate) < 0) 
			{
				setErrorMessage(ProgramConstants.END_DATE_LESSTHAN_BEGINDATE,
						new Object[] {},
						ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
						"PhEndDt1");
				errFlg = false;

			}
			
			/* For CR-2196 This method is commented */
			/*
			 * if (beginDate.compareTo(endDate) == 0) { logger.debug("Before
			 * BeginDate & EndDate Comparision");
			 * setErrorMessage(ProgramConstants.BEGINDATE_EQUALS_ENDDATE, new
			 * Object[] {}, ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
			 * "PhBgDT1"); errFlg = false; logger.debug("After BeginDate &
			 * EndDate Comparision" + errFlg); }
			 */
		}
		if (validDates && (phoneVO.getEndDate() != null && phoneVO.getEndDate().trim()
				.length() > 0)) 
		{
			logger.debug("..phone validation for END DATE ...");
			if ((sysDate.compareTo(endDate)) > 0)
			{
				setErrorMessage(ProgramConstants.END_DATE_LESSTHAN_SYSTEMDATE,
				new Object[] {},
				ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
				"PhEndDt1");
				errFlg = false;

			}
	
		}
		
		logger.debug("10..phone validation...");
		if (usageTypeCode == null || usageTypeCode.trim().length() == x) {
			setErrorMessage(ProgramConstants.ERR_SW_REQUIRED,
					new Object[] { "Phone Type" },
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "usgTyp2");
			errFlg = false;
		}
		/*
		 * if (phoneVO.getPhoneNumber() != null &&
		 * phoneVO.getPhoneNumber().trim().length() != x) {
		 * 
		 * String phoneDB = phoneVO.getPhoneNumber(); char phoneDBArr[] =
		 * phoneDB.toCharArray(); char phoneUIArr[] = new
		 * char[(phoneDBArr.length + 2)];
		 * 
		 * for (int i = 0, j = 0; i < phoneDBArr.length; i++, j++) { if (i == 3 ||
		 * i == 6) { phoneUIArr[j] = '-'; j++; phoneUIArr[j] = phoneDBArr[i]; }
		 * else phoneUIArr[j] = phoneDBArr[i]; } phoneVO.setPhoneNumber(new
		 * String(phoneUIArr)); }
		 */
		logger.debug("11..phone validation...");
		return errFlg;
	}

	/**
	 * This method will make a call to Validate Note method in the common class
	 * and if it is valid it will update in the CommonEntityVO.
	 * 
	 * @param commonNotenote
	 *            holds common notcevo.
	 * @return boolean
	 */
	public boolean validateNotes(CommonNotesVO commonNotenote) {
		
	    CorrespondenceDataBean correspondenceDataBean=getCorrespondenceDataBean();
	    boolean valid = true;
		int x = 0;
		if (commonNotenote != null) {
			String noteText = commonNotenote.getNoteText();
			String usageTypeCode = commonNotenote.getUsageTypeCode();
			if (noteText == null || noteText.trim().length() == x) {
				setErrorMessage(ProgramConstants.NOTES_TEXT_NOT_NULL,
						new Object[] {},
						ProgramConstants.COMMON_NOTES_PROPERTIES, "lblNotes");
				valid = false;
				correspondenceDataBean.setCrSaved(false);
			}
			if (usageTypeCode == null || usageTypeCode.trim().length() == x) {
				setErrorMessage(ProgramConstants.USAGETYPE_CODE_NOT_NULL,
						new Object[] {},
						ProgramConstants.COMMON_NOTES_PROPERTIES,
						"lblUsageTypeCode");
				valid = false;
				correspondenceDataBean.setCrSaved(false);
			}
		}
		 /**
		 * Code is added below for not retaining the error messages 
		 * on Log Correspondence page for the Defect ESPRD00852896
		 */
		if(valid == false)
		{
			correspondenceDataBean.setLogCRErrMsgFlag(true);
		}
		else
		{
			correspondenceDataBean.setLogCRErrMsgFlag(false);
		}
		return valid;
	}

	/**
	 * This method will make a call to Validate Contact method in the common
	 * class and if it is valid it will update in the CommonEntityVO.
	 * 
	 * @param commonContactVO
	 *            holds common commoncontact info.
	 * @return boolean
	 */
	
	
	
	
	public boolean validateContact(CommonContactVO commonContactVO) {
		CommonEntityDataBean commonEntityDataBean = getCEntityDataBean();
		logger.debug("entered into validate contact");
		boolean valid = true;
		boolean validBeginDate = true;
		boolean validEndDate = true;
		String firstName = commonContactVO.getNameVO().getFirstName();
		String lastName = commonContactVO.getNameVO().getLastName();
		String middleName = commonContactVO.getNameVO().getMiddleName();
		String ssn = commonContactVO.getSSN();
		String title = commonContactVO.getTitle();
		//FindBug Issue Resolved
		//String dod = commonContactVO.getDateOfDeath();
		/* Added for new elements under Add contact section for ES2 */
		String status = commonContactVO.getStatus();
		String contactType = commonContactVO.getContactType();
		String significance = commonContactVO.getSignificance();
		Date strBeginDate = null;
		Date strEndDate = null;
		Date sysDate = new Date();
		strBeginDate = ContactHelper.dateConverter(commonContactVO
				.getBeginDate());
		strEndDate = ContactHelper.dateConverter(commonContactVO.getEndDate());
		/* End Add contact section for ES2 */
		Date dobirth = null;
		Date dodeath = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				ProgramConstants.DATE_FORMAT, Locale.getDefault());
		int x = 0;

		/* To check and set valid format for DOB */
		if (commonContactVO.getDateOfBirth() != null
				&& !commonContactVO.getDateOfBirth().equals(
						ProgramConstants.EMPTY_STRING)) {
			try {
				logger.debug("try for dob....");
				commonContactVO.setDateOfBirth(getValidateDate(commonContactVO
						.getDateOfBirth()));
				dateFormat.setLenient(false);
				dobirth = dateFormat.parse(commonContactVO.getDateOfBirth());
			} catch (ParseException e1) {
				logger.debug("catch for dob....");
				logger.debug("validating dates, parsing error ");
				setErrorMessage(ProgramConstants.CONTACT_TYPE_BEGINDATE_INVALID,
						new Object[] {},
						ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "dob2");
				valid = false;

			}

		}

		/* To check and set valid format for DOD */
		if (commonContactVO.getDateOfDeath() != null
				&& !commonContactVO.getDateOfDeath().equals(
						ProgramConstants.EMPTY_STRING)) {

			try {
				logger.debug("try for dod");
				commonContactVO.setDateOfDeath(getValidateDate(commonContactVO
						.getDateOfDeath()));
				dateFormat.setLenient(false);
				dodeath = dateFormat.parse(commonContactVO.getDateOfDeath());
			} catch (ParseException e1) {
				logger.debug("catch for dod....");
				logger.debug("validating dod ");
				setErrorMessage(ProgramConstants.CONTACT_TYPE_BEGINDATE_INVALID,
						new Object[] {},
						ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "dod");
				valid = false;

			}

		}

		/* To check dob is less than dod */
		if (dobirth != null && dodeath != null && dobirth.after(dodeath)) {
			logger.debug("dob less than dod error");
			setErrorMessage(ProgramConstants.CONTACT_DOD_GREATERTHAN_DOB,
					new Object[] {},
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "dob2");
			valid = false;
		}
		//Code for ES2----********************//
		/* To check and set valid format for contact type BeginDate */
		if (commonContactVO.getBeginDate() != null
				&& !commonContactVO.getBeginDate().equals(
						ProgramConstants.EMPTY_STRING)) {
			if (EnterpriseCommonValidator.validateDate(commonContactVO
					.getBeginDate())) {
				try {
					logger.debug("try for strbegindate....");
					commonContactVO
							.setBeginDate(getValidateDate(commonContactVO
									.getBeginDate()));
					dateFormat.setLenient(false);
					strBeginDate = dateFormat.parse(commonContactVO
							.getBeginDate());
				} catch (ParseException e1) {
					logger.debug("catch for strbegindate....");
					logger.debug("in validating strbegindate");
				}
			} else {
				setErrorMessage(
						ProgramConstants.WRONG_DATE_FORMAT,
						new Object[] {ContactManagementConstants.BEGIN_DATE},
						 ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "dob3");
				valid = false;
				validBeginDate=false;
			}
		}
		/* To check and set valid format for contact type EndDate */
		if (commonContactVO.getEndDate() != null
				&& !commonContactVO.getEndDate().equals(
						ProgramConstants.EMPTY_STRING)) {
			if (EnterpriseCommonValidator.validateDate(commonContactVO
					.getEndDate())) {
				try {
					logger.debug("try for strenddate....");
					commonContactVO.setEndDate(getValidateDate(commonContactVO
							.getEndDate()));
					dateFormat.setLenient(false);
					strEndDate = dateFormat.parse(commonContactVO.getEndDate());
				} catch (ParseException e1) {
					logger.debug("catch for strenddate....");
					logger.debug("in validating strenddate");
				}
			} else {
				setErrorMessage(ProgramConstants.WRONG_DATE_FORMAT,
						new Object[] {ContactManagementConstants.END_DATE},
						  ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "dob4");
				valid = false;

			}

		}

		/* To check if BeginDate is less then EndDate */
		//       ESPRD00059351
		/*
		 * if (strBeginDate != null && strEndDate != null &&
		 * strBeginDate.after(strEndDate)) {
		 * 
		 * logger.debug("str begin end error...."); setErrorMessage(
		 * ProgramConstants.CONTACT_END_DATE_LESSTHAN_BEGINDATE, new Object[]
		 * {}, ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "dob3"); valid =
		 * false;
		 *  }
		 */

		/* if begin date is not entered */
		if (commonContactVO.getBeginDate() == null
				|| commonContactVO.getBeginDate().trim().length() == x) {
			logger.debug("begin date not null...");
			setErrorMessage(ProgramConstants.ERR_SW_REQUIRED,
					new Object[] { "Begin Date" },
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "dob3");
			valid = false;

		}

		/* if End date is not entered */
		if (commonContactVO.getEndDate() == null
				|| commonContactVO.getEndDate().trim().length() == x) {
			logger.debug("end date not null....");
			setErrorMessage(ProgramConstants.ERR_SW_REQUIRED,
					new Object[] { " End Date" },
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "dob4");
			valid = false;
		}

		try {
			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
			sysDate = df.parse(df.format(new Date()));
		} catch (ParseException e) {
			logger.error("Error During Parsing date in ContactHelper" + e);
			logger.error(e.getMessage(), e);
		}

		logger.info("system date--->" + sysDate);

		if (validBeginDate && ((commonContactVO.getBeginDate() != null 
							&& commonContactVO.getBeginDate().trim().length() > 0)))
			{  
				if (commonEntityDataBean.isContactFlag()) 
				 {			
					if(strBeginDate.compareTo(sysDate) < 0) 
						{
							setErrorMessage(
									ProgramConstants.CONTACT_BEGIN_DATE_LESSTHAN_SYSDATE,
									new Object[] {},
									ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "dob3");
							valid = false;
						}
				 }
			 }
		/* To check if BeginDate is less then EndDate */

		if (validEndDate
				&& (((commonContactVO.getEndDate() != null && commonContactVO
						.getEndDate().trim().length() > 0) && (commonContactVO
						.getBeginDate() != null && commonContactVO
						.getBeginDate().trim().length() > 0)) && (strEndDate
						.compareTo(strBeginDate) < 0))) {
			setErrorMessage(
					ProgramConstants.CONTACT_END_DATE_LESSTHAN_BEGINDATE,
					new Object[] {},
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "dob4");
			valid = false;
		}

		//End ES2 code

		if (firstName == null || firstName.trim().length() == x) {
			setErrorMessage(ProgramConstants.ERR_SW_REQUIRED,
					new Object[] { " First Name" },
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "fstNam");
			valid = false;
		}
		if (firstName != null && !validateAlphaNumeric(firstName)) {

			setErrorMessage(
					//ContactManagementConstants.FIRST_NAME_MSG,
					ContactManagementConstants.DATA_IN_CORRECT_FORMAT,
					new Object[] {},
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "fstNam");
			valid = false;
		}

		if (lastName == null || lastName.trim().length() == x) {
			setErrorMessage(ProgramConstants.ERR_SW_REQUIRED,
					new Object[] { "Last Name" },
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "lstNam");
			valid = false;
		}
		if (lastName != null && !validateAlphaNumeric(lastName)) {
			setErrorMessage(
					//ContactManagementConstants.LAST_NAME_MSG,
					ContactManagementConstants.DATA_IN_CORRECT_FORMAT,
					new Object[] {},
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "lstNam");
			valid = false;
		}
		if (middleName != null && !validateAlphaNumeric(middleName)) {
			setErrorMessage(ContactManagementConstants.DATA_IN_CORRECT_FORMAT,
					new Object[] {},
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "mdlName");
			valid = false;
		}

		if (title != null && !validateAlphaNumeric(title)) {
			setErrorMessage(ProgramConstants.CONTACT_VALID_TITLE,
					new Object[] {},
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "title");
			valid = false;
		}
		/**
		 * Removed as part of ES2 change
		 */

		/*
		 * if (entityType == null || entityType.trim().length() == 0) {
		 * setErrorMessage(ProgramConstants.CONTACT_ENTITY_TYPE_NOT_NULL,
		 * ProgramConstants.COMMON_CONTACT_PROPERTIES, null); valid = false; }
		 */
		//Added for ES2
		if (status == null || status.trim().length() == x) {
			setErrorMessage(ProgramConstants.ERR_SW_REQUIRED,
					new Object[] { "Status " },
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "status");
			valid = false;
		}
		if (contactType == null || contactType.trim().length() == x) {
			setErrorMessage(ProgramConstants.ERR_SW_REQUIRED,
					new Object[] { "Contact Type " },
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE, "contyp");
			valid = false;
		}
		if (significance == null || significance.trim().length() == x) {
			setErrorMessage(ProgramConstants.ERR_SW_REQUIRED,
					new Object[] { " Significance " },
					ProgramConstants.COMMON_ERROR_MESSAGES_BUNDLE,
					"significance");
			valid = false;
		}
		//End code for ES2
		if (ssn != null && ssn.trim().length() != x
				&& !EnterpriseCommonValidator.validateSSN(ssn)) {
			/* Using existing Error message for SSN Invalid format */
			setErrorMessage(EnterpriseMessageConstants.ERR_SW_SSN_INVALID,
					new Object[] { ContactManagementConstants.SSN_NUMBER },
					ContactManagementConstants.CONTACT_MGMT_CMN_ERR_MSG_BUNDLE,
					"ssn");
			valid = false;
		}
		return valid;
	}

	/**
	 * This is a default constructor, in this constructor ContactEntityVO should
	 * be updated with the Latest value from the CommonEntityDataBean.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public CommonEntityValidator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * To get NoteSet DO.
	 * 
	 * @return NoteSet
	 * @throws CommonEntityUIException
	 *             CommonEntityUIException is thrown.
	 */
	public NoteSet getNoteSetDO() throws CommonEntityUIException {
		NoteSet noteSet = null;
		boolean validNotes = true;
		CommonEntityDataBean commonEntityDataBean = ContactHelper
				.getCommonEntityDataBean();
		if (commonEntityDataBean != null) {
			CommonEntityVO commonEnitityVO = commonEntityDataBean
					.getCommonEntityVO();
			logger.info("commonEntityDataBean.isSaveNotesChk()"
					+ commonEntityDataBean.isSaveNotesChk());

			if (commonEntityDataBean.isSaveNotesChk()) {
				validNotes = validateNotes(commonEnitityVO.getCommonNotesVO());
				if (!validNotes) {
					throw new CommonEntityUIException("error",
							"Requried values for CommonEntites Missing");
				} else {
					CommonNotesControllerBean notesController = new CommonNotesControllerBean();
					notesController.saveNotes();
				}
			}
			ContactHelper contactHelper = new ContactHelper();
			NoteSetVO noteSetVO = commonEntityDataBean.getCommonEntityVO()
					.getNoteSetVO();
			if (null != noteSetVO) {
				noteSet = contactHelper.convertNoteSetVOToDomain(noteSetVO);
			} else {
				logger.debug("noteSetVO is null");
			}
		}
		return noteSet;
	}

	/**
	 * This method is used for populating Resource Bundle.
	 * 
	 * @param facesContext
	 *            facesContext.
	 * @return ResourceBundle
	 */
	public static ResourceBundle resourceBundle(FacesContext facesContext) {
		facesContext = FacesContext.getCurrentInstance();
		UIViewRoot root = facesContext.getViewRoot();
		String messageBundle = facesContext.getApplication().getMessageBundle();
		Locale locale = root.getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(messageBundle, locale);
		return bundle;
	}

	/**
	 * @param eAddressVOList
	 *            eAddressVOList is a parameter
	 * @param beginDate
	 *            beginDate is a parameter
	 * @param endDate
	 *            endDate is a parameter
	 * @param selectedIndex
	 *            selectedIndex is a parameter
	 * @return Returns boolean
	 */
	/*
	 * private boolean isDateOverlapForEAddress(ArrayList eAddressVOList, Date
	 * beginDate, Date endDate, int selectedIndex) {
	 * 
	 * logger.debug("Inside the isDateOverlapForEAddress() ++++++++++"); boolean
	 * overlapFlg = false; for (int i = 0, size = eAddressVOList.size(); i <
	 * size; i++) { EAddressVO eAddressVO = (EAddressVO) eAddressVOList.get(i);
	 * if (eAddressVO != null && selectedIndex != i) { if
	 * (beginDate.compareTo(ContactHelper.dateConverter(eAddressVO
	 * .getBeginDate())) >= 0 && beginDate.compareTo(ContactHelper
	 * .dateConverter(eAddressVO.getEndDate())) <= 0) { overlapFlg = true; } if
	 * (endDate.compareTo(ContactHelper.dateConverter(eAddressVO
	 * .getBeginDate())) >= 0 && endDate.compareTo(ContactHelper
	 * .dateConverter(eAddressVO.getEndDate())) <= 0) { overlapFlg = true;
	 * break; } } }
	 * 
	 * return false; }
	 */

	/**
	 * @param contactTypeVOList
	 *            contactTypeVOList is a parameter
	 * @param contactType
	 *            contactType is a parameter
	 * @param beginDate
	 *            beginDate is a parameter
	 * @param endDate
	 *            endDate is a parameter
	 * @param selectedIndex
	 *            selectedIndex is a parameter
	 * @return Returns boolean
	 */
	/*
	 * private boolean isDateOverlapping(ArrayList contactTypeVOList, String
	 * contactType, Date beginDate, Date endDate, int selectedIndex) {
	 * 
	 * boolean overlapFlg = false;
	 * 
	 * for (int i = 0, size = contactTypeVOList.size(); i < size; i++) {
	 * CommonContactTypeVO contactTypeVO = (CommonContactTypeVO)
	 * contactTypeVOList .get(i); if (contactTypeVO != null && selectedIndex !=
	 * i && !contactTypeVO.getVoidIndicator().equals("0")) { if (contactType !=
	 * null && contactType.equals(contactTypeVO.getContactType())) { if
	 * (beginDate.compareTo(ContactHelper
	 * .dateConverter(contactTypeVO.getBeginDate())) >= 0 &&
	 * beginDate.compareTo(ContactHelper
	 * .dateConverter(contactTypeVO.getEndDate())) <= 0) { overlapFlg = true;
	 * break; } if (endDate.compareTo(ContactHelper.dateConverter(contactTypeVO
	 * .getBeginDate())) >= 0 && endDate.compareTo(ContactHelper
	 * .dateConverter(contactTypeVO.getEndDate())) <= 0) { overlapFlg = true;
	 * break; } //} } }
	 * 
	 * return overlapFlg; }
	 */

	/*
	 * private boolean isAddressOverlapping(ArrayList addressVOList, String
	 * city,String addressLine1,String addressLine2, int selectedIndex) {
	 * addressVOList = new ArrayList(); // AddressVO addressVO = new
	 * AddressVO(); boolean overlapFlg = false; for (int i = 0, size =
	 * addressVOList.size(); i < size; i++) { AddressVO addressVO = (AddressVO)
	 * addressVOList .get(i); if (addressVO != null && selectedIndex != i) { if
	 * (addressLine1.compareTo(addressVO) ) { overlapFlg = true; break; } } }
	 * return overlapFlg; }
	 */
	/**
	 * This method will validate the web address.
	 * 
	 * @param expression
	 *            Web Address.
	 * @return boolean : true if the expression matches the pattern
	 *         http://ww.wss.com/ format
	 */
	/*
	 * private static boolean validateURL(String expression) { Pattern p =
	 * Pattern.compile(ProgramConstants.URL_PATTERN); Matcher m =
	 * p.matcher(expression); return m.matches(); }
	 */
	/**
	 * This method will return valid date value.
	 * 
	 * @param dateString
	 *            :String object
	 * @return String
	 */
	public String getValidateDate(String dateString) {
		String dateVal = dateString;
		if (dateString.matches(ValidatorConstants.DATE_PATTERN1)) {
			dateVal = dateString.replace('-', '/');
		}
		if (dateString.matches(ValidatorConstants.DATE_PATTERN2)) {
			dateVal = dateString.substring(0, ProgramNumberConstants.INT_TWO)
					+ "/"
					+ dateString.substring(ProgramNumberConstants.INT_TWO,
							ProgramNumberConstants.INT_FOUR)
					+ "/"
					+ dateString.substring(ProgramNumberConstants.INT_FOUR,
							ProgramNumberConstants.INT_EIGHT);
		}
		return dateVal;
	}

	/**
	 * This method will validate the web address.
	 * 
	 * @param expression
	 *            Web Address.
	 * @return boolean : true if the expression matches the pattern
	 *         http://ww.wss.com/ format
	 */
	public static boolean validateAlphaNumeric(String expression) {
		Pattern p = Pattern.compile(ProgramConstants.ALPHANUMERIC_SPCLCHAR_PATTERN);
		Matcher m = p.matcher(expression);
		return m.matches();
	}

	/**
	 * This method will validate the web address.
	 * 
	 * @param expression
	 *            ALPHANUMERIC with space.
	 * @return boolean : true if the expression matches the pattern
	 */
	public static boolean validateIntnPhoneNumber(String expression) {
		Pattern p = Pattern.compile(ProgramConstants.INTN_PHONENUMBER_PATTERN);
		Matcher m = p.matcher(expression);
		return m.matches();
	}

	/**
	 * @return Returns the commonEntityDataBean.
	 */
	public CommonEntityDataBean getCommonEntityDataBean() {
		return commonEntityDataBean;
	}

	/**
	 * @param commonEntityDataBean
	 *            The commonEntityDataBean to set.
	 */
	public void setCommonEntityDataBean(
			CommonEntityDataBean commonEntityDataBean) {
		this.commonEntityDataBean = commonEntityDataBean;
	}

	/**
	 * This method used for setting error display messages.
	 * 
	 * @param errorName :
	 *            String errorName.
	 * @param arguments :
	 *            Array of Object. Parameters to be passed to the message
	 * @param messageBundle :
	 *            String messageBundle.
	 * @param componentId :
	 *            String componentId.
	 */

	public void setErrorMessage(String errorName, Object[] arguments,
			String messageBundle, String componentId) {
		logger.info("setErrorMessage");

		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot root = facesContext.getViewRoot();
		Locale locale = root.getLocale();
		String clientId = null;

		facesContext.getApplication().setMessageBundle(messageBundle);
		String errorMsg = format(messageBundle, errorName, arguments, locale);

		FacesMessage fc = new FacesMessage();
		fc.setDetail(errorMsg);

		if (componentId != null) {
			UIComponent uiComponent = findComponentInRoot(componentId);
			clientId = uiComponent.getClientId(facesContext);
		}

		facesContext.addMessage(clientId, fc);
	}

	/**
	 * This operation will return the corresponding formatted error message from
	 * the specified bundle.
	 * 
	 * @param bundleName :
	 *            Name of the bundle from which the error message to be
	 *            displayed.
	 * @param errorName :
	 *            String object of errorname to fetch corresponding message.
	 * @param arguments :
	 *            Object array with dynamic values to be inserted.
	 * @param locale :
	 *            Locale object to support internationalization.
	 * @return String: Formatted string value
	 */
	public static String format(String bundleName, String errorName,
			Object[] arguments, Locale locale) {
		ResourceBundle myResources = ResourceBundle.getBundle(bundleName,
				locale);
		return MessageFormat
				.format(myResources.getString(errorName), arguments);
	}

	/**
	 * This operation is used to find the component in root by passing id.
	 * 
	 * @param id :
	 *            String object.
	 * @return UIComponent : UIComponent object.
	 */

	public UIComponent findComponentInRoot(String id) {
		logger.info("findComponentInRoot");

		UIComponent component = null;
		FacesContext context = FacesContext.getCurrentInstance();

		if (context != null) {
			UIComponent root = context.getViewRoot();
			component = findComponent(root, id);
		}
		return component;
	}

	/**
	 * This operation is used to find the component by passing id.
	 * 
	 * @param base :
	 *            View root component of the jsp.
	 * @param id :
	 *            Id of the component from jsp.
	 * @return UIComponent object.
	 */

	public UIComponent findComponent(UIComponent base, String id) {

		logger.info("findComponent");
		// Is the "base" component itself the match we are looking for?

		if (id.equals(base.getId())) {
			return base;
		}

		// Search through our facets and children

		UIComponent component = null;
		UIComponent result = null;
		Iterator cmpIterator = base.getFacetsAndChildren();

		while (cmpIterator.hasNext() && (result == null)) {
			component = (UIComponent) cmpIterator.next();

			if (id.equals(component.getId())) {
				result = component;
				break;
			}
			result = findComponent(component, id);
			if (result != null) {
				break;
			}
		}
		return result;
	}
	
	
	
	
	
	

	/**
	 * @param message
	 *            holds the message.
	 * @param propertiesFile
	 *            holds propertiesFile.
	 * @param componentName
	 *            componentName.
	 */
	/*
	 * public void setErrorMessage(String message, String propertiesFile, String
	 * componentName) { FacesContext fc = FacesContext.getCurrentInstance();
	 * 
	 * UIViewRoot root = fc.getViewRoot(); Locale locale = root.getLocale();
	 * 
	 * String clientId = null; //Object[] arguments = new Object[]{};
	 * fc.getApplication().setMessageBundle(propertiesFile); ResourceBundle
	 * bundle = resourceBundle(fc);
	 * 
	 * String errorMsg = MessageUtil.format(propertiesFile, message, arguments,
	 * locale);
	 * 
	 * String errorMsg = bundle.getString(message); FacesMessage fm = new
	 * FacesMessage(); fm.setDetail(errorMsg); if (componentName != null) {
	 * 
	 * UIComponent uiComponent = findComponentInRoot(componentName); clientId =
	 * uiComponent.getClientId(fc); }
	 * //fm.setSeverity(FacesMessage.SEVERITY_ERROR); fc.addMessage(clientId,
	 * fm); }
	 *  
	 *//**
	    * This operation is used to find the component in root by passing id.
	    * 
	    * @param id :
	    *            String object.
	    * @return UIComponent : UIComponent object.
	    */
	/*
	 * public UIComponent findComponentInRoot(String id) {
	 * logger.info("findComponentInRoot"); TraceToken tt = null; tt =
	 * findComponentInRoot"); UIComponent component = null; FacesContext context =
	 * FacesContext.getCurrentInstance();
	 * 
	 * if (context != null) { UIComponent root = context.getViewRoot();
	 * component = findComponent(root, id); } 
	 * return component; }
	 *  
	 *//**
	    * This operation is used to find the component by passing id.
	    * 
	    * @param base :
	    *            View root component of the jsp.
	    * @param id :
	    *            Id of the component from jsp.
	    * @return UIComponent object.
	    */
	/*
	 * public UIComponent findComponent(UIComponent base, String id) {
	 * logger.info("findComponent"); 
	 * UIComponent result = null; // Is the "base" component itself the match we
	 * are looking for? if (id.equals(base.getId())) { result = base; }
	 * 
	 * else { // Search through our facets and children UIComponent component =
	 * null;
	 * 
	 * Iterator cmpIterator = base.getFacetsAndChildren();
	 * 
	 * while (cmpIterator.hasNext() && (result == null)) { component =
	 * (UIComponent) cmpIterator.next(); if (id.equals(component.getId())) {
	 * result = component; break; } result = findComponent(component, id); if
	 * (result != null) { break; } } }
	 * 
	 */
	public static CommonEntityDataBean getCEntityDataBean()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        CommonEntityDataBean commonEntityDataBean = (CommonEntityDataBean) fc
                .getApplication().createValueBinding(
                        "#{" + ProgramConstants.COMMONENTITYDATABEAN + "}")
                .getValue(fc);
        return commonEntityDataBean;
    }
	
	 /**
     * This method is to create an instance of Data Bean.
     *
     * @return Returns CorrespondenceDataBean
     */
    public CorrespondenceDataBean getCorrespondenceDataBean()
    {


        FacesContext fc = FacesContext.getCurrentInstance();
        CorrespondenceDataBean correspondenceDataBean = (CorrespondenceDataBean)fc.getApplication().getVariableResolver().resolveVariable(fc,ContactManagementConstants.CORRESPONDENCE_BEAN_NAME);
		if (correspondenceDataBean == null){
         correspondenceDataBean = (CorrespondenceDataBean) fc
                .getApplication().createValueBinding(
                        "#{" + ContactManagementConstants.CORRESPONDENCE_BEAN_NAME + "}")
                .getValue(fc);
			}
       
        return correspondenceDataBean;
    }
    
	
}