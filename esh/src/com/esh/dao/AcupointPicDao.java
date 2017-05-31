package com.esh.dao;

import com.esh.entity.AcupointPicture;

public interface AcupointPicDao {

	/**
	 * 根据学位图Id获取学位动图
	 * @param picId
	 * @return
	 */
	public AcupointPicture getAcuPicByPicId(int picId);
}
