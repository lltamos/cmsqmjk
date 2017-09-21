package com.quanmin.dao.mapper;

import com.quanmin.model.Information;
import com.quanmin.model.InformationExample;
import com.quanmin.model.LabelInformation;
import com.quanmin.model.custom.CMSLableInformation;
import com.quanmin.model.custom.LableInformation;
import com.quanmin.model.custom.SearchCondition;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InformationMapper {
	int countByExample(InformationExample example);

	int deleteByExample(InformationExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Information record);

	int insertSelective(Information record);

	List<Information> selectByExampleWithBLOBs(InformationExample example);

	List<Information> selectByExample(InformationExample example);

	Information selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Information record, @Param("example") InformationExample example);

	int updateByExampleWithBLOBs(@Param("record") Information record, @Param("example") InformationExample example);

	int updateByExample(@Param("record") Information record, @Param("example") InformationExample example);

	int updateByPrimaryKeySelective(Information record);

	int updateByPrimaryKeyWithBLOBs(Information record);

	int updateByPrimaryKey(Information record);

	/**
	 * 查询热门资讯
	 * 
	 * @param searchCondition
	 * @return
	 */
	List<LableInformation> showIndexInformation(SearchCondition searchCondition);


	/**
	 * 查看收藏列表
	 * 
	 * @param userId
	 * @return
	 */
	List<LabelInformation> showCollectionByUserId( @Param("userId")Integer userId);

	/**
	 * 查看消息详细
	 * 
	 * @param searchCondition
	 * @return
	 */
	List<LableInformation> showIndexInformationDetail(SearchCondition searchCondition);

	/**
	 * 根据type进行资讯查询
	 */
	List<LableInformation> showInformationListByCondition(SearchCondition searchCondition);

	/**
	 * 根据type查询出对应的精选条目
	 * 
	 * @param searchCondition
	 * @return
	 */
	LableInformation selectFeaturedByCondition(SearchCondition searchCondition);

	/**
	 * cms查看资讯列表
	 * @param condition
	 * @return
	 */
	List<CMSLableInformation> selectInfoListByCondition(SearchCondition condition);

}