package com.esh.globle;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * 全局缓冲
 */
public class Globle {
	
	private static byte[] defaultHPicCache = null;
	//用户头像图片缓冲
	private static HashMap<Integer, byte[]> heaPicCache=new HashMap<Integer, byte[]>();
	
	
	/**
	 * 上传一张图片，存在则覆盖，不存在则添加
	 * @param userId
	 * 		用户id
	 * @param pic
	 * 		图片数据
	 */
	public static void uploadPic(Integer userId, byte[] pic)
	{
		if(heaPicCache.containsKey(userId))
		{
			//若存在，则替换，忽略返回的旧图片数据
			heaPicCache.replace(userId, pic);
		}else
		{
			//若不存在，则添加
			heaPicCache.put(userId, pic);
		}
	}
	
	/**
	 * 判断用户的图片缓存是否存在
	 * @param userId
	 * 		用户id
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
	 * 获取图片
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
	 * 清除图片
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
	 * 获取默认用户头像
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
