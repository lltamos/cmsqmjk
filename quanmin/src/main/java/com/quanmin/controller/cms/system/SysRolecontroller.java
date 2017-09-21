package com.quanmin.controller.cms.system;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quanmin.model.SysRole;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.service.system.SysRoleService;
import com.quanmin.util.Commons;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2017/6/30.
 * 关于角色
 */
@RestController
@RequestMapping("/system/1/")
public class SysRolecontroller {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 编辑或新增角色
     *
     * @param request
     * @param httpServletResponse
     * @param sysRole
     * @param permissionIds
     * @param roleId
     * @return
     */
    @RequestMapping("saveorupdaterole")
    public ResultUtils saveRoleInfo(HttpServletRequest request, HttpServletResponse httpServletResponse, SysRole sysRole, String permissionIds, Integer roleId) {

        ResultUtils resultUtils = new ResultUtils();
        Integer i = sysRoleService.saveRoleInfo(sysRole, permissionIds, roleId);


        if (i > 0) {
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

    /**
     * 根据角色id查询拥有角色
     *
     * @param request
     * @param httpServletResponse
     * @param roleId
     * @return
     */
    @RequestMapping("getroleone")
    public ResultUtils getOneByRoleId(HttpServletRequest request, HttpServletResponse httpServletResponse, Integer roleId) {

        ResultUtils resultUtils = new ResultUtils();
        Map<String, Object> map = sysRoleService.selectRoleListOrOneByRoleId(roleId);

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

    /**
     * 根据角色id查询拥有角色
     *
     * @param request
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("getrolelist")
    public ResultUtils getRoleList(HttpServletRequest request, HttpServletResponse httpServletResponse, SearchCondition searchCondition) {
        ResultUtils resultUtils = new ResultUtils();
        if (null != searchCondition.getPage() && null != searchCondition.getSize()) {
            PageHelper.startPage(searchCondition.getPage(), searchCondition.getSize(), true);
        }

        List<SysRole> roleList = sysRoleService.getRoleList(searchCondition);

        if (null != roleList && roleList.size() > 0) {
            resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
            resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
            resultUtils.setSuccess(Commons.DATA_TRUE);
            resultUtils.setValue(roleList);
            if (null != searchCondition.getPage() && null != searchCondition.getSize()) {
                resultUtils.setCount(((Page) roleList).getPages());
            }
            return resultUtils;
        }
        resultUtils.setSuccess(Commons.DATA_FALSE);
        resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
        resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
        return resultUtils;
    }

    @RequestMapping("deleterole")
    public ResultUtils deleteRoleByRoleId(HttpServletRequest request, HttpServletResponse httpServletResponse, Integer roleId) {
        ResultUtils resultUtils = new ResultUtils();
        Integer i = sysRoleService.deleteRoleByRoleId(roleId);

        if (i > 0) {
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

    /**
     * 校验角色名称
     * @param request
     * @param httpServletResponse
     * @param rolename
     * @return
     */
    @RequestMapping("checkrolename")
    public ResultUtils checkRoleameByRoleName(HttpServletRequest request, HttpServletResponse httpServletResponse, String rolename) {
        ResultUtils resultUtils = new ResultUtils();
        Integer count=sysRoleService.checkRoleameByRoleName(rolename);
        if(null!=count&&count==0){
            resultUtils.setMsg("成功");
            resultUtils.setResultCode("200");
            resultUtils.setSuccess(true);
        }else {
            resultUtils.setMsg("失败");
            resultUtils.setResultCode("500");
            resultUtils.setSuccess(false);
        }
        return resultUtils;
    }
    }
