package com.esh.dao;

import java.util.List;

import com.esh.entity.Apuser;

public interface ApuserDao {

	/**
	 * ͨ��userId��diseaseId��ȡ�û����ƹ���Ѩλ��Ϣ�Լ�
	 * @param userId
	 * @param diseaseId
	 * @return
	 */
	public List<Apuser> getApuserByUserIdAndDiseaseId(int userId, int diseaseId);
	

	/**
	 * ͨ��acupointId��diseaseId��ȡʹ����ͳ��ֵ
	 * @param did
	 * @param apId
	 * @return
	 */
	public int getCountOfApuserByAcpointIdAndDiseaseId(int did, int apId);


	/**
	 * ͨ���û�id��Ѩλid��ȡ�豸��������
	 * @param userId
	 * @param acupoingId
	 * @param diseaseId 
	 * @return
	 */
	public Apuser getApuserByUserIdAndAcupointId(int userId, int acupoingId, int diseaseId);


	/**
	 * ���������û�����������userId��diseaseId�Լ�acupoingId���Ψһ����룬�������
	 * @param apuser
	 * @return
	 */
	public int saveOrUpdateCueDevParma(Apuser apuser);

	/**
	 * ��ȡuserId��Ӧ��ʷ��ʷ��������,
	 * ���ϴ�ʱ����Ϊ�ο����н�������
	 * @param userId
	 * @return
	 */
	public List<Apuser> getHistApusersByUserId(int userId);
}
