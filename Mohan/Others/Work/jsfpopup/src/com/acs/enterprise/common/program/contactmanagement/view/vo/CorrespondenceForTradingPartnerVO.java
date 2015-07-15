/**
 * 
 */
package com.acs.enterprise.common.program.contactmanagement.view.vo;

/**
 * @author sudarshanamr
 *
 */
public class CorrespondenceForTradingPartnerVO extends CorrespondenceForVO{
	
	 public CorrespondenceForTradingPartnerVO()
	    {
	        super();
	        System.out.println(" Calling Super CorrespondenceForTradingPartnerVO");
	    }

	/** holds the entity Type desc*/
	private String entityTypeDesc;

  /** holds the trading partner id*/
	private String tradingPartnerId;

  /** holds the name*/	
	private String name;

   /** holds the classification*/
	private String classification;
	
   /** holds the classificationstr*/
   private String classificationstr;

	/**
 * @return the classificationstr
 */
public String getClassificationstr() {
	return classificationstr;
}

/**
 * @param classificationstr the classificationstr to set
 */
public void setClassificationstr(String classificationstr) {
	this.classificationstr = classificationstr;
}

	/** holds the status*/
	private String status;
	
	
	/** holds the statusstr*/
	private String statusstr;
	
	/**
	 * @return the statusstr
	 */
	public String getStatusstr() {
		return statusstr;
	}

	/**
	 * @param statusstr the statusstr to set
	 */
	public void setStatusstr(String statusstr) {
		this.statusstr = statusstr;
	}

	/** holds the contact*/
	private String contact;
	
	 /** holds the commonentitySK */
    private String commonEntitySK;

	/**
	 * @return the entityTypeDesc
	 */
	public String getEntityTypeDesc() {
		return entityTypeDesc;
	}

	/**
	 * @param entityTypeDesc the entityTypeDesc to set
	 */
	public void setEntityTypeDesc(String entityTypeDesc) {
		this.entityTypeDesc = entityTypeDesc;
	}

	/**
	 * @return the tradingPartnerId
	 */
	public String getTradingPartnerId() {
		return tradingPartnerId;
	}

	/**
	 * @param tradingPartnerId the tradingPartnerId to set
	 */
	public void setTradingPartnerId(String tradingPartnerId) {
		this.tradingPartnerId = tradingPartnerId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the classification
	 */
	public String getClassification() {
		return classification;
	}

	/**
	 * @param classification the classification to set
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the commonEntitySK
	 */
	public String getCommonEntitySK() {
		return commonEntitySK;
	}

	/**
	 * @param commonEntitySK the commonEntitySK to set
	 */
	public void setCommonEntitySK(String commonEntitySK) {
		this.commonEntitySK = commonEntitySK;
	}
    

}
