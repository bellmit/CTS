// general header
function writeHeaderGeneral(where){
	um.baseSRC = where+'images/';
	theHeader = '<div id="head">' +
					'<div id="headImg"><a href="'+where+'index.html"><img src="'+where+'images/' + appLogo +'" /></a></div>' +
					'<div id="headTxt">' +
						'<div id="headLine2"><a href="javascript:void(0)">Contact Us</a>&nbsp;&#124;&nbsp;<a href="javascript:void(0)">Help</a>&nbsp;&#124;&nbsp;' +
						 '<form name="searchForm" action="">' +
							'<input type="text" size="15" accesskey="E" />&nbsp;&nbsp;<a href="javascript:void(0)">S<span class="accesskey">e</span>arch</a>' +
						 '</form>&nbsp;&#124;&nbsp;<a href="javascript:void(0)">Login</a>' +
						'</div>' +
					'</div>' +
				'</div>' +
				'<div id="nav">' +
					'<ul id="udm" class="udm">' +
						'<li><a id="menuBarTitle" onclick="document.location.href=\''+where+'index.html\'">Welcome</a></li>' +
						'<li><a href="'+where+'program/landing.html">Program</a>' +
							'<ul>' +
								'<li><a href="http://www.dhhs.nh.gov/dhhs/medicaidprogram/default.htm" target="_blank">Medicaid Information</a></li>' +
								'<li><a href="http://www.dhhs.nh.gov/dhhs/medassistelig/eligibility/children-under19-medical.htm" target="_blank">Children\'s Health Plan</a></li>' +
								'<li><a href="http://www.dhhs.nh.gov/DHHS/Medicaidprogram/pbm.htm" target="_blank">Pharmacy Services</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+where+'members/memberLandingPublic.html">Member</a>' +
							'<ul>' +
								'<li><a href="'+where+'members/howtoapply.html">How to Apply</a></li>' +
								'<li><a href="'+where+'members/benefitsoverview.html">Benefits Overview</a></li>' +
								'<li><a href="'+where+'members/generaleligibilityrules.html">General Eligibility Rules</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+where+'providers/provLandingPublic.html">Provider</a>' +
							'<ul>' +
								'<li><a href="'+where+'providers/enrollment/start.html">Enrollment</a></li>' +
								'<li><a href="'+where+'providers/providermanuals.html">Provider Manuals</a></li>' +
								'<li><a href="'+where+'help/faq.html">Provider FAQs</a></li>' +
								'<li><a href="'+where+'providers/billingmanuals.html">Billing Manuals</a></li>' +								
								'<li><a href="'+where+'documentation/bannermessages.html">Messages & Announcements</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+where+'documentation/landing.html">Documentation</a>' +
							'<ul>' +
								'<li><a href="'+where+'documentation/documentsandforms.html">Documents &amp; Forms</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a href="'+where+'directories/landing.html">Directories</a>' +
							'<ul>' +
								'<li><a href="'+where+'directories/findprovider.html">Find a Health Care Provider</a></li>' +
								'<li><a href="'+where+'directories/findstateoffice.html">Find a State Office</a></li>' +
							'</ul>' +
						'</li>' +
					'</ul>' +
				'</div>' +
				'<div class="clear"></div>' +
				'<div><img src="'+where+'images/x.gif" border="0" width="1" height="2" alt="" /></div>';
	document.write(theHeader);
}