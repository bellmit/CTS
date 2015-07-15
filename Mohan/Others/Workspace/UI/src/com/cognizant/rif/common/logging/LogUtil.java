package com.cognizant.rif.common.logging;

/**************************************************************************************
 **************************************************************************************/
import java.net.URL;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * This is the class used to log the messages at various level  
 *
 */
public final class LogUtil {
    public static final String STR_DEBUG = "DEBUG";
    public static final String STR_INFO = "INFO";
    public static final String STR_WARN = "WARN";
    public static final String STR_ERROR = "ERROR";
    public static final String STR_FATAL = "FATAL";
    static {
        setAppender();
    }

    /**
     * Method setAppender. Intializes Log4J configurations by loading the property file.
     * Log4j.properties is the default property file searched in the classpath where this class
     * is loaded. The name of the property file could be overwritten by giving the property file
     * name as the system property like -Dlog4jPropFile.
     *
     */
    public static void setAppender() {
        final LogUtil util = new LogUtil(); /* Instantiated to get the URL of this class loader.*/
        String log4jProp = System.getProperty("log4jPropFile"); /* To override default File name*/

        if (log4jProp == null) {
            log4jProp = "log4j.xml";
        }

        /* URL propFileUrl = util.getClass().getResource(log4jProp);*/
        final URL propFileUrl = util.getClass().getResource("/" + log4jProp);

        /* URL propFileUrl = ClassLoader.getSystemResource(log4jProp);*/
        // PropertyConfigurator.configure(propFileUrl);
        DOMConfigurator.configure(propFileUrl);
    }

    /**
     * 
     * @param level
     * @param className
     * @param methodName
     * @param message
     * @param loggerName
     */
    public static void log(final String level, final String className, final String methodName,
            final String message, final String loggerName) {
        logMessage(level, className, methodName, message, loggerName, null);
    }

    /**
     * 
     * @param className
     * @param methodName
     * @param message
     * @param loggerName
     */
    public static void debug(final String className, final String methodName, final String message,
            final String loggerName) {
        if (LogUtil.isDebugEnabled(loggerName)) {
            log(STR_DEBUG, className, methodName, message, loggerName);
        }
    }

    /**
     * 
     * @param className
     * @param methodName
     * @param message
     * @param loggerName
     */
    public static void info(final String className, final String methodName, final String message,
            final String loggerName) {
        if (LogUtil.isInfoEnabled(loggerName)) {
            log(STR_INFO, className, methodName, message, loggerName);
        }
    }

    /**
     * 
     * @param className
     * @param methodName
     * @param message
     * @param loggerName
     */
    public static void warn(final String className, final String methodName, final String message,
            final String loggerName) {
        log(STR_WARN, className, methodName, message, loggerName);
    }

    /**
     * 
     * @param className
     * @param methodName
     * @param message
     * @param loggerName
     */
    public static void error(final String className, final String methodName, final String message,
            final String loggerName) {
        log(STR_ERROR, className, methodName, message, loggerName);
    }

    /**
     * 
     * @param className
     * @param methodName
     * @param message
     * @param loggerName
     */
    public static void fatal(final String className, final String methodName, final String message,
            final String loggerName) {
        log(STR_FATAL, className, methodName, message, loggerName);
    }

    /**
     * 
     * @param level
     * @param className
     * @param methodName
     * @param message
     * @param exception
     * @param loggerName
     */
    public static void logException(final  String level, final String className, final String methodName,
            final String message, final  Throwable exception, final String loggerName) {
        logMessage(level, className, methodName, message, loggerName, exception);
    }

    /**
     * 
     * @param level
     * @param className
     * @param methodName
     * @param message
     * @param loggerName
     * @param exception
     */
    private static void logMessage(final String level, final String className, final String methodName, final String message, final String loggerName, final  Throwable exception) {
        final Logger logger = Logger.getLogger(loggerName);
        final StringBuffer messageBuffer = new StringBuffer();

        messageBuffer.append(className);
        messageBuffer.append('.');
        messageBuffer.append(methodName);
        messageBuffer.append("() - ");
        messageBuffer.append(message);
        if (exception == null) {
            logger.log(Level.toLevel(level), messageBuffer.toString());
        } else {
            logger.log(Level.toLevel(level), messageBuffer.toString(), exception);
        }
    }

    /**
     * 
     * @param className
     * @param methodName
     * @param message
     * @param exception
     * @param loggerName
     */
    public static void warnException(final String className, final String methodName,
            final String message, final  Throwable exception, final String loggerName) {
        logException(STR_WARN, className, methodName, message, exception, loggerName);
    }

    /**
     * 
     * @param className
     * @param methodName
     * @param message
     * @param exception
     * @param loggerName
     */
    public static void fatalException(final String className, final String methodName,
            final String message, final  Throwable exception, final String loggerName) {
        logException(STR_FATAL, className, methodName, message, exception, loggerName);
    }

    /**
     * 
     * @param loggerName
     * @return
     */
    public static boolean isInfoEnabled(String loggerName) {
        return Logger.getLogger(loggerName).isInfoEnabled();
    }

    /**
     * 
     * @param loggerName
     * @return
     */
    public static boolean isDebugEnabled(String loggerName) {
        return Logger.getLogger(loggerName).isInfoEnabled();
    }
    
}
