package com.quanmin.util;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by llsmp on 2017/7/21.
 * 单号生产工具类
 */
@Component
public class SerialNumberUtils {
    @Resource
    private RedisUtils redisUtils;

    public String createOrderNum() {

        StringBuffer result = new StringBuffer();
        String currentDate = DateConvertUtils.getDirName();
        currentDate = currentDate.substring( 2 );
        result.append( currentDate );

        String num = redisUtils.getValue( Commons.REDIS_KEY_ORDERNUMBER );

        if (num.length() <= 5) {
            String s = "00000000000" + num;
            num = s.substring( s.length() - 5 );
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank( num ))
            result.append( num );
        else
            result.append( 0 );

        redisUtils.incrs( Commons.REDIS_KEY_ORDERNUMBER );

        return result.toString();

    }


}
