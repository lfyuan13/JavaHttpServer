package com.test;

import com.ylf.lifecycle.Lifecycle;
import com.ylf.lifecycle.LifecycleEvent;
import com.ylf.lifecycle.LifecycleListener;
import com.ylf.logger.Logger;
import com.ylf.util.LoggerUtil;

public class PipeStartListener implements LifecycleListener{

	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		if(event.getType() == Lifecycle.START_EVENT)
			LoggerUtil.getLogger(this.getClass().getPackage().getName()).log("listener: pipe start event.", Logger.DEBUG);
	}

}
