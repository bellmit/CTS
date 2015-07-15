<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<meta http-equiv="cache-control" content="no-cache"/>
	<link rel="stylesheet" type="text/css" href="css/portal.css" />
	<link rel="stylesheet" type="text/css" href="css/print.css" media="print" />
	<script type="text/javascript" src="includes/udm-resources/udm-custom.js"></script>
	<script type="text/javascript" src="includes/udm-resources/udm-control.js"></script>
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="css/portal.css" />
	<link rel="stylesheet" type="text/css" href="css/selfservice.css" />
	<link rel="stylesheet" type="text/css" href="css/print.css" media="print" />
	<script type="text/javascript" src="includes/udm-resources/horizontal-absolute-top-left.js"></script>
	<script type="text/javascript" src="includes/udm-resources/udm-control.js"></script>
	<script type="text/javascript" src="includes/general.js"></script>
	<script type="text/javascript" src="includes/inc_portlet.js"></script>
	<script type="text/javascript" src="includes/inc_topNav.js"></script>
	<script type="text/javascript" src="includes/inc_footer.js"></script>
  	<script src="js/libs/jquery-1.10.2.js"></script>
	<script src="js/libs/handlebars-1.1.2.js"></script>
	<script src="js/libs/ember-1.4.0.js"></script>
    <script src="http://emberjs.com.s3.amazonaws.com/getting-started/ember-data.js"></script>
	<script src="js/app.js"></script>
    <script src="js/model.js"></script>
    <script src="js/router.js"></script>
    <script src="js/controller.js"></script>
    <script src="js/view.js"></script>
	
	<script type="text/javascript">
		function invokePojo(){
			document.forms[0].action="/RulesExternalization/RulesPojoController";
			document.forms[0].submit();
		}
		function invokePojo2(){
			document.forms[0].action="/RulesExternalization/PojoController";
			document.forms[0].submit();
		}
		
		function invokeBlaze(){
			document.forms[0].action="/RulesExternalization/RulesBlazeController";
			document.forms[0].submit();			
		}

		function invokeMultiple(){
			document.forms[0].action="/RulesExternalization/RulesMultipleController";
			document.forms[0].submit();			
		}		
		function exceptionFlow(){
			document.forms[0].action="/RulesExternalization/RulesExceptionController";
			document.forms[0].submit();			
		}		
	</script>
	
<META name="GENERATOR" content="MSHTML 9.00.8112.16421"></HEAD>
<BODY>



<div id="wrapper">

<div id="head">
	<div id="headImg"><img src="images/logo_mt.gif" /></div>
	<div id="headTxt">
		<div id="headLine2"><a href="#">Contact Us</a>&nbsp;&#124;&nbsp;<a href="#">Help</a>&nbsp;&#124;&nbsp;
		 <form name="testForm" method="post">
			<a href="#">S<span class="accesskey">e</span>arch</a>
		 </form>&nbsp;&#124;&nbsp;<a href="#">Logout</a>
		</div>
	</div>
</div>
<div id="nav">

    <ul id="udm" class="udm" style="width: 100%;">
        <li>
            <a id="menuBarTitle" class="" style="z-index: 1034;">

                Home

            </a>
        </li>
       
        
    </ul>

</div>


<div class="clear"></div>

<div></div>


<div id="content">

<DIV class="floatContainer">
<DIV style="width: 100%;"><!-- Start Copy -->
<DIV>

<!-- 
	<TABLE>
  <TBODY>
  <TR>
    <TD><LABEL>Member Name:</LABEL> Margaret L. Kingsley</TD></TR>
  <TR>
    <TD title="Primary Care Provider"><LABEL>PCP:</LABEL> Robert J. Lincoln,
    MD</TD></TR></TBODY></TABLE><BR>
     
     
    </DIV>

-->

	<div id="customContents">
	<br><br><br><br><br><br>
		<div class="clear"><H3><u>Please click any of the Button to test RIF</u></H3></div><br><br>
		<input class="formBtn" type="Button" onclick="javascript:invokePojo()" value="Invoke Pojo">
		
		<input class="formBtn" type="Button" onclick="javascript:invokePojo2()" value="Invoke Pojo 2">
		
		<input class="formBtn" type="Button" onclick="javascript:invokeMultiple()" value="Invoke Multiple">
		
		<input class="formBtn" type="Button" onclick="javascript:exceptionFlow()" value="Exception Flow">
		
		
		
		
		
		
		<br><br><br>
		<% int n=0;%>
		<%if(request.getAttribute("ruleCount")!=null){ 
			
				for (int i=0;i< Integer.parseInt(request.getAttribute("ruleCount").toString());i++){%>
					<H3><u>Rules Invocation Results</u></H3>
					<%n=n+1; %>
					<%Object responseData=request.getAttribute("responseData"+n)==null?"":request.getAttribute("responseData"+n);%>
					<%Object resultBeforeChange=request.getAttribute("resultBeforeChange"+n)==null?"":request.getAttribute("resultBeforeChange"+n);%>
					<%Object resultAfterChange=request.getAttribute("resultAfterChange"+n)==null?"":request.getAttribute("resultAfterChange"+n);%>
					<%Object ruleName=request.getAttribute("ruleName"+n)==null?"":request.getAttribute("ruleName"+n);%>
					<%Object errorData=request.getAttribute("errorData"+n)==null?"":request.getAttribute("errorData"+n);%>
					
					<b>Rule Name : <%=ruleName%></b><br>
					
					<%if(responseData!=null){%>
					<%if(responseData.toString().indexOf("success")!=-1){ %>
						<b>Rules Execution Status : <font color='green'><%=responseData %></font></b><br>
					<%}else{%>
						<b>Rules Execution Status : <font color='red'><%=responseData %></font></b><br>
					<%}} %>
					
					<b>Data Before Rule Execute: <%=resultBeforeChange %></b><br>
					<b>Data After Rule Execute: <%=resultAfterChange %></b><br>
					
					<%if(null!=errorData && errorData.toString().length()>0 ){%>
						<b>Error Detail :<font color='red'><%=errorData %></font></b>
					<%}%><br>
					
					<%}%>										
				<%
		
		}else{%>
		
		<H3><u>Rules Invocation Results</u></H3>
		<%Object responseData=request.getAttribute("responseData")==null?"":request.getAttribute("responseData");%>
		<%Object resultBeforeChange=request.getAttribute("resultBeforeChange")==null?"":request.getAttribute("resultBeforeChange");%>
		<%Object resultAfterChange=request.getAttribute("resultAfterChange")==null?"":request.getAttribute("resultAfterChange");%>
		<%Object ruleName=request.getAttribute("ruleName")==null?"":request.getAttribute("ruleName");%>
		<%Object errorData=request.getAttribute("errorData")==null?"":request.getAttribute("errorData");%>
		
		<b>Rule Name : <%=ruleName %></b><br>
		<%if(responseData.toString().indexOf("success")!=-1){ %>
			<b>Rules Execution Status : <font color='green'><%=responseData %></font></b><br>
		<%}else{%>
			<b>Rules Execution Status : <font color='red'><%=responseData %></font></b><br>
		<%} %>
		
		<b>Data Before Rule Execute: <%=resultBeforeChange %></b><br>
		<b>Data After Rule Execute: <%=resultAfterChange %></b><br>
		
		<%if(null!=errorData && errorData.toString().length()>0 ){%>
			<b>Error Detail :<font color='red'><%=errorData %></font></b>
		<%}%>	
	<%} %>

		
	</div>
	<br><br><br><br><br><br>

    <div id="footer" align="center">
 	    <br></br>
	    ©2013 XEROX CORPORATION. All rights reserved.
	    <br></br>
	    <a target="_blank" href="javascript:document.location.href='../help/privacy.html'">
		Privacy Policy
	    </a>
	     |
	    <a target="_blank" href="javascript:document.location.href='../help/sitemap_member.html'">
		Site Map
	    </a>
	     |
	    <a target="_blank" href="javascript:document.location.href='../help/tou.html'">
		Terms of Use
	    </a>
	     |
	    <a target="_blank" href="javascript:document.location.href='../help/browser.html'">
		Browser Requirements
	    </a>
	     |
	    <a target="_blank" href="javascript:document.location.href='../help/accessibility.html'">
		Accessibility Compliance
	    </a>
	</div>
    <div>
    </div>

</div>


</div>


</BODY></HTML>



