package com.esh.dao;

import com.esh.entity.User;

public interface UserDao {

	
	/**
	 * 获取用户的账号信息---待删除
	 * @param condition
	 * @return
	 */
	public User getUserByConditions(String condition);

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
}
