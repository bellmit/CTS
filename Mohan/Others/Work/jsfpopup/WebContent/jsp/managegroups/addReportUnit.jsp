<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactManageGroup"
	var="msg" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />

<SCRIPT type="text/javascript">
		
		/*Small save Big Save start*/
		frmId = 'view<portlet:namespace/>:AddReportUnit';
		/*Small save Big Save ends*/
		
		var sliderState='closed';
		function doSlider(formID){
			var el = document.getElementById('view<portlet:namespace/>:'+formID+':'+"main_pod");
			var slide=document.getElementById('view<portlet:namespace/>:'+formID+':'+"slider");
			var treePod=document.getElementById('view<portlet:namespace/>:'+formID+':'+"navigator_pod");
			var status=document.getElementById('view<portlet:namespace/>:'+formID+':'+"sliderStatus");
			alert(status);
			if (sliderState == 'open'){
			  treePod.style.display = '';
			  el.style.width = "73%";
			  sliderState = 'closed';
			  status.value = 'open';
			  slide.innerHTML = '&lt;'
			}
			else{
			  treePod.style.display = 'none';
			  el.style.width = "97%";
			  sliderState = 'open';
			  status.value = 'closed';
			  slide.innerHTML = '&gt;'
			}
		}
		
		function showHide(){
			var el = document.getElementById('view<portlet:namespace/>:AddReportUnit:'+"main_pod");
			var slide=document.getElementById('view<portlet:namespace/>:AddReportUnit:'+"slider");
			var treePod=document.getElementById('view<portlet:namespace/>:AddReportUnit:'+"navigator_pod");
			var status=document.getElementById('view<portlet:namespace/>:AddReportUnit:'+"sliderStatus").value;
			if (status == 'open'){
			  treePod.style.display = '';
			  el.style.width = "73%";
			  slide.innerHTML = '&lt;'
			}
			else{
			  treePod.style.display = 'none';
			  el.style.width = "97%";
			  slide.innerHTML = '&gt;'
			}
		}				
	
	function doaddReportUnitClickAction(link)
	{						
		var answer = confirm("Are you sure you want to navigate away from page?");
		if(answer)
		{
			link.fireEvent('onclick');
		}
	}	
</SCRIPT>

<f:view>

	<%-- Small save Big Save start --%>
	<f:subview id="repUnitService">
		<jsp:include page="/jsp/commonEntities/commonScripts.jsp" />
	</f:subview>

	<%-- Small save Big Save ends --%>

	<body onBeforeUnload="warnBeforeExit(frmId);">
	<h:form id="AddReportUnit">
		<h:inputHidden id="sliderStatus"
			value="#{lobHierarchyDataBean.sliderStatus}"></h:inputHidden>
		<m:div styleClass="infoTitle" align="left">
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1553" value="#{ent['label-sw-reqFld']}"
					styleClass="errorMessage" />
			</m:span>
		</m:div>
		<m:table id="PROVIDERMMT20120731164811619" width="100%">
			<m:tr>
				<m:td id="navigator_pod" styleClass="navigator_pod">
					<m:div>
						<f:subview id="bpTree">
							<jsp:include page="/jsp/managegroups/lobHierachy.jsp" />
						</f:subview>
					</m:div>
				</m:td>
				<m:td id="slider" align="center" styleClass="slider"
					onmousedown="doSlider('AddReportUnit')"
					title="#{msg['label.lob.slider']}">
					<f:verbatim>
						<h:outputText id="shift" value="#{msg['label.lob.lshift']}" />
					</f:verbatim>
				</m:td>
				<m:td styleClass="sliderSpace"></m:td>
				<m:td id="main_pod" styleClass="main_pod">
					<m:div id="addRep" rendered="#{lobHierarchyDataBean.flag}">
						<m:section id="PROVIDERMMS20120731164811620" styleClass="otherbg">
							<m:legend>
								<f:verbatim>
									<h:outputText id="PRGCMGTOT1554" styleClass="strong"
										value="#{msg['label.lob.addReport']}" />
								</f:verbatim>
							</m:legend>
							<m:div styleClass="infoActions">
								<t:commandLink id="cancelAddReport"
									style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:42px;BORDER:0;text-align:left;font-family: Verdana; font-size: 10px;clip: rect(auto, auto, 0px, auto);  vertical-align: top;word-spacing: normal;position:relative;top:-25px"
									onmousedown="if(event.button==1) flagWarn=false; if(event.button==1) doaddReportUnitClickAction(this); flagWarn=true"
									action="#{lobHierarchyControlBean.cancelEditUnit}">
									<h:outputText id="PRGCMGTOT1555"
										value="#{msg['label.lob.cancelbutton']}" />
								</t:commandLink>
							</m:div>
							<m:div styleClass="infoActions">
								<h:outputText id="PRGCMGTOT1556" escape="false"
									style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:10px;BORDER:0;text-align:left;font-family: Verdana; font-size: 10px;clip: rect(auto, auto, 0px, auto);  vertical-align: top;word-spacing: normal;position:relative;top:-25px"
									value="|" />
							</m:div>
							<m:div styleClass="infoActions">
								<t:commandLink id="radd1"
									style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;font-weight:bold;FONT-SIZE:12px;WIDTH:33px;BORDER:0;text-align:left;font-family: Verdana; font-size: 10px;clip: rect(auto, auto, 0px, auto);  vertical-align: top;word-spacing: normal;position:relative;top:-25px"
									onmousedown="if(event.button==1) flagWarn=false;"
									action="#{lobHierarchyControlBean.addReportUnit}">
									<h:outputText id="PRGCMGTOT1557"
										value="#{msg['label.lob.savebutton']}" />
								</t:commandLink>
							</m:div>
							<m:div styleClass="msgBox"
									rendered="#{lobHierarchyDataBean.showSucessMessage}">
									<h:outputText id="PRGCMGTOT1558"
										value="#{ent['label-sw-success']}" />
								</m:div>
								<h:messages id="PRGCMGTMS25" showDetail="true"
									showSummary="false" styleClass="errorMessage" layout="table" />
								<m:table id="PROVIDERMMT20120731164811621" styleClass="dataTable" width="100%">
									<m:tr>
										<m:td>
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT1559"
													value="#{ref['label.ref.reqSymbol']}" />
											</m:span>
											
											<%--Code Modified for the defect ESPRD00722464 on 01/19/2012-Start --%>
											
											<h:outputLabel id="reporting_name" for="name1"
												value="#{msg['label.lob.descName']}" />
											<m:br></m:br>
											<h:inputText id="name1"
												value="#{lobHierarchyDataBean.lobHieracVO.lobDesc}" />
											
											<m:br></m:br>
											<h:message id="PRGCMGTM2229" for="reporting_name"
												showDetail="true" styleClass="errorMessage" />
												
												<%--Code Modified for the defect ESPRD00722464 on 01/19/2012-End --%>
												
										</m:td>
									</m:tr>
								</m:table>
							</m:section>
					</m:div>
				</m:td>
			</m:tr>
		</m:table>
		<t:saveState id="CMGTTOMSS470" value="#{lobHierarchyDataBean.flag}"></t:saveState>
		<t:saveState id="CMGTTOMSS471" value="#{lobHierarchyDataBean.idName}"></t:saveState>
		<t:saveState id="CMGTTOMSS472"
			value="#{lobHierarchyDataBean.showSucessMessage}"></t:saveState>
		<t:saveState id="CMGTTOMSS473"
			value="#{lobHierarchyDataBean.sliderStatus}"></t:saveState>
	</h:form>
	</body>
	<f:verbatim>
		<SCRIPT type="text/javascript">
		showHide();
		</SCRIPT>
	</f:verbatim>
</f:view>


