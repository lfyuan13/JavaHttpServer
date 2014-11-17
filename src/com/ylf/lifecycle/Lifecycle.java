package com.ylf.lifecycle;

import java.util.ArrayList;

/**
 * the component of our container should be implemented by this interface
 * @author Administrator
 *
 */
public interface Lifecycle {
	public static int BEFORE_START_EVENT = 1;
	public static int START_EVENT = 2;
	public static int AFTER_START_EVENT = 3;
	public static int BEFORE_STOP_EVENT = 4;
	public static int STOP_EVENT = 5;
	public static int AFTER_STOP_EVENT = 6;

	public void addLifecycleListener(LifecycleListener listener);
	public void removeLifecycleListener(LifecycleListener listener);
	public ArrayList<LifecycleListener> getLifecycleListeners();
	
	public void start();
	public void stop();
}
