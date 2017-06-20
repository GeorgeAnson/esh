package com.esh.globle;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * ȫ�ֻ���
 */
public class Globle {
	
	private static byte[] defaultHPicCache = null;
	//�û�ͷ��ͼƬ����
	private static HashMap<Integer, byte[]> heaPicCache=new HashMap<Integer, byte[]>();
	
	
	/**
	 * �ϴ�һ��ͼƬ�������򸲸ǣ������������
	 * @param userId
	 * 		�û�id
	 * @param pic
	 * 		ͼƬ����
	 */
	public static void uploadPic(Integer userId, byte[] pic)
	{
		if(heaPicCache.containsKey(userId))
		{
			//�����ڣ����滻�����Է��صľ�ͼƬ����
			heaPicCache.replace(userId, pic);
		}else
		{
			//�������ڣ������
			heaPicCache.put(userId, pic);
		}
	}
	
	/**
	 * �ж��û���ͼƬ�����Ƿ����
	 * @param userId
	 * 		�û�id
	 * @return
	 */
	public static boolean isExistHeaPic(Integer userId)
	{
		boolean flag=false;
		if(heaPicCache!=null&&!heaPicCache.isEmpty())
		{
			flag=heaPicCache.containsKey(userId);
		}
		return flag;
	}
	
	/**
	 * ��ȡͼƬ
	 * @param userId
	 * @return
	 */
	public static byte[] getHeaPic(Integer userId)
	{
		if(heaPicCache==null)
		{
			return null;
		}else
		{
			return heaPicCache.get(userId);
		}
	}
	
	/**
	 * ���ͼƬ
	 * @param userId
	 */
	public static void clrHeaPic(Integer userId)
	{
		if(heaPicCache!=null&&!heaPicCache.isEmpty())
		{
			heaPicCache.remove(userId);
		}
	}
	
	public static HashMap<Integer, byte[]> getHeaPicCache()
	{
		return heaPicCache;
	}
	
	public static void setHeaPicCache(HashMap<Integer, byte[]> heaPicCache)
	{
		Globle.heaPicCache=heaPicCache;
	}
	
	/**
	 * ��ȡĬ���û�ͷ��
	 * @return
	 */
	public static byte[] getDefaultHpic()
	{
		if(defaultHPicCache==null)
		{
	        BufferedImage bi=null;
	        try {
	        	URL url=new URL("http://127.0.0.1/esh/assets/images/default.png");
				bi = ImageIO.read(url);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
			    ImageIO.write(bi, "png", baos);
			    defaultHPicCache = baos.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return defaultHPicCache;
	}
}
