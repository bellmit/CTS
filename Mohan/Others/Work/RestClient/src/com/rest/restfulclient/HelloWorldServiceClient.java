package com.rest.restfulclient;


import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


public class HelloWorldServiceClient {
	
	 static final String REST_URI = "http://localhost:8080/RulesExternalization/";
	 static final String hello_uri = "/ExecutePojoService/";
	 
	 
	 public static void main(String[] s) {
		 String msg = "welcome to jersey";
		 ClientConfig config = new DefaultClientConfig();   
		 Client client = Client.create(config);   
		 WebResource service = client.resource(REST_URI);   
         WebResource addService = service.path("").path(hello_uri);
         System.out.println("Rest service execution"+getResponse(addService));

	 }

private static String getResponse(WebResource service) {   
       return service.accept(MediaType.TEXT_XML).get(ClientResponse.class).toString();   
       }   
  
    private static String getOutputAsXML(WebResource service) {   
    	return service.accept(MediaType.TEXT_XML).get(String.class);   
    	} 




}
