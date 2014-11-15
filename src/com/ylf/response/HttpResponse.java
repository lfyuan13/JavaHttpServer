package com.ylf.response;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.ylf.request.HttpRequest;

public class HttpResponse {

	private OutputStream out;
	private HttpRequest request;
	
	public HttpResponse(OutputStream out){
		this.out = out;
	}

	public OutputStream getOut() {
		return out;
	}

	public void setOut(OutputStream out) {
		this.out = out;
	}

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}
	
	public PrintWriter getWriter(){
		return new PrintWriter(out, true);
	}
	
}
