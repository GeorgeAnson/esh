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
import com.esh.json.form.SuggestForCustomer;
import com.esh.json.form.response.SuggestBean;
import com.esh.service.SuggestionForCusService;
import com.esh.utils.ControllerUtil;
import com.esh.utils.ERRORUtil;
import com.esh.utils.JSONUtil;

import net.sf.json.JSONArray;

@Component
public class SuggestAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	SuggestionForCusService suggestionForCusService; 

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type").trim();
		//��ʼ���Ƽ��б�
		if(type.toLowerCase().equals(Constants.INIT.toLowerCase()))
		{
			getSuggestionList(request, response);
		}
	}
	
	/**
	 * ͨ����֢��ȡ�Ƽ��б�
	 * @param request
	 * @param response
	 */
	private void getSuggestionList(HttpServletRequest request, HttpServletResponse response) {
		String jsonString=request.getParameter("getAcupList").trim();
		SuggestForCustomer suggestForCustomer=(SuggestForCustomer) JSONUtil.jsonToBean(jsonString, SuggestForCustomer.class);
		List<SuggestForCustomer> suggestForCustomers=new ArrayList<>();
		int errorCode=Constants.NO_ERROR_EXIST;
//		//ƥ���Ƽ���Ϣ
		errorCode=suggestionForCusService.getSuggestionsAcupForCustomer(suggestForCustomers, suggestForCustomer);
//		//��ȡ�Ƽ���Ϣ
		List<SuggestBean> suggestBeans=new ArrayList<>();
		for(SuggestForCustomer sfc:suggestForCustomers)
		{
			suggestBeans.add(sfc.getSuggestBean());
		}
		//�����Ƽ��б�
		JSONArray jsonArray=new JSONArray();
		jsonArray.add(errorCode==Constants.NO_ERROR_EXIST?true:false);
		jsonArray.add(errorCode==Constants.NO_ERROR_EXIST?suggestBeans:ERRORUtil.message(errorCode));
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		ControllerUtil.out(response, jsonArray);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
