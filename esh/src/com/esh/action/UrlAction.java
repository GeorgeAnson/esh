package com.esh.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esh.dao.UserDao;
import com.esh.globle.Constants;
import com.esh.service.UserService;

/**
 * test≤‚ ‘
 * @author Administrator
 *
 */
@Component
public class UrlAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserService userService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("÷¥––urlAction");
		System.out.println("urlAction: "+request.getSession().getAttribute(Constants.USER_ID));
		response.sendRedirect(request.getContextPath()+"/userMenu.html");
	}
}
