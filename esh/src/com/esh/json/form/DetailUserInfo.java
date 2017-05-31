package com.esh.json.form;

import java.io.Serializable;

public class DetailUserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int duAge=0;//用户年龄
	private int duGender=0;//用户性别
	private String duMediHist=null;//用户病史
	private String duAddress=null;//用户居住地址
	private String duEmerCall=null;//用户紧急联系人联系方式
	private String duProfession=null;//用户职业
	private String duAllergy=null;//用户过敏物质
	private double duHeight=0;//用户身高
	private double duWeight=0;//用户体重
	
	public DetailUserInfo() {
		
	}

	public int getDuAge() {
		return duAge;
	}

	public void setDuAge(int duAge) {
		this.duAge = duAge;
	}

	public int getDuGender() {
		return duGender;
	}

	public void setDuGender(int duGender) {
		this.duGender = duGender;
	}

	public String getDuMediHist() {
		return duMediHist;
	}

	public void setDuMediHist(String duMediHist) {
		this.duMediHist = duMediHist;
	}

	public String getDuAddress() {
		return duAddress;
	}

	public void setDuAddress(String duAddress) {
		this.duAddress = duAddress;
	}

	public String getDuEmerCall() {
		return duEmerCall;
	}

	public void setDuEmerCall(String duEmerCall) {
		this.duEmerCall = duEmerCall;
	}

	public String getDuProfession() {
		return duProfession;
	}

	public void setDuProfession(String duProfession) {
		this.duProfession = duProfession;
	}

	public String getDuAllergy() {
		return duAllergy;
	}

	public void setDuAllergy(String duAllergy) {
		this.duAllergy = duAllergy;
	}

	public double getDuHeight() {
		return duHeight;
	}

	public void setDuHeight(double duHeight) {
		this.duHeight = duHeight;
	}

	public double getDuWeight() {
		return duWeight;
	}

	public void setDuWeight(double duWeight) {
		this.duWeight = duWeight;
	}

	@Override
	public String toString() {
		return "DetailUserInfo [duAge=" + duAge + ", duGender=" + duGender + ", duMediHist=" + duMediHist
				+ ", duAddress=" + duAddress + ", duEmerCall=" + duEmerCall + ", duProfession=" + duProfession
				+ ", duAllergy=" + duAllergy + ", duHeight=" + duHeight + ", duWeight=" + duWeight + "]";
	}
}
