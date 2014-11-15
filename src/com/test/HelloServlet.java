package com.test;

import com.ylf.servlet.Servlet;
import com.ylf.servlet.ServletConfig;
import com.ylf.servlet.ServletRequest;
import com.ylf.servlet.ServletResponse;

public class HelloServlet implements Servlet{

	@Override
	public void init(ServletConfig config) {
		
	}

	@Override
	public void service(ServletRequest req, ServletResponse resp) {
		try{
			Thread.sleep(100);
		}catch(Exception e){
			e.printStackTrace();
		}
		resp.getWriter().println("hello, world.");
		
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

}
