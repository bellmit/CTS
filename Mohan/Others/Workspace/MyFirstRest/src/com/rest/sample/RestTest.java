package com.rest.sample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path ("/resttest") 
public class RestTest {

	@GET
	public String showMyName()
	{
		return "MOhan";
	}
	
}
