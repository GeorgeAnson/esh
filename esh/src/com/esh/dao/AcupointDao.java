package com.esh.dao;

import com.esh.entity.Acupoint;

public interface AcupointDao {

	/**
	 * ͨ��Ѩλid��ȡѨλ��Ϣ
	 * @param acupointId
	 * @return
	 */
	public Acupoint getAcupointByAcupointId(int acupointId);
}
