package com.esh.listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.esh.globle.Constants;



/**
 * 单机登录监听,控制同一用户不会多客户端同时在线
 */
public class OnLineListener implements HttpSessionListener, HttpSessionAttributeListener {
	
	
	
	/**
	 * 不同的会话Session中，Servlet中调用setAttribute保存信息
	 * @event session绑定该属性的事件
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		if(event.getValue()!=null)
		{
			int userId=(int) event.getValue();
			//获取application作用域对象
			ServletContext context = event.getSession().getServletContext();
			if(context.getAttribute(Constants.ONLINE_USERS)==null)
			{
				context.setAttribute(Constants.ONLINE_USERS, new HashMap<Integer, String>());
			}
			@SuppressWarnings("unchecked")
			HashMap<Integer, String> userMap=(HashMap<Integer, String>) context.getAttribute(Constants.ONLINE_USERS);
			userMap.put(userId, event.getSession().getId());
			System.out.println("添加用户： "+userId);
			
			for(Entry<Integer, String> entry:userMap.entrySet())
			{
				System.out.println("在线用户："+entry);
			}
		}
	}

	/**
	 * 移除Session中已存在的属性时触发
	 * @event session绑定该属性的事件
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		int userId=(int) event.getValue();
		Map<Integer, String> userMap=(Map<Integer, String>) event.getSession().getServletContext().getAttribute(Constants.ONLINE_USERS);
		if(userMap!=null)
		{
			userMap.remove(userId);
			System.out.println("移除用户  : "+userId);
		}
		if(userMap!=null)
		{
			for(Entry<Integer, String> entry:userMap.entrySet())
			{
				System.out.println("在线用户："+entry);
			}	
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		System.out.println(getTime() + "  创建session " + event.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		System.out.println(getTime() + " 销毁session " + event.getSession().getId());
	}
	
	
	/**
	 * 获取系统时间
	 * @return
	 */
	private String getTime() {
		long l = System.currentTimeMillis();
		// new日期对象
		Date date = new Date(l);
		// 转换日期输出格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}
	
}
