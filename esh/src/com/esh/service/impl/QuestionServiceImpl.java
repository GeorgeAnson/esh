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
		//����userId
		User user=new User();
		user.setUid(questionForm.getUserId());
		question.setUser(user);
		//���ò�֢Id
		Disease disease=new Disease();
		disease.setDid(questionForm.getDiseaseId());
		question.setDisease(disease);
		//���ý���Id
		Suggestion suggestion=suggestionDao.getSuggestionByAcupointIdAndDiseaseId(questionForm.getAcupId(), questionForm.getDiseaseId());
		suggestion.setSevaluation((suggestion.getSqusRecoSum()*suggestion.getSevaluation()+questionForm.getUsaf())/(suggestion.getSqusRecoSum()+1));
		suggestion.setSqusRecoSum(suggestion.getSqusRecoSum()+1);
		question.setSuggestion(suggestion);
		//���������
		question.setSatisfaction(questionForm.getUsaf());
		//�����ύʱ��
		question.setTime(new Date());
		//������Ч
		question.setEffection(questionForm.getUeffec());
	}

	@Override
	public int saveQuestion(Question question) {
		int errorCode=Constants.NO_ERROR_EXIST;
		Connection conn=JDBCUtil.getConnection();
		try
		{
			conn.setAutoCommit(false);
			//�����ʾ���Ϣ
			int id=questionDao.saveUserQuestion(question, conn);
			logger.info(id==-1?"�ʾ���Ϣ���ݴ洢ʧ�ܣ�λ�ã�"+this.getClass().getName():"�ʾ���Ϣ�洢�ɹ�");
			if(id!=-1)
			{
				//���ʾ���Ϣ����ɹ������½��������
				errorCode=suggestionDao.updateSuggestion(question.getSuggestion(), conn);
				logger.info(errorCode==Constants.NO_ERROR_EXIST?"������Ϣ���³ɹ�":"������Ϣ��ϸʧ�ܣ�λ�ã�"+this.getClass().getName());
			}else
			{
				errorCode=Constants.UNKNOWN_OPERATION_ERROR;
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e)
		{
			e.printStackTrace();
			logger.info("�������ʧ�ܣ�λ�ã�"+this.getClass().getName());
		}finally
		{
			JDBCUtil.close(conn);
		}
		return errorCode;
	}
}
