package com.xerox.enterprise.ghs.mmis.sax;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.xerox.enterprise.ghs.mmis.rif.core.RulesXMLConfigReader;


public class SAXParserUtility {

	private static SAXParserFactory saxParserFactory;
	
	private static SAXParser saxParser;
	
	private static RulesXMLConfigReader configReader;
	
	/*Static logger to log a message */
	private static final Logger LOG = LoggerFactory.getLogger(SAXParserUtility.class);	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintStream out=null;
		try {
			out = new PrintStream(new FileOutputStream("parser.txt"));
			System.setOut(out);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
		saxParserFactory = SAXParserFactory.newInstance();
		try {
			saxParser = saxParserFactory.newSAXParser();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		configReader = new RulesXMLConfigReader();		
		loadRulesConfigfromXML();
		out.close();
	}

	private static void loadRulesConfigfromXML() {
		
        try {
        	System.out.println("Xml Path:"+Thread.currentThread().getContextClassLoader().
        			getResource("Rules-Metadata.xml").getFile());
        	InputStream is=Thread.currentThread().getContextClassLoader().
        			getResource("Rules-Metadata.xml").openStream();       	
        	saxParser.parse(is, configReader);
		} catch (SAXException e) {			
			LOG.error("SAX configuration exception occured while parsing Rules Metadata file", e);
			
		} catch (IOException ioe) {			
			LOG.error("IO exception occured while parsing Rules Metadata file", ioe);
			
		}
        System.out.println("No Rules has parser error:"+configReader.getErrorCount());
	}
}
