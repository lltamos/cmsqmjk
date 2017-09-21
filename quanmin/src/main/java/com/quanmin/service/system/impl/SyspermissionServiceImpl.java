package com.quanmin.service.system.impl;

import com.quanmin.dao.mapper.SysPermissionMapper;
import com.quanmin.model.SysPermission;
import com.quanmin.model.SysPermissionExample;
import com.quanmin.service.system.SyspermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yang on 2017/6/29.
 */
@Service
public class SyspermissionServiceImpl implements SyspermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> getPermissionList() {
        SysPermissionExample example = new SysPermissionExample();
        List<SysPermission> permissionList = sysPermissionMapper.selectByExample(example);
        return permissionList;
    }

    @Override
    public List<SysPermission> selectPermissionByRoleId(Integer roleId) {
        List<SysPermission> list = sysPermissionMapper.selectPermissionByRoleId(roleId);


        return list;
    }
}
