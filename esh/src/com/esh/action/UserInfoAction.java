package com.esh.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esh.entity.DetailUser;
import com.esh.entity.User;
import com.esh.globle.Constants;
import com.esh.json.form.BasicUserInfo;
import com.esh.json.form.DetailUserInfo;
import com.esh.json.form.request.UserInfoForm;
import com.esh.service.UserService;
import com.esh.utils.ControllerUtil;
import com.esh.utils.ERRORUtil;
import com.esh.utils.JSONUtil;

import net.sf.json.JSONObject;

@Component
public class UserInfoAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	UserService userService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type").trim();
		if(type.toLowerCase().equals(Constants.UPDATE.toLowerCase()))
		{
			//用户详细信息，若存在则更新，不存在则插入
			saveOrUpdateUserDetailInfo(request, response);
		}
	}
	
	/**
	 * 存在用户详细信息则更新，不存在则插入记录
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	private void saveOrUpdateUserDetailInfo(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		int errorCode=Constants.NO_ERROR_EXIST;
		//获取提交信息 
		String jsonString=request.getParameter("updateUserInfo").trim();
		System.out.println(request.getCharacterEncoding()+" ; "+jsonString);
		UserInfoForm userInfoForm=(UserInfoForm) JSONUtil.jsonToBean(jsonString, UserInfoForm.class);
		BasicUserInfo basicUserInfo=(BasicUserInfo) JSONUtil.jsonToBean(jsonString, BasicUserInfo.class);
		DetailUserInfo detailUserInfo=(DetailUserInfo) JSONUtil.jsonToBean(jsonString, DetailUserInfo.class);
		userInfoForm.setBasicUser(basicUserInfo);
		userInfoForm.setDetailUser(detailUserInfo);
		//获取信息实体
		User user=new User();
		DetailUser detailUser=new DetailUser();
		//获取基本信息
		errorCode=userService.getBasicUserInfo(userInfoForm, user);
		//获取详细信息
		errorCode=userService.getDetailUserInfo(userInfoForm, detailUser);
		//更新基本信息
		errorCode=userService.updateBasicInfo(user);
		//更新图片缓存
		errorCode=userService.initHeadPicture(user, this.getServletConfig().getServletContext().getRealPath("/"));
		//保存或者更新详细用户信息
		errorCode=userService.saveOrUpdateDetailInfo(detailUser);
		//返回更新结果
		JSONObject data=new JSONObject();
		data.element("status", errorCode==Constants.NO_ERROR_EXIST?true:false);
		data.element("msg", errorCode==Constants.NO_ERROR_EXIST?ERRORUtil.message(Constants.INFORMATION_UPDATE_SECCESS):ERRORUtil.message(errorCode));
		data.element("headPic", "image_cache\\"+user.getHpicName());
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		ControllerUtil.out(response, data);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
