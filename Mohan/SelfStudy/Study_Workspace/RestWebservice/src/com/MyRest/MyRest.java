package com.MyRest;

import org.glassfish.jersey.server.ResourceConfig;

public class MyRest extends ResourceConfig
{

	public MyRest()
	{
	        packages("com.mohan.restService");
	   
	}
}
