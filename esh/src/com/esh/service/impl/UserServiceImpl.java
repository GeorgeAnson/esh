package com.esh.service.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esh.dao.UserDao;
import com.esh.entity.DetailUser;
import com.esh.entity.User;
import com.esh.globle.Constants;
import com.esh.json.form.request.SigninForm;
import com.esh.json.form.request.SignupForm;
import com.esh.json.form.request.UserInfoForm;
import com.esh.service.UserService;
import com.esh.utils.Utils;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	Logger logger=LoggerFactory.getLogger(getClass().getName());
	
	@Override
	public int save(SignupForm signupForm) {
		User user=new User();
		user.setUname(signupForm.getAccount());
		user.setPwd(signupForm.getPassword());
		user.setUstatus(Constants.STATIC_ACTIVE);
		int userId = userDao.save(user);
		return userId;
	}

	@Override
	public int check(SignupForm signupForm) {
		
		int errorCode=Constants.NO_ERROR_EXIST;
		
		errorCode=Utils.isValideAccount(signupForm.getAccount());
		
		if(errorCode!=Constants.NO_ERROR_EXIST)
		{
			return errorCode;
		}else
		{
			User user=userDao.getUserByAccount(signupForm.getAccount());
			if(user!=null)
			{
				errorCode = Constants.USER_ACCOUNT_EXIST;
			}
		}
		return errorCode;
	}

	@Override
	public int checkLoginInfo(SigninForm signinForm) {
		int errorCode=Constants.NO_ERROR_EXIST;
		User user=userDao.getUserByAccount(signinForm.getAccount());
		System.out.println(user);
		if(user==null)
		{
			errorCode=Constants.USER_ACCPUNT_NOT_EXIST;
		}else if(user!=null&&!user.getPwd().equals(signinForm.getPassword()))
		{
			errorCode=Constants.USER_LOGIN_PWD_ERROR;
		}else
		{
			//��¼�ɹ���Ϊ����session�б���userId����sessionId
			signinForm.setUserId(user.getUid());
		}
		return errorCode;
	}

	@Override
	public int saveOrUpdateDetailInfo(DetailUser detailUser) {
		int errorCode=Constants.NO_ERROR_EXIST;
		errorCode=userDao.saveOrUpdateUserDetailInfo(detailUser);
		logger.info(errorCode==Constants.NO_ERROR_EXIST?"������߸����û���ϸ��Ϣ�ɹ�":"������߸����û�������Ϣʧ�ܣ�λ�ã�"+this.getClass().getName());
		return errorCode;
	}

	@Override
	public int updateBasicInfo(User user) {
		int errorCode=Constants.NO_ERROR_EXIST;
		errorCode=userDao.update(user);
		logger.info(errorCode==Constants.NO_ERROR_EXIST?"�û�������Ϣ���³ɹ�":"�û�������Ϣ����ʧ�ܣ�λ�ã�"+this.getClass().getName());
		return errorCode;
	}

	@Override
	public int getBasicUserInfo(UserInfoForm userInfoForm, User user) {
		int errorCode=Constants.NO_ERROR_EXIST;
		if(userInfoForm==null)
		{
			errorCode=Constants.INFORMATION_NOT_EXIST;
		}else
		{
			user.setUid(userInfoForm.getUserId());
			user.setHpic(userInfoForm.getBasicUser().getHpic());
			user.setHpicName(userInfoForm.getBasicUser().getHpicName());
			user.setUemail(userInfoForm.getBasicUser().getUemail());
			user.setUname(userInfoForm.getBasicUser().getUname());
			user.setUphone(userInfoForm.getBasicUser().getUphone());
			user.setUstatus(Constants.STATIC_ACTIVE);
		}
		return errorCode;
	}

	@Override
	public int getDetailUserInfo(UserInfoForm userInfoForm, DetailUser detailUser) {
		int errorCode=Constants.NO_ERROR_EXIST;
		if(userInfoForm==null)
		{
			errorCode=Constants.INFORMATION_NOT_EXIST;
		}else
		{
			detailUser.setUid(userInfoForm.getUserId());
			detailUser.setDuAddress(userInfoForm.getDetailUser().getDuAddress());
			detailUser.setDuAge(userInfoForm.getDetailUser().getDuAge());
			detailUser.setDuAllergy(userInfoForm.getDetailUser().getDuAllergy());
			detailUser.setDuEmerCall(userInfoForm.getDetailUser().getDuEmerCall());
			detailUser.setDuGender(userInfoForm.getDetailUser().getDuGender());
			detailUser.setDuHeight(userInfoForm.getDetailUser().getDuHeight());
			detailUser.setDuMediHist(userInfoForm.getDetailUser().getDuMediHist());
			detailUser.setDuProfession(userInfoForm.getDetailUser().getDuProfession());
			detailUser.setDuWeight(userInfoForm.getDetailUser().getDuWeight());
		}
		return errorCode;
	}

	@Override
	public int initHeadPicture(User user, String parentPath) {
		int errorCode=Constants.NO_ERROR_EXIST;
		if(user.getHpic()!=null)
		{
			String path=parentPath+"image_cache\\"+user.getHpicName();
			//��·����Ӧ���ļ������ڣ��򴴽��ļ�
			if(!new File(path).exists())
			{
				if(!Utils.getFile(user.getHpic(), path))
				{
					errorCode=Constants.INFORMATION_LOAD_DEFEAT;
					logger.info("��ͼ����ʧ�ܣ�λ����Ϣ��"+this.getClass().getName());
				}else
				{
					logger.info("���ͼƬ����ɹ�,ͼƬ���ƣ�"+user.getHpicName());
				}
			}
		}
		return errorCode;
	}
}
