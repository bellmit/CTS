define(function(){

		var memberServiceObj = {
				
			memberHomeUrl: contextPath+"/getClaims"				
			
			,searchMemberForEligibility:function(claimSearchData){
				
				
							
				$.get(this.memberHomeUrl,claimSearchData,function(data,status) {
					
					
					$('.hideGrid').show();
					
		               var tData = [];		               
		               for(var k=0;k<data.claimDetails.length;k++){
		               
		            		  var rObj = {
		            			   'fromDate':data.claimDetails[k].strDisplayBegin
		        				  ,'toDate':data.claimDetails[k].strDisplayEnd
		        				  ,'providerName':data.claimDetails[k].providerName
		        				  ,'claimType':data.claimDetails[k].claimTypeCD
		            				  
		            		  };
		            		  
		            		  tData.push(rObj); 	   
		               }
		              
		               
			           $('#claimResults').jdataTable({
				     		jsondata:tData,
				     		itemsPerPage:5,
				     		header:{
				     		by:[],
				     		titles:['From Date','To Date','Provider Name','Claim Type'],
				     		sort:['date','date','string','string']
				     		},
				     		//pagination:true
			     	});
			    
					});
				
			},
			
			recentClaims:function(){
				
				$.post(contextPath+"/memberLandingPageResult"	, function( data,status ) {		             
		               var tData = [];		               
		               for(var k=0;k<data.RecentClaims.length;k++){
		               
		            		  var rObj = {
		            			   'fromDate':data.RecentClaims[k].fromDate
		        				  ,'toDate':data.RecentClaims[k].toDate
		        				  ,'providerName':data.RecentClaims[k].providerName
		        				  ,'claimType':data.RecentClaims[k].claimType
		            				  
		            		  };
		            		  
		            		  tData.push(rObj); 	   
		               }
		               
		               
			           $('#recentClaims').jdataTable({
			     		jsondata:tData,
			     		itemsPerPage:5,
			     		header:{
			     		by:[],
			     		titles:['From Date','To Date','Provider Name','Claim Type'],
			     		sort:['date','date','string','string']
			     		},
			     		pagination:false
			     	});
			     });
				
			}
		
		};		
		
	return memberServiceObj;	
	
});