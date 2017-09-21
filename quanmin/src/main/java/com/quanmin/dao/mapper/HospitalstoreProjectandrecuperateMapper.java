package com.quanmin.dao.mapper;

import com.quanmin.model.HospitalstoreProjectandrecuperate;
import com.quanmin.model.HospitalstoreProjectandrecuperateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HospitalstoreProjectandrecuperateMapper {
    int countByExample(HospitalstoreProjectandrecuperateExample example);

    int deleteByExample(HospitalstoreProjectandrecuperateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HospitalstoreProjectandrecuperate record);

    int insertSelective(HospitalstoreProjectandrecuperate record);

    List<HospitalstoreProjectandrecuperate> selectByExample(HospitalstoreProjectandrecuperateExample example);

    HospitalstoreProjectandrecuperate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HospitalstoreProjectandrecuperate record, @Param("example") HospitalstoreProjectandrecuperateExample example);

    int updateByExample(@Param("record") HospitalstoreProjectandrecuperate record, @Param("example") HospitalstoreProjectandrecuperateExample example);

    int updateByPrimaryKeySelective(HospitalstoreProjectandrecuperate record);

    int updateByPrimaryKey(HospitalstoreProjectandrecuperate record);
}