<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>


<f:loadBundle var="srCrspd"
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactCorrespondenceSearch" />
<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />
<SCRIPT type="text/javascript">
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
	</SCRIPT>
<f:view>
	<body onkeypress="focusButton();" onload="onLoadFocus();">
	<t:saveState id="CMGTTOMSS528" value="#{cs_searchCorrespondenceDataBean}" />
	<t:saveState id="CMGTTOMSS529" value="#{cs_searchCorrespondenceDataBean.listOfRepUnits}" />
	<t:saveState id="CMGTTOMSS530" value="#{cs_searchCorrespondenceDataBean.searchResults}" />
	<t:saveState id="CMGTTOMSS531" value="#{cs_searchCorrespondenceDataBean.showResultsDiv}" />
	<t:saveState id="CMGTTOMSS532"		value="#{cs_searchCorrespondenceDataBean.listOfRefDeptUnits}" />
	<t:saveState id="CMGTTOMSS533"		value="#{cs_searchCorrespondenceDataBean.listOfRefBusUnits}" />
	<t:saveState id="CMGTTOMSS534" value="#{cs_searchCorrespondenceDataBean.lobVVList}" />
	<h:inputHidden id="PRGCMGTIH36" value="#{cs_searchCorrespondenceDataBean.validValues}"></h:inputHidden>
	<h:inputHidden id="PRGCMGTIH37" value="#{cs_searchCorrespondenceControllerBean.loadEntityData}"></h:inputHidden>
<hx:scriptCollector>
	<h:form id="logCorrespondence">
		
		<m:body>

			<m:inputHidden name="Send.CorrespondenceSk"
				value="SendCorrespondenceSk"></m:inputHidden>
	<%-- onload cursor focus --%>
			<h:inputHidden id="cursorFocusId" value="#{cs_searchCorrespondenceDataBean.cursorFocusId}"></h:inputHidden>
			<h:inputHidden id="PRGCMGTIH38" value="#{cs_searchCorrespondenceControllerBean.loadSearchCRPermission}"></h:inputHidden> 
			<h:inputHidden id="PROVIDERIHH20120731164811689" value="#{cs_searchCorrespondenceControllerBean.memberCorrespondence}"/>
			
				<m:div styleClass="moreInfoBar" >
					<m:div styleClass="infoTitle" align="left">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT1812" value="*Required Fields" styleClass="required" />
						</m:span>
						<h:outputText id="PRGCMGTOT1813" escape="false"	value="#{ref['label.ref.singleSpace']}" />
						<h:outputText id="PRGCMGTOT1814" escape="false"	value="#{ref['label.ref.singleSpace']}" />
						<h:outputText id="PRGCMGTOT1815" escape="false"	value="#{ref['label.ref.singleSpace']}" />
						<h:outputText id="PRGCMGTOT1816" escape="false"	value="#{ref['label.ref.singleSpace']}" />
						</m:div>

					
						<m:div styleClass="infoActions">
						
						<%--Below code is modified to not display Add Correspondence link for inquiry All user for Defect ESPRD00802214 --%>
						   <m:div rendered="#{cs_searchCorrespondenceControllerBean.controlRequired}">
							<f:verbatim>  <a href="/wps/myportal/SearchCREntity" id="srchLink1" > </f:verbatim>
								<h:outputText styleClass="strong" id="srchLink2" value="#{srCrspd['label.crspd.addCR']}" />
						<f:verbatim></a></f:verbatim> 
						</m:div>
						<%--<f:verbatim>  
						
							<h:outputLink value ="/wps/myportal/SearchCREntity" id="srchLink1" rendered = "#{cs_searchCorrespondenceControllerBean.controlRequired}" >
								<h:outputText styleClass="strong" id="srchLink2" value="#{srCrspd['label.crspd.addCR']}"/>
							</h:outputLink>
						</f:verbatim>--%>
						    <m:div rendered="#{! cs_searchCorrespondenceControllerBean.controlRequired}">
							<h:outputLabel  id="srchLink3"	value="#{srCrspd['label.crspd.addCR']}"/>
							<h:outputText id="PRGCMGTOT1817" escape="false"	value="#{ref['label.ref.singleSpace']}" />
							</m:div>
						</m:div>
						</m:div>
				        
			
			
			<f:subview id="logCrspdAsscSubview">
				<f:subview id="logCrspdAssociatedSubview">
					<f:subview id="searchforAssCRSPDsubview">

						<h:messages id="PRGCMGTMS33" showDetail="true" layout="table" showSummary="false"							style="color:Red" />
						<jsp:include
							page="/jsp/searchcorrespondence/inc_searchForCorrespondence.jsp" />
					</f:subview>
				</f:subview>
			</f:subview>

			<f:subview id="searchForCrspdResults">

				<jsp:include
					page="/jsp/searchcorrespondence/inc_searchCorrespondenceResults.jsp" />
			</f:subview>

		</m:body>
		<script>
			portletNameSpace = 'view<portlet:namespace/>:';
			renderDiv('logCorrespondence:logCrspdAsscSubview:logCrspdAssociatedSubview:searchforAssCRSPDsubview:advancedCrspdSrchSubview:advancedOptionsHidden','logCorrespondence:logCrspdAsscSubview:logCrspdAssociatedSubview:searchforAssCRSPDsubview:advancedCrspdSrchSubview:plusMinusAdvCrspdSearch','srchCrspd_div_advcSrchOptns');
			
		
	</script>

	</h:form>
	</hx:scriptCollector>
</body>
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
