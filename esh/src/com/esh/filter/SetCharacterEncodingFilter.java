package com.esh.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class SetCharacterEncodingFilter implements Filter{

	//�����ʽ
	protected String encoding=null;
	
	@Override
	public void destroy() {
		this.encoding=null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// �жϵ�ǰ�����Ƿ񵥶����ñ����ʽ
		if(request.getCharacterEncoding()==null)
		{
			//��ȡĬ�ϱ����ʽ
			String encoding=this.encoding;
			if(encoding!=null)
			{
				request.setCharacterEncoding(encoding);
				response.setContentType("text/plain;charset="+encoding);
				response.setCharacterEncoding(encoding);
			}
			System.out.println("���ñ���Ϊutf-8�����ʽ");
		}else
		{
			System.out.println("��ǰ���룺"+request.getCharacterEncoding());
		}
		
		//����һ��������ת��
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		//��ȡ��ʼ������
		this.encoding=filterConfig.getInitParameter("encoding");
		System.out.println("encioding:  "+encoding);
	}

}
