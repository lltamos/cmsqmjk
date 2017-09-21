package com.quanmin.dao.mapper;

import com.quanmin.model.HospitalLevel;
import com.quanmin.model.HospitalLevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HospitalLevelMapper {
    int countByExample(HospitalLevelExample example);

    int deleteByExample(HospitalLevelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HospitalLevel record);

    int insertSelective(HospitalLevel record);

    List<HospitalLevel> selectByExample(HospitalLevelExample example);

    HospitalLevel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HospitalLevel record, @Param("example") HospitalLevelExample example);

    int updateByExample(@Param("record") HospitalLevel record, @Param("example") HospitalLevelExample example);

    int updateByPrimaryKeySelective(HospitalLevel record);

    int updateByPrimaryKey(HospitalLevel record);
}