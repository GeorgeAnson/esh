package com.esh.dao;

import java.util.List;

import com.esh.entity.Disease;

public interface DiseaseDao {

	/**
	 * 获取所有疾病类型列表
	 * @return
	 */
	public List<Disease> getAllDisease();

	/**
	 * 通过病症id获取病症信息
	 * @param id
	 * @return
	 */
	public Disease getDiseaseByDiseaseId(int id);
}
