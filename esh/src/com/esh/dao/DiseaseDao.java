package com.esh.dao;

import java.util.List;

import com.esh.entity.Disease;

public interface DiseaseDao {

	/**
	 * ��ȡ���м��������б�
	 * @return
	 */
	public List<Disease> getAllDisease();

	/**
	 * ͨ����֢id��ȡ��֢��Ϣ
	 * @param id
	 * @return
	 */
	public Disease getDiseaseByDiseaseId(int id);
}
