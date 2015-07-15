<%
	String uri = request.getRequestURI();
  int slashes = 0;
	for( int inx = 0; (inx = uri.indexOf( "/", inx + 1 )) != -1; slashes++ );
  String root = "";
	for( int inx = 0; inx < slashes - 1; inx++ )
	{
		root += "../";
	}
%>
<script type="text/javascript" src="<%= request.getContextPath() %>/includes/inc_headerInternal.js"></script>
<script>writeHeaderInternal("<%= root %>");</script>