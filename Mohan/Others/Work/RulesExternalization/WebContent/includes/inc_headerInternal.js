// internal header
function writeHeaderInternal(path) {
	um.baseSRC = path+'images/';
   	theHeader = '<div id="head">' +
					'<div id="headImg"><a href="'+path+'index.html"><img src="'+path+'images/' + appLogo +'" alt="' + appName +'" border="0" /></a></div>' +
					'<div id="headTxt">' +
						'<div id="headLine2"><a href="javascript:void(0)">Contact Us</a>&nbsp;&#124;&nbsp;<a href="javascript:void(0)">Help</a>&nbsp;&#124;&nbsp;' +
							'<form name="searchForm" action="">' +
								'<input type="text" size="15" accesskey="E" />&nbsp;&nbsp;<a href="javascript:void(0)">S<span class="accesskey">e</span>arch</a>' +
							'</form>&nbsp;&#124;&nbsp;<a href="'+path+'logout.html">Logout</a>' +
						'</div>' +
					'</div>' +
				'</div>' +
				'<div id="nav">' +
					'<ul id="udm" class="udm">' +
						'<li><a href="'+path+'internal/projectcontrol/landing.html">Project Control</a>' +
							'<ul>' +
								'<li><a href="'+path+'internal/projectcontrol/projectrepository.html">Project Repository</a></li>' +
								'<li><a href="'+path+'internal/projectcontrol/issuemanagement.html">Issue Management</a></li>' +
								'<li><a href="'+path+'internal/projectcontrol/performancemonitoring.html">Performance Monitoring</a></li>' +
								'<li><a href="'+path+'internal/projectcontrol/performancemanagement.html">Performance Management</a></li>' +
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
								'<li><a href="'+path+'internal/projectcontrol/globalAuditSearch.html">Global Audit Search</a></li>' +
								'<li><a href="'+path+'internal/globalCaseSearch.html">Global Case Search</a></li>' +
								'<li><a href="#">Document Finder aka IntraViewer</a></li>' +
							'</ul>' +
						'</li>' +
                  '<li><a href="'+ path +'internal/member/landing.html">Member</a>' +
									'<ul>' +
										'<li><a href="'+ path +'internal/member/membersearch.html?action=memberdetails.html&">Member Inquiry</a></li>' +
										'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=uncaneligibility.html&"">Unconcatenated Eligibility</a></li>' +
										'<li><a>Member Maintenance</a>' +
											'<ul>' +
		                      			'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=memberdetails.html&">Member Details</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=eligibility.html&">Eligibility Spans</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=pcp.html&">Primary Care Provider</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=enrollmentspans.html&">Enrollment Spans</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=ltc.html&">Nursing Facility Spans</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=lockin.html&">Lock-in Spans</a></li>' +
												//'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=medicarespans.html&">Medicare Spans</a></li>' +
												'<li><a href="'+path+'internal/financial/tpl/edittplpolicy.html">TPL Information</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=absentparent.html&">Absent Parent Info</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/caseinfo.html">Case Info</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/alternateId.html">Alt ID Table Maintenance</a></li>' +
												'<li><a href="'+path+'internal/member/maintenance/authorizedRepSearch.html">Member Representatives</a></li>' +
											'</ul>' +
										'</li>' +
									'<li><a href="'+path+'internal/member/addNewMember.html">Add New Members</a></li>' +
									'<li><a href="'+path+'internal/member/membersearch.html?action=swipecard/requestswipecard.html&">Request Member Cards</a></li>' +
									'<li><a href="'+path+'internal/member/suspenseprocessing.html">Process Suspended Transactions</a></li>' +
									// '<li><a href="'+path+'internal/member/epsdtsearch.html">EPSDT</a></li>' +
									//'<li><a>Buy-in Request</a></li>' +
									'<li><a href="'+path+'internal/member/membersearch.html?action=eligibilityquickview.html&">Eligibility Quick View</a></li>' +
									//'<li><a href="'+path+'internal/member/liabilities.html">Other Member Liabilities</a></li>' +
									//'<li><a href="'+path+'internal/member/premium.html">Premium</a></li>' +
									'<li><a href="'+path+'internal/member/membersearch.html?action=maintainappeals.html&">Appeals Inquiry</a></li>' +
									'<li><a href="'+path+'internal/contactManagement/casenew.html">Add New Appeals</a></li>' +
									'<li><a href="'+path+'internal/member/memberretrieve.html">Retrieve Member from Archive</a></li>' +
									'<li><a href="'+path+'internal/member/maintenance/maintainsearch.html?action=msq.html&">Medical Service Questionnaires</a></li>' +
									'<li><a href="'+path+'internal/member/maintenance/mergeduplicates.html">Suspect Duplicate Merge</a></li>' +
									'<li><a href="'+path+'internal/member/maintenance/unmergeClaims.html">Transfer Claims </a></li>' +
									//'<li><a>Unmerge</a>' +
									 //'<ul>' +	
										'<li><a href="'+path+'internal/member/maintenance/unmergeAltId.html">Unmerge Alternate ID Transfer</a></li>' +
										//	'<li><a href="'+path+'internal/member/maintenance/unmergeClaims.html">Claims Transfer</a></li>' +
										//'</ul>' +
									'</li>' +
									'<li style="display:' + ( state == "OH" ? "block" : "none") + '"><a href="'+path+'internal/member/healthReports.html">Health Reports</a></li>' +
								'</ul>' +
							'</li>' +
						  '<li><a href="'+path+'internal/provider/landing.html">Provider</a>' +
							 '<ul>' +
							    '<li><a>Providers</a>'+
							    	'<ul>' +
										'<li><a href="'+ path +'internal/provider/providermaintenancesearch.html">Provider Maintenance</a></li>' +
										'<li><a href="'+ path +'internal/provider/providersearch.html">Provider Inquiry</a></li>' +
										'<li><a href="'+ path +'internal/provider/enrollmenttrackingsearch.html">Provider Application Maintenance</a></li>' +
									'</ul>' +
								'</li>' +		
								'<li><a href="'+ path +'internal/provider/typespecialty.html">Provider Type / Specialty Maintenance</a></li>' +						
							    '<li><a>Trading Partners</a>'+
							    	'<ul>' +								
										'<li><a href="'+ path +'internal/provider/tradingpartner/tradingpartnermaintenance.html">Trading Partner Maintenance</a></li>' +
										'<li><a href="'+ path +'internal/provider/tradingpartner/tradingpartnerinquiry.html">Trading Partner Inquiry</a></li>' +
										'<li><a href="'+ path +'internal/provider/tradingappmaintenance.html">Trading Partner App Maintenance</a></li>' +										
									'</ul>' +
								'</li>' +			
							//	'<li><a href="'+ path +'internal/provider/carriersearch.html">Medicare Carrier Maintenance</a></li>' +
							//	'<li><a href="'+ path +'internal/provider/ssntaxmaintenancesearch.html">SSN/Tax ID Maintenance</a></li>' +
							//	'<li><a href="'+ path +'internal/provider/enrollmentevent.html">Enrollment Events</a></li>' +
							//	'<li><a href="'+ path +'internal/provider/applicationpackagesearch.html">Application Package Search</a></li>' +
								'<li><a>ARS - Census</a>'+
									'<ul>'+
										'<li><a href="'+path+'internal/provider/census/enterCensusData.html">Enter Census Data</a></li>' +
										'<li><a href="'+path+'internal/provider/census/searchMDS.html">Search MDS Data</a></li>' +
										'<li><a href="'+path+'internal/provider/census/searchFacility.html">Search Facility</a></li>' +
										'<li><a href="'+path+'internal/provider/census/searchResidentAcuity.html">Search Acuity by Resident</a></li>' +
									'</ul>' +
	            		   '</li>' +	
								'<li><a>ARS - Cost Report</a>'+
									'<ul>'+
										'<li><a href="'+path+'internal/provider/costAcuity/searchCostReport.html">Search Cost Report</a></li>' +
										'<li><a href="'+path+'internal/provider/costAcuity/auditorWorkbook.html">Auditor Workbook</a></li>' +
									'</ul>' +
	            		  '</li>' +
	            		  /*'<li><a>ARS - Home Office Cost Report</a>'+
									'<ul>'+
										'<li><a href="'+path+'internal/provider/homeOfficeCostReport/searchCostReport.html">Search Cost Report</a></li>' +
										'<li><a href="'+path+'internal/provider/homeOfficeCostReport/viewHomeOfficeReport.html">View/Update Home Office Cost Report</a></li>' +
										
									'</ul>' +
	            		  '</li>' +*/
								'<li><a>ARS - MQIP</a>' +
									'<ul>'+
										'<li><a href="'+path+'internal/provider/mqip/manageMQIP.html">MQIP</a></li>' +
	 									'<li><a href="'+path+'internal/provider/mqip/manageNfqa.html">Manage NFQA Tax Reports</a></li>' +
	 								'</ul>' +
	 							'</li>' +
								'<li><a>ARS - Performance</a>' +
									'<ul>'+
	 									'<li><a href="'+path+'internal/provider/performance/performance.html">Performance Measure</a></li>' +
	 									'<li><a href="'+path+'internal/provider/performance/weightPerformanceMeasures.html">Weight Performance Measures</a></li>' +
	 									'<li><a href="'+path+'internal/provider/performance/enterPerformanceData.html">Enter Performance Data</a></li>' +
	 									'<li><a href="'+path+'internal/provider/performance/performanceMultiplier.html">Performance Multiplier</a></li>' +
	 									'<li><a href="'+path+'internal/provider/performance/performanceScore.html">Search Facility Performance Score</a></li>' +
	 								'</ul>' +
            		      '</li>' +
								'<li><a>ARS - Rate Setting</a>'+
	 								'<ul>'+
	 									'<li><a href="'+path+'internal/provider/rateSetting/inflation.html">Inflation Factor</a></li>' +
	 									'<li><a href="'+path+'internal/provider/rateSetting/initiateRateSetting.html">Initiate Rate Setting Process</a></li>' +
	 									'<li><a href="'+path+'internal/provider/rateSetting/maintainCostComponentParameters.html">Maintain Cost Component Parameters</a></li>' +
	 									'<li><a href="'+path+'internal/provider/rateSetting/managebudgetNeutral.html">Manage Budget Neutral Calculations</a></li>' +
	 									//'<li><a href="'+path+'internal/provider/rateSetting/usualCustomary.html">Usual & Customary Charge</a></li>' +
	 									'<li><a href="'+path+'internal/provider/rateSetting/searchFacilityRateData.html">Search Facility Rate Data</a></li>' +
	 								'</ul>' +
            		      '</li>' +
            		      '<li><a>ARS - RUG</a>' +
            		      	'<ul>'+
	 									'<li><a href="'+path+'internal/provider/rug/rug.html">RUG</a></li>' +
	 									'<li><a href="'+path+'internal/provider/rug/rugWeightInput.html">RUG Weight Input</a></li>' +
	 									'<li><a href="'+path+'internal/provider/rug/approveRugWeightInput.html">Approve RUG Weights</a></li>' +
	 								'</ul>' +
	 							'</li>' +
							  '<li><a href="'+path+'internal/provider/providerreportrequest.html">Provider Mailing Labels</a></li>' +	            		    
							  '<li><a >Provider Appeals</a>' +
							 		'<ul>'+
	 									'<li><a href="'+path+'internal/provider/providerappealsearch.html">Search Appeals</a></li>' +
	 									'<li><a href="'+path+'internal/contactManagement/casenew.html">New Appeal</a></li>' +
	 								'</ul>' +
						      '</li>' +	    	            		    
                     '</ul>' +
                  '</li>' +
                  '<li><a href="'+ path +'internal/contactManagement/landing.html">Ct Mgmt</a>' +
                     '<ul>' +
								'<li><a href="'+ path +'internal/contactManagement/correspondenceSearch.html">Correspondence</a></li>' +
								'<li><a href="'+ path +'internal/contactManagement/entitySearch.html">Entity</a></li>' +
								'<li><a href="'+ path +'internal/contactManagement/searchCase.html">Case</a></li>' +
								'<li><a>Maintenance</a>'+
									'<ul>'+
										'<li><a href="'+ path +'internal/contactManagement/MaintainCategory.html">Category</a></li>' +
										'<li><a href="'+ path +'internal/contactManagement/maintainCallScript.html">Call Scripts</a></li>' +
										//'<li><a href="'+ path +'internal/contactManagement/maintainSubject.html">Subject</a></li>' +
										'<li><a href="'+ path +'internal/contactManagement/maintainTemplates.html">Templates</a></li>' +
										//'<li><a href="'+ path +'internal/contactManagement/maintainCaseDates.html">Case Dates</a></li>' +
										//'<li><a href="'+ path +'internal/contactManagement/maintainCaseSteps.html">Case Steps</a></li>' +
										'<li><a href="'+ path +'internal/contactManagement/maintainCaseType.html">Case Type</a></li>' +
										'<li><a href="'+ path +'internal/contactManagement/maintainCustomFields.html">Custom Fields</a></li>' +
									'</ul>' +
      		    			'</li>' +
      		    			'<li><a href="'+ path +'internal/contactManagement/notesSearch.html">Notes</a></li>' +
      		    			'<li><a href="' + path + 'internal/contactManagement/archiveCRSearch.html">Archive CR Search</a></li>' +
            		    			'<li><a href="' + path + 'internal/contactManagement/archiveCaseSearch.html">Archive Case Search</a></li>' +
         				'</ul>' +
         			'</li>' +
                  '<li><a href="'+path+'internal/claims/landing.html">Claims</a>' +
                     '<ul>' +
                        '<li><a href="'+path+'internal/claims/searchclaim.html">Entry</a></li>' +
                        '<li><a href="'+path+'internal/claims/searchbatch.html">Batch Control</a></li>' +
                        '<li><a href="'+path+'internal/claims/claimSearch.html">Inquiry</a></li>' +
                        '<li><a href="'+path+'internal/claims/searchcorrection.html">Correction</a></li>' +
                        '<li><a href="'+path+'internal/claims/searchmass.html">Mass Adjustments</a></li>' +
                        '<li><a href="'+ path +'internal/claims/massAdjustSelectClaims.html">Mass Adjustment Claim Selection</a></li>' +
                        '<li><a href="'+path+'internal/claims/searchsuspense.html">Suspense Release</a></li>' +
                        '<li><a href="'+path+'internal/claims/searchserviceauth.html">Service Authorization</a></li>' +
                        '<li><a href="'+path+'internal/claims/searchfinancialinquiry.html">Financial</a></li>' +
                        '<li><a href="'+path+'internal/claims/history.html">History Request</a></li>' +
                        '<li><a >Adjudication</a>' +
									'<ul>' +
	          				        '<li><a href="'+path+'internal/claims/cosassign.html">Category of Svc Assignment</a></li>' +
			                        '<li><a href="'+path+'internal/claims/claimtypeassign.html">Claim Type Assignment</a></li>' +
	                  				'<li><a href="'+path+'internal/claims/dupcheck.html">Duplicate Check</a></li>' +
	                  				'<li><a href="'+path+'internal/claims/searchfiscalpend.html">Fiscal Pend</a></li>' +
	                  				'<li><a href="'+path+'internal/claims/fundcode.html">Fund Code</a></li>' +
											'<li><a href="'+path+'internal/claims/tplmatrix.html">TPL Matrix</a></li>' +
									'</ul>' +
								'</li>' +
							'</li>' +
                     '</ul>' +
                  '</li>' +
                  '<li><a href="'+path+'internal/care/landing.html">EPSDT</a>' +
                     '<ul>' +
                        '<li><a href="'+path+'internal/care/epsdtsearch.html">EPSDT Details</a></li>' +
                        '<li><a href="'+path+'internal/care/periodicity.html">Periodicity Schedule</a></li>' +
                     '</ul>' +
                  '</li>' +

                  '<li><a href="'+path+'internal/rulesmgmt/landing.html">Rules Mgmt</a>' +
                     '<ul>' +
                        '<li><a href="'+path+'internal/rulesmgmt/searchclaimexception.html">Claim Exception</a></li>' +
                        '<li><a href="'+path+'internal/rulesmgmt/searchauthorizationexception.html">Service Authorization Exception</a></li>' +
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
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchgroupregion.html">ASC Group / ASC Region</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprovideridbenefitplanid.html">Institutional Provider ID / Network ID / Benefit Plan ID</a></li>' +
										'<li><a class="menuLabel">Procedure Code</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecos.html">COS</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodeplaceofservice.html">Place of Service</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodebenefitplanid.html">Benefit Plan ID</a></li>' +
										//'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodemodifierplaceofservice.html">Modifier / Place of Service</a></li>' +
										//'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodeproceduremodifier.html">Procedure Modifier</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodeproviderid.html">Provider ID / Network ID</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodeprovidertype.html">Provider Type</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedureproviderspecialties.html">Provider Specialty</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedurecodeprovidertaxonomy.html">Provider Taxonomy</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchprocedureprovideridbenefitplanid.html">Provider ID / Network ID / Benefit Plan ID</a></li>' +
										'<li><a class="menuLabel">Revenue Code</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchrevenuecodeprovidertype.html">Revenue Type / Provider Type</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchrevenuecodeprovideridregion.html">Revenue Type / Provider ID / Network ID / Region</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchrevenuecodebenefitplanid.html">Revenue Type / Benefit Plan ID</a></li>' +
										'<li><a href="'+path+'internal/rulesmgmt/rates/searchrevenuecodeproviderid.html">Revenue Type / Provider ID / Network ID</a></li>' +
								   '</ul>' +
								'</li>' +
								'<li><a>Rules Maintenance</a></li>' +
								'<li><a>Code Maintenance</a>' +
								   '<ul>' +
									  '<li><a href="'+path+'internal/rulesmgmt/codes/searchdiagnosis.html">Diagnosis</a></li>' +
									  '<li><a href="'+path+'internal/rulesmgmt/codes/searchdrg.html">DRG</a></li>' +
									  '<li><a href="'+path+'internal/rulesmgmt/codes/searchicd.html">Surgical Procedure</a></li>' +
									  '<li><a href="'+path+'internal/rulesmgmt/codes/searchprocedurecode.html">Procedure</a></li>' +
									  '<li><a href="'+path+'internal/rulesmgmt/codes/searchrevenue.html">Revenue</a></li>' +
									'</ul>' +
								'</li>' +
								'<li><a href="'+path+'internal/rulesmgmt/searchtext.html">Text Management</a></li>' +
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
								'<li><a href="'+path+'internal/benefits/plannavigator.html">Plan Navigator</a></li>' +
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
                      '<li><a href="'+path+'internal/infoanalysis/healthanalytics.html">Health Analytics Home</a></li>' +                     
                      '<li><a href="http://entbidev2/cognos8/cgi-bin/cognosisapi.dll?b_action=xts.run&m=portal/report-viewer.xts&ui.action=run&ui.object=%2fcontent%2fpackage%5b%40name%3d%27HealthSPOTLIGHT%27%5d%2ffolder%5b%40name%3d%27EFADS%20Home%20Pages%27%5d%2freport%5b%40name%3d%27EFADS%20Home%27%5d&ui.backURL=%2fcognos8%2fcgi-bin%2fcognosisapi.dll%3fb_action%3dxts.run%26m%3dportal%2fcc.xts%26m_folder%3di793CF257C82741298D07731C079F80ED&CAMPassword=cognos&CAMUsername=rprater" target="_new">Fraud & Abuse / SURS (EFADS)</a></li>' +
                      '<li><a href="'+path+'internal/infoanalysis/managementreporting.html">Management Reporting (EMAR)</a></li>' +
                      '<li><a href="'+path+'internal/infoanalysis/operationalreporting.html">Operational Reporting (EOR)</a></li>' +
                      '<li><a href="'+path+'internal/infoanalysis/riskanalysis.html">Risk Analysis</a></li>' +
                      '<li><a href="'+path+'internal/infoanalysis/hedisreporting.html">HEDIS Reporting</a></li>' +
                      '<li><a href="'+path+'internal/infoanalysis/statisticalanalysis.html">Statistical Analysis</a></li>' +
                      '<li><a href="'+path+'internal/infoanalysis/performancemetrics.html">Performance Metrics</a></li>' +
                      '<li><a href="http://entbidev2/cognos8/cgi-bin/cognos.cgi?b_action=xts.run&m=portal/launch.xts&ui.tool=QueryStudio&ui.object=/content/package[@name=\'HealthSPOTLIGHT\']&ui.action=new" target="_new">Adhoc</a></li>' +
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
												'<li><a >Financial Receipt</a>' +
													'<ul>' +
												'<li><a href="'+path+'internal/financial/receipt.html">Receipts</a>' +
												'<li><a href="'+path+'internal/financial/receiptapproval.html">Receipt Approval</a>' +
													'</ul>' +
												'</li>' +
										'<li><a href="'+path+'internal/financial/ar/financialsearch.html">Accounts Receivable</a>' +
										'<li><a href="'+path+'internal/financial/ar/financialentitysearch.html">Financial Accounting Entity</a>' +
										'<li><a href="'+path+'internal/financial/budgetlinesearch.html">Budget Inquiry</a></li>' +
										'<li><a href="'+path+'internal/financial/bank.html">Financial Bank Account</a>' +
										'<li><a>County Billing</a>' +
											'<ul>' +											
											'<li><a href="'+path+'internal/financial/countybilling/SearchCountyBillingDisputes.html">County Bill Disputes</a>' +
											'<li><a href="'+path+'internal/financial/countybilling/SearchOtherBillingUnitTransactions.html">Billing Unit Transactions</a>' +
											'<li><a href="'+path+'internal/financial/countybilling/llcForm.html">County LLC Form</a>' +
											'<li><a href="'+path+'internal/financial/countybilling/searchCountyInvoices.html">County Invoices</a>' +
											'</ul>' +
										'</li>' +
									 '</ul>' +
								  '</li>' +
								  '<li><a href="'+path+'security/landing.html">My Account</a>' +
											'<ul>' +
												 '<li><a href="'+path+'security/editprofile.html">Edit Profile</a></li>' +
												 '<li><a href="'+path+'security/changepassword.html">Change Password</a></li>' +							 
												 '<li><a href="'+path+'security/manageusers.html">Manage Users</a></li>' +
											'</ul>' +
										'</li>' +
							   '</ul>' +
									'</div>' +
            '<div class="clear"></div>' +
			'<div><img src="'+path+'images/x.gif" border="0" width="1" height="2" alt="" /></div>';
   document.write(theHeader);
}