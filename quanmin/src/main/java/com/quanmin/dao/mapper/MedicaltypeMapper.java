package com.quanmin.dao.mapper;

import com.quanmin.model.Medicaltype;
import com.quanmin.model.MedicaltypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MedicaltypeMapper {
    int countByExample(MedicaltypeExample example);

    int deleteByExample(MedicaltypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Medicaltype record);

    int insertSelective(Medicaltype record);

    List<Medicaltype> selectByExample(MedicaltypeExample example);

    Medicaltype selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Medicaltype record, @Param("example") MedicaltypeExample example);

    int updateByExample(@Param("record") Medicaltype record, @Param("example") MedicaltypeExample example);

    int updateByPrimaryKeySelective(Medicaltype record);

    int updateByPrimaryKey(Medicaltype record);
}