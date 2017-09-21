package com.quanmin.dao.mapper;

import com.quanmin.model.UserHraReport;
import com.quanmin.model.UserHraReportExample;
import com.quanmin.model.custom.SearchCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserHraReportMapper {
    int countByExample(UserHraReportExample example);

    int deleteByExample(UserHraReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserHraReport record);

    int insertSelective(UserHraReport record);

    List<UserHraReport> selectByExample(UserHraReportExample example);

    UserHraReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserHraReport record, @Param("example") UserHraReportExample example);

    int updateByExample(@Param("record") UserHraReport record, @Param("example") UserHraReportExample example);

    int updateByPrimaryKeySelective(UserHraReport record);

    int updateByPrimaryKey(UserHraReport record);

    /**
     * 获取报告列表
     *
     * @param condition
     * @return
     */
    List<UserHraReport> getUserReprotListBySearchCondition(SearchCondition condition);

}