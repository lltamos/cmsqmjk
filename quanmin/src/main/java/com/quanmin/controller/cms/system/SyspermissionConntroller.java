package com.quanmin.controller.cms.system;

import com.quanmin.model.SysPermission;
import com.quanmin.service.system.SyspermissionService;
import com.quanmin.systemutils.ZTreeResult;
import com.quanmin.util.Commons;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang on 2017/6/29.
 */

/**
 * 关于功能
 */
@RestController
@RequestMapping("system/1/")
public class SyspermissionConntroller {

    @Autowired
    private SyspermissionService syspermissionService;

    /**
     * 查看所有权限列表
     *
     * @param request
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("permissionlist")
    public ResultUtils getPermissionList(HttpServletRequest request, HttpServletResponse httpServletResponse) {

        ResultUtils resultUtils = new ResultUtils();

        List<SysPermission> syspermissionList = syspermissionService.getPermissionList();

        List<ZTreeResult> zTreeResultList = new ArrayList<>();
        if (null != syspermissionList && syspermissionList.size() > 0) {

            for (SysPermission permission : syspermissionList) {
                ZTreeResult zTreeResult = new ZTreeResult();
                zTreeResult.setId(permission.getId());
                zTreeResult.setName(permission.getPermissionName());
                zTreeResult.setpId(permission.getPid());
                zTreeResult.setOpen(true);
                zTreeResultList.add(zTreeResult);
            }
        }
        if (null != zTreeResultList && zTreeResultList.size() > 0) {
            resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
            resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
            resultUtils.setSuccess(Commons.DATA_TRUE);
            resultUtils.setValue(zTreeResultList);
            return resultUtils;
        }
        resultUtils.setSuccess(Commons.DATA_FALSE);
        resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
        resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
        return resultUtils;


    }
}
