package com.esh.dao;

import com.esh.entity.Question;

public interface QuestionDao {

	/**
	 * �����û��ύ���ʾ���Ϣ
	 * @param question
	 * @return
	 */
	public int saveUserQuestion(Question question);
}
