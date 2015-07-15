/*
 * Created on Nov 12, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.commonentities.view.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.acs.enterprise.common.program.commonentities.view.vo.AddressVO;

/**
 * @author c1002465
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddressVOConverter implements Converter {

    private static final String FIELD_DIVIDER = ";";

    /**
     * 
     */
    public AddressVOConverter() {
        super();
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
     */
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        AddressVO vo = new AddressVO();

        try
        {
            String[] fields = value.split(FIELD_DIVIDER);

            vo.setAddressline1(fields[0]);
            vo.setAddressline2(fields[1]);
            vo.setCity(fields[2]);
            vo.setState(fields[3]);
            vo.setZipCode5(fields[4]);
            vo.setZipCode4(fields[5]);
            vo.setCounty(fields[6]);
            vo.setLatitude(fields[7]);
            vo.setLongitude(fields[8]);
        }
        catch (Exception e)
        {
            throw new ConverterException(e);
        }

        return vo;
    }

    /* (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    public String getAsString(FacesContext context, UIComponent component, Object obj) {
        StringBuffer buffer = new StringBuffer();
        try
        {
            AddressVO vo = (AddressVO) obj;
    
            buffer.append(vo.getAddressline1()).append(FIELD_DIVIDER);
            buffer.append(vo.getAddressline2()).append(FIELD_DIVIDER);
            buffer.append(vo.getCity()).append(FIELD_DIVIDER);
            buffer.append(vo.getState()).append(FIELD_DIVIDER);
            buffer.append(vo.getZipCode5()).append(FIELD_DIVIDER);
            buffer.append(vo.getZipCode4()).append(FIELD_DIVIDER);
            buffer.append(vo.getCounty()).append(FIELD_DIVIDER);
            buffer.append(vo.getLatitude()).append(FIELD_DIVIDER);
            buffer.append(vo.getLongitude());
        }
        catch (Exception e)
        {
            throw new ConverterException(e);
        }

        return buffer.toString();
    }

}
