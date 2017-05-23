package com.esh.entity;

import java.io.Serializable;

public class DiseaseInformation implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int did=0;
	private String dname=null;
	private int dfid=0;
	private String description=null;
	private String dhighRiskGroup=null;
	private String dhighRiskGender=null;
	
	public DiseaseInformation() {
		// TODO Auto-generated constructor stub
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public int getDfid() {
		return dfid;
	}

	public void setDfid(int dfid) {
		this.dfid = dfid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDhighRiskGroup() {
		return dhighRiskGroup;
	}

	public void setDhighRiskGroup(String dhighRiskGroup) {
		this.dhighRiskGroup = dhighRiskGroup;
	}

	public String getDhighRiskGender() {
		return dhighRiskGender;
	}

	public void setDhighRiskGender(String dhighRiskGender) {
		this.dhighRiskGender = dhighRiskGender;
	}

	@Override
	public String toString() {
		return "DiseaseInformation [did=" + did + ", dname=" + dname + ", dfid=" + dfid + ", description=" + description
				+ ", dhighRiskGroup=" + dhighRiskGroup + ", dhighRiskGender=" + dhighRiskGender + "]";
	}
		
}
