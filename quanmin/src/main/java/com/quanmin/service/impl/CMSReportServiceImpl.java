package com.quanmin.service.impl;

import com.github.pagehelper.PageHelper;
import com.quanmin.call.UserReportPDFInfo;
import com.quanmin.dao.mapper.SysUserMapper;
import com.quanmin.dao.mapper.UserHraReportMapper;
import com.quanmin.model.SysUser;
import com.quanmin.model.SysUserExample;
import com.quanmin.model.UserHraReport;
import com.quanmin.model.UserHraReportExample;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.service.CMSReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2017/6/27.
 */
@Service
public class CMSReportServiceImpl implements CMSReportService {


    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private UserHraReportMapper userHraReportMapper;

    @Override
    public   List<UserHraReport> getUserReprotListBySearchCondition(SearchCondition condition) {

        PageHelper.startPage(condition.getPage(), condition.getSize(), true);

        List<UserHraReport> list = userHraReportMapper.getUserReprotListBySearchCondition(condition);

        return list;
    }

    @Override
    public void getHRAUserReport() {
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria().andUserTypeEqualTo("0");
        List<SysUser> list = sysUserMapper.selectByExample(userExample);
        if (null != list && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                List<Map<String, String>> userReportPDFList = UserReportPDFInfo.getUserReportPDFInfoByPhoneAndIdNo(list.get(i).getPhone(), list.get(i).getIdNo());
                for (int x = 0; x < userReportPDFList.size(); x++) {

                    UserHraReport userHraReport = new UserHraReport();
                    userHraReport.setCreateTime(new Date());
                    userHraReport.setHraId(Integer.parseInt(userReportPDFList.get(x).get("reportId")));
                    userHraReport.setHraReportTime(userReportPDFList.get(x).get("creattime"));
                    userHraReport.setHraReportUrl(userReportPDFList.get(x).get("reporturl"));
                    userHraReport.setHraUserId(null);
                    userHraReport.setUserId(list.get(i).getId());
                    userHraReport.setHraUserName(list.get(i).getName());

                    UserHraReportExample hraexample = new UserHraReportExample();
                    hraexample.createCriteria().andHraIdEqualTo(Integer.parseInt(userReportPDFList.get(x).get("reportId")));
                    List<UserHraReport> userHraReportsList = userHraReportMapper.selectByExample(hraexample);

                    if (userHraReportsList.size() == 0) {
                        userHraReportMapper.insertSelective(userHraReport);
                    }
                }

            }
        }

    }
}
