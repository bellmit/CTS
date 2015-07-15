package com.cognizant.rif.vo;

public class RuleReportVO {
	
	private String pojoPath;
	private String invokeCodePath;
	private String patternPath;
	private String junitPath;
	private boolean isRuleBatchMode;
	public String getPojoPath() {
		return pojoPath;
	}
	public void setPojoPath(String pojoPath) {
		this.pojoPath = pojoPath;
	}
	public String getInvokeCodePath() {
		return invokeCodePath;
	}
	public void setInvokeCodePath(String invokeCodePath) {
		this.invokeCodePath = invokeCodePath;
	}
	public String getPatternPath() {
		return patternPath;
	}
	public void setPatternPath(String patternPath) {
		this.patternPath = patternPath;
	}
	public String getJunitPath() {
		return junitPath;
	}
	public void setJunitPath(String junitPath) {
		this.junitPath = junitPath;
	}
	public boolean isRuleBatchMode() {
		return isRuleBatchMode;
	}
	public void setRuleBatchMode(boolean isRuleBatchMode) {
		this.isRuleBatchMode = isRuleBatchMode;
	}
}
