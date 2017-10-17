package com.quanmin.model;

public class RecuperateWithBLOBs extends Recuperate {
    private String info;

    private String explain;

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
    }
}