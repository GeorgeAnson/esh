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
		// TODO Auto-generated method stub
		String jsonString=request.getParameter("signup").trim();
		SignupForm signupForm=(SignupForm) JSONUtil.jsonToBean(jsonString, SignupForm.class);
		
		System.out.println(signupForm);
		JSONObject data=new JSONObject();
		int errorCode=Constants.NO_ERROR_EXIST;
		errorCode = userService.check(signupForm);
		if(errorCode!=Constants.NO_ERROR_EXIST)
		{
			data.element("status", false);
			data.element("msg", ERRORUtil.message(errorCode));
			ControllerUtil.out(response, data);
		}else
		{
			errorCode=userService.save(signupForm);
			System.out.println("errorCode : "+errorCode);
			data.element("status", errorCode==Constants.NO_ERROR_EXIST?false:true);
			data.element("url", request.getContextPath()+"/signin.html");
			data.element("msg", errorCode!=Constants.NO_ERROR_EXIST?ERRORUtil.message(errorCode):ERRORUtil.message(Constants.REGISTER_SUCCESS));
			ControllerUtil.out(response, data);
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
