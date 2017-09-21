package com.quanmin.dao.mapper;

import com.quanmin.model.Smscode;
import com.quanmin.model.SmscodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmscodeMapper {
    int countByExample(SmscodeExample example);

    int deleteByExample(SmscodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Smscode record);

    int insertSelective(Smscode record);

    List<Smscode> selectByExample(SmscodeExample example);

    Smscode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Smscode record, @Param("example") SmscodeExample example);

    int updateByExample(@Param("record") Smscode record, @Param("example") SmscodeExample example);

    int updateByPrimaryKeySelective(Smscode record);

    int updateByPrimaryKey(Smscode record);
}