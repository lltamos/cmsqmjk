package com.quanmin.dao.mapper;

import com.quanmin.model.Versioninfo;
import com.quanmin.model.VersioninfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VersioninfoMapper {
    int countByExample(VersioninfoExample example);

    int deleteByExample(VersioninfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Versioninfo record);

    int insertSelective(Versioninfo record);

    List<Versioninfo> selectByExample(VersioninfoExample example);

    Versioninfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Versioninfo record, @Param("example") VersioninfoExample example);

    int updateByExample(@Param("record") Versioninfo record, @Param("example") VersioninfoExample example);

    int updateByPrimaryKeySelective(Versioninfo record);

    int updateByPrimaryKey(Versioninfo record);
}