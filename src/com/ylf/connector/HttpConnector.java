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
 * �汾���룺
 * 1. Processor�أ���߲���������
 * 2. ����Container Pipeline����
 * �ð��µ�HttpConnector��HttpProcessor�Ƕ�HttpServer�ķֽ�
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
	 * ��ʼ��serversocket
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