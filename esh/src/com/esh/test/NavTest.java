package com.esh.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.esh.globle.Constants;
import com.esh.json.form.DiseaseNav;
import com.esh.service.NavService;
import com.esh.service.impl.NavServiceImpl;
import com.esh.utils.ERRORUtil;

import net.sf.json.JSONObject;

public class NavTest {

	NavService navService=new NavServiceImpl();
	
	Logger logger=LoggerFactory.getLogger(getClass().getName());
	
	@Test
	public void init()
	{
		int errorCode=Constants.NO_ERROR_EXIST;
		DiseaseNav diseaseNav=new DiseaseNav();
		errorCode=navService.createNavs(diseaseNav);
		JSONObject data=new JSONObject();
		data.element("status", errorCode==Constants.NO_ERROR_EXIST?true:false);
		data.element("msg", errorCode==Constants.NO_ERROR_EXIST?diseaseNav.getDiseaseNavs():ERRORUtil.message(errorCode));
		logger.info("∂¡»°≥…π¶"+data.toString());
	}
}
