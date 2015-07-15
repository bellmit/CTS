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
		frmId = 'view<portlet:namespace/>:AddBusinessUnit';
		/*Small save Big Save ends*/
		
		var sliderState='closed';
		function doSlider(formID){
			var el = document.getElementById('view<portlet:namespace/>:'+formID+':'+"main_pod");
			var slide=document.getElementById('view<portlet:namespace/>:'+formID+':'+"slider");
			var treePod=document.getElementById('view<portlet:namespace/>:'+formID+':'+"navigator_pod");
			var status=document.getElementById('view<portlet:namespace/>:'+formID+':'+"sliderStatus");
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
			var el = document.getElementById('view<portlet:namespace/>:editReportUnit:'+"main_pod");
			var slide=document.getElementById('view<portlet:namespace/>:editReportUnit:'+"slider");
			var treePod=document.getElementById('view<portlet:namespace/>:editReportUnit:'+"navigator_pod");
			var status=document.getElementById('view<portlet:namespace/>:editReportUnit:'+"sliderStatus").value;
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
		
		function doAddBussinessUnitCancelAction(link)
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

	<body>
	<h:form id="AddBusinessUnit">

		<h:inputHidden id="controlRequiredForAddBusinessunit"
			value="#{lobHierarchyControlBean.controlRequired}"></h:inputHidden>

		<h:inputHidden id="sliderStatus"
			value="#{lobHierarchyDataBean.sliderStatus}"></h:inputHidden>
		<m:div styleClass="infoTitle" align="left">
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1538" value="#{ent['label-sw-reqFld']}"
					styleClass="errorMessage" />
			</m:span>
		</m:div>
		<m:table id="PROVIDERMMT20120731164811613" width="100%">
			<m:tr>
				<m:td id="navigator_pod" styleClass="navigator_pod">
					<m:div align="left">
						<f:subview id="bpTree">
							<jsp:include page="/jsp/managegroups/lobHierachy.jsp" />
						</f:subview>
					</m:div>
				</m:td>
				<m:td id="slider" align="center" styleClass="slider"
					onmousedown="doSlider('AddBusinessUnit')"
					title="#{msg['label.lob.slider']}">
					<f:verbatim>
						<h:outputText id="shift" value="#{msg['label.lob.lshift']}" />
					</f:verbatim>
				</m:td>
				<m:td styleClass="sliderSpace"></m:td>
				<m:td id="main_pod" styleClass="main_pod">
					<m:div id="addBuss" rendered="#{lobHierarchyDataBean.flag}">
						<m:section id="PROVIDERMMS20120731164811614" styleClass="otherbg">
							<m:legend>
								<f:verbatim>
									<h:outputText id="PRGCMGTOT1539" styleClass="strong"
										value="#{msg['label.lob.addBusiness']}" />
								</f:verbatim>
							</m:legend>
							<m:div styleClass="infoActions">
								<t:commandLink id="bcan11"
									style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:42px;BORDER:0;text-align:left;font-family: Verdana; font-size: 10px;clip: rect(auto, auto, 0px, auto);  vertical-align: top;word-spacing: normal;position:relative;top:-25px"
									action="#{lobHierarchyControlBean.cancelEditUnit}">
									<h:outputText id="PRGCMGTOT1540"
										value="#{msg['label.lob.cancelbutton']}" />
								</t:commandLink>
							</m:div>
							<m:div styleClass="infoActions">
								<h:outputText id="PRGCMGTOT1541" escape="false"
									style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:10px;BORDER:0;text-align:left;font-family: Verdana; font-size: 10px;clip: rect(auto, auto, 0px, auto);  vertical-align: top;word-spacing: normal;position:relative;top:-25px"
									value="|" />
							</m:div>
							<m:div styleClass="infoActions">
								<t:commandLink id="badd"
									style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;font-weight:bold;FONT-SIZE:12px;WIDTH:33px;BORDER:0;text-align:left;font-family: Verdana; font-size: 10px;clip: rect(auto, auto, 0px, auto);  vertical-align: top;word-spacing: normal;position:relative;top:-25px"
									onmousedown="if(event.button==1) flagWarn=false;"
									action="#{lobHierarchyControlBean.addBusinessUnit}">
									<h:outputText id="PRGCMGTOT1542"
										value="#{msg['label.lob.savebutton']}" />
								</t:commandLink>
							</m:div>
							<m:div styleClass="msgBox"
									rendered="#{lobHierarchyDataBean.showSucessMessage}">
									<h:outputText id="PRGCMGTOT1543"
										value="#{ent['label-sw-success']}" />
								</m:div>
								<h:messages id="PRGCMGTMS23" showDetail="true"
									showSummary="false" styleClass="errorMessage" layout="table" />
								<m:table id="PROVIDERMMT20120731164811615" styleClass="dataTable" width="100%">
									<m:tr>
										<m:td>
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT1544"
													value="#{ref['label.ref.reqSymbol']}" />
											</m:span>
											<%--Code Modified for the defect ESPRD00722464 on 01/19/2012-Start --%>
											
											<h:outputLabel id="LobHierarchyName2" for="name2"
												value="#{msg['label.lob.descName']}" />
											<m:br></m:br>
											<h:inputText id="name2"
												value="#{lobHierarchyDataBean.lobHieracVO.lobDesc}" />
											
											<m:br></m:br>
												<h:message id="PRGCMGTM216" for="LobHierarchyName2"
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
		<t:saveState id="CMGTTOMSS460" value="#{lobHierarchyDataBean.flag}"></t:saveState>
		<t:saveState id="CMGTTOMSS461" value="#{lobHierarchyDataBean.idName}"></t:saveState>
		<t:saveState id="CMGTTOMSS462"
			value="#{lobHierarchyDataBean.showSucessMessage}"></t:saveState>
		<t:saveState id="CMGTTOMSS463"
			value="#{lobHierarchyDataBean.sliderStatus}"></t:saveState>
	</h:form>
	<%-- var thisForm : stores the form reference. Form reference is use to get  the computed  role stored in hidden form field.
         FlagRole : Indicator to store role information .Stores 'False' for inquire only users and 'True' for other user.
	--%>
	<script type="text/JavaScript">

   var thisForm = 'view<portlet:namespace/>:AddBusinessUnit';

   flagRole =((typeof (document.getElementById(thisForm+': controlRequiredForAddBusinessunit) !=  'undefined')) && document.getElementById(thisForm+':controlRequiredForAddBusinessunit) != null ?  document.getElementById(thisForm+':controlRequiredForAddBusinessunit).value:true);

</script>
	</body>
	<f:verbatim>
		<SCRIPT type="text/javascript">
		showHide();
		</SCRIPT>
	</f:verbatim>
</f:view>
