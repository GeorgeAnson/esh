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
		//获取穴位详细页信息
		if(type.toLowerCase().equals(Constants.DETAIL.toLowerCase()))
		{
			getAcupointDetail(request, response);
		}
	}
	
	/**
	 * 获取穴位的详细信息
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
		//获取穴位详细信息
		 Acupoint acupoint = acupointService.getAcupointByAcupointId(acupId);
		//组合详情数据
		AcupointDetail acupointDetail=new AcupointDetail();
//		if(acupoint==null||(acupoint!=null&&acupoint.getPicture().getPictureId()==0))
//		{
//			//若穴位不存在或者配图未获取
//			errorCode=Constants.INFORMATION_LOAD_DEFEAT;
//		}else
//		{
//			//为不存在缓存的动图增加缓存
//			errorCode=acupointService.initDiseasePicture(acupoint.getPicture(),
//					this.getServletConfig().getServletContext().getRealPath("/"));
//			//添加返回数据
//			acupointDetail.setAcupointId(acupoint.getApId());
//			acupointDetail.setAcupointName(acupoint.getApName());
//			acupointDetail.setDescription(acupoint.getDesctibe());
//			acupointDetail.setImg(request.getContextPath()+"/image_cache/"+acupoint.getPicture().getPictureName());
//		}
		//由于存在穴位动图不存在的情况，因此采用如下方式，不存在穴位动图则加载默认动图
		if(acupoint!=null&&acupoint.getApId()!=0)
		{
			//如果存在穴位动图，则为不存在缓存的动图增加缓存
			if(acupoint.getPicture().getPictureId()!=0)
			{
				errorCode=acupointService.initDiseasePicture(acupoint.getPicture(),
						this.getServletConfig().getServletContext().getRealPath("/"));
			}
			//添加返回数据
			acupointDetail.setAcupointId(acupoint.getApId());
			acupointDetail.setAcupointName(acupoint.getApName());
			acupointDetail.setDescription(acupoint.getDesctibe());
			//如果不存在穴位动图，则返回默认加载数据动图的url，否则返回动图的访问url
			acupointDetail.setImg(acupoint.getPicture().getPictureId()!=0?request.getContextPath()+"/image_cache/"+acupoint.getPicture().getPictureName():request.getContextPath()+"/assets/images/default.gif");
		}else
		{
			errorCode=Constants.INFORMATION_LOAD_DEFEAT;
		}
		
		//返回详情数据
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
