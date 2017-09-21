package com.quanmin.model.custom;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SearchCondition implements Serializable {
    private Integer id;
    private Integer page;
    private Integer size;// 每页显示的条数
    private Integer startNum;// 开始页
    private Integer status;
    private String searchKey;// 关键词
    private String informationId;// 内容id
    private Integer type;
    private String sex;
    private String area;
    private String age;
    private String userId;// 用户id
    private String featured;//是否热门 0 是，1不是
    private String popular;//是否流行 0是，1不是
    private String labelId;//标签id
    private String publishStatus;//发布状态 0未发布，1发布
    private String bbsInformationId;//帖子id
    private Integer provinceId;//省id
    private String company;//供应商

    private String startTime;//开始时间
    private String endTime;//结束时间

    private Integer timeType;//时间类型，1，1天内，2，三天内，3，一周内，4两周内，5一个月内

    private String checkDate;//筛查日期
    private Integer roleId;//角色id

    private Integer departmentId;//部门id

    private Long categoryId;//中药的类别id

}
