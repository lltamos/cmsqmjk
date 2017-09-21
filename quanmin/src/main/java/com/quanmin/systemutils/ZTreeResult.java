package com.quanmin.systemutils;

/**
 * Created by yang on 2017/6/29.
 * 用户ztree数返回所需要的数据
 */

public class ZTreeResult {
    private  Integer id;
    private  Integer pId ;
    private String name;
    private boolean open;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

}
