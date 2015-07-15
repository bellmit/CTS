define(function(){

	var memberPageObj ={
			
			 claimAmount	    : 	$("#claimAmount")
			,selectpicker		:	$('.selectpicker')
			,datePickers		:	$('.date')			
	
	};	
	populateClaimTypeData();
	
	return memberPageObj;

});

function populateClaimTypeData() {

	$.get(contextPath + "/claimType", function(data, status) {
		var claimTypeListData = data.claimTypeList;
		$('#claimType').empty();
		$.each(claimTypeListData, function(index, value) {
			$('#claimType').append($('<option/>', {
				value : value.validValueCode,
				text : value.shortDescription
			}));
		});

		$('#claimType_dialog').empty();
		$.each(claimTypeListData, function(index, value) {
			$('#claimType_dialog').append($('<option/>', {
				value : value.validValueCode,
				text : value.shortDescription
			}));
		});
	});
}


