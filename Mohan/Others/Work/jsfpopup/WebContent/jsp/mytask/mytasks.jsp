<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<portlet:defineObjects />
<script type="text/javascript">

var myCrcheck;
var myCaseCrCheck;
function myTaskTabChange()
{
	var formId = 'view<portlet:namespace/>:MyTaskFormId';
	var obj = document.getElementById(formId+':crDet:crAlertCancelId');
	var caseObj = document.getElementById(formId+':grps:caseCancelId');
	if(obj!=null && myCrcheck == null)
	{
		obj.click();
		myCrcheck = null;
	}
	if(caseObj!=null && myCaseCrCheck == null)
	{
		caseObj.click();
		myCaseCrCheck = null;
	}
	return true;
}
function linkCheck(obj)
{
	
	myCrcheck = obj;
}
function linkCheckforCR(obj)
{
	
	myCaseCrCheck = obj;
}
</script>
<f:view>
	
<hx:scriptCollector>	
	<m:body >
	<h:form id="MyTaskFormId">
	
	<h:messages id="PRGCMGTMS30" showDetail="true" layout="table" showSummary="false"				styleClass="errorMessage" />
		
			<h:inputHidden id="PRGCMGTIH30" value="#{myTaskControllerBean.allData}" ></h:inputHidden>
		
			<m:div styleClass="tabs">
				<%--540203_Performance_Fix_23Dec10 starts::: --%>	
				<%--<t:panelTabbedPane bgcolor="#FFFFCC" width="95%" id="mytasktabbed" binding="#{myTaskDataBean.htmlPanelTabbedPane}"								serverSideTabSwitch="true">--%>
								<%--Added for heapdump issue start --%>
							<t:panelTabbedPane width="100%" id="mytasktabbed"												selectedIndex="#{myTaskDataBean.tabIndex}" serverSideTabSwitch="true" >
												<%--Added for heapdump issue end --%>
							<t:panelTab id="myalert" label="#{messageBean.myTaskMap['label.myTask.alert']}">
									<m:div rendered="#{myTaskDataBean.mytaskAlertFlag}">
										<f:subview id="aler">
											<jsp:include page="inc_alerts.jsp" />
										</f:subview>
									</m:div>
							</t:panelTab>
						<t:panelTab id="myCr" label="#{messageBean.myTaskMap['label.myTask.myCRs']}">
									<m:div rendered="#{myTaskDataBean.mytaskCRsFlag}">
										<f:subview id="myCrs">
										<jsp:include page="inc_myCRs.jsp" />
										</f:subview>
									</m:div>
							</t:panelTab>
						<t:panelTab id="myGrpCrs" label="#{messageBean.myTaskMap['label.myTask.grpCRs']}">
									<m:div rendered="#{myTaskDataBean.mytaskGrpCRsFlag}">
										<f:subview id="myGrpCr">
										<jsp:include page="inc_groupCRs.jsp" />
										</f:subview>
									</m:div>
							</t:panelTab>
					<t:panelTab id="myCrswaatch" label="#{messageBean.myTaskMap['label.myTask.crsWatchList']}">
									<m:div rendered="#{myTaskDataBean.mytaskCRsWatchListFlag}">
										<f:subview id="crswatchList">
											<jsp:include page="inc_crsWatchList.jsp" />
										</f:subview>
									</m:div>
							</t:panelTab>
						<%--WSRP merge issue- below flag removed for MyCaseRecords, Group Case Records and MyCaseWatchlist  --%>
						<%--&& applicationStreamNameBean.applicationNameFlag --%>
					 <t:panelTab id="mytaskCaserecs" label="#{messageBean.myTaskMap['label.myTask.myCaseRecs']}">
									<m:div rendered="#{myTaskDataBean.mytaskCaseRecsFlag}">
										<f:subview id="mytaskCaserec">
											<jsp:include page="inc_myCaseRecords.jsp" />
										</f:subview>
									</m:div>
							</t:panelTab>
				 <t:panelTab id="mytaskGrpCaserecs" label="#{messageBean.myTaskMap['label.myTask.groupCaseRecs']}">
									<m:div rendered="#{myTaskDataBean.mytaskGrpCaseRecsFlag}">
										<f:subview id="mytaskGrpCaserec">
											<jsp:include page="inc_groupCaseRecords.jsp" />
										</f:subview>
									</m:div>
							</t:panelTab>
				<t:panelTab id="mytaskCaserecWaatch" label="#{messageBean.myTaskMap['label.myTask.caseRecWatchList']}">
									<m:div rendered="#{myTaskDataBean.mytaskCaseRecWatchListFlag}">
										<f:subview id="mytaskCaserecWaatchLi">
											<jsp:include page="inc_caseWatchList.jsp" />
										</f:subview>
									</m:div>
							</t:panelTab>
					<t:tabChangeListener
									type="com.acs.enterprise.common.program.contactmanagement.view.bean.MyTaskTabListner" />	
				</t:panelTabbedPane>	
				<%--540203_Performance_Fix_23Dec10 closing::: --%>			
				
			
				
			</m:div>
		
	</h:form>
	
	</m:body></hx:scriptCollector>
</f:view>






