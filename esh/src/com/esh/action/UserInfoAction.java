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
import com.esh.json.form.response.UserProfile;
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
		if(type.toLowerCase().equals(Constants.INIT.toLowerCase()))
		{
			//��ȡ�û�ҳ���ʼ����Ϣ
			getUserProfile(request, response);
		}
		
		if(type.toLowerCase().equals(Constants.UPDATE.toLowerCase()))
		{
			//�û���ϸ��Ϣ������������£������������
			saveOrUpdateUserDetailInfo(request, response);
		}
	}
	
	/**
	 * ��ʼ���û����˽��棬��ȡ�û���Ϣ�ſ�
	 * @param request
	 * @param response
	 */
	private void getUserProfile(HttpServletRequest request, HttpServletResponse response) {
		int errorCode=Constants.NO_ERROR_EXIST;
		//��ȡ�ύ��Ϣ
		String temp=request.getParameter("userId").trim();
		int userId=Integer.parseInt(temp);
		//��ȡ�û���Ҫ��Ϣ
		UserProfile userProfile=new UserProfile();
		userProfile.setUserId(userId);
		errorCode=userService.getUserProfile(userProfile);
		//����ͼƬ����
		errorCode=userService.initHeadPicture(userProfile.getUhdata(), userProfile.getUhpic(), this.getServletConfig().getServletContext().getRealPath("/"));
		userProfile.setUhdata(null);//���践�ص���Ϣ
		userProfile.setUhpic(request.getContextPath()+"/image_cache/"+userProfile.getUhpic());
		//������Ϣ
		JSONObject data=new JSONObject();
		data.element("status", errorCode==Constants.NO_ERROR_EXIST?true:false);
		data.element("msg", errorCode==Constants.NO_ERROR_EXIST?userProfile:ERRORUtil.message(errorCode));
		data.element("url", request.getContextPath()+"/userMenu.html");
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		ControllerUtil.out(response, data);
	}

	/**
	 * �����û���ϸ��Ϣ����£�������������¼
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	private void saveOrUpdateUserDetailInfo(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		int errorCode=Constants.NO_ERROR_EXIST;
		//��ȡ�ύ��Ϣ 
		String jsonString=request.getParameter("updateUserInfo").trim();
		System.out.println(request.getCharacterEncoding()+" ; "+jsonString);
		UserInfoForm userInfoForm=(UserInfoForm) JSONUtil.jsonToBean(jsonString, UserInfoForm.class);
		BasicUserInfo basicUserInfo=(BasicUserInfo) JSONUtil.jsonToBean(jsonString, BasicUserInfo.class);
		DetailUserInfo detailUserInfo=(DetailUserInfo) JSONUtil.jsonToBean(jsonString, DetailUserInfo.class);
		userInfoForm.setBasicUser(basicUserInfo);
		userInfoForm.setDetailUser(detailUserInfo);
		//��ȡ��Ϣʵ��
		User user=new User();
		DetailUser detailUser=new DetailUser();
		//��ȡ������Ϣ
		errorCode=userService.getBasicUserInfo(userInfoForm, user);
		//��ȡ��ϸ��Ϣ
		errorCode=userService.getDetailUserInfo(userInfoForm, detailUser);
		//���»�����Ϣ
		errorCode=userService.updateBasicInfo(user);
		//����ͼƬ����
		errorCode=userService.initHeadPicture(user.getHpic(), user.getHpicName(), this.getServletConfig().getServletContext().getRealPath("/"));
		//������߸�����ϸ�û���Ϣ
		errorCode=userService.saveOrUpdateDetailInfo(detailUser);
		//���ظ��½��
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
