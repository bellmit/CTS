function writeTopNav(area) {
	um.baseSRC = path + 'images/';
	var nav_generalNo= '<div id="head">' +
					'<div id="headImg"><img src="'+ path +'images/' + appLogo +'" /></div>' +
					'<div id="headTxt">' +
						'<div id="headLine2"><a href="help/contactus.html">Contact Us</a>&nbsp;&#124;&nbsp;<a href="javascript:openHelpWindow(\'' + path +'\')">Help</a>&nbsp;&#124;&nbsp;' +
						 '<a href="javascript:openSearchWindow(\'' + path +'\')">Search' +
						'</div>' +
					'</div>' +
				'</div>' +
				'<div id="nav">' +
					'<ul id="udm" class="udm">' +
						'<li><a id="menuBarTitle" onclick="document.location.href=\''+ path +'index.html\'">Home</a></li>' +
						'<li><a href="'+ path +'program/landing.html">Program</a>' +
							'<ul>' +
								'<li><a href="http://medicaidprovider.hhs.mt.gov/clientpages/clientindex.shtml" target="_blank">Medicaid Information</a></li>' +
								'<li><a href="http://hmk.mt.gov/" target="_blank">Healthy Montana Kids</a></li>' +
								'<li><a href="http://www.dphhs.mt.gov/prescriptiondrug/prescriptiondrugassistance.shtml" target="_blank">Pharmacy Services</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+ path +'members/memberLandingPublic.html">Members</a>' +
							'<ul>' +
								'<li><a href="https://app.mt.gov/mtc/apply/index.html" target="_blank">How to Apply</a></li>' +
								'<li><a href="'+ path +'documentation/memberFAQ.html">Member FAQ</a>' +
								'<li><a href="http://medicaidprovider.hhs.mt.gov/clientpages/clientindex.shtml" target="_blank">Benefits Overview</a></li>' +
								'<li><a href="http://www.dphhs.mt.gov/hcsd/medicaid.shtml" target="_blank">General Eligibility Rules</a></li>' +
								'<li><a href="'+ path +'directories/findproviderpublic.html">Find a Health Care Provider</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+ path +'providers/provLandingPublic.html">Provider</a>' +
							'<ul>' +
								'<li><a href="'+ path +'documentation/bannermessages.html">Messages & Announcements</a></li>' +
								'<li><a href="'+ path +'providers/enrollment/start.html">Enrollment</a></li>' +
								'<li><a href="'+ path +'help/faq.html">Provider FAQ</a></li>' +		
								
								'<li><a href="'+ path +'providers/eula.html">Billing Manuals</a></li>' +								
							'</ul>' +
						'</li>' +
						'<li><a href="'+ path +'documentation/landing.html">Documentation</a>' +
							'<ul>' +
								'<li><a href="'+ path +'documentation/messages.html">Messages & Announcements</a></li>' +
								'<li><a href="'+ path +'documentation/documentsandforms.html">Documents &amp; Forms</a></li>' +
								'<li><a href="'+ path +'documentation/userguides.html">User&nbsp;Guides</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+ path +'directories/landing.html">Directories</a>' +
							'<ul>' +
								'<li><a href="'+ path +'directories/findproviderpublic.html">Find a Health Care Provider</a></li>' +
								'<li><a href="http://www.dphhs.mt.gov/contactus/index.shtml" target="_blank">Find a State Office</a></li>' +
							'</ul>' +
						'</li>' +
					'</ul>' +
				'</div>' +
				'<div class="clear"></div>' +
				'<div><img src="'+ path +'images/x.gif" border="0" width="1" height="2" alt="" /></div>';
	
	var nav_general = '<div id="head">' +
						
					'<div id="headImg"><img src="'+ path +'images/' + appLogo +'" /></div>' +
					'<div id="headTxt">' +
						'<div id="headLine2"><a href="help/contactus.html">Contact Us</a>&nbsp;&#124;&nbsp;<a href="javascript:openHelpWindow(\'' + path +'\')">Help</a>&nbsp;&#124;&nbsp;' +
						 '<a href="javascript:openSearchWindow(\'' + path +'\')">Search' +
						'</div>' +
					'</div>' +
				'</div>' +
				'<div id="nav">' +
					'<ul id="udm" class="udm">' +
						'<li><a id="menuBarTitle" onclick="document.location.href=\''+ path +'index.html\'">Home</a></li>' +
						'<li><a href="'+ path +'program/landing.html">Program</a>' +
							'<ul>' +
								'<li><a href="http://www.dphhs.mt.gov/programsservices/medicaid.shtml" target="_blank">Medicaid</a></li>' +
								'<li><a href="http://hmk.mt.gov/" target="_blank">Healthy Montana Kids</a></li>' +
								'<li><a href="http://www.dphhs.mt.gov/amdd/services/index.shtml" target="_blank">Mental Health Services Plan</a></li>' +
								'<li><a href="http://www.dphhs.mt.gov/sltc/services/communityservices/index.shtml" target="_blank">Home and Community Based Services</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+ path +'members/memberLandingPublic.html">Member</a>' +
							'<ul>' +
								'<li><a href="https://app.mt.gov/mtc/apply/index.html" target="_blank">How to Apply</a></li>' +
								'<li><a href="http://medicaidprovider.hhs.mt.gov/clientpages/clientindex.shtml" target="_blank">Benefits Overview</a></li>' +
								'<li><a href="http://www.dphhs.mt.gov/hcsd/medicaid.shtml" target="_blank">General Eligibility Rules</a></li>' +
								'<li><a href="'+ path +'directories/findproviderpublic.html">Find a Health Care Provider</a></li>' +
								'<li><a href="'+ path +'documentation/memberFAQ.html">Member FAQ</a>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+ path +'providers/provLandingPublic.html">Provider</a>' +
							'<ul>' +
								'<li><a href="'+ path +'documentation/bannermessages.html">Messages & Announcements</a></li>' +
								'<li><a href="'+ path +'providers/enrollment/start.html">Enrollment</a></li>' +
								'<li><a href="'+ path +'help/faq.html">Provider FAQ</a></li>' +		
								'<li><a href="'+ path +'providers/eula.html">Billing Manuals</a></li>' +								
							'</ul>' +
						'</li>' +
						'<li><a href="'+ path +'documentation/landing.html">Documentation</a>' +
							'<ul>' +
								'<li><a href="'+ path +'documentation/messages.html">Messages & Announcements</a></li>' +
								'<li><a href="'+ path +'documentation/documentsandforms.html">Documents &amp; Forms</a></li>' +
								'<li><a href="'+ path +'documentation/userguides.html">User&nbsp;Guides</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+ path +'directories/landing.html">Directories</a>' +
							'<ul>' +
								'<li><a href="'+ path +'directories/findproviderpublic.html">Find a Health Care Provider</a></li>' +
								'<li><a href="http://www.dphhs.mt.gov/contactus/index.shtml" target="_blank">Find a State Office</a></li>' +
							'</ul>' +
						'</li>' +
					'</ul>' +
				'</div>' +
				'<div class="clear"></div>' +
				'<div><img src="'+ path +'images/x.gif" border="0" width="1" height="2" alt="" /></div>';
				
			var nav_generalm = '<div id="head">' +
					'<div id="headImg"><img src="'+ path +'images/' + appLogo +'" /></div>' +
					'<div id="headTxt">' +
						'<div id="headLine2"><a href="help/contactus.html">Contact Us</a>&nbsp;&#124;&nbsp;<a href="javascript:openHelpWindow(\'' + path +'\')">Help</a>&nbsp;&#124;&nbsp;' +
						 '<form name="searchForm" action="">' +
							'<a href="javascript:openSearchWindow(\'' + path +'\')">S<span class="accesskey">e</span>arch</a>' +
						 '</form>&nbsp;&#124;&nbsp;<a href="javascript:void(0)">Login</a>' +
						'</div>' +
					'</div>' +
				'</div>' +
				'<div id="nav">' +
					'<ul id="udm" class="udm">' +
						'<li><a id="menuBarTitle" onclick="document.location.href=\''+ path +'index.html\'">Home</a></li>' +
						'<li><a href="'+ path +'program/landing.html">Program</a>' +
							'<ul>' +
								'<li><a href="http://medicaidprovider.hhs.mt.gov/clientpages/clientindex.shtml" target="_blank">Medicaid Information</a></li>' +
								'<li><a href="http://hmk.mt.gov/" target="_blank">Healthy Montana Kids</a></li>' +
								'<li><a href="http://www.dphhs.mt.gov/prescriptiondrug/prescriptiondrugassistance.shtml" target="_blank">Pharmacy Services</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+ path +'members/memberLandingPublic.html">Member</a>' +
							'<ul>' +
								'<li><a href="https://app.mt.gov/mtc/apply/index.html" target="_blank">How to Apply</a></li>' +
								'<li><a href="http://medicaidprovider.hhs.mt.gov/clientpages/clientindex.shtml" target="_blank">Benefits Overview</a></li>' +
								'<li><a href="http://www.dphhs.mt.gov/hcsd/medicaid.shtml" target="_blank">General Eligibility Rules</a></li>' +
							'</ul>' +
						'<li><a href="'+ path +'documentation/landing.html">Documentation</a>' +
							'<ul>' +
								'<li><a href="'+ path +'documentation/documentsandforms.html">Documents &amp; Forms</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+ path +'directories/landing.html">Directories</a>' +
							'<ul>' +
								'<li><a href="'+ path +'directories/findprovider.html">Find a Health Care Provider</a></li>' +
								'<li><a href="http://www.dphhs.mt.gov/contactus/index.shtml" target="_blank">Find a State Office</a></li>' +
							'</ul>' +
						'</li>' +
					'</ul>' +
				'</div>' +
				'<div class="clear"></div>' +
				'<div><img src="'+ path +'images/x.gif" border="0" width="1" height="2" alt="" /></div>';
	
	var nav_provider = '<div id="head">' +
					'<div id="headImg"><img src="'+ path +'images/' + appLogo +'" /></div>' +
					'<div id="headTxt">' +
						'<div id="headLine2"><a href="help/contactus.html">Contact Us</a>&nbsp;&#124;&nbsp;<a href="javascript:openHelpWindow(\'' + path +'\')">Help</a>&nbsp;&#124;&nbsp;' +
						 '<form name="searchForm" action="">' +
							'<a href="javascript:openSearchWindow(\'' + path +'\')">S<span class="accesskey">e</span>arch</a>' +
						 '</form>&nbsp;&#124;&nbsp;<a href="'+ path +'providers/index.html">Logout</a>' +
						'</div>' +
					'</div>' +
				'</div>' +
				'<div id="nav">' +
					'<ul id="udm" class="udm">' +
						//'<li><a id="menuBarTitle" href="'+path+'internal/inbox/provider_inbox.html">Provider</a></li>' +
						'<li><a id="menuBarTitle" onclick="document.location.href=\''+ path +'internal/inbox/provider_inbox.html\'">Home</a></li>' +
						'<li><a href="'+path+'providers/memberProvLanding.html">Member</a>' +
							'<ul>' +
								'<li><a href="'+path+'providers/checkeligibility.html">Check Eligibility</a></li>' +
								//'<li><a href="'+path+'members/presumptiveeligibility/presumptiveeligibilitysearch.html">Enter Presumptive Eligibility</a></li>' +
								//'<li><a >Batch Eligibility</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+path+'providers/claims/authLanding5010.html">Authorizations</a>' +
							'<ul>' +
								//'<li><a href="'+path+'providers/claims/newserviceauth5010.html">Submit Authorization Request</a></li>' +
								//'<li><a href="'+path+'providers/claims/newHcbcServiceAuth5010.html">Submit HCBC Authorization Request</a></li>' +
								'<li><a href="'+path+'providers/claims/searchserviceauth.html">View / Edit Authorization Request</a></li>' +
								'<li><a>Authorization</a>' +
									'<ul>' +
										'<li><a href="'+ path +'providers/claims/newserviceauth5010.html?action=prof&">Submit Professional Authorization</a></li>' +
										'<li><a href="'+ path +'providers/claims/newserviceauth5010.html?action=hcbc&">Submit HCBC Authorization</a></li>'+
										'<li><a href="'+ path +'providers/claims/newserviceauth5010.html?action=dent&">Submit Dental Authorization</a></li>'+
										'<li><a href="'+ path +'providers/claims/newserviceauth5010.html?action=dme&">Submit DME Authorization</a></li>'+
										'<li><a href="'+ path +'providers/claims/newserviceauth5010.html?action=inst&">Submit Institutional Authorization</a></li>'+
									'</ul>' +
								'</li>' +
								'<li><a>Referral</a>' +
									'<ul>' +
									//	'<a href="'+path+'providers/claims/newreferral5010.html?action=csp&">Submit Coordinated Services Program Referral</a></li>' +
									//	'<a href="'+path+'providers/claims/newreferral5010.html?action=othdent&">Submit Other/Dental Referral</a></li>' +
										'<li><a href="'+path+'providers/claims/newreferral5010.html?action=pcm&">Submit Primary Care or Pharmacy Referral</a></li>' +															
										'<li><a href="'+path+'providers/claims/newreferralReq5010.html?action=pcm&">Submit Primary Care or Pharmacy Referral Request</a></li>' +
										'<li><a href="'+path+'providers/claims/searchreferral.html">View / Edit Referral</a></li>' +
									'</ul>' +
								'</li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+ path +'providers/claims/index.html">Claims</a>' +
							'<ul>' +
								'<li><a>Create Claims</a>' +
									'<ul>' +
										'<li><a href="'+ path +'providers/claims/searchclaim_submitted.html">Create Claim from Processed Claim</a></li>' +
										'<li><a href="'+ path +'providers/claims/searchtemplate_createfrom.html">Create Claim from Template</a></li>' +
										'<li><a href="'+ path +'providers/claims/newclaimdental5010.html">Create Dental Claim</a></li>' +
										'<li><a href="'+ path +'providers/claims/newclaimpro15010.html">Create Professional Claim</a></li>' +
										'<li><a href="'+ path +'providers/claims/newclaiminst5010.html">Create Institutional Claim</a></li>' +
										'<li><a href="'+ path +'providers/claims/rosterBilling.html">Create Roster Billing</a></li>' +
										
										//'<li><a href="'+ path +'providers/claims/optionsClaim.html">Create Options Claim</a></li>' +
										
										
									'</ul>' +
								'</li>' +
								'<li><a>Manage Claims</a>' +
									'<ul>' +
										'<li><a href="'+ path +'providers/claims/searchclaim_editdelete.html">Edit / Delete Saved Claim</a></li>' +
										'<li><a href="'+ path +'providers/claims/claimSearch_viewSubmitted.html">View Submitted Claims</a></li>' +
									'</ul>' +
								'</li>' +
								'<li><a>Create Templates</a>' +
									'<ul>' +
										'<li><a href="'+ path +'providers/claims/createtemplatename.html?action=dentalTemp&">Create Dental Template</a></li>' +
										'<li><a href="'+ path +'providers/claims/createtemplatename.html?action=instTemp&">Create Institutional Template</a></li>' +
										'<li><a href="'+ path +'providers/claims/createtemplatename.html?action=profTemp&">Create Professional Template</a></li>' +
									'</ul>' +
								'</li>' +
								'<li><a>Manage Templates</a>' +
									'<ul>' +
										'<li><a href="'+ path +'providers/claims/searchtemplate_copy.html">Copy Template</a></li>' +
										'<li><a href="'+ path +'providers/claims/searchtemplate_editdelete.html">View / Edit / Delete Template</a></li>' +
									'</ul>' +
								'</li>' +
								'<li><a href="'+path+'providers/claims/claimStatusInquiry5010.html">Claim Status Inquiry</a></li>' +
								'<li><a href="'+ path +'providers/claims/paymentInquiry.html">Payment Inquiry</a></li>' +
								//'<li><a href="'+ path +'providers/claims/searchexceptioncode.html">Check Exception Code</a></li>' +
								//'<li><a href="'+ path +'providers/claims/searchprocedurecode.html">Check Procedure Code</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+ path +'providers/edi/landing.html">EDI</a>' +
							'<ul>' +
								'<li><a href="'+ path +'providers/edi/edifrm.html">File Retrieval Mailbox</a></li>' +
								'<li><a href="'+ path +'providers/edi/submitX12BatchFile.html">Upload X12</a></li>' +
								//'<li><a href="'+ path +'internal/contactManagement/downloadEDGS.html">File Retrieval Mailbox</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+ path +'providers/provAccountLanding.html">My Account</a>' +
							'<ul>' +
							 '<li><a href="'+path+'security/providerchangepin.html">Change AVR PIN</a></li>' +
								 '<li><a href="'+ path +'security/providerchangepassword.html">Change Password</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+ path +'providers/manageusersLanding.html">Manage Users</a>' +
							'<ul>' +
								 '<li><a href="'+ path +'security/providermanageusers.html">Manage Users</a></li>' +
							'</ul>' +
						'</li>' +
					'</ul>' +
				'</div>' +
				'<div class="clear"></div>' +
				'<div><img src="'+ path +'images/x.gif" border="0" width="1" height="2" alt="" /></div>';
				
	var nav_member = '<div id="head">' +
					'<div id="headImg"><img src="'+ path +'images/' + appLogo +'" /></div>' +
					'<div id="headTxt">' +
						'<div id="headLine2"><a href="help/contactus.html">Contact Us</a>&nbsp;&#124;&nbsp;<a href="javascript:openHelpWindow(\'' + path +'\')">Help</a>&nbsp;&#124;&nbsp;' +
						 '<form name="searchForm" action="">' +
							'<a href="javascript:openSearchWindow(\'' + path +'\')">S<span class="accesskey">e</span>arch</a>' +
						 '</form>&nbsp;&#124;&nbsp;<a href="'+ path +'members/index.html">Logout</a>' +
						'</div>' +
					'</div>' +
				'</div>' +
				'<div id="nav">' +
					'<ul id="udm" class="udm">' +
						'<li><a id="menuBarTitle" onclick="document.location.href=\''+ path +'members/index2.html\'">Home</a></li>' +
						//'<li><a>Members</a>' +
						//	'<ul>' +
			            //  '<li><a href="'+ path +'members/eligibilityquickview.html">Display Quick View Eligibility</a></li>' +			              
						//	'</ul>' + 
						//'</li>' +													
						//'<li><a>Benefit Plans</a>' +
							//'<ul>' +
				           //'<li><a href="'+ path +'members/maintainpcp.html">Maintain Primary Care Provider</a></li>' +              
							//'</ul>' + 
						//'</li>' +
						'<li><a href="'+ path +'members/summaryOfCareLanding.html">Summary of Care</a>' +
							'<ul>' +
			              '<li><a href="'+ path +'members/displayclaims.html">Display Claims</a></li>' +
			              '<li><a href="'+ path +'members/providersvisited.html">Display Providers Visited</a></li>' +
			              '<li><a href="'+ path +'members/epsdt.html">Well-Child (EPSDT)</a></li>' +
			              '<li><a href="#">Plan of Care</a></li>' +
				      '<li><a href="servicelimits.html">Benefit/Service Limits</a></li>' +
			              '<li style="display:' + ( state == "OH" ? "block" : "none") + '"><a href="'+path+'internal/member/healthReports.html">Health Reports</a></li>' +
							'</ul>' + 
						'</li>' + 												
						'<li><a href="'+ path +'members/requestsLanding.html">Requests</a>' +
							'<ul>' +
							  '<li><a target="new" href="https://base.liveperson.net">Live Chat</a></li>' +
				              '<li><a href="'+ path +'members/searchmsq.html">Medical Services Questionnaire</a></li>' +
				              '<li><a href="'+ path +'members/requestTransport.html">Personal Transportation</a></li>' +
				              '<li><a href="'+ path +'members/findprovider.html">General Provider Search</a></li>' +
				              '<li><a href="javascript:mbrFindProv(0)">Select/Search for PCP/MCO Provider</a></li>' +
				              '<li><a href="'+ path +'members/updateTPL.html">Update Other Insurance</a></li>' +
				              '<li><a href="'+ path +'members/userProfile.html">Update User Preferences</a></li>' +
				             // '<li><a href="'+ path +'members/requestswipecard.html">Request Swipe Card Replacement</a></li>' +
							'</ul>' + 
						'</li>' +
						'<li><a href="'+ path +'members/myAccountLanding.html">My Account</a>' +
						'<ul>' +
							// '<li><a href="'+ path +'security/editprofile.html">Edit Profile</a></li>' +
							 '<li><a href="'+ path +'security/memberchangepin.html">Change PIN</a></li>' +
						'</ul>' +
					'</li>' +					
					'</ul>' +
				'</div>' +
				'<div class="clear"></div>' +
				'<div><img src="'+ path +'images/x.gif" border="0" width="1" height="2" alt="" /></div>';
				
	var nav_internal = 	'<div id="head">' +
							'<div id="headImg"><img src="'+ path +'images/' + appLogo +'" alt="' + appName +'" border="0" /></div>' +
							'<div id="headTxt">' +
								'<div id="headLine2"><a href="help/contactus.html">Contact Us</a>&nbsp;&#124;&nbsp;&nbsp;<a href="javascript:openHelpWindow(\'' + path +'\')">Help</a>&nbsp;&#124;&nbsp;' +
									'<form name="searchForm" action="">' +
										'<a href="javascript:openSearchWindow(\'' + path +'\')">S<span class="accesskey">e</span>arch</a>' +
									'</form>&nbsp;&#124;&nbsp;<a href="'+ path +'internal/index.html">Logout</a>' +
								'</div>' +
							'</div>' +
						'</div>' +
						'<div id="nav">' +
							'<ul id="udm" class="udm">' +
								'<li><a id="menuBarTitle" onclick="document.location.href=\''+ path +'internal/inbox/inbox.html\'">Home</a></li>' +
								'<li><a href="'+ path +'internal/projectcontrol/landing.html">Project Control</a>' +
									'<ul>' +
										//'<li><a href="'+ path +'internal/projectcontrol/projectrepository.html">Project Repository</a></li>' +
										//'<li><a href="'+ path +'internal/projectcontrol/issuemanagement.html">Issue Management</a></li>' +
										//'<li><a href="'+ path +'internal/projectcontrol/performancemonitoring.html">Performance Monitoring</a></li>' +
										//'<li><a>Case / Corr Mgmt</a>' +
											//'<ul>' +
												//'<li><a>Interaction Management</a></li>' +
												//'<li><a>Contact Management</a></li>' +
												//'<li><a>Letter Generation</a></li>' +
											//'</ul>' +
										//'</li>' +
										//'<li><a>Document Mgmt</a>' +
											//'<ul>' +
												//'<li><a>Images</a></li>' +
												//'<li><a>Reports</a></li>' +
												//'<li><a>Workflows</a></li>' +
											//'</ul>' +
										//'</li>' +
										'<li><a href="'+path+'internal/projectcontrol/eventlogging.html">Event Logging</a></li>' +
										'<li><a href="'+path+'internal/projectcontrol/globalAuditSearch.html">Global Audit</a></li>' +
										'<li><a href="'+path+'internal/globalCaseSearch.html">Global Case</a></li>' +
										'<li><a href=" http://10.221.84.126/docfinity/intraviewer/Logon.cfm" target="new">Document Finder aka IntraViewer</a></li>' +
										'<li><a href="'+path+'internal/provider/searchEDMS.html">Search Document Repository</a></li>' +						
									
									'</ul>' +
								'</li>' +
                  		'<li><a href="'+ path +'internal/member/landing.html">Member</a>' +
									'<ul>' +
										'<li><a href="'+ path +'internal/member/membersearch.html?action=memberdetails.html&">Member Summary Inquiry</a></li>' +
										'<li><a href="'+ path +'internal/member/membersearch.html?action=servicelimits.html&">Benefit/Service Limits</a></li>' +
										'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=uncaneligibility.html&">Unconcatenated Eligibility</a></li>' +
										'<li><a href="'+path+'internal/member/membersearch.html?action=eligibilityquickview.html&">Eligibility Quick View</a></li>' +
										'<li><a href="'+path+'internal/member/membersearch.html?action=maintainappeals.html&">Member Appeals Inquiry</a></li>' +
										'<li><a>Member Maintenance</a>' +
											'<ul>' +
			                      			'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=memberdetails.html&">Member Details</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=eligibility.html&">Eligibility COE/Spenddown</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=eligibilitySpecial.html&">Special Eligibility/Waiver Liability</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=enrollmentspans.html&">Benefit Plan Enrollment</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=pcp.html&">PCCM/Managed Care</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=ltc.html&">Nursing Facility Spans/Patient Liability</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=pcp.html&">Review/Suspend/Restriction</a></li>' +
												//'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=medicarespans.html&">TPL/Medicare Information</a></li>' +
												'<li><a href="'+path+'internal/financial/tpl/searchtplpolicy.html">TPL/Medicare Information</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=absentparent.html&">Absent Parent/Custodial Entity</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/caseinfo.html">Case</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/authorizedRepSearch.html">Member Representative</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=alternateId.html&">Alternate ID</a></li>' +
												'<li><a href="'+path+'internal/member/membersearch.html?action=maintenance/requestswipecard.html&">Member ID Card</a></li>' +
												'<li><a href="'+path+'internal/member/membersearch.html?action=maintenance/planOfCare.html&">Plan of Care</a></li>' +
												'<li><a href="'+path+'internal/member/membersearch.html?action=maintenance/costPlan.html&">Cost Plan</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/memberWaitsearch.html">Wait List</a></li>' +
											'</ul>' +
										'</li>' +
									'<li><a href="'+path+'internal/member/addNewMember.html">Add a Member</a></li>' +
									'<li><a href="'+path+'internal/contactManagement/casenewmember.html">Add Member Appeal</a></li>' +
									'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=msq.html&">Medical Services Questionnaire</a></li>' +
									'<li><a href="'+path+'internal/member/maintenance/mergeduplicates.html">Merge Suspect Duplicates</a></li>' +
									'<li><a href="'+path+'internal/member/maintenance/unmergeAltId.html">Unmerge-Alternate ID Transfer</a></li>' +
									'<li><a href="'+path+'internal/member/maintenance/unmergeClaims.html">Transfer Claims</a></li>' +
									//CR278 removed '<li><a href="'+path+'internal/member/suspenseprocessing.html">Process Suspended Transactions</a></li>' +
									// '<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=../../care/epsdt.html&">EPSDT</a></li>' +
									//'<li><a>Buy-in Request</a></li>' +
									//'<li><a href="'+path+'internal/member/liabilities.html">Other Member Liabilities</a></li>' +
									//'<li><a href="'+path+'internal/member/premium.html">Premium</a></li>' +
									//CR278 removed '<li><a href="'+path+'internal/member/memberretrieve.html">Retrieve Member from Archive</a></li>' +
									//'<li><a>Unmerge</a>' +
									 //'<ul>' +	
										//	'<li><a href="'+path+'internal/member/maintenance/unmergeClaims.html">Claims Transfer</a></li>' +
										//'</ul>' +
									//'</li>' +
									'<li><a>Medicare</a>' +
									'<ul>' +
									'<li><a href="'+path+'internal/member/edbSearch.html">MMA</a></li>' +
										'<li><a href="'+path+'internal/member/bendexSearch.html">BENDEX</a></li>' +
										'<li><a href="'+path+'internal/member/buyinSearch.html">Buy-in Suspense Review</a></li>' +
										'<li><a href="'+path+'internal/member/buyinHistory.html">Buy-in History</a></li>' +
										'<li><a href="'+path+'internal/member/buyinManEntrySearch.html">Buy-in Manual Transaction</a></li>' +
									'</ul>' +
								'</li>' +

									'<li style="display:' + ( state == "OH" ? "block" : "none") + '"><a href="'+path+'internal/member/healthReports.html">Health Reports</a></li>' +
								 '</ul>' +
							  '</li>' +
								'<li><a href="'+ path +'internal/provider/landing.html">Provider</a>' +
							 		'<ul>' +
									    '<li><a>Providers</a>'+
									    	'<ul>' +
												'<li><a href="'+ path +'internal/provider/providermaintenancesearch.html">Provider Maintenance</a></li>' +
												'<li><a href="'+ path +'internal/provider/man.html">Managed Care</a></li>' +
												'<li><a href="'+ path +'internal/provider/providersearch.html">Provider Inquiry</a></li>' +
												'<li><a href="'+ path +'internal/provider/eligConfirmation.html">Member Eligibility Confirmation</a></li>' +
												'<li><a href="'+ path +'internal/provider/enrollmenttrackingsearch.html">Provider Application Maintenance</a></li>' +
											'</ul>' +
										'</li>' +	
										'<li><a href="'+ path +'internal/provider/providerTraining.html">Provider Training Courses / Sessions</a></li>' +
										'<li><a href="'+ path +'internal/provider/typespecialty.html">Provider Type / Specialty Maintenance</a></li>' +
										'<li><a href="'+ path +'internal/provider/typeSpecialtyTaxonomy.html">Provider Type/Specialty/Taxonomy</a></li>' +
									    '<li><a>Trading Partners</a>'+
									    	'<ul>' +								
												'<li><a href="'+ path +'internal/provider/tradingpartner/tradingpartnermaintenance.html">Trading Partner Maintenance</a></li>' +
												'<li><a href="'+ path +'internal/provider/tradingpartner/tradingpartnerinquiry.html">Trading Partner Inquiry</a></li>' +
												'<li><a href="'+ path +'internal/provider/tradingappmaintenance.html">Trading Partner App Maintenance</a></li>' +
												//cr 1239 not available for internal menu '<li><a href="'+ path +'internal/provider/tradingpartner/edifrm.html">File Retrieval Mailbox</a></li>' +
												//'<li><a href="'+ path +'internal/contactManagement/downloadEDGS.html">File Retrieval Mailbox</a></li>' +
											'</ul>' +
										'</li>' +
									//	'<li><a href="'+ path +'internal/provider/carriersearch.html">Medicare Carrier Maintenance</a></li>' +
									//	'<li><a href="'+ path +'internal/provider/ssntaxmaintenancesearch.html">SSN/Tax ID Maintenance</a></li>' +
									//	'<li><a href="'+ path +'internal/provider/enrollmentevent.html">Enrollment Events</a></li>' +
									//	'<li><a href="'+ path +'internal/provider/applicationpackagesearch.html">Application Package Search</a></li>' +
									//	'<li><a>ARS - Census</a>' +
									//		'<ul>'+
									//			'<li><a href="'+path+'internal/provider/census/enterCensusData.html">Enter Census Data</a></li>' +
									//			'<li><a href="'+path+'internal/provider/census/searchMDS.html">Search MDS Data</a></li>' +
									//			'<li><a href="'+path+'internal/provider/census/searchFacility.html">Search Facility</a></li>' +
									//			'<li><a href="'+path+'internal/provider/census/searchResidentAcuity.html">Search Acuity by Resident</a></li>' +
									//		'</ul>' +
			            		  			//      '</li>' +	
									//	'<li><a>ARS - Cost Report</a>'+
									//		'<ul>'+
									//			'<li><a href="'+path+'internal/provider/costAcuity/searchCostReport.html">Search Cost Report</a></li>' +
                                    /*                                  //
					                                //			'<li><a href="'+path+'internal/provider/costAcuity/auditorWorkbook.html">Auditor Workbook</a></li>' +
                                    */                                  //		'</ul>' +
			            		                        //      '</li>' +
			            		  /*'<li><a>ARS - Home Office Cost Report</a>'+
											'<ul>'+
												'<li><a href="'+path+'internal/provider/homeOfficeCostReport/searchCostReport.html">Search Cost Report</a></li>' +
												'<li><a href="'+path+'internal/provider/homeOfficeCostReport/viewHomeOfficeReport.html">View/Update Home Office Cost Report</a></li>' +												
											'</ul>' +
			            		  '</li>' +*/
									//	'<li><a>ARS - MQIP</a>' +
									//		'<ul>'+
									//			'<li><a href="'+path+'internal/provider/mqip/manageMQIP.html">MQIP</a></li>' +
			 						//			'<li><a href="'+path+'internal/provider/mqip/manageNfqa.html">Manage NFQA Tax Reports</a></li>' +
			 						//		'</ul>' +
			 						//	'</li>' +
									//	'<li><a>ARS - Performance</a>' +
									//		'<ul>'+
			 						//			'<li><a href="'+path+'internal/provider/performance/performance.html">Performance Measure</a></li>' +
			 						//			'<li><a href="'+path+'internal/provider/performance/weightPerformanceMeasures.html">Weight Performance Measures</a></li>' +
			 						//			'<li><a href="'+path+'internal/provider/performance/enterPerformanceData.html">Enter Performance Data</a></li>' +
			 						//			'<li><a href="'+path+'internal/provider/performance/performanceMultiplier.html">Performance Multiplier</a></li>' +
			 						//			'<li><a href="'+path+'internal/provider/performance/performanceScore.html">Search Facility Performance Score</a></li>' +
			 						//		'</ul>' +
		            		                                //        '</li>' +
									//	'<li><a>ARS - Rate Setting</a>'+
			 						//		'<ul>'+
			 						//			'<li><a href="'+path+'internal/provider/rateSetting/inflation.html">Inflation Factor</a></li>' +
			 						//			'<li><a href="'+path+'internal/provider/rateSetting/initiateRateSetting.html">Initiate Rate Setting Process</a></li>' +
			 						//			'<li><a href="'+path+'internal/provider/rateSetting/maintainCostComponentParameters.html">Maintain Cost Component Parameters</a></li>' +
			 						//			'<li><a href="'+path+'internal/provider/rateSetting/managebudgetNeutral.html">Manage Budget Neutral Calculations</a></li>' +
			 						//			//'<li><a href="'+path+'internal/provider/rateSetting/usualCustomary.html">Usual & Customary Charge</a></li>' +
			 						//			'<li><a href="'+path+'internal/provider/rateSetting/searchFacilityRateData.html">Search Facility Rate Data</a></li>' +
			 						//		'</ul>' +
		            		                                //         '</li>' +
		            		                                //          '<li><a>ARS - RUG</a>' +
		            		      	                        //                '<ul>'+
			 						//			'<li><a href="'+path+'internal/provider/rug/rug.html">RUG</a></li>' +
			 						//			'<li><a href="'+path+'internal/provider/rug/rugWeightInput.html">RUG Weight Input</a></li>' +
			 						//			'<li><a href="'+path+'internal/provider/rug/approveRugWeightInput.html">Approve RUG Weights</a></li>' +
			 						//		'</ul>' +
			 						//	'</li>' +
										//'<li><a href="'+ path +'internal/provider/providerreportrequest.html">Provider Mailing Labels</a></li>' +	            		    
										'<li><a href="'+ path +'internal/provider/providerappealsearch.html">Provider Appeals Inquiry</a></li>' +
										'<li><a href="'+ path +'internal/contactManagement/casenewprovider.html">Add Provider Appeals</a></li>' +	    	            		    
									'</ul>' +
								'</li>' +
                  				'<li><a href="'+ path +'internal/contactManagement/landing.html">Ct Mgmt</a>' +
                     				'<ul>' +
										'<li><a href="'+ path +'internal/contactManagement/correspondenceSearch.html">Correspondence</a></li>' +
										'<li><a href="'+ path +'internal/contactManagement/entitySearch.html">Entity</a></li>' +
										'<li><a href="'+ path +'internal/contactManagement/searchCase.html">Case</a></li>' +
										'<li><a>Maintenance</a>'+
	 										'<ul>'+
												'<li><a href="'+ path +'internal/contactManagement/maintainCallScript.html">Call Scripts</a></li>' +
												'<li><a href="'+ path +'internal/contactManagement/MaintainCategory.html">Category</a></li>' +
												//removed per Derrick'<li><a href="'+ path +'internal/contactManagement/maintainSubject.html">Subject</a></li>' +
												'<li><a href="'+ path +'internal/contactManagement/maintainkeywords.html">Keywords</a></li>' +
												'<li><a href="'+ path +'internal/contactManagement/maintainTemplates.html">Templates</a></li>' +
												//'<li><a href="'+ path +'internal/contactManagement/maintainCaseDates.html">Case Dates</a></li>' +
												//'<li><a href="'+ path +'internal/contactManagement/maintainCaseSteps.html">Case Steps</a></li>' +
												'<li><a href="'+ path +'internal/contactManagement/maintainCaseType.html">Case Type</a></li>' +
												'<li><a href="'+ path +'internal/contactManagement/maintainCustomFields.html">Custom Fields</a></li>' +
												'<li><a href="'+ path +'internal/contactManagement/maintainEdmsDefaults.html">EDMS Defaults</a></li>' +
												'<li><a href="'+ path +'internal/contactManagement/maintainFilters.html">Filters</a></li>' +
												'<li><a href="'+ path +'internal/contactManagement/managegroups.html">Manage Groups</a></li>' +
										//Added for New dicussion
												
											'</ul>' +
            		    			'</li>' +
            		    			'<li><a href="'+ path +'internal/contactManagement/notesSearch.html">Notes</a></li>' +
            		    			//'<li><a href="' + path + 'internal/contactManagement/letterGenerate.html">Letter Generation</a></li>' +
            		    			//removed per Derrick'<li><a href="' + path + 'internal/contactManagement/archiveCRSearch.html">Archive CR Search</a></li>' +
            		    			//removed per Derrick'<li><a href="' + path + 'internal/contactManagement/archiveCaseSearch.html">Archive Case Search</a></li>' +
            		    			//removed per Casey'<li><a href="'+ path +'internal/contactManagement/downloadEDGS.html">EDSG File Search</a></li>' +            		    			<!--'<li><a href="'+ path +'internal/contactManagement/searchEDMS.html">Search Doc. Repository</a></li>' +-->
                  					'<li><a>Reassign</a>'+
	 										'<ul>'+
												'<li><a href="'+ path +'internal/contactManagement/reassignCase.html">Case</a></li>' +
												'<li><a href="'+ path +'internal/contactManagement/reassignCorrespondence.html">Correspondence</a></li>' +
											'</ul>' +
            		    			'</li>' +
            		    					'</ul>' +
                  				'</li>' +
                  				'<li><a href="'+ path +'internal/claims/landing.html">Claims</a>' +
                     				'<ul>' +
										'<li><a href="'+ path +'internal/claims/searchclaim.html">Entry</a></li>' +
										'<li><a href="'+ path +'internal/claims/searchbatch.html">Batch Control</a></li>' +
										'<li><a href="'+ path +'internal/claims/claimSearch.html">Inquiry</a></li>' +
										'<li><a href="'+ path +'internal/claims/searchcorrection.html">Correction</a></li>' +
										'<li><a href="'+ path +'internal/claims/searchmass.html">Mass Adjustments</a></li>' +
										'<li><a href="'+ path +'internal/claims/searchreferral.html">Primary Care or Pharmacy Referral</a></li>' +
										'<li><a href="'+ path +'internal/claims/massAdjustSelectClaims.html">Mass Adjustment Claim Selection</a></li>' +
										'<li><a href="'+ path +'internal/claims/rosterBilling.html">Roster Billing</a></li>' +
										'<li><a href="'+ path +'internal/claims/searchsuspense.html">Suspense Release</a></li>' +
										'<li><a href="'+ path +'internal/claims/searchserviceauth5010.html">Service Authorization</a></li>' +
										'<li><a href="'+ path +'internal/claims/searchmassupdate.html">SA Mass Update</a></li>' +
										'<li><a href="'+ path +'internal/claims/searchfinancialinquiry.html">Financial</a></li>' +
										'<li><a href="'+path+'internal/claims/history.html">History Request</a></li>' +
										'<li><a >Adjudication</a>' +
											'<ul>' +
												'<li><a href="'+ path +'internal/claims/cosassign.html">Category of Svc Assignment</a></li>' +
												'<li><a href="'+ path +'internal/claims/claimtypeassign.html">Claim Type Assignment</a></li>' +
												'<li><a href="'+ path +'internal/claims/dupcheck.html">Duplicate Check</a></li>' +
												'<li><a href="'+ path +'internal/claims/searchfiscalpend.html">Fiscal Pend</a></li>' +
												'<li><a href="'+ path +'internal/claims/fundcode.html">Fund Code</a></li>' +
												'<li><a href="'+ path +'internal/claims/claimsLocking.html">Locking</a></li>' +
												'<li><a href="'+ path +'internal/claims/tplmatrix.html">TPL Matrix</a></li>' +
											'</ul>' +
										'</li>' +
                     				'</ul>' +
                  				'</li>' +
								'<li><a href="'+ path +'internal/care/landing.html">EPSDT</a>' +
									'<ul>' +
										'<li><a href="'+ path +'internal/member/maintenance/maintainsearch.html?action=../../care/epsdt.html&">EPSDT Details</a></li>' +
										'<li><a href="'+ path +'internal/care/periodicity.html">Periodicity Schedule</a></li>' +
									'</ul>' +
								'</li>' +
								'<li><a href="'+path+'internal/rulesmgmt/landing.html">Rules Mgmt</a>' +
                     '<ul>' +
                        '<li><a href="'+path+'internal/rulesmgmt/searchclaimexception.html">Claim Exception</a></li>' +
                        '<li><a href="'+path+'internal/rulesmgmt/searchauthorizationexception.html">Service Authorization Exception</a></li>' +
                        //'<li><a href="'+path+'internal/rulesmgmt/hipaacodetranslation.html">HIPAA Code Translation</a></li>' +
                        '<li><a href="'+path+'internal/rulesmgmt/saletterassignment.html">SA Letter Assignment</a></li>' +
                        '<li><a href="'+path+'internal/rulesmgmt/searchsysparam.html">System Parameter</a></li>' +
                        '<li><a href="'+path+'internal/rulesmgmt/searchsyslist.html">System List</a></li>' +
								//'<li><a href="'+path+'internal/rulesmgmt/searchgroupsystemlist.html">Group System List</a></li>' +
								'<li><a href="'+path+'internal/rulesmgmt/searchvalidvalue.html">Data Element/Valid Value</a></li>' +
		                        '<li><a >Utilization Review</a>' +
		                           '<ul>' +
		                              '<li><a href="'+path+'internal/rulesmgmt/ur/searchmedcriteria.html">Medical Criteria</a></li>' +
		                              '<li><a href="'+path+'internal/rulesmgmt/ur/searchparameter.html">Medical Parameter</a></li>' +
									  '<li><a href="'+path+'internal/rulesmgmt/ur/searchursyslist.html">System List</a></li>' +
								 '</ul>' +
								'</li>' +
								'<li><a>Rates</a>' +
								   '<ul>' +
										//OFF for MT'<li><a href="'+path+'internal/rulesmgmt/rates/searchgroupregion.html">ASC Group / ASC Region</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchatp.html">Automated Test Panel (ATP)</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprovideridbenefitplanid.html">Institutional Provider ID / Network ID / Benefit Plan ID</a></li>' +
										'<li><a class="menuLabel">Procedure Code</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodebenefitplanid.html">Benefit Plan ID</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodebenefitplanga.html">Benefit Plan ID/ Provider Type/ Geographic Area</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodebpprovidertypespecsub.html">Benefit Plan ID/ Provider Type/Provider Specialty/ Provider Sub-Specialty</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecos.html">COS</a></li>' +
										
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodeproceduremodifier.html">Modifier</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodeplaceofservice.html">Place of Service</a></li>' +
										
										//'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodemodifierplaceofservice.html">Modifier / Place of Service</a></li>' +
										//'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodeproceduremodifier.html">Procedure Modifier</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodeproviderid.html">Provider ID / Network ID</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodeprovidertype.html">Provider Type</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedureproviderspecialties.html">Provider Specialty</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodeprovidertaxonomy.html">Provider Taxonomy</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedureprovideridbenefitplanid.html">Provider ID / Network ID / Benefit Plan ID</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodeprovidertypespecsub.html">Provider Type/Provider Specialty/ Provider Sub-Specialty</a></li>' +
										
										'<li><a class="menuLabel">Revenue Code</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchrevenuecodeprovidertype.html">Revenue Type / Provider Type</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchrevenuecodeprovideridregion.html">Revenue Type / Provider ID / Network ID / Geographic Area</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchrevenuecodebenefitplanid.html">Revenue Type / Benefit Plan ID / Provider ID</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchrevenuecodeproviderid.html">Revenue Type / Provider ID / Network ID</a></li>' +
								   '</ul>' +
								'</li>' +
								'<li><a>Rules Maintenance</a></li>' +
								'<li><a>Code Maintenance</a>' +
								   '<ul>' +
									  '<li><a href="'+path+'internal/rulesmgmt/codes/searchdiagnosis.html">Diagnosis</a></li>' +
									  '<li><a href="'+path+'internal/rulesmgmt/codes/searchaprdrg.html">APR-DRG</a></li>' +
									  '<li><a href="'+path+'internal/rulesmgmt/codes/searchdrg.html">CMS DRG</a></li>' +
									  '<li><a href="'+path+'internal/rulesmgmt/codes/searchapc.html">APC</a></li>' +
									  '<li><a href="'+path+'internal/rulesmgmt/codes/searchicd.html">Surgical Procedure</a></li>' +
									  '<li><a href="'+path+'internal/rulesmgmt/codes/searchprocedurecode.html">Procedure</a></li>' +
									  '<li><a href="'+path+'internal/rulesmgmt/codes/searchrevenue.html">Revenue</a></li>' +
									  '<li><a href="'+path+'internal/rulesmgmt/codes/searchmodifier.html">Modifier</a></li>' +
									  '<li><a href="'+path+'internal/rulesmgmt/codes/modifierGroup.html">Modifier Group</a></li>' +
									  '<li><a href="'+path+'internal/rulesmgmt/codes/searchrbrvs.html">RBRVS</a></li>' +
									'</ul>' +
								'</li>' +
								'<li><a href="'+path+'internal/rulesmgmt/searchtext.html">Text Management</a></li>' +
								'<li><a href="'+path+'internal/rulesmgmt/eventtxt.html">Event Text Management</a></li>' +
								'<li><a href="'+path+'internal/rulesmgmt/searchmap.html">Map Definition</a></li>' +
								'<li><a href="'+path+'internal/rulesmgmt/searchcustomer.html">Customer Management</a></li>' +
								'<li><a href="'+path+'internal/rulesmgmt/searchlob.html">Line of Business</a></li>' +
								'<li><a href="'+path+'internal/rulesmgmt/searchnetworkmco.html">Network</a></li>' +
								'<li><a href="'+path+'internal/rulesmgmt/searchndccode.html">National Drug Code</a></li>' +
								'<li><a href="'+path+'internal/rulesmgmt/searchdea.html">Drug Enforcement Agency</a></li>' +
							   '</ul>' +
							'</li>' +
							'<li><a href="'+path+'internal/benefits/landing.html">Benefits</a>' +
							 '<ul>' +
								'<li><a href="'+path+'internal/benefits/lobbenefitplanhierarchy.html">Plan Hierarchy</a></li>' +
								'<li><a href="'+path+'internal/benefits/benefitplan.html">Benefit Plan</a></li>' +
								//'<li><a href="'+path+'internal/benefits/carryOverAccumulator.html">Carryover Accumulator</a></li>' +
							'</ul>' +
						  '</li>' +
						  '<li><a href="'+path+'internal/infoanalysis/landing.html">Info Analysis</a>' +
									 '<ul>' +
//										'<li><a href="'+path+'internal/infoanalysis/performancemetrics.html">Performance Metrics</a></li>' +
//										'<li><a >Reporting</a>' +
//										   '<ul>' +
//											  '<li><a href="'+path+'internal/infoanalysis/executivereports.html">Executive Reports</a></li>' +
//											  '<li><a href="'+path+'internal/infoanalysis/adhoc.html">Adhoc</a></li>' +
//											  '<li><a href="'+path+'internal/infoanalysis/managementreporting.html">Management Reporting</a></li>' +
//											  '<li><a href="'+path+'internal/infoanalysis/functionalfolders.html">Functional Folders</a></li>' +
//											  '<li><a href="'+path+'internal/infoanalysis/medicaidreferences.html">Medicaid References</a></li>' +
//											  '<li><a href="'+path+'internal/infoanalysis/pbm.html">PBM</a></li>' +
//										   '</ul>' +
//										'</li>' +
//										'<li><a href="'+path+'internal/infoanalysis/performancemanagement.html">Performance Management</a></li>' +
//										'<li><a href="'+path+'internal/infoanalysis/statisticalanalysis.html">Statistical Analysis</a></li>' +
//										'<li><a href="'+path+'internal/infoanalysis/riskanalysis.html">Risk Analysis</a></li>' +
//										'<li><a href="'+path+'internal/infoanalysis/surs.html">SURS / Fraud and Abuse</a></li>' +
//										'<li><a href="'+path+'internal/infoanalysis/hcpm.html">Healthcare Performance Measures</a></li>' +
//                     				    '<li><a href="'+path+'internal/infoanalysis/healthanalytics.html">Health Analytics Home</a></li>' +                     
                     					'<li><a href="http://entbidemo01/cognos8/cgi-bin/cognosisapi.dll?b_action=xts.run&m=portal/report-viewer.xts&ui.action=run&ui.object=%2fcontent%2fpackage%5b%40name%3d%27HealthSPOTLIGHT%27%5d%2ffolder%5b%40name%3d%27EFADS%20Home%20Pages%27%5d%2freport%5b%40name%3d%27EFADS%20Home%27%5d&ui.backURL=%2fcognos8%2fcgi-bin%2fcognosisapi.dll%3fb_action%3dxts.run%26m%3dportal%2fcc.xts%26m_folder%3di793CF257C82741298D07731C079F80ED&CAMPassword=cognos&CAMUsername=rprater" target="_new">Fraud & Abuse / SURS (EFADS)</a></li>' +
									    '<li><a href="http://entbidemo01/cognos8/cgi-bin/cognos.cgi?b_action=xts.run&m=portal/report-viewer.xts&ui.action=run&ui.object=%2fcontent%2fpackage%5b%40name%3d%27Enterprise%20MARS%20Phase%20II%27%5d%2freport%5b%40name%3d%27MR-HOME-PAGE%27%5d&cv.ccurl=1&ui.backURL=%2fcognos8%2fcgi-bin%2fcognos.cgi%3fb_action%3dxts.run%26m%3dportal%2fcc.xts%26m_folder%3di6C0BE4E9AFE64D23875C24C1AC200361&CAMPassword=cognos&CAMUsername=rprater" target="_blank">Management Reporting (EMAR)</a></li>' +
                      					'<li><a href="http://entbidemo01/cognos8/cgi-bin/cognos.cgi?b_action=xts.run&m=portal/report-viewer.xts&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27EQR%27%5d%2ffolder%5b%40name%3d%27Operational%20Reporting%20Repository%27%5d%2ffolder%5b%40name%3d%27Home%20Pages%27%5d%2freport%5b%40name%3d%272.%20EOR-Home%20Page%27%5d&cv.ccurl=1&ui.backURL=%2fcognos8%2fcgi-bin%2fcognos.cgi%3fb_action%3dxts.run%26m%3dportal%2fcc.xts%26m_folder%3di8FF2DD616F5B467B8B532B69B9E8F48F&CAMPassword=cognos&CAMUsername=RPrater">Operational Reporting (EOR)</a></li>' +
//                      					'<li><a href="'+path+'internal/infoanalysis/riskanalysis.html">Risk Analysis</a></li>' +
//                      					'<li><a href="'+path+'internal/infoanalysis/hedisreporting.html">HEDIS Reporting</a></li>' +
//                      					'<li><a href="'+path+'internal/infoanalysis/statisticalanalysis.html">Statistical Analysis</a></li>' +
//                      					'<li><a href="http://entbidemo01/cognos8/cgi-bin/cognos.cgi?b_action=xts.run&m=portal/launch.xts&ui.tool=MetricStudio&ui.object=/content/package[@name=\'Init%20MM\']&ui.action=new" target="_blank" style="font-family:Arial;font-weight:bold;font-size:8pt;" class="hy">Metrics Home</A></li>' +									  
										'<li><a >Ad Hoc</a>' +
										   '<ul>' +
											  '<li><a href="">Reports Studio</a></li>' +
											  '<li><a href="">Query Studio</a></li>' +
											  '<li><a href="'+path+'internal/infoanalysis/defined.html">User Defined Tables</a></li>' +
											'</ul>' +
										'</li>' +
									'</ul>' +
								  '</li>' +
								  '<li><a href="'+path+'internal/financial/landing.html">Financial</a>' +
									 '<ul>' +
										'<li><a>Third Party Liability</a>' +
													'<ul>' +
												'<li><a href="'+path+'internal/financial/tpl/searchtplbilling.html">Billing</a>' +
														'<li><a href="'+path+'internal/financial/tpl/searchcarrier.html">Carrier</a>' +
														'<li><a href="'+path+'internal/financial/tpl/searchtplpolicy.html">Policy</a>' +
														'<li><a href="'+path+'internal/financial/tpl/searchtplrecovery.html">Recovery Case</a>' +
														'<li><a href="'+path+'internal/financial/tpl/masschange.html">Mass Change Request</a>' +
														'<li><a href="'+path+'internal/financial/tpl/hippdetermination.html">HIPP Information</a>' +
													'</ul>' +
												'</li>' +
												'<li><a >Accounts Payable</a>' +
													'<ul>' +
												'<li><a href="'+path+'internal/financial/ar/financialpayoutsearch.html">Payout</a>' +
												'<li><a href="'+path+'internal/financial/ar/financialpayoutapprovesearch.html">Payout Approval</a>' +
													'</ul>' +
												'</li>' +
												'<li><a href="'+path+'internal/financial/receipt.html">Financial Receipt</a>' +
												//	'<ul>' +
												//no need to have a branch of one - moved link up'<li><a href="'+path+'internal/financial/receipt.html">Receipts</a>' +
												//'<li><a href="'+path+'internal/financial/receiptapproval.html">Receipt Approval</a>' +
												//	'</ul>' +
												'</li>' +
										'<li><a href="'+path+'internal/financial/ar/financialsearch.html">Accounts Receivable</a>' +
										'<li><a href="'+path+'internal/financial/ar/financialentitysearch.html">Financial Accounting Entity</a>' +
										'<li><a href="'+path+'internal/financial/budgetlinesearch.html">Budget Inquiry</a></li>' +
										'<li><a href="'+path+'internal/financial/bank.html">Financial Bank Account</a>' +
										//		'<li><a>County Billing</a>' +
										//			'<ul>' +
										//			//'<li><a href="'+path+'internal/financial/countybilling/legalliability.html">Legal Liability</a>' +
										//			'<li><a href="'+path+'internal/financial/countybilling/SearchCountyBillingDisputes.html">County Bill Invoices</a>' +
										//			'<li><a href="'+path+'internal/financial/countybilling/SearchOtherBillingUnitTransactions.html">Billing Unit Transactions</a>' +
										//			// remove LLC option per CR 907 Jean Beatty 12/12/2007
										//			//'<li><a href="'+path+'internal/financial/countybilling/llcForm.html">County LLC Form</a>' +
										//			'<li><a href="'+path+'internal/financial/countybilling/searchCountyInvoices.html">County Invoice Files</a>' +
										//			'</ul>' +
										//		'</li>' +
											 '</ul>' +
										  '</li>' +
//leave this hidden until eric ulberg is ready										 '<li><a href="'+path+'internal/mancare/landing.html">Managed Care</a>' +
//									 '<ul>' +
//											'<li><a href="'+path+'internal/mancare/masschangeselection.html">Mass Change Selection</a></li>' +
//											'<li><a href="'+path+'internal/mancare/autoassigned.html">Auto Assignment</a></li>' +
//										 '</ul>' +
//									'</li>' +
										 '<li><a href="'+path+'security/landing.html">My Account</a>' +
													'<ul>' +
														//'<li><a href="'+path+'security/editprofile.html">Edit Profile</a></li>' +
														 '<li><a href="'+path+'security/changepasswordInternal.html">Change Password</a></li>' +					 														// '<li><a href="'+path+'security/manageusers.html">Manage Users</a></li>' +
													'</ul>' +
										'</li>' +
										  '<li><a href="'+path+'security/manageusersLanding.html">Manage Users</a>' +
													'<ul>' + 
														 '<li><a href="'+path+'security/manageusers.html">Manage Users</a></li>' +
													'</ul>' +
										'</li>' +
									   '</ul>' +
									'</div>' +
            '<div class="clear"></div>' +
				'<div><img src="'+ path +'images/x.gif" border="0" width="1" height="2" alt="" /></div>';
	
	switch (area) {
		case "General":
			document.write(nav_general);
			break;
		case "GeneralNo":
			document.write(nav_generalNo);
			break;
			case "GeneralM":
			document.write(nav_generalm);
			break;
		case "Provider":
			document.write(nav_provider);
			break;
		case "Member":
			document.write(nav_member);
			break;
		case "Internal":
			document.write(nav_internal);
			break;
		default:
			document.write(nav_general);
			break;
	}
}