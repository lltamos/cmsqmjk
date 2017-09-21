package com.quanmin.model.po;

import com.quanmin.model.Recuperate;
import com.quanmin.model.custom.PhpRecuperateInfo;

import java.util.List;

/**
 * Created by llsmp on 2017/7/20.
 */
public class RecuperatePo extends Recuperate {

    private List<PhpRecuperateInfo.ValueBean.ProjectinfoBean> projectinfo;

    public List<PhpRecuperateInfo.ValueBean.ProjectinfoBean> getProjectinfo() {
        return projectinfo;
    }

    public void setProjectinfo(List<PhpRecuperateInfo.ValueBean.ProjectinfoBean> projectinfo) {
        this.projectinfo = projectinfo;
    }
}
