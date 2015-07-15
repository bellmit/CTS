define(function(){

		var providerServiceObj = {
			
			baseUrl: contextPath
				
			,providerHomeUrl: "/providerHomePage"
				
			,memberEligibilityUrl:"/memberEligibility"
			
			,searchMemberForEligibilityUrl:"/searchMemberForEligibility"			
			
			,searchMemberForEligibility:function(formData){
				
				var concatUrl = this.baseUrl+this.searchMemberForEligibilityUrl;
				
				$.ajax({
					url:concatUrl,				
					type:"POST", 				
					contentType: "application/json",
					data: formData, 
					success: function(responseData){
						LOGGER.info("success");
						displyMemberList(responseData)
						
					}
				});			
				
			}
		
		};
		
		
		return providerServiceObj;	
	
});


