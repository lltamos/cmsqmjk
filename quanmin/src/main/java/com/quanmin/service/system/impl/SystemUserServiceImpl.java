package com.quanmin.service.system.impl;

import com.quanmin.dao.mapper.SysDepartmentMapper;
import com.quanmin.dao.mapper.SysUserMapper;
import com.quanmin.dao.mapper.SysUserRoleMapper;
import com.quanmin.model.*;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.custom.SysUserAndDepartmentInfo;
import com.quanmin.service.system.SystemUserService;
import com.quanmin.util.EncryptUtils;
import com.quanmin.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2017/7/3.
 */
@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysDepartment> getDepartmentId() {
        SysDepartmentExample departmentExample = new SysDepartmentExample();
        List<SysDepartment> departmentList = sysDepartmentMapper.selectByExample(departmentExample);

        return null != departmentList && departmentList.size() > 0 ? departmentList : null;
    }

    @Override
    public List<SysUserAndDepartmentInfo> getSystemListByCondition(SearchCondition searchCondition) {
        List<SysUserAndDepartmentInfo> list = sysUserMapper.getSystemListByCondition(searchCondition);
        return list;
    }

    @Override
    public Integer saveOrUpdateByUserId(Integer userId, SysUser sysUser, Integer roleId, Integer statusType) {
        int i = 0;
        sysUser.setCreateDate(new Date());
        sysUser.setUserType("1");
        if (!StringUtil.isEmpty(sysUser.getPassword())) {
            sysUser.setPassword(EncryptUtils.md5(sysUser.getPassword()));
        }
        if (null == userId) {
            i = sysUserMapper.insertSelective(sysUser);
        } else {
            sysUser.setId(userId);
            i = sysUserMapper.updateByPrimaryKeySelective(sysUser);
            if (statusType != null) {
                return i;
            }
        }
        if (i > 0) {
            if (null == userId) {
                SysUserRole userRole = new SysUserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(sysUser.getId());
                userRole.setCreateTime(new Date());
                userRole.setDelStatus("0");
                int i1 = sysUserRoleMapper.insertSelective(userRole);
            } else {
                SysUserRoleExample userRoleExample = new SysUserRoleExample();
                SysUserRoleExample.Criteria criteria = userRoleExample.createCriteria();
                criteria.andUserIdEqualTo(userId);
                int i1 = sysUserRoleMapper.deleteByExample(userRoleExample);
                if (i1 > 0) {
                    SysUserRole userRole = new SysUserRole();
                    userRole.setRoleId(roleId);
                    userRole.setUserId(sysUser.getId());
                    userRole.setCreateTime(new Date());
                    userRole.setDelStatus("0");
                    sysUserRoleMapper.insertSelective(userRole);
                }

            }
        }
        return i;
    }

    @Override
    public Map<String, Object> selectUserInfoByUserId(Integer userId) {
        Map<String, Object> map = new HashMap<>();

        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        map.put("userinfo", null != sysUser ? sysUser : "");
        SysUserRoleExample userRoleExample = new SysUserRoleExample();
        SysUserRoleExample.Criteria criteria = userRoleExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<SysUserRole> roleList = sysUserRoleMapper.selectByExample(userRoleExample);

        map.put("roleId", null != roleList && roleList.size() > 0 ? roleList.get(0).getRoleId() : "");


        return map;
    }
}
