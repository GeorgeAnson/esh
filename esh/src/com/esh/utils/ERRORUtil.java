package com.esh.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.esh.globle.Constants;

public class ERRORUtil {

	
	private static String CODE=null;//���������
	private static String MESSAGE=null;//������Ϣ
	private static Properties PROP=null;
	
	/**
	 * ���������ļ�
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
	 * ���������ļ�
	 */
	private static void loadProperties()
	{
		
		try 
		{
			PROP.load(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()
					+"error.properties"));
		} catch (IOException e)
		{
			System.out.println("����������ļ�����");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * ���ش������Ӧ�Ĵ�����Ϣ
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
	 * ��ȡ��ǰ������Ϣ�Ĵ�����
	 * @return
	 * 		String
	 */
	public static String code()
	{
		return CODE;
	}
	
	
	
	
	
	/**
	 * �ͷŴ�����Ϣ
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
