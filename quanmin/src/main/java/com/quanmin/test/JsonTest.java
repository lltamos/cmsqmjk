package com.quanmin.test;

import java.util.Collection;
import java.util.HashMap;

@SuppressWarnings("ALL")
public class JsonTest {

    public static void main(String[] args) {

        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("test01", "1");
        hashMap.put("test02", "3");
        HashMap<String, String> newHashMap = new HashMap<>(hashMap);
        Collection<String> values = newHashMap.values();
        for(String v :values){
            System.out.println(v);
        }



    }



}
