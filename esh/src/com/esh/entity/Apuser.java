package com.esh.entity;

import java.io.Serializable;
import java.util.Date;

public class Apuser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ustrength=0;//�û�����ǿ��
	private int umode=0;//�û�����ģʽ
	private int ufrequency=0;//�û�����Ƶ��ֵ
	private int utime=0;//�û�����ʱ��
	private int upulse=0;//�û���������ֵ
	private int uperiod=0;//�û������Ƶ��Ƴ�
	private Date updateTime=null;//����ʱ��
	private User user=null;//�û�
	private Acupoint acupoint=null;//Ѩλ
	private Disease disease=null;//��֢
	
	public Apuser() {
		
	}

	public int getUstrength() {
		return ustrength;
	}

	public void setUstrength(int ustrength) {
		this.ustrength = ustrength;
	}

	public int getUmode() {
		return umode;
	}

	public void setUmode(int umode) {
		this.umode = umode;
	}

	public int getUfrequency() {
		return ufrequency;
	}

	public void setUfrequency(int ufrequency) {
		this.ufrequency = ufrequency;
	}

	public int getUtime() {
		return utime;
	}

	public void setUtime(int utime) {
		this.utime = utime;
	}

	public int getUpulse() {
		return upulse;
	}

	public void setUpulse(int upulse) {
		this.upulse = upulse;
	}

	public int getUperiod() {
		return uperiod;
	}

	public void setUperiod(int uperiod) {
		this.uperiod = uperiod;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Acupoint getAcupoint() {
		return acupoint;
	}

	public void setAcupoint(Acupoint acupoint) {
		this.acupoint = acupoint;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	@Override
	public String toString() {
		return "Apuser [ustrength=" + ustrength + ", umode=" + umode + ", ufrequency=" + ufrequency + ", utime=" + utime
				+ ", upulse=" + upulse + ", uperiod=" + uperiod + ", updateTime=" + updateTime + "]";
	}
}
