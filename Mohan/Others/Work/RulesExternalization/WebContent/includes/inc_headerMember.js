// general header
function writeHeaderMember(path){
	um.baseSRC = path+'images/';
	theHeader = '<div id="head">' +
					'<div id="headImg"><a href="'+path+'index.html"><img src="'+path+'images/' + appLogo +'" /></a></div>' +
					'<div id="headTxt">' +
						'<div id="headLine2"><a href="http://www.dhhs.nh.gov/dhhs/contact+directory/default.htm" target="_blank">Contact Us</a>&nbsp;&#124;&nbsp;<a href="javascript:void(0)">Help</a>&nbsp;&#124;&nbsp;' +
						 '<form name="searchForm" action="">' +
							'<input type="text" size="15" accesskey="E" />&nbsp;&nbsp;<a href="javascript:void(0)">S<span class="accesskey">e</span>arch</a>' +
						 '</form>&nbsp;&#124;&nbsp;<a href="'+path+'logout.html">Logout</a>' +
						'</div>' +
					'</div>' +
				'</div>' +
				'<div id="nav">' +
					'<ul id="udm" class="udm">' +
						'<li><a id="menuBarTitle" onclick="document.location.href=\''+path+'members/index2.html\'">Member</a></li>' +
						'<li><a href="'+ path +'members/summaryOfCareLanding.html">Summary of Care</a>' +
							'<ul>' +
			              '<li><a href="'+path+'members/displayclaims.html">Display Claims</a></li>' +
			              '<li><a href="'+path+'members/providersvisited.html">Display Doctors / Providers Visited</a></li>' +
			              '<li style="display:' + ( state == "OH" ? "block" : "none") + '"><a href="'+path+'internal/member/healthReports.html">Health Reports</a></li>' +
							'</ul>' + 
						'</li>' + 												
						'<li><a href="'+ path +'members/requestsLanding.html">Requests</a>' +
							'<ul>' +
				              '<li><a href="'+path+'directories/findprovider.html">Find a Health Care Provider</a></li>' +
				              // NH does not have this'<li><a href="'+path+'members/requestswipecard.html">Request Swipe Card Replacement</a></li>' +
							'</ul>' + 
						'</li>' +
						'<li><a href="'+ path +'members/myAccountLanding.html">My Account</a>' +
						'<ul>' +
							 '<li><a href="'+path+'security/editprofile.html">Edit Profile</a></li>' +
							 '<li><a href="'+path+'security/changepin.html">Change PIN</a></li>' +
						'</ul>' +
					'</li>' +
					'</ul>' +
				'</div>' +
				'<br clear="all" />';
	document.write(theHeader);
}