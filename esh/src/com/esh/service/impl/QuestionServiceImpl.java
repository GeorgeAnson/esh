package com.esh.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esh.dao.QuestionDao;
import com.esh.dao.SuggestionDao;
import com.esh.dao.jdbc.JDBCUtil;
import com.esh.entity.Disease;
import com.esh.entity.Question;
import com.esh.entity.Suggestion;
import com.esh.entity.User;
import com.esh.globle.Constants;
import com.esh.json.form.request.QuestionForm;
import com.esh.service.QuestionService;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	SuggestionDao suggestionDao;
	
	@Autowired
	QuestionDao questionDao;
	
	Logger logger=LoggerFactory.getLogger(getClass().getName());
	
	@Override
	public void transferObject(Question question, QuestionForm questionForm) {
		//设置userId
		User user=new User();
		user.setUid(questionForm.getUserId());
		question.setUser(user);
		//设置病症Id
		Disease disease=new Disease();
		disease.setDid(questionForm.getDiseaseId());
		question.setDisease(disease);
		//设置建议Id
		Suggestion suggestion=suggestionDao.getSuggestionByAcupointIdAndDiseaseId(questionForm.getAcupId(), questionForm.getDiseaseId());
		suggestion.setSevaluation((suggestion.getSqusRecoSum()*suggestion.getSevaluation()+questionForm.getUsaf())/(suggestion.getSqusRecoSum()+1));
		suggestion.setSqusRecoSum(suggestion.getSqusRecoSum()+1);
		question.setSuggestion(suggestion);
		//设置满意度
		question.setSatisfaction(questionForm.getUsaf());
		//设置提交时间
		question.setTime(new Date());
		//设置疗效
		question.setEffection(questionForm.getUeffec());
	}

	@Override
	public int saveQuestion(Question question) {
		int errorCode=Constants.NO_ERROR_EXIST;
		Connection conn=JDBCUtil.getConnection();
		try
		{
			conn.setAutoCommit(false);
			//保存问卷信息
			int id=questionDao.saveUserQuestion(question, conn);
			logger.info(id==-1?"问卷信息数据存储失败，位置："+this.getClass().getName():"问卷信息存储成功");
			if(id!=-1)
			{
				//若问卷信息保存成功，更新建议表数据
				errorCode=suggestionDao.updateSuggestion(question.getSuggestion(), conn);
				logger.info(errorCode==Constants.NO_ERROR_EXIST?"建议信息更新成功":"建议信息更细失败，位置："+this.getClass().getName());
			}else
			{
				errorCode=Constants.UNKNOWN_OPERATION_ERROR;
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e)
		{
			e.printStackTrace();
			logger.info("事物操作失败，位置："+this.getClass().getName());
		}finally
		{
			JDBCUtil.close(conn);
		}
		return errorCode;
	}
}
