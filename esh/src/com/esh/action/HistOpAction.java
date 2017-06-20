package com.esh.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esh.globle.Constants;
import com.esh.json.form.response.HistCueData;
import com.esh.service.ApuserService;
import com.esh.utils.ControllerUtil;
import com.esh.utils.ERRORUtil;
import com.esh.utils.JSONUtil;

import net.sf.json.JSONArray;

@Component
public class HistOpAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	ApuserService apuserService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type").trim();
		if(type.toLowerCase().equals(Constants.INIT.toLowerCase()))
		{
			//Home.html初始化后，ajax请求立即加载历史理疗穴位信息data:type:init
			getHistCueInfo(request, response);
		}
	}
	
	/**
	 * 获取历史理疗信息
	 * @param request
	 * @param response
	 */
	private void getHistCueInfo(HttpServletRequest request, HttpServletResponse response) {
		int errorCode=Constants.NO_ERROR_EXIST;
		//获取历史理疗数据
		List<HistCueData> cueDatas=new ArrayList<HistCueData>();
		System.out.println("用户id： "+(request.getParameter("userId").trim()));
		errorCode=apuserService.getHistApusersByUserId(cueDatas, Integer.parseInt(request.getParameter("userId").trim()));//(int)request.getSession().getAttribute(Constants.USER_ID)
		//返回历史理疗数据
		JSONArray jsonArray=new JSONArray();
		jsonArray.add(errorCode==Constants.NO_ERROR_EXIST?true:false);
		jsonArray.add(errorCode==Constants.NO_ERROR_EXIST?JSONUtil.listToJson(cueDatas):ERRORUtil.message(errorCode));
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		ControllerUtil.out(response, jsonArray);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
