package com.quanmin.service.system;

import com.quanmin.model.SysDepartment;
import com.quanmin.model.SysUser;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.custom.SysUserAndDepartmentInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2017/7/3.
 * 账户管理
 */
public interface SystemUserService {

    /**
     * 查看所有部门
     *
     * @return
     */
    List<SysDepartment> getDepartmentId();

    /**
     * 根据条
     *件获取账户列表
     * @param searchCondition
     * @return
     */
    List<SysUserAndDepartmentInfo> getSystemListByCondition(SearchCondition searchCondition);

    /**
     * 新增或者更新用户信息
     * @param userId
     * @param sysUser
     * @param roleId
     * @return
     */
    Integer saveOrUpdateByUserId(Integer userId, SysUser sysUser, Integer roleId, Integer statusType);

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    Map<String,Object> selectUserInfoByUserId(Integer userId);
}
