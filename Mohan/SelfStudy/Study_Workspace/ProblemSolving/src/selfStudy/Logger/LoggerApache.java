package selfStudy.Logger;

import org.apache.log4j.Logger;

public class LoggerApache {
	
	private static  Logger log=Logger.getLogger(LoggerApache.class.getName());
	
	
	public static void main(String args[])
	{
		
		log.error("Error Message");
		log.warn("Warning Message");
		log.info("Info Message");
		log.debug("Debug Message");
	}

}
