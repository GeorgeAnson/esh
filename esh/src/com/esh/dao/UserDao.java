package com.esh.dao;

import com.esh.entity.User;

public interface UserDao {

	
	/**
	 * ��ȡ�û����˺���Ϣ---��ɾ��
	 * @param condition
	 * @return
	 */
	public User getUserByConditions(String condition);

	/**
	 * �����û���Ϣ
	 * @param user
	 * @return
	 */
	public int save(User user);

	/**
	 * �����û��˺Ų����û���Ϣ
	 * @param account
	 * @return
	 */
	public User getUserByAccount(String account);
}
