<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<f:view>
	<h:form>
		<h1>Hello</h1>
		<h:inputText value="#{searchBean.searchNumber}" />		
		<h:commandButton value="Search" action="#{searchBean.doSearch}" />
	</h:form>	
</f:view>