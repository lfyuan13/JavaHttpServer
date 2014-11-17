package com.ylf.connector;

import java.security.InvalidParameterException;

import com.ylf.logger.Logger;
import com.ylf.util.LoggerUtil;

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
		Logger logger = LoggerUtil.getLogger(this.getClass().getPackage().getName());
		int closed = 0;
		while(closed < size){
			try{
				synchronized(this){
					if(current < 0)
						wait();
					logger.log("close " + processors[current].toString(), Logger.INFO );
					processors[current--].close();
					closed ++;
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.log("Processor pool close ok.", Logger.INFO);
	}
}
