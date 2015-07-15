package com.xerox.ghs.mmis.provider.model;

import java.util.Date;

public class DentalClaimVO {
	
	private String lastName;
	
	private String medicalProviderID;
	
	private String strServiceDate;
	
	private Date serviceDate;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMedicalProviderID() {
		return medicalProviderID;
	}

	public void setMedicalProviderID(String medicalProviderID) {
		this.medicalProviderID = medicalProviderID;
	}

	public String getStrServiceDate() {
		return strServiceDate;
	}

	@SuppressWarnings("deprecation")
	public void setStrServiceDate(String strServiceDate) {
		
		this.strServiceDate = strServiceDate;
		
		if (strServiceDate != "")
        {
           this.serviceDate = new Date(strServiceDate);
          
       }
		
	}

	public Date getServiceDate() {
		return serviceDate;
	}


}
