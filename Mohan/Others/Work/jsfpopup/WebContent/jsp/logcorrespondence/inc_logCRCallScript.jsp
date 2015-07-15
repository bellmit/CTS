<%-- 
  - Author(s): Wipro
  - Date: Mon Jan 29 14:37:45 IST 2007
  - Copyright Notice: Copyright (c) 2006 Affiliated Computer Services, Inc.
  - @ 2006
  - Description: 
  --%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactCorrespondenceLog"
	var="crspd" />

<m:div id="CallScriptID1">
	<f:subview id="logCrspdCallScriptSubview">
		<m:section id="CallScriptID2" styleClass="otherbg">
			<m:legend id="CallScriptID3">
				<h:outputLink					onclick="setHiddenValue('logCorrespondence:logCrspdCallScrptSubview:logCrspdCallScriptSubview:callScriptHidden','minus');toggle('log_div_callScript',2);							plusMinusToggle('log_div_callScript',this,'logCorrespondence:logCrspdCallScrptSubview:logCrspdCallScriptSubview:callScriptHidden');							 return false;"					id="plusMinuscallScript" styleClass="plus">

					<h:outputText id="CallScriptID4" value="#{crspd['label.crspd.callscript']}" />
					<h:inputHidden id="callScriptHidden"						value="#{CorrespondenceDataBean.callScriptHidden}"></h:inputHidden>
				</h:outputLink>

			</m:legend>
			<m:div sid="log_div_callScript" id="crCallID">
				<m:div id="crCallScript">
					<h:outputLabel for="callScriptDesc" id="callScriptDescID">
						<h:outputText value="#{crspd['label.crspd.description']}"							id="callScriptDesc" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="callScriptDescText"						value="#{CorrespondenceDataBean.correspondenceRecordVO.callScriptDesc}" />
					<m:br />
					<m:br />
					<h:outputLabel for="callScriptText" id="callScriptTextID">
						<h:outputText value="#{crspd['label.crspd.text']}"							id="callScriptText" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="callScriptTextID1"						value="#{CorrespondenceDataBean.correspondenceRecordVO.callScriptText}" />
					<m:br />
				</m:div>
			</m:div>
		</m:section>
	</f:subview>
</m:div>
