package com.cognizant.rif.utilities;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cognizant.rif.dto.MetaDataInfo;
import com.cognizant.rif.dto.RuleDescriptionInfo;



public class RulesExtXMLConfigReader extends DefaultHandler {
	
	
	private String ruleid;
	private boolean ruleidExist=false;
	private RuleDescriptionInfo ruleInfo;
	private MetaDataInfo metaDataInfo=null;
	private List<String> intMethodList;
	private List<String> exttMethodList;
	// Rules attributes from metadata xml
			boolean name = false;
		    boolean type = false;
		    boolean implement = false;
		    boolean effStartDate = false;
		    boolean effEndDate = false;
		    boolean MITAMapping = false;
		    boolean redThresInms = false;
		    boolean amberThresInms = false;
	private boolean internalMethodExist;
	private boolean externalMethodExist;	    

	public RulesExtXMLConfigReader(RuleDescriptionInfo ruleDescInfo,
			String ruleID) {
		ruleid=ruleID;
		ruleInfo=ruleDescInfo;
	}
	
	
	/* public void startDocument() throws SAXException {
	        System.out.println("start document   : ");
	    }

	    public void endDocument() throws SAXException {
	        System.out.println("end document     : ");
	    }

	    public void startElement(String uri, String localName,
	        String qName, Attributes attributes)
	    throws SAXException {

	        System.out.println("start element    : " + qName);
	    }

	    public void endElement(String uri, String localName, String qName)
	    throws SAXException {
	        System.out.println("end element      : " + qName);
	    }

	    public void characters(char ch[], int start, int length)
	    throws SAXException {
	        System.out.println("start characters : " +
	            new String(ch, start, length));
	    }*/
	
	    
	    /**
	     * 
	     */
	    @Override
	    public void startElement(String uri, String localName, String qName, Attributes attributes)
	            throws SAXException {
	    	if (qName.equalsIgnoreCase("Rule")) {
	            //create a new rule
	            String ruleId = attributes.getValue("id");
	            if(ruleid.equalsIgnoreCase(ruleId))
	            {
	            	metaDataInfo = new MetaDataInfo();
	            	ruleInfo.setRuleID(ruleId);
	            	metaDataInfo.setRuleID(ruleId);
	            	ruleidExist=true;
	            	System.out.println("<<<Start Element for Rule ID ==>"+ruleId+" >>>");
	            }
	        } else if (qName.equalsIgnoreCase("name")) {
	            //set boolean values for fields, will be used in setting Employee variables
	            name = ruleidExist && true;
	        } else if (qName.equalsIgnoreCase("type")) {
	            type = ruleidExist && true;
	        } else if (qName.equalsIgnoreCase("implement")) {
	            implement = ruleidExist && true;
	        } else if (qName.equalsIgnoreCase("effectivestartdate")) {
	        	effStartDate = ruleidExist && true;
	        } else if (qName.equalsIgnoreCase("effectiveenddate")) {
	        	effEndDate = ruleidExist && true;
	        } else if (qName.equalsIgnoreCase("MITAMapping")) {
	        	MITAMapping = ruleidExist && true;
	        } else if (qName.equalsIgnoreCase("RedThresholdInMilliSeconds")) {
	        	redThresInms = ruleidExist && true;
	        } else if (qName.equalsIgnoreCase("AmberThresholdInMilliSeconds")) {
	        	amberThresInms = ruleidExist && true;
	        }else if (qName.equalsIgnoreCase("internalMethod")) {
	        	internalMethodExist = ruleidExist && true;
	        }else if (qName.equalsIgnoreCase("externalMethod")) {
	        	externalMethodExist = ruleidExist && true;
	        }
	        
	        
	    }
	    
	    /**
	     * 
	     */
	    @Override
	    public void endElement(String uri, String localName, String qName) throws SAXException {
	        if (qName.equalsIgnoreCase("Rule") && ruleidExist) {
	        	ruleInfo.setMetaDataInfo(metaDataInfo);
	        	if(intMethodList!=null)
	        	{
	        		ruleInfo.setInternalMethodtoExtract(intMethodList);
	        		System.out.println("intMethodList size==>"+intMethodList.size());
	        	}
	        	if(exttMethodList!=null)
	        	{
	        		ruleInfo.setExternalMethodtoExtract(exttMethodList);
	        		System.out.println("exttMethodList size==>"+exttMethodList.size());
	        	}
	        	
	        	System.out.println("<<<End Element>>>");
	        	ruleidExist=false;
	        }
	    }
	 
	 
	    /**
	     * 
	     */
		@Override
	    public void characters(char ch[], int start, int length) throws SAXException {
			if(ruleidExist)
			{
		    	if (name) {
				    metaDataInfo.setRuleName(new String(ch, start, length));
				    name = false;
				} else if (type) {
					metaDataInfo.setType(Integer.parseInt(new String(ch, start, length)));
				    type = false;
				} else if (implement) {
					metaDataInfo.setImplementationClass(new String(ch, start, length));
				    implement = false;
				} else if (effStartDate) {
					metaDataInfo.setStartDate(new String(ch, start, length));				
					effStartDate = false;
				} else if (effEndDate) {
					metaDataInfo.setEndDate(new String(ch, start, length));
					effEndDate = false;
				} else if (MITAMapping) {
					metaDataInfo.setMitaMapping(new String(ch, start, length));
					MITAMapping = false;
				} else if (redThresInms) {
					metaDataInfo.setRedThresholdInMilliSeconds(Integer.parseInt(new String(ch, start, length)));
					redThresInms = false;
				} else if (amberThresInms) {
					metaDataInfo.setAmberThresholdInMilliSeconds(Integer.parseInt(new String(ch, start, length)));
					amberThresInms = false;
				}
				else if (internalMethodExist) {
					if(intMethodList==null)
						intMethodList=new ArrayList<String>();
					intMethodList.add(new String(ch, start, length));
					internalMethodExist = false;
				}
				else if (externalMethodExist) {
					if(exttMethodList==null)
						exttMethodList=new ArrayList<String>();
					exttMethodList.add(new String(ch, start, length));
					externalMethodExist = false;
				}
		    	
			}
	      
	    }

}
