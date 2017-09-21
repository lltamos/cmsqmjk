package com.quanmin.controller.app;

import com.quanmin.service.APPMapService;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yang on 2017/6/22.
 * 关于位置
 */
@RestController
@RequestMapping("/api/1/")
public class APPMapController {

    @Autowired
    private APPMapService mapService;
    /**
     * 获取位置信息
     */
    @RequestMapping("getposition")
    ResultUtils getPosition(String log, String lat){
        ResultUtils position = mapService.getPosition(log, lat);
        return position;
    }

    /**
     * 加载位置的历史记录和开通城市以及未开通城市
     * @param userId
     * @return
     */
    @RequestMapping("gethistorypositionandposition")
   public ResultUtils getHistoryPositionAndPosition(Integer userId){
       ResultUtils historyPositionAndPosition = mapService.getHistoryPositionAndPosition(userId);
       return  historyPositionAndPosition;

   }
}
