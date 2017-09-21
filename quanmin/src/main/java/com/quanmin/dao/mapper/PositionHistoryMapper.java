package com.quanmin.dao.mapper;

import com.quanmin.model.PositionHistory;
import com.quanmin.model.PositionHistoryExample;
import com.quanmin.model.custom.PositionHistoryInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionHistoryMapper {
    int countByExample(PositionHistoryExample example);

    int deleteByExample(PositionHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PositionHistory record);

    int insertSelective(PositionHistory record);

    List<PositionHistory> selectByExample(PositionHistoryExample example);

    PositionHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PositionHistory record, @Param("example") PositionHistoryExample example);

    int updateByExample(@Param("record") PositionHistory record, @Param("example") PositionHistoryExample example);

    int updateByPrimaryKeySelective(PositionHistory record);

    int updateByPrimaryKey(PositionHistory record);

    /**
     * 查询历史位置
     * @param userId
     * @return
     */
    List<PositionHistoryInfo> selectlHistoryByuserId(Integer userId);
}