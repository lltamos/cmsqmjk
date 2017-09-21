package com.quanmin.controller.cms;

import com.quanmin.dao.mapper.SCImageMapper;
import com.quanmin.model.Label;
import com.quanmin.model.SCImage;
import com.quanmin.model.custom.LableInformation;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.service.CMSContentService;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.LoadPropertiesDataUtils;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 内容管理
 */

@Controller
@RequestMapping("/cms/1/")
public class CMSContentController {

    @Autowired
    private CMSContentService contentService;


    @Autowired
    private SCImageMapper scimageMapper;

    /**
     * 保存图片标签
     */
    @RequestMapping("/scsavelabel")
    @ResponseBody
    public ResultUtils scSaveLabel(HttpServletRequest request, HttpServletResponse response, String scName) {
        ResultUtils result = contentService.scSaveLabel(scName);
        return result;
    }

    /**
     * 图片标签列表
     */
    @RequestMapping("/sclabellist")
    @ResponseBody
    public ResultUtils sclabelList(HttpServletRequest request, HttpServletResponse response, String scName) {
        ResultUtils result = contentService.sclabelList();
        return result;
    }

    /**
     * 图片标签列表
     */
    @RequestMapping("/scimagelist")
    @ResponseBody
    public ResultUtils scImagelList(HttpServletRequest request, HttpServletResponse response, SearchCondition condition) {
        ResultUtils result = contentService.scImagelList(condition);
        return result;
    }

    /**
     * 添加标签
     */
    @RequestMapping("/savelabel")
    @ResponseBody
    public ResultUtils saveLabel(HttpServletRequest request, HttpServletResponse response, Label label) {
        ResultUtils result = contentService.saveLabel(label);
        return result;
    }

    /**
     * 咨询标签列表
     */
    @RequestMapping("/labellist")
    @ResponseBody
    public ResultUtils lableList(HttpServletRequest request, HttpServletResponse response) {
        ResultUtils result = contentService.lableList();
        return result;
    }

    /**
     * 保存内容
     */
    @RequestMapping("/savecontent")
    @ResponseBody
    public ResultUtils saveContent(@ModelAttribute LableInformation labelInformation, HttpServletRequest request,
                                   HttpServletResponse response, String type, String popular, String informationId) {
        ResultUtils result = contentService.saveContent(labelInformation, type, popular, informationId
        );
        return result;
    }

    /**
     * 保存封面图片
     *
     * @param request
     * @param response
     * @param fileName
     * @return
     */
    @RequestMapping("/saveimage")
    @ResponseBody
    public ResultUtils saveImage(HttpServletRequest request, HttpServletResponse response, String fileName,
                                 String sclabelId) {
        ResultUtils result = new ResultUtils();
        String basePath = LoadPropertiesDataUtils.getValue("qm.uploading.url");
        String visitUrl = LoadPropertiesDataUtils.getValue("qm.visit.url");

        MultipartHttpServletRequest httpServletRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) httpServletRequest.getFile(fileName);
        // 处理文件名称
        String name = commonsMultipartFile.getName();
        String randomUUID = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        // 获取后缀名
        String originalFilename = commonsMultipartFile.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        String newName = name + randomUUID + suffix;

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
            // 图片信息插入到单独存储图片的信息表中
            SCImage record = new SCImage();
            record.setScId(Integer.parseInt(sclabelId));
            record.setImgurl(visitUrl + newName);
            record.setType(0);
            record.setCreateTime(new Date());
            scimageMapper.insertSelective(record);

            result.setMsg("上传成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue(visitUrl + newName);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("上传失败");
            result.setResultCode("500");
            result.setSuccess(false);
            result.setValue("");
            return result;
        }
    }

    /**
     * 保存封面图片
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/savemanyimage")
    @ResponseBody
    public ResultUtils savemanyImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        ResultUtils result = new ResultUtils();
        String basePath = LoadPropertiesDataUtils.getValue("qm.uploading.url");
        String visitUrl = LoadPropertiesDataUtils.getValue("qm.visit.url");
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
                String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
                String sourceName=originalFilename.substring(0,originalFilename.length()-suffix.length());
                String newName = sourceName + "-"+randomUUID + suffix;
                byte[] bytes = multipartFile.getBytes();
                String outUrl = basePath + newName;
                File file = new File(outUrl);
                FileOutputStream fop = new FileOutputStream(file);
                if (!file.exists()) {
                    file.createNewFile();
                }
                fop.write(bytes);
                fop.flush();
                fop.close();
                resulturl += visitUrl + newName+",";
            }
            return  ResultUtils.returnSucess("上传成功",resulturl.substring(0,resulturl.lastIndexOf(",")));
        } else {
            return ResultUtils.returnFail();
        }
    }

    /**
     * 信息列表
     */
    @RequestMapping("/infolist")
    @ResponseBody
    public String infoListByCondition(SearchCondition condition, HttpServletRequest request,
                                      HttpServletResponse response, String type) {
        ResultUtils list = contentService.selectInfoListByCondition(condition);
        return JsonUtils.objectToJson(list);
    }

    /**
     * 信息删除
     */
    @RequestMapping("/deleteinfo")
    @ResponseBody
    public String deleteInfoByInfoId(SearchCondition condition, HttpServletRequest request,
                                     HttpServletResponse response) {
        ResultUtils list = contentService.deleteInfoByInfoId(condition);
        return JsonUtils.objectToJson(list);
    }

    /**
     * 根据id查询咨询
     *
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/selectoneinfo")
    @ResponseBody
    public String selectInfoByInfoId(SearchCondition condition, HttpServletRequest request,
                                     HttpServletResponse response) {
        ResultUtils list = contentService.selectInfoByInfoId(condition);
        return JsonUtils.objectToJson(list);
    }


    /**
     * 重新生成咨询html页面
     */
    @RequestMapping("/generatehtml")
    @ResponseBody
    public void generateHtml(HttpServletRequest request, HttpServletResponse response) {
        contentService.generateHtml();

    }

}
