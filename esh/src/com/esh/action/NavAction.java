package com.esh.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esh.globle.Constants;
import com.esh.json.form.DiseaseNav;
import com.esh.service.NavService;
import com.esh.utils.ControllerUtil;
import com.esh.utils.ERRORUtil;

import net.sf.json.JSONArray;

@Component
public class NavAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	NavService navService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type").trim();
		//String type=(String) JSONUtil.jsonToBean(jsonString, String.class);
		//页面加载完成，创建菜单
		if(type.toLowerCase().equals(Constants.INIT.toLowerCase()))
		{
			InitNav(request, response);
		}
	}
	
	/**
	 * 创建二级菜单
	 * @param request
	 * @param response
	 */
	private void InitNav(HttpServletRequest request, HttpServletResponse response) {
		int errorCode=Constants.NO_ERROR_EXIST;
		DiseaseNav diseaseNav=new DiseaseNav();
		errorCode=navService.createNavs(diseaseNav);
		JSONArray jsonArray=new JSONArray();
		jsonArray.add(errorCode==Constants.NO_ERROR_EXIST?true:false);
		jsonArray.add(errorCode==Constants.NO_ERROR_EXIST?request.getContextPath()+"/treat.html":request.getContextPath()+"/selectEquit.html");
		jsonArray.add(errorCode==Constants.NO_ERROR_EXIST?diseaseNav.getDiseaseNavs().entrySet():ERRORUtil.message(errorCode));
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		ControllerUtil.out(response, jsonArray);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
