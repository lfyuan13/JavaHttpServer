package com.ylf.util;

public class StringBuilderUtil {
	/**
	 * �÷�����ȡstringbuilder����һ�У�ͬʱҲ�������һ������
	 * @param sb
	 * @return ���ز�������һ�е����ݣ�����Ѿ�����β�ˣ�����һ��ΪNull
	 */
	public static String nextLine(StringBuilder sb){
		int index = sb.indexOf("\r\n");
		String line;
		
		if(index < 0){
			if(sb.equals(""))
				return null;
			else{
				line = sb.toString();
				sb.delete(0, sb.length());  // ������һ��
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
