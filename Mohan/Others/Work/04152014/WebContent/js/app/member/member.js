

$('document').ready(function(){
	
	
	require(['member/displayClaims','member/memberServices'],function(displayClaims,memberServices){
		
				
		$('.hideGrid').hide();
		
		/* DropDown  */
		displayClaims.selectpicker.selectpicker({
		size: 15
		});
	    
		/* From Date */
		displayClaims.datePickers.datetimepicker({
		pickTime: false,
		});
	    
		/* slider */
		displayClaims.claimAmount.slider({});
		
		/* Grid Data populate*/	
		memberServices.recentClaims();
		
		$('#searchButton').on('click',function(e){
			e.preventDefault();	
			var claimSearchData = $('#claimSearch').serializeObject();
			$('#myModal').modal('hide');
			if($('#claimResults .tblcontainer tr').length===0){
				memberServices.searchMemberForEligibility(claimSearchData);
			}
		 });
		
		$('#advancedSearch').on('click',function(e){
			e.preventDefault();	
			var claimSearchData = $('#advanceClaimSearch').serializeObject();
			$('#myModal').modal('hide');
			if($('#claimResults .tblcontainer tr').length===0){
				memberServices.searchMemberForEligibility(claimSearchData);
			}
		 });
		
    });  
	
	 $(document).on('click','#claimResults table td',function(){
			var claimType = $(this).parent('tr').find('td').eq(3).text();
			 if(claimType === 'Dental'){
				window.location ='claim_details_dental.html';
			}
			
		 });
	
});



