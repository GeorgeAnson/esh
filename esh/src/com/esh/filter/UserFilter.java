package com.esh.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * check user login status
 */
public class UserFilter implements Filter {

	// ok url
	private String pass = null;
	// bad url
	private String error_url = null;
	// save the users' status string
	private String userConstants = null;
	//无需检测的页面
	private String initConfig=null;
	/**
	 * to get parma from config file
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		pass = config.getInitParameter("pass").trim();
		error_url = config.getInitParameter("error_url").trim();
		userConstants = config.getInitParameter("userConstants").trim();
		initConfig=config.getInitParameter("init_config").trim();
		System.out.println(" "+error_url+" ; "+userConstants+" ; "+initConfig);
	}

	/*
	 * check action
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String url=request.getRequestURI();
		System.out.println("请求资源："+url+"， 请求头信息："+request.getHeader("Cookie"));
		boolean flag=false;
		//System.out.println("information: "+request.getParameter("signin"));
		
		if(isCheckAble(request, initConfig)||isCheckAble(request, pass))
		{
			flag=true;
		}
		if(flag)
		{
			chain.doFilter(req, resp);
			return;
		}else
		{
			response.sendRedirect(request.getContextPath()+error_url);
			return;
		}
		
	}

//	/**
//	 * 用户是否在线
//	 * @param request
//	 * @return
//	 */
//	private boolean isUserAvaliable(HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		boolean flag=false;
//		HttpSession session=request.getSession(true);
//		Cookie[] cookies = ((HttpServletRequest) request).getCookies();     
//        String id = null;       
//       if (cookies != null) {     
//           for (Cookie c : cookies) {     
//               if (c.getName().equals("id")) {     
//                    id = c.getValue().trim();     
//                }
//            }
//        }
//       System.out.println("用户ID："+id);
//       if(!"".equals(id)&&id!=null)
//       {
//    	   UserDao userDao=new UserDaoImpl();
//           User user=userDao.getUserByUserId(Integer.parseInt(id));
//           System.out.println("当前用户："+user.getUname());
//           if (user!=null) {
//                session = ((HttpServletRequest) request).getSession(true);     
//                session.setAttribute(Constants.USER_ID, id);
//                flag=true;
//            }
//       }
//		return flag;
//	}

	/**
	 * check whether the url is belong to passed urls
	 */
	private boolean isCheckAble(HttpServletRequest request, String parma) {
		boolean flag=false;
		if (parma == null)
		{
			flag=false;
		}else
		{
			String url = request.getRequestURI();
			String[] permitParams = parma.split(";");
			for (String permit : permitParams)
			{
				if (isURLPassable(request.getContextPath() + permit, url, request))
				{
					System.out.println("请求："+url+"; 匹配："+permit);
					flag=true;
					break;
				}
			}
		}
		System.out.println("匹配结果："+flag);
		return flag;
	}

	/**
	 * check whether the url should be passed
	 *
	 * @param permit
	 *
	 * @param url
	 *
	 * @return is pass
	 */
	private boolean isURLPassable(String permit, String url, HttpServletRequest request) {
		try {

			String reg = "";
			if (permit.equals(request.getContextPath() + "/"))
				reg = "^" + permit + "$";
			else
				reg = "^" + permit + ".*$";
			Pattern p = Pattern.compile(reg);
			return p.matcher(url).matches();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void destroy() {
	}
}
