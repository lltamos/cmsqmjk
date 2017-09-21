package com.quanmin.util;

import com.alipay.api.AlipayConstants;

public final class  Commons {

    // 登录校验接口
    public static String LOGINUSERINFO = "http://data.cqmjk.com/index.php/index/app/loginuserinfo";
    //    查看pdf报告接口
    public static String REPORTPDFINFO = "http://data.cqmjk.com/index.php/index/app/history";
    // 查询报告接口列表
    public static String REPROTLISTDATA = "http://data.cqmjk.com/index/app/reportDataSimple";
    // 查询报告接口
    public static String REPROTDATA = "http://data.cqmjk.com/index/app/reportData";
    // 查询建议接口
    public static String ANALYZE = "http://data.cqmjk.com/index/app/Opinion";

    //支付宝接口信息
    public static String APPLI_URL = "https://openapi.alipay.com/gateway.do";
    //appid
    public static String APP_ID = "2017061407488251";
    //私钥
    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCKuZ7U2ZK9FYh19W62kZY/OnL6AIu+24ulyVFRZ+h2oBgWvCKxD/NsIGqtM/HSV0plwEWbvarqLjMNSPUNGiOo/mFtIvXXdq2n5Jw+Uohnlijo2lcWF2f3JU3u3pST+w5dgcfOWz03LVnazOYETm+QMVi0JUIDJMhQk5WNF08QGKKlfdfl7e5+Bd4Q0FgegC/KJczTeKpRyDCHy3FHJ3uezJ5GFib0zEodUoNN5VgadRE8w73ZW4sbBGUrEO0EnEwL3ucAIi2FBTakoud23xOHPU1fu6aMwF7DziqyCGlGbXXfTeY+71LniuMxV7rZGYnMnOlXlpwCV/Kho8V5dmlRAgMBAAECggEAdfOLq/juvlzPsVQkdZDmCg69cfoC2B296BF7VdcenbZTpQkECj7Av3/0lB4lj63usQNBbEFhUEpF2BbfXOuLxIW9+tiN5UygLK/BdXAhUMopMIttHnh6SnZqhB62f/gn57OQWtHTA9ZInzQ2oxS54QVEIa/IJ/ofY/uIXfWioNDAnc/g0cdphWiD1gPF/n37klfeNmt72gioTNDspadxkxACPbBUPGK4ONrxfuVmaBRnBeHlVyCA3497UpRxgLi52+RqLTNoChL9aOxdH0nLW0zbMj74pLX3xetXhCI36Ay+xucyEcMNrs07TusoNT98WvdCMRIXPABoV6HXpMAWwQKBgQDGa72c3xmrN00ew4QEFHfn5mKZCevSgHZDW8B89TT5xPy2CkX/eeI/DsEmVRAUybIqPCl0W/OxV9zmoByUaAXZ0lCGceWswWsCHSEFmER3wwgYn9bfAE/cpi61tlm05cRqeOLLoh4BvB0x1NjDByVT9Xl4N53slHJgFq20lGGN6QKBgQCy+zVbCSS/E2LtaAwPhVEJwu9terdEz8Kg8ZTG30D7xv8UGsdNN8L+pA+RSnxEqfizDOR1ahoMdDO+K+ll3EZ+/mVfwN7TvKMyxSl0Ll9jCZLwMyMhwGySzVzMY6nC1+Tcd8LtCL9dnHyEhGAsQFf6kIYDaRAnyCoR1mHveWjXKQKBgFqQqK4mYpf0mN22zdXy5bvSOySxE5M0EUazZUtjvHdYgyQv0j1Gi47OlDiD0OA8I3cFmMJEihljCi+12vWd+TWvuhNzfyp36fjABOeB1ZZ4TYS3yCJnCQykSWOlRJK94QXcAWCCsYGHNCgfh4SVCWOmAnhtokZuhzn2f/iRVWmpAoGAIW4cdCmMDIiBdNh3UDrVfR5E/EzsiFTKBKe64z5189Eiec3apgihSlC4e6nO9kBNLJxe27N6tBLMS1wGVzlCjZvUsYLj2+ajEOuUp5e1x1jVcjoK+NXpMa5cA+0lmzgaDjfsV7vHiqOc+l+DWyCVjj6KWX65PsT7XqONXV5u/6kCgYEAiOsW+cydTjp9V3fB0dBiT9GgK5Rml+Nj/LjSKaN7gHPbpLjHKDQ1oJoLzn4Evl3fvgcC6EiU6pSuenwlZv7kSc/yoQpuV2ORVFNn29JVb+TopUjDW20YUFcn0uqsuK2RZEaCeXBHleEOZ469JzfPJFEl2qfQ0VT/LmtlJsOTYfk=";

    public static String CHARSET = AlipayConstants.CHARSET_UTF8;
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlkANfiFgt1/ixk26fHHZ1KHrj7yZHenC280OW3Zu7OWLQ8QrIsY1EhijsTSYSoVQ02TEj8RD+a/9TMvUfub49dmFj6iviecOdVJKrEkPV1/owHtWeSFPPi17imY05hBfha7DqrKYqkcsrPeXcc3gmduLd3UXTsZIGCkB8rIlX74Q3DNY9gaBlX2Hecl23pE6l2GsHtASbolfUb6irArma2Ow+gneZbH4mJdreAcoX4xw3PV/55xPpYG9tCi865f769gLEBdN6VE4h5de0sZYTGcJQRc72auqe/djA+fStbnG00Ut5bvBgkgq3OBPHnH/b3AghjzHP2WdrjL1U3HK/wIDAQAB";
    public static String SIGNTYPE = "RSA2";
    public static String FORMAT = "json";
    public static String ALIGATEWAY = "https://openapi.alipay.com/gateway.do";


//    kuaidiniao
    //电商ID
    public static String EBusinessID = "1298144";
    //电商加密私钥，快递鸟提供，注意保管，不要泄漏
    public static String AppKey = "401928e7-f4fb-4a89-b6fb-795ff6a9f842";
    //请求url
    public static String ReqURL = "http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx";


    //rediskey
    public static String REDIS_COMMONDITY_USER_SEARCH_HISTORY = "CommondityUserSearchHistory";


    //百度地图api
    public static String BAIDU_MAP = "http://api.map.baidu.com/geocoder/v2/?output=json&pois=1&ak=ElBFzvmXZH4yeruTkHCU9GOEqXhAsYDW&location=";


    /*统一错误码定义*/
    //是否成功
    public static boolean DATA_TRUE = true;
    public static boolean DATA_FALSE = false;

    // 请求成功
    public static String DATA_SUCCESS_CODE = "200";
    public static String DATA_SUCCESS_STR = "成功";
    // 请求失败
    public static String DATA_ERROR_CODE = "500";
    public static String DATA_ERROR_STR = "失败";

    public static String DATA_EXCPTION_CODE = "902";
    public static String DATA_EXCPTION_STR = "数据传入异常";


    public static String DATA_CHECK_CODE = "800";
    public static String DATA_CHECK_STR = "验证无效";



    public static String DATA_VILIDATE_ID_CODE = "516";
    public static String DATA_VILIDATE_ID_STR = "身份证输入错误";



    public static String SMS_CODE_FAST_STR = "短信验证码发送过频繁";
    public static String SMS_CODE_FAST_CODE = "526";

    public static String SMS_CODE_OVER_CODE = "验证码超出同模板同号码天发送上限";
    public static String SMS_CODE_OVER_STR = "527";

    public static String SMS_CODE_EXPIRED_STR = "528";
    public static String SMS_CODE_EXPRIED_STR = "验证码过期";

    public static String API_GET_USER_ID = "http://192.168.1.110/www/quanminjiankang/wechat/api/apporder/makeUserId";
    public static String API_GET_ORDERTIME = "http://192.168.1.110/WWW/quanminjiankang/wechat/api/apporder/ordertime";


    //jedis
    public static String REDIS_KEY_ORDERNUMBER = "REDIS_KEY_ORDERNUMBER";//订单编号后五位


}

