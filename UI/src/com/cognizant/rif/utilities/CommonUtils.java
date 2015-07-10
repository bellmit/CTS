package com.cognizant.rif.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommonUtils {
	
	public static <T> void addValueToTargetMap(String keyName, T value,
			Map<String, List<T>> targetMap) {
		
		if(targetMap.containsKey(keyName)){
			List<T> methodsList = targetMap.get(keyName);
			if(!methodsList.contains(value)){
				methodsList.add(value);
				targetMap.put(keyName, methodsList);
			}
			
		}
		else
		{
			List<T> methodsList = new ArrayList<T>();
			methodsList.add(value);
			targetMap.put(keyName, methodsList);
		}
	}

}
