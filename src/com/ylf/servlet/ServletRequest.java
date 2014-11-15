package com.ylf.servlet;

import javax.xml.ws.spi.http.HttpHandler;

public interface ServletRequest {

	public String getRequestUri();
	public String getMethod();
	public String getVersion();
	public String getQueryString();
	
	public String getHeader(String key);
	public String getParameter(String name);
	public Cookie[] getCookies();
}
