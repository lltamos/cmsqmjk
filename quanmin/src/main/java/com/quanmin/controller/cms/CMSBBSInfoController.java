package com.quanmin.controller.cms;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quanmin.call.UserReportPDFInfo;
import com.quanmin.model.BBSInformation;
import com.quanmin.model.SysUser;
import com.quanmin.model.custom.BBSInformationAndUser;
import com.quanmin.model.custom.CommentInfo;
import com.quanmin.model.custom.FamilyInfo;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.service.CMSBBSInfoService;
import com.quanmin.service.CMSUserService;
import com.quanmin.util.Commons;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yang on 2017/6/27.
 */
@RestController
@RequestMapping("/cms/1/")
public class CMSBBSInfoController {

    @Autowired
    private CMSBBSInfoService infoService;

    @RequestMapping("/bbslist")
    public ResultUtils getBBsListByCondition(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, SearchCondition searchCondition) {
        ResultUtils resultUtils = new ResultUtils();
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(searchCondition.getPage(), searchCondition.getSize(), true);
        List<BBSInformationAndUser> list = infoService.getBBsListByCondition(searchCondition);
        if (null != list && list.size() > 0) {
            map.put("list", list);
            resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
            resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
            resultUtils.setSuccess(Commons.DATA_TRUE);
            resultUtils.setValue(map);
            resultUtils.setCount(((Page) list).getPages());
        } else {
            resultUtils.setMsg(Commons.DATA_ERROR_STR);
            resultUtils.setResultCode(Commons.DATA_ERROR_CODE);
            resultUtils.setSuccess(Commons.DATA_FALSE);
        }
        return resultUtils;
    }

    /**
     * 删除帖子
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param searchCondition
     * @return
     */
    @RequestMapping("/deletebbsinfo")
    public ResultUtils deleteByCondition(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, SearchCondition searchCondition) {
        ResultUtils resultUtils = new ResultUtils();
        Map<String, Object> map = new HashMap<>();
        Integer i = infoService.deleteByCondition(searchCondition);
        if (i > 0) {
            resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
            resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
            resultUtils.setSuccess(Commons.DATA_TRUE);
        } else {
            resultUtils.setMsg(Commons.DATA_ERROR_STR);
            resultUtils.setResultCode(Commons.DATA_ERROR_CODE);
            resultUtils.setSuccess(Commons.DATA_FALSE);
        }
        return resultUtils;
    }

    /**
     * 查看单个帖子
     * @param httpServletRequest
     * @param httpServletResponse
     * @param searchCondition
     * @return
     */
    @RequestMapping("/bbsoneinfo")
    public ResultUtils selectBBSInfoOneByCondition(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, SearchCondition searchCondition) {
        ResultUtils resultUtils = new ResultUtils();
        Map<String, Object> map = new HashMap<>();
        BBSInformation information = infoService.showCMSBBSInfoById(searchCondition);
        List<CommentInfo> list = infoService.showCMSBBSCommentById(searchCondition);
        if (null!=information&&list.size()>0) {
            map.put("commentInfo",list);
        }else{
            map.put("commentInfo","");
        }
        if (null!=information) {
            map.put("bbsinformation",information);
            resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
            resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
            resultUtils.setSuccess(Commons.DATA_TRUE);
            resultUtils.setValue(map);
        } else {
            resultUtils.setMsg(Commons.DATA_ERROR_STR);
            resultUtils.setResultCode(Commons.DATA_ERROR_CODE);
            resultUtils.setSuccess(Commons.DATA_FALSE);
        }
        return resultUtils;
    }

    /**
     * 删除评论
     * @param httpServletRequest
     * @param httpServletResponse
     * @param commentId
     * @return
     */
    @PostMapping("/deletecomment")
    public ResultUtils deleteCommentByCOmmentId(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Integer commentId) {
     Integer result=   infoService.deleteCommentByCOmmentId(commentId);
     return  result>0?ResultUtils.returnSucess():ResultUtils.returnFail();
    }
}
