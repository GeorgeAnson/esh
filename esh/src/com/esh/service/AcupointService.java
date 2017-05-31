package com.esh.service;

import com.esh.entity.Acupoint;
import com.esh.entity.AcupointPicture;

public interface AcupointService {

	/**
	 * ͨ��Ѩλid��ȡѨλ��ϸ��Ϣ
	 * @param acpuointId
	 * @return
	 */
	public Acupoint getAcupointByAcupointId(int acupointId);
	
	/**
	 * ���ͼƬ���棬�������ڻ��棬�����
	 * @param picture
	 * @param parentPath
	 * @return
	 */
	public int initDiseasePicture(AcupointPicture picture, String parentPath);
}
