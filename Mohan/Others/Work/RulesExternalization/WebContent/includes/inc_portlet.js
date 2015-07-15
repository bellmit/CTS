// JavaScript Document

// pods
function writePodBegin(podTitle,podBG) {
	if (typeof podBG == 'undefined') {
		podBG = "otherbg"
	}
	
	if (podTitle == 'no') {
		begin = '<div class="pod ' + podBG + '">' +
					'<div class="pod_content">';
	}
	
	else {
		begin = '<div class="pod ' + podBG + '">' +
					'<div class="pod_content">' +
						'<div class="podTitle">' + podTitle + '</div>' +
						'<div class="podLine"><img src="'+ path +'images/x.gif" width="1" height="1" /></div>' +
						'<div><img src="'+ path +'images/x.gif" border="0" width="1" height="10" alt="" /></div>';
	}
	document.write(begin);
}

function writePodEnd() {
	end =		'<div><img src="'+ path +'images/x.gif" border="0" width="1" height="5" alt="" /></div>' +
			'</div>' +
	'</div>';
	document.write(end);
}
//end pods

// Script to write out the portlet html

/*
pWhere - is where you are in the directory structure "../../" this way the images show up correctly

pTitle - is the title of your portlet

pPrint - If you want the "Print" icon to show up enter "1", if you DO NOT want a "Print" icon enter "0"

pHelp - If you want the "Help" icon to show up enter "1", if you DO NOT want a "Help" icon enter "0"

pActionBar - If you want the "ActionBar" to show up enter "1", if you DO NOT want the "ActionBar" enter "0"

pActionLinks - pass in the html of the links to use.

pClass - pass in the style class if you wish to use one

*/

function openPrintWindow()
{
   var ppwurl = window.location.href;
   // Script to remove special characters from URL so IE will accept as 2nd parameter in open method. This is only being done to
   // test the viablility of passing the URL of the calling page, if needed, somewhere other than in the first parameter. Some tweaking
   // of the replacement characters may be necessary. 
   var ppwurl = ppwurl.replace(/\:/g,'_c_');
   var ppwurl = ppwurl.replace(/[\/]/g,'_s_');
   var ppwurl = ppwurl.replace(/\-/g,'_d_');
   var ppwurl = ppwurl.replace(/\./g,'_p_');
   // var ppw = window.open('',window.location.href,'toolbar=no,menubar=no,location=no,scrollbars=yes,status=no,directories=no');
   var ppw = window.open('',ppwurl,'toolbar=no,menubar=no,location=no,scrollbars=yes,status=no,directories=no');
   // Script to restore special characters to URL if needed
   // var ppwurl = ppwurl.replace(/_c_/g,':');
   // var ppwurl = ppwurl.replace(/_s_/g,'/');
   // var ppwurl = ppwurl.replace(/_d_/g,'-');
   // var ppwurl = ppwurl.replace(/_p_/g,'.');
   var doc = ppw.document;
   ppw.document.writeln('<HTML><HEAD><title>Print Preview Window</title>');
   ppw.document.write('<link rel="stylesheet" href="'+ path +'style/printPreview.css" title="printPreviewStyleSheet">');
   ppw.document.writeln('<SCRIPT type="text/javascript" src="'+ path +'includes/htmlTileInclude.js"><\/SCRIPT>');
   ppw.document.writeln('<SCRIPT type="text/javascript" src="'+ path +'includes/printPreview.js"><\/SCRIPT>');
   ppw.document.writeln('</HEAD>');
   ppw.document.writeln('<BODY>');
   ppw.document.writeln('<DIV id="ppwPage">');
   ppw.document.writeln('<DIV id="ppwContent">');
   ppw.document.writeln('</DIV>');
   ppw.document.writeln('<DIV id="ppwButtons">');
   ppw.document.writeln('<FORM method="post">');
   ppw.document.writeln('<INPUT type="button" value="Print" id="ppwPrintButton" class="ppwButton" onClick="window.print();" >&nbsp;&nbsp;&nbsp;');
   ppw.document.writeln('<INPUT type="button" value="Close" id="ppwCancelButton" class="ppwButton" onClick="window.close();" >');
   ppw.document.writeln('</FORM>');
   ppw.document.writeln('</DIV>');
   ppw.document.writeln('</DIV>');
   // ppw.document.writeln('<SCRIPT>htmlTileInclude("ppwContent",window.name);disableFormElements();cssChanger("printPreviewStyleSheet");<\/SCRIPT>');
   ppw.document.writeln('<SCRIPT>htmlTileInclude("ppwContent",window.location.href);disableFormElements();cssChanger("printPreviewStyleSheet");<\/SCRIPT>');
   ppw.document.writeln('</BODY>');
   ppw.document.writeln('</HTML>');
   ppw.document.close();
}

function writePortletBegin(pWhere,pTitle,pPrint,pHelp,pActionBar,pActionLinks,pClass){
	if (typeof path == 'undefined') {
		path = pWhere
	}
	if (typeof helpLink == 'undefined'){
		helpLink = "/help.html";	
	}
	if (pPrint == 2){
      pPrint = '<a href="javascript:portalPrint();" title="Print">Print</a>&nbsp;'
	}
	if (pPrint == 1){
		pPrint = '<a href="javascript:portalPrint();" title="Print">Print</a>&nbsp;'
	}
	else if (pPrint == 0){
		pPrint = ''
	}
	//if (pHelp == 1){
		//pHelp = '<a href="javascript:popContextHelp(\''+ pWhere +'\')" title="Help">Help</a>&nbsp;'
	//}
	if (pHelp == 1){
		pHelp = '<a href="javascript:popOpenHelp(\''+ helpLink +'\')" title="Help">Help</a>&nbsp;'
	}
	else if (pHelp == 0){
		pHelp = ''
	}
  pConcat = pPrint != '' && pHelp != '' ? '&#124;&nbsp;' : '';
  if (pActionBar == 1){
		pActionBar = '<div class="portletActionBar">' +
						'<div class="portletRequired">* Required Field</div>' +
						'<div class="portletActions">' + pActionLinks + '</div>' +
						'<div class="clear"></div>' +
					'</div>';
	}
	else if (pActionBar == 0){
		pActionBar = ''
	}
	begin = '<div class="portlet">' +
				'<div class="portletTitleBar">' +
					'<div class="portletTitle">' + pTitle + '</div>' +
					'<div class="portletIcons">' + pPrint + pConcat + pHelp + '</div>' + //<a href="javascript:void(0)" title="Minimize"><img src="'+ path +'images/title_minimize.gif" alt="Minimize" align="absmiddle" /></a><a href="javascript:void(0)" title="Maximize"><img src="'+ path +'images/title_maximize.gif" alt="Maximize" align="absmiddle" /></a>
 					'<div class="clear"></div>' +
				'</div>' +
				'<div class="portletContent">' +
					pActionBar +
					'<div class="portletArea ' + pClass + '">';
			
	document.write(begin);
	
	
}


function writePortletBeginNoMin(pWhere,pTitle,pPrint,pHelp,pActionBar,pActionLinks,pClass){
	if (typeof path == 'undefined') {
		path = pWhere
	}
	if (typeof helpLink == 'undefined'){
		helpLink = "/help.html";	
	}
	if (pPrint == 2){
      pPrint = '<a href="javascript:portalPrint();" title="Print">Print</a>&nbsp;'
	}
	if (pPrint == 1){
		pPrint = '<a href="javascript:portalPrint();" title="Print">Print</a>&nbsp;'
	}
	else if (pPrint == 0){
		pPrint = ''
	}
	//if (pHelp == 1){
		//pHelp = '<a href="javascript:popContextHelp(\''+ pWhere +'\')" title="Help">Help</a>&nbsp;'
	//}
	if (pHelp == 1){
		pHelp = '<a href="javascript:popOpenHelp(\''+ helpLink +'\')" title="Help">Help</a>&nbsp;'
	}
	else if (pHelp == 0){
		pHelp = ''
	}
  pConcat = pPrint != '' && pHelp != '' ? '&#124;&nbsp;' : '';
  if (pActionBar == 1){
		pActionBar = '<div class="portletActionBar">' +
						'<div class="portletRequired">* Required Field</div>' +
						'<div class="portletActions">' + pActionLinks + '</div>' +
						'<div class="clear"></div>' +
					'</div>';
	}
	else if (pActionBar == 0){
		pActionBar = ''
	}
	begin = '<div class="portlet">' +
				'<div class="portletTitleBar">' +
					'<div class="portletTitle">' + pTitle + '</div>' +
					'<div class="portletIcons">' + pPrint + pConcat + pHelp + '</div>' +
					'<div class="clear"></div>' +
				'</div>' +
				'<div class="portletContent">' +
					pActionBar +
					'<div class="portletArea ' + pClass + '">';
			
	document.write(begin);
	
	
}






function writePortletEnd(){
	end = '</div></div></div>';
	document.write(end);
}
//end portlet




function writeQuickLinks(path) {
	qLinks =	'<div class="linkMenu">' +
					'<ul class="podList">' +
						'<li><a href="' + path + 'http://www.dhhs.nh.gov/foryou/index.htm">Information Topics A-Z</a></li>' +
						'<li><a href="' + path + 'directories/findprovider.html">Find a Health Care Provider</a></li>' +
						'<li><a href="http://www.dhhs.nh.gov/dhhs/medicaidprogram/targeted-services.htm" target="_blank">Benefits Overview</a></li>' +
						'<li><a href="' + path + 'providers/enrollment/start.html">Provider Enrollment</a></li>' +
						'<li><a href="' + path + 'documentation/documentsandforms.html">Documents and Forms</a></li>' +
						'<li><a href="http://www.dhhs.state.nh.us/dhhs/medicaidprogram/contact+info/default.htm" target="_blank">Report Fraud &amp; Abuse</a></li>' +
					'</ul>' +
				'</div>';
	document.write(qLinks);
}
function writeQuickLinksFAQ1(path) {
	qLinks =	'<div class="linkMenu">' +
					'<ul class="podList">' +
						'<li><a href="http://www.dhhs.nh.gov/foryou/index.htm" target="_blank">Information Topics A-Z</a></li>' +
						'<li><a href="' + path + 'directories/findprovider.html">Find a Health Care Provider</a></li>' +
						'<li><a href="http://www.dhhs.nh.gov/dhhs/medicaidprogram/targeted-services.htm" target="_blank">Benefits Overview</a></li>' +
						'<li><a href="' + path + 'providers/enrollment/start.html">Provider Enrollment</a></li>' +
						'<li><a href="' + path + 'documentation/documentsandforms.html">Documents and Forms</a></li>' +
						'<li><a href="http://www.dhhs.state.nh.us/dhhs/medicaidprogram/contact+info/default.htm" target="_blank">Report Fraud &amp; Abuse</a></li>' +
					'</ul>' +
				'</div>';
	document.write(qLinks);
}
function writeQuickLinks2(path) {
	qLinks =	'<div class="linkMenu">' +
					'<ul class="podList">' +
						'<li><a href="' + path + 'http://www.dhhs.nh.gov/foryou/index.htm">Information Topics A-Z</a></li>' +
						'<li><a href="' + path + 'directories/findprovider.html">Find a Health Care Provider</a></li>' +
						'<li><a href="http://www.dhhs.nh.gov/dhhs/medicaidprogram/targeted-services.htm" target="_blank">Benefits Overview</a></li>' +
						//'<li><a href="' + path + 'providers/enrollment/start.html">Provider Enrollment</a></li>' +
						'<li><a href="' + path + 'documentation/documentsandforms.html">Documents and Forms</a></li>' +
						'<li><a href="http://www.dhhs.state.nh.us/dhhs/medicaidprogram/contact+info/default.htm" target="_blank">Report Fraud &amp; Abuse</a></li>' +
					'</ul>' +
				'</div>';
	document.write(qLinks);
}
function writeQuickLinks21(path) {
	qLinks =	'<div class="linkMenu">' +
					'<ul class="podList">' +
						'<li><a href="http://www.dhhs.nh.gov/foryou/index.htm" target="_blank">Information Topics A-Z</a></li>' +
						'<li><a href="' + path + 'directories/findprovider.html">Find a Health Care Provider</a></li>' +
						'<li><a href="http://www.dhhs.nh.gov/dhhs/medicaidprogram/targeted-services.htm" target="_blank">Benefits Overview</a></li>' +
						//'<li><a href="' + path + 'providers/enrollment/start.html">Provider Enrollment</a></li>' +
						'<li><a href="' + path + 'documentation/documentsandforms.html">Documents and Forms</a></li>' +
						'<li><a href="http://www.dhhs.state.nh.us/dhhs/medicaidprogram/contact+info/default.htm" target="_blank">Report Fraud &amp; Abuse</a></li>' +
					'</ul>' +
				'</div>';
	document.write(qLinks);
}
function writeQuickLinks1(path) {
	qLinks =	'<div class="linkMenu">' +
					'<ul class="podList">' +
						'<li><a href="http://www.dhhs.nh.gov/foryou/index.htm" target="_blank">Information Topics A-Z</a></li>' +
						'<li><a href="' + path + 'directories/findprovider.html" target="_blank">Find a Health Care Provider</a></li>' +
						'<li><a href="http://www.dhhs.nh.gov/dhhs/medicaidprogram/targeted-services.htm" target="_blank">Benefits Overview</a></li>' +
						'<li><a href="' + path + 'providers/enrollment/start.html">Provider Enrollment</a></li>' +
						'<li><a href="' + path + 'documentation/documentsandforms.html">Documents and Forms</a></li>' +
						'<li><a href="http://www.dhhs.state.nh.us/dhhs/medicaidprogram/contact+info/default.htm" target="_blank">Report Fraud &amp; Abuse</a></li>' +
						'<li><a href="' + path + 'providers/billingmanuals.html">Billing Manuals</a></li>' +
					'</ul>' +
				'</div>';
	document.write(qLinks);
}
function writeQuickLinksInternal(path) {
	qLinks =	'<div class="linkMenu">' +
					'<ul class="podList">' +
						'<li><a href="http://www.dhhs.nh.gov/foryou/index.htm" target="_blank">Information Topics A-Z</a></li>' +
						'<li><a href="directories/findprovider.html">Find a Health Care Provider</a></li>' +
						'<li><a href="http://www.dhhs.nh.gov/dhhs/medicaidprogram/targeted-services.htm" target="_blank">Benefits Overview</a></li>' +
						'<li><a href="providers/enrollment/start.html">Provider Enrollment</a></li>' +
						'<li><a href="documentation/documentsandforms.html">Documents and Forms</a></li>' +
						'<li><a href="http://www.dhhs.state.nh.us/dhhs/medicaidprogram/contact+info/default.htm" target="_blank">Report Fraud &amp; Abuse</a></li>' + 
						'<li><a href="http://www.dhhs.state.nh.us" target="_blank">Department of Health and Human Services</a></li>' + 
					'</ul>' +
				'</div>';
	document.write(qLinks);
}
function writeQuickLinksPublic(path) {
	qLinks =	'<div class="linkMenu">' +
					'<ul class="podList">' +
						'<li><a href="http://www.dhhs.nh.gov/foryou/index.htm" target="_blank">Information Topics A-Z</a></li>' +
						'<li><a href="directories/findproviderpublic.html">Find a Health Care Provider</a></li>' +
						'<li><a href="http://www.dhhs.nh.gov/dhhs/medicaidprogram/targeted-services.htm" target="_blank">Benefits Overview</a></li>' +
						'<li><a href="providers/enrollment/start.html">Provider Enrollment</a></li>' +
						'<li><a href="documentation/documentsandforms.html">Documents and Forms</a></li>' +
						'<li><a href="http://www.dhhs.state.nh.us/dhhs/medicaidprogram/contact+info/default.htm" target="_blank">Report Fraud &amp; Abuse</a></li>' + 
						'<li><a href="http://www.dhhs.state.nh.us" target="_blank">Department of Health and Human Services</a></li>' + 
					'</ul>' +
				'</div>';
	document.write(qLinks);
}
function writeQuickLinksPublic1(path) {
	qLinks =	'<div class="linkMenu">' +
					'<ul class="podList">' +
						'<li><a href="http://www.dhhs.nh.gov/foryou/index.htm" target="_blank">Information Topics A-Z</a></li>' +
						'<li><a href="directories/findproviderpublic.html">Find a Health Care Provider</a></li>' +
						'<li><a href="http://www.dhhs.nh.gov/dhhs/medicaidprogram/targeted-services.htm" target="_blank">Benefits Overview</a></li>' +
						'<li><a href="providers/enrollment/start.html">Provider Enrollment</a></li>' +
						'<li><a href="documentation/documentsandforms.html">Documents and Forms</a></li>' +
						'<li><a href="http://www.dhhs.state.nh.us/dhhs/medicaidprogram/contact+info/default.htm" target="_blank">Report Fraud &amp; Abuse</a></li>' + 
						'<li><a href="http://www.dhhs.state.nh.us" target="_blank">Department of Health and Human Services</a></li>' + 
					'</ul>' +
				'</div>';
	document.write(qLinks);
}
function writeQuickLinksPublic2(path) {
	qLinks =	'<div class="linkMenu">' +
					'<ul class="podList">' +
						'<li><a href="directories/findproviderpublic.html">Find a Health Care Provider</a></li>' +
						'<li><a href="providers/enrollment/start.html">Provider Enrollment</a></li>' +
						'<li><a href="documentation/documentsandforms.html">Documents and Forms</a></li>' +
						'<li><a href="http://www.dhhs.state.nh.us" target="_blank">Department of Health and Human Services</a></li>' + 
					'</ul>' +
				'</div>';
	document.write(qLinks);
}
function writeQuickLinksPublic3(path) {
	qLinks =	'<div class="linkMenu">' +
					'<ul class="podList">' +
						'<li><a href="providers/enrollment/start.html">Provider Enrollment</a></li>' +
						'<li><a href="http://www.dhhs.state.nh.us" target="_blank">Department of Health and Human Services</a></li>' + 
					'</ul>' +
				'</div>';
	document.write(qLinks);
}
function writeQuickLinksSecureProvider(path) {
	qLinks =	'<div class="linkMenu">' +
					'<ul class="podList">' +
						'<li><a href="' + path + 'directories/findprovider.html">Find a Health Care Provider</a></li>' +
						'<li><a href="http://www.dhhs.nh.gov/dhhs/medicaidprogram/targeted-services.htm" target="_blank">Member Benefits</a></li>' +
						'<li><a href="' + path + 'providers/enrollment/start.html">Provider Enrollment</a></li>' +
						'<li><a href="javascript:void(0);">Documents and Forms</a></li>' +
						//'<li><a href="' + path + 'help/fastep1.html">Report Fraud &amp; Abuse</a></li>' +
						'<li><a href="' + path + 'providers/providerInquiry.html">Provider Inquiry</a></li>' +
						'<li><a href="' + path + 'providers/providerResources.html">Provider Resources</a></li>' +
					'</ul>' +
				'</div>';
	document.write(qLinks);
}

function writeQuickLinksSecureProvider1(path) {
	qLinks =	'<div class="linkMenu">' +
					'<ul class="podList">' +
						'<li><a href="' + path + '/providers/enrollment/addLocationIndv/demographic.html">Add Service Location</a></li>' +
						'<li><a href="' + path + 'documentation/bannermessages.html" target="_blank">Messages & Announcements</a></li>' +
						'<li><a href="' + path + 'help/faq.html" target="_blank">Provider FAQ</a></li>' +
						'<li><a href="' + path + 'providers/providerInquiry.html" target="_blank">Provider Inquiry</a></li>' +
						'<li><a href="' + path + 'providers/providermanuals.html" target="_blank">Provider Manuals</a></li>' +
						//'<li><a href="' + path + 'providers/benefitsoverview.html" target="_blank">Benefits Overview</a></li>' +
						'<li><a href="' + path + 'providers/trainingRegistration.html" target="_blank">Provider Training Registration</a></li>' +
						//'<li><a href="' + path + 'fastep1.html" target="_blank">Report Fraud &amp; Abuse</a></li>' +
						'<li><a href="' + path + 'providers/providerResources.html" target="_blank">Provider Resources</a></li>' +
						
						'<li><a href="' + path + 'providers/feeschedule.html" target="_blank">Fee Schedules</a></li>' +
						'<li><a href="' + path + 'providers/enrollment/tradingpartner/instructions.html" target="_blank">Trading Partner Enrollment</a></li>' +
					'</ul>' +
				'</div>';
	document.write(qLinks);
}

function writeQuickLinksSecureProviderCR(path) {
	qLinks =	'<div class="linkMenu">' +
					'<ul class="podList">' +
						'<li><a href="' + path + '/providers/enrollment/addLocationIndv/demographic.html">Add Service Location</a></li>' +
						'<li><a href="' + path + 'documentation/messagesCR.html" target="_blank">Messages & Announcements</a></li>' +
						'<li><a href="' + path + 'help/faq.html" target="_blank">Provider FAQ</a></li>' +
						'<li><a href="' + path + 'providers/providerInquiry.html" target="_blank">Provider Inquiry</a></li>' +
						'<li><a href="' + path + 'providers/providermanualsCR.html" target="_blank">Provider Manuals</a></li>' +
						//'<li><a href="' + path + 'providers/benefitsoverview.html" target="_blank">Benefits Overview</a></li>' +
						'<li><a href="' + path + 'providers/trainingRegistration.html" target="_blank">Provider Training Registration</a></li>' +
						//'<li><a href="' + path + 'fastep1.html" target="_blank">Report Fraud &amp; Abuse</a></li>' +
						'<li><a href="' + path + 'providers/providerResourcesCR.html" target="_blank">Provider Resources</a></li>' +
						'<li><a href="' + path + 'providers/enrollment/tradingpartner/instructions.html" target="_blank">Trading Partner Enrollment</a></li>' +
					'</ul>' +
				'</div>';
	document.write(qLinks);
}


function writeQuickLinksSecureInternal(path) {
	qLinks =	'<div class="linkMenu">' +
					'<ul class="podList">' +
						'<li><a href="' + path + 'internal/member/eligibilityquickview.html">Eligibility Quick View</a></li>' +
						'<li><a href="' + path + 'internal/claims/claimSearch.html">Claim Inquiry</a></li>' +
						'<li><a href="' + path + 'internal/claims/searchserviceauth5010.html">Service Auth Inquiry</a></li>' +
						'<li><a href="' + path + 'providers/providerResourcesCR.html">Resources</a></li>' +
					'</ul>' +
				'</div>';
	document.write(qLinks);
}

function writeRegister(path) {
	register =	'<div>For providers to obtain a user name and password to use the Health Enterprise portal, they must be a current provider for Medicaid. For trading partners to obtain a username and password, they must be a current Trading Partner with a trading partner ID. To begin the registration process, they must have their enrollment form ready.</div>' +
				'<div>' +
					'<ul class="podList">' +						
						'<li><a href="' + path + 'providers/provider-web-registration.html">Register</a></li>' +						
					'</ul>' +
				'</div>';
	document.write(register);
}

function writeRegister1(path) {
	register =	'<div>For providers to obtain a user name and password to use the Health Enterprise portal, they must be a current provider for Medicaid. To begin the registration process, they must have their enrollment form ready.</div>' +
				'<div>' +
					'<ul class="podList">' +						
						'<li><a href="' + path + 'providers/provider-web-registration.html">Register</a></li>' +						
					'</ul>' +
				'</div>';
	document.write(register);
}

function writeMessage(path,str) {
	if(!str) str = '';
	message = 		'<div id="message_success' + str + '" class="hide">' +
					'<div id="msgBox' + str + '" class="msgBox">' +
						'Success' +
					'</div>' +
					'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
				'</div>' +

				'<div id="message_altid' + str + '" class="hide">' +
					'<div id="msgAltBox' + str + '" class="msgBox">' +
						'The Alternate ID has been successfully transferred to the Target Member. TCN Specific Claims Adjustment request(s) have been generated successfully for the transferred Alternate ID.' +
					'</div>' +
					'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
				'</div>' +

				'<div id="message_unmerge_claims' + str + '" class="hide">' +
					'<div id="msgUnmergeClaimsBox' + str + '" class="msgBox">' +
						'TCN Specific Claims Adjustment request(s) generated successfully for selected claims.' +
					'</div>' +
					'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
				'</div>' +
				
				'<div id="message_delete' + str + '" class="hide">' +
					'<div id="msgDeleteBox' + str + '" class="msgBox">' +
						'Successfully Deleted' +
					'</div>' +
					'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
				'</div>' +

				'<div id="message_void' + str + '" class="hide">' +
					'<div id="msgVoidBox' + str + '" class="msgBox">' +
						'Successfully Voided' +
					'</div>' +
					'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
				'</div>' +

				'<div id="message_error' + str + '" class="hide">' +
					'<div id="errorBox' + str + '" class="errorText">' +
						'<img src="' + path + 'images/icon_warning_sml.gif"> Please correct the following errors:' +
						'<div class="indent errorText">Error 1</div>' +
					'</div>' +
					'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
				'</div>'+

				'<div id="message_duplicate_member' + str + '" class="hide">' +
					'<div id="errorBox' + str + '" class="errorText">' +
						'<img src="' + path + 'images/icon_warning_sml.gif">&nbsp;&nbsp;&nbsp;A Potential Duplicate Carrier has been found.' +						
					'</div>' +
					'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
				'</div>';
	document.write(message)
}


function writeMsg(type, id){
       el = document.getElementById(id);
       el.innerHTML = "";
       el.className = "msgBox";
       if (type == 'save'){
               el.innerHTML = "Saved";
       }
       else if (type == "delete"){
               el.innerHTML = "Successfully Deleted";
       }
       else if (type == "attachMore"){
           el.innerHTML = "Selected documents are available. Search, Select and Attach more documents, or Save to return to the source record with the attachments.";
       }
       else if (type == "error"){
               el.innerHTML = "ERROR";
       }
       else if (type == "validate"){
               el.innerHTML = "Validated";
       }
       showMe(id,'message_')
}


