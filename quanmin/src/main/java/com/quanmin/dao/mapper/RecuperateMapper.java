package com.quanmin.dao.mapper;

import com.quanmin.model.Recuperate;
import com.quanmin.model.RecuperateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecuperateMapper {
    int countByExample(RecuperateExample example);

    int deleteByExample(RecuperateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Recuperate record);

    int insertSelective(Recuperate record);

    List<Recuperate> selectByExampleWithBLOBs(RecuperateExample example);

    List<Recuperate> selectByExample(RecuperateExample example);

    Recuperate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Recuperate record, @Param("example") RecuperateExample example);

    int updateByExampleWithBLOBs(@Param("record") Recuperate record, @Param("example") RecuperateExample example);

    int updateByExample(@Param("record") Recuperate record, @Param("example") RecuperateExample example);

    int updateByPrimaryKeySelective(Recuperate record);

    int updateByPrimaryKeyWithBLOBs(Recuperate record);

    int updateByPrimaryKey(Recuperate record);
    /**
     * 查询调理方案
     * @param infoId
     * @return
     */
	List<Recuperate> selectRecperateByInforId(@Param("infoId")Integer infoId);
	
}