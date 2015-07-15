package com.cognizant.tool.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.cognizant.tool.reader.MessageReader;
import com.cognizant.tool.writer.ExcelWriter;

public class TestMessageReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Main Method...");
		File fileName=new File("C:\\Users\\396662\\Desktop\\MT_Rules\\Message Master.xlsx");
		
		MessageReader msgRdr=new MessageReader(fileName);
		try {
			Set<String> erroridList=msgRdr.readMessageSheet();
			System.out.println("Error id count==>"+erroridList.size());
			fileName=new File("C:\\Users\\396662\\Desktop\\RuleMap.xlsx");
			msgRdr.setFname(fileName);
			Map<String,String> rulesMap=msgRdr.readRuleSheet();
			//System.out.println("RuleMap =>"+rulesMap);
			System.out.println("RuleMap size"+rulesMap.size());
			relateErrorToRule(rulesMap,erroridList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void relateErrorToRule(Map<String,String> rulesMap,Set<String> erroridList) throws IOException
	{
		Set<String> actionList=new HashSet<String>();
		actionList.addAll(rulesMap.values());
		Map<String,List<String>> errorToRule=new HashMap<>();
		for(String errorId:erroridList)
		{
			for(String action:actionList)
			{
				if(action.contains(errorId))
				{
					String ruleID=getKeyByValue(rulesMap, action);
					if(errorToRule.containsKey(errorId))
					{
						List<String> ruleList=errorToRule.get(errorId);
						ruleList.add(ruleID);
					}
					else
					{
						List<String> ruleidList=new ArrayList<>();
						ruleidList.add(ruleID);
						errorToRule.put(errorId, ruleidList);
					}
				}
			}
		}
		System.out.println("Error to Rule Map==>"+errorToRule);
		System.out.println("Total error message mapped to Rule ID==>"+errorToRule.size());
		
		int ruleidCount=0;
		for(Entry<String,List<String>> entry:errorToRule.entrySet())
		{
			ruleidCount=ruleidCount+entry.getValue().size();
		}
		System.out.println("Total no of Rule ID==>"+ruleidCount);
		//write to XLS file
		File fname=new File("C:\\Users\\396662\\Desktop\\ErrorToRuleMap.xlsx");
		ExcelWriter writer=new ExcelWriter();
		writer.setFile(fname);
		writer.rulewriter(errorToRule);
		
	}
	
	public <E> void printList(List<E> list)
	{
		for(E e:list)
		{
			String str=(String)e;
			System.out.println(str.toString());
		}
		
	}
	
	public static <R , A> R getKeyByValue(Map<R , A> map,A value)
	{
		for(Entry<R,A> entry:map.entrySet())
		{
			if(entry.getValue().equals(value))
			{
				return entry.getKey();
			}
		}
		return null;
	}
}
