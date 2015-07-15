package com.mohan.restService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/sayHello/{Name}")
public class SayHello {
	
	
	@GET
	@Produces("text/plain")
	public String restSayStringHello(@PathParam("Name") String userName)
	{
		System.out.println("Rest Call Method ::>restSayStringHello"+userName);
		if(userName!=null && ! userName.isEmpty())
				return "Rest call for user :"+userName;
		
		return "This is Rest Call1";
	}
	/*
	@GET
	public String testSayString()
	{
		return "TESTsayString";
	}*/

}
