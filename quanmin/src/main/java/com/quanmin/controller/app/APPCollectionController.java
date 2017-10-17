package com.quanmin.controller.app;

import com.quanmin.model.jpapo.ShopCommodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quanmin.model.Collection;
import com.quanmin.model.Feedback;
import com.quanmin.service.APPCollectionService;
import com.quanmin.service.APPFeedbackService;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.ResultUtils;

import java.util.List;

/**
 * 收藏
 *
 * @author heasy
 */
@Controller
@RequestMapping(value = "/api/1/")
public class APPCollectionController {

    @Autowired
    private APPCollectionService collectionService;

    /**
     * 帖子收藏列表
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/showcollection")
    @ResponseBody
    public ResultUtils showCollectionByUserId(Integer userId) {
        ResultUtils result = collectionService.showCollectionByUserId(userId);
        return result;
    }

    /**
     * 收藏和取消收藏资讯
     *
     * @param informationId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/collectionorcancelinformation")
    @ResponseBody
    public ResultUtils saveOrUpdateCollectionInformation(Integer informationId, Integer userId, String type) {
        ResultUtils result = collectionService.saveOrUpdateCollectionInformation(informationId, userId, type);
        return result;
    }

    /**
     * 商品收藏列表
     *
     * @param userId
     * @param type
     * @return
     */
    @RequestMapping(value = "/showcollectioncommodity")
    @ResponseBody
    public ResultUtils showCollectionCommodityByUserId(Integer userId, Integer type) {
        List<ShopCommodity> commoditiesList = collectionService.showCollectionCommodityByUserId(userId, type);
        return null != commoditiesList && commoditiesList.size() > 0 ? ResultUtils.returnSucess(commoditiesList) : ResultUtils.returnFail();
    }
}
