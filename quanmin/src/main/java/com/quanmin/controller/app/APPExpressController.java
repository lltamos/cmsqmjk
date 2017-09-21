package com.quanmin.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quanmin.model.custom.ExpressDataInfo;
import com.quanmin.util.ExpressUtils;
import com.quanmin.util.ResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 快递查询
 *
 * @author heasy
 */
@Controller
@RequestMapping(value = "/api/1/")
public class APPExpressController {

    /**
     * 获取快递信息
     *
     * @return
     */
    @RequestMapping(value = "/getexpressinfo")
    @ResponseBody
    public ResultUtils getExpressInfo(String expCode, String expNo) {
        ExpressUtils api = new ExpressUtils();

        try {
            String result = api.getOrderTracesByJson(expCode, expNo);
//            数据解析
            JSONObject rootjsonBean = JSONObject.parseObject(result);
            JSONArray traces = rootjsonBean.getJSONArray("Traces");
            String js = JSONObject.toJSONString(traces);
            List<ExpressDataInfo> collection = JSONObject.parseArray(js, ExpressDataInfo.class);


            return null != collection && collection.size() > 0 ? ResultUtils.returnSucess(collection) : ResultUtils.returnFail();

        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.returnFail();
        }
    }

}
