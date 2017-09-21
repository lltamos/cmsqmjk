package com.quanmin.util;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

import java.util.HashMap;
import java.util.Set;

public class SendSMSUtil {
    public static Integer sendSMS(String phone, String templateId, String[] datas)  {
        HashMap<String, Object> result = null;
        String accountSid = LoadPropertiesDataUtils.getValue("qm.sms.account.sid");
        String accountToken = LoadPropertiesDataUtils.getValue("qm.sms.auth.token");
        String appId = LoadPropertiesDataUtils.getValue("qm.sms.app.id");
        String serverIP = LoadPropertiesDataUtils.getValue("qm.sms.rest.url");
        String serverPort = LoadPropertiesDataUtils.getValue("qm.sms.port");

        //初始化SDK
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

        //******************************注释*********************************************
        //*初始化服务器地址和端口                                                       *
        //*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
        //*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
        //*******************************************************************************
        restAPI.init(serverIP, serverPort);

        //******************************注释*********************************************
        //*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
        //*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
        //*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
        //*******************************************************************************
        restAPI.setAccount(accountSid, accountToken);


        //String urlString = "UserID=707951&Account=13501391482&Password=2AL8DOWE&Content=您的注册验证码为9869，该验证码10分钟内有效。如非本人操作请忽略此短信！【浪驰软件】&Phones=13501391482&ReturnXJ=1";
//        String urlString = "UserID=707951&Account=13501391482&Password=672458B4728C9AB8C98BE67C2B5E5888399B0C30&Content=您的注册验证码为8986，该验证码10分钟内有效。如非本人操作请忽略此短信！【浪驰软件】&Phones=13691475903&ReturnXJ=1";


        //******************************注释*********************************************
        //*初始化应用ID                                                                 *
        //*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
        //*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
        //*******************************************************************************
        restAPI.setAppId(appId);


        //******************************注释****************************************************************
        //*调用发送模板短信的接口发送短信                                                                  *
        //*参数顺序说明：                                                                                  *
        //*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号                          *
        //*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。    *
        //*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
        //*第三个参数是要替换的内容数组。																														       *
        //**************************************************************************************************

        //**************************************举例说明***********************************************************************
        //*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为           *
        //*result = restAPI.sendTemplateSMS("13800000000","1" ,new String[]{"6532","5"});																		  *
        //*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入     *
        //*********************************************************************************************************************
//        result = restAPI.sendTemplateSMS(phone, "167058", new String[]{code, "5"});
        result = restAPI.sendTemplateSMS(phone, templateId, datas);

        System.out.println("SDKTestGetSubAccounts result=" + result);

        if ("000000".equals(result.get("statusCode"))) {
            //正常返回输出data包体信息（map）
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for (String key : keySet) {
                Object object = data.get(key);
                System.out.println(key + " = " + object);
            }
            return 0;
        } else   if ("160038".equals(result.get("statusCode"))) {
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
            return 1;
        }
        else   if ("160040".equals(result.get("statusCode")))    {
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
            return 2;
        }else{
            return 3;
        }
    }

    public static void main(String[] args) {
        ResultUtils resultUtils = new ResultUtils();
        Integer result = SendSMSUtil.sendSMS("17778067291", "167058",new String[]{1324+"", "5"});
        switch (result) {
            case 0:
                // 插入到数据库中
//                Integer coderes = loginService.getCodeLogin(phone, code);
//                if (coderes == 0) {
//                    return ResultUtils.returnSucess("发送成功");
//                } else {
//                    return ResultUtils.returnFail("发送失败");
//                }
            case 1:
                resultUtils.setMsg(Commons.SMS_CODE_FAST_STR);
                resultUtils.setResultCode(Commons.SMS_CODE_FAST_CODE);
                break;
            case 2:
                resultUtils.setMsg(Commons.SMS_CODE_OVER_CODE);
                resultUtils.setResultCode(Commons.SMS_CODE_OVER_STR);
                break;
            case 3:
//                return ResultUtils.returnFail("发送失败");
        }

    }
}
