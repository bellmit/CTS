<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<f:loadBundle basename="demo.bundle.Messages" var="Message"/>

<HTML>
  <HEAD> <title>Input Data Page</title>
		<style>
			.header {
				font-size:30px;
				color:black;
				padding-bottom: 20px;
			}
			p.note {
				border:1 solid black;
				font-size:12px;
			}

	</style>
	<script language="javascript">

		var formId; // reference to the main form
		var winId;	// reference to the popup window


		// This function calls the popup window.
		//
		function showPlaceList(action, form, target) {
			formId=action.form.id;
			features="height=300,width=250,status=yes,toolbar=no,menubar=no,location=no,scrollbars=yes";			
			winId=window.open('','list',features); // open an empty window

			hform=document.forms[form]; // reference to the hidden form
			  
			//This is an emulation of the action link being clicked.
			hform[form+':'+target].value=form+':'+target;

			// Copy the current country variable value
			// to the corresponding field of the hidden form.
			hform[form+":country"].value = getCountry(action.form);

			// Submit the hidden form. The output will be sent to the just opened window.
			hform.submit();
		}
		
		
		// This function is called from the popup window 
		// when a user clicks on a state or province from the list.
		// The selected value is copied to a "place" text field
		// in the main form.
		// 
		function updatePlace(place) {
					form=document.forms[formId];
					form[formId+":place"].value=place;
					winId.close();
		}
		
		
		// This function returns the selected value 
		// from the drop down list.
		//
		function getCountry (form) {
			field = form[form.id+":country"];
			return field.value;
		}
	
		// This function cleans up the "place" text field.
		//  
		function resetTextField(form, field) {
			fieldName=form.id+":"+field;
			form[fieldName].value="";
		}

	</script>
 </HEAD>

 <body bgcolor="white">
	<f:view>
	  <%-- This is the main form shown on the screen. --%>
    <h:form id="whereForm">
	 		<h:panelGrid columns="2" headerClass="header">
 				<f:facet name="header">
		 			<h:outputText value="#{Message.inputdata_header}"/>
 				</f:facet> 
 				
 				<h:outputText value="#{Message.promptName}"/>
				<h:selectOneMenu id="country" value="#{GetPlaceBean.country}" onchange="resetTextField(this.form,'place')">
					<f:selectItem itemValue="USA" itemLabel="Unated States"/>
					<f:selectItem itemValue="Canada"  itemLabel="Canada"/>
				</h:selectOneMenu>

   			<h:outputText value="#{Message.promptPlace}"/>
   			<h:panelGroup>
    			<h:inputText id="place" value="#{GetPlaceBean.place}" />
    			<h:commandButton id="find" action="showPlace" value="..." immediate="true"
    			 onmousedown="showPlaceList(this,'placeList','find')"
    			 onclick="return true">
    			 	<f:actionListener type="demo.PopUpActionlistener" />
    			 </h:commandButton>
    		</h:panelGroup>

 				<f:facet name="footer">
 					<h:panelGrid width="410" columns="1">
 						<f:facet name="header">
		 					<h:commandButton id="visit" action="visit" value="Let's Go...">
		 						<f:actionListener type="demo.PopUpActionlistener" />
		 					</h:commandButton>
					 	</f:facet>
					 	<f:verbatim>
					 		<br>
					 		<p class="note">
					 			<b>Using Popup Windows in JavaServer Faces Applications</b><br><br>
					 			This example shows how to use a client-side script to control a form field
					 			rendered by JSF, how to launch a popup window and return a result back 
					 			to the main window, and how to use the standard JSF Navigation Framework for navigation
					 			in a multiple-windows interface enviromnent.<br>
					 			(Validation of user input is outside of the scope for this example.)
					 		</p>
					  </f:verbatim>				 
		 			</h:panelGrid>
 				</f:facet>
 			</h:panelGrid>    	
    </h:form>
    <%-- This hidden form used to send a request to a popup window. --%>
    <h:form id="placeList" target="list">
    	<h:inputHidden id="country" value="#{ListHolderBean.country}">
    	</h:inputHidden>
    			<%--
    				The command_link below is used to navigate to a page
    				that shows a list of states or provinces.
    			--%>
    			<h:commandLink id="find" action="showPlace" value="">
    				<f:verbatim></f:verbatim>
    			</h:commandLink>
    </h:form>    	
  </f:view>
 </body>
</HTML>  
