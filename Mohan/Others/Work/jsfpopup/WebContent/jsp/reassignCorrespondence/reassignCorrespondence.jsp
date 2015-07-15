<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<SCRIPT type="text/javascript">
formId = 'view<portlet:namespace/>:logCorrespondence';
flagWarn = true;	
function focusButton()
	{
	  var fbid1 ='view<portlet:namespace/>:logCorrespondence:logCrspdAsscSubview:logCrspdAssociatedSubview:searchforAssCRSPDsubview:searchCr'; 
	  if (fbid1 != null && document.getElementById(fbid1)!= null) 
	  {
		if (window.event.keyCode == 13)
		{	
			document.getElementById(fbid1).focus();
		}
	  }
	}

function warnBeforeExitForReassign()
 {
	if(flagWarn==true)
	{	  	
  		if (isFormChanged(formId) == true)
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

function isFormChanged(formId)
{
	var ele = document.forms[formId].length;
 	for ( i=0; i < ele; i++ ) 
	{
 		if (document.forms[formId].elements[i].type != undefined)
		{
 			var flag=""; 
            var defInd="";
            var selInd="";
          	for ( var x =0 ; x < document.forms[formId].elements[i].length; x++ ) 
				{
	            	if(document.forms[formId].elements[i].options[x].defaultSelected) 
					{
	                	flag="true";
	                   	defInd=x;
					}  
	                if(document.forms[formId].elements[i].options[ x ].selected)  
					{
	                	selInd=x;
					}
				}
             	if(defInd=="" && selInd!="")
              	{
                	flag="true";
                   	defInd=0;
				}    
             	if(flag=="true") 
				{  
					if (document.forms[formId].elements[i].options[selInd].value!=document.forms[formId].elements[i].options[defInd].value )  
					{
              	   		flag="false";
					}
				}
            	if(flag=="false") 
				{
					return true;
				}
            }
		}
 	    return false;
 	 }
 
//Below function is added for CR:ESPRD00798895
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
		var option=confirm("The changes made on the current page will be lost if they are not saved.\n Please select 'Ok' to save and continue or 'Cancel' to continue without saving");
		if(option){
			var reasgnvar ='view<portlet:namespace/>:logCorrespondence:searchForCrspdResults:saveNContinueId';
			var reasgnvarhid ='view<portlet:namespace/>:logCorrespondence:searchForCrspdResults:continueTypeId';
			var saveNCont=document.getElementById(reasgnvar);
			document.getElementById(reasgnvarhid).value=cont;
			flagWarn=false;
			saveNCont.click();
			flagWarn=false;
			return false;
		}
		else{
		}
		
	}
	
}

function findObjectByPartOfID(partOfID)
{
	for(var i=0; i<document.forms.length; i++)
	{
		for(var j=0; j<document.forms[i].elements.length; j++)
		{
			var idValue = document.forms[i].elements[j].id;
			if(idValue.indexOf(partOfID)!=-1)
			{
			//	G_isFirstTime = false;
			//	G_countObject = document.forms[i].elements[j];
				return document.forms[i].elements[j];
			}
		}
	}		
	return null;
}


	</SCRIPT>
<f:loadBundle var="srCrspd"
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactCorrespondenceSearch" />

<f:view>
	<t:saveState id="CMGTTOMSS503"
		value="#{cs_searchCorrespondenceDataBean}" />
	<t:saveState id="CMGTTOMSS504"
		value="#{cs_searchCorrespondenceDataBean.listOfRepUnits}" />
	<t:saveState id="CMGTTOMSS505"
		value="#{cs_searchCorrespondenceDataBean.searchResults}" />
	<t:saveState id="CMGTTOMSS506"
		value="#{cs_searchCorrespondenceDataBean.showResultsDiv}" />
	<t:saveState id="CMGTTOMSS507"
		value="#{cs_searchCorrespondenceDataBean.listOfRefDeptUnits}" />
	<t:saveState id="CMGTTOMSS508"
		value="#{cs_searchCorrespondenceDataBean.listOfRefBusUnits}" />
	<t:saveState id="CMGTTOMSS509"
		value="#{cs_searchCorrespondenceDataBean.lobVVList}" />
	<h:inputHidden id="PRGCMGTIH33"
		value="#{cs_searchCorrespondenceDataBean.validValues}"></h:inputHidden>
	<h:inputHidden id="PRGCMGTIH34"
		value="#{cs_searchCorrespondenceControllerBean.loadEntityData}"></h:inputHidden>
	<h:inputHidden id="PRGCMGTIH35"
		value="#{cs_searchCorrespondenceControllerBean.loadReAssignCRPermission}"></h:inputHidden>
	<hx:scriptCollector>
		<h:form id="logCorrespondence">

			<m:body onkeypress="focusButton();" onload="onLoadFocus();">

				<m:inputHidden name="Send.CorrespondenceSk"
					value="SendCorrespondenceSk"></m:inputHidden>
				<m:inputHidden name="reassignCorrespondence"
					value="reassignCorrespondence"></m:inputHidden>
				<%-- onload cursor focus --%>
				<h:inputHidden id="cursorFocusId"
					value="#{cs_searchCorrespondenceDataBean.cursorFocusId}"></h:inputHidden>

				<%--Defect Fixed 10/05/2011--%>

				<m:div styleClass="floatContainer">
					<m:div styleClass="fullwidth">
						<m:div styleClass="moreInfoBar">
							<m:div styleClass="infoTitle" align="left">
								<h:outputText id="requiredFileds" value="*Required Fields"
									styleClass="required" />
							</m:div>

							<m:div
								rendered="#{cs_searchCorrespondenceDataBean.renderReassignBtn}"
								styleClass="infoActions">
								<h:commandButton id="PRGCMGTCB88"
									style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:14px;WIDTH:65px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
									onclick="if (!(confirm('Are you sure you want to Reassign the selected records?'))) return(false);"
									value="Reassign"
									action="#{cs_correspondenceControllerBean.reassignCorrespondence}"
									disabled="#{!cs_searchCorrespondenceControllerBean.ctrlReqForReassignCorr}" />
							</m:div>
							<m:div
								rendered="#{!cs_searchCorrespondenceDataBean.renderReassignBtn}"
								styleClass="infoActions">
								<h:commandButton id="PRGCMGTCB89"
									style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:14px;WIDTH:65px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
									value="Reassign"
									onclick="if (!warnBeforeExitForReassign())  return(false);"
									action="#{cs_correspondenceControllerBean.reassignCorrespondence}"
									disabled="#{!cs_searchCorrespondenceControllerBean.ctrlReqForReassignCorr}" />
							</m:div>
						</m:div>
					</m:div>
				</m:div>

				<f:subview id="logCrspdAsscSubview">
					<f:subview id="logCrspdAssociatedSubview">
						<f:subview id="searchforAssCRSPDsubview">
							<h:messages id="PRGCMGTMS31" showDetail="true" layout="table"
								showSummary="false" style="color:Red" />
							<jsp:include
								page="/jsp/searchcorrespondence/inc_searchForCorrespondence.jsp" />
						</f:subview>
					</f:subview>
				</f:subview>

				<f:subview id="searchForCrspdResults">
					<jsp:include
						page="/jsp/reassignCorrespondence/inc_reassignCorrespondenceResult.jsp" />
				</f:subview>

			</m:body>

			<script>
			portletNameSpace = 'view<portlet:namespace/>:';
			renderDiv('logCorrespondence:logCrspdAsscSubview:logCrspdAssociatedSubview:searchforAssCRSPDsubview:advancedCrspdSrchSubview:advancedOptionsHidden','logCorrespondence:logCrspdAsscSubview:logCrspdAssociatedSubview:searchforAssCRSPDsubview:advancedCrspdSrchSubview:plusMinusAdvCrspdSearch','srchCrspd_div_advcSrchOptns');		
		</script>



		</h:form>
	</hx:scriptCollector>
</f:view>
<SCRIPT type="text/javascript">
 function onLoadFocus(){
 
		//find the user browser
		var brow=(navigator.appName);

		if(brow == 'Microsoft Internet Explorer'){
			//alert("its internet explorer");
		}else if(brow == 'Netscape'){
			//alert("its Mozilla");
			jscalendarInit();
		}
		
		frmId = 'view<portlet:namespace/>:logCorrespondence';
		var pageId ='view<portlet:namespace/>:logCorrespondence:logCrspdAsscSubview:logCrspdAssociatedSubview:searchforAssCRSPDsubview:';
		if(document.getElementById(frmId+':cursorFocusId')!=null || document.getElementById(frmId+':cursorFocusId').value!=''){
		    var cursorFocus=pageId+document.getElementById(frmId+':cursorFocusId').value;
			if(cursorFocus!=null && document.getElementById(cursorFocus)!= null){
				document.getElementById(cursorFocus).focus();
			}
		}
	}
</SCRIPT>
