package com.test;

import com.ylf.servlet.Servlet;
import com.ylf.servlet.ServletConfig;
import com.ylf.servlet.ServletRequest;
import com.ylf.servlet.ServletResponse;

public class GoodbyeServlet implements Servlet{

	@Override
	public void init(ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void service(ServletRequest req, ServletResponse resp) {
		resp.getWriter().println("good bye, man.");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
