package com.esh.entity;

import java.io.Serializable;

public class Disease implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int did=0;//症状id
	private int dfid=0;//症状父类id
	private String dname=null;//症状名称
	private String description=null;//症状表述
	private String dhighRiskGroup=null;//症状高发人群
	private String dhighRiskGender=null;//症状高发性别
	private int dstatus=0;//症状记录状态	
	private AcupointPicture picture=null;//对应学位动图
	
	public Disease() {
		
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public int getDfid() {
		return dfid;
	}

	public void setDfid(int dfid) {
		this.dfid = dfid;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
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

	public int getDstatus() {
		return dstatus;
	}

	public void setDstatus(int dstatus) {
		this.dstatus = dstatus;
	}

	public AcupointPicture getPicture() {
		return picture;
	}

	public void setPicture(AcupointPicture picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Disease [did=" + did + ", dfid=" + dfid + ", dname=" + dname + ", description=" + description
				+ ", dhighRiskGroup=" + dhighRiskGroup + ", dhighRiskGender=" + dhighRiskGender + ", dstatus=" + dstatus
				+ "]";
	}
}
