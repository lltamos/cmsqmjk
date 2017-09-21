package com.quanmin.dao.mapper;

import com.quanmin.model.SysUser;
import com.quanmin.model.SysUserExample;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.custom.SysUserAndDepartmentInfo;
import com.quanmin.model.custom.UserCountInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 获取用户和部门信息
     * @param searchCondition
     * @return
     */
    List<SysUserAndDepartmentInfo> getSystemListByCondition(SearchCondition searchCondition);

    /**
     * 获取日周月用户总数
     * @return
     */
    List<UserCountInfo> addUserCount();

    /**
     * 统计数量
     * @param data
     * @param type
     * @return
     */
    List<String> addUserStatisticsCount( @Param("data")String data, @Param("type") Integer type);

    /**
     * 统计数据
     * @param data
     * @param type
     * @return
     */
    List<Integer> addUserStatisticsMap( @Param("data")String data,  @Param("type")Integer type);
}