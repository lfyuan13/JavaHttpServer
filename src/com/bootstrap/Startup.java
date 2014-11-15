package com.bootstrap;

import java.io.IOException;

import com.ylf.connector.HttpConnector;
import com.ylf.container.BasicValue;
import com.ylf.container.Container;
import com.ylf.container.HeaderLogValue;
import com.ylf.container.Pipeline;
import com.ylf.container.SimpleContainer;
import com.ylf.container.SimplePipeline;
import com.ylf.container.UriLogValue;
import com.ylf.container.Value;
import com.ylf.container.ValueContext;

public class Startup {
	public static void main(String[] args){
		HttpConnector connector = new HttpConnector();
		// inject 3 value
		Value value2 = new HeaderLogValue();
		Value value1 = new UriLogValue();
		Value base = new BasicValue();
		// set pipeline
		Pipeline pipe = new SimplePipeline(base);
		pipe.addValue(value1);
		pipe.addValue(value2);
		// set container
		Container container = new SimpleContainer(pipe);
		connector.setContainer(container);
		
		// invoke this thread
		connector.start();
		try {
			System.in.read();
			System.out.println("you have send shutdown command.");
			connector.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
