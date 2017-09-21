package com.quanmin.util;

import java.lang.reflect.Field;

/**
 * Created by llsmp on 2017/7/20.
 */
public class ReflectionUtils {


    public static void convers(Object obj, Class clazz) {

        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field f : fields) {

                if (f.getType() == clazz) {

                    if (clazz.getSimpleName().contains( "String" )) {
                        f.setAccessible( true );
                        Object o = f.get( obj );
                        if (o == null) continue;
                        String s = o.toString();
                        if ("".equals( s )) {
                            f.set( obj, null );
                        }

                    }
                }
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
    }


}
