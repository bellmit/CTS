$('document').ready(function(){
	
	require(['provider/providerPageObject','provider/providerServices'],function(providerPage,providerService){

		var searchData = {};
		
		console.log("inside member");
		
		/* DropDown  */
		
		providerPage.claimTypeBox.selectpicker({
			size: 15
		});
		  
		providerPage.datePickers.datetimepicker({
			pickTime: false,
		});
		
		providerPage.memberSearchForm.change(
			function(){
				
				searchData = $(this).serializeObject();
				console.log(searchData);
			}
		).find('input[type=submit]').click(
			function(e){
				e.preventDefault();
				//validation call
				var reqData = JSON.stringify(searchData);
				providerService.searchMemberForEligibility(reqData);

				console.log("form submitted..");
				
			}	
		).find('input[type=reset]').click(
			
			function(e){
			//	$('.selectpicker').val('All');
			//	$('.selectpicker').selectpicker('render');
				console.log("form reset");
				
			}
		);
		
		
	});
	
	
	

});

function displyMemberList(gridData){
	$('#recentClaims').find('thead').html('');
	//$('#recentClaims').find('tbody').html('');
	$('#recentClaims').jdataTable({
			jsondata:gridData,
			itemsPerPage:20,
			header:{
				by:['memberId','dob','firstName','lastName','gender','serviceFrom','serviceTo'],
				titles:['Member ID','Date of Birth','First Name','Last Name','Gender','Service From','Service To'],
				sort:['number','string','string','string','string','string','string']
			},
		pagination:false
	});	
	
}
		