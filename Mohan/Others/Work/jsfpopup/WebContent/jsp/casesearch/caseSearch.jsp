<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>

 
<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CMSearchCase"
	var="msg1" />
<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />
<script>

    function getSearch(e)
    {
	  var keyCode=e.keyCode;
	  if(keyCode == '13')
      {
      var searchBt = findObjectByPartOfID('searchCr');
	  searchBt.click();
      }

    }

   function getSearchentity(e)
    {
	  var keyCode=e.keyCode;
	  if(keyCode == '13')
        {
		var searchBt = findObjectByPartOfID('searchentity2');
	 	searchBt.click();
        }
    }

   function findObjectByPartOfID(partOfID)
   {
	for(i=0; i<document.forms.length; i++)
	 {
		for(j=0; j<document.forms[i].elements.length; j++)
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

</script>


<m:div styleClass="floatContainer">
	<m:inputHidden value="#{caseSearchControllerBean.entityDetails}"></m:inputHidden>
	<h:inputHidden id="PRGCMGTIH11" value="#{caseSearchControllerBean.loadValidValue}" rendered="#{caseSearchDataBean.validValueFlag}"></h:inputHidden>
	<%-- Moved from Main page (maincaseSearch.jsp)--%>
	<h:inputHidden id="PRGCMGTIH13" value="#{caseSearchControllerBean.memberLogCase}"></h:inputHidden>
	<%--<t:saveState id="CMGTTOMSS35" value="#{caseSearchDataBean}"></t:saveState>
	--%><m:pod title="Search For Case" styleClass="otherbg">

		<jsp:include page="/jsp/casesearch/inc_mainCaseSearch.jsp" />

		<m:div id="t1_CaseClaimData">

			<t:htmlTag value="h4">
				<t:commandLink
					action="#{caseSearchControllerBean.getAdvOptionsPlus}"
					onmousedown="javascript:flagWarn=false;" id="plusid"
					styleClass="plus"
					rendered="#{ not caseSearchDataBean.advOptionsFlag}">
					<h:outputText id="advancedSearch_textPlus"
						value="#{msg1['label.searchCase.advanceSearchOptions']}" />
				</t:commandLink>

				<t:commandLink
					action="#{caseSearchControllerBean.getAdvOptionsMinus}"
					onmousedown="javascript:flagWarn=false;" id="minusid"
					styleClass="minus"
					rendered="#{caseSearchDataBean.advOptionsFlag}">
					<h:outputText id="advancedSearch_textMinus"
						value="#{msg1['label.searchCase.advanceSearchOptions']}" />
				</t:commandLink>
				<h:inputHidden id="advancedSearchHiddenID"					value="#{caseSearchDataBean.plus}" />
			</t:htmlTag>
			<m:div id="srchCrspd_div_AdvcSrchOptns"
					rendered="#{caseSearchDataBean.advOptionsFlag}">
			<jsp:include page="/jsp/casesearch/inc_advanceCaseSearch.jsp" />
			</m:div>
		</m:div>

		<m:div align="right">
		<%--Modified for defect ESPRD00624909(TSU issue)--%>
		<%--<h:commandButton styleClass="formBtn" value="Search" id="searchCase"				action="#{caseSearchControllerBean.searchCase}"				onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);" />--%>
			<%-- Added the new method in the action attribute for the HeapDump Fix --%>
			<h:commandButton				action="#{caseSearchControllerBean.searchCaseRecords}"		value="Search"		styleClass="formBtn"
			onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);"
			id="SEARCHCASEBTN" />
				
				
			<h:outputText id="PRGCMGTOT263" escape="false" value="#{ref['label.ref.singleSpace']}" />
			
		<%--	<h:commandButton styleClass="formBtn" value="Reset" id="searchResetId"				action="#{caseSearchControllerBean.resetSerachCase}"				onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);" />--%>
				
			<h:commandButton styleClass="formBtn" value="Reset" id="searchResetId"			action="#{caseSearchControllerBean.resetSerachCase}"				styleClass="formBtn" onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);" />
			<h:outputText id="PRGCMGTOT264" escape="false" value="#{ref['label.ref.singleSpace']}" />
		</m:div>

	</m:pod>

</m:div>
