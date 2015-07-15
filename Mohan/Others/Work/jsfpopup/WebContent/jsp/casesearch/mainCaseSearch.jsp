<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<portlet:defineObjects />

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CMSearchCase"
	var="msg" />

<script type="text/javascript">
 /*Added for CR Cursor focus starts*/
		var thisForm = 'view<portlet:namespace/>:CaseSearch';
		function focusThis(id) { 
   			document.getElementById(thisForm+':focusId').value=id;  			
	   	}						
		if (window.addEventListener) //DOM method for binding an event
            	window.addEventListener("load", onLoadFunctions, false);
      	else if (window.attachEvent) //IE exclusive method for binding an event
            	window.attachEvent("onload", onLoadFunctions);
      	else if (document.getElementById) //support older modern browsers
           		window.onload=onLoadFunctions;		
		function onLoadFunctions() {
			focusOnLoad();	
		}
		function focusOnLoad() {
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
			if(document.getElementById(thisForm+':focusId').value==''){
				document.getElementById('view<portlet:namespace/>:CaseSearch:caseSrchAsscSubview:CaseSearchEntityType').focus();
			}

   			var focusPage = '#'+document.getElementById(thisForm+':focusId').value;
			if(focusPage != '' && focusPage != '#') {				
				document.location.href=focusPage;
			}
   		}
   		/*Added for CR Cursor focus ends*/ 

function setHiddenValue(hiddenVariable, hiddenValue) 
{
hiddenVariable.value = hiddenValue;
}

function toggleTest(obj,state){
 var el = document.getElementById(obj);
 if (state==1){
  el.style.display = 'block';
 }
 else if (state==0){
  el.style.display = 'none';
 }
 else if (state==2){
  if (el.style.display == 'none'){
   el.style.display = 'block'; 
  }
  else if ((el.style.display == 'block') || (el.style.display == '')){
   el.style.display = 'none';
  }
 }
}
/*
 * Used to display either '+' image or '-' image when a section 
 * is closed or expanded respectively
 */
function plusMinusForRefreshTest(id,obj,hiddenTextId)
{
 var hiddenTxt = find(hiddenTextId);
 var el = document.getElementById(id);

 if (el.style.display == 'none')
 {
  obj.className = 'plus';
  hiddenTxt.value = 'plus';
 }
 else if ((el.style.display == 'block')  || (el.style.display == ''))
 {
  obj.className = 'minus';
  hiddenTxt.value = 'minus';
 }
 else if (el.style.display == '')
 {
  obj.className = 'minus';
  hiddenTxt.value = 'minus';
 }
}

function focusButton()
	{
	  var fbid1 ='view<portlet:namespace/>:CaseSearch:caseSrchAsscSubview:SEARCHCASEBTN'; 
	  if (fbid1 != null && document.getElementById(fbid1)!= null) 
	  {
		if (window.event.keyCode == 13)
		{	
			document.getElementById(fbid1).focus();
		}
	  }else{
		//alert("search case button");
	  }
	}
</script>

<f:view>
<hx:scriptCollector>
	<%--<t:saveState id="CMGTTOMSS36" value="#{caseSearchDataBean}"></t:saveState>--%>
	
	
	<m:body onload="javascript:onLoadFocus();"  onkeypress="focusButton();">
	
        <h:inputHidden id="PRGCMGTIH12" value="#{caseSearchControllerBean.link2Show}"></h:inputHidden>
		<%-- moved to inner jsp (jsp/casesearch/caseSearch.jsp) because valid values need to be loaded.
		<h:inputHidden id="PRGCMGTIH13" value="#{caseSearchControllerBean.memberLogCase}"></h:inputHidden>--%>
					
		<m:div id="wrapper">
			<m:div id="content">
				<m:div styleClass="floatContainer">
					<h:form id="CaseSearch">
						<m:inputHidden
								name="com.ibm.portal.propertybroker.action"
								value="sendCaseDetailsAction"></m:inputHidden>
						<h:inputHidden id="focusId" value=" "></h:inputHidden>
						<m:div styleClass="moreInfoBar">
                    <h:inputHidden id="cursorFocusId" value="#{caseSearchDataBean.cursorFocusIdCaseSearch}"></h:inputHidden>
							<m:div styleClass="infoActions">
                         <%--     <h:outputLink id="PRGCMGTOLK16" value="#{msg['label.searchCase.link']}">
									<h:outputText id="PRGCMGTOT276" styleClass="strong" rendered="#{!caseSearchDataBean.disableAddSearch}"										value="#{msg['label.searchCase.addCase']}"></h:outputText>
								</h:outputLink> --%>
								<f:verbatim> <a href="/wps/myportal/CaseEntitySearch" id="PRGCMGTOLK16">

									<h:outputText id="PRGCMGTOT276" styleClass="strong" rendered="#{!caseSearchDataBean.disableAddSearch}"	value="#{msg['label.searchCase.addCase']}"></h:outputText>

								</a></f:verbatim>

								
                                <h:outputText id="PRGCMGTOT277" styleClass="strong" rendered="#{caseSearchDataBean.disableAddSearch}"										value="#{msg['label.searchCase.addCase']}"></h:outputText>
								<%--Commented for defect ESPRD00480127--%>
								<%--
								<h:outputText id="PRGCMGTOT278" styleClass="strong"									value="#{msg['lable.searchCase.devider']}"></h:outputText>
								<h:outputLink id="PRGCMGTOLK17" value="#{msg['label.searchCase.linkCancel']}">
									<h:outputText id="PRGCMGTOT279" styleClass="strong"										value="#{msg['button.searchCase.cancel']}"></h:outputText>
								</h:outputLink>--%>
							</m:div>
						</m:div>

						<f:subview id="caseSrchAsscSubview">
							<h:messages id="PRGCMGTMS1" showDetail="true" layout="table"								showSummary="false" style="color:Red" />
							<jsp:include page="/jsp/casesearch/caseSearch.jsp" />
						</f:subview>
						<f:subview id="searchForCrspdResults">
							<jsp:include page="/jsp/casesearch/caseSearchResults.jsp" />
						</f:subview>

					</h:form>
				</m:div>
			</m:div>
		</m:div>
	</m:body>
	<script language="JavaScript">
	// Added the subview id in the second perameter of renderDIV function
	// for the defect id ESPRD00709497_10OCT2011
			portletNameSpace = 'view<portlet:namespace/>:';
			renderDiv('CaseSearch:caseSrchAsscSubview:advancedSearchHiddenID',
            'CaseSearch:caseSrchAsscSubview:plusMinus_advancedSearchFalse',
            'showhide_AdvencedOpt');
     </script>
	</hx:scriptCollector>
</f:view>
<script type="text/javascript">
 function onLoadFocus(){
		frmId = 'view<portlet:namespace/>:CaseSearch';
var pageId ='view<portlet:namespace/>:CaseSearch:caseSrchAsscSubview:';
		if(document.getElementById(frmId+':cursorFocusId')!=null || document.getElementById(frmId+':cursorFocusId').value!=''){
		    var cursorFocus=pageId+document.getElementById(frmId+':cursorFocusId').value;
			if(cursorFocus!=null && document.getElementById(cursorFocus)!= null){
				document.getElementById(cursorFocus).focus();
			}
		}
	}
</script>
