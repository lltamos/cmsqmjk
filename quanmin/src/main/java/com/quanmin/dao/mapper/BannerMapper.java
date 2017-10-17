package com.quanmin.dao.mapper;

import com.quanmin.model.Banner;
import com.quanmin.model.BannerExample;
import com.quanmin.model.BannerWithBLOBs;
import java.util.List;

import com.quanmin.model.custom.SearchCondition;
import org.apache.ibatis.annotations.Param;

public interface BannerMapper {
    int countByExample(BannerExample example);

    int deleteByExample(BannerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BannerWithBLOBs record);

    int insertSelective(BannerWithBLOBs record);

    List<BannerWithBLOBs> selectByExampleWithBLOBs(BannerExample example);

    List<Banner> selectByExample(BannerExample example);

    BannerWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BannerWithBLOBs record, @Param("example") BannerExample example);

    int updateByExampleWithBLOBs(@Param("record") BannerWithBLOBs record, @Param("example") BannerExample example);

    int updateByExample(@Param("record") Banner record, @Param("example") BannerExample example);

    int updateByPrimaryKeySelective(BannerWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BannerWithBLOBs record);

    int updateByPrimaryKey(Banner record);

    List<BannerWithBLOBs> listBannerBySearchCondition(SearchCondition condition);
}