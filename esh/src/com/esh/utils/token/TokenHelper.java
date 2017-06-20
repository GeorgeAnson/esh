package com.esh.utils.token;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Administrator
 *token工具类，作为CSRF保护
 */
public class TokenHelper {

	//token命名空间
	public static final String TOKEN_NAMESPACE="esh.tokens";
	//token字段名
	public static final String TOKEN_NAME_FIELD="nbut.esh.name";
	private static final Logger logger=LoggerFactory.getLogger(TokenHelper.class);
	private static final Random random=new Random();
	private static HashMap<String, String> mapCacheClient=new HashMap<String, String>();
	
	public static void setMapCacheClient(HashMap<String, String> mapCacheClient)
	{
		TokenHelper.mapCacheClient=mapCacheClient;
	}
	
	/**
	 * 使用随机字符串作为token名字保存token
	 * @param request
	 * @return
	 */
	public static String setToken(HttpServletRequest request)
	{
		return setToken(request, generateGUID());
	}

	
	/**
	 * 使用给定字符串作为token名字保存token
	 * @param request
	 * @param tokenName
	 * @return
	 */
	private static String setToken(HttpServletRequest request, String tokenName) {
		String token=generateGUID();
		setCacheToken(request, tokenName,token);
		return token;
	}

	/**
	 * 保存token，具有固定的名称和值
	 * @param request
	 * @param tokenName
	 * @param token
	 */
	private static void setCacheToken(HttpServletRequest request, String tokenName, String token) {
		try
		{
			String name=buildTokenCacheAttributeName(tokenName);
			mapCacheClient.put(name, token);
			System.out.println("存储的token值："+mapCacheClient.get(name));
			request.setAttribute(TOKEN_NAME_FIELD, name);  
	        //request.setAttribute(name, token);
		}catch(Exception e)
		{
			logger.error("创建token缓存失败");
			e.printStackTrace();
		}
	}

	/**
	 * 构建token名字
	 * @param tokenName
	 * @return
	 */
	private static String buildTokenCacheAttributeName(String tokenName) {
		return TOKEN_NAMESPACE+"."+tokenName;
	}

	/**
	 * 获取token
	 * @param request
	 * @param tokenName
	 * @return
	 */
	public static String getToken(HttpServletRequest request, String tokenName)  
	{
		if(tokenName==null)
		{
			return null;
		}
		Map<String, String[]> parmas=request.getParameterMap();
		String[] tokens=parmas.get(tokenName);
		String token=null;
		if(tokens==null||tokens.length<1)
		{
			logger.warn("不存在token，token名称："+tokenName);
			return null;
		}
		token=tokens[0];
		return token;
	}
	
	public static String getTokenName(HttpServletRequest request)
	{
		Map<String, String[]> parmas=request.getParameterMap();
		if(!parmas.containsKey(TOKEN_NAME_FIELD))
		{
			logger.warn("不存在token名");
			return null;
		}
		String[] tokenNames=parmas.get(TOKEN_NAME_FIELD);
		String tokenName=null;
		if(tokenNames==null||tokenNames.length<1)
		{
			logger.warn("获取token为null");
			return null;
		}
		tokenName=tokenNames[0];
		return tokenName;
	}
	
	/**
	 * 判断请求流参数token合法性，合法则删除token，使其不再合法
	 * @param request
	 * @return
	 */
	public static boolean validToken(HttpServletRequest request)
	{
		String tokenName=getTokenName(request);
		if(tokenName==null)
		{
			logger.debug("不存在token名，token不合法");
			return false;
		}
		String token=getToken(request, tokenName);
		if(token==null)
		{
			logger.debug("token: "+tokenName+"不合法");
			return false;
		}
		String tokenCacheName=buildTokenCacheAttributeName(tokenName);
		String cacheToken=mapCacheClient.remove(tokenCacheName);
		if(!token.equals(cacheToken))
		{
			logger.warn("请求的token不合法");
			return false;
		}
		return true;
	}
	
	private static String generateGUID() {
        return new BigInteger(165, random).toString(36).toUpperCase();
	}
}
