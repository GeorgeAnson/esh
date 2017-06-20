package com.esh.dao;

import java.sql.Connection;
import java.util.List;

import com.esh.entity.Suggestion;

public interface SuggestionDao {

	/**
	 * ͨ���Ƽ��б�Ĳ�֢id��ȡ�Ƽ���¼
	 * @param diseaseId
	 * @return
	 */
	public List<Suggestion> getSuggestionByDiseaseId(int diseaseId);

	/**
	 * ����diseaseId��acupointId��ȡsuggestionʵ��
	 * @param apId
	 * @param did
	 * @return
	 */
	public Suggestion getSuggestionByAcupointIdAndDiseaseId(int apId, int did);

	/**
	 * ���½�����Ϣ
	 * @param suggestion
	 * @return
	 */
	public int updateSuggestion(Suggestion suggestion, Connection conn);
}
