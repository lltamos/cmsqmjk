package com.quanmin.controller.cms;

import com.quanmin.service.AccountBlogService;
import com.quanmin.util.ResultUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/cms/1/blog")
public class CMSAccountBlogController {

    @Resource
    private AccountBlogService accountBlogService;

    /**
     * 入账记录
     * @param type 0分页，1导出
     */
    @RequestMapping("inaccount")
    @ResponseBody
    public ResultUtils inAccount(Integer type, Integer size, Integer page, String querystr) {
        return accountBlogService.inAccount(type, size, page, querystr);
    }

    /**
     * 出账记录
     */
    @RequestMapping("outaccount")
    @ResponseBody
    public ResultUtils outAccount(Integer type, Integer size, Integer page, String querystr) {
        return accountBlogService.outAccount(type, size, page, querystr);
    }
}
