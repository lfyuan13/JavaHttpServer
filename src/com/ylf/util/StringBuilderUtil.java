package com.ylf.util;

public class StringBuilderUtil {
	/**
	 * 该方法获取stringbuilder的下一行，同时也清除掉上一行数据
	 * @param sb
	 * @return 返回参数是下一行的数据，如果已经到结尾了，则下一行为Null
	 */
	public static String nextLine(StringBuilder sb){
		int index = sb.indexOf("\r\n");
		String line;
		
		if(index < 0){
			if(sb.equals(""))
				return null;
			else{
				line = sb.toString();
				sb.delete(0, sb.length());  // 清除最后一行
			}
		}
		else{
			line = sb.substring(0, index);
			sb.delete(0, index+2);
		}
		return line;
	}
	
	public static void clear(StringBuilder sb){
		sb.delete(0, sb.length());
	}
}
