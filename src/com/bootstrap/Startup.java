package com.bootstrap;

import java.io.IOException;

import com.test.ContainerBeforeStartListener;
import com.test.ContainerStartListener;
import com.test.ContainerStopListener;
import com.test.PipeAfterStopListener;
import com.test.PipeStartListener;
import com.test.PipeStopListener;
import com.ylf.connector.HttpConnector;
import com.ylf.container.BasicValue;
import com.ylf.container.Container;
import com.ylf.container.HeaderLogValue;
import com.ylf.container.Pipeline;
import com.ylf.container.SimpleContainer;
import com.ylf.container.SimplePipeline;
import com.ylf.container.UriLogValue;
import com.ylf.container.Value;
import com.ylf.logger.BaseLogger;
import com.ylf.logger.Logger;
import com.ylf.logger.SystemOutLogger;
import com.ylf.util.LoggerUtil;

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
		
		pipe.addLifecycleListener(new PipeStartListener());  // pipe listener
		pipe.addLifecycleListener(new PipeStopListener());
		pipe.addLifecycleListener(new PipeAfterStopListener());
		
		// set container
		Container container = new SimpleContainer(pipe);
		// lifecycle listener
		container.addLifecycleListener(new ContainerBeforeStartListener());  // container listener
		container.addLifecycleListener(new ContainerStartListener());
		container.addLifecycleListener(new ContainerStopListener());

		connector.setContainer(container);
		
		// invoke this thread
		connector.start();
		try {
			System.in.read();
			LoggerUtil.getLogger(Startup.class.getPackage().getName()).log("you have send shutdown command.", Logger.DEBUG);
			connector.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
