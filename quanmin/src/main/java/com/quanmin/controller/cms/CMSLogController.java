package com.quanmin.controller.cms;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quanmin.controller.app.APPFamilyController;
import com.quanmin.model.custom.FamilyInfo;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.custom.SysLogVO;
import com.quanmin.service.CMSLogService;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.ResultUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: By heasy.
 * @Date: 2017/9/14.
 * @Contcat: yz972641975@gmail.com.
 * @Description: 日志管理
 * @Modified By:
 */
@RestController
@RequestMapping("cms/1/")
public class CMSLogController {

    Logger logger = Logger.getLogger(CMSLogController.class);
    @Autowired
    private CMSLogService cmsLogService;


    /**
     * 日志管理列表
     *
     * @param condition
     * @return
     */
    @PostMapping(value = "listLog")
    @ResponseBody
    public ResultUtils listLogByCondition(SearchCondition condition) {

        PageHelper.startPage(condition.getPage(), condition.getStartNum());
        List<SysLogVO> result = cmsLogService.listLogByCondition(condition);
        return null != result && result.size() > 0 ? ResultUtils.returnSucess(result, ((Page) result).getPages()) : ResultUtils.returnFail();

    }
}
