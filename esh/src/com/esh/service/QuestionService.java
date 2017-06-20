package com.esh.service;

import com.esh.entity.Question;
import com.esh.json.form.request.QuestionForm;

public interface QuestionService {

	/**
	 * 将问卷请求数据对象转换成内部问卷对象
	 * @param question
	 * @param questionForm
	 */
	public void transferObject(Question question, QuestionForm questionForm);

	/**
	 * 保存问卷数据
	 * @param question
	 * @return
	 */
	public int saveQuestion(Question question);
}
