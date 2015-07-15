<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />

<script language="javascript"><!--

/*Small save Big Save start*/    	 	
 	flagWarn=true;  
	formId = 'view<portlet:namespace/>:';    
	selectOne=false; 
    fileSavedFlag=false;
    datechanged=false;
  /** Added this function for Big Save/Little Save CR
  This function will tell if there is any data changed on the form
  */
  
   function isFormChanged(formId) {
	  var ele = document.forms[formId].length;
	 for(var i=0;i<ele;i++) {
	 
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
			   val = "";
               if ( document.forms[formId].elements[i].checked != document.forms[formId].elements[i].defaultChecked ){
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
				
				case "hidden" : 
				break;		  
			  }		  
		   }	
	    }
		formId = 'view<portlet:namespace/>:'; 
	 	return false;
	}   
 	function warnBeforeExit(frmId) 
 	{		
 	    if(formId == 'view<portlet:namespace/>:')
 	    {
 	    	formId=formId+frmId;
 	    } 	  	  
      	var rowadd=document.getElementById(formId+':internalSubmit').value;       	
       	<%--if(document.getElementById(formId+':smallSaveCount'))
        { 
          	var savecount = document.getElementById(formId+':smallSaveCount').value;         
           	if(savecount != '0')
            {            
           		rowadd = 'true' ;
            }
        }--%>      
      	var isSavedOrUpdate=document.getElementById(formId+':warnBeforeExitId').value;     
			if(isSavedOrUpdate == 'saveOrUpdate')
			{
			     flagWarn=false;
			}else{
				if(flagWarn==true ){	
		  		  		
		  		formId = 'view<portlet:namespace/>:'; 
			    event.returnValue="The data on the page has been modified\n Do you want to discard your changes?";
		  	
		}	
			} 	
        if(fileSavedFlag == true)
		{
		     flagWarn=false;
		}	
		if(flagWarn==true ){	
			
		  	if (selectOne || rowadd == 'true' || isFormChanged(formId) == true)
		  	{		  		
		  		formId = 'view<portlet:namespace/>:'; 
			    event.returnValue="The data on the page has been modified\n Do you want to discard your changes?";
		  	}
		}		
 	}

 	
		
	function setformId(frmId)
	{       
      	formId='view<portlet:namespace/>:'+frmId;     
	}
	
	
	function internalsubmmit(frmId)
	{
		if(formId == 'view<portlet:namespace/>:')
 	    {
 	     	formId=formId+frmId;
 	    }
		 document.getElementById(formId+':internalSubmit').value='true';
		 fileSavedFlag=true;
	}

   // Added for the defect id ESPRD00304541
   function orderNoWarn(value)
	{
	 flagWarnOrderNo= event.returnValue="System will overwrite the existing Case Step or delete the association of Case Step";
	 alert(flagWarnOrderNo);
	}
	
	function automaticRouteToWarn(value)
	{
	 flagWarnOrderNo= event.returnValue="System will overwrite the existing Case Step or delete the association of Case Step";
	 alert(flagWarnOrderNo);
    }
	// Ends 
	
	
    function addeditrow(frmId)
    {	      
   		if(formId == 'view<portlet:namespace/>:')
 	    {
 	     	formId=formId+frmId;
 	    }
       	if(isFormChanged(formId))
       
		document.getElementById(formId+':internalSubmit').value='true';		

      	fileSavedFlag=true;

    } 
    function warnBeforeExitForLogCase(formId) {
        //var smallSaveFlag=document.getElementById(formId + ':'  + "logCrSmallSaveFlag").value 
        if(flagWarn==true){
      		if (isFormChanged(formId) == true){	
      				
      			event.returnValue="The data on the page has been modified\n Do you want to discard your changes?";
      		}  		  		  
      	  }
        flagWarn=true;
     }
    
    /*
	function warnBeforeCancel() 
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
	var flagRole=true;
    function warnBeforeCancel() {
    	formId='view<portlet:namespace/>:CMlogCase';
        //if(flagWarn == true && flagRole == true && flg == false){ // Commented for the defect id ESPRD00705914 
       	if(flagWarn == true && flagRole == true){
			event.returnValue = "If data has been entered and not saved, it will be lost. \nSelect 'OK' to navigate away from the page\n or 'Cancel' to return to the page to save the data.?";
		}
		flagWarn=true;
		flagRole=true;
   }
	window.onbeforeunload=warnBeforeCancel;
	
 --></script>
