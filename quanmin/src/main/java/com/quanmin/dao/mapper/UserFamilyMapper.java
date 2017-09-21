package com.quanmin.dao.mapper;

import com.quanmin.model.UserFamily;
import com.quanmin.model.UserFamilyExample;
import com.quanmin.model.custom.FamilyInfo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserFamilyMapper {
	int countByExample(UserFamilyExample example);

	int deleteByExample(UserFamilyExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(UserFamily record);

	int insertSelective(UserFamily record);

	List<UserFamily> selectByExample(UserFamilyExample example);

	UserFamily selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") UserFamily record, @Param("example") UserFamilyExample example);

	int updateByExample(@Param("record") UserFamily record, @Param("example") UserFamilyExample example);

	int updateByPrimaryKeySelective(UserFamily record);

	int updateByPrimaryKey(UserFamily record);

	/**
	 * 查询家庭信息
	 * 
	 * @param id
	 * @return
	 */
	@Deprecated
	List<FamilyInfo> selectFamilyInfoByUserId(@Param("userId") Integer id);

	/**
	 * 查询家庭信息
	 *
	 * @since 2.1版本
	 * @param userId
	 * @return
	 */
	List<FamilyInfo> showFamilyListByUserId(@Param("userId") Integer userId);
}