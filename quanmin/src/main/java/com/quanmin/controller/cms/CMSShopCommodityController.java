package com.quanmin.controller.cms;

import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.jpapo.ShopCommodity;
import com.quanmin.service.APPShopCommodityService;
import com.quanmin.service.CMSShopCommodityService;
import com.quanmin.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by yang on 2017/7/25.
 */
@RestController
@RequestMapping("/cms/1/")
public class  CMSShopCommodityController {


    @Autowired
    private CMSShopCommodityService shopCommodityService;


    /**
     * 根据商品名称搜索
     *
     * @param name
     * @return
     */
    @RequestMapping("/findbynameornumber")
    public ResultUtils findByName(String name,String number) {
        List<ShopCommodity> shopCommodityList = shopCommodityService.findByName(name,number);

        return null!=shopCommodityList&&shopCommodityList.size()>0?ResultUtils.returnSucess(shopCommodityList):ResultUtils.returnFail();
    }


    /**
     * 保存图片
     * @param response
     * @param request
     * @param fileName
     * @param type
     * @return
     * @throws IOException
     */
    @RequestMapping("/saveshopimage")
    @ResponseBody

    public ResultUtils saveImage(HttpServletResponse response,HttpServletRequest request,String fileName, Integer type) throws IOException {
        String basePath = "";
        String visit = "";
        switch (type) {
            case 1:
                basePath = LoadPropertiesDataUtils.getValue("qm.shop.cover.uploading.coverurl");
                visit = LoadPropertiesDataUtils.getValue("qm.shop.cover.visit.coverurl");
                break;
            case 2:
                basePath = LoadPropertiesDataUtils.getValue("qm.shop.detail.uploading.detailurl");
                visit = LoadPropertiesDataUtils.getValue("qm.shop.detail.visit.detailurl");
                break;
            case 3:
                basePath = LoadPropertiesDataUtils.getValue("qm.shop.parameter.uploading.parmeterurl");
                visit = LoadPropertiesDataUtils.getValue("qm.shop.parameter.visit.parmeterurl");
                break;
        }

        MultipartHttpServletRequest httpServletRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) httpServletRequest.getFile(fileName);
        // 处理文件名称
        String name = commonsMultipartFile.getName();
        String randomUUID = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        // 获取后缀名
        String originalFilename = commonsMultipartFile.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newName = DateConvertUtils.getCurrentDate()+"-"+name + randomUUID + suffix;
        byte[] bytes = commonsMultipartFile.getBytes();
        String outUrl = basePath + newName;
        File file = new File(outUrl);
        try {
            FileOutputStream fop = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }
            fop.write(bytes);
            fop.flush();
            fop.close();
            return ResultUtils.returnSucess(visit+newName);
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultUtils.returnFail();
        }
    }


    /**
     * 保存商品
     *
     * @param shopCommodity
     * @return
     */
    @RequestMapping("/saveshopcommondity")
    @ResponseBody
    public ResultUtils saveShopCommondity(ShopCommodity shopCommodity,Long commondityId) {
        int i = shopCommodityService.saveShopCommondity(shopCommodity,commondityId);
        return (i > 0) ? ResultUtils.returnSucess("") : ResultUtils.returnFail();
    }

    /**
     * 查看商品列表
     * @param searchCondition
     * @return
     */
    @RequestMapping("/shopcommonditylist")
    @ResponseBody
    public ResultUtils shopCommondityList(SearchCondition searchCondition) {
        Page<ShopCommodity> list = shopCommodityService.shopCommondityList(searchCondition);
        return null != list && list.getContent().size() > 0 ? ResultUtils.returnSucess("成功", list) : ResultUtils.returnFail();
    }

    /**
     * 商品上架下架
     * @param commondityId
     * @param status
     * @return
     */
    @RequestMapping("/shopcommondityupordown")
    @ResponseBody
    public ResultUtils shopCommondityUpOrDown(Long commondityId,Integer status) {
       int i = shopCommodityService.shopCommondityUpOrDown(commondityId,status);
        return i > 0 ? ResultUtils.returnSucess() : ResultUtils.returnFail();
    }

    /**
     * 根据商品id查看
     * @param commondityId
     * @return
     */
    @RequestMapping("/shopcommondityone")
    @ResponseBody
    public ResultUtils shopCommonditOne(Long commondityId) {
        Map<String,Object> shopCommodity = shopCommodityService.shopCommonditOne(commondityId);
        return null!=shopCommodity ? ResultUtils.returnSucess(shopCommodity) : ResultUtils.returnFail();
    }

    /**
     * 查询厂商列表
     * @return
     */
    @RequestMapping("/findcompany")
    @ResponseBody
    public ResultUtils shopCompanyAll(Integer type) {
        List<String> list = shopCommodityService.shopCompanyAll(type);
        return null!=list&&list.size()>0 ? ResultUtils.returnSucess(list) : ResultUtils.returnFail();
    }




}
