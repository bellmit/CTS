<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<script type="text/javascript">

var pageLoadController = true;
function invokeServlet()
{
	//alert ('Inside invokeServlet');
	var urlPath = window.opener.getURLPath();
	//alert ("urlPath = " + urlPath);
	document.showPdfForm.action = urlPath;
	document.showPdfForm.submit();
	//alert ('End of invokeServlet');
}

function callParent2RefreshData()
{
	//alert ('Inside callParent2RefreshData()');
	window.opener.submitParentForm();
}

</script>
<body onload="javascript:invokeServlet();" onunload="callParent2RefreshData();">
<form name="showPdfForm" id="showPdfForm">
<P align="center">Attachment is loading...</P>
</form>

</body>
