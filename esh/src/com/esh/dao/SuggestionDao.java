package com.esh.dao;

import java.util.List;

import com.esh.entity.Suggestion;

public interface SuggestionDao {

	/**
	 * ͨ���Ƽ��б�Ĳ�֢id��ȡ�Ƽ���¼
	 * @param diseaseId
	 * @return
	 */
	public List<Suggestion> getSuggestionByDiseaseId(int diseaseId);
}
