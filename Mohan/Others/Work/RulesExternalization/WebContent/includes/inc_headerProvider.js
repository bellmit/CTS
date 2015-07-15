// general header
function writeHeaderProvider(path){
	um.baseSRC = path+'images/';
	theHeader = '<div id="head">' +
					'<div id="headImg"><a href="'+path+'index.html"><img src="'+path+'images/' + appLogo +'" /></a></div>' +
					'<div id="headTxt">' +
						'<div id="headLine2"><a href="'+path+'internal/inbox/provider_contact.html">Contact Us</a>&nbsp;&#124;&nbsp;<a href="javascript:void(0)">Help</a>&nbsp;&#124;&nbsp;' +
						 '<form name="searchForm" action="">' +
							'<input type="text" size="15" accesskey="E" />&nbsp;&nbsp;<a href="javascript:void(0)">S<span class="accesskey">e</span>arch</a>' +
						 '</form>&nbsp;&#124;&nbsp;<a href="'+path+'logout.html">Logout</a>' +
						'</div>' +
					'</div>' +
				'</div>' +
				'<div id="nav">' +
					'<ul id="udm" class="udm">' +
						'<li><a id="menuBarTitle" href="'+path+'internal/inbox/provider_inbox.html">Provider</a></li>' +
						'<li><a href="'+path+'providers/memberProvLanding.html">Member</a>' +
							'<ul>' +
								'<li><a href="'+path+'providers/checkeligibility.html">Check Eligibility</a></li>' +
								'<li><a href="'+path+'members/presumptiveeligibility/presumptiveeligibilitysearch.html">Enter Presumptive Eligibility</a></li>' +
								'<li><a >Batch Eligibility</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+path+'providers/claims/authLanding.html">Authorizations</a>' +
							'<ul>' +
								'<li><a href="'+path+'providers/claims/newserviceauth.html">Submit Authorization Request</a></li>' +
								'<li><a href="'+path+'providers/claims/newHcbcServiceAuth.html">Submit HCBC Authorization Request</a></li>' +
								'<li><a href="'+path+'providers/claims/searchserviceauth.html">View / Edit Authorization Request</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+path+'providers/claims/index.html">Claims</a>' +
							'<ul>' +
								'<li><a>Create Claims</a>' +
									'<ul>' +
										'<li><a href="'+path+'providers/claims/newclaimpro.html">Create Professional Claim</a></li>' +
										'<li><a href="'+path+'providers/claims/newclaiminst.html">Create Institutional Claim</a></li>' +
										'<li><a href="'+path+'providers/claims/newclaimdental.html">Create Dental Claim</a></li>' +
										'<li><a href="'+ path +'providers/claims/optionsClaim.html">Create Options Claim</a></li>' +
										'<li><a href="'+path+'providers/claims/searchtemplate_createfrom.html">Create Claim from Template</a></li>' +
										'<li><a href="'+path+'providers/claims/searchclaim_submitted.html">Create Claim from Processed Claim</a></li>' +
									'</ul>' +
								'</li>' +
								'<li><a>Manage Claims</a>' +
									'<ul>' +
										'<li><a href="'+path+'providers/claims/searchclaim_editdelete.html">Edit / Delete Saved Claim</a></li>' +
										'<li><a href="'+path+'providers/claims/claimSearch_viewSubmitted.html">View Submitted Claims</a></li>' +
									'</ul>' +
								'</li>' +
								'<li><a>Create Templates</a>' +
									'<ul>' +
										'<li><a href="'+path+'providers/claims/createtemplatename.html?action=profTemp&">Create Professional Template</a></li>' +
										'<li><a href="'+path+'providers/claims/createtemplatename.html?action=instTemp&">Create Institutional Template</a></li>' +
										'<li><a href="'+path+'providers/claims/createtemplatename.html?action=dentalTemp&">Create Dental Template</a></li>' +
									'</ul>' +
								'</li>' +
								'<li><a>Manage Templates</a>' +
									'<ul>' +
										'<li><a href="'+path+'providers/claims/searchtemplate_editdelete.html">View / Edit / Delete Template</a></li>' +
									'</ul>' +
								'</li>' +
								'<li><a href="'+path+'providers/claims/claimStatusInquiry.html">Claim Status Inquiry</a></li>' +
								'<li><a href="'+path+'providers/claims/paymentInquiry.html">Payment Inquiry</a></li>' +
								//'<li><a href="'+path+'providers/claims/searchexceptioncode.html">Check Exception Code</a></li>' +
								//'<li><a href="'+path+'providers/claims/searchprocedurecode.html">Check Procedure Code</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+ path +'providers/edi/landing.html">EDI</a>' +
							'<ul>' +
								'<li><a href="'+ path +'providers/edi/submitX12BatchFile.html">Upload X12</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+path+'providers/nursinghome/nursingFacilityLanding.html">Nursing Facilities</a>' +
							'<ul>' +
								'<li><a href="'+path+'providers/nursinghome/censusLanding.html">Census</a>' +
								'<li><a>Medicaid Cost Report</a>' +
									'<ul>' +
										'<li><a href="'+path+'providers/nursinghome/newMedicaidReport/instructions.html?action=Instructions&">Create Report</a></li>' +
										'<li><a href="'+path+'providers/nursinghome/searchMedicaidAnnualReport.html?action=update&">Update Saved Reports</a></li>' +
										'<li><a href="'+path+'providers/nursinghome/searchMedicaidAnnualReport.html?action=view&">View Reports</a></li>' +
									'</ul>' +
								'</li>' +
								/*'<li><a>Home Office Cost Report</a>' +
									'<ul>' +
										'<li><a href="'+path+'providers/nursinghome/homeOfficeReport/instructions.html?action=Instructions&">Create Report</a></li>' +
										'<li><a href="'+path+'providers/nursinghome/searchHomeOfficeReport.html">View/Update Reports</a></li>' +
									'</ul>' +
								'</li>' +*/
							'</ul>' +
						'</li>' +
						/* county users will no longer be signing on as providers 01/15/2007
						'<li><a href="'+path+'providers/financialLanding.html">Financial</a>' +
							'<ul>' +
								'<li><a href="'+path+'internal/financial/countybilling/limitedliability.html">County User Limited Liability</a>' +
							'</ul>' +
						'</li>' +*/
						'<li><a href="'+path+'providers/provAccountLanding.html">My Account</a>' +
							'<ul>' +
								 '<li><a href="'+path+'security/editprofile.html">Edit Profile</a></li>' +
								 '<li><a href="'+path+'security/changepassword.html">Change Password</a></li>' +
								 '<li><a href="'+path+'security/manageusers.html">Manage Users</a></li>' +
								 '<li><a href="'+path+'security/providerchangepin.html">Change PIN</a></li>' +
							'</ul>' +
						'</li>' +
					'</ul>' +
				'</div>' +
				'<br clear="all" />';
	document.write(theHeader);
}