package com.quanmin.util;


public class StringArrayUtil {
	public static String join(String[] strs,String op){
		String r="";
		if (strs==null||strs.length==0){
			return "";
		}else if(strs.length>=1){
			r=strs[0];
			for(int i=1;i<strs.length;i++){
				r = r+op+strs[i]; 
			}
			return r;
		}
		return r;
	}
	
}
