package com.quanmin.service.system;

import com.quanmin.model.SysPermission;

import java.util.List;

/**
 * Created by yang on 2017/6/29.
 */
public interface SyspermissionService {
    /**
     * 获取所有权限列表
     *
     * @return
     */
    List<SysPermission> getPermissionList();

    /**
     * 根据角色id查看权限
     *
     * @param roleId
     * @return
     */
    List<SysPermission> selectPermissionByRoleId(Integer roleId);
}
