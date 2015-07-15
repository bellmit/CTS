package com.xerox.enterprise.ghs.mmis.sax;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.xerox.enterprise.ghs.mmis.rif.core.SAXConfigHandler;
 
public class SAXParserUtil {
 
   public static void main(String argv[]) {
 
    try {
    	PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
    	System.setOut(out);
 
	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();
	SAXConfigHandler handler =new SAXConfigHandler();
	
 
       saxParser.parse("C:\\Users\\396662\\Desktop\\XML Issue\\Rules-Metadata.xml", handler);
 
     } catch (Exception e) {
       e.printStackTrace();
     }
 
   }
 
}