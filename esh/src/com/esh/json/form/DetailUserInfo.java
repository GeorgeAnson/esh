package com.esh.json.form;

import java.io.Serializable;

public class DetailUserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int duAge=0;//�û�����
	private int duGender=0;//�û��Ա�
	private String duMediHist=null;//�û���ʷ
	private String duAddress=null;//�û���ס��ַ
	private String duEmerCall=null;//�û�������ϵ����ϵ��ʽ
	private String duProfession=null;//�û�ְҵ
	private String duAllergy=null;//�û���������
	private double duHeight=0;//�û����
	private double duWeight=0;//�û�����
	
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
