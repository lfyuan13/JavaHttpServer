package com.ylf.connector;

import java.security.InvalidKeyException;
import java.security.InvalidParameterException;

public class HttpProcessorPool {
	private int capacity;
	private int size;
	private int current;
	
	private HttpProcessor[] processors;  // 这里当然把HttpProcessor设计成接口更合理
	private HttpConnector connector;
	
	public HttpProcessorPool(int min, int max, HttpConnector connector){
		if(min <= 0)
			throw new InvalidParameterException("min size<0");
		if(max < min)
			max = min;
		size = min;
		capacity = max;
		
		processors = new HttpProcessor[size];
		for(current=0; current<size; current++){
			processors[current] = new HttpProcessor(connector);
			processors[current].start();  // 启动线程
		}
		current--;
	}
	
	public HttpProcessor pop(){
		synchronized(this){
			if(current < 0){
				if(size < capacity){
					HttpProcessor p = new HttpProcessor(connector);
					size++;
					p.start();
					return p;
				}else
					return null;
			}else{
				HttpProcessor tmp = processors[current--] ;
				return tmp;
			}
		}
	}
	
	public void push(HttpProcessor p){
		synchronized (this) {
			if(current >= size)
				throw new ArrayIndexOutOfBoundsException();
			processors[++current] = p;
			notifyAll();
		}
	}
	
	public void close(){
		int closed = 0;
		while(closed < size){
			try{
				synchronized(this){
					if(current < 0)
						wait();
					System.out.println("close " + processors[current].toString() );
					processors[current--].close();
					closed ++;
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("Processor pool close ok.");
	}
}
