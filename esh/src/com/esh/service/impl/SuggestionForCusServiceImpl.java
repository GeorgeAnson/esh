package com.esh.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esh.dao.ApuserDao;
import com.esh.entity.Suggestion;
import com.esh.globle.Constants;
import com.esh.json.form.SuggestForCustomer;
import com.esh.service.ApuserService;
import com.esh.service.SuggestionForCusService;
import com.esh.service.SuggestionService;

@Service("suggestionForCusService")
public class SuggestionForCusServiceImpl implements SuggestionForCusService {

	@Autowired
	ApuserService apuserService;
	
	@Autowired
	SuggestionService suggestionService;
	
	@Autowired
	ApuserDao apuserDao;
	
	Logger logger=LoggerFactory.getLogger(getClass().getName());
	
	@Override
	public int getSuggestionsAcupForCustomer(List<SuggestForCustomer> suggestForCustomers,
			SuggestForCustomer suggestForCustomer) {
		int errorCode=Constants.NO_ERROR_EXIST;
		//��ȡ�û�������Ϣ���е�Ѩλ��¼
//		List<Apuser> apusers=new ArrayList<>();
//		apusers=apuserService.getApuserByUserIdAndDiseaseId(suggestForCustomer.getUserId(), suggestForCustomer.getDiseaseId());
//		if(apusers.size()==0)
//		{
//			logger.info("---------------�������û����Ƽ�¼�����Ի�ȡ�Ƽ����¼---------------");
//		}else
//		{
//			logger.info("---------------�����û����Ƽ�¼����ȡ�����Ի�ȡ�Ƽ����¼---------------");
//			for(Apuser apuser:apusers)
//			{
//				//����ʹ���߼�¼����
//				int sum=apuserDao.getCountOfApuserByAcpointIdAndDiseaseId(apuser.getDisease().getDid(),apuser.getAcupoint().getApId());
//				SuggestForCustomer temp=new SuggestForCustomer();
//				temp.getSuggestBean().setAcupointDescribe(apuser.getAcupoint().getDesctibe());
//				temp.getSuggestBean().setAcupointName(apuser.getAcupoint().getApName());
////				temp.setDiseaseId(apuser.getDisease().getDid());
//				temp.getSuggestBean().setDiseaseName(apuser.getDisease().getDname());
//				temp.setCustomerPeriod(apuser.getUperiod());
//				temp.getSuggestBean().setSumOfCustomer(sum);
////				temp.setUserId(apuser.getUser().getUid());
//				temp.getSuggestBean().setAcupointId(apuser.getAcupoint().getApId());
//				suggestForCustomers.add(temp);
//			}
//		}
		//��ȡ�Ƽ����ж�Ӧ���Ƽ���¼
		List<Suggestion> suggestions=new ArrayList<>();
		suggestions=suggestionService.getSuggestionsByDiseaseId(suggestForCustomer.getDiseaseId());
		if(suggestions.size()==0)
		{
			logger.info("�����ڶ�Ӧ�Ƽ����е��Ƽ���¼��λ�ã�"+this.getClass().getName());
			errorCode=Constants.INFORMATION_LOAD_DEFEAT;
		}else
		{
			logger.info("---------------���Ի�ȡ�Ƽ����е��Ƽ���¼�ɹ�---------------");
			for(Suggestion suggestion:suggestions)
			{
				//����ʹ���߼�¼����
				int sum=apuserDao.getCountOfApuserByAcpointIdAndDiseaseId(suggestion.getDisease().getDid(),suggestion.getAcupoint().getApId());
				SuggestForCustomer temp=new SuggestForCustomer();
				temp.getSuggestBean().setAcupointDescribe(suggestion.getAcupoint().getDesctibe());
				temp.getSuggestBean().setAcupointName(suggestion.getAcupoint().getApName());
//				temp.setDiseaseId(suggestion.getDisease().getDid());
				temp.getSuggestBean().setDiseaseName(suggestion.getDisease().getDname());
				temp.getSuggestBean().setSuggestEvaluation(suggestion.getSevaluation());
				temp.setSuggestSperiod(suggestion.getSperiod());
				temp.getSuggestBean().setSumOfCustomer(sum);
//				temp.setUserId(suggestion);
				temp.getSuggestBean().setAcupointId(suggestion.getAcupoint().getApId());
				suggestForCustomers.add(temp);
			}
		}
		//��������Ǽ�
		for(SuggestForCustomer sfc:suggestForCustomers)
		{
			if(sfc.getSuggestBean()!=null&&sfc.getSuggestBean().getSuggestEvaluation()==0)
			{
				for(Suggestion suggestion:suggestions)
				{
					if(sfc.getDiseaseId()==suggestion.getDisease().getDid()&&sfc.getSuggestBean().getAcupointId()==suggestion.getAcupoint().getApId())
					{
						sfc.getSuggestBean().setSuggestEvaluation(suggestion.getSevaluation());
					}
				}
			}
		}
		logger.info("---------------ȫ���Ǽ�����ɹ�---------------");
		return errorCode;
	}

}
