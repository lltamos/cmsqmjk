package com.quanmin.service;

import com.quanmin.model.UserHraReport;
import com.quanmin.model.custom.SearchCondition;

import java.util.List;

/**
 * Created by yang on 2017/6/27.
 */
public interface CMSReportService {

    /**
     * 查询所有用户报告列表
     * @param condition
     * @return
     */
    List<UserHraReport> getUserReprotListBySearchCondition(SearchCondition condition);

    /**
     * 生成报告
     * @return
     */
   void getHRAUserReport();
}
