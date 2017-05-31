package com.esh.json.form.response;

import java.io.Serializable;
/**
 * 对应病症的用户穴位推荐列表
 * @author Administrator
 *
 */
public class SuggestBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String diseaseName=null;//病症名称
	private int acupointId=0;//穴位id
	private String acupointName=null;//穴位名称
	private String acupointDescribe=null;//穴位描述
	private double suggestEvaluation=0;//推荐指数
	private int sumOfCustomer=0;//使用总人数
	
	public SuggestBean() {
		
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public int getAcupointId() {
		return acupointId;
	}

	public void setAcupointId(int acupointId) {
		this.acupointId = acupointId;
	}
	public String getAcupointName() {
		return acupointName;
	}

	public void setAcupointName(String acupointName) {
		this.acupointName = acupointName;
	}

	public String getAcupointDescribe() {
		return acupointDescribe;
	}

	public void setAcupointDescribe(String acupointDescribe) {
		this.acupointDescribe = acupointDescribe;
	}

	public double getSuggestEvaluation() {
		return suggestEvaluation;
	}

	public void setSuggestEvaluation(double suggestEvaluation) {
		this.suggestEvaluation = suggestEvaluation;
	}

	public int getSumOfCustomer() {
		return sumOfCustomer;
	}

	public void setSumOfCustomer(int sumOfCustomer) {
		this.sumOfCustomer = sumOfCustomer;
	}

	@Override
	public String toString() {
		return "SuggestBean [diseaseName=" + diseaseName + ", acupointId=" + acupointId + ", acupointName="
				+ acupointName + ", acupointDescribe=" + acupointDescribe + ", suggestEvaluation=" + suggestEvaluation
				+ ", sumOfCustomer=" + sumOfCustomer + "]";
	}
}
