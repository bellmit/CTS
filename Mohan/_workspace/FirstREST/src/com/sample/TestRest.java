package com.sample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path ("/mohan")
public class TestRest {

	@GET
	public String getmyName()
	{
		return "MOhan";
	}
	
}
