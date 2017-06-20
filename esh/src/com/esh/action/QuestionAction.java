package com.esh.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esh.entity.Question;
import com.esh.globle.Constants;
import com.esh.json.form.request.QuestionForm;
import com.esh.service.QuestionService;
import com.esh.utils.ControllerUtil;
import com.esh.utils.ERRORUtil;
import com.esh.utils.JSONUtil;

import net.sf.json.JSONObject;

@Component
public class QuestionAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	QuestionService questionService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type").trim();
		if(type.toLowerCase().equals(Constants.ADD.toLowerCase()))
		{
			//添加新的问卷调查信息
			saveQuestionData(request, response);
		}
	}
	
	/**
	 * 添加新的问卷调查数据
	 * @param request
	 * @param response
	 */
	private void saveQuestionData(HttpServletRequest request, HttpServletResponse response) {
		int errorCode=Constants.NO_ERROR_EXIST;
		//获取参数
		String jsonString=request.getParameter("question").trim();
		QuestionForm questionForm=(QuestionForm) JSONUtil.jsonToBean(jsonString, QuestionForm.class);
		//转换对象
		Question question=new Question();
		questionService.transferObject(question, questionForm);
		errorCode=questionService.saveQuestion(question);
		//返回详情数据
		JSONObject data=new JSONObject();
		data.element("status", errorCode==Constants.NO_ERROR_EXIST?true:false);
		data.element("msg", errorCode==Constants.NO_ERROR_EXIST?ERRORUtil.message(Constants.INFORMATION_UPDATE_SECCESS):ERRORUtil.message(errorCode));
		data.element("url", errorCode==Constants.NO_ERROR_EXIST?request.getContextPath()+"/selectEquit.html":"");
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		ControllerUtil.out(response, data);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
