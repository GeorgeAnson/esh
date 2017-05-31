package com.esh.json.form;

import java.io.Serializable;
/**
 * 设备参数请求流数据类，保存上传数据或
 * @author Administrator
 *
 */
public class CueDevParma implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId=0;//用户id
	private int acupointId=0;//穴位id
	private int diseaseId=0;//病症id
	private String diseaseParentName=null;//病症父类类别
	private String diseaseName=null;//病症名称
	private int devStrength=0;//强度值
	private int devMode=0;//设备模式值
	private int devFrequency=0;//频率值
	private int devTime=0;//时长值
	private int devPulse=0;//脉冲值
	
	public CueDevParma() {
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAcupointId() {
		return acupointId;
	}

	public void setAcupointId(int acupointId) {
		this.acupointId = acupointId;
	}

	public int getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(int diseaseId) {
		this.diseaseId = diseaseId;
	}

	public String getDiseaseParentName() {
		return diseaseParentName;
	}

	public void setDiseaseParentName(String diseaseParentName) {
		this.diseaseParentName = diseaseParentName;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public int getDevStrength() {
		return devStrength;
	}

	public void setDevStrength(int devStrength) {
		this.devStrength = devStrength;
	}

	public int getDevMode() {
		return devMode;
	}

	public void setDevMode(int devMode) {
		this.devMode = devMode;
	}

	public int getDevFrequency() {
		return devFrequency;
	}

	public void setDevFrequency(int devFrequency) {
		this.devFrequency = devFrequency;
	}

	public int getDevTime() {
		return devTime;
	}

	public void setDevTime(int devTime) {
		this.devTime = devTime;
	}

	public int getDevPulse() {
		return devPulse;
	}

	public void setDevPulse(int devPulse) {
		this.devPulse = devPulse;
	}

	@Override
	public String toString() {
		return "CueDevParma [userId=" + userId + ", acupointId=" + acupointId + ", diseaseId=" + diseaseId
				+ ", diseaseParentName=" + diseaseParentName + ", diseaseName=" + diseaseName + ", devStrength="
				+ devStrength + ", devMode=" + devMode + ", devFrequency=" + devFrequency + ", devTime=" + devTime
				+ ", devPulse=" + devPulse + "]";
	}
}
