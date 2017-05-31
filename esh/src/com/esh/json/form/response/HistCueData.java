package com.esh.json.form.response;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户使用过的历史治疗穴位信息
 * @author Administrator
 *
 */
public class HistCueData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId=0;//用户id
	private int acupointId=0;//穴位id
	private int diseaseId=0;//病症id
	private Date lastCueTime=null;//上次治疗数据上传时间
	private String diseaseName=null;//病症名称
	private String acupointName=null;//穴位名称
	private int devTime=0;//治疗时长
	private int devPeriod=0;//已经治疗的治疗疗程
	
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
