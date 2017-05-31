package com.esh.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esh.dao.DiseaseDao;
import com.esh.entity.Disease;
import com.esh.globle.Constants;
import com.esh.json.form.DiseaseNav;
import com.esh.service.NavService;
import com.esh.utils.ERRORUtil;

@Service("navService")
public class NavServiceImpl implements NavService {

	@Autowired
	DiseaseDao diseaseDao;
	//获取logger对象
	Logger logger=LoggerFactory.getLogger(getClass().getName());
	
	@Override
	public int createNavs(DiseaseNav diseaseNav) {
		int errorCode=Constants.NO_ERROR_EXIST;
		//若对象为初始化，则初始化对象
		if(diseaseNav==null)
		{
			try
			{
				diseaseNav=new DiseaseNav();
			}catch(Exception e)
			{
				e.printStackTrace();
				errorCode=Constants.ENTITY_INSTANCE_DEFEAT;
				logger.info(DiseaseNav.class.getName()+ERRORUtil.message(errorCode));
			}
		}
		//获取所有疾病类型
		List<Disease> diseases=diseaseDao.getAllDisease();
		diseaseNav.setDiseases(diseases);
		//生成菜单是否成功
		if(!diseaseNav.Create())
		{
			errorCode=Constants.INFORMATION_LOAD_DEFEAT;
			logger.info("菜单创建失败,发生错误的类："+Disease.class.getName());
		}else
		{
			logger.info("-------------菜单创建成功---------------");
		}
		return errorCode;
	}

}
