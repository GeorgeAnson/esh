package com.esh.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esh.dao.UserDao;
import com.esh.entity.User;
import com.esh.globle.Constants;
import com.esh.json.form.request.SigninForm;
import com.esh.service.UserService;
import com.esh.utils.ControllerUtil;
import com.esh.utils.ERRORUtil;
import com.esh.utils.JSONUtil;
import com.esh.utils.Utils;

import net.sf.json.JSONObject;

@Component
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserService userService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("执行loginAction");
		String jsonString=request.getParameter("signin").trim();
		SigninForm signinForm=(SigninForm) JSONUtil.jsonToBean(jsonString, SigninForm.class);
		System.out.println(signinForm);
		int errorCode=Constants.NO_ERROR_EXIST;
		//判断用户是否存在
		errorCode=userService.checkLoginInfo(signinForm);
		//登录成功，保存用户Id
		if(errorCode==Constants.NO_ERROR_EXIST)
		{
			request.getSession().setAttribute(Constants.USER_ID, signinForm.getUserId());
			Cookie cookie=new Cookie("userId", signinForm.getUserId()+"");
			response.addCookie(cookie);
			//更新用户登录时间
			updateUserBasicInfo(signinForm);
		}
		System.out.println(request.getSession().getAttribute(Constants.USER_ID));
		//返回json：状态，信息，url
		JSONObject data=new JSONObject();
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		data.element("status", errorCode==Constants.NO_ERROR_EXIST?true:false);
		data.element("msg", errorCode==Constants.NO_ERROR_EXIST?"":ERRORUtil.message(errorCode));
		data.element("userId", errorCode==Constants.NO_ERROR_EXIST?signinForm.getUserId():"null");
		data.element("url", errorCode==Constants.NO_ERROR_EXIST?request.getContextPath()+"/home.html":"");
		System.out.println("返回json："+data.toString());
		ControllerUtil.out(response, data);
	}


	/**
	 * 更新用户登录信息
	 * @param signinForm
	 */
	private void updateUserBasicInfo(SigninForm signinForm) {		
		User user=new User();
		user.setUid(signinForm.getUserId());
		user.setUstatus(Constants.STATIC_ACTIVE);
		user.setUlastime(Utils.stringToExactDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		userDao.update(user);
	}
}
