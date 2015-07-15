<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<script type="text/javascript">
		<c:set var="context" value="${pageContext.request.contextPath}" />
		var contextPath = '<c:out value="${context}"/>';		
  	</script>
  	
  	<script>
	function callAjaxGet()
	{
		alert('callAjax');
		xmlhttp=new XMLHttpRequest();
		xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
			  //alert(xmlhttp.responseText);
			  document.getElementById("displayajax").innerHTML=xmlhttp.responseText;
		    }
		  }
	    xmlhttp.open("GET","/MySpringMVC/getAjaxDataGet?name='mohan'",true);
		xmlhttp.send();
		
	}
	function callAjaxPost()
	{
		alert('callAjax');
		xmlhttp=new XMLHttpRequest();
		xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
			  //alert(xmlhttp.responseText);
			  //document.getElementById("displayajax").innerHTML=xmlhttp.responseText;
			  xmldoc=xmlhttp.responseXML;
			  x=xmldoc.getElementsByTagName("Name");
			  txt="";
			  for (i=0;i<x.length;i++)
			   {
			   txt=txt + x[i].childNodes[0].nodeValue + "<br>";
			   }
			  document.getElementById("displayajax").innerHTML=txt;
		    }
		  };
		xmlhttp.open("POST","/MySpringMVC/getAjaxDataPost",true);
		xmlhttp.send();
		
	}
	
	function callAjaxRest()
	{
		alert('callAjaxRest');
		xmlhttp=new XMLHttpRequest();
		xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
			  alert(xmlhttp.responseText);
			  document.getElementById("displayajax").innerHTML=xmlhttp.responseText;
			  
		    }
		  };
		xmlhttp.open("POST","http://localhost:9999/calcrest/calc/add/1/2",true);
		xmlhttp.send();
		
	}
</script>
  	
  	

<!-- <script>
   $(function() {
	  alert('test');
    $( "#tabs" ).tabs();
  }); 
  </script> -->
<div id=tabs>
	<ul>
        <li><a href="#tabs-1">Ajax Call</a></li>
        <li><a href="#tabs-2">JGrid</a></li>
        <li><a href="#tabs-3">REST</a></li>
    </ul>
    <div id="tabs-1">
        <p>
			<input type="text" name="name" id="checkname"/>
			<input type="button" name="search" id="search" value="search" onchange="" ></input>
			  			
			<div id='suggestname' class='responsivetbl'>
		          <div class='tblcontainer'>
		            <table class="table table-bordered table-striped">
		               <thead>
		               </thead>
		               <tbody>
		               </tbody> 
		            </table>
		         </div> 
     		</div>
			
		</p>
		<div id="displayajax"></div>
		<input type="button" value="GetMethod" onclick="callAjaxGet();">
		<input type="button" value="PostMethod" onclick="callAjaxPost();">
		<!-- <input type="button" value="RESTCALL" onclick="callAjaxRest();"> -->
    </div>
    <div id="tabs-2">
        <p>Content for Tab 2</p>
    </div>
    <div id="tabs-3">
        <p>Content for Tab 3</p>
    </div>
</div>