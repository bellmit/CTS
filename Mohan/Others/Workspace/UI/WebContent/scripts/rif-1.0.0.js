$(document).ready(function() {
		$( "#progressbar" ).progressbar({
		value: false
		});
		
		$("#uiForm").submit(function(e)
				{
					console.log( $( this ).serialize() );
					if(!validate())
					{
						alert("Please Enter Rule Id or Message Id");
					}
					else
					{
					    var postData = $(this).serializeArray();
					    var formURL = $(this).attr("action");
					    $( "#progressbar" ).show();
					    $( "#showDownload" ).hide();
					    
					    $.ajax(
					    {
					        url : formURL,
					        type: "POST",
					        data : postData,
					        datatype : "json",
					        success:function(data) 
					        {
					        	//Set pojoPath
					            var sUrl=data.pojoPath;
					            $("#pojoLink").attr("href", sUrl);
					            var fileName = sUrl.substring(sUrl.lastIndexOf('\\') +1, sUrl.length);
					            $("#pojoLink").attr("download",fileName);
					            
					            //Set invokeLink
					            sUrl=data.invokeCodePath;
					            $("#invokeLink").attr("href", sUrl);
					            fileName = sUrl.substring(sUrl.lastIndexOf('\\') +1, sUrl.length);
					            $("#invokeLink").attr("download",fileName);
					            
					            //Set patternLink
					            sUrl=data.patternPath;
					            $("#patternLink").attr("href", sUrl);
					            fileName = sUrl.substring(sUrl.lastIndexOf('\\') +1, sUrl.length);
					            $("#patternLink").attr("download",fileName);
					            
					            
					            $( "#progressbar" ).hide();
					            $( "#showDownload" ).show();
					        },
					        error: function(jqXHR, textStatus, errorThrown) 
					        {
					            //if fails      
					             alert('fails'+textStatus+errorThrown);
					        }
					    });
					}
				    e.preventDefault(); //STOP default action
				    e.unbind();
				});
	
	//UploadFile
		var options = {
		        beforeSend : function() {
		                $("#progressbox").show();
		                // clear everything
		                $("#progressbar").width('0%');
		                $("#message").empty();
		                $("#percent").html("0%");
		        },
		        uploadProgress : function(event, position, total, percentComplete) {
		                $("#progressbar").width(percentComplete + '%');
		                $("#percent").html(percentComplete + '%');

		                // change message text to red after 50%
		                if (percentComplete > 50) {
		                $("#message").html("<font color='red'>File Upload is in progress</font>");
		                }
		        },
		        success : function(data) {
		                $("#progressbar").width('100%');
		                $("#percent").html('100%');
		              //Set pojoPath
			            var sUrl=data.pojoPath;
			            $("#pojoLink1").attr("href", sUrl);
			            var fileName = sUrl.substring(sUrl.lastIndexOf('\\') +1, sUrl.length);
			            $("#pojoLink1").attr("download",fileName);
			            
			            //Set invokeLink
			            sUrl=data.invokeCodePath;
			            $("#invokeLink1").attr("href", sUrl);
			            fileName = sUrl.substring(sUrl.lastIndexOf('\\') +1, sUrl.length);
			            $("#invokeLink1").attr("download",fileName);
			            
			            if(data.hasOwnProperty("junitPath"))
			            {
			            	sUrl=data.junitPath;
			            	$("#junitLink1").attr("href", sUrl);
			            	fileName = sUrl.substring(sUrl.lastIndexOf('\\') +1, sUrl.length);
			            	$("#junitLink1").attr("download",fileName);
			            }
			            else
			            {
			            	$("#junitLink1").attr("href", "#");
			            }
			            $( "#progressbar" ).hide();
			            $( "#showDownload1" ).show();
		                
		        },
		        complete : function(response) {
		        $("#message").html("<font color='blue'>POJO has been extracted!</font>");
		        },
		        error : function() {
		        $("#message").html("<font color='red'> ERROR: unable to upload files</font>");
		        }
		};
		$("#UploadForm").ajaxForm(options);
		
		
	});

function validate()
{
	if($('#ruleId').val()=="" && $('#messageId').val()=="")
		return false;
	return true;
}