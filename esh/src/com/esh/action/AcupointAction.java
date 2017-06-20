package com.esh.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esh.entity.Acupoint;
import com.esh.globle.Constants;
import com.esh.json.form.response.AcupointDetail;
import com.esh.service.AcupointService;
import com.esh.utils.ControllerUtil;
import com.esh.utils.ERRORUtil;

import net.sf.json.JSONObject;

@Component
public class AcupointAction extends HttpServlet{

	@Autowired
	AcupointService acupointService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type").trim();
		//��ȡѨλ��ϸҳ��Ϣ
		if(type.toLowerCase().equals(Constants.DETAIL.toLowerCase()))
		{
			getAcupointDetail(request, response);
		}
	}
	
	/**
	 * ��ȡѨλ����ϸ��Ϣ
	 * @param request
	 * @param response
	 */
	private void getAcupointDetail(HttpServletRequest request, HttpServletResponse response) {
		String temp=request.getParameter("acupId").trim();
		int acupId=0;
		if(temp!=null&&!"".equals(temp))
		{
			acupId=Integer.parseInt(temp);
		}
		int errorCode=Constants.NO_ERROR_EXIST;
		//��ȡѨλ��ϸ��Ϣ
		 Acupoint acupoint = acupointService.getAcupointByAcupointId(acupId);
		//�����������
		AcupointDetail acupointDetail=new AcupointDetail();
//		if(acupoint==null||(acupoint!=null&&acupoint.getPicture().getPictureId()==0))
//		{
//			//��Ѩλ�����ڻ�����ͼδ��ȡ
//			errorCode=Constants.INFORMATION_LOAD_DEFEAT;
//		}else
//		{
//			//Ϊ�����ڻ���Ķ�ͼ���ӻ���
//			errorCode=acupointService.initDiseasePicture(acupoint.getPicture(),
//					this.getServletConfig().getServletContext().getRealPath("/"));
//			//��ӷ�������
//			acupointDetail.setAcupointId(acupoint.getApId());
//			acupointDetail.setAcupointName(acupoint.getApName());
//			acupointDetail.setDescription(acupoint.getDesctibe());
//			acupointDetail.setImg(request.getContextPath()+"/image_cache/"+acupoint.getPicture().getPictureName());
//		}
		//���ڴ���Ѩλ��ͼ�����ڵ��������˲������·�ʽ��������Ѩλ��ͼ�����Ĭ�϶�ͼ
		if(acupoint!=null&&acupoint.getApId()!=0)
		{
			//�������Ѩλ��ͼ����Ϊ�����ڻ���Ķ�ͼ���ӻ���
			if(acupoint.getPicture().getPictureId()!=0)
			{
				errorCode=acupointService.initDiseasePicture(acupoint.getPicture(),
						this.getServletConfig().getServletContext().getRealPath("/"));
			}
			//��ӷ�������
			acupointDetail.setAcupointId(acupoint.getApId());
			acupointDetail.setAcupointName(acupoint.getApName());
			acupointDetail.setDescription(acupoint.getDesctibe());
			//���������Ѩλ��ͼ���򷵻�Ĭ�ϼ������ݶ�ͼ��url�����򷵻ض�ͼ�ķ���url
			acupointDetail.setImg(acupoint.getPicture().getPictureId()!=0?request.getContextPath()+"/image_cache/"+acupoint.getPicture().getPictureName():request.getContextPath()+"/assets/images/default.gif");
		}else
		{
			errorCode=Constants.INFORMATION_LOAD_DEFEAT;
		}
		
		//������������
		JSONObject data=new JSONObject();
		data.element("status", errorCode==Constants.NO_ERROR_EXIST?true:false);
		data.element("msg", errorCode==Constants.NO_ERROR_EXIST?acupointDetail:ERRORUtil.message(errorCode));
		data.element("url", errorCode==Constants.NO_ERROR_EXIST?request.getContextPath()+"/treat.html":"");
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		ControllerUtil.out(response, data);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
