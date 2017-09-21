package com.quanmin.dao.mapper;

import com.quanmin.model.Smslog;
import com.quanmin.model.SmslogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmslogMapper {
    int countByExample(SmslogExample example);

    int deleteByExample(SmslogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Smslog record);

    int insertSelective(Smslog record);

    List<Smslog> selectByExample(SmslogExample example);

    Smslog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Smslog record, @Param("example") SmslogExample example);

    int updateByExample(@Param("record") Smslog record, @Param("example") SmslogExample example);

    int updateByPrimaryKeySelective(Smslog record);

    int updateByPrimaryKey(Smslog record);
}