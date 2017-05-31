package com.esh.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esh.globle.Constants;
import com.esh.json.form.CueDevParma;
import com.esh.service.CueDevService;
import com.esh.utils.ControllerUtil;
import com.esh.utils.ERRORUtil;
import com.esh.utils.JSONUtil;

import net.sf.json.JSONObject;

/**
 * 获取穴位理疗参数
 * @author Administrator
 *
 */
@Component
public class CueDevAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	CueDevService cueDevService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type").trim();
		
		if(type.toLowerCase().equals(Constants.INIT.toLowerCase()))
		{
			//获取治疗的参数值
			getCueDevParma(request, response);
		}else if(type.toLowerCase().equals(Constants.UPDATE.toLowerCase()))
		{
			//保存或更新治疗参数
			saveOrUpdateDevParma(request, response);
		}
	}
	
	/**
	 * 保存或者更新设备操作参数
	 * @param request
	 * @param response
	 */
	private void saveOrUpdateDevParma(HttpServletRequest request, HttpServletResponse response) {
		String jsonString=request.getParameter("upLoadOpValues").trim();
		CueDevParma cueDevParma=(CueDevParma) JSONUtil.jsonToBean(jsonString, CueDevParma.class);
		int errorCode=Constants.NO_ERROR_EXIST;
		//保存或更新设备参数
		errorCode=cueDevService.saveOrUpdateCueDevParma(cueDevParma);
		//返回处理结果
		JSONObject data=new JSONObject();
		data.element("status", errorCode==Constants.NO_ERROR_EXIST?true:false);
		data.element("msg", errorCode==Constants.NO_ERROR_EXIST?ERRORUtil.message(Constants.INFORMATION_UPDATE_SECCESS):ERRORUtil.message(errorCode));
		data.element("url", errorCode==Constants.NO_ERROR_EXIST?request.getContextPath()+"/questionnaire.html":"");
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		ControllerUtil.out(response, data);
	}

	/**
	 * 获取穴位治疗参数信息
	 * 首先从apuser表中获取，若不存则从apoint表中获取
	 * @param request
	 * @param response
	 */
	private void getCueDevParma(HttpServletRequest request, HttpServletResponse response) {
		String jsonString=request.getParameter("getAcupOpInfo").trim();
		CueDevParma cueDevParma=(CueDevParma) JSONUtil.jsonToBean(jsonString, CueDevParma.class);
		int errorCode=Constants.NO_ERROR_EXIST;
		//获取参数
		errorCode=cueDevService.getCueDevParma(cueDevParma);
		//返回设备参数
		JSONObject data=new JSONObject();
		data.element("status", errorCode==Constants.NO_ERROR_EXIST?true:false);
		data.element("msg", errorCode==Constants.NO_ERROR_EXIST?cueDevParma:ERRORUtil.message(errorCode));
		data.element("url", request.getContextPath()+"/treat.html");
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		ControllerUtil.out(response, data);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
