package com.esh.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esh.dao.AcupointDao;
import com.esh.dao.ApuserDao;
import com.esh.dao.DiseaseDao;
import com.esh.entity.Acupoint;
import com.esh.entity.Apuser;
import com.esh.entity.Disease;
import com.esh.entity.User;
import com.esh.globle.Constants;
import com.esh.json.form.CueDevParma;
import com.esh.service.CueDevService;
import com.esh.utils.ERRORUtil;
import com.esh.utils.Utils;
@Service("cueDevService")
public class CueDevServiceImpl implements CueDevService {

	Logger logger=LoggerFactory.getLogger(getClass().getName());
	
	@Autowired
	ApuserDao apuserDao;
	
	@Autowired
	AcupointDao acupointDao;
	
	@Autowired
	DiseaseDao diseaseDao;
	
	@Override
	public int getCueDevParma(CueDevParma cueDevParma) {
		int errorCode=Constants.NO_ERROR_EXIST;
		Apuser apuser=apuserDao.getApuserByUserIdAndAcupointId(cueDevParma.getUserId(), cueDevParma.getAcupointId(), cueDevParma.getDiseaseId());
		logger.info(apuser==null?"不存在用户历史治疗参数，尝试获取默认穴位治疗参数："+this.getClass().getName():"获取用户历史治疗参数成功");
		if(apuser==null)
		{
			Acupoint acupoint=acupointDao.getAcupointByAcupointId(cueDevParma.getAcupointId());
			logger.info(acupoint==null?"不存在默认穴位治疗参数，位置："+this.getClass().getName():"获取用户历史治疗参数成功");
			if(acupoint!=null)
			{
				cueDevParma.setDevFrequency(acupoint.getApFrequency());
				cueDevParma.setDevMode(acupoint.getApMode());
				cueDevParma.setDevPulse(acupoint.getApulse());
				cueDevParma.setDevStrength(acupoint.getApStrength());
				cueDevParma.setDevTime(acupoint.getApTime());
			}else
			{
				errorCode=Constants.INFORMATION_LOAD_DEFEAT;
			}
		}else
		{
			cueDevParma.setDevFrequency(apuser.getUfrequency());
			cueDevParma.setDevMode(apuser.getUmode());
			cueDevParma.setDevPulse(apuser.getUpulse());
			cueDevParma.setDevStrength(apuser.getUstrength());
			cueDevParma.setDevTime(apuser.getUtime());
		}
		//获取穴位对应的病症父类类别以及名称
		Disease disease=diseaseDao.getDiseaseByDiseaseId(cueDevParma.getDiseaseId());
		logger.info(disease==null?"不存在该病症记录："+this.getClass().getName():"获取病症名称成功");
		Disease parentDis=diseaseDao.getDiseaseByDiseaseId(disease.getDfid());
		logger.info(parentDis==null?"不存在该病症类型记录："+this.getClass().getName():"获取病症类型成功");
		if(disease!=null&&parentDis!=null)
		{
			cueDevParma.setDiseaseName(disease.getDname());
			cueDevParma.setDiseaseParentName(parentDis.getDname());
		}else
		{
			errorCode=Constants.INFORMATION_LOAD_DEFEAT;
		}
		return errorCode;
	}

	@Override
	public int saveOrUpdateCueDevParma(CueDevParma cueDevParma) {
		int errorCode=Constants.NO_ERROR_EXIST;
		logger.info((cueDevParma.getAcupointId()==0||cueDevParma.getDiseaseId()==0
				||cueDevParma.getUserId()==0)?ERRORUtil.message(errorCode=Constants.INVALID_REQUEST):"提交请求信息正常");
		//保存或更新该用户的设备操作参数
		if(cueDevParma.getAcupointId()!=0&&cueDevParma.getDiseaseId()!=0&&cueDevParma.getUserId()!=0)
		{
			Apuser apuser=apuserDao.getApuserByUserIdAndAcupointId(cueDevParma.getUserId(), cueDevParma.getAcupointId(), cueDevParma.getDiseaseId());
			logger.info(apuser==null?"不存在用户历史操作参数记录，开始插入新记录":"存在用户历史操作参数记录，开始更新记录");
			if(apuser==null)
			{
				apuser=new Apuser();
				apuser.setAcupoint(new Acupoint());
				apuser.setDisease(new Disease());
				apuser.setUser(new User());
			}
			apuser.getAcupoint().setApId(cueDevParma.getAcupointId());
			apuser.getDisease().setDid(cueDevParma.getDiseaseId());
			apuser.getUser().setUid(cueDevParma.getUserId());
			apuser.setUfrequency(cueDevParma.getDevFrequency());
			apuser.setUmode(cueDevParma.getDevMode());
			apuser.setUperiod(apuser.getUperiod()+1);
			apuser.setUpulse(cueDevParma.getDevPulse());
			apuser.setUstrength(cueDevParma.getDevStrength());
			apuser.setUtime(cueDevParma.getDevTime()<1?1:cueDevParma.getDevTime());//治疗时长默认大于等于1分钟
			apuser.setUpdateTime(Utils.stringToExactDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
			errorCode=apuserDao.saveOrUpdateCueDevParma(apuser);
			logger.info(errorCode==Constants.NO_ERROR_EXIST?ERRORUtil.message(Constants.INFORMATION_UPDATE_SECCESS):ERRORUtil.message(errorCode));
		}
		return errorCode;
	}
}
