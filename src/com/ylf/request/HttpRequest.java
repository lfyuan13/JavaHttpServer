package com.ylf.request;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.ylf.servlet.Cookie;
import com.ylf.util.RequestUtil;

public class HttpRequest {
	private InputStream in;
	
	// request parameter
	private String method;
	private String requestUri;
	private String version;
	private String queryString;
	private String postData;
	
	private HashMap<String, String> header;
	private ArrayList<Cookie> cookies;
	private ParameterMap parameters;
	
	public HttpRequest(){}
	
	public HttpRequest(InputStream in){
		this.in = in;
		header = new HashMap<String, String>();
		cookies = new ArrayList<Cookie>();
		parameters = new ParameterMap();
	}
	

	public InputStream getIn() {
		return in;
	}

	public void setIn(InputStream in) {
		this.in = in;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	
	public String getPostData() {
		return postData;
	}

	public void setPostData(String postData) {
		this.postData = postData;
	}

	// ******** parameters *******
	public void addParameter(String key, String value){
		parameters.put(key, value);
	}
	public String getParameter(String key){
		return parameters.get(key);
	}
	
	// ******** header *******
	public void addHeader(String key, String value){
		header.put(key, value);
	}
	
	public String getHeader(String key){
		return header.get(key);
	}

	// ******** cookies *******
	public void addCookie(Cookie c){
		cookies.add(c);
	}
	
	public Cookie[] getCookies(){
		return (Cookie[])cookies.toArray();
	}
	
	/**
	 * 解析inputStream，交给RequestUtil处理
	 * 
	 */
	public void parse(){
		parameters.setLocked(false);  // 打开锁
		RequestUtil.parse(this, in);
		parameters.setLocked(true);  // 锁定
	}

	@Override
	public String toString() {
		return "HttpRequest [in=" + in + ", method=" + method + ", requestUri="
				+ requestUri + ", version=" + version + ", queryString="
				+ queryString + ", postData=" + postData + ", header=" + header
				+ ", cookies=" + cookies + ", parameters=" + parameters + "]";
	}
	
}
