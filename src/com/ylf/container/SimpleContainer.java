package com.ylf.container;

import java.util.ArrayList;

import com.ylf.lifecycle.Lifecycle;
import com.ylf.lifecycle.LifecycleException;
import com.ylf.lifecycle.LifecycleListener;
import com.ylf.lifecycle.LifecycleSupport;
import com.ylf.logger.Logger;
import com.ylf.servlet.ServletRequest;
import com.ylf.servlet.ServletResponse;

/**
 * Container is a level-by-level structure
 * @author Administrator
 *
 */
public class SimpleContainer implements Container{

	private Pipeline pipe;
	private ArrayList<Container> children;
	private LifecycleSupport lifecycleSupport;  // inner Lifecycle manager
	private boolean started;
	
	public SimpleContainer(Pipeline pipe){
		this.started = false;
		this.pipe = pipe;
		this.lifecycleSupport = new LifecycleSupport(this);
	}
	
	@Override
	public void invoke(ServletRequest req, ServletResponse resp) {
		pipe.invoke(req, resp);
	}

	@Override
	public ArrayList<Container> getChildren() {
		return children;
	}

	@Override
	public void addChild(Container container) {
		children.add(container);
	}

	
	// ************
	// below method is from Lifecycle interface
	// ************
	@Override
	public void start() {
		if(started)
			throw new LifecycleException("LifecycleException: this container has been started.");
		lifecycleSupport.notifyLifecycleListener(Lifecycle.BEFORE_START_EVENT, null);
		
		// 1. start children
		if(children != null){
			for(Container child : children)
				child.start();
		}
		
		// 2. start pipeline
		pipe.start();
			
		// 3. start self
		lifecycleSupport.notifyLifecycleListener(Lifecycle.START_EVENT, null);
		
		// 4. after start
		lifecycleSupport.notifyLifecycleListener(Lifecycle.AFTER_START_EVENT, null);
		
	}
	@Override
	public void stop() {
		lifecycleSupport.notifyLifecycleListener(Lifecycle.BEFORE_STOP_EVENT, null);
		lifecycleSupport.notifyLifecycleListener(Lifecycle.STOP_EVENT, null);
		
		// 1. stop children
		if(children != null){
			for(Container child : children)
				child.stop();
		}
		
		// 2. stop pipeline
		pipe.stop();
		
		// 3. after stop
		lifecycleSupport.notifyLifecycleListener(Lifecycle.AFTER_STOP_EVENT, null);
	}
	
	@Override
	public void addLifecycleListener(LifecycleListener listener) {
		lifecycleSupport.addLifecycleListener(listener);
	}

	@Override
	public void removeLifecycleListener(LifecycleListener listener) {
		lifecycleSupport.removeLifecycleListener(listener);
	}

	@Override
	public ArrayList<LifecycleListener> getLifecycleListeners() {
		return lifecycleSupport.getLifecycleListeners();
	}

}
