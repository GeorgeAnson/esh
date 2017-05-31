package com.esh.entity;

import java.io.Serializable;
import java.util.Date;

public class Apuser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ustrength=0;//用户治疗强度
	private int umode=0;//用户治疗模式
	private int ufrequency=0;//用户治疗频率值
	private int utime=0;//用户治疗时长
	private int upulse=0;//用户治疗脉冲值
	private int uperiod=0;//用户已治疗的疗程
	private Date updateTime=null;//更新时间
	private User user=null;//用户
	private Acupoint acupoint=null;//穴位
	private Disease disease=null;//病症
	
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
