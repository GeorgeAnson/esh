package com.esh.dao;

import java.sql.Connection;

import com.esh.entity.Question;

public interface QuestionDao {

	/**
	 * 保存用户提交的问卷信息
	 * @param question
	 * @return
	 */
	public int saveUserQuestion(Question question, Connection conn);
}
