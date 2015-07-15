package com.acs.enterprise.common.program.contactmanagement.view.bean;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;

import org.apache.myfaces.custom.tabbedpane.TabChangeEvent;
import org.apache.myfaces.custom.tabbedpane.TabChangeListener;

import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

public class MaintainCaseTypeTabListner implements TabChangeListener{

	
	private CaseTypeDataBean caseTypeDataBean ;

	private static final EnterpriseLogger logger = EnterpriseLogFactory
    .getLogger(MaintainCaseTypeTabListner.class);
	
	
	public MaintainCaseTypeTabListner() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Object getDataBean(String dataBeanName)

    {
        FacesContext fc = FacesContext.getCurrentInstance();
        String valueBindingStr = "#{" + dataBeanName + "}";
        Object dataBeanObj = null;
        dataBeanObj = fc.getApplication().getVariableResolver()
                .resolveVariable(fc, dataBeanName);
        if (dataBeanObj == null)

        {

            dataBeanObj = fc.getApplication().createValueBinding(
                    valueBindingStr).getValue(fc);

        }
        return dataBeanObj;

    }
	
	
	@Override
	public void processTabChange(TabChangeEvent event)
			throws AbortProcessingException {
		
		caseTypeDataBean = (CaseTypeDataBean) getDataBean(ContactManagementConstants.CASE_TYPE_DATA_BEAN);
		if(logger.isInfoEnabled()){
		logger.info("===inside the method processTabChange :::::");
		}
		caseTypeDataBean.setMaintainCaseTypeFocusId("maintainCaseTypeSort");//Added for Page Focus Fix
		if(event.getNewTabIndex() == 1){
			caseTypeDataBean.setCaseTypeCaseStepsFlag(false);
			caseTypeDataBean.setCaseTypeCaseEventsFlag(true);
			caseTypeDataBean.setCaseTypeCustomFieldsFlag(false);
			caseTypeDataBean.setCaseTypeTemplatesFlag(false);
			
		}else if(event.getNewTabIndex() == 2){
			caseTypeDataBean.setCaseTypeCaseStepsFlag(false);
			caseTypeDataBean.setCaseTypeCaseEventsFlag(false);
			caseTypeDataBean.setCaseTypeCustomFieldsFlag(true);
			caseTypeDataBean.setCaseTypeTemplatesFlag(false);
			
		}else if(event.getNewTabIndex() == 3){
			caseTypeDataBean.setCaseTypeCaseStepsFlag(false);
			caseTypeDataBean.setCaseTypeCaseEventsFlag(false);
			caseTypeDataBean.setCaseTypeCustomFieldsFlag(false);
			caseTypeDataBean.setCaseTypeTemplatesFlag(true);
			
		}else{
			caseTypeDataBean.setCaseTypeCaseStepsFlag(true);
			caseTypeDataBean.setCaseTypeCaseEventsFlag(false);
			caseTypeDataBean.setCaseTypeCustomFieldsFlag(false);
			caseTypeDataBean.setCaseTypeTemplatesFlag(false);
	}

}
	
}
