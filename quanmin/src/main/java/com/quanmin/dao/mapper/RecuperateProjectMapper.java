package com.quanmin.dao.mapper;

import com.quanmin.model.RecuperateProject;
import com.quanmin.model.RecuperateProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecuperateProjectMapper {
    int countByExample(RecuperateProjectExample example);

    int deleteByExample(RecuperateProjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RecuperateProject record);

    int insertSelective(RecuperateProject record);

    List<RecuperateProject> selectByExample(RecuperateProjectExample example);

    RecuperateProject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RecuperateProject record, @Param("example") RecuperateProjectExample example);

    int updateByExample(@Param("record") RecuperateProject record, @Param("example") RecuperateProjectExample example);

    int updateByPrimaryKeySelective(RecuperateProject record);

    int updateByPrimaryKey(RecuperateProject record);
}