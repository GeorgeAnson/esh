package com.esh.service;

import com.esh.entity.DetailUser;
import com.esh.entity.User;
import com.esh.json.form.request.SigninForm;
import com.esh.json.form.request.SignupForm;
import com.esh.json.form.request.UserInfoForm;

public interface UserService {

	/**
	 * ����ע����Ϣ
	 * @param signupForm
	 * @return
	 */
	public int save(SignupForm signupForm);

	/**
	 * ����û���¼
	 * @param signinForm
	 * @return
	 */
	public int checkLoginInfo(SigninForm signinForm);

	/**
	 * ���ע����Ϣ
	 * @param signupForm
	 * @return
	 */
	public int check(SignupForm signupForm);
	
	/**
	 * ������߱����û���ϸ��Ϣ
	 * @param detailUser
	 * @return
	 */
	public int saveOrUpdateDetailInfo(DetailUser detailUser);
	
	/**
	 * ���»����û���Ϣ
	 * @param user
	 * @return
	 */
	public int updateBasicInfo(User user);
	
	/**
	 * ��userInfoForm�л�ȡ�����û���Ϣ
	 * @param userInfoForm
	 * @param user
	 * @return
	 */
	public int getBasicUserInfo(UserInfoForm userInfoForm, User user);
	
	
	/**
	 * ��userInfoForm�л�ȡ��ϸ�û���Ϣ
	 * @param userInfoForm
	 * @param detailUser
	 * @return
	 */
	public int getDetailUserInfo(UserInfoForm userInfoForm, DetailUser detailUser);
	
	/**
	 * ���ͼƬ���棬�������ڻ��棬�����
	 * @param user
	 * @param parentPath
	 * @return
	 */
	public int initHeadPicture(User user, String parentPath);
}
