<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<f:loadBundle basename="demo.bundle.Messages" var="Message"/>

<HTML>
    <HEAD> <title>Welcome Page</title> </HEAD>
	
    <body bgcolor="white">
    	<f:view>
				<h3>
    			<h:outputText value="#{Message.greeting_text}" />
    			<h:outputText value="#{GetPlaceBean.place}" />,
    			<h:outputText value="#{GetPlaceBean.country}" />! 
    		</h3>
				<h:form>
					<h:commandLink action="back">
						<f:verbatim>Visit another destination</f:verbatim>
					</h:commandLink>
				</h:form>    		
    	</f:view>
	</body>	
</HTML>  
