package com.quanmin.dao.mapper;

import com.quanmin.model.HospitalStoreInformation;
import com.quanmin.model.HospitalStoreInformationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HospitalStoreInformationMapper {
    int countByExample(HospitalStoreInformationExample example);

    int deleteByExample(HospitalStoreInformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HospitalStoreInformation record);

    int insertSelective(HospitalStoreInformation record);

    List<HospitalStoreInformation> selectByExample(HospitalStoreInformationExample example);

    HospitalStoreInformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HospitalStoreInformation record,
                                 @Param("example") HospitalStoreInformationExample example);

    int updateByExample(@Param("record") HospitalStoreInformation record,
                        @Param("example") HospitalStoreInformationExample example);

    int updateByPrimaryKeySelective(HospitalStoreInformation record);

    int updateByPrimaryKey(HospitalStoreInformation record);

    /**
     * 根据医疗类型id查询
     *
     * @param medicalTypeId
     * @return
     */
    List<HospitalStoreInformation> getHospitalStoreInformationByMedicalTypeId(@Param("medicalTypeId") Integer medicalTypeId, @Param("cityId") Integer cityId);


}