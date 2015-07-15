<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>
<portlet:defineObjects />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<script>
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
  	function assctnDeassctnWarn(callScriptId,assignedTyp,tabName,chkBoxId,rowIndex)
	{
		var triggerEvnt = false;
		var triggerCatEvnt = false;
		var description = findObjectByPartOfID('callscriptDesc').value;
		//alert(description + ':'+ callScriptId );
		if(callScriptId!="" && description!="")
		{
			if(tabName=="entTyp")
			{
				//alert(chkBoxId.checked);
				if(chkBoxId.checked==true)
				{
					var data = confirm("Call script " + callScriptId + " is currently associated to the Entity Type " + assignedTyp + ". Do you want to disassociate this call script and continue with this change? ");
					if(data)
					{
						//alert('Inside if');						
						if(chkBoxId.checked)	
						{			
							chkBoxId.checked = true;
						}else if(!chkBoxId.checked)
						{		
							chkBoxId.checked = false;
						}
						triggerEvnt = true;
					}
					else
					{
						//alert('Inside else');
						if(chkBoxId.checked)	
						{			
							chkBoxId.checked = false;
						}else if(!chkBoxId.checked)
						{		
							chkBoxId.checked = true;
						}
						triggerEvnt = false;
					}
				}
				else
				{
					//alert('here else');
					triggerEvnt = true;
					
					var csChkBxObj = findObjectByPartOfID('csChkBxVal');
					csChkBxObj.value=true;
					if(chkBoxId.checked)	
					{			
						chkBoxId.checked = true;
					}else if(!chkBoxId.checked)
					{		
						chkBoxId.checked = false;
					}
				}
			}
			if(tabName=="cat")
			{
				//alert(chkBoxId.checked);
				if(chkBoxId.checked==true)
				{
					var data = confirm("Call script " + callScriptId + " is currently associated to the Category " + assignedTyp + ". Do you want to disassociate this call script and continue with this change? ");
					if(data)
					{
						//alert('Inside if');						
						if(chkBoxId.checked)	
						{			
							chkBoxId.checked = true;
						}else if(!chkBoxId.checked)
						{		
							chkBoxId.checked = false;
						}
						triggerCatEvnt = true;
					}
					else
					{
						//alert('Inside else');
						if(chkBoxId.checked)	
						{			
							chkBoxId.checked = false;
						}else if(!chkBoxId.checked)
						{		
							chkBoxId.checked = true;
						}
						triggerCatEvnt = false;
					}
				}
				else
				{
					//alert('here else cat');
					triggerCatEvnt = true;
					
					var csChkBxObj = findObjectByPartOfID('csCatChkBxVal');
					csChkBxObj.value=true;
					if(chkBoxId.checked)	
					{			
						chkBoxId.checked = true;
					}else if(!chkBoxId.checked)
					{		
						chkBoxId.checked = false;
					}
				}
			}
			if(tabName=="sub")
			{
				//alert(chkBoxId.checked);
				if(chkBoxId.checked==true)
				{
					var data = confirm("Call script " + callScriptId + " is currently associated to the Subject " + assignedTyp + ". Do you want to disassociate this call script and continue with this change? ");
					if(data)
					{
						//alert('Inside if');						
						if(chkBoxId.checked)	
						{			
							chkBoxId.checked = true;
						}else if(!chkBoxId.checked)
						{		
							chkBoxId.checked = false;
						}
						triggerSubEvnt = true;
					}
					else
					{
						//alert('Inside else');
						if(chkBoxId.checked)	
						{			
							chkBoxId.checked = false;
						}else if(!chkBoxId.checked)
						{		
							chkBoxId.checked = true;
						}
						triggerSubEvnt = false;
					}
				}
				else
				{
					//alert('here else sub');
					triggerSubEvnt = true;
					
					var csChkBxObj = findObjectByPartOfID('csSubChkBxVal');
					csChkBxObj.value=true;
					if(chkBoxId.checked)	
					{			
						chkBoxId.checked = true;
					}else if(!chkBoxId.checked)
					{		
						chkBoxId.checked = false;
					}
				}
			}
			
		}
		else if(tabName=="entTyp")
		{
			//alert('inside final else ent');
			triggerEvnt = true;
		}
		else if(tabName=="cat")
		{
			//alert('inside final else cat');
			triggerCatEvnt = true;
		}
		else if(tabName=="sub")
		{
			//alert('inside final else sub');
			triggerSubEvnt = true;
		}
		if(triggerEvnt)
		{
			//alert('Entity event')
			var hiddenEntObject = findObjectByPartOfID('hiddenEnt');
			var csEntIndexCode = findObjectByPartOfID('csEntIndexCode');
			//alert('rowIndex'+rowIndex)
			csEntIndexCode.value=rowIndex;
			hiddenEntObject.click();
		}
		else if(triggerCatEvnt)
		{
			//alert('Category event')		
			//alert('rowIndex'+rowIndex)	
			var hiddenCatObject = findObjectByPartOfID('hiddenCat');
			var csCatIndexCode = findObjectByPartOfID('csCatIndexCode');
			
			csCatIndexCode.value=rowIndex;
			hiddenCatObject.click();
		}
		else if(triggerSubEvnt)
		{
			//alert('Subject event')		
			//alert('rowIndex'+rowIndex)	
			var hiddenSubObject = findObjectByPartOfID('hiddenSub');
			var csSubIndexCode = findObjectByPartOfID('csSubIndexCode');
			
			csSubIndexCode.value=rowIndex;
			hiddenSubObject.click();
		}
	}  

</script>


<script type="text/javascript">
/*Small save Big Save start*/
	frmId = 'view<portlet:namespace/>:form1';
	
	/*Small save Big Save ends*/
	function doEditCallScriptClickAction(link)
		{			
			
			var answer = confirm("Are you sure you want to navigate away from page?");				
			if(answer)
			{
				link.fireEvent('onclick');
			}
					
		}	
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
 

function renderAudit(id) 
{ 
    var hiddenValuecallscript= document.getElementById('view<portlet:namespace/>:form1:callScriptAuditInc:'+id1);
	var hiddenValue = document.getElementById('view<portlet:namespace/>:form1:auditlogDetails:'+id);
    
	if ((hiddenValue == null) ||(hiddenValue == '')|| (hiddenValue.length == 0)) {
		hideMe('audit_plus');
	} else if ((hiddenValue.value == 'false')) 
	{
		hideMe('audit_plus');
	} 
		 
	if ((hiddenValuecallscript == null) ||(hiddenValuecallscript == '')|| (hiddenValuecallscript.length == 0)) {
		hideMe('audit_plus');
	} else if ((hiddenValuecallscript.value == 'false')) 
	{
		hideMe('audit_plus');
	} 
		
}
/* For Datatable onmouseover and onmouseout*/
		var color;
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
	
	/** This method is called to show the Warning message*/
       function warnUser(checkBoxId) 
       { 
           var returnData = confirm("Assignment/De-Assignment with Entity Types ,Categories and Subjects may Overwrite the exsisting Call Script or delete the association of Call Script");
           
           if(returnData)
           {
               if(checkBoxId.checked)
               {
           		checkBoxId.checked = true;
           		}
           	  else
           	  {
           	    checkBoxId.checked = false;
           	  }
           }
           else
           {
           	 if(checkBoxId.checked)
               {
           		checkBoxId.checked = false;
           		}
           	  else
           	  {
           	    checkBoxId.checked = true;
           	  }
           }
           
      }   
      
      /** This method confirms the Delete Operation */    
      
       function confirmDeleteSC(formID,id) 
       { 
       
           var box = confirm("Are you sure that you want to Delete?");
           if(box == true){
               document.getElementById('view<portlet:namespace/>:'+formID+':'+id).click();                         
            }  else{
				return false;
		    }        
       } 
	
	   function textCounter(field,cntfield,maxlimit) 
		{ 
			var el = field;
			var e2;
			for(var i=0; i< document.forms.length; i++)
			{
				for (var j = 0; j < document.forms[i].elements.length; j++) 
				{
					if (document.forms[i].elements[j].id.indexOf(cntfield) >= 0)
					{
						e2 = document.forms[i].elements[j];
					}
				}
			}
			if (el.value.length > maxlimit) // if too long...trim it!
				el.value = el.value.substring(0, maxlimit);
				// otherwise, update 'characters left' counter
			else
				e2.value = maxlimit - el.value.length;
		}
	
</script>
<script>
		var thisForm = 'view<portlet:namespace/>:form1';
		function focusThis(id) 
		{
			if(id == 'csrpt')
			{
				id = document.getElementById(thisForm+':csFocusId').id;
			}
			if(id == 'add')
			{
				id = document.getElementById(thisForm+':csFocusId').id;
			}
			if(id == 'upd')
			{
				id = document.getElementById(thisForm+':cfrUpdate').id;
			}
			

   		document.getElementById(thisForm+':focusId').value=id; 
	   	}	
			
		if (window.addEventListener) //DOM method for binding an event
            	window.addEventListener("load", onLoadFunctions, false);
      	else if (window.attachEvent) //IE exclusive method for binding an event
            	window.attachEvent("onload", onLoadFunctions);
      	else if (document.getElementById) //support older modern browsers
           		window.onload=onLoadFunctions;
		
		function onLoadFunctions() {
			focusOnLoad();			
		}

		function focusOnLoad() {
   		var focusPage = '#'+document.getElementById(thisForm+':focusId').value;
			if(focusPage != '' && focusPage != '#') {				
				document.location.href=focusPage;
			}
   		}
	</script>

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="cont" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactCallScriptMaintenance"
	var="msg" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.GlobalAuditLabels"
	var="msgAudit" />




<f:view>

	<t:saveState id="CMGTTOMSS296"
		value="#{CallScriptDataBean.callScriptSearchCriteriaVO}"></t:saveState>
	<t:saveState id="CMGTTOMSS297"
		value="#{CallScriptDataBean.callScriptVO}"></t:saveState>
	<t:saveState id="CMGTTOMSS298" value="#{CallScriptDataBean.entityList}"></t:saveState>
	<t:saveState id="CMGTTOMSS299"
		value="#{CallScriptDataBean.categoryList}"></t:saveState>
	<%--<t:saveState id="CMGTTOMSS300"
		value="#{CallScriptDataBean.maintainCallScriptList}"></t:saveState>--%>
	<t:saveState id="CMGTTOMSS301"
		value="#{CallScriptDataBean.subjectList}"></t:saveState>
	<t:saveState id="CMGTTOMSS302"
		value="#{CallScriptDataBean.showAddCallScripts }"></t:saveState>
	<t:saveState id="CMGTTOMSS303"
		value="#{CallScriptDataBean.showEditCallScripts }"></t:saveState>
	<t:saveState id="CMGTTOMSS304"
		value="#{CallScriptDataBean.editCallScritFlag}"></t:saveState>
	<t:saveState id="CMGTTOMSS305"
		value="#{CallScriptDataBean.editCallScritRowIndex}"></t:saveState>
	<t:saveState id="CMGTTOMSS306"
		value="#{CallScriptDataBean.subjectValidvalues}"></t:saveState>
	<t:saveState id="CMGTTOMSS307"
		value="#{CallScriptDataBean.callScriptVO.inactive}"></t:saveState>

	<f:subview id="provService">
		<jsp:include page="/jsp/commonEntities/commonScripts.jsp" />
	</f:subview>
	<body onload="renderAudit('audit_open');">

	<h:form id="form1">

		<h:inputHidden value="#{CallScriptControllerBean.csEntIndexFlag}"
			id="csEntIndexCode"></h:inputHidden>
		<h:inputHidden value="#{CallScriptControllerBean.csCatIndexFlag}"
			id="csCatIndexCode"></h:inputHidden>
		<h:inputHidden value="#{CallScriptControllerBean.csSubIndexFlag}"
			id="csSubIndexCode"></h:inputHidden>

		<h:inputHidden value="#{CallScriptControllerBean.csChkBxVal}"
			id="csChkBxVal"></h:inputHidden>
		<h:inputHidden value="#{CallScriptControllerBean.csCatChkBxVal}"
			id="csCatChkBxVal"></h:inputHidden>
		<h:inputHidden value="#{CallScriptControllerBean.csSubChkBxVal}"
			id="csSubChkBxVal"></h:inputHidden>
		<h:inputHidden value="#{CallScriptControllerBean.loadUserPermissions}"
			id="loadUserPermissions"></h:inputHidden>
		<h:inputHidden id="focusId"
			value="#{CallScriptDataBean.inputHiddenId}" />

		<%-- start Added for CR implementation--%>
		<h:inputHidden value="#{CallScriptControllerBean.controlRequired}"
			id="controlRequiredForCallscript" />
		<%-- End Added for CR implementation--%>



		<h:commandButton id="hiddenEnt" styleClass="hide"
			value="Hidden Button To Click"
			action="#{CallScriptControllerBean.csEntityAssociation}">
		</h:commandButton>
		<h:commandButton id="hiddenCat" styleClass="hide"
			value="Hidden Button To Click"
			action="#{CallScriptControllerBean.csCategoryAssociation}">
		</h:commandButton>
		<h:commandButton id="hiddenSub" styleClass="hide"
			value="Hidden Button To Click"
			action="#{CallScriptControllerBean.csSubAssociation}">
		</h:commandButton>

		<m:div styleClass="floatContainer">
			<m:div styleClass="fullwidth">
				<m:div styleClass="moreInfoBar">
					<m:div styleClass="infoTitle" align="left">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT1028"
								value="#{ent['label-sw-reqFld']}" styleClass="colorMaroon" />
						</m:span>

					</m:div>

					<m:div styleClass="infoActions">
					<%--	<h:outputLink id="PRGCMGTOLK29" onclick="flagWarn=true;"
							value="/wps/myportal/InternalUserHomePage">
							<h:outputText id="PRGCMGTOT1029" styleClass="strong"
								value="#{ent['label-sw-cancel']}" />
						</h:outputLink>--%>
						<f:verbatim>  <a href="/wps/myportal/InternalUserHomePage" id="PRGCMGTOLK29" onclick="flagWarn=true;"> </f:verbatim>
							<h:outputText id="PRGCMGTOT1029" styleClass="strong" value="#{ent['label-sw-cancel']}" />
							<f:verbatim></a></f:verbatim>
					</m:div>
				</m:div>
				<h:messages id="PRGCMGTMS8" showDetail="true" layout="table"
					showSummary="false" styleClass="errorMessage" />
				<m:br />
				<m:br />

				<m:section id="PROVIDERMMS20120731164811424" styleClass="otherbg">
					<m:legend>

						<h:outputText id="PRGCMGTOT1030" styleClass="strong"
							value="#{msg['title.label.callscript.maintainCallScripts']}" />

					</m:legend>
					<m:table id="PROVIDERMMT20120731164811425" width="100%">
						<m:tr>
							<m:td>
							</m:td>
						</m:tr>
					</m:table>

					<m:table id="PROVIDERMMT20120731164811426" styleClass="tableBar" width="100%">
						<m:tr>
							<m:td styleClass="tableAction">
								<%-- Added "disabled" --%>
								<h:commandButton id="PRGCMGTCB36" styleClass="formBtn"
									disabled="#{!CallScriptControllerBean.controlRequired}"
									value="#{msg['label.callscript.addCallScripts']}"
									onmousedown="javascript:flagWarn=false;"
									action="#{CallScriptControllerBean.addCallScripts}">
								</h:commandButton>





							</m:td>
						</m:tr>
					</m:table>
					<t:dataTable cellspacing="0" styleClass="dataTable"
						columnClasses="columnClass" headerClass="headerClass"
						footerClass="footerClass" rowClasses="row_alt,row"
						id="callScriptID" width="100%" var="callscript"
						onmouseover="return tableMouseOver(this, event);"
						onmouseout="return tableMouseOut(this, event);"
						first="#{CallScriptDataBean.csRowIndex}" rowIndexVar="rowIndex"
						value="#{CallScriptDataBean.maintainCallScriptList}" rows="10">



						<h:column id="activecol">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD119" columns="2">
									<h:outputLabel id="PRGCMGTOLL486" for="VoidInd"
										value="#{msg['label.callscript.Active']}">
									</h:outputLabel>
									<h:panelGroup id="VoidInd">
										<t:div styleClass="alignLeft">
											<t:commandLink id="actCallScriptCommandLink1"
												actionListener="#{CallScriptControllerBean.getAllSortedCallScripts}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{CallScriptDataBean.imageRender !='actCallScriptCommandLink1'}">

												<%--<h:graphicImage alt="#{msg['label.callscript.forAscd']}"
													styleClass="sort_asc" width="8" url="/images/x.gif" />  --%>
												
												<m:div title="#{msg['label.callscript.forAscd']}" styleClass="sort_asc" />

												<f:attribute name="columnName" value="statusCode" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="alignLeft">
											<t:commandLink id="actCallScriptCommandLink2"
												actionListener="#{CallScriptControllerBean.getAllSortedCallScripts}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{CallScriptDataBean.imageRender !='actCallScriptCommandLink2'}">

												<%--<h:graphicImage alt="#{msg['label.callscript.forDsnd']}"
													styleClass="sort_desc" width="8" url="/images/x.gif" /> --%>
													
												<m:div title="#{msg['label.callscript.forDsnd']}"	styleClass="sort_desc" />

												<f:attribute name="columnName" value="statusCode" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>

							<h:outputText id="activeValue"
								value="#{callscript.callScriptStatus}" />


						</h:column>

						<h:column id="callscrpID">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD120" columns="2">
									<h:outputLabel id="PRGCMGTOLL487" for="callscrpSK"
										value="#{msg['label.callscript.callScriptID']}">
									</h:outputLabel>
									<h:panelGroup id="callscrpSK">
										<t:div styleClass="alignLeft">
											<t:commandLink id="valueCallScriptCommandLink1"
												actionListener="#{CallScriptControllerBean.getAllSortedCallScripts}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{CallScriptDataBean.imageRender !='valueCallScriptCommandLink1'}">

												<%--<h:graphicImage alt="#{msg['label.callscript.forAscd']}"
													styleClass="sort_asc" width="8" url="/images/x.gif" /> --%>
												
												<m:div title="#{msg['label.callscript.forAscd']}" styleClass="sort_asc" />

												<f:attribute name="columnName" value="callScriptSK" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="alignLeft">
											<t:commandLink id="valueCallScriptCommandLink2"
												actionListener="#{CallScriptControllerBean.getAllSortedCallScripts}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{CallScriptDataBean.imageRender !='valueCallScriptCommandLink2'}">

												<%--<h:graphicImage alt="#{msg['label.callscript.forDsnd']}"
													styleClass="sort_desc" width="8" url="/images/x.gif" /> --%>
												
												<m:div title="#{msg['label.callscript.forDsnd']}"  styleClass="sort_desc" />

												<f:attribute name="columnName" value="callScriptSK" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<t:commandLink id="edit"
								action="#{CallScriptControllerBean.getCallScriptDetails}"
								onmousedown="javascript:flagWarn=false;">
								<h:outputText id="callidVal" value="#{callscript.callScriptSK}" />
								<f:param name="indexCode" value="#{rowIndex}"></f:param>
							</t:commandLink>
						</h:column>

						<h:column id="desccol">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD121" columns="2">
									<h:outputLabel id="PRGCMGTOLL488" for="asgCallS"
										value="#{cont['label.ref.description']}">
									</h:outputLabel>
									<h:panelGroup id="asgCallS">
										<t:div styleClass="alignLeft">
											<t:commandLink id="descCallScriptCommandLink1"
												actionListener="#{CallScriptControllerBean.getAllSortedCallScripts}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{CallScriptDataBean.imageRender !='descCallScriptCommandLink1'}">

												<%--<h:graphicImage alt="#{msg['label.callscript.forAscd']}"
													styleClass="sort_asc" width="8" url="/images/x.gif" />  --%>
											
												<m:div title="#{msg['label.callscript.forAscd']}" styleClass="sort_asc" />

												<f:attribute name="columnName" value="description" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div styleClass="alignLeft">
											<t:commandLink id="descCallScriptCommandLink2"
												actionListener="#{CallScriptControllerBean.getAllSortedCallScripts}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{CallScriptDataBean.imageRender !='descCallScriptCommandLink2'}">

												<%--<h:graphicImage alt="#{msg['label.callscript.forDsnd']}"
													styleClass="sort_desc" width="8" url="/images/x.gif" /> --%>
													
												<m:div title="#{msg['label.callscript.forDsnd']}"  styleClass="sort_desc" />

												<f:attribute name="columnName" value="description" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>

							<h:outputText id="descval" value="#{callscript.description}" />
						</h:column>
						<%-- Snippet for  displaying  No Data --%>

						<f:facet name="footer">

							<m:div id="nodata" rendered="#{CallScriptDataBean.noData}"
								align="center">
								<h:outputText id="PRGCMGTOT1031"
									value="#{cont['label.ref.noData']}"></h:outputText>
							</m:div>

						</f:facet>

					</t:dataTable>

					<t:dataScroller id="PROVIDERMDS20120731164811427" pageCountVar="pageCount" pageIndexVar="pageIndex"
						paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
						paginatorMaxPages="3" immediate="false" for="callScriptID"
						firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
						rowsCountVar="rowsCount" styleClass="dataScroller">
						<f:facet name="previous">
							<h:outputText id="PRGCMGTOT1032" styleClass="underline"
								value="#{cont['label.ref.lt']}" rendered="#{pageIndex > 1}"></h:outputText>
						</f:facet>
						<f:facet name="next">
							<h:outputText id="PRGCMGTOT1033" styleClass="underline"
								value="#{cont['label.ref.gt']}"
								rendered="#{pageIndex < pageCount}"></h:outputText>
						</f:facet>
						<h:outputText id="PRGCMGTOT1034" styleClass="dataScrollerText"
							value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
							rendered="#{rowsCount>0}">
						</h:outputText>
					</t:dataScroller>

					<m:br />
					<m:br />
					<h:inputHidden id="csFocusId" value="csFocusId"></h:inputHidden>
					<m:div id="edtcs" styleClass=""
						rendered="#{CallScriptDataBean.showAddCallScripts || CallScriptDataBean.showEditCallScripts }">


						<m:div styleClass="moreInfo">

							<%--  EDIT CALL SCRIPTS SECTION --%>
							<m:div styleClass="moreInfoBar"
								rendered="#{CallScriptDataBean.showEditCallScripts}">

								<m:div styleClass="infoTitle">
									<h:outputText id="PRGCMGTOT1035"
										value="#{msg['label.callscript.editCallScript']}" />
								</m:div>
								<m:div styleClass="infoActions">


									<%--<h:commandLink id="PRGCMGTCL152" styleClass="strong"											rendered="#{CallScriptControllerBean.controlRequired}"											action="#{CallScriptControllerBean.updateCallScript}"											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="PRGCMGTOT1036" value="#{ent['label-sw-save']}"></h:outputText>
										</h:commandLink>--%>
									<h:commandButton
										onmousedown="if(event.button==1) flagWarn=false;focusThis('upd');"
										id="cfrUpdate"
										style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
										disabled="#{!CallScriptControllerBean.controlRequired}"
										value="#{ent['label-sw-save']}"
										action="#{CallScriptControllerBean.updateCallScript}" />
									<h:outputText id="PRGCMGTOT1037" escape="false"
										value="#{ref['label.ref.linkSeperator']}" />

									<%--<h:commandLink id="PRGCMGTCL153"											rendered="#{CallScriptControllerBean.controlRequired}"																						action="#{CallScriptControllerBean.resetEditCallScripts}"											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="PRGCMGTOT1038" value="#{ent['label-sw-reset']}"></h:outputText>
										</h:commandLink>--%>
									<h:commandButton id="PRGCMGTCB37"
										style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
										disabled="#{!CallScriptControllerBean.controlRequired}"
										value="#{ent['label-sw-reset']}"
										onmousedown="javascript:flagWarn=false;"
										action="#{CallScriptControllerBean.resetEditCallScripts}" />



									<h:outputText id="PRGCMGTOT1039" escape="false"
										value="#{ref['label.ref.linkSeperator']}" />

									<h:commandButton id="hbtn"
										style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
										disabled="#{!CallScriptControllerBean.controlRequired || !CallScriptControllerBean.controlRequiredFOrDelete}"
										onclick="javascript:flagWarn=false;return confirmDeleteSC('form1','hiddenButton');"
										value="#{ent['label-sw-delete']}"
										action="#{CallScriptControllerBean.deleteCallscript}">
									</h:commandButton>
									<h:outputText id="PRGCMGTOT1040" escape="false"
										value="#{ref['label.ref.linkSeperator']}" />

									<h:commandButton id="PRGCMGTCB38"
										onmousedown="if(event.button==1) flagWarn=false;"
										style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:55px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
										value="#{ref['label.ref.auditLog']}"
										action="#{CallScriptControllerBean.doAuditKeyListOperation}" />




									<h:outputText id="PRGCMGTOT1041" escape="false"
										value="#{ref['label.ref.linkSeperator']}" />
									<h:commandButton id="PRGCMGTCB39" onclick="flagWarn=true;"
										style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
										value="#{ent['label-sw-cancel']}"
										action="#{CallScriptControllerBean.cancelEditCallScripts}" />

									<%--	<h:commandLink id="PRGCMGTCL154"											action="#{CallScriptControllerBean.cancelEditCallScripts}"											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="PRGCMGTOT1042" value="#{ent['label-sw-cancel']}"></h:outputText>
										</h:commandLink>--%>

								</m:div>
							</m:div>

							<%--  ADD CALL SCRIPTS SECTION --%>
							<m:div styleClass="moreInfoBar"
								rendered="#{CallScriptDataBean.showAddCallScripts}">
								<m:div styleClass="infoTitle">
									<h:outputText id="PRGCMGTOT1043"
										value="#{msg['label.callscript.addCallScript']}" />
								</m:div>
								<m:div styleClass="infoActions">

									<m:div>
										<t:commandLink styleClass="strong" id="cfrCreate"
											rendered="#{CallScriptControllerBean.controlRequired}"
											action="#{CallScriptControllerBean.createCallScript}"
											onmousedown="javascript:flagWarn=false;focusThis('add');">
											<h:outputText id="PRGCMGTOT1044"
												value="#{ent['label-sw-save']}"></h:outputText>
										</t:commandLink>
										<h:outputText id="PRGCMGTOT1045" escape="false"
											value="#{ref['label.ref.linkSeperator']}"
											rendered="#{CallScriptControllerBean.controlRequired}" />
										<t:commandLink id="PRGCMGTCL155"
											rendered="#{CallScriptControllerBean.controlRequired}"
											action="#{CallScriptControllerBean.resetCallScripts}"
											onmousedown="javascript:flagWarn=false;">
											<h:outputText id="PRGCMGTOT1046"
												value="#{ent['label-sw-reset']}"></h:outputText>
										</t:commandLink>
										<h:outputText id="PRGCMGTOT1047" escape="false"
											value="#{ref['label.ref.linkSeperator']}"
											rendered="#{CallScriptControllerBean.controlRequired}" />


										<t:commandLink id="PRGCMGTCL156" onclick="flagWarn=true;"
											action="#{CallScriptControllerBean.cancelAddCallScripts}">
											<h:outputText id="PRGCMGTOT1048"
												value="#{ent['label-sw-cancel']}"></h:outputText>
										</t:commandLink>
									</m:div>
								</m:div>
							</m:div>

							<%-- Common to both Edit and Add blocks --%>
							<m:div styleClass="moreInfoContent">
								<m:div>
								</m:div>
								<m:div styleClass="msgBox"
									rendered="#{CallScriptDataBean.showSucessMessage}">
									<h:outputText id="PRGCMGTOT1049"
										value="#{ent['label-sw-success']}" />
								</m:div>
								<m:div rendered="#{CallScriptDataBean.showCallScriptDesc}">
									<h:messages id="PRGCMGTMS9" showDetail="true" layout="table"
										showSummary="false" styleClass="errorMessage" />
								</m:div>

								<m:div styleClass="fullwidth">
									<m:table id="PROVIDERMMT20120731164811428" cellspacing="0" width="100%">
										<m:tr>
											<m:td>
												<m:div styleClass="padb">

													<h:outputLabel id="PRGCMGTOLL489" for="activeradioval"
														value="#{msg['label.callscript.Active']}">
													</h:outputLabel>

													<h:selectOneRadio id="activeradioval"
														value="#{CallScriptDataBean.callScriptVO.callScriptStatus}">
														<f:selectItem itemLabel="#{msg['label.callscript.Yes']}"
															itemValue="Yes" />&nbsp;&nbsp;
                                                             <f:selectItem
															itemLabel="#{msg['label.callscript.No']}" itemValue="No" /> &nbsp;
                                                         </h:selectOneRadio>

												</m:div>

											</m:td>

											<m:td>
												<m:div styleClass="padb">
													<h:outputLabel id="PRGCMGTOLL490" for="callstId"
														value="#{msg['label.callscript.callScriptID']}">
													</h:outputLabel>
													<m:br></m:br>
													<h:outputText id="callstId"
														value="#{CallScriptDataBean.callScriptVO.callScriptSK}" />

												</m:div>
											</m:td>
											<m:td>

												<m:div styleClass="padb">
													<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT1050"
															value="#{cont['label.ref.reqSymbol']}" />
													</m:span>
													<h:outputLabel id="PRGCMGTOLL491" for="callscriptDesc"
														value="#{cont['label.ref.description']}"></h:outputLabel>
													<m:br></m:br>
													<h:panelGroup id="callGroup" styleClass="panelGroup"
														rendered="#{CallScriptControllerBean.showActiveScreen}">

														<h:inputText size="40" id="callscriptDesc"
															disabled="#{CallScriptDataBean.callScriptVO.inactive}"
															value="#{CallScriptDataBean.callScriptVO.description}"
															maxlength="50"></h:inputText>
													</h:panelGroup>

													<m:br></m:br>
													<h:message id="PRGCMGTM160" for="callscriptDesc"
														showDetail="true" styleClass="errorMessage" />
												</m:div>

											</m:td>
										</m:tr>
									</m:table>
									<%-- Strating of Tabs  --%>
									<m:anchor name="editCallScriptFocus"></m:anchor>
									<%-- for auto focus  --%>
									<m:div styleClass="tabs" id="tabssection">
										<t:panelTabbedPane id="CMGTTOMPTP1" width="99%"
											selectedIndex="#{CallScriptDataBean.selectedIndex}">

											<%--<t:panelTab id="CMGTTOMPT1" styleClass="required">--%>
											<t:panelTab label="#{msg['label.callscript.callScript']}"
												onclick="javascript:flagWarn=false;" id="tab1"
												styleClass="required">
												<%--<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT1051" value="#{cont['label.ref.reqSymbol']}" />
													</m:span>--%>

												<jsp:include page="inc_maintainCallScriptsCS.jsp" />
											</t:panelTab>

											<t:panelTab label="#{msg['label.callscript.entityType']}"
												onclick="javascript:flagWarn=false;" id="tab2">
												<jsp:include page="inc_maintainCallScriptsET.jsp" />
											</t:panelTab>
											<t:panelTab label="#{msg['label.callscript.category']}"
												onclick="javascript:flagWarn=false;" id="tab3">
												<jsp:include page="inc_maintainCallScriptsCategory.jsp" />
											</t:panelTab>
											<t:panelTab label="#{msg['label.callscript.subject']}"
												onclick="javascript:flagWarn=false;" id="tab4">
												<jsp:include page="inc_maintainCallScriptsSub.jsp" />
											</t:panelTab>

										</t:panelTabbedPane>
									</m:div>

									<%--  Audit Log 
										<m:div rendered="#{CallScriptDataBean.showEditCallScripts}">
											<f:subview id="auditlogDetails">
												<jsp:include
													page="/jsp/maintaincallscripts/inc_maintainCallScriptsAuditLog.jsp" />
											</f:subview>
										</m:div>--%>
									<m:div
										rendered="#{not empty CallScriptDataBean.callScriptVO.callScriptSK}">
										<f:subview id="callScriptAuditInc">
											<m:div rendered="#{CallScriptDataBean.auditLogFlag}">
												<audit:auditTableSet id="callScriptAuditId"
													value="#{CallScriptDataBean.callScriptVO.auditKeyList}"
													numOfRecPerPage="10"></audit:auditTableSet>
											</m:div>
										</f:subview>


									</m:div>







								</m:div>
							</m:div>




						</m:div>
					</m:div>

				</m:section>

			</m:div>
		</m:div>



	</h:form>

	<%-- var thisForm : stores the form reference. Form reference is use to get  the computed  role stored in hidden form field.
         FlagRole : Indicator to store role information .Stores 'False' for inquire only users and 'True' for other user.
	--%>
	<script type="text/JavaScript">

   var thisForm = 'view<portlet:namespace/>:form1';

   flagRole =((typeof (document.getElementById(thisForm+': controlRequiredForCallscript) !=  'undefined')) && document.getElementById(thisForm+':controlRequiredForCallscript) != null ?  document.getElementById(thisForm+':controlRequiredForCallscript).value:true);

</script>

	</body>

</f:view>

