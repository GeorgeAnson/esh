package com.esh.dao;

import java.util.List;

import com.esh.entity.Suggestion;

public interface SuggestionDao {

	/**
	 * 通过推荐列表的病症id获取推荐记录
	 * @param diseaseId
	 * @return
	 */
	public List<Suggestion> getSuggestionByDiseaseId(int diseaseId);
}
