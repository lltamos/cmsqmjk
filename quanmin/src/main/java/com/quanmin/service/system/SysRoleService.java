package com.quanmin.service.system;

import com.quanmin.model.SysRole;
import com.quanmin.model.custom.SearchCondition;

import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2017/6/30.
 */
public interface SysRoleService {

    /**
     * 编辑或新增角色
     *
     * @param sysRole
     * @param permissionIds
     * @param roleId
     * @return
     */
    Integer saveRoleInfo(SysRole sysRole, String permissionIds, Integer roleId);

    /**
     * 根据id查看信息
     *
     * @param roleId
     * @return
     */
    Map<String, Object> selectRoleListOrOneByRoleId(Integer roleId);

    /**
     * 获取角色列表
     *
     * @return
     */
    List<SysRole> getRoleList(SearchCondition searchCondition);

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    Integer deleteRoleByRoleId(Integer roleId);

    /**
     * 根据用户名称查询角色
     *
     * @param id
     * @return
     */
    SysRole selectRoleByUserId(Integer userId);


    /**
     * 校验角色名称
     *
     * @param roleName
     * @return
     */
    Integer checkRoleameByRoleName(String roleName);
}
