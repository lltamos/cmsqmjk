package com.quanmin.util;

import org.springframework.data.domain.Sort;

/**
 * SortUtils
 *
 * @author llsmp
 * @date 2017/7/27
 */
public class SortUtils {

    public static Sort DESCCreateTime(){
         return new Sort( Sort.Direction.DESC ,"createTime");
    }



}
