package com.rest.sample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path ("/rest/resttest") 
public class TestRest {

	@GET
	public String showMyName()
	{
		return "MOhan";
	}
	
}
