package com.quanmin.controller.app;

import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.jpapo.ShopCollection;
import com.quanmin.model.jpapo.ShopCommodity;
import com.quanmin.service.APPShopCommodityService;
import com.quanmin.util.Commons;
import com.quanmin.util.RedisUtils;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yang on 2017/7/25.
 */
@RestController
@RequestMapping("/api/1/")
public class APPShopCommodityController {
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private APPShopCommodityService shopCommodityService;

    /**
     * 根据条件搜索
     *
     * @param searchCondition
     * @return
     */
    @RequestMapping("/findbysearchkey")
    public ResultUtils findBySearchkeyCondition(SearchCondition searchCondition) {
      return shopCommodityService.findBySearchkeyCondition(searchCondition);


    }

    /**
     * 根据类型搜索
     *
     * @param searchCondition
     * @return
     */
    @RequestMapping("/findbytype")
    public ResultUtils findByTypeCondition(SearchCondition searchCondition) {
        return shopCommodityService.findByTypeCondition(searchCondition);
    }

    /**
     * 根据商品id查询商品详细内容
     *
     * @param commodityId
     * @return
     */
    @RequestMapping("/findbycommodityid")
    public ResultUtils findByid(Long commodityId, Integer userId) {
        Map<String, Object> map = shopCommodityService.findByCommodityId(commodityId, userId);
        return null != map && map.size() > 0 ? ResultUtils.returnSucess(map) : ResultUtils.returnFail();
    }

    /**
     * 根据用户id查询搜索历史
     *
     * @param userId
     * @return
     */
    @RequestMapping("/findsearchhistory")
    public ResultUtils findSearchHistoryByUserId(Long userId) {
        //查询出所有的数据，
        Set<String> historySet = redisUtils.smembers(Commons.REDIS_COMMONDITY_USER_SEARCH_HISTORY + userId);
        return null != historySet && historySet.size() > 0 ? ResultUtils.returnSucess(historySet) : ResultUtils.returnFail();
    }

    /**
     * 用户收藏
     *
     * @param shopCollection
     * @return
     */
    @RequestMapping("/usersaveorcanncelshopcollection")
    public ResultUtils userSaveOrCanncelShopCollection(ShopCollection shopCollection) {
        int i = shopCommodityService.userSaveOrCanncelShopCollection(shopCollection);
        return i > 0 ? ResultUtils.returnSucess(i) : ResultUtils.returnFail();
    }

    /**
     * 推荐列表
     *
     * @return
     */
    @RequestMapping("/systemrecommend")
    public ResultUtils systemRecommend( ) {
        List<ShopCommodity> list = shopCommodityService.systemRecommend();
        return null != list && list.size() > 0 ? ResultUtils.returnSucess(list) : ResultUtils.returnFail();
    }


    /**
     * 根据商品编号查询
     *
     * @param number
     * @return
     */
    @RequestMapping("/findbynumber")
    public ResultUtils findByNumberCondition(String number) {
        Map<String, Object> map = shopCommodityService.findByNumberCondition(number);
        return null != map && map.size() > 0 ? ResultUtils.returnSucess(map) : ResultUtils.returnFail();
    }


}
