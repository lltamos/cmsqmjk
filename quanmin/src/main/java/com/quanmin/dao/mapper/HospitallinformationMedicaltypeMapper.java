package com.quanmin.dao.mapper;

import com.quanmin.model.HospitallinformationMedicaltype;
import com.quanmin.model.HospitallinformationMedicaltypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HospitallinformationMedicaltypeMapper {
    int countByExample(HospitallinformationMedicaltypeExample example);

    int deleteByExample(HospitallinformationMedicaltypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HospitallinformationMedicaltype record);

    int insertSelective(HospitallinformationMedicaltype record);

    List<HospitallinformationMedicaltype> selectByExample(HospitallinformationMedicaltypeExample example);

    HospitallinformationMedicaltype selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HospitallinformationMedicaltype record, @Param("example") HospitallinformationMedicaltypeExample example);

    int updateByExample(@Param("record") HospitallinformationMedicaltype record, @Param("example") HospitallinformationMedicaltypeExample example);

    int updateByPrimaryKeySelective(HospitallinformationMedicaltype record);

    int updateByPrimaryKey(HospitallinformationMedicaltype record);
}