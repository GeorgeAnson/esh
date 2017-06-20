package com.esh.dao;

import java.sql.Connection;
import java.util.List;

import com.esh.entity.Suggestion;

public interface SuggestionDao {

	/**
	 * 通过推荐列表的病症id获取推荐记录
	 * @param diseaseId
	 * @return
	 */
	public List<Suggestion> getSuggestionByDiseaseId(int diseaseId);

	/**
	 * 根据diseaseId和acupointId获取suggestion实体
	 * @param apId
	 * @param did
	 * @return
	 */
	public Suggestion getSuggestionByAcupointIdAndDiseaseId(int apId, int did);

	/**
	 * 更新建议信息
	 * @param suggestion
	 * @return
	 */
	public int updateSuggestion(Suggestion suggestion, Connection conn);
}
