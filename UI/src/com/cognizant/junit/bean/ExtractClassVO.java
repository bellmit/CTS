package com.cognizant.junit.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExtractClassVO {
	
	String className;
	String packageName;
	List<String> importList=new ArrayList<String>();
	Map<String,Map<String,String>> localVarMap = new LinkedHashMap<>();
	Map<String,String> methodSignature=new HashMap<>();
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public List<String> getImportList() {
		return importList;
	}
	public void setImportList(List<String> importList) {
		this.importList = importList;
	}
	public Map<String, Map<String, String>> getLocalVarMap() {
		return localVarMap;
	}
	public void setLocalVarMap(Map<String, Map<String, String>> localVarMap) {
		this.localVarMap = localVarMap;
	}
	public Map<String, String> getMethodSignature() {
		return methodSignature;
	}
	public void setMethodSignature(Map<String, String> methodSignature) {
		this.methodSignature = methodSignature;
	}

}
