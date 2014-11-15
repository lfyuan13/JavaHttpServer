package com.ylf.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeUtil {
	public static String getFormatTime(){
		GregorianCalendar gc = new GregorianCalendar();
		return gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH)+1) +"-" + gc.get(Calendar.DATE) + 
				" " + gc.get(Calendar.HOUR_OF_DAY) + ":" + gc.get(Calendar.MINUTE) + ":" + gc.get(Calendar.SECOND);
	}
}
