package com.esh.json.form;

import java.io.Serializable;
/**
 * �豸���������������࣬�����ϴ����ݻ�
 * @author Administrator
 *
 */
public class CueDevParma implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId=0;//�û�id
	private int acupointId=0;//Ѩλid
	private int diseaseId=0;//��֢id
	private String diseaseParentName=null;//��֢�������
	private String diseaseName=null;//��֢����
	private int devStrength=0;//ǿ��ֵ
	private int devMode=0;//�豸ģʽֵ
	private int devFrequency=0;//Ƶ��ֵ
	private int devTime=0;//ʱ��ֵ
	private int devPulse=0;//����ֵ
	
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
