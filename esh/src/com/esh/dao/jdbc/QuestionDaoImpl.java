package com.esh.dao.jdbc;

import org.springframework.stereotype.Service;

import com.esh.dao.QuestionDao;
import com.esh.entity.Question;

@Service("questionDao")
public class QuestionDaoImpl extends JDBCBase implements QuestionDao {

	@Override
	public int saveUserQuestion(Question question) {
		String sql="insert into question(user_id, d_id, s_id,satisfaction, time,effect) values(?,?,?,?,?,?)";
		Object[] parma={
			question.getUser().getUid(),
			question.getDisease().getDid(),
			question.getSuggestion().getSid(),
			question.getSatisfaction(),
			question.getTime(),
			question.getEffection()
		};
		int id=save(sql, parma);
		return id;
	}
}
