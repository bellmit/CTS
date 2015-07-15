window.LOGGER = {  
       INFO  : "INFO",     
       DEBUG : "DEBUG",
       ERROR : "ERROR",
       WARN  : "WARN",
       REPORT: "REPORT",       
       log: function(loglevel, data){
    	   		if(arguments.length > 1){
    	   			switch(loglevel){
    	   				case "INFO"   :  this.info(data);break;
					 	case "DEBUG"  :  this.debug(data);break;
					 	case "ERROR"  :  this.error(data);break;
					 	case "WARN"   :  this.warn(data);break;
					 	case "REPORT" :  this.report(data);break;
				 		default       :  console.log(data);
    	   			}
    	   		}else{
    	   			console.log(loglevel);
    	   		}
       }, // end of log function
       info:function(data){
    	   //custom logic
    	   console.info(data);
       },
       debug:function(data){
    	   //custom logic
    	   console.debug(data);
       },
       error:function(data){
    	   //custom logic
    	   console.debug(data);
       },
       warn:function(data){
    	   //custom logic
    	   console.warn(data);
       },
       report:function(data){
    	   //custom logic
    	   console.info(data);
       }       
       
}; // End of Logger
