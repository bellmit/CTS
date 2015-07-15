// general header
function writeHeaderExample(where){
	um.baseSRC = where+'images/';
	theHeader = '<div id="head">' +
					'<div id="headImg"><a href="'+where+'index.html"><img src="'+where+'images/header_internal.gif" border="0" alt="ACS - Health Enterprise Portal" /></a></div>' +
					'<div id="headTxt">' +
						'<div id="headLine1"><a>Administration</a>&nbsp;&nbsp;&#124;&nbsp;&nbsp;'+ today + '</div>' +
						'<div id="headLine2"><a>Help</a>&nbsp;&nbsp;&#124;&nbsp;&nbsp;' +
						 '<form name="searchForm" action="">' +
							'<input type="text" size="15" accesskey="E" />&nbsp;&nbsp;<a>S<span class="accesskey">e</span>arch</a>' +
						 '</form>&nbsp;&nbsp;&#124;&nbsp;&nbsp;<a>Logout</a>' +
						'</div>' +
					'</div>' +
				'</div>' +
				'<div id="nav">' +
					'<ul id="udm" class="udm">' +
						'<li><a id="menuBarTitle" onclick="document.location.href=\''+where+'templates/portlet.html\'">Examples</a></li>' +
						'<li><a>Examples</a>' +
							'<ul>' +
								'<li><a href="'+where+'templates/portlet.html">Portlet</a></li>' +
								'<li><a href="'+where+'templates/fieldset.html">Fieldsets</a></li>' +
								'<li><a href="'+where+'templates/tabs.html">Tabs</a></li>' +
								'<li><a href="'+where+'templates/common.html">Common Data</a></li>' +
								'<li><a href="'+where+'templates/tables.html">Tables</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a>Lorem</a>' +
							'<ul>' +
								'<li><a>How to Apply</a></li>' +
								'<li><a>Benefits Overview</a></li>' +
								'<li><a>General Eligibility Rules</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a>Ipsum</a>' +
							'<ul>' +
								'<li><a>Enrollment</a></li>' +
								'<li><a>Provider Manuals</a></li>' +
								'<li><a>Benefits Overview</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a>Dolsor</a>' +
							'<ul>' +
								'<li><a>Banner Messages</a></li>' +
								'<li><a>Provider Manuals</a></li>' +
								'<li><a>Documents &amp; Forms</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a>Imet</a>' +
							'<ul>' +
								'<li><a>Find a Health Care Provider</a></li>' +
								'<li><a>Find a State Office</a></li>' +
							'</ul>' +
						'</li>' +
						'<li><a>Lorem Ipsum</a>' +
							'<ul>' +
								'<li><a>Edit Profile</a></li>' +
								'<li><a>Change Password</a></li>' +
								'<li><a>Security Admin</a></li>' +
							'</ul>' +
						'</li>' +
						//'<li><a onclick="document.location.href=\''+where+'internal/index.html\'">Internal (test only)</a></li>' +
					'</ul>' +
				'</div>' +
				'<div class="clear"></div>' +
				'<div><img src="'+where+'images/x.gif" border="0" width="1" height="2" alt="" /></div>';
	document.write(theHeader);
}