package com.esh.dao;

import com.esh.entity.DetailUser;
import com.esh.entity.User;

public interface UserDao {

	
	/**
	 * ��ȡ�û����˺���Ϣ
	 * @param userId
	 * @return
	 */
	public User getUserByUserId(int userId);

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

	/**
	 * �����û���Ϣ
	 * @param user
	 * @return
	 */
	public int update(User user);

	/**
	 * ������߸����û���ϸ��Ϣ
	 * @param detailUser
	 * @return
	 */
	public int saveOrUpdateUserDetailInfo(DetailUser detailUser);
}
