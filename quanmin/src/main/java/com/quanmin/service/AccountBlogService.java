package com.quanmin.service;

import com.quanmin.util.ResultUtils;

public interface AccountBlogService {


    ResultUtils inAccount(Integer type,Integer size,Integer page,String queryStr);

    ResultUtils outAccount(Integer type,Integer size,Integer page,String queryStr);


}
