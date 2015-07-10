package com.cognizant.rif.utilities;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.cognizant.rif.dto.MetaDataInfo;


public class MetaData {
	
	private static final String RIF_METADATA_XML="D:\\RIF\\Rules-Metadata.xml";
	static final String RULE_CONFIG="RulesConfig";
	static final String RULE="Rule";
	static final String NAME="name";
	static final String ID="Id";
	static final String TYPE="type";
	static final String IMPLEMENT="implement";
	static final String START_DATE="effectivestartdate";
	static final String END_DATE="effectiveenddate";
	static final String MITA_MAPPPING="MITAMapping";
	static final String SLA="sla";
	static final String RED_THRESH_HOLD="RedThresholdInMilliSeconds";
	static final String AMBER_THRESH_HOLD="AmberThresholdInMilliSeconds";
		
	

		public static void updateMetaDataInfo(MetaDataInfo info) throws Exception{
			String filepath = RIF_METADATA_XML;
			String ruleID=info.getRuleID();
			String ruleName=info.getRuleName();
			int type=info.getType();
			String implementationClass=info.getImplementationClass();
			String startDate=info.getEndDate();
			String endDate=info.getEndDate();
			String mitaMapping=info.getMitaMapping();
		    int redThresholdInMilliSeconds=info.getRedThresholdInMilliSeconds();
		    int amberThresholdInMilliSeconds=info.getAmberThresholdInMilliSeconds();
		    
		    if(ruleID==null || ruleID.trim().length()==0){
		    	throw new Exception("Invalid Input to create Rules MetaData Info. ruldID is null");
		    }else if(ruleName==null || ruleName.trim().length()==0){
		    	throw new Exception("Invalid Input to create Rules MetaData Info. ruleName is null");
		    }/*else if(type==null || type.trim().length()==0){
		    	throw new Exception("Invalid Input to create Rules MetaData Info. type is null");
		    }*/else if(implementationClass==null || implementationClass.trim().length()==0){
		    	throw new Exception("Invalid Input to create Rules MetaData Info. implementationClass is null");
		    }else if(startDate==null || startDate.trim().length()==0){
		    	throw new Exception("Invalid Input to create Rules MetaData Info. startDate is null");
		    }else if(endDate==null || endDate.trim().length()==0){
		    	throw new Exception("Invalid Input to create Rules MetaData Info. endDate is null");
		    }else if(mitaMapping==null || mitaMapping.trim().length()==0){
		    	throw new Exception("Invalid Input to create Rules MetaData Info. mitaMapping is null");
		    }/*else if(redThresholdInMilliSeconds==null || redThresholdInMilliSeconds.trim().length()==0){
		    	throw new Exception("Invalid Input to create Rules MetaData Info. redThresholdInMilliSeconds is null");
		    }else if(amberThresholdInMilliSeconds==null || amberThresholdInMilliSeconds.trim().length()==0){
		    	throw new Exception("Invalid Input to create Rules MetaData Info. amberThresholdInMilliSeconds is null");
		    }*/
		    
		   try {
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			Node rulesConfig = doc.getElementsByTagName(RULE_CONFIG).item(0);
			System.out.println(rulesConfig.getNodeName());		
			Node rule = doc.createElement(RULE);;
			Element element=(Element)rule;
			element.setAttribute(ID, ruleID);	        						
			Element name = doc.createElement(NAME);
			name.setTextContent(ruleName);
			Element elementType = doc.createElement(TYPE);
			elementType.setTextContent(Integer.toString(type));
			Element implement = doc.createElement(IMPLEMENT);
			implement.setTextContent(implementationClass);
			Element effectivestartdate = doc.createElement(START_DATE);
			effectivestartdate.setTextContent(startDate);
			Element effectiveenddate = doc.createElement(END_DATE);
			effectiveenddate.setTextContent(endDate);
			Element mITAMapping = doc.createElement(MITA_MAPPPING);
			mITAMapping.setTextContent(mitaMapping);
			Element sla = doc.createElement(SLA);

			Element redThreshold=doc.createElement(RED_THRESH_HOLD);
			redThreshold.setTextContent(Integer.toString(redThresholdInMilliSeconds));
			Element amberThreshold=doc.createElement(AMBER_THRESH_HOLD);
			amberThreshold.setTextContent(Integer.toString(amberThresholdInMilliSeconds));
			sla.appendChild(redThreshold);
			sla.appendChild(amberThreshold);
			rule.appendChild(name);
			rule.appendChild(elementType);
			rule.appendChild(implement);
			rule.appendChild(effectivestartdate);
			rule.appendChild(effectiveenddate);
			rule.appendChild(mITAMapping);
			
			rule.appendChild(sla);

			rulesConfig.appendChild(rule);			

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(source, result);
	 
			System.out.println("Metadata XML entry Done");
	 
		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }
		}
	}	

