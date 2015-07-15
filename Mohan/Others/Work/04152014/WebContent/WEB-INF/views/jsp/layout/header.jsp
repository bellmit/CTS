<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<script type="text/javascript">
		<c:set var="context" value="${pageContext.request.contextPath}" />
		var contextPath = '<c:out value="${context}"/>';		
  	</script> 
   	
<header id='header'>
   <button type="button" class="navbar-toggle"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
   <div id='logo'><a href='#'><img src='images/Xerox_logo.png'/></a></div>
   <div id="top-nav">
      <form role='form'>
         <button type="button" class='searchbtn'> <i class="glyphicon glyph-search"></i> </button>
         <input type="text" placeholder="Search here..." class="form-control search">
      </form>
      <ul class='topLinks'>
      
     	<c:if test="${quickLinkList ne null}">
        	<li class="hidden-xs hidden-sm dropdown">
            	<a class="dropdown-toggle" data-toggle="dropdown" href="#">
            		Quick Links <span class="caret"></span>
            	</a>
            
				<ul class="dropdown-menu">
		    		<c:forEach items="${quickLinkList}" var="menuItem">
		    			<li><a href='${menuItem.link}'><i>&raquo;</i>${menuItem.title}</a></li>
		    		</c:forEach>
		    	</ul>
	            
         	</li>
         
         </c:if>
         
         <li class='hidden-xs hidden-sm'><a href='#'>Contact Us</a></li>
         <li class='hidden-xs hidden-sm'><a href='#'>Help</a></li>
         
         <c:if test="${sessionScope.userLoginsession ne null}">
	         <li class="hidden-xs hidden-sm dropdown last">
	            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
	           		<span class='glyphicon glyphicon-user'></span> 
	            	<c:out value="${sessionScope.userLoginsession}"/>
					<span class="caret"></span>
	            </a>
	            <ul class="dropdown-menu">
	               <li><a href='#'>My Account</a></li>
	               <li><a href='${context}/userLogout'>Logout</a></li>
	            </ul>
        	 </li>
         </c:if>
         
      </ul>
   </div>
</header> 