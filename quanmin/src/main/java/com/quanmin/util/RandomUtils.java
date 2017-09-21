package com.quanmin.util;

import java.util.Random;

public class RandomUtils {
	 /** 
     * 获取指定位数的随机数 
     * @param num 
     * @return 
     */  
    public static String getRandom(int num){  
        Random random = new Random();  
        StringBuilder sb = new StringBuilder();  
        for(int i = 0;i < num; i++){  
            sb.append(String.valueOf(random.nextInt(10)));  
        }  
        return sb.toString();  
    }  
    
    /**
     * 生成4位验证码
     */
    public static Integer getRandom4(){  
    	Integer code=(int)((Math.random()*9+1)*1000);
        return code;  
    }
}
