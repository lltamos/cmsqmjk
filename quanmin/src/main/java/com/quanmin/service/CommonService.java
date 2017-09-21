package com.quanmin.service;

import com.quanmin.model.jpapo.ShopDict;

import java.util.List;

public interface CommonService {
    List<ShopDict> shopDictList(Integer type);
}
