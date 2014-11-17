package com.ylf.connector;

import java.net.Socket;

import com.ylf.logger.Logger;
import com.ylf.request.HttpRequest;
import com.ylf.response.HttpResponse;
import com.ylf.servlet.HttpServletRequest;
import com.ylf.servlet.HttpServletResponse;
import com.ylf.servlet.ServletProcessor;
import com.ylf.util.LoggerUtil;
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
		Logger logger = LoggerUtil.getLogger(this.getClass().getPackage().getName());
		logger.log("id=" + iid + " processor is working...", Logger.DEBUG);
		try{
			HttpRequest request = new HttpRequest(socket.getInputStream());
			HttpResponse response = new HttpResponse(socket.getOutputStream());

			request.parse();
			
			logger.log("[HttpProcessor] request:\n" + request, Logger.INFO);
			connector.getContainer().invoke(new HttpServletRequest(request), new HttpServletResponse(response));
			socket.close();
			
			logger.log("id=" + iid + " processor stop:" + TimeUtil.getFormatTime(), Logger.DEBUG);
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
					while(!stop && socket==null){
						wait();
					}
				}
				if(stop)
					break;
				process();  // 处理
				socket = null;
				
				getConnector().push(this);  // 添加会池
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		LoggerUtil.getLogger(this.getClass().getPackage().getName()).log("from self processor: " + toString() + "has stopped.", Logger.INFO);
	}
	
	public synchronized void close(){
		stop = true;
		notifyAll();
	}

	@Override
	public String toString() {
		return "Processor ["+ iid + "]";
	}
	
	
}
