package com.esh.service;

import java.util.List;

import com.esh.json.form.SuggestForCustomer;

public interface SuggestionForCusService {

	/**
	 * ��ȡ�Ƽ�ѧλ��Ϣ
	 * ������Ϣ��user_id��disease_id
	 * 1.���û��Ѿ����ƹ���Ӧ��Ѩλ��ͨ��userId�Լ�disease_id��ȡ�û�ʹ�ù���Ѩλ�б�
	 * 2.��û�У�����Ƽ��б���ͨ��disease_id��ȡap_id�б�ͨ��ap_id��ȡAcuPoint��ӦѨλ
	 * @param suggestForCustomers 
	 * @param suggestForCustomer
	 * @return
	 */
	public int getSuggestionsAcupForCustomer(List<SuggestForCustomer> suggestForCustomers, SuggestForCustomer suggestForCustomer);
}
