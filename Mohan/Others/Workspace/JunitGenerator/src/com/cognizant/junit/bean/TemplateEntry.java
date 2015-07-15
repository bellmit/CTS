package com.cognizant.junit.bean;

import java.util.List;

/**
 * DataHolder class. Needs to be public since velocity is using it in the
 * template.
 *
 * @author Jon Osborn
 */
public class TemplateEntry {

    private final List<MethodComposite> methodList;
    private final List<MethodComposite> privateMethodList;
    private final List<MethodComposite> fieldList;

    private String className;
    private String packageName;

    public TemplateEntry(String className,
                         String packageName,
                         List<MethodComposite> methodList,
                         List<MethodComposite> privateMethodList,
                         List<MethodComposite> fieldList) {
        this.className = className;
        this.packageName = packageName;
        this.methodList = methodList;
        this.privateMethodList = privateMethodList;
        this.fieldList = fieldList;
    }

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

	public List<MethodComposite> getMethodList() {
		return methodList;
	}

	public List<MethodComposite> getPrivateMethodList() {
		return privateMethodList;
	}

	public List<MethodComposite> getFieldList() {
		return fieldList;
	}

   
}
