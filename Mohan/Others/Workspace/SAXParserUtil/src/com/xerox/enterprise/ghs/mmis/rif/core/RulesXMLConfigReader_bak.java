/**
 * Copyright 2014 Xerox Corporation.
 */

package com.xerox.enterprise.ghs.mmis.rif.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;



import com.xerox.enterprise.ghs.mmis.rif.core.RIFConstants;
import com.xerox.enterprise.ghs.mmis.rif.core.RuleMetadata;

/**
 * XML Parser for the rules meta data information.
 * @author 368432
 *
 */
public class RulesXMLConfigReader_bak extends DefaultHandler{
	
	
	/*Static logger to log a message */
	private static final Logger LOG = LoggerFactory.getLogger(RulesXMLConfigReader_bak.class);	
		
	private transient RuleMetadata ruleMetaData;
	
	private transient String ruleId;
	
	private final transient SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
	
	private final transient StringBuilder sbImplement = new StringBuilder();
	private final transient StringBuilder sbEndDate = new StringBuilder();
	private final transient StringBuilder sbStartDate = new StringBuilder();
	
	
	// Rules attributes from metadata xml
	private transient boolean name, type, implement, effStartDate, effEndDate, MITAMapping, redThresInms, amberThresInms;
	private  static int errorCounter=0;
	/*
	 * Initialize RulesXMLConfigReader constructor
	 * @param rifCache - EhCache object to store RuleMetaData
	 */
	public RulesXMLConfigReader_bak(){
		super();
		errorCounter=0;
	}   
    
	public int getErrorCount()
	{
		return errorCounter;
	}
    
    
    /**
     * Reading through XML nodes and create new RuleMetaData object. 
     * @throws SAXException
     */
    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes)
            throws SAXException {    	
    	//System.out.println("StartElement");
        if ("Rule".equalsIgnoreCase(qName)) {            
            ruleId = attributes.getValue("id");            
            ruleMetaData = new RuleMetadata();
            ruleMetaData.setRuleId(ruleId);
        } else if (RIFConstants.RULE_NAME_ATTR.equalsIgnoreCase(qName)) {            
            name = true;
        } else if (RIFConstants.RULE_TYPE_ATTR.equalsIgnoreCase(qName)) {
            type = true;
        } else if (RIFConstants.RULE_IMPLEMENT_ATTR.equalsIgnoreCase(qName)) {
        	sbImplement.setLength(0);
            implement = true;
        } else if (RIFConstants.RULE_EFFSTARTDATE_ATTR.equalsIgnoreCase(qName)) {
        	sbStartDate.setLength(0);
        	effStartDate = true;
        } 
        checkRemainingStartElement(qName);
    }
    
    /**
     * Add a rule Element object into Cache for each rule.
     * @throws SAXException
     */
    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
    	if(RIFConstants.RULE_IMPLEMENT_ATTR.equalsIgnoreCase(qName)) {    		
    		ruleMetaData.setImplement(sbImplement.toString().trim());    		
    		implement = false;
    	}
    	try {
	    	if (RIFConstants.RULE_EFFENDDATE_ATTR.equalsIgnoreCase(qName)) {
				
	    		ruleMetaData.setEffEndDate(formatter.parse(sbEndDate.toString().trim()));
				
				effEndDate = false;
	        }
	    	if (RIFConstants.RULE_EFFSTARTDATE_ATTR.equalsIgnoreCase(qName)) {
				ruleMetaData.setEffStartDate(formatter.parse(sbStartDate.toString().trim()));
				
				effEndDate = false;
	        }
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
    		System.out.println("Parser Error present in Rule"+ruleId);
    		errorCounter++;
			e.printStackTrace();
		} catch (Exception e)
		{
			System.out.println("Error present in Rule"+ruleId);
			e.printStackTrace();
		}
    	
    	if ("Rule".equalsIgnoreCase(qName)) {        	
            //add rule object to cache
        	System.out.println("Rule "+ruleId+" is parsed...");
        }
    }
 
 
    /**
     * Set the Rules Meta Data attribute values into Rule object.
     * @throws SAXException. 
     */
	@Override
    public void characters(final char ch[], final int start, final int length) throws SAXException {
    	try {
	        if (name) {	            
	            ruleMetaData.setRuleName(new String(ch, start, length));
	            name = false;
	        } else if (type) {
	        	int ruleType = Integer.parseInt(new String(ch, start, length));	        	
	        	ruleMetaData.setRuleType(ruleType);
	            type = false;
	        } else if (implement) {	        	
	        	sbImplement.append(new String(ch, start, length));
	        } else if (effStartDate) {	  
	        	sbStartDate.append(new String(ch, start, length));
	        	//ruleMetaData.setEffStartDate(formatter.parse(new String(ch, start, length).trim()));				
	        	//effStartDate = false;
	        } 
	        checkRemainingCharacters(ch, start, length);	        
        } catch (ParseException e) {        	
			LOG.error("XML Parser error occured while reading RuleMetaData attributes",e);
			throw new SAXException(e);
		}
    }
	
	/**
	 * Reading through XML nodes and create new RuleMetaData object. 
	 * @param qName
	 */
	private void checkRemainingStartElement(final String qName) {
		if (RIFConstants.RULE_EFFENDDATE_ATTR.equalsIgnoreCase(qName)) {
			sbEndDate.setLength(0);
			effEndDate = true;
		} else if (RIFConstants.RULE_MITAMAPPING_ATTR.equalsIgnoreCase(qName)) {
			MITAMapping = true;
		} else if (RIFConstants.RULE_REDTHRES_ATTR.equalsIgnoreCase(qName)) {
			redThresInms = true;
		} else if (RIFConstants.RULE_AMBERTHRES_ATTR.equalsIgnoreCase(qName)) {
			amberThresInms = true;
		}
	}
	
	/**
	 * Set the Rules Meta Data attribute values into Rule object.
	 * @param ch
	 * @param start
	 * @param length
	 * @throws ParseException
	 */
	private void checkRemainingCharacters(final char ch[], final int start, final int length) throws ParseException {
		if (effEndDate) {
        	sbEndDate.append(new String(ch, start, length));
			//ruleMetaData.setEffEndDate(formatter.parse(new String(ch, start, length).trim()));
			//effEndDate = false;
        } else if (MITAMapping) {
        	ruleMetaData.setMITAMapping(new String(ch, start, length));
        	MITAMapping = false;
        } else if (redThresInms) {
        	ruleMetaData.setSlaRedThreshold(Integer.parseInt(new String(ch, start, length)));
        	redThresInms = false;
        } else if (amberThresInms) {
        	ruleMetaData.setSlaAmberThreshold(Integer.parseInt(new String(ch, start, length)));
        	amberThresInms = false;
        }
	}

}
