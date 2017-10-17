package com.quanmin.controller.app;

import com.quanmin.model.Information;
import com.quanmin.model.custom.LableInformation;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.service.APPInformationService;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 内容管理
 *
 * @author heasy
 */
@Controller
@RequestMapping(value = "/api/1/")
public class APPInformationController {

    @Autowired
    private APPInformationService informationService;

    /**
     * 首页资讯列表接口
     *
     * @param searchCondition
     * @return
     */
    @RequestMapping(value = "/showindexinformation")
    @ResponseBody
    public ResultUtils showIndexInformation(SearchCondition searchCondition) {
        ResultUtils result = informationService.showIndexInformation(searchCondition);
        return result;
    }

    /**
     * 资讯详细内容
     *
     * @param searchCondition
     * @return
     */
    @Deprecated
    @RequestMapping(value = "/informationdetail")
    @ResponseBody
    public ResultUtils showInformationdetail(SearchCondition searchCondition) {
        ResultUtils result = informationService.showInformationdetail(searchCondition);
        return result;
    }

    /**
     * 资讯详细内容
     *
     * @since 2.2
     *
     * @param searchCondition
     * @return
     */

    @RequestMapping(value = "/getinformationdetail")
    @ResponseBody
    public ResultUtils getInformationdetail(SearchCondition searchCondition) {
        LableInformation result = informationService.getInformationdetail(searchCondition);
        return null!=result?ResultUtils.returnSucess(result): ResultUtils.returnFail();
    }


    /**
     * 根据type进行资讯查询
     */
    @RequestMapping(value = "/showinformationlist")
    @ResponseBody
    public ResultUtils showInformationListByCondition(SearchCondition searchCondition) {
        ResultUtils result = informationService.showInformationListByCondition(searchCondition);
        return result;
    }



}
