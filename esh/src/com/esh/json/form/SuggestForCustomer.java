package com.esh.json.form;

import java.io.Serializable;

import com.esh.json.form.response.SuggestBean;
/**
 * 推荐列表，中间处理类，接收请求流参数，事务处理中间数据临时存储
 * @author Administrator
 *
 */
public class SuggestForCustomer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId=0;//请求的用户id
	private int diseaseId=0;//请求的病症id
	SuggestBean suggestBean=new SuggestBean();//推荐bean，封装其中的推荐列表数据信息
	private int suggestSperiod=0;//推荐疗程
	private int customerPeriod=0;//用户治疗疗程
	
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
