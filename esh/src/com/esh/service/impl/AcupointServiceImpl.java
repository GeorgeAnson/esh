package com.esh.service.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esh.dao.AcupointDao;
import com.esh.dao.AcupointPicDao;
import com.esh.entity.Acupoint;
import com.esh.entity.AcupointPicture;
import com.esh.globle.Constants;
import com.esh.service.AcupointService;
import com.esh.utils.Utils;

@Service("acupointService")
public class AcupointServiceImpl implements AcupointService {

	
	Logger logger=LoggerFactory.getLogger(getClass().getName());
	
	@Autowired
	AcupointDao acupointDao;
	
	@Autowired
	AcupointPicDao acupointPicDao;
	
	@Override
	public Acupoint getAcupointByAcupointId(int acupointId) {
		Acupoint acupoint=acupointDao.getAcupointByAcupointId(acupointId);
		if(acupoint!=null&&acupoint.getPicture()!=null&&acupoint.getPicture().getPictureId()!=0)
		{
			acupoint.setPicture(acupointPicDao.getAcuPicByPicId(acupoint.getPicture().getPictureId()));
		}
		logger.info((acupoint!=null&&acupoint.getPicture()!=null)?"��ȡ����ɹ�":"��ȡʧ�ܣ�λ����Ϣ��"+this.getClass().getName());
		return acupoint;
	}

	@Override
	public int initDiseasePicture(AcupointPicture picture, String parentPath) {
		int errorCode=Constants.NO_ERROR_EXIST;
		if(picture.getData()!=null)
		{
			String path=parentPath+"image_cache\\"+picture.getPictureName();
			//��·����Ӧ���ļ������ڣ��򴴽��ļ�
			if(!new File(path).exists())
			{
				if(!Utils.getFile(picture.getData(), path))
				{
					errorCode=Constants.INFORMATION_LOAD_DEFEAT;
					logger.info("��ͼ����ʧ�ܣ�λ����Ϣ��"+this.getClass().getName());
				}else
				{
					logger.info("���ͼƬ����ɹ�,ͼƬ���ƣ�"+picture.getPictureName());
				}
			}
		}
		return errorCode;
	}

}
