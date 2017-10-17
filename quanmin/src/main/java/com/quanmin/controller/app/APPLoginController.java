package com.quanmin.controller.app;

import com.quanmin.model.SysUser;
import com.quanmin.service.APPLoginService;
import com.quanmin.util.Commons;
import com.quanmin.util.RandomUtils;
import com.quanmin.util.ResultUtils;
import com.quanmin.util.SendSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录信息
 *
 * @author quanmin
 */
@Controller
@RequestMapping(value = "/api/1/")
public class APPLoginController {



    @Autowired
    private APPLoginService loginService;

    /**
     * 获取验证
     *
     * @param request
     * @param response
     * @param phone
     * @return
     */
    @RequestMapping(value = "/getcode")
    @ResponseBody
    public ResultUtils getCode(HttpServletRequest request, HttpServletResponse response, String phone) {
        ResultUtils resultUtils = new ResultUtils();
        resultUtils.setSuccess(false);
        resultUtils.setValue("");
        // 生成4位随机数
        int code = RandomUtils.getRandom4();

//        result = restAPI.sendTemplateSMS(phone, "167058", new String[]{code, "5"});

        Integer result = SendSmsUtil.sendSMS(phone, "167058",new String[]{code+"", "5"});
        switch (result) {
            case 0:
                // 插入到数据库中
                Integer coderes = loginService.getCodeLogin(phone, code);
                if (coderes >= 0) {
                    return ResultUtils.returnSucess("发送成功");
                } else {
                    return ResultUtils.returnFail("发送失败");
                }
            case 1:
                resultUtils.setMsg(Commons.SMS_CODE_FAST_STR);
                resultUtils.setResultCode(Commons.SMS_CODE_FAST_CODE);
                resultUtils.setSuccess(Commons.DATA_FALSE);
                break;
            case 2:
                resultUtils.setMsg(Commons.SMS_CODE_OVER_CODE);
                resultUtils.setResultCode(Commons.SMS_CODE_OVER_STR);
                resultUtils.setSuccess(Commons.DATA_FALSE);
                break;
            default :
                return ResultUtils.returnFail();
        }

        return resultUtils;
    }

    /**
     * 用户登录
     *
     * @param request
     * @param response
     * @param phone
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public ResultUtils userLogin(HttpServletRequest request, HttpServletResponse response, String phone, String code, String registrationId) {
        // 插入到数据库中
        ResultUtils rs = loginService.userLogin(phone, code, registrationId, request);
        return rs;
    }

    /**
     * 退出
     *
     * @param user
     * @param token
     * @return
     */
    @RequestMapping(value = "/loginout")
    @ResponseBody
    public ResultUtils logout(@ModelAttribute SysUser user, String token) {
        ResultUtils logout = loginService.logout(user, token);
        return logout;
    }

    /**
     * 校验身份证信息
     *
     * @return
     */
    @RequestMapping(value = "/checkcard")
    @ResponseBody
    public ResultUtils cheacIdCardByUserIdAndIdCard(@ModelAttribute SysUser user) {
        ResultUtils result = loginService.checkIdCardByUserIdAndIdCard(user);
        return result;
    }

    /**
     * 校验身份证号是否输入正确
     *
     * @param idCard
     * @return
     */
    @RequestMapping(value = "/validateidcard")
    @ResponseBody
    public ResultUtils validateidcard(String idCard) {
        ResultUtils result = loginService.validateidcard(idCard);
        return result;
    }

    /**
     * 生成reids缓存
     */
    @RequestMapping(value = "/generatecache")
    @ResponseBody
    public void generateCache() {
        ResultUtils result = loginService.generateCache();
    }

    /**
     * 保险审核临时登录，游客登录模式
     */
    @RequestMapping(value = "/touristslogin")
    @ResponseBody
    public ResultUtils touristsLogin(HttpServletRequest request, HttpServletResponse response) {
        return loginService.touristsLogin("18888888888", request, response);
    }

    /**
     * 修改昵称显示
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updatenickname")
    @ResponseBody
    public ResultUtils updatenickname(HttpServletRequest request, HttpServletResponse response) {
        return loginService.updatenickname();
    }


}
