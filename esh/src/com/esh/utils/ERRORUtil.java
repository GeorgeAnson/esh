package com.esh.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esh.globle.Constants;

public class ERRORUtil {

	
	private static String CODE=null;//错误类别码
	private static String MESSAGE=null;//错误信息
	private static Properties PROP=null;
	
	static Logger logger=LoggerFactory.getLogger(Constants.ERROR_CLASS_LOADER.trim());
	
	/**
	 * 加载配置文件
	 */
	static
	{
		if(PROP==null)
		{
			PROP=new Properties();;
		}
		loadProperties();
	}
	
	
	
	/**
	 * 载入配置文件
	 */
	private static void loadProperties()
	{
		
		try 
		{
			PROP.load(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()
					+"error.properties"));
		} catch (IOException e)
		{
			logger.info("------------------载入错误码文件出错------------------");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * 返回错误码对应的错误信息
	 * @param code
	 * 		int
	 * @return
	 * 		String
	 */
	public static String message(int code)
	{
		CODE=new String(code+"");
		
		String temp=PROP.getProperty(CODE);
		if(temp==null)
		{
			temp=PROP.getProperty((Constants.UNKNOWN_REGISTER_ERROR+"").trim());
		}
		try {
			MESSAGE=new String(temp.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.info("----------------------错误信息转码错误----------------------------");
			e.printStackTrace();
		}
		return MESSAGE;
	}
	
	
	
	
	
	/**
	 * 获取当前错误信息的错误码
	 * @return
	 * 		String
	 */
	public static String code()
	{
		return CODE;
	}
	
	
	
	
	
	/**
	 * 释放错误信息
	 */
	public void clear()
	{
		if(CODE!=null)
		{
			CODE=null;
		}
		if(MESSAGE!=null)
		{
			MESSAGE=null;
		}
	}
	
}
