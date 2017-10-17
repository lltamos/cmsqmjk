package com.quanmin.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quanmin.dao.mapper.*;
import com.quanmin.model.*;
import com.quanmin.model.LabelExample.Criteria;
import com.quanmin.model.custom.CMSLableInformation;
import com.quanmin.model.custom.LableInformation;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.service.CMSContentService;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.LoadPropertiesDataUtils;
import com.quanmin.util.ResultUtils;
import com.quanmin.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("ALL")
@Service
public class CMSContentServiceImpl implements CMSContentService {


    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private InformationMapper informationMapper;
    @Autowired
    private LabelInformationMapper labelInformationMapper;

    @Autowired
    private SCLableMapper sclableMapper;

    @Autowired
    private SCImageMapper scimageMapper;

    @Override
    public ResultUtils saveContent(LableInformation labelInformation, String type, String popular, String informationId) {
        ResultUtils result = new ResultUtils();

        // 输出html

        labelInformation = this.outHtmlContent(labelInformation);

        Information information = new Information();
        information.setBodyTitle(labelInformation.getBodyTitle());
        information.setTitle(labelInformation.getTitle());
        information.setUserId(labelInformation.getUserId());
        information.setContent(labelInformation.getContent());
        information.setCoverUrl(labelInformation.getCoverUrl());


        String labelIds = labelInformation.getLabelId();
        // 类型等于1，发布文章
        if ("1".equals(type)) {
            information.setPublishTime(new Date());
            information.setPublish(1);
            information.setPublishUrl(labelInformation.getPublishUrl());
            information.setCreateTime(new Date());
        }else{
            information.setPublish(0);
        }

        if (StringUtil.isEmpty(informationId)) {
            information.setCreateTime(new Date());
            int i = informationMapper.insertSelective(information);
            if (i > 0) {
                LabelInformation label = new LabelInformation();
                label.setCreateTime(new Date());
                label.setInformationId(information.getId());
                label.setLabelId(Integer.parseInt(labelIds));
                label.setFeatured(labelInformation.getFeatured());
                if ("0".equals(popular)) {
                    label.setPopular(popular);
                }
                int k = labelInformationMapper.insertSelective(label);
            }
            // 如果插入信息成功，插入到标签关联表中
            result.setMsg("添加成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue("");
            return result;

        } else {
            information.setId(Integer.parseInt(informationId));
            information.setUpdateTime(new Date());
            int i = informationMapper.updateByPrimaryKeySelective(information);
            LabelInformationExample labelInformationExample = new LabelInformationExample();
            labelInformationExample.createCriteria().andInformationIdEqualTo(information.getId());
            labelInformationMapper.deleteByExample(labelInformationExample);
            LabelInformation label = new LabelInformation();
            label.setCreateTime(new Date());
            label.setInformationId(information.getId());
            label.setLabelId(Integer.parseInt(labelIds));
            label.setFeatured(labelInformation.getFeatured());
            if ("0".equals(popular)) {
                label.setPopular(popular);
            }
            labelInformationMapper.insertSelective(label);
            result.setMsg("更新成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue("");
            return result;
        }
    }



    /**
     * 输出到文件中
     *
     * @param labelInformation
     * @return
     */
    private LableInformation outHtmlContent(LableInformation labelInformation) {
        String basePath = LoadPropertiesDataUtils.getValue("qm.uploading.zixunhtmlurl");
        String visitUrl = LoadPropertiesDataUtils.getValue("qm.visit.zixunurl");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String newdate = sdf.format(date);
        labelInformation.setCreateTime(date);
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>" + labelInformation.getTitle() + "</title>");
        sb.append(
                "<meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,user-scalable=0\" />");
        // css
        sb.append("<link href=\"./cmszixuncss/cmszixun.css\" rel=\"stylesheet\" type=\"text/css\">");
        // js
        sb.append(
                "<script src=\"./cmszixunjs/jquery.js\" type=\"text/javascript\"></script> <script src=\"./cmszixunjs/cmszixun.js\" type=\"text/javascript\"></script>");
        sb.append("</head> <body>");

        // 控制封面
        sb.append("<header>");
        sb.append("<div class=\"head-img\">");
        sb.append("<img src=\"" + labelInformation.getCoverUrl() + "\"alt=\"" + labelInformation.getCoverUrl()
                + "\"width=\"100%\" height=\"auto\">");
        sb.append("</div>");
        sb.append("<p class=\"img-intro\">" + labelInformation.getTitle() + "</p>");
        sb.append("<p class=\"word-intro clearFloat\">");
        sb.append("<span class=\"hour\">" + newdate + "</span>");
        sb.append("<span class=\"change big\">");
        sb.append("<img src=\"./cmszixunimg/word2.jpg\" width=\"100%\">" + "</span>");
        sb.append("<span class=\"change small\">");
        sb.append("<img src=\"./cmszixunimg/word1.jpg\" width=\"100%\">" + "</span>");
        sb.append("</p>");
        sb.append("</header>");
        // 控制内容
        sb.append("<div class=\"para\" id=\"content\">" + labelInformation.getContent() + "</div>");
        sb.append("</body>");
        sb.append("</html>");

        // 写入文件
        try {
            String fileName = UUID.randomUUID().toString().replace("-", "");
            File file = new File(basePath + fileName + ".html");
            FileOutputStream fop = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }

            byte[] contentInBytes = sb.toString().getBytes("UTF-8");
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
            labelInformation.setPublishUrl(visitUrl + fileName + ".html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return labelInformation;
    }

    @Override
    public ResultUtils selectInfoListByCondition(SearchCondition condition) {
        ResultUtils result = new ResultUtils();
        Map<String, String> map = new TreeMap<>(new Comparator<String>(){

            /*
             * int compare(Object o1, Object o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
            @Override
            public int compare(String o1, String o2) {

                //指定排序器按照降序排列
                return o2.compareTo(o1);
            }
        });

        PageHelper.startPage(condition.getPage(), condition.getSize());
        List<CMSLableInformation> list = informationMapper.selectInfoListByCondition(condition);


        for (CMSLableInformation lableInformation : list) {
            String s = map.get(lableInformation.getCreateTime());
            if (s == null) {
                map.put(lableInformation.getCreateTime(), JSON.toJSONString(lableInformation));
            } else {
                map.put(lableInformation.getCreateTime(), s + JSON.toJSONString(lableInformation));
            }
            System.out.println(map);
        }


        if (null != list && list.size() > 0) {
            result.setMsg("查询成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setCount(((Page) list).getPages());
            result.setValue(map);
            return result;
        }
        result.setMsg("查询失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

    @Override
    public ResultUtils deleteInfoByInfoId(SearchCondition condition) {
        ResultUtils result = new ResultUtils();
        Information record = new Information();
        record.setId(Integer.parseInt(condition.getInformationId()));
        record.setDelStatus(1);
        int i = informationMapper.updateByPrimaryKeySelective(record);
        if (i > 0) {
            result.setMsg("删除成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue(i);
            return result;
        }
        result.setMsg("删除失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

    /**
     * 重新生成html页面
     */
    @Override
    public void generateHtml() {
        InformationExample example = new InformationExample();
        example.createCriteria();
        List<Information> list = informationMapper.selectByExampleWithBLOBs(example);
        for (Information information : list) {
            LableInformation lableInformation = new LableInformation();
            lableInformation.setBodyTitle(information.getBodyTitle());
            lableInformation.setCreateTime(information.getCreateTime());
            lableInformation.setTitle(information.getTitle());
            lableInformation.setContent(information.getContent());
            lableInformation.setCoverUrl(information.getCoverUrl());

            LableInformation outHtmlContent = this.outHtmlContent(lableInformation);
            Information newinformation = new Information();
            newinformation.setId(information.getId());
            newinformation.setPublishUrl(outHtmlContent.getPublishUrl());
            newinformation.setUpdateTime(new Date());
            informationMapper.updateByPrimaryKeySelective(newinformation);
        }

    }

    @Override
    public ResultUtils lableList() {
        ResultUtils result = new ResultUtils();
        LabelExample lableExample = new LabelExample();
        Criteria criteria = lableExample.createCriteria();
        criteria.andIdNotEqualTo(1);
        List<Label> list = labelMapper.selectByExample(lableExample);
        if (null != list && list.size() > 0) {
            result.setMsg("查询成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue(list);
            return result;
        }
        result.setMsg("查询失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

    @Override
    public ResultUtils saveLabel(Label label) {
        ResultUtils result = new ResultUtils();
        int i = labelMapper.insertSelective(label);
        if (i > 0) {
            result.setMsg("添加成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue("");
            return result;
        }
        result.setMsg("添加失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

    @Override
    public ResultUtils scSaveLabel(String scName) {
        ResultUtils result = new ResultUtils();
        SCLable record = new SCLable();
        record.setCreateTime(new Date());
        record.setName(scName);
        record.setType(0);
        int i = sclableMapper.insertSelective(record);
        if (i > 0) {
            result.setMsg("添加成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue("");
            return result;
        }
        result.setMsg("添加失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

    @Override
    public ResultUtils sclabelList() {
        ResultUtils result = new ResultUtils();
        List<SCLable> list = sclableMapper.selectLableAndCount();
        if (null != list && list.size() > 0) {
            result.setMsg("添加成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue(list);
            return result;
        }
        result.setMsg("添加失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

    @Override
    public ResultUtils scImagelList(SearchCondition condition) {
        ResultUtils result = new ResultUtils();
        PageHelper.startPage(condition.getPage(), condition.getSize());

        SCImageExample scImageExample = new SCImageExample();
        scImageExample.setOrderByClause("create_time desc");
        com.quanmin.model.SCImageExample.Criteria criteria = scImageExample.createCriteria();
        if (condition.getId() != null ) {
            criteria.andScIdEqualTo(condition.getId());
        }
        List<SCImage> list = scimageMapper.selectByExample(scImageExample);

        if (null != list && list.size() > 0) {
            result.setMsg("查询成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setCount(((Page) list).getPages());
            result.setValue(list);
            return result;
        }
        result.setMsg("查询失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

    @Override
    public ResultUtils selectInfoByInfoId(SearchCondition condition) {

        Map<String, Object> map = new HashMap<>();
        ResultUtils result = new ResultUtils();
        Information information = informationMapper.selectByPrimaryKey(StringUtil.isEmpty(condition.getInformationId()) ? 0 : Integer.parseInt(condition.getInformationId()));
        map.put("information", information);
        LabelInformationExample example = new LabelInformationExample();
        LabelInformationExample.Criteria criteria = example.createCriteria();
        criteria.andInformationIdEqualTo(Integer.parseInt(condition.getInformationId()));
        List<LabelInformation> labelInformations = labelInformationMapper.selectByExample(example);
        map.put("lableId", labelInformations.get(0).getLabelId());
        map.put("popular", labelInformations.get(0).getPopular());
        map.put("featured", labelInformations.get(0).getFeatured());
        if (null != information) {
            result.setMsg("查询成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue(map);
            return result;
        }
        result.setMsg("查询失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

}
