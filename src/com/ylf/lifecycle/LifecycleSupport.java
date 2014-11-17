package com.ylf.lifecycle;

import java.util.ArrayList;
import java.util.List;

public class LifecycleSupport implements Lifecycle{

	private ArrayList<LifecycleListener> listeners;
	private Lifecycle source;
	
	public LifecycleSupport(Lifecycle source){
		this.listeners = new ArrayList<LifecycleListener>();
		this.source = source;
	}
	
	/**
	 *  notify all the listener
	 * @param source the event source
	 * @param type the event type
	 * @param arg the transfer parameter 
	 */
	public void notifyLifecycleListener(int type, Object arg){
		LifecycleEvent event = new LifecycleEvent(source, type, arg);
		// notify all the listener
		for(LifecycleListener listener : listeners)
			listener.lifecycleEvent(event);
	}
	
	@Override
	public void addLifecycleListener(LifecycleListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeLifecycleListener(LifecycleListener listener) {
		listeners.remove(listener);
	}

	@Override
	public ArrayList<LifecycleListener> getLifecycleListeners() {
		return listeners;
	}

	@Override
	public void start() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void stop() {
		throw new UnsupportedOperationException();
	}

}
