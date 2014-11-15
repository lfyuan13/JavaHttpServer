package com.ylf.servlet;

import java.io.PrintWriter;

import com.ylf.response.HttpResponse;

public class HttpServletResponse implements ServletResponse{

	private HttpResponse response;
	
	public HttpServletResponse(HttpResponse response){
		this.response = response;
	}
	@Override
	public PrintWriter getWriter() {
		return response.getWriter();
	}
	
	/**
	 * ����һ����ֵ��ͷ����Ӧ����
	 */
	@Override
	public void setHeader(String key, String value) {
		response.getWriter().println(key + ": " + value);
	}

	
	
}
