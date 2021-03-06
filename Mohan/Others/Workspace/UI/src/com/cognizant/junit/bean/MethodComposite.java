package com.cognizant.junit.bean;


import java.util.List;

/**
 * A holder for the dissection of the methods
 *
 * @author Jon Osborn
 * @since 1/3/12 4:37 PM
 */
public class MethodComposite {
    private String name;
    private String signature;
    private List<String> paramClasses;
    private List<String> paramNames;
    private List<String> reflectionCode;
    private List<String> rifContextObj;
    private String ruleInvokeCode;
    
    
    public String getRuleInvokeCode() {
		return ruleInvokeCode;
	}

	public void setRuleInvokeCode(String ruleInvokeCode) {
		this.ruleInvokeCode = ruleInvokeCode;
	}

	public List<String> getRifContextObj() {
		return rifContextObj;
	}

	public void setRifContextObj(List<String> rifContextObj) {
		this.rifContextObj = rifContextObj;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public List<String> getParamClasses() {
        return paramClasses;
    }

    public void setParamClasses(List<String> paramClasses) {
        this.paramClasses = paramClasses;
    }

    public List<String> getParamNames() {
        return paramNames;
    }

    public void setParamNames(List<String> paramNames) {
        this.paramNames = paramNames;
    }

    public List<String> getReflectionCode() {
        return reflectionCode;
    }

    public void setReflectionCode(List<String> reflectionCode) {
        this.reflectionCode = reflectionCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("MethodComposite");
        //sb.append("{method=").append(method);
        sb.append("{ name='").append(name).append('\'');
        sb.append(", signature='").append(signature).append('\'');
        sb.append(", paramClasses=").append(paramClasses);
        sb.append(", paramNames=").append(paramNames);
        sb.append(", reflectionCode=").append(reflectionCode);
        sb.append('}');
        return sb.toString();
    }
}
