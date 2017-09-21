package com.quanmin.service;

import com.quanmin.model.SysUser;
import com.quanmin.model.custom.FamilyInfo;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.custom.UserCountInfo;
import com.quanmin.util.ResultUtils;

import java.util.List;

public interface CMSUserService {

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    SysUser userLogin(String userName, String password);

    /**
     * 校验密码
     *
     * @param password
     * @return
     */
    Integer checkPassword(String password, String id);

    /**
     * 修改密码
     *
     * @param newpassword
     * @param id
     * @return
     */
    Integer updatePassword(String newpassword, String id);

    /**
     * 根据条件查询用户信息
     *
     * @param condition
     * @return
     */
    List<SysUser> getUserListBySearchCondition(SearchCondition condition);

    /**
     * 通过用户id查询用户详细信息
     *
     * @param id
     * @return
     */
    SysUser getUserDetailInfoById(String id);

    /**
     * 查询用户id查询家庭成员
     *
     * @param id
     * @return
     */
    List<FamilyInfo> getUserFamilyDetailInfoByUserId(String id);

    /**
     * 校验用户名称是否存在
     *
     * @param username
     * @return
     */
    Integer checkUsernameByUserName(String username);

    /**
     * 根据用户id删除用户信息
     * @param userId
     * @return
     */
    Integer deleteUserByUserId(Integer userId);

    /**
     * 获取日周月用户总数
     * @return
     */
    List<UserCountInfo> addUserCount();

    /**
     * 活跃用户统计
     * @return
     */
    List<UserCountInfo> activeUserCount();

    /**
     * 获取日周月用户总数图表
     * @param data
     * @param type
     * @return
     */
    ResultUtils addUserStatisticsMap(String data, Integer type);

    /**
     * 获取活跃用户总数图表
     * @param data
     * @param type
     * @return
     */
    ResultUtils activeUserCountMap(String data, Integer type);
}
