package com.esh.dao;

import com.esh.entity.DetailUser;
import com.esh.entity.User;

public interface UserDao {

	
	/**
	 * 获取用户的账号信息
	 * @param userId
	 * @return
	 */
	public User getUserByUserId(int userId);

	/**
	 * 保存用户信息
	 * @param user
	 * @return
	 */
	public int save(User user);

	/**
	 * 根据用户账号查找用户信息
	 * @param account
	 * @return
	 */
	public User getUserByAccount(String account);

	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int update(User user);

	/**
	 * 保存或者更新用户详细信息
	 * @param detailUser
	 * @return
	 */
	public int saveOrUpdateUserDetailInfo(DetailUser detailUser);
}
