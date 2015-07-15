package selfStudy.Logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerTest {

	public static Logger logger=Logger.getLogger(LoggerTest.class.getName());
	/**
	 * @param args
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	public static void main(String[] args) throws SecurityException, IOException {
		// TODO Auto-generated method stub
		Handler handler=new FileHandler("mohan.log",true);
		handler.setLevel(Level.WARNING);
		logger.addHandler(handler);
		logger.setLevel(Level.INFO);
		
		logger.log(Level.INFO, "MY Fisrt Logger");
		logger.warning("Info Messafwe");
		handler.setFormatter(new SimpleFormatter());
	}

}
