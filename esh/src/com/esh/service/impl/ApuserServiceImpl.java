package com.esh.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esh.dao.AcupointDao;
import com.esh.dao.ApuserDao;
import com.esh.dao.DiseaseDao;
import com.esh.dao.SuggestionDao;
import com.esh.entity.Acupoint;
import com.esh.entity.Apuser;
import com.esh.entity.Disease;
import com.esh.entity.Suggestion;
import com.esh.globle.Constants;
import com.esh.json.form.response.HistCueData;
import com.esh.service.ApuserService;

@Service("apuserService")
public class ApuserServiceImpl implements ApuserService {

	@Autowired
	ApuserDao apuserDao;
	
	@Autowired
	AcupointDao acupointDao;
	
	@Autowired
	DiseaseDao diseaseDao;
	
	@Autowired
	SuggestionDao suggestionDao;
	
	Logger logger=LoggerFactory.getLogger(getClass().getName());
	
	@Override
	public List<Apuser> getApuserByUserIdAndDiseaseId(int userId, int diseaseId) {
		List<Apuser> apusers=new ArrayList<>();
		apusers=apuserDao.getApuserByUserIdAndDiseaseId(userId, diseaseId);
		if(apusers!=null&&apusers.size()!=0)
		{
			//��ȡ�û����������ж�ӦѨλ����Ϣ
			for(Apuser apuser:apusers)
			{
				Acupoint acupoint=acupointDao.getAcupointByAcupointId(apuser.getAcupoint().getApId());
				if(acupoint!=null)
				{
					apuser.setAcupoint(acupoint);
				}
				logger.info(acupoint==null?"�����ڶ�ӦѨλ��ϸ���ݼ�¼��λ�ã�"+this.getClass().getName():"��ȡѨλ��ϸ��Ϣ�ɹ�");
				Disease disease=diseaseDao.getDiseaseByDiseaseId(apuser.getDisease().getDid());
				if(disease!=null)
				{
					apuser.setDisease(disease);
				}
				logger.info(disease==null?"�����ڶ�Ӧ��֢��ϸ���ݼ�¼��λ�ã�"+this.getClass().getName():"��ȡ��֢��ϸ���ݳɹ�");
			}
		}
		logger.info(apusers==null?"�����ڶ�Ӧ�û��������ݼ�¼,λ�ã�"+this.getClass().getName():"��ȡ�û��������ݳɹ�");
		return apusers;
	}


	@Override
	public int getHistApusersByUserId(List<HistCueData> cueDatas, int userId) {
		int errorCode=Constants.NO_ERROR_EXIST;
		List<Apuser> apusers=new ArrayList<Apuser>();
		apusers=apuserDao.getHistApusersByUserId(userId);
		logger.info(apusers.size()==0||apusers.isEmpty()?"��������ʷ�������ݼ�¼,λ�ã�"+this.getClass().getName():"��ȡ�û��������ݳɹ�");
		if(apusers.isEmpty()||apusers.size()==0)
		{
			errorCode=Constants.INFORMATION_NOT_EXIST;
		}else
		{
			for(Apuser apuser:apusers)
			{
				Acupoint acupoint=new Acupoint();
				acupoint=acupointDao.getAcupointByAcupointId(apuser.getAcupoint().getApId());
				Disease disease=new Disease();
				disease=diseaseDao.getDiseaseByDiseaseId(apuser.getDisease().getDid());
				HistCueData cueData=new HistCueData();
				cueData.setAcupointId(acupoint.getApId());
				cueData.setAcupointName(acupoint.getApName());
				cueData.setUsrPeriod(apuser.getUperiod());
				Suggestion suggestion=suggestionDao.getSuggestionByAcupointIdAndDiseaseId(acupoint.getApId(),disease.getDid());
				if(suggestion!=null)
				{
					cueData.setDevPeriod(suggestion.getSperiod());
				}
				cueData.setDevTime(apuser.getUtime());
				cueData.setDiseaseId(disease.getDid());
				cueData.setDiseaseName(disease.getDname());
				cueData.setLastCueTime(apuser.getUpdateTime());
				cueData.setUserId(userId);
				cueDatas.add(cueData);
			}
			logger.info("-------------------��ʷ�������ݼ��سɹ�---------------------------");
			System.out.println(cueDatas);
		}
		return errorCode;
	}
}
