package com.ylf.connector;

import java.net.Socket;

import com.ylf.request.HttpRequest;
import com.ylf.response.HttpResponse;
import com.ylf.servlet.HttpServletRequest;
import com.ylf.servlet.HttpServletResponse;
import com.ylf.servlet.ServletProcessor;
import com.ylf.util.TimeUtil;

public class HttpProcessor extends Thread{
	private static int id = 0;
	private int iid;

	private ServletProcessor processor;  // 以后你叫container
	private Socket socket;
	private HttpConnector connector;
	private boolean stop;
	
	public HttpProcessor(HttpConnector connector) {
		iid = id++;
		socket = null;
		stop = false;
		processor = new ServletProcessor();
		this.connector = connector;
	}
	
	public HttpConnector getConnector(){
		return connector;
	}
	
	// init socket and notify thread
	public void assign(Socket socket){
		System.out.println("id=" + iid + " processor start:" + TimeUtil.getFormatTime());
		synchronized(this){
			this.socket = socket;
			notifyAll();
		}
	}
	
	
	public boolean process(){
		System.out.println("id=" + iid + " processor is working...");
		try{
			HttpRequest request = new HttpRequest(socket.getInputStream());
			HttpResponse response = new HttpResponse(socket.getOutputStream());

			request.parse();
			
			System.out.println("[HttpProcessor] request:\n" + request);
//			processor.process(new HttpServletRequest(request), new HttpServletResponse(response));
			connector.getContainer().invoke(new HttpServletRequest(request), new HttpServletResponse(response));
			socket.close();
			
			System.out.println("id=" + iid + " processor stop:" + TimeUtil.getFormatTime());
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void run() {
		while(!stop){
			
				try{
					synchronized(this){
						while(socket==null){
							wait();
						}
					}
				process();  // 处理
				socket = null;
				
				getConnector().push(this);  // 添加会池
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
	
	public void close(){
		stop = true;
	}

	@Override
	public String toString() {
		return "Processor ["+ iid + "]";
	}
	
	
}
