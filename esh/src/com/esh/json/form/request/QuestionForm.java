package com.esh.json.form.request;

import java.io.Serializable;

/**
 * 用户问卷数据
 * @author Administrator
 *
 */
public class QuestionForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId=0;//用户id
	private int diseaseId=0;//病症id
	private int acupId=0;//穴位id
	private int suId=0;//建议id
	private double usaf=0;//用户满意度
	private String ueffec=null;//穴位疗效
	
	public QuestionForm() {
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(int diseaseId) {
		this.diseaseId = diseaseId;
	}

	public int getAcupId() {
		return acupId;
	}

	public void setAcupId(int acupId) {
		this.acupId = acupId;
	}

	public int getSuId() {
		return suId;
	}

	public void setSuId(int suId) {
		this.suId = suId;
	}

	public double getUsaf() {
		return usaf;
	}

	public void setUsaf(double usaf) {
		this.usaf = usaf;
	}

	public String getUeffec() {
		return ueffec;
	}

	public void setUeffec(String ueffec) {
		this.ueffec = ueffec;
	}

	@Override
	public String toString() {
		return "QuestionForm [userId=" + userId + ", diseaseId=" + diseaseId + ", acupId=" + acupId + ", suId=" + suId
				+ ", usaf=" + usaf + ", ueffec=" + ueffec + "]";
	}
}
