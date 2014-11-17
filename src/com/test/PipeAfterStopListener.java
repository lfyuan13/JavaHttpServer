package com.test;

import com.ylf.lifecycle.Lifecycle;
import com.ylf.lifecycle.LifecycleEvent;
import com.ylf.lifecycle.LifecycleListener;
import com.ylf.logger.Logger;
import com.ylf.util.LoggerUtil;

public class PipeAfterStopListener implements LifecycleListener{

	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		if(event.getType() == Lifecycle.AFTER_STOP_EVENT)
			LoggerUtil.getLogger(this.getClass().getPackage().getName()).log("Listener: pipe after stop", Logger.DEBUG);
	}

}