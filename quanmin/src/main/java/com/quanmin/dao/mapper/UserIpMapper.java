package com.quanmin.dao.mapper;

import com.quanmin.model.UserIp;
import com.quanmin.model.UserIpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserIpMapper {
    int countByExample(UserIpExample example);

    int deleteByExample(UserIpExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserIp record);

    int insertSelective(UserIp record);

    List<UserIp> selectByExample(UserIpExample example);

    UserIp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserIp record, @Param("example") UserIpExample example);

    int updateByExample(@Param("record") UserIp record, @Param("example") UserIpExample example);

    int updateByPrimaryKeySelective(UserIp record);

    int updateByPrimaryKey(UserIp record);
}