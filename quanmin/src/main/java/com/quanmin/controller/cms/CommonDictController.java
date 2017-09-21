package com.quanmin.controller.cms;

import com.quanmin.model.SysArea;
import com.quanmin.model.jpapo.ShopCommodity;
import com.quanmin.model.jpapo.ShopDict;
import com.quanmin.service.CommonDictService;
import com.quanmin.util.Commons;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by yang on 2017/7/25.
 * 定义一些字典信息
 */
@RestController
@RequestMapping("/common/1/shop/")
public class CommonDictController {

    @Autowired
    private CommonDictService commonDictService;

    /**
     * 查询省
     *
     * @return
     */
    @RequestMapping("/provincelist")
    public ResultUtils provinceList() {
        List<SysArea> list = commonDictService.findProvinceList(0);
        return (null != list && list.size() > 0) ? ResultUtils.returnSucess(list) : ResultUtils.returnFail();
    }

    /**
     * 查询市
     *
     * @param provinceId
     * @return
     */
    @RequestMapping("/findcitybyprovinceid")
    public ResultUtils findCityByProvinceId(Integer provinceId) {
        List<SysArea> list = commonDictService.findCityByProvinceId(provinceId);
        return (null != list && list.size() > 0) ? ResultUtils.returnSucess(list) : ResultUtils.returnFail();
    }

    /**
     * 获取标签列表
     *
     * @return
     */
    @RequestMapping("/finddictlist")
    public ResultUtils findShopDictList(Integer type, String commodityNumber) {
        List<ShopDict> shopCommodityList = commonDictService.shopDictList(type, commodityNumber);

        return (null != shopCommodityList) ? ResultUtils.returnSucess(shopCommodityList) : ResultUtils.returnFail();

    }

    /**
     * 保存标签，颜色，或者大小
     *
     * @param shopDict
     * @return
     */
    @RequestMapping("/savedict")
    public ResultUtils saveShopDict(ShopDict shopDict) {
        ShopDict result = commonDictService.saveShopDict(shopDict);
        return (null != result) ? ResultUtils.returnSucess(result) : ResultUtils.returnFail();
    }

    /**
     * 根据标签查看是否付存在
     *
     * @param shopDict
     * @return
     */
    @RequestMapping("/findbycommodityidandname")
    public ResultUtils findByCommodityIdAndNameCondition(ShopDict shopDict) {
        ShopDict result = commonDictService.findByCommodityIdAndNameCondition(shopDict);
        return (null != result) ? ResultUtils.returnSucess(result) : ResultUtils.returnFail();
    }

}
