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
 *token�����࣬��ΪCSRF����
 */
public class TokenHelper {

	//token�����ռ�
	public static final String TOKEN_NAMESPACE="esh.tokens";
	//token�ֶ���
	public static final String TOKEN_NAME_FIELD="nbut.esh.name";
	private static final Logger logger=LoggerFactory.getLogger(TokenHelper.class);
	private static final Random random=new Random();
	private static HashMap<String, String> mapCacheClient=new HashMap<String, String>();
	
	public static void setMapCacheClient(HashMap<String, String> mapCacheClient)
	{
		TokenHelper.mapCacheClient=mapCacheClient;
	}
	
	/**
	 * ʹ������ַ�����Ϊtoken���ֱ���token
	 * @param request
	 * @return
	 */
	public static String setToken(HttpServletRequest request)
	{
		return setToken(request, generateGUID());
	}

	
	/**
	 * ʹ�ø����ַ�����Ϊtoken���ֱ���token
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
	 * ����token�����й̶������ƺ�ֵ
	 * @param request
	 * @param tokenName
	 * @param token
	 */
	private static void setCacheToken(HttpServletRequest request, String tokenName, String token) {
		try
		{
			String name=buildTokenCacheAttributeName(tokenName);
			mapCacheClient.put(name, token);
			System.out.println("�洢��tokenֵ��"+mapCacheClient.get(name));
			request.setAttribute(TOKEN_NAME_FIELD, name);  
	        //request.setAttribute(name, token);
		}catch(Exception e)
		{
			logger.error("����token����ʧ��");
			e.printStackTrace();
		}
	}

	/**
	 * ����token����
	 * @param tokenName
	 * @return
	 */
	private static String buildTokenCacheAttributeName(String tokenName) {
		return TOKEN_NAMESPACE+"."+tokenName;
	}

	/**
	 * ��ȡtoken
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
			logger.warn("������token��token���ƣ�"+tokenName);
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
			logger.warn("������token��");
			return null;
		}
		String[] tokenNames=parmas.get(TOKEN_NAME_FIELD);
		String tokenName=null;
		if(tokenNames==null||tokenNames.length<1)
		{
			logger.warn("��ȡtokenΪnull");
			return null;
		}
		tokenName=tokenNames[0];
		return tokenName;
	}
	
	/**
	 * �ж�����������token�Ϸ��ԣ��Ϸ���ɾ��token��ʹ�䲻�ٺϷ�
	 * @param request
	 * @return
	 */
	public static boolean validToken(HttpServletRequest request)
	{
		String tokenName=getTokenName(request);
		if(tokenName==null)
		{
			logger.debug("������token����token���Ϸ�");
			return false;
		}
		String token=getToken(request, tokenName);
		if(token==null)
		{
			logger.debug("token: "+tokenName+"���Ϸ�");
			return false;
		}
		String tokenCacheName=buildTokenCacheAttributeName(tokenName);
		String cacheToken=mapCacheClient.remove(tokenCacheName);
		if(!token.equals(cacheToken))
		{
			logger.warn("�����token���Ϸ�");
			return false;
		}
		return true;
	}
	
	private static String generateGUID() {
        return new BigInteger(165, random).toString(36).toUpperCase();
	}
}
