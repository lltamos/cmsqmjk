package com.quanmin.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quanmin.model.BBSInformation;
import com.quanmin.model.BBSInformationExample;
import com.quanmin.model.custom.BBSInformationAndUser;
import com.quanmin.model.custom.SearchCondition;

public interface BBSInformationMapper {
    int countByExample(BBSInformationExample example);

    int deleteByExample(BBSInformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BBSInformation record);

    int insertSelective(BBSInformation record);

    List<BBSInformation> selectByExampleWithBLOBs(BBSInformationExample example);

    List<BBSInformation> selectByExample(BBSInformationExample example);

    BBSInformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BBSInformation record, @Param("example") BBSInformationExample example);

    int updateByExampleWithBLOBs(@Param("record") BBSInformation record, @Param("example") BBSInformationExample example);

    int updateByExample(@Param("record") BBSInformation record, @Param("example") BBSInformationExample example);

    int updateByPrimaryKeySelective(BBSInformation record);

    int updateByPrimaryKeyWithBLOBs(BBSInformation record);

    int updateByPrimaryKey(BBSInformation record);

    /**
     *  根据条件查看论坛列表
     * @param searchCondition 
     * @return
     */
	List<BBSInformationAndUser> showBBSList(SearchCondition searchCondition);
}