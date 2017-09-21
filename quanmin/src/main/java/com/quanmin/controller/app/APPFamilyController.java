package com.quanmin.controller.app;

import com.quanmin.model.custom.FamilyInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quanmin.model.Family;
import com.quanmin.model.Feedback;
import com.quanmin.service.APPFamilyService;
import com.quanmin.service.APPFeedbackService;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.ResultUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 家庭成员
 *
 * @author heasy
 */
@Controller
@RequestMapping(value = "/api/1/")
public class APPFamilyController {

    Logger logger = Logger.getLogger(APPFamilyController.class);

    @Autowired
    private APPFamilyService familyService;

    /**
     * 保存家庭成员
     *
     * @param family
     * @param userId
     * @return
     */
    @Deprecated
    @RequestMapping(value = "/savefamily")
    @ResponseBody
    public String saveFamily(Family family, Integer userId, String appellation) {
        ResultUtils result = familyService.saveFamily(family, userId, appellation);
        return JsonUtils.objectToJson(result);
    }

    /**
     * 保存家庭成员
     *
     * @param family
     * @param userId
     * @param appellation
     * @param type
     * @return
     * @since 2.2
     */
    @PostMapping(value = "/savefamilymember")
    @ResponseBody
    public String saveFamilyMember(Family family, Integer userId, String appellation, Integer type) {
        ResultUtils result = familyService.saveFamilyMember(family, userId, appellation, type);
        return JsonUtils.objectToJson(result);
    }

    @Deprecated
    /**
     * 展示家庭成员
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/showfamily")
    @ResponseBody
    public String showFamily(Integer id) {
        ResultUtils result = familyService.showFamilyByUserId(id);
        return JsonUtils.objectToJson(result);
    }

    /**
     * 删除家庭信息
     *
     * @param id
     * @param familyId
     * @return
     */
    @RequestMapping(value = "/deletefamily")
    @ResponseBody
    public String deleteFamily(Integer id, Integer familyId) {
        ResultUtils result = familyService.deleteFamily(id, familyId);
        return JsonUtils.objectToJson(result);
    }


    /**
     * 展示家庭成员列表
     *
     * @param userId
     * @return
     * @since 2.2版本
     */
    @PostMapping(value = "/showfamilylist")
    @ResponseBody
    public ResultUtils showFamilyList(Integer userId) {

            List<FamilyInfo> result = familyService.showFamilyListByUserId(userId);
            return null != result && result.size() > 0 ? ResultUtils.returnSucess(result) : ResultUtils.returnFail();
    }

    /**
     * 申请查看家属报告
     *
     * @param userFamilyId
     * @param userId
     * @return
     */
    @PostMapping(value = "/applycheckreport")
    @ResponseBody
    public ResultUtils applyCheckReportByUserId(Integer userFamilyId, Integer userId) {
            ResultUtils result = familyService.applyCheckReportByUserId(userFamilyId, userId);
            return result;
    }

    /**
     * 校验查看家属报告验证码
     * @param phone
     * @param code
     * @return
     */
    @PostMapping(value = "/checkreportcode")
    @ResponseBody
    public ResultUtils checkReportCode(String idNo,Integer userFamilyId,String phone, String code) {
        ResultUtils result= familyService.checkReportCode(idNo,phone,code,userFamilyId);
        return result;
    }

    /**
     * 查看家属报告
     * @param phone
     * @param idNo
     * @return
     */
    @PostMapping(value = "/checkfamilymembersreport")
    @ResponseBody
    public ResultUtils checkFamilymembersReportByUserFamilyId(String phone,String idNo) {
        ResultUtils result= familyService.checkFamilymembersReportByUserFamilyId(phone,idNo);
        return result;
    }
}
