package com.quanmin.service.system.impl;

import com.quanmin.dao.mapper.SysRoleMapper;
import com.quanmin.dao.mapper.SysRolePermissionMapper;
import com.quanmin.model.SysRole;
import com.quanmin.model.SysRoleExample;
import com.quanmin.model.SysRolePermission;
import com.quanmin.model.SysRolePermissionExample;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.service.system.SysRoleService;
import com.quanmin.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2017/6/30.
 */
@SuppressWarnings("ALL")
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public Integer saveRoleInfo(SysRole sysRole, String permissionIds, Integer roleId) {
        Integer count = 0;
        int i = 0;
        Integer insertRoleId = null;
        sysRole.setCreateDate(new Date());
//        新增
        if (null != roleId) {
            SysRolePermissionExample sysexample = new SysRolePermissionExample();
            SysRolePermissionExample.Criteria criteria = sysexample.createCriteria();
            criteria.andRoleIdEqualTo(roleId);
            i = sysRolePermissionMapper.deleteByExample(sysexample);
            sysRole.setId(roleId);
            sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        } else {
            i = sysRoleMapper.insertSelective(sysRole);
            insertRoleId = sysRole.getId();
        }
        if (i > 0) {
            String[] strings = permissionIds.split(",");
            for (int j = 0; j < strings.length; j++) {
                SysRolePermission sysRolePermission = new SysRolePermission();
                sysRolePermission.setRoleId(null==roleId?insertRoleId:roleId);

                sysRolePermission.setPermissionId(Integer.parseInt(strings[j]));
                sysRolePermission.setDelStatus("0");
                sysRolePermission.setCreateTime(new Date());
                sysRolePermissionMapper.insertSelective(sysRolePermission);
                count++;
            }
        }

        return count;
    }

    @Override
    public Map<String, Object> selectRoleListOrOneByRoleId(Integer roleId) {
        Map<String, Object> map = new HashMap<>();
        SysRolePermissionExample example = new SysRolePermissionExample();
        SysRolePermissionExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        List<SysRolePermission> list = sysRolePermissionMapper.selectByExample(example);

        if (null != list && list.size() > 0) {
            map.put("permissionlist", list);
        }

        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
        if (null != sysRole) {
            map.put("role", sysRole);
        }


        return map;
    }

    @Override
    public List<SysRole> getRoleList(SearchCondition searchCondition) {

        SysRoleExample example = new SysRoleExample();
        example.setOrderByClause("create_date desc");
        if (!StringUtil.isEmpty(searchCondition.getSearchKey())) {
            SysRoleExample.Criteria criteria = example.createCriteria();
            criteria.andRoleNameLike("%" + searchCondition.getSearchKey() + "%");
            SysRoleExample.Criteria criteria1 = example.createCriteria().andDescriptionLike("%" + searchCondition.getSearchKey() + "%");
            example.or(criteria1);
        }
        List<SysRole> roleList = sysRoleMapper.selectByExample(example);

        return roleList;
    }

    @Override
    public Integer deleteRoleByRoleId(Integer roleId) {
        int i = sysRoleMapper.deleteByPrimaryKey(roleId);
        SysRolePermissionExample example = new SysRolePermissionExample();
        SysRolePermissionExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        int i1 = sysRolePermissionMapper.deleteByExample(example);

        return i;
    }

    @Override
    public SysRole selectRoleByUserId(Integer userId) {
        SysRole sysRole = sysRoleMapper.selectRoleByUserId(userId);
        return sysRole;
    }

    @Override
    public Integer checkRoleameByRoleName(String roleName) {
        SysRoleExample roleExample = new SysRoleExample();
        roleExample.createCriteria().andRoleNameEqualTo(roleName);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(roleExample);
        return sysRoles.size();
    }
}
