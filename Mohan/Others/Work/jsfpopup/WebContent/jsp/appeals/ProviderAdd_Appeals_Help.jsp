<%@ taglib uri="http://acs.enterprise.com/help" prefix="e"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<f:view>
 <f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactAppealsMaintenance"
	var="helpmsg" />
 <f:loadBundle
	basename="com.acs.enterprise.common.util.config.ServerLocation"
	var="helpserver" /> 
	
 		<script type="text/javascript">
    	    var url = '<e:helpPath helpServer="#{helpserver['httpProviderURL']}" helpJunctionUrl="#{helpserver['internalHelpJunction']}" mapFile="#{helpmsg['mapFile']}" helpRoot="#{helpmsg['helpRoot']}" helpContentPath="#{helpmsg['helpContentPath']}" helpId="#{helpmsg['helpId.providerAppl']}"/>'; 

		location.href=url;
        </script>

</f:view>
