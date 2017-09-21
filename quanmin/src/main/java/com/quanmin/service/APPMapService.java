package com.quanmin.service;

import com.quanmin.util.ResultUtils;

/**
 * Created by yang on 2017/6/22.
 * 位置
 */
public interface APPMapService {
    /**
     * 获取位置信息
     * @param log 经度
     * @param lat 纬度
     * @return
     */
    ResultUtils getPosition(String log, String lat);

    /**
     * 加载位置的历史记录和开通城市以及未开通城市
     * @param userId
     * @return
     */
    ResultUtils getHistoryPositionAndPosition(Integer userId);



}
