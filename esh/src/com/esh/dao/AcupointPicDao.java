package com.esh.dao;

import com.esh.entity.AcupointPicture;

public interface AcupointPicDao {

	/**
	 * ����ѧλͼId��ȡѧλ��ͼ
	 * @param picId
	 * @return
	 */
	public AcupointPicture getAcuPicByPicId(int picId);
}
