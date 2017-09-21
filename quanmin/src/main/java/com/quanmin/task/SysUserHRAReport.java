package com.quanmin.task;

import com.quanmin.service.CMSReportService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yang on 2017/7/7.
 */

/**
 * 同步报告
 */
public class SysUserHRAReport {

    @Autowired
    private CMSReportService reportService;


    /**
     * 获取所有用户的hra报告
     */
    public void getHRAUserReport() {
        //        PageHelper.startPage(condition.getPage(), condition.getSize(), true);
        reportService.getHRAUserReport();

    }
}
