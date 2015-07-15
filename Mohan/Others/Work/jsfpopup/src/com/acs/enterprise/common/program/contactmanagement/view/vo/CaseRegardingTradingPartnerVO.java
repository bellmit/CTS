/*
 * Created on Feb 11, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.vo;

/**
 * @author ankitg
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CaseRegardingTradingPartnerVO extends CaseRegardingTPL {
	
	  /** holds the entity Type desc*/
		private String entityTypeDesc;
	
	  /** holds the trading partner id*/
		private String tradingPartnerId;
	
	  /** holds the name*/	
		private String name;
	
	   /** holds the classification*/
		private String classification;
	
		/** holds the status*/
		private String status;
		
		/** holds the contact*/
		private String contact;
		
		 /** holds the commonentitySK */
	    private String commonEntitySK;
		
		
	

	/**
	 * @return Returns the classification.
	 */
	public String getClassification() {
		return classification;
	}
	/**
	 * @param classification The classification to set.
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}
	/**
	 * @return Returns the entityTypeDesc.
	 */
	public String getEntityTypeDesc() {
		return entityTypeDesc;
	}
	/**
	 * @param entityTypeDesc The entityTypeDesc to set.
	 */
	public void setEntityTypeDesc(String entityTypeDesc) {
		this.entityTypeDesc = entityTypeDesc;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
		/**
		 * @return Returns the status.
		 */
		public String getStatus() {
			return status;
		}
		/**
		 * @param status The status to set.
		 */
		public void setStatus(String status) {
			this.status = status;
		}
	/**
	 * @return Returns the tradingPartnerId.
	 */
	public String getTradingPartnerId() {
		return tradingPartnerId;
	}
	/**
	 * @param tradingPartnerId The tradingPartnerId to set.
	 */
	public void setTradingPartnerId(String tradingPartnerId) {
		this.tradingPartnerId = tradingPartnerId;
	}
		/**
		 * @return Returns the contact.
		 */
		public String getContact() {
			return contact;
		}
		/**
		 * @param contact The contact to set.
		 */
		public void setContact(String contact) {
			this.contact = contact;
		}
		/**
		 * @return Returns the commonEntitySK.
		 */
		public String getCommonEntitySK() {
			return commonEntitySK;
		}
		/**
		 * @param commonEntitySK The commonEntitySK to set.
		 */
		public void setCommonEntitySK(String commonEntitySK) {
			this.commonEntitySK = commonEntitySK;
		}
}
