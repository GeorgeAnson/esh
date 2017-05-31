package com.esh.service;

import java.util.List;

import com.esh.entity.Apuser;
import com.esh.json.form.response.HistCueData;

public interface ApuserService {

	/**
	 * ͨ��userId��diseaseId��ȡѨλ��Ϣ
	 * @param userId
	 * @param diseaseId
	 * @return
	 */
	public List<Apuser> getApuserByUserIdAndDiseaseId(int userId, int diseaseId);

	/**
	 * ��ȡ�û�userId����ʷ��������
	 * @param userId
	 * @return
	 */
	public int getHistApusersByUserId(List<HistCueData> cueDatas, int userId);
}
