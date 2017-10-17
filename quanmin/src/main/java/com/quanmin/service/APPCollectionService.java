package com.quanmin.service;

import com.quanmin.model.jpapo.ShopCommodity;
import com.quanmin.util.ResultUtils;

import java.util.List;

public interface APPCollectionService {

	/**
	 * 收藏和取消收藏资讯
	 * 
	 * @param informationId
	 * @param userId
	 * @param type
	 * @return
	 */
	ResultUtils saveOrUpdateCollectionInformation(Integer informationId, Integer userId, String type);

	/**
	 * 收藏列表
	 * 
	 * @param userId
	 * @return
	 */
	ResultUtils showCollectionByUserId(Integer userId);

	/**
	 * 商品收藏列表
	 * @param userId
	 * @param type
	 * @return
	 */
	List<ShopCommodity> showCollectionCommodityByUserId(Integer userId, Integer type);
}
