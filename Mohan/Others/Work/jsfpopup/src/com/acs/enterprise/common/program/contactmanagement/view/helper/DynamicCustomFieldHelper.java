/**
 * Copyright (c) 2006 Affiliated Computer Services, Inc.
 */

package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.Application;

// Begin - Commented unused HTML Components for the performance fix.
//import javax.faces.component.UISelectItems;
//import javax.faces.component.html.HtmlInputText;
//import javax.faces.component.html.HtmlOutputLabel;
//import javax.faces.component.html.HtmlOutputText;
//import javax.faces.component.html.HtmlSelectBooleanCheckbox;
//import javax.faces.component.html.HtmlSelectOneMenu;
// End - Commented unused HTML Components for the performance fix.

import javax.faces.model.SelectItem;

import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomField;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldDropDownValue;
import com.acs.enterprise.common.program.contactmanagement.common.domain.CustomFieldValue;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldVO;
import com.acs.enterprise.common.program.contactmanagement.view.vo.CustomFieldValueVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;
//import com.acs.enterprise.ui.javax.faces.component.calendar.ACSInputCalendar; // Commented the unused ACSInputCalendar for the performance fix
import com.acs.enterprise.ui.javax.faces.component.htmLib.components.UIBr;

/**
 * This class implements methods for creating dynamic components like TextBox,
 * Labels, CheckBoxes, etc..,.
 * 
 * @author Wipro
 */
public class DynamicCustomFieldHelper
{
    /** Creates an instance of the logger. */
    private transient EnterpriseLogger log = EnterpriseLogFactory
            .getLogger(getClass().getName());

    /**
     * Renders the Date component.
     * 
     * @param children
     *            holds the list of customFields and add it to the HtmlPanelGrid
     * @param functionalSK
     * @param id
     * @param customField
     * @param app
     * @param bindValue
     */

// Begin - Commented unused method for the performance fix.	 	 
    /*public void getDateComponent(List children, String functionalSK, String id,
            CustomField customField, Application app, String bindValue, 
			boolean isCRClosed, boolean isUpdateMode)
    {
        ACSInputCalendar cal = new ACSInputCalendar();
        cal.setId(id + customField.getCustomFieldSK());
        log.debug("For calender id is $$ " + cal.getId());
        cal.setMonthYearRowClass("yearMonthHeader");
        cal.setSize(10);
        cal.setWeekRowClass("weekHeader");
        cal.setRenderPopupButtonAsImage(true);
        cal.setPopupDateFormat("MM/dd/yyyy");
        cal.setCurrentDayCellClass("currentDayCell");
        cal.setRenderAsPopup(true);
        cal.setAddResources(true);
        log.debug("Binding Date : " + bindValue);
        cal.setValueBinding("value", app.createValueBinding(bindValue));
        if (isCRClosed)
        {
            cal.setDisabled(true);
        }
        else if(isUpdateMode)
        {
            cal
            .setDisabled(customField.getValueProtectedIndicator()
                    .booleanValue());
        }
        
        children.add(cal);
    }*/
// End - Commented unused method for the performance fix.	 

    /**
     * Represents an HTML label element, used to define an accessible label for
     * a corresponding input element.
     * 
     * @param children
     *            holds the list of customFields and add it to the HtmlPanelGrid
     * @param functionalSK
     * @param id
     * @param label
     * @param forID
     */

// Begin - Commented unused method for the performance fix.	 
    /*public void getLabelComponent(List children, String functionalSK,
            String id, String label, String forID)
    {
        HtmlOutputLabel t2 = new HtmlOutputLabel();
        t2.setId(id + functionalSK);
        log.debug("For label id is $$ " + t2.getId());
        t2.setFor(forID);
        if (label != null)
        {
            t2.setValue(label);
        }
        else
        {
            t2.setValue("");
        }
        children.add(t2);
    }*/
// End - Commented unused method for the performance fix.	 

    /**
     * Renders the component value as text (Space), optionally wrapping in a
     * span element if CSS styles or style classes are specified.
     * 
     * @param children
     *            holds the list of customFields and add it to the HtmlPanelGrid
     * @param functionalSK
     * @param id
     */

// Begin - Commented unused method for the performance fix.	 	 
   /* public void getSpace(List children, String functionalSK, String id)
    {
        HtmlOutputText outText1 = new HtmlOutputText();
//        outText1.setId(id + functionalSK);
        outText1.setValue("  ");
        children.add(outText1);
    }*/
// End - Commented unused method for the performance fix.	 

    /**
     * Renders the component value as text (Required), optionally wrapping in a
     * span element if CSS styles or style classes are specified.
     * 
     * @param children
     *            holds the list of customFields and add it to the HtmlPanelGrid
     * @param functionalSK
     * @param id
     * @param req
     */

// Begin - Commented unused method for the performance fix.	 	 
    /*public void getRequiredText(List children, String functionalSK, String id,
            String req)
    {
        HtmlOutputText outText1 = new HtmlOutputText();
        outText1.setId(id + functionalSK);
        outText1.setValue(req);
        outText1.setStyleClass("required");
        children.add(outText1);
    }*/
// End - Commented unused method for the performance fix.	 

    /**
     * Renders the BR component.
     * 
     * @param children
     *            holds the list of customFields and add it to the HtmlPanelGrid
     */
    public void getBRComponent(List children)
    {
        UIBr br = new UIBr();
        children.add(br);
    }

    /**
     * Represents an HTML input element of type text.
     * 
     * @param children
     *            holds the list of customFields and add it to the HtmlPanelGrid
     * @param functionalSK
     * @param id
     * @param customField
     * @param app
     * @param bindValue
     */

// Begin - Commented unused method for the performance fix.	 	 
    /*public void getInputComponent(List children, String functionalSK,
            String id, CustomField customField, Application app,
            String bindValue, boolean isCRClosed, boolean isUpdateMode)
    {
        HtmlInputText text1 = new HtmlInputText();
        text1.setId(id + customField.getCustomFieldSK());
        text1.setSize(10);
        log.debug("For Input id is $$ " + text1.getId());
        log.debug("Binding Input : " + bindValue);
        text1.setValueBinding("value", app.createValueBinding(bindValue));
        if (isCRClosed)
        {
            text1.setDisabled(true);
        }
        else if(isUpdateMode)
        {
	        text1.setDisabled(customField.getValueProtectedIndicator()
	                .booleanValue());
        }
       
        children.add(text1);
    }*/
// End - Commented unused method for the performance fix.	 

    /**
     * Represents a single-selection component that is rendered as an HTML
     * select element, showing a single available option at a time.
     * 
     * @param children
     *            holds the list of customFields and add it to the HtmlPanelGrid
     * @param functionalSK
     * @param id
     * @param customField
     * @param app
     * @param bindValue
     */

// Begin - Commented unused method for the performance fix.	 	 
    /*public void getDropDownComponent(List children, String functionalSK,
            String id, CustomField customField, Application app,
            String bindValue, boolean isCRClosed, boolean isUpdateMode)
    {
    	
        HtmlSelectOneMenu menu = new HtmlSelectOneMenu();
        
        CustomFieldDropDownValue dropDownValue = null;
        menu.setId(id + customField.getCustomFieldSK());
        if (isCRClosed)
        {
            menu.setDisabled(true);
        }
        else if(isUpdateMode)
        {
	        menu.setDisabled(customField.getValueProtectedIndicator()
	                .booleanValue());
        }
        log.debug("Binding Menu : " + bindValue);
        menu.setValueBinding("value", app.createValueBinding(bindValue));
      
        UISelectItems items = new UISelectItems();
        ArrayList arr = new ArrayList();
        Set values = null;
        values = customField.getCustomFieldDropDownValues();
        if (values != null && !values.isEmpty())
        {
            log.debug("CFDD is not empty");
            arr.add(new SelectItem(" ", ""));
            Iterator iterator = values.iterator();
            while (iterator.hasNext())
            {
                dropDownValue = (CustomFieldDropDownValue) iterator.next();
                if (dropDownValue != null)
                {
                    log.debug("in Select box values are $$ "
                            + dropDownValue.getCustomFieldPickLastValue());
                    arr.add(new SelectItem(dropDownValue
                            .getDisplaySortOrderNumber().toString(),
                            dropDownValue.getCustomFieldPickLastValue()));
                }
            }
        }
        else
        {
            log.debug("CFDD is empty");
            arr.add(new SelectItem("", ""));
        }
        items.setValue(arr);
        menu.getChildren().add(items);
        children.add(menu);
        
    }*/
// End - Commented unused method for the performance fix.	 

    /**
     * Represents an HTML input element of type checkbox.
     * 
     * @param children
     *            holds the list of customFields and add it to the HtmlPanelGrid
     * @param functionalSK
     * @param id
     * @param customField
     * @param app
     * @param bindValue
     */
// Begin - Commented unused method for the performance fix.	 	 
    /*public void getCheckBoxComponent(List children, String functionalSK,
            String id, CustomField customField, Application app,
            String bindValue, boolean isCRClosed, boolean isUpdateMode)
    {
        HtmlSelectBooleanCheckbox checkBox = new HtmlSelectBooleanCheckbox();
        checkBox.setId(id + customField.getCustomFieldSK());
        checkBox.setTitle("title");
       
        log.debug("Binding Chk : " + bindValue);
        checkBox.setValueBinding("value", app.createValueBinding(bindValue));
        if (isCRClosed)
        {
            checkBox.setDisabled(true);
        }
        else if(isUpdateMode)
        {
	        checkBox.setDisabled(customField.getValueProtectedIndicator()
	                .booleanValue());
        }
        children.add(checkBox);
    }*/
// End - Commented unused method for the performance fix.	 

    /**
     * @param value
     * @param beanName
     * @return String : binding value for the component. 
     */
    public String generateBindingValue(int value, String beanName)
    {
        String bindingValue = new String();
        switch (value)
        {
            case 0:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementZero}";
                break;
            }
            case 1:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementOne}";
                break;
            }
            case 2:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementTwo}";
                break;
            }
            case 3:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementThree}";
                break;
            }
            case 4:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementFour}";
                break;
            }
            case 5:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementFive}";
                break;
            }
            case 6:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementSix}";
                break;
            }
            case 7:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementSeven}";
                break;
            }
            case 8:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementEight}";
                break;
            }
            case 9:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementNine}";
                break;
            }
            case 10:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementTen}";
                break;
            }
            case 11:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementEleven}";
                break;
            }
            case 12:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementTwelve}";
                break;
            }
            case 13:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementThirteen}";
                break;
            }
            case 14:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementFourteen}";
                break;
            }
            case 15:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementFifteen}";
                break;
            }
            case 16:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementSixteen}";
                break;
            }
            case 17:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementSeventeen}";
                break;
            }
            case 18:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementEighteen}";
                break;
            }
            case 19:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementNinteen}";
                break;
            }
            case 20:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementTwenty}";
                break;
            }
            default:
                break;
        }
        return bindingValue;
    }
    
    public String generateBindingValueForChkBox(int value, String beanName)
    {
        String bindingValue = new String();
        switch (value)
        {
            case 0:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementZeroForChkbox}";
                break;
            }
            case 1:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementOneForChkbox}";
                break;
            }
            case 2:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementTwoForChkbox}";
                break;
            }
            case 3:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementThreeForChkbox}";
                break;
            }
            case 4:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementFourForChkbox}";
                break;
            }
            case 5:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementFiveForChkbox}";
                break;
            }
            case 6:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementSixForChkbox}";
                break;
            }
            case 7:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementSevenForChkbox}";
                break;
            }
            case 8:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementEightForChkbox}";
                break;
            }
            case 9:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementNineForChkbox}";
                break;
            }
            case 10:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementTenForChkbox}";
                break;
            }
            case 11:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementElevenForChkbox}";
                break;
            }
            case 12:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementTwelveForChkbox}";
                break;
            }
            case 13:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementThirteenForChkbox}";
                break;
            }
            case 14:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementFourteenForChkbox}";
                break;
            }
            case 15:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementFifteenForChkbox}";
                break;
            }
            case 16:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementSixteenForChkbox}";
                break;
            }
            case 17:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementSeventeenForChkbox}";
                break;
            }
            case 18:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementEighteenForChkbox}";
                break;
            }
            case 19:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementNinteenForChkbox}";
                break;
            }
            case 20:
            {
                bindingValue = "#{" + beanName
                        + ".customFieldValueVO.elementTwentyForChkbox}";
                break;
            }
            default:
                break;
        }
        return bindingValue;
    }
    
    public String getCustomFieldValue(int index,
            CustomFieldValueVO customFieldValueVO)
    {
    	if(log.isDebugEnabled())
    	{
    		log.debug("getCustomFieldValue is started for $$ " + index
    				+ " Custom field.");
    	}
        String value = null;
        if (index == 0)
        {
            if (customFieldValueVO.getElementZero() != null)
            {
                value = customFieldValueVO.getElementZero().toString();
            }
        }
        else if (index == 1)
        {
            if (customFieldValueVO.getElementOne() != null)
            {
                value = customFieldValueVO.getElementOne().toString();
            }
        }
        else if (index == 2)
        {
        	
            if (customFieldValueVO.getElementTwo() != null)
            {
                value = customFieldValueVO.getElementTwo().toString();
            }
        }
        else if (index == 3)
        {
            if (customFieldValueVO.getElementThree() != null)
            {
                value = customFieldValueVO.getElementThree().toString();
            }
        }
        else if (index == 4)
        {
            if (customFieldValueVO.getElementFour() != null)
            {
                value = customFieldValueVO.getElementFour().toString();
            }
        }
        else if (index == 5)
        {
            if (customFieldValueVO.getElementFive() != null)
            {
                value = customFieldValueVO.getElementFive().toString();
            }
        }
        else if (index == 6)
        {
            if (customFieldValueVO.getElementSix() != null)
            {
                value = customFieldValueVO.getElementSix().toString();
            }
        }
        else if (index == 7)
        {
            if (customFieldValueVO.getElementSeven() != null)
            {
                value = customFieldValueVO.getElementSeven().toString();
            }
        }
        else if (index == 8)
        {
            if (customFieldValueVO.getElementEight() != null)
            {
                value = customFieldValueVO.getElementEight().toString();
            }
        }
        else if (index == 9)
        {
            if (customFieldValueVO.getElementNine() != null)
            {
                value = customFieldValueVO.getElementNine().toString();
            }
        }
        else if (index == 10)
        {
            if (customFieldValueVO.getElementTen() != null)
            {
                value = customFieldValueVO.getElementTen().toString();
            }
        }
        else if (index == 11)
        {
            if (customFieldValueVO.getElementEleven() != null)
            {
                value = customFieldValueVO.getElementEleven().toString();
            }
        }
        else if (index == 12)
        {
            if (customFieldValueVO.getElementTwelve() != null)
            {
                value = customFieldValueVO.getElementTwelve().toString();
            }
        }
        else if (index == 13)
        {
            if (customFieldValueVO.getElementThirteen() != null)
            {
                value = customFieldValueVO.getElementThirteen().toString();
            }
        }
        else if (index == 14)
        {
            if (customFieldValueVO.getElementFourteen() != null)
            {
                value = customFieldValueVO.getElementFourteen().toString();
            }
        }
        else if (index == 15)
        {
            if (customFieldValueVO.getElementFifteen() != null)
            {
                value = customFieldValueVO.getElementFifteen().toString();
            }
        }
        else if (index == 16)
        {
            if (customFieldValueVO.getElementSixteen() != null)
            {
                value = customFieldValueVO.getElementSixteen().toString();
            }
        }
        else if (index == 17)
        {
            if (customFieldValueVO.getElementSeventeen() != null)
            {
                value = customFieldValueVO.getElementSeventeen().toString();
            }
        }
        else if (index == 18)
        {
            if (customFieldValueVO.getElementEighteen() != null)
            {
                value = customFieldValueVO.getElementEighteen().toString();
            }
        }
        else if (index == 19)
        {
            if (customFieldValueVO.getElementNinteen() != null)
            {
                value = customFieldValueVO.getElementNinteen().toString();
            }
        }
        else if (index == 20)
        {
            if (customFieldValueVO.getElementTwenty() != null)
            {
                value = customFieldValueVO.getElementTwenty().toString();
            }
        }
        if(log.isDebugEnabled())
        {
        	log.debug("The final value for custom field " + index+ " in getCustomFieldValue() is $$ " + value);
        }
        return value;
    }
    
  // Begin - Added the method for the PanelGrid Fix
    /**
     * This method is used to display the HTMLComponents on the UI based on the datatype.
     * 
     * @param list
     * @param isCRClosed
     * @param valueMap
     * @param isUpdateMode
     * @return cfList
     */
    public List showDynamicFields(List list, boolean isCRClosed, Map valueMap, boolean isUpdateMode)
    {
    	CustomField field = null;
    	List cfList = new ArrayList();
    	if(((list != null && !list.isEmpty())))
    	{
    		int cfListSize = 0;
    		cfListSize = list.size();
    		for(int i=0;i<cfListSize;i++)
    		{
    			field = (CustomField) list.get(i);
    			 if (field.getDataTypeCode().equals("DD"))
    			 {
    				 CustomFieldVO customFieldVO = new CustomFieldVO();
    				 customFieldVO.setRequiredFlag(String.valueOf(field.getRequiredValueIndicator()));
    				 customFieldVO.setLabelName(field.getDescription());
    				 customFieldVO.setElementID("customField"+i);
    				 Set set = new HashSet(field.getCustomFieldDropDownValues());
    				 List dropDownList = new ArrayList();
    				 Iterator itr = set.iterator();
    				 int k = 0;
    				 while(itr.hasNext())
    				 {
    					 CustomFieldDropDownValue customFieldDropDownValue = (CustomFieldDropDownValue) itr.next();
    					 dropDownList.add(new SelectItem(customFieldDropDownValue.getCustomFieldPickLastValue(), 
    							 customFieldDropDownValue.getCustomFieldPickLastValue()));
    					 k++;
    				 }
    				 
    				 customFieldVO.setCustomFieldDDList(dropDownList);
    				 customFieldVO.setDataType(field.getDataTypeCode());
    				 if(valueMap != null)
    				 {
	    				 CustomFieldValue customFieldValue = (CustomFieldValue) valueMap.get(field.getCustomFieldSK());
	    				 if (customFieldValue != null) {
	    					 customFieldVO.setComponentInputData(customFieldValue.getCustomFieldValue());
	    	     				if(isCRClosed)
	    	   			        {
	    	      					 customFieldVO.setProtectedFlag("false");
	    	   			        }
	    	   			        else if(isUpdateMode)
	    	   			        {
	    	   			        	customFieldVO.setProtectedFlag(String.valueOf(field.getValueProtectedIndicator()
	    	   			                    .booleanValue()));
	    	   			        }
	    				 }
    				 }
    				 cfList.add(customFieldVO);
    			 }
    			 else if (field.getDataTypeCode().equals("CB"))
    			 {
    				 CustomFieldVO customFieldVO = new CustomFieldVO();
    				 customFieldVO.setRequiredFlag(String.valueOf(field.getRequiredValueIndicator()));
    				 customFieldVO.setLabelName(field.getDescription());
    				 customFieldVO.setDataType(field.getDataTypeCode());
    				 customFieldVO.setElementID("customField"+i);
    				 if(valueMap != null)
    				 {
	    				 CustomFieldValue customFieldValue = (CustomFieldValue) valueMap.get(field.getCustomFieldSK());
	    				 if (customFieldValue != null) {
	    					 customFieldVO.setCheckBoxValue(Boolean.valueOf(customFieldValue.getCustomFieldValue()));
	        				 if(isCRClosed)
	      			        {
	         					 customFieldVO.setProtectedFlag("false");
	      			        }
	      			        else if(isUpdateMode)
	      			        {
	      			        	customFieldVO.setProtectedFlag(String.valueOf(field.getValueProtectedIndicator()
	      			                    .booleanValue()));
	      			        }
	    				 }
    				 }
    				 cfList.add(customFieldVO);
    			 }
    			 else if (field.getDataTypeCode().equals("D"))
    			 {
    				 CustomFieldVO customFieldVO = new CustomFieldVO();
    				 customFieldVO.setRequiredFlag(String.valueOf(field.getRequiredValueIndicator()));
    				 customFieldVO.setLabelName(field.getDescription());
    				 customFieldVO.setDataType(field.getDataTypeCode());
    				 customFieldVO.setElementID("customField"+i);
    				 if(valueMap != null)
    				 {
	    				 CustomFieldValue customFieldValue = (CustomFieldValue) valueMap.get(field.getCustomFieldSK());
	    				 if (customFieldValue != null) {
	    					 customFieldVO.setComponentInputData(customFieldValue.getCustomFieldValue());
	        				 if(isCRClosed)
	      			        {
	         					 customFieldVO.setProtectedFlag("false");
	      			        }
	      			        else if(isUpdateMode)
	      			        {
	      			        	customFieldVO.setProtectedFlag(String.valueOf(field.getValueProtectedIndicator()
	      			                    .booleanValue()));
	      			        }
	    				 }
    				 }
					cfList.add(customFieldVO);
    			 }
    			 else if (field.getDataTypeCode().equals("N")
                         || field.getDataTypeCode().equals("T")
                         || field.getDataTypeCode().equals("DA"))
                 {
    				 CustomFieldVO customFieldVO = new CustomFieldVO();
    				 customFieldVO.setRequiredFlag(String.valueOf(field.getRequiredValueIndicator()));
    				 customFieldVO.setLabelName(field.getDescription());
    				 customFieldVO.setDataType(field.getDataTypeCode());
    				 customFieldVO.setElementID("customField"+i);
    				 if(field.getTotalLengthInBytes() != null)
    				 {
    					 customFieldVO.setLength(field.getTotalLengthInBytes().toString());
    				 }
    				 else
    				 {
    					 customFieldVO.setLength("10");
    				 }
    				 if(valueMap != null)
    				 {
	    				 CustomFieldValue customFieldValue = (CustomFieldValue) valueMap.get(field.getCustomFieldSK());
	    				 if (customFieldValue != null) {
	    					 customFieldVO.setComponentInputData(customFieldValue.getCustomFieldValue());
	        				 if(isCRClosed)
	      			        {
	         					 customFieldVO.setProtectedFlag("false");
	      			        }
	      			        else if(isUpdateMode)
	      			        {
	      			        	customFieldVO.setProtectedFlag(String.valueOf(field.getValueProtectedIndicator()
	      			                    .booleanValue()));
	      			        }
	    				 }
    				 }
 					 cfList.add(customFieldVO);
                 }
    		}
    		return cfList;
    	}
	    return null;
    }
    // End - Added the method for the PanelGrid Fix
}
