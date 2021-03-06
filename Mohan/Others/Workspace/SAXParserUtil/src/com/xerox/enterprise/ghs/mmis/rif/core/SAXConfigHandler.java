package com.xerox.enterprise.ghs.mmis.rif.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXConfigHandler extends DefaultHandler {
	
	String ruleId = null;
	boolean name = false;
	boolean type = false;
	boolean implement = false;
	boolean effStartDate = false;
	boolean effEndDate = false;
	boolean MITAMapping = false;
	boolean redThresInms = false;
	boolean amberThresInms = false;
	StringBuilder startdate=new StringBuilder();
	StringBuilder enddate=new StringBuilder();
	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
	public void startElement(String uri, String localName,String qName, 
            Attributes attributes) throws SAXException {

	
		
	//System.out.println("Start Element :" + qName);
	if ("Rule".equalsIgnoreCase(qName)) {            
        ruleId = attributes.getValue("id");            
       
    } else if (RIFConstants.RULE_NAME_ATTR.equalsIgnoreCase(qName)) {            
        name = true;
    } else if (RIFConstants.RULE_TYPE_ATTR.equalsIgnoreCase(qName)) {
        type = true;
    } else if (RIFConstants.RULE_IMPLEMENT_ATTR.equalsIgnoreCase(qName)) {
        implement = true;
    } else if (RIFConstants.RULE_EFFSTARTDATE_ATTR.equalsIgnoreCase(qName)) {
    	startdate=new StringBuilder();
    	effStartDate = true;
    } else if (RIFConstants.RULE_EFFENDDATE_ATTR.equalsIgnoreCase(qName)) {
		enddate=new StringBuilder();
		effEndDate = true;
	} else if (RIFConstants.RULE_MITAMAPPING_ATTR.equalsIgnoreCase(qName)) {
		MITAMapping = true;
	} else if (RIFConstants.RULE_REDTHRES_ATTR.equalsIgnoreCase(qName)) {
		redThresInms = true;
	} else if (RIFConstants.RULE_AMBERTHRES_ATTR.equalsIgnoreCase(qName)) {
		amberThresInms = true;
	}

	
}

public void endElement(String uri, String localName,
	String qName) throws SAXException {

	//System.out.println("End Element :" + qName);
	
	if(effStartDate)
	{
		System.out.println("effStartDate : " + startdate);
		try {
			System.out.println("Parsed Date "+sdf.parse(startdate.toString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Start Date:"+e.toString());
			e.printStackTrace();
		}
	}
	if(effEndDate)
	{
		System.out.println("effEndDate : " + enddate);
		try {
			System.out.println("Parsed Date "+sdf.parse(enddate.toString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("End Date:"+e.toString());
			e.printStackTrace();
		}
	}
	name = false;
	 type = false;
	 implement = false;
	 effStartDate = false;
	 effEndDate = false;
	 MITAMapping = false;
	 redThresInms = false;
	 amberThresInms = false;

}

public void characters(char ch[], int start, int length) throws SAXException {

	if(name)
	{
		System.out.println("name : " + new String(ch, start, length));
	}
	if(type)
	{
		System.out.println("type : " + new String(ch, start, length));
	}
	if(implement)
	{
		System.out.println("implement : " + new String(ch, start, length));
	}
	if(effStartDate)
	{
		System.out.println("effStartDate : " + new String(ch, start, length));
		startdate.append(new String(ch, start, length));
	}
	if(effEndDate)
	{
		System.out.println("effEndDate : " + new String(ch, start, length));
		enddate.append(new String(ch, start, length));
		try {
			System.out.println("Parsed Date "+sdf.parse(new String(ch, start, length)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("End Date:"+e.toString());
			e.printStackTrace();
		}
	}
	if(MITAMapping)
	{
		System.out.println("MITAMapping : " + new String(ch, start, length));
	}
	if(redThresInms)
	{
		System.out.println("redThresInms : " + new String(ch, start, length));
	}
	if(amberThresInms)
	{
		System.out.println("amberThresInms : " + new String(ch, start, length));
		//amberThresInms=false;
	}
	
	
	
}

}
