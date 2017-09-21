package com.quanmin.dao.mapper;

import com.quanmin.model.SCLable;
import com.quanmin.model.SCLableExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SCLableMapper {
    int countByExample(SCLableExample example);

    int deleteByExample(SCLableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SCLable record);

    int insertSelective(SCLable record);

    List<SCLable> selectByExample(SCLableExample example);

    SCLable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SCLable record, @Param("example") SCLableExample example);

    int updateByExample(@Param("record") SCLable record, @Param("example") SCLableExample example);

    int updateByPrimaryKeySelective(SCLable record);

    int updateByPrimaryKey(SCLable record);

    List<SCLable> selectLableAndCount();
}