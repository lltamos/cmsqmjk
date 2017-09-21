package com.quanmin.service;

import com.quanmin.model.Family;
import com.quanmin.model.custom.FamilyInfo;
import com.quanmin.util.ResultUtils;

import java.util.List;

public interface APPFamilyService {

	/**
	 * 保存家庭信息
	 * 
	 * @param family
	 * @param userId
	 * @param appellation
	 * @return
	 */
	@Deprecated
	ResultUtils saveFamily(Family family, Integer userId, String appellation);

	/**
	 * 展示家庭成员
	 * 
	 * @param id
	 * @return
	 */
	ResultUtils showFamilyByUserId(Integer id);

	/**
	 * 删除家庭信息
	 * 
	 * @param id
	 * @param familyId
	 * @return
	 */
	ResultUtils deleteFamily(Integer id, Integer familyId);

	/**
	 * 展示家庭成员
	 * @since  2.1版本
	 * @param userId
	 * @return
	 */
	List<FamilyInfo> showFamilyListByUserId(Integer userId);

	/**
	 * 申请查看报告
	 * @param userFamilyId
	 * @return
	 */
    ResultUtils applyCheckReportByUserId(Integer userFamilyId,Integer userId);

	/**
	 * 保存家庭信息
	 * @param family
	 * @param userId
	 * @param appellation
	 * @param type
	 * @since 2.2版本
	 * @return
	 */
	ResultUtils saveFamilyMember(Family family, Integer userId, String appellation, Integer type);

	/**
	 * 校验查看验证码
	 * @param phone
	 * @param code
	 * @param userFamilyId
	 * @return
	 */
    ResultUtils checkReportCode(String idNo,String phone, String code, Integer userFamilyId);

	/**
	 * 查看家属报告
	 * @param phone
	 * @param idNo
	 * @return
	 */
	ResultUtils checkFamilymembersReportByUserFamilyId(String phone,String idNo);
}
