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
 * ��ȡѨλ���Ʋ���
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
			//��ȡ���ƵĲ���ֵ
			getCueDevParma(request, response);
		}else if(type.toLowerCase().equals(Constants.UPDATE.toLowerCase()))
		{
			//�����������Ʋ���
			saveOrUpdateDevParma(request, response);
		}
	}
	
	/**
	 * ������߸����豸��������
	 * @param request
	 * @param response
	 */
	private void saveOrUpdateDevParma(HttpServletRequest request, HttpServletResponse response) {
		String jsonString=request.getParameter("upLoadOpValues").trim();
		CueDevParma cueDevParma=(CueDevParma) JSONUtil.jsonToBean(jsonString, CueDevParma.class);
		int errorCode=Constants.NO_ERROR_EXIST;
		//���������豸����
		errorCode=cueDevService.saveOrUpdateCueDevParma(cueDevParma);
		//���ش�����
		JSONObject data=new JSONObject();
		data.element("status", errorCode==Constants.NO_ERROR_EXIST?true:false);
		data.element("msg", errorCode==Constants.NO_ERROR_EXIST?ERRORUtil.message(Constants.INFORMATION_UPDATE_SECCESS):ERRORUtil.message(errorCode));
		data.element("url", errorCode==Constants.NO_ERROR_EXIST?request.getContextPath()+"/questionnaire.html":"");
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		ControllerUtil.out(response, data);
	}

	/**
	 * ��ȡѨλ���Ʋ�����Ϣ
	 * ���ȴ�apuser���л�ȡ�����������apoint���л�ȡ
	 * @param request
	 * @param response
	 */
	private void getCueDevParma(HttpServletRequest request, HttpServletResponse response) {
		String jsonString=request.getParameter("getAcupOpInfo").trim();
		CueDevParma cueDevParma=(CueDevParma) JSONUtil.jsonToBean(jsonString, CueDevParma.class);
		int errorCode=Constants.NO_ERROR_EXIST;
		//��ȡ����
		errorCode=cueDevService.getCueDevParma(cueDevParma);
		//�����豸����
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
