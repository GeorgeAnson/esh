package com.esh.entity;

import java.io.Serializable;

public class Acupoint implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int apId=0;//ѧλid
	private String apName=null;//ѧλ����
	private String desctibe=null;//ѧλ����
	private int apStrength=0;//����ǿ��ֵ
	private int apMode=0;//����ģʽ
	private int apFrequency=0;//����Ƶ��ֵ
	private int apTime=0;//����������ʱ��
	private int apulse=0;//��������ֵ
	private int apIsFirst=0;//�Ƿ�Ϊ��һ������
	private AcupointPicture picture=null;//ѨλͼƬ
	
	public Acupoint() {
		
	}

	public int getApId() {
		return apId;
	}

	public void setApId(int apId) {
		this.apId = apId;
	}

	public String getApName() {
		return apName;
	}

	public void setApName(String apName) {
		this.apName = apName;
	}

	public String getDesctibe() {
		return desctibe;
	}

	public void setDesctibe(String desctibe) {
		this.desctibe = desctibe;
	}

	public int getApStrength() {
		return apStrength;
	}

	public void setApStrength(int apStrength) {
		this.apStrength = apStrength;
	}

	public int getApMode() {
		return apMode;
	}

	public void setApMode(int apMode) {
		this.apMode = apMode;
	}

	public int getApFrequency() {
		return apFrequency;
	}

	public void setApFrequency(int apFrequency) {
		this.apFrequency = apFrequency;
	}

	public int getApTime() {
		return apTime;
	}

	public void setApTime(int apTime) {
		this.apTime = apTime;
	}

	public int getApulse() {
		return apulse;
	}

	public void setApulse(int apulse) {
		this.apulse = apulse;
	}

	public int getApIsFirst() {
		return apIsFirst;
	}

	public void setApIsFirst(int apIsFirst) {
		this.apIsFirst = apIsFirst;
	}

	public AcupointPicture getPicture() {
		return picture;
	}

	public void setPicture(AcupointPicture picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Acupoint [apId=" + apId + ", apName=" + apName + ", desctibe=" + desctibe + ", apStrength=" + apStrength
				+ ", apMode=" + apMode + ", apFrequency=" + apFrequency + ", apTime=" + apTime + ", apulse=" + apulse
				+ ", apIsFirst=" + apIsFirst + "]";
	}
}
