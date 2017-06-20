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
		logger.info(apuser==null?"�������û���ʷ���Ʋ��������Ի�ȡĬ��Ѩλ���Ʋ�����"+this.getClass().getName():"��ȡ�û���ʷ���Ʋ����ɹ�");
		if(apuser==null)
		{
			Acupoint acupoint=acupointDao.getAcupointByAcupointId(cueDevParma.getAcupointId());
			logger.info(acupoint==null?"������Ĭ��Ѩλ���Ʋ�����λ�ã�"+this.getClass().getName():"��ȡ�û���ʷ���Ʋ����ɹ�");
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
		//��ȡѨλ��Ӧ�Ĳ�֢��������Լ�����
		Disease disease=diseaseDao.getDiseaseByDiseaseId(cueDevParma.getDiseaseId());
		logger.info(disease==null?"�����ڸò�֢��¼��"+this.getClass().getName():"��ȡ��֢���Ƴɹ�");
		Disease parentDis=diseaseDao.getDiseaseByDiseaseId(disease.getDfid());
		logger.info(parentDis==null?"�����ڸò�֢���ͼ�¼��"+this.getClass().getName():"��ȡ��֢���ͳɹ�");
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
				||cueDevParma.getUserId()==0)?ERRORUtil.message(errorCode=Constants.INVALID_REQUEST):"�ύ������Ϣ����");
		//�������¸��û����豸��������
		if(cueDevParma.getAcupointId()!=0&&cueDevParma.getDiseaseId()!=0&&cueDevParma.getUserId()!=0)
		{
			Apuser apuser=apuserDao.getApuserByUserIdAndAcupointId(cueDevParma.getUserId(), cueDevParma.getAcupointId(), cueDevParma.getDiseaseId());
			logger.info(apuser==null?"�������û���ʷ����������¼����ʼ�����¼�¼":"�����û���ʷ����������¼����ʼ���¼�¼");
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
			apuser.setUtime(cueDevParma.getDevTime()<1?1:cueDevParma.getDevTime());//����ʱ��Ĭ�ϴ��ڵ���1����
			apuser.setUpdateTime(Utils.stringToExactDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
			errorCode=apuserDao.saveOrUpdateCueDevParma(apuser);
			logger.info(errorCode==Constants.NO_ERROR_EXIST?ERRORUtil.message(Constants.INFORMATION_UPDATE_SECCESS):ERRORUtil.message(errorCode));
		}
		return errorCode;
	}
}
