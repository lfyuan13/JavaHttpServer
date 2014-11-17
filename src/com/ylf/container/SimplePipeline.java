package com.ylf.container;

import java.util.ArrayList;

import com.ylf.lifecycle.Lifecycle;
import com.ylf.lifecycle.LifecycleListener;
import com.ylf.lifecycle.LifecycleSupport;
import com.ylf.logger.Logger;
import com.ylf.servlet.ServletRequest;
import com.ylf.servlet.ServletResponse;

public class SimplePipeline implements Pipeline{
	private ValueContext valueContext;
	private ArrayList<Value> values;
	private LifecycleSupport lifecycleSupport;
	
	public SimplePipeline(Value base){
		this.lifecycleSupport = new LifecycleSupport(this);
		this.valueContext = new SimpleValueContext(base);
		this.values = new ArrayList<Value>();
	}
	
	@Override
	public void invoke(ServletRequest req, ServletResponse resp) {
		valueContext.invokeNext(req, resp);
	}
	
	@Override
	public void addValue(Value value) {
		values.add(value);
	}

	@Override
	public void removeValue(Value value) {
		values.remove(value);
	}


	// ***************
	// ***************
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

	@Override
	public void start() {
		lifecycleSupport.notifyLifecycleListener(Lifecycle.START_EVENT, null);
	}

	@Override
	public void stop() {
		lifecycleSupport.notifyLifecycleListener(Lifecycle.STOP_EVENT, null);
	}
	
	
	/**
	 * iterator of ArrayList
	 * @author Administrator
	 *
	 */
	class SimpleValueContext implements ValueContext{
		private int idx;
		private Value base;
		
		public SimpleValueContext(Value base){
			this.idx = -1;
			this.base = base;
		}
		
		@Override
		public void invokeNext(ServletRequest req, ServletResponse resp) {
			++idx;
			if(idx < values.size()){
				values.get(idx).invoke(req, resp, this);
			}else if(idx == values.size()){
				base.invoke(req, resp, this);
			}else
				return;
		}

		
	}






}
