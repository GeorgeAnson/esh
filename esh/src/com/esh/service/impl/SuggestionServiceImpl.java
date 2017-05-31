package com.esh.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esh.dao.AcupointDao;
import com.esh.dao.DiseaseDao;
import com.esh.dao.SuggestionDao;
import com.esh.dao.jdbc.JDBCBase;
import com.esh.entity.Acupoint;
import com.esh.entity.Disease;
import com.esh.entity.Suggestion;
import com.esh.service.SuggestionService;

@Service("suggestionService")
public class SuggestionServiceImpl extends JDBCBase implements SuggestionService {

	@Autowired
	SuggestionDao suggestionDao;
	
	@Autowired
	DiseaseDao diseaseDao;
	
	@Autowired
	AcupointDao acupointDao;
	
	Logger logger=LoggerFactory.getLogger(getClass().getName());
	
	@Override
	public List<Suggestion> getSuggestionsByDiseaseId(int diseaseId) {
		List<Suggestion> suggestions=new ArrayList<>();
		suggestions=suggestionDao.getSuggestionByDiseaseId(diseaseId);
		if(suggestions!=null)
		{
			for(Suggestion suggestion:suggestions)
			{
				Disease disease=diseaseDao.getDiseaseByDiseaseId(suggestion.getDisease().getDid());
				if(disease!=null)
				{
					suggestion.setDisease(disease);
				}
				logger.info(disease==null?"�����ڶ�Ӧ��֢��Ϣ��λ�ã�"+this.getClass().getName():"��ȡ��֢��Ϣ�ɹ�");
				Acupoint acupoint=acupointDao.getAcupointByAcupointId(suggestion.getAcupoint().getApId());
				if(acupoint!=null)
				{
					suggestion.setAcupoint(acupoint);
				}
				logger.info(acupoint==null?"�����ڶ�ӦѨλ��Ϣ��λ�ã�"+this.getClass().getName():"��ȡѨλ��Ϣ�ɹ�");
			}
		}
		logger.info(suggestions==null?"�����ڶ�Ӧ���Ƽ���Ϣ���ϣ�λ�ã�"+this.getClass().getName():"��ȡ�Ƽ���Ϣ�ɹ�");
		return suggestions;
	}

}
