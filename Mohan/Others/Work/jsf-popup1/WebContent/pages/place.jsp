<%--
This page is used to show a clickable list of states or provinces.
Once the user clicks a link, the corresponding text field 
in the main window form is updated with the name of the
selected state province.

The beta release of JSF-RI contains a well-known bug that disallows
the use of static text for the "value" attribute of "h:output_link"
when it appears inside "h:data_table". To work around this bug, a JSP
EL expression is used. Initially, it should have a value of "#".
--%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<html>
	<head>
		<title>Show Place</title>
		<script type="text/javascript">
			function update(place) {
				window.opener.updatePlace(place);
			}	
		</script>
	</head>
	<body>
		<f:view>
			<h:dataTable  value="#{ListHolderBean.items}"  var="place">
				<h:column>
					<h:outputLink title="#{place}" onmousedown="update(this.title);" value="#{place}" onclick="return false">
						<h:outputText value="#{place}" />
					</h:outputLink>
				</h:column>
			</h:dataTable>
		</f:view>
	</body>
</html>
