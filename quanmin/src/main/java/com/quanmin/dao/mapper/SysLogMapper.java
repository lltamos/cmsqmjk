package com.quanmin.dao.mapper;

import com.quanmin.model.SysLog;
import com.quanmin.model.SysLogExample;
import java.util.List;

import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.custom.SysLogVO;
import com.quanmin.model.custom.UserCountInfo;
import org.apache.ibatis.annotations.Param;

public interface SysLogMapper {
    int countByExample(SysLogExample example);

    int deleteByExample(SysLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    List<SysLog> selectByExample(SysLogExample example);

    SysLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysLog record, @Param("example") SysLogExample example);

    int updateByExample(@Param("record") SysLog record, @Param("example") SysLogExample example);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

    /**
     * 活跃用户总数
     * @return
     */
    List<UserCountInfo> activeUserCount();

    /**
     * 查询图表x轴
     * @param data
     * @param type
     * @return
     */
    List<String> addUserStatisticsCount(@Param("data")String data, @Param("type")Integer type);
    /**
     * 查询图表y轴
     * @param data
     * @param type
     * @return
     */
    List<Integer> addUserStatisticsMap(@Param("data")String data, @Param("type")Integer type);

    /**
     * 根据条件查询日志
     * @param condition
     * @return
     */
    List<SysLogVO> listLogByCondition(SearchCondition condition);
}