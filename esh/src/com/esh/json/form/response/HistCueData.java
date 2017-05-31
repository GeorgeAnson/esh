package com.esh.json.form.response;

import java.io.Serializable;
import java.util.Date;

/**
 * �û�ʹ�ù�����ʷ����Ѩλ��Ϣ
 * @author Administrator
 *
 */
public class HistCueData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId=0;//�û�id
	private int acupointId=0;//Ѩλid
	private int diseaseId=0;//��֢id
	private Date lastCueTime=null;//�ϴ����������ϴ�ʱ��
	private String diseaseName=null;//��֢����
	private String acupointName=null;//Ѩλ����
	private int devTime=0;//����ʱ��
	private int devPeriod=0;//�Ѿ����Ƶ������Ƴ�
	
	public HistCueData() {
		
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

	public Date getLastCueTime() {
		return lastCueTime;
	}

	public void setLastCueTime(Date lastCueTime) {
		this.lastCueTime = lastCueTime;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getAcupointName() {
		return acupointName;
	}

	public void setAcupointName(String acupointName) {
		this.acupointName = acupointName;
	}

	public int getDevTime() {
		return devTime;
	}

	public void setDevTime(int devTime) {
		this.devTime = devTime;
	}

	public int getDevPeriod() {
		return devPeriod;
	}

	public void setDevPeriod(int devPeriod) {
		this.devPeriod = devPeriod;
	}

	@Override
	public String toString() {
		return "HistCueData [userId=" + userId + ", acupointId=" + acupointId + ", diseaseId=" + diseaseId
				+ ", lastCueTime=" + lastCueTime + ", diseaseName=" + diseaseName + ", acupointName=" + acupointName
				+ ", devTime=" + devTime + ", devPeriod=" + devPeriod + "]";
	}
}
