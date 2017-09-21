package com.quanmin.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
/**
 * url相关工具
 * @author quanmin
 *
 */
public class UrlUtils {
	/**
	 * uuid
	 * @return
	 */
	public static String MessageId(){
		UUID uuid=UUID.randomUUID();
		String MessageId=uuid.toString().toUpperCase().replaceAll("-", "");
		return MessageId;
	}
	public static String Dtsend(){
		/**
		 * 系统时间
		 */
		DateFormat df=new SimpleDateFormat("yyyyMMddhhmmss");
		String time=df.format(new Date());
		return time;
	}
	
}
