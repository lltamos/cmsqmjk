package com.quanmin.dao.mapper;

import com.quanmin.model.LabelInformation;
import com.quanmin.model.LabelInformationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LabelInformationMapper {
    int countByExample(LabelInformationExample example);

    int deleteByExample(LabelInformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LabelInformation record);

    int insertSelective(LabelInformation record);

    List<LabelInformation> selectByExample(LabelInformationExample example);

    LabelInformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LabelInformation record, @Param("example") LabelInformationExample example);

    int updateByExample(@Param("record") LabelInformation record, @Param("example") LabelInformationExample example);

    int updateByPrimaryKeySelective(LabelInformation record);

    int updateByPrimaryKey(LabelInformation record);
}