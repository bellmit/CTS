<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ReassignCaseRecords"
	var="msg" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />

<SCRIPT type="text/javascript">
var thisForm = 'view<portlet:namespace/>:CaseSearch';

function findObjectByPartOfID(partOfID)
{
	for(var i=0; i<document.forms.length; i++)
	{
		for(var j=0; j<document.forms[i].elements.length; j++)
		{
			var idValue = document.forms[i].elements[j].id;
			if(idValue.indexOf(partOfID)!=-1)
			{
				G_isFirstTime = false;
				G_countObject = document.forms[i].elements[j];
				return document.forms[i].elements[j];
			}
		}
	}		
	return null;
}
function warnBeforeExitForReassign()
 {
	if(flagWarn==true)
	{	  	
  		if (isFormChanged(thisForm) == true)
		{
	 		var confirmed = confirm('Are you sure you want to Reassign the selected records?')
			if(confirmed)
			{
			return true;
			}
			else
			{
			return false;
			}
	    }
	 }
	 flagWarn=true;
  }

// this function is invoked to show the user an option to save and navigate to next/previous page
// if and only if user selected an 'Assigned to' value for atleast one record in individual assignment mode of current page.
function warnBeforeGoToNext(cont)
{
	var noOfRows=10;
	var isChanged=false;
	for(var row=0;row<noOfRows;row++){
		var dropDownObj=findObjectByPartOfID(row+':departmentId');
		if(dropDownObj!=null && (dropDownObj.value!='' && dropDownObj.value!=' ')){
			isChanged=true;
			break;
		}
	}
	if(isChanged){
		var option=confirm("The changes made on the current page will be lost if not saved.\n Please select 'OK' to Save and Continue or 'Cancel' to continue without saving");
		if(option){
			var saveNCont=document.getElementById(thisForm+':saveNContinueId');
			document.getElementById(thisForm+':continueTypeId').value=cont;
			flagWarn=false;
			saveNCont.click();
			flagWarn=false;
			return false;
		}else{
			
		}
	}
}


function onChangReassign()
{
var hiddenButtonObject = findObjectByPartOfID('reassignValueChangeID');
	hiddenButtonObject.click();
	return null;
}
	



</SCRIPT>

<f:view>

<%--Added by infinite for CR 1825 Gray Mode change--%>
			<h:inputHidden id="link2ShowId"
				value="#{caseSearchControllerBean.link2Show}" />
			<h:inputHidden id="userHavingUpdateRole" value="#{caseSearchDataBean.controlRequired}" />
			<%--End--%>

	<%--<t:saveState value="#{caseSearchDataBean}"></t:saveState> --%>
	<f:subview id="provService">
		<jsp:include page="/jsp/script/scripts.jsp" />
	</f:subview>

	
	<m:body onload="onLoadFocus();warnBeforeCancel();renderAudit('audit_open');">
		<h:form id="CaseSearch" >
			<%-- Added  for  reassign   --%>
			 <h:commandButton id="reassignValueChangeID" styleClass="hide"  onmousedown="javascript:flagWarn=false;" 
				value="Hidden Button To Click"
				action="#{caseSearchControllerBean.onChangeReassignAll}" />
				 <h:commandButton id="saveNContinueId" styleClass="hide"  onclick="javascript:flagWarn=false;" 
				value="Hidden Button To Click"
				action="#{caseSearchControllerBean.reassignIndivualCaseNContinue}" />
				<h:inputHidden id="continueTypeId" value="#{caseSearchDataBean.continueType}" />
			<%-- Commented for Code Clean (PERFORMANCE) --%>
			<%-- <h:inputHidden value="#{caseSearchControllerBean.memberLogCase}"></h:inputHidden> --%>
			<h:inputHidden id="cursorFocusId" value="#{caseSearchDataBean.cursorFocusId}"></h:inputHidden>
			<m:div id="wrapper">
				<m:div id="content">
					<m:div styleClass="floatContainer">

						<m:div styleClass="moreInfoBar">
							<m:div styleClass="infoTitle" align="left">
								<m:span styleClass="required">
									<h:outputText id="CMGTOT90" value="#{ent['label-sw-reqFld']}"										styleClass="colorMaroon" />
								</m:span>

							</m:div>
							<m:div rendered="#{caseSearchDataBean.showReassignResultsPage}" styleClass="infoActions">
							<%-- Reassign method modified for CR 798895 for Bulk reassign. --%>
								<h:commandButton id="PROVIDERCB20120731164811666"
									style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:14px;WIDTH:65px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
									onclick="javascript:flagWarn=false;if (!(confirm('Are you sure you want to Reassign the selected records?'))) return(false);"
									value="#{msg['lable.reassignCase.reassign']}"
									disabled="#{caseSearchDataBean.disableReassignLink}"
									action="#{caseSearchControllerBean.reassignCaseRecords}" />
								
								

							</m:div>
							<m:div rendered="#{!caseSearchDataBean.showReassignResultsPage}" styleClass="infoActions">
							<%-- Reassign method modified for CR 798895 for Bulk reassign. --%>
							<h:commandButton id="PROVIDERCB20120731164811667"
									style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:14px;WIDTH:65px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
									onclick="javascript:flagWarn=false;if (!warnBeforeExitForReassign())  return(false); "
									value="#{msg['lable.reassignCase.reassign']}"
									disabled="#{caseSearchDataBean.disableReassignLink}"
									action="#{caseSearchControllerBean.reassignCaseRecords}" />
								
								

							</m:div>
						</m:div>
						<%-- Moved from inside of SearchCase section to outside for the defect id ESPRD00736670_21DEC2011 --%>
						<m:div rendered="#{caseSearchDataBean.showCaseSearchMsgFlag}">	
								<h:messages  showDetail="true" layout="table" id="caseSearchErrorMessage" showSummary="false"	style="color:Red"></h:messages>
						</m:div>
						<m:div styleClass="strong">
							<m:br></m:br>
							<m:br></m:br>
							<m:section id="PROVIDERMMS20120731164811668" styleClass="otherbg">
								<m:legend>

									<h:outputText id="CMGTOT91" value="#{msg['label.searchCase.searchForCase']}" />

								</m:legend>
								
								<m:div>									
									<f:subview id="caseSearchInclude">
										<jsp:include page="/jsp/reassignCaseRecord/caseSearch.jsp" />

									</f:subview>
								</m:div>
								<m:table id="PROVIDERMMT20120731164811669" styleClass="tableBar" width="100%">
									<m:tr>
										<m:td styleClass="tableAction">
											<!-- Start - Modified the property name in the action attribute for the search fix -->
											<h:commandButton  id="CMGTCB13"  styleClass="formBtn"  onmousedown="javascript:flagWarn=false;" 
												value="#{msg1['button.searchCase.search']}"
												action="#{caseSearchControllerBean.reassignCaseRecordsSearch}" >
											<f:attribute value="#{caseSearchDataBean.showReassignResultsPage}" name="sam"/>
											</h:commandButton>
											<!-- End - Modified the property name in the action attribute for the search fix -->
											<h:commandButton  id="CMGTCB14"  styleClass="formBtn"  onmousedown="javascript:flagWarn=false;" 
												value="#{msg1['button.searchCase.reset']}"
												action="#{caseSearchControllerBean.resetSerachCase}" />
										</m:td>
									</m:tr>
								</m:table>
								<m:br></m:br>
								<m:div>
									<f:subview id="caseSearchResults">
										<jsp:include
											page="/jsp/reassignCaseRecord/inc_reassignCaseResult.jsp" />
									</f:subview>

								</m:div>
								
							</m:section>
						</m:div>

					</m:div>
				</m:div>
			</m:div>
		</h:form>
	</m:body>
	<script language="JavaScript">
	
			portletNameSpace = 'view<portlet:namespace/>:';
			
			renderDiv('CaseSearch:caseSearchInclude:advancedSearchHiddenID',
            'CaseSearch:plusMinus_advancedSearchFalse',
            'showhide_AdvencedOpt');
            
	</script>
</f:view>
<%--
	  var thisForm : stores the form reference. Form reference is use to get  the computed  role stored in hidden form field.
	   FlagRole : Indicator to store role information .Stores 'False' for inquire only users and 'True' for other user.
	--%>
		<script type="text/JavaScript">
		var flagRole = ((typeof(document.getElementById(thisForm+':userHavingUpdateRole') != 'undefined')) && document.getElementById(thisForm+':userHavingUpdateRole') != null ? flagRole = document.getElementById(thisForm+':userHavingUpdateRole').value:true);
	</script>
<script language="JavaScript">
 function onLoadFocus(){
		frmId = 'view<portlet:namespace/>:CaseSearch';
		var pageId ='view<portlet:namespace/>:CaseSearch:caseSearchInclude:';

		//starting of java script code for calender in mozilla
		//find the user browser
		var brow=(navigator.appName);

		if(brow == 'Microsoft Internet Explorer'){
			//alert("its internet explorer");
		}else if(brow == 'Netscape'){
			//alert("its Mozilla");
			jscalendarInit();
		}
		//end of java script code for calender in mozilla
		if(document.getElementById(frmId+':cursorFocusId')!=null || document.getElementById(frmId+':cursorFocusId').value!=''){
		    var cursorFocus=pageId+document.getElementById(frmId+':cursorFocusId').value;
			if(cursorFocus!=null && document.getElementById(cursorFocus)!= null){
				document.getElementById(cursorFocus).focus();
			}
		}
	}
</script>
