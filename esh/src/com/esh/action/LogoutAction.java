package com.esh.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.esh.globle.Constants;

@Component
public class LogoutAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute(Constants.USER_ID)!=null)
		{
			System.out.println("注销账户:"+request.getSession().getAttribute(Constants.USER_ID));
			request.getSession().removeAttribute(Constants.USER_ID);
		}
		System.out.println("注销账户后跳转："+request.getContextPath()+"/default_cn.html");
		response.sendRedirect(request.getContextPath()+"/default_cn.html");
	}

}
