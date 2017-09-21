package com.quanmin.dao.mapper;

import com.quanmin.model.SCImage;
import com.quanmin.model.SCImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SCImageMapper {
    int countByExample(SCImageExample example);

    int deleteByExample(SCImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SCImage record);

    int insertSelective(SCImage record);

    List<SCImage> selectByExample(SCImageExample example);

    SCImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SCImage record, @Param("example") SCImageExample example);

    int updateByExample(@Param("record") SCImage record, @Param("example") SCImageExample example);

    int updateByPrimaryKeySelective(SCImage record);

    int updateByPrimaryKey(SCImage record);
}