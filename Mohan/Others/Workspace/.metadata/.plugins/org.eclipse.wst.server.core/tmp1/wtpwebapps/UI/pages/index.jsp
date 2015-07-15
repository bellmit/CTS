<html>

<head>


<script src="scripts\jquery-1.11.1.js">

</script>
<script>
$(document).ready(function(){
	alert('test');
	$("button").click(function()
			{
				alert('button click');
			});
});</script>
<!-- <script>
function getFolder(){
  return showModalDialog("folderDialog.html","","width:400px;height:400px;resizeable:yes;");
}

function submitData(){
	 /*  if(uiForm.ruleId.value==""){
		  alert("Please Enter Rule Id");
	  }else{
		  uiForm.submit();
	  } */
} 
	
</SCRIPT>--> 
</head>
<form name="uiForm" action="/RIF/processInput" method="post"> 
<!--Outter table for center alignment -->
<table style="width: 100%;height: 100%;border: 0px;" >
<tr><td align="center" valign="middle">

<table style="width=50%;" border="1">
	



<tr>
		<td colspan="3" align="center">Input Details </td>
</tr>		
	
	<tr>
		<td>
		Enter RuleID <br>Ex:-[IGB0003.0001.01]
		</td> 
		<td><font color="red">*</font></td>
		<td>
			<input name="ruleId" type="text">
		</td>
	</tr>

	<tr>
		<td>
		 Enter Message ID <br>Ex:-[34-9999-0003]
		</td> 
		<td><font color="red"></font></td>
		<td>
			<input name="messageId" type="text">
		</td>
	</tr>
	
		<tr>
		<td>
		 Select Module 
		</td> 
		<td><font color="red"></font></td>
		<td>
			<select name="module">
				<option value="All">All</option>
				<option value="enterprise">enterprise</option>
				<option value="member">member</option>
				<option value="operations">operations</option>
				<option value="operations_comn">operations_comn</option>
				<option value="program">program</option>
				<option value="provider">provider</option>
			</select>
		</td>
	</tr>
	
		<tr>
		<td>
		 Select Module 
		</td> 
		<td><font color="red"></font></td>
		<td>
							<input type="text" name="sourcePath"> <input type="button" value="Browse..." onclick="this.form.sourcePath.value=getFolder()">
		</td>
	</tr>
		
	</table>
	
	<br><br>
	
	<br><br>
	
		<table border="1" width="50%">
		<tr>
		<td colspan="2" align="center">Generate Output </td>
		</tr>		
		
		<tr>

		
		<td align="center" colspan="2">
			<input type="button" value="Generate">
		</td>
	</tr>
	</table>
	
		<br><br>
		
		<table border="1" width="50%">
		<tr>
		<td colspan="2" align="center">Download</td>
		</tr>		
		
		<tr>
		<td>
		Download POJO <br>
		</td> 
		
		<td>
			<A href="">Click</A>
		</td>
	</tr>
		<tr>
		<td>
		Download Invocation Code <br>
		</td> 
		
		<td>
			<A href="">Click</A>
		</td>
	</tr>

	</tr>
		<tr>
		<td>
		Download Rule Identified pattern file  <br>
		</td> 
		
		<td>
			<A href="">Click</A>
		</td>
	</tr>
		
	</table>
	
	<!--Outter table for center alignment -->
	</td></tr>
	
	</table>
	
	</form>
	   
</html>