package com.esh.entity;

import java.io.Serializable;

public class Suggestion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sid=0;//建议id
	private String sname=null;//建议名称
	private String scontext=null;//建议内容
	private double sevaluation=0;//建议星级
	private int speriod=0;//建议疗程
	private Disease disease=null;//建议对应的症状
	private Acupoint acupoint=null;//建议穴位
	
	public Suggestion() {
		
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getScontext() {
		return scontext;
	}

	public void setScontext(String scontext) {
		this.scontext = scontext;
	}

	public double getSevaluation() {
		return sevaluation;
	}

	public void setSevaluation(double sevaluation) {
		this.sevaluation = sevaluation;
	}

	public int getSperiod() {
		return speriod;
	}

	public void setSperiod(int speriod) {
		this.speriod = speriod;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}
	
	public Acupoint getAcupoint() {
		return acupoint;
	}

	public void setAcupoint(Acupoint acupoint) {
		this.acupoint = acupoint;
	}

	@Override
	public String toString() {
		return "Suggest [sid=" + sid + ", sname=" + sname + ", scontext=" + scontext + ", sevaluation=" + sevaluation
				+ ", speriod=" + speriod + "]";
	}
}
