
<%@ taglib uri="http://acs.enterprise.com/help" prefix="e"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<f:view>
	<f:loadBundle
		basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CMContactManagementCase"
		var="helpmsg" />
	<f:loadBundle
		basename="com.acs.enterprise.common.util.config.ServerLocation"
		var="helpserver" />

<f:verbatim>
	<script type="text/javascript">

    	var url = '<e:helpPath helpServer="#{helpserver['httpProviderURL']}" helpJunctionUrl="#{helpserver['internalHelpJunction']}" mapFile="#{helpmsg['mapFile']}" helpRoot="#{helpmsg['helpRoot']}" helpContentPath="#{helpmsg['helpContentPath']}" helpId="#{helpmsg['helpId']}"/>'; 
		window.location.href = url;

        </script>
</f:verbatim>

<body onload="openLogCaseHelpURL()">
</body>

</f:view>
