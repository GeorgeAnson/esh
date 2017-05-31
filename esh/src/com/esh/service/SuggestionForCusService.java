package com.esh.service;

import java.util.List;

import com.esh.json.form.SuggestForCustomer;

public interface SuggestionForCusService {

	/**
	 * 获取推荐学位信息
	 * 请求信息：user_id，disease_id
	 * 1.若用户已经治疗过对应的穴位，通过userId以及disease_id获取用户使用过的穴位列表
	 * 2.若没有，则从推荐列表中通过disease_id获取ap_id列表，通过ap_id获取AcuPoint对应穴位
	 * @param suggestForCustomers 
	 * @param suggestForCustomer
	 * @return
	 */
	public int getSuggestionsAcupForCustomer(List<SuggestForCustomer> suggestForCustomers, SuggestForCustomer suggestForCustomer);
}
