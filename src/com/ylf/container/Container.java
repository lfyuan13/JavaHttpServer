package com.ylf.container;

import java.util.ArrayList;

import com.ylf.lifecycle.Lifecycle;
import com.ylf.logger.Logger;
import com.ylf.servlet.ServletRequest;
import com.ylf.servlet.ServletResponse;

/**
 * Container should extends Lifecycle
 * @author Administrator
 *
 */
public interface Container extends Lifecycle{
	public void invoke(ServletRequest req, ServletResponse resp);
	public ArrayList<Container> getChildren();
	public void addChild(Container container);
	
}
