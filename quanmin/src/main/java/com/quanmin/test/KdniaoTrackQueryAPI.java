package com.quanmin.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.quanmin.model.custom.ExpressDataInfo;
import com.quanmin.util.ExpressUtils;

import java.util.List;


/**
 *
 * 快递鸟物流轨迹即时查询接口
 *
 * @技术QQ群: 456320272
 * @see: http://www.kdniao.com/YundanChaxunAPI.aspx
 * @copyright: 深圳市快金数据技术服务有限公司
 *
 * DEMO中的电商ID与私钥仅限测试使用，正式环境请单独注册账号
 * 单日超过500单查询量，建议接入我方物流轨迹订阅推送接口
 *
 * ID和Key请到官网申请：http://www.kdniao.com/ServiceApply.aspx
 */

public class KdniaoTrackQueryAPI {

    //DEMO
    public static void main(String[] args) {
        ExpressUtils api = new ExpressUtils();
        try {
            String result = api.getOrderTracesByJson("STO", "402642314609");
            JSONObject rootjsonBean = JSONObject.parseObject(result);
            JSONArray traces = rootjsonBean.getJSONArray("Traces");
            String js=JSONObject.toJSONString(traces);
            List<ExpressDataInfo> collection = JSONObject.parseArray(js, ExpressDataInfo.class);

            System.out.println(collection.size());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
