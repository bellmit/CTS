<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<script type="text/javascript">

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
 

function renderAudit(id1) 
{ 
	var hiddenValueCI = document.getElementById('view<portlet:namespace/>:sysparamAD:auditlogDetails:'+id1);
	
    
	if ((hiddenValueCI == null) ||(hiddenValueCI == '')|| (hiddenValueCI.length == 0)) 
	{
		hideMe('audit_plus');
	} else if ((hiddenValueCI.value == 'false')) 
	{
		hideMe('audit_plus');
	} 

	
}
function editLobCancel(link)
{
	if (isFormChanged(frmId) == true)
	{
  		var answer = confirm("Unsaved changes will be lost.  Are you sure that you want to navigate?");				
		if(answer)
		{						
			var entObject = findObjectByPartOfID(link);
			entObject.click();
		}
	}
	else
	{
		var entObject = findObjectByPartOfID(link);
		entObject.click();
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
function doEditLobClickAction(link)
		{						
			var answer = confirm("Are you sure you want to navigate away from page?");
			if(answer)
			{
				link.fireEvent('onclick');
			}
		}	
</script>
<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactManageGroup"
	var="msg" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.GlobalAuditLabels"
	var="msgAudit" />


<SCRIPT type="text/javascript">
		
		/*Small save Big Save start*/
		frmId = 'view<portlet:namespace/>:EditLOB';
		/*Small save Big Save ends*/

		var sliderState='closed';
		function doSlider(formID){
			var el = document.getElementById('view<portlet:namespace/>:'+formID+':'+"main_pod");
			var treePod=document.getElementById('view<portlet:namespace/>:'+formID+':'+"navigator_pod");
			var slide=document.getElementById('view<portlet:namespace/>:'+formID+':'+"slider");
			var status=document.getElementById('view<portlet:namespace/>:'+formID+':'+"sliderStatus");
			if (sliderState == 'open'){
			  treePod.style.display = '';
			  el.style.width = "73%";
			  sliderState = 'closed';
			  status.value='open';
			  slide.innerHTML = '&lt;'
			}
			else{
			  treePod.style.display = 'none';
			  el.style.width = "97%";
			  sliderState = 'open';
			  status.value='closed';
			  slide.innerHTML = '&gt;'
			}
		}
		
		function showHide(){
			var el = document.getElementById('view<portlet:namespace/>:EditLOB:'+"main_pod");
			var slide=document.getElementById('view<portlet:namespace/>:EditLOB:'+"slider");
			var treePod=document.getElementById('view<portlet:namespace/>:EditLOB:'+"navigator_pod");
			var status=document.getElementById('view<portlet:namespace/>:EditLOB:'+"sliderStatus").value;
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
</SCRIPT>

<f:view>

	<%-- Small save Big Save start --%>
	<f:subview id="lobService">
		<jsp:include page="/jsp/commonEntities/commonScripts.jsp" />
	</f:subview>
	<%-- Small save Big Save ends --%>

	<body onBeforeUnload="warnBeforeExit(frmId);">
	<h:inputHidden id="loadUserPermission"			value="#{lobHierarchyControlBean.loadUserPermission}"></h:inputHidden>
	<h:form id="EditLOB">
		<h:inputHidden id="sliderStatus"			value="#{lobHierarchyDataBean.sliderStatus}"></h:inputHidden>
		<m:div styleClass="infoTitle" align="left">
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1580" value="#{ent['label-sw-reqFld']}"					styleClass="errorMessage" />
			</m:span>
		</m:div>
		<m:div>
			<m:table id="PROVIDERMMT20120731164811630" width="100%">
				<m:tr>
				<%--Fix for Defect ID : ESPRD00678595 --%>
					<m:td id="navigator_pod" styleClass="navigator_pod" onmousedown="javascript:flagWarn=false;">
						<m:div align="left">
							<f:subview id="bpTree">
								<jsp:include page="/jsp/managegroups/lobHierachy.jsp" />
							</f:subview>
						</m:div>
					</m:td>
					<m:td id="slider" align="center" styleClass="slider"
						onmousedown="doSlider('EditLOB');"
						title="#{msg['label.lob.slider']}">
						<m:div styleClass="otherbg" align="center"
							rendered="#{lobHierarchyDataBean.flag}">
							<f:verbatim>
								<h:outputText id="shift" value="#{msg['label.lob.lshift']}" />
							</f:verbatim>
						</m:div>
					</m:td>
					<m:td styleClass="sliderSpace"></m:td>
					<m:td id="main_pod" styleClass="main_pod">
						<h:messages id="PRGCMGTMS28"></h:messages>
						<m:div id="editLob" rendered="#{lobHierarchyDataBean.flag}">
							<m:section id="PROVIDERMMS20120731164811631" styleClass="otherbg">
								<m:legend>
									<f:verbatim>
										<h:outputText id="PRGCMGTOT1581" styleClass="strong"											value="#{msg['title.label.managegroups.editLOB']}"  />
									</f:verbatim>
								</m:legend>
								<m:div styleClass="infoActions">
								<h:commandButton id="cancel"										 action="#{lobHierarchyControlBean.cancelEditUnit}"				                         										 style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:45px;BORDER:0;text-align:left;font-family: Verdana; font-size: 10px;clip: rect(auto, auto, 0px, auto);  vertical-align: top;word-spacing: normal;position:relative;top:-25px"									     onmousedown="if(event.button==1) flagWarn=false; if(event.button==1) editLobCancel('cancel');flagWarn=true" 										 value="#{msg['label.lob.cancelbutton']}"></h:commandButton>
								
									
								</m:div>
								<m:div styleClass="infoActions">
									<h:outputText id="PRGCMGTOT1582" escape="false" style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:10px;BORDER:0;text-align:left;font-family: Verdana; font-size: 10px;clip: rect(auto, auto, 0px, auto);  vertical-align: top;word-spacing: normal;position:relative;top:-25px"										value="|" />
								</m:div>
								<m:div styleClass="infoActions">
									<%--<h:commandLink id="ar"										onmousedown="if(event.button==1) flagWarn=false;"										action="#{lobHierarchyControlBean.addReportPage}">
										<h:outputText id="PRGCMGTOT1583" value="#{msg['label.lob.addReport']}" />
										</h:commandLink>--%>

									<h:commandButton id="PRGCMGTCB83"										onmousedown="if(event.button==1) flagWarn=false;"										style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:112px;BORDER:0;text-align:left;font-family: Verdana; font-size: 10px;clip: rect(auto, auto, 0px, auto);  vertical-align: top;word-spacing: normal;position:relative;top:-25px"										disabled="#{!lobHierarchyControlBean.controlRequired}"										value="#{msg['label.lob.addReport']}"										action="#{lobHierarchyControlBean.addReportPage}" />

								</m:div>
								<m:table id="PROVIDERMMT20120731164811632" styleClass="dataTable" width="100%">
									<m:tr>
										<m:td>
											<h:outputLabel id="LobHierarchyName" for="name"												value="#{msg['label.lob.descName']}" />
											<m:br></m:br>
											<h:inputText id="name"												value="#{lobHierarchyDataBean.lobHieracVO.lobHieracParentVO.lobDesc}"												disabled="true" readonly="true" />
										</m:td>
									</m:tr>
								</m:table>
							</m:section>
						</m:div>
					</m:td>
				</m:tr>
			</m:table>
		</m:div>
		<t:saveState id="CMGTTOMSS483" value="#{lobHierarchyDataBean.flag}"></t:saveState>
		<t:saveState id="CMGTTOMSS484" value="#{lobHierarchyDataBean.idName}"></t:saveState>
		<t:saveState id="CMGTTOMSS485" value="#{lobHierarchyDataBean.sliderStatus}"></t:saveState>
	</h:form>
	</body>
	<f:verbatim>
		<SCRIPT type="text/javascript">
		showHide();
		</SCRIPT>
	</f:verbatim>
</f:view>


