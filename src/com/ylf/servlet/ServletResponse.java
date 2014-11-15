package com.ylf.servlet;

import java.io.PrintWriter;

public interface ServletResponse {
	public PrintWriter getWriter();
	public void setHeader(String key, String value);
}
