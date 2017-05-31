package com.esh.service;

import java.util.List;

import com.esh.entity.Suggestion;

public interface SuggestionService {

	/**
	 * 通过diseaseId获取推荐记录
	 * @param diseaseId
	 * @return
	 */
	public List<Suggestion> getSuggestionsByDiseaseId(int diseaseId);
}
