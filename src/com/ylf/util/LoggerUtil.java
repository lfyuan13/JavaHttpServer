package com.ylf.util;

import java.util.HashMap;

import com.ylf.logger.Logger;
import com.ylf.logger.SystemOutLogger;

public class LoggerUtil {
	private static HashMap<String, Logger> loggerMap = new HashMap<String, Logger>();
	
	public static Logger getLogger(String packageName){
		if(!loggerMap.containsKey(packageName))
			loggerMap.put(packageName, new SystemOutLogger(Logger.DEBUG));
		return loggerMap.get(packageName);
	}
}
