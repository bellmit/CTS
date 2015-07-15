/*
 * Created on Oct 3, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.vo;

import com.acs.enterprise.common.base.common.vo.EnterpriseBaseVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;


/**
 * CorrespondenceAttachmentXrefVO.
 * @author banota
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CorrespondenceAttachmentXrefVO extends EnterpriseBaseVO
{
    /** For storing the attachment indicator */
    private Boolean attachIndicator;
    
    /** For storing the attachment Sks */
    private Long attachmentSk;
    
    /**
     * Constructor for CorrespondenceAttachmentXrefVO.
     */
    public CorrespondenceAttachmentXrefVO()
    {
        super();
        EnterpriseLogger logger = EnterpriseLogFactory
                .getLogger(CorrespondenceAttachmentXrefVO.class);
        logger.debug("CorrespondenceAttachmentXrefVO constructor");
    }
   
    /**
     * @param attachIndicator The attachIndicator to set.
     */
    public void setAttachIndicator(Boolean attachIndicator)
    {
        this.attachIndicator = attachIndicator;
    }
    
    /**
     * @return Returns the attachmentSk.
     */
    public Long getAttachmentSk()
    {
        return attachmentSk;
    }
    
    /**
     * @param attachmentSk The attachmentSk to set.
     */
    public void setAttachmentSk(Long attachmentSk)
    {
        this.attachmentSk = attachmentSk;
    }
    /**
     * @return Returns the attachIndicator.
     */
    public Boolean getAttachIndicator()
    {
        return attachIndicator;
    }
}
