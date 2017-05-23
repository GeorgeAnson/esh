package com.esh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esh.dao.UserDao;
import com.esh.entity.User;
import com.esh.globle.Constants;
import com.esh.json.form.request.SigninForm;
import com.esh.json.form.request.SignupForm;
import com.esh.service.UserService;
import com.esh.utils.Utils;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public int save(SignupForm signupForm) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setUname(signupForm.getAccount());
		user.setPwd(signupForm.getPassword());
		user.setUstatus(Constants.STATIC_ACTIVE);
		int userId = userDao.save(user);
		return userId;
	}

	@Override
	public int check(SignupForm signupForm) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
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
			//登录成功，为了在session中保存userId，绑定sessionId
			signinForm.setUserId(user.getUid());
		}
		return errorCode;
	}
}
