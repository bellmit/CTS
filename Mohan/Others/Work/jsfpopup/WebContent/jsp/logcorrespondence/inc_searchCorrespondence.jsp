<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<script>

	
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
	
	function tableMouseOver(thisObj, thisEvent) { 

        var row = getRow(thisObj, thisEvent); 
        if (row) setRowClass(row,"rowonon_mouse","rowonon_mouse_right","rowonon_mouse_left"); 
	} 
	
	function tableMouseOut(thisObj, thisEvent) { 
	//alert("Inside tableMouseOut");
        var row = getRow(thisObj, thisEvent); 
        if (row) setRowClassOne(row,"rowone"); 
	} 
</script>

<f:loadBundle var="crsrch"
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactCorrespondenceSearch" />

<t:saveState id="CMGTTOMSS259" value="#{cs_searchCorrespondenceDataBean}" />
<t:saveState id="CMGTTOMSS260" value="#{cs_searchCorrespondenceDataBean.listOfRepUnits}" />
<t:saveState id="CMGTTOMSS261" value="#{cs_searchCorrespondenceDataBean.searchResults}" />
<t:saveState id="CMGTTOMSS262" value="#{cs_searchCorrespondenceDataBean.showResultsDiv}" />

<t:saveState id="CMGTTOMSS263"	value="#{cs_searchCorrespondenceDataBean.listOfRefBusUnits}" />
<t:saveState id="CMGTTOMSS264" value="#{cs_searchCorrespondenceDataBean.lobVVList}" />
<t:saveState id="CMGTTOMSS265"	value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO}" />


<m:div styleClass="infoActions">
</m:div>
<m:div styleClass="floatContainer">
	<h:messages id="PRGCMGTMS5" showDetail="true" style="color: red" />

	<t:htmlTag value="h4">
		<h:outputText id="PRGCMGTOT914" value="Correspondence Record Search"></h:outputText>
	</t:htmlTag>
	
</m:div>
<%--<m:inputHidden name="LogCrsSrchEntity_ACTION_NAME" 
										value="LogCrsEnt_SOURCE_ACTION"></m:inputHidden>--%>

<jsp:include
	page="/jsp/logcorrespondence/inc_searchForLogCorrespondence.jsp" />
	
<m:div>
	<f:subview id="searchforAssCRSPDResultsSubview">	
		<jsp:include
			page="/jsp/logcorrespondence/inc_searchCorrespondenceResults.jsp" />
	</f:subview>
</m:div>



















