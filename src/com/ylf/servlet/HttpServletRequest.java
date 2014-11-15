package com.ylf.servlet;

import com.ylf.request.HttpRequest;

public class HttpServletRequest implements ServletRequest{
	private HttpRequest request;
	
	public HttpServletRequest(HttpRequest request){
		this.request = request;
	}

	@Override
	public String getRequestUri() {
		return request.getRequestUri();
	}

	@Override
	public String getMethod() {
		return request.getMethod();
	}

	@Override
	public String getVersion() {
		return request.getVersion();
	}

	@Override
	public String getQueryString() {
		return request.getQueryString();
	}

	@Override
	public String getHeader(String key) {
		return request.getHeader(key);
	}

	@Override
	public String getParameter(String key) {
		return request.getParameter(key);
	}

	@Override
	public Cookie[] getCookies() {
		return request.getCookies();
	}

	
	
	
}
