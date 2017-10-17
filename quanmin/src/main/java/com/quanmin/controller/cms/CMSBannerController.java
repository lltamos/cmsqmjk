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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
     * 保存或者更新banner
     *
     * @param bannerWithBLOBs
     * @return
     */
    @PostMapping(value = "savebanner")
    @ResponseBody
    public ResultUtils saveBannerInfo(BannerWithBLOBs bannerWithBLOBs) {

        int result = cmsBannerService.saveBannerInfo(bannerWithBLOBs);

        return result > 0 ? ResultUtils.returnSucess() : ResultUtils.returnFail();

    }

    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }

    /**
     * 保存图片
     *
     * @param response
     * @param request
     * @param type
     * @return
     * @throws IOException
     */
    @RequestMapping("/savehtmlinfo")
    @ResponseBody

    public ResultUtils saveImage(HttpServletResponse response, HttpServletRequest request,  Integer type) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        ResultUtils result = new ResultUtils();
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
            case 5:
                basePath = LoadPropertiesDataUtils.getValue("qm.banner.cover.uploading.parmeterurl");
                visit = LoadPropertiesDataUtils.getValue("qm.banner.cover.visit.parmeterurl");
                break;
            default:
                return ResultUtils.returnFail();
        }

        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            //取出一个list的multipartfile
            List<MultipartFile> files = multipartRequest.getFiles("upfile");
            String resulturl = "";
            for (MultipartFile multipartFile : files) {
                // 处理文件名称
                String name = multipartFile.getName();
                String randomUUID = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
                // 获取后缀名
                String originalFilename = multipartFile.getOriginalFilename();
                byte[] bytes = multipartFile.getBytes();
                String outUrl = basePath + originalFilename;
                File file = new File(outUrl);
                FileOutputStream fop = new FileOutputStream(file);
                if (!file.exists()) {
                    file.createNewFile();
                }
                fop.write(bytes);
                fop.flush();
                fop.close();
                resulturl += visit+ originalFilename+",";
            }
            return  ResultUtils.returnSucess("上传成功",resulturl.substring(0,resulturl.lastIndexOf(",")));
        } else {
            return ResultUtils.returnFail();
        }

    }

    /**
     * 根据条件查询banner列表
     *
     * @param condition
     * @return
     */
    @PostMapping(value = "listbanner")
    @ResponseBody
    public ResultUtils listBannerBySearchCondition(SearchCondition condition) {

        PageHelper.startPage(condition.getPage(), condition.getSize());
        List<BannerWithBLOBs> result = cmsBannerService.listBannerBySearchCondition(condition);
        return null != result && result.size() > 0 ? ResultUtils.returnSucess(result, ((Page) result).getPages()) : ResultUtils.returnFail();
    }

    /**
     * 根据id查看
     *
     * @param bannerId
     * @return
     */
    @PostMapping(value = "getbanner")
    @ResponseBody
    public ResultUtils getBannerByBannerId(Integer bannerId) {
        BannerWithBLOBs result = cmsBannerService.getBannerByBannerId(bannerId);
        return null != result ? ResultUtils.returnSucess(result) : ResultUtils.returnFail();
    }

    /**
     * banner上线或者下线
     * @param bannerId
     * @return
     */
    @PostMapping(value = "upordown")
    @ResponseBody
    public ResultUtils getUpOrDownByBannerId(Integer bannerId,Integer bannerStatus) {
        int result = cmsBannerService.getUpOrDownByBannerId(bannerId,bannerStatus);
        return result > 0 ? ResultUtils.returnSucess() : ResultUtils.returnFail();
    }


}
