package com.esh.service;

import com.esh.entity.Question;
import com.esh.json.form.request.QuestionForm;

public interface QuestionService {

	/**
	 * ���ʾ��������ݶ���ת�����ڲ��ʾ����
	 * @param question
	 * @param questionForm
	 */
	public void transferObject(Question question, QuestionForm questionForm);

	/**
	 * �����ʾ�����
	 * @param question
	 * @return
	 */
	public int saveQuestion(Question question);
}
