function showProviderOptions(entityType){
    if(entityType=='provider'){
        radioToggleNoBlock('provider_ProviderType',1);
		 radioToggle('provID',1);
		  radioToggle('provID1',1);
    	  radioToggleNoBlock('districtOffice',0);
		   radioToggle('provID',1);
		  radioToggle('provID1',1);
		  radioToggleNoBlock('county_Name',0);
		  radioToggleNoBlock('attorney',0);
        providerType=document.getElementById('providerTypeOptions').value;
        showNameOptions(providerType);
    }
    else if( entityType == 'districtOffice' ){
    	  radioToggleNoBlock('districtOffice',1);
		  radioToggleNoBlock('county_Name',1);
    	  radioToggleNoBlock('provider_ProviderName',0);
        radioToggleNoBlock('provider_ProviderType',0);
		 radioToggle('provID',0);
		  radioToggle('provID1',0);
        radioToggleNoBlock('fullName',0);
        radioToggleNoBlock('ssn',0);
		 radioToggle('provID',0);
		  radioToggle('provID1',0);
		  radioToggleNoBlock('attorney',0);
	}
    else if( entityType == 'attorney' ){
    	  radioToggleNoBlock('districtOffice',0);
		  radioToggleNoBlock('county_Name',0);
    	  radioToggleNoBlock('provider_ProviderName',0);
        radioToggleNoBlock('provider_ProviderType',0);
		 radioToggle('provID',0);
		  radioToggle('provID1',0);
        radioToggleNoBlock('fullName',1);
        radioToggleNoBlock('ssn',1);
		 radioToggle('provID',0);
		  radioToggle('provID1',0);
		  radioToggleNoBlock('attorney',1);
    }
    else{
        radioToggleNoBlock('provider_ProviderType',0);
		radioToggleNoBlock('county_Name',0);
    	  radioToggleNoBlock('districtOffice',0);
        radioToggleNoBlock('provider_ProviderName',0);
		 radioToggle('provID',0);
		  radioToggle('provID1',0);
        radioToggleNoBlock('fullName',1);
        radioToggleNoBlock('ssn',1);
		 radioToggle('provID',0);
		  radioToggle('provID1',0);
		  radioToggleNoBlock('attorney',0);
    }
}// end of function showProviderOptions




function showNameOptions(providerType){
    switch(providerType){
        case 'both':{
            radioToggleNoBlock('provider_ProviderName',1);
            radioToggleNoBlock('fullName',1);
            break;
        }
        case 'group':{
            radioToggleNoBlock('provider_ProviderName',1);
            radioToggleNoBlock('fullName',0);
            break;
        }
        default:{
            radioToggleNoBlock('provider_ProviderName',0);
            radioToggleNoBlock('fullName',1);
            break;
        }
    }//switch
}// end of function showNameOptions





function showEntitySearch(){
        radioToggle("entitySearch", 1);
        radioToggle("maintainEntity", 0);
}// end of function showEntitySearch





function hideEntitySearch(){
        radioToggle("entitySearch", 0);
        radioToggle("maintainEntity", 1);
}// end of 





function setEntityEdit(){
	var addField = document.getElementById('entAdd')
	var editField = document.getElementById('entEdit')
	addField.style.display = 'none';
	editField.style.display = '';
	showProviderOptions('provider')
}// end of 




function incCaseFor(){
  caseFor++;
  radioToggle("providerDetails", 1);
  for (var inx = 1; inx <= caseFor - 1; inx++){
      radioToggle("caseForRow0", 0);
      if (document.getElementById("caseForRow" + inx) != null){
          radioToggle("caseForRow" + inx, 1);
      }
  }
}// end of function incCaseFor



/*
NOT BEING USED

function showExtention(extentionColumnId, phoneType){

    if(phoneType=="work")
    {
        radioToggle(extentionColumnId, 1);
    }
    else
    {
        radioToggle(extentionColumnId, 0);
    }
}

function updatePrimary(primaryRow)
{}

*/