package com.esh.json.form.response;

import java.io.Serializable;
/**
 * ��Ӧ��֢���û�Ѩλ�Ƽ��б�
 * @author Administrator
 *
 */
public class SuggestBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String diseaseName=null;//��֢����
	private int acupointId=0;//Ѩλid
	private String acupointName=null;//Ѩλ����
	private String acupointDescribe=null;//Ѩλ����
	private double suggestEvaluation=0;//�Ƽ�ָ��
	private int sumOfCustomer=0;//ʹ��������
	
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
