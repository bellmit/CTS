<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />
<f:loadBundle
	basename="com.acs.mmis.common.enterprise.lettergeneration.nl.GLB_IN_LetterAndResponses"
	var="mesg" />
 <script>
window.onload=onLoadExecute;
var dosub = false;
var theButtId = '';
function onLoadExecute()
{	
	if (!dosub)
	{	
		document.location.href = '#submitPageFocus';
		window.scrollBy(0, -28);
		if(document.getElementById('view<portlet:namespace/>:rightClickForm:createltrIPCINDLTR').value)
		{
			theButtId = 'view<portlet:namespace/>:rightClickForm:invokeCreateLtrCase';
		}		
		submitPage();
	}					
}
function submitPage()
{
	dosub = true;
	document.getElementById(theButtId).disabled = false;
	document.getElementById(theButtId).click();		
}
</script>

<f:view>
	<body style="overflow:hidden">
		<m:anchor name="submitPageFocus"></m:anchor>
		<h:form id="rightClickForm">
			<h:inputHidden id="createltrIPCINDLTR" value="#{LettersAndResponsesDataBean.createLtrCase}"></h:inputHidden>	
			<h:inputHidden id="CASEtoLGRPREVIEW" value="#{LettersAndResponsesDataBean.preView}"></h:inputHidden>
			<h:inputHidden id="funcSKHidden" value="#{LettersAndResponsesDataBean.funcSK}"></h:inputHidden>
			<h:inputHidden id="funcAreaHidden" value="#{LettersAndResponsesDataBean.funcArea}"></h:inputHidden>
			<h:inputHidden id="ltrCatHidden" value="#{LettersAndResponsesDataBean.letterCategory}"></h:inputHidden>
			<m:div align="center">          
				<m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/>
	            <m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/>
	            <%-- Modified for replacing text in place of progress bar image for performance --%>    
	            <f:verbatim  escape="false">
	            	<h3>Please wait while your page is processing.</h3>
	            </f:verbatim>
	            <m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/>
	            <m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/><m:br/>            
			</m:div>
			
			<h:commandLink	action="#{LettersAndResponsesControllerBean.createLetter}"
			onmousedown="javascript:flagWarn=false;"
			style="border-width:4px;border-style:solid;color:white;background-color:#518ACD;border-color:#518ACD;font-size:11px;font-weight:bold;border-height:80px;" 
			styleClass="hide"
			id="invokeCreateLtrCase" rendered="#{!logCaseDataBean.disableScreenField}">
			<h:outputText style="cursor:default;color:white;height:13px;text-align:center;padding:2px 1px 2px 1px;margin:0px 0 5px 5px;border-right: 2px solid #999999;text-decoration:none;width: 200px;" 
			rendered="#{!logCaseDataBean.disableScreenField}" id="displyCreateBtTextRender"
				value="#{mesg['label.ltres.createltr']}" />
			<f:param id="createLetterParam1" name="send.CommonLetterInputData.Action"
				value="SendCommonLetterInputAction"></f:param>
			<f:param id="createLetterParam2" name="funcSK"
				value="#{LettersAndResponsesDataBean.funcSK}"></f:param>
			<f:param id="createLetterParam3" name="funcArea"
				value="#{LettersAndResponsesDataBean.funcArea}"></f:param>
			<f:param id="createLetterParam4" name="LETTER_CATEGORY"
				value="#{LettersAndResponsesDataBean.letterCategory}"></f:param>
			<f:param id="memberIDParam123" name="MEMBER_ID"
				value="#{logCaseDataBean.caseRegardingVO.caseRegardingMemberVO.memberId}"></f:param>
			<f:param name="preView" value="#{LettersAndResponsesDataBean.preView}"></f:param>
			</h:commandLink>
			
		</h:form>
	</body>
</f:view>