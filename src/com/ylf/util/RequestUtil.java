package com.ylf.util;

import java.io.IOException;
import java.io.InputStream;

import com.ylf.request.HttpRequest;

public class RequestUtil {
	public static int MAX_SIZE = 4*1024;
	
	public static void parse(HttpRequest req, InputStream in){
		if(in == null){
//			System.out.println("[RequestUtil] inputstream is null!");
			return ;
		}
		// read data
		byte[] bytes = new byte[MAX_SIZE];
		try {
			int available = in.read(bytes);
			if(available <= 0)
				return;
//			System.out.println("[RequestUtil] available:" + available);
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<available; i++){
				sb.append((char)bytes[i]);
			}
			
//			System.out.println("[RequestUtil] " + sb);
			
			parseRequestLine(req, sb);  // 解析请求行
			parseHeader(req, sb);  // 解析请求头
			parseBody(req, sb);  // 解析请求体
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void parseRequestLine(HttpRequest req, StringBuilder sb){
		int index = sb.indexOf("\r\n");
		if(index < 0){
//			System.out.println("[RequestUtil] parse RequestLine error.");
			return;
		}

		String requestLine = StringBuilderUtil.nextLine(sb);
		int index1, index2;

		index1 = requestLine.indexOf(" ");
		// method
		if(index1 > 0){
			req.setMethod(requestLine.substring(0, index1));
		}
		// uri and queryString
		index2 = requestLine.indexOf(" ", index1+1);
		if(index2 > index1){
			int question = requestLine.indexOf("?");
			if(question < 0){
				req.setQueryString("");
				req.setRequestUri(requestLine.substring(index1+1, index2));
			}else{
				req.setQueryString(requestLine.substring(question+1, index2));
				req.setRequestUri(requestLine.substring(index1+1, question));
			}
		}
		// version
		req.setVersion(requestLine.substring(index2+6, index));
		
	}
	
	private static void parseHeader(HttpRequest req, StringBuilder sb){
		while(true){
			String line = StringBuilderUtil.nextLine(sb);
			if(line==null || "".equals(line)){
				break;
			}
			int index = line.indexOf(":");
			String key = line.substring(0, index);
			String value = line.substring(index+2);
			if(key.equals("Cookie"))
				parseCookie(req, value);
			else
				req.addHeader(key, value);
		}
	}
	
	private static void parseBody(HttpRequest req, StringBuilder sb){
		if(req.getMethod().equals("POST")){
			req.setPostData(sb.toString());
			StringBuilderUtil.clear(sb);
		}
	}

	private static void parseCookie(HttpRequest req, String cookieLine){
		
	}
}
