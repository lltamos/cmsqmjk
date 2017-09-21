package com.quanmin.dao.mapper;

import com.quanmin.model.HospitalstorelinformationMedicaltype;
import com.quanmin.model.HospitalstorelinformationMedicaltypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HospitalstorelinformationMedicaltypeMapper {
    int countByExample(HospitalstorelinformationMedicaltypeExample example);

    int deleteByExample(HospitalstorelinformationMedicaltypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HospitalstorelinformationMedicaltype record);

    int insertSelective(HospitalstorelinformationMedicaltype record);

    List<HospitalstorelinformationMedicaltype> selectByExample(HospitalstorelinformationMedicaltypeExample example);

    HospitalstorelinformationMedicaltype selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HospitalstorelinformationMedicaltype record, @Param("example") HospitalstorelinformationMedicaltypeExample example);

    int updateByExample(@Param("record") HospitalstorelinformationMedicaltype record, @Param("example") HospitalstorelinformationMedicaltypeExample example);

    int updateByPrimaryKeySelective(HospitalstorelinformationMedicaltype record);

    int updateByPrimaryKey(HospitalstorelinformationMedicaltype record);
}