package com.quanmin.test;


import java.util.HashMap;

/**
 * @Author: By heasy.
 * @Date: 2017/9/27.
 * @Contcat: yz972641975@gmail.com.
 * @Description:
 * @Modified By:
 */
@SuppressWarnings("ALL")
public class HMap extends ABSTestHashMap {
    static  int threadLocal=0;

    public static void main(String[] args) throws InterruptedException {

        HashMap<String,Integer> map=new HashMap();
        map.put("aasdas",1);
        map.put("dddsdbasd",1);
        map.put("aasdas",1);


    }


    static double test(Integer n) {
        double sqrt=Math.sqrt(n);
        double ceil=Math.ceil(sqrt);
        return Math.pow(2, ceil);
    }

    static class JobCallable extends Thread {
        @Override
        public void run() {
            soudt();
        }

        public  synchronized void soudt() {
                System.out.println(threadLocal++);
        }
    }


}
