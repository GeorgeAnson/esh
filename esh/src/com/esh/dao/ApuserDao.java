package com.esh.dao;

import java.util.List;

import com.esh.entity.Apuser;

public interface ApuserDao {

	/**
	 * 通过userId和diseaseId获取用户治疗过的穴位信息以及
	 * @param userId
	 * @param diseaseId
	 * @return
	 */
	public List<Apuser> getApuserByUserIdAndDiseaseId(int userId, int diseaseId);
	

	/**
	 * 通过acupointId和diseaseId获取使用量统计值
	 * @param did
	 * @param apId
	 * @return
	 */
	public int getCountOfApuserByAcpointIdAndDiseaseId(int did, int apId);


	/**
	 * 通过用户id和穴位id获取设备操作参数
	 * @param userId
	 * @param acupoingId
	 * @param diseaseId 
	 * @return
	 */
	public Apuser getApuserByUserIdAndAcupointId(int userId, int acupoingId, int diseaseId);


	/**
	 * 保存或更新用户操作参数，userId，diseaseId以及acupoingId组合唯一则插入，否则更新
	 * @param apuser
	 * @return
	 */
	public int saveOrUpdateCueDevParma(Apuser apuser);

	/**
	 * 获取userId对应历史历史理疗数据,
	 * 以上传时间列为参考进行降序排序
	 * @param userId
	 * @return
	 */
	public List<Apuser> getHistApusersByUserId(int userId);
}
