package com.quanmin.controller.cms.system;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quanmin.model.SysDepartment;
import com.quanmin.model.SysUser;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.custom.SysUserAndDepartmentInfo;
import com.quanmin.service.system.SystemUserService;
import com.quanmin.util.Commons;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2017/7/3.
 * 用户管理
 */
@RestController
@RequestMapping("/cms/1/")
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    /**
     * 根据条件获取账户列表
     *
     * @param request
     * @param response
     * @param searchCondition
     * @return
     */
    @RequestMapping("getsystemlist")
    public ResultUtils getSystemListByCondition(HttpServletRequest request, HttpServletResponse response, SearchCondition searchCondition) {
        ResultUtils resultUtils = new ResultUtils();
        Map<String, Object> map = new HashMap<>();
        if (null != searchCondition.getSize() && null != searchCondition.getPage()) {
            PageHelper.startPage(searchCondition.getPage(), searchCondition.getSize(), true);
        }
        List<SysUserAndDepartmentInfo> infoList = systemUserService.getSystemListByCondition(searchCondition);
        map.put("infolist", null != infoList && infoList.size() > 0 ? infoList : "");

        if (null != map && map.size() > 0) {
            resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
            resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
            resultUtils.setSuccess(Commons.DATA_TRUE);
            if (null != searchCondition.getSize() && null != searchCondition.getPage()) {
                resultUtils.setCount(((Page) infoList).getPages());
            }
            resultUtils.setValue(map);
            return resultUtils;
        }

        resultUtils.setSuccess(Commons.DATA_FALSE);
        resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
        resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
        return resultUtils;
    }

    /**
     * 获取部门信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("getdepartment")
    public ResultUtils getDepartmentId(HttpServletRequest request, HttpServletResponse response) {
        ResultUtils resultUtils = new ResultUtils();
        List<SysDepartment> res = systemUserService.getDepartmentId();

        if (null != res) {
            resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
            resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
            resultUtils.setSuccess(Commons.DATA_TRUE);
            resultUtils.setValue(res);

            return resultUtils;
        }

        resultUtils.setSuccess(Commons.DATA_FALSE);
        resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
        resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
        return resultUtils;
    }

    /**
     * 获取部门信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("saveorupdate")
    public ResultUtils saveOrUpdateByUserId(HttpServletRequest request, HttpServletResponse response, Integer userId, SysUser sysUser, Integer roleId, Integer statusType) {
        ResultUtils resultUtils = new ResultUtils();
        Integer integer = systemUserService.saveOrUpdateByUserId(userId, sysUser, roleId, statusType);

        if (null != integer) {
            resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
            resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
            resultUtils.setSuccess(Commons.DATA_TRUE);
            return resultUtils;
        }

        resultUtils.setSuccess(Commons.DATA_FALSE);
        resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
        resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
        return resultUtils;
    }

    @RequestMapping("getuserinfo")
    public ResultUtils selectUserInfoByUserId(HttpServletRequest request, HttpServletResponse response, Integer userId) {
        ResultUtils resultUtils = new ResultUtils();

        Map<String, Object> map = systemUserService.selectUserInfoByUserId(userId);

        if (null != map && map.size() > 0) {
            resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
            resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
            resultUtils.setSuccess(Commons.DATA_TRUE);
            resultUtils.setValue(map);
            return resultUtils;
        }

        resultUtils.setSuccess(Commons.DATA_FALSE);
        resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
        resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
        return resultUtils;
    }


}
