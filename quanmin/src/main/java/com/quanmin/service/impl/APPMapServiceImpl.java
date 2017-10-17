package com.quanmin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.quanmin.dao.mapper.PositionHistoryMapper;
import com.quanmin.dao.mapper.PositionMapper;
import com.quanmin.model.Position;
import com.quanmin.model.PositionExample;
import com.quanmin.model.custom.PositionHistoryInfo;
import com.quanmin.model.custom.PositionInfo;
import com.quanmin.service.APPMapService;
import com.quanmin.util.Commons;
import com.quanmin.util.ResultUtils;
import com.quanmin.util.alipayutil.Positionutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2017/6/22.
 */
@SuppressWarnings("ALL")
@Service
public class APPMapServiceImpl implements APPMapService {

    //用于返回给ios为空
    private static ArrayList<Object> nullObjects = new ArrayList<>();

    @Autowired
    private PositionHistoryMapper positionHistoryMapper;

    @Autowired
    private PositionMapper positionMapper;
    @Override
    public ResultUtils getPosition(String log, String lat) {
        ResultUtils result = new ResultUtils();
        String positionInfo = Positionutil.getPosition(log, lat);
        JSONObject jsonObject = JSONObject.parseObject(positionInfo);
        JSONObject parseObject = JSONObject.parseObject(jsonObject.get("result").toString());
        String addressComponent = parseObject.get("addressComponent").toString();

        PositionInfo position = JSON.parseObject(addressComponent, PositionInfo.class);

        if ((null == log || "".equals(log)) || (null == lat || "".equals(lat))
                || (null == addressComponent && !"".equals(addressComponent))) {
            result.setSuccess(Commons.DATA_FALSE);
            result.setResultCode(Commons.DATA_ERROR_CODE);
            result.setMsg(Commons.DATA_ERROR_STR);
            return result;
        }
        result.setValue(position);
        result.setSuccess(Commons.DATA_TRUE);
        result.setResultCode(Commons.DATA_SUCCESS_CODE);
        result.setMsg(Commons.DATA_SUCCESS_STR);
        return result;
    }

    @Override
    public ResultUtils getHistoryPositionAndPosition(Integer userId) {
        ResultUtils result = new ResultUtils();
        Map<String, Object> map = new HashMap<>();
        List<PositionHistoryInfo> positionHistoryList = positionHistoryMapper.selectlHistoryByuserId(userId);


        PositionExample positionExample = new PositionExample();
        List<Position> positionList = positionMapper.selectByExample(positionExample);

        List<Position> positionListOpen = new ArrayList<>();
        List<Position> positionListUnOpen = new ArrayList<>();
        for (Position p : positionList) {
            if (p.getType() == 0) {
                positionListOpen.add(p);
                continue;
            }
            if (p.getType() == 1) {
                positionListUnOpen.add(p);
                continue;
            }
        }

        map.put("positionListOpen", null != positionListOpen && positionListOpen.size() > 0 ? positionListOpen : nullObjects);
        map.put("positionListUnOpen", null != positionListUnOpen && positionListUnOpen.size() > 0 ? positionListUnOpen : nullObjects);
        map.put("positionHistoryList", null != positionHistoryList && positionHistoryList.size() > 0 ? positionHistoryList : nullObjects);

        if (map.size() > 0) {
            result.setValue(map);
            result.setSuccess(Commons.DATA_TRUE);
            result.setResultCode(Commons.DATA_SUCCESS_CODE);
            result.setMsg(Commons.DATA_SUCCESS_STR);
            return result;
        }
        result.setSuccess(Commons.DATA_FALSE);
        result.setResultCode(Commons.DATA_ERROR_CODE);
        result.setMsg(Commons.DATA_ERROR_STR);
        return result;

    }
}
