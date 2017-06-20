package com.esh.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esh.globle.Constants;
import com.esh.json.form.request.SignupForm;
import com.esh.service.UserService;
import com.esh.utils.ControllerUtil;
import com.esh.utils.ERRORUtil;
import com.esh.utils.JSONUtil;

import net.sf.json.JSONObject;

@Component
public class RegisterAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	UserService userService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonString=request.getParameter("signup").trim();
		SignupForm signupForm=(SignupForm) JSONUtil.jsonToBean(jsonString, SignupForm.class);
		//注册
		System.out.println(signupForm);
		JSONObject data=new JSONObject();
		int errorCode=Constants.NO_ERROR_EXIST;
		//检查提交的信息是否合法
		errorCode = userService.check(signupForm);
		if(errorCode==Constants.NO_ERROR_EXIST)
		{
			//保存用户信息
			errorCode=userService.save(signupForm);
		}
		//返回数据
		data.element("status", errorCode==Constants.NO_ERROR_EXIST?true:false);
		data.element("url", errorCode==Constants.NO_ERROR_EXIST?request.getContextPath()+"/signin.html":request.getContextPath()+"/signupSec.html");
		data.element("msg", errorCode==Constants.NO_ERROR_EXIST?ERRORUtil.message(Constants.REGISTER_SUCCESS):ERRORUtil.message(errorCode));
		ControllerUtil.out(response, data);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
