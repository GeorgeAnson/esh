package com.esh.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.esh.globle.Constants;

public class ERRORUtil {

	
	private static String CODE=null;//错误类别码
	private static String MESSAGE=null;//错误信息
	private static Properties PROP=null;
	
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
			System.out.println("载入错误码文件出错");
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
		
		MESSAGE=PROP.getProperty(CODE);
		if(MESSAGE==null)
		{
			MESSAGE=PROP.getProperty((Constants.UNKNOWN_REGISTER_ERROR+"").trim());
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
