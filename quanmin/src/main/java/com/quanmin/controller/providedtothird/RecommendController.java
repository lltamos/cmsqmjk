package com.quanmin.controller.providedtothird;

import com.quanmin.service.RecommendService;
import com.quanmin.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: By heasy.
 * @Date: 2017/9/25.
 * @Contcat: yz972641975@gmail.com.
 * @Description: 第三方推荐
 * @Modified By:
 */
@RestController
@RequestMapping("/third/1")
@Api(value = "RecommendController 推荐接口")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @ApiOperation("获取短信验证码")
    @RequestMapping(value = "/getcode", method = RequestMethod.POST)
    public ResultUtils getCode(@ApiParam(required = true, name = "phone", value = "手机号", type = "String") @RequestParam(value = "phone") String phone) {

        ResultUtils resultUtils = new ResultUtils();
        resultUtils.setSuccess(false);
        resultUtils.setValue("");
        // 生成4位随机数
        int code = RandomUtils.getRandom4();

        boolean phoneLegal = PhoneFormatCheckUtils.isPhoneLegal(phone);
        if (!phoneLegal) {
            return ResultUtils.returnFail(Commons.SMS_CODE_PHONE_CODE, Commons.SMS_CODE_PHONE_STR);
        }
        Integer result = SendSmsUtil.sendSMS(phone, "207532", new String[]{code + "", "5"});
        switch (result) {
            case 0:
                // 插入到数据库中
                Integer coderes = recommendService.insertCode(phone, code);
                if (coderes >= 0) {
                    return ResultUtils.returnSucess(code);
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
            default:
                return ResultUtils.returnFail("发送失败");
        }

        return resultUtils;
    }


    @ApiOperation("注册")
    @RequestMapping(value = "/applyregister", method = RequestMethod.POST)
    public ResultUtils applyRegister(@ApiParam(required = true, name = "referralphone", value = "推荐人手机号", type = "String") @RequestParam(value = "referralphone") String referral, @ApiParam(required = true, name = "refereephone", value = "被推荐人手机号", type = "String") @RequestParam(value = "refereephone") String referee, @ApiParam(required = true, name = "code", value = "验证码", type = "String") @RequestParam(value = "code") String code) {
        boolean a = PhoneFormatCheckUtils.isPhoneLegal(referral);
        boolean b = PhoneFormatCheckUtils.isPhoneLegal(referee);
        if (!(a && b)) {
            return ResultUtils.returnFail(Commons.SMS_CODE_PHONE_CODE, Commons.SMS_CODE_PHONE_STR);
        }

        return recommendService.applyRegister(referee, referral, code);


    }
}
