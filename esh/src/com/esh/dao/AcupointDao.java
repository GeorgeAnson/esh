package com.esh.dao;

import com.esh.entity.Acupoint;

public interface AcupointDao {

	/**
	 * 通过穴位id获取穴位信息
	 * @param acupointId
	 * @return
	 */
	public Acupoint getAcupointByAcupointId(int acupointId);
}
