/*
 * Created on Dec 18, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
/* import java.util.HashMap; */
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* import javax.faces.component.UISelectItems; */
import javax.faces.model.SelectItem;

import com.acs.enterprise.common.base.view.bean.EnterpriseBaseDataBean;
import com.acs.enterprise.common.logging.common.domain.AuditLog;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.EnterpriseUser;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.vo.LobHierarchyVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author Wipro TODO To change the template for this generated type comment go
 *         to Window - Preferences - Java - Code Style - Code Templates
 */
public class LobHierarchyDataBean
        extends EnterpriseBaseDataBean
{

    /**
     * EnterpriseLogger Name for Logging.
     */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(LobHierarchyControlBean.class);

    /**
     * Holds the falg
     */
    private boolean flag = false;

    /**
     * Holds the showSucessMessage
     */
    private Boolean showSucessMessage = Boolean.FALSE;
    
    private boolean auditLogRendered = false;

    private boolean auditHistoryRender = false;

    private List auditHistoryList = new ArrayList();

    private boolean auditColumnHistoryRender = false;

    private AuditLog selectedAuditLog;
    
    /** holds the total number of records per page. */
    private int itemsPerPage = 10;
    
    /**
     * This is used to show parent audit log for details page.
     */
    private boolean auditParentHistoryRender = false;

    /**
     * This is used to hold parent audit field history list for details page.
     */
    private ArrayList auditParentHistoryList = new ArrayList();
    
    /**
     * This is used to show column changes for parent page.
     */
    private boolean auditParentColumnHistoryRender = false;
    
    
    /* Variable to reneder delete message.
     */
    private boolean showDeletedMessage = false;

    /**
     * Declated to hold the selected parent AuditLog.
     */
    private AuditLog selectedParentAuditLog;

    /**
     * This is used to hold the state of the Audit block open or closed.
     */
    private boolean auditOpen = false;
    
    private boolean auditLogFlag = false;
    private boolean auditLogFlagBU = false;
    private boolean auditLogFlagDept = false;

    /**
	 * @return Returns the itemsPerPage.
	 */
	public int getItemsPerPage() {
		return itemsPerPage;
	}
	/**
	 * @param itemsPerPage The itemsPerPage to set.
	 */
	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}


    /**
	 * @return Returns the selectedAuditLog.
	 */
	public AuditLog getSelectedAuditLog() {
		return selectedAuditLog;
	}
	/**
	 * @param selectedAuditLog The selectedAuditLog to set.
	 */
	public void setSelectedAuditLog(AuditLog selectedAuditLog) {
		this.selectedAuditLog = selectedAuditLog;
	}
    /**
	 * @return Returns the auditHistoryList.
	 */
	public List getAuditHistoryList() {
		return auditHistoryList;
	}
	/**
	 * @param auditHistoryList The auditHistoryList to set.
	 */
	public void setAuditHistoryList(List auditHistoryList) {
		this.auditHistoryList = auditHistoryList;
	}
	/**
	 * @return Returns the auditColumnHistoryRender.
	 */
	public boolean isAuditColumnHistoryRender() {
		return auditColumnHistoryRender;
	}
	/**
	 * @param auditColumnHistoryRender The auditColumnHistoryRender to set.
	 */
	public void setAuditColumnHistoryRender(boolean auditColumnHistoryRender) {
		this.auditColumnHistoryRender = auditColumnHistoryRender;
	}
    /**
     * Holds the sliderStatus
     */
    private String sliderStatus = "open";
    
	/**
	 * @return Returns the auditHistoryRender.
	 */
	public boolean isAuditHistoryRender() {
		return auditHistoryRender;
	}
	/**
	 * @param auditHistoryRender The auditHistoryRender to set.
	 */
	public void setAuditHistoryRender(boolean auditHistoryRender) {
		this.auditHistoryRender = auditHistoryRender;
	}
	/**
	 * @return Returns the auditLogRendered.
	 */
	public boolean isAuditLogRendered() {
		return auditLogRendered;
	}
	/**
	 * @param auditLogRendered The auditLogRendered to set.
	 */
	public void setAuditLogRendered(boolean auditLogRendered) {
		this.auditLogRendered = auditLogRendered;
	}
    /**
     * @return Returns the showSucessMessage.
     */
    public Boolean getShowSucessMessage()
    {
        return showSucessMessage;
    }

    /**
     * @param showSucessMessage
     *            The showSucessMessage to set.
     */
    public void setShowSucessMessage(Boolean showSucessMessage)
    {
        this.showSucessMessage = showSucessMessage;
    }

    /**
     * Holds the supervisorsList
     */
    private List supervisorsList = new ArrayList();

    /**
     * Constructor of LobHierarchyDataBean
     */
    public LobHierarchyDataBean()
    {
        super();
        logger.debug("Inside Constructor of LobHierarchyDataBean");
        this.supervisorsList = loadSupervisorData();
    }

    /**
     * @return Returns the userAndSK.
     */
    public Map getUserAndSK()
    {
        return userAndSK;
    }

    /**
     * @param userAndSK
     *            The userAndSK to set.
     */
    public void setUserAndSK(Map userAndSK)
    {
        this.userAndSK = userAndSK;
    }

    /**
     * Holds the userAndSK
     */
    private Map userAndSK;

    /**
     * This method will Fetch all Supervisor Users.
     * 
     * @return List : List of the supervisors
     */
    private List loadSupervisorData()
    {
        logger.info("===loadSupervisorData=====");
    	List tempList = new ArrayList();
        ContactMaintenanceDelegate contactMaintenanceDelegate = new ContactMaintenanceDelegate();

        try
        {
            List superUsersList = contactMaintenanceDelegate
                    .getAllSupervisors();
            
          
            if (superUsersList != null)
            {
                int size = superUsersList.size();
              //  ArrayList alUser=new ArrayList(size);  //FInd bug issue              
              //  Map mapUser=new HashMap(); //FInd bug issue
                for (int i = 0; i < size; ++i)
                {
                    EnterpriseUser enterpriseUser = (EnterpriseUser) superUsersList.get(i);
                    boolean temp = enterpriseUser.getAccountActiveIndicator().booleanValue();
                    logger.info("Temp Value :----> " + temp);
                    if(temp)
                    {
                    		tempList.add(new SelectItem(enterpriseUser.getUserWorkUnitSK(),
                    		enterpriseUser.getLastName()+ContactManagementConstants.COMMA_SEPARATOR
							+ContactManagementConstants.SPACE_STRING
							+ enterpriseUser.getFirstName() + ContactManagementConstants.HYPHEN_SEPARATOR
                            + enterpriseUser.getUserID()));
                    }
                }
            }
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
            logger.error(e.getMessage(), e);
        }
        return tempList;
    }

    /**
     * @return Returns the flag.
     */
    public boolean isFlag()
    {
        return flag;
    }

    /**
     * @param flag
     *            The flag to set.
     */
    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }

    /**
     * @return Returns the lobHieracVO.
     */
    public LobHierarchyVO getLobHieracVO()
    {
        return lobHieracVO;
    }

    /**
     * @param lobHieracVO
     *            The lobHieracVO to set.
     */
    public void setLobHieracVO(LobHierarchyVO lobHieracVO)
    {
        this.lobHieracVO = lobHieracVO;
    }

    /**
     * Holds the lobHieracVO
     */
    private LobHierarchyVO lobHieracVO;

    /**
     * Holds the lobHieracVO
     */
    private String lobDesc;

    /**
     * @return Returns the lobDesc.
     */
    public String getLobDesc()
    {
        return lobDesc;
    }

    /**
     * @param lobDesc
     *            The lobDesc to set.
     */
    public void setLobDesc(String lobDesc)
    {
        this.lobDesc = lobDesc;
    }

    /**
     * which holds Supervisor Name
     */
    private String superviosrName;

    /**
     * @return Returns the superviosrName.
     */
    public String getSuperviosrName()
    {
        return superviosrName;

    }

    /**
     * @param superviosrName
     *            The superviosrName to set.
     */
    public void setSuperviosrName(String superviosrName)
    {
        this.superviosrName = superviosrName;
    }

    /**
     * which holds idName
     */
    private String idName;

    /**
     * @return Returns the idName.
     */
    public String getIdName()
    {
        return idName;
    }

    /**
     * @param idName
     *            The idName to set.
     */
    public void setIdName(String idName)
    {
        this.idName = idName;
    }

    /**
     * @return Returns the supervisorsList.
     */
    public List getSupervisorsList()
    {
        return supervisorsList;
    }

    /**
     * @param supervisorsList
     *            The supervisorsList to set.
     */
    public void setSupervisorsList(List supervisorsList)
    {
        this.supervisorsList = supervisorsList;
    }
    /**
     * @return Returns the sliderStatus.
     */
    public String getSliderStatus()
    {
        return sliderStatus;
    }
    /**
     * @param sliderStatus The sliderStatus to set.
     */
    public void setSliderStatus(String sliderStatus)
    {
        this.sliderStatus = sliderStatus;
    }
	/**
	 * @return Returns the auditParentHistoryRender.
	 */
	public boolean isAuditParentHistoryRender() {
		return auditParentHistoryRender;
	}
	/**
	 * @param auditParentHistoryRender The auditParentHistoryRender to set.
	 */
	public void setAuditParentHistoryRender(boolean auditParentHistoryRender) {
		this.auditParentHistoryRender = auditParentHistoryRender;
	}
	/**
	 * @return Returns the auditParentHistoryList.
	 */
	public ArrayList getAuditParentHistoryList() {
		return auditParentHistoryList;
	}
	/**
	 * @param auditParentHistoryList The auditParentHistoryList to set.
	 */
	public void setAuditParentHistoryList(ArrayList auditParentHistoryList) {
		this.auditParentHistoryList = auditParentHistoryList;
	}
	/**
	 * @return Returns the auditParentColumnHistoryRender.
	 */
	public boolean isAuditParentColumnHistoryRender() {
		return auditParentColumnHistoryRender;
	}
	/**
	 * @param auditParentColumnHistoryRender The auditParentColumnHistoryRender to set.
	 */
	public void setAuditParentColumnHistoryRender(
			boolean auditParentColumnHistoryRender) {
		this.auditParentColumnHistoryRender = auditParentColumnHistoryRender;
	}
	/**
	 * @return Returns the selectedParentAuditLog.
	 */
	public AuditLog getSelectedParentAuditLog() {
		return selectedParentAuditLog;
	}
	/**
	 * @param selectedParentAuditLog The selectedParentAuditLog to set.
	 */
	public void setSelectedParentAuditLog(AuditLog selectedParentAuditLog) {
		this.selectedParentAuditLog = selectedParentAuditLog;
	}
	/**
	 * @return Returns the auditOpen.
	 */
	public boolean isAuditOpen() {
		return auditOpen;
	}
	/**
	 * @param auditOpen The auditOpen to set.
	 */
	public void setAuditOpen(boolean auditOpen) {
		this.auditOpen = auditOpen;
	}
    /**
     * @return Returns the showDeletedMessage.
     */
    public boolean isShowDeletedMessage() {
        return showDeletedMessage;
    }
    /**
     * @param showDeletedMessage The showDeletedMessage to set.
     */
    public void setShowDeletedMessage(boolean showDeletedMessage) {
        this.showDeletedMessage = showDeletedMessage;
    }
    
    private boolean showAuditLogPage = true;
	/**
	 * @return Returns the showAuditLogPage.
	 */
	public boolean isShowAuditLogPage() {
		return showAuditLogPage;
	}
	/**
	 * @param showAuditLogPage The showAuditLogPage to set.
	 */
	public void setShowAuditLogPage(boolean showAuditLogPage) {
		this.showAuditLogPage = showAuditLogPage;
	}
	/**
	 * @return Returns the auditLogFlag.
	 */
	public boolean isAuditLogFlag() {
		return auditLogFlag;
	}
	/**
	 * @param auditLogFlag The auditLogFlag to set.
	 */
	public void setAuditLogFlag(boolean auditLogFlag) {
		this.auditLogFlag = auditLogFlag;
	}
	/**
	 * @return Returns the auditLogFlagBU.
	 */
	public boolean isAuditLogFlagBU() {
		return auditLogFlagBU;
	}
	/**
	 * @param auditLogFlagBU The auditLogFlagBU to set.
	 */
	public void setAuditLogFlagBU(boolean auditLogFlagBU) {
		this.auditLogFlagBU = auditLogFlagBU;
	}
	/**
	 * @return Returns the auditLogFlagDept.
	 */
	public boolean isAuditLogFlagDept() {
		return auditLogFlagDept;
	}
	/**
	 * @param auditLogFlagDept The auditLogFlagDept to set.
	 */
	public void setAuditLogFlagDept(boolean auditLogFlagDept) {
		this.auditLogFlagDept = auditLogFlagDept;
	}
}
