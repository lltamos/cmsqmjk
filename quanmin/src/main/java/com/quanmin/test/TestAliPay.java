package com.quanmin.test;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.quanmin.util.Commons;

/**
 * Created by yang on 2017/6/16.
 */
public class TestAliPay {
//
//    public static String URL = "https://openapi.alipay.com/gateway.do";
//    public static String APP_ID = "2017061407488251";
//    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCKuZ7U2ZK9FYh19W62kZY/OnL6AIu+24ulyVFRZ+h2oBgWvCKxD/NsIGqtM/HSV0plwEWbvarqLjMNSPUNGiOo/mFtIvXXdq2n5Jw+Uohnlijo2lcWF2f3JU3u3pST+w5dgcfOWz03LVnazOYETm+QMVi0JUIDJMhQk5WNF08QGKKlfdfl7e5+Bd4Q0FgegC/KJczTeKpRyDCHy3FHJ3uezJ5GFib0zEodUoNN5VgadRE8w73ZW4sbBGUrEO0EnEwL3ucAIi2FBTakoud23xOHPU1fu6aMwF7DziqyCGlGbXXfTeY+71LniuMxV7rZGYnMnOlXlpwCV/Kho8V5dmlRAgMBAAECggEAdfOLq/juvlzPsVQkdZDmCg69cfoC2B296BF7VdcenbZTpQkECj7Av3/0lB4lj63usQNBbEFhUEpF2BbfXOuLxIW9+tiN5UygLK/BdXAhUMopMIttHnh6SnZqhB62f/gn57OQWtHTA9ZInzQ2oxS54QVEIa/IJ/ofY/uIXfWioNDAnc/g0cdphWiD1gPF/n37klfeNmt72gioTNDspadxkxACPbBUPGK4ONrxfuVmaBRnBeHlVyCA3497UpRxgLi52+RqLTNoChL9aOxdH0nLW0zbMj74pLX3xetXhCI36Ay+xucyEcMNrs07TusoNT98WvdCMRIXPABoV6HXpMAWwQKBgQDGa72c3xmrN00ew4QEFHfn5mKZCevSgHZDW8B89TT5xPy2CkX/eeI/DsEmVRAUybIqPCl0W/OxV9zmoByUaAXZ0lCGceWswWsCHSEFmER3wwgYn9bfAE/cpi61tlm05cRqeOLLoh4BvB0x1NjDByVT9Xl4N53slHJgFq20lGGN6QKBgQCy+zVbCSS/E2LtaAwPhVEJwu9terdEz8Kg8ZTG30D7xv8UGsdNN8L+pA+RSnxEqfizDOR1ahoMdDO+K+ll3EZ+/mVfwN7TvKMyxSl0Ll9jCZLwMyMhwGySzVzMY6nC1+Tcd8LtCL9dnHyEhGAsQFf6kIYDaRAnyCoR1mHveWjXKQKBgFqQqK4mYpf0mN22zdXy5bvSOySxE5M0EUazZUtjvHdYgyQv0j1Gi47OlDiD0OA8I3cFmMJEihljCi+12vWd+TWvuhNzfyp36fjABOeB1ZZ4TYS3yCJnCQykSWOlRJK94QXcAWCCsYGHNCgfh4SVCWOmAnhtokZuhzn2f/iRVWmpAoGAIW4cdCmMDIiBdNh3UDrVfR5E/EzsiFTKBKe64z5189Eiec3apgihSlC4e6nO9kBNLJxe27N6tBLMS1wGVzlCjZvUsYLj2+ajEOuUp5e1x1jVcjoK+NXpMa5cA+0lmzgaDjfsV7vHiqOc+l+DWyCVjj6KWX65PsT7XqONXV5u/6kCgYEAiOsW+cydTjp9V3fB0dBiT9GgK5Rml+Nj/LjSKaN7gHPbpLjHKDQ1oJoLzn4Evl3fvgcC6EiU6pSuenwlZv7kSc/yoQpuV2ORVFNn29JVb+TopUjDW20YUFcn0uqsuK2RZEaCeXBHleEOZ469JzfPJFEl2qfQ0VT/LmtlJsOTYfk=";
//    public static String APP_RSA_PRIVATE = "";
//    public static String CHARSET = AlipayConstants.CHARSET_UTF8;
//    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlkANfiFgt1/ixk26fHHZ1KHrj7yZHenC280OW3Zu7OWLQ8QrIsY1EhijsTSYSoVQ02TEj8RD+a/9TMvUfub49dmFj6iviecOdVJKrEkPV1/owHtWeSFPPi17imY05hBfha7DqrKYqkcsrPeXcc3gmduLd3UXTsZIGCkB8rIlX74Q3DNY9gaBlX2Hecl23pE6l2GsHtASbolfUb6irArma2Ow+gneZbH4mJdreAcoX4xw3PV/55xPpYG9tCi865f769gLEBdN6VE4h5de0sZYTGcJQRc72auqe/djA+fStbnG00Ut5bvBgkgq3OBPHnH/b3AghjzHP2WdrjL1U3HK/wIDAQAB";
//    public static String signType = "RSA2";

//    public static String URL = "https://openapi.alipaydev.com/gateway.do";
//    public static String APP_ID = "2016080600180249";
//    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDftozxt+YeyBPCjeQuuRq7X2sSTiP/4LNsyhZzDzR++d6JBCEqJ9bvJHcBcCcIDoO7XoqBJRluLRPPdID2Xg/i+0x2mF7rbV6BeKCrHjERi+MByhW5HyDB0coUX4j29+VlcnqciGvSq9WS2dQnTrUfikAkJgrQc1hdi5py6lLi+vBeLJlHCE3qwHoaKjI5tzoD9sQ2j+tTGpFnZEhpmQJUNtFLhfSewC48coE+j4cTc7PFDvovwXTOlSZstY1wZCkXpx954FAzCCKUa4aq35hgApL7bMzPu6NE6kefJ10ThkPSQNRe+btxSycH6SpIgPhHqJ8xkSq0lBeTc3+07EDhAgMBAAECggEAeLMGYlgE+XCbcKmbhjXqnVOC8lJki+aRB84UxKlcenGXxjcLKbJq/YWgC4+WD3u9STdd5QW+Lbp2aMSD2+iuC5noDEhzVfQhPBt4HDri4IOny8xbYhDQXKIytEFDJXRkORLF4nScMALz7RJHq5mEkWrG5nn79bF16Kqsfvm8FKerAWCCg/vQF15zVyMVKzCFe5y71oBXm6JyGhLfbNjTobotzAvmoMLPE3RTIE4C5b1gq9RpGzUTwMjeJY3pchghSdZMSsqcUuEW50czGZgllnJ7kikus/YVqD5jDCDzQLmVWWQu0kSmjRV/L/9/AiipY/1q1/DoxUcj7y24idflbQKBgQD0u1ptHSXAGKzi4cR7viIEqazKEatRRzH8WhGEKqhyTXRP3s81ISy6Zz+7JVths6hoID0H/hJ8TmpZvZMTmAsI6MmvDr6Js6Wg0a7Pmf5bNGnflO/eE0fGBiLLpdeVjK9+46jbtRvcKeUOJrAA6lTOR+Mv3BLlcMO1OrWVEyrDUwKBgQDqA3Mplv87LoGIVnn/RaAMkZRX6G/+Pcq57dCdxj8mS9wlW+AxOCI9zHE2/qzdXAb+ZgUA0H/S7uRA1TAUeGJ7+sTOwV5aEUSt9zKYLJt64UUXxXi/sljY5D/pqBBpdZ25fjIWk3hJ6XHWqoqrc62CpgLELzePMQFL1clsSDj4ewKBgQDlKerq8bg/wNtjOHV8SfYg16FtNk5ttXUhEFYGpMtG/TBE+iadYYs4oSYUYE7JlinEFywPJ+K3c9YyYkouttjeZt00h2H0H7ygcrh2vYRocxQQwK48DWHQFD7Q42KUL0DGWPfthBGqf7B1J9T3hnYTE02xqyomnwkj0u3lwiTw8QKBgFbBAKiieeyOySaqKIAiZ7tBHSWZ3ir67XUQXQ6guDiSVwLilLKqhMc0S6R/tcY6n70gRSqQIVMpjSUb478zAg/EX8Hmnfv1+ugjhHY1004yxVKSurkWfSmrNKaCz9LI77gcuKT19tov2Swj/VSiheixcsUYXBiRqlFa6FlkTPqRAoGAQk3rm2sxaH0tZQ99AB0Jjbp2quvTVBE6R8123cB/46v5n5fhyfuOT/zFDDrd2UYLF5jSMX6ZL3Cs/dlL70nnVkQhxzTweIQ37ggg77Pln46eqasaQcZ8PEy/hewxbUYAn7dWk99sjO5oL0vBItEK2SEvtbI7Avs6ESOF4JHYTIA=";
//    public static String CHARSET = AlipayConstants.CHARSET_UTF8;
//    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx0HSI4e/noqIBtA/9cs4E/UANZL2flt2gg3VerIt5Oqah1nTrZj1CQS9p6XHhA6ocultlwNP1fY24Qh4z8/tij0yxxl86ucXvXkYr3QiaVryBQZzW95uNQUpn09OjvBFnSFO4ouZ4VJmXujrnhU4GXhlAFVu7NpbNzjnPIvshuMDOglf4LhkQd6RKNjS8kOFGoyup+mHTz9EsZ93Z7fBqdaNM89FgjV+nwBmExelgRvp7LUG42+KMI7z8wpSZCQG5AKhxYkNYKDVpul/Si4xSHJM5YZDx08bpIofGgwvKvSGXkqgB16+ERRjtZu0thJHu9+cZCQi58RUIW3WTOSGiQIDAQAB";
//    public static String signType = "RSA2";
//    public static String APP_RSA_PRIVATE = "";


    public static void main(String[] args) throws Exception {
        //实例化客户端
//        AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, signType);
//        判断是使用哪种方式进行加密
//        boolean rsa2 = (APP_PRIVATE_KEY.length() > 0);
//
//        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APP_ID, rsa2);
//        System.out.println(JSON.toJSON(params));

//        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
//        String privateKey = rsa2 ? APP_PRIVATE_KEY : APP_RSA_PRIVATE;
//        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
//        System.out.println(sign);
//        final String orderInfo = orderParam + "&" + sign;
//        String s = AlipaySignature.rsaSign(JSON.toJSON(params).toString(), APP_PRIVATE_KEY, CHARSET);
//        System.out.println(s);

//        System.out.println(orderInfo);

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", Commons.APP_ID, Commons.APP_PRIVATE_KEY, "json", Commons.CHARSET, Commons.ALIPAY_PUBLIC_KEY, "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo("20150320010101001");
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("商户外网可以访问的异步地址");

        //这里和普通的接口调用不同，使用的是sdkExecute
        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。


    }
}