package com.xerox.ghs.mmis.enterprise.common.security.common.model;

/**
 * 
 * @author 399320
 * 
 */
public class User {

	/** Holds serialVersionUID */
	private static final long serialVersionUID = 244205441877955460L;

	/**
	 * Holds user ID info
	 */
	private String userId;

	/**
	 * Holds first name info
	 */
	private String firstName;

	/**
	 * Holds last name info
	 */
	private String lastName;

	private String userPassword;

	/**
	 * Holds users hierarchyLOB info
	 */
	private String hierarchyLOB;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword
	 *            the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the hierarchyLOB
	 */
	public String getHierarchyLOB() {
		return hierarchyLOB;
	}

	/**
	 * @param hierarchyLOB
	 *            the hierarchyLOB to set
	 */
	public void setHierarchyLOB(String hierarchyLOB) {
		this.hierarchyLOB = hierarchyLOB;
	}

}
