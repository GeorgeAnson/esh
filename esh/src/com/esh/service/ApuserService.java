package com.esh.service;

import java.util.List;

import com.esh.entity.Apuser;
import com.esh.json.form.response.HistCueData;

public interface ApuserService {

	/**
	 * 通过userId和diseaseId获取穴位信息
	 * @param userId
	 * @param diseaseId
	 * @return
	 */
	public List<Apuser> getApuserByUserIdAndDiseaseId(int userId, int diseaseId);

	/**
	 * 获取用户userId的历史理疗数据
	 * @param userId
	 * @return
	 */
	public int getHistApusersByUserId(List<HistCueData> cueDatas, int userId);
}
