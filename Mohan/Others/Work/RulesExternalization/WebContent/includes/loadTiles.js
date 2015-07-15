//These can only be used once per page, so let your user know that only one
//will work. We aren't going to make a bunch of these just for the samples.
function loadAuditInfo()
	{
	htmlTileInclude('auditInfo', path +'tiles/auditInfo.html');
	return true
}
function loadAuditInfoHeader()
	{
	htmlTileInclude('auditInfoHeader', path +'tiles/auditInfoHeader.html');
	return true
}

//new for test October 2009
function loadAuditInfoX()
{
htmlTileInclude('auditInfoX', path +'tiles/auditInfoX.html');
return true
}
function loadAuditInfoHeaderX()
{
htmlTileInclude('auditInfoHeaderX', path +'tiles/auditInfoHeaderX.html');
return true
}
function loadInternalSAPage()
{
   htmlTileInclude('isarequestingprovider', '../../tiles/sa_requestingprovider.html');
   htmlTileInclude('isaserviceauthorizationinfo', '../../tiles/sa_serviceauthorizationinfo.html');
   htmlTileInclude('internalsatab2', '../../tiles/sa_tab2.html');
   htmlTileInclude('internalsatab3', '../../tiles/sa_tab3.html');
   htmlTileInclude('isanotes', '../../tiles/sa_notes.html');
   htmlTileInclude('isapaperworkattachment', '../../tiles/sa_paperworkattachment.html');
   htmlTileInclude('isaadditionalliinfo', '../../tiles/sa_additionalliinfo.html');
   
}

function loadInternalSAPage1()
{
   htmlTileInclude('isarequestingprovider', '../../tiles/sa_requestingprovider.html');
   htmlTileInclude('isaserviceauthorizationinfo', '../../tiles/sa_serviceauthorizationinfo.html');
   htmlTileInclude('internalsatab2', '../../tiles/sa_tab2.html');
  //htmlTileInclude('internalsatab3', '../../tiles/sa_tab3.html');
  // htmlTileInclude('isanotes', '../../tiles/sa_notes.html');
  // htmlTileInclude('isapaperworkattachment', '../../tiles/sa_paperworkattachment.html');
   //htmlTileInclude('isaadditionalliinfo', '../../tiles/sa_additionalliinfo.html');
   
}

function loadInternalSAPage5010()
{
   htmlTileInclude('isarequestingprovider5010', '../../tiles/sa_requestingprovider5010.html');
   htmlTileInclude('isaserviceauthorizationinfo5010', '../../tiles/sa_serviceauthorizationinfo5010.html');
   htmlTileInclude('internalsatab25010', '../../tiles/sa_tab25010.html');
  //htmlTileInclude('internalsatab3', '../../tiles/sa_tab3.html');
  // htmlTileInclude('isanotes', '../../tiles/sa_notes.html');
  // htmlTileInclude('isapaperworkattachment', '../../tiles/sa_paperworkattachment.html');
   //htmlTileInclude('isaadditionalliinfo', '../../tiles/sa_additionalliinfo.html');
   
}


/*
	initDentalInquiry() - loads 3 hipaa 837 file, then loads tiles for the loaded files
*/

/////   INTERNAL DENTAL CLAIM INQUIRY & HIPAA TABS //////////////////////////////////////
function initDentalInquiry(){
	var page=''
	htmlTileInclude('otherPayerTile', '../../tiles/claim_otherPayer_view.html');
	if(loadDentalHipaa()) // loads 3 pages
	{
		loadDentalHipaaTabOne(page)  		// loads tiles for hipaa tab1
		loadDentalHipaaTabTwo(page)		// loads tiles for hipaa tab2
		loadDentalHipaaTabThree(page)		// loads tiles for hipaa tab3
	}
	return true;
}

function initDentalCorr(){
	var page='corr';
	
	if(loadDentalHipaa()) // loads 3 pages
	{
		loadDentalHipaaTabOne(page)  		// loads tiles for hipaa tab1
		loadDentalHipaaTabTwo(page)		// loads tiles for hipaa tab2
		loadDentalHipaaTabThree(page)		// loads tiles for hipaa tab3
	}
	
	return true;
}

function loadDentalHipaa()
{
	htmlTileInclude('hipaa1', '../../tiles/hipaa837DentalTab1.html');
	htmlTileInclude('hipaa2', '../../tiles/hipaa837DentalTab2.html');
	htmlTileInclude('hipaa3', '../../tiles/hipaa837DentalTab3.html')
	
	return true;	
}

function loadDentalHipaaTabOne(page)
{
	htmlTileInclude('billingProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('payToProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('renderProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('memberIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherInsMemberIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherInsPayerIdTable', '../../tiles/staticSecondaryIDTable.html');
}

function loadDentalHipaaTabTwo(page)
{
	htmlTileInclude('moreReferringProviderIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('serviceFacilityIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherPayerPatientIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherPayerReferringIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherPayerRenderIdTable', '../../tiles/staticSecondaryIDTable.html');
}

function loadDentalHipaaTabThree(page)
{
	htmlTileInclude('t3_renderIdTable', '../../tiles/staticSecondaryIDTable.html');
}

/////   INTERNAL PROFESSIONAL CLAIM INQUIRY & HIPAA TABS //////////////////////////////////////
function initProfInquiry(){
	var page = '';
	htmlTileInclude('otherPayerTile', '../../tiles/claim_otherPayer_view.html');
	if(loadProfHipaa()) // loads 3 pages
	{
		loadProfHipaaTabOne(page)  		// loads tiles for hipaa tab1
		loadProfHipaaTabTwo(page)			// loads tiles for hipaa tab2
		loadProfHipaaTabThree(page)		// loads tiles for hipaa tab3
	}
	return true;
}

function initProfCorr(){
	var page = 'corr';
	if(loadProfHipaa()) // loads 3 pages
	{
		loadProfHipaaTabOne(page)  		// loads tiles for hipaa tab1
		loadProfHipaaTabTwo(page)			// loads tiles for hipaa tab2
		loadProfHipaaTabThree(page)		// loads tiles for hipaa tab3
	}
	return true;
}

function loadProfHipaa()
{
	htmlTileInclude('hipaa1', '../../tiles/hipaa837ProfTab1.html')
	htmlTileInclude('hipaa2', '../../tiles/hipaa837ProfTab2.html')
	htmlTileInclude('hipaa3', '../../tiles/hipaa837ProfTab3.html')
	return true;	
}

function loadProfHipaaTabOne(page)
{
	htmlTileInclude('billingProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('billingProvContactTable', '../../tiles/claims_staticContactTable.html');
	htmlTileInclude('payToProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('renderProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	//htmlTileInclude('renderProvIdTable1', '../../tiles/staticSecondaryIDTableRen.html');
	htmlTileInclude('referringProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	//htmlTileInclude('referringProvIdTable1', '../../tiles/staticSecondaryIDTableRef.html');
	htmlTileInclude('memberIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherInsMemberIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherInsPayerIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherInsPayerContactTable', '../../tiles/claims_staticContactTable.html');
	
}

function loadProfHipaaTabTwo(page)
{

	htmlTileInclude('serviceFacilityIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('purchProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('superProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherPayerPatientIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherPayerReferProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherPayerRenderProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherPayerPurchProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherPayerSuperProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherPayerServiceProvIdTable', '../../tiles/staticSecondaryIDTable.html');
}

function loadProfHipaaTabThree(page)
{
	htmlTileInclude('t3_renderProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('t3_referProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('t3_purchServProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('t3_orderProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('t3_orderProvContactTable', '../../tiles/claims_staticContactTable.html');
	htmlTileInclude('t3_superProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('t3_servFacilityIdTable', '../../tiles/staticSecondaryIDTable.html');
	if(page=='corr')
	{
		htmlTileInclude('t3_drugIdTable', '../../tiles/claims_drugIdTable.html');
	}
	else
	{
		htmlTileInclude('t3_drugIdTable', '../../tiles/claims_staticDrugIdTable.html');
	}
}


/////   INTERNAL INSTITUTIONAL CLAIM INQUIRY & HIPAA TABS //////////////////////////////////////
function initInstInquiry(){
	var page=''
	htmlTileInclude('otherPayerTile', '../../tiles/claim_otherPayer_view.html');
	if(loadInstHipaa()) // loads 3 pages
	{
		loadInstHipaaTabOne(page)  		// loads tiles for hipaa tab1
		loadInstHipaaTabTwo(page)		// loads tiles for hipaa tab2
		loadInstHipaaTabThree(page)		// loads tiles for hipaa tab3
	}
	return true;
}

function initInstInquiry5010(){
	var page=''
	htmlTileInclude('otherPayerTile', '../../tiles/claim_otherPayer_view.html');
	if(loadInstHipaa5010()) // loads 3 pages
	{
		loadInstHipaaTabOne5010(page)  		// loads tiles for hipaa tab1
		loadInstHipaaTabTwo5010(page)		// loads tiles for hipaa tab2
		loadInstHipaaTabThree5010(page)		// loads tiles for hipaa tab3
	}
	return true;
}
function initInstCorr(){
	var page='corr'
	if(loadInstHipaa()) // loads 3 pages
	{
		loadInstHipaaTabOne()  		// loads tiles for hipaa tab1
		loadInstHipaaTabTwo()		// loads tiles for hipaa tab2
		loadInstHipaaTabThree(page)		// loads tiles for hipaa tab3
	}
	return true;
}

function initInstCorr5010(){
	var page='corr'
	if(loadInstHipaa5010()) // loads 3 pages
	{
		loadInstHipaaTabOne5010()  		// loads tiles for hipaa tab1
		loadInstHipaaTabTwo5010()		// loads tiles for hipaa tab2
		loadInstHipaaTabThree5010(page)		// loads tiles for hipaa tab3
	}
	return true;
}

function loadInstHipaa()
{
	htmlTileInclude('hipaa1', '../../tiles/hipaa837InstTab1.html')
	htmlTileInclude('hipaa2', '../../tiles/hipaa837InstTab2.html')
	htmlTileInclude('hipaa3', '../../tiles/hipaa837InstTab3.html')
	return true;	
}

function loadInstHipaa5010()
{
	htmlTileInclude('hipaa1', '../../tiles/hipaa837InstTab1-5010.html')
	htmlTileInclude('hipaa2', '../../tiles/hipaa837InstTab2-5010.html')
	htmlTileInclude('hipaa3', '../../tiles/hipaa837InstTab3-5010.html')
	return true;	
}

function loadInstHipaaTabOne(page)
{
	htmlTileInclude('billingProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('billingProvContactTable', '../../tiles/claims_staticContactTable.html');
	htmlTileInclude('payToProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('memberIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherSubIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherInsPayerIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherAttendIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherPhysIdTable', '../../tiles/staticSecondaryIDTable.html');

}

function loadInstHipaaTabOne5010(page)
{
	htmlTileInclude('billingProvIdTable', '../../tiles/staticSecondaryIDTable5010.html');
	htmlTileInclude('billingProvContactTable', '../../tiles/claims_staticContactTable.html');
	htmlTileInclude('payToProvIdTable', '../../tiles/staticSecondaryIDTable5010.html');
	htmlTileInclude('otherSubIdTable', '../../tiles/staticSecondaryIDTable5010.html');
	htmlTileInclude('otherAttendIdTable', '../../tiles/staticSecondaryIDTable5010.html');
	htmlTileInclude('otherPhysIdTable', '../../tiles/staticSecondaryIDTable5010.html');

}

function loadInstHipaaTabTwo(page)
{

	htmlTileInclude('serviceFacilityIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('operatingPhysIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherPayerPatientIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherPayerOperIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherPayerOtherProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherPayerAttendProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	htmlTileInclude('otherPayerServFacProvIdTable', '../../tiles/staticSecondaryIDTable.html');
}

function loadInstHipaaTabTwo5010(page)
{

	htmlTileInclude('serviceFacilityIdTable', '../../tiles/staticSecondaryIDTable5010.html');
	htmlTileInclude('operatingPhysIdTable', '../../tiles/staticSecondaryIDTable5010.html');
	htmlTileInclude('otherPayerPatientIdTable', '../../tiles/staticSecondaryIDTable5010.html');
	htmlTileInclude('otherPayerOtherProvIdTable', '../../tiles/staticSecondaryIDTable5010.html');
}
function loadInstHipaaTabThree(page)
{
	if(page=='corr')
	{
		htmlTileInclude('t3_drugIdTable', '../../tiles/claims_drugIdTable.html');
	}
	else
	{
		htmlTileInclude('t3_drugIdTable', '../../tiles/claims_staticDrugIdTable.html');
	}
	htmlTileInclude('t3_otherProvIdTable', '../../tiles/staticSecondaryIDTable.html');
	
}

function loadInstHipaaTabThree5010(page)
{
	if(page=='corr')
	{
		htmlTileInclude('t3_drugIdTable', '../../tiles/claims_drugIdTable.html');
	}
	else
	{
		htmlTileInclude('t3_drugIdTable', '../../tiles/claims_staticDrugIdTable.html');
	}
	htmlTileInclude('t3_otherProvIdTable', '../../tiles/staticSecondaryIDTable5010.html');
	
}

/*  CONTACT MANAGEMENT Rob */
function loadLogCorrespodence()
{
	htmlTileInclude('maintainEntityFields', path + 'tiles/cm_maintainEntity.html');
	//htmlTileInclude('common_contact_maintain', path + 'tiles/common_contact.html');
	//htmlTileInclude('common_e-address', path +'tiles/common_e-address.html');
	//htmlTileInclude('common_phone', path +'tiles/common_phone.html');
	//htmlTileInclude('common_contact', path + 'tiles/common_contact.html');
	return true
}

//var root = 'file://C:/magicFiles/ui-prototype/WebContent/'
function loadCase()
{
	htmlTileInclude('maintainEntityFields',  root + 'tiles/cm_maintainEntity.html');
	//htmlTileInclude('common_contact_maintain', path + 'tiles/common_contact.html');
	//htmlTileInclude('common_e-address', path +'tiles/common_e-address.html');
	//htmlTileInclude('common_phone', path +'tiles/common_phone.html');
	//htmlTileInclude('common_contact', path + 'tiles/common_contact.html');
	return true
}

function loadAddEntity()
{
	//htmlTileInclude('maintainEntityFields', path + 'tiles/cm_maintainEntity.html');
}

function loadEntitySearch()
{
	htmlTileInclude('maintainEntityFields', path + 'tiles/cm_maintainEntity.html');
}


/* Rob */	
function loadContact()
{
	htmlTileInclude('common_contact', path + 'tiles/common_contact.html');
	return true
}

function loadEaddress()
	{
	htmlTileInclude('common_e-address', path +'tiles/common_e-address.html');
	return true
}

function loadCommon_ContactAddress()
	{
	htmlTileInclude('common_contactaddress', path +'tiles/common_address.html');
	return true
}

function loadCommon_ContactAddress21()
	{
	htmlTileInclude('common_contactaddress21', path +'tiles/common_addressContact.html');
	return true
}

function loadCommon_ContactAddressMember()
    {
    htmlTileInclude('common_contactaddressMember', path +'tiles/common_addressMember.html');
    htmlTileInclude('common_contactaddressMember2', path +'tiles/common_addressMember2.html');
    return true
}

function loadCommon_ContactAddressMember3lines()
    {
    htmlTileInclude('common_contactaddressMember3lines', path +'tiles/common_addressMember3lines.html');
    htmlTileInclude('common_contactaddressMember3lines2', path +'tiles/common_addressMember3lines2.html');
    return true
}

function loadCommon_ContactAddressProvider()
    {
    htmlTileInclude('common_contactaddressProvider', path +'tiles/common_addressProvider.html');
    htmlTileInclude('common_contactaddressProvider2', path +'tiles/common_addressProvider.html');
    return true
}

function loadPhone()
	{
	htmlTileInclude('common_phone', path +'tiles/common_phone.html');
	return true
}

function loadCaseSteps()
	{
	htmlTileInclude('caseSteps', path +'tiles/caseSteps.html');
	return true
}

function loadCaseEvents()
	{
	htmlTileInclude('caseEvents', path +'tiles/caseEvents.html');
	return true
}

function loadLtrResp()
	{
	htmlTileInclude('LtrResp', path +'tiles/letter_response.html');
	return true
}

function loadLLtrotherbg()
	{
	htmlTileInclude('LtrRespotherbg', path +'tiles/letter_responseotherbg.html');
	return true
}

function loadAddrExtra()
	{
	htmlTileInclude('addrExtra', path +'tiles/common_addressExtra.html');
	return true
}
function loadAddrExtra2()
	{
	htmlTileInclude('addrExtra2', path +'tiles/common_addressExtra.html');
	return true
}
function loadAddrExtra3()
	{
	htmlTileInclude('addrExtra3', path +'tiles/common_addressExtra.html');
	return true
}
function loadAddrExtra4()
	{
	htmlTileInclude('addrExtra4', path +'tiles/common_addressExtra.html');
	return true
}

// Common Address - this is not being used - we are using common_contactaddress above
function loadCommonAddress()
{
	htmlTileInclude('currentAddresses', '../tiles/ca_currentAddresses.html');		
	htmlTileInclude('editAddressDetails', '../tiles/ca_editableAddressDetails.html');	
	htmlTileInclude('newAddress', '../tiles/ca_newAddress.html');	
	htmlTileInclude('searchAddress', '../tiles/ca_searchAddress.html');	
	htmlTileInclude('addressHistory', '../tiles/ca_addressHistory.html');	
	htmlTileInclude('historyView', '../tiles/ca_addressDetails.html');	
	htmlTileInclude('searchView', '../tiles/ca_addressDetails.html');	
}





// tested on file templates\test_rob.html
function loadTestRob()
{
	htmlTileInclude('maintainEntityFields', path + 'tiles/cm_maintainEntity.html');
	/*var oAjax = new Array()
		oAjax[0] = 'common_contact';
	if(htmlTileInclude('common_contact', path + 'tiles/common_contact.html'))
	{
		relPath(oAjax)
		tigra_tables('contactCommon_table', 1, 0, 'row_alt', 'row', 'row_mouse', 'row_selected');
	}
	*/
	
}