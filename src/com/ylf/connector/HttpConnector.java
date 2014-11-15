package com.ylf.connector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.ylf.container.Container;
import com.ylf.container.SimpleContainer;
import com.ylf.util.TimeUtil;

/**
 * v3:
 * 版本加入：
 * 1. Processor池，提高并发访问量
 * 2. 加入Container Pipeline技术
 * 该包下的HttpConnector和HttpProcessor是对HttpServer的分解
 * @author Administrator
 *
 */
public class HttpConnector extends Thread {
	private ServerSocket server;
	private HttpProcessorPool pool;
	private Container container;
	private boolean stop;
	
	public HttpConnector(){
		System.out.println("version 3: ylf_tomcat");
		if(init())
			System.out.println("[HttpConnector] create http server ok.");
		else
			System.out.println("[HttpConnector] create http server fail.");
	}
	
	/**
	 * 初始化serversocket
	 * @return
	 */
	private boolean init(){
		try{
			stop = false;
			server = new ServerSocket(38383, 1, InetAddress.getByName("localhost"));
			pool = new HttpProcessorPool(10, 20, this);
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
				Socket socket = server.accept();
				System.out.println("[HttpConnector] " + TimeUtil.getFormatTime() + " receive...");
				HttpProcessor p = pool.pop();
				if(p==null){
					socket.close();
				}else{
					p.assign(socket);
				}
				System.out.println("[HttpConnector] " + TimeUtil.getFormatTime() + " next wait..");
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void push(HttpProcessor processor){
		pool.push(processor);
	}
	
	public HttpProcessor pop(){
		return pool.pop();
	}
	
	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}
	
	public void close(){
		pool.close();
	}

}
