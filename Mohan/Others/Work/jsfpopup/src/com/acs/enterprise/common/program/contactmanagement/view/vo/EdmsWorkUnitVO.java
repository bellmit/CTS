/*
 * Created on Oct 3, 2007
 *
 */
package com.acs.enterprise.common.program.contactmanagement.view.vo;

import java.util.List;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.internalmessage.common.vo.EnterpriseUserVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 Holds the information about EDMSDefaultsVO.
 */
public class EdmsWorkUnitVO extends EnterpriseBaseVO 
{
	/** Enterprise Logger for Logging *//*
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(EdmsWorkUnitVO.class);*/
    
	/**
     * constructor for EDMSDefaultsDOConverter.
     */
    public EdmsWorkUnitVO()
    {
    	super();
    	EnterpriseLogger logger = EnterpriseLogFactory
				.getLogger(EdmsWorkUnitVO.class);
		logger.debug("EdmsWorkUnitVO constructor");
    }
    
	 /**
     * Holds collection of message value objects
     */
    private List messageList;

    /**
     * Holds collection of message receiver value objects
     */
    private List messageReceiversList;

    /**
     * Holds Work Unit Key, a primary key
     */
    private Long workUnitSK;

    /**
     * Holds Work Unit Type Code
     */
    private String workUnitTypeCode;
    
    /** Holds collection of messages objects */
    private EnterpriseUserVO enterpriseUserVO;

    /**
     * Holds username
     */
    private String userName;

	/**
	 * @return Returns the enterpriseUserVO.
	 */
	public EnterpriseUserVO getEnterpriseUserVO() 
	{
		return enterpriseUserVO;
	}
	
	/**
	 * @param enterpriseUserVO The enterpriseUserVO to set.
	 */
	public void setEnterpriseUserVO(EnterpriseUserVO enterpriseUserVO) 
	{
		this.enterpriseUserVO = enterpriseUserVO;
	}

    /**
     * This method is used to retrieve WorkUnitsk Key Info
     * 
     * @return the workUnitSK : WorkUnit Id
     */
    public Long getWorkUnitSK()
    {
        return workUnitSK;
    }

    /**
     * This method is used to set the WorkUnitSK Key Info
     * 
     * @param workUnitSK :
     *            WorkUnit Id
     */
    public void setWorkUnitSK(Long workUnitSK)
    {
        this.workUnitSK = workUnitSK;
    }

    /**
     * This method is used to retrieve WorkUnitTypeCode Info
     * 
     * @return workUnitTypeCode : workUnitTypeCode
     */
    public String getWorkUnitTypeCode()
    {
        return workUnitTypeCode;
    }

    /**
     * This method is used to set the WorkUnitTypeCode Info
     * 
     * @param workUnitTypeCode :
     *            Work Unit Id
     */
    public void setWorkUnitTypeCode(String workUnitTypeCode)
    {
        this.workUnitTypeCode = workUnitTypeCode;
    }
    
	/**
	 * @return Returns the messageList.
	 */
	public List getMessageList() 
	{
		return messageList;
	}
	
	/**
	 * @param messageList The messageList to set.
	 */
	public void setMessageList(List messageList) 
	{
		this.messageList = messageList;
	}
	
	/**
	 * @return Returns the messageReceiversList.
	 */
	public List getMessageReceiversList() 
	{
		return messageReceiversList;
	}
	
	/**
	 * @param messageReceiversList The messageReceiversList to set.
	 */
	public void setMessageReceiversList(List messageReceiversList)
	{
		this.messageReceiversList = messageReceiversList;
	}
	
    /**
     * @return Returns the userName.
     */
    public String getUserName()
    {
        return userName;
    }
    
    /**
     * @param userName The userName to set.
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
}

