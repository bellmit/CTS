<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />

 
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


<m:div id="logCaseSearch1Div" styleClass="floatContainer">
	<h:inputHidden id="logCaseSearchHid1" value="#{caseSearchControllerBean.entityDetails}"></h:inputHidden>
	<m:pod id="logCaseSearchPod1" title="Search For Case" styleClass="otherbg">
		<f:subview id="logCaseSearchSubView1">
		<jsp:include page="/jsp/casesearch/inc_mainCaseSearch.jsp" />
		</f:subview>
		<m:div id="t1_CaseClaimData">
		
		<t:htmlTag value="h4">

				<t:commandLink
					action="#{caseSearchControllerBean.getAdvOptionsPlus}"
					onmousedown="javascript:flagWarn=false;focusPage('caseCaseSearchPage');"
					id="plusid" styleClass="plus"
					rendered="#{ not caseSearchDataBean.advOptionsFlag}">
					<h:outputText id="adv_Case_SearchPlus"
						value="#{msg1['label.searchCase.advanceSearchOptions']}">
					</h:outputText>

				</t:commandLink>

				<t:commandLink
					action="#{caseSearchControllerBean.getAdvOptionsMinus}"
					onmousedown="javascript:flagWarn=false;focusPage('caseCaseSearchPage');"
					id="minusid" styleClass="minus"
					rendered="#{caseSearchDataBean.advOptionsFlag}">
					<h:outputText id="adv_Case_SearchMinus"
						value="#{msg1['label.searchCase.advanceSearchOptions']}">
					</h:outputText>
				</t:commandLink>
				<h:inputHidden id="advancedSearchHiddenID"					value="#{caseSearchDataBean.plus}" />
				<h:inputHidden id="advancedOptionsCaseHidden"
					value="#{logCaseDataBean.advancedOptionsHidden}"></h:inputHidden>

			</t:htmlTag>
		

			<%--<t:htmlTag id="logCaseSearchHtmlTag1" value="h4">
				<h:outputLink					onclick="setHiddenValue('CMlogCase:associatedCaseandCorrespondence:logCaseAssociatedCase:logCaseAssCaseSubView1:caseSearchSubview1:advancedSearchHiddenID1','minus');	 										toggleTest('showhide_AdvencedOpt',2);											plusMinusForRefreshTest('showhide_AdvencedOpt',this,'CMlogCase:associatedCaseandCorrespondence:logCaseAssociatedCase:logCaseAssCaseSubView1:caseSearchSubview1:advancedSearchHiddenID1');											return false;"					id="plusMinus_advancedSearchFalse" styleClass="plus">
					<h:outputText id="advancedSearch_text"						value="#{msg1['label.searchCase.advanceSearchOptions']}" />
				</h:outputLink>
				<h:inputHidden id="advancedSearchHiddenID1"					value="#{caseSearchDataBean.plus}" />
			</t:htmlTag> --%>
			<%--<f:subview id="logCaseSearchSubView2">
			--%>
			<m:div id="srchCrspd_div_AdvcSrchOptns"
					rendered="#{caseSearchDataBean.advOptionsFlag}">
			
			<jsp:include page="/jsp/casesearch/inc_advanceCaseSearch.jsp" />
			<%-- </f:subview>
		--%></m:div>
		</m:div>

		<m:div id="logCaseSearch3Div" align="right">
			<h:commandButton styleClass="formBtn" value="Search" id="searchCase"				action="#{caseSearchControllerBean.searchCase}"				onmousedown="javascript:flagWarn=false;focusPage('caseCaseSearchPage');" />
			<h:outputText id="logCaseSearchOutTxt1" escape="false" value="#{ref['label.ref.singleSpace']}" />
			<h:commandButton styleClass="formBtn" value="Reset" id="resetSerachCaseId"				action="#{caseSearchControllerBean.resetSerachCase}"				onmousedown="javascript:flagWarn=false;focusPage('caseCaseSearchPage');" />
			<h:outputText id="logCaseSearchOutTxt2" escape="false" value="#{ref['label.ref.singleSpace']}" />
		</m:div>

	</m:pod>

</m:div>
