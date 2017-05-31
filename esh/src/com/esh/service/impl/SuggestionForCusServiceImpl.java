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
		//获取用户治疗信息表中的穴位记录
//		List<Apuser> apusers=new ArrayList<>();
//		apusers=apuserService.getApuserByUserIdAndDiseaseId(suggestForCustomer.getUserId(), suggestForCustomer.getDiseaseId());
//		if(apusers.size()==0)
//		{
//			logger.info("---------------不存在用户治疗记录，尝试获取推荐表记录---------------");
//		}else
//		{
//			logger.info("---------------存在用户治疗记录并获取，尝试获取推荐表记录---------------");
//			for(Apuser apuser:apusers)
//			{
//				//搜索使用者记录总数
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
		//获取推荐表中对应的推荐记录
		List<Suggestion> suggestions=new ArrayList<>();
		suggestions=suggestionService.getSuggestionsByDiseaseId(suggestForCustomer.getDiseaseId());
		if(suggestions.size()==0)
		{
			logger.info("不存在对应推荐表中的推荐记录，位置："+this.getClass().getName());
			errorCode=Constants.INFORMATION_LOAD_DEFEAT;
		}else
		{
			logger.info("---------------尝试获取推荐表中的推荐记录成功---------------");
			for(Suggestion suggestion:suggestions)
			{
				//搜索使用者记录总数
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
		//填充评估星级
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
		logger.info("---------------全部星级运算成功---------------");
		return errorCode;
	}

}
