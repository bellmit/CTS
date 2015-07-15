<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <div id='menu'>
	<div class="sidebar-collapse" id="sidebar-collapse">
	   <i class="glyphicon glyph-menu-min">Menu</i>
	</div>  
    <nav id='nav' class="sidebar" >		
		<c:if test="${menuList ne null}">
			<ul>
	    		<c:forEach items="${menuList}" var="menuItem">
					<li>
						<a href='${menuItem.link}' title='${menuItem.title}'>
							<i class='${menuItem.domClass}'></i>
							<span>${menuItem.title}</span>
						</a>
						<c:if test="${menuItem.subMenus ne null}">
							<ul>
								<c:forEach items="${menuItem.subMenus}" var="subMenuItem">
									<li>
										<a href='${subMenuItem.link}'>${subMenuItem.title}</a>
									</li>
								</c:forEach>										
							</ul>
						</c:if>						
					</li>
	    		</c:forEach>
	    	</ul>
		</c:if>
    </nav>

    <div class='newsAlert'>
    	<h4><i class='glyphicon glyph-alert'></i>News & Alerts</h4>
    	
   		<c:if test="${newsList ne null}">
    		<c:forEach items="${newsList}" var="newsItem">
    			<h5>${newsItem.title}</h5>
      			<p>${newsItem.content}</p>
    		</c:forEach>
		</c:if>
    </div>
    
  </div>