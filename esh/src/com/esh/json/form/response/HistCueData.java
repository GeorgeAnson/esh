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
	private double devTime=0;//����ʱ��
	private double devPeriod=0;//�������Ƶ��������Ƴ�
	private double usrPeriod=0;//�û��Ѿ����Ƶ��Ƴ�
	
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

	public double getDevTime() {
		return devTime;
	}

	public void setDevTime(double devTime) {
		this.devTime = devTime;
	}

	public double getDevPeriod() {
		return devPeriod;
	}

	public void setDevPeriod(double devPeriod) {
		this.devPeriod = devPeriod;
	}

	public double getUsrPeriod() {
		return usrPeriod;
	}

	public void setUsrPeriod(double usrPeriod) {
		this.usrPeriod = usrPeriod;
	}

	@Override
	public String toString() {
		return "HistCueData [userId=" + userId + ", acupointId=" + acupointId + ", diseaseId=" + diseaseId
				+ ", lastCueTime=" + lastCueTime + ", diseaseName=" + diseaseName + ", acupointName=" + acupointName
				+ ", devTime=" + devTime + ", devPeriod=" + devPeriod + ", usrPeriod=" + usrPeriod + "]";
	}
}
