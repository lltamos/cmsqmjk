package com.quanmin.service;

import com.quanmin.model.custom.LableInformation;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.util.ResultUtils;

public interface APPInformationService {

	/**
	 * 首页资讯列表
	 * @param searchCondition
	 * @return
	 */
	ResultUtils showIndexInformation(SearchCondition searchCondition);

	/**
	 * 查看详细内容
	 * @return
	 */
	@Deprecated
	ResultUtils showInformationdetail(SearchCondition searchCondition);

	/**
	 * 根据type查询健康资讯信息，
	 * @param searchCondition
	 * @return
	 */
	ResultUtils showInformationListByCondition(SearchCondition searchCondition);


    /**
     * 资讯详细内容
     *
     * @since 2.2
     *
     * @param searchCondition
     * @return
     */

    LableInformation getInformationdetail(SearchCondition searchCondition);
}
