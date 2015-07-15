<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<f:view>
	<h:form>
		<h1>Greater Than 10</h1>	
		<h:inputText value="#{searchResultBean.searchNum}"/>	
	</h:form>	
</f:view>