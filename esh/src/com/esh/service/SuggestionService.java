package com.esh.service;

import java.util.List;

import com.esh.entity.Suggestion;

public interface SuggestionService {

	/**
	 * ͨ��diseaseId��ȡ�Ƽ���¼
	 * @param diseaseId
	 * @return
	 */
	public List<Suggestion> getSuggestionsByDiseaseId(int diseaseId);
}
