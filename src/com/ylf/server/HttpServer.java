package com.ylf.server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.ylf.request.HttpRequest;
import com.ylf.response.HttpResponse;
import com.ylf.servlet.HttpServletRequest;
import com.ylf.servlet.HttpServletResponse;
import com.ylf.servlet.ServletProcessor;
import com.ylf.util.Config;

@Deprecated
public class HttpServer {

	ServerSocket server;
	
	public HttpServer(){
		if(init())
			System.out.println("[HttpServer] create http server ok.");
		else
			System.out.println("[HttpServer] create http server fail.");
	}
	
	public void await(){
		boolean shutdown = false;
		ServletProcessor processor = new ServletProcessor();  // 类似container功能
		
		while(!shutdown){
			try{
				Socket socket = server.accept();
				//
				System.out.println("[HttpServer] receive a request.");
				HttpRequest request = new HttpRequest(socket.getInputStream());
				request.parse();
				
				HttpResponse response = new HttpResponse(socket.getOutputStream());
				response.setRequest(request);
//				response.send();
				
				// 这里构建servlet request, response
				processor.process(new HttpServletRequest(request), new HttpServletResponse(response));
				
				
//				if(request.getHeader().getUri().equals("/shutdown"))
				if(request.getRequestUri().equals("/shutdown"))
					shutdown = true;
				System.out.println("[HttpServer] close a request.");
				socket.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private boolean init(){
		try{
			server = new ServerSocket(38383, 1, InetAddress.getByName("localhost"));
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	
//	
//	public static void main(String[] args) {
//		HttpServer hs = new HttpServer();
//		hs.await();
//	}

}
