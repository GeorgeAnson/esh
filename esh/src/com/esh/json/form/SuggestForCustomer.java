package com.esh.json.form;

import java.io.Serializable;

import com.esh.json.form.response.SuggestBean;
/**
 * �Ƽ��б��м䴦���࣬�����������������������м�������ʱ�洢
 * @author Administrator
 *
 */
public class SuggestForCustomer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId=0;//������û�id
	private int diseaseId=0;//����Ĳ�֢id
	SuggestBean suggestBean=new SuggestBean();//�Ƽ�bean����װ���е��Ƽ��б�������Ϣ
	private int suggestSperiod=0;//�Ƽ��Ƴ�
	private int customerPeriod=0;//�û������Ƴ�
	
	public SuggestForCustomer() {
		
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

	public SuggestBean getSuggestBean() {
		return suggestBean;
	}

	public void setSuggestBean(SuggestBean suggestBean) {
		this.suggestBean = suggestBean;
	}

	public int getSuggestSperiod() {
		return suggestSperiod;
	}

	public void setSuggestSperiod(int suggestSperiod) {
		this.suggestSperiod = suggestSperiod;
	}

	public int getCustomerPeriod() {
		return customerPeriod;
	}

	public void setCustomerPeriod(int customerPeriod) {
		this.customerPeriod = customerPeriod;
	}

	@Override
	public String toString() {
		return "SuggestForCustomer [userId=" + userId + ", diseaseId=" + diseaseId + ", suggestBean=" + suggestBean
				+ ", suggestSperiod=" + suggestSperiod + ", customerPeriod=" + customerPeriod + "]";
	}
}
