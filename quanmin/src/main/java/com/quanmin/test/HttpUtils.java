package com.quanmin.test;

import com.alipay.api.AlipayApiException;
import com.quanmin.util.Commons;
import com.quanmin.util.HttpRequestUtils;
import com.quanmin.util.ResultUtils;
import com.quanmin.util.alipayutil.PayUtils;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
public class HttpUtils {
    public static void main(String[] args) {
      /*  List<NameValuePair> param=new ArrayList<>();
        param.add(new BasicNameValuePair("appMobile", sysUser.getUsername()));
        param.add(new BasicNameValuePair("user_status", "1"));
        param.add(new BasicNameValuePair("userIdcard", sysUser.getIdNo()));
        param.add(new BasicNameValuePair("userIdcard", storeId.toString()));
        param.add(new BasicNameValuePair("userType", "3"));
        param.add(new BasicNameValuePair("isVip", "0"));*/
    //    Map<String, Object> m=HttpRequestUtils.httpPost("http://testapi.1hudoctor.com/api?m=login&account=YongHeHealth&password=a918ca3dcd8cbebd41f96d2cbefee499&platform=YongHeHealth&deviceType=YongHeHealth&version=1.0", null);

        Object exception=new Exception();
        if (exception instanceof Throwable){
            System.out.println(1);
        }

    }
}
