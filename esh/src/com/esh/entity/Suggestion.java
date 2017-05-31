package com.esh.entity;

import java.io.Serializable;

public class Suggestion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sid=0;//����id
	private String sname=null;//��������
	private String scontext=null;//��������
	private double sevaluation=0;//�����Ǽ�
	private int speriod=0;//�����Ƴ�
	private Disease disease=null;//�����Ӧ��֢״
	private Acupoint acupoint=null;//����Ѩλ
	
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
