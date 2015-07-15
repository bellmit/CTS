<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>
<portlet:defineObjects />
<%@page import="javax.faces.context.*"%>

<f:view>


	<f:verbatim>
		<SCRIPT type="text/javascript">
		
			var color;
			var flg = false;
		  	function getRow(thisObj, thisEvent) { 
		        if (!thisEvent || !thisObj) return; 
		        var p =  ((thisEvent.target) ? thisEvent.target : ((thisEvent.srcElement) ? thisEvent.srcElement : null)); 
		        var tr=null; 
		        
		        while (tr==null && p!=null) { 
		                if (p.tagName && p.tagName.toUpperCase()=="TR") tr=p; 
		                else p = p.parentNode; 
		        } 
		        if (tr && tr.parentNode.tagName.toUpperCase()=="TBODY" && tr.parentNode.parentNode.id==thisObj.id) {
		              return tr; 
		        }      
		        return null; 
			} 
		
			function setRowClass (tr, name, name1, name2) { 
		               
		        if (!tr || !tr.cells || !tr.cells.length) return; 
		        //tr.className=name
		        for (var i=0; i<tr.cells.length; i++) { 
		                //alert(tr.cells[i].className);
		                if(i==0) {
		                 tr.cells[i].className = name2;
		                }else if(i==tr.cells.length-1) {
		                 tr.cells[i].className = name1;
		                }else {
		                  tr.cells[i].className = name; 
		                }
		        } 
			} 
	
			function setRowClassOne (tr, name) { 
		        if (!tr || !tr.cells || !tr.cells.length) return; 
		        //tr.className=name
		        for (var i=0; i<tr.cells.length; i++) {                
		                  tr.cells[i].className = name;               
		        } 
			} 

			function tableMouseOver(thisObj, thisEvent) { 
		        var row = getRow(thisObj, thisEvent); 
		        if (row) setRowClass(row,"rowonon_mouse","rowonon_mouse_right","rowonon_mouse_left"); 
			} 
	
			function tableMouseOut(thisObj, thisEvent) { 
			//alert("Inside tableMouseOut");
		        var row = getRow(thisObj, thisEvent); 
		        if (row) setRowClassOne(row,"rowone"); 
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
 

			function renderAudit(id) 
			{ 
				var hiddenValue = document.getElementById('view<portlet:namespace/>:CategoryForm:auditlogDetails:'+id);
			    
				if ((hiddenValue == null) ||(hiddenValue == '')|| (hiddenValue.length == 0)) {
					hideMe('audit_plus');
				} else if ((hiddenValue.value == 'false')) 
				{
					hideMe('audit_plus');
				} 
					
			}
	function enableField ()
{
	//alert ('Inside enableField ()');
	var ltrKeyLenObj = document.getElementById ('view<portlet:namespace/>:letterKeywords:keywordTypeEnable');
	var ltrKeyLenTargetObj = document.getElementById ('view<portlet:namespace/>:letterKeywords:inputLengthDiv');

	//alert ('ltrKeyLenObj = ' + ltrKeyLenObj + ' ### ltrKeyLenTargetObj = ' + ltrKeyLenTargetObj);
	if (ltrKeyLenObj != null) {
		//alert ("Inside IF condition : " + ltrKeyLenObj.value);
		if (ltrKeyLenObj.value == '01') {
			if (ltrKeyLenTargetObj != null) {
				ltrKeyLenTargetObj.style.display = "block";
			}
		} else if (ltrKeyLenObj.value == '02' || ltrKeyLenObj.value == '03') {
			ltrKeyLenTargetObj.style.display = "none";
			//alert ('VALUE IS 02 / 03');
		}
	} else {
		//alert ("Inside ELSE condition");
		// ltrKeyLenObj.style.display = "none";
	}
	focusComponent ();
}

function focusComponent ()
{
	//alert ('Inside Maintain Letter Keywords focusComponent ()');
	var focusVal = document.getElementById ("view<portlet:namespace/>:letterKeywords:curFocusValId");
	var kwdTypeCode = document.getElementById ("view<portlet:namespace/>:letterKeywords:keywordTypeEnable");
	var kwdId = document.getElementById ("view<portlet:namespace/>:letterKeywords:keywordId");
	//alert ('focusVal = ' + focusVal);
	if (focusVal != null)
	{
		//alert ('focusVal = ' + focusVal.value);
		//alert ('Keyword Type Code for EDIT = ' + kwdTypeCode);
		//alert ('Keyword ID for ADD = ' + kwdId);
		if (focusVal.value == 'Edit')
		{
			//alert ('Ready to Set Focus for EDIT');
			kwdTypeCode.focus();
			focusVal.value = "";
		} else if (focusVal.value == 'Add')
		{
			//alert ('Ready to Set Focus for ADD');
			kwdId.focus();
			focusVal.value = "";
		}
	}
}
function dataloss()
	 { 
	 	
		//var r = confirm('Are you sure you want to navigate away from this page?\n   If data has been entered and not saved, it will be lost. Select OK to navigate away from the page or Cancel to return to the page to save the data.');
		var r = confirm('If data has been entered and not saved, it will be lost. \nSelect OK to navigate away from the page \nor Cancel to return to the page to save the data.');
		if(r==true)
			{
				flg= true;
				return true;
			}
		else
			{
				return false;			
			}
		flagWarn=false;
		flagRole=false;
	}

		</SCRIPT>

	</f:verbatim>



	<body id="letterKeywordsBody" onload="enableField()">
	<h:inputHidden id="PRGCMGTIH001" value="#{MaintainKeywordsControllerBean.allKeywords}"
		rendered="#{MaintainKeywordsDataBean.dataBeanFlag}"></h:inputHidden>
	<h:form id="letterKeywords">
		<f:subview id="scriptViewLTRKYDID">
			<jsp:include page="/jsp/script/scripts.jsp" />
		</f:subview>
		<f:loadBundle var="letterKeywords"
			basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_LetterKeywordsMaintenance" />

		<f:loadBundle
			basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
			var="ref" />

		<f:loadBundle
			basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
			var="ent" />

		<h:inputHidden id="curFocusValId"
			value="#{MaintainKeywordsDataBean.initialCursorFocusValue}"></h:inputHidden>

		<h:outputText id="PRGCMGTOT1495"
			value="#{MaintainKeywordsControllerBean.pageAccessPermission}"></h:outputText>
		<m:body>
			<%--ESPRD00696117: to remove break in fieldset <m:table width="100%"> --%>
			<m:div styleClass="moreInfoBar">
				<h:panelGroup id="linksSection">
					<m:div styleClass="infoTitle">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT1496" value="*Required Field"
								styleClass="style:RED" style="font-weight:normal" />
						</m:span>
					</m:div>
					<m:div styleClass="infoActions">
						<h:commandButton id="LtrKywdsAuditLogCmdButton"
							onclick="javascript:flagWarn=false;"
							style="align:right;COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:10px;WIDTH:54px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
							value="#{letterKeywords['label.ref.auditLog']}"
							action="#{MaintainKeywordsControllerBean.doAuditKeyListOperation}">
						</h:commandButton>
					</m:div>
				</h:panelGroup>
			</m:div>
			<h:messages id="PRGCMGTMS21" layout="table" showDetail="true"
				showSummary="false" style="color: red" />
			<m:br clear="all" />
			<%--m:table id="LtrKywdsAuditLogLinkTable" width="97%">
				<m:tr>
					<m:td>
						<m:div align="right" id="LtrKywdsAuditLogLinkDiv">
							<%--h:commandLink id="LtrKywdsAuditLogCmdLink"
								action="#{MaintainKeywordsControllerBean.doAuditKeyListOperation}">
								<h:outputText id="LtrKywdsAuditLogLinkText" value="#{letterKeywords['label.ref.auditLog']}" />
							</h:commandLink--%>
			<%--h:commandButton id="LtrKywdsAuditLogCmdButton"
								style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:54px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
								value="#{letterKeywords['label.ref.auditLog']}" disabled="#{MaintainKeywordsDataBean.updateLtrKeywords}"
								action="#{MaintainKeywordsControllerBean.doAuditKeyListOperation}" >
							</h:commandButton>
						</m:div>
					</m:td>
				</m:tr>
			</m:table --%>
			<m:br />
			<m:section id="PRGCMGTS01">
				<m:legend>
					<h:outputText id="PRGCMGTOT1497"
						value="#{letterKeywords['label.letterkeyword']}"></h:outputText>
				</m:legend>
				<m:br />

				<m:div align="right">
					<h:commandButton id="PRGCMGTCB78"
						onclick="javascript:flagWarn=false;" styleClass="formBtn"
						value="#{letterKeywords['btn.label.keyword.addKeyword']}"
						action="#{MaintainKeywordsControllerBean.displayAddKeywordSection}"
						disabled="#{MaintainKeywordsDataBean.updateLtrKeywords}" />
				</m:div>
				<%-- NH DEFECT FIX  ESPRD00709872 --%>
				<m:div sid="showhide_results"
					rendered="#{MaintainKeywordsDataBean.showResultsDiv}">
					<m:div styleClass="infoTitle">
						<%--h:dataTable cellspacing="0" border="0" styleClass="dataTable" width="99%"
							columnClasses="columnClass" headerClass="headerClass" 
							footerClass="footerClass" rowClasses="row_alt,row"
							binding="#{MaintainKeywordsDataBean.keywordHtmlDataTable}"
							value="#{MaintainKeywordsDataBean.keywordList}" rows="5"
							id="LetterKeywordsTable" var="keywordList"
							onmouseover="return tableMouseOver(this, event);"
							onmouseout="return tableMouseOut(this, event);"--%>
						<t:dataTable id="LetterKeywordsTable" width="100%"
							styleClass="dataTable"
							first="#{MaintainKeywordsDataBean.startIndexForCatgry}"
							rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
							rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
							rowOnClick="firstChild.lastChild.click();" rows="10"
							var="keywordList" columnClasses="columnClass"
							headerClass="headerClass" footerClass="footerClass"
							rowClasses="row_alt,row" rowIndexVar="rowIndex"
							value="#{MaintainKeywordsDataBean.keywordList}">

							<h:column id="letterKeywordID">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD167" columns="2">
										<h:outputLabel id="PRGCMGTOLL631" for="panel1"
											styleClass="alignLeft"
											value="#{letterKeywords['label.keyword.keywordId']}" />
										<h:panelGroup id="panel1">
											<t:div styleClass="alignLeft">
												<t:commandLink id="keywordIdAscCommandLink"
													onclick="javascript:flagWarn=false;"
													style="text-decoration: none;"
													actionListener="#{MaintainKeywordsControllerBean.sortColumn}"
													rendered="#{MaintainKeywordsDataBean.imageRender != 'keywordIdAscCommandLink'}">
													<%--<h:graphicImage alt="for ascending" styleClass="sort_asc"
														width="8" /> --%>
													<m:div title="for ascending" styleClass="sort_asc" />
													<f:attribute name="columnName" value="KEYWORD_ID" />
													<f:attribute name="sortOrder" value="asc" />
													<%--h:outputText value="Asc"/--%>
												</t:commandLink>
											</t:div>
											<t:div styleClass="alignLeft">
												<t:commandLink id="keywordIdDescCommandLink"
													onclick="javascript:flagWarn=false;"
													style="text-decoration: none;"
													actionListener="#{MaintainKeywordsControllerBean.sortColumn}"
													rendered="#{MaintainKeywordsDataBean.imageRender != 'keywordIdDescCommandLink'}">
													<%--<h:graphicImage alt="for descending" styleClass="sort_desc"
														width="8" />--%>
													<m:div title="for descending" styleClass="sort_desc" />
													<f:attribute name="columnName" value="KEYWORD_ID" />
													<f:attribute name="sortOrder" value="desc" />
													<%--h:outputText value="Desc"/--%>
												</t:commandLink>
											</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<%--h:commandLink id="editId" immediate="true" style="None"
									action="#{MaintainKeywordsControllerBean.getKeywordDetails}">
									<h:outputText id="PRGCMGTOT1498" value="#{keywordList.keywordId}" />
									<f:param name="indexCode" value="#{rowIndex}"></f:param>
								</h:commandLink--%>
								<t:commandLink id="editId" immediate="true" style="None"
									onclick="javascript:flagWarn=false;"
									action="#{MaintainKeywordsControllerBean.getKeywordDetails}">
									<h:outputText id="valKeywordID"
										value="#{keywordList.keywordId}" />
									<f:param name="rowNum" value="#{rowIndex}"></f:param>
								</t:commandLink>
							</h:column>
							<h:column id="letterKeywordLabelID">
								<f:facet name="header">
									<h:panelGrid id="PRGCMGTPGD168" columns="2">
										<h:outputLabel id="PRGCMGTOLL632" for="panel2"
											styleClass="alignLeft"
											value="#{letterKeywords['label.keyword.label']}" />
										<h:panelGroup id="panel2">
											<t:div styleClass="alignLeft">
												<t:commandLink id="keywordLabelAscCommandLink"
													onclick="javascript:flagWarn=false;"
													style="text-decoration: none;"
													actionListener="#{MaintainKeywordsControllerBean.sortColumn}"
													rendered="#{MaintainKeywordsDataBean.imageRender != 'keywordLabelAscCommandLink'}">
													<%--<h:graphicImage alt="for ascending" styleClass="sort_asc"
														width="8" /> --%>
													<m:div title="for ascending" styleClass="sort_asc" />
													<f:attribute name="columnName" value="KEYWORD_LABEL" />
													<f:attribute name="sortOrder" value="asc" />
													<%--h:outputText value="Asc"/--%>
												</t:commandLink>
											</t:div>
											<t:div styleClass="alignLeft">
												<t:commandLink id="keywordLabelDescCommandLink"
													onclick="javascript:flagWarn=false;"
													style="text-decoration: none;"
													actionListener="#{MaintainKeywordsControllerBean.sortColumn}"
													rendered="#{MaintainKeywordsDataBean.imageRender != 'keywordLabelDescCommandLink'}">
													<%--<h:graphicImage alt="for descending" styleClass="sort_desc"
														width="8" /> --%>
													<m:div title="for descending" styleClass="sort_desc" />
													<f:attribute name="columnName" value="KEYWORD_LABEL" />
													<f:attribute name="sortOrder" value="desc" />
													<%--h:outputText value="Desc"/--%>
												</t:commandLink>
											</t:div>
										</h:panelGroup>
									</h:panelGrid>
								</f:facet>
								<%--h:commandLink id="editLabel" immediate="true"
									action="#{MaintainKeywordsControllerBean.getKeywordDetails}">
									<h:outputText id="PRGCMGTOT1499" value="#{keywordList.keywordLabel}" />
									<f:param name="indexCode" value="#{rowIndex}"></f:param>
								</h:commandLink--%>
								<%-- ESPRD00696117 for selected row to be highlighted--%>
								<h:outputText id="PRGCMGTOT1500"
									value="#{keywordList.keywordLabel}"
									rendered="#{not empty keywordList.keywordLabel}" />
								<h:outputText id="PRGCMGTOT1500_1" value="-"
									style="visibility:hidden;"
									rendered="#{empty keywordList.keywordLabel}" />
								<%--End of modification: CTS--%>
							</h:column>
						</t:dataTable>
					</m:div>
					<%--<m:div styleClass="searchResults">
						<%--<t:dataScroller id="CMGTTOMDS53" for="LetterKeywordsTable"
							paginator="true" onclick="javascript:flagWarn=false;"
							paginatorActiveColumnStyle="font-weight:bold;"
							paginatorMaxPages="3" immediate="false" pageCountVar="pageCount"
							pageIndexVar="pageIndex" firstRowIndexVar="firstRowIndex"
							lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
							style="float:right;position:relative;bottom:-6px;text-decoration:underline;">
							<f:facet name="previous">
								<h:outputText id="PRGCMGTOT1501"
									style="text-decoration:underline;"
									value="#{letterKeywords['link.label.keyword.previous']}"
									rendered="#{pageIndex > 1}">
								</h:outputText>
							</f:facet>
							<%--ESPRD00696117 row count should not be bold/strong--%>
					<%--<h:outputText id="PRGCMGTOT1502" rendered="#{rowsCount > 0}"
								value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
								style="float:left;position:relative;bottom:-6px;font-weight:normal;" />
							<%--End of modification: CTS--%>
					<%--<f:facet name="next">
								<h:outputText id="PRGCMGTOT1503"
									style="text-decoration:underline;"
									value="#{letterKeywords['link.label.keyword.next']}"
									rendered="#{pageIndex < pageCount}">
								</h:outputText>
							</f:facet>
						</t:dataScroller>--%>
					<%--Pagination starts from here --%>
					<h:panelGrid id="searchinfoDetails" columns="2" style="width:100%;">
						<h:panelGroup id="searchRecordInfo"
							style="text-align: left;width:100%;">
							<h:outputText id="searchRecordInfoText"
								value="#{MaintainKeywordsDataBean.startRecord}-#{MaintainKeywordsDataBean.endRecord} of #{MaintainKeywordsDataBean.count}" />
						</h:panelGroup>
						<h:panelGroup id="searchPageInfo"
							style="text-align:right;width:100%;">
							<t:commandLink id="showPrevious"
								action="#{MaintainKeywordsControllerBean.searchPageNavigation}"
								rendered="#{MaintainKeywordsDataBean.showPrevious}"
								onclick="javascript:flagWarn=false;">
								<f:param name="param" value="showPrevious" />
								<h:outputText id="showPreviousText" escape="false"
									value="&lt&lt" />
								<f:verbatim>&nbsp;</f:verbatim>
							</t:commandLink>
							<t:commandLink id="firstPage"
								action="#{MaintainKeywordsControllerBean.searchPageNavigation}"
								rendered="#{MaintainKeywordsDataBean.renderFirstPage}"
								onclick="javascript:flagWarn=false;">
								<f:param name="param" value="firstPage" />
								<h:outputText id="firstPageText"
									value="#{MaintainKeywordsDataBean.firstPage}"
									rendered="#{!MaintainKeywordsDataBean.boldPageNum[0]}" />
								<h:outputText id="firstPageText1"
									style="color:blue;font-weight:bold;"
									value="#{MaintainKeywordsDataBean.firstPage}"
									rendered="#{MaintainKeywordsDataBean.boldPageNum[0]}" />
								<f:verbatim>&nbsp;</f:verbatim>
							</t:commandLink>
							<t:commandLink id="firstPagePlusOne"
								action="#{MaintainKeywordsControllerBean.searchPageNavigation}"
								rendered="#{MaintainKeywordsDataBean.renderFirstPagePlusOne}"
								onclick="javascript:flagWarn=false;">
								<f:param name="param" value="firstPagePlusOne" />
								<h:outputText id="firstPagePlusOneText"
									value="#{MaintainKeywordsDataBean.firstPage+1}"
									rendered="#{!MaintainKeywordsDataBean.boldPageNum[1]}" />
								<h:outputText id="firstPagePlusOneText1"
									style="color:blue;font-weight:bold;"
									value="#{MaintainKeywordsDataBean.firstPage+1}"
									rendered="#{MaintainKeywordsDataBean.boldPageNum[1]}" />
								<f:verbatim>&nbsp;</f:verbatim>
							</t:commandLink>
							<t:commandLink id="firstPagePlusTwo"
								action="#{MaintainKeywordsControllerBean.searchPageNavigation}"
								rendered="#{MaintainKeywordsDataBean.renderFirstPagePlusTwo}"
								onclick="javascript:flagWarn=false;">
								<f:param name="param" value="firstPagePlusTwo" />
								<h:outputText id="firstPagePlusTwoText"
									value="#{MaintainKeywordsDataBean.firstPage+2}"
									rendered="#{!MaintainKeywordsDataBean.boldPageNum[2]}" />
								<h:outputText id="firstPagePlusTwoText1"
									style="color:blue;font-weight:bold;"
									value="#{MaintainKeywordsDataBean.firstPage+2}"
									rendered="#{MaintainKeywordsDataBean.boldPageNum[2]}" />
								<f:verbatim>&nbsp;</f:verbatim>
							</t:commandLink>
							<t:commandLink id="showNext"
								action="#{MaintainKeywordsControllerBean.searchPageNavigation}"
								rendered="#{MaintainKeywordsDataBean.showNext}"
								onclick="javascript:flagWarn=false;">
								<f:param name="param" value="showNext" />
								<h:outputText id="showNextText" value=">>" />
							</t:commandLink>
						</h:panelGroup>
					</h:panelGrid>

					<%--Pagination end from here --%>
				</m:div>
				<m:br />
				<%--</m:div>--%>
				<m:br />
				<m:div styleClass="moreInfo"
					rendered="#{MaintainKeywordsDataBean.renderKeywordDetailsPage}">
					<m:div styleClass="moreInfoBar">
						<m:table id="PROVIDERMMT20120731164811606" border="0" width="100%">
							<m:tr>
								<m:td styleClass="alignLeft">

									<m:div styleClass="infoTitle"
										rendered="#{MaintainKeywordsDataBean.renderAddKeyword}">
										<h:outputText id="PRGCMGTOT1504"
											value="#{letterKeywords['title.label.keyword.addKeyword']}" />
									</m:div>
									<m:div styleClass="infoTitle"
										rendered="#{MaintainKeywordsDataBean.renderEditKeyword}">
										<h:outputText id="PRGCMGTOT1505"
											value="#{letterKeywords['title.label.keyword.editKeyword']}" />
									</m:div>
								</m:td>
								<m:td styleClass="alignRight">
									<m:div styleClass="infoActions">
										<h:panelGroup id="PRGCMGTPGP114">
											<%--h:commandLink styleClass="strong"
												action="#{MaintainKeywordsControllerBean.saveKeywordDetails}">
												<h:outputText id="PRGCMGTOT1506" value="#{ent['label-sw-save']}" />
											</h:commandLink--%>
											<%--below command button added for CR 1825--%>
											<%--ESPRD00696117 for making Save bold--%>
											<%--Modified for the Defect ESPRD00814205--%>
											<h:commandButton id="editSaveButton"
												onclick="javascript:flagWarn=false;"
												action="#{MaintainKeywordsControllerBean.saveKeywordDetails}"
												value="#{ent['label-sw-save']}"
												disabled="#{MaintainKeywordsDataBean.updateLtrKeywords}"
												style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:14px;WIDTH:35px;BORDER:0;text-align:left;font-family: Verdana;font-weight:bold;font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal">
												<%--End of modification: CTS--%>
											</h:commandButton>
											<h:outputText id="PRGCMGTOT1507" escape="false"
												value="#{ref['label.ref.linkSeperator']}" />
											<%--h:commandLink styleClass="commandLink"
												action="#{MaintainKeywordsControllerBean.resetPage}" immediate="true">
												<h:outputText id="PRGCMGTOT1508" value="#{ent['label-sw-reset']}" />
											</h:commandLink--%>
											<%--below command button added for CR 1825--%>
											<h:commandButton id="editResetButton"
												action="#{MaintainKeywordsControllerBean.resetPage}"
												onclick="javascript:flagWarn=false;"
												value="#{ent['label-sw-reset']}"
												disabled="#{MaintainKeywordsDataBean.updateLtrKeywords}"
												style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:14px;WIDTH:35px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal">
											</h:commandButton>
											<h:outputText id="PRGCMGTOT1509" escape="false"
												value="#{ref['label.ref.linkSeperator']}" />
											<t:commandLink id="PRGCMGTCL222" styleClass="commandLink"
												action="#{MaintainKeywordsControllerBean.cancelPage}">
												<h:outputText id="PRGCMGTOT1510"
													value="#{ent['label-sw-cancel']}" />
											</t:commandLink>
										</h:panelGroup>
									</m:div>
								</m:td>
							</m:tr>
						</m:table>
					</m:div>
					<m:div styleClass="moreInfoContent">
						<m:div styleClass="padb">
							<m:div styleClass="msgBox"
								rendered="#{MaintainKeywordsDataBean.showSuccessMessage}">
								<h:outputText id="PRGCMGTOT1511"
									value="#{ent['label-sw-success']}" />
							</m:div>
							<m:div>
								<m:table id="PROVIDERMMT20120731164811607" cellspacing="0" border="0">
									<m:tr>
										<m:td>
											<m:table id="PROVIDERMMT20120731164811608" cellspacing="4" border="0">
												<m:tr>
													<m:td width="40%">
														<h:outputLabel id="PRGCMGTOLL633"
															value="#{letterKeywords['label.keyword.keywordId']}" for="keywordId" />
													</m:td>
													<m:td width="20%">
														<h:outputLabel id="PRGCMGTOLL634"
															value="#{letterKeywords['label.keyword.Type']}" for="keywordTypeEnable" />
													</m:td>
													<m:td width="40%">
														<h:outputLabel id="PRGCMGTOLL635"
															value="#{letterKeywords['label.keyword.label']}" for="keywordLabel"/>
													</m:td>
												</m:tr>
												<m:tr>
													<%--ESPRD00696117 for label alignment--%>
													<m:div styleClass="infoTitle"
														rendered="#{MaintainKeywordsDataBean.renderEditKeyword}">
														<m:td>
															<h:outputText id="PRGCMGTOT1512"
																value="#{MaintainKeywordsDataBean.letterKeywordVO.keywordId}" />
														</m:td>
													</m:div>
													<m:div styleClass="infoTitle"
														rendered="#{MaintainKeywordsDataBean.renderAddKeyword}">
														<m:td>
															<h:inputText id="keywordId" size="30" maxlength="50"
																value="#{MaintainKeywordsDataBean.letterKeywordVO.keywordId}" />
														</m:td>
													</m:div>


													<m:div styleClass="infoTitle" id="keywordTypeId">
														<m:td>
															<!-- 
															- Release: NH Defect Fix
															- Date: 9/07/2011
															- Author : CTS
															- Defect ID # : ESPRD00686737
															- Purpose : Commneted and modified code segment for proper working of Data Loss Warning message. 					
														-->
															<%--<h:selectOneMenu id="keywordTypeEnable"			
															value="#{MaintainKeywordsDataBean.letterKeywordVO.keywordTypeCode}" 
															onclick="javascript:flagWarn=false;"	 onchange="enableField()">
																<f:selectItems
																	value="#{MaintainKeywordsDataBean.keywordTypesList}" />
															</h:selectOneMenu>--%>
															<h:selectOneMenu id="keywordTypeEnable"
																value="#{MaintainKeywordsDataBean.letterKeywordVO.keywordTypeCode}"
																onchange="enableField()">
																<f:selectItems
																	value="#{MaintainKeywordsDataBean.keywordTypesList}" />
															</h:selectOneMenu>
														</m:td>
													</m:div>


													<m:div styleClass="infoTitle">
														<m:td>
															<h:inputText id="keywordLabel" size="30" maxlength="250"
																value="#{MaintainKeywordsDataBean.letterKeywordVO.keywordLabel}" />
														</m:td>
													</m:div>

												</m:tr>
												<m:tr>
													<m:td>
														<h:outputLabel id="PRGCMGTOLL636"
															value="#{letterKeywords['label.keyword.shortDescription']}" for="keywordShortDesc" />
													</m:td>
													<m:td colspan="2">
														<h:outputLabel id="PRGCMGTOLL637"
															value="#{letterKeywords['label.keyword.longDescription']}" for="keywordLongDesc"/>
													</m:td>
												</m:tr>
												<m:tr>

													<m:div styleClass="infoTitle">
														<m:td>
															<h:inputText id="keywordShortDesc" size="20"
																maxlength="10"
																value="#{MaintainKeywordsDataBean.letterKeywordVO.keywordShortDesc}" />
														</m:td>
													</m:div>


													<m:div styleClass="infoTitle">
														<m:td colspan="2">
															<h:inputText id="keywordLongDesc" size="40"
																maxlength="40"
																value="#{MaintainKeywordsDataBean.letterKeywordVO.keywordLongDesc}" />
														</m:td>
													</m:div>

												</m:tr>
												<m:tr>

													<m:div id="inputLengthDiv">
														<m:td>
															<h:outputLabel id="inputLengthLable"
																value="#{letterKeywords['label.keyword.inputlength']}" for="inputLength"/>
															<m:br id="inputLengthBr" />
															<h:inputText id="inputLength" size="20" maxlength="4"
																value="#{MaintainKeywordsDataBean.letterKeywordVO.displaySize}" />
														</m:td>
													</m:div>
													<%--added as part of CR 827474 --%>
													<m:div styleClass="infoTitle">
														<m:td colspan="2">
															<h:outputLabel id="keyWdDataTypCd"
																value="#{letterKeywords['label.keyword.datatypecode']}"
																for="keyWdDataTyCdSelect" />
															<m:br id="keywordDataTypeCdbr" />
															<h:selectOneMenu id="keyWdDataTyCdSelect"
																value="#{MaintainKeywordsDataBean.letterKeywordVO.keyWordDataTyCd}">
																<f:selectItems
																	value="#{MaintainKeywordsDataBean.keywordDataTypesList}" />
															</h:selectOneMenu>
														</m:td>
													</m:div>
													
													

												</m:tr>
											</m:table>
										</m:td>
									</m:tr>

									<m:tr>
										<m:td width="100%">
											<m:table id="PROVIDERMMT20120731164811609">
												<m:tr>
													<m:td>
														<h:outputLabel id="PRGCMGTOLL638"
															value="#{letterKeywords['label.keyword.availableTemplates']}" for="availableTemplates" />
													</m:td>
													<m:td>
														<h:outputText id="PRGCMGTOT1513" value=" "></h:outputText>
													</m:td>
													<m:td>
														<h:outputLabel id="PRGCMGTOLL639"
															value="#{letterKeywords['label.keyword.associatedTemplates']}" for="associatedTemplates" />
													</m:td>
												</m:tr>
												<m:tr>

													<m:div styleClass="infoTitle">
														<m:td>

															<h:selectManyListbox id="availableTemplates" size="10"
																onclick="javascript:flagWarn=false;"
																value="#{MaintainKeywordsDataBean.selectedTemplatesFromAvailable}"
																disabled="#{MaintainKeywordsDataBean.updateLtrKeywords}">
																<f:selectItems
																	value="#{MaintainKeywordsDataBean.availableTemplatesAsSelectItems}" />
															</h:selectManyListbox>
														</m:td>
													</m:div>

													<m:td>
														<m:div align="right">
															<h:commandButton id="PRGCMGTCB79" styleClass="formBtn"
																value="#{letterKeywords['btn.label.keyword.moveright']}"
																onclick="enableField();flagWarn=false;"
																action="#{MaintainKeywordsControllerBean.associateSelectedTemplatesWithKeyword}"
																disabled="#{MaintainKeywordsDataBean.updateLtrKeywords}" />
														</m:div>
														<m:br />
														<m:div align="right">
															<h:commandButton id="PRGCMGTCB80" styleClass="formBtn"
																value="#{letterKeywords['btn.label.keyword.moveleft']}"
																onclick="enableField();flagWarn=false;"
																action="#{MaintainKeywordsControllerBean.disAssociateSelectedTemplatesWithKeyword}"
																disabled="#{MaintainKeywordsDataBean.updateLtrKeywords}" />
														</m:div>
													</m:td>

													<m:div styleClass="infoTitle">
														<m:td>
															<h:selectManyListbox id="associatedTemplates" size="10"
																onclick="javascript:flagWarn=false;"
																value="#{MaintainKeywordsDataBean.selectedTemplatesFromAssociated}"
																disabled="#{MaintainKeywordsDataBean.updateLtrKeywords}">
																<f:selectItems
																	value="#{MaintainKeywordsDataBean.associatedTemplatesAsSelectItems}" />
															</h:selectManyListbox>
														</m:td>
													</m:div>
													<%--End of modification: CTS--%>
												</m:tr>
											</m:table>
										</m:td>
									</m:tr>
								</m:table>
							</m:div>
						</m:div>
						<%--Audit Log Section Start --%>
						<m:div id="LtrKywdsDetailsAuditDivOne"
							rendered="#{MaintainKeywordsDataBean.auditLogFlag}">
							<f:subview id="LtrKywdsDetailsAuditSubView">
								<m:div id="LtrKywdsDetailsAuditDivTwo">
									<audit:auditTableSet id="LtrKywdsDetailsAuditId"
										value="#{MaintainKeywordsDataBean.letterKeywordVO.auditKeyList}"
										numOfRecPerPage="10"></audit:auditTableSet>
								</m:div>
							</f:subview>
						</m:div>
						<%--Audit Log Section End --%>
					</m:div>
				</m:div>
			</m:section>
			<%--</m:table>--%>
		</m:body>
	</h:form>
	</body>
</f:view>
