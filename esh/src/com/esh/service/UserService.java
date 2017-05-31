package com.esh.service;

import com.esh.entity.DetailUser;
import com.esh.entity.User;
import com.esh.json.form.request.SigninForm;
import com.esh.json.form.request.SignupForm;
import com.esh.json.form.request.UserInfoForm;

public interface UserService {

	/**
	 * 保存注册信息
	 * @param signupForm
	 * @return
	 */
	public int save(SignupForm signupForm);

	/**
	 * 检查用户登录
	 * @param signinForm
	 * @return
	 */
	public int checkLoginInfo(SigninForm signinForm);

	/**
	 * 检查注册信息
	 * @param signupForm
	 * @return
	 */
	public int check(SignupForm signupForm);
	
	/**
	 * 插入或者保存用户详细信息
	 * @param detailUser
	 * @return
	 */
	public int saveOrUpdateDetailInfo(DetailUser detailUser);
	
	/**
	 * 更新基本用户信息
	 * @param user
	 * @return
	 */
	public int updateBasicInfo(User user);
	
	/**
	 * 从userInfoForm中获取基本用户信息
	 * @param userInfoForm
	 * @param user
	 * @return
	 */
	public int getBasicUserInfo(UserInfoForm userInfoForm, User user);
	
	
	/**
	 * 从userInfoForm中获取详细用户信息
	 * @param userInfoForm
	 * @param detailUser
	 * @return
	 */
	public int getDetailUserInfo(UserInfoForm userInfoForm, DetailUser detailUser);
	
	/**
	 * 检查图片缓存，若不存在缓存，则更新
	 * @param user
	 * @param parentPath
	 * @return
	 */
	public int initHeadPicture(User user, String parentPath);
}
