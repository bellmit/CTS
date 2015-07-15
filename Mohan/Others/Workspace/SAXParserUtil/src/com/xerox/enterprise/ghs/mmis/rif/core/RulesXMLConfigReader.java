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
public class RulesXMLConfigReader extends DefaultHandler{
	
	
	/*Static logger to log a message */
	private static final Logger LOG = LoggerFactory.getLogger(RulesXMLConfigReader.class);	
	
	//private final transient Ehcache rifCache;
	
	private transient RuleMetadata ruleMetaData;
	
	private transient String ruleId;
	
	private final transient SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
	
	private final transient StringBuilder sbBuilder = new StringBuilder();
	
	// Rules attributes from metadata xml
	private transient boolean name, type, implement, effStartDate, effEndDate, MITAMapping, redThresInms, amberThresInms;
	private  static int errorCounter=0;
	/*
	 * Initialize RulesXMLConfigReader constructor
	 * @param rifCache - EhCache object to store RuleMetaData
	 */
	public RulesXMLConfigReader(){
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
    	sbBuilder.setLength(0);
        if ("Rule".equalsIgnoreCase(qName)) {            
            ruleId = attributes.getValue("id");            
            ruleMetaData = new RuleMetadata();
            ruleMetaData.setRuleId(ruleId);           
        } else if (RIFConstants.RULE_NAME_ATTR.equalsIgnoreCase(qName)) {            
            name = true;
        } else if (RIFConstants.RULE_TYPE_ATTR.equalsIgnoreCase(qName)) {
            type = true;
        } else if (RIFConstants.RULE_IMPLEMENT_ATTR.equalsIgnoreCase(qName)) {
        	
            implement = true;
        } else if (RIFConstants.RULE_EFFSTARTDATE_ATTR.equalsIgnoreCase(qName)) {
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
    	/*if(RIFConstants.RULE_IMPLEMENT_ATTR.equalsIgnoreCase(qName)) {    		
    		ruleMetaData.setImplement(sbImplement.toString());    		
    		implement = false;
    	}*/
    	if (RIFConstants.RULE_NAME_ATTR.equalsIgnoreCase(qName)) { 
    		
    		ruleMetaData.setRuleName(sbBuilder.toString().trim());
    		System.out.println("End Element:Name:"+ruleMetaData.getRuleName());
            name = false;
        } else if (RIFConstants.RULE_TYPE_ATTR.equalsIgnoreCase(qName)) {
        	int ruleType = Integer.parseInt(sbBuilder.toString().trim());	        	
        	ruleMetaData.setRuleType(ruleType);
        	System.out.println("End Element:ruleType:"+ruleMetaData.getRuleType());
            type = false;
        } else if (RIFConstants.RULE_IMPLEMENT_ATTR.equalsIgnoreCase(qName)) {
        	ruleMetaData.setImplement(sbBuilder.toString().trim());  
        	System.out.println("End Element:Implement:"+ruleMetaData.getImplement());
    		implement = false;
        } else if (RIFConstants.RULE_EFFSTARTDATE_ATTR.equalsIgnoreCase(qName)) {
        	try {
				ruleMetaData.setEffStartDate(formatter.parse(sbBuilder.toString().trim()));
				System.out.println("End Element:EffStartDate:"+ruleMetaData.getEffStartDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				LOG.error("Start Date Formater while reading RuleMetaData attributes",e);
				e.printStackTrace();
				errorCounter++;
			}
        	effStartDate=false;
        } 
    	checkRemainingEndElement(qName);    	
    	if ("Rule".equalsIgnoreCase(qName)) { 
    		System.out.println("Rule Id"+ruleMetaData.getRuleId());
            //add rule object to cache
        	//rifCache.put(new Element(ruleId, ruleMetaData));
        }
    }



	private void checkRemainingEndElement(final String qName) {
		if (RIFConstants.RULE_EFFENDDATE_ATTR.equalsIgnoreCase(qName)) {
        	try {
				ruleMetaData.setEffEndDate(formatter.parse(sbBuilder.toString().trim()));
				System.out.println("End Element:EffEndDate:"+ruleMetaData.getEffEndDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				LOG.error("End Date Formater while reading RuleMetaData attributes",e);
				e.printStackTrace();
				errorCounter++;
			}
        	effEndDate=false;
		} else if (RIFConstants.RULE_MITAMAPPING_ATTR.equalsIgnoreCase(qName)) {
			ruleMetaData.setMITAMapping(sbBuilder.toString().trim());
			System.out.println("End Element:MITAMapping:"+ruleMetaData.getMITAMapping());
        	MITAMapping = false;
		} else if (RIFConstants.RULE_REDTHRES_ATTR.equalsIgnoreCase(qName)) {
			ruleMetaData.setSlaRedThreshold(Integer.parseInt(sbBuilder.toString().trim()));
			System.out.println("End Element:SlaRedThreshold:"+ruleMetaData.getSlaRedThreshold());
        	redThresInms = false;
		} else if (RIFConstants.RULE_AMBERTHRES_ATTR.equalsIgnoreCase(qName)) {
			ruleMetaData.setSlaAmberThreshold(Integer.parseInt(sbBuilder.toString().trim()));
			System.out.println("End Element:SlaAmberThreshold:"+ruleMetaData.getSlaAmberThreshold());
        	amberThresInms = false;
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
	        	sbBuilder.append(new String(ch, start, length));
	            System.out.println("Char:Name"+new String(ch, start, length));
	        } else if (type) {
	        	sbBuilder.append(new String(ch, start, length));
	        	System.out.println("Char:type"+new String(ch, start, length));
	        } else if (implement) {	   
	        	sbBuilder.append(new String(ch, start, length));
	        	System.out.println("Char:implement"+new String(ch, start, length));
	        } else if (effStartDate) {	 
	        	sbBuilder.append(new String(ch, start, length));
	        	System.out.println("Char:effStartDate"+new String(ch, start, length));				
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
			sbBuilder.append(new String(ch, start, length));
			System.out.println("Char:effEndDate"+new String(ch, start, length));
			//effEndDate = false;
        } else if (MITAMapping) {
        	sbBuilder.append(new String(ch, start, length));
        	System.out.println("Char:MITAMapping"+new String(ch, start, length));
        } else if (redThresInms) {
        	sbBuilder.append(new String(ch, start, length));
        	System.out.println("Char:redThresInms"+new String(ch, start, length));
        } else if (amberThresInms) {
        	sbBuilder.append(new String(ch, start, length));
        	System.out.println("Char:amberThresInms"+new String(ch, start, length));
        }
	}

}
