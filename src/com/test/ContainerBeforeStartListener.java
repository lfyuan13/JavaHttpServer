package com.test;

import com.ylf.container.Container;
import com.ylf.lifecycle.Lifecycle;
import com.ylf.lifecycle.LifecycleEvent;
import com.ylf.lifecycle.LifecycleListener;
import com.ylf.logger.Logger;
import com.ylf.util.LoggerUtil;

public class ContainerBeforeStartListener implements LifecycleListener{

	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		if(event.getType() == Lifecycle.BEFORE_START_EVENT){
			LoggerUtil.getLogger(this.getClass().getPackage().getName()).log("Listener: container before start event is started.", Logger.INFO);
		}
	}

}
