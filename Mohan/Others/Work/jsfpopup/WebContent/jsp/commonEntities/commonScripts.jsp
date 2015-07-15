<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />

<script>
<!--
/*Small save Big Save start*/
	flagWarn=true;  
	 
	 /*Small save Big Save ends*/
  

function testCall(nothing2DoOnUnload){
	// alert('script calling..');
  		
  	if (!nothing2DoOnUnload) {
  	// alert('script calling..');
	  	if (isFormChanged() == true){
	  		// alert('script calling..');
	  		var answer = confirm ("The data on the page has been modified\n Do you want to discard your changes?");
	  	}
  	}

}
function confirmDelete(formID,subviewID,id,msg) 
       { 

           var box = confirm(msg);
      if(box == true)
		   {

		
               document.getElementById('view<portlet:namespace/>:'+formID+':'+subviewID+':'+id).click();                         
           }          
       }
  
  
  function toggleSigns(obj,state){
	
	 var el = document.getElementById(obj);
	 
	 if (state==1){
	  el.style.display = 'block';
	 }
	 else if (state==0){
	  el.style.display = 'none';
	 }
	 else if (state==2){
	  if (el.style.display == 'none'){
	   el.style.display = 'block'; 
	  }
	  else if (el.style.display == 'block'){
	   el.style.display = 'none';
	  }  else {
	   el.style.display = 'none';
	  }
	 }
  }
 

  function plusMinusAfterRefresh(id,obj,hiddenTextId)
  {
    var hiddenTxt = find(hiddenTextId);
    var el = document.getElementById(id);
    if (el.style.display == 'none')
    {
     obj.className = 'plus';
     hiddenTxt.value = 'plus';
    }
  else if ((el.style.display == 'block')  || (el.style.display == ''))
   {
    obj.className = 'minus';
    hiddenTxt.value = 'minus';
   }
 else if (el.style.display == '')
  {
   obj.className = 'minus';
   hiddenTxt.value = 'minus';
  }

}

 function afterRefreshToggle(hiddenText, area, outputLinkID)
 {
   var hiddenTxtComponent = find(hiddenText);
  if (hiddenTxtComponent.value == 'minus')
   {
    showAll(area);
    var outputLinkComp = findLink(outputLinkID);
    plusMinusForRefresh(area,outputLinkComp,hiddenText)
   }
 }

 function setHiddenValue(hiddenVariable, hiddenValue) 
  {
 	hiddenVariable.value = hiddenValue;
  }

 function renderOtherHidden(formId,subviewId,hiddenId, sectionId,linkId)
  { 
    var ele = 'view<portlet:namespace/>:' + formId + ':' + subviewId +  ':' + hiddenId ;
   
	var hiddenVariable = document.getElementById(ele);
    
	if ((hiddenVariable == null) ||(hiddenVariable == '')|| (hiddenVariable.length == 0)) 
	{
	  hideDivById(sectionId);
	} 
	else if ((hiddenVariable.value == 'plus')) 
	{ 
	 hideDivById(sectionId);
		
	} 
	else if ((hiddenVariable.value == 'minus')) 
	{
	 var ele1 = 'view<portlet:namespace/>:' + formId + ':' + subviewId +  ':' + linkId ;	
	 var plusMinusLinkObj = document.getElementById(ele1);	
	 plusMinusLinkObj.className = 'minus';
	} 
	else 
	{
		hideDivById(sectionId);		
	}
  }
  function renderOtherHiddenSection(formId,hiddenId, sectionId,linkId)
  { 
    var ele = 'view<portlet:namespace/>:' + formId + ':'  + hiddenId ;
   
	var hiddenVariable = document.getElementById(ele);
    
	if ((hiddenVariable == null) ||(hiddenVariable == '')|| (hiddenVariable.length == 0)) 
	{
	  hideDivById(sectionId);
	} 
	else if ((hiddenVariable.value == 'plus')) 
	{ 
	 hideDivById(sectionId);
		
	} 
	else if ((hiddenVariable.value == 'minus')) 
	{
	 var ele1 = 'view<portlet:namespace/>:' + formId + ':'  + linkId ;	
	 var plusMinusLinkObj = document.getElementById(ele1);	
	 plusMinusLinkObj.className = 'minus';
	} 
	else 
	{
		hideDivById(sectionId);		
	}
  }
  function hideDivById(hideId) 
  {
   if (!document.getElementById) return null;
	 var hideVar = document.getElementById(hideId);	
			
	if (hideVar != null)
	 {
	 	hideVar.style.display = "none";
     }
  }
  
    function isElementChanged( ele, i ) {
	 var isEleChanged = false; 
	 switch ( ele[i].type ) { 
	  case "text" : 
	   
	   if ( ele[i].value != ele[i].defaultValue ) return true;
	  break;
	
	  case "textarea" : 
	   if ( ele[i].value != ele[i].defaultValue ) return true;
	  break;
	
	  case "radio" :
	   val = "";
	   if ( ele[i].checked != ele[i].defaultChecked ) return true;
	  break;
	
	  case "select-one" : 
	   for ( var x =0 ; x <ele[i].length; x++ ) {
	    if ( ele[i].options[ x ].selected 
	      != ele[i].options[ x ].defaultSelected ) 
	        return true;
	   }
	  break;
	
	  case "select-multiple" :
	   for ( var x =0 ; x <ele[i].length; x++ ) {
	    if ( ele[i].options[ x ].selected 
	      != ele[i].options[ x ].defaultSelected ) 
	        return true;
	   }
	  break;
	
	  case "checkbox" :
	   if ( ele[i].checked != ele[i].defaultChecked ) return true;
	
	  default:
	  
	   return false;
	  break;
	 }
   }


/*Small save Big Save start*/

  /** Added this function for Big Save/Little Save CR
  This function will tell if there is any data changed on the form
  */
  
  function isFormChanged(formId) {
    
 	 var ele = document.forms[formId].length;
 	 
 	 for ( i=0; i < ele; i++ ) {
 		  if (document.forms[formId].elements[i].type != undefined){
 		  
 			 //var isEleChanged = false; 
 			 
 			
 			 switch ( document.forms[formId].elements[i].type ) { 
 			  case "text" : 
 			   if ( document.forms[formId].elements[i].value != document.forms[formId].elements[i].defaultValue ){
 			   
 			    return true;
 			   }
 			  break;
 			
 			  case "textarea" : 
 			   if ( document.forms[formId].elements[i].value != document.forms[formId].elements[i].defaultValue ){
 			   
 			    return true;
 			   }
 			  break;
 			
 			  case "radio" :
 			  
 			   if ( document.forms[formId].elements[i].checked != document.forms[formId].elements[i].defaultChecked ){
 			   	
 			    return true;
 			   }
 			  break;
 			
 			  case "select-one" :
                var flag=""; 
                var defInd="";
                var selInd="";
              for ( var x =0 ; x < document.forms[formId].elements[i].length; x++ ) {
                if(document.forms[formId].elements[i].options[x].defaultSelected) {
                   flag="true";
                   defInd=x;
                }  
              if(document.forms[formId].elements[i].options[ x ].selected)   {
                   selInd=x;
                }
              }
             if(defInd=="" && selInd!="")
              {
                   flag="true";
                   defInd=0;
              }    
             if(flag=="true") {  
               if (document.forms[formId].elements[i].options[selInd].value!=document.forms[formId].elements[i].options[defInd].value )  {
              	   flag="false";
              }
             }
            if(flag=="false") {
                 return true;
            }

 			  break;
 			
 			  case "select-multiple" :
 			   for ( var x =0 ; x <document.forms[formId].elements[i].length; x++ ) {
 			    if ( document.forms[formId].elements[i].options[ x ].selected 
 			      != document.forms[formId].elements[i].options[ x ].defaultSelected ) {
 			   		
 			        return true;
 			    }
 			   }
 			  break;
 			
 			  case "checkbox" :
 			    if ( document.forms[formId].elements[i].checked != document.forms[formId].elements[i].defaultChecked ){
 			   	
 			     return true;
 			    }
 				break;
 				
 			case "hidden" : 
 				
 					break;
 				
 			default:			 
 			  break;
 			 }		  
 		}		  
 	 }
 	 return false;
 	 }
 



  /** This function will warn the user before he leaves the page. checkval=document.getElementById(formId + ':'  + "fileSavedFlag").value;
  
	 if (checkval == "true"){
	{
	alert(checkval);
	flagWarn=false;
	}
	
  */  
  function warnBeforeExit(formId) {
  //alert("flagWarn::"+flagWarn);
  /*alert("Form Id::::"+formId);*/
 
	if(flagWarn==true){	  	
  		// alert('inside warnBeforeExit');
		  	if (isFormChanged(formId) == true){
		  		 //alert('inside isFormChanged(formId)');		  		
			  		 		 event.returnValue="The data on the page has been modified\n Do you want to discard your changes?";
		  }
	  }
	 flagWarn=true;
  }
  
  
   function warnBeforeExitForMaintainEntity(formId)
  { 
      var smallSaveFlag=document.getElementById(formId + ':'  + "smallSaveFlag").value  
      if(document.getElementById(formId + ':'  + "fileSavedFlag").value == 'true')
      { 
         flagWarn=false;
      } 
	  if(flagWarn==true){	    		
		  	if (smallSaveFlag == 'true' ||isFormChanged(formId) == true){		  		 	  		
			  	 event.returnValue="The data on the page has been modified\n Do you want to discard your changes?";
		  }
	  }
	  flagWarn=true;
  }
  
  
  
  
  function warnBeforeExitForLogCrs(formId) {
    //alert("flagWarn::"+flagWarn);
    /*alert("Form Id::::"+formId);*/
    var smallSaveFlag=document.getElementById(formId + ':'  + "logCrSmallSaveFlag").value 
  	if(flagWarn==true){	  	
  		if (smallSaveFlag == 'true' || isFormChanged(formId) == true){		  		
  			event.returnValue="The data on the page has been modified\n Do you want to discard your changes?";
  		}  		  		  
  	  }
    flagWarn=true;

    }
    
/*Small save Big Save ends*/
  
 function renderContact(hiddenId,linkId,sectionId)
 {
 	var portletNameSpace = 'view<portlet:namespace/>';
 	var hiddenId = portletNameSpace + ':' + hiddenId;
 	var linkId = portletNameSpace + ':' + linkId;
 	var sectionId = portletNameSpace + ':' + sectionId;
 	var hiddenVariable = document.getElementById(hiddenId);
 	if ((hiddenVariable.value == 'plus')) 
 	{
 		  hideCntDivById(sectionId);
 	}
 	else if ((hiddenVariable.value == 'minus')) 
 	{
 		var plusMinusLinkObj = document.getElementById(linkId);	
	 	plusMinusLinkObj.className = 'minus';
 	}
 	else 
	{
	  hideCntDivById(sectionId);
	}
 	
 }
 
  function hideCntDivById(hideId) 
  {
   if (!document.getElementById){ return null};
  
	 var hideVar = document.getElementById(hideId);	
			
	if (hideVar != null)
	 {
	 	
	 	hideVar.style.display = "none";
     }
     
  }
  function entityToggle(obj,state){
  var portletNameSpace = 'view<portlet:namespace/>';
  obj = portletNameSpace + ':'+ obj;
	var el = document.getElementById(obj);
	if (state==1){
		el.style.display = 'block';
	}
	else if (state==0){
		el.style.display = 'none';
	}
	else if (state==2){
		if ((el.style.display == 'none') || (el.style.display == '')) {
			el.style.display = 'block';	
		}
		else if (el.style.display == 'block'){
			el.style.display = 'none';
		}
	}}
	function entityPlusMinusToggle(id,obj,hiddenTextId) 
	{
		
		var portletNameSpace = 'view<portlet:namespace/>';
		id = portletNameSpace + ':'+ id;
		hiddenTextId = portletNameSpace + ":" + hiddenTextId;
		 var hiddenTxt = document.getElementById(hiddenTextId);
		 
		 var el = document.getElementById(id);
		 if (el.style.display == 'none') {
		  obj.className = 'plus';
		  hiddenTxt.value = 'plus';
		 } else if (el.style.display == 'block') {
		  obj.className = 'minus';
		  hiddenTxt.value = 'minus';
		 } else if (el.style.display == '') {
		  obj.className = 'minus';
		  hiddenTxt.value = 'minus';
		 }
		
	}
 function setEntityHiddenValue(hiddenVariable, hiddenValue) {
 	var portletNameSpace = 'view<portlet:namespace/>:';
 	document.getElementById(portletNameSpace+hiddenVariable).value = hiddenValue;
 }  
  
  


/* Added for CR ESPRD00653670
 Description:
 Warn the user with Data Loss Warning Messages when there is a potential for loss of entered data.
 Function use role information and  warning flag indicator as input and warn the user with Data Loss Warning Messages (confirm popup) when the role is not inquiry and  warn flag indicator value is true.
 User should  Select 'OK' to navigate away from the page or 'Cancel' to return to the page to save the data.                                       

Input :
flagWarn: Component should pass 'False' indicator value when pop up is not require(Default to true).
FlagRole: It is used to track user role in the enterprise, it will be set to false when the user is inquiry only user(Default to true).

Output : 
 Show warn confirm pop up when both the indicator is 'true'. If any of the indicator is 'false' / The user select OK confirm button it will perform the default intended action.
 If User select Cancel confirm button it will suppress the default action and stay on the same screen.
Window Object has returnValue property.  If you set it to false the default action is prevented.
 */

//script.js file:- This is the common file which is included in all the jsp files.
//Plug-in the below JavaScript code in script.js file
  

	
	<%-- var flagWarn=true;  
     var flagRole=true;
     
    function warnBeforeCancel()
     {		
         if(flagWarn == true && flagRole == true)
        {	
			event.returnValue = "If data has been entered and not saved, it will be lost. Select 'OK' to navigate away from the page or 'Cancel' to return to the page to save the data.?";   		
		}
		flagWarn=true;
		flagRole=true;
   	}
		window.onbeforeunload=warnBeforeCancel;--%>

		 var flagWarn=true;  
	     var flagRole=true;
	     flagDuplicateConfirmMsg = false; 

	 	window.onbeforeunload = function(evt) 
	 	{ 
	 		evt = (evt) ? evt : window.event; 
	 		if(flagWarn == true)
	 	 	 { 
	 			(flagRole == true)
	 			{
			 		if(!flagDuplicateConfirmMsg) 
			 	 	{ 
				 		evt.returnValue = "If data has been entered and not saved, it will be lost. Select 'OK' to navigate away from the page or 'Cancel' to return to the page to save the data."; 
				 		disableDuplicateConfirmMsg();     
		 			}
	 			}	
	 		}
		 	flagWarn=true;
		 	flagRole=true; 
	 	}

	 	function disableDuplicateConfirmMsg()
	 	{
	 		flagDuplicateConfirmMsg = true; 
	 		setTimeout("enableConfirmMsg()", 50);
	 	}


	 	function enableConfirmMsg()
	 	{ 
	 		flagDuplicateConfirmMsg = false;
	 	} 

	 	<%--Below java script code is added to display valid pop up message when user will click cancel button in Associated Correspondence section For Defect ESPRD00802327--%>

	 	flagWarning=true; 
	 	//flagRole=true;
	 	flagDuplicateConfMsg = false;
	 		function cancelPopUp(flagWarning){
	 		
	 			if(flagWarning=='true') { 
	 			
	 				if( flagRole == 'true'|| flagRole == true) {
	 					if(!flagDuplicateConfMsg) { 
	 					    var btnClick=confirm("If data has been entered and not saved, it will be lost.\nSelect 'OK' to navigate away from the page\nor 'Cancel' to return to the page to save the data.");
	 						if (btnClick==false)
	 						{ 
	 							flagWarn = true;
	 							return false;
	 						}
	 				   		disableDuplicateConfMsg();
	 					}
	 			}else{
	 				flagWarning = true;
	 				flagRole = false;
	 			}
	 		  }
	 	    }

	 		function disableDuplicateConfMsg()
	 		{
	 			flagDuplicateConfMsg = true; 
	 			setTimeout("enableConfMsg()", 50);
	 		}

	 		function enableConfMsg()
	 		{ 
	 			flagDuplicateConfMsg = false;
	 		} 



--></script>

