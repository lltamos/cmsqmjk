package com.quanmin.service;

import com.quanmin.model.SysArea;
import com.quanmin.model.custom.SearchCondition;

import java.util.List;

/**
 * Created by yang on 2017/6/27.
 */
public interface CMSAreaService {

    List<SysArea> getAreaListAll(SearchCondition condition);
}
