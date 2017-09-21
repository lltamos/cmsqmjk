package com.quanmin.controller.cms;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quanmin.model.BannerWithBLOBs;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.custom.SysLogVO;
import com.quanmin.service.CMSBannerService;
import com.quanmin.service.CMSLogService;
import com.quanmin.util.DateConvertUtils;
import com.quanmin.util.LoadPropertiesDataUtils;
import com.quanmin.util.ResultUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @Author: By heasy.
 * @Date: 2017/9/14.
 * @Contcat: yz972641975@gmail.com.
 * @Description: banner管理
 * @Modified By:
 */
@RestController
@RequestMapping("cms/1/")
public class CMSBannerController {

    @Autowired
    private CMSBannerService cmsBannerService;


    /**
     * 保存banner
     * @param bannerWithBLOBs
     * @return
     */
    @PostMapping(value = "savebanner")
    @ResponseBody
    public ResultUtils saveBannerInfo(BannerWithBLOBs bannerWithBLOBs) {

       int result = cmsBannerService.saveBannerInfo(bannerWithBLOBs);

        return result> 0 ? ResultUtils.returnSucess() : ResultUtils.returnFail();

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
    @RequestMapping("/savehtmlinfo")
    @ResponseBody

    public ResultUtils saveImage(HttpServletResponse response, HttpServletRequest request, String fileName, Integer type) throws IOException {
        String basePath = "";
        String visit = "";
        switch (type) {
            case 1:
                basePath = LoadPropertiesDataUtils.getValue("qm.banner.html.uploading.parmeterurl");
                visit = LoadPropertiesDataUtils.getValue("qm.banner.html.visit.parmeterurl");
                break;
            case 2:
                basePath = LoadPropertiesDataUtils.getValue("qm.banner.css.uploading.parmeterurl");
                visit = LoadPropertiesDataUtils.getValue("qm.banner.css.visit.parmeterurl");
                break;
            case 3:
                basePath = LoadPropertiesDataUtils.getValue("qm.banner.js.uploading.parmeterurl");
                visit = LoadPropertiesDataUtils.getValue("qm.banner.js.visit.parmeterurl");
                break;
            case 4:
                basePath = LoadPropertiesDataUtils.getValue("qm.banner.image.uploading.parmeterurl");
                visit = LoadPropertiesDataUtils.getValue("qm.banner.image.visit.parmeterurl");
                break;
        }

        MultipartHttpServletRequest httpServletRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) httpServletRequest.getFile(fileName);
        // 处理文件名称
        String name = commonsMultipartFile.getName();
        // 获取后缀名
        String originalFilename = commonsMultipartFile.getOriginalFilename();
        byte[] bytes = commonsMultipartFile.getBytes();
        String outUrl = basePath + originalFilename;
        File file = new File(outUrl);
        try {
            FileOutputStream fop = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }
            fop.write(bytes);
            fop.flush();
            fop.close();
            return ResultUtils.returnSucess(visit+originalFilename);
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultUtils.returnFail();
        }
    }

}
