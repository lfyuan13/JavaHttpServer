package com.ylf.container;

import com.ylf.servlet.Servlet;
import com.ylf.servlet.ServletRequest;
import com.ylf.servlet.ServletResponse;
import com.ylf.util.Config;

public class BasicValue implements Value{

	private Config config;
	
	public BasicValue(){
		config = Config.getInstance();
	}
	@Override
	public void invoke(ServletRequest req, ServletResponse resp, ValueContext context) {
		System.out.println("visit the base.");
		
		Servlet servlet = config.getServlet(req.getRequestUri());
		if(servlet == null){
			resp.getWriter().println("404!Not found!");
		}else
			servlet.service(req, resp);
	}

}
