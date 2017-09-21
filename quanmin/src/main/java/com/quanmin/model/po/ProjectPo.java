package com.quanmin.model.po;

import com.quanmin.model.Project;

/**
 * Created by llsmp on 2017/7/20.
 */
public class ProjectPo extends Project {
    private String explain;

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
        setDescription( explain );

    }


}
