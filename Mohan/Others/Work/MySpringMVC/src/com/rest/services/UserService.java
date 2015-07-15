package com.rest.services;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Consumes("application/json")
@Produces("application/json")
public interface UserService {
	
	@Path("/user")
	public Map<String,String> getUserDetails();

}
