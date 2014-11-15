package com.ylf.servlet;

public interface Servlet {
	// method of the life-circle
	public void init(ServletConfig config);
	public void service(ServletRequest req, ServletResponse resp);
	public void destroy();
	
	//
	public ServletConfig getServletConfig();
	public String getServletInfo();

}
